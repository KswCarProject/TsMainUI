package com.ts.can.ford.wc;

import android.app.Activity;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanRelativeCarInfoView;
import com.ts.canview.CanItemTextBtnList;
import com.ts.canview.CanScrollList;

public class CanFordWcWarnInfoView extends CanRelativeCarInfoView {
    private static int[] mBits = {1, 2, 4, 8, 16, 32, 64, 128};
    private static int[][] mTextArr;
    private CanScrollList mManager;
    private CanDataInfo.FordWcWarnInfo mWarnInfos;

    static {
        int[] iArr = new int[8];
        iArr[0] = R.string.can_ford_wc_warn_text_1;
        iArr[1] = R.string.can_ford_wc_warn_text_48;
        int[] iArr2 = new int[8];
        iArr2[0] = R.string.can_ford_wc_warn_text_2;
        iArr2[1] = R.string.can_ford_wc_warn_text_3;
        iArr2[2] = R.string.can_ford_wc_warn_text_2;
        int[] iArr3 = {R.string.can_ford_wc_warn_text_4, R.string.can_ford_wc_warn_text_5, R.string.can_ford_wc_warn_text_6, R.string.can_ford_wc_warn_text_7, R.string.can_ford_wc_warn_text_8, R.string.can_ford_wc_warn_text_9, R.string.can_ford_wc_warn_text_4, R.string.can_ford_wc_warn_text_5};
        int[] iArr4 = new int[8];
        iArr4[0] = R.string.can_ford_wc_warn_text_6;
        iArr4[1] = R.string.can_ford_wc_warn_text_7;
        iArr4[2] = R.string.can_ford_wc_warn_text_8;
        iArr4[3] = R.string.can_ford_wc_warn_text_9;
        iArr4[4] = R.string.can_ford_wc_warn_text_10;
        int[] iArr5 = new int[8];
        iArr5[0] = R.string.can_ford_wc_warn_text_11;
        iArr5[1] = R.string.can_ford_wc_warn_text_12;
        iArr5[2] = R.string.can_ford_wc_warn_text_13;
        iArr5[3] = R.string.can_ford_wc_warn_text_14;
        iArr5[4] = R.string.can_ford_wc_warn_text_15;
        iArr5[5] = R.string.can_ford_wc_warn_text_16;
        iArr5[6] = R.string.can_ford_wc_warn_text_17;
        int[] iArr6 = {R.string.can_ford_wc_warn_text_18, R.string.can_ford_wc_warn_text_19, R.string.can_ford_wc_warn_text_20, R.string.can_ford_wc_warn_text_21, R.string.can_ford_wc_warn_text_22, R.string.can_ford_wc_warn_text_23, R.string.can_ford_wc_warn_text_24, R.string.can_ford_wc_warn_text_25};
        int[] iArr7 = new int[8];
        iArr7[0] = R.string.can_ford_wc_warn_text_26;
        iArr7[1] = R.string.can_ford_wc_warn_text_27;
        iArr7[2] = R.string.can_ford_wc_warn_text_28;
        iArr7[3] = R.string.can_ford_wc_warn_text_29;
        iArr7[4] = R.string.can_ford_wc_warn_text_30;
        int[] iArr8 = {R.string.can_ford_wc_warn_text_31, R.string.can_ford_wc_warn_text_32, R.string.can_ford_wc_warn_text_33, R.string.can_ford_wc_warn_text_34, R.string.can_ford_wc_warn_text_14, R.string.can_ford_wc_warn_text_35, R.string.can_ford_wc_warn_text_36, R.string.can_ford_wc_warn_text_37};
        int[] iArr9 = new int[8];
        iArr9[2] = R.string.can_ford_wc_warn_text_38;
        iArr9[3] = R.string.can_ford_wc_warn_text_38;
        iArr9[4] = R.string.can_ford_wc_warn_text_39;
        iArr9[5] = R.string.can_ford_wc_warn_text_40;
        iArr9[6] = R.string.can_ford_wc_warn_text_41;
        int[] iArr10 = new int[8];
        iArr10[0] = R.string.can_ford_wc_warn_text_42;
        iArr10[3] = R.string.can_ford_wc_warn_text_43;
        iArr10[4] = R.string.can_ford_wc_warn_text_44;
        iArr10[5] = R.string.can_ford_wc_warn_text_45;
        iArr10[6] = R.string.can_ford_wc_warn_text_46;
        iArr10[7] = R.string.can_ford_wc_warn_text_47;
        mTextArr = new int[][]{iArr, iArr2, iArr3, iArr4, iArr5, iArr6, iArr7, iArr8, iArr9, iArr10};
    }

    public CanFordWcWarnInfoView(Activity activity) {
        super(activity);
    }

    public void onClick(View v) {
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        ScrollView scrollView = new ScrollView(getActivity());
        getRelativeManager().AddView(scrollView, 0, 0, -1, -1);
        LinearLayout layout = new LinearLayout(getActivity());
        layout.setOrientation(1);
        this.mManager = new CanScrollList(layout, 0);
        scrollView.addView(this.mManager.getLayout(), -1, -1);
        this.mWarnInfos = new CanDataInfo.FordWcWarnInfo();
    }

    private void addItem(int text) {
        if (text != 0) {
            CanItemTextBtnList item = new CanItemTextBtnList((Context) getActivity(), text);
            item.ShowArrow(false);
            this.mManager.AddView(item.GetView());
        }
    }

    public void ResetData(boolean check) {
        CanJni.FordWcGetWarnInfo(this.mWarnInfos);
        if (!i2b(this.mWarnInfos.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mWarnInfos.Update)) {
            this.mWarnInfos.Update = 0;
            this.mManager.RemoveAllViews();
            int[] warns = this.mWarnInfos.Warn;
            for (int i = 0; i < warns.length; i++) {
                addWarnItem(warns[i], mTextArr[i]);
            }
        }
    }

    private void addWarnItem(int warn, int[] texts) {
        for (int i = 0; i < mBits.length; i++) {
            if ((mBits[i] & warn) != 0) {
                addItem(texts[i]);
            }
        }
    }

    public void QueryData() {
        CanJni.FordWcQuery(5, 1, 116);
    }

    public boolean onTouch(View arg0, MotionEvent arg1) {
        return false;
    }
}
