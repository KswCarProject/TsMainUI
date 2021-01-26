package com.ts.can.cadillac.withcd;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
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
import com.ts.other.CustomImgView;
import com.ts.other.CustomTextView;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;
import com.txznet.sdk.TXZResourceManager;
import com.yyw.ts70xhw.KeyDef;
import com.yyw.ts70xhw.Mcu;

public class CanCadillacACBottomView implements View.OnTouchListener {
    public static final int ITEM_BTN_LIGHT = 1;
    public static final int ITEM_BTN_LT_TEMP_DEC = 4;
    public static final int ITEM_BTN_LT_TEMP_INC = 3;
    public static final int ITEM_BTN_OFF = 2;
    public static final int ITEM_BTN_OPEN_AC = 19;
    public static final int ITEM_BTN_RT_TEMP_DEC = 14;
    public static final int ITEM_BTN_RT_TEMP_INC = 13;
    public static final int ITEM_BTN_VOL_DEC = 18;
    public static final int ITEM_BTN_VOL_INC = 17;
    public static final int ITEM_BTN_WIND_DEC = 5;
    public static final int ITEM_BTN_WIND_INC = 6;
    private static final String TAG = "CanCadillacACBottomView";
    private CanDataInfo.CAN_ACInfo mAcInfo;
    private CustomImgView mBtnAc;
    private CustomImgView mBtnAuto;
    private CustomImgView mBtnFrontWind;
    private ParamButton mBtnLight;
    private CustomImgView mBtnLoopMode;
    private ParamButton mBtnLtTempDec;
    private ParamButton mBtnLtTempInc;
    private CustomImgView mBtnMax;
    private ParamButton mBtnOff;
    private ParamButton mBtnRtTempDec;
    private ParamButton mBtnRtTempInc;
    private CustomImgView mBtnSync;
    private ParamButton mBtnVolDec;
    private ParamButton mBtnVolInc;
    private ParamButton mBtnWindDec;
    private ParamButton mBtnWindInc;
    private Context mContext;
    /* access modifiers changed from: private */
    public Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            if (CanFunc.mCanInit == 1) {
                CanCadillacACBottomView.this.updateAC();
            }
            CanCadillacACBottomView.this.mHandler.sendEmptyMessageDelayed(0, 200);
        }
    };
    private CustomImgView[] mIvWindMode = new CustomImgView[3];
    private CustomImgView[] mIvWinds = new CustomImgView[8];
    private RelativeLayout mLayout;
    private OnUpdateListener mListener;
    private RelativeLayoutManager mManager;
    private CustomTextView mTvLtTemp;
    private CustomTextView mTvRtTemp;
    private int[] mWindDnIcons = {R.drawable.cad_ac_bel_pro00_dn, R.drawable.cad_ac_bel_pro01_dn, R.drawable.cad_ac_bel_pro02_dn, R.drawable.cad_ac_bel_pro03_dn, R.drawable.cad_ac_bel_pro04_dn, R.drawable.cad_ac_bel_pro05_dn, R.drawable.cad_ac_bel_pro06_dn, R.drawable.cad_ac_bel_pro07_dn};
    private int[] mWindUpIcons = {R.drawable.cad_ac_bel_pro00_up, R.drawable.cad_ac_bel_pro01_up, R.drawable.cad_ac_bel_pro02_up, R.drawable.cad_ac_bel_pro03_up, R.drawable.cad_ac_bel_pro04_up, R.drawable.cad_ac_bel_pro05_up, R.drawable.cad_ac_bel_pro06_up, R.drawable.cad_ac_bel_pro07_up};

    public interface OnUpdateListener {
        void onOpenAC();

        void onUpdateFinish();
    }

    public void setOnUpdateListener(OnUpdateListener listener) {
        this.mListener = listener;
    }

    public CanCadillacACBottomView(Context context) {
        this.mContext = context;
        this.mLayout = new RelativeLayout(context);
        this.mManager = new RelativeLayoutManager(this.mLayout);
        addChildViews();
        this.mHandler.sendEmptyMessageDelayed(0, 200);
        if (CanFunc.mFsCanTp != 88) {
            CanJni.CadillacWithCDQuery(50);
        }
    }

    public RelativeLayout getView() {
        return this.mLayout;
    }

    private void addChildViews() {
        this.mManager.AddImage(0, 0, 768, 131).setBackgroundResource(R.drawable.cad_ac_bel_bg);
        this.mBtnLight = AddBtn(1, 3, 8, 92, 57, R.drawable.cad_ac_bel_bright_up, R.drawable.cad_ac_bel_bright_dn);
        this.mBtnOff = AddBtn(2, 3, 69, 92, 57, R.drawable.cad_ac_bel_off_up, R.drawable.cad_ac_bel_off_dn);
        this.mBtnVolInc = AddBtn(17, 674, 8, 92, 57, R.drawable.cad_ac_bel_addsound_up, R.drawable.cad_ac_bel_addsound_dn);
        this.mBtnVolDec = AddBtn(18, 674, 69, 92, 57, R.drawable.cad_ac_bel_resound_up, R.drawable.cad_ac_bel_resound_dn);
        this.mBtnLtTempInc = AddBtn(3, 96, 8, 92, 57, R.drawable.cad_ac_bel_add_up, R.drawable.cad_ac_bel_add_dn);
        this.mBtnLtTempDec = AddBtn(4, 96, 69, 92, 57, R.drawable.cad_ac_bel_reduce_up, R.drawable.cad_ac_bel_reduce_dn);
        this.mBtnRtTempInc = AddBtn(13, CanCameraUI.BTN_CAMERY_2018_MODE2, 8, 92, 57, R.drawable.cad_ac_bel_add_up, R.drawable.cad_ac_bel_add_dn);
        this.mBtnRtTempDec = AddBtn(14, CanCameraUI.BTN_CAMERY_2018_MODE2, 69, 92, 57, R.drawable.cad_ac_bel_reduce_up, R.drawable.cad_ac_bel_reduce_dn);
        this.mTvLtTemp = AddTemp(110, 54, 69, 20);
        this.mTvRtTemp = AddTemp(595, 54, 69, 20);
        this.mBtnWindDec = AddBtn(5, 255, 8, 60, 43, R.drawable.cad_ac_bel_fan01_up, R.drawable.cad_ac_bel_fan01_dn);
        this.mBtnWindInc = AddBtn(6, 461, 8, 60, 43, R.drawable.cad_ac_bel_fan02_up, R.drawable.cad_ac_bel_fan02_dn);
        for (int i = 0; i < this.mIvWinds.length; i++) {
            this.mIvWinds[i] = AddImage((i * 16) + KeyDef.RKEY_RADIO_6S, 12, 8, 34, this.mWindUpIcons[i]);
        }
        this.mBtnFrontWind = AddImage(Can.CAN_CHRYSLER_ONE_HC, 52, 64, 35, R.drawable.cad_ac_bel_wind_up);
        this.mBtnAc = AddImage(KeyDef.RKEY_EJECT, 52, 64, 35, R.drawable.cad_ac_bel_ac_up);
        this.mBtnSync = AddImage(395, 52, 64, 35, R.drawable.cad_ac_bel_sync_up);
        this.mBtnAuto = AddImage(474, 52, 64, 35, R.drawable.cad_ac_bel_auto_up);
        this.mBtnMax = AddImage(KeyDef.RKEY_OPEN, 88, 64, 35, R.drawable.cad_ac_bel_max_up);
        this.mBtnLoopMode = AddImage(395, 88, 64, 35, R.drawable.cad_ac_bel_wxh_up);
        this.mIvWindMode[0] = AddImage(Can.CAN_FLAT_WC, 88, 64, 35, R.drawable.cad_ac_bel_jt01_dn);
        this.mIvWindMode[1] = AddImage(Can.CAN_FLAT_WC, 88, 64, 35, R.drawable.cad_ac_bel_jt02_dn);
        this.mIvWindMode[2] = AddImage(Can.CAN_FLAT_WC, 88, 64, 35, R.drawable.cad_ac_bel_jt03_dn);
        AddImage(Can.CAN_FLAT_WC, 88, 64, 35, R.drawable.cad_ac_bel_people_up);
        AddBtn(19, 461, 71, 88, 60, R.drawable.cad_ac_bel_tab_up, R.drawable.cad_ac_bel_tab_dn);
    }

    public void updateAC() {
        Can.updateAC();
        ResetData();
        if (this.mListener != null) {
            this.mListener.onUpdateFinish();
        }
    }

    private void ResetData() {
        int i;
        int i2;
        int i3 = 0;
        this.mAcInfo = Can.mACInfo;
        this.mAcInfo.Update = 0;
        if (this.mAcInfo.PWR == 0) {
            this.mBtnFrontWind.setImageResource(R.drawable.cad_ac_bel_wind_up);
            this.mBtnAc.setImageResource(R.drawable.cad_ac_bel_ac_up);
            this.mBtnSync.setImageResource(R.drawable.cad_ac_bel_sync_up);
            this.mBtnAuto.setImageResource(R.drawable.cad_ac_bel_auto_up);
            this.mBtnMax.setImageResource(R.drawable.cad_ac_bel_max_up);
            this.mIvWindMode[0].setVisibility(8);
            this.mIvWindMode[1].setVisibility(8);
            this.mIvWindMode[2].setVisibility(8);
        } else {
            if (this.mAcInfo.fgDFBL == 1) {
                this.mBtnFrontWind.setImageResource(R.drawable.cad_ac_bel_wind_dn);
            } else {
                this.mBtnFrontWind.setImageResource(R.drawable.cad_ac_bel_wind_up);
            }
            if (this.mAcInfo.fgAC == 1) {
                this.mBtnAc.setImageResource(R.drawable.cad_ac_bel_ac_dn);
            } else {
                this.mBtnAc.setImageResource(R.drawable.cad_ac_bel_ac_up);
            }
            if (this.mAcInfo.fgDual == 1) {
                this.mBtnSync.setImageResource(R.drawable.cad_ac_bel_sync_dn);
            } else {
                this.mBtnSync.setImageResource(R.drawable.cad_ac_bel_sync_up);
            }
            if (this.mAcInfo.nAutoLight == 1) {
                this.mBtnAuto.setImageResource(R.drawable.cad_ac_bel_auto_dn);
            } else {
                this.mBtnAuto.setImageResource(R.drawable.cad_ac_bel_auto_up);
            }
            if (CanJni.GetCanFsTp() == 88) {
                if (this.mAcInfo.fgMaxFornt == 1) {
                    this.mBtnMax.setImageResource(R.drawable.cad_ac_bel_max_dn);
                } else {
                    this.mBtnMax.setImageResource(R.drawable.cad_ac_bel_max_up);
                }
            } else if (this.mAcInfo.fgACMax == 1) {
                this.mBtnMax.setImageResource(R.drawable.cad_ac_bel_max_dn);
            } else {
                this.mBtnMax.setImageResource(R.drawable.cad_ac_bel_max_up);
            }
            CustomImgView customImgView = this.mIvWindMode[0];
            if (this.mAcInfo.fgUpWind == 1) {
                i = 0;
            } else {
                i = 8;
            }
            customImgView.setVisibility(i);
            CustomImgView customImgView2 = this.mIvWindMode[1];
            if (this.mAcInfo.fgParallelWind == 1) {
                i2 = 0;
            } else {
                i2 = 8;
            }
            customImgView2.setVisibility(i2);
            CustomImgView customImgView3 = this.mIvWindMode[2];
            if (this.mAcInfo.fgDownWind != 1) {
                i3 = 8;
            }
            customImgView3.setVisibility(i3);
        }
        if (this.mAcInfo.fgAQS == 1) {
            this.mBtnLoopMode.setImageResource(R.drawable.cad_ac_bel_a_up);
        } else if (this.mAcInfo.fgInnerLoop == 1) {
            this.mBtnLoopMode.setImageResource(R.drawable.cad_ac_bel_nxh_dn);
        } else {
            this.mBtnLoopMode.setImageResource(R.drawable.cad_ac_bel_wxh_up);
        }
        int wind = this.mAcInfo.nWindValue;
        for (int i4 = 0; i4 < this.mIvWinds.length; i4++) {
            if (CanJni.GetCanFsTp() == 88) {
                if (i4 < wind) {
                    this.mIvWinds[i4].setImageResource(this.mWindDnIcons[i4]);
                } else {
                    this.mIvWinds[i4].setImageResource(this.mWindUpIcons[i4]);
                }
            } else if (i4 < (wind + 1) / 2) {
                this.mIvWinds[i4].setImageResource(this.mWindDnIcons[i4]);
            } else {
                this.mIvWinds[i4].setImageResource(this.mWindUpIcons[i4]);
            }
        }
        this.mTvLtTemp.setText(this.mAcInfo.szLtTemp);
        this.mTvRtTemp.setText(this.mAcInfo.szRtTemp);
    }

    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        int Id = ((Integer) v.getTag()).intValue();
        if (action == 0) {
            Log.d(TAG, "****ACTION_DOWN*****");
            v.setSelected(true);
            if (CanJni.GetCanFsTp() != 88) {
                switch (Id) {
                    case 1:
                        if (Mcu.BklisOn() > 0) {
                            Mcu.SetCkey(6);
                            break;
                        }
                        break;
                    case 2:
                        CanJni.CadillacWithCDSendKey(74, 1);
                        break;
                    case 3:
                        CanJni.CadillacWithCDSendKey(64, 1);
                        break;
                    case 4:
                        CanJni.CadillacWithCDSendKey(65, 1);
                        break;
                    case 5:
                        CanJni.CadillacWithCDSendKey(69, 1);
                        break;
                    case 6:
                        CanJni.CadillacWithCDSendKey(68, 1);
                        break;
                    case 13:
                        CanJni.CadillacWithCDSendKey(66, 1);
                        break;
                    case 14:
                        CanJni.CadillacWithCDSendKey(67, 1);
                        break;
                    case 17:
                        CanJni.CadillacWithCDSendKey(49, 1);
                        break;
                    case 18:
                        CanJni.CadillacWithCDSendKey(48, 1);
                        break;
                    case 19:
                        if (this.mListener != null && (CanJni.GetCanFsTp() == 118 || CanJni.GetCanFsTp() == 88)) {
                            this.mListener.onOpenAC();
                            break;
                        }
                }
            } else {
                switch (Id) {
                    case 1:
                        if (Mcu.BklisOn() > 0) {
                            Mcu.SetCkey(6);
                            break;
                        }
                        break;
                    case 2:
                        CanJni.GmSbAcSet(8, 1);
                        break;
                    case 3:
                        CanJni.GmSbAcSet(2, 1);
                        break;
                    case 4:
                        CanJni.GmSbAcSet(2, 2);
                        break;
                    case 5:
                        CanJni.GmSbAcSet(5, 2);
                        break;
                    case 6:
                        CanJni.GmSbAcSet(5, 1);
                        break;
                    case 13:
                        CanJni.GmSbAcSet(7, 1);
                        break;
                    case 14:
                        CanJni.GmSbAcSet(7, 2);
                        break;
                    case 17:
                        CanJni.GmSbCarKeyCtl(16, 1);
                        break;
                    case 18:
                        CanJni.GmSbCarKeyCtl(17, 1);
                        break;
                    case 19:
                        if (this.mListener != null && (CanJni.GetCanFsTp() == 118 || CanJni.GetCanFsTp() == 88)) {
                            this.mListener.onOpenAC();
                            break;
                        }
                }
            }
        } else if (1 == action) {
            Log.d(TAG, "****ACTION_UP*****");
            v.setSelected(false);
            if (CanJni.GetCanFsTp() == 88) {
                switch (Id) {
                    case 2:
                        CanJni.GmSbAcSet(8, 0);
                        break;
                    case 3:
                        CanJni.GmSbAcSet(2, 0);
                        break;
                    case 4:
                        CanJni.GmSbAcSet(2, 0);
                        break;
                    case 5:
                        CanJni.GmSbAcSet(5, 0);
                        break;
                    case 6:
                        CanJni.GmSbAcSet(5, 0);
                        break;
                    case 13:
                        CanJni.GmSbAcSet(7, 0);
                        break;
                    case 14:
                        CanJni.GmSbAcSet(7, 0);
                        break;
                    case 17:
                        CanJni.GmSbCarKeyCtl(16, 0);
                        break;
                    case 18:
                        CanJni.GmSbCarKeyCtl(17, 0);
                        break;
                }
            } else {
                CanJni.CadillacWithCDSendKey(0, 0);
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
        temp.setText(TXZResourceManager.STYLE_DEFAULT);
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
