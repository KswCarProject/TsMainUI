package com.ts.can.jiangling;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;

public class CanJlBmsActivity extends CanJlBaseActivity implements UserCallBack {
    public static final String TAG = "CanJlBmsActivity";
    protected String[] DcdtdygcArr = {"未过充", "过充"};
    protected String[] DcdtdygfArr = {"未过放", "过放"};
    protected String[] DcfdglArr = {"未过流", "过流"};
    protected String[] Qddjzt = {"默认", "电源消耗模式", "电源产生模式", "关闭", "已准备好"};
    protected String[] SocGdzsArr = {"正常", "过低"};
    protected CanDataInfo.JL_BAT_DATA1 mData1 = new CanDataInfo.JL_BAT_DATA1();
    protected CanDataInfo.JL_BAT_DATA2 mData2 = new CanDataInfo.JL_BAT_DATA2();
    protected CanDataInfo.JL_BAT_BMS5 mData5 = new CanDataInfo.JL_BAT_BMS5();
    protected CanDataInfo.JL_CAR_FAULT_DATA mFault = new CanDataInfo.JL_CAR_FAULT_DATA();
    protected CanJianglingItem mItemBmsYxcdjscdlMax;
    protected CanJianglingItem mItemBmsYxcdjscdyMax;
    protected CanJianglingItem mItemCdjcddlqqz;
    protected CanJianglingItem mItemCdjcddyqqz;
    protected CanJianglingItem mItemDcdtdygc;
    protected CanJianglingItem mItemDcdtdygf;
    protected CanJianglingItem mItemDcfdgl;
    protected CanJianglingItem mItemDjkzqwd;
    protected CanJianglingItem mItemDjwd;
    protected CanJianglingItem mItemDjzs;
    protected CanJianglingItem mItemGydczcqcdgl;
    protected CanJianglingItem mItemGydczcqfdgl;
    protected CanJianglingItem mItemGydczdqcdgl;
    protected CanJianglingItem mItemGydczdqfdgl;
    protected CanJianglingItem mItemMxdl;
    protected CanJianglingItem mItemMxdy;
    protected CanJianglingItem mItemNbqwd;
    protected CanJianglingItem mItemQdjzzt;
    protected CanJianglingItem mItemRunMode;
    protected CanJianglingItem mItemRunSta;
    protected CanJianglingItem mItemSocgdzs;
    protected CanDataInfo.JL_CAR_DATA mStaData = new CanDataInfo.JL_CAR_DATA();

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mBtnStatus.setDrawable(R.drawable.can_jl_xx_icon_up, R.drawable.can_jl_xx_icon_dn);
        this.mBtnStatus.setText(R.string.can_machine_status);
        this.mItemRunSta = AddStaLine(getString(R.string.can_running_status));
        this.mItemMxdy = AddStaLine(getString(R.string.can_dfqc_control_v));
        this.mItemMxdl = AddStaLine(getString(R.string.can_dfqc_control_a));
        this.mItemDjwd = AddStaLine(getString(R.string.can_dfqc_motor_c));
        if (CanJni.GetCanFsTp() == 79 && CanJni.GetSubType() == 1) {
            this.mItemDjkzqwd = AddStaLine(getString(R.string.can_djkzqwd));
            this.mItemNbqwd = AddStaLine(getString(R.string.can_nbqwd));
            this.mItemQdjzzt = AddStaLine(getString(R.string.can_qddjzt));
            this.mItemBmsYxcdjscdyMax = AddStaLine(getString(R.string.can_bmsyxcdjscdyzdz));
            this.mItemBmsYxcdjscdlMax = AddStaLine(getString(R.string.can_bmsyxcdjscdlzdz));
            this.mItemGydczcqcdgl = AddStaLine(getString(R.string.can_gydczcqcdgl));
            this.mItemGydczdqcdgl = AddStaLine(getString(R.string.can_gydczdqcdgl));
            this.mItemCdjcddyqqz = AddStaLine(getString(R.string.can_cdjcddyqqz));
            this.mItemCdjcddlqqz = AddStaLine(getString(R.string.can_cdjcddlqqz));
            this.mItemGydczcqfdgl = AddStaLine(getString(R.string.can_gydczcqfdgl));
            this.mItemGydczdqfdgl = AddStaLine(getString(R.string.can_gydczdqfdgl));
            this.mItemDcdtdygc = AddStaLine(getString(R.string.can_dcdtdygc));
            this.mItemDcdtdygf = AddStaLine(getString(R.string.can_dcdtdygf));
            this.mItemDcfdgl = AddStaLine(getString(R.string.can_dcfdgl));
            this.mItemSocgdzs = AddStaLine(getString(R.string.can_socgdzs));
        }
        this.mBtnStatus.setOnClickListener(this);
        this.mBtnWarn.setOnClickListener(this);
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
        CanJni.JLGetCarMsg(this.mStaData);
        if (i2b(this.mStaData.UpdateOnce) && (!check || i2b(this.mStaData.Update))) {
            this.mStaData.Update = 0;
            switch (this.mStaData.fgReady) {
                case 0:
                    this.mItemRunSta.SetVal("--");
                    break;
                case 1:
                    this.mItemRunSta.SetVal(getString(R.string.can_system_ready));
                    break;
            }
            CanJni.JLGetWarn(this.mWarnData);
            UpdateWarnNum(this.mWarnData.MachineWarn);
            RemoveAllWarn();
            AddWarn(this.mStaData.TooHot, getString(R.string.can_machine_hot));
            AddWarn(this.mStaData.fgDldzgz, getString(R.string.can_battery_error));
            AddWarn(this.mStaData.fgDlgz, getString(R.string.can_a_error));
            AddWarn(this.mStaData.fgXtgz, getString(R.string.can_system_error));
            AddWarn(this.mStaData.Dldcqd, getString(R.string.can_battery_disconnect_error));
            this.mItemMxdy.SetVal(String.format("%.1f V", new Object[]{Double.valueOf(((double) this.mStaData.DY) * 0.1d)}));
            this.mItemMxdl.SetVal(String.format("%.1f A", new Object[]{Double.valueOf(3200.0d - (((double) this.mStaData.Dl) * 0.1d))}));
            if (CanJni.GetCanFsTp() == 28 || (CanJni.GetCanFsTp() == 79 && CanJni.GetSubType() == 0)) {
                this.mItemDjwd.SetVal(String.format("%d  ℃", new Object[]{Integer.valueOf(this.mStaData.DjTmp - 40)}));
            }
        }
        if (CanJni.GetCanFsTp() == 79 && CanJni.GetSubType() == 1) {
            CanJni.JLGetCarBms5(this.mData5);
            if (!i2b(this.mData5.UpdateOnce)) {
                return;
            }
            if (!check || i2b(this.mData5.Update)) {
                this.mData5.Update = 0;
                this.mItemDjkzqwd.SetVal(GetTempStr(this.mData5.Djkzqwd));
                this.mItemDjwd.SetVal(GetTempStr(this.mData5.Djwd));
                this.mItemNbqwd.SetVal(GetTempStr(this.mData5.Nbqwd));
                if (this.mData5.Qddjzt <= 4) {
                    this.mItemQdjzzt.SetVal(this.Qddjzt[this.mData5.Qddjzt]);
                } else {
                    this.mItemQdjzzt.SetVal("--");
                }
                if (this.mData5.YxcdjscdyMax == 65535) {
                    this.mItemBmsYxcdjscdyMax.SetVal("--");
                } else {
                    this.mItemBmsYxcdjscdyMax.SetVal(String.format("%.1f V", new Object[]{Double.valueOf(((double) this.mData5.YxcdjscdyMax) * 0.5d)}));
                }
                if (this.mData5.YxcdjsxdlMax == 65535) {
                    this.mItemBmsYxcdjscdlMax.SetVal("--");
                } else {
                    this.mItemBmsYxcdjscdlMax.SetVal(String.format("%.1f A", new Object[]{Double.valueOf(((double) this.mData5.YxcdjsxdlMax) * 0.5d)}));
                }
                this.mItemGydczcqcdgl.SetVal(String.format("%d Kw", new Object[]{Integer.valueOf(this.mData5.Gydczcqcdgl)}));
                this.mItemGydczdqcdgl.SetVal(String.format("%d Kw", new Object[]{Integer.valueOf(this.mData5.Gydczdqcdgl)}));
                if (this.mData5.Cdjcddyqqz == 65535) {
                    this.mItemCdjcddyqqz.SetVal("--");
                } else {
                    this.mItemCdjcddyqqz.SetVal(String.format("%.1f V", new Object[]{Double.valueOf(((double) this.mData5.Cdjcddyqqz) * 0.5d)}));
                }
                if (this.mData5.Cdjcddlqqz == 65535) {
                    this.mItemCdjcddlqqz.SetVal("--");
                } else {
                    this.mItemCdjcddlqqz.SetVal(String.format("%.1f A", new Object[]{Double.valueOf(((double) this.mData5.Cdjcddlqqz) * 0.5d)}));
                }
                this.mItemGydczcqfdgl.SetVal(String.format("%d Kw", new Object[]{Integer.valueOf(this.mData5.Gydczcqfdgl)}));
                this.mItemGydczdqfdgl.SetVal(String.format("%d Kw", new Object[]{Integer.valueOf(this.mData5.Gydczdqfdgl)}));
                if (this.mData5.Dcdtdygc > 1) {
                    this.mItemDcdtdygc.SetVal("--");
                } else {
                    this.mItemDcdtdygc.SetVal(this.DcdtdygcArr[this.mData5.Dcdtdygc]);
                }
                if (this.mData5.Dcdtdygf > 1) {
                    this.mItemDcdtdygf.SetVal("--");
                } else {
                    this.mItemDcdtdygf.SetVal(this.DcdtdygfArr[this.mData5.Dcdtdygf]);
                }
                if (this.mData5.Dcfdgl > 1) {
                    this.mItemDcfdgl.SetVal("--");
                } else {
                    this.mItemDcfdgl.SetVal(this.DcfdglArr[this.mData5.Dcfdgl]);
                }
                if (this.mData5.Socgdzs > 1) {
                    this.mItemSocgdzs.SetVal("--");
                } else {
                    this.mItemSocgdzs.SetVal(this.SocGdzsArr[this.mData5.Socgdzs]);
                }
            }
        }
    }

    public String GetTempStr(int temp) {
        if (temp > 250) {
            return "-- ℃";
        }
        return String.format("%d ℃", new Object[]{Integer.valueOf(temp - 40)});
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                ShowStatus(true);
                return;
            case 2:
                if (this.mWarnNum > 0) {
                    ShowStatus(false);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void UserAll() {
        ResetData(true);
    }
}
