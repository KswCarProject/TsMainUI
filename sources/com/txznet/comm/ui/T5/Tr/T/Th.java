package com.txznet.comm.ui.T5.Tr.T;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.txznet.comm.ui.T5.T.TA;
import com.txznet.comm.ui.T5.T.TM;
import com.txznet.comm.ui.T5.Tn;
import com.txznet.comm.ui.T5.Tr.T0;
import com.txznet.comm.ui.T9.Tk;
import com.txznet.comm.ui.TE.T9;
import com.yyw.ts70xhw.Radio;
import java.util.List;

/* compiled from: Proguard */
public class Th extends T0 {
    private static Th Tn = new Th();

    /* renamed from: T  reason: collision with root package name */
    AdapterView.OnItemClickListener f484T = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
            try {
                T.C0019T viewHolder = (T.C0019T) view.getTag();
                viewHolder.Ty.setVisibility(0);
                view.setBackgroundColor(Color.rgb(16, 174, 255));
                Tk.Tr().Ty(viewHolder.Tr.getText().toString().trim());
            } catch (Exception e) {
            }
        }
    };
    private TA T9;
    private View.OnClickListener Tk = new View.OnClickListener() {
        public void onClick(View v) {
            if (Th.this.Tr != null && Th.this.Tr.Tv == v) {
                Tk.Tr().Tn();
            }
        }
    };
    /* access modifiers changed from: private */
    public Tr Tr = null;

    /* compiled from: Proguard */
    class Tr {

        /* renamed from: T  reason: collision with root package name */
        View f489T;
        ListView T5 = null;
        ListView T9 = null;
        TextView TE = null;
        ListView TZ = null;
        TextView Tk = null;
        TextView Tn = null;
        TextView Tr = null;
        RelativeLayout Tv = null;
        ListView Ty = null;

        Tr() {
        }
    }

    public static Th T() {
        return Tn;
    }

    private Th() {
    }

    public Tn.T T(TM data) {
        this.T9 = (TA) data;
        Tn.T adapter = new Tn.T();
        adapter.Tr = T9();
        adapter.f466T = 29;
        return adapter;
    }

    private View T9() {
        this.Tr = new Tr();
        LinearLayout layout = new LinearLayout(com.txznet.comm.Tr.T.Tr());
        this.Tr.f489T = layout;
        layout.setBackgroundColor(Color.parseColor("#FF0A0A0A"));
        layout.setOrientation(1);
        RelativeLayout relativeLayout = new RelativeLayout(com.txznet.comm.Tr.T.Tr());
        relativeLayout.setPadding((int) com.txznet.comm.ui.TE.Tr.Tn("x30"), 0, (int) com.txznet.comm.ui.TE.Tr.Tn("x60"), 0);
        relativeLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        layout.addView(relativeLayout);
        RelativeLayout rlBack = new RelativeLayout(com.txznet.comm.Tr.T.Tr());
        this.Tr.Tv = rlBack;
        rlBack.setClickable(true);
        RelativeLayout.LayoutParams rlLayoutParams = new RelativeLayout.LayoutParams(-2, -2);
        rlLayoutParams.addRule(15);
        rlBack.setLayoutParams(rlLayoutParams);
        relativeLayout.addView(rlBack);
        ImageView ivBack = new ImageView(com.txznet.comm.Tr.T.Tr());
        ivBack.setScaleType(ImageView.ScaleType.FIT_END);
        ivBack.setImageDrawable(com.txznet.comm.ui.TE.Tr.Ty("button_back"));
        RelativeLayout.LayoutParams rlLayoutParams2 = new RelativeLayout.LayoutParams(-2, (int) com.txznet.comm.ui.TE.Tr.Tn("y16"));
        rlLayoutParams2.addRule(15);
        rlLayoutParams2.addRule(9);
        rlLayoutParams2.rightMargin = (int) com.txznet.comm.ui.TE.Tr.Tn("x20");
        ivBack.setId(T9.T());
        ivBack.setLayoutParams(rlLayoutParams2);
        rlBack.addView(ivBack);
        TextView textView = new TextView(com.txznet.comm.Tr.T.Tr());
        textView.setTextColor(Color.parseColor("#FFFFFF"));
        textView.setHintTextColor(Color.parseColor("#40454b"));
        textView.setTextSize(31.0f);
        textView.setText("返回");
        RelativeLayout.LayoutParams rlLayoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
        rlLayoutParams3.addRule(15);
        rlLayoutParams3.addRule(1, ivBack.getId());
        textView.setLayoutParams(rlLayoutParams3);
        rlBack.addView(textView);
        TextView textView2 = new TextView(com.txznet.comm.Tr.T.Tr());
        textView2.setTextColor(Color.parseColor("#FFFFFF"));
        textView2.setHintTextColor(Color.parseColor("#40454b"));
        textView2.setTextSize(37.0f);
        textView2.setText("修改关键字");
        textView2.setGravity(17);
        RelativeLayout.LayoutParams rlLayoutParams4 = new RelativeLayout.LayoutParams(-2, -2);
        rlLayoutParams4.addRule(13);
        textView2.setLayoutParams(rlLayoutParams4);
        relativeLayout.addView(textView2);
        ScrollView scrollView = new ScrollView(com.txznet.comm.Tr.T.Tr());
        scrollView.setScrollBarStyle(50331648);
        scrollView.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        layout.addView(scrollView);
        LinearLayout llContent = new LinearLayout(com.txznet.comm.Tr.T.Tr());
        llContent.setPadding(0, (int) com.txznet.comm.ui.TE.Tr.Tn("y20"), 0, 0);
        llContent.setOrientation(1);
        llContent.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        scrollView.addView(llContent);
        TextView textView3 = new TextView(com.txznet.comm.Tr.T.Tr());
        this.Tr.Tr = textView3;
        textView3.setBackgroundColor(Color.parseColor("#40FFFFFF"));
        textView3.setPadding((int) com.txznet.comm.ui.TE.Tr.Tn("x40"), 0, 0, 0);
        textView3.setTextSize(com.txznet.comm.ui.TE.Tr.Tn("y27"));
        textView3.setGravity(16);
        textView3.setVisibility(8);
        textView3.setTextColor(Color.parseColor("#999999"));
        textView3.setText("当前城市");
        textView3.setLayoutParams(new LinearLayout.LayoutParams(-1, (int) com.txznet.comm.ui.TE.Tr.Tn("y60")));
        llContent.addView(textView3);
        View view = new View(com.txznet.comm.Tr.T.Tr());
        view.setBackgroundColor(Color.parseColor("#1AFFFFFF"));
        view.setLayoutParams(new LinearLayout.LayoutParams(-1, 2));
        llContent.addView(view);
        ListView lvCurCity = new ListView(com.txznet.comm.Tr.T.Tr());
        this.Tr.Ty = lvCurCity;
        lvCurCity.setBackgroundColor(Color.parseColor("#26FFFFFF"));
        lvCurCity.setDivider(new ColorDrawable(Color.parseColor("#1AFFFFFF")));
        lvCurCity.setDividerHeight(1);
        lvCurCity.setVisibility(8);
        lvCurCity.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        llContent.addView(lvCurCity);
        TextView textView4 = new TextView(com.txznet.comm.Tr.T.Tr());
        this.Tr.TE = textView4;
        textView4.setBackgroundColor(Color.parseColor("#40FFFFFF"));
        textView4.setPadding((int) com.txznet.comm.ui.TE.Tr.Tn("x40"), 0, 0, 0);
        textView4.setTextSize(com.txznet.comm.ui.TE.Tr.Tn("y27"));
        textView4.setGravity(16);
        textView4.setVisibility(8);
        textView4.setTextColor(Color.parseColor("#999999"));
        textView4.setText("目标城市");
        textView4.setLayoutParams(new LinearLayout.LayoutParams(-1, (int) com.txznet.comm.ui.TE.Tr.Tn("y60")));
        llContent.addView(textView4);
        View view2 = new View(com.txznet.comm.Tr.T.Tr());
        view2.setBackgroundColor(Color.parseColor("#1AFFFFFF"));
        view2.setLayoutParams(new LinearLayout.LayoutParams(-1, 2));
        llContent.addView(view2);
        ListView lvTarCity = new ListView(com.txznet.comm.Tr.T.Tr());
        this.Tr.T5 = lvTarCity;
        lvTarCity.setBackgroundColor(Color.parseColor("#26FFFFFF"));
        lvTarCity.setDivider(new ColorDrawable(Color.parseColor("#1AFFFFFF")));
        lvTarCity.setDividerHeight(1);
        lvTarCity.setVisibility(8);
        lvTarCity.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        llContent.addView(lvTarCity);
        TextView textView5 = new TextView(com.txznet.comm.Tr.T.Tr());
        this.Tr.Tn = textView5;
        textView5.setBackgroundColor(Color.parseColor("#40FFFFFF"));
        textView5.setPadding((int) com.txznet.comm.ui.TE.Tr.Tn("x40"), 0, 0, 0);
        textView5.setTextSize(com.txznet.comm.ui.TE.Tr.Tn("y27"));
        textView5.setGravity(16);
        textView5.setVisibility(8);
        textView5.setTextColor(Color.parseColor("#999999"));
        textView5.setText("常驻城市");
        textView5.setLayoutParams(new LinearLayout.LayoutParams(-1, (int) com.txznet.comm.ui.TE.Tr.Tn("y60")));
        llContent.addView(textView5);
        View view3 = new View(com.txznet.comm.Tr.T.Tr());
        view3.setBackgroundColor(Color.parseColor("#1AFFFFFF"));
        view3.setLayoutParams(new LinearLayout.LayoutParams(-1, 2));
        llContent.addView(view3);
        ListView lvPerCity = new ListView(com.txznet.comm.Tr.T.Tr());
        this.Tr.T9 = lvPerCity;
        lvPerCity.setBackgroundColor(Color.parseColor("#26FFFFFF"));
        lvPerCity.setDivider(new ColorDrawable(Color.parseColor("#1AFFFFFF")));
        lvPerCity.setDividerHeight(1);
        lvPerCity.setVisibility(8);
        lvPerCity.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        llContent.addView(lvPerCity);
        View view4 = new View(com.txznet.comm.Tr.T.Tr());
        view4.setBackgroundColor(Color.parseColor("#1AFFFFFF"));
        view4.setLayoutParams(new LinearLayout.LayoutParams(-1, 2));
        llContent.addView(view4);
        TextView textView6 = new TextView(com.txznet.comm.Tr.T.Tr());
        this.Tr.Tk = textView6;
        textView6.setBackgroundColor(Color.parseColor("#40FFFFFF"));
        textView6.setPadding((int) com.txznet.comm.ui.TE.Tr.Tn("x40"), 0, 0, 0);
        textView6.setTextSize(com.txznet.comm.ui.TE.Tr.Tn("y27"));
        textView6.setGravity(16);
        textView6.setVisibility(8);
        textView6.setTextColor(Color.parseColor("#999999"));
        textView6.setText("推荐城市");
        textView6.setLayoutParams(new LinearLayout.LayoutParams(-1, (int) com.txznet.comm.ui.TE.Tr.Tn("y60")));
        llContent.addView(textView6);
        View view5 = new View(com.txznet.comm.Tr.T.Tr());
        view5.setBackgroundColor(Color.parseColor("#1AFFFFFF"));
        view5.setLayoutParams(new LinearLayout.LayoutParams(-1, 2));
        llContent.addView(view5);
        ListView lvNomCity = new ListView(com.txznet.comm.Tr.T.Tr());
        this.Tr.TZ = lvNomCity;
        lvNomCity.setBackgroundColor(Color.parseColor("#26FFFFFF"));
        lvNomCity.setDivider(new ColorDrawable(Color.parseColor("#1AFFFFFF")));
        lvNomCity.setDividerHeight(1);
        lvNomCity.setVisibility(8);
        lvNomCity.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        llContent.addView(lvNomCity);
        rlBack.setOnClickListener(this.Tk);
        if (this.T9.f434T == null || this.T9.f434T.size() <= 0) {
            this.Tr.Tr.setVisibility(8);
            this.Tr.Ty.setVisibility(8);
        } else {
            this.Tr.Tr.setVisibility(0);
            this.Tr.Ty.setVisibility(0);
            this.Tr.Ty.setAdapter(new T(com.txznet.comm.Tr.T.Tr(), this.T9.f434T));
            T(this.Tr.Ty);
            this.Tr.Ty.setOnItemClickListener(this.f484T);
        }
        if (this.T9.Tn == null || this.T9.Tn.size() <= 0) {
            this.Tr.Tn.setVisibility(8);
            this.Tr.T9.setVisibility(8);
        } else {
            this.Tr.Tn.setVisibility(0);
            this.Tr.T9.setVisibility(0);
            this.Tr.T9.setAdapter(new T(com.txznet.comm.Tr.T.Tr(), this.T9.Tn));
            T(this.Tr.T9);
            this.Tr.T9.setOnItemClickListener(this.f484T);
        }
        if (this.T9.Ty == null || this.T9.Ty.size() <= 0) {
            this.Tr.Tk.setVisibility(8);
            this.Tr.TZ.setVisibility(8);
        } else {
            this.Tr.Tk.setVisibility(0);
            this.Tr.TZ.setVisibility(0);
            this.Tr.TZ.setAdapter(new T(com.txznet.comm.Tr.T.Tr(), this.T9.Ty));
            T(this.Tr.TZ);
            this.Tr.TZ.setOnItemClickListener(this.f484T);
        }
        if (this.T9.Tr == null || this.T9.Tr.size() <= 0) {
            this.Tr.T5.setVisibility(8);
            this.Tr.TE.setVisibility(8);
        } else {
            this.Tr.T5.setVisibility(0);
            this.Tr.TE.setVisibility(0);
            this.Tr.T5.setAdapter(new T(com.txznet.comm.Tr.T.Tr(), this.T9.Tr));
            T(this.Tr.T5);
            this.Tr.T5.setOnItemClickListener(this.f484T);
        }
        return layout;
    }

    public void Tr() {
    }

    public void T(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter != null) {
            int totalHeight = 0;
            int len = listAdapter.getCount();
            for (int i = 0; i < len; i++) {
                View listItem = listAdapter.getView(i, (View) null, listView);
                listItem.measure(0, 0);
                totalHeight += listItem.getMeasuredHeight();
            }
            ViewGroup.LayoutParams params = listView.getLayoutParams();
            params.height = (listView.getDividerHeight() * (listAdapter.getCount() - 1)) + totalHeight;
            listView.setLayoutParams(params);
        }
    }

    /* compiled from: Proguard */
    public class T extends BaseAdapter {

        /* renamed from: T  reason: collision with root package name */
        List f487T;
        Context Tr;

        public T(Context context, List<String> displayList) {
            this.Tr = context;
            this.f487T = displayList;
        }

        public int getCount() {
            if (this.f487T != null) {
                return this.f487T.size();
            }
            return 0;
        }

        public Object getItem(int position) {
            if (this.f487T == null || this.f487T.size() <= 0) {
                return null;
            }
            return this.f487T.get(position);
        }

        public long getItemId(int position) {
            return (long) position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            C0019T itemViewHolder;
            if (convertView == null) {
                itemViewHolder = T();
                convertView = itemViewHolder.f488T;
                convertView.setTag(itemViewHolder);
            } else {
                itemViewHolder = (C0019T) convertView.getTag();
            }
            if (this.f487T != null) {
                itemViewHolder.Tr.setText((String) this.f487T.get(position));
            }
            return convertView;
        }

        /* renamed from: com.txznet.comm.ui.T5.Tr.T.Th$T$T  reason: collision with other inner class name */
        /* compiled from: Proguard */
        class C0019T {

            /* renamed from: T  reason: collision with root package name */
            RelativeLayout f488T;
            TextView Tr;
            ImageView Ty;

            C0019T() {
            }
        }

        private C0019T T() {
            C0019T itemViewHolder = new C0019T();
            itemViewHolder.f488T = new RelativeLayout(com.txznet.comm.Tr.T.Tr());
            itemViewHolder.f488T.setDescendantFocusability(Radio.AFS_FLAG);
            itemViewHolder.f488T.setLayoutParams(new AbsListView.LayoutParams(-1, (int) com.txznet.comm.ui.TE.Tr.Tn("y80")));
            itemViewHolder.Tr = new TextView(com.txznet.comm.Tr.T.Tr());
            itemViewHolder.Tr.setTextColor(Color.parseColor("#FFFFFF"));
            itemViewHolder.Tr.setFocusable(false);
            itemViewHolder.Tr.setGravity(16);
            itemViewHolder.Tr.setPadding((int) com.txznet.comm.ui.TE.Tr.Tn("X60"), 0, 0, 0);
            itemViewHolder.Tr.setTextSize(com.txznet.comm.ui.TE.Tr.Tn("y35"));
            itemViewHolder.Tr.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
            itemViewHolder.f488T.addView(itemViewHolder.Tr);
            itemViewHolder.Ty = new ImageView(com.txznet.comm.Tr.T.Tr());
            itemViewHolder.Ty.setFocusable(false);
            itemViewHolder.Ty.setPadding(0, 0, 0, (int) com.txznet.comm.ui.TE.Tr.Tn("x60"));
            itemViewHolder.Ty.setVisibility(8);
            itemViewHolder.Ty.setImageDrawable(com.txznet.comm.ui.TE.Tr.Ty("city_item_select"));
            RelativeLayout.LayoutParams rlLayoutParams = new RelativeLayout.LayoutParams(-2, -2);
            rlLayoutParams.addRule(15);
            rlLayoutParams.addRule(11);
            itemViewHolder.Ty.setLayoutParams(rlLayoutParams);
            itemViewHolder.f488T.addView(itemViewHolder.Ty);
            return itemViewHolder;
        }
    }
}
