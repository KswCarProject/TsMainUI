package com.ts.can.cc.dj.hf;

import android.app.Activity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;
import com.ts.canview.CanItemTitleValList;

public class CanHfDjSeatStatusView extends CanScrollCarInfoView {
    private CanDataInfo.CcHfDj_ChairInfo mChairInfo;
    private String mOffStr;

    public CanHfDjSeatStatusView(Activity activity) {
        super(activity, 8);
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mOffStr = getActivity().getResources().getString(R.string.can_off);
        this.mItemTitleIds = new int[]{R.string.can_lt_hot, R.string.can_lt_wind, R.string.can_lt_am, R.string.can_lt_yt, R.string.can_rt_hot, R.string.can_rt_wind, R.string.can_rt_am, R.string.can_rt_yt};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE};
        this.mChairInfo = new CanDataInfo.CcHfDj_ChairInfo();
    }

    public void ResetData(boolean check) {
        CanJni.CcHfDjGetChairInfo(this.mChairInfo);
        if (!i2b(this.mChairInfo.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mChairInfo.Update)) {
            this.mChairInfo.Update = 0;
            if (this.mChairInfo.nLtChairHot == 0) {
                updateItem(0, 0, this.mOffStr);
            } else {
                updateItem(0, 0, new StringBuilder(String.valueOf(this.mChairInfo.nLtChairHot)).toString());
            }
            if (this.mChairInfo.nLtChairWind == 0) {
                updateItem(1, 0, this.mOffStr);
            } else {
                updateItem(1, 0, new StringBuilder(String.valueOf(this.mChairInfo.nLtChairWind)).toString());
            }
            if (this.mChairInfo.nLtChairMsj == 0) {
                updateItem(2, 0, this.mOffStr);
            } else {
                updateItem(2, 0, new StringBuilder(String.valueOf(this.mChairInfo.nLtChairMsj)).toString());
            }
            if (this.mChairInfo.nLtChairYt == 0) {
                updateItem(3, 0, this.mOffStr);
            } else {
                updateItem(3, 0, new StringBuilder(String.valueOf(this.mChairInfo.nLtChairYt)).toString());
            }
            if (this.mChairInfo.nRtChairHot == 0) {
                updateItem(4, 0, this.mOffStr);
            } else {
                updateItem(4, 0, new StringBuilder(String.valueOf(this.mChairInfo.nRtChairHot)).toString());
            }
            if (this.mChairInfo.nRtChairWind == 0) {
                updateItem(5, 0, this.mOffStr);
            } else {
                updateItem(5, 0, new StringBuilder(String.valueOf(this.mChairInfo.nRtChairWind)).toString());
            }
            if (this.mChairInfo.nRtChairMsj == 0) {
                updateItem(6, 0, this.mOffStr);
            } else {
                updateItem(6, 0, new StringBuilder(String.valueOf(this.mChairInfo.nRtChairMsj)).toString());
            }
            if (this.mChairInfo.nRtChairYt == 0) {
                updateItem(7, 0, this.mOffStr);
            } else {
                updateItem(7, 0, new StringBuilder(String.valueOf(this.mChairInfo.nRtChairYt)).toString());
            }
        }
    }

    public boolean onTouch(View view, MotionEvent event) {
        int id = ((Integer) view.getTag()).intValue();
        int action = event.getAction();
        if (action != 0) {
            if (action == 1) {
                switch (id) {
                    case 0:
                        CanJni.CcHfDjAcSet(11, 0);
                        break;
                    case 1:
                        CanJni.CcHfDjAcSet(12, 0);
                        break;
                    case 2:
                        CanJni.CcHfDjAcSet(17, 0);
                        break;
                    case 3:
                        CanJni.CcHfDjAcSet(18, 0);
                        break;
                    case 4:
                        CanJni.CcHfDjAcSet(13, 0);
                        break;
                    case 5:
                        CanJni.CcHfDjAcSet(14, 0);
                        break;
                    case 6:
                        CanJni.CcHfDjAcSet(19, 0);
                        break;
                    case 7:
                        CanJni.CcHfDjAcSet(20, 0);
                        break;
                }
            }
        } else {
            Log.d("lh", "id = " + id);
            switch (id) {
                case 0:
                    CanJni.CcHfDjAcSet(11, 1);
                    break;
                case 1:
                    CanJni.CcHfDjAcSet(12, 1);
                    break;
                case 2:
                    CanJni.CcHfDjAcSet(17, 1);
                    break;
                case 3:
                    CanJni.CcHfDjAcSet(18, 1);
                    break;
                case 4:
                    CanJni.CcHfDjAcSet(13, 1);
                    break;
                case 5:
                    CanJni.CcHfDjAcSet(14, 1);
                    break;
                case 6:
                    CanJni.CcHfDjAcSet(19, 1);
                    break;
                case 7:
                    CanJni.CcHfDjAcSet(20, 1);
                    break;
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        super.InitUI();
        for (int i = 0; i < 8; i++) {
            CanItemTitleValList mCanItemTitleValList = (CanItemTitleValList) this.mItemObjects[i];
            mCanItemTitleValList.GetBtn().setTag(Integer.valueOf(i));
            mCanItemTitleValList.GetBtn().setOnClickListener((View.OnClickListener) null);
            mCanItemTitleValList.GetBtn().setOnTouchListener(this);
        }
    }

    public void QueryData() {
        CanJni.CcHfDjQuery(19);
    }
}
