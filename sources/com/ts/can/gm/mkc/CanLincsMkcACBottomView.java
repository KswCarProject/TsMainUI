package com.ts.can.gm.mkc;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanCameraUI;
import com.ts.can.CanFunc;
import com.ts.can.toyota.dj.CanToyotaDJCarDeviceView;
import com.ts.other.CustomImgView;
import com.ts.other.CustomTextView;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;
import com.yyw.ts70xhw.Iop;
import com.yyw.ts70xhw.KeyDef;
import com.yyw.ts70xhw.Mcu;

public class CanLincsMkcACBottomView implements View.OnTouchListener {
    public static final int ITEM_BTN_LIGHT = 8;
    public static final int ITEM_BTN_NEXT = 7;
    public static final int ITEM_BTN_OPEN_AC = 9;
    public static final int ITEM_BTN_POWER = 1;
    public static final int ITEM_BTN_PRE = 6;
    public static final int ITEM_BTN_VOL_DEC = 3;
    public static final int ITEM_BTN_VOL_INC = 2;
    public static final int ITEM_BTN_WIND_DEC = 4;
    public static final int ITEM_BTN_WIND_INC = 5;
    private static final String TAG = "CanMkcACBottomView";
    private static CanLincsMkcACBottomView sInstance;
    private CanDataInfo.CAN_ACInfo mAcInfo;
    private CustomImgView mBtnAc;
    private CustomImgView mBtnAuto;
    private CustomImgView mBtnFrontWind;
    private ParamButton mBtnLight;
    private CustomImgView mBtnLoopMode;
    private ParamButton mBtnNext;
    private ParamButton mBtnPower;
    private ParamButton mBtnPre;
    private CustomImgView mBtnRearHot;
    private CustomImgView mBtnSync;
    private ParamButton mBtnVolDec;
    private ParamButton mBtnVolInc;
    private ParamButton mBtnWindDec;
    private ParamButton mBtnWindInc;
    private Context mContext;
    private CustomImgView[] mIvWindMode = new CustomImgView[3];
    private CustomImgView[] mIvWinds = new CustomImgView[7];
    private RelativeLayout mLayout;
    private OnUpdateListener mListener;
    private RelativeLayoutManager mManager;
    private CustomTextView mTvLtTemp;
    private CustomTextView mTvRtTemp;
    private int[] mWindDnIcons = {R.drawable.lin_ac_bel_pro01_dn, R.drawable.lin_ac_bel_pro02_dn, R.drawable.lin_ac_bel_pro03_dn, R.drawable.lin_ac_bel_pro04_dn, R.drawable.lin_ac_bel_pro05_dn, R.drawable.lin_ac_bel_pro06_dn, R.drawable.lin_ac_bel_pro07_dn};
    private int[] mWindUpIcons = {R.drawable.lin_ac_bel_pro01_up, R.drawable.lin_ac_bel_pro02_up, R.drawable.lin_ac_bel_pro03_up, R.drawable.lin_ac_bel_pro04_up, R.drawable.lin_ac_bel_pro05_up, R.drawable.lin_ac_bel_pro06_up, R.drawable.lin_ac_bel_pro07_up};

    public interface OnUpdateListener {
        void onOpenAC();

        void onUpdateFinish();
    }

    public void setOnUpdateListener(OnUpdateListener listener) {
        this.mListener = listener;
    }

