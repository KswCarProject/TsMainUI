package com.ts.can.gm.comm;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanCameraUI;
import com.ts.can.CanRelativeCarInfoView;
import com.ts.main.common.MainSet;
import com.ts.other.CustomImgView;
import com.ts.other.RelativeLayoutManager;
import com.yyw.ts70xhw.KeyDef;

public class CanGMHybridView extends CanRelativeCarInfoView {
    public static final String TAG = "CanGMHybridView";
    private static final int X_CAR = 82;
    private static final int Y_CAR = 20;
    /* access modifiers changed from: private */
    public static final int[] mBattery = {R.drawable.can_ydhh_dc_00, R.drawable.can_ydhh_dc_01, R.drawable.can_ydhh_dc_02, R.drawable.can_ydhh_dc_03, R.drawable.can_ydhh_dc_04, R.drawable.can_ydhh_dc_05, R.drawable.can_ydhh_dc_06, R.drawable.can_ydhh_dc_07, R.drawable.can_ydhh_dc_08};
    public CustomImgView.onPaint mDirPaint = new CustomImgView.onPaint() {
        public boolean userPaint(CustomImgView view, Canvas canvas, Paint paint) {
            Log.d("CanGMHybridView", "mHybridData.UpdateOnce=" + CanGMHybridView.this.mHybridData.UpdateOnce);
            Log.d("CanGMHybridView", "mHybridData.Battery=" + CanGMHybridView.this.mHybridData.Battery);
            if (CanGMHybridView.this.mHybridData.UpdateOnce == 0) {
                view.drawImage(R.drawable.can_fdj_cl_up, 128, 29);
                view.drawImage(R.drawable.can_fdj_md_up, 201, 83);
                view.drawImage(R.drawable.can_cl_md_up, 144, 47);
                view.drawImage(R.drawable.can_md_dc_up, 408, Can.CAN_FORD_WC);
                view.drawImage(R.drawable.can_ydhh_dc_08, CanCameraUI.BTN_CCH9_MODE10, 177);
            } else {
                if (CanGMHybridView.this.i2b(CanGMHybridView.this.mHybridData.Fdjqdcl)) {
                    view.drawImage(R.drawable.can_fdj_cl_dn, 128, 29);
                } else {
                    view.drawImage(R.drawable.can_fdj_cl_up, 128, 29);
                }
                if (CanGMHybridView.this.i2b(CanGMHybridView.this.mHybridData.Fdjqdmd)) {
                    view.drawImage(R.drawable.can_fdj_md_dn, 201, 83);
                } else {
                    view.drawImage(R.drawable.can_fdj_md_up, 201, 83);
                }
                if (CanGMHybridView.this.i2b(CanGMHybridView.this.mHybridData.Mdqdcl)) {
                    view.drawImage(R.drawable.can_md_cl, 144, 47);
                } else if (CanGMHybridView.this.i2b(CanGMHybridView.this.mHybridData.Clqdmd)) {
                    view.drawImage(R.drawable.can_cl_md_dn, 144, 47);
                } else {
                    view.drawImage(R.drawable.can_cl_md_up, 144, 47);
                }
                if (CanGMHybridView.this.i2b(CanGMHybridView.this.mHybridData.Mdqddc)) {
                    view.drawImage(R.drawable.can_md_dc_dn, 408, Can.CAN_FORD_WC);
                } else if (CanGMHybridView.this.i2b(CanGMHybridView.this.mHybridData.Dcqdmd)) {
                    view.drawImage(R.drawable.can_dc_md, 408, Can.CAN_FORD_WC);
                } else {
                    view.drawImage(R.drawable.can_md_dc_up, 408, Can.CAN_FORD_WC);
                }
                if (CanGMHybridView.this.mHybridData.Battery < 0 || CanGMHybridView.this.mHybridData.Battery > 8) {
                    view.drawImage(R.drawable.can_ydhh_dc_08, CanCameraUI.BTN_CCH9_MODE10, 177);
                } else {
                    view.drawImage(CanGMHybridView.mBattery[CanGMHybridView.this.mHybridData.Battery], CanCameraUI.BTN_CCH9_MODE10, 177);
                }
            }
            view.drawImage(R.drawable.can_ydhh_fdj, 93, 27);
            return false;
        }
    };
    /* access modifiers changed from: private */
    public CanDataInfo.GM_Hybrid mHybridData = new CanDataInfo.GM_Hybrid();
    protected CustomImgView mImgCar;
    protected CustomImgView mImgDirect;
    protected RelativeLayoutManager mManager;

    public CanGMHybridView(Activity activity) {
        super(activity);
    }

    public boolean onTouch(View arg0, MotionEvent arg1) {
        return false;
    }

    public void onClick(View arg0) {
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mManager = getRelativeManager();
        this.mImgCar = this.mManager.AddImage(82, 70, KeyDef.SKEY_CALLUP_4, 396);
        this.mImgCar.setImageResource(R.drawable.can_ydhh_bg);
    }

    public void doOnResume() {
        this.mImgDirect = this.mManager.AddImage(82, 70, KeyDef.SKEY_CALLUP_4, 396);
        this.mImgDirect.setUserPaint(this.mDirPaint);
        super.doOnResume();
    }

    public void ResetData(boolean check) {
        CanJni.GMGetHyBridInfo(this.mHybridData);
        if (!i2b(this.mHybridData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mHybridData.Update)) {
            this.mHybridData.Update = 0;
            Log.d("CanGMHybridView", MainSet.SP_RLF_KORON);
            this.mImgDirect.invalidate();
        }
    }

    public void QueryData() {
    }
}
