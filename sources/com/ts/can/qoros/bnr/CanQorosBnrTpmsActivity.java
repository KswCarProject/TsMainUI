package com.ts.can.qoros.bnr;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.internal.view.SupportMenu;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanBaseActivity;
import com.ts.can.CanCameraUI;
import com.ts.can.WaveView;
import com.ts.canview.CanItemProgressList;
import com.ts.main.common.MainSet;
import com.ts.main.txz.AmapAuto;
import com.ts.other.CustomImgView;
import com.ts.other.CustomTextView;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;
import com.yyw.ts70xhw.KeyDef;

public class CanQorosBnrTpmsActivity extends CanBaseActivity implements UserCallBack, View.OnClickListener, CanItemProgressList.onPosChange {
    public static final int ITEM_GAS_STATION = 1;
    public static final int ITEM_TYBD = 2;
    public static final int ITEM_VOL = 0;
    public static final String TAG = "CanQorosBnrTpmsActivity";
    private static final String[] mWarnArrays = {"胎压正常", "胎压警告"};
    private boolean isTybdClicked = false;
    protected ParamButton mBtnGasStation;
    protected ParamButton mBtnTybd;
    protected CanDataInfo.QorosBnrTpms mData = new CanDataInfo.QorosBnrTpms();
    protected CustomImgView[] mIvCircles = new CustomImgView[4];
    protected CustomImgView mIvGasStation;
    protected CustomImgView[] mIvTyres = new CustomImgView[4];
    protected RelativeLayoutManager mManager;
    protected CanDataInfo.QorosBnrBaseMsg mMsgData = new CanDataInfo.QorosBnrBaseMsg();
    protected CanDataInfo.QorosBnrSet mSetData = new CanDataInfo.QorosBnrSet();
    protected TextView mTvOilUnit;
    protected CustomTextView mTvOilValue;
    protected CustomTextView[] mTvPress = new CustomTextView[4];
    protected CustomTextView mTvStatus;
    protected CustomTextView[] mTvTemp = new CustomTextView[4];
    protected TextView[] mTvTpmUnits = new TextView[4];
    protected CustomTextView[] mTvTpms = new CustomTextView[4];
    protected TextView[] mTvValueUnits = new TextView[3];
    protected CustomTextView[] mTvValues = new CustomTextView[3];
    protected CustomTextView[] mTvWarn = new CustomTextView[4];
    private int mTybdValue = 0;
    protected WaveView mWaveView;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_can_comm_relative);
        if (MainSet.GetScreenType() == 5) {
            initUI_1280x480();
        } else {
            initUI();
        }
    }

    private void initUI() {
        ((RelativeLayout) findViewById(R.id.can_comm_layout)).setBackgroundResource(R.drawable.can_rf_tpms_bg);
        this.mManager = new RelativeLayoutManager(this, R.id.can_comm_layout);
        for (int i = 0; i < 4; i++) {
            this.mIvTyres[i] = this.mManager.AddImage(((i % 2) * 132) + 431, ((i / 2) * 186) + 116);
            this.mIvTyres[i].setStateDrawable(R.drawable.can_rf_tpms_up, R.drawable.can_rf_tpms_dn);
            this.mTvPress[i] = this.mManager.AddCusText(((i % 2) * CanCameraUI.BTN_TRUMPCHI_GS7_MODE4) + 91, ((i / 2) * Can.CAN_CC_HF_DJ) + 69, 281, 50);
            this.mTvPress[i].SetPixelSize(35);
            this.mTvPress[i].setGravity(17);
            this.mTvWarn[i] = this.mManager.AddCusText(((i % 2) * CanCameraUI.BTN_TRUMPCHI_GS7_MODE4) + 91, ((i / 2) * Can.CAN_CC_HF_DJ) + 124, 281, 50);
            this.mTvWarn[i].SetPixelSize(35);
            this.mTvWarn[i].setGravity(17);
        }
    }

    private void initUI_1280x480() {
        this.mManager = new RelativeLayoutManager(this, R.id.can_comm_layout);
        this.mManager.GetLayout().setBackgroundResource(R.drawable.canvw_bg);
        this.mIvCircles[0] = AddImage(47, 80, 184, 90, R.drawable.tpms_left_up, R.drawable.tpms_left_dn);
        this.mIvCircles[1] = AddImage(311, 80, 184, 90, R.drawable.tpms_right_up, R.drawable.tpms_right_dn);
        this.mIvCircles[2] = AddImage(47, 191, 184, 90, R.drawable.tpms_left_up, R.drawable.tpms_left_dn);
        this.mIvCircles[3] = AddImage(311, 191, 184, 90, R.drawable.tpms_right_up, R.drawable.tpms_right_dn);
        this.mManager.AddImageEx(137, 30, 268, 298, R.drawable.can_tpms_car_gz);
        this.mTvTpms[0] = AddImpactText(45, 100, 50, 50);
        this.mTvTpms[1] = AddImpactText(CanCameraUI.BTN_TRUMPCHI_GS4_MODE3, 100, 50, 50);
        this.mTvTpms[2] = AddImpactText(45, 211, 50, 50);
        this.mTvTpms[3] = AddImpactText(CanCameraUI.BTN_TRUMPCHI_GS4_MODE3, 211, 50, 50);
        this.mTvTpmUnits[0] = AddUnitText(96, 115, "Bar");
        this.mTvTpmUnits[1] = AddUnitText(453, 115, "Bar");
        this.mTvTpmUnits[2] = AddUnitText(96, Can.CAN_FORD_SYNC3, "Bar");
        this.mTvTpmUnits[3] = AddUnitText(453, Can.CAN_FORD_SYNC3, "Bar");
        this.mBtnTybd = AddButton(CanCameraUI.BTN_CCH9_MODE14, 89, Can.CAN_RENAUL_KOLEOS_XFY, 66, 2, R.drawable.tpms_btn_calibrat_up, R.drawable.tpms_btn_calibrat_dn);
        this.mBtnTybd.setText(R.string.can_tybd);
        this.mBtnTybd.setTextColor(getTextColor());
        this.mBtnTybd.setGravity(16);
        this.mBtnTybd.setPadding(100, 0, 0, 0);
        this.mBtnGasStation = AddButton(CanCameraUI.BTN_CCH9_MODE14, 201, Can.CAN_RENAUL_KOLEOS_XFY, 66, 1, R.drawable.tpms_btn_station_up, R.drawable.tpms_btn_station_dn);
        this.mBtnGasStation.setText(R.string.can_near_gas_station);
        this.mBtnGasStation.setTextColor(getTextColor());
        this.mBtnGasStation.setGravity(16);
        this.mBtnGasStation.setPadding(90, 0, 0, 0);
        this.mWaveView = new WaveView(this);
        this.mManager.AddView(this.mWaveView, 1069, 83, 70, 200);
        this.mIvGasStation = this.mManager.AddImageEx(990, 64, 168, Can.CAN_FLAT_WC, R.drawable.canvw_elctric_gz);
        this.mTvOilValue = AddImpactText(1074, 162, 40, 40);
        this.mTvOilUnit = AddUnitText(1115, 171, "%");
        this.mManager.AddImageEx(0, 345, 1280, 80, R.drawable.canvw_bottom_bg);
        this.mManager.AddImageEx(34, 365, 90, 60, R.drawable.canvw_road_haul_gz);
        AddText(136, 365, 90, 60, R.string.can_dis_trav);
        this.mManager.AddImageEx(476, 365, 90, 60, R.drawable.canvw_oilwear_gz);
        AddText(578, 365, 90, 60, R.string.can_pjyh);
        this.mManager.AddImageEx(890, 365, 90, 60, R.drawable.canvw_residual_gz);
        AddText(992, 365, 90, 60, R.string.can_sylc);
        this.mTvValues[0] = AddImpactText(Can.CAN_CHRYSLER_ONE_HC, 365, Can.CAN_BENC_ZMYT, 60);
        this.mTvValues[1] = AddImpactText(CanCameraUI.BTN_SENOVA_SUB_BJ40_MODE1, 365, Can.CAN_BENC_ZMYT, 60);
        this.mTvValues[2] = AddImpactText(1060, 365, Can.CAN_BENC_ZMYT, 60);
        this.mTvValueUnits[0] = AddUnitText(380, 385, "KM");
        this.mTvValueUnits[1] = AddUnitText(KeyDef.SKEY_VOLUP_1, 385, "L/100KM");
        this.mTvValueUnits[2] = AddUnitText(1204, 385, "KM");
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
        Log.d(TAG, "-----onResume-----");
        CanJni.QorosBnrQuery(210, 0);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
        Log.d(TAG, "-----onPause-----");
    }

    private void showGasStation(boolean show) {
        this.mBtnGasStation.Show(show);
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) this.mIvGasStation.getLayoutParams();
        lp.topMargin = show ? 17 : 64;
        this.mIvGasStation.setLayoutParams(lp);
        RelativeLayout.LayoutParams lp2 = (RelativeLayout.LayoutParams) this.mWaveView.getLayoutParams();
        lp2.topMargin = show ? 36 : 83;
        this.mWaveView.setLayoutParams(lp2);
        RelativeLayout.LayoutParams lp3 = (RelativeLayout.LayoutParams) this.mTvOilValue.getLayoutParams();
        lp3.topMargin = show ? 115 : 162;
        this.mTvOilValue.setLayoutParams(lp3);
        RelativeLayout.LayoutParams lp4 = (RelativeLayout.LayoutParams) this.mTvOilUnit.getLayoutParams();
        lp4.topMargin = show ? 124 : 171;
        this.mTvOilUnit.setLayoutParams(lp4);
    }

    private void ResetData(boolean check) {
        CanJni.QorosBnrGetCarTpms(this.mData);
        if (i2b(this.mData.UpdateOnce) && (!check || i2b(this.mData.Update))) {
            this.mData.Update = 0;
            if (MainSet.GetScreenType() == 5) {
                setTpmsValue(new int[]{this.mData.Fl, this.mData.Fr, this.mData.Rl, this.mData.Rr});
                this.mIvCircles[0].SetSel(this.mData.FrWarn);
                this.mIvCircles[1].SetSel(this.mData.FlWarn);
                this.mIvCircles[2].SetSel(this.mData.RrWarn);
                this.mIvCircles[3].SetSel(this.mData.RlWarn);
            } else {
                SetVal(0, this.mData.Fl);
                SetVal(1, this.mData.Fr);
                SetVal(2, this.mData.Rl);
                SetVal(3, this.mData.Rr);
                setTyresInfos(this.mData.FrWarn, this.mData.FlWarn, this.mData.RrWarn, this.mData.RlWarn);
            }
        }
        if (MainSet.GetScreenType() == 5) {
            CanJni.QorosBnrGetCarInfo(this.mMsgData);
            if (i2b(this.mMsgData.UpdateOnce) && (!check || i2b(this.mMsgData.Update))) {
                this.mMsgData.Update = 0;
                int oilValue = this.mMsgData.FuleLev;
                this.mWaveView.setProgress(oilValue);
                this.mTvOilValue.setText(String.valueOf(oilValue));
                if (this.mMsgData.KilMile != 16777215) {
                    this.mTvValues[0].setText(String.valueOf(this.mMsgData.KilMile));
                } else {
                    this.mTvValues[0].setText("-.-");
                }
                this.mTvValues[1].setText(String.format("%.1f", new Object[]{Float.valueOf(((float) this.mMsgData.AverConsum) * 0.1f)}));
                this.mTvValues[2].setText(String.valueOf(this.mMsgData.Rang));
            }
            CanJni.QorosBnrGetCarSet(this.mSetData);
            if (!i2b(this.mSetData.UpdateOnce)) {
                return;
            }
            if (!check || i2b(this.mSetData.Update)) {
                this.mSetData.Update = 0;
                int tybd = this.mSetData.Tysdwc;
                if (this.isTybdClicked && this.mTybdValue != tybd && tybd == 1) {
                    Toast.makeText(this, R.string.can_tpms_ok, 1).show();
                    this.isTybdClicked = false;
                }
                this.mTybdValue = tybd;
            }
        }
    }

    private void setTpmsValue(int[] tpms) {
        for (int i = 0; i < this.mTvTpms.length; i++) {
            if (tpms[i] == 255 || tpms[i] == 0) {
                this.mTvTpms[i].setText("-.-");
            } else {
                this.mTvTpms[i].setText(String.format("%.1f", new Object[]{Float.valueOf((((float) tpms[i]) * 1.373f) / 100.0f)}));
            }
        }
    }

    private void setTyresInfos(int fLState, int fRState, int rLState, int rRState) {
        boolean z;
        int[] states = {fLState, fRState, rLState, rRState};
        for (int i = 0; i < this.mIvTyres.length; i++) {
            CustomImgView customImgView = this.mIvTyres[i];
            if (states[i] != 0) {
                z = true;
            } else {
                z = false;
            }
            customImgView.setSelected(z);
            int sta = states[i];
            if (sta > 1) {
                sta = 1;
            }
            this.mTvWarn[i].setText(mWarnArrays[sta]);
            switch (states[i]) {
                case 0:
                    this.mTvWarn[i].setTextColor(-1);
                    break;
                default:
                    this.mTvWarn[i].setTextColor(SupportMenu.CATEGORY_MASK);
                    break;
            }
        }
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                AmapAuto.GetInstance().SearchGasStation();
                return;
            case 2:
                CanJni.QorosBnrCarSet(9, 1);
                this.isTybdClicked = true;
                return;
            default:
                return;
        }
    }

    public void UserAll() {
        ResetData(true);
    }

    public void onChanged(int id, int pos) {
    }

    public String GetPressStr(int press) {
        return String.format("%.1f kpa", new Object[]{Double.valueOf(((double) press) * 1.373d)});
    }

    public String GetTempStr(int temp) {
        if (temp >= 166) {
            return "-- ℃";
        }
        return String.format("%d ℃", new Object[]{Integer.valueOf(temp - 40)});
    }

    public void SetVal(int id, int press) {
        this.mTvPress[id].setText(GetPressStr(press));
    }

    public CustomImgView AddImage(int x, int y, int w, int h, int normal, int selected) {
        CustomImgView img = this.mManager.AddImage(x, y, w, h);
        img.setStateDrawable(normal, selected);
        return img;
    }

    public CustomTextView AddText(int x, int y, int w, int h, int resId) {
        CustomTextView text = this.mManager.AddCusText(x, y, w, h);
        text.setText(resId);
        text.setGravity(17);
        text.setTextColor(Color.parseColor("#969696"));
        return text;
    }

    public CustomTextView AddImpactText(int x, int y, int w, int h) {
        CustomTextView text = this.mManager.AddCusText(x, y, w, h);
        text.setGravity(21);
        text.setTextColor(-1);
        text.setTypeface(Typeface.DEFAULT_BOLD);
        text.setTextSize(0, 24.0f);
        text.setText("-.-");
        return text;
    }

    public TextView AddUnitText(int x, int y, String unit) {
        TextView text = this.mManager.AddText(x, y);
        text.setText(unit);
        text.setTextColor(-1);
        text.setTextSize(0, 18.0f);
        return text;
    }

    public ParamButton AddButton(int x, int y, int w, int h, int id, int normal, int press) {
        ParamButton button = this.mManager.AddButton(x, y, w, h);
        button.setTag(Integer.valueOf(id));
        button.setDrawable(normal, press);
        button.setOnClickListener(this);
        return button;
    }

    public ColorStateList getTextColor() {
        return new ColorStateList(new int[][]{new int[]{16842919}, new int[0]}, new int[]{Color.parseColor("#ff265c"), -1});
    }
}
