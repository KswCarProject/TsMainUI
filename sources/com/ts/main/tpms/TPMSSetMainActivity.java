package com.ts.main.tpms;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.lgb.canmodule.Can;
import com.ts.MainUI.R;
import com.ts.MainUI.StTpms;
import com.ts.can.CanCameraUI;
import com.ts.main.common.MainSet;
import com.ts.main.common.MainUI;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;

public class TPMSSetMainActivity extends Activity {
    private TextView[] ItemDisp = new TextView[5];
    private ParamButton[] TaiwaDw = new ParamButton[3];
    private ParamButton[] TempDw = new ParamButton[2];
    private ParamButton[] TpmsAdd = new ParamButton[3];
    private ParamButton[] TpmsDec = new ParamButton[3];
    RelativeLayoutManager TpmsSetManage;
    private TextView[] TpmsShowinfo = new TextView[3];
    private String[] TpmssetOptions;
    StTpms mStTpms = StTpms.GetInstance();
    int nScrW;
    int nSrcH;

    /* access modifiers changed from: package-private */
    public int PsiToPsk(int nadd, int nPsk) {
        double nRet;
        Log.i("scj", "nPsk=" + nPsk);
        if (nadd == 1) {
            nRet = (((double) nPsk) * 0.14504d) + 0.1d + 0.05d;
        } else {
            nRet = ((((double) nPsk) * 0.14504d) - 0.1d) + 0.05d;
        }
        Log.i("scj", "nPskdd=" + ((int) (nRet / 0.14504d)));
        return (int) (nRet / 0.14504d);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (MainSet.GetScreenType() == 3 || MainSet.GetScreenType() == 6) {
            setContentView(R.layout.activity_tpmsset_main_v);
        } else {
            setContentView(R.layout.activity_tpmsset_main);
        }
        this.TpmsSetManage = new RelativeLayoutManager(this, R.id.activity_tpmsset_mainid);
        this.TpmssetOptions = getResources().getStringArray(R.array.tpms_general_disp);
        if (MainSet.GetScreenType() == 5 || MainSet.GetScreenType() == 6 || MainSet.GetScreenType() == 3) {
            this.nScrW = MainUI.mScrW;
            this.nSrcH = MainUI.mScrH;
            if (MainSet.GetScreenType() == 3) {
                this.nScrW = 768;
                this.nSrcH = 381;
            } else if (MainSet.GetScreenType() == 6) {
                this.nScrW = 800;
                this.nSrcH = 480;
            }
            this.TpmsSetManage.GetLayout().setBackgroundResource(R.drawable.tpms_parameter_bg1);
            this.TpmsSetManage.AddImageEx(this.nScrW - 258, 10, 184, 60, R.drawable.tpms_parameter_temp_box);
            this.TpmsSetManage.AddImageEx(this.nScrW - 258, 90, 184, 60, R.drawable.tpms_parameter_temp_box);
            this.TpmsSetManage.AddImageEx(this.nScrW - 578, 180, CanCameraUI.BTN_HMS7_ESC, 60, R.drawable.tpms_parameter_jia_box);
            this.TpmsSetManage.AddImageEx(this.nScrW - 578, 270, CanCameraUI.BTN_HMS7_ESC, 60, R.drawable.tpms_parameter_jia_box);
            this.TpmsSetManage.AddImageEx(this.nScrW - 578, 360, CanCameraUI.BTN_HMS7_ESC, 60, R.drawable.tpms_parameter_jia_box);
        } else {
            ViewGroup.LayoutParams params = this.TpmsSetManage.GetLayout().getLayoutParams();
            params.height = CanCameraUI.BTN_NISSAN_XTRAL_RVS_ASSIST6;
            this.TpmsSetManage.GetLayout().setLayoutParams(params);
            this.TpmsSetManage.AddImageEx(766, 49, 184, 60, R.drawable.tpms_parameter_temp_box);
            this.TpmsSetManage.AddImageEx(766, 135, 184, 60, R.drawable.tpms_parameter_temp_box);
        }
        for (int i = 0; i < 5; i++) {
            if (MainSet.GetScreenType() == 5) {
                this.ItemDisp[i] = this.TpmsSetManage.AddText(20, (i * 90) + 10, Can.CAN_VOLKS_XP, 60);
            } else if (MainSet.GetScreenType() == 3) {
                this.ItemDisp[i] = this.TpmsSetManage.AddText(20, (i * 90) + 10, Can.CAN_VOLKS_XP, 60);
            } else if (MainSet.GetScreenType() == 6) {
                this.ItemDisp[i] = this.TpmsSetManage.AddText(20, (i * 90) + 10, Can.CAN_VOLKS_XP, 60);
            } else if (i < 2) {
                this.ItemDisp[i] = this.TpmsSetManage.AddText(20, (i * 92) + 49, Can.CAN_VOLKS_XP, 60);
            } else {
                this.ItemDisp[i] = this.TpmsSetManage.AddText(20, (i * 92) + 69, Can.CAN_VOLKS_XP, 60);
            }
            this.ItemDisp[i].setText(this.TpmssetOptions[i]);
            this.ItemDisp[i].setTextSize(0, 36.0f);
            this.ItemDisp[i].setTextColor(-1);
        }
        for (int j = 0; j < 3; j++) {
            if (MainSet.GetScreenType() == 5) {
                this.TaiwaDw[j] = this.TpmsSetManage.AddButton(((this.nScrW - 258) - 90) + (j * 91), 90, 90, 60);
            } else if (MainSet.GetScreenType() == 3) {
                this.TaiwaDw[j] = this.TpmsSetManage.AddButton(((this.nScrW - 258) - 90) + (j * 91), 90, 90, 60);
            } else if (MainSet.GetScreenType() == 6) {
                this.TaiwaDw[j] = this.TpmsSetManage.AddButton(((this.nScrW - 258) - 90) + (j * 91), 90, 90, 60);
            } else {
                this.TaiwaDw[j] = this.TpmsSetManage.AddButton((j * 91) + 677, 135, 90, 60);
            }
            this.TaiwaDw[j].setDrawable(R.drawable.tpms_parameter_temp_left_up, R.drawable.tpms_parameter_temp_left_dn);
            this.TaiwaDw[j].setTextSize(0, 24.0f);
            this.TaiwaDw[j].setTextColor(-1);
            this.TaiwaDw[j].setTag(Integer.valueOf(j));
            this.TaiwaDw[j].setOnClickListener(new View.OnClickListener() {
                public void onClick(View arg0) {
                    switch (((Integer) arg0.getTag()).intValue()) {
                        case 0:
                            TPMSSetMainActivity.this.mStTpms.tpmsSave.nPskDW = 0;
                            break;
                        case 1:
                            TPMSSetMainActivity.this.mStTpms.tpmsSave.nPskDW = 1;
                            break;
                        case 2:
                            TPMSSetMainActivity.this.mStTpms.tpmsSave.nPskDW = 2;
                            break;
                    }
                    TPMSSetMainActivity.this.UpdateTaiyaDw();
                    TPMSSetMainActivity.this.UpdateShowInfor();
                }
            });
        }
        for (int j2 = 0; j2 < 2; j2++) {
            if (MainSet.GetScreenType() == 5) {
                this.TempDw[j2] = this.TpmsSetManage.AddButton((this.nScrW - 258) + (j2 * 91), 10, 90, 60);
            } else if (MainSet.GetScreenType() == 3) {
                this.TempDw[j2] = this.TpmsSetManage.AddButton((this.nScrW - 258) + (j2 * 91), 10, 90, 60);
            } else if (MainSet.GetScreenType() == 6) {
                this.TempDw[j2] = this.TpmsSetManage.AddButton((this.nScrW - 258) + (j2 * 91), 10, 90, 60);
            } else {
                this.TempDw[j2] = this.TpmsSetManage.AddButton((j2 * 91) + 767, 49, 90, 60);
            }
            this.TempDw[j2].setDrawable(R.drawable.tpms_parameter_temp_left_up, R.drawable.tpms_parameter_temp_left_dn);
            this.TempDw[j2].setTextSize(0, 24.0f);
            this.TempDw[j2].setTextColor(-1);
            this.TempDw[j2].setTag(Integer.valueOf(j2));
            this.TempDw[j2].setOnClickListener(new View.OnClickListener() {
                public void onClick(View arg0) {
                    switch (((Integer) arg0.getTag()).intValue()) {
                        case 0:
                            TPMSSetMainActivity.this.mStTpms.tpmsSave.nTempDW = 0;
                            break;
                        case 1:
                            TPMSSetMainActivity.this.mStTpms.tpmsSave.nTempDW = 1;
                            break;
                    }
                    TPMSSetMainActivity.this.UpdateTempDw();
                    TPMSSetMainActivity.this.UpdateShowInfor();
                }
            });
        }
        this.TempDw[0].setText(R.string.Laucher_out_temp_dw1);
        this.TempDw[1].setText(R.string.Laucher_out_temp_dw2);
        this.TaiwaDw[0].setText(R.string.tpms_general_bar);
        this.TaiwaDw[1].setText(R.string.tpms_general_psi);
        this.TaiwaDw[2].setText(R.string.tpms_general_kpa);
        UpdateTempDw();
        UpdateTaiyaDw();
        for (int i2 = 0; i2 < 3; i2++) {
            if (MainSet.GetScreenType() == 5) {
                this.TpmsDec[i2] = this.TpmsSetManage.AddButton(this.nScrW - 578, (i2 * 90) + 190, 92, 60);
            } else if (MainSet.GetScreenType() == 3) {
                this.TpmsDec[i2] = this.TpmsSetManage.AddButton(this.nScrW - 578, (i2 * 90) + 190, 92, 60);
            } else if (MainSet.GetScreenType() == 6) {
                this.TpmsDec[i2] = this.TpmsSetManage.AddButton(this.nScrW - 578, (i2 * 90) + 190, 92, 60);
            } else {
                this.TpmsDec[i2] = this.TpmsSetManage.AddButton(447, (i2 * 87) + 263, 92, 60);
            }
            this.TpmsDec[i2].setTag(Integer.valueOf(i2));
            this.TpmsDec[i2].setDrawable(R.drawable.tpms_parameter_jian_up, R.drawable.tpms_parameter_jian_dn);
            this.TpmsDec[i2].setOnClickListener(new View.OnClickListener() {
                public void onClick(View arg0) {
                    switch (((Integer) arg0.getTag()).intValue()) {
                        case 0:
                            if (TPMSSetMainActivity.this.mStTpms.tpmsSave.nTempHigh > StTpms.GetInstance().GetSettempMin()) {
                                StTpms.TPMS_SAVE tpms_save = TPMSSetMainActivity.this.mStTpms.tpmsSave;
                                tpms_save.nTempHigh--;
                                StTpms.GetInstance().SetWarnParat();
                                break;
                            }
                            break;
                        case 1:
                            if (TPMSSetMainActivity.this.mStTpms.tpmsSave.nPskHigh > StTpms.GetInstance().GetSetpskHighMin()) {
                                if (StTpms.GetInstance().nTpmsType == 1) {
                                    StTpms.TPMS_SAVE tpms_save2 = TPMSSetMainActivity.this.mStTpms.tpmsSave;
                                    tpms_save2.nPskHigh--;
                                } else {
                                    StTpms.TPMS_SAVE tpms_save3 = TPMSSetMainActivity.this.mStTpms.tpmsSave;
                                    tpms_save3.nPskHigh -= 10;
                                }
                                StTpms.GetInstance().SetWarnParat();
                                break;
                            }
                            break;
                        case 2:
                            if (TPMSSetMainActivity.this.mStTpms.tpmsSave.nPskLow > StTpms.GetInstance().GetSetpskLowMin()) {
                                if (StTpms.GetInstance().nTpmsType == 1) {
                                    StTpms.TPMS_SAVE tpms_save4 = TPMSSetMainActivity.this.mStTpms.tpmsSave;
                                    tpms_save4.nPskLow--;
                                } else {
                                    StTpms.TPMS_SAVE tpms_save5 = TPMSSetMainActivity.this.mStTpms.tpmsSave;
                                    tpms_save5.nPskLow -= 10;
                                }
                                StTpms.GetInstance().SetWarnParat();
                                break;
                            }
                            break;
                    }
                    TPMSSetMainActivity.this.UpdateShowInfor();
                }
            });
            if (MainSet.GetScreenType() == 5) {
                this.TpmsAdd[i2] = this.TpmsSetManage.AddButton(this.nScrW - 166, (i2 * 90) + 190, 92, 60);
            } else if (MainSet.GetScreenType() == 3) {
                this.TpmsAdd[i2] = this.TpmsSetManage.AddButton(this.nScrW - 166, (i2 * 90) + 190, 92, 60);
            } else if (MainSet.GetScreenType() == 6) {
                this.TpmsAdd[i2] = this.TpmsSetManage.AddButton(this.nScrW - 166, (i2 * 90) + 190, 92, 60);
            } else {
                this.TpmsAdd[i2] = this.TpmsSetManage.AddButton(859, (i2 * 87) + 263, 92, 60);
            }
            this.TpmsAdd[i2].setDrawable(R.drawable.tpms_parameter_jia_up, R.drawable.tpms_parameter_jia_dn);
            this.TpmsAdd[i2].setTag(Integer.valueOf(i2));
            this.TpmsAdd[i2].setOnClickListener(new View.OnClickListener() {
                public void onClick(View arg0) {
                    switch (((Integer) arg0.getTag()).intValue()) {
                        case 0:
                            if (TPMSSetMainActivity.this.mStTpms.tpmsSave.nTempHigh < StTpms.GetInstance().GetSettempMAX()) {
                                TPMSSetMainActivity.this.mStTpms.tpmsSave.nTempHigh++;
                                StTpms.GetInstance().SetWarnParat();
                                break;
                            }
                            break;
                        case 1:
                            if (TPMSSetMainActivity.this.mStTpms.tpmsSave.nPskHigh < StTpms.GetInstance().GetSetpskHighMAX()) {
                                if (StTpms.GetInstance().nTpmsType == 1) {
                                    TPMSSetMainActivity.this.mStTpms.tpmsSave.nPskHigh++;
                                } else {
                                    TPMSSetMainActivity.this.mStTpms.tpmsSave.nPskHigh += 10;
                                }
                                StTpms.GetInstance().SetWarnParat();
                                break;
                            }
                            break;
                        case 2:
                            if (TPMSSetMainActivity.this.mStTpms.tpmsSave.nPskLow < StTpms.GetInstance().GetSetpskLowMAX()) {
                                if (StTpms.GetInstance().nTpmsType == 1) {
                                    TPMSSetMainActivity.this.mStTpms.tpmsSave.nPskLow++;
                                } else {
                                    TPMSSetMainActivity.this.mStTpms.tpmsSave.nPskLow += 10;
                                }
                                StTpms.GetInstance().SetWarnParat();
                                break;
                            }
                            break;
                    }
                    TPMSSetMainActivity.this.UpdateShowInfor();
                }
            });
            if (MainSet.GetScreenType() == 5) {
                this.TpmsShowinfo[i2] = this.TpmsSetManage.AddText(this.nScrW - 372, (i2 * 90) + 190, 130, 60);
            } else if (MainSet.GetScreenType() == 3) {
                this.TpmsShowinfo[i2] = this.TpmsSetManage.AddText(this.nScrW - 372, (i2 * 90) + 190, 130, 60);
            } else if (MainSet.GetScreenType() == 6) {
                this.TpmsShowinfo[i2] = this.TpmsSetManage.AddText(this.nScrW - 372, (i2 * 90) + 190, 130, 60);
            } else {
                this.TpmsShowinfo[i2] = this.TpmsSetManage.AddText(653, (i2 * 87) + 273, 130, 60);
            }
            this.TpmsShowinfo[i2].setTextSize(0, 30.0f);
            this.TpmsShowinfo[i2].setTextColor(-1);
        }
        UpdateShowInfor();
    }

