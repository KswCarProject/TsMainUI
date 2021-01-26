package com.ts.can.gm.xt5;

import android.content.Context;
import android.support.v4.internal.view.SupportMenu;
import android.view.View;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.Evc;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.backcar.BackcarService;
import com.ts.can.CanCameraUI;
import com.ts.can.CanCommonActivity;
import com.ts.can.CanFunc;
import com.ts.can.benc.withcd.CanBencWithCDCarFuncActivity;
import com.ts.canview.CanItemMsgBox;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemProgressList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanItemTextBtnList;
import com.ts.canview.CanScrollList;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.Iop;
import com.yyw.ts70xhw.Mcu;

public class CanCadillacXt5CarFuncActivity extends CanCommonActivity implements CanItemPopupList.onPopItemClick, CanItemProgressList.onPosChange, CanItemMsgBox.onMsgBoxClick {
    private static final int ITEM_AUDIO_IC = 2;
    private static final int ITEM_CAMERA = 0;
    private static final int ITEM_HOST_RES = 4;
    private static final int ITEM_NAVI_PER = 3;
    private static final int ITEM_RES_AUTO = 5;
    private static final int ITEM_R_CAMERA = 1;
    static byte[] data = new byte[25];
    private static int m_AudioIcb = 0;
    private static int m_Camerb = 0;
    private static int m_HostResb = 0;
    private static int m_NaviPerb = 0;
    private static int m_RCamerb = 0;
    private static int m_ResAutoCnt = 0;
    private int[] mCamera8259Arr = {R.string.can_rvs_carmode, R.string.can_rvs_cvbs, R.string.can_rvs_avm, R.string.can_rvs_ahd};
    private int[] mCameraArr = {R.string.can_rvs_carmode, R.string.can_rvs_cvbs, R.string.can_rvs_vga};
    private CanItemSwitchList mItemAudioIc;
    private CanItemPopupList mItemCamera;
    private CanItemPopupList mItemHostRes;
    private CanItemProgressList mItemNaviPer;
    private CanItemSwitchList mItemRCamera;
    private CanItemTextBtnList mItemResAuto;
    private CanScrollList mManager;
    private String[] mRes8259Arr = {"800x480", "400x240", "1024x600", "1280x480", "480x240", "ats lowver 480x270", "--", "--", "--", "1920x720", "--", "--", "A"};

    /* access modifiers changed from: protected */
    public int GetContentLayoutId() {
        return R.layout.activity_can_comm_list;
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mManager = new CanScrollList(this);
        if (CanFunc.getInstance().IsCore() != 1) {
            this.mItemCamera = AddPopupItem(R.string.can_camera_360, this.mCameraArr, 0);
        } else {
            this.mItemCamera = AddPopupItem(R.string.can_camera_360, this.mCamera8259Arr, 0);
        }
        this.mItemRCamera = AddCheckItem(R.string.can_tigger7_start_avm, 1);
        this.mItemAudioIc = AddCheckItem(R.string.can_ycusbxlbsfcdhy, 2);
        this.mItemNaviPer = AddProgressItem(R.string.can_navi_pre, 3);
        this.mItemNaviPer.SetMinMax(0, 100);
        this.mItemNaviPer.SetUserValText();
        this.mItemHostRes = AddPopupItem(R.string.can_host_res, this.mRes8259Arr, 4);
        this.mItemResAuto = AddTextBtn(R.string.can_Resolution_adaptive, 5);
        if (!(CanJni.GetSubType() == 4 || CanJni.GetSubType() == 5) || CanFunc.getInstance().IsCore() == 1) {
            this.mItemAudioIc.ShowGone(false);
        }
        if (CanFunc.getInstance().IsCore() != 1) {
            this.mItemHostRes.ShowGone(false);
            this.mItemResAuto.ShowGone(false);
        }
    }

    /* access modifiers changed from: protected */
    public CanItemSwitchList AddCheckItem(int resId, int Id) {
        CanItemSwitchList item = new CanItemSwitchList(this, resId);
        item.SetIdClickListener(this, Id);
        this.mManager.AddView(item.GetView());
        item.ShowGone(true);
        return item;
    }

    /* access modifiers changed from: protected */
    public CanItemPopupList AddPopupItem(int text, String[] val, int id) {
        CanItemPopupList item = new CanItemPopupList((Context) this, text, val, (CanItemPopupList.onPopItemClick) this);
        item.SetId(id);
        this.mManager.AddView(item.GetView());
        item.ShowGone(true);
        return item;
    }

