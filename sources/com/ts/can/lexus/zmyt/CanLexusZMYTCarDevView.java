package com.ts.can.lexus.zmyt;

import android.app.Activity;
import android.content.Intent;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.Evc;
import com.ts.MainUI.R;
import com.ts.MainUI.TsDisplay;
import com.ts.backcar.AutoFitTextureView;
import com.ts.backcar.BackcarService;
import com.ts.can.CanCameraUI;
import com.ts.can.CanCarDeviceActivity;
import com.ts.can.CanFunc;
import com.ts.can.CanIF;
import com.ts.can.CanRelativeCarInfoView;
import com.ts.can.MyApplication;
import com.ts.main.common.MainSet;
import com.ts.other.CustomImgView;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;
import com.txznet.sdk.TXZResourceManager;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.Iop;
import com.yyw.ts70xhw.KeyDef;
import com.yyw.ts70xhw.Mcu;
import java.io.UnsupportedEncodingException;

public class CanLexusZMYTCarDevView extends CanRelativeCarInfoView implements View.OnLongClickListener {
    public static final int ITEM_DN = 17;
    public static final int ITEM_UP = 16;
    private static final int LONG_KEY_TICK = 6;
    public static final int PAGE_AUX = 3;
    public static final int PAGE_BTMUSIC = 4;
    public static final int PAGE_DISC = 2;
    public static final int PAGE_PHONE = 6;
    public static final int PAGE_POW_OF = 0;
    public static final int PAGE_RADIO = 1;
    public static final int PAGE_USB = 5;
    public static int mBtCnt = 0;
    public static final int[] mDsicNumArr = {R.drawable.lkss_disc_01, R.drawable.lkss_disc_02, R.drawable.lkss_disc_03, R.drawable.lkss_disc_04, R.drawable.lkss_disc_05, R.drawable.lkss_disc_06};
    private static int mLastDiscSta = 0;
    private static int mLastTextSta = 0;
    private static long mLastTick = 0;
    public static int mOldBtSta = 0;
    private static long mTextLastTick = 0;
    public static boolean mfgAutoEnt = false;
    public static boolean mfgFinish = false;
    public static boolean mfgShow = false;
    private CanDataInfo.LexusZmytBaseInfo mBaseData;
    private ParamButton mBtnDn;
    private ParamButton mBtnUp;
    private AutoFitTextureView mCameraView;
    private CanDataInfo.LexusZmytCarInfo mCarData;
    private CustomImgView mImgBg;
    private CustomImgView mImgBt;
    private CustomImgView mImgDisnIN;
    private CustomImgView mImgLine;
    private CustomImgView mImgLogo;
    private CustomImgView mImgPhone;
    private CustomImgView mImgRPT;
    private CustomImgView mImgRand;
    private CustomImgView mImgScan;
    private RelativeLayoutManager mManager;
    private TextView mTextAsl;
    private TextView mTextLeftName;
    private TextView mTextLogo;
    private TextView mTextRPT;
    private TextView mTextR_BandDW;
    private TextView mTextR_Freq;
    private TextView mTextR_ST;
    private TextView mTextR_TR;
    private TextView mTextRand;
    private TextView mTextRightValue;
    private TextView mTextScan;
    private TextView mTextString;
    private TextView mTextcd_usb1;
    private TextView mTextcd_usb2;
    private TextView mTextcd_usb3;
    private TextView mTextcd_usb4;
    private TextView mTextcd_usbTime;
    private CustomImgView[] m_CdSta;
    private CustomImgView[] m_SignalSta;
    private long mlongKeyTick = 0;

    public CanLexusZMYTCarDevView(Activity activity) {
        super(activity);
    }

    public boolean onTouch(View v, MotionEvent event) {
        if (CanLexusZMYTCarInitView.HostRes() == 0) {
            int action = event.getAction();
            int Id = ((Integer) v.getTag()).intValue();
            if (action == 0) {
                sendKey(Id, 1);
            } else if (action == 1) {
                sendKey(Id, 0);
            }
        }
        return false;
    }

