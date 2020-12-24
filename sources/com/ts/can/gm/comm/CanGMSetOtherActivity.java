package com.ts.can.gm.comm;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.canview.CanItemMsgBox;
import com.ts.canview.CanItemProgressList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanItemTextBtnList;
import com.ts.canview.CanScrollList;

public class CanGMSetOtherActivity extends CanGMBaseActivity implements View.OnClickListener, UserCallBack, CanItemProgressList.onPosChange, CanItemMsgBox.onMsgBoxClick {
    public static final int ITEM_FS_SET = 3;
    private static final int ITEM_MAX = 3;
    private static final int ITEM_MIN = 1;
    public static final int ITEM_RADAR_SW = 1;
    public static final int ITEM_WARN_VOICE = 2;
    public static final String TAG = "CanGMSetOtherActivity";
    private CanItemTextBtnList mItemFsSet;
    private CanItemSwitchList mItemRadarSW;
    private CanItemProgressList mItemWarnVoice;
    private CanScrollList mManager;
    private CanDataInfo.GM_Radar mRadarData = new CanDataInfo.GM_Radar();
    private CanDataInfo.GM_WarnVoice mVoiceData = new CanDataInfo.GM_WarnVoice();
    private boolean mbLayout;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        InitUI();
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        CanJni.GMGetCarRadar(this.mRadarData);
        CanJni.GMGetWarnVoice(this.mVoiceData);
        if (i2b(this.mRadarData.UpdateOnce) && (!check || i2b(this.mRadarData.Update))) {
            this.mRadarData.Update = 0;
            this.mItemRadarSW.SetCheck(this.mRadarData.fgOn);
        }
        if (!i2b(this.mVoiceData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mVoiceData.Update)) {
            this.mVoiceData.Update = 0;
            this.mItemWarnVoice.SetCurVal(this.mVoiceData.Voice);
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
        QueryData();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mManager = new CanScrollList(this);
        this.mItemWarnVoice = new CanItemProgressList((Context) this, R.string.can_warn_voice);
        this.mItemWarnVoice.SetMinMax(0, 15);
        this.mItemWarnVoice.SetIdCallBack(2, this);
        this.mManager.AddView(this.mItemWarnVoice.GetView());
        this.mItemRadarSW = AddCheckItem(R.string.can_r_radar_sw, 1);
        this.mItemFsSet = new CanItemTextBtnList((Context) this, R.string.can_factory_set);
        this.mItemFsSet.SetIdClickListener(this, 3);
        this.mManager.AddView(this.mItemFsSet.GetView());
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        for (int i = 1; i <= 3; i++) {
            ShowItem(i);
        }
    }

    /* access modifiers changed from: protected */
    public boolean IsHaveItem(int item) {
        switch (item) {
        }
        return i2b(0);
    }

    /* access modifiers changed from: protected */
    public void ShowItem(int item) {
        boolean show = IsHaveItem(item);
        switch (item) {
            case 1:
                this.mItemRadarSW.ShowGone(show);
                return;
            case 2:
                this.mItemWarnVoice.ShowGone(show);
                return;
            case 3:
                this.mItemFsSet.ShowGone(show);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public CanItemSwitchList AddCheckItem(int resId, int Id) {
        CanItemSwitchList item = new CanItemSwitchList(this, resId);
        item.SetIdClickListener(this, Id);
        this.mManager.AddView(item.GetView());
        return item;
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                CanJni.GMRadarCtrl(Neg(this.mRadarData.fgOn));
                return;
            case 3:
                new CanItemMsgBox(3, this, R.string.can_sure_setup, this);
                return;
            default:
                return;
        }
    }

    public void UserAll() {
        ResetData(true);
    }

    public void onChanged(int id, int pos) {
        if (id == 2) {
            CanJni.GMWarnVoiceCtrl(pos);
        }
    }

    public void onOK(int param) {
        CanJni.GMCarCtrl(128, 1);
    }
}
