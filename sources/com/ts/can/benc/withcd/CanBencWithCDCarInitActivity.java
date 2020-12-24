package com.ts.can.benc.withcd;

import android.content.Context;
import android.content.Intent;
import android.support.v4.internal.view.SupportMenu;
import android.view.View;
import android.widget.RelativeLayout;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.backcar.BackcarService;
import com.ts.can.CanCameraUI;
import com.ts.can.CanCommonActivity;
import com.ts.can.CanFunc;
import com.ts.can.MyApplication;
import com.ts.can.toyota.dj.CanToyotaDJCarDeviceView;
import com.ts.canview.CanItemBlankTextList;
import com.ts.canview.CanItemMsgBox;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemProgressList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanItemTextBtnList;
import com.ts.canview.CanScrollList;
import com.ts.factoryset.FsCanActivity;
import com.ts.main.common.MainSet;
import com.ts.other.CustomTextView;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.Mcu;

public class CanBencWithCDCarInitActivity extends CanCommonActivity implements CanItemPopupList.onPopItemClick, CanItemProgressList.onPosChange, CanItemMsgBox.onMsgBoxClick {
    private static final int ITEM_AUDIO_H = 10;
    private static final int ITEM_AUDIO_L = 9;
    private static final int ITEM_AUDIO_LOAD = 8;
    private static final int ITEM_AUX_ACTIVE = 1;
    private static final int ITEM_CAR_AUX = 11;
    private static final int ITEM_CAR_DOOR_UI = 7;
    private static final int ITEM_CAR_NAVI = 0;
    private static final int ITEM_HOST_RES = 2;
    private static final int ITEM_LVDS_TYPE = 13;
    private static final int ITEM_METHOD = 6;
    private static final int ITEM_RADIO_UI1 = 3;
    private static final int ITEM_RADIO_UI2 = 4;
    private static final int ITEM_RES_AUTO = 12;
    private static final int ITEM_USB_NUM = 5;
    static byte[] data = new byte[25];
    private static int m_AudioLoadb = 0;
    private static int m_Audiohb = 0;
    private static int m_Audiolb = 0;
    private static int m_Auxb = 0;
    private static int m_CarNavib = 0;
    private static int m_Connectb = 0;
    private static int m_DoorUIb = 0;
    private static int m_HostResb = 0;
    private static int m_HostUsbb = 0;
    private static int m_LvdsTypeb = 0;
    private static int m_Methodb = 0;
    private static int m_RadioUib = 0;
    private static int m_ResAutoCnt = 0;
    private String[] mAudioLoadArr = {"AUX", "TV", "BT"};
    private String[] mAuidoHArr = {"0", MainSet.SP_XPH5, MainSet.SP_RLF_KORON, MainSet.SP_XH_DMAX, MainSet.SP_KS_QOROS, MainSet.SP_LM_WR, MainSet.SP_YSJ_QP, MainSet.SP_TW_CJW, MainSet.SP_FLKJ, MainSet.SP_FXCARPLAY, MainSet.SP_XH_FORD, "11", "12", "13", "14", "15"};
    private String[] mAuidoLArr = {"0", MainSet.SP_XPH5, MainSet.SP_RLF_KORON, MainSet.SP_XH_DMAX, MainSet.SP_KS_QOROS, MainSet.SP_LM_WR, MainSet.SP_YSJ_QP, MainSet.SP_TW_CJW, MainSet.SP_FLKJ};
    protected ParamButton[] mBtnRadio = new ParamButton[2];
    private String[] mCarDoorUIArr;
    private CanItemPopupList mItemAudioH;
    private CanItemPopupList mItemAudioL;
    private CanItemPopupList mItemAudioLoad;
    private CanItemTextBtnList mItemAuxActive;
    private CanItemSwitchList mItemCarAux;
    private CanItemPopupList mItemCarDoorUI;
    private CanItemSwitchList mItemCarNavi;
    private CanItemPopupList mItemHostRes;
    private CanItemPopupList mItemHostUsb;
    private CanItemPopupList mItemLvdsType;
    private CanItemPopupList mItemMethod;
    private CanItemTextBtnList mItemResAuto;
    private String[] mLvdsTypeArr = {"Jedia", "Vesa"};
    private CanScrollList mManager;
    private RelativeLayoutManager mManager2;
    private String[] mMethodbArr = {"USB", "AMP", "BT"};
    private String[] mRes8259Arr = {"800x480", "400x240", "1024x600", "--", "--", "--", "--", "--", "--", "1920x720", "--", "--", "A"};
    private String[] mResArr = {MainSet.SP_XPH5, MainSet.SP_RLF_KORON, MainSet.SP_XH_DMAX, MainSet.SP_KS_QOROS, MainSet.SP_LM_WR, MainSet.SP_YSJ_QP, MainSet.SP_TW_CJW, MainSet.SP_FLKJ, MainSet.SP_FXCARPLAY, MainSet.SP_XH_FORD, "11", "12", "13"};
    protected CustomTextView mTextRadioUI;
    private String[] mUsbNumArr = {MainSet.SP_XPH5, MainSet.SP_RLF_KORON};

