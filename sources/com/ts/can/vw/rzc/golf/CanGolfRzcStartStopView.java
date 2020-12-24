package com.ts.can.vw.rzc.golf;

import android.app.Activity;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanRelativeCarInfoView;
import com.ts.canview.CanItemTextBtnList;
import com.ts.canview.CanScrollList;

public class CanGolfRzcStartStopView extends CanRelativeCarInfoView {
    public static final String TAG = "CanGolfRzcStartStopView";
    private CanItemTextBtnList[] mItemSS;
    private CanScrollList mManager;
    private CanDataInfo.GolfCarTip mSSData = new CanDataInfo.GolfCarTip();
    private String[] mStrMsgArray;
    private String[] mStrStaArray;

    public CanGolfRzcStartStopView(Activity activity) {
        super(activity);
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        getActivity().setContentView(R.layout.activity_can_comm_list);
        this.mManager = new CanScrollList(getActivity());
        this.mManager = new CanScrollList(getActivity());
        this.mItemSS = new CanItemTextBtnList[30];
        for (int i = 0; i < this.mItemSS.length; i++) {
            this.mItemSS[i] = new CanItemTextBtnList((Context) getActivity(), 0);
            this.mItemSS[i].ShowArrow(false);
        }
        this.mStrStaArray = getStringArray(R.array.can_start_stop_other_msg);
        this.mStrMsgArray = getStringArray(R.array.can_start_stop_msg);
    }

    public void onClick(View v) {
    }

    public boolean onTouch(View arg0, MotionEvent arg1) {
        return false;
    }

    public void ResetData(boolean check) {
        CanJni.GolfGetCarTip(this.mSSData);
        if (!i2b(this.mSSData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSSData.Update)) {
            this.mSSData.Update = 0;
            this.mManager.RemoveAllViews();
            if (this.mSSData.Num != 0) {
                int num = this.mSSData.Num;
                if (num > this.mItemSS.length) {
                    num = this.mItemSS.length;
                }
                int j = 0;
                for (int i = 0; i < num; i++) {
                    if (this.mSSData.TipData[i] < this.mStrMsgArray.length) {
                        this.mItemSS[j].SetVal(this.mStrMsgArray[this.mSSData.TipData[i]]);
                        this.mManager.AddView(this.mItemSS[j].GetView());
                        j++;
                    }
                }
            } else if (this.mSSData.StartStop < this.mStrStaArray.length) {
                this.mItemSS[0].SetVal(this.mStrStaArray[this.mSSData.StartStop]);
                this.mManager.AddView(this.mItemSS[0].GetView());
            }
        }
    }

    public void QueryData() {
        if (!i2b(this.mSSData.UpdateOnce)) {
            CanJni.GolfQuery(96, 0);
        }
    }
}