    /* access modifiers changed from: package-private */
    public void UpdateShowInfor() {
        if (this.mStTpms.tpmsSave.nTempDW == 1) {
            this.TpmsShowinfo[0].setText(String.format("%d", new Object[]{Integer.valueOf((int) (32.0d + (((double) this.mStTpms.tpmsSave.nTempHigh) * 1.8d)))}));
        } else {
            this.TpmsShowinfo[0].setText(String.format("%d", new Object[]{Integer.valueOf(this.mStTpms.tpmsSave.nTempHigh)}));
        }
        if (this.mStTpms.tpmsSave.nPskDW == 0) {
            if (StTpms.GetInstance().nTpmsType == 1) {
                this.TpmsShowinfo[1].setText(String.format("%.2f", new Object[]{Double.valueOf((((double) this.mStTpms.tpmsSave.nPskHigh) / 0.14504d) / 100.0d)}));
                this.TpmsShowinfo[2].setText(String.format("%.2f", new Object[]{Double.valueOf((((double) this.mStTpms.tpmsSave.nPskLow) / 0.14504d) / 100.0d)}));
                return;
            }
            this.TpmsShowinfo[1].setText(String.format("%.2f", new Object[]{Double.valueOf(((double) this.mStTpms.tpmsSave.nPskHigh) / 100.0d)}));
            this.TpmsShowinfo[2].setText(String.format("%.2f", new Object[]{Double.valueOf(((double) this.mStTpms.tpmsSave.nPskLow) / 100.0d)}));
        } else if (this.mStTpms.tpmsSave.nPskDW == 2) {
            if (StTpms.GetInstance().nTpmsType == 1) {
                this.TpmsShowinfo[1].setText(String.format("%d", new Object[]{Integer.valueOf((int) (((double) this.mStTpms.tpmsSave.nPskHigh) / 0.14504d))}));
                this.TpmsShowinfo[2].setText(String.format("%d", new Object[]{Integer.valueOf((int) (((double) this.mStTpms.tpmsSave.nPskLow) / 0.14504d))}));
                return;
            }
            this.TpmsShowinfo[1].setText(String.format("%d", new Object[]{Integer.valueOf(this.mStTpms.tpmsSave.nPskHigh)}));
            this.TpmsShowinfo[2].setText(String.format("%d", new Object[]{Integer.valueOf(this.mStTpms.tpmsSave.nPskLow)}));
        } else if (StTpms.GetInstance().nTpmsType == 1) {
            this.TpmsShowinfo[1].setText(String.format("%d", new Object[]{Integer.valueOf(this.mStTpms.tpmsSave.nPskHigh)}));
            this.TpmsShowinfo[2].setText(String.format("%d", new Object[]{Integer.valueOf(this.mStTpms.tpmsSave.nPskLow)}));
        } else {
            this.TpmsShowinfo[1].setText(String.format("%d", new Object[]{Integer.valueOf((int) (((double) this.mStTpms.tpmsSave.nPskHigh) * 0.14505d))}));
            this.TpmsShowinfo[2].setText(String.format("%d", new Object[]{Integer.valueOf((int) (((double) this.mStTpms.tpmsSave.nPskLow) * 0.14505d))}));
        }
    }

    /* access modifiers changed from: package-private */
    public void UpdateTaiyaDw() {
        this.TaiwaDw[0].SetSel(0);
        this.TaiwaDw[1].SetSel(0);
        this.TaiwaDw[2].SetSel(0);
        if (this.mStTpms.tpmsSave.nPskDW <= 2 && this.mStTpms.tpmsSave.nPskDW >= 0) {
            this.TaiwaDw[this.mStTpms.tpmsSave.nPskDW].SetSel(1);
        }
    }

    /* access modifiers changed from: package-private */
    public void UpdateTempDw() {
        if (this.mStTpms.tpmsSave.nTempDW == 1) {
            this.TempDw[0].SetSel(0);
            this.TempDw[1].SetSel(1);
            return;
        }
        this.TempDw[0].SetSel(1);
        this.TempDw[1].SetSel(0);
    }
}