    /* access modifiers changed from: protected */
    public int GetContentLayoutId() {
        return R.layout.activity_can_comm_list;
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mManager = new CanScrollList(this);
        RelativeLayout item = new RelativeLayout(this);
        this.mManager.AddView(item, -1, -2);
        RelativeLayoutManager mManager22 = new RelativeLayoutManager(item);
        this.mTextRadioUI = mManager22.AddCusText(50, 0, 1249, 80);
        this.mTextRadioUI.setText(R.string.can_host_radio_ui);
        this.mTextRadioUI.SetPxSize(45);
        this.mTextRadioUI.setGravity(19);
        this.mBtnRadio[0] = mManager22.AddButton(CanCameraUI.BTN_TRUMPCHI_GS4_MODE1, 0, 377, 121);
        this.mBtnRadio[0].setTag(3);
        this.mBtnRadio[0].setOnClickListener(this);
        this.mBtnRadio[0].setDrawable(R.drawable.main_radio01_icon_up, R.drawable.main_radio01_icon_dn);
        this.mBtnRadio[1] = mManager22.AddButton(800, 0, 377, 121);
        this.mBtnRadio[1].setTag(4);
        this.mBtnRadio[1].setOnClickListener(this);
        this.mBtnRadio[1].setDrawable(R.drawable.main_radio02_icon_up, R.drawable.main_radio02_icon_dn);
        this.mCarDoorUIArr = new String[]{getResources().getString(R.string.can_benc_car), getResources().getString(R.string.can_benc_suv), getResources().getString(R.string.can_benc_sprot), getResources().getString(R.string.can_benc_g_ver), getResources().getString(R.string.can_benc_v_ver), getResources().getString(R.string.can_benc_landrover_ver), getResources().getString(R.string.can_benc_brex), getResources().getString(R.string.can_benc_a_ver)};
        this.mItemCarNavi = AddCheckItem(R.string.can_host_with_navi, 0);
        this.mItemCarAux = AddCheckItem(R.string.can_host_with_aux, 11);
        this.mItemAuxActive = AddTextBtn(R.string.can_aux_active, 1);
        this.mItemHostUsb = AddPopupItem(R.string.can_host_usb, this.mUsbNumArr, 5);
        this.mItemResAuto = AddTextBtn(R.string.can_Resolution_adaptive, 12);
        this.mItemLvdsType = AddPopupItem(R.string.can_lvds_type, this.mLvdsTypeArr, 13);
        if (CanFunc.getInstance().IsCore() != 1) {
            this.mItemHostRes = AddPopupItem(R.string.can_host_res, this.mResArr, 2);
        } else {
            this.mItemHostRes = AddPopupItem(R.string.can_host_res, this.mRes8259Arr, 2);
        }
        this.mItemMethod = AddPopupItem(R.string.can_benc_car_audio_chg_method, this.mMethodbArr, 6);
        this.mItemCarDoorUI = AddPopupItem(R.string.can_door_ui, this.mCarDoorUIArr, 7);
        this.mItemAudioLoad = AddPopupItem(R.string.can_connect_method, this.mAudioLoadArr, 8);
        this.mItemAudioL = AddPopupItem(R.string.can_benc_auido_l, this.mAuidoLArr, 9);
        this.mItemAudioH = AddPopupItem(R.string.can_benc_auido_h, this.mAuidoHArr, 10);
        if (CanFunc.getInstance().IsCore() != 1) {
            this.mItemResAuto.ShowGone(false);
            this.mItemLvdsType.ShowGone(false);
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
    public CanItemBlankTextList AddTitleItem(int resId) {
        CanItemBlankTextList item = new CanItemBlankTextList((Context) this, resId);
        this.mManager.AddView(item.GetView());
        return item;
    }

    /* access modifiers changed from: protected */
    public ParamButton AddBtn(int id, int x, int y, int w, int h, int up, int dn) {
        ParamButton btn = this.mManager2.AddButton(x, y, w, h);
        btn.setTag(Integer.valueOf(id));
        btn.setOnClickListener(this);
        btn.setDrawable(up, dn);
        return btn;
    }

    private CanItemTextBtnList AddTextBtn(int text, int id) {
        CanItemTextBtnList btn = new CanItemTextBtnList((Context) this, text);
        btn.SetIdClickListener(this, id);
        this.mManager.AddView(btn.GetView());
        btn.ShowGone(true);
        return btn;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v26, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v30, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v34, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v38, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v46, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v50, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v54, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v58, resolved type: byte} */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void ResetData(boolean r28) {
        /*
            r27 = this;
            int r17 = m_CarNavib
            int r18 = com.yyw.ts70xhw.FtSet.Getlgb3()
            r18 = r18 & 1
            r0 = r17
            r1 = r18
            if (r0 != r1) goto L_0x0010
            if (r28 != 0) goto L_0x0023
        L_0x0010:
            int r17 = com.yyw.ts70xhw.FtSet.Getlgb3()
            r17 = r17 & 1
            m_CarNavib = r17
            r0 = r27
            com.ts.canview.CanItemSwitchList r0 = r0.mItemCarNavi
            r17 = r0
            int r18 = m_CarNavib
            r17.SetCheck((int) r18)
        L_0x0023:
            int r17 = m_RadioUib
            int r18 = com.yyw.ts70xhw.FtSet.Getlgb3()
            r18 = r18 & 2
            int r18 = r18 >> 1
            r0 = r17
            r1 = r18
            if (r0 != r1) goto L_0x0035
            if (r28 != 0) goto L_0x00a7
        L_0x0035:
            int r17 = com.yyw.ts70xhw.FtSet.Getlgb3()
            r17 = r17 & 2
            int r17 = r17 >> 1
            m_RadioUib = r17
            int r17 = m_RadioUib
            if (r17 != 0) goto L_0x04de
            r0 = r27
            com.ts.other.ParamButton[] r0 = r0.mBtnRadio
            r17 = r0
            r18 = 0
            r17 = r17[r18]
            int r18 = com.ts.MainUI.R.drawable.main_radio01_icon_dn
            int r19 = com.ts.MainUI.R.drawable.main_radio01_icon_dn
            r17.setDrawable(r18, r19)
            r0 = r27
            com.ts.other.ParamButton[] r0 = r0.mBtnRadio
            r17 = r0
            r18 = 1
            r17 = r17[r18]
            int r18 = com.ts.MainUI.R.drawable.main_radio02_icon_up
            int r19 = com.ts.MainUI.R.drawable.main_radio02_icon_up
            r17.setDrawable(r18, r19)
            r0 = r27
            com.ts.canview.CanItemSwitchList r0 = r0.mItemCarNavi
            r17 = r0
            r18 = 1
            r17.ShowGone((boolean) r18)
            r0 = r27
            com.ts.canview.CanItemSwitchList r0 = r0.mItemCarAux
            r17 = r0
            r18 = 1
            r17.ShowGone((boolean) r18)
            r0 = r27
            com.ts.canview.CanItemTextBtnList r0 = r0.mItemAuxActive
            r17 = r0
            r18 = 1
            r17.ShowGone((boolean) r18)
            r0 = r27
            com.ts.canview.CanItemPopupList r0 = r0.mItemMethod
            r17 = r0
            r18 = 0
            r17.ShowGone((boolean) r18)
            r0 = r27
            com.ts.canview.CanItemPopupList r0 = r0.mItemHostUsb
            r17 = r0
            r18 = 0
            r17.ShowGone((boolean) r18)
            r0 = r27
            com.ts.canview.CanItemPopupList r0 = r0.mItemAudioLoad
            r17 = r0
            r18 = 1
            r17.ShowGone((boolean) r18)
        L_0x00a7:
            int r17 = m_HostUsbb
            int r18 = com.yyw.ts70xhw.FtSet.Getlgb3()
            r18 = r18 & 48
            int r18 = r18 >> 4
            r0 = r17
            r1 = r18
            if (r0 != r1) goto L_0x00b9
            if (r28 != 0) goto L_0x00ce
        L_0x00b9:
            int r17 = com.yyw.ts70xhw.FtSet.Getlgb3()
            r17 = r17 & 48
            int r17 = r17 >> 4
            m_HostUsbb = r17
            r0 = r27
            com.ts.canview.CanItemPopupList r0 = r0.mItemHostUsb
            r17 = r0
            int r18 = m_HostUsbb
            r17.SetSel(r18)
        L_0x00ce:
            int r17 = m_DoorUIb
            int r18 = com.yyw.ts70xhw.FtSet.Getlgb3()
            r19 = 61440(0xf000, float:8.6096E-41)
            r18 = r18 & r19
            int r18 = r18 >> 12
            r0 = r17
            r1 = r18
            if (r0 != r1) goto L_0x00e3
            if (r28 != 0) goto L_0x00fb
        L_0x00e3:
            int r17 = com.yyw.ts70xhw.FtSet.Getlgb3()
            r18 = 61440(0xf000, float:8.6096E-41)
            r17 = r17 & r18
            int r17 = r17 >> 12
            m_DoorUIb = r17
            r0 = r27
            com.ts.canview.CanItemPopupList r0 = r0.mItemCarDoorUI
            r17 = r0
            int r18 = m_DoorUIb
            r17.SetSel(r18)
        L_0x00fb:
            int r17 = m_HostResb
            int r18 = com.yyw.ts70xhw.FtSet.Getlgb4()
            r0 = r17
            r1 = r18
            if (r0 != r1) goto L_0x0109
            if (r28 != 0) goto L_0x011c
        L_0x0109:
            int r17 = com.yyw.ts70xhw.FtSet.Getlgb4()
            m_HostResb = r17
            r0 = r27
            com.ts.canview.CanItemPopupList r0 = r0.mItemHostRes
            r17 = r0
            int r18 = com.yyw.ts70xhw.FtSet.Getlgb4()
            r17.SetSel(r18)
        L_0x011c:
            int r17 = m_Methodb
            int r18 = com.yyw.ts70xhw.FtSet.Getlgb2()
            r19 = 61440(0xf000, float:8.6096E-41)
            r18 = r18 & r19
            int r18 = r18 >> 12
            r0 = r17
            r1 = r18
            if (r0 != r1) goto L_0x0131
            if (r28 != 0) goto L_0x0149
        L_0x0131:
            int r17 = com.yyw.ts70xhw.FtSet.Getlgb2()
            r18 = 61440(0xf000, float:8.6096E-41)
            r17 = r17 & r18
            int r17 = r17 >> 12
            m_Methodb = r17
            r0 = r27
            com.ts.canview.CanItemPopupList r0 = r0.mItemMethod
            r17 = r0
            int r18 = m_Methodb
            r17.SetSel(r18)
        L_0x0149:
            int r17 = m_AudioLoadb
            int r18 = com.yyw.ts70xhw.FtSet.Getlgb3()
            r18 = r18 & 12
            int r18 = r18 >> 2
            r0 = r17
            r1 = r18
            if (r0 != r1) goto L_0x015b
            if (r28 != 0) goto L_0x0170
        L_0x015b:
            int r17 = com.yyw.ts70xhw.FtSet.Getlgb3()
            r17 = r17 & 12
            int r17 = r17 >> 2
            m_AudioLoadb = r17
            r0 = r27
            com.ts.canview.CanItemPopupList r0 = r0.mItemAudioLoad
            r17 = r0
            int r18 = m_AudioLoadb
            r17.SetSel(r18)
        L_0x0170:
            int r17 = m_Audiolb
            int r18 = com.yyw.ts70xhw.FtSet.Getlgb5()
            r19 = 61440(0xf000, float:8.6096E-41)
            r18 = r18 & r19
            int r18 = r18 >> 12
            r0 = r17
            r1 = r18
            if (r0 != r1) goto L_0x0185
            if (r28 != 0) goto L_0x019d
        L_0x0185:
            int r17 = com.yyw.ts70xhw.FtSet.Getlgb5()
            r18 = 61440(0xf000, float:8.6096E-41)
            r17 = r17 & r18
            int r17 = r17 >> 12
            m_Audiolb = r17
            r0 = r27
            com.ts.canview.CanItemPopupList r0 = r0.mItemAudioL
            r17 = r0
            int r18 = m_Audiolb
            r17.SetSel(r18)
        L_0x019d:
            int r17 = m_Audiohb
            int r18 = com.yyw.ts70xhw.FtSet.Getlgb5()
            r0 = r18
            r0 = r0 & 3840(0xf00, float:5.381E-42)
            r18 = r0
            int r18 = r18 >> 8
            r0 = r17
            r1 = r18
            if (r0 != r1) goto L_0x01b3
            if (r28 != 0) goto L_0x01cc
        L_0x01b3:
            int r17 = com.yyw.ts70xhw.FtSet.Getlgb5()
            r0 = r17
            r0 = r0 & 3840(0xf00, float:5.381E-42)
            r17 = r0
            int r17 = r17 >> 8
            m_Audiohb = r17
            r0 = r27
            com.ts.canview.CanItemPopupList r0 = r0.mItemAudioH
            r17 = r0
            int r18 = m_Audiohb
            r17.SetSel(r18)
        L_0x01cc:
            int r17 = m_Auxb
            int r18 = com.yyw.ts70xhw.FtSet.Getlgb3()
            r0 = r18
            r0 = r0 & 768(0x300, float:1.076E-42)
            r18 = r0
            int r18 = r18 >> 8
            r0 = r17
            r1 = r18
            if (r0 != r1) goto L_0x01e2
            if (r28 != 0) goto L_0x01fb
        L_0x01e2:
            int r17 = com.yyw.ts70xhw.FtSet.Getlgb3()
            r0 = r17
            r0 = r0 & 768(0x300, float:1.076E-42)
            r17 = r0
            int r17 = r17 >> 8
            m_Auxb = r17
            r0 = r27
            com.ts.canview.CanItemSwitchList r0 = r0.mItemCarAux
            r17 = r0
            int r18 = m_Auxb
            r17.SetCheck((int) r18)
        L_0x01fb:
            int r17 = m_LvdsTypeb
            int r18 = com.yyw.ts70xhw.FtSet.Getyw5()
            int r18 = r18 >> 1
            r18 = r18 & 3
            r0 = r17
            r1 = r18
            if (r0 != r1) goto L_0x020d
            if (r28 != 0) goto L_0x0222
        L_0x020d:
            int r17 = com.yyw.ts70xhw.FtSet.Getyw5()
            int r17 = r17 >> 1
            r17 = r17 & 3
            m_LvdsTypeb = r17
            r0 = r27
            com.ts.canview.CanItemPopupList r0 = r0.mItemLvdsType
            r17 = r0
            int r18 = m_LvdsTypeb
            r17.SetSel(r18)
        L_0x0222:
            int r17 = m_ResAutoCnt
            if (r17 <= 0) goto L_0x04dd
            int r17 = m_ResAutoCnt
            int r17 = r17 + -1
            m_ResAutoCnt = r17
            byte[] r17 = data
            int r17 = com.yyw.ts70xhw.Mcu.GetOrgTiming(r17)
            if (r17 <= 0) goto L_0x062c
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
            if (r17 <= 0) goto L_0x0620
            android.content.res.Resources r17 = r27.getResources()
            int r18 = com.ts.MainUI.R.string.can_win
            java.lang.String r16 = r17.getString(r18)
            r17 = 800(0x320, float:1.121E-42)
            r0 = r17
            if (r5 != r0) goto L_0x0544
            r17 = 480(0x1e0, float:6.73E-43)
            r0 = r17
            if (r10 != r0) goto L_0x0544
            r17 = 0
            HostResSet(r17)
            r17 = 0
            r18 = 0
            r19 = 0
            SetCamType(r17, r18, r19)
            com.ts.canview.CanItemMsgBox r15 = new com.ts.canview.CanItemMsgBox
            r17 = 2
            int r18 = com.ts.MainUI.R.string.can_car_select_tip
            r0 = r17
            r1 = r27
            r2 = r18
            r3 = r27
            r15.<init>(r0, r1, r2, r3)
        L_0x03f3:
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
        L_0x04dd:
            return
        L_0x04de:
            r0 = r27
            com.ts.other.ParamButton[] r0 = r0.mBtnRadio
            r17 = r0
            r18 = 0
            r17 = r17[r18]
            int r18 = com.ts.MainUI.R.drawable.main_radio01_icon_up
            int r19 = com.ts.MainUI.R.drawable.main_radio01_icon_up
            r17.setDrawable(r18, r19)
            r0 = r27
            com.ts.other.ParamButton[] r0 = r0.mBtnRadio
            r17 = r0
            r18 = 1
            r17 = r17[r18]
            int r18 = com.ts.MainUI.R.drawable.main_radio02_icon_dn
            int r19 = com.ts.MainUI.R.drawable.main_radio02_icon_dn
            r17.setDrawable(r18, r19)
            r0 = r27
            com.ts.canview.CanItemSwitchList r0 = r0.mItemCarNavi
            r17 = r0
            r18 = 0
            r17.ShowGone((boolean) r18)
            r0 = r27
            com.ts.canview.CanItemSwitchList r0 = r0.mItemCarAux
            r17 = r0
            r18 = 0
            r17.ShowGone((boolean) r18)
            r0 = r27
            com.ts.canview.CanItemTextBtnList r0 = r0.mItemAuxActive
            r17 = r0
            r18 = 0
            r17.ShowGone((boolean) r18)
            r0 = r27
            com.ts.canview.CanItemPopupList r0 = r0.mItemMethod
            r17 = r0
            r18 = 1
            r17.ShowGone((boolean) r18)
            r0 = r27
            com.ts.canview.CanItemPopupList r0 = r0.mItemHostUsb
            r17 = r0
            r18 = 1
            r17.ShowGone((boolean) r18)
            r0 = r27
            com.ts.canview.CanItemPopupList r0 = r0.mItemAudioLoad
            r17 = r0
            r18 = 0
            r17.ShowGone((boolean) r18)
            goto L_0x00a7
        L_0x0544:
            r17 = 400(0x190, float:5.6E-43)
            r0 = r17
            if (r5 != r0) goto L_0x0571
            r17 = 240(0xf0, float:3.36E-43)
            r0 = r17
            if (r10 != r0) goto L_0x0571
            r17 = 1
            HostResSet(r17)
            r17 = 0
            r18 = 0
            r19 = 0
            SetCamType(r17, r18, r19)
            com.ts.canview.CanItemMsgBox r15 = new com.ts.canview.CanItemMsgBox
            r17 = 2
            int r18 = com.ts.MainUI.R.string.can_car_select_tip
            r0 = r17
            r1 = r27
            r2 = r18
            r3 = r27
            r15.<init>(r0, r1, r2, r3)
            goto L_0x03f3
        L_0x0571:
            r17 = 1920(0x780, float:2.69E-42)
            r0 = r17
            if (r5 != r0) goto L_0x059e
            r17 = 720(0x2d0, float:1.009E-42)
            r0 = r17
            if (r10 != r0) goto L_0x059e
            r17 = 9
            HostResSet(r17)
            r17 = 0
            r18 = 0
            r19 = 0
            SetCamType(r17, r18, r19)
            com.ts.canview.CanItemMsgBox r15 = new com.ts.canview.CanItemMsgBox
            r17 = 2
            int r18 = com.ts.MainUI.R.string.can_car_select_tip
            r0 = r17
            r1 = r27
            r2 = r18
            r3 = r27
            r15.<init>(r0, r1, r2, r3)
            goto L_0x03f3
        L_0x059e:
            r17 = 1024(0x400, float:1.435E-42)
            r0 = r17
            if (r5 != r0) goto L_0x05cb
            r17 = 600(0x258, float:8.41E-43)
            r0 = r17
            if (r10 != r0) goto L_0x05cb
            r17 = 2
            HostResSet(r17)
            r17 = 0
            r18 = 0
            r19 = 0
            SetCamType(r17, r18, r19)
            com.ts.canview.CanItemMsgBox r15 = new com.ts.canview.CanItemMsgBox
            r17 = 2
            int r18 = com.ts.MainUI.R.string.can_car_select_tip
            r0 = r17
            r1 = r27
            r2 = r18
            r3 = r27
            r15.<init>(r0, r1, r2, r3)
            goto L_0x03f3
        L_0x05cb:
            int r17 = r7 + r8
            int r17 = r17 + r6
            int r17 = r17 + r5
            r0 = r17
            if (r9 != r0) goto L_0x0614
            r17 = 50
            r0 = r17
            if (r9 < r0) goto L_0x0614
            int r17 = r12 + r13
            int r17 = r17 + r11
            int r17 = r17 + r10
            r0 = r17
            if (r14 != r0) goto L_0x0614
            r17 = 50
            r0 = r17
            if (r14 < r0) goto L_0x0614
            android.content.res.Resources r17 = r27.getResources()
            int r18 = com.ts.MainUI.R.string.can_Resolution_auto
            java.lang.String r16 = r17.getString(r18)
            r17 = 12
            HostResSet(r17)
            r17 = 1
            r0 = r17
            SetCamType(r0, r5, r10)
            com.ts.canview.CanItemMsgBox r15 = new com.ts.canview.CanItemMsgBox
            r17 = 2
            int r18 = com.ts.MainUI.R.string.can_car_select_tip
            r0 = r17
            r1 = r27
            r2 = r18
            r3 = r27
            r15.<init>(r0, r1, r2, r3)
            goto L_0x03f3
        L_0x0614:
            android.content.res.Resources r17 = r27.getResources()
            int r18 = com.ts.MainUI.R.string.can_Invalid_resolution
            java.lang.String r16 = r17.getString(r18)
            goto L_0x03f3
        L_0x0620:
            android.content.res.Resources r17 = r27.getResources()
            int r18 = com.ts.MainUI.R.string.can_Invalid_resolution
            java.lang.String r16 = r17.getString(r18)
            goto L_0x03f3
        L_0x062c:
            int r17 = m_ResAutoCnt
            if (r17 != 0) goto L_0x04dd
            com.ts.canview.CanTimerMsgBox r17 = new com.ts.canview.CanTimerMsgBox
            r0 = r27
            com.ts.canview.CanScrollList r0 = r0.mManager
            r18 = r0
            android.widget.LinearLayout r18 = r18.getLayout()
            android.content.Context r18 = r18.getContext()
            r19 = 3000(0xbb8, double:1.482E-320)
            int r21 = com.ts.MainUI.R.string.can_defeat
            r17.<init>((android.content.Context) r18, (long) r19, (int) r21)
            goto L_0x04dd
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ts.can.benc.withcd.CanBencWithCDCarInitActivity.ResetData(boolean):void");
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                if ((FtSet.Getlgb3() & 1) > 0) {
                    FtSet.Setlgb3(FtSet.Getlgb3() & 65534);
                    return;
                } else {
                    FtSet.Setlgb3(FtSet.Getlgb3() | 1);
                    return;
                }
            case 1:
                new CanItemMsgBox(1, this, R.string.can_sure_aux_active, this);
                return;
            case 3:
                FtSet.Setlgb3(FtSet.Getlgb3() & 65533);
                return;
            case 4:
                FtSet.Setlgb3((FtSet.Getlgb3() & 65533) | 2);
                return;
            case 11:
                if ((FtSet.Getlgb3() & CanToyotaDJCarDeviceView.ITEM_PLAY) > 0) {
                    FtSet.Setlgb3(FtSet.Getlgb3() & 64767);
                    return;
                } else {
                    FtSet.Setlgb3(FtSet.Getlgb3() | 256);
                    return;
                }
            case 12:
                m_ResAutoCnt = 100;
                Mcu.ReqOrgTiming(1);
                return;
            default:
                return;
        }
    }

    public static int IsWithNavi() {
        if ((FtSet.Getlgb3() & 1) > 0) {
            return 1;
        }
        return 0;
    }

    public static int UsbNum() {
        return (FtSet.Getlgb3() & 48) >> 4;
    }

    public static int HostRes() {
        return FtSet.Getlgb4();
    }

    public static int HostResSet(int num) {
        FtSet.Setlgb4(num);
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
            y = 240;
        } else if (HostRes() == 9) {
            x = 1920;
            y = 720;
        } else if (HostRes() == 2) {
            x = 1024;
            y = CanCameraUI.BTN_GOLF_WC_MODE1;
        } else if (HostRes() == 12 && fore > 0) {
            x = px;
            y = py;
        }
        if (CanBencWithCDCarFuncActivity.RvsMode() == 1) {
            BackcarService.getInstance().SetCamType(5, x, y);
        } else if (CanBencWithCDCarFuncActivity.RvsMode() == 2) {
            BackcarService.getInstance().SetCamType(0, x, y);
        } else {
            BackcarService.getInstance().SetCamType(0, x, y);
        }
        return 1;
    }

    public static int IsRviewDis() {
        if (HostRes() == 9) {
            return 1;
        }
        return 0;
    }

    public static int RadioUi() {
        return (FtSet.Getlgb3() & 2) >> 1;
    }

    public static int AudioMethod() {
        return (FtSet.Getlgb2() & 61440) >> 12;
    }

    public static int AudioLoad() {
        return (FtSet.Getlgb3() & 12) >> 2;
    }

    public static int AudioL() {
        return (FtSet.Getlgb5() & 61440) >> 12;
    }

    public static int AudioH() {
        return (FtSet.Getlgb5() & 3840) >> 8;
    }

    public static int IsWithAux() {
        return (FtSet.Getlgb3() & 256) >> 8;
    }

    public static int IsDoorUI() {
        return (FtSet.Getlgb3() & 61440) >> 12;
    }

    public static int IsLvdsType() {
        return (FtSet.Getyw5() & 6) >> 1;
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 2:
                FtSet.Setlgb4(item);
                Mcu.SendXKey(FtSet.Getlgb4() + 50);
                SetCamType(0, 0, 0);
                if (CanFunc.getInstance().IsCore() == 1) {
                    new CanItemMsgBox(2, this, R.string.can_car_select_tip, this);
                    return;
                }
                return;
            case 5:
                int temp = FtSet.Getlgb3() & 65487;
                if (item == 0) {
                    FtSet.Setlgb3(temp);
                    return;
                } else {
                    FtSet.Setlgb3(temp | 16);
                    return;
                }
            case 6:
                int temp2 = FtSet.Getlgb2() & FsCanActivity.DOOR_UPDATE_ALL;
                if (item == 0) {
                    FtSet.Setlgb2(temp2 & FsCanActivity.DOOR_UPDATE_ALL);
                    return;
                } else if (item == 1) {
                    FtSet.Setlgb2(temp2 | 4096);
                    return;
                } else if (item == 2) {
                    FtSet.Setlgb2(temp2 | 8192);
                    return;
                } else {
                    return;
                }
            case 7:
                int temp3 = FtSet.Getlgb3() & FsCanActivity.DOOR_UPDATE_ALL;
                if (item == 0) {
                    FtSet.Setlgb3(temp3);
                } else if (item == 1) {
                    FtSet.Setlgb3(temp3 | 4096);
                } else if (item == 2) {
                    FtSet.Setlgb3(temp3 | 8192);
                } else if (item == 3) {
                    FtSet.Setlgb3(temp3 | 12288);
                } else if (item == 4) {
                    FtSet.Setlgb3(temp3 | 16384);
                } else if (item == 5) {
                    FtSet.Setlgb3(temp3 | 20480);
                } else if (item == 6) {
                    FtSet.Setlgb3(temp3 | 24576);
                } else {
                    FtSet.Setlgb3(temp3 | 28672);
                }
                byte[] fileMsg = new byte[8];
                fileMsg[0] = (byte) FtSet.GetCanTp();
                fileMsg[1] = (byte) FtSet.GetCanSubT();
                fileMsg[2] = (byte) item;
                CanFunc.getInstance();
                CanFunc.sendFileCarInfo(fileMsg, CanFunc.Can_Factory_Set_fileName);
                Intent intent = new Intent("com.ts.can.BROADCAST_CAN_INFO");
                intent.putExtra("CAR_DOOR_UI", item);
                MyApplication.mContext.sendBroadcast(intent);
                return;
            case 8:
                int temp4 = FtSet.Getlgb3() & 65523;
                if (item == 0) {
                    FtSet.Setlgb3(temp4);
                    return;
                } else if (item == 1) {
                    FtSet.Setlgb3(temp4 | 4);
                    return;
                } else {
                    FtSet.Setlgb3(temp4 | 8);
                    return;
                }
            case 9:
                FtSet.Setlgb5((item << 12) | (FtSet.Getlgb5() & FsCanActivity.DOOR_UPDATE_ALL));
                return;
            case 10:
                FtSet.Setlgb5((item << 8) | (FtSet.Getlgb5() & 61695));
                return;
            case 13:
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

    /* access modifiers changed from: protected */
    public void QueryData() {
    }

    public void onOK(int param) {
        switch (param) {
            case 1:
                CanJni.BencZmytWithCDAuxActive(1, 0);
                return;
            case 2:
                Mcu.SendXKey(20);
                return;
            default:
                return;
        }
    }
}
