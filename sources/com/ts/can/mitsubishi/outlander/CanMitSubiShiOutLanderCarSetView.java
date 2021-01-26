package com.ts.can.mitsubishi.outlander;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;
import com.ts.canview.CanItemMsgBox;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemSwitchList;

public class CanMitSubiShiOutLanderCarSetView extends CanScrollCarInfoView {
    private static final int ITEM_ACAC = 20;
    private static final int ITEM_ADU = 18;
    private static final int ITEM_AFWM = 24;
    private static final int ITEM_ALOT = 8;
    private static final int ITEM_APT = 25;
    private static final int ITEM_ARDAS = 23;
    private static final int ITEM_AWFBOTOTWL = 4;
    private static final int ITEM_DTBU = 17;
    private static final int ITEM_ELOWRU = 10;
    private static final int ITEM_FFAR = 22;
    private static final int ITEM_FWAR = 21;
    private static final int ITEM_HAO = 7;
    private static final int ITEM_HTWEV = 9;
    private static final int ITEM_ILT = 11;
    private static final int ITEM_ILTADIC = 12;
    private static final int ITEM_OTFTLTT3F = 15;
    private static final int ITEM_OTLG3F = 14;
    private static final int ITEM_RAC = 19;
    private static final int ITEM_RESET = 26;
    private static final int ITEM_RUTAL = 1;
    private static final int ITEM_RWAWIR = 6;
    private static final int ITEM_RWII = 5;
    private static final int ITEM_SOC = 13;
    private static final int ITEM_TOAR = 16;
    private static final int ITEM_TSLA = 0;
    private static final int ITEM_WLTW = 3;
    private static final int ITEM_WWIO = 2;
    private String[] mACACArr;
    private String[] mADUArr;
    private String[] mAFWMArr;
    private String[] mALOTArr;
    private String[] mAPTArr;
    private String[] mDTBUArr;
    private String[] mELOWRUArr;
    private String[] mFFARArr;
    private String[] mFWARArr;
    private String[] mHTWEVArr;
    private String[] mILTADICArr;
    private String[] mILTArr;
    private String[] mNameArr;
    private String[] mOTFTLTT3FArr;
    private String[] mRACArr;
    private String[] mRUTALArr;
    private String[] mRWAWIRArr;
    private String[] mRWIIArr;
    private String[] mSOCArr;
    private CanDataInfo.SLOutLanderSetData mSetData;
    private String[] mTOARArr;
    private String[] mTSLAArr;
    private String[] mWWIOArr;

