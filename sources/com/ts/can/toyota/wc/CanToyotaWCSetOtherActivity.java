package com.ts.can.toyota.wc;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import com.lgb.canmodule.Can;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanCameraUI;
import com.ts.canview.CanItemBlankTextList;
import com.ts.canview.CanItemCheckList;
import com.ts.canview.CanItemProgressList;
import com.ts.other.CustomImgView;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;
import com.yyw.ts70xhw.KeyDef;

public class CanToyotaWCSetOtherActivity extends CanToyotaWCBaseActivity implements UserCallBack, View.OnClickListener, CanItemProgressList.onPosChange {
    public static final int ITEM_FRONT = 3;
    public static final int ITEM_REAR = 2;
    public static final int ITEM_SHOW = 1;
    public static final int ITEM_VOL = 0;
    public static final String TAG = "CanToyotaWCSetOtherActivity";
    protected ParamButton mBtnFront;
    protected ParamButton mBtnRear;
    protected CustomImgView mImgCar;
    protected CanItemBlankTextList mItemDistance;
    protected CanItemCheckList mItemShow;
    protected CanItemProgressList mItemVol;
    protected RelativeLayoutManager mManager;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_can_comm_relative);
        initCommonScreen();
    }

    private void initCommonScreen() {
        this.mManager = new RelativeLayoutManager(this, R.id.can_comm_layout);
        this.mItemVol = new CanItemProgressList((Context) this, R.string.can_warn_vol);
        this.mItemVol.SetMinMax(0, 5);
        this.mItemVol.SetIdCallBack(0, this);
        this.mManager.AddView(this.mItemVol.GetView(), 0, 10, -2, 85);
        this.mItemShow = new CanItemCheckList(this, R.string.can_display);
        this.mItemShow.SetIdClickListener(this, 1);
        this.mManager.AddView(this.mItemShow.GetView(), 0, 95, -2, 85);
        this.mItemDistance = new CanItemBlankTextList((Context) this, R.string.can_distance);
        this.mManager.AddView(this.mItemDistance.GetView(), 0, 180, -2, 85);
        this.mImgCar = this.mManager.AddImage(577, 197, 160, 350);
        this.mImgCar.setDrawDt(-577, -197);
        this.mImgCar.setUserPaint(new CustomImgView.onPaint() {
            public boolean userPaint(CustomImgView view, Canvas canvas, Paint paint) {
                view.drawImage(R.drawable.can_radar_set_car, 606, 268);
                view.drawImage(R.drawable.can_radar_set_h, CanCameraUI.BTN_CCH9_MODE1, 211);
                view.drawImage(R.drawable.can_radar_set_l, CanCameraUI.BTN_CCH9_MODE1, Can.CAN_NISSAN_RICH6_WC);
                view.drawImage(R.drawable.can_radar_set_h, CanCameraUI.BTN_CCH9_MODE1, 481);
                view.drawImage(R.drawable.can_radar_set_l, CanCameraUI.BTN_CCH9_MODE1, 507);
                if (CanToyotaWCSetOtherActivity.this.mSetData.UpdateOnce == 0) {
                    return false;
                }
                if (CanToyotaWCSetOtherActivity.this.mSetData.RadarFrontDis == 1) {
                    view.drawImage(R.drawable.can_radar_set_sjx_r, 577, 201);
                    view.drawImage(R.drawable.can_radar_set_sjx_l, CanCameraUI.BTN_CC_WC_DIRECTION3, 201);
                } else {
                    view.drawImage(R.drawable.can_radar_set_sjx_r, 577, Can.CAN_FORD_SYNC3);
                    view.drawImage(R.drawable.can_radar_set_sjx_l, CanCameraUI.BTN_CC_WC_DIRECTION3, Can.CAN_FORD_SYNC3);
                }
                if (CanToyotaWCSetOtherActivity.this.mSetData.RadarRearDis == 1) {
                    view.drawImage(R.drawable.can_radar_set_sjx_r, 577, 471);
                    view.drawImage(R.drawable.can_radar_set_sjx_l, CanCameraUI.BTN_CC_WC_DIRECTION3, 471);
                    return false;
                }
                view.drawImage(R.drawable.can_radar_set_sjx_r, 577, 496);
                view.drawImage(R.drawable.can_radar_set_sjx_l, CanCameraUI.BTN_CC_WC_DIRECTION3, 496);
                return false;
            }
        });
        this.mBtnRear = this.mManager.AddButton(KeyDef.SKEY_SEEKDN_5, 462);
        SetCommBtn(this.mBtnRear, R.string.can_rear);
        this.mBtnRear.setTag(2);
        this.mBtnRear.setOnClickListener(this);
        this.mBtnFront = this.mManager.AddButton(KeyDef.SKEY_SEEKDN_5, 362);
        SetCommBtn(this.mBtnFront, R.string.can_fore);
        this.mBtnFront.setTag(3);
        this.mBtnFront.setOnClickListener(this);
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
        GetSetData();
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            this.mItemVol.SetCurVal(this.mSetData.RadarVol);
            this.mItemShow.SetCheck(this.mSetData.RadarSw);
            this.mImgCar.invalidate();
        }
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                CarSet(1, 8, Neg(this.mSetData.RadarSw));
                return;
            case 2:
                CarSet(1, 10, 2);
                return;
            case 3:
                CarSet(1, 10, 1);
                return;
            default:
                return;
        }
    }

    public void UserAll() {
        ResetData(true);
    }

    public void onChanged(int id, int pos) {
        if (id == 0 && pos > 0) {
            CarSet(1, 9, pos);
        }
    }
}
