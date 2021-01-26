package com.ts.main.tpms;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.lgb.canmodule.Can;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.StTpms;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanCameraUI;
import com.ts.factoryset.AtcDisplaySettingsUtils;
import com.ts.main.common.MainSet;
import com.ts.main.common.WinShow;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;
import com.txznet.sdk.TXZResourceManager;
import com.yyw.ts70xhw.KeyDef;

public class TPMSMainActivity extends Activity implements UserCallBack {
    private static final int TPMS_DN_COLOR = -65536;
    private static final int TPMS_UP_COLOR = -16711936;
    static int nNum = 0;
    TextView[] ShowError = new TextView[4];
    TextView[] TempWd = new TextView[4];
    ParamButton TpmsChange;
    TextView[] TpmsDw = new TextView[4];
    RelativeLayoutManager TpmsManage;
    ParamButton TpmsOnoff;
    private String[] TpmsOptionError;
    private String[] TpmsOptions2;
    ParamButton TpmsSetUp;
    ParamButton[] TpmsShowData = new ParamButton[4];
    ParamButton TpmsStudy;
    StTpms mStTpms = StTpms.GetInstance();
    int nflashflag = 1;

    /* access modifiers changed from: package-private */
    public void UpdateBtnOff() {
        if (this.mStTpms.tpmsSave.nOnOffFlag > 2 || this.mStTpms.tpmsSave.nOnOffFlag < 0) {
            this.TpmsOnoff.setText(TXZResourceManager.STYLE_DEFAULT);
        } else {
            this.TpmsOnoff.setText(this.TpmsOptions2[this.mStTpms.tpmsSave.nOnOffFlag]);
        }
    }