    public CanMitSubiShiOutLanderCarSetView(Activity activity) {
        super(activity, 27);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                CanJni.OutLanderCarSet(1, item);
                return;
            case 1:
                CanJni.OutLanderCarSet(2, item);
                return;
            case 2:
                CanJni.OutLanderCarSet(16, item);
                return;
            case 5:
                CanJni.OutLanderCarSet(19, item);
                return;
            case 6:
                CanJni.OutLanderCarSet(20, item);
                return;
            case 8:
                CanJni.OutLanderCarSet(33, item);
                return;
            case 9:
                CanJni.OutLanderCarSet(34, item);
                return;
            case 10:
                CanJni.OutLanderCarSet(35, item);
                return;
            case 11:
                CanJni.OutLanderCarSet(36, item);
                return;
            case 12:
                CanJni.OutLanderCarSet(37, item);
                return;
            case 13:
                CanJni.OutLanderCarSet(48, item);
                return;
            case 15:
                CanJni.OutLanderCarSet(50, item);
                return;
            case 16:
                CanJni.OutLanderCarSet(64, item);
                return;
            case 17:
                CanJni.OutLanderCarSet(65, item);
                return;
            case 18:
                CanJni.OutLanderCarSet(66, item);
                return;
            case 19:
                CanJni.OutLanderCarSet(80, item);
                return;
            case 20:
                CanJni.OutLanderCarSet(81, item);
                return;
            case 21:
                CanJni.OutLanderCarSet(82, item);
                return;
            case 22:
                CanJni.OutLanderCarSet(83, item);
                return;
            case 24:
                CanJni.OutLanderCarSet(96, item);
                return;
            case 25:
                CanJni.OutLanderCarSet(97, item);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        int id = ((Integer) v.getTag()).intValue();
        switch (id) {
            case 3:
                CanJni.OutLanderCarSet(17, Neg(this.mSetData.WipersLinkedtoWasher));
                return;
            case 4:
                CanJni.OutLanderCarSet(18, Neg(this.mSetData.AutomaticWashFunctionByOneTouchOfTheWasherLever));
                return;
            case 7:
                CanJni.OutLanderCarSet(32, Neg(this.mSetData.HeadlightAutoOff));
                return;
            case 14:
                CanJni.OutLanderCarSet(49, Neg(this.mSetData.OneTouchLeverGivingFlashes));
                return;
            case 23:
                CanJni.OutLanderCarSet(84, Neg(this.mSetData.AutoRearDefrostAtStart));
                return;
            case 26:
                new CanItemMsgBox(id, getActivity(), R.string.can_sure_reset, new CanItemMsgBox.onMsgBoxClick() {
                    public void onOK(int param) {
                        CanJni.OutLanderCarSet(0, 1);
                    }
                }, (CanItemMsgBox.onMsgBoxClick2) null);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_zddlmd, R.string.can_csldjxsysq, R.string.can_znysksgn, R.string.can_zdgdsjsz, R.string.can_znysksgn, R.string.can_zdgdsjsz, R.string.can_zddlmd, R.string.can_csldjxsysq, R.string.can_znysksgn, R.string.can_zdgdsjsz, R.string.can_znysksgn, R.string.can_zdgdsjsz, R.string.can_zddlmd, R.string.can_csldjxsysq, R.string.can_znysksgn, R.string.can_zdgdsjsz, R.string.can_znysksgn, R.string.can_zdgdsjsz, R.string.can_zddlmd, R.string.can_csldjxsysq, R.string.can_znysksgn, R.string.can_zdgdsjsz, R.string.can_znysksgn, R.string.can_zdgdsjsz, R.string.can_znysksgn, R.string.can_zdgdsjsz, R.string.can_reset_set};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.TITLE};
        int[] mNullArr = new int[0];
        this.mPopValueIds[0] = mNullArr;
        this.mPopValueIds[1] = mNullArr;
        this.mPopValueIds[2] = mNullArr;
        this.mPopValueIds[5] = mNullArr;
        this.mPopValueIds[6] = mNullArr;
        this.mPopValueIds[8] = mNullArr;
        this.mPopValueIds[9] = mNullArr;
        this.mPopValueIds[10] = mNullArr;
        this.mPopValueIds[11] = mNullArr;
        this.mPopValueIds[12] = mNullArr;
        this.mPopValueIds[13] = mNullArr;
        this.mPopValueIds[15] = mNullArr;
        this.mPopValueIds[16] = mNullArr;
        this.mPopValueIds[17] = mNullArr;
        this.mPopValueIds[18] = mNullArr;
        this.mPopValueIds[19] = mNullArr;
        this.mPopValueIds[20] = mNullArr;
        this.mPopValueIds[21] = mNullArr;
        this.mPopValueIds[22] = mNullArr;
        this.mPopValueIds[24] = mNullArr;
        this.mPopValueIds[25] = mNullArr;
        this.mSetData = new CanDataInfo.SLOutLanderSetData();
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        super.InitUI();
        initArr();
        ((CanItemSwitchList) this.mItemObjects[3]).GetBtn().setText(this.mNameArr[3]);
        ((CanItemSwitchList) this.mItemObjects[4]).GetBtn().setText(this.mNameArr[4]);
        ((CanItemSwitchList) this.mItemObjects[7]).GetBtn().setText(this.mNameArr[7]);
        ((CanItemSwitchList) this.mItemObjects[14]).GetBtn().setText(this.mNameArr[14]);
        ((CanItemSwitchList) this.mItemObjects[23]).GetBtn().setText(this.mNameArr[23]);
        ReChangePopupList(0, this.mTSLAArr);
        ReChangePopupList(1, this.mRUTALArr);
        ReChangePopupList(2, this.mWWIOArr);
        ReChangePopupList(5, this.mRWIIArr);
        ReChangePopupList(6, this.mRWAWIRArr);
        ReChangePopupList(8, this.mALOTArr);
        ReChangePopupList(9, this.mHTWEVArr);
        ReChangePopupList(10, this.mELOWRUArr);
        ReChangePopupList(11, this.mILTArr);
        ReChangePopupList(12, this.mILTADICArr);
        ReChangePopupList(13, this.mSOCArr);
        ReChangePopupList(15, this.mOTFTLTT3FArr);
        ReChangePopupList(16, this.mTOARArr);
        ReChangePopupList(17, this.mDTBUArr);
        ReChangePopupList(18, this.mADUArr);
        ReChangePopupList(19, this.mRACArr);
        ReChangePopupList(20, this.mACACArr);
        ReChangePopupList(21, this.mFWARArr);
        ReChangePopupList(22, this.mFFARArr);
        ReChangePopupList(24, this.mAFWMArr);
        ReChangePopupList(25, this.mAPTArr);
    }

    private void ReChangePopupList(int ID, String[] mStringArr) {
        getScrollManager().RemoveView(ID);
        this.mItemObjects[ID] = getScrollManager().addItemPopupList(this.mItemTitleIds[0], mStringArr, ID, this, ID);
        ((CanItemPopupList) this.mItemObjects[ID]).GetBtn().setText(this.mNameArr[ID]);
    }

    private void initArr() {
        this.mTSLAArr = new String[]{"Once/Unlock:Twice", "Once/Unlock:Off", "Off/Unlock:Twice", "Twice/Unlock:Once", "Off/Unlock:Once", "Twice/Unlock:Off", "OFF"};
        this.mRUTALArr = new String[]{"OFF", "3 Seconds", "5 Seconds"};
        this.mWWIOArr = new String[]{"4 Seconds", "variable", "variable(by vehicle speed)", "variable(rain sensing)"};
        this.mRWIIArr = new String[]{"0 Seconds", "4 Seconds", "8 Seconds", "16 Seconds"};
        this.mRWAWIRArr = new String[]{"Rear wiper switch on", "Front/Rear wiper switch on"};
        this.mALOTArr = new String[]{"Fast", "Quite fast", "Normal", "Quite slow", "Slow"};
        this.mHTWEVArr = new String[]{"OFF", "15 Seconds", "30 Seconds", "1 Minute", "3 Minutes"};
        this.mELOWRUArr = new String[]{"OFF", "Front position lamp on", "Head lights on"};
        this.mILTArr = new String[]{"Never", "3 Minutes", "30 Minutes", "60 Minutes"};
        this.mILTADICArr = new String[]{"0 Second", "7.5 Seconds", "15 Seconds", "30 Seconds", "1 Minute", "2 Minutes", "3 Minutes"};
        this.mSOCArr = new String[]{"Ignition switch on or accessory", "Ignition switch on"};
        this.mOTFTLTT3FArr = new String[]{"Short", "Long"};
        this.mTOARArr = new String[]{"30 Seconds", "1 Minute", "2 Minutes", "3 Minutes"};
        this.mDTBUArr = new String[]{"All doors", "Driver door only"};
        this.mADUArr = new String[]{"OFF", "Connects to gearlever in P position", "Connects to ignition switch OFF(LOCK) position"};
        this.mRACArr = new String[]{"Manual", "AutoMatic"};
        this.mACACArr = new String[]{"Manual", "AutoMatic"};
        this.mFWARArr = new String[]{"Normal", "More airflow to foot", "More airflow to windshield"};
        this.mFFARArr = new String[]{"Normal", "More airflow to face", "More airflow to foot"};
        this.mAFWMArr = new String[]{"None", "Fold out(by vehicle speed)", "Fold in/out by ignition switch ON/OFFF(lock) and driver door Open/Closed", "Fold in/out by keyless entry(keyless operation) unlock/lock"};
        this.mAPTArr = new String[]{"Never", "30 Minutes", "60 Minutes"};
        this.mNameArr = new String[]{"Turn signal lights answerback", "Re-unlock timeing after locking", "Windshield wipers intermittent operation", "Wipers linked to washer", "Automatic wash function by one touch of the washer lever", "Rear wiper intermittent interval", "Rear wiper activated when in Reverse", "Headlight Auto-Off", "Auto light on timing", "Headlight timeout when exiting vehicle", "Exterior lights on with remote unlock", "Interior light timeout", "Interior light timeout after door is closed", "Signal operation conditions", "One touch lever giving 3 flashes", "Operating time for the lever to trigger 3 flashes", "Timing of auto relock", "Doors to be unlocked", "Automatic door unlock", "Recirculation automatic control", "Airc conditioner automatic control", "Foot/windshield airflow ratio", "Face/foot airflow ratio", "Automatic rear defrost at (engine) start", "Auto fold wing mirrors", "Accessory power timeout"};
    }

    public void ResetData(boolean check) {
        CanJni.OutLanderGetCaret(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            updateItem(0, this.mSetData.TurnSignalLightsAnswerBack);
            updateItem(1, this.mSetData.ReunlockTimeingAfterLocking);
            updateItem(2, this.mSetData.WindshieldWipersIntermittentOperation);
            updateItem(3, this.mSetData.WipersLinkedtoWasher);
            updateItem(4, this.mSetData.AutomaticWashFunctionByOneTouchOfTheWasherLever);
            updateItem(5, this.mSetData.RearWiperIntermittentInterval);
            updateItem(6, this.mSetData.RearWiperActivatedWhenInReverse);
            updateItem(7, this.mSetData.HeadlightAutoOff);
            updateItem(8, this.mSetData.AutoLightOnTiming);
            updateItem(9, this.mSetData.HeadlightTimeoutWhenExitVehicle);
            updateItem(10, this.mSetData.ExteriorLightsOnWithRemoteUnlock);
            updateItem(11, this.mSetData.InteriorLightTimeout);
            updateItem(12, this.mSetData.InteriorLightTimeoutAfterDoorClosed);
            updateItem(13, this.mSetData.SignalOperationConditions);
            updateItem(14, this.mSetData.OneTouchLeverGivingFlashes);
            updateItem(15, this.mSetData.OperatingTimeForLeverToTriggerFlashes);
            updateItem(16, this.mSetData.TimingOfAutoRelock);
            updateItem(17, this.mSetData.DoorsToBeUnlocked);
            updateItem(18, this.mSetData.AutoDoorUnlock);
            updateItem(19, this.mSetData.RecirculationAutoControl);
            updateItem(20, this.mSetData.AirConditonerAutoControl);
            updateItem(21, this.mSetData.FootWindAirflowRatio);
            updateItem(22, this.mSetData.FaceFootAirflowRatio);
            updateItem(23, this.mSetData.AutoRearDefrostAtStart);
            updateItem(24, this.mSetData.AutoFoldWingMirrors);
            updateItem(25, this.mSetData.AccessoryPowerTimeout);
        }
    }

    public void QueryData() {
        CanJni.OutLanderQuery(64, 0);
    }
}
