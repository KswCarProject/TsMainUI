package com.ts.set;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.SystemProperties;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.bt.BtExe;
import com.ts.can.CanCameraUI;
import com.ts.can.CanIF;
import com.ts.main.common.MainSet;
import com.ts.main.common.MainVerSion;
import com.ts.main.common.tool;
import com.ts.set.setview.SetMainItemTopName;
import com.ts.set.setview.SetMainMtemBtn;
import com.ts.set.setview.SettingNumInuptDlg;
import com.ts.set.setview.UISetSroView;
import com.ts.t3.T3WeakJoinUtils;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.Mcu;

public class SettingEquipmentActivity extends Activity implements View.OnClickListener, SettingNumInuptDlg.onInputOK, UserCallBack {
    private static final String TAG = "SettingEquipmentActivity";
    SettingNumInuptDlg FtSetDialog;
    SetMainMtemBtn[] SetEqument = new SetMainMtemBtn[6];
    private String iccid = null;
    private String imei = null;
    /* access modifiers changed from: private */
    public int inputKey = 0;
    private CanDataInfo.CAN_VerInfo mCAN_VerInfo = new CanDataInfo.CAN_VerInfo();
    private TextView mtvICCID;
    private TextView mtvIMEI;
    int nNUM = 0;
    private final int password = 1314214;
    private String[] setEquipmentOptions;
    SetMainItemTopName topname;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UISetSroView.Inint(this);
        initSetEquipmentOptions();
        UISetSroView.Show(this);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        String ShowInfo;
        char[] mcuVer = new char[32];
        Mcu.GetMcuVer(mcuVer);
        if (getResources().getString(R.string.custom_num_show).equals("TSKJ")) {
            ShowInfo = String.valueOf(this.setEquipmentOptions[0]) + " : " + getResources().getString(R.string.custom_num) + MainSet.GetHMIVersion();
        } else {
            ShowInfo = String.valueOf(this.setEquipmentOptions[0]) + " : " + getResources().getString(R.string.custom_num_show) + MainSet.GetHMIVersion();
        }
        if (MainSet.GetInstance().IsMathToMcu()) {
            this.SetEqument[0].SetTileText(ShowInfo);
        } else {
            this.SetEqument[0].SetTileText(String.valueOf(ShowInfo) + "(Error)");
        }
        int mScrW = SystemProperties.getInt("ro.forfan.hardware.width", 1024);
        int mScrH = SystemProperties.getInt("ro.forfan.hardware.height", 600);
        if (mScrW == 1024 && mScrH == 600) {
            this.SetEqument[1].SetTileText(String.valueOf(this.setEquipmentOptions[1]) + " : " + String.valueOf(mcuVer) + "(H)");
        } else {
            this.SetEqument[1].SetTileText(String.valueOf(this.setEquipmentOptions[1]) + " : " + String.valueOf(mcuVer) + "(W)");
        }
        this.SetEqument[2].SetTileText(String.valueOf(this.setEquipmentOptions[2]) + " : " + MainSet.GetMediaVersion());
        CanJni.GetVersion(this.mCAN_VerInfo);
        this.SetEqument[3].SetTileText(String.valueOf(this.setEquipmentOptions[3]) + " : " + CanIF.byte2String(this.mCAN_VerInfo.VerData));
        this.SetEqument[4].SetTileText(String.valueOf(this.setEquipmentOptions[4]) + " : " + BtExe.getBtInstance().getVersion());
        this.SetEqument[5].SetTileText(String.valueOf(this.setEquipmentOptions[5]) + " : " + MainVerSion.ROM_VERSION);
        this.inputKey = 0;
        for (int i = 0; i < this.SetEqument.length; i++) {
            TextView tv = (TextView) this.SetEqument[i].GetView().findViewById(R.id.set_text);
            if (i < 3) {
                tv.setTag(Integer.valueOf(i));
            } else {
                tv.setTag(Integer.valueOf(i - 1));
            }
            tv.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    SettingEquipmentActivity.this.inputKey = ((Integer) v.getTag()).intValue() + 1 + (SettingEquipmentActivity.this.inputKey * 10);
                    if (SettingEquipmentActivity.this.inputKey == 1314214) {
                        try {
                            SettingEquipmentActivity.this.startActivity(new Intent(SettingEquipmentActivity.this, Class.forName("com.ts.factoryset.FsMainActivity")));
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
        super.onResume();
        if (MainSet.bShowDlg) {
            MainSet.bShowDlg = false;
            this.FtSetDialog = new SettingNumInuptDlg();
            this.FtSetDialog.createDlg(this, this, 8);
        }
        MainTask.GetInstance().SetUserCallBack(this);
    }

    private void initSetEquipmentOptions() {
        this.topname = new SetMainItemTopName(this, getResources().getStringArray(R.array.set_main_options)[8]);
        this.topname.iv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SettingEquipmentActivity.this.finish();
            }
        });
        UISetSroView.AddView(this.topname.GetView(), 1024, -2);
        String string = getResources().getString(R.string.custom_num_show);
        this.setEquipmentOptions = getResources().getStringArray(R.array.set_eqpment_options);
        for (int setOpt = 0; setOpt < 6; setOpt++) {
            switch (setOpt) {
                case 0:
                    this.SetEqument[0] = new SetMainMtemBtn(this, this.setEquipmentOptions[setOpt]);
                    this.SetEqument[0].SetBtnFormat(R.drawable.setup_equipment_set);
                    this.SetEqument[0].SetBtnUserCallback(setOpt, this);
                    UISetSroView.AddView(this.SetEqument[0].GetView(), 1024, 111);
                    break;
                case 1:
                    this.SetEqument[setOpt] = new SetMainMtemBtn(this, this.setEquipmentOptions[setOpt]);
                    if (FtSet.IsIconExist(103) == 1) {
                        this.SetEqument[1].SetBtnFormat(R.drawable.setup_equipment_open);
                        this.SetEqument[1].SetBtnUserCallback(setOpt, this);
                    }
                    UISetSroView.AddView(this.SetEqument[setOpt].GetView(), 1024, 111);
                    break;
                case 2:
                case 4:
                    this.SetEqument[setOpt] = new SetMainMtemBtn(this, this.setEquipmentOptions[setOpt]);
                    UISetSroView.AddView(this.SetEqument[setOpt].GetView(), 1024, 111);
                    break;
                case 3:
                    this.SetEqument[setOpt] = new SetMainMtemBtn(this, this.setEquipmentOptions[setOpt]);
                    CanJni.GetVersion(this.mCAN_VerInfo);
                    if (this.mCAN_VerInfo.UpdateOnce != 1) {
                        break;
                    } else {
                        UISetSroView.AddView(this.SetEqument[setOpt].GetView(), 1024, 111);
                        break;
                    }
                case 5:
                    this.SetEqument[5] = new SetMainMtemBtn(this, this.setEquipmentOptions[setOpt]);
                    this.SetEqument[5].SetBtnFormat(R.drawable.setup_equipment_open);
                    this.SetEqument[5].SetBtnUserCallback(setOpt, this);
                    break;
            }
        }
        int id = getResources().getIdentifier("custom_logo", "drawable", getPackageName());
        if (id != 0 && (this.SetEqument[0].GetView() instanceof RelativeLayout)) {
            ImageView custom_logo = new ImageView(this);
            custom_logo.setImageResource(id);
            Drawable logo = getResources().getDrawable(id, (Resources.Theme) null);
            int width = logo.getIntrinsicWidth();
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(width, logo.getIntrinsicHeight());
            params.leftMargin = 918 - width;
            params.addRule(15);
            ((RelativeLayout) this.SetEqument[0].GetView()).addView(custom_logo, params);
        }
        if (T3WeakJoinUtils.bIsT3WeakPrj) {
            this.mtvIMEI = new TextView(this);
            this.mtvIMEI.setGravity(17);
            this.mtvIMEI.setTextColor(ViewCompat.MEASURED_STATE_MASK);
            RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(CanCameraUI.BTN_TRUMPCHI_GS4_MODE1, 76);
            params2.addRule(11);
            params2.addRule(15);
            ((RelativeLayout) this.SetEqument[2].GetView()).addView(this.mtvIMEI, params2);
            this.mtvICCID = new TextView(this);
            this.mtvICCID.setGravity(17);
            this.mtvICCID.setTextColor(ViewCompat.MEASURED_STATE_MASK);
            ((RelativeLayout) this.SetEqument[4].GetView()).addView(this.mtvICCID, params2);
        }
    }

    public void onClick(View arg0) {
        switch (((Integer) arg0.getTag()).intValue()) {
            case 0:
                this.FtSetDialog = new SettingNumInuptDlg();
                this.FtSetDialog.createDlg(this, this, 8);
                return;
            case 1:
                Mcu.SetCkey(71);
                return;
            default:
                return;
        }
    }

    public void onOK(String val) {
        MainSet.GetInstance().DealFactorySec(val, this);
    }

    public void UserAll() {
        this.nNUM++;
        if (this.nNUM % 30 == 0) {
            this.SetEqument[2].SetTileText(String.valueOf(this.setEquipmentOptions[2]) + " : " + MainSet.GetMediaVersion());
        }
        if (T3WeakJoinUtils.bIsT3WeakPrj) {
            if (this.imei == null) {
                this.imei = tool.getIMEI(this);
                if (!(this.imei == null || this.mtvIMEI == null)) {
                    this.mtvIMEI.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, new BitmapDrawable(tool.createBarCode(this.imei, 500, 76)), (Drawable) null, (Drawable) null);
                    this.mtvIMEI.setText(this.imei);
                }
            }
            if (this.iccid == null) {
                this.iccid = tool.getICCID(this);
                if (this.iccid != null && this.mtvICCID != null) {
                    this.mtvICCID.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, new BitmapDrawable(tool.createBarCode(this.iccid, 500, 76)), (Drawable) null, (Drawable) null);
                    this.mtvICCID.setText(this.iccid);
                }
            }
        }
    }
}
