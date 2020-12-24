package com.ts.can.jac.wc;

import android.os.Bundle;
import android.support.v4.internal.view.SupportMenu;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanBaseActivity;
import com.ts.can.CanCameraUI;
import com.ts.canview.CanItemProgressList;
import com.ts.other.CustomImgView;
import com.ts.other.CustomTextView;
import com.ts.other.RelativeLayoutManager;

public class CanJACRefineWcTpmsActivity extends CanBaseActivity implements UserCallBack, View.OnClickListener, CanItemProgressList.onPosChange {
    public static final int ITEM_VOL = 0;
    public static final String TAG = "CanJACRefineWcTpmsActivity";
    private static final String[] mWarnArrays = {"胎压正常", "传感器丢失", "轮胎漏气", "气压过低", "气压过高", "传感器电量过低", "传感器损坏", "胎压微高或微低", "温度过高"};
    protected CanDataInfo.JAC_PMS_DATA mData = new CanDataInfo.JAC_PMS_DATA();
    protected CustomImgView[] mIvTyres = new CustomImgView[4];
    protected RelativeLayoutManager mManager;
    protected CustomTextView[] mTvPress = new CustomTextView[4];
    protected CustomTextView mTvStatus;
    protected CustomTextView[] mTvTemp = new CustomTextView[4];
    protected CustomTextView[] mTvWarn = new CustomTextView[4];
    protected CanDataInfo.JAC_TPMS_WARN_WC mWarn = new CanDataInfo.JAC_TPMS_WARN_WC();

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_can_comm_relative);
        ((RelativeLayout) findViewById(R.id.can_comm_layout)).setBackgroundResource(R.drawable.can_rf_tpms_bg);
        this.mManager = new RelativeLayoutManager(this, R.id.can_comm_layout);
        this.mTvStatus = this.mManager.AddCusText(375, 45, 281, 50);
        this.mTvStatus.SetPixelSize(35);
        this.mTvStatus.setGravity(17);
        this.mManager.AddImage(375, 55, R.drawable.can_rf_tpms_line);
        for (int i = 0; i < 4; i++) {
            this.mIvTyres[i] = this.mManager.AddImage(((i % 2) * 132) + 431, ((i / 2) * 186) + 116);
            this.mIvTyres[i].setStateDrawable(R.drawable.can_rf_tpms_up, R.drawable.can_rf_tpms_dn);
            this.mTvPress[i] = this.mManager.AddCusText(((i % 2) * CanCameraUI.BTN_TRUMPCHI_GS7_MODE4) + 91, ((i / 2) * Can.CAN_CC_HF_DJ) + 49, 281, 50);
            this.mTvPress[i].SetPixelSize(35);
            this.mTvPress[i].setGravity(17);
            this.mTvTemp[i] = this.mManager.AddCusText(((i % 2) * CanCameraUI.BTN_TRUMPCHI_GS7_MODE4) + 91, ((i / 2) * Can.CAN_CC_HF_DJ) + 95, 281, 50);
            this.mTvTemp[i].SetPixelSize(35);
            this.mTvTemp[i].setGravity(17);
            this.mTvWarn[i] = this.mManager.AddCusText(((i % 2) * CanCameraUI.BTN_TRUMPCHI_GS7_MODE4) + 91, ((i / 2) * Can.CAN_CC_HF_DJ) + 144, 281, 50);
            this.mTvWarn[i].SetPixelSize(32);
            this.mTvWarn[i].setGravity(17);
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
        Log.d(TAG, "-----onResume-----");
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
        Log.d(TAG, "-----onPause-----");
    }

    private void ResetData(boolean check) {
        CanJni.JACRefineGetTpms(this.mData);
        if (i2b(this.mData.UpdateOnce) && (!check || i2b(this.mData.Update))) {
            this.mData.Update = 0;
            SetVal(0, this.mData.FLPress, this.mData.FLTemp);
            SetVal(1, this.mData.FRPress, this.mData.FRTemp);
            SetVal(2, this.mData.RLPress, this.mData.RLTemp);
            SetVal(3, this.mData.RRPress, this.mData.RRTemp);
        }
        CanJni.JacRefineWcGetTpmsWarn(this.mWarn);
        int Warm = 0;
        if (!i2b(this.mWarn.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mWarn.Update)) {
            this.mWarn.Update = 0;
            if (this.mWarn.FLWarm[0] > 0 || this.mWarn.FLWarm[1] > 0 || this.mWarn.FLWarm[2] > 0 || this.mWarn.FLWarm[3] > 0 || this.mWarn.FLWarm[4] > 0 || this.mWarn.FLWarm[5] > 0 || this.mWarn.FLWarm[6] > 0 || this.mWarn.FLWarm[7] > 0) {
                this.mIvTyres[0].setSelected(true);
                if (this.mWarn.FLWarm[0] > 0) {
                    this.mTvWarn[0].setText(mWarnArrays[1]);
                }
                if (this.mWarn.FLWarm[1] > 0) {
                    this.mTvWarn[0].setText(mWarnArrays[2]);
                }
                if (this.mWarn.FLWarm[2] > 0) {
                    this.mTvWarn[0].setText(mWarnArrays[3]);
                }
                if (this.mWarn.FLWarm[3] > 0) {
                    this.mTvWarn[0].setText(mWarnArrays[4]);
                }
                if (this.mWarn.FLWarm[4] > 0) {
                    this.mTvWarn[0].setText(mWarnArrays[5]);
                }
                if (this.mWarn.FLWarm[5] > 0) {
                    this.mTvWarn[0].setText(mWarnArrays[6]);
                }
                if (this.mWarn.FLWarm[6] > 0) {
                    this.mTvWarn[0].setText(mWarnArrays[7]);
                }
                if (this.mWarn.FLWarm[7] > 0) {
                    this.mTvWarn[0].setText(mWarnArrays[8]);
                }
                this.mTvWarn[0].setTextColor(SupportMenu.CATEGORY_MASK);
                Warm = 1;
            } else {
                this.mIvTyres[0].setSelected(false);
                this.mTvWarn[0].setText(mWarnArrays[0]);
                this.mTvWarn[0].setTextColor(-1);
            }
            if (this.mWarn.FRWarm[0] > 0 || this.mWarn.FRWarm[1] > 0 || this.mWarn.FRWarm[2] > 0 || this.mWarn.FRWarm[3] > 0 || this.mWarn.FRWarm[4] > 0 || this.mWarn.FRWarm[5] > 0 || this.mWarn.FRWarm[6] > 0 || this.mWarn.FRWarm[7] > 0) {
                this.mIvTyres[1].setSelected(true);
                if (this.mWarn.FRWarm[0] > 0) {
                    this.mTvWarn[1].setText(mWarnArrays[1]);
                }
                if (this.mWarn.FRWarm[1] > 0) {
                    this.mTvWarn[1].setText(mWarnArrays[2]);
                }
                if (this.mWarn.FRWarm[2] > 0) {
                    this.mTvWarn[1].setText(mWarnArrays[3]);
                }
                if (this.mWarn.FRWarm[3] > 0) {
                    this.mTvWarn[1].setText(mWarnArrays[4]);
                }
                if (this.mWarn.FRWarm[4] > 0) {
                    this.mTvWarn[1].setText(mWarnArrays[5]);
                }
                if (this.mWarn.FRWarm[5] > 0) {
                    this.mTvWarn[1].setText(mWarnArrays[6]);
                }
                if (this.mWarn.FRWarm[6] > 0) {
                    this.mTvWarn[1].setText(mWarnArrays[7]);
                }
                if (this.mWarn.FRWarm[7] > 0) {
                    this.mTvWarn[1].setText(mWarnArrays[8]);
                }
                this.mTvWarn[1].setTextColor(SupportMenu.CATEGORY_MASK);
                Warm = 1;
            } else {
                this.mIvTyres[1].setSelected(false);
                this.mTvWarn[1].setText(mWarnArrays[0]);
                this.mTvWarn[1].setTextColor(-1);
            }
            if (this.mWarn.RLWarm[0] > 0 || this.mWarn.RLWarm[1] > 0 || this.mWarn.RLWarm[2] > 0 || this.mWarn.RLWarm[3] > 0 || this.mWarn.RLWarm[4] > 0 || this.mWarn.RLWarm[5] > 0 || this.mWarn.RLWarm[6] > 0 || this.mWarn.RLWarm[7] > 0) {
                this.mIvTyres[2].setSelected(true);
                if (this.mWarn.RLWarm[0] > 0) {
                    this.mTvWarn[2].setText(mWarnArrays[1]);
                }
                if (this.mWarn.RLWarm[1] > 0) {
                    this.mTvWarn[2].setText(mWarnArrays[2]);
                }
                if (this.mWarn.RLWarm[2] > 0) {
                    this.mTvWarn[2].setText(mWarnArrays[3]);
                }
                if (this.mWarn.RLWarm[3] > 0) {
                    this.mTvWarn[2].setText(mWarnArrays[4]);
                }
                if (this.mWarn.RLWarm[4] > 0) {
                    this.mTvWarn[2].setText(mWarnArrays[5]);
                }
                if (this.mWarn.RLWarm[5] > 0) {
                    this.mTvWarn[2].setText(mWarnArrays[6]);
                }
                if (this.mWarn.RLWarm[6] > 0) {
                    this.mTvWarn[2].setText(mWarnArrays[7]);
                }
                if (this.mWarn.RLWarm[7] > 0) {
                    this.mTvWarn[2].setText(mWarnArrays[8]);
                }
                this.mTvWarn[2].setTextColor(SupportMenu.CATEGORY_MASK);
                Warm = 1;
            } else {
                this.mIvTyres[2].setSelected(false);
                this.mTvWarn[2].setText(mWarnArrays[0]);
                this.mTvWarn[2].setTextColor(-1);
            }
            if (this.mWarn.RRWarm[0] > 0 || this.mWarn.RRWarm[1] > 0 || this.mWarn.RRWarm[2] > 0 || this.mWarn.RRWarm[3] > 0 || this.mWarn.RRWarm[4] > 0 || this.mWarn.RRWarm[5] > 0 || this.mWarn.RRWarm[6] > 0 || this.mWarn.RRWarm[7] > 0) {
                this.mIvTyres[3].setSelected(true);
                if (this.mWarn.RRWarm[0] > 0) {
                    this.mTvWarn[3].setText(mWarnArrays[1]);
                }
                if (this.mWarn.RRWarm[1] > 0) {
                    this.mTvWarn[3].setText(mWarnArrays[2]);
                }
                if (this.mWarn.RRWarm[2] > 0) {
                    this.mTvWarn[3].setText(mWarnArrays[3]);
                }
                if (this.mWarn.RRWarm[3] > 0) {
                    this.mTvWarn[3].setText(mWarnArrays[4]);
                }
                if (this.mWarn.RRWarm[4] > 0) {
                    this.mTvWarn[3].setText(mWarnArrays[5]);
                }
                if (this.mWarn.RRWarm[5] > 0) {
                    this.mTvWarn[3].setText(mWarnArrays[6]);
                }
                if (this.mWarn.RRWarm[6] > 0) {
                    this.mTvWarn[3].setText(mWarnArrays[7]);
                }
                if (this.mWarn.RRWarm[7] > 0) {
                    this.mTvWarn[3].setText(mWarnArrays[8]);
                }
                this.mTvWarn[3].setTextColor(SupportMenu.CATEGORY_MASK);
                Warm = 1;
            } else {
                this.mIvTyres[3].setSelected(false);
                this.mTvWarn[3].setText(mWarnArrays[0]);
                this.mTvWarn[3].setTextColor(-1);
            }
            if (this.mWarn.SysWarn[0] > 0) {
                this.mTvStatus.setText("学习模式");
                this.mTvStatus.setTextColor(-1);
            } else if (this.mWarn.SysWarn[1] > 0) {
                this.mTvStatus.setText("系统故障");
                this.mTvStatus.setTextColor(SupportMenu.CATEGORY_MASK);
            } else if (this.mWarn.SysWarn[2] > 0) {
                this.mTvStatus.setText("学习未完成");
                this.mTvStatus.setTextColor(SupportMenu.CATEGORY_MASK);
            } else if (Warm > 0) {
                this.mTvStatus.setText("系统异常");
                this.mTvStatus.setTextColor(SupportMenu.CATEGORY_MASK);
            } else {
                this.mTvStatus.setText("系统正常");
                this.mTvStatus.setTextColor(-1);
            }
        }
    }

    public void onClick(View v) {
        int intValue = ((Integer) v.getTag()).intValue();
    }

    public void UserAll() {
        ResetData(true);
    }

    public void onChanged(int id, int pos) {
    }

    public String GetPressStr(int press) {
        if (press >= 255) {
            return "-.- bar";
        }
        return String.format("%.1f bar", new Object[]{Double.valueOf(((double) press) * 0.1d)});
    }

    public String GetTempStr(int temp) {
        if (temp >= 255) {
            return "-- ℃";
        }
        return String.format("%d ℃", new Object[]{Integer.valueOf(temp)});
    }

    public void SetVal(int id, int press, int temp) {
        this.mTvPress[id].setText(GetPressStr(press));
        this.mTvTemp[id].setText(GetTempStr(temp));
    }
}
