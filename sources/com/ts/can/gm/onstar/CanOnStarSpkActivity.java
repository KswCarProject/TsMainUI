package com.ts.can.gm.onstar;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
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
import com.ts.canview.CanItemProgressList;
import com.ts.main.common.MainSet;
import com.ts.other.CustomImgView;
import com.ts.other.CustomTextView;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;
import com.txznet.sdk.TXZResourceManager;
import com.yyw.ts70xhw.KeyDef;

public class CanOnStarSpkActivity extends CanBaseActivity implements UserCallBack, View.OnClickListener, CanItemProgressList.onPosChange {
    public static final int ITEM_BKSP = 35;
    public static final int ITEM_CALL = 32;
    public static final int ITEM_HANGUP = 33;
    public static final int ITEM_NUM0 = 16;
    public static final int ITEM_NUM9 = 25;
    public static final int ITEM_NUMJ = 27;
    public static final int ITEM_NUMX = 26;
    public static final int ITEM_PWROFF = 34;
    public static final String TAG = "CanOnStarSpkActivity";
    protected static DisplayMetrics mDisplayMetrics = new DisplayMetrics();
    protected static final int[] mNumArrDn = {R.drawable.can_an_num01_dn, R.drawable.can_an_num02_dn, R.drawable.can_an_num03_dn, R.drawable.can_an_num04_dn, R.drawable.can_an_num05_dn, R.drawable.can_an_num06_dn, R.drawable.can_an_num07_dn, R.drawable.can_an_num08_dn, R.drawable.can_an_num09_dn};
    protected static final int[] mNumArrUp = {R.drawable.can_an_num01_up, R.drawable.can_an_num02_up, R.drawable.can_an_num03_up, R.drawable.can_an_num04_up, R.drawable.can_an_num05_up, R.drawable.can_an_num06_up, R.drawable.can_an_num07_up, R.drawable.can_an_num08_up, R.drawable.can_an_num09_up};
    private static String mStrNo = TXZResourceManager.STYLE_DEFAULT;
    private static Activity mThis = null;
    protected ParamButton mBtnBackSp;
    protected ParamButton mBtnCall;
    protected ParamButton mBtnHangup;
    protected ParamButton[] mBtnNum = new ParamButton[12];
    protected ParamButton mBtnPWROff;
    protected CustomImgView mImgCall;
    protected RelativeLayoutManager mManager;
    private CanDataInfo.GM_PhoneInfo mPhoneData = new CanDataInfo.GM_PhoneInfo();
    private CanDataInfo.GM_OnStarSta mStaData = new CanDataInfo.GM_OnStarSta();
    protected CustomTextView mTvCall;
    protected CustomTextView mTvInput;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_can_comm_relative);
        this.mManager = new RelativeLayoutManager(this, R.id.can_comm_layout);
        getWindowManager().getDefaultDisplay().getMetrics(mDisplayMetrics);
        Log.d("nyw", String.format("%d,%d", new Object[]{Integer.valueOf(mDisplayMetrics.widthPixels), Integer.valueOf(mDisplayMetrics.heightPixels)}));
        if (MainSet.GetScreenType() == 3) {
            initScreen_768x1024();
        } else if (MainSet.GetScreenType() == 5) {
            initScreen_1280x480();
        } else {
            FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) this.mManager.GetLayout().getLayoutParams();
            lp.width = 1024;
            lp.height = CanCameraUI.BTN_NISSAN_XTRAL_RVS_ASSIST6;
            lp.gravity = 17;
            this.mManager.GetLayout().setLayoutParams(lp);
            initCommonScreen();
            this.mManager.GetLayout().setScaleX((((float) mDisplayMetrics.widthPixels) * 1.0f) / 1024.0f);
            this.mManager.GetLayout().setScaleY((((float) mDisplayMetrics.heightPixels) * 1.0f) / 600.0f);
            Log.d("nyw", String.format("%.2f,%.2f", new Object[]{Float.valueOf((((float) mDisplayMetrics.widthPixels) * 1.0f) / 1024.0f), Float.valueOf((((float) mDisplayMetrics.heightPixels) * 1.0f) / 600.0f)}));
        }
    }

    private void initScreen_1280x480() {
        this.mManager.AddImage(150, 0, R.drawable.can_an_bg);
        for (int i = 0; i < 9; i++) {
            this.mBtnNum[i + 1] = this.mManager.AddButton(((i % 3) * 136) + 516, ((i / 3) * 86) + 90, 109, 64);
            this.mBtnNum[i + 1].setStateUpDn(mNumArrUp[i], mNumArrDn[i]);
            this.mBtnNum[i + 1].setOnClickListener(this);
            this.mBtnNum[i + 1].setTag(Integer.valueOf(i + 17));
        }
        this.mBtnNum[0] = this.mManager.AddButton(652, 350, 109, 64);
        this.mBtnNum[0].setStateUpDn(R.drawable.can_an_num00_up, R.drawable.can_an_num00_dn);
        this.mBtnNum[0].setOnClickListener(this);
        this.mBtnNum[0].setTag(16);
        this.mBtnNum[10] = this.mManager.AddButton(516, 350, 109, 64);
        this.mBtnNum[10].setStateUpDn(R.drawable.can_an_asterisk_up, R.drawable.can_an_asterisk_dn);
        this.mBtnNum[10].setOnClickListener(this);
        this.mBtnNum[10].setTag(26);
        this.mBtnNum[11] = this.mManager.AddButton(KeyDef.SKEY_SEEKDN_2, 350, 109, 64);
        this.mBtnNum[11].setStateUpDn(R.drawable.can_an_well_up, R.drawable.can_an_well_dn);
        this.mBtnNum[11].setOnClickListener(this);
        this.mBtnNum[11].setTag(27);
        this.mBtnCall = this.mManager.AddButton(923, 100, 108, 90);
        this.mBtnCall.setStateUpDn(R.drawable.can_an_call_up, R.drawable.can_an_call_dn);
        this.mBtnCall.setOnClickListener(this);
        this.mBtnCall.setTag(32);
        this.mBtnHangup = this.mManager.AddButton(923, 211, 108, 90);
        this.mBtnHangup.setStateUpDn(R.drawable.can_an_hand_up, R.drawable.can_an_hand_dn);
        this.mBtnHangup.setOnClickListener(this);
        this.mBtnHangup.setTag(33);
        this.mBtnPWROff = this.mManager.AddButton(923, KeyDef.RKEY_RDS_TA, 108, 90);
        this.mBtnPWROff.setStateUpDn(R.drawable.can_an_off_up, R.drawable.can_an_off_dn);
        this.mBtnPWROff.setOnClickListener(this);
        this.mBtnPWROff.setTag(34);
        this.mBtnBackSp = this.mManager.AddButton(970, 7, 62, 56);
        this.mBtnBackSp.setStateUpDn(R.drawable.can_an_del_up, R.drawable.can_an_del_dn);
        this.mBtnBackSp.setOnClickListener(this);
        this.mBtnBackSp.setTag(35);
        this.mImgCall = this.mManager.AddImage(KeyDef.RKEY_MEDIA_SLOW, 90, 93, 80);
        this.mTvInput = this.mManager.AddCusText(CanCameraUI.BTN_YG9_XBS_MODE1, 7, 440, 56);
        this.mTvInput.setGravity(21);
        this.mTvInput.SetPxSize(45);
        this.mTvInput.setEllipsize(TextUtils.TruncateAt.START);
        this.mTvInput.setSingleLine();
        this.mTvCall = this.mManager.AddCusText(258, 270, Can.CAN_CC_HF_DJ, 118);
        this.mTvCall.setGravity(3);
        this.mTvCall.SetPxSize(50);
        this.mTvCall.setEllipsize(TextUtils.TruncateAt.END);
    }

    private void initScreen_768x1024() {
        this.mManager.AddImage(0, 0, R.drawable.can_an_bg);
        int nY = 0;
        if (CanFunc.getInstance().IsCore() == 1) {
            nY = 45;
        }
        for (int i = 0; i < 9; i++) {
            this.mBtnNum[i + 1] = this.mManager.AddButton(((i % 3) * 127) + 260, ((i / 3) * 83) + 100 + nY, 109, 64);
            this.mBtnNum[i + 1].setStateUpDn(mNumArrUp[i], mNumArrDn[i]);
            this.mBtnNum[i + 1].setOnClickListener(this);
            this.mBtnNum[i + 1].setTag(Integer.valueOf(i + 17));
        }
        this.mBtnNum[0] = this.mManager.AddButton(387, nY + 345, 109, 64);
        this.mBtnNum[0].setStateUpDn(R.drawable.can_an_num00_up, R.drawable.can_an_num00_dn);
        this.mBtnNum[0].setOnClickListener(this);
        this.mBtnNum[0].setTag(16);
        this.mBtnNum[10] = this.mManager.AddButton(262, nY + 345, 109, 64);
        this.mBtnNum[10].setStateUpDn(R.drawable.can_an_asterisk_up, R.drawable.can_an_asterisk_dn);
        this.mBtnNum[10].setOnClickListener(this);
        this.mBtnNum[10].setTag(26);
        this.mBtnNum[11] = this.mManager.AddButton(514, nY + 345, 109, 64);
        this.mBtnNum[11].setStateUpDn(R.drawable.can_an_well_up, R.drawable.can_an_well_dn);
        this.mBtnNum[11].setOnClickListener(this);
        this.mBtnNum[11].setTag(27);
        this.mBtnCall = this.mManager.AddButton(CanCameraUI.BTN_LANDWIND_3D_LEFT_DOWN, nY + 106, 108, 90);
        this.mBtnCall.setStateUpDn(R.drawable.can_an_call_up, R.drawable.can_an_call_dn);
        this.mBtnCall.setOnClickListener(this);
        this.mBtnCall.setTag(32);
        this.mBtnHangup = this.mManager.AddButton(CanCameraUI.BTN_LANDWIND_3D_LEFT_DOWN, nY + 211, 108, 90);
        this.mBtnHangup.setStateUpDn(R.drawable.can_an_hand_up, R.drawable.can_an_hand_dn);
        this.mBtnHangup.setOnClickListener(this);
        this.mBtnHangup.setTag(33);
        this.mBtnPWROff = this.mManager.AddButton(CanCameraUI.BTN_LANDWIND_3D_LEFT_DOWN, nY + KeyDef.RKEY_MEDIA_SLOW, 108, 90);
        this.mBtnPWROff.setStateUpDn(R.drawable.can_an_off_up, R.drawable.can_an_off_dn);
        this.mBtnPWROff.setOnClickListener(this);
        this.mBtnPWROff.setTag(34);
        this.mBtnBackSp = this.mManager.AddButton(691, nY + 19, 62, 56);
        this.mBtnBackSp.setStateUpDn(R.drawable.can_an_del_up, R.drawable.can_an_del_dn);
        this.mBtnBackSp.setOnClickListener(this);
        this.mBtnBackSp.setTag(35);
        this.mImgCall = this.mManager.AddImage(77, nY + 92, 93, 80);
        this.mTvInput = this.mManager.AddCusText(261, nY + 19, 421, 56);
        this.mTvInput.setGravity(21);
        this.mTvInput.SetPxSize(45);
        this.mTvInput.setEllipsize(TextUtils.TruncateAt.START);
        this.mTvInput.setSingleLine();
        this.mTvCall = this.mManager.AddCusText(9, nY + 270, Can.CAN_CC_HF_DJ, 118);
        this.mTvCall.setGravity(3);
        this.mTvCall.SetPxSize(50);
        this.mTvCall.setEllipsize(TextUtils.TruncateAt.END);
    }

    private void initCommonScreen() {
        this.mManager.AddImage(17, 14, R.drawable.can_an_bg);
        for (int i = 0; i < 9; i++) {
            this.mBtnNum[i + 1] = this.mManager.AddButton(((i % 3) * 165) + 348, ((i / 3) * 104) + 124);
            this.mBtnNum[i + 1].setStateUpDn(mNumArrUp[i], mNumArrDn[i]);
            this.mBtnNum[i + 1].setOnClickListener(this);
            this.mBtnNum[i + 1].setTag(Integer.valueOf(i + 17));
        }
        this.mBtnNum[0] = this.mManager.AddButton(513, 437);
        this.mBtnNum[0].setStateUpDn(R.drawable.can_an_num00_up, R.drawable.can_an_num00_dn);
        this.mBtnNum[0].setOnClickListener(this);
        this.mBtnNum[0].setTag(16);
        this.mBtnNum[10] = this.mManager.AddButton(348, 437);
        this.mBtnNum[10].setStateUpDn(R.drawable.can_an_asterisk_up, R.drawable.can_an_asterisk_dn);
        this.mBtnNum[10].setOnClickListener(this);
        this.mBtnNum[10].setTag(26);
        this.mBtnNum[11] = this.mManager.AddButton(681, 437);
        this.mBtnNum[11].setStateUpDn(R.drawable.can_an_well_up, R.drawable.can_an_well_dn);
        this.mBtnNum[11].setOnClickListener(this);
        this.mBtnNum[11].setTag(27);
        this.mBtnCall = this.mManager.AddButton(849, 125);
        this.mBtnCall.setStateUpDn(R.drawable.can_an_call_up, R.drawable.can_an_call_dn);
        this.mBtnCall.setOnClickListener(this);
        this.mBtnCall.setTag(32);
        this.mBtnHangup = this.mManager.AddButton(849, 264);
        this.mBtnHangup.setStateUpDn(R.drawable.can_an_hand_up, R.drawable.can_an_hand_dn);
        this.mBtnHangup.setOnClickListener(this);
        this.mBtnHangup.setTag(33);
        this.mBtnPWROff = this.mManager.AddButton(849, CanCameraUI.BTN_TRUMPCHI_GS4_MODE5);
        this.mBtnPWROff.setStateUpDn(R.drawable.can_an_off_up, R.drawable.can_an_off_dn);
        this.mBtnPWROff.setOnClickListener(this);
        this.mBtnPWROff.setTag(34);
        this.mBtnBackSp = this.mManager.AddButton(917, 19);
        this.mBtnBackSp.setStateUpDn(R.drawable.can_an_del_up, R.drawable.can_an_del_dn);
        this.mBtnBackSp.setOnClickListener(this);
        this.mBtnBackSp.setTag(35);
        this.mImgCall = this.mManager.AddImage(106, 115, 123, 106);
        this.mTvInput = this.mManager.AddCusText(349, 19, 556, 74);
        this.mTvInput.setGravity(21);
        this.mTvInput.SetPxSize(45);
        this.mTvInput.setEllipsize(TextUtils.TruncateAt.START);
        this.mTvInput.setSingleLine();
        this.mTvCall = this.mManager.AddCusText(29, KeyDef.RKEY_RDS_AF, 278, 118);
        this.mTvCall.setGravity(3);
        this.mTvCall.SetPxSize(60);
        this.mTvCall.setEllipsize(TextUtils.TruncateAt.END);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
        UpdateInputText();
        mThis = this;
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
        mThis = null;
    }

    private void ResetData(boolean check) {
        CanJni.GMGetOnStar(this.mStaData, this.mPhoneData);
        if (i2b(this.mStaData.UpdateOnce) && (!check || i2b(this.mStaData.Update))) {
            this.mStaData.Update = 0;
            switch (this.mStaData.Sta) {
                case 2:
                    this.mImgCall.setImageResource(R.drawable.can_an_phone1);
                    this.mImgCall.Show(true);
                    break;
                case 3:
                    this.mImgCall.setImageResource(R.drawable.can_an_phone2);
                    this.mImgCall.Show(true);
                    break;
                case 4:
                    this.mImgCall.setImageResource(R.drawable.can_an_phone);
                    this.mImgCall.Show(true);
                    break;
                default:
                    this.mImgCall.Show(false);
                    break;
            }
        }
        if (i2b(this.mPhoneData.UpdateOnce) && (!check || i2b(this.mPhoneData.Update))) {
            this.mPhoneData.Update = 0;
            this.mTvCall.setText(CanIF.byte2String(this.mPhoneData.szNum));
        }
        if (!check && this.mStaData.Sta == 0) {
            CanJni.GMOnStarCtrl(1);
        }
    }

    private void UpdateInputText() {
        this.mTvInput.setText(mStrNo);
    }

    public void onClick(View v) {
        int Id = ((Integer) v.getTag()).intValue();
        if (Id < 16 || Id > 27) {
            switch (Id) {
                case 32:
                    if (2 == this.mStaData.Sta) {
                        CanJni.GMOnStarCtrl(2);
                        return;
                    } else if (mStrNo != null && mStrNo.length() > 0) {
                        CanJni.GMOnStarDial(mStrNo);
                        return;
                    } else {
                        return;
                    }
                case 33:
                    if (this.mStaData.Sta > 1) {
                        CanJni.GMOnStarCtrl(3);
                    }
                    mStrNo = TXZResourceManager.STYLE_DEFAULT;
                    UpdateInputText();
                    return;
                case 34:
                    CanJni.GMOnStarCtrl(0);
                    return;
                case 35:
                    if (mStrNo.length() <= 1) {
                        mStrNo = TXZResourceManager.STYLE_DEFAULT;
                    } else {
                        mStrNo = mStrNo.substring(0, mStrNo.length() - 1);
                    }
                    UpdateInputText();
                    return;
                default:
                    return;
            }
        } else {
            if (4 == this.mStaData.Sta) {
                CanJni.GMOnStarCtrl((Id - 16) | 128);
            }
            if (26 == Id) {
                mStrNo = String.valueOf(mStrNo) + "*";
            } else if (27 == Id) {
                mStrNo = String.valueOf(mStrNo) + "#";
            } else {
                mStrNo = String.valueOf(mStrNo) + String.format("%c", new Object[]{Integer.valueOf((Id + 48) - 16)});
            }
            UpdateInputText();
        }
    }

    public void UserAll() {
        ResetData(true);
    }

    public void onChanged(int id, int pos) {
    }

    public static void HandleStaChange(int NewSta, int OldSta) {
        if (NewSta > 0) {
            if (mThis == null && !i2b(CanFunc.IsCamMode())) {
                CanFunc.showCanActivity(CanOnStarSpkActivity.class);
            }
            Evc.GetInstance().evol_aux_hold();
            return;
        }
        mStrNo = TXZResourceManager.STYLE_DEFAULT;
        if (OldSta > 0 && mThis != null) {
            mThis.finish();
        }
        Evc.GetInstance().evol_aux_release();
    }
}
