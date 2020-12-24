package com.ts.can.cc.h6_rzc;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;
import com.ts.canview.CanItemTitleValList;

public class CanCCH6RzcCarSeatStatusView extends CanScrollCarInfoView {
    private CanDataInfo.ChairHotInfo m_ChairHotInfo;

    public CanCCH6RzcCarSeatStatusView(Activity activity) {
        super(activity, 4);
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        switch (v.getId()) {
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_lt_am, R.string.can_lt_yt, R.string.can_rt_am, R.string.can_rt_yt};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE};
        this.m_ChairHotInfo = new CanDataInfo.ChairHotInfo();
    }

    public void ResetData(boolean check) {
        CanJni.CcHfH9GetChairHotInfo(this.m_ChairHotInfo);
        if (!i2b(this.m_ChairHotInfo.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.m_ChairHotInfo.Update)) {
            updateItem(0, this.m_ChairHotInfo.nLtChairMsj, String.format("%d", new Object[]{Integer.valueOf(this.m_ChairHotInfo.nLtChairMsj)}));
            updateItem(1, this.m_ChairHotInfo.nLtChairYt, String.format("%d", new Object[]{Integer.valueOf(this.m_ChairHotInfo.nLtChairYt)}));
            updateItem(2, this.m_ChairHotInfo.nRtChairMsj, String.format("%d", new Object[]{Integer.valueOf(this.m_ChairHotInfo.nRtChairMsj)}));
            updateItem(3, this.m_ChairHotInfo.nRtChairYt, String.format("%d", new Object[]{Integer.valueOf(this.m_ChairHotInfo.nRtChairYt)}));
        }
    }

    public boolean onTouch(View view, MotionEvent event) {
        int id = ((Integer) view.getTag()).intValue();
        int action = event.getAction();
        if (action != 0) {
            if (action == 1) {
                switch (id) {
                    case 0:
                        CanJni.CCH2sAcSet(40, 0);
                        break;
                    case 1:
                        CanJni.CCH2sAcSet(42, 0);
                        break;
                    case 2:
                        CanJni.CCH2sAcSet(41, 0);
                        break;
                    case 3:
                        CanJni.CCH2sAcSet(43, 0);
                        break;
                }
            }
        } else {
            switch (id) {
                case 0:
                    CanJni.CCH2sAcSet(40, 1);
                    break;
                case 1:
                    CanJni.CCH2sAcSet(42, 1);
                    break;
                case 2:
                    CanJni.CCH2sAcSet(41, 1);
                    break;
                case 3:
                    CanJni.CCH2sAcSet(43, 1);
                    break;
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        super.InitUI();
        for (int i = 0; i < 4; i++) {
            CanItemTitleValList mCanItemTitleValList = (CanItemTitleValList) this.mItemObjects[i];
            mCanItemTitleValList.GetBtn().setTag(Integer.valueOf(i));
            mCanItemTitleValList.GetBtn().setOnClickListener((View.OnClickListener) null);
            mCanItemTitleValList.GetBtn().setOnTouchListener(this);
        }
    }

    public void QueryData() {
        CanJni.CCCarQueryRzc(53, 0);
    }
}
