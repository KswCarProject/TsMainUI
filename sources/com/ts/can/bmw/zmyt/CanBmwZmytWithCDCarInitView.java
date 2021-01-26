package com.ts.can.bmw.zmyt;

import android.app.Activity;
import android.support.v4.internal.view.SupportMenu;
import android.view.View;
import com.lgb.canmodule.Can;
import com.ts.MainUI.R;
import com.ts.backcar.BackcarService;
import com.ts.can.CanCameraUI;
import com.ts.can.CanFunc;
import com.ts.can.CanScrollCarInfoView;
import com.ts.canview.CanItemMsgBox;
import com.ts.factoryset.FsCanActivity;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.Mcu;

public class CanBmwZmytWithCDCarInitView extends CanScrollCarInfoView implements CanItemMsgBox.onMsgBoxClick {
    private static final int ITEM_AUX_CONFIG = 0;
    private static final int ITEM_AUX_PARA1 = 1;
    private static final int ITEM_AUX_PARA2 = 2;
    private static final int ITEM_AUX_PARA3 = 3;
    private static final int ITEM_AUX_PARA4 = 4;
    private static final int ITEM_HOST_RES = 5;
    private static final int ITEM_LVDS_TYPE = 7;
    private static final int ITEM_MAX = 7;
    private static final int ITEM_MIN = 0;
    private static final int ITEM_RES_AUTO = 6;
    static byte[] data = new byte[25];
    private static int m_AuxConfigb = 0;
    private static int m_AuxPara1b = 0;
    private static int m_AuxPara2b = 0;
    private static int m_AuxPara3b = 0;
    private static int m_AuxPara4b = 0;
    private static int m_HostResb = 0;
    private static int m_LvdsTypeb = 0;
    private static int m_ResAutoCnt = 0;

