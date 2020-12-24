package com.ts.can.ford.wc;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.Evc;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanBaseActivity;
import com.ts.can.CanCameraUI;
import com.ts.can.CanFunc;
import com.ts.can.CanIF;
import com.ts.other.CustomImgView;
import com.ts.other.CustomTextView;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;
import com.yyw.ts70xhw.KeyDef;

public class CanFordWcSyncUIActivity extends CanBaseActivity implements UserCallBack, View.OnClickListener {
    public static final int ITEM_DLG_BTN0 = 37;
    public static final int ITEM_DLG_BTN1 = 38;
    public static final int ITEM_DLG_BTN2 = 39;
    public static final int ITEM_DLG_BTN3 = 40;
    public static final int ITEM_DLG_LINE0 = 32;
    public static final int ITEM_DLG_LINE1 = 33;
    public static final int ITEM_DLG_LINE2 = 34;
    public static final int ITEM_DLG_LINE3 = 35;
    public static final int ITEM_DLG_LINE4 = 36;
    public static final int ITEM_LT_KB = 4;
    public static final int ITEM_LT_MUSIC = 1;
    public static final int ITEM_LT_PHONE = 2;
    public static final int ITEM_LT_SPEECH = 3;
    public static final int ITEM_MENU_BTN0 = 21;
    public static final int ITEM_MENU_BTN1 = 22;
    public static final int ITEM_MENU_BTN2 = 23;
    public static final int ITEM_MENU_BTN3 = 24;
    public static final int ITEM_MENU_LINE0 = 16;
    public static final int ITEM_MENU_LINE1 = 17;
    public static final int ITEM_MENU_LINE2 = 18;
    public static final int ITEM_MENU_LINE3 = 19;
    public static final int ITEM_MENU_LINE4 = 20;
    public static final int ITEM_RT_BTN = 80;
    public static final String TAG = "CanFordWcSyncUIActivity";
    public static final int[] mDlgYArr = {218, 182, 144, 114, 84, 48};
    public static final int[] mIcoBatArr = {R.drawable.can_sync_wc_battery_0, R.drawable.can_sync_wc_battery_1, R.drawable.can_sync_wc_battery_2, R.drawable.can_sync_wc_battery_3, R.drawable.can_sync_wc_battery_4, R.drawable.can_sync_wc_battery_5};
    public static final int[] mIcoSigArr = {R.drawable.can_sync_wc_signal_0, R.drawable.can_sync_wc_signal_1, R.drawable.can_sync_wc_signal_2, R.drawable.can_sync_wc_signal_3, R.drawable.can_sync_wc_signal_4, R.drawable.can_sync_wc_signal_5};
    public static boolean mIsNeedFinish = false;
    public static boolean mIsSyncWin = false;
    protected ParamButton mBtnDn;
    protected ParamButton mBtnInfo;
    protected ParamButton mBtnLeft;
    protected ParamButton mBtnLtKeyboard;
    protected ParamButton mBtnLtMusic;
    protected ParamButton mBtnLtPhone;
    protected ParamButton mBtnLtSpeech;
    protected ParamButton mBtnNext;
    protected ParamButton[] mBtnNum = new ParamButton[10];
    protected ParamButton mBtnNumJ;
    protected ParamButton mBtnNumX;
    protected ParamButton mBtnOK;
    protected ParamButton mBtnPrev;
    protected ParamButton mBtnRight;
    protected ParamButton mBtnUp;
    protected long mDelayTime;
    private CanDataInfo.FordWcSyncText mIcon = new CanDataInfo.FordWcSyncText();
    private CanDataInfo.FordWcSyncText[] mKeys = new CanDataInfo.FordWcSyncText[4];
    protected RelativeLayout mLayoutMenu;
    private CanDataInfo.FordWcSyncText[] mLines = new CanDataInfo.FordWcSyncText[5];
    protected RelativeLayoutManager mManMenu;
    protected RelativeLayoutManager mManager;
    protected SyncSKey[] mMenuKey = new SyncSKey[4];
    protected SyncLine[] mMenuLine = new SyncLine[5];
    protected CustomImgView[] mScreenIcons = new CustomImgView[6];
    private CanDataInfo.FordWcSyncPlayInfo mSyncPlayInfo = new CanDataInfo.FordWcSyncPlayInfo();
    protected CustomTextView mSyncTime;
    private CanDataInfo.FordWcSyncUI mSyncUI = new CanDataInfo.FordWcSyncUI();
    protected int mX = Can.CAN_BENZ_SMART_OD;
    protected int mY = 48;
    protected boolean mfgShowKb;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (157 == CanJni.GetCanType()) {
            setContentView(R.layout.activity_can_comm_relative);
            InitUI();
            return;
        }
        Log.d(TAG, "-----NOT FORD PROTOCAL-----");
        finish();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        mIsNeedFinish = false;
        mIsSyncWin = false;
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        Evc.GetInstance().evol_workmode_set(12);
        ResetData(false);
        CanJni.FordWcSyncQuery(208, this.mSyncUI.ScreenNum, 0);
        mIsSyncWin = true;
        mIsNeedFinish = false;
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
        mIsSyncWin = false;
    }

    /* access modifiers changed from: protected */
    public ParamButton AddImgBtn(int id, int x, int y, int up, int dn) {
        ParamButton btn = this.mManager.AddButton(x, y);
        btn.setTag(Integer.valueOf(id));
        btn.setOnClickListener(this);
        btn.setStateUpDn(up, dn);
        return btn;
    }

    /* access modifiers changed from: protected */
    public ParamButton AddImgBtn(int id, int x, int y, int w, int h, int up, int dn) {
        ParamButton btn = this.mManager.AddButton(x, y, w, h);
        btn.setTag(Integer.valueOf(id));
        btn.setOnClickListener(this);
        btn.setStateUpDn(up, dn);
        return btn;
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mManager = new RelativeLayoutManager((RelativeLayout) findViewById(R.id.can_comm_layout));
        initCommonScreen();
        ShowRtBtn();
    }

    private void initCommonScreen() {
        this.mBtnLtMusic = AddImgBtn(1, 0, 0, R.drawable.can_sync_music_up, R.drawable.can_sync_music_dn);
        this.mBtnLtPhone = AddImgBtn(2, 0, Can.CAN_BENC_ZMYT, R.drawable.can_sync_phone_up, R.drawable.can_sync_phone_dn);
        this.mBtnLtSpeech = AddImgBtn(3, 0, 278, R.drawable.can_sync_mike_up, R.drawable.can_sync_mike_dn);
        this.mBtnLtKeyboard = AddImgBtn(4, 0, 415, R.drawable.can_sync_dial_up, R.drawable.can_sync_dial_dn);
        this.mManager.AddImage(706, 59, R.drawable.can_sync_right_box);
        this.mBtnPrev = AddImgBtn(90, 711, 394, R.drawable.can_sync_seekup_up, R.drawable.can_sync_seekup_dn);
        this.mBtnNext = AddImgBtn(91, 907, 394, R.drawable.can_sync_seekdn_up, R.drawable.can_sync_seekdn_dn);
        this.mBtnUp = AddImgBtn(92, KeyDef.SKEY_MODE_1, 69, R.drawable.can_sync_on_up, R.drawable.can_sync_on_dn);
        this.mBtnDn = AddImgBtn(93, KeyDef.SKEY_MODE_1, Can.CAN_BYD_S6_S7, R.drawable.can_sync_dn_up, R.drawable.can_sync_dn_dn);
        this.mBtnLeft = AddImgBtn(94, 711, 149, R.drawable.can_sync_left_up, R.drawable.can_sync_left_dn);
        this.mBtnRight = AddImgBtn(95, 907, 149, R.drawable.can_sync_right_up, R.drawable.can_sync_right_dn);
        this.mBtnOK = AddImgBtn(96, KeyDef.SKEY_MODE_1, 149, R.drawable.can_sync_ok_up, R.drawable.can_sync_ok_dn);
        this.mBtnInfo = AddImgBtn(99, KeyDef.SKEY_MODE_1, 394, R.drawable.can_sync_info_up, R.drawable.can_sync_info_dn);
        this.mBtnNum[1] = AddImgBtn(101, 711, 69, R.drawable.can_sync_num01_up, R.drawable.can_sync_num01_dn);
        this.mBtnNum[2] = AddImgBtn(102, KeyDef.SKEY_MODE_1, 69, R.drawable.can_sync_num02_up, R.drawable.can_sync_num02_dn);
        this.mBtnNum[3] = AddImgBtn(103, 907, 69, R.drawable.can_sync_num03_up, R.drawable.can_sync_num03_dn);
        this.mBtnNum[4] = AddImgBtn(104, 711, 149, R.drawable.can_sync_num04_up, R.drawable.can_sync_num04_dn);
        this.mBtnNum[5] = AddImgBtn(105, KeyDef.SKEY_MODE_1, 149, R.drawable.can_sync_num05_up, R.drawable.can_sync_num05_dn);
        this.mBtnNum[6] = AddImgBtn(106, 907, 149, R.drawable.can_sync_num06_up, R.drawable.can_sync_num06_dn);
        this.mBtnNum[7] = AddImgBtn(107, 711, Can.CAN_BYD_S6_S7, R.drawable.can_sync_num07_up, R.drawable.can_sync_num07_dn);
        this.mBtnNum[8] = AddImgBtn(108, KeyDef.SKEY_MODE_1, Can.CAN_BYD_S6_S7, R.drawable.can_sync_num08_up, R.drawable.can_sync_num08_dn);
        this.mBtnNum[9] = AddImgBtn(109, 907, Can.CAN_BYD_S6_S7, R.drawable.can_sync_num09_up, R.drawable.can_sync_num09_dn);
        this.mBtnNumX = AddImgBtn(112, 711, KeyDef.RKEY_CMMB_PBC, R.drawable.can_sync_aste_up, R.drawable.can_sync_aste_dn);
        this.mBtnNum[0] = AddImgBtn(100, KeyDef.SKEY_MODE_1, KeyDef.RKEY_CMMB_PBC, R.drawable.can_sync_num00_up, R.drawable.can_sync_num00_dn);
        this.mBtnNumJ = AddImgBtn(113, 907, KeyDef.RKEY_CMMB_PBC, R.drawable.can_sync_well_up, R.drawable.can_sync_well_dn);
        this.mScreenIcons[0] = this.mManager.AddImageEx(270, 10, 45, 44, 0);
        this.mScreenIcons[1] = this.mManager.AddImageEx(KeyDef.RKEY_RADIO_3S, 10, 45, 44, 0);
        this.mScreenIcons[2] = this.mManager.AddImageEx(380, 10, 45, 44, 0);
        this.mScreenIcons[3] = this.mManager.AddImageEx(435, 10, 45, 44, 0);
        this.mScreenIcons[4] = this.mManager.AddImageEx(490, 10, 45, 44, 0);
        this.mScreenIcons[5] = this.mManager.AddImageEx(CanCameraUI.BTN_NISSAN_XTRAL_RVS_ASSIST6, 10, 45, 44, 0);
        this.mSyncTime = this.mManager.AddCusText(754, 10, 200, 33);
        this.mSyncTime.setGravity(17);
        this.mSyncTime.SetPixelSize(28);
        this.mLayoutMenu = new RelativeLayout(this);
        this.mManager.AddView(this.mLayoutMenu, this.mX, this.mY, 705 - this.mX, 500 - this.mY);
        this.mManMenu = new RelativeLayoutManager(this.mLayoutMenu);
        for (int i = 0; i < 5; i++) {
            this.mMenuLine[i] = new SyncLine(this, i + 16, this);
            this.mManMenu.AddView(this.mMenuLine[i].GetView(), 237 - this.mX, ((i * 64) + 59) - this.mY, 441, 59);
        }
        for (int i2 = 0; i2 < 4; i2++) {
            this.mMenuKey[i2] = new SyncSKey(this, i2 + 21, this);
            this.mMenuKey[i2].Show(true);
            this.mManMenu.AddViewWrapContent(this.mMenuKey[i2].GetView(), ((i2 * 113) + Can.CAN_NISSAN_RICH6_WC) - this.mX, 392 - this.mY);
        }
    }

    /* access modifiers changed from: protected */
    public void ShowRtBtn() {
        boolean showMedia = !this.mfgShowKb;
        this.mBtnUp.Show(showMedia);
        this.mBtnDn.Show(showMedia);
        this.mBtnLeft.Show(showMedia);
        this.mBtnRight.Show(showMedia);
        this.mBtnOK.Show(showMedia);
        this.mBtnInfo.Show(showMedia);
        this.mBtnPrev.Show(showMedia);
        this.mBtnNext.Show(showMedia);
        for (int i = 0; i < 10; i++) {
            this.mBtnNum[i].Show(this.mfgShowKb);
        }
        this.mBtnNumX.Show(this.mfgShowKb);
        this.mBtnNumJ.Show(this.mfgShowKb);
    }

    private void UpdateUI(boolean check) {
        CanJni.FordWcGetSyncUI(this.mSyncUI);
        CanJni.FordWcGetSyncPlayInfo(this.mSyncPlayInfo);
        if (i2b(this.mSyncPlayInfo.UpdateOnce) && (!check || i2b(this.mSyncPlayInfo.Update))) {
            this.mSyncPlayInfo.Update = 0;
            this.mDelayTime = 100;
            this.mSyncTime.setVisibility(0);
            int time = this.mSyncPlayInfo.Time;
            int hour = time / 3600;
            this.mSyncTime.setText(String.format("%02d : %02d : %02d", new Object[]{Integer.valueOf(hour), Integer.valueOf((time - (hour * 3600)) / 60), Integer.valueOf(time % 60)}));
        }
        for (int i = 0; i < this.mLines.length; i++) {
            updateLine(check, i);
        }
        for (int i2 = 0; i2 < this.mKeys.length; i2++) {
            updateKey(check, i2);
        }
        CanJni.FordWcGetSyncText(this.mIcon, 9);
        if (!i2b(this.mIcon.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mIcon.Update)) {
            this.mIcon.Update = 0;
            int[] values = this.mIcon.szText1;
            for (int i3 = 0; i3 < values.length; i3++) {
                if (i3 < this.mScreenIcons.length) {
                    updateScreenIcon(i3, values[i3]);
                } else {
                    int index = (i3 - 6) / 2;
                    updateLineIcon(index, values[(index * 2) + 6], values[(index * 2) + 7], i3 % 2 == 0);
                }
            }
        }
    }

    private void updateLineIcon(int index, int value, int value2, boolean isLeft) {
        this.mMenuLine[index].SetSel(false);
        Log.d("HAHA", "updateLineIcon index = " + index + " --> value =" + value + " --> value2 =" + value2);
        if (value == 1 || value2 == 1) {
            this.mMenuLine[index].SetSel(true);
        }
        if (value == 3) {
            this.mMenuLine[index].SetIcon(R.drawable.can_sync_icon_76, true);
        } else if (value == 8) {
            this.mMenuLine[index].SetIcon(R.drawable.can_sync_icon_28, true);
        } else if (value == 9) {
            this.mMenuLine[index].SetIcon(R.drawable.can_sync_icon_27, true);
        } else {
            this.mMenuLine[index].SetIcon(0, true);
        }
        if (value2 == 3) {
            this.mMenuLine[index].SetIcon(R.drawable.can_sync_icon_76, false);
        } else if (value2 == 8) {
            this.mMenuLine[index].SetIcon(R.drawable.can_sync_icon_28, false);
        } else if (value2 == 9) {
            this.mMenuLine[index].SetIcon(R.drawable.can_sync_icon_27, false);
        } else {
            this.mMenuLine[index].SetIcon(0, false);
        }
    }

    private void updateScreenIcon(int index, int value) {
        Log.d("HAHA", "index = " + index + " --> value =" + value);
        if (value == 4) {
            this.mScreenIcons[index].setImageResource(R.drawable.can_sync_icon_cd);
        } else if (value == 5) {
            this.mScreenIcons[index].setImageResource(R.drawable.can_sync_icon_0b);
        } else if (value == 6) {
            this.mScreenIcons[index].setImageResource(R.drawable.can_sync_icon_24);
        } else if (value == 7) {
            this.mScreenIcons[index].setImageResource(R.drawable.can_sync_icon_11);
        } else if (value >= 16 && value <= 21) {
            this.mScreenIcons[index].setImageResource(mIcoBatArr[value - 16]);
        } else if (value < 32 || value > 37) {
            this.mScreenIcons[index].setImageResource(0);
        } else {
            this.mScreenIcons[index].setImageResource(mIcoSigArr[value - 32]);
        }
    }

    private void updateLine(boolean check, int index) {
        CanDataInfo.FordWcSyncText[] fordWcSyncTextArr = this.mLines;
        CanDataInfo.FordWcSyncText syncText = new CanDataInfo.FordWcSyncText();
        fordWcSyncTextArr[index] = syncText;
        CanJni.FordWcGetSyncText(syncText, index);
        byte[] c = new byte[48];
        if (!i2b(syncText.UpdateOnce)) {
            return;
        }
        if (!check || i2b(syncText.Update)) {
            syncText.Update = 0;
            boolean isEnd = false;
            for (int i = 0; i < syncText.szText1.length; i++) {
                if (i != syncText.szText1.length - 1) {
                    int cur = syncText.szText1[i];
                    int next = syncText.szText1[i + 1];
                    if (cur == 0 && next == 0) {
                        isEnd = true;
                    }
                }
                if (!isEnd) {
                    c[i] = (byte) syncText.szText1[i];
                } else {
                    c[i] = 0;
                }
            }
            boolean isEnd2 = false;
            for (int i2 = 0; i2 < syncText.szText2.length; i2++) {
                if (i2 != syncText.szText2.length - 1) {
                    int cur2 = syncText.szText2[i2];
                    int next2 = syncText.szText2[i2 + 1];
                    if (cur2 == 0 && next2 == 0) {
                        isEnd2 = true;
                    }
                }
                if (!isEnd2) {
                    c[i2 + 16] = (byte) syncText.szText2[i2];
                } else {
                    c[i2 + 16] = 0;
                }
            }
            boolean isEnd3 = false;
            for (int i3 = 0; i3 < syncText.szText3.length; i3++) {
                if (i3 != syncText.szText3.length - 1) {
                    int cur3 = syncText.szText3[i3];
                    int next3 = syncText.szText3[i3 + 1];
                    if (cur3 == 0 && next3 == 0) {
                        isEnd3 = true;
                    }
                }
                if (!isEnd3) {
                    c[i3 + 32] = (byte) syncText.szText3[i3];
                } else {
                    c[i3 + 32] = 0;
                }
            }
            Log.d("HAHA", "Line " + (index + 1) + " : Str = " + CanIF.byte2UnicodeStr(c));
            this.mMenuLine[index].SetText(CanIF.byte2UnicodeStr(c));
        }
    }

    private void updateKey(boolean check, int index) {
        CanDataInfo.FordWcSyncText[] fordWcSyncTextArr = this.mKeys;
        CanDataInfo.FordWcSyncText syncText = new CanDataInfo.FordWcSyncText();
        fordWcSyncTextArr[index] = syncText;
        CanJni.FordWcGetSyncText(syncText, index + 5);
        byte[] c = new byte[48];
        if (!i2b(syncText.UpdateOnce)) {
            return;
        }
        if (!check || i2b(syncText.Update)) {
            syncText.Update = 0;
            boolean isEnd = false;
            for (int i = 0; i < syncText.szText1.length; i++) {
                if (i != syncText.szText1.length - 1) {
                    int cur = syncText.szText1[i];
                    int next = syncText.szText1[i + 1];
                    if (cur == 0 && next == 0) {
                        isEnd = true;
                    }
                }
                if (!isEnd) {
                    c[i] = (byte) syncText.szText1[i];
                } else {
                    c[i] = 0;
                }
            }
            boolean isEnd2 = false;
            for (int i2 = 0; i2 < syncText.szText2.length; i2++) {
                if (i2 != syncText.szText2.length - 1) {
                    int cur2 = syncText.szText2[i2];
                    int next2 = syncText.szText2[i2 + 1];
                    if (cur2 == 0 && next2 == 0) {
                        isEnd2 = true;
                    }
                }
                if (!isEnd2) {
                    c[i2 + 16] = (byte) syncText.szText2[i2];
                } else {
                    c[i2 + 16] = 0;
                }
            }
            boolean isEnd3 = false;
            for (int i3 = 0; i3 < syncText.szText3.length; i3++) {
                if (i3 != syncText.szText3.length - 1) {
                    int cur3 = syncText.szText3[i3];
                    int next3 = syncText.szText3[i3 + 1];
                    if (cur3 == 0 && next3 == 0) {
                        isEnd3 = true;
                    }
                }
                if (!isEnd3) {
                    c[i3 + 32] = (byte) syncText.szText3[i3];
                } else {
                    c[i3 + 32] = 0;
                }
            }
            Log.d("HAHA", "Key " + (index + 1) + " : Str = " + CanIF.byte2UnicodeStr(c));
            this.mMenuKey[index].SetText(CanIF.byte2UnicodeStr(c));
        }
    }

    private void ResetData(boolean check) {
        UpdateUI(check);
    }

    public void onClick(View v) {
        boolean z;
        int id = ((Integer) v.getTag()).intValue();
        int screenNum = this.mSyncUI.ScreenNum;
        switch (id) {
            case 1:
                CanJni.FordWcSyncKey(screenNum, 2, 17);
                return;
            case 2:
                CanJni.FordWcSyncKey(screenNum, 2, 18);
                return;
            case 3:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
                return;
            case 4:
                if (this.mfgShowKb) {
                    z = false;
                } else {
                    z = true;
                }
                this.mfgShowKb = z;
                ShowRtBtn();
                return;
            case 21:
                CanJni.FordWcSyncKey(screenNum, 1, 1);
                return;
            case 22:
                CanJni.FordWcSyncKey(screenNum, 1, 2);
                return;
            case 23:
                CanJni.FordWcSyncKey(screenNum, 1, 3);
                return;
            case 24:
                CanJni.FordWcSyncKey(screenNum, 1, 4);
                return;
            case 37:
                CanJni.FordWcSyncKey(screenNum, 1, 1);
                return;
            case 38:
                CanJni.FordWcSyncKey(screenNum, 1, 2);
                return;
            case 39:
                CanJni.FordWcSyncKey(screenNum, 1, 3);
                return;
            case 40:
                CanJni.FordWcSyncKey(screenNum, 1, 4);
                return;
            default:
                if (id >= 80) {
                    CanJni.FordWcSyncKey(screenNum, 2, id - 80);
                    return;
                }
                return;
        }
    }

    public void UserAll() {
        ResetData(true);
        if (this.mDelayTime != 0) {
            this.mDelayTime--;
            if (this.mDelayTime == 0) {
                this.mSyncTime.setVisibility(4);
            }
        }
        if (mIsNeedFinish) {
            mIsNeedFinish = false;
            finish();
        }
    }

    public static void ShowSyncClick() {
        if (!mIsSyncWin) {
            CanFunc.showCanActivity(CanFordWcSyncUIActivity.class);
        }
    }

    public static void dealAudioChange() {
        CanJni.FordWcGetSyncStatus(new CanDataInfo.FordWcSyncStatus());
    }

    public static void dealUIChange() {
        CanDataInfo.FordWcSyncStatus syncStatus = new CanDataInfo.FordWcSyncStatus();
        CanJni.FordWcGetSyncStatus(syncStatus);
        if (syncStatus.UiReq == 1 || syncStatus.UiReq == 2) {
            mIsNeedFinish = false;
            if (!mIsSyncWin) {
                CanFunc.showCanActivity(CanFordWcSyncUIActivity.class);
                return;
            }
            return;
        }
        mIsNeedFinish = true;
    }
}
