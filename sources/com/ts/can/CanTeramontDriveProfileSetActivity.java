package com.ts.can;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.canview.CanItemMsgBox;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanItemTitleValList;
import com.ts.canview.CanScrollList;
import com.ts.main.common.MainSet;
import com.ts.other.CustomImgView;
import com.ts.other.RelativeLayoutManager;
import com.yyw.ts70xhw.KeyDef;

public class CanTeramontDriveProfileSetActivity extends CanBaseActivity implements CanItemPopupList.onPopItemClick, UserCallBack, View.OnClickListener {
    private static final int BTN_ECO = 10;
    private static final int BTN_INDIVIDUAL = 13;
    private static final int BTN_NORMAL = 11;
    private static final int BTN_SPORT = 12;
    public static final String DRIVE_PATTER = "drive_pattern";
    public static final int DRIVE_PATTER_CROSS = 2;
    public static final int DRIVE_PATTER_CROSS_IN = 3;
    public static final int DRIVE_PATTER_ECO = 1;
    public static final int DRIVE_PATTER_SNOW = 0;
    private static final int ITEM_AIR = 3;
    private static final int ITEM_ENGINE = 1;
    private static final int ITEM_PDQB = 5;
    private static final int ITEM_RESET = 7;
    private static final int ITEM_STEERING = 0;
    private static final int ITEM_WHEEL = 2;
    private static final int ITEM_XPFZ = 4;
    private static final int ITEM_ZCFZ = 6;
    private CanItemPopupList mAirItem;
    private int[] mCrossArray = {R.string.can_mode_normal, R.string.can_teramont_cross};
    private CanDataInfo.GolfCrossDriveProfile mCrossProfile = new CanDataInfo.GolfCrossDriveProfile();
    private int mDrivePattern;
    private CustomImgView[] mDrvieProfile = new CustomImgView[4];
    private int[] mEcoArray = {R.string.can_mode_normal, R.string.can_eco};
    private CanItemPopupList mEngineItem;
    private CanScrollList mListManager;
    private CanItemSwitchList mPdqbItem;
    private TextView[] mProfile = new TextView[4];
    private int mProfileType;
    private CanDataInfo.GolfSeatDriveProfile mSeatDriveProfile = new CanDataInfo.GolfSeatDriveProfile();
    private int[] mSportArray = {R.string.can_mode_normal, R.string.can_sport};
    private CanItemPopupList mSteeringItem;
    private CanItemPopupList mWheelItem;
    private CanItemSwitchList mXpfzItem;
    private CanItemSwitchList mZcfzItem;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        this.mDrivePattern = getIntent().getIntExtra("drive_pattern", 0);
        if (this.mDrivePattern == 1) {
            setContentView(R.layout.activity_can_golf_seat_drive_profile);
        } else {
            setContentView(R.layout.activity_can_comm_list);
        }
        InitUI();
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
        super.onPause();
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
    }

    private void InitUI() {
        if (this.mDrivePattern == 1) {
            initEcoItems();
            return;
        }
        this.mListManager = new CanScrollList(this);
        if (this.mDrivePattern == 0) {
            initCommonItems(R.string.can_sport, R.string.can_teramont_xuedi, false, R.string.can_mode_normal);
        } else if (this.mDrivePattern == 2) {
            initCrossItems();
        } else if (this.mDrivePattern == 3) {
            initCrossInItems();
        }
    }

    private void initEcoItems() {
        String[] ecoArray = getResources().getStringArray(R.array.can_teramont_eco_array);
        RelativeLayoutManager manager = new RelativeLayoutManager(this, R.id.can_golf_seat_drive_profile_layout);
        if (MainSet.GetScreenType() == 3) {
            FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) manager.GetLayout().getLayoutParams();
            lp.width = 1024;
            lp.height = CanCameraUI.BTN_NISSAN_XTRAL_RVS_ASSIST6;
            lp.gravity = 17;
            manager.GetLayout().setLayoutParams(lp);
        }
        this.mDrvieProfile[0] = manager.AddImage(115, 150, 190, 184);
        this.mDrvieProfile[0].setStateDrawable(R.drawable.can_dzjcms_car01_up, R.drawable.can_dzjcms_car01_dn, R.drawable.can_dzjcms_car01_dn);
        this.mDrvieProfile[0].setTag(10);
        this.mDrvieProfile[0].setOnClickListener(this);
        this.mDrvieProfile[1] = manager.AddImage(KeyDef.RKEY_ANGLEDN, 150, 190, 184);
        this.mDrvieProfile[1].setStateDrawable(R.drawable.can_dzjcms_car02_up, R.drawable.can_dzjcms_car02_dn, R.drawable.can_dzjcms_car02_dn);
        this.mDrvieProfile[1].setTag(11);
        this.mDrvieProfile[1].setOnClickListener(this);
        this.mDrvieProfile[2] = manager.AddImage(CanCameraUI.BTN_GEELY_YJX6_MODE2, 150, 190, 184);
        this.mDrvieProfile[2].setStateDrawable(R.drawable.can_dzjcms_car03_up, R.drawable.can_dzjcms_car03_dn, R.drawable.can_dzjcms_car03_dn);
        this.mDrvieProfile[2].setTag(12);
        this.mDrvieProfile[2].setOnClickListener(this);
        this.mDrvieProfile[3] = manager.AddImage(724, 150, 190, 184);
        this.mDrvieProfile[3].setStateDrawable(R.drawable.can_dzjcms_car04_up, R.drawable.can_dzjcms_car04_dn, R.drawable.can_dzjcms_car04_dn);
        this.mDrvieProfile[3].setTag(13);
        this.mDrvieProfile[3].setOnClickListener(this);
        for (int i = 0; i < this.mProfile.length; i++) {
            this.mProfile[i] = manager.AddText((i * 203) + 115, 200, 190, 184);
            this.mProfile[i].setTextSize(0, 30.0f);
            this.mProfile[i].setTextColor(-1);
            this.mProfile[i].setText(ecoArray[i]);
            this.mProfile[i].setGravity(17);
        }
        if (MainSet.GetScreenType() == 3) {
            manager.GetLayout().setScaleX(0.78f);
            manager.GetLayout().setScaleY(0.79f);
        }
    }

    private void initCommonItems(int steering, int engine, boolean showWheel, int air) {
        this.mListManager.addItemTitleValList(R.string.can_teramont_steering, 0, false, (View.OnClickListener) null).SetVal(steering);
        this.mListManager.addItemTitleValList(R.string.can_teramont_engine, 0, false, (View.OnClickListener) null).SetVal(engine);
        CanItemTitleValList wheelItem = this.mListManager.addItemTitleValList(R.string.can_teramont_wheelengine, 0, false, (View.OnClickListener) null);
        wheelItem.SetVal(R.string.can_teramont_cross);
        wheelItem.ShowGone(showWheel);
        this.mListManager.addItemTitleValList(R.string.can_dfqc_ac, 0, false, (View.OnClickListener) null).SetVal(air);
    }

    private void initCrossItems() {
        initCommonItems(R.string.can_mode_normal, R.string.can_teramont_cross, true, R.string.can_mode_normal);
        this.mListManager.addItemTitleValList(R.string.can_teramont_xpfzxt, 0, false, (View.OnClickListener) null).SetVal(R.string.can_teramont_open);
        this.mListManager.addItemTitleValList(R.string.can_teramont_pdqbfz, 0, false, (View.OnClickListener) null).SetVal(R.string.can_teramont_open);
        this.mListManager.addItemTitleValList(R.string.can_teramont_zcfz, 0, false, (View.OnClickListener) null).SetVal(R.string.can_teramont_open);
    }

    private void initCrossInItems() {
        this.mSteeringItem = this.mListManager.addItemPopupList(R.string.can_teramont_steering, this.mSportArray, 0, (CanItemPopupList.onPopItemClick) this);
        this.mEngineItem = this.mListManager.addItemPopupList(R.string.can_teramont_engine, this.mCrossArray, 1, (CanItemPopupList.onPopItemClick) this);
        this.mWheelItem = this.mListManager.addItemPopupList(R.string.can_teramont_wheelengine, this.mCrossArray, 2, (CanItemPopupList.onPopItemClick) this);
        this.mAirItem = this.mListManager.addItemPopupList(R.string.can_dfqc_ac, this.mEcoArray, 3, (CanItemPopupList.onPopItemClick) this);
        this.mXpfzItem = this.mListManager.addItemCheckBox(R.string.can_teramont_xpfzxt, 4, this);
        this.mPdqbItem = this.mListManager.addItemCheckBox(R.string.can_teramont_pdqbfz, 5, this);
        this.mZcfzItem = this.mListManager.addItemCheckBox(R.string.can_teramont_zcfz, 6, this);
        this.mListManager.addItemFsSetList(R.string.can_teramont_model_reset, 7, this);
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        int i = 0;
        if (this.mDrivePattern != 0 && this.mDrivePattern != 2) {
            if (this.mDrivePattern == 1) {
                CanJni.GolfGetSeatDriveProfile(this.mSeatDriveProfile);
                if (!i2b(this.mSeatDriveProfile.UpdateOnce)) {
                    return;
                }
                if (!check || i2b(this.mSeatDriveProfile.Update)) {
                    this.mSeatDriveProfile.Update = 0;
                    int type = this.mSeatDriveProfile.ProfileType;
                    for (CustomImgView selected : this.mDrvieProfile) {
                        selected.setSelected(false);
                    }
                    if (type == 0) {
                        this.mDrvieProfile[1].setSelected(true);
                    } else if (type == 1) {
                        this.mDrvieProfile[2].setSelected(true);
                    } else if (type == 2) {
                        this.mDrvieProfile[0].setSelected(true);
                    } else if (type == 3) {
                        this.mDrvieProfile[3].setSelected(true);
                    }
                }
            } else if (this.mDrivePattern == 3) {
                CanJni.TeramontGetCrossProfile(this.mCrossProfile);
                if (!i2b(this.mCrossProfile.UpdateOnce)) {
                    return;
                }
                if (!check || i2b(this.mCrossProfile.Update)) {
                    this.mCrossProfile.Update = 0;
                    this.mSteeringItem.SetSel(this.mCrossProfile.CrossSteering);
                    this.mEngineItem.SetSel(this.mCrossProfile.CrossEngine);
                    this.mWheelItem.SetSel(this.mCrossProfile.CrossFourWheel);
                    int air = this.mCrossProfile.CrossClimate;
                    CanItemPopupList canItemPopupList = this.mAirItem;
                    if (air != 0) {
                        i = 1;
                    }
                    canItemPopupList.SetSel(i);
                    this.mXpfzItem.SetCheck(this.mCrossProfile.CrossDbs);
                    this.mPdqbItem.SetCheck(this.mCrossProfile.CrossRampStart);
                    this.mZcfzItem.SetCheck(this.mCrossProfile.CrossPbc);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
        if (this.mDrivePattern == 3) {
            CanJni.GolfQuery(64, 177);
        } else if (this.mDrivePattern == 1) {
            CanJni.GolfQuery(64, 160);
        }
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                CanJni.GolfSendCmd(Can.CAN_BENZ_SMART_OD, item);
                return;
            case 1:
                CanJni.GolfSendCmd(Can.CAN_FORD_SYNC3, item);
                return;
            case 2:
                CanJni.GolfSendCmd(Can.CAN_JIANGLING_MYX, item);
                return;
            case 3:
                CanJni.GolfSendCmd(Can.CAN_TEANA_OLD_DJ, item == 0 ? 0 : 2);
                return;
            default:
                return;
        }
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 4:
                CanJni.GolfSendCmd(Can.CAN_VOLVO_XFY, Neg(this.mCrossProfile.CrossDbs));
                return;
            case 5:
                CanJni.GolfSendCmd(Can.CAN_CC_HF_DJ, Neg(this.mCrossProfile.CrossRampStart));
                return;
            case 6:
                CanJni.GolfSendCmd(Can.CAN_BYD_S6_S7, Neg(this.mCrossProfile.CrossPbc));
                return;
            case 7:
                new CanItemMsgBox(1, this, R.string.can_teramont_reset_notice, new CanItemMsgBox.onMsgBoxClick() {
                    public void onOK(int param) {
                        CanJni.GolfSendCmd(Can.CAN_FLAT_WC, 1);
                    }
                });
                return;
            case 10:
                CanJni.GolfSendCmd(208, 2);
                startEcoProfileSetActivity(0);
                return;
            case 11:
                CanJni.GolfSendCmd(208, 0);
                startEcoProfileSetActivity(1);
                return;
            case 12:
                CanJni.GolfSendCmd(208, 1);
                startEcoProfileSetActivity(2);
                return;
            case 13:
                CanJni.GolfSendCmd(208, 3);
                startEcoProfileSetActivity(3);
                return;
            default:
                return;
        }
    }

    private void startEcoProfileSetActivity(int type) {
        Intent i = new Intent(this, CanTeramontEcoProfileSetActivity.class);
        i.putExtra("eco_profile", type);
        startActivity(i);
        overridePendingTransition(0, 0);
    }

    public void UserAll() {
        ResetData(true);
    }
}
