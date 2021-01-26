package com.ts.can.audi.rzc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.SystemClock;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.ts.MainUI.R;
import com.ts.can.CanACActivity;
import com.ts.can.CanBaseACView;
import com.ts.can.CanFunc;
import com.ts.main.common.MainSet;
import com.ts.other.CustomImgView;
import com.ts.other.RelativeLayoutManager;
import com.txznet.sdk.TXZPoiSearchManager;
import com.yyw.ts70xhw.KeyDef;

public class CanAudiQ5RzcACView extends CanBaseACView {
    private static int AC_SHOW_TIME = TXZPoiSearchManager.DEFAULT_NEARBY_RADIUS;
    public static final String TAG = "CanAudiQ5RzcACView";
    public static final int TEMP_STYLE_GQCQ = 1;
    public static final int TEMP_STYLE_NORMAL = 0;
    private CanDataInfo.CAN_ACInfo mACInfo;
    private ImageView mAc;
    private ImageView mAcMax;
    private ImageView mAuto;
    private TextView mAutoMode;
    private TextView mAutolMode;
    private ImageView mDual;
    private ImageView mEco;
    private ImageView mForeLight;
    private ImageView mFrontMax;
    private long mLastTime = 0;
    private CustomImgView[] mLeftWind;
    private ImageView mLoop;
    private ImageView mLtFire;
    private ImageView[] mLtHot;
    private TextView mLtTemp;
    private CustomImgView[] mLtTempLevel;
    private RelativeLayoutManager mManager;
    private ImageView mRearLight;
    private ImageView mRearLock;
    private CustomImgView[] mRightWind;
    private ImageView mRtFire;
    private ImageView[] mRtHot;
    private TextView mRtTemp;
    private CustomImgView[] mRtTempLevel;
    private int mTempStyle = 0;
    private ImageView mWindDn;
    private ImageView[] mWindDnSmall;
    private ImageView mWindFr;
    private ImageView[] mWindFrontWidnSmall;
    private ImageView mWindPx;
    private ImageView[] mWindPxSmall;
    private ImageView mWindUp;
    private ImageView[] mWindUpSmall;

