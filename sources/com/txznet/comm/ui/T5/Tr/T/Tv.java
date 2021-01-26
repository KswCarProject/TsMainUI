package com.txznet.comm.ui.T5.Tr.T;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.ts.factoryset.FsCanActivity;
import com.txznet.comm.Tr.Tr.Tn;
import com.txznet.comm.ui.T5.T.TM;
import com.txznet.comm.ui.T5.Tn;
import com.txznet.comm.ui.T5.Tr.Tx;
import com.txznet.comm.ui.T9.Tk;
import com.txznet.comm.ui.TE.T9;
import com.txznet.comm.ui.TE.Tr;
import com.txznet.txz.util.TE;

/* compiled from: Proguard */
public class Tv extends Tx {
    private static Tv Tr = new Tv();
    /* access modifiers changed from: private */

    /* renamed from: T  reason: collision with root package name */
    public T f497T = null;
    private View.OnFocusChangeListener T9 = new View.OnFocusChangeListener() {
        public void onFocusChange(View v, boolean hasFocus) {
            Tv.this.f497T.Tn = (TextView) v;
        }
    };
    private TextWatcher TZ = new TextWatcher() {
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (TextUtils.isEmpty(Tv.this.f497T.Tr.getEditableText().toString())) {
                Tv.this.f497T.f503T.setSelected(false);
            } else {
                Tv.this.f497T.f503T.setSelected(true);
            }
        }

        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        public void afterTextChanged(Editable s) {
        }
    };
    private TextView.OnEditorActionListener Tk = new TextView.OnEditorActionListener() {
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if (event != null) {
                Tn.Tn("KeyEvent:" + event.getAction());
            }
            Tv.this.Tk();
            Tk.Tr().Tr(Tv.this.f497T.Tr.getText().toString());
            return true;
        }
    };
    private View.OnClickListener Tn = new View.OnClickListener() {
        public void onClick(View v) {
            if (Tv.this.f497T == null) {
                return;
            }
            if (v == Tv.this.f497T.Ty) {
                Tv.this.Tk();
                Tk.Tr().Ty();
            } else if (v == Tv.this.f497T.f503T) {
                Tv.this.Tk();
                Tk.Tr().Tr(Tv.this.f497T.Tr.getText().toString());
            }
        }
    };

    /* compiled from: Proguard */
    class T {

        /* renamed from: T  reason: collision with root package name */
        LinearLayout f503T;
        View T9;
        TextView Tn;
        EditText Tr;
        RelativeLayout Ty;

        T() {
        }
    }

    public static Tv T() {
        return Tr;
    }

    private Tv() {
    }

    public Tn.T T(TM data) {
        Tn.T adapter = new Tn.T();
        adapter.Tr = T9();
        adapter.f466T = 28;
        return adapter;
    }

    private View T9() {
        this.f497T = new T();
        LinearLayout layout = new LinearLayout(com.txznet.comm.Tr.T.Tr());
        this.f497T.T9 = layout;
        layout.setBackgroundDrawable(Tr.Ty("widget_color"));
        layout.setOrientation(1);
        RelativeLayout rlTitle = new RelativeLayout(com.txznet.comm.Tr.T.Tr());
        rlTitle.setPadding((int) Tr.Tn("x30"), 0, (int) Tr.Tn("x60"), 0);
        rlTitle.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        layout.addView(rlTitle);
        RelativeLayout rlBack = new RelativeLayout(com.txznet.comm.Tr.T.Tr());
        this.f497T.Ty = rlBack;
        rlBack.setClickable(true);
        RelativeLayout.LayoutParams rlLayoutParams = new RelativeLayout.LayoutParams(-2, -2);
        rlLayoutParams.addRule(15);
        rlBack.setLayoutParams(rlLayoutParams);
        rlTitle.addView(rlBack);
        ImageView ivBack = new ImageView(com.txznet.comm.Tr.T.Tr());
        ivBack.setScaleType(ImageView.ScaleType.FIT_END);
        ivBack.setImageDrawable(Tr.Ty("button_back"));
        RelativeLayout.LayoutParams rlLayoutParams2 = new RelativeLayout.LayoutParams(-2, (int) Tr.Tn("y16"));
        rlLayoutParams2.addRule(15);
        rlLayoutParams2.addRule(9);
        rlLayoutParams2.rightMargin = (int) Tr.Tn("x20");
        ivBack.setId(T9.T());
        ivBack.setLayoutParams(rlLayoutParams2);
        rlBack.addView(ivBack);
        TextView tvBack = new TextView(com.txznet.comm.Tr.T.Tr());
        tvBack.setTextColor(Color.parseColor("#FFFFFF"));
        tvBack.setHintTextColor(Color.parseColor("#40454b"));
        tvBack.setTextSize(31.0f);
        tvBack.setText("返回");
        RelativeLayout.LayoutParams rlLayoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
        rlLayoutParams3.addRule(15);
        rlLayoutParams3.addRule(1, ivBack.getId());
        tvBack.setLayoutParams(rlLayoutParams3);
        rlBack.addView(tvBack);
        TextView tvTitle = new TextView(com.txznet.comm.Tr.T.Tr());
        tvTitle.setTextColor(Color.parseColor("#FFFFFF"));
        tvTitle.setHintTextColor(Color.parseColor("#40454b"));
        tvTitle.setTextSize(37.0f);
        tvTitle.setText("修改关键字");
        tvTitle.setGravity(17);
        RelativeLayout.LayoutParams rlLayoutParams4 = new RelativeLayout.LayoutParams(-2, -2);
        rlLayoutParams4.addRule(13);
        tvTitle.setLayoutParams(rlLayoutParams4);
        rlTitle.addView(tvTitle);
        LinearLayout llEdit = new LinearLayout(com.txznet.comm.Tr.T.Tr());
        llEdit.setPadding((int) Tr.Tn("x60"), 0, (int) Tr.Tn("x60"), 0);
        llEdit.setOrientation(0);
        llEdit.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        layout.addView(llEdit);
        final EditText edDest = new EditText(com.txznet.comm.Tr.T.Tr());
        this.f497T.Tr = edDest;
        edDest.setBackgroundDrawable(Tr.Ty("search_edit_bg"));
        edDest.setFilters(new InputFilter[]{new InputFilter.LengthFilter(20)});
        edDest.setImeOptions(268435456);
        edDest.setHint("请输入您的目的地");
        edDest.setPadding((int) Tr.Tn("x24"), 0, 0, 0);
        edDest.setSingleLine();
        edDest.setTextColor(Color.parseColor("#FFFFFF"));
        edDest.setHintTextColor(Color.parseColor("#40454b"));
        edDest.setCursorVisible(false);
        edDest.setTextSize(38.0f);
        LinearLayout.LayoutParams llLayoutParams = new LinearLayout.LayoutParams(0, (int) Tr.Tn("y90"), 1.0f);
        llLayoutParams.setMargins(0, 0, 0, 0);
        edDest.setLayoutParams(llLayoutParams);
        llEdit.addView(edDest);
        final LinearLayout llSearch = new LinearLayout(com.txznet.comm.Tr.T.Tr());
        this.f497T.f503T = llSearch;
        llSearch.setBackgroundDrawable(Tr.Ty("activity_home_search_bg"));
        llSearch.setGravity(17);
        llSearch.setOrientation(1);
        llSearch.setClickable(true);
        llSearch.setFocusable(true);
        llSearch.setBackgroundDrawable(Tr.Ty("search_btn_bg"));
        LinearLayout.LayoutParams llLayoutParams2 = new LinearLayout.LayoutParams((int) Tr.Tn("x150"), (int) Tr.Tn("y90"));
        llLayoutParams2.gravity = 17;
        llSearch.setLayoutParams(llLayoutParams2);
        llEdit.addView(llSearch);
        TextView textView = new TextView(com.txznet.comm.Tr.T.Tr());
        textView.setGravity(17);
        textView.setPadding(0, 0, 0, 0);
        textView.setTextSize(36.0f);
        textView.setBackgroundColor(0);
        textView.setTextColor(T(Color.parseColor("#FFFFFF"), Color.parseColor("#8ce3fd"), Color.parseColor("#8ce3fd"), FsCanActivity.DOOR_UPDATE_ALL));
        textView.setText("搜索");
        LinearLayout.LayoutParams llLayoutParams3 = new LinearLayout.LayoutParams(-2, -2);
        llLayoutParams3.gravity = 17;
        textView.setLayoutParams(llLayoutParams3);
        llSearch.addView(textView);
        rlBack.setOnClickListener(this.Tn);
        llSearch.setOnClickListener(this.Tn);
        edDest.setOnFocusChangeListener(this.T9);
        edDest.setOnEditorActionListener(this.Tk);
        edDest.addTextChangedListener(this.TZ);
        final TextView textView2 = textView;
        edDest.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                edDest.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                Drawable edDrawable = Tr.Ty("search_edit_bg");
                Drawable btDrawable = Tr.Ty("search_btn_bg");
                if (edDrawable != null) {
                    edDest.setBackgroundDrawable(edDrawable);
                }
                if (btDrawable != null) {
                    llSearch.setBackgroundDrawable(btDrawable);
                    textView2.setText("搜索");
                    textView2.setTextColor(Tv.this.T(Color.parseColor("#FFFFFF"), Color.parseColor("#8ce3fd"), Color.parseColor("#8ce3fd"), FsCanActivity.DOOR_UPDATE_ALL));
                }
            }
        });
        if (TE.T("keyboardFullScreen", false)) {
            edDest.setImeOptions(edDest.getImeOptions() & -268435457 & -33554433);
        }
        return layout;
    }

    public void Tr() {
    }

    /* access modifiers changed from: private */
    public void Tk() {
        if (this.f497T != null && this.f497T.Tn != null) {
            ((InputMethodManager) com.txznet.comm.Tr.T.Tr().getSystemService("input_method")).hideSoftInputFromWindow(this.f497T.Tn.getWindowToken(), 0);
        }
    }

    /* access modifiers changed from: private */
    public ColorStateList T(int normal, int pressed, int focused, int unable) {
        return new ColorStateList(new int[][]{new int[]{16842919, 16842910}, new int[]{16842910, 16842908}, new int[]{16842910}, new int[]{16842908}, new int[]{16842909}, new int[0]}, new int[]{pressed, focused, normal, focused, unable, normal});
    }
}
