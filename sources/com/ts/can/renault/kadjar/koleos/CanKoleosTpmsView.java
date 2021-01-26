package com.ts.can.renault.kadjar.koleos;

import android.app.Activity;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanCameraUI;
import com.ts.can.CanRelativeCarInfoView;
import com.ts.other.CustomImgView;
import com.ts.other.CustomTextView;
import com.txznet.sdk.TXZResourceManager;

public class CanKoleosTpmsView extends CanRelativeCarInfoView {
    private static final int[] mTips = {R.string.can_tpms_openning, R.string.can_tpms_show, R.string.can_tpms_adjust, R.string.can_tpms_failure, R.string.can_tpms_check, R.string.can_tpms_damage};
    private static Toast mToast;
    protected CustomImgView[] mIvTyres;
    private CanDataInfo.KadjarTpmsInfo mTpmsInfo;
    protected CustomTextView[] mTvTpms;

    public CanKoleosTpmsView(Activity activity) {
        super(activity);
    }

    public boolean onTouch(View arg0, MotionEvent arg1) {
        return false;
    }

    public void onClick(View v) {
        int intValue = ((Integer) v.getTag()).intValue();
    }

    public static void ShowTpmsTips(Context context) {
        int Ts;
        CanDataInfo.KadjarTpmsInfo tmpsInfo = new CanDataInfo.KadjarTpmsInfo();
        CanJni.KadjarGetCarTpmsData(tmpsInfo);
        if (tmpsInfo.UpdateOnce != 0 && tmpsInfo.Update != 0 && (Ts = tmpsInfo.Ts) > 0 && Ts < 7) {
            showToast(context, Ts - 1);
        }
    }

    private static void showToast(Context context, int index) {
        if (mToast == null) {
            mToast = Toast.makeText(context, TXZResourceManager.STYLE_DEFAULT, 1);
        }
        mToast.setText(mTips[index]);
        mToast.show();
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        setBackgroundResource(R.drawable.can_rf_tpms_bg);
        initView();
        this.mTpmsInfo = new CanDataInfo.KadjarTpmsInfo();
    }

    private void initView() {
        this.mTvTpms = new CustomTextView[4];
        this.mIvTyres = new CustomImgView[4];
        for (int i = 0; i < 4; i++) {
            this.mTvTpms[i] = getRelativeManager().AddCusText(((i % 2) * CanCameraUI.BTN_TRUMPCHI_GS7_MODE4) + 91, ((i / 2) * Can.CAN_CC_HF_DJ) + 90, 281, 50);
            this.mTvTpms[i].SetPixelSize(45);
            this.mTvTpms[i].setGravity(17);
            this.mIvTyres[i] = addImage(((i % 2) * 132) + 431, ((i / 2) * 186) + 116);
            this.mIvTyres[i].setStateDrawable(R.drawable.can_rf_tpms_up, R.drawable.can_rf_tpms_dn);
        }
    }

    public void ResetData(boolean check) {
        CanJni.KadjarGetCarTpmsData(this.mTpmsInfo);
        if (!i2b(this.mTpmsInfo.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mTpmsInfo.Update)) {
            for (int i = 0; i < 4; i++) {
                this.mTvTpms[i].setText(GetPressStr(this.mTpmsInfo.Val[i]));
            }
        }
    }

    public void QueryData() {
        CanJni.KadjarCarQuery(97, 0);
    }

    public String GetPressStr(int press) {
        if (press == 255) {
            return "-.-";
        }
        return String.format("%.1f", new Object[]{Float.valueOf(((float) press) * 0.03f)});
    }
}
