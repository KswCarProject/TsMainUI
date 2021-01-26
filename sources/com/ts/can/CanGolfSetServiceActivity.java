package com.ts.can;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.canview.CanItemBlankTextList;
import com.ts.canview.CanItemTextBtnList;
import com.ts.canview.CanScrollList;
import com.txznet.sdk.TXZResourceManager;

public class CanGolfSetServiceActivity extends CanGolfBaseActivity implements UserCallBack {
    public static final String TAG = "CanGolfSetServiceActivity";
    private CanDataInfo.GolfDaysComm mInspecDays = new CanDataInfo.GolfDaysComm();
    private CanDataInfo.GolfDistanceCommon mInspecDis = new CanDataInfo.GolfDistanceCommon();
    private CanItemBlankTextList mItemInspecTitle;
    private CanItemTextBtnList mItemInspection;
    private CanItemBlankTextList mItemNoTitle;
    private CanItemTextBtnList mItemOil;
    private CanItemBlankTextList mItemOilTitle;
    private CanItemTextBtnList mItemVehiNo;
    private CanScrollList mManager;
    private CanDataInfo.GolfDaysComm mOilDays = new CanDataInfo.GolfDaysComm();
    private CanDataInfo.GolfDistanceCommon mOilDis = new CanDataInfo.GolfDistanceCommon();
    private String mStrDays;
    private String[] mStrDisDWArray = {"km", "mi"};
    private String mStrFm1;
    private String mStrFm2;
    private String mStrOverDue;
    private CanDataInfo.GolfVehicleNo mVehicleNo = new CanDataInfo.GolfVehicleNo();

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        InitUI();
        Resources res = getResources();
        this.mStrDays = res.getString(R.string.can_days);
        this.mStrOverDue = res.getString(R.string.can_overdue);
        this.mStrFm1 = res.getString(R.string.can_gf_service_fm1);
        this.mStrFm2 = res.getString(R.string.can_gf_service_fm2);
        System.out.println(System.getProperty("file.encoding"));
        System.out.println(System.getProperty("user.language"));
        System.out.println(System.getProperty("user.region"));
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        CanJni.GolfGetService(this.mVehicleNo, this.mInspecDays, this.mOilDays, this.mInspecDis, this.mOilDis);
        if (i2b(this.mVehicleNo.UpdateOnce) && (!check || i2b(this.mVehicleNo.Update))) {
            this.mVehicleNo.Update = 0;
            this.mItemVehiNo.SetVal(CanIF.byte2String(this.mVehicleNo.szNo));
        }
        if (i2b(this.mInspecDays.UpdateOnce) && i2b(this.mInspecDis.UpdateOnce) && (!check || i2b(this.mInspecDays.Update) || i2b(this.mInspecDis.Update))) {
            this.mInspecDays.Update = 0;
            this.mInspecDis.Update = 0;
            this.mItemInspection.SetVal(FormatData(this.mInspecDays, this.mInspecDis));
        }
        if (i2b(this.mOilDays.UpdateOnce) && i2b(this.mOilDis.UpdateOnce)) {
            if (!check || i2b(this.mOilDays.Update) || i2b(this.mOilDis.Update)) {
                this.mOilDays.Update = 0;
                this.mOilDis.Update = 0;
                this.mItemOil.SetVal(FormatData(this.mOilDays, this.mOilDis));
            }
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
        CanJni.GolfQuery(99, 0);
        Sleep(5);
        CanJni.GolfQuery(99, 16);
        Sleep(5);
        CanJni.GolfQuery(99, 17);
        Sleep(5);
        CanJni.GolfQuery(99, 32);
        Sleep(5);
        CanJni.GolfQuery(99, 33);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
        QueryData();
    }

    public void UserAll() {
        ResetData(true);
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mManager = new CanScrollList(this);
        this.mManager.RemoveAllViews();
        this.mItemNoTitle = new CanItemBlankTextList((Context) this, R.string.can_vehi_n_o);
        this.mItemInspecTitle = new CanItemBlankTextList((Context) this, R.string.can_inspection);
        this.mItemOilTitle = new CanItemBlankTextList((Context) this, R.string.can_oil_change);
        this.mItemVehiNo = new CanItemTextBtnList((Context) this, 0);
        this.mItemVehiNo.ShowArrow(false);
        this.mItemInspection = new CanItemTextBtnList((Context) this, 0);
        this.mItemInspection.ShowArrow(false);
        this.mItemOil = new CanItemTextBtnList((Context) this, 0);
        this.mItemOil.ShowArrow(false);
        this.mManager.AddView(this.mItemNoTitle.GetView());
        this.mManager.AddView(this.mItemVehiNo.GetView());
        this.mManager.AddView(this.mItemInspecTitle.GetView());
        this.mManager.AddView(this.mItemInspection.GetView());
        this.mManager.AddView(this.mItemOilTitle.GetView());
        this.mManager.AddView(this.mItemOil.GetView());
    }

    /* access modifiers changed from: protected */
    @SuppressLint({"DefaultLocale"})
    public String FormatData(CanDataInfo.GolfDaysComm days, CanDataInfo.GolfDistanceCommon dis) {
        String strDays = TXZResourceManager.STYLE_DEFAULT;
        String strDis = TXZResourceManager.STYLE_DEFAULT;
        switch (days.ShowType) {
            case 0:
                strDays = "--";
                break;
            case 1:
                strDays = String.format("%d %s", new Object[]{Integer.valueOf(days.Value), this.mStrDays});
                break;
            case 2:
                strDays = String.format("%s %d %s", new Object[]{this.mStrOverDue, Integer.valueOf(days.Value), this.mStrDays});
                break;
        }
        switch (dis.ShowType) {
            case 0:
                strDis = "--";
                break;
            case 1:
                strDis = String.format("%d %s", new Object[]{Integer.valueOf(dis.Value), this.mStrDisDWArray[dis.DW & 1]});
                break;
            case 2:
                strDis = String.format("%s %d %s", new Object[]{this.mStrOverDue, Integer.valueOf(days.Value), this.mStrDisDWArray[dis.DW & 1]});
                break;
        }
        return String.format("%s %s %s %s", new Object[]{this.mStrFm1, strDis, this.mStrFm2, strDays});
    }
}
