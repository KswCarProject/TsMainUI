package com.ts.can.audi.xhd;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanFunc;
import com.ts.can.CanScrollCarInfoView;
import com.ts.can.MyApplication;
import com.ts.factoryset.FsCanActivity;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.Mcu;

public class CanAudiWithCDCarInitView extends CanScrollCarInfoView {
    private static final int ITEM_AUX_CONFIG = 0;
    private static final int ITEM_AUX_LINE1 = 1;
    private static final int ITEM_AUX_LINE2 = 2;
    private static final int ITEM_CAR_DOOR_UI = 4;
    private static final int ITEM_HOST_RES = 3;
    private static final int ITEM_MAX = 4;
    private static final int ITEM_MIN = 0;
    private static int m_AuxConfigb = 0;
    private static int m_AuxLIn1b = 0;
    private static int m_AuxLIn2b = 0;
    private static int m_DoorUIb = 0;
    private static int m_HostResb = 0;

    public CanAudiWithCDCarInitView(Activity activity) {
        super(activity, 5);
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_audi_aux_config, R.string.can_audi_aux_line1, R.string.can_audi_aux_line2, R.string.can_host_res, R.string.can_door_ui};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP};
        this.mPopValueIds[0] = new int[]{R.array.can_AuxConfigArr};
        this.mPopValueIds[1] = new int[]{R.array.can_AuxLine1Arr};
        this.mPopValueIds[2] = new int[]{R.array.can_AuxLine2Arr};
        this.mPopValueIds[3] = new int[]{R.array.can_digital_arr};
        this.mPopValueIds[4] = new int[]{R.array.can_door_audi_ui_arr};
    }

    public void ResetData(boolean check) {
        if (m_AuxConfigb != (FtSet.Getlgb3() & 15) || !check) {
            m_AuxConfigb = FtSet.Getlgb3() & 15;
            updateItem(0, m_AuxConfigb);
            if (m_AuxConfigb == 0) {
                showItem(2, 0);
            } else {
                showItem(2, 1);
            }
        }
        if (m_AuxLIn1b != ((FtSet.Getlgb3() & 240) >> 4) || !check) {
            m_AuxLIn1b = (FtSet.Getlgb3() & 240) >> 4;
            updateItem(1, m_AuxLIn1b);
        }
        if (m_AuxLIn2b != ((FtSet.Getlgb3() & 3840) >> 8) || !check) {
            m_AuxLIn2b = (FtSet.Getlgb3() & 3840) >> 8;
            updateItem(2, m_AuxLIn2b);
        }
        if (m_HostResb != ((FtSet.Getlgb3() & 61440) >> 12) || !check) {
            m_HostResb = (FtSet.Getlgb3() & 61440) >> 12;
            updateItem(3, m_HostResb);
        }
        if (m_DoorUIb != ((FtSet.Getlgb4() & 61440) >> 12) || !check) {
            m_DoorUIb = (FtSet.Getlgb4() & 61440) >> 12;
            updateItem(4, m_DoorUIb);
        }
    }

    public void onClick(View v) {
        int intValue = ((Integer) v.getTag()).intValue();
    }

    public static int IsAuxConfig() {
        if ((FtSet.Getlgb3() & 15) > 0) {
            return 1;
        }
        return 0;
    }

    public static int IsAuxLin1() {
        return (FtSet.Getlgb3() & 240) >> 4;
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
            case 1:
                FtSet.Setlgb3((item << 4) | (FtSet.Getlgb3() & 65295));
                return;
            case 2:
                FtSet.Setlgb3((item << 8) | (FtSet.Getlgb3() & 61695));
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
    }

    public void QueryData() {
    }
}