    /* access modifiers changed from: package-private */
    public void startTpmsStudy(int type) {
        Intent i = new Intent(this, TpmsStudyActivity.class);
        i.putExtra(TpmsStudyActivity.KEY_TYPE, type);
        startActivity(i);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tpmsmain);
        this.TpmsOptionError = getResources().getStringArray(R.array.tpms_general_error);
        this.TpmsOptions2 = getResources().getStringArray(R.array.tpms_general_mode);
        this.TpmsManage = new RelativeLayoutManager(this, R.id.activity_tpms_mainid);
        if (MainSet.GetScreenType() == 5) {
            this.TpmsManage.GetLayout().setBackgroundResource(R.drawable.tpms_bg1);
            this.TpmsOnoff = this.TpmsManage.AddButton(578, 14);
        } else if (MainSet.GetScreenType() == 3) {
            this.TpmsManage.GetLayout().setBackgroundResource(R.drawable.tpms_bg_v);
            this.TpmsOnoff = this.TpmsManage.AddButton(578, 14);
        } else if (MainSet.GetScreenType() == 6) {
            this.TpmsOnoff = this.TpmsManage.AddButton(578, 14);
        } else if (MainSet.GetScreenType() == 8) {
            ViewGroup.LayoutParams params = this.TpmsManage.GetLayout().getLayoutParams();
            params.height = CanCameraUI.BTN_NISSAN_XTRAL_RVS_ASSIST6;
            this.TpmsManage.GetLayout().setLayoutParams(params);
            this.TpmsOnoff = this.TpmsManage.AddButton(450, 14);
        } else if (MainSet.GetScreenType() == 10) {
            this.TpmsManage.GetLayout().setBackgroundResource(R.drawable.tpms_bg_1280);
            this.TpmsOnoff = this.TpmsManage.AddButton(CanCameraUI.BTN_TRUMPCHI_GS7_MODE3, 14);
        } else {
            this.TpmsOnoff = this.TpmsManage.AddButton(450, 14);
        }
        this.TpmsOnoff.setDrawable(R.drawable.tpms_off_up, R.drawable.tpms_off_dn);
        this.TpmsOnoff.setTextColor(-1);
        this.TpmsOnoff.setTextSize(0, 30.0f);
        UpdateBtnOff();
        this.TpmsOnoff.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                if (TPMSMainActivity.this.mStTpms.tpmsSave.nOnOffFlag == 0) {
                    TPMSMainActivity.this.mStTpms.tpmsSave.nOnOffFlag = 1;
                } else if (TPMSMainActivity.this.mStTpms.tpmsSave.nOnOffFlag == 1) {
                    TPMSMainActivity.this.mStTpms.tpmsSave.nOnOffFlag = 2;
                } else {
                    TPMSMainActivity.this.mStTpms.tpmsSave.nOnOffFlag = 0;
                }
                TPMSMainActivity.this.UpdateBtnOff();
            }
        });
        this.TpmsSetUp = this.TpmsManage.AddButton(20, 14, 126, 65);
        this.TpmsSetUp.setDrawable(R.drawable.tpms_parameter_setting_up, R.drawable.tpms_parameter_setting_dn);
        this.TpmsSetUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                WinShow.show("com.ts.MainUI", "com.ts.main.tpms.TPMSSetMainActivity");
            }
        });
        if (StTpms.GetInstance().nTpmsType == 1) {
            this.TpmsStudy = this.TpmsManage.AddButton(1000, 14, 126, 65);
            this.TpmsStudy.setDrawable(R.drawable.tpms_pdxx_up, R.drawable.tpms_pdxx_dn);
            this.TpmsStudy.setOnClickListener(new View.OnClickListener() {
                public void onClick(View arg0) {
                    TPMSMainActivity.this.startTpmsStudy(1);
                }
            });
            this.TpmsChange = this.TpmsManage.AddButton(CanCameraUI.BTN_ZOYTEX5_WC_ESC, 14, 126, 65);
            this.TpmsChange.setDrawable(R.drawable.tpms_ltjh_up, R.drawable.tpms_ltjh_dn);
            this.TpmsChange.setOnClickListener(new View.OnClickListener() {
                public void onClick(View arg0) {
                    TPMSMainActivity.this.startTpmsStudy(0);
                }
            });
        }
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                int k = (i * 2) + j;
                if (MainSet.GetScreenType() == 5) {
                    this.TpmsShowData[k] = this.TpmsManage.AddButton((j * 687) + 172, (i * 170) + 80, Can.CAN_NISSAN_XFY, 130);
                    this.TpmsShowData[k].setTextSize(0, 88.0f);
                } else if (MainSet.GetScreenType() == 3) {
                    this.TpmsShowData[k] = this.TpmsManage.AddButton((j * 559) + 20, (i * 170) + 80, 200, 130);
                    this.TpmsShowData[k].setTextSize(0, 60.0f);
                } else if (MainSet.GetScreenType() == 6) {
                    this.TpmsShowData[k] = this.TpmsManage.AddButton((j * 559) + 10, (i * 170) + 80, Can.CAN_NISSAN_XFY, 130);
                    this.TpmsShowData[k].setTextSize(0, 88.0f);
                } else if (MainSet.GetScreenType() == 10) {
                    this.TpmsShowData[k] = this.TpmsManage.AddButton((j * 912) + 30, (i * 300) + 90, Can.CAN_NISSAN_XFY, 130);
                    this.TpmsShowData[k].setTextSize(0, 88.0f);
                } else {
                    this.TpmsShowData[k] = this.TpmsManage.AddButton((j * 730) + 20, (i * Can.CAN_VOLKS_XP) + 90, Can.CAN_NISSAN_XFY, 130);
                    this.TpmsShowData[k].setTextSize(0, 88.0f);
                }
                this.TpmsShowData[k].setBackgroundColor(0);
                this.TpmsShowData[k].setTextColor(TPMS_UP_COLOR);
                this.TpmsShowData[k].setTag(Integer.valueOf(k));
                this.TpmsShowData[k].setOnClickListener(new View.OnClickListener() {
                    public void onClick(View arg0) {
                        if (TPMSMainActivity.this.mStTpms.TpmsDisp.info[((Integer) arg0.getTag()).intValue()].nState != 1) {
                        }
                    }
                });
                this.TpmsShowData[k].setOnLongClickListener(new View.OnLongClickListener() {
                    public boolean onLongClick(View arg0) {
                        int nTag = ((Integer) arg0.getTag()).intValue();
                        int nState = TPMSMainActivity.this.mStTpms.TpmsDisp.info[nTag].nState;
                        if (StTpms.GetInstance().nTpmsType == 0) {
                            if (nState == 1 || nState == 2) {
                                TPMSMainActivity.this.mStTpms.UnStudy(nTag);
                                Toast.makeText(TPMSMainActivity.this, TPMSMainActivity.this.getResources().getString(R.string.tpms_general_end), 0).show();
                            } else {
                                TPMSMainActivity.this.mStTpms.Study(nTag);
                                Toast.makeText(TPMSMainActivity.this, TPMSMainActivity.this.getResources().getString(R.string.tpms_general_satrt), 0).show();
                            }
                        }
                        return false;
                    }
                });
                if (MainSet.GetScreenType() == 5) {
                    this.TpmsDw[k] = this.TpmsManage.AddText((j * 639) + 293, (i * 160) + 190, 80, 50);
                    this.TempWd[k] = this.TpmsManage.AddText((j * 406) + 391, (i * 150) + 142, 100, 50);
                    this.ShowError[k] = this.TpmsManage.AddText((j * 667) + Can.CAN_MZD_LUOMU, (i * 170) + 70, Can.CAN_NISSAN_XFY, 40);
                } else if (MainSet.GetScreenType() == 3) {
                    this.TpmsDw[k] = this.TpmsManage.AddText((j * 500) + 109, (i * 170) + 163, 80, 50);
                    this.TempWd[k] = this.TpmsManage.AddText((j * KeyDef.RKEY_AVIN) + 189, (i * Can.CAN_BENC_ZMYT) + 152, 100, 50);
                    this.ShowError[k] = this.TpmsManage.AddText((j * 600) + 0, (i * 170) + 80, Can.CAN_NISSAN_XFY, 40);
                } else if (MainSet.GetScreenType() == 6) {
                    this.TpmsDw[k] = this.TpmsManage.AddText((j * 550) + 109, (i * 180) + 183, 80, 50);
                    this.TempWd[k] = this.TpmsManage.AddText((j * 276) + Can.CAN_VOLVO_XFY, (i * 160) + 142, 100, 50);
                    this.ShowError[k] = this.TpmsManage.AddText((j * 550) + 0, (i * 170) + 80, Can.CAN_NISSAN_XFY, 40);
                } else if (MainSet.GetScreenType() == 10) {
                    this.TpmsDw[k] = this.TpmsManage.AddText((j * 725) + Can.CAN_NISSAN_RICH6_WC, (i * 300) + 220, 80, 50);
                    this.TempWd[k] = this.TpmsManage.AddText((j * 454) + AtcDisplaySettingsUtils.SPECIFIC_Y_SMALL2, (i * 170) + Can.CAN_FLAT_WC, 100, 50);
                    this.ShowError[k] = this.TpmsManage.AddText((j * 912) + 62, (i * 300) + 80, Can.CAN_NISSAN_XFY, 40);
                } else {
                    this.TpmsDw[k] = this.TpmsManage.AddText((j * CanCameraUI.BTN_CAMERY_2018_MODE1) + 190, (i * Can.CAN_VOLKS_XP) + 220, 80, 50);
                    this.TempWd[k] = this.TpmsManage.AddText((j * 350) + 298, (i * 210) + 160, 100, 50);
                    this.ShowError[k] = this.TpmsManage.AddText((j * 730) + 50, (i * Can.CAN_VOLKS_XP) + 80, Can.CAN_NISSAN_XFY, 40);
                }
                this.TpmsDw[k].setTextColor(TPMS_UP_COLOR);
                this.TpmsDw[k].setBackgroundColor(0);
                this.TpmsDw[k].setTextSize(0, 42.0f);
                this.TempWd[k].setTextColor(TPMS_UP_COLOR);
                this.TempWd[k].setBackgroundColor(0);
                this.TempWd[k].setTextSize(0, 36.0f);
                this.ShowError[k].setTextColor(-65536);
                this.ShowError[k].setBackgroundColor(0);
                this.ShowError[k].setTextSize(0, 27.0f);
                UpdateTpms(k);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void ShowError() {
    }

    /* access modifiers changed from: package-private */
    public void UpdateTpms(int nNum2) {
        if (StTpms.GetInstance().nTpmsType == 1) {
            int nnMytemp = (int) this.mStTpms.TpmsDisp.info[nNum2].npa;
            int nMytemp = this.mStTpms.TpmsDisp.info[nNum2].nTemp;
            if (this.mStTpms.TpmsDisp.info[nNum2].nState == 1) {
                this.ShowError[nNum2].setText(this.TpmsOptionError[5]);
            } else if (this.mStTpms.TpmsDisp.info[nNum2].nState == 2) {
                this.ShowError[nNum2].setText(this.TpmsOptionError[6]);
            } else if (StTpms.GetInstance().nTpmsType == 1 && this.mStTpms.TpmsDisp.info[nNum2].nInvalid == 0) {
                this.TpmsDw[nNum2].setVisibility(4);
                this.TpmsShowData[nNum2].setVisibility(4);
                this.TempWd[nNum2].setVisibility(4);
                return;
            } else if (this.mStTpms.TpmsDisp.info[nNum2].nWarnS > 0) {
                this.ShowError[nNum2].setText(this.TpmsOptionError[2]);
                this.TpmsDw[nNum2].setVisibility(4);
                this.TpmsShowData[nNum2].setVisibility(4);
                this.TempWd[nNum2].setVisibility(4);
                return;
            } else if (this.mStTpms.TpmsDisp.info[nNum2].nWarnP > 0) {
                this.ShowError[nNum2].setText(this.TpmsOptionError[0]);
            } else if (this.mStTpms.TpmsDisp.info[nNum2].nWarnV > 0) {
                this.ShowError[nNum2].setText(this.TpmsOptionError[1]);
            } else if (((double) nnMytemp) < ((double) this.mStTpms.tpmsSave.nPskLow) / 0.14504d) {
                this.ShowError[nNum2].setText(this.TpmsOptionError[3]);
            } else if (((double) nnMytemp) > ((double) this.mStTpms.tpmsSave.nPskHigh) / 0.14504d) {
                this.ShowError[nNum2].setText(this.TpmsOptionError[4]);
            } else if (nMytemp > this.mStTpms.tpmsSave.nTempHigh) {
                this.ShowError[nNum2].setText(this.TpmsOptionError[7]);
            } else {
                this.ShowError[nNum2].setText(TXZResourceManager.STYLE_DEFAULT);
            }
            this.TpmsDw[nNum2].setVisibility(0);
            this.TpmsShowData[nNum2].setVisibility(0);
            this.TempWd[nNum2].setVisibility(0);
            if (((double) nnMytemp) > ((double) this.mStTpms.tpmsSave.nPskHigh) / 0.14504d || ((double) nnMytemp) < ((double) this.mStTpms.tpmsSave.nPskLow) / 0.14504d) {
                this.TpmsDw[nNum2].setTextColor(-65536);
                this.TpmsShowData[nNum2].setTextColor(-65536);
            } else {
                this.TpmsDw[nNum2].setTextColor(TPMS_UP_COLOR);
                this.TpmsShowData[nNum2].setTextColor(TPMS_UP_COLOR);
            }
            if (this.mStTpms.tpmsSave.nPskDW == 0) {
                this.TpmsDw[nNum2].setText(R.string.tpms_general_bar);
                this.TpmsShowData[nNum2].setText(String.format("%.1f", new Object[]{Double.valueOf(this.mStTpms.TpmsDisp.info[nNum2].npa / 100.0d)}));
            } else if (this.mStTpms.tpmsSave.nPskDW == 2) {
                this.TpmsDw[nNum2].setText(R.string.tpms_general_kpa);
                this.TpmsShowData[nNum2].setText(String.format("%3d", new Object[]{Integer.valueOf((int) this.mStTpms.TpmsDisp.info[nNum2].npa)}));
            } else {
                this.TpmsDw[nNum2].setText(R.string.tpms_general_psi);
                this.TpmsShowData[nNum2].setText(String.format("%.1f", new Object[]{Double.valueOf(this.mStTpms.TpmsDisp.info[nNum2].npa * 0.14504d)}));
            }
            if (nMytemp > this.mStTpms.tpmsSave.nTempHigh) {
                this.TempWd[nNum2].setTextColor(-65536);
            } else {
                this.TempWd[nNum2].setTextColor(TPMS_UP_COLOR);
            }
            if (this.mStTpms.tpmsSave.nTempDW == 1) {
                this.TempWd[nNum2].setText(String.format("%d℉", new Object[]{Integer.valueOf((int) (32.0d + (((double) this.mStTpms.TpmsDisp.info[nNum2].nTemp) * 1.8d)))}));
                return;
            }
            this.TempWd[nNum2].setText(String.format("%d℃", new Object[]{Integer.valueOf(this.mStTpms.TpmsDisp.info[nNum2].nTemp)}));
            return;
        }
        int nnMytemp2 = (int) this.mStTpms.TpmsDisp.info[nNum2].npa;
        int nMytemp2 = this.mStTpms.TpmsDisp.info[nNum2].nTemp;
        if (this.mStTpms.TpmsDisp.info[nNum2].nState == 1) {
            this.ShowError[nNum2].setText(this.TpmsOptionError[5]);
        } else if (this.mStTpms.TpmsDisp.info[nNum2].nState == 2) {
            this.ShowError[nNum2].setText(this.TpmsOptionError[6]);
        } else if (StTpms.GetInstance().nTpmsType == 1 && this.mStTpms.TpmsDisp.info[nNum2].nInvalid == 0) {
            this.TpmsDw[nNum2].setVisibility(4);
            this.TpmsShowData[nNum2].setVisibility(4);
            this.TempWd[nNum2].setVisibility(4);
            return;
        } else if (this.mStTpms.TpmsDisp.info[nNum2].nWarnS > 0) {
            this.ShowError[nNum2].setText(this.TpmsOptionError[2]);
            this.TpmsDw[nNum2].setVisibility(4);
            this.TpmsShowData[nNum2].setVisibility(4);
            this.TempWd[nNum2].setVisibility(4);
            return;
        } else if (this.mStTpms.TpmsDisp.info[nNum2].nWarnP > 0) {
            this.ShowError[nNum2].setText(this.TpmsOptionError[0]);
        } else if (this.mStTpms.TpmsDisp.info[nNum2].nWarnV > 0) {
            this.ShowError[nNum2].setText(this.TpmsOptionError[1]);
        } else if (nnMytemp2 < this.mStTpms.tpmsSave.nPskLow) {
            this.ShowError[nNum2].setText(this.TpmsOptionError[3]);
        } else if (nnMytemp2 > this.mStTpms.tpmsSave.nPskHigh) {
            this.ShowError[nNum2].setText(this.TpmsOptionError[4]);
        } else if (nMytemp2 > this.mStTpms.tpmsSave.nTempHigh) {
            this.ShowError[nNum2].setText(this.TpmsOptionError[7]);
        } else {
            this.ShowError[nNum2].setText(TXZResourceManager.STYLE_DEFAULT);
        }
        this.TpmsDw[nNum2].setVisibility(0);
        this.TpmsShowData[nNum2].setVisibility(0);
        this.TempWd[nNum2].setVisibility(0);
        if (nnMytemp2 > this.mStTpms.tpmsSave.nPskHigh || nnMytemp2 < this.mStTpms.tpmsSave.nPskLow) {
            this.TpmsDw[nNum2].setTextColor(-65536);
            this.TpmsShowData[nNum2].setTextColor(-65536);
        } else {
            this.TpmsDw[nNum2].setTextColor(TPMS_UP_COLOR);
            this.TpmsShowData[nNum2].setTextColor(TPMS_UP_COLOR);
        }
        if (this.mStTpms.tpmsSave.nPskDW == 0) {
            this.TpmsDw[nNum2].setText(R.string.tpms_general_bar);
            this.TpmsShowData[nNum2].setText(String.format("%.1f", new Object[]{Double.valueOf(this.mStTpms.TpmsDisp.info[nNum2].npa / 100.0d)}));
        } else if (this.mStTpms.tpmsSave.nPskDW == 2) {
            this.TpmsDw[nNum2].setText(R.string.tpms_general_kpa);
            this.TpmsShowData[nNum2].setText(String.format("%3d", new Object[]{Integer.valueOf((int) this.mStTpms.TpmsDisp.info[nNum2].npa)}));
        } else {
            this.TpmsDw[nNum2].setText(R.string.tpms_general_psi);
            this.TpmsShowData[nNum2].setText(String.format("%.1f", new Object[]{Double.valueOf(this.mStTpms.TpmsDisp.info[nNum2].npa * 0.14504d)}));
        }
        if (nMytemp2 > this.mStTpms.tpmsSave.nTempHigh) {
            this.TempWd[nNum2].setTextColor(-65536);
        } else {
            this.TempWd[nNum2].setTextColor(TPMS_UP_COLOR);
        }
        if (this.mStTpms.tpmsSave.nTempDW == 1) {
            this.TempWd[nNum2].setText(String.format("%d℉", new Object[]{Integer.valueOf((int) (32.0d + (((double) this.mStTpms.TpmsDisp.info[nNum2].nTemp) * 1.8d)))}));
            return;
        }
        this.TempWd[nNum2].setText(String.format("%d℃", new Object[]{Integer.valueOf(this.mStTpms.TpmsDisp.info[nNum2].nTemp)}));
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        MainTask.GetInstance().SetUserCallBack(this);
        if (this.mStTpms.tpmsSave.nOnOffFlag == 0) {
            this.TpmsOnoff.SetSel(0);
        } else {
            this.TpmsOnoff.SetSel(1);
        }
        UpdateInfo();
        MainSet.GetInstance().TwShowTitle(getResources().getString(R.string.title_activity_tpmsmain));
        StTpms.GetInstance().GetInfomation();
        super.onResume();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        MainSet.GetInstance().TwShowTitle(TXZResourceManager.STYLE_DEFAULT);
        super.onPause();
    }

    /* access modifiers changed from: package-private */
    public void UpdateInfo() {
        for (int i = 0; i < 4; i++) {
            UpdateTpms(i);
        }
    }

    public void UserAll() {
        int i = nNum;
        nNum = i + 1;
        if (i % 30 == 0) {
            UpdateInfo();
        }
    }
}
