package com.ts.main.skin.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.StateSet;
import android.util.TypedValue;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.SdkConstants;
import com.android.internal.R;
import com.ts.main.common.WinShow;
import com.ts.other.CustomImgView;
import com.ts.other.ParamButton;
import com.yyw.ts70xhw.Mcu;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import org.xmlpull.v1.XmlPullParser;

@SuppressLint({"NewApi"})
public class SkinUtils implements Application.ActivityLifecycleCallbacks, ViewGroup.OnHierarchyChangeListener {
    private static final String ACTION_LIFE_CYCLE_CHANGE = "com.android.activity.lifecyclechange";
    /* access modifiers changed from: private */
    public static SkinUtils mUtils = new SkinUtils();
    Map<Activity, List<View>> mActivityTreeMap = new HashMap();
    private List<View> mCurViewList = null;
    BroadcastReceiver mLifecycleReciver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("android.intent.action.CONFIGURATION_CHANGED")) {
                SkinUtils.this.IllStateChange(Mcu.GetIll());
            }
        }
    };
    WeakHashMap<View, Drawable> mViewCache = new WeakHashMap<>();

    public static SkinUtils getInstance() {
        return mUtils;
    }

    public void registerBroadCast(Context context) {
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.intent.action.CONFIGURATION_CHANGED");
        context.registerReceiver(this.mLifecycleReciver, filter);
    }

    public void onActivityCreated(Activity activity, Bundle bundle) {
        String className = activity.getClass().getName();
        if (className.startsWith("com.ts.main.radio") || className.startsWith("com.ts.bt") || (className.startsWith("com.ts.set") && !className.startsWith("com.ts.set.dsp") && !className.equals("com.ts.set.SettingSoundActivity"))) {
            if (this.mActivityTreeMap.get(activity) == null) {
                this.mActivityTreeMap.put(activity, new ArrayList<>());
            }
            this.mCurViewList = this.mActivityTreeMap.get(activity);
            LayoutInflater.from(activity).setFactory2(new LayoutInflater.Factory2() {
                public View onCreateView(String name, Context context, AttributeSet attr) {
                    return null;
                }

                public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
                    if (parent != null) {
                        ((ViewGroup) parent).setOnHierarchyChangeListener(SkinUtils.mUtils);
                    }
                    int background = SkinUtils.this.getAttrBackground(context, attrs);
                    try {
                        View view = (View) Class.forName("android.widget." + name).getDeclaredConstructor(new Class[]{Context.class, AttributeSet.class}).newInstance(new Object[]{context, attrs});
                        view.setBackgroundResource(background);
                        return view;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }
                }
            });
        }
    }

    public void onActivityDestroyed(Activity activity) {
        if (this.mActivityTreeMap.containsKey(activity)) {
            this.mActivityTreeMap.remove(activity);
        }
    }

    public void onActivityPaused(Activity activity) {
    }

    public void onActivityResumed(Activity activity) {
        this.mCurViewList = this.mActivityTreeMap.get(activity);
        IllStateChange(Mcu.GetIll());
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle arg1) {
    }

    public void onActivityStarted(Activity activity) {
    }

    public void onActivityStopped(Activity activity) {
    }

    private void traverseViews(ViewGroup vg, List<View> list) {
        for (int i = 0; i < vg.getChildCount(); i++) {
            View v = vg.getChildAt(i);
            if (v instanceof ViewGroup) {
                traverseViews((ViewGroup) v, list);
                ((ViewGroup) v).setOnHierarchyChangeListener(mUtils);
            }
            if (!list.contains(vg.getChildAt(i))) {
                list.add(vg.getChildAt(i));
            }
        }
    }

    public void onChildViewAdded(View parent, final View child) {
        if (parent != null) {
            ((ViewGroup) parent).setOnHierarchyChangeListener(mUtils);
        }
        if (child.getContext() instanceof Activity) {
            this.mCurViewList = this.mActivityTreeMap.get((Activity) child.getContext());
        }
        if (child instanceof ViewGroup) {
            ((ViewGroup) child).setOnHierarchyChangeListener(mUtils);
            traverseViews((ViewGroup) child, this.mCurViewList);
        } else if (child instanceof TextView) {
            int id = child.getId();
            if (id <= 0 || child.getContext().getResources().getResourcePackageName(id).equals("com.ts.MainUI")) {
                ((TextView) child).addTextChangedListener(new TextWatcher() {
                    public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                    }

                    public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                    }

                    public void afterTextChanged(Editable arg0) {
                        if (Mcu.GetIll() == 1) {
                            if (((TextView) child).getTextColors().getDefaultColor() == -16777216) {
                                ((TextView) child).setTextColor(-1);
                            }
                        } else if (((TextView) child).getTextColors().getDefaultColor() == -1) {
                            ((TextView) child).setTextColor(ViewCompat.MEASURED_STATE_MASK);
                        }
                    }
                });
            } else {
                return;
            }
        }
        if (this.mCurViewList == null) {
            Context context = child.getContext();
            if (context instanceof Activity) {
                this.mCurViewList = this.mActivityTreeMap.get((Activity) context);
            }
        }
        if (this.mCurViewList != null) {
            if (!this.mCurViewList.contains(parent)) {
                this.mCurViewList.add(parent);
            }
            if (!this.mCurViewList.contains(child)) {
                this.mCurViewList.add(child);
            }
        }
        changeViewTheme(Mcu.GetIll(), child);
    }

    /* access modifiers changed from: protected */
    public void changeImageViewSrc(ImageView child, int illState) {
        int background;
        try {
            Field field = ImageView.class.getDeclaredField("mResource");
            field.setAccessible(true);
            int background2 = field.getInt(child);
            if (background2 != 0) {
                String name = child.getResources().getResourceEntryName(background2);
                if (name == null) {
                    return;
                }
                if (name.endsWith("_night") && illState == 0) {
                    int newId = child.getResources().getIdentifier(name.substring(0, name.length() - "_night".length()), "drawable", child.getContext().getPackageName());
                    if (newId > 0) {
                        child.setImageResource(newId);
                    }
                } else if (!name.endsWith("_night") && illState == 1) {
                    if (child.getDrawable() instanceof StateListDrawable) {
                        Drawable drawable = this.mViewCache.get(child);
                        if (drawable == null) {
                            drawable = createThemeDrawable(child.getResources(), background2, "night");
                        }
                        if (drawable != null) {
                            child.setImageDrawable(drawable);
                            this.mViewCache.put(child, drawable);
                        }
                    }
                    int newId2 = child.getResources().getIdentifier(String.valueOf(name) + "_night", "drawable", child.getContext().getPackageName());
                    if (newId2 > 0) {
                        child.setImageResource(newId2);
                    }
                }
            } else if ((child.getDrawable() instanceof ThemeStateListDrawable) && (background = ((ThemeStateListDrawable) child.getBackground()).getmOriginalBgId()) != 0) {
                if (illState == 1) {
                    Drawable drawable2 = this.mViewCache.get(child);
                    if (drawable2 == null) {
                        drawable2 = createThemeDrawable(child.getResources(), background, "night");
                    }
                    if (drawable2 != null) {
                        child.setImageDrawable(drawable2);
                        this.mViewCache.put(child, drawable2);
                        return;
                    }
                    return;
                }
                child.setImageResource(background);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: protected */
    public void changeBackground(View child, int illState) {
        int background;
        try {
            Field field = View.class.getDeclaredField("mBackgroundResource");
            field.setAccessible(true);
            int background2 = field.getInt(child);
            if (background2 != 0) {
                String name = child.getResources().getResourceEntryName(background2);
                if (name == null) {
                    return;
                }
                if (name.endsWith("_night") && illState == 0) {
                    int newId = child.getResources().getIdentifier(name.substring(0, name.length() - "_night".length()), "drawable", child.getContext().getPackageName());
                    if (newId > 0) {
                        child.setBackgroundResource(newId);
                    }
                } else if (!name.endsWith("_night") && illState == 1) {
                    if (child.getBackground() instanceof StateListDrawable) {
                        Drawable drawable = this.mViewCache.get(child);
                        if (drawable == null) {
                            drawable = createThemeDrawable(child.getResources(), background2, "night");
                        }
                        if (drawable != null) {
                            child.setBackground(drawable);
                            this.mViewCache.put(child, drawable);
                        }
                    }
                    int newId2 = child.getResources().getIdentifier(String.valueOf(name) + "_night", "drawable", child.getContext().getPackageName());
                    if (newId2 > 0) {
                        child.setBackgroundResource(newId2);
                    }
                }
            } else if ((child.getBackground() instanceof ThemeStateListDrawable) && (background = ((ThemeStateListDrawable) child.getBackground()).getmOriginalBgId()) != 0) {
                if (illState == 1) {
                    Drawable drawable2 = this.mViewCache.get(child);
                    if (drawable2 == null) {
                        drawable2 = createThemeDrawable(child.getResources(), background, "night");
                    }
                    if (drawable2 != null) {
                        child.setBackground(drawable2);
                        this.mViewCache.put(child, drawable2);
                        return;
                    }
                    return;
                }
                child.setBackgroundResource(background);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onChildViewRemoved(View parent, View child) {
        this.mViewCache.remove(child);
    }

    /* access modifiers changed from: protected */
    public int getAttrBackground(Context context, AttributeSet attrs) {
        int background = 0;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.View);
        if (a.hasValue(13)) {
            TypedValue value = new TypedValue();
            a.getValue(13, value);
            background = value.resourceId;
        }
        a.recycle();
        return background;
    }

    /* access modifiers changed from: protected */
    public int getAttrImageViewSrc(Context context, AttributeSet attrs) {
        int src = 0;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ImageView);
        if (a.hasValue(0)) {
            TypedValue value = new TypedValue();
            a.getValue(0, value);
            src = value.resourceId;
        }
        a.recycle();
        return src;
    }

    public void IllStateChange(int nILL) {
        String activityName = WinShow.getTopActivityName();
        if (activityName != null) {
            for (Map.Entry<Activity, List<View>> entry : this.mActivityTreeMap.entrySet()) {
                if (entry.getKey().getClass().getName().equals(activityName)) {
                    for (View view : entry.getValue()) {
                        changeViewTheme(nILL, view);
                    }
                    return;
                }
            }
        }
        if (this.mCurViewList != null) {
            for (View view2 : this.mCurViewList) {
                changeViewTheme(nILL, view2);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void changeViewTheme(int nILL, View view) {
        if (view instanceof ParamButton) {
            if (nILL == 1) {
                ((ParamButton) view).setTheme("night");
            } else {
                ((ParamButton) view).setTheme((String) null);
            }
        } else if (view instanceof CustomImgView) {
            if (nILL == 1) {
                ((CustomImgView) view).applyTheme("night");
            } else {
                ((CustomImgView) view).applyTheme((String) null);
            }
        } else if (view instanceof ViewGroup) {
            if (view.getBackground() instanceof ColorDrawable) {
                ColorDrawable drawable = (ColorDrawable) view.getBackground();
                if (nILL == 1 && drawable.getColor() == -1) {
                    view.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
                } else if (nILL == 0 && drawable.getColor() == -16777216) {
                    view.setBackgroundColor(-1);
                }
            }
            changeBackground(view, nILL);
        } else {
            if (view instanceof ImageView) {
                changeImageViewSrc((ImageView) view, nILL);
            }
            changeBackground(view, nILL);
        }
        if (view instanceof TextView) {
            int id = view.getId();
            if (id > 0 && !view.getContext().getResources().getResourcePackageName(id).equals("com.ts.MainUI")) {
                return;
            }
            if (nILL == 1) {
                if (((TextView) view).getTextColors().getDefaultColor() == -16777216) {
                    ((TextView) view).setTextColor(-1);
                }
            } else if (((TextView) view).getTextColors().getDefaultColor() == -1) {
                ((TextView) view).setTextColor(ViewCompat.MEASURED_STATE_MASK);
            }
        }
    }

    private StateListDrawable createThemeDrawable(Resources r, int id, String themeName) throws Exception {
        XmlPullParser parser = r.getXml(id);
        AttributeSet attrs = Xml.asAttributeSet(parser);
        int innerDepth = parser.getDepth() + 1;
        StateListDrawable drawable = null;
        while (true) {
            int type = parser.next();
            if (type == 1 || (parser.getDepth() < innerDepth && type == 3)) {
                return drawable;
            }
            if (type == 2 && parser.getName().equals(SdkConstants.TAG_ITEM)) {
                if (drawable == null) {
                    drawable = new ThemeStateListDrawable(id);
                }
                TypedArray a = r.obtainAttributes(attrs, R.styleable.StateListDrawableItem);
                TypedValue value = new TypedValue();
                a.getValue(0, value);
                int drawableId = value.resourceId;
                a.recycle();
                String entryName = r.getResourceEntryName(drawableId);
                if (entryName.endsWith(themeName)) {
                    continue;
                } else {
                    int newId = r.getIdentifier(String.valueOf(entryName) + "_" + themeName, "drawable", r.getResourcePackageName(drawableId));
                    if (newId <= 0) {
                        throw new Exception("can't find theme resource with name " + entryName + "_" + themeName);
                    }
                    drawable.addState(extractStateSet(attrs), r.getDrawable(newId));
                }
            }
        }
        return drawable;
    }

    /* access modifiers changed from: package-private */
    public int[] extractStateSet(AttributeSet attrs) {
        int j;
        int numAttrs = attrs.getAttributeCount();
        int[] states = new int[numAttrs];
        int i = 0;
        int j2 = 0;
        while (i < numAttrs) {
            int stateResId = attrs.getAttributeNameResource(i);
            switch (stateResId) {
                case 0:
                    j = j2;
                    break;
                case 16842960:
                case 16843161:
                    j = j2;
                    break;
                default:
                    j = j2 + 1;
                    if (!attrs.getAttributeBooleanValue(i, false)) {
                        stateResId = -stateResId;
                    }
                    states[j2] = stateResId;
                    break;
            }
            i++;
            j2 = j;
        }
        return StateSet.trimStateSet(states, j2);
    }
}