    public CanBmwZmytWithCDCarInitView(Activity activity) {
        super(activity, 8);
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_bmw_entauxmode, R.string.can_bmw_entauxmode_para, R.string.can_bmw_entauxmode_para2, R.string.can_bmw_entauxmode_para3, R.string.can_bmw_entauxmode_para4, R.string.can_host_res, R.string.can_Resolution_adaptive, R.string.can_lvds_type};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.POP};
        this.mPopValueIds[0] = new int[]{R.array.can_ent_aux_arrays};
        this.mPopValueIds[1] = new int[]{R.array.can_ent_aux_para_arrays};
        this.mPopValueIds[2] = new int[]{R.array.can_ent_aux_para_arrays};
        this.mPopValueIds[3] = new int[]{R.array.can_ent_aux_para_arrays};
        this.mPopValueIds[4] = new int[]{R.array.can_ent_aux_para_arrays};
        if (CanFunc.getInstance().IsCore() != 1) {
            this.mPopValueIds[5] = new int[]{R.array.can_digital_arr};
        } else {
            this.mPopValueIds[5] = new int[]{R.array.can_res_ts_bmw_num};
        }
        this.mPopValueIds[7] = new int[]{R.array.can_lvds_type};
        if (CanFunc.getInstance().IsCore() != 1) {
            this.mItemVisibles[6] = 0;
            this.mItemVisibles[7] = 0;
            return;
        }
        this.mItemVisibles[6] = 1;
        this.mItemVisibles[7] = 1;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v22, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v26, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v30, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v34, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v42, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v46, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v50, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v54, resolved type: byte} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void ResetData(boolean r28) {
        /*
            r27 = this;
            int r17 = m_AuxConfigb
            int r18 = com.yyw.ts70xhw.FtSet.Getlgb3()
            r18 = r18 & 15
            r0 = r17
            r1 = r18
            if (r0 != r1) goto L_0x0010
            if (r28 != 0) goto L_0x0063
        L_0x0010:
            int r17 = com.yyw.ts70xhw.FtSet.Getlgb3()
            r17 = r17 & 15
            m_AuxConfigb = r17
            r17 = 0
            int r18 = m_AuxConfigb
            r0 = r27
            r1 = r17
            r2 = r18
            r0.updateItem((int) r1, (int) r2)
            int r17 = m_AuxConfigb
            r18 = 1
            r0 = r17
            r1 = r18
            if (r0 != r1) goto L_0x043c
            r17 = 1
            r18 = 0
            r0 = r27
            r1 = r17
            r2 = r18
            r0.showItem(r1, r2)
            r17 = 2
            r18 = 1
            r0 = r27
            r1 = r17
            r2 = r18
            r0.showItem(r1, r2)
            r17 = 3
            r18 = 1
            r0 = r27
            r1 = r17
            r2 = r18
            r0.showItem(r1, r2)
            r17 = 4
            r18 = 0
            r0 = r27
            r1 = r17
            r2 = r18
            r0.showItem(r1, r2)
        L_0x0063:
            int r17 = m_AuxPara1b
            int r18 = com.yyw.ts70xhw.FtSet.Getlgb3()
            r0 = r18
            r0 = r0 & 240(0xf0, float:3.36E-43)
            r18 = r0
            int r18 = r18 >> 4
            r0 = r17
            r1 = r18
            if (r0 != r1) goto L_0x0079
            if (r28 != 0) goto L_0x0094
        L_0x0079:
            int r17 = com.yyw.ts70xhw.FtSet.Getlgb3()
            r0 = r17
            r0 = r0 & 240(0xf0, float:3.36E-43)
            r17 = r0
            int r17 = r17 >> 4
            m_AuxPara1b = r17
            r17 = 1
            int r18 = m_AuxPara1b
            r0 = r27
            r1 = r17
            r2 = r18
            r0.updateItem((int) r1, (int) r2)
        L_0x0094:
            int r17 = m_AuxPara2b
            int r18 = com.yyw.ts70xhw.FtSet.Getlgb3()
            r0 = r18
            r0 = r0 & 3840(0xf00, float:5.381E-42)
            r18 = r0
            int r18 = r18 >> 8
            r0 = r17
            r1 = r18
            if (r0 != r1) goto L_0x00aa
            if (r28 != 0) goto L_0x00c5
        L_0x00aa:
            int r17 = com.yyw.ts70xhw.FtSet.Getlgb3()
            r0 = r17
            r0 = r0 & 3840(0xf00, float:5.381E-42)
            r17 = r0
            int r17 = r17 >> 8
            m_AuxPara2b = r17
            r17 = 2
            int r18 = m_AuxPara2b
            r0 = r27
            r1 = r17
            r2 = r18
            r0.updateItem((int) r1, (int) r2)
        L_0x00c5:
            int r17 = m_AuxPara3b
            int r18 = com.yyw.ts70xhw.FtSet.Getlgb3()
            r19 = 61440(0xf000, float:8.6096E-41)
            r18 = r18 & r19
            int r18 = r18 >> 12
            r0 = r17
            r1 = r18
            if (r0 != r1) goto L_0x00da
            if (r28 != 0) goto L_0x00f4
        L_0x00da:
            int r17 = com.yyw.ts70xhw.FtSet.Getlgb3()
            r18 = 61440(0xf000, float:8.6096E-41)
            r17 = r17 & r18
            int r17 = r17 >> 12
            m_AuxPara3b = r17
            r17 = 3
            int r18 = m_AuxPara3b
            r0 = r27
            r1 = r17
            r2 = r18
            r0.updateItem((int) r1, (int) r2)
        L_0x00f4:
            int r17 = m_AuxPara4b
            int r18 = com.yyw.ts70xhw.FtSet.Getlgb4()
            r19 = 61440(0xf000, float:8.6096E-41)
            r18 = r18 & r19
            int r18 = r18 >> 12
            r0 = r17
            r1 = r18
            if (r0 != r1) goto L_0x0109
            if (r28 != 0) goto L_0x0123
        L_0x0109:
            int r17 = com.yyw.ts70xhw.FtSet.Getlgb4()
            r18 = 61440(0xf000, float:8.6096E-41)
            r17 = r17 & r18
            int r17 = r17 >> 12
            m_AuxPara4b = r17
            r17 = 4
            int r18 = m_AuxPara4b
            r0 = r27
            r1 = r17
            r2 = r18
            r0.updateItem((int) r1, (int) r2)
        L_0x0123:
            int r17 = m_HostResb
            int r18 = com.yyw.ts70xhw.FtSet.Getlgb4()
            r0 = r18
            r0 = r0 & 3840(0xf00, float:5.381E-42)
            r18 = r0
            int r18 = r18 >> 8
            r0 = r17
            r1 = r18
            if (r0 != r1) goto L_0x0139
            if (r28 != 0) goto L_0x0154
        L_0x0139:
            int r17 = com.yyw.ts70xhw.FtSet.Getlgb4()
            r0 = r17
            r0 = r0 & 3840(0xf00, float:5.381E-42)
            r17 = r0
            int r17 = r17 >> 8
            m_HostResb = r17
            r17 = 5
            int r18 = m_HostResb
            r0 = r27
            r1 = r17
            r2 = r18
            r0.updateItem((int) r1, (int) r2)
        L_0x0154:
            int r17 = m_LvdsTypeb
            int r18 = com.yyw.ts70xhw.FtSet.Getyw5()
            int r18 = r18 >> 1
            r18 = r18 & 3
            r0 = r17
            r1 = r18
            if (r0 != r1) goto L_0x0166
            if (r28 != 0) goto L_0x017d
        L_0x0166:
            int r17 = com.yyw.ts70xhw.FtSet.Getyw5()
            int r17 = r17 >> 1
            r17 = r17 & 3
            m_LvdsTypeb = r17
            r17 = 7
            int r18 = m_LvdsTypeb
            r0 = r27
            r1 = r17
            r2 = r18
            r0.updateItem((int) r1, (int) r2)
        L_0x017d:
            int r17 = m_ResAutoCnt
            if (r17 <= 0) goto L_0x043b
            int r17 = m_ResAutoCnt
            int r17 = r17 + -1
            m_ResAutoCnt = r17
            byte[] r17 = data
            int r17 = com.yyw.ts70xhw.Mcu.GetOrgTiming(r17)
            if (r17 <= 0) goto L_0x067a
            r17 = 0
            m_ResAutoCnt = r17
            r16 = 0
            byte[] r17 = data
            r18 = 1
            byte r17 = r17[r18]
            r0 = r17
            r0 = r0 & 255(0xff, float:3.57E-43)
            r17 = r0
            r0 = r17
            int r0 = r0 * 256
            r17 = r0
            byte[] r18 = data
            r19 = 2
            byte r18 = r18[r19]
            r0 = r18
            r0 = r0 & 255(0xff, float:3.57E-43)
            r18 = r0
            int r7 = r17 + r18
            byte[] r17 = data
            r18 = 3
            byte r17 = r17[r18]
            r0 = r17
            r0 = r0 & 255(0xff, float:3.57E-43)
            r17 = r0
            r0 = r17
            int r0 = r0 * 256
            r17 = r0
            byte[] r18 = data
            r19 = 4
            byte r18 = r18[r19]
            r0 = r18
            r0 = r0 & 255(0xff, float:3.57E-43)
            r18 = r0
            int r8 = r17 + r18
            byte[] r17 = data
            r18 = 5
            byte r17 = r17[r18]
            r0 = r17
            r0 = r0 & 255(0xff, float:3.57E-43)
            r17 = r0
            r0 = r17
            int r0 = r0 * 256
            r17 = r0
            byte[] r18 = data
            r19 = 6
            byte r18 = r18[r19]
            r0 = r18
            r0 = r0 & 255(0xff, float:3.57E-43)
            r18 = r0
            int r6 = r17 + r18
            byte[] r17 = data
            r18 = 7
            byte r17 = r17[r18]
            r0 = r17
            r0 = r0 & 255(0xff, float:3.57E-43)
            r17 = r0
            r0 = r17
            int r0 = r0 * 256
            r17 = r0
            byte[] r18 = data
            r19 = 8
            byte r18 = r18[r19]
            r0 = r18
            r0 = r0 & 255(0xff, float:3.57E-43)
            r18 = r0
            int r5 = r17 + r18
            byte[] r17 = data
            r18 = 9
            byte r17 = r17[r18]
            r0 = r17
            r0 = r0 & 255(0xff, float:3.57E-43)
            r17 = r0
            r0 = r17
            int r0 = r0 * 256
            r17 = r0
            byte[] r18 = data
            r19 = 10
            byte r18 = r18[r19]
            r0 = r18
            r0 = r0 & 255(0xff, float:3.57E-43)
            r18 = r0
            int r9 = r17 + r18
            byte[] r17 = data
            r18 = 11
            byte r17 = r17[r18]
            r0 = r17
            r0 = r0 & 255(0xff, float:3.57E-43)
            r17 = r0
            r0 = r17
            int r0 = r0 * 256
            r17 = r0
            byte[] r18 = data
            r19 = 12
            byte r18 = r18[r19]
            r0 = r18
            r0 = r0 & 255(0xff, float:3.57E-43)
            r18 = r0
            int r12 = r17 + r18
            byte[] r17 = data
            r18 = 13
            byte r17 = r17[r18]
            r0 = r17
            r0 = r0 & 255(0xff, float:3.57E-43)
            r17 = r0
            r0 = r17
            int r0 = r0 * 256
            r17 = r0
            byte[] r18 = data
            r19 = 14
            byte r18 = r18[r19]
            r0 = r18
            r0 = r0 & 255(0xff, float:3.57E-43)
            r18 = r0
            int r13 = r17 + r18
            byte[] r17 = data
            r18 = 15
            byte r17 = r17[r18]
            r0 = r17
            r0 = r0 & 255(0xff, float:3.57E-43)
            r17 = r0
            r0 = r17
            int r0 = r0 * 256
            r17 = r0
            byte[] r18 = data
            r19 = 16
            byte r18 = r18[r19]
            r0 = r18
            r0 = r0 & 255(0xff, float:3.57E-43)
            r18 = r0
            int r11 = r17 + r18
            byte[] r17 = data
            r18 = 17
            byte r17 = r17[r18]
            r0 = r17
            r0 = r0 & 255(0xff, float:3.57E-43)
            r17 = r0
            r0 = r17
            int r0 = r0 * 256
            r17 = r0
            byte[] r18 = data
            r19 = 18
            byte r18 = r18[r19]
            r0 = r18
            r0 = r0 & 255(0xff, float:3.57E-43)
            r18 = r0
            int r10 = r17 + r18
            byte[] r17 = data
            r18 = 19
            byte r17 = r17[r18]
            r0 = r17
            r0 = r0 & 255(0xff, float:3.57E-43)
            r17 = r0
            r0 = r17
            int r0 = r0 * 256
            r17 = r0
            byte[] r18 = data
            r19 = 20
            byte r18 = r18[r19]
            r0 = r18
            r0 = r0 & 255(0xff, float:3.57E-43)
            r18 = r0
            int r14 = r17 + r18
            byte[] r17 = data
            r18 = 21
            byte r17 = r17[r18]
            r0 = r17
            r0 = r0 & 255(0xff, float:3.57E-43)
            r17 = r0
            r18 = 65536(0x10000, float:9.18355E-41)
            int r17 = r17 * r18
            byte[] r18 = data
            r19 = 22
            byte r18 = r18[r19]
            r0 = r18
            r0 = r0 & 255(0xff, float:3.57E-43)
            r18 = r0
            r0 = r18
            int r0 = r0 * 256
            r18 = r0
            int r17 = r17 + r18
            byte[] r18 = data
            r19 = 23
            byte r18 = r18[r19]
            r0 = r18
            r0 = r0 & 255(0xff, float:3.57E-43)
            r18 = r0
            int r4 = r17 + r18
            byte[] r17 = data
            r18 = 0
            byte r17 = r17[r18]
            r0 = r17
            r0 = r0 & 255(0xff, float:3.57E-43)
            r17 = r0
            int r17 = r17 >> 1
            r17 = r17 & 1
            if (r17 <= 0) goto L_0x066a
            android.app.Activity r17 = r27.getActivity()
            android.content.res.Resources r17 = r17.getResources()
            int r18 = com.ts.MainUI.R.string.can_win
            java.lang.String r16 = r17.getString(r18)
            r17 = 800(0x320, float:1.121E-42)
            r0 = r17
            if (r5 != r0) goto L_0x04b2
            r17 = 480(0x1e0, float:6.73E-43)
            r0 = r17
            if (r10 != r0) goto L_0x04b2
            r17 = 0
            HostResSet(r17)
            r17 = 0
            r18 = 0
            r19 = 0
            SetCamType(r17, r18, r19)
            com.ts.canview.CanItemMsgBox r15 = new com.ts.canview.CanItemMsgBox
            r17 = 5
            android.app.Activity r18 = r27.getActivity()
            int r19 = com.ts.MainUI.R.string.can_car_select_tip
            r0 = r17
            r1 = r18
            r2 = r19
            r3 = r27
            r15.<init>(r0, r1, r2, r3)
        L_0x0356:
            com.ts.canview.CanTimerMsgBox r17 = new com.ts.canview.CanTimerMsgBox
            android.view.View r18 = r27.getView()
            android.content.Context r18 = r18.getContext()
            r19 = 5000(0x1388, double:2.4703E-320)
            java.lang.StringBuilder r21 = new java.lang.StringBuilder
            java.lang.String r22 = java.lang.String.valueOf(r16)
            r21.<init>(r22)
            java.lang.String r22 = "\nWrk:%d,    Sg:%d,   Pll:%d,    Poll:%d              \nHfp:%d,    Hs:%d,   Hbp:%d,    Hact:%d,    Htotal:%d\nVfp:%d,    Vs:%d,   Vbp:%d,    Vact:%d,    Vtotal:%d,\nClk:%d"
            r23 = 15
            r0 = r23
            java.lang.Object[] r0 = new java.lang.Object[r0]
            r23 = r0
            r24 = 0
            byte[] r25 = data
            r26 = 0
            byte r25 = r25[r26]
            r0 = r25
            r0 = r0 & 255(0xff, float:3.57E-43)
            r25 = r0
            r25 = r25 & 1
            java.lang.Integer r25 = java.lang.Integer.valueOf(r25)
            r23[r24] = r25
            r24 = 1
            byte[] r25 = data
            r26 = 0
            byte r25 = r25[r26]
            r0 = r25
            r0 = r0 & 255(0xff, float:3.57E-43)
            r25 = r0
            int r25 = r25 >> 1
            r25 = r25 & 1
            java.lang.Integer r25 = java.lang.Integer.valueOf(r25)
            r23[r24] = r25
            r24 = 2
            byte[] r25 = data
            r26 = 0
            byte r25 = r25[r26]
            r0 = r25
            r0 = r0 & 255(0xff, float:3.57E-43)
            r25 = r0
            int r25 = r25 >> 2
            r25 = r25 & 1
            java.lang.Integer r25 = java.lang.Integer.valueOf(r25)
            r23[r24] = r25
            r24 = 3
            byte[] r25 = data
            r26 = 0
            byte r25 = r25[r26]
            r0 = r25
            r0 = r0 & 255(0xff, float:3.57E-43)
            r25 = r0
            int r25 = r25 >> 3
            r25 = r25 & 3
            java.lang.Integer r25 = java.lang.Integer.valueOf(r25)
            r23[r24] = r25
            r24 = 4
            java.lang.Integer r25 = java.lang.Integer.valueOf(r7)
            r23[r24] = r25
            r24 = 5
            java.lang.Integer r25 = java.lang.Integer.valueOf(r8)
            r23[r24] = r25
            r24 = 6
            java.lang.Integer r25 = java.lang.Integer.valueOf(r6)
            r23[r24] = r25
            r24 = 7
            java.lang.Integer r25 = java.lang.Integer.valueOf(r5)
            r23[r24] = r25
            r24 = 8
            java.lang.Integer r25 = java.lang.Integer.valueOf(r9)
            r23[r24] = r25
            r24 = 9
            java.lang.Integer r25 = java.lang.Integer.valueOf(r12)
            r23[r24] = r25
            r24 = 10
            java.lang.Integer r25 = java.lang.Integer.valueOf(r13)
            r23[r24] = r25
            r24 = 11
            java.lang.Integer r25 = java.lang.Integer.valueOf(r11)
            r23[r24] = r25
            r24 = 12
            java.lang.Integer r25 = java.lang.Integer.valueOf(r10)
            r23[r24] = r25
            r24 = 13
            java.lang.Integer r25 = java.lang.Integer.valueOf(r14)
            r23[r24] = r25
            r24 = 14
            java.lang.Integer r25 = java.lang.Integer.valueOf(r4)
            r23[r24] = r25
            java.lang.String r22 = java.lang.String.format(r22, r23)
            java.lang.StringBuilder r21 = r21.append(r22)
            java.lang.String r21 = r21.toString()
            r17.<init>((android.content.Context) r18, (long) r19, (java.lang.String) r21)
        L_0x043b:
            return
        L_0x043c:
            int r17 = m_AuxConfigb
            r18 = 2
            r0 = r17
            r1 = r18
            if (r0 != r1) goto L_0x047c
            r17 = 1
            r18 = 0
            r0 = r27
            r1 = r17
            r2 = r18
            r0.showItem(r1, r2)
            r17 = 2
            r18 = 0
            r0 = r27
            r1 = r17
            r2 = r18
            r0.showItem(r1, r2)
            r17 = 3
            r18 = 0
            r0 = r27
            r1 = r17
            r2 = r18
            r0.showItem(r1, r2)
            r17 = 4
            r18 = 1
            r0 = r27
            r1 = r17
            r2 = r18
            r0.showItem(r1, r2)
            goto L_0x0063
        L_0x047c:
            r17 = 1
            r18 = 1
            r0 = r27
            r1 = r17
            r2 = r18
            r0.showItem(r1, r2)
            r17 = 2
            r18 = 0
            r0 = r27
            r1 = r17
            r2 = r18
            r0.showItem(r1, r2)
            r17 = 3
            r18 = 0
            r0 = r27
            r1 = r17
            r2 = r18
            r0.showItem(r1, r2)
            r17 = 4
            r18 = 0
            r0 = r27
            r1 = r17
            r2 = r18
            r0.showItem(r1, r2)
            goto L_0x0063
        L_0x04b2:
            r17 = 400(0x190, float:5.6E-43)
            r0 = r17
            if (r5 != r0) goto L_0x04e3
            r17 = 240(0xf0, float:3.36E-43)
            r0 = r17
            if (r10 != r0) goto L_0x04e3
            r17 = 1
            HostResSet(r17)
            r17 = 0
            r18 = 0
            r19 = 0
            SetCamType(r17, r18, r19)
            com.ts.canview.CanItemMsgBox r15 = new com.ts.canview.CanItemMsgBox
            r17 = 5
            android.app.Activity r18 = r27.getActivity()
            int r19 = com.ts.MainUI.R.string.can_car_select_tip
            r0 = r17
            r1 = r18
            r2 = r19
            r3 = r27
            r15.<init>(r0, r1, r2, r3)
            goto L_0x0356
        L_0x04e3:
            r17 = 1920(0x780, float:2.69E-42)
            r0 = r17
            if (r5 != r0) goto L_0x0514
            r17 = 720(0x2d0, float:1.009E-42)
            r0 = r17
            if (r10 != r0) goto L_0x0514
            r17 = 9
            HostResSet(r17)
            r17 = 0
            r18 = 0
            r19 = 0
            SetCamType(r17, r18, r19)
            com.ts.canview.CanItemMsgBox r15 = new com.ts.canview.CanItemMsgBox
            r17 = 5
            android.app.Activity r18 = r27.getActivity()
            int r19 = com.ts.MainUI.R.string.can_car_select_tip
            r0 = r17
            r1 = r18
            r2 = r19
            r3 = r27
            r15.<init>(r0, r1, r2, r3)
            goto L_0x0356
        L_0x0514:
            r17 = 1024(0x400, float:1.435E-42)
            r0 = r17
            if (r5 != r0) goto L_0x0545
            r17 = 600(0x258, float:8.41E-43)
            r0 = r17
            if (r10 != r0) goto L_0x0545
            r17 = 2
            HostResSet(r17)
            r17 = 0
            r18 = 0
            r19 = 0
            SetCamType(r17, r18, r19)
            com.ts.canview.CanItemMsgBox r15 = new com.ts.canview.CanItemMsgBox
            r17 = 5
            android.app.Activity r18 = r27.getActivity()
            int r19 = com.ts.MainUI.R.string.can_car_select_tip
            r0 = r17
            r1 = r18
            r2 = r19
            r3 = r27
            r15.<init>(r0, r1, r2, r3)
            goto L_0x0356
        L_0x0545:
            r17 = 1280(0x500, float:1.794E-42)
            r0 = r17
            if (r5 != r0) goto L_0x0576
            r17 = 480(0x1e0, float:6.73E-43)
            r0 = r17
            if (r10 != r0) goto L_0x0576
            r17 = 3
            HostResSet(r17)
            r17 = 0
            r18 = 0
            r19 = 0
            SetCamType(r17, r18, r19)
            com.ts.canview.CanItemMsgBox r15 = new com.ts.canview.CanItemMsgBox
            r17 = 5
            android.app.Activity r18 = r27.getActivity()
            int r19 = com.ts.MainUI.R.string.can_car_select_tip
            r0 = r17
            r1 = r18
            r2 = r19
            r3 = r27
            r15.<init>(r0, r1, r2, r3)
            goto L_0x0356
        L_0x0576:
            r17 = 480(0x1e0, float:6.73E-43)
            r0 = r17
            if (r5 != r0) goto L_0x05a7
            r17 = 240(0xf0, float:3.36E-43)
            r0 = r17
            if (r10 != r0) goto L_0x05a7
            r17 = 4
            HostResSet(r17)
            r17 = 0
            r18 = 0
            r19 = 0
            SetCamType(r17, r18, r19)
            com.ts.canview.CanItemMsgBox r15 = new com.ts.canview.CanItemMsgBox
            r17 = 5
            android.app.Activity r18 = r27.getActivity()
            int r19 = com.ts.MainUI.R.string.can_car_select_tip
            r0 = r17
            r1 = r18
            r2 = r19
            r3 = r27
            r15.<init>(r0, r1, r2, r3)
            goto L_0x0356
        L_0x05a7:
            r17 = 640(0x280, float:8.97E-43)
            r0 = r17
            if (r5 != r0) goto L_0x05d8
            r17 = 250(0xfa, float:3.5E-43)
            r0 = r17
            if (r10 != r0) goto L_0x05d8
            r17 = 5
            HostResSet(r17)
            r17 = 0
            r18 = 0
            r19 = 0
            SetCamType(r17, r18, r19)
            com.ts.canview.CanItemMsgBox r15 = new com.ts.canview.CanItemMsgBox
            r17 = 5
            android.app.Activity r18 = r27.getActivity()
            int r19 = com.ts.MainUI.R.string.can_car_select_tip
            r0 = r17
            r1 = r18
            r2 = r19
            r3 = r27
            r15.<init>(r0, r1, r2, r3)
            goto L_0x0356
        L_0x05d8:
            r17 = 400(0x190, float:5.6E-43)
            r0 = r17
            if (r5 != r0) goto L_0x0609
            r17 = 250(0xfa, float:3.5E-43)
            r0 = r17
            if (r10 != r0) goto L_0x0609
            r17 = 6
            HostResSet(r17)
            r17 = 0
            r18 = 0
            r19 = 0
            SetCamType(r17, r18, r19)
            com.ts.canview.CanItemMsgBox r15 = new com.ts.canview.CanItemMsgBox
            r17 = 5
            android.app.Activity r18 = r27.getActivity()
            int r19 = com.ts.MainUI.R.string.can_car_select_tip
            r0 = r17
            r1 = r18
            r2 = r19
            r3 = r27
            r15.<init>(r0, r1, r2, r3)
            goto L_0x0356
        L_0x0609:
            int r17 = r7 + r8
            int r17 = r17 + r6
            int r17 = r17 + r5
            r0 = r17
            if (r9 != r0) goto L_0x065a
            r17 = 50
            r0 = r17
            if (r9 < r0) goto L_0x065a
            int r17 = r12 + r13
            int r17 = r17 + r11
            int r17 = r17 + r10
            r0 = r17
            if (r14 != r0) goto L_0x065a
            r17 = 50
            r0 = r17
            if (r14 < r0) goto L_0x065a
            android.app.Activity r17 = r27.getActivity()
            android.content.res.Resources r17 = r17.getResources()
            int r18 = com.ts.MainUI.R.string.can_Resolution_auto
            java.lang.String r16 = r17.getString(r18)
            r17 = 12
            HostResSet(r17)
            r17 = 1
            r0 = r17
            SetCamType(r0, r5, r10)
            com.ts.canview.CanItemMsgBox r15 = new com.ts.canview.CanItemMsgBox
            r17 = 5
            android.app.Activity r18 = r27.getActivity()
            int r19 = com.ts.MainUI.R.string.can_car_select_tip
            r0 = r17
            r1 = r18
            r2 = r19
            r3 = r27
            r15.<init>(r0, r1, r2, r3)
            goto L_0x0356
        L_0x065a:
            android.app.Activity r17 = r27.getActivity()
            android.content.res.Resources r17 = r17.getResources()
            int r18 = com.ts.MainUI.R.string.can_Invalid_resolution
            java.lang.String r16 = r17.getString(r18)
            goto L_0x0356
        L_0x066a:
            android.app.Activity r17 = r27.getActivity()
            android.content.res.Resources r17 = r17.getResources()
            int r18 = com.ts.MainUI.R.string.can_Invalid_resolution
            java.lang.String r16 = r17.getString(r18)
            goto L_0x0356
        L_0x067a:
            int r17 = m_ResAutoCnt
            if (r17 != 0) goto L_0x043b
            com.ts.canview.CanTimerMsgBox r17 = new com.ts.canview.CanTimerMsgBox
            android.view.View r18 = r27.getView()
            android.content.Context r18 = r18.getContext()
            r19 = 3000(0xbb8, double:1.482E-320)
            int r21 = com.ts.MainUI.R.string.can_defeat
            r17.<init>((android.content.Context) r18, (long) r19, (int) r21)
            goto L_0x043b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ts.can.bmw.zmyt.CanBmwZmytWithCDCarInitView.ResetData(boolean):void");
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 6:
                m_ResAutoCnt = 100;
                Mcu.ReqOrgTiming(1);
                return;
            default:
                return;
        }
    }

    public static int IsAuxConfig() {
        if ((FtSet.Getlgb3() & 15) >= 3) {
            return 1;
        }
        return (FtSet.Getlgb3() & 15) + 1;
    }

    public static int IsAuxPara1() {
        if (((FtSet.Getlgb3() & Can.CAN_VOLKS_XP) >> 4) == 0 || ((FtSet.Getlgb3() & Can.CAN_VOLKS_XP) >> 4) >= 8) {
            return 1;
        }
        return ((FtSet.Getlgb3() & Can.CAN_VOLKS_XP) >> 4) + 1;
    }

    public static int IsAuxPara2() {
        if (((FtSet.Getlgb3() & 3840) >> 8) == 0 || ((FtSet.Getlgb3() & 3840) >> 8) >= 8) {
            return 1;
        }
        return ((FtSet.Getlgb3() & 3840) >> 8) + 1;
    }

    public static int IsAuxPara3() {
        if (((FtSet.Getlgb3() & 61440) >> 12) == 0 || ((FtSet.Getlgb3() & 61440) >> 12) >= 8) {
            return 1;
        }
        return ((FtSet.Getlgb3() & 61440) >> 12) + 1;
    }

    public static int IsAuxPara4() {
        if (((FtSet.Getlgb4() & 61440) >> 12) == 0 || ((FtSet.Getlgb4() & 61440) >> 12) >= 8) {
            return 1;
        }
        return ((FtSet.Getlgb4() & 61440) >> 12) + 1;
    }

    public static int HostRes() {
        return (FtSet.Getlgb4() & 3840) >> 8;
    }

    public static int IsLvdsType() {
        return (FtSet.Getyw5() & 6) >> 1;
    }

    public static int HostResSet(int num) {
        FtSet.Setlgb4((num << 8) | (FtSet.Getlgb4() & 61695));
        return 1;
    }

    public static int SetCamType(int fore, int px, int py) {
        if (CanFunc.getInstance().IsCore() != 1) {
            return 0;
        }
        int x = SupportMenu.USER_MASK;
        int y = SupportMenu.USER_MASK;
        if (HostRes() == 0) {
            x = 800;
            y = 480;
        } else if (HostRes() == 1) {
            x = CanCameraUI.BTN_TRUMPCHI_GS4_MODE1;
            y = Can.CAN_VOLKS_XP;
        } else if (HostRes() == 9) {
            x = 1920;
            y = 720;
        } else if (HostRes() == 2) {
            x = 1024;
            y = 600;
        } else if (HostRes() == 3) {
            x = 1280;
            y = 480;
        } else if (HostRes() == 4) {
            x = 480;
            y = Can.CAN_VOLKS_XP;
        } else if (HostRes() == 5) {
            x = CanCameraUI.BTN_LANDWIND_2D3D;
            y = Can.CAN_NISSAN_XFY;
        } else if (HostRes() == 6) {
            x = CanCameraUI.BTN_TRUMPCHI_GS4_MODE1;
            y = Can.CAN_NISSAN_XFY;
        } else if (HostRes() == 12 && fore > 0) {
            x = px;
            y = py;
            if (x < 100 || y < 100) {
                return 0;
            }
        }
        if (CanBmwZmytWithCDCarFuncView.RvsMode() == 1) {
            BackcarService.getInstance().SetCamType(5, x, y);
        } else if (CanBmwZmytWithCDCarFuncView.RvsMode() == 2) {
            BackcarService.getInstance().SetCamType(3, x, y);
        } else if (CanBmwZmytWithCDCarFuncView.RvsMode() == 3) {
            BackcarService.getInstance().SetCamType(4, x, y);
        } else {
            BackcarService.getInstance().SetCamType(0, x, y);
        }
        return 1;
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
                return;
            case 4:
                FtSet.Setlgb4((item << 12) | (FtSet.Getlgb4() & FsCanActivity.DOOR_UPDATE_ALL));
                return;
            case 5:
                FtSet.Setlgb4((item << 8) | (FtSet.Getlgb4() & 61695));
                Mcu.SendXKey(((FtSet.Getlgb4() & 3840) >> 8) + 50);
                SetCamType(0, 0, 0);
                if (CanFunc.getInstance().IsCore() == 1) {
                    new CanItemMsgBox(5, getActivity(), R.string.can_car_select_tip, this);
                    return;
                }
                return;
            case 7:
                FtSet.Setyw5((item << 1) | (FtSet.Getyw5() & 65529));
                if (item == 0) {
                    Mcu.ReqOrgTiming(32);
                    return;
                } else if (item == 1) {
                    Mcu.ReqOrgTiming(33);
                    return;
                } else {
                    return;
                }
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onOK(int param) {
        switch (param) {
            case 5:
                Mcu.SendXKey(20);
                return;
            default:
                return;
        }
    }

    public void QueryData() {
    }
}
