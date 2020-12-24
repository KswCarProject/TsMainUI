package com.ts.can;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.canview.CanItemBlankTextList;
import com.ts.canview.CanItemCheckList;
import com.ts.canview.CanItemProgressList;
import com.ts.main.common.MainSet;
import com.ts.other.CustomImgView;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;
import com.yyw.ts70xhw.KeyDef;

public class CanPradoParkAssistActivity extends CanToyotaBaseActivity implements UserCallBack, View.OnClickListener, CanItemProgressList.onPosChange {
    public static final int ITEM_REAR = 2;
    public static final int ITEM_SHOW = 1;
    public static final int ITEM_VOL = 0;
    public static final String TAG = "CanPradoParkAssistActivity";
    protected ParamButton mBtnRear;
    protected CanDataInfo.ToyotaCtrlInfo mCtrlData = new CanDataInfo.ToyotaCtrlInfo();
    protected CustomImgView mImgCar;
    protected CanItemBlankTextList mItemDistance;
    protected CanItemCheckList mItemShow;
    protected CanItemProgressList mItemVol;
    protected RelativeLayoutManager mManager;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_can_comm_relative);
        if (MainSet.GetScreenType() == 5) {
            initScreen_1280x480();
        } else {
            initCommonScreen();
        }
    }

    private void initScreen_1280x480() {
        this.mManager = new RelativeLayoutManager(this, R.id.can_comm_layout);
        this.mItemVol = new CanItemProgressList((Context) this, "Park Assist");
        this.mItemVol.SetMinMax(0, 5);
        this.mItemVol.SetIdCallBack(0, this);
        this.mManager.AddView(this.mItemVol.GetView(), 0, 5, -2, 80);
        this.mItemShow = new CanItemCheckList(this, R.string.can_display);
        this.mItemShow.SetIdClickListener(this, 1);
        this.mManager.AddView(this.mItemShow.GetView(), 0, 90, -2, 80);
        this.mItemDistance = new CanItemBlankTextList((Context) this, R.string.can_distance);
        this.mManager.AddView(this.mItemDistance.GetView(), 0, 170, -2, 80);
        this.mImgCar = this.mManager.AddImage(810, 170, 200, 350);
        this.mImgCar.setDrawDt(-810, -135);
        this.mImgCar.setUserPaint(new CustomImgView.onPaint() {
            public boolean userPaint(CustomImgView view, Canvas canvas, Paint paint) {
                view.drawImage(R.drawable.can_radar_set_car, KeyDef.SKEY_RETURN_5, 135);
                view.drawImage(R.drawable.can_radar_set_h, KeyDef.SKEY_SPEECH_4, 343);
                view.drawImage(R.drawable.can_radar_set_l, KeyDef.SKEY_SPEECH_4, 363);
                if (CanPradoParkAssistActivity.this.mCtrlData.UpdateOnce == 0) {
                    return false;
                }
                if (!CanPradoParkAssistActivity.i2b(CanPradoParkAssistActivity.this.mCtrlData.fgRearRadarFar)) {
                    view.drawImage(R.drawable.can_radar_set_sjx_r, KeyDef.SKEY_CALLUP_3, 350);
                    view.drawImage(R.drawable.can_radar_set_sjx_l, 935, 350);
                    return false;
                }
                view.drawImage(R.drawable.can_radar_set_sjx_r, KeyDef.SKEY_CALLUP_3, KeyDef.RKEY_RDS_TA);
                view.drawImage(R.drawable.can_radar_set_sjx_l, 935, KeyDef.RKEY_RDS_TA);
                return false;
            }
        });
        this.mBtnRear = this.mManager.AddButton(1030, 347, Can.CAN_FORD_WC, 59);
        SetCommBtn(this.mBtnRear, R.string.can_rear);
        this.mBtnRear.setTag(2);
        this.mBtnRear.setOnClickListener(this);
    }

    private void initCommonScreen() {
        this.mManager = new RelativeLayoutManager(this, R.id.can_comm_layout);
        this.mItemVol = new CanItemProgressList((Context) this, "Park Assist");
        this.mItemVol.SetMinMax(0, 5);
        this.mItemVol.SetIdCallBack(0, this);
        this.mManager.AddView(this.mItemVol.GetView(), 0, 10, -2, 85);
        this.mItemShow = new CanItemCheckList(this, R.string.can_display);
        this.mItemShow.SetIdClickListener(this, 1);
        this.mManager.AddView(this.mItemShow.GetView(), 0, 95, -2, 85);
        this.mItemDistance = new CanItemBlankTextList((Context) this, R.string.can_distance);
        this.mManager.AddView(this.mItemDistance.GetView(), 0, 180, -2, 85);
        this.mImgCar = this.mManager.AddImage(577, 197, Can.CAN_CHANA_CS75_WC, 350);
        this.mImgCar.setDrawDt(-577, -197);
        this.mImgCar.setUserPaint(new CustomImgView.onPaint() {
            public boolean userPaint(CustomImgView view, Canvas canvas, Paint paint) {
                view.drawImage(R.drawable.can_radar_set_car, 606, 263);
                view.drawImage(R.drawable.can_radar_set_h, CanCameraUI.BTN_CCH9_MODE1, 481);
                view.drawImage(R.drawable.can_radar_set_l, CanCameraUI.BTN_CCH9_MODE1, 507);
                if (CanPradoParkAssistActivity.this.mCtrlData.UpdateOnce == 0) {
                    return false;
                }
                if (!CanPradoParkAssistActivity.i2b(CanPradoParkAssistActivity.this.mCtrlData.fgRearRadarFar)) {
                    view.drawImage(R.drawable.can_radar_set_sjx_r, 577, 476);
                    view.drawImage(R.drawable.can_radar_set_sjx_l, CanCameraUI.BTN_CC_WC_DIRECTION3, 476);
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
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        CanJni.ToyotaQuery(30, 0);
        ResetData(false);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
    }

    private void ResetData(boolean check) {
        CanJni.ToyotaGetCtrlInfo(this.mCtrlData);
        if (!i2b(this.mCtrlData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mCtrlData.Update)) {
            this.mCtrlData.Update = 0;
            this.mItemVol.SetCurVal(this.mCtrlData.FAVol);
            this.mItemShow.SetCheck(this.mCtrlData.fgFA);
            this.mImgCar.invalidate();
        }
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                CarSet(22, Neg(this.mCtrlData.fgFA));
                return;
            case 2:
                CarSet(23, Neg(this.mCtrlData.fgRearRadarFar));
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
            CarSet(21, pos);
        }
    }
}
