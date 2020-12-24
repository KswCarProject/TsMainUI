package com.ts.can.bmw.mini;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.ts.MainUI.R;

public class CanBMWMiniStatusServiceActivity extends CanBMWMiniBaseActivity {
    private TextView mItemNone;
    private LinearLayout[] mLayoutArrays = new LinearLayout[8];
    private RelativeLayout mManager;
    private String[] mServiceArrays;
    private int[] mServiceIcons = {R.drawable.can_mini_ok_icon, R.drawable.can_mini_set_icon, R.drawable.can_mini_warning_icon, 17170445};

    /* access modifiers changed from: protected */
    public void SetLayoutContainer() {
        setContentView(R.layout.activity_can_comm_relative);
        this.mManager = (RelativeLayout) findViewById(R.id.can_comm_layout);
        this.mServiceArrays = getResources().getStringArray(R.array.can_by_service_array);
    }

    /* access modifiers changed from: protected */
    public void AddItemView() {
        for (int i = 0; i < this.mLayoutArrays.length; i++) {
            this.mLayoutArrays[i] = addServiceItem(((i % 2) * 460) + 120, ((i / 2) * 100) + 100, i);
        }
        this.mItemNone = addTextWithNone();
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        GetMiniService();
        if (!i2b(this.mCarService.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mCarService.Update)) {
            this.mCarService.Update = 0;
            int num = this.mCarService.Num;
            setVisibility(num);
            int curIndex = this.mCarService.CurIndex;
            if (curIndex >= 1 && curIndex <= num) {
                for (int i = 0; i < this.mCarService.Status.length; i++) {
                    int itemStatus = this.mCarService.Status[i];
                    LinearLayout layout = this.mLayoutArrays[i];
                    if (itemStatus < 0 || itemStatus >= 3) {
                        setIcon(layout, this.mServiceIcons[3], 3);
                    } else {
                        setIcon(layout, this.mServiceIcons[itemStatus], itemStatus);
                    }
                }
                for (int i2 = 0; i2 < this.mCarService.Index.length; i2++) {
                    int itemNum = this.mCarService.Index[i2];
                    setText(this.mLayoutArrays[i2], this.mServiceArrays[itemNum], itemNum);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void Query() {
        Query(75);
    }

    private void setVisibility(int num) {
        if (num == 0) {
            this.mItemNone.setVisibility(0);
            for (LinearLayout visibility : this.mLayoutArrays) {
                visibility.setVisibility(8);
            }
            return;
        }
        this.mItemNone.setVisibility(8);
        for (int i = 0; i < num; i++) {
            this.mLayoutArrays[i].setVisibility(0);
        }
    }

    private void setIcon(LinearLayout layout, int resId, int status) {
        ImageView icon = (ImageView) layout.getChildAt(0);
        if (icon != null) {
            icon.setImageResource(resId);
            icon.setTag(Integer.valueOf(status));
        }
    }

    private void setText(LinearLayout layout, String str, int num) {
        TextView text = (TextView) layout.getChildAt(1);
        if (text != null) {
            text.setText(str);
            text.setTag(Integer.valueOf(num));
        }
    }

    private LinearLayout addServiceItem(int x, int y, int id) {
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(0);
        layout.setBackgroundResource(R.drawable.btn_mini_service_item);
        layout.setClickable(true);
        layout.setOnClickListener(this);
        layout.setTag(Integer.valueOf(id));
        layout.setVisibility(8);
        layout.setPadding(10, 0, 10, 0);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(300, 58);
        params.leftMargin = x;
        params.topMargin = y;
        this.mManager.addView(layout, params);
        addIcon(layout);
        addText(layout);
        return layout;
    }

    private void addIcon(LinearLayout layout) {
        ImageView icon = new ImageView(this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(-2, -2);
        lp.gravity = 16;
        layout.addView(icon, lp);
    }

    private void addText(LinearLayout layout) {
        TextView text = new TextView(this);
        text.setTextColor(-1);
        text.setTextSize(0, 30.0f);
        text.setSingleLine();
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(-2, -2);
        lp.leftMargin = 10;
        lp.gravity = 16;
        layout.addView(text, lp);
    }

    private TextView addTextWithNone() {
        TextView text = new TextView(this);
        text.setText(R.string.can_check_control_none);
        text.setTextColor(-1);
        text.setTextSize(0, 30.0f);
        text.setVisibility(8);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(-2, -2);
        params.addRule(13);
        this.mManager.addView(text, params);
        return text;
    }

    public void onClick(View v) {
        startSubWin(CanBMWMiniServiceDetailActivity.class, getClickedItem(((Integer) v.getTag()).intValue()));
    }

    private Bundle getClickedItem(int id) {
        Bundle data = new Bundle();
        data.putInt(CanBMWMiniServiceDetailActivity.KEY_INDEX, id);
        View icon = this.mLayoutArrays[id].getChildAt(0);
        if (icon != null) {
            data.putInt("status", ((Integer) icon.getTag()).intValue());
        }
        TextView text = (TextView) this.mLayoutArrays[id].getChildAt(1);
        if (text != null) {
            data.putInt(CanBMWMiniServiceDetailActivity.KEY_NUM, ((Integer) text.getTag()).intValue());
        }
        return data;
    }

    private void startSubWin(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent(this, cls);
        intent.putExtras(bundle);
        startActivity(intent);
        overridePendingTransition(0, 0);
    }
}