    public CanAudiQ5RzcACView(Activity activity) {
        super(activity);
    }

    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        int intValue = ((Integer) v.getTag()).intValue();
        if (action == 0) {
            CanFunc.mLastACTick = SystemClock.uptimeMillis();
            return false;
        }
        if (1 == action) {
        }
        return false;
    }

    public void onClick(View arg0) {
    }

    /* access modifiers changed from: protected */
    public void InitData() {
    }

    /* access modifiers changed from: protected */
    public void InitViews() {
        View view = View.inflate(getActivity(), R.layout.activity_can_ac_audi_rzc_q5, (ViewGroup) null);
        getRelativeManager().AddViewWrapContent(view, 0, 0);
        initCommonScreen(view);
    }

    private void initCommonScreen(View view) {
        boolean isLandScreen = true;
        this.mWindUpSmall = new ImageView[2];
        this.mWindPxSmall = new ImageView[2];
        this.mWindDnSmall = new ImageView[2];
        this.mWindFrontWidnSmall = new ImageView[2];
        this.mLtHot = new ImageView[3];
        this.mRtHot = new ImageView[3];
        this.mLtTempLevel = new CustomImgView[7];
        this.mRtTempLevel = new CustomImgView[7];
        this.mRightWind = new CustomImgView[12];
        this.mLeftWind = new CustomImgView[12];
        this.mLoop = (ImageView) view.findViewById(R.id.can_ac_loop);
        this.mEco = (ImageView) view.findViewById(R.id.can_ac_eco);
        this.mDual = (ImageView) view.findViewById(R.id.can_ac_dual);
        this.mAc = (ImageView) view.findViewById(R.id.can_ac_ac);
        this.mAuto = (ImageView) view.findViewById(R.id.can_ac_auto);
        this.mRearLight = (ImageView) view.findViewById(R.id.can_ac_rear_light);
        this.mForeLight = (ImageView) view.findViewById(R.id.can_ac_fore_light);
        this.mRearLock = (ImageView) view.findViewById(R.id.can_ac_rear_lock);
        this.mAcMax = (ImageView) view.findViewById(R.id.can_ac_acmax);
        this.mFrontMax = (ImageView) view.findViewById(R.id.can_ac_frontmax);
        this.mLtFire = (ImageView) view.findViewById(R.id.can_ac_ltfire);
        this.mLtHot[0] = (ImageView) view.findViewById(R.id.can_ac_lthot1);
        this.mLtHot[1] = (ImageView) view.findViewById(R.id.can_ac_lthot2);
        this.mLtHot[2] = (ImageView) view.findViewById(R.id.can_ac_lthot3);
        this.mRtFire = (ImageView) view.findViewById(R.id.can_ac_rtfire);
        this.mRtHot[0] = (ImageView) view.findViewById(R.id.can_ac_rthot1);
        this.mRtHot[1] = (ImageView) view.findViewById(R.id.can_ac_rthot2);
        this.mRtHot[2] = (ImageView) view.findViewById(R.id.can_ac_rthot3);
        this.mWindFr = (ImageView) view.findViewById(R.id.can_ac_wind_fore);
        this.mWindUp = (ImageView) view.findViewById(R.id.can_ac_wind_up);
        this.mWindPx = (ImageView) view.findViewById(R.id.can_ac_wind_px);
        this.mWindDn = (ImageView) view.findViewById(R.id.can_ac_wind_dn);
        this.mWindUpSmall[0] = (ImageView) view.findViewById(R.id.can_ac_wind_l_up);
        this.mWindUpSmall[1] = (ImageView) view.findViewById(R.id.can_ac_wind_r_up);
        this.mWindPxSmall[0] = (ImageView) view.findViewById(R.id.can_ac_wind_l_px);
        this.mWindPxSmall[1] = (ImageView) view.findViewById(R.id.can_ac_wind_r_px);
        this.mWindDnSmall[0] = (ImageView) view.findViewById(R.id.can_ac_wind_l_dn);
        this.mWindDnSmall[1] = (ImageView) view.findViewById(R.id.can_ac_wind_r_dn);
        this.mWindFrontWidnSmall[0] = (ImageView) view.findViewById(R.id.can_ac_frontwind_l_up);
        this.mWindFrontWidnSmall[1] = (ImageView) view.findViewById(R.id.can_ac_frontwind_r_up);
        this.mLtTemp = (TextView) view.findViewById(R.id.can_ac_lttemp);
        this.mRtTemp = (TextView) view.findViewById(R.id.can_ac_rttemp);
        this.mAutoMode = (TextView) view.findViewById(R.id.can_ac_automode_txt);
        this.mAutolMode = (TextView) view.findViewById(R.id.can_ac_l_automode_txt);
        setIvStateDrawable(this.mEco, R.drawable.conditioning_eco_up, R.drawable.conditioning_eco_dn);
        setIvStateDrawable(this.mDual, R.drawable.conditioning_dual_up, R.drawable.conditioning_dual_dn);
        setIvStateDrawable(this.mAc, R.drawable.conditioning_ac_up, R.drawable.conditioning_ac_dn);
        setIvStateDrawable(this.mRearLight, R.drawable.conditioning_heat_up, R.drawable.conditioning_heat_dn);
        setIvStateDrawable(this.mForeLight, R.drawable.conditioning_wind_up, R.drawable.conditioning_wind_dn);
        setIvStateDrawable(this.mRearLock, R.drawable.conditioning_rear_up, R.drawable.conditioning_rear_dn);
        setIvStateDrawable(this.mAcMax, R.drawable.conditioning_acmax_up, R.drawable.conditioning_acmax_dn);
        setIvStateDrawable(this.mFrontMax, R.drawable.conditioning_max_up, R.drawable.conditioning_max_dn);
        AC_SHOW_TIME = TXZPoiSearchManager.DEFAULT_NEARBY_RADIUS;
        if (MainSet.GetScreenType() != 3) {
            isLandScreen = false;
        }
        for (int i = 0; i < 3; i++) {
            setIvStateDrawable(this.mLtHot[i], R.drawable.conditioning_rect_up, R.drawable.conditioning_rect_dn);
            setIvStateDrawable(this.mRtHot[i], R.drawable.conditioning_rect_up, R.drawable.conditioning_rect_dn);
        }
        this.mManager = new RelativeLayoutManager((RelativeLayout) view.findViewById(R.id.can_ac_audi_rzc_q5_layout));
        for (int i2 = 0; i2 < 7; i2++) {
            if (isLandScreen) {
                this.mLtTempLevel[i2] = this.mManager.AddImageEx((i2 * 16) + 23, 374, 14, 32, 0);
                this.mRtTempLevel[i2] = this.mManager.AddImageEx((i2 * 16) + 636, 374, 14, 32, 0);
            } else {
                this.mLtTempLevel[i2] = this.mManager.AddImage((i2 * 24) + 26, 468);
                this.mRtTempLevel[i2] = this.mManager.AddImage((i2 * 24) + KeyDef.SKEY_RETURN_4, 468);
            }
            this.mLtTempLevel[i2].setStateDrawable(R.drawable.can_rj_rect_gray, R.drawable.can_rj_rect_red);
            this.mLtTempLevel[i2].Show(false);
            this.mRtTempLevel[i2].setStateDrawable(R.drawable.can_rj_rect_gray, R.drawable.can_rj_rect_red);
            this.mRtTempLevel[i2].Show(false);
        }
        for (int i3 = 0; i3 < this.mRightWind.length; i3++) {
            this.mRightWind[i3] = this.mManager.AddImage((i3 * 18) + 260, 460, 16, 50);
            this.mRightWind[i3].setStateDrawable(R.drawable.can_rj_rect_gray, R.drawable.can_rj_rect_blue);
        }
        for (int i4 = 0; i4 < this.mLeftWind.length; i4++) {
            this.mLeftWind[i4] = this.mManager.AddImage((i4 * 18) + 554, 460, 16, 50);
            this.mLeftWind[i4].setStateDrawable(R.drawable.can_rj_rect_gray, R.drawable.can_rj_rect_blue);
        }
    }

    protected static int uint2Bool(int val) {
        if (val == 0) {
            return 1;
        }
        return 0;
    }

    protected static boolean int2Bool(int val) {
        if (val == 0) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void updateACUI() {
        this.mLastTime = SystemClock.uptimeMillis();
        this.mACInfo = Can.mACInfo;
        Can.mACInfo.Update = 0;
        if (int2Bool(this.mACInfo.fgLoopDisable)) {
            setIvDrawable(this.mLoop, R.drawable.conditioning_car_up);
        } else if (int2Bool(this.mACInfo.fgAQS)) {
            setIvDrawable(this.mLoop, R.drawable.conditioning_car04_dn);
        } else if (int2Bool(this.mACInfo.fgInnerLoop)) {
            setIvDrawable(this.mLoop, R.drawable.conditioning_car01_dn);
        } else {
            setIvDrawable(this.mLoop, R.drawable.conditioning_car_dn);
        }
        setIvSel(this.mEco, this.mACInfo.fgEco);
        setIvSel(this.mDual, this.mACInfo.fgDual);
        setIvSel(this.mAc, this.mACInfo.fgAC);
        switch (this.mACInfo.nAutoLight) {
            case 1:
                setIvDrawable(this.mAuto, R.drawable.conditioning_auto02_dn);
                break;
            case 3:
                setIvDrawable(this.mAuto, R.drawable.conditioning_auto01_dn);
                break;
            default:
                setIvDrawable(this.mAuto, R.drawable.conditioning_auto_up);
                break;
        }
        setIvSel(this.mRearLight, this.mACInfo.fgRearLight);
        setIvSel(this.mForeLight, this.mACInfo.fgDFBL);
        setIvSel(this.mRearLock, this.mACInfo.fgRearLock);
        setIvSel(this.mAcMax, this.mACInfo.fgACMax);
        setIvSel(this.mFrontMax, this.mACInfo.fgMaxFornt);
        int ltHot = this.mACInfo.nLtChairHot & 3;
        int rtHot = this.mACInfo.nRtChairHot & 3;
        for (int i = 0; i < ltHot; i++) {
            this.mLtHot[i].setSelected(true);
        }
        for (int i2 = ltHot; i2 < 3; i2++) {
            this.mLtHot[i2].setSelected(false);
        }
        for (int i3 = 0; i3 < rtHot; i3++) {
            this.mRtHot[i3].setSelected(true);
        }
        for (int i4 = rtHot; i4 < 3; i4++) {
            this.mRtHot[i4].setSelected(false);
        }
        setIvShow(this.mLtFire, ltHot);
        setIvShow(this.mRtFire, rtHot);
        for (int i5 = 0; i5 < 3; i5++) {
            setIvShow(this.mLtHot[i5], ltHot);
            setIvShow(this.mRtHot[i5], rtHot);
        }
        if (this.mACInfo.bDaulAc == 1) {
            setIvShow(this.mWindUp, 0);
            setIvShow(this.mWindPx, 0);
            setIvShow(this.mWindDn, 0);
            setIvShow(this.mWindFr, 0);
            setIvShow(this.mWindUpSmall[0], this.mACInfo.fgUpWind);
            setIvShow(this.mWindPxSmall[0], this.mACInfo.fgParallelWind);
            setIvShow(this.mWindDnSmall[0], this.mACInfo.fgDownWind);
            setIvShow(this.mWindUpSmall[1], this.mACInfo.bUpWindFlgR);
            setIvShow(this.mWindPxSmall[1], this.mACInfo.bParallelWindFlgR);
            setIvShow(this.mWindDnSmall[1], this.mACInfo.bDownWindFlgR);
            setIvShow(this.mWindFrontWidnSmall[0], this.mACInfo.fgForeWindMode);
            setIvShow(this.mWindFrontWidnSmall[1], this.mACInfo.fgForeWindModeR);
            setViewShow((View) this.mAutoMode, this.mACInfo.fgAutoMode);
            if (this.mACInfo.fgAutoModeR > 0) {
                this.mAutolMode.setText("AUTO4");
            } else if (this.mACInfo.fgAutoWindR > 0) {
                this.mAutolMode.setText("AUTO3");
            } else {
                this.mAutolMode.setText(" ");
            }
        } else {
            setIvShow(this.mWindUpSmall[0], 0);
            setIvShow(this.mWindPxSmall[0], 0);
            setIvShow(this.mWindDnSmall[0], 0);
            setIvShow(this.mWindUpSmall[1], 0);
            setIvShow(this.mWindPxSmall[1], 0);
            setIvShow(this.mWindDnSmall[1], 0);
            setIvShow(this.mWindFrontWidnSmall[0], 0);
            setIvShow(this.mWindFrontWidnSmall[1], 0);
            setIvShow(this.mWindUp, this.mACInfo.fgUpWind);
            setIvShow(this.mWindPx, this.mACInfo.fgParallelWind);
            setIvShow(this.mWindDn, this.mACInfo.fgDownWind);
            setIvShow(this.mWindFr, this.mACInfo.fgForeWindMode);
        }
        try {
            this.mLtTemp.setText(this.mACInfo.szLtTemp);
            this.mRtTemp.setText(this.mACInfo.szRtTemp);
        } catch (Exception e) {
            Log.e(TAG, "set Temp text Exception!");
        }
        int rightWind = this.mACInfo.nWindValue;
        for (int i6 = 0; i6 < rightWind; i6++) {
            this.mRightWind[i6].setSelected(true);
        }
        for (int i7 = rightWind; i7 < 12; i7++) {
            this.mRightWind[i7].setSelected(false);
        }
        int leftWind = this.mACInfo.nWindValueR;
        for (int i8 = 0; i8 < leftWind; i8++) {
            this.mLeftWind[i8].setSelected(true);
        }
        for (int i9 = leftWind; i9 < 12; i9++) {
            this.mLeftWind[i9].setSelected(false);
        }
    }

    /* access modifiers changed from: protected */
    public void SetTempStyle() {
        boolean bTxtTemp;
        boolean bLevelTemp;
        this.mTempStyle = 0;
        if (this.mTempStyle == 0) {
            bTxtTemp = true;
        } else {
            bTxtTemp = false;
        }
        if (this.mTempStyle == 1) {
            bLevelTemp = true;
        } else {
            bLevelTemp = false;
        }
        setViewShow((View) this.mLtTemp, bTxtTemp);
        setViewShow((View) this.mRtTemp, bTxtTemp);
        for (int i = 0; i < 7; i++) {
            this.mLtTempLevel[i].Show(bLevelTemp);
            this.mRtTempLevel[i].Show(bLevelTemp);
        }
    }

    public static boolean showAc(Context context) {
        if (!Can.updateAC()) {
            return false;
        }
        Intent intent = new Intent();
        intent.setClass(context, CanACActivity.class);
        intent.setFlags(268435456);
        context.startActivity(intent);
        return true;
    }

    public void setIvStateDrawable(ImageView iv, int normal, int selected) {
        Drawable iSelected = null;
        Resources res = getActivity().getResources();
        StateListDrawable sd = new StateListDrawable();
        Drawable iNormal = normal == -1 ? null : res.getDrawable(normal);
        if (selected != -1) {
            iSelected = res.getDrawable(selected);
        }
        sd.addState(new int[]{16842913}, iSelected);
        sd.addState(new int[]{16842910}, iNormal);
        sd.addState(new int[0], iNormal);
        iv.setBackground(sd);
    }

    public void setIvDrawable(ImageView iv, int resId) {
        iv.setImageDrawable(getActivity().getResources().getDrawable(resId));
    }

    public void setIvSel(ImageView iv, int val) {
        if (int2Bool(val)) {
            iv.setSelected(true);
        } else {
            iv.setSelected(false);
        }
    }

    public void setIvShow(ImageView iv, int val) {
        if (iv != null) {
            if (int2Bool(val)) {
                iv.setVisibility(0);
            } else {
                iv.setVisibility(4);
            }
        }
    }
}
