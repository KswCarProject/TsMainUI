package com.ts.can.vw.touareg;

import android.os.Bundle;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanBaseActivity;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanScrollList;

public class CanTouaregSetUnitActivity extends CanBaseActivity implements View.OnClickListener, UserCallBack, CanItemPopupList.onPopItemClick {
    private static final int ITEM_DISTANCE = 1;
    private static final int ITEM_LANG = 7;
    private static final int ITEM_OIL = 5;
    private static final int ITEM_SPEED = 2;
    private static final int ITEM_TEMP = 3;
    private static final int ITEM_TYRES = 6;
    private static final int ITEM_VOLUME = 4;
    private static final String[] mLangArr = {"English", "chinese", " german", "Italian", "French", "Swedish", "Spanish", "dutch", "portug", "Japanese", "norweg", "finnish", "Danish", "greek", "Arabic", "turkish"};
    private static final String[] mOilDW = {"L/100km", "km/l", "mpg(US)", "mpg(UK)"};
    private static final String[] mRangeDW = {"mi", "km"};
    private static final String[] mSpeedDW = {"m/h", "km/h"};
    private static final String[] mTempDW = {"℉", "℃"};
    private static final String[] mTyresDW = {"kPa", "bar", "psi"};
    private static final String[] mVolumeDW = {"L", "gal(US)", "gal(UK)"};
    private CanItemPopupList mItemLang;
    private CanItemPopupList mItemOil;
    private CanItemPopupList mItemRange;
    private CanItemPopupList mItemSpeed;
    private CanItemPopupList mItemTemp;
    private CanItemPopupList mItemTyres;
    private CanItemPopupList mItemVolume;
    private CanScrollList mManager;
    private CanDataInfo.TouaregWcDw mUnitData = new CanDataInfo.TouaregWcDw();

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        InitUI();
    }

    private void InitUI() {
        this.mManager = new CanScrollList(this);
        this.mItemRange = this.mManager.addItemPopupList(R.string.can_distance_l_c, mRangeDW, 1, (CanItemPopupList.onPopItemClick) this);
        this.mItemSpeed = this.mManager.addItemPopupList(R.string.can_speed, mSpeedDW, 2, (CanItemPopupList.onPopItemClick) this);
        this.mItemTemp = this.mManager.addItemPopupList(R.string.can_temperature, mTempDW, 3, (CanItemPopupList.onPopItemClick) this);
        this.mItemVolume = this.mManager.addItemPopupList(R.string.can_volume, mVolumeDW, 4, (CanItemPopupList.onPopItemClick) this);
        this.mItemOil = this.mManager.addItemPopupList(R.string.can_consumption, mOilDW, 5, (CanItemPopupList.onPopItemClick) this);
        this.mItemTyres = this.mManager.addItemPopupList(R.string.can_pressure, mTyresDW, 6, (CanItemPopupList.onPopItemClick) this);
        this.mItemLang = this.mManager.addItemPopupList(R.string.can_car_lang, mLangArr, 7, (CanItemPopupList.onPopItemClick) this);
        this.mItemRange.ShowGone(false);
        this.mItemSpeed.ShowGone(false);
        this.mItemTemp.ShowGone(false);
        this.mItemVolume.ShowGone(false);
        this.mItemOil.ShowGone(false);
        this.mItemTyres.ShowGone(false);
        if (CanJni.GetCanFsTp() != 310) {
            this.mItemLang.ShowGone(false);
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
        CanJni.TouaregQuery(5, 1, 193);
    }

    private void ResetData(boolean check) {
        CanJni.TouaregGetDwData(this.mUnitData);
        if (!i2b(this.mUnitData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mUnitData.Update)) {
            this.mUnitData.Update = 0;
            this.mItemRange.ShowGone(this.mUnitData.DisAvalid);
            this.mItemSpeed.ShowGone(this.mUnitData.SpeedAvalid);
            this.mItemTemp.ShowGone(this.mUnitData.TempAvalid);
            this.mItemVolume.ShowGone(this.mUnitData.RlAvalid);
            this.mItemOil.ShowGone(this.mUnitData.PowAvalid);
            this.mItemTyres.ShowGone(this.mUnitData.TpmsAvalid);
            this.mItemRange.SetSel(this.mUnitData.Distance);
            this.mItemSpeed.SetSel(this.mUnitData.Speed);
            this.mItemTemp.SetSel(this.mUnitData.Temp);
            this.mItemVolume.SetSel(this.mUnitData.Rl);
            this.mItemOil.SetSel(this.mUnitData.Pwr);
            this.mItemTyres.SetSel(this.mUnitData.TpmsDw);
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
    }

    public void onClick(View v) {
        int intValue = ((Integer) v.getTag()).intValue();
    }

    public int retVal(int Val) {
        if (Val > 0) {
            return 1;
        }
        return 2;
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 1:
                CanJni.TouaregDwSet(1, retVal(item));
                return;
            case 2:
                CanJni.TouaregDwSet(2, retVal(item));
                return;
            case 3:
                CanJni.TouaregDwSet(3, retVal(item));
                return;
            case 4:
                CanJni.TouaregDwSet(4, item + 1);
                return;
            case 5:
                CanJni.TouaregDwSet(5, item + 1);
                return;
            case 6:
                CanJni.TouaregDwSet(6, item + 1);
                return;
            case 7:
                CanJni.TouaregLangCmd(1, item + 1);
                return;
            default:
                return;
        }
    }

    public void UserAll() {
        ResetData(true);
    }
}
