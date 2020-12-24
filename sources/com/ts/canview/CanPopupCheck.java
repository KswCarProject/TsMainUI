package com.ts.canview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.ts.MainUI.R;
import com.ts.canview.CanItemPopupCheckList;
import com.ts.other.CustomDialog;
import java.util.ArrayList;

/* compiled from: CanItemPopupCheckList */
class CanPopupCheck extends CustomDialog implements View.OnClickListener {
    private static final int[] mBk = {R.drawable.can_comm_msgbox_line2, R.drawable.can_comm_msgbox_line3, R.drawable.can_comm_msgbox_line4, R.drawable.can_comm_msgbox_line5, R.drawable.can_comm_msgbox_line6};
    private CanItemPopupCheckList.onPopCheckItemClick mCallBack;
    private CheckBox[] mCheckBoxArray;
    private int mId;
    private String[] mTitleArray;
    private TextView mTxtPositive;
    private View[] mViewArray;
    private View mViewPositive;

    public CanPopupCheck(int Id, Context context, int y, String[] text, int[] sel, CanItemPopupCheckList.onPopCheckItemClick cb) {
        createDlg(Id, context, y, text, sel, cb);
    }

    public CanPopupCheck() {
    }

    public void createDlg(int Id, Context context, int y, String[] text, int[] sel, CanItemPopupCheckList.onPopCheckItemClick cb) {
        this.mId = Id;
        this.mCallBack = cb;
        if (text.length >= 2) {
            super.create(R.layout.can_popup_bk, context);
            int y2 = (y - GetStatusBarHeight(context)) - 15;
            WindowManager.LayoutParams wmlp = this.mWindow.getAttributes();
            wmlp.gravity = 53;
            wmlp.x = 0;
            wmlp.y = y2;
            this.mWindow.setAttributes(wmlp);
            int line = text.length;
            ScrollView scrView = (ScrollView) this.mWindow.findViewById(R.id.can_comm_scrview);
            if (line >= 7 || line <= 1) {
                scrView.setBackgroundResource(R.drawable.can_comm_msgbox_line6);
            } else {
                scrView.setBackgroundResource(mBk[line - 2]);
            }
            LinearLayout layout = (LinearLayout) this.mWindow.findViewById(R.id.can_comm_lineview);
            this.mViewArray = new View[line];
            this.mCheckBoxArray = new CheckBox[line];
            LayoutInflater li = LayoutInflater.from(context);
            for (int i = 0; i < line; i++) {
                this.mViewArray[i] = li.inflate(R.layout.can_item_popup_check_menu, (ViewGroup) null);
                this.mViewArray[i].setTag(Integer.valueOf(i));
                this.mCheckBoxArray[i] = (CheckBox) this.mViewArray[i].findViewById(R.id.val);
                this.mCheckBoxArray[i].setText(text[i]);
                layout.addView(this.mViewArray[i]);
                this.mViewArray[i].setOnClickListener(this);
            }
            if (sel != null) {
                for (int i2 = 0; i2 < sel.length; i2++) {
                    if (sel[i2] >= 0 && sel[i2] < line) {
                        this.mCheckBoxArray[sel[i2]].setSelected(true);
                        this.mCheckBoxArray[sel[i2]].setChecked(true);
                    }
                }
            }
        }
    }

    private int GetStatusBarHeight(Context context) {
        int sbar = 0;
        try {
            Class<?> c = Class.forName("com.android.internal.R$dimen");
            sbar = context.getResources().getDimensionPixelSize(Integer.parseInt(c.getField("status_bar_height").get(c.newInstance()).toString()));
        } catch (Exception e1) {
            Log.d("", "get status bar height fail");
            e1.printStackTrace();
        }
        Log.d("statusBarHeight = ", new StringBuilder().append(sbar).toString());
        return sbar;
    }

    public void onClick(View v) {
        boolean z;
        boolean z2 = false;
        int tag = ((Integer) v.getTag()).intValue();
        CheckBox checkBox = this.mCheckBoxArray[tag];
        if (this.mCheckBoxArray[tag].isSelected()) {
            z = false;
        } else {
            z = true;
        }
        checkBox.setSelected(z);
        CheckBox checkBox2 = this.mCheckBoxArray[tag];
        if (!this.mCheckBoxArray[tag].isChecked()) {
            z2 = true;
        }
        checkBox2.setChecked(z2);
        ArrayList<Integer> mList = new ArrayList<>();
        for (int i = 0; i < this.mCheckBoxArray.length; i++) {
            if (this.mCheckBoxArray[i].isChecked()) {
                mList.add(Integer.valueOf(i));
            }
        }
        int[] mInt = new int[mList.size()];
        for (int i2 = 0; i2 < mList.size(); i2++) {
            mInt[i2] = mList.get(i2).intValue();
        }
        if (this.mCallBack != null) {
            this.mCallBack.onPositiveItem(this.mId, mInt);
        }
    }
}
