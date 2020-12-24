package com.ts.can.bmw.withcd;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanBaseActivity;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemProgressList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanItemTextBtnList;
import com.ts.canview.CanScrollList;
import com.yyw.ts70xhw.FtSet;

public class CanBmwWithCDCarInfoActivity extends CanBaseActivity implements UserCallBack, CanItemPopupList.onPopItemClick, CanItemProgressList.onPosChange, View.OnClickListener {
    private static final int ITEM_AUDIO = 7;
    private static final int ITEM_CAN_IAP = 6;
    private static final int ITEM_CARTYPE = 5;
    private static final int ITEM_MAX = 6;
    private static final int ITEM_MIN = 0;
    private static final int ITEM_NAVI_VOL = 1;
    private static final int ITEM_RVS_MIRROR = 3;
    private static final int ITEM_RVS_MODE = 2;
    private static final int ITEM_SYSTEM_VOL = 0;
    private static final int ITEM_TURN_RVS = 4;
    private static int m_CarAudiob = 0;
    private static int m_CarTypeb = 0;
    private static int m_NaviVolb = 0;
    private static int m_RvsMirrorb = 0;
    private static int m_RvsModeb = 0;
    private static int m_SystemVolb = 0;
    private static int m_TurnRvs360b = 0;
    private String[] mAudioArr = {"NUM8", "AUX3"};
    private String[] mCameraArr = {"原车模式", "(CVBS)模式", "360(VGA)模式", "360(CVBS)模式"};
    private CanItemPopupList mCarAudio;
    private CanItemPopupList mCarTypeItem;
    private CanItemTextBtnList mItemCanIap;
    private CanScrollList mManager;
    private CanItemProgressList mNaviVolBar;
    private CanItemSwitchList mRvsMirrorSwitch;
    private CanItemPopupList mRvsModeItem;
    protected CanDataInfo.BmwWithCD_Set mSetData = new CanDataInfo.BmwWithCD_Set();
    private CanItemProgressList mSystemVolBar;
    private CanItemSwitchList mTurnRvs360Switch;
    private String[] mTypeArr = {"Harman1 1280*480", "Harman2 1280*480", "Harman  1280*480 Cic", "Harman1 800*480", "Harman2 800*480", "Alpine  800*480", "Temp5   800*480", "Harman  800*480 Cic", "Alpine  800*480 Cic", "Harman  1280*480 Ccc", "Harman2  800*480 Cic", "Eur     800*480 Ccc", "Jap     1280*480 Ccc"};
    protected CanDataInfo.BmwWithCD_WorkMode mWorkMode = new CanDataInfo.BmwWithCD_WorkMode();

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        System.out.println("onCreate");
        InitUI();
    }

    private void InitUI() {
        this.mManager = new CanScrollList(this);
        this.mCarTypeItem = this.mManager.addItemPopupList(R.string.can_host_configuration, this.mTypeArr, 5, (CanItemPopupList.onPopItemClick) this);
        this.mRvsModeItem = this.mManager.addItemPopupList(R.string.can_camera_360, this.mCameraArr, 2, (CanItemPopupList.onPopItemClick) this);
        this.mRvsMirrorSwitch = this.mManager.addItemCheckBox(R.string.can_rvs_mirror, 3, this);
        this.mTurnRvs360Switch = this.mManager.addItemCheckBox(R.string.can_tigger7_start_avm, 4, this);
        this.mSystemVolBar = this.mManager.addItemProgressList(R.string.can_system_vol, 0, (CanItemProgressList.onPosChange) this);
        this.mSystemVolBar.SetMinMax(0, 40);
        this.mSystemVolBar.SetStep(1);
        this.mSystemVolBar.SetUserValText();
        this.mNaviVolBar = this.mManager.addItemProgressList(R.string.can_navi_vol, 1, (CanItemProgressList.onPosChange) this);
        this.mNaviVolBar.SetMinMax(0, 40);
        this.mNaviVolBar.SetStep(1);
        this.mNaviVolBar.SetUserValText();
        this.mCarAudio = this.mManager.addItemPopupList(R.string.can_bmw_entauxmode, this.mAudioArr, 7, (CanItemPopupList.onPopItemClick) this);
        this.mItemCanIap = AddIcoItem(R.string.can_can_iap, 6);
    }

    /* access modifiers changed from: protected */
    public CanItemTextBtnList AddIcoItem(int text, int id) {
        CanItemTextBtnList item = new CanItemTextBtnList((Context) this, text);
        item.SetIdClickListener(this, id);
        this.mManager.AddView(item.GetView());
        item.ShowGone(true);
        return item;
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
        LayoutUI();
        m_SystemVolb = -1;
        m_NaviVolb = -1;
        m_RvsModeb = -1;
        m_RvsMirrorb = -1;
        m_TurnRvs360b = -1;
        m_CarTypeb = -1;
        m_CarAudiob = -1;
    }

    private void LayoutUI() {
        for (int i = 0; i <= 6; i++) {
            ShowItem(i);
        }
    }

    /* access modifiers changed from: protected */
    public boolean IsHaveItem(int item) {
        int ret = 0;
        switch (item) {
            case 0:
                ret = 0;
                break;
            case 1:
                ret = 0;
                break;
            case 2:
                ret = 1;
                break;
            case 3:
                ret = 1;
                break;
            case 4:
                if ((FtSet.Getlgb1() & 8) > 0) {
                    ret = 1;
                    break;
                }
                break;
            case 5:
                ret = 1;
                break;
            case 6:
                ret = 1;
                break;
            case 7:
                ret = 1;
                break;
        }
        return i2b(ret);
    }

    private void ShowItem(int item) {
        boolean show = IsHaveItem(item);
        switch (item) {
            case 0:
                this.mSystemVolBar.ShowGone(show);
                return;
            case 1:
                this.mNaviVolBar.ShowGone(show);
                return;
            case 2:
                this.mRvsModeItem.ShowGone(show);
                return;
            case 3:
                this.mRvsMirrorSwitch.ShowGone(show);
                return;
            case 4:
                this.mTurnRvs360Switch.ShowGone(show);
                return;
            case 5:
                this.mCarTypeItem.ShowGone(show);
                return;
            case 7:
                this.mCarAudio.ShowGone(show);
                return;
            default:
                return;
        }
    }

    private void ResetData(boolean check) {
        int RvsMode;
        if ((FtSet.Getlgb1() & 16) > 0) {
            RvsMode = 3;
        } else if ((FtSet.Getlgb1() & 8) > 0) {
            RvsMode = 2;
        } else if ((FtSet.Getlgb1() & 1) > 0) {
            RvsMode = 0;
        } else {
            RvsMode = 1;
        }
        int RvsMirror = FtSet.Getlgb1() & 2;
        int TurnRvs = FtSet.Getlgb1() & 32;
        if (!(RvsMode == m_RvsModeb && RvsMirror == m_RvsMirrorb && TurnRvs == m_TurnRvs360b)) {
            m_RvsModeb = RvsMode;
            m_RvsMirrorb = RvsMirror;
            m_TurnRvs360b = TurnRvs;
            this.mRvsModeItem.SetSel(RvsMode);
            this.mRvsMirrorSwitch.SetCheck(RvsMirror);
            this.mTurnRvs360Switch.SetCheck(TurnRvs);
        }
        if (FtSet.Getlgb4() != m_CarTypeb) {
            m_CarTypeb = FtSet.Getlgb4();
            this.mCarTypeItem.SetSel(m_CarTypeb);
        }
        if (FtSet.Getlgb2() != m_CarAudiob) {
            m_CarAudiob = FtSet.Getlgb2();
            this.mCarAudio.SetSel(m_CarAudiob);
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
    }

    public static int IsBmwType() {
        if (FtSet.Getlgb4() == 9) {
            return 128;
        }
        if (FtSet.Getlgb4() == 10) {
            return 9;
        }
        if (FtSet.Getlgb4() == 11) {
            return 126;
        }
        if (FtSet.Getlgb4() == 12) {
            return 129;
        }
        return FtSet.Getlgb4();
    }

    public static int IsBmwAuxType() {
        if (FtSet.Getlgb2() == 1) {
            return 1;
        }
        return 0;
    }

    public void onItem(int id, int item) {
        int temp;
        switch (id) {
            case 2:
                int temp2 = FtSet.Getlgb1() & Can.CAN_CC_HF_DJ;
                if (item == 0) {
                    FtSet.Setlgb1(temp2 | 1);
                } else if (item == 1) {
                    FtSet.Setlgb1(temp2);
                } else if (item == 2) {
                    FtSet.Setlgb1(temp2 | 8);
                } else if (item == 3) {
                    FtSet.Setlgb1(temp2 | 16);
                }
                int temp3 = FtSet.Getlgb1();
                if (FtSet.GetCamTrack() > 0) {
                    temp = temp3 & 191;
                } else {
                    temp = temp3 | 64;
                }
                CanJni.BmwWithCDCarSet(149, temp, 0, 0);
                LayoutUI();
                return;
            case 5:
                FtSet.Setlgb4(item);
                CanJni.BmwWithCDCarSet(162, 0, IsBmwType(), 0);
                return;
            case 7:
                FtSet.Setlgb2(item);
                return;
            default:
                return;
        }
    }

    public void onClick(View v) {
        int temp;
        int temp2;
        switch (((Integer) v.getTag()).intValue()) {
            case 3:
                if ((FtSet.Getlgb1() & 2) > 0) {
                    FtSet.Setlgb1(FtSet.Getlgb1() & Can.CAN_FORD_ESCORT_LY);
                } else {
                    FtSet.Setlgb1(FtSet.Getlgb1() | 2);
                }
                int temp3 = FtSet.Getlgb1();
                if (FtSet.GetCamTrack() > 0) {
                    temp2 = temp3 & 191;
                } else {
                    temp2 = temp3 | 64;
                }
                CanJni.BmwWithCDCarSet(149, temp2, 0, 0);
                return;
            case 4:
                if ((FtSet.Getlgb1() & 32) > 0) {
                    FtSet.Setlgb1(FtSet.Getlgb1() & Can.CAN_X80_RZC);
                } else {
                    FtSet.Setlgb1(FtSet.Getlgb1() | 32);
                }
                int temp4 = FtSet.Getlgb1();
                if (FtSet.GetCamTrack() > 0) {
                    temp = temp4 & 191;
                } else {
                    temp = temp4 | 64;
                }
                CanJni.BmwWithCDCarSet(149, temp, 0, 0);
                return;
            case 6:
                enterSubWin(CanBmwWithCDUpdateActivity.class);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void UserAll() {
        ResetData(true);
    }
}