    private void sendKey(int Id, int para) {
        switch (Id) {
            case 16:
                if (this.mBaseData.ShowMode == 2) {
                    CanJni.LexusZmytKeyCmd(49, para);
                    return;
                } else if (this.mBaseData.ShowMode == 3) {
                    switch (this.mBaseData.AudioMode) {
                        case 1:
                            CanJni.LexusZmytKeyCmd(41, para);
                            return;
                        case 2:
                            CanJni.LexusZmytKeyCmd(39, para);
                            return;
                        case 3:
                            CanJni.LexusZmytKeyCmd(37, para);
                            return;
                        case 4:
                            CanJni.LexusZmytKeyCmd(33, para);
                            return;
                        case 5:
                            CanJni.LexusZmytKeyCmd(35, para);
                            return;
                        case 6:
                            CanJni.LexusZmytKeyCmd(48, para);
                            return;
                        default:
                            return;
                    }
                } else {
                    return;
                }
            case 17:
                if (this.mBaseData.ShowMode == 2) {
                    CanJni.LexusZmytKeyCmd(49, 1);
                    return;
                } else if (this.mBaseData.ShowMode == 3) {
                    switch (this.mBaseData.AudioMode) {
                        case 1:
                            CanJni.LexusZmytKeyCmd(40, para);
                            return;
                        case 2:
                            CanJni.LexusZmytKeyCmd(38, para);
                            return;
                        case 3:
                            CanJni.LexusZmytKeyCmd(36, para);
                            return;
                        case 4:
                            CanJni.LexusZmytKeyCmd(32, para);
                            return;
                        case 5:
                            CanJni.LexusZmytKeyCmd(34, para);
                            return;
                        case 6:
                            CanJni.LexusZmytKeyCmd(48, para);
                            return;
                        default:
                            return;
                    }
                } else {
                    return;
                }
            default:
                return;
        }
    }

    public void onClick(View v) {
        int intValue = ((Integer) v.getTag()).intValue();
    }

