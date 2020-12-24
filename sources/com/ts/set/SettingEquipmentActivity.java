package com.ts.set;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import com.ts.MainUI.R;
import com.ts.bt.BtExe;
import com.ts.main.common.MainSet;
import com.ts.main.common.MainVerSion;
import com.ts.set.setview.SetMainItemTopName;
import com.ts.set.setview.SetMainMtemBtn;
import com.ts.set.setview.SettingNumInuptDlg;
import com.ts.set.setview.UISetSroView;
import com.yyw.ts70xhw.Mcu;

public class SettingEquipmentActivity extends Activity implements View.OnClickListener, SettingNumInuptDlg.onInputOK {
    private static final String TAG = "SettingEquipmentActivity";
    SettingNumInuptDlg FtSetDialog;
    SetMainMtemBtn[] SetEqument = new SetMainMtemBtn[6];
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
        this.SetEqument[1].SetTileText(String.valueOf(this.setEquipmentOptions[1]) + " : " + String.valueOf(mcuVer));
        this.SetEqument[2].SetTileText(String.valueOf(this.setEquipmentOptions[2]) + " : " + MainSet.GetMediaVersion());
        this.SetEqument[3].SetTileText(String.valueOf(this.setEquipmentOptions[3]) + " : ");
        this.SetEqument[4].SetTileText(String.valueOf(this.setEquipmentOptions[4]) + " : " + BtExe.getBtInstance().getVersion());
        this.SetEqument[5].SetTileText(String.valueOf(this.setEquipmentOptions[5]) + " : " + MainVerSion.ROM_VERSION);
        super.onResume();
    }

    private void initSetEquipmentOptions() {
        this.topname = new SetMainItemTopName(this, getResources().getStringArray(R.array.set_main_options)[8]);
        this.topname.iv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SettingEquipmentActivity.this.finish();
            }
        });
        UISetSroView.AddView(this.topname.GetView(), 1280, 80);
        this.setEquipmentOptions = getResources().getStringArray(R.array.set_eqpment_options);
        for (int setOpt = 0; setOpt < 6; setOpt++) {
            switch (setOpt) {
                case 0:
                    this.SetEqument[0] = new SetMainMtemBtn(this, this.setEquipmentOptions[setOpt]);
                    this.SetEqument[0].SetBtnFormat(R.drawable.setup_equipment_set);
                    this.SetEqument[0].SetBtnUserCallback(setOpt, this);
                    UISetSroView.AddView(this.SetEqument[0].GetView(), 1280, 87);
                    break;
                case 1:
                case 2:
                case 3:
                case 4:
                    this.SetEqument[setOpt] = new SetMainMtemBtn(this, this.setEquipmentOptions[setOpt]);
                    if (setOpt == 3) {
                        break;
                    } else {
                        UISetSroView.AddView(this.SetEqument[setOpt].GetView(), 1280, 87);
                        break;
                    }
                case 5:
                    this.SetEqument[5] = new SetMainMtemBtn(this, this.setEquipmentOptions[setOpt]);
                    this.SetEqument[5].SetBtnFormat(R.drawable.setup_equipment_open);
                    this.SetEqument[5].SetBtnUserCallback(setOpt, this);
                    break;
            }
        }
    }

    public void onClick(View arg0) {
        switch (((Integer) arg0.getTag()).intValue()) {
            case 0:
                this.FtSetDialog = new SettingNumInuptDlg();
                this.FtSetDialog.createDlg(this, this, 8);
                return;
            case 5:
                Mcu.SetCkey(71);
                return;
            default:
                return;
        }
    }

    public void onOK(String val) {
        MainSet.GetInstance().DealFactorySec(val, this);
    }
}