    /* access modifiers changed from: protected */
    public CanItemPopupList AddPopupItem(int text, int[] val, int id) {
        CanItemPopupList item = new CanItemPopupList((Context) this, text, val, (CanItemPopupList.onPopItemClick) this);
        item.SetId(id);
        this.mManager.AddView(item.GetView());
        return item;
    }

    /* access modifiers changed from: protected */
    public CanItemProgressList AddProgressItem(int resId, int Id) {
        CanItemProgressList item = new CanItemProgressList((Context) this, resId);
        item.SetIdCallBack(Id, this);
        this.mManager.AddView(item.GetView());
        return item;
    }

    private CanItemTextBtnList AddTextBtn(int text, int id) {
        CanItemTextBtnList btn = new CanItemTextBtnList((Context) this, text);
        btn.SetIdClickListener(this, id);
        this.mManager.AddView(btn.GetView());
        btn.ShowGone(true);
        return btn;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v18, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v22, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v26, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v30, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v38, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v42, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v46, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v50, resolved type: byte} */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void ResetData(boolean r28) {
        /*
            r27 = this;
            int r17 = m_Camerb
            int r18 = com.yyw.ts70xhw.FtSet.Getlgb1()
            r0 = r17
            r1 = r18
            if (r0 != r1) goto L_0x0026
            int r17 = m_RCamerb
            int r18 = com.yyw.ts70xhw.FtSet.Getlgb2()
            r0 = r17
            r1 = r18
            if (r0 != r1) goto L_0x0026
            int r17 = m_AudioIcb
            int r18 = com.yyw.ts70xhw.FtSet.Getlgb4()
            r0 = r17
            r1 = r18
            if (r0 != r1) goto L_0x0026
            if (r28 != 0) goto L_0x005f
        L_0x0026:
            int r17 = com.yyw.ts70xhw.FtSet.Getlgb1()
            m_Camerb = r17
            int r17 = com.yyw.ts70xhw.FtSet.Getlgb2()
            m_RCamerb = r17
            int r17 = com.yyw.ts70xhw.FtSet.Getlgb4()
            m_AudioIcb = r17
            r0 = r27
            com.ts.canview.CanItemPopupList r0 = r0.mItemCamera
            r17 = r0
            int r18 = com.yyw.ts70xhw.FtSet.Getlgb1()
            r17.SetSel(r18)
            r0 = r27
            com.ts.canview.CanItemSwitchList r0 = r0.mItemRCamera
            r17 = r0
            int r18 = com.yyw.ts70xhw.FtSet.Getlgb2()
            r17.SetCheck((int) r18)
            r0 = r27
            com.ts.canview.CanItemSwitchList r0 = r0.mItemAudioIc
            r17 = r0
            int r18 = com.yyw.ts70xhw.FtSet.Getlgb4()
            r17.SetCheck((int) r18)
        L_0x005f:
            int r17 = m_NaviPerb
            int r18 = NaviPre()
            r0 = r17
            r1 = r18
            if (r0 != r1) goto L_0x006d
            if (r28 != 0) goto L_0x009d
        L_0x006d:
            int r17 = NaviPre()
            m_NaviPerb = r17
            r0 = r27
            com.ts.canview.CanItemProgressList r0 = r0.mItemNaviPer
            r17 = r0
            int r18 = m_NaviPerb
            r17.SetCurVal(r18)
            r0 = r27
            com.ts.canview.CanItemProgressList r0 = r0.mItemNaviPer
            r17 = r0
            java.lang.StringBuilder r18 = new java.lang.StringBuilder
            int r19 = m_NaviPerb
            java.lang.String r19 = java.lang.String.valueOf(r19)
            r18.<init>(r19)
            java.lang.String r19 = "%"
            java.lang.StringBuilder r18 = r18.append(r19)
            java.lang.String r18 = r18.toString()
            r17.SetValText((java.lang.String) r18)
        L_0x009d:
            int r17 = m_HostResb
            int r18 = com.yyw.ts70xhw.FtSet.Getlgb5()
            r0 = r17
            r1 = r18
            if (r0 != r1) goto L_0x00ab
            if (r28 != 0) goto L_0x00be
        L_0x00ab:
            int r17 = com.yyw.ts70xhw.FtSet.Getlgb5()
            m_HostResb = r17
            r0 = r27
            com.ts.canview.CanItemPopupList r0 = r0.mItemHostRes
            r17 = r0
            int r18 = com.yyw.ts70xhw.FtSet.Getlgb5()
            r17.SetSel(r18)
        L_0x00be:
            int r17 = m_ResAutoCnt
            if (r17 <= 0) goto L_0x037a
            int r17 = m_ResAutoCnt
            int r17 = r17 + -1
            m_ResAutoCnt = r17
            byte[] r17 = data
            int r17 = com.yyw.ts70xhw.Mcu.GetOrgTiming(r17)
            if (r17 <= 0) goto L_0x04ea
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
            if (r17 <= 0) goto L_0x04de
            android.content.res.Resources r17 = r27.getResources()
            int r18 = com.ts.MainUI.R.string.can_win
            java.lang.String r16 = r17.getString(r18)
            r17 = 800(0x320, float:1.121E-42)
            r0 = r17
            if (r5 != r0) goto L_0x037b
            r17 = 480(0x1e0, float:6.73E-43)
            r0 = r17
            if (r10 != r0) goto L_0x037b
            r17 = 0
            HostResSet(r17)
            r17 = 0
            r18 = 0
            r19 = 0
            SetCamType(r17, r18, r19)
            com.ts.canview.CanItemMsgBox r15 = new com.ts.canview.CanItemMsgBox
            r17 = 4
            int r18 = com.ts.MainUI.R.string.can_car_select_tip
            r0 = r17
            r1 = r27
            r2 = r18
            r3 = r27
            r15.<init>(r0, r1, r2, r3)
        L_0x028f:
            com.ts.canview.CanTimerMsgBox r17 = new com.ts.canview.CanTimerMsgBox
            r0 = r27
            com.ts.canview.CanScrollList r0 = r0.mManager
            r18 = r0
            android.widget.LinearLayout r18 = r18.getLayout()
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
        L_0x037a:
            return
        L_0x037b:
            r17 = 400(0x190, float:5.6E-43)
            r0 = r17
            if (r5 != r0) goto L_0x03a8
            r17 = 240(0xf0, float:3.36E-43)
            r0 = r17
            if (r10 != r0) goto L_0x03a8
            r17 = 1
            HostResSet(r17)
            r17 = 0
            r18 = 0
            r19 = 0
            SetCamType(r17, r18, r19)
            com.ts.canview.CanItemMsgBox r15 = new com.ts.canview.CanItemMsgBox
            r17 = 4
            int r18 = com.ts.MainUI.R.string.can_car_select_tip
            r0 = r17
            r1 = r27
            r2 = r18
            r3 = r27
            r15.<init>(r0, r1, r2, r3)
            goto L_0x028f
        L_0x03a8:
            r17 = 1920(0x780, float:2.69E-42)
            r0 = r17
            if (r5 != r0) goto L_0x03d5
            r17 = 720(0x2d0, float:1.009E-42)
            r0 = r17
            if (r10 != r0) goto L_0x03d5
            r17 = 9
            HostResSet(r17)
            r17 = 0
            r18 = 0
            r19 = 0
            SetCamType(r17, r18, r19)
            com.ts.canview.CanItemMsgBox r15 = new com.ts.canview.CanItemMsgBox
            r17 = 4
            int r18 = com.ts.MainUI.R.string.can_car_select_tip
            r0 = r17
            r1 = r27
            r2 = r18
            r3 = r27
            r15.<init>(r0, r1, r2, r3)
            goto L_0x028f
        L_0x03d5:
            r17 = 1024(0x400, float:1.435E-42)
            r0 = r17
            if (r5 != r0) goto L_0x0402
            r17 = 600(0x258, float:8.41E-43)
            r0 = r17
            if (r10 != r0) goto L_0x0402
            r17 = 2
            HostResSet(r17)
            r17 = 0
            r18 = 0
            r19 = 0
            SetCamType(r17, r18, r19)
            com.ts.canview.CanItemMsgBox r15 = new com.ts.canview.CanItemMsgBox
            r17 = 4
            int r18 = com.ts.MainUI.R.string.can_car_select_tip
            r0 = r17
            r1 = r27
            r2 = r18
            r3 = r27
            r15.<init>(r0, r1, r2, r3)
            goto L_0x028f
        L_0x0402:
            r17 = 1280(0x500, float:1.794E-42)
            r0 = r17
            if (r5 != r0) goto L_0x042f
            r17 = 480(0x1e0, float:6.73E-43)
            r0 = r17
            if (r10 != r0) goto L_0x042f
            r17 = 3
            HostResSet(r17)
            r17 = 0
            r18 = 0
            r19 = 0
            SetCamType(r17, r18, r19)
            com.ts.canview.CanItemMsgBox r15 = new com.ts.canview.CanItemMsgBox
            r17 = 4
            int r18 = com.ts.MainUI.R.string.can_car_select_tip
            r0 = r17
            r1 = r27
            r2 = r18
            r3 = r27
            r15.<init>(r0, r1, r2, r3)
            goto L_0x028f
        L_0x042f:
            r17 = 480(0x1e0, float:6.73E-43)
            r0 = r17
            if (r5 != r0) goto L_0x045c
            r17 = 240(0xf0, float:3.36E-43)
            r0 = r17
            if (r10 != r0) goto L_0x045c
            r17 = 4
            HostResSet(r17)
            r17 = 0
            r18 = 0
            r19 = 0
            SetCamType(r17, r18, r19)
            com.ts.canview.CanItemMsgBox r15 = new com.ts.canview.CanItemMsgBox
            r17 = 4
            int r18 = com.ts.MainUI.R.string.can_car_select_tip
            r0 = r17
            r1 = r27
            r2 = r18
            r3 = r27
            r15.<init>(r0, r1, r2, r3)
            goto L_0x028f
        L_0x045c:
            r17 = 480(0x1e0, float:6.73E-43)
            r0 = r17
            if (r5 != r0) goto L_0x0489
            r17 = 270(0x10e, float:3.78E-43)
            r0 = r17
            if (r10 != r0) goto L_0x0489
            r17 = 5
            HostResSet(r17)
            r17 = 0
            r18 = 0
            r19 = 0
            SetCamType(r17, r18, r19)
            com.ts.canview.CanItemMsgBox r15 = new com.ts.canview.CanItemMsgBox
            r17 = 4
            int r18 = com.ts.MainUI.R.string.can_car_select_tip
            r0 = r17
            r1 = r27
            r2 = r18
            r3 = r27
            r15.<init>(r0, r1, r2, r3)
            goto L_0x028f
        L_0x0489:
            int r17 = r7 + r8
            int r17 = r17 + r6
            int r17 = r17 + r5
            r0 = r17
            if (r9 != r0) goto L_0x04d2
            r17 = 50
            r0 = r17
            if (r9 < r0) goto L_0x04d2
            int r17 = r12 + r13
            int r17 = r17 + r11
            int r17 = r17 + r10
            r0 = r17
            if (r14 != r0) goto L_0x04d2
            r17 = 50
            r0 = r17
            if (r14 < r0) goto L_0x04d2
            android.content.res.Resources r17 = r27.getResources()
            int r18 = com.ts.MainUI.R.string.can_Resolution_auto
            java.lang.String r16 = r17.getString(r18)
            r17 = 12
            HostResSet(r17)
            r17 = 1
            r0 = r17
            SetCamType(r0, r5, r10)
            com.ts.canview.CanItemMsgBox r15 = new com.ts.canview.CanItemMsgBox
            r17 = 4
            int r18 = com.ts.MainUI.R.string.can_car_select_tip
            r0 = r17
            r1 = r27
            r2 = r18
            r3 = r27
            r15.<init>(r0, r1, r2, r3)
            goto L_0x028f
        L_0x04d2:
            android.content.res.Resources r17 = r27.getResources()
            int r18 = com.ts.MainUI.R.string.can_Invalid_resolution
            java.lang.String r16 = r17.getString(r18)
            goto L_0x028f
        L_0x04de:
            android.content.res.Resources r17 = r27.getResources()
            int r18 = com.ts.MainUI.R.string.can_Invalid_resolution
            java.lang.String r16 = r17.getString(r18)
            goto L_0x028f
        L_0x04ea:
            int r17 = m_ResAutoCnt
            if (r17 != 0) goto L_0x037a
            com.ts.canview.CanTimerMsgBox r17 = new com.ts.canview.CanTimerMsgBox
            r0 = r27
            com.ts.canview.CanScrollList r0 = r0.mManager
            r18 = r0
            android.widget.LinearLayout r18 = r18.getLayout()
            android.content.Context r18 = r18.getContext()
            r19 = 3000(0xbb8, double:1.482E-320)
            int r21 = com.ts.MainUI.R.string.can_defeat
            r17.<init>((android.content.Context) r18, (long) r19, (int) r21)
            goto L_0x037a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ts.can.gm.xt5.CanCadillacXt5CarFuncActivity.ResetData(boolean):void");
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
        if (CanFunc.getInstance().IsCore() == 1) {
            return;
        }
        if (FtSet.Getlgb1() == 2) {
            this.mItemRCamera.ShowGone(false);
        } else {
            this.mItemRCamera.ShowGone(true);
        }
    }

    public static int RCamera() {
        return FtSet.Getlgb2() & 15;
    }

    public static int RvsMode() {
        return FtSet.Getlgb1() & 15;
    }

    public static int NaviPre() {
        return (FtSet.Getyw5() & 65280) >> 8;
    }

    public static int HostRes() {
        return FtSet.Getlgb5();
    }

    public static int IsLvdsType() {
        return (FtSet.Getyw5() & 6) >> 1;
    }

    public static int HostResSet(int num) {
        FtSet.Setlgb5(num);
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
            x = 480;
            y = 270;
        } else if (HostRes() == 12 && fore > 0) {
            x = px;
            y = py;
            if (x < 100 || y < 100) {
                return 0;
            }
        }
        if (RvsMode() == 0) {
            BackcarService.getInstance().SetCamType(5, x, y);
        } else if (RvsMode() == 2) {
            BackcarService.getInstance().SetCamType(3, x, y);
        } else if (RvsMode() == 3) {
            BackcarService.getInstance().SetCamType(4, x, y);
        } else {
            BackcarService.getInstance().SetCamType(0, x, y);
        }
        return 1;
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                if (FtSet.Getlgb2() > 0) {
                    FtSet.Setlgb2(0);
                    Mcu.SendXKey(40);
                    return;
                }
                FtSet.Setlgb2(1);
                Mcu.SendXKey(41);
                return;
            case 2:
                if (FtSet.Getlgb4() > 0) {
                    FtSet.Setlgb4(0);
                    return;
                } else {
                    FtSet.Setlgb4(1);
                    return;
                }
            case 5:
                m_ResAutoCnt = 100;
                Mcu.ReqOrgTiming(1);
                return;
            default:
                return;
        }
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                if (CanFunc.getInstance().IsCore() == 1) {
                    if (item == 0) {
                        FtSet.Setlgb1(0);
                        Mcu.SendXKey(34);
                    } else if (item == 1) {
                        FtSet.Setlgb1(1);
                        Mcu.SendXKey(33);
                    } else if (item == 2) {
                        FtSet.Setlgb1(2);
                        Mcu.SendXKey(35);
                    } else if (item == 3) {
                        FtSet.Setlgb1(3);
                        Mcu.SendXKey(36);
                        CanCameraUI.GetInstance().nLayoutReLoad = 1;
                    }
                    SetCamType(0, 0, 0);
                    if (CanFunc.getInstance().IsCore() != 1) {
                        return;
                    }
                    if (RvsMode() == 2) {
                        this.mItemRCamera.ShowGone(false);
                        Mcu.SendXKey(40);
                        return;
                    }
                    this.mItemRCamera.ShowGone(true);
                    if (RCamera() > 0) {
                        Mcu.SendXKey(41);
                        return;
                    } else {
                        Mcu.SendXKey(40);
                        return;
                    }
                } else if (item == 0) {
                    this.mItemRCamera.ShowGone(true);
                    FtSet.Setlgb1(0);
                    Mcu.SendXKey(34);
                    return;
                } else if (item == 1) {
                    this.mItemRCamera.ShowGone(true);
                    FtSet.Setlgb1(1);
                    Mcu.SendXKey(33);
                    return;
                } else if (item == 2) {
                    FtSet.Setlgb1(2);
                    Mcu.SendXKey(35);
                    Sleep(10);
                    FtSet.Setlgb2(0);
                    Mcu.SendXKey(40);
                    this.mItemRCamera.ShowGone(false);
                    return;
                } else {
                    return;
                }
            case 4:
                FtSet.Setlgb5(item);
                Mcu.SendXKey(FtSet.Getlgb5() + 50);
                SetCamType(0, 0, 0);
                if (CanFunc.getInstance().IsCore() == 1) {
                    new CanItemMsgBox(4, this, R.string.can_car_select_tip, this);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
        switch (id) {
            case 3:
                FtSet.Setyw5((pos << 8) | (FtSet.Getyw5() & 255));
                if (Evc.GetInstance() != null && Iop.GetWorkMode() == 12) {
                    Evc.GetInstance().SetNaviVolDn(CanBencWithCDCarFuncActivity.BencZmytNaviPre());
                    return;
                }
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
    }

    public void onOK(int param) {
        switch (param) {
            case 4:
                Mcu.SendXKey(20);
                return;
            default:
                return;
        }
    }
}