    public boolean onLongClick(View v) {
        int intValue = ((Integer) v.getTag()).intValue();
        return false;
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mBaseData = new CanDataInfo.LexusZmytBaseInfo();
        this.mCarData = new CanDataInfo.LexusZmytCarInfo();
        Log.d("lq", "CanLexusZMYTCarInitView.HostRes()" + CanLexusZMYTCarInitView.HostRes());
        if (CanLexusZMYTCarInitView.HostRes() == 0) {
            this.mManager = getRelativeManager();
            InitData();
            if (MainSet.GetScreenType() == 9) {
                FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) this.mManager.GetLayout().getLayoutParams();
                lp.width = 1280;
                lp.height = 425;
                lp.gravity = 17;
                this.mManager.GetLayout().setLayoutParams(lp);
                InitView();
                this.mManager.GetLayout().setScaleX(1.5f);
                this.mManager.GetLayout().setScaleY(1.56f);
                return;
            }
            InitView();
            return;
        }
        getActivity().setContentView(R.layout.activity_can_withcd_base);
    }

    public void doOnPause() {
        super.doOnPause();
        mfgShow = false;
        mfgAutoEnt = false;
        if (CanLexusZMYTCarInitView.HostRes() != 0) {
            BackcarService.getInstance().StopCamera();
        }
    }

    public void doOnResume() {
        super.doOnResume();
        if (!mfgAutoEnt) {
            Evc.GetInstance().evol_workmode_set(12);
        }
        mfgShow = true;
        mfgFinish = false;
        if (CanLexusZMYTCarInitView.HostRes() != 0) {
            TsDisplay.GetInstance().SetDispParat(-1);
            MainSet.GetInstance().SetVideoChannel(2);
            this.mCameraView = (AutoFitTextureView) getActivity().findViewById(R.id.DevtextureView);
            BackcarService.getInstance().StartCamera(this.mCameraView, false);
            this.mCameraView.setOnTouchListener(this);
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.mCameraView.getLayoutParams();
            layoutParams.width = -1;
            layoutParams.height = -1;
            layoutParams.leftMargin = 0;
            this.mCameraView.setLayoutParams(layoutParams);
            Log.d("lq", "mCameraView");
        }
    }

    private void InitView() {
        getRelativeManager().AddImage(0, 0, R.drawable.lkss_mode_bg);
        this.mImgLine = getRelativeManager().AddImage(210, Can.CAN_BJ20_WC, R.drawable.lkss_line);
        this.mImgLogo = getRelativeManager().AddImage(33, 75, R.drawable.lkss_close_logo);
        this.mImgBg = getRelativeManager().AddImage(208, 13, R.drawable.lkss_close_bg);
        this.mImgBg.setVisibility(4);
        this.mTextLogo = AddText(213, 200, 129, 45);
        this.mTextLogo.setTextSize(0, 42.0f);
        this.mTextAsl = AddText(KeyDef.RKEY_ANGLEUP, 176, 71, 23);
        this.mTextAsl.setText("ASL");
        this.mTextAsl.setTextSize(0, 20.0f);
        this.mTextR_TR = AddText(377, 208, 79, 29);
        this.mTextR_TR.setTextSize(0, 27.0f);
        this.mTextR_Freq = AddText(467, 180, 210, 60);
        this.mTextR_Freq.setTextSize(0, 55.0f);
        this.mTextR_Freq.setGravity(17);
        this.mTextR_BandDW = AddText(687, 208, 79, 29);
        this.mTextR_BandDW.setTextSize(0, 27.0f);
        this.mTextR_ST = AddText(CanCameraUI.BTN_CCH9_MODE2, 164, 41, 23);
        this.mTextR_ST.setText("ST");
        this.mTextR_ST.setTextSize(0, 20.0f);
        this.mTextcd_usb1 = AddText(417, 164, 71, 23);
        this.mTextcd_usb1.setTextSize(0, 20.0f);
        this.mTextcd_usb2 = AddText(540, 164, 71, 23);
        this.mTextcd_usb2.setTextSize(0, 20.0f);
        this.mTextcd_usb3 = AddText(417, 200, 71, 44);
        this.mTextcd_usb3.setTextSize(0, 35.0f);
        this.mTextcd_usb4 = AddText(540, 200, 71, 44);
        this.mTextcd_usb4.setTextSize(0, 35.0f);
        this.mTextcd_usbTime = AddText(CanCameraUI.BTN_SENOVA_SUB_BJ40_MODE3, Can.CAN_LEXUS_IZ, 139, 35);
        this.mTextcd_usbTime.setTextSize(0, 30.0f);
        this.mTextcd_usb1.setGravity(17);
        this.mTextcd_usb2.setGravity(17);
        this.mTextcd_usb3.setGravity(17);
        this.mTextcd_usb4.setGravity(17);
        this.mTextcd_usbTime.setGravity(17);
        this.mTextLeftName = AddText(417, 194, 123, 60);
        this.mTextRightValue = AddText(550, 194, 123, 60);
        this.mTextString = AddText(KeyDef.RKEY_RADIO_5S, 204, 450, 160);
        this.mTextString.setGravity(17);
        this.mImgBt = getRelativeManager().AddImage(371, 119, R.drawable.lkss_bt);
        this.mImgPhone = getRelativeManager().AddImage(CanCameraUI.BTN_TRUMPCHI_GS4_MODE2, 119, R.drawable.lkss_xinhao_01);
        for (int i = 0; i < 5; i++) {
            if (i == 0) {
                this.m_SignalSta[i] = getRelativeManager().AddImage(411, 130, R.drawable.lkss_xinhao_02);
            } else if (i == 1) {
                this.m_SignalSta[i] = getRelativeManager().AddImage(415, 124, R.drawable.lkss_xinhao_03);
            } else {
                this.m_SignalSta[i] = getRelativeManager().AddImage(((i - 2) * 4) + 419, 120, R.drawable.lkss_xinhao_04);
            }
        }
        this.mImgDisnIN = getRelativeManager().AddImage(461, 79, R.drawable.lkss_disc_in);
        for (int i2 = 0; i2 < 6; i2++) {
            this.m_CdSta[i2] = getRelativeManager().AddImage((i2 * 27) + 568, 79, mDsicNumArr[i2]);
            this.m_CdSta[i2].Show(false);
        }
        this.mTextRand = AddText(499, 117, 85, 25);
        this.mTextRand.setTextSize(0, 22.0f);
        this.mTextRPT = AddText(608, 117, 71, 25);
        this.mTextRPT.setTextSize(0, 22.0f);
        this.mTextScan = AddText(697, 117, 71, 25);
        this.mTextScan.setTextSize(0, 22.0f);
        this.mImgRand = getRelativeManager().AddImage(480, 127, R.drawable.lkss_logo1);
        this.mImgRPT = getRelativeManager().AddImage(587, 128, R.drawable.lkss_logo2);
        this.mImgScan = getRelativeManager().AddImage(681, 127, R.drawable.lkss_logo1);
        this.mBtnUp = addButton(417, 264, R.drawable.can_sync_seekup_up, R.drawable.can_sync_seekup_dn, 16);
        this.mBtnDn = addButton(540, 264, R.drawable.can_sync_seekdn_up, R.drawable.can_sync_seekdn_dn, 17);
    }

    private void InitData() {
        this.m_CdSta = new CustomImgView[6];
        this.m_SignalSta = new CustomImgView[5];
    }

    public void ResetData(boolean check) {
        if (CanLexusZMYTCarInitView.HostRes() == 0) {
            CanJni.LexusZmytGetBaseInfo(this.mBaseData);
            CanJni.LexusZmytGetCarInfo(this.mCarData);
            UpdateEXD(check);
        }
        if (mfgShow && mfgFinish) {
            mfgFinish = false;
            mfgShow = false;
            getActivity().finish();
        }
    }

    public void QueryData() {
    }

    private long getTickCount() {
        return SystemClock.uptimeMillis();
    }

    private void UpdateDiscFlag() {
        if (this.mBaseData.Mode != 0) {
            long curTick = getTickCount();
            if (curTick > mLastTick + 666) {
                mLastTick = curTick;
                if (mLastDiscSta != 0) {
                    mLastDiscSta = 0;
                } else {
                    mLastDiscSta = 1;
                }
                this.mImgDisnIN.setVisibility(ShowView(this.mBaseData.IconEject));
                if (this.mBaseData.IconEject == 0) {
                    this.mImgDisnIN.setVisibility(8);
                } else if (this.mBaseData.IconEject == 3) {
                    this.mImgDisnIN.setVisibility(0);
                } else if (this.mBaseData.IconEject == 1) {
                    if (mLastDiscSta != 0) {
                        this.mImgDisnIN.Show(true);
                    } else {
                        this.mImgDisnIN.Show(false);
                    }
                }
                for (int i = 0; i < 6; i++) {
                    if (this.mBaseData.IconDisc[i] == 0) {
                        this.m_CdSta[i].Show(false);
                    } else if (this.mBaseData.IconDisc[i] == 3) {
                        this.m_CdSta[i].Show(true);
                    } else if (this.mBaseData.IconDisc[i] == 1) {
                        if (mLastDiscSta != 0) {
                            this.m_CdSta[i].Show(true);
                        } else {
                            this.m_CdSta[i].Show(false);
                        }
                    }
                }
            }
        }
    }

    private void UpdateEXD(boolean check) {
        if (this.mBaseData.UpdateOnce != 0 && (!check || this.mBaseData.Update != 0)) {
            this.mBaseData.Update = 0;
            UpdateView();
        }
        UpdateDiscFlag();
        if (this.mTextString != null && !TextUtils.isEmpty(this.mTextString.getText())) {
            if (this.mBaseData.StrSta > 0) {
                long curTick = getTickCount();
                if (curTick > mTextLastTick + 666) {
                    mTextLastTick = curTick;
                    if (mLastTextSta != 0) {
                        mLastTextSta = 0;
                    } else {
                        mLastTextSta = 1;
                    }
                    if (mLastTextSta != 0) {
                        this.mTextString.setVisibility(0);
                    } else {
                        this.mTextString.setVisibility(4);
                    }
                }
            } else {
                this.mTextString.setVisibility(0);
            }
        }
    }

    private String byte2ASCIIString(byte[] data, int len) {
        try {
            return new String(data, 0, len, "GBK");
        } catch (UnsupportedEncodingException e) {
            return TXZResourceManager.STYLE_DEFAULT;
        }
    }

    private void UpdateText() {
        if (this.mBaseData.ShowMode == 15) {
            this.mTextLeftName.setText(TXZResourceManager.STYLE_DEFAULT);
            this.mTextRightValue.setText(TXZResourceManager.STYLE_DEFAULT);
            if (this.mBaseData.StrInfo == 0) {
                this.mTextLogo.setText(TXZResourceManager.STYLE_DEFAULT);
            }
            this.mTextString.setText(byte2ASCIIString(this.mBaseData.Str, this.mBaseData.StrLen));
            return;
        }
        this.mTextString.setText(TXZResourceManager.STYLE_DEFAULT);
        if (this.mBaseData.ShowMode == 1) {
            this.mTextLeftName.setText("VOL");
            if (this.mBaseData.Vol > 63 || this.mBaseData.Vol < 0) {
                this.mTextRightValue.setText("MAX");
            } else {
                this.mTextRightValue.setText(String.format("%d", new Object[]{Integer.valueOf(this.mBaseData.Vol)}));
            }
        }
        if (this.mBaseData.ShowMode == 2) {
            this.mTextLeftName.setText("MUTE");
            this.mTextRightValue.setText(TXZResourceManager.STYLE_DEFAULT);
        }
        if (this.mBaseData.ShowMode == 3) {
            switch (this.mBaseData.AudioMode) {
                case 0:
                    this.mTextLeftName.setText(TXZResourceManager.STYLE_DEFAULT);
                    this.mTextRightValue.setText(TXZResourceManager.STYLE_DEFAULT);
                    return;
                case 1:
                    this.mTextLeftName.setText("BAS");
                    this.mTextRightValue.setText(String.format("%d", new Object[]{Integer.valueOf(this.mBaseData.Bas - 16)}));
                    return;
                case 2:
                    this.mTextLeftName.setText("MID");
                    this.mTextRightValue.setText(String.format("%d", new Object[]{Integer.valueOf(this.mBaseData.Mid - 16)}));
                    return;
                case 3:
                    this.mTextLeftName.setText("TRE");
                    this.mTextRightValue.setText(String.format("%d", new Object[]{Integer.valueOf(this.mBaseData.Tre - 16)}));
                    return;
                case 4:
                    this.mTextLeftName.setText("FAD");
                    this.mTextRightValue.setText(setValText(this.mBaseData.Fad, false));
                    return;
                case 5:
                    this.mTextLeftName.setText("BAL");
                    this.mTextRightValue.setText(setValText(this.mBaseData.Bal, true));
                    return;
                case 6:
                    this.mTextLeftName.setText("ASL");
                    if (this.mBaseData.Asl == 0) {
                        this.mTextRightValue.setText("OFF");
                        return;
                    } else if (this.mBaseData.Asl == 1) {
                        this.mTextRightValue.setText("ON");
                        return;
                    } else {
                        return;
                    }
                default:
                    return;
            }
        }
    }

    private void UpdateView() {
        if (this.mBaseData.Mode == 1) {
            ShowPage(1);
            this.mImgLogo.setImageResource(R.drawable.lkss_radio_logo);
            this.mTextLogo.setText(getRadioBand(this.mBaseData.Band));
            if (this.mBaseData.Pre == 0) {
                this.mTextR_TR.setText(TXZResourceManager.STYLE_DEFAULT);
            } else {
                this.mTextR_TR.setText(String.format("P%d", new Object[]{Integer.valueOf(this.mBaseData.Pre)}));
            }
            if (this.mBaseData.Band == 1 || this.mBaseData.Band == 2) {
                this.mTextR_BandDW.setText("MHz");
                this.mTextR_Freq.setText(String.format("%.2f", new Object[]{Double.valueOf(((double) this.mBaseData.Freq) * 0.01d)}));
            } else if (this.mBaseData.Band == 16) {
                this.mTextR_BandDW.setText("KHz");
                this.mTextR_Freq.setText(new StringBuilder(String.valueOf(this.mBaseData.Freq)).toString());
            }
            this.mTextR_ST.setVisibility(ShowView(this.mBaseData.St));
        } else if (this.mBaseData.Mode == 2) {
            ShowPage(2);
            this.mImgLogo.setImageResource(R.drawable.lkss_cd_logo);
            this.mTextLogo.setText("CD");
            this.mTextcd_usb1.setText("DISC");
            this.mTextcd_usb2.setText("TR");
            this.mTextcd_usb3.setText(getNormalText(this.mBaseData.DiscNum, 1, 6));
            this.mTextcd_usb4.setText(getNormalText(this.mBaseData.Track, 1, Can.CAN_VOLKS_XP));
            this.mTextcd_usbTime.setText(String.valueOf(getPlayNormalText(this.mBaseData.PlayMin, 0, Can.CAN_VOLKS_XP)) + ":" + getPlayNormalText(this.mBaseData.PlaySec, 0, 59));
        } else if (this.mBaseData.Mode == 3) {
            ShowPage(3);
            this.mTextLogo.setText("AUX");
            this.mImgLogo.setImageResource(R.drawable.lkss_aux_logo);
        } else if (this.mBaseData.Mode == 4) {
            ShowPage(4);
            this.mImgLogo.setImageResource(R.drawable.lkss_btmusic_logo);
            this.mTextLogo.setText("BT");
            this.mTextcd_usb2.setText("TR");
            this.mTextcd_usb4.setText(getNormalText(this.mBaseData.BtTrack, 1, Can.CAN_VOLKS_XP));
            this.mTextcd_usbTime.setText(String.valueOf(getPlayNormalText(this.mBaseData.BtPlayMin, 0, Can.CAN_VOLKS_XP)) + ":" + getPlayNormalText(this.mBaseData.BtPlaySec, 0, 59));
        } else if (this.mBaseData.Mode == 5) {
            ShowPage(5);
            this.mImgLogo.setImageResource(R.drawable.lkss_usb_logo);
            this.mTextLogo.setText("USB");
            this.mTextcd_usb1.setText("FLD");
            this.mTextcd_usb2.setText("FILE");
            this.mTextcd_usb3.setText(getNormalText(this.mBaseData.UsbFolder, 1, Can.CAN_VOLKS_XP));
            this.mTextcd_usb4.setText(getNormalText(this.mBaseData.UsbTrack, 1, Can.CAN_VOLKS_XP));
            this.mTextcd_usbTime.setText(String.valueOf(getPlayNormalText(this.mBaseData.UsbPlayMin, 0, Can.CAN_VOLKS_XP)) + ":" + getPlayNormalText(this.mBaseData.UsbPlaySec, 0, 59));
        } else if (this.mBaseData.Mode == 6) {
            ShowPage(6);
            this.mTextLogo.setText("TEL");
            this.mImgLogo.setImageResource(R.drawable.lkss_phone_logo);
        }
        if (this.mBaseData.Mode != 0) {
            setPublicInfo();
            this.mImgBg.setVisibility(4);
            this.mImgLine.setVisibility(0);
            if (this.mBaseData.ShowMode != 0) {
                this.mTextR_BandDW.setVisibility(ShowView(false));
                this.mTextR_Freq.setVisibility(ShowView(false));
                this.mTextR_ST.setVisibility(ShowView(false));
                this.mTextR_TR.setVisibility(ShowView(false));
                this.mTextcd_usb1.setVisibility(ShowView(false));
                this.mTextcd_usb2.setVisibility(ShowView(false));
                this.mTextcd_usb3.setVisibility(ShowView(false));
                this.mTextcd_usb4.setVisibility(ShowView(false));
                this.mTextcd_usbTime.setVisibility(ShowView(false));
                UpdateText();
                if (!i2b(this.mCarData.AmpControl)) {
                    return;
                }
                if (this.mBaseData.ShowMode == 2 || this.mBaseData.ShowMode == 3) {
                    this.mBtnDn.setVisibility(0);
                    this.mBtnUp.setVisibility(0);
                    return;
                }
                this.mBtnDn.setVisibility(8);
                this.mBtnUp.setVisibility(8);
                return;
            }
            this.mTextLeftName.setText(TXZResourceManager.STYLE_DEFAULT);
            this.mTextRightValue.setText(TXZResourceManager.STYLE_DEFAULT);
            this.mTextString.setText(TXZResourceManager.STYLE_DEFAULT);
            this.mBtnDn.setVisibility(8);
            this.mBtnUp.setVisibility(8);
            return;
        }
        this.mTextLeftName.setText(TXZResourceManager.STYLE_DEFAULT);
        this.mTextRightValue.setText(TXZResourceManager.STYLE_DEFAULT);
        this.mTextString.setText(TXZResourceManager.STYLE_DEFAULT);
        this.mImgLogo.setImageResource(R.drawable.lkss_close_logo);
        this.mImgBg.setVisibility(0);
        this.mImgLine.setVisibility(4);
        ShowPage(0);
    }

    private String getNormalText(int Num, int minNum, int maxNum) {
        if (Num > maxNum || Num < minNum) {
            return "--";
        }
        return String.format("%d", new Object[]{Integer.valueOf(Num)});
    }

    private String getPlayNormalText(int Num, int minNum, int maxNum) {
        if (Num > maxNum || Num < minNum) {
            return "--";
        }
        return String.format("%02d", new Object[]{Integer.valueOf(Num)});
    }

    private void setPublicInfo() {
        this.mTextAsl.setVisibility(ShowView(this.mBaseData.AslShow));
        this.mImgBt.setVisibility(ShowView(this.mBaseData.IconBt));
        this.mImgPhone.setVisibility(ShowView(this.mBaseData.IconPhone));
        for (int i = 0; i < this.m_SignalSta.length; i++) {
            if (this.mBaseData.PhoneSignal > i) {
                this.m_SignalSta[i].setVisibility(0);
            } else {
                this.m_SignalSta[i].setVisibility(4);
            }
        }
        if (this.mBaseData.Scan == 0) {
            this.mTextScan.setText(TXZResourceManager.STYLE_DEFAULT);
            this.mImgScan.setVisibility(8);
        } else if (this.mBaseData.Scan == 1) {
            this.mTextScan.setText("SCAN");
            this.mImgScan.setVisibility(0);
        } else if (this.mBaseData.Scan == 2) {
            this.mTextScan.setText("SCAN");
            this.mImgScan.setVisibility(8);
        }
        if (this.mBaseData.Rpt == 0) {
            this.mTextRPT.setText(TXZResourceManager.STYLE_DEFAULT);
            this.mImgRPT.setVisibility(8);
        } else if (this.mBaseData.Rpt == 1) {
            this.mTextRPT.setText("RPT");
            this.mImgRPT.setVisibility(0);
        } else if (this.mBaseData.Rpt == 2) {
            this.mTextRPT.setText("RPT");
            this.mImgRPT.setVisibility(8);
        }
        if (this.mBaseData.Rand == 0) {
            this.mTextRand.setText(TXZResourceManager.STYLE_DEFAULT);
            this.mImgRand.setVisibility(8);
        } else if (this.mBaseData.Rand == 1) {
            this.mTextRand.setText("RAND");
            this.mImgRand.setVisibility(0);
        } else if (this.mBaseData.Rand == 2) {
            this.mTextRand.setText("RAND");
            this.mImgRand.setVisibility(8);
        }
    }

    private int ShowView(int show) {
        return i2b(show) ? 0 : 8;
    }

    private int ShowView(boolean show) {
        return show ? 0 : 8;
    }

    private void ShowPage(int page) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean cd_usb;
        boolean cd_usb_bt;
        boolean no_poweroff;
        TextView textView = this.mTextR_BandDW;
        if (page == 1) {
            z = true;
        } else {
            z = false;
        }
        textView.setVisibility(ShowView(z));
        TextView textView2 = this.mTextR_Freq;
        if (page == 1) {
            z2 = true;
        } else {
            z2 = false;
        }
        textView2.setVisibility(ShowView(z2));
        TextView textView3 = this.mTextR_ST;
        if (page == 1) {
            z3 = true;
        } else {
            z3 = false;
        }
        textView3.setVisibility(ShowView(z3));
        TextView textView4 = this.mTextR_TR;
        if (page == 1) {
            z4 = true;
        } else {
            z4 = false;
        }
        textView4.setVisibility(ShowView(z4));
        if (page == 2 || page == 5) {
            cd_usb = true;
        } else {
            cd_usb = false;
        }
        if (page == 2 || page == 5 || page == 4) {
            cd_usb_bt = true;
        } else {
            cd_usb_bt = false;
        }
        this.mTextcd_usb1.setVisibility(ShowView(cd_usb));
        this.mTextcd_usb2.setVisibility(ShowView(cd_usb_bt));
        this.mTextcd_usb3.setVisibility(ShowView(cd_usb));
        this.mTextcd_usb4.setVisibility(ShowView(cd_usb_bt));
        this.mTextcd_usbTime.setVisibility(ShowView(cd_usb_bt));
        if (page != 0) {
            no_poweroff = true;
        } else {
            no_poweroff = false;
        }
        this.mTextLogo.setVisibility(ShowView(no_poweroff));
        this.mTextAsl.setVisibility(ShowView(no_poweroff));
        this.mTextRand.setVisibility(ShowView(no_poweroff));
        this.mTextRPT.setVisibility(ShowView(no_poweroff));
        this.mTextScan.setVisibility(ShowView(no_poweroff));
        this.mImgRand.setVisibility(ShowView(no_poweroff));
        this.mImgRPT.setVisibility(ShowView(no_poweroff));
        this.mImgScan.setVisibility(ShowView(no_poweroff));
        this.mImgBt.setVisibility(ShowView(no_poweroff));
        for (CustomImgView visibility : this.m_SignalSta) {
            visibility.setVisibility(ShowView(no_poweroff));
        }
        this.mImgPhone.setVisibility(ShowView(no_poweroff));
        if (page == 0) {
            this.mImgDisnIN.setVisibility(8);
            for (CustomImgView visibility2 : this.m_CdSta) {
                visibility2.setVisibility(8);
            }
        }
    }

    private TextView AddText(int x, int y, int w, int h) {
        new TextView(getActivity());
        TextView text = getRelativeManager().AddText(x, y, w, h);
        text.setTextSize(0, 40.0f);
        text.setTextColor(-1);
        text.setGravity(19);
        return text;
    }

    /* access modifiers changed from: protected */
    public String getRadioBand(int band) {
        switch (band) {
            case 1:
                return "FM1";
            case 2:
                return "FM2";
            case 16:
                return "AM";
            default:
                return TXZResourceManager.STYLE_DEFAULT;
        }
    }

    private String setValText(int val, boolean isLtRt) {
        if (isLtRt) {
            if (val == 16) {
                return "0";
            }
            if (val < 16) {
                return "L" + String.valueOf(16 - val);
            }
            if (val > 16) {
                return "R" + String.valueOf(val - 16);
            }
        } else if (val == 16) {
            return "0";
        } else {
            if (val < 16) {
                return "F" + String.valueOf(16 - val);
            }
            if (val > 16) {
                return "R" + String.valueOf(val - 16);
            }
        }
        return "0";
    }

    private ParamButton addButton(int x, int y, int normal, int selected, int id) {
        ParamButton button = getRelativeManager().AddButton(x, y);
        button.setStateDrawable(normal, selected, selected);
        button.setOnTouchListener(this);
        button.setTag(Integer.valueOf(id));
        button.setVisibility(8);
        return button;
    }

    public static void Init() {
        if (FtSet.Getlgb1() != 0) {
            Mcu.SendXKey(33);
        } else {
            Mcu.SendXKey(34);
        }
        Mcu.SendXKey(40);
        Mcu.SendXKey(CanLexusZMYTCarInitView.HostRes() + 50);
        Mcu.SendXKey(CanLexusZMYTCarFuncView.RvsDelay() + 42);
        if (FtSet.IsIconExist(1) == 0) {
            Iop.RstPort(0);
        }
        byte[] fileMsg = new byte[8];
        fileMsg[0] = (byte) FtSet.GetCanTp();
        fileMsg[1] = (byte) ((FtSet.GetCanTp() >> 8) & 255);
        fileMsg[2] = (byte) FtSet.GetCanSubT();
        fileMsg[3] = (byte) CanLexusZMYTCarInitView.IsHaveAir();
        CanFunc.getInstance();
        CanFunc.sendFileCarInfo(fileMsg, CanFunc.Can_Factory_Set_fileName);
        MyApplication.mContext.sendBroadcast(new Intent("com.ts.can.BROADCAST_CAN_INFO_LEXUS_AIR"));
    }

    public static void DealDevEvent() {
        if (FtSet.IsIconExist(1) == 0) {
            switch (mOldBtSta) {
                case 0:
                    if (Iop.GetMediaOrBlue() > 0) {
                        mOldBtSta = 1;
                        mBtCnt = 10;
                        break;
                    }
                    break;
                case 1:
                    if (mBtCnt <= 0) {
                        mOldBtSta = 2;
                        Iop.RstPort(1);
                        break;
                    } else {
                        mBtCnt--;
                        break;
                    }
                case 2:
                    if (Iop.GetMediaOrBlue() == 0) {
                        Iop.RstPort(0);
                        mOldBtSta = 0;
                        break;
                    }
                    break;
            }
        }
        if (CanIF.mGpsVoiceDelay > 0) {
            CanIF.mGpsVoiceDelay--;
            if (CanIF.mGpsVoiceDelay == 0) {
                Iop.RstPort(1);
            }
        }
    }

    public static void showLexusZMYTWin() {
        if (!mfgShow) {
            mfgAutoEnt = true;
            CanFunc.showCanActivity(CanCarDeviceActivity.class);
        }
    }

    public static void entLexusZMYTMode() {
        if (!mfgShow) {
            CanFunc.showCanActivity(CanCarDeviceActivity.class);
        }
    }

    public static void finishLexusZMYTWin() {
        if (mfgShow) {
            mfgFinish = true;
        }
    }

    public static boolean IsLexusZMYTWin() {
        return mfgShow;
    }
}
