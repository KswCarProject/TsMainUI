package com.ts.can.vw.golf.wc;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanBaseActivity;
import com.ts.can.CanCameraUI;
import com.ts.can.CanFunc;
import com.ts.canview.CanItemMsgBox;
import com.ts.canview.CanPopupDialog;
import com.ts.other.CustomImgView;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;
import com.txznet.sdk.TXZResourceManager;
import com.yyw.ts70xhw.KeyDef;

public class CanGolfWcVehicleStatusActivity extends CanBaseActivity implements View.OnClickListener, CanItemMsgBox.onMsgBoxClick, UserCallBack {
    public static final int BTN_REPORTS = 1;
    public static final int BTN_START_STOP = 2;
    public static final int BTN_TOP_LEFT = 3;
    public static final int BTN_TOP_RIGHT = 4;
    public static final int BTN_TPMS_SET = 5;
    public static final int BTN_TYRES_CHECK = 6;
    protected static DisplayMetrics mDisplayMetrics = new DisplayMetrics();
    protected ParamButton mBtnReports;
    protected ParamButton mBtnStartStop;
    protected ParamButton mBtnTopLeft;
    protected ParamButton mBtnTopRight;
    protected ParamButton mBtnTpmsSet;
    private ParamButton mBtnTyresStatus;
    protected TextView mCenterTitle;
    private int mCurrentPage = 0;
    protected CustomImgView mImgGou;
    protected CustomImgView mImgStaCar;
    protected CustomImgView mImgTpmsCar;
    protected CustomImgView mImgTyresCar;
    private CustomImgView[] mIvTyres = new CustomImgView[4];
    protected RelativeLayoutManager mManager;
    private int mPageCount = 2;
    private TextView[] mRealTyres = new TextView[4];
    private TextView[] mReferenceTyres = new TextView[4];
    private CanDataInfo.GolfWcDirectTpms mTpmsData = new CanDataInfo.GolfWcDirectTpms();
    protected TextView[] mTpmsTip = new TextView[4];
    private int[] mTpmsTipArrays = {R.string.can_tpms_tip1, R.string.can_tpms_tip2, R.string.can_tpms_tip3, R.string.can_tpms_tip4};
    protected TextView mTvNoReports;
    protected TextView mTvOneReport;
    private CanDataInfo.GolfWcTyres mTyresAdt = new CanDataInfo.GolfWcTyres();
    private String[] mTyresArray;
    private CanDataInfo.GolfWcTyres mTyresData = new CanDataInfo.GolfWcTyres();
    private TextView mTyresUnit;
    private String[] mTyresWarnArray;
    private TextView mTyresWarnTip;
    private int[] mUnits = {R.string.can_pressure_kpa, R.string.can_pressure_bar, R.string.can_pressure_psi};
    private CanDataInfo.GolfWcVehicleWarn mVehicleWarn = new CanDataInfo.GolfWcVehicleWarn();

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_relative);
        this.mManager = new RelativeLayoutManager(this, R.id.can_comm_layout);
        getWindowManager().getDefaultDisplay().getMetrics(mDisplayMetrics);
        Log.d("nyw", String.format("%d,%d", new Object[]{Integer.valueOf(mDisplayMetrics.widthPixels), Integer.valueOf(mDisplayMetrics.heightPixels)}));
        FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) this.mManager.GetLayout().getLayoutParams();
        lp.width = 1024;
        lp.height = CanCameraUI.BTN_NISSAN_XTRAL_RVS_ASSIST6;
        lp.gravity = 17;
        this.mManager.GetLayout().setLayoutParams(lp);
        InitUI();
        this.mManager.GetLayout().setScaleX((((float) mDisplayMetrics.widthPixels) * 1.0f) / 1024.0f);
        this.mManager.GetLayout().setScaleY((((float) mDisplayMetrics.heightPixels) * 1.0f) / 600.0f);
        Log.d("nyw", String.format("%.2f,%.2f", new Object[]{Float.valueOf((((float) mDisplayMetrics.widthPixels) * 1.0f) / 1024.0f), Float.valueOf((((float) mDisplayMetrics.heightPixels) * 1.0f) / 600.0f)}));
    }

    private void InitUI() {
        this.mImgStaCar = this.mManager.AddImage(CanCameraUI.BTN_CHANA_ALSVINV7_MODE1, Can.CAN_CHRYSLER_TXB, 470, Can.CAN_FLAT_WC);
        this.mImgStaCar.setStateDrawable(R.drawable.can_golf_car_up, R.drawable.can_golf_car_dn);
        this.mImgGou = this.mManager.AddImage(50, 162, 45, 39);
        this.mImgGou.setImageResource(R.drawable.can_golf_check);
        this.mTvNoReports = this.mManager.AddText(100, 161, CanCameraUI.BTN_TRUMPCHI_GS4_MODE1, 40);
        this.mTvNoReports.setTextSize(0, 30.0f);
        this.mTvNoReports.setTextColor(-1);
        this.mTvNoReports.setGravity(19);
        this.mTvNoReports.setText(R.string.can_no_entries);
        this.mTvOneReport = this.mManager.AddText(50, 161, 960, 40);
        this.mTvOneReport.setTextSize(0, 30.0f);
        this.mTvOneReport.setTextColor(-1);
        this.mTvOneReport.setGravity(19);
        this.mTvOneReport.setText(R.string.can_no_entries);
        this.mBtnReports = this.mManager.AddButton(30, 144, 439, 83);
        this.mBtnReports.setStateUpDn(R.drawable.can_golf_warning_up, R.drawable.can_golf_warning_dn);
        this.mBtnReports.setTextSize(0, 45.0f);
        this.mBtnReports.setTextColor(-1);
        this.mBtnReports.setPadding(80, 0, 0, 0);
        this.mBtnReports.setTag(1);
        this.mBtnReports.setOnClickListener(this);
        this.mBtnStartStop = this.mManager.AddButton(28, 373, 352, 90);
        this.mBtnStartStop.setStateUpDn(R.drawable.can_golf_info_up, R.drawable.can_golf_info_dn);
        this.mBtnStartStop.setText(R.string.can_start_stop);
        this.mBtnStartStop.setTextColor(-1);
        this.mBtnStartStop.setPadding(80, 0, 0, 0);
        this.mBtnStartStop.setTag(2);
        this.mBtnStartStop.setOnClickListener(this);
        int lenstr = getResources().getString(R.string.can_start_stop).length();
        if (lenstr < 10) {
            lenstr = 10;
        }
        this.mBtnStartStop.setTextSize(0, (float) (450 / lenstr));
        this.mCenterTitle = this.mManager.AddText(262, 30, 500, 74);
        this.mCenterTitle.setTextColor(-1);
        this.mCenterTitle.setTextSize(0, 60.0f);
        this.mCenterTitle.setGravity(17);
        this.mCenterTitle.setText(R.string.can_vehi_status);
        this.mBtnTopLeft = this.mManager.AddButton(28, 30, 74, 74);
        this.mBtnTopLeft.setStateUpDn(R.drawable.can_golf_vup_up, R.drawable.can_golf_vup_dn);
        this.mBtnTopLeft.setTag(3);
        this.mBtnTopLeft.setOnClickListener(this);
        this.mBtnTopRight = this.mManager.AddButton(922, 30, 74, 74);
        this.mBtnTopRight.setStateUpDn(R.drawable.can_golf_vdn_up, R.drawable.can_golf_vdn_dn);
        this.mBtnTopRight.setTag(4);
        this.mBtnTopRight.setOnClickListener(this);
        this.mBtnTpmsSet = this.mManager.AddButton(29, 395, 213, 90);
        this.mBtnTpmsSet.setStateUpDn(R.drawable.can_golf_warning_set_up, R.drawable.can_golf_warning_set_dn);
        this.mBtnTpmsSet.setOnClickListener(this);
        this.mBtnTpmsSet.setTag(5);
        this.mImgTpmsCar = this.mManager.AddImage(586, Can.CAN_FORD_SYNC3, 348, 267);
        this.mImgTpmsCar.setImageResource(R.drawable.can_golf_car02);
        for (int i = 0; i < this.mTpmsTip.length; i++) {
            this.mTpmsTip[i] = this.mManager.AddText(29, (i * 50) + 150, CanCameraUI.BTN_TRUMPCHI_GS4_MODE1, 50);
            this.mTpmsTip[i].setTextSize(0, 35.0f);
            this.mTpmsTip[i].setTextColor(-1);
            this.mTpmsTip[i].setText(this.mTpmsTipArrays[i]);
            this.mTpmsTip[i].setGravity(19);
        }
        this.mImgGou.Show(false);
        this.mBtnReports.Show(false);
        setViewShow((View) this.mTvNoReports, false);
        setViewShow((View) this.mTvOneReport, false);
        initTyresViews();
    }

    private void initTyresViews() {
        this.mBtnTyresStatus = this.mManager.AddButton(28, CanCameraUI.BTN_TRUMPCHI_GS4_MODE1, 386, 80);
        this.mBtnTyresStatus.setStateUpDn(R.drawable.can_golf_teramont_up, R.drawable.can_golf_teramont_dn);
        this.mBtnTyresStatus.setText(TXZResourceManager.STYLE_DEFAULT);
        this.mBtnTyresStatus.setPadding(30, 0, 0, 0);
        this.mBtnTyresStatus.setTextColor(-1);
        this.mBtnTyresStatus.setTextSize(0, 30.0f);
        this.mBtnTyresStatus.setTag(6);
        this.mBtnTyresStatus.setOnClickListener(this);
        this.mBtnTyresStatus.Show(false);
        for (int i = 0; i < this.mRealTyres.length; i++) {
            this.mRealTyres[i] = this.mManager.AddText(((i % 2) * 300) + 540, ((i / 2) * 160) + 160);
            this.mRealTyres[i].setTextSize(0, 35.0f);
            this.mRealTyres[i].setTextColor(-1);
            this.mRealTyres[i].setText(TXZResourceManager.STYLE_DEFAULT);
            this.mRealTyres[i].setGravity(17);
        }
        for (int i2 = 0; i2 < this.mReferenceTyres.length; i2++) {
            this.mReferenceTyres[i2] = this.mManager.AddText(((i2 % 2) * 300) + 540, ((i2 / 2) * 160) + 210);
            this.mReferenceTyres[i2].setTextSize(0, 25.0f);
            this.mReferenceTyres[i2].setTextColor(Color.parseColor("#a1a1a1"));
            this.mReferenceTyres[i2].setText(TXZResourceManager.STYLE_DEFAULT);
            this.mReferenceTyres[i2].setGravity(17);
        }
        this.mTyresUnit = this.mManager.AddText(CanCameraUI.BTN_CC_WC_DIRECTION1, 450);
        this.mTyresUnit.setTextSize(0, 35.0f);
        this.mTyresUnit.setTextColor(-1);
        this.mTyresUnit.setText(TXZResourceManager.STYLE_DEFAULT);
        this.mTyresArray = getResources().getStringArray(R.array.can_golf_teramont_tyres_array);
        String str = this.mTyresArray[0];
        this.mTyresArray[0] = this.mTyresArray[1];
        this.mTyresArray[1] = str;
        this.mIvTyres[0] = this.mManager.AddImage(635, 193);
        this.mIvTyres[0].setStateDrawable(R.drawable.can_vw_mqb_tyre01_up, R.drawable.can_vw_mqb_tyre01_dn);
        this.mIvTyres[1] = this.mManager.AddImage(KeyDef.SKEY_POWEWR_4, 193);
        this.mIvTyres[1].setStateDrawable(R.drawable.can_vw_mqb_tyre01_up, R.drawable.can_vw_mqb_tyre01_dn);
        this.mIvTyres[2] = this.mManager.AddImage(CanCameraUI.BTN_CCH9_MODE6, 310);
        this.mIvTyres[2].setStateDrawable(R.drawable.can_vw_mqb_tyre02_up, R.drawable.can_vw_mqb_tyre02_dn);
        this.mIvTyres[3] = this.mManager.AddImage(KeyDef.SKEY_SEEKUP_4, 310);
        this.mIvTyres[3].setStateDrawable(R.drawable.can_vw_mqb_tyre02_up, R.drawable.can_vw_mqb_tyre02_dn);
        this.mImgTyresCar = this.mManager.AddImageEx(540, 150, 348, 267, R.drawable.can_vw_mqb_taiya);
        this.mTyresWarnTip = this.mManager.AddText(45, 150);
        this.mTyresWarnTip.setTextSize(0, 40.0f);
        this.mTyresWarnTip.setTextColor(-1);
        this.mTyresWarnTip.setText(TXZResourceManager.STYLE_DEFAULT);
        this.mTyresWarnArray = getResources().getStringArray(R.array.can_golf_teramont_tyres_warn_array);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        checkDirectTpms();
        if (CanFunc.mfgShowTpms && this.mPageCount == 3) {
            this.mCurrentPage = 2;
        }
        ShowPage();
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
    }

    private boolean checkDirectTpms() {
        CanJni.GolfWcGetTyresData(1, this.mTyresAdt);
        if (this.mTyresAdt.DirectTpms == 1) {
            this.mPageCount = 3;
            return true;
        }
        this.mPageCount = 2;
        return false;
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
        CanFunc.mfgShowTpms = false;
    }

    private void ResetData(boolean check) {
        checkDirectTpms();
        if (this.mPageCount == 2 && this.mCurrentPage == 2) {
            PageSwitch(true);
        } else if (this.mCurrentPage == 1) {
        } else {
            if (this.mCurrentPage == 2) {
                CanJni.GolfWcGetTyresData(0, this.mTyresData);
                if (i2b(this.mTyresData.UpdateOnce) && (!check || i2b(this.mTyresData.Update))) {
                    this.mTyresData.Update = 0;
                    int tyres = this.mTyresData.DirectTpms;
                    if (tyres >= 0 && tyres < this.mTyresArray.length) {
                        this.mBtnTyresStatus.setText(this.mTyresArray[tyres]);
                    }
                }
                CanJni.GolfWcGetDirectTpmsData(this.mTpmsData);
                if (i2b(this.mTpmsData.UpdateOnce) && (!check || i2b(this.mTpmsData.Update))) {
                    this.mTpmsData.Update = 0;
                    int unit = this.mTpmsData.DW;
                    if (unit >= 0 && unit < this.mUnits.length) {
                        this.mTyresUnit.setText(this.mUnits[unit]);
                        setTyresValues(unit, this.mTpmsData.CurVal, this.mTpmsData.RefVal);
                    }
                    int[] tpmsSta = {this.mTpmsData.FlSta, this.mTpmsData.FrSta, this.mTpmsData.RlSta, this.mTpmsData.RrSta};
                    for (int i = 0; i < this.mIvTyres.length; i++) {
                        this.mIvTyres[i].SetSel(tpmsSta[i]);
                    }
                    if (i2b(this.mTpmsData.FlSta) || i2b(this.mTpmsData.FrSta) || i2b(this.mTpmsData.RlSta) || i2b(this.mTpmsData.RrSta)) {
                        this.mTyresWarnTip.setText(this.mTyresWarnArray[1]);
                    } else {
                        this.mTyresWarnTip.setText(this.mTyresWarnArray[0]);
                    }
                }
                if (!check) {
                    CanJni.GolfWcQueryData(1, 72);
                    Sleep(5);
                    CanJni.GolfWcQueryData(1, 70);
                    return;
                }
                return;
            }
            CanJni.GolfWcGetVehicleWarn(this.mVehicleWarn);
            if (i2b(this.mVehicleWarn.UpdateOnce)) {
                if (!check || i2b(this.mVehicleWarn.Update)) {
                    this.mVehicleWarn.Update = 0;
                    setViewShow((View) this.mTvNoReports, false);
                    this.mImgGou.Show(false);
                    this.mBtnReports.Show(false);
                    setViewShow((View) this.mTvOneReport, false);
                    this.mImgStaCar.SetSel(this.mVehicleWarn.Num);
                    if (this.mVehicleWarn.Num == 0) {
                        this.mImgGou.Show(true);
                        setViewShow((View) this.mTvNoReports, true);
                    } else if (1 == this.mVehicleWarn.Num) {
                        setViewShow((View) this.mTvOneReport, true);
                        int index = this.mVehicleWarn.Warn[0];
                        if (index < CanGolfWcReportsActivity.getReportsArrays(this).length) {
                            this.mTvOneReport.setText(CanGolfWcReportsActivity.getReportsArrays(this)[index]);
                        } else {
                            this.mTvOneReport.setText(TXZResourceManager.STYLE_DEFAULT);
                        }
                    } else if (this.mVehicleWarn.Num > 1) {
                        this.mBtnReports.Show(true);
                        this.mBtnReports.setText(String.format("%d %s", new Object[]{Integer.valueOf(this.mVehicleWarn.Num), getResources().getString(R.string.can_gf_reports)}));
                    }
                }
            } else if (!check) {
                CanJni.GolfWcQueryData(1, 116);
            }
        }
    }

    private void setTyresValues(int unit, int[] realValues, int[] referenceValues) {
        for (int i = 0; i < this.mRealTyres.length; i++) {
            if (unit == 0) {
                this.mRealTyres[i].setText(String.format("%.1f", new Object[]{Float.valueOf(((float) realValues[i]) / 10.0f)}));
                this.mReferenceTyres[i].setText(String.format("(%.1f)", new Object[]{Float.valueOf(((float) referenceValues[i]) / 10.0f)}));
            } else if (unit == 1) {
                this.mRealTyres[i].setText(String.format("%.1f", new Object[]{Float.valueOf(((float) realValues[i]) / 10.0f)}));
                this.mReferenceTyres[i].setText(String.format("(%.1f)", new Object[]{Float.valueOf(((float) referenceValues[i]) / 10.0f)}));
            } else {
                this.mRealTyres[i].setText(String.format("%.1f", new Object[]{Float.valueOf(((float) realValues[i]) / 10.0f)}));
                this.mReferenceTyres[i].setText(String.format("(%.1f)", new Object[]{Float.valueOf(((float) referenceValues[i]) / 10.0f)}));
            }
        }
    }

    private void ShowPage() {
        boolean bStatusShow;
        boolean bTPMSShow;
        boolean bTyresShow;
        if (this.mCurrentPage == 0) {
            bStatusShow = true;
        } else {
            bStatusShow = false;
        }
        if (this.mCurrentPage == 1) {
            bTPMSShow = true;
        } else {
            bTPMSShow = false;
        }
        if (this.mCurrentPage == 2) {
            bTyresShow = true;
        } else {
            bTyresShow = false;
        }
        if (bStatusShow) {
            this.mCenterTitle.setText(R.string.can_vehi_status);
            this.mBtnTyresStatus.Show(false);
        } else if (bTPMSShow) {
            this.mCenterTitle.setText(R.string.can_tyres_tpms);
            this.mBtnTyresStatus.Show(false);
        } else if (bTyresShow) {
            this.mCenterTitle.setText(R.string.can_teramont_zjstyjc);
        }
        if (bTPMSShow || bTyresShow) {
            setViewShow((View) this.mTvNoReports, false);
            this.mImgGou.Show(false);
            this.mBtnReports.Show(false);
            setViewShow((View) this.mTvOneReport, false);
        }
        checkDirectTpms();
        if (!bTPMSShow) {
            ResetData(false);
        }
        this.mImgStaCar.Show(bStatusShow);
        this.mBtnStartStop.Show(bStatusShow);
        for (TextView viewShow : this.mTpmsTip) {
            setViewShow((View) viewShow, bTPMSShow);
        }
        this.mBtnTpmsSet.Show(bTPMSShow);
        this.mImgTpmsCar.Show(bTPMSShow);
        this.mImgTyresCar.Show(bTyresShow);
        setViewShow((View) this.mTyresUnit, bTyresShow);
        setViewShow((View) this.mTyresWarnTip, bTyresShow);
        this.mBtnTyresStatus.Show(bTyresShow);
        for (int i = 0; i < this.mRealTyres.length; i++) {
            setViewShow((View) this.mRealTyres[i], bTyresShow);
            setViewShow((View) this.mReferenceTyres[i], bTyresShow);
            setViewShow((View) this.mIvTyres[i], bTyresShow);
        }
    }

    private void PageSwitch(boolean isNext) {
        if (isNext) {
            this.mCurrentPage++;
            this.mCurrentPage = this.mCurrentPage < this.mPageCount ? this.mCurrentPage : 0;
        } else {
            this.mCurrentPage--;
            this.mCurrentPage = this.mCurrentPage < 0 ? this.mPageCount - 1 : this.mCurrentPage;
        }
        ShowPage();
    }

    public void onClick(View v) {
        int Id = ((Integer) v.getTag()).intValue();
        switch (Id) {
            case 1:
                enterSubWin(CanGolfWcReportsActivity.class);
                return;
            case 2:
                enterSubWin(CanGolfWcStartStopActivity.class);
                return;
            case 3:
                PageSwitch(false);
                return;
            case 4:
                PageSwitch(true);
                return;
            case 5:
                new CanItemMsgBox(Id, this, R.string.can_tpms_box, this);
                return;
            case 6:
                new CanPopupDialog((Context) this, this.mTyresArray, this.mTyresData.DirectTpms, v, (CanPopupDialog.OnItemClick) new CanPopupDialog.OnItemClick() {
                    public void onItem(int item) {
                        CanJni.GolfWcTyresSet(4, item);
                    }
                });
                return;
            default:
                return;
        }
    }

    public void onOK(int param) {
        if (param == 5) {
            CanJni.GolfWcTyresSet(1, 1);
        }
    }

    public void UserAll() {
        ResetData(true);
    }
}
