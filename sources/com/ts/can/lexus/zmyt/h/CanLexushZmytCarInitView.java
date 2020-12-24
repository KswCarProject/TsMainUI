package com.ts.can.lexus.zmyt.h;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanFunc;
import com.ts.can.CanScrollCarInfoView;
import com.ts.can.MyApplication;
import com.ts.factoryset.FsCanActivity;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.Mcu;

public class CanLexushZmytCarInitView extends CanScrollCarInfoView {
    private static final int ITEM_AIR = 1;
    private static final int ITEM_AUX_CONFIG = 0;
    private static final int ITEM_CAR_DOOR_UI = 4;
    private static final int ITEM_HOST_RES = 3;
    private static final int ITEM_MAX = 4;
    private static final int ITEM_MIN = 0;
    private static final int ITEM_SLEEP_TIME = 2;
    private static int m_Airb = 0;
    private static int m_AuxConfigb = 0;
    private static int m_DoorUIb = 0;
    private static int m_HostResb = 0;
    private CanDataInfo.LexusH_CarSta m_SetData;

    public CanLexushZmytCarInitView(Activity activity) {
        super(activity, 5);
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_audi_aux_config, R.string.can_dfqc_ac, R.string.can_jmqxmsj, R.string.can_host_res, R.string.can_door_ui};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP};
        this.mPopValueIds[0] = new int[]{R.array.can_ent_aux_para2_arrays};
        int[][] iArr = this.mProgressAttrs;
        int[] iArr2 = new int[4];
        iArr2[1] = 255;
        iArr2[2] = 1;
        iArr[2] = iArr2;
        this.mPopValueIds[3] = new int[]{R.array.can_digital_arr};
        this.mPopValueIds[4] = new int[]{R.array.can_door_ui_arr};
        this.m_SetData = new CanDataInfo.LexusH_CarSta();
    }

    public void ResetData(boolean check) {
        if (m_AuxConfigb != (FtSet.Getlgb3() & 15) || !check) {
            m_AuxConfigb = FtSet.Getlgb3() & 15;
            updateItem(0, m_AuxConfigb);
        }
        if (m_Airb != ((FtSet.Getlgb3() & 240) >> 4) || !check) {
            m_Airb = (FtSet.Getlgb3() & 240) >> 4;
            updateItem(1, m_Airb == 0 ? 1 : 0);
        }
        if (m_HostResb != ((FtSet.Getlgb3() & 61440) >> 12) || !check) {
            m_HostResb = (FtSet.Getlgb3() & 61440) >> 12;
            updateItem(3, m_HostResb);
        }
        if (m_DoorUIb != ((FtSet.Getlgb4() & 61440) >> 12) || !check) {
            m_DoorUIb = (FtSet.Getlgb4() & 61440) >> 12;
            updateItem(4, m_DoorUIb);
        }
        CanJni.LexusHZmytGetCarSta(this.m_SetData);
        if (i2b(this.m_SetData.UpdateOnce) && (!check || i2b(this.m_SetData.Update))) {
            if (this.m_SetData.SleepTime == 0) {
                updateItem(2, this.m_SetData.SleepTime, " 30s");
            } else {
                updateItem(2, this.m_SetData.SleepTime, String.format("%ds", new Object[]{Integer.valueOf(this.m_SetData.SleepTime * 5)}));
            }
        }
        showItem(4, 0);
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                int temp = FtSet.Getlgb3() & 65295;
                if (((FtSet.Getlgb3() & 240) >> 4) > 0) {
                    FtSet.Setlgb3(temp);
                } else {
                    FtSet.Setlgb3(temp | 16);
                }
                byte[] fileMsg = new byte[8];
                fileMsg[0] = (byte) FtSet.GetCanTp();
                fileMsg[1] = (byte) ((FtSet.GetCanTp() >> 8) & 255);
                fileMsg[2] = (byte) FtSet.GetCanSubT();
                fileMsg[3] = (byte) IsHaveAir();
                CanFunc.getInstance();
                CanFunc.sendFileCarInfo(fileMsg, CanFunc.Can_Factory_Set_fileName);
                MyApplication.mContext.sendBroadcast(new Intent("com.ts.can.BROADCAST_CAN_INFO_LEXUS_AIR"));
                return;
            default:
                return;
        }
    }

    public static int IsAuxConfig() {
        return FtSet.Getlgb3() & 15;
    }

    public static int IsHaveAir() {
        if (((FtSet.Getlgb3() & 240) >> 4) > 0) {
            return 0;
        }
        return 1;
    }

    public static int IsAuxLin2() {
        return (FtSet.Getlgb3() & 3840) >> 8;
    }

    public static int HostRes() {
        return (FtSet.Getlgb3() & 61440) >> 12;
    }

    public static int DoorUI() {
        return (FtSet.Getlgb4() & 61440) >> 12;
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                FtSet.Setlgb3((FtSet.Getlgb3() & 65520) | item);
                return;
            case 3:
                FtSet.Setlgb3((item << 12) | (FtSet.Getlgb3() & FsCanActivity.DOOR_UPDATE_ALL));
                Mcu.SendXKey(((FtSet.Getlgb3() & 61440) >> 12) + 50);
                return;
            case 4:
                FtSet.Setlgb4((item << 12) | (FtSet.Getlgb4() & FsCanActivity.DOOR_UPDATE_ALL));
                byte[] fileMsg = new byte[8];
                fileMsg[0] = (byte) FtSet.GetCanTp();
                fileMsg[1] = (byte) item;
                CanFunc.sendFileCarInfo(fileMsg, CanFunc.Can_Factory_Set_fileName);
                Intent intent = new Intent("com.ts.can.BROADCAST_CAN_INFO");
                intent.putExtra("CanType", CanJni.GetCanType());
                intent.putExtra("CanSubType", item);
                MyApplication.mContext.sendBroadcast(intent);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
        switch (id) {
            case 2:
                CanJni.LexusHZmytConfigSet(48, pos);
                return;
            default:
                return;
        }
    }

    public void QueryData() {
        CanJni.LexusHZmytQuery(24);
    }
}
