package com.ts.can.ford.wc.fiesta;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
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
import java.lang.reflect.Array;

public class CanFordFiestaWcWarnInfoView extends CanRelativeCarInfoView {
    private static int[] mBits = {1, 2, 4, 8, 16, 32, 64, 128};
    private CanScrollList mManager;
    private String[][] mTextArr;
    private CanDataInfo.FiestaWc_WarnData mWarnInfos;

    public CanFordFiestaWcWarnInfoView(Activity activity) {
        super(activity);
    }

    public void onClick(View v) {
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        int n;
        this.mTextArr = (String[][]) Array.newInstance(String.class, new int[]{6, 8});
        String[] warninfo = getActivity().getResources().getStringArray(R.array.can_ford_fiesta_wc_warninfo);
        int n2 = 0;
        int len = warninfo.length;
        int i = 0;
        while (i < 6) {
            int j = 0;
            int n3 = n2;
            while (j < 8) {
                if (n3 < len) {
                    n = n3 + 1;
                    this.mTextArr[i][j] = warninfo[n3];
                } else {
                    this.mTextArr[i][j] = "";
                    n = n3;
                }
                j++;
                n3 = n;
            }
            i++;
            n2 = n3;
        }
        ScrollView scrollView = new ScrollView(getActivity());
        getRelativeManager().AddView(scrollView, 0, 0, -1, -1);
        LinearLayout layout = new LinearLayout(getActivity());
        layout.setOrientation(1);
        this.mManager = new CanScrollList(layout, 0);
        scrollView.addView(this.mManager.getLayout(), -1, -1);
        this.mWarnInfos = new CanDataInfo.FiestaWc_WarnData();
    }

    private void addItem(String text) {
        if (!TextUtils.isEmpty(text)) {
            CanItemTextBtnList item = new CanItemTextBtnList((Context) getActivity(), text);
            item.ShowArrow(false);
            this.mManager.AddView(item.GetView());
        }
    }

    public void ResetData(boolean check) {
        CanJni.FordOldFiestaWcGetWarnData(this.mWarnInfos);
        if (!i2b(this.mWarnInfos.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mWarnInfos.Update)) {
            this.mWarnInfos.Update = 0;
            this.mManager.RemoveAllViews();
            int[] warns = this.mWarnInfos.Data;
            for (int i = 1; i < warns.length; i++) {
                addWarnItem(warns[i], this.mTextArr[i - 1]);
            }
        }
    }

    private void addWarnItem(int warn, String[] texts) {
        for (int i = 0; i < mBits.length; i++) {
            if ((mBits[i] & warn) != 0) {
                addItem(texts[i]);
            }
        }
    }

    public void QueryData() {
    }

    public boolean onTouch(View arg0, MotionEvent arg1) {
        return false;
    }
}
