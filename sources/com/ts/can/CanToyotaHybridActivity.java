package com.ts.can;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.main.common.MainSet;
import com.ts.other.CustomImgView;
import com.ts.other.RelativeLayoutManager;
import com.yyw.ts70xhw.KeyDef;

public class CanToyotaHybridActivity extends CanToyotaBaseActivity implements UserCallBack, View.OnClickListener {
    public static final String TAG = "CanToyotaHybridActivity";
    private static final int X_CAR = 82;
    private static final int Y_CAR = 20;
    /* access modifiers changed from: private */
    public static final int[] mBattery = {R.drawable.can_ydhh_dc_00, R.drawable.can_ydhh_dc_01, R.drawable.can_ydhh_dc_02, R.drawable.can_ydhh_dc_03, R.drawable.can_ydhh_dc_04, R.drawable.can_ydhh_dc_05, R.drawable.can_ydhh_dc_06, R.drawable.can_ydhh_dc_07, R.drawable.can_ydhh_dc_08};
    private CustomImgView.onPaint mDirPaint = new CustomImgView.onPaint() {
        public boolean userPaint(CustomImgView view, Canvas canvas, Paint paint) {
            if (CanToyotaHybridActivity.this.mHybridData.UpdateOnce == 0) {
                view.drawImage(R.drawable.can_fdj_cl_up, 128, 29);
                view.drawImage(R.drawable.can_fdj_md_up, 201, 83);
                view.drawImage(R.drawable.can_cl_md_up, 144, 47);
                view.drawImage(R.drawable.can_md_dc_up, 408, Can.CAN_FORD_WC);
                view.drawImage(R.drawable.can_ydhh_dc_08, CanCameraUI.BTN_CCH9_MODE10, 177);
            } else {
                if (CanToyotaHybridActivity.i2b(CanToyotaHybridActivity.this.mHybridData.FdjQdCl)) {
                    view.drawImage(R.drawable.can_fdj_cl_dn, 128, 29);
                } else {
                    view.drawImage(R.drawable.can_fdj_cl_up, 128, 29);
                }
                if (CanToyotaHybridActivity.i2b(CanToyotaHybridActivity.this.mHybridData.FdjQdMd)) {
                    view.drawImage(R.drawable.can_fdj_md_dn, 201, 83);
                } else {
                    view.drawImage(R.drawable.can_fdj_md_up, 201, 83);
                }
                if (CanToyotaHybridActivity.i2b(CanToyotaHybridActivity.this.mHybridData.MdQdCl)) {
                    view.drawImage(R.drawable.can_md_cl, 144, 47);
                } else if (CanToyotaHybridActivity.i2b(CanToyotaHybridActivity.this.mHybridData.ClQdMd)) {
                    view.drawImage(R.drawable.can_cl_md_dn, 144, 47);
                } else {
                    view.drawImage(R.drawable.can_cl_md_up, 144, 47);
                }
                if (CanToyotaHybridActivity.i2b(CanToyotaHybridActivity.this.mHybridData.MdQdDc)) {
                    view.drawImage(R.drawable.can_md_dc_dn, 408, Can.CAN_FORD_WC);
                } else if (CanToyotaHybridActivity.i2b(CanToyotaHybridActivity.this.mHybridData.DcQdMd)) {
                    view.drawImage(R.drawable.can_dc_md, 408, Can.CAN_FORD_WC);
                } else {
                    view.drawImage(R.drawable.can_md_dc_up, 408, Can.CAN_FORD_WC);
                }
                if (CanToyotaHybridActivity.this.mHybridData.Battery < 0 || CanToyotaHybridActivity.this.mHybridData.Battery > 8) {
                    view.drawImage(R.drawable.can_ydhh_dc_08, CanCameraUI.BTN_CCH9_MODE10, 177);
                } else {
                    view.drawImage(CanToyotaHybridActivity.mBattery[CanToyotaHybridActivity.this.mHybridData.Battery], CanCameraUI.BTN_CCH9_MODE10, 177);
                }
            }
            view.drawImage(R.drawable.can_ydhh_fdj, 93, 27);
            return false;
        }
    };
    /* access modifiers changed from: private */
    public CanDataInfo.ToyotaHybrid mHybridData = new CanDataInfo.ToyotaHybrid();
    protected CustomImgView mImgCar;
    protected CustomImgView mImgDirect;
    protected RelativeLayoutManager mManager;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_can_comm_relative);
        this.mManager = new RelativeLayoutManager(this, R.id.can_comm_layout);
        if (MainSet.GetScreenType() == 3) {
            FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) this.mManager.GetLayout().getLayoutParams();
            lp.width = 1024;
            lp.height = CanCameraUI.BTN_NISSAN_XTRAL_RVS_ASSIST6;
            lp.gravity = 17;
            this.mManager.GetLayout().setLayoutParams(lp);
        }
        this.mImgCar = this.mManager.AddImage(82, 70, KeyDef.SKEY_CALLUP_4, 396);
        this.mImgCar.setImageResource(R.drawable.can_ydhh_bg);
        this.mImgDirect = this.mManager.AddImage(82, 70, KeyDef.SKEY_CALLUP_4, 396);
        this.mImgDirect.setUserPaint(this.mDirPaint);
        if (MainSet.GetScreenType() == 3) {
            this.mManager.GetLayout().setScaleX(0.78f);
            this.mManager.GetLayout().setScaleY(0.79f);
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
    }

    private void ResetData(boolean check) {
        CanJni.ToyotaGetHybrid(this.mHybridData);
        if (!i2b(this.mHybridData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mHybridData.Update)) {
            this.mHybridData.Update = 0;
            this.mImgDirect.invalidate();
        }
    }

    public void onClick(View v) {
        int intValue = ((Integer) v.getTag()).intValue();
    }

    public void UserAll() {
        ResetData(true);
    }
}