    public static CanLincsMkcACBottomView getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new CanLincsMkcACBottomView(context);
        }
        return sInstance;
    }

    private CanLincsMkcACBottomView(Context context) {
        this.mContext = context;
        this.mLayout = new RelativeLayout(context);
        this.mManager = new RelativeLayoutManager(this.mLayout);
        addChildViews();
    }

    public RelativeLayout getView() {
        return this.mLayout;
    }

    private void addChildViews() {
        this.mManager.AddImage(0, 0, CanToyotaDJCarDeviceView.ITEM_PLAY, 146).setBackgroundResource(R.drawable.lin_ac_bel_bg);
        this.mTvLtTemp = AddTemp(209, 12, 60, 43);
        this.mTvRtTemp = AddTemp(507, 12, 60, 43);
        this.mBtnLight = AddBtn(8, 692, 30, 60, 93, R.drawable.lin_ac_bel_closed_up, R.drawable.lin_ac_bel_closed_dn);
        this.mBtnPower = AddBtn(1, 14, 30, 60, 93, R.drawable.lin_ac_bel_shut_up, R.drawable.lin_ac_bel_shut_dn);
        this.mBtnVolInc = AddBtn(2, 145, 30, 60, 93, R.drawable.lin_ac_bel_vad_up, R.drawable.lin_ac_bel_vad_dn);
        this.mBtnVolDec = AddBtn(3, 84, 30, 60, 93, R.drawable.lin_ac_bel_vre_up, R.drawable.lin_ac_bel_vre_dn);
        this.mBtnPre = AddBtn(6, CanCameraUI.BTN_TRUMPCHI_GS7_MODE3, 30, 60, 93, R.drawable.lin_ac_bel_up_up, R.drawable.lin_ac_bel_up_dn);
        this.mBtnNext = AddBtn(7, CanCameraUI.BTN_CCH9_MODE14, 30, 60, 93, R.drawable.lin_ac_bel_dn_up, R.drawable.lin_ac_bel_dn_dn);
        this.mBtnWindDec = AddBtn(4, 270, 12, 60, 43, R.drawable.lin_ac_bel_fan01_up, R.drawable.lin_ac_bel_fan01_dn);
        this.mBtnWindInc = AddBtn(5, 446, 12, 60, 43, R.drawable.lin_ac_bel_fan02_up, R.drawable.lin_ac_bel_fan02_dn);
        for (int i = 0; i < this.mIvWinds.length; i++) {
            this.mIvWinds[i] = AddImage((i * 16) + KeyDef.RKEY_POWER_ON, 16, 8, 34, this.mWindUpIcons[i]);
        }
        this.mBtnFrontWind = AddImage(Can.CAN_NISSAN_RICH6_WC, 75, 60, 31, R.drawable.lin_ac_bel_wind_up);
        this.mBtnAc = AddImage(Can.CAN_SAIL_RW550_MG6_WC, 110, 60, 31, R.drawable.lin_ac_bel_ac_up);
        this.mBtnSync = AddImage(CanCameraUI.BTN_HMS7_MODE2, 110, 60, 31, R.drawable.lin_ac_bel_sync_up);
        this.mBtnAuto = AddImage(268, 110, 60, 31, R.drawable.lin_ac_bel_auto_up);
        this.mBtnRearHot = AddImage(470, 75, 60, 31, R.drawable.lin_ac_bel_bwind_up);
        this.mBtnLoopMode = AddImage(435, 110, 60, 31, R.drawable.lin_ac_bel_wxh_up);
        this.mIvWindMode[0] = AddImage(345, 64, 31, 20, R.drawable.lin_ac_bel_jt01_up);
        this.mIvWindMode[1] = AddImage(345, 78, 31, 20, R.drawable.lin_ac_bel_jt02_up);
        this.mIvWindMode[2] = AddImage(345, 92, 31, 20, R.drawable.lin_ac_bel_jt03_up);
        AddImage(377, 64, 48, 50, R.drawable.lin_ac_bel_people_up);
        AddBtn(9, 344, 93, 80, 53, R.drawable.lin_ac_bel_tab_up, R.drawable.lin_ac_bel_tab_dn);
    }

    public void updateAC() {
        Can.updateAC();
        ResetData();
        if (this.mListener != null) {
            this.mListener.onUpdateFinish();
        }
    }

    private void ResetData() {
        this.mAcInfo = Can.mACInfo;
        this.mAcInfo.Update = 0;
        if (this.mAcInfo.fgDFBL == 1) {
            this.mBtnFrontWind.setImageResource(R.drawable.lin_ac_bel_wind_dn);
        } else {
            this.mBtnFrontWind.setImageResource(R.drawable.lin_ac_bel_wind_up);
        }
        if (this.mAcInfo.fgAC == 1) {
            this.mBtnAc.setImageResource(R.drawable.lin_ac_bel_ac_dn);
        } else {
            this.mBtnAc.setImageResource(R.drawable.lin_ac_bel_ac_up);
        }
        if (this.mAcInfo.fgDual == 1) {
            this.mBtnSync.setImageResource(R.drawable.lin_ac_bel_sync_dn);
        } else {
            this.mBtnSync.setImageResource(R.drawable.cad_ac_bel_sync_up);
        }
        if (this.mAcInfo.nAutoLight == 1) {
            this.mBtnAuto.setImageResource(R.drawable.lin_ac_bel_auto_dn);
        } else {
            this.mBtnAuto.setImageResource(R.drawable.lin_ac_bel_auto_up);
        }
        if (this.mAcInfo.fgRearLight == 1) {
            this.mBtnRearHot.setImageResource(R.drawable.lin_ac_bel_bwind_dn);
        } else {
            this.mBtnRearHot.setImageResource(R.drawable.lin_ac_bel_bwind_up);
        }
        if (this.mAcInfo.fgAQS == 1) {
            this.mBtnLoopMode.setImageResource(R.drawable.lin_ac_bel_a_dn);
        } else if (this.mAcInfo.fgInnerLoop == 1) {
            this.mBtnLoopMode.setImageResource(R.drawable.lin_ac_bel_nxh_dn);
        } else {
            this.mBtnLoopMode.setImageResource(R.drawable.lin_ac_bel_wxh_dn);
        }
        if (this.mAcInfo.fgUpWind > 0) {
            this.mIvWindMode[0].setImageResource(R.drawable.lin_ac_bel_jt01_dn);
        } else {
            this.mIvWindMode[0].setImageResource(R.drawable.lin_ac_bel_jt01_up);
        }
        if (this.mAcInfo.fgParallelWind > 0) {
            this.mIvWindMode[1].setImageResource(R.drawable.lin_ac_bel_jt02_dn);
        } else {
            this.mIvWindMode[1].setImageResource(R.drawable.lin_ac_bel_jt02_up);
        }
        if (this.mAcInfo.fgDownWind > 0) {
            this.mIvWindMode[2].setImageResource(R.drawable.lin_ac_bel_jt03_dn);
        } else {
            this.mIvWindMode[2].setImageResource(R.drawable.lin_ac_bel_jt03_up);
        }
        int wind = this.mAcInfo.nWindValue;
        for (int i = 0; i < this.mIvWinds.length; i++) {
            if (i < wind) {
                this.mIvWinds[i].setImageResource(this.mWindDnIcons[i]);
            } else {
                this.mIvWinds[i].setImageResource(this.mWindUpIcons[i]);
            }
        }
        this.mTvLtTemp.setText(this.mAcInfo.szLtTemp);
        this.mTvRtTemp.setText(this.mAcInfo.szRtTemp);
    }

    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        int Id = ((Integer) v.getTag()).intValue();
        if (CanFunc.mCanInit == 1) {
            if (action != 0) {
                if (1 == action) {
                    Log.d(TAG, "****ACTION_UP*****");
                    v.setSelected(false);
                    switch (Id) {
                        case 1:
                            CanJni.GmSbCarKeyCtl(20, 0);
                            break;
                        case 2:
                            CanJni.GmSbCarKeyCtl(16, 0);
                            break;
                        case 3:
                            CanJni.GmSbCarKeyCtl(17, 0);
                            break;
                        case 4:
                            CanJni.GmSbAcSet(5, 0);
                            break;
                        case 5:
                            CanJni.GmSbAcSet(5, 0);
                            break;
                        case 6:
                            if (Iop.GetWorkMode() == 12) {
                                CanJni.GmSbCarKeyCtl(25, 0);
                                break;
                            }
                            break;
                        case 7:
                            if (Iop.GetWorkMode() == 12) {
                                CanJni.GmSbCarKeyCtl(24, 0);
                                break;
                            }
                            break;
                    }
                }
            } else {
                Log.d(TAG, "****ACTION_DOWN*****");
                v.setSelected(true);
                switch (Id) {
                    case 1:
                        CanJni.GmSbCarKeyCtl(20, 1);
                        break;
                    case 2:
                        CanJni.GmSbCarKeyCtl(16, 1);
                        break;
                    case 3:
                        CanJni.GmSbCarKeyCtl(17, 1);
                        break;
                    case 4:
                        CanJni.GmSbAcSet(5, 2);
                        break;
                    case 5:
                        CanJni.GmSbAcSet(5, 1);
                        break;
                    case 6:
                        if (Iop.GetWorkMode() != 12) {
                            Mcu.SetCkey(45);
                            break;
                        } else {
                            CanJni.GmSbCarKeyCtl(25, 1);
                            break;
                        }
                    case 7:
                        if (Iop.GetWorkMode() != 12) {
                            Mcu.SetCkey(44);
                            break;
                        } else {
                            CanJni.GmSbCarKeyCtl(24, 1);
                            break;
                        }
                    case 8:
                        Mcu.SetCkey(6);
                        break;
                    case 9:
                        if (this.mListener != null && CanJni.GetCanFsTp() == 88) {
                            this.mListener.onOpenAC();
                            break;
                        }
                }
            }
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public ParamButton AddStatus(int x, int y, int w, int h, int up, int dn) {
        ParamButton btn = this.mManager.AddButton(x, y, w, h);
        btn.setDrawable(up, dn);
        return btn;
    }

    /* access modifiers changed from: protected */
    public ParamButton AddBtn(int id, int x, int y, int w, int h, int up, int dn) {
        ParamButton btn = this.mManager.AddButton(x, y, w, h);
        btn.setTag(Integer.valueOf(id));
        btn.setOnTouchListener(this);
        btn.setDrawable(up, dn);
        return btn;
    }

    /* access modifiers changed from: protected */
    public CustomTextView AddTemp(int x, int y, int w, int h) {
        CustomTextView temp = this.mManager.AddCusText(x, y, w, h);
        temp.SetPxSize(24);
        temp.setText("--℃");
        temp.setGravity(17);
        return temp;
    }

    /* access modifiers changed from: protected */
    public CustomTextView AddText(int x, int y, int w, int h) {
        CustomTextView temp = this.mManager.AddCusText(x, y, w, h);
        temp.SetPxSize(28);
        temp.setText("");
        temp.setGravity(17);
        return temp;
    }

    /* access modifiers changed from: protected */
    public CustomImgView AddImage(int x, int y, int w, int h, int resId) {
        CustomImgView image = this.mManager.AddImage(x, y, w, h);
        if (resId != 0) {
            image.setImageResource(resId);
        }
        return image;
    }
}
