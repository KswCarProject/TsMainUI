package com.ts.can.gm.wc;

import android.app.Activity;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.Evc;
import com.ts.MainUI.R;
import com.ts.can.CanCameraUI;
import com.ts.can.CanCarInfoSub1Activity;
import com.ts.can.CanFunc;
import com.ts.can.CanRelativeCarInfoView;
import com.ts.other.CustomImgView;
import com.ts.other.CustomTextView;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;
import com.txznet.sdk.TXZResourceManager;
import com.yyw.ts70xhw.KeyDef;

public class CanGMWcOnStarSpkView extends CanRelativeCarInfoView {
    public static final int ITEM_BKSP = 35;
    public static final int ITEM_CALL = 32;
    public static final int ITEM_HANGUP = 33;
    public static final int ITEM_NUM0 = 16;
    public static final int ITEM_NUM9 = 25;
    public static final int ITEM_NUMJ = 27;
    public static final int ITEM_NUMX = 26;
    public static final int ITEM_PWROFF = 34;
    protected static final int[] mNumArrDn = {R.drawable.can_an_num01_dn, R.drawable.can_an_num02_dn, R.drawable.can_an_num03_dn, R.drawable.can_an_num04_dn, R.drawable.can_an_num05_dn, R.drawable.can_an_num06_dn, R.drawable.can_an_num07_dn, R.drawable.can_an_num08_dn, R.drawable.can_an_num09_dn};
    protected static final int[] mNumArrUp = {R.drawable.can_an_num01_up, R.drawable.can_an_num02_up, R.drawable.can_an_num03_up, R.drawable.can_an_num04_up, R.drawable.can_an_num05_up, R.drawable.can_an_num06_up, R.drawable.can_an_num07_up, R.drawable.can_an_num08_up, R.drawable.can_an_num09_up};
    private static String mStrNo = TXZResourceManager.STYLE_DEFAULT;
    private static Activity mThis = null;
    protected ParamButton mBtnBackSp;
    protected ParamButton mBtnCall;
    protected ParamButton mBtnHangup;
    protected ParamButton[] mBtnNum;
    protected ParamButton mBtnPWROff;
    protected CustomImgView mImgCall;
    protected RelativeLayoutManager mManager;
    private CanDataInfo.GM_PhoneInfo mPhoneData;
    private CanDataInfo.GM_OnStarSta mStaData;
    protected CustomTextView mTvCall;
    protected CustomTextView mTvInput;

    public CanGMWcOnStarSpkView(Activity activity) {
        super(activity);
    }

    public boolean onTouch(View arg0, MotionEvent arg1) {
        return false;
    }

    public void onClick(View v) {
        int Id = ((Integer) v.getTag()).intValue();
        if (Id < 16 || Id > 27) {
            switch (Id) {
                case 32:
                    if (2 == this.mStaData.Sta) {
                        CanJni.GmWcCarOnStarSet(1, 0);
                        return;
                    } else if (mStrNo == null || mStrNo.length() <= 0) {
                        CanJni.GmWcCarOnStarSet(7, 0);
                        return;
                    } else {
                        CanJni.GmWcCarOnStarDial(mStrNo);
                        return;
                    }
                case 33:
                    if (this.mStaData.Sta > 1) {
                        CanJni.GmWcCarOnStarSet(2, 0);
                        CanJni.GmWcCarOnStarSet(3, 0);
                    }
                    mStrNo = TXZResourceManager.STYLE_DEFAULT;
                    UpdateInputText();
                    return;
                case 34:
                    CanJni.GmWcCarOnStarSet(8, 0);
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
                if (26 == Id) {
                    CanJni.GmWcCarOnStarSet(4, 42);
                } else if (27 == Id) {
                    CanJni.GmWcCarOnStarSet(4, 35);
                } else {
                    CanJni.GmWcCarOnStarSet(4, (Id - 16) + 48);
                }
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

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mManager = getRelativeManager();
        this.mManager.AddImage(17, 14, R.drawable.can_an_bg);
        this.mBtnNum = new ParamButton[12];
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
        this.mStaData = new CanDataInfo.GM_OnStarSta();
        this.mPhoneData = new CanDataInfo.GM_PhoneInfo();
    }

    public void doOnResume() {
        UpdateInputText();
        mThis = getActivity();
        CanJni.GmWcCarOnStarSet(8, 1);
    }

    private void UpdateInputText() {
        this.mTvInput.setText(mStrNo);
    }

    public void doOnPause() {
        super.doOnPause();
        mThis = null;
        CanJni.GmWcCarOnStarSet(8, 0);
    }

    public void ResetData(boolean check) {
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
            this.mTvCall.setText(new String(this.mPhoneData.szNum));
        }
        if (!check && this.mStaData.Sta == 0) {
            CanJni.GmWcCarOnStarSet(8, 1);
        }
    }

    public void QueryData() {
    }

    public static void HandleStaChange(int NewSta, int OldSta) {
        if (NewSta > 0) {
            if (mThis == null) {
                if (!(CanFunc.IsCamMode() != 0)) {
                    CanFunc.showCanActivity(CanCarInfoSub1Activity.class, -1);
                }
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
