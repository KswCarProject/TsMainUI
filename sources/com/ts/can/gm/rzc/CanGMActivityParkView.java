package com.ts.can.gm.rzc;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanCameraUI;
import com.ts.can.CanRelativeCarInfoView;
import com.ts.other.CustomImgView;
import com.ts.other.RelativeLayoutManager;
import com.yyw.ts70xhw.KeyDef;

public class CanGMActivityParkView extends CanRelativeCarInfoView {
    public static final int BTN_HORRIZONAL = 1;
    public static final int BTN_LEFT = 3;
    public static final int BTN_RIGHT = 4;
    public static final int BTN_VERTICAL = 2;
    public static final String TAG = "CanGMActivityParkView";
    /* access modifiers changed from: private */
    public CanDataInfo.GM_AutoPark mAutoPark = new CanDataInfo.GM_AutoPark();
    private CustomImgView mImgCarDir;
    protected CustomImgView mImgPark;
    protected RelativeLayoutManager mManager;
    private TextView mParkDir;
    private Button mParkHorizonal;
    private TextView mParkInfo;
    private String[] mParkInfoArray;
    private Button mParkLeft;
    public CustomImgView.onPaint mParkPaint = new CustomImgView.onPaint() {
        public boolean userPaint(CustomImgView view, Canvas canvas, Paint paint) {
            switch (CanGMActivityParkView.this.mAutoPark.TsMsg) {
                case 1:
                case 3:
                    view.drawImage(R.drawable.can_gm_park_b_car, 468, 193);
                    view.drawImage(R.drawable.can_gm_park_01, 552, 210);
                    view.drawImage(R.drawable.can_gm_park_r_01, 626, Can.CAN_AUDI_ZMYT);
                    return false;
                case 2:
                case 4:
                    view.drawImage(R.drawable.can_gm_park_b_car, 656, 193);
                    view.drawImage(R.drawable.can_gm_park_02, CanCameraUI.BTN_GOLF_WC_MODE2, 210);
                    view.drawImage(R.drawable.can_gm_park_l_01, 467, Can.CAN_AUDI_ZMYT);
                    return false;
                case 9:
                    view.drawImage(R.drawable.can_gm_park_r_02, 626, Can.CAN_AUDI_ZMYT);
                    view.drawImage(R.drawable.can_gm_park_b_car, 487, 112);
                    view.drawImage(R.drawable.can_gm_park_10, KeyDef.RKEY_RDS_PTY, Can.CAN_FORD_WC);
                    view.drawImage(R.drawable.can_gm_park_11, KeyDef.RKEY_res2, Can.CAN_MZD_LUOMU);
                    view.drawImage(R.drawable.can_gm_park_r, 350, KeyDef.RKEY_RADIO_5S);
                    view.drawImage(R.drawable.can_gm_park_12, CanCameraUI.BTN_VW_WC_MODE3, 112);
                    return false;
                case 13:
                    view.drawImage(R.drawable.can_gm_park_l_02, 467, Can.CAN_AUDI_ZMYT);
                    view.drawImage(R.drawable.can_gm_park_b_car, 599, 112);
                    view.drawImage(R.drawable.can_gm_park_10, KeyDef.RKEY_RDS_PTY, Can.CAN_FORD_WC);
                    view.drawImage(R.drawable.can_gm_park_11, KeyDef.RKEY_res2, Can.CAN_MZD_LUOMU);
                    view.drawImage(R.drawable.can_gm_park_r, 350, KeyDef.RKEY_RADIO_5S);
                    view.drawImage(R.drawable.can_gm_park_13, 485, 112);
                    return false;
                case 17:
                    view.drawImage(R.drawable.can_gm_park_b_car, 468, 204);
                    view.drawImage(R.drawable.can_gm_park_01, 552, 221);
                    view.drawImage(R.drawable.can_gm_park_h_r_01, CanCameraUI.BTN_CCH9_MODE14, Can.CAN_AUDI_ZMYT);
                    return false;
                case 18:
                    view.drawImage(R.drawable.can_gm_park_b_car, 656, 204);
                    view.drawImage(R.drawable.can_gm_park_02, CanCameraUI.BTN_GOLF_WC_MODE2, 221);
                    view.drawImage(R.drawable.can_gm_park_h_l_01, 434, 149);
                    return false;
                case 19:
                    view.drawImage(R.drawable.can_gm_park_h_r_02, CanCameraUI.BTN_CCH9_MODE14, Can.CAN_AUDI_ZMYT);
                    view.drawImage(R.drawable.can_gm_park_b_car, 487, 112);
                    view.drawImage(R.drawable.can_gm_park_10, KeyDef.RKEY_RDS_PTY, Can.CAN_FORD_WC);
                    view.drawImage(R.drawable.can_gm_park_11, KeyDef.RKEY_res2, Can.CAN_MZD_LUOMU);
                    view.drawImage(R.drawable.can_gm_park_r, 350, KeyDef.RKEY_RADIO_5S);
                    view.drawImage(R.drawable.can_gm_park_14, CanCameraUI.BTN_VW_WC_MODE3, 112);
                    return false;
                case 22:
                    view.drawImage(R.drawable.can_gm_park_h_l_02, 434, 149);
                    view.drawImage(R.drawable.can_gm_park_b_car, CanCameraUI.BTN_SENOVA_SUB_BJ40_MODE5, 112);
                    view.drawImage(R.drawable.can_gm_park_10, KeyDef.RKEY_RDS_PTY, Can.CAN_FORD_WC);
                    view.drawImage(R.drawable.can_gm_park_11, KeyDef.RKEY_res2, Can.CAN_MZD_LUOMU);
                    view.drawImage(R.drawable.can_gm_park_r, 350, KeyDef.RKEY_RADIO_5S);
                    view.drawImage(R.drawable.can_gm_park_15, 496, 112);
                    return false;
                case 27:
                    view.drawImage(R.drawable.can_gm_park_16, 582, 129);
                    view.drawImage(R.drawable.can_gm_park_p, KeyDef.RKEY_RDS_PTY, Can.CAN_FORD_WC);
                    view.drawImage(R.drawable.can_gm_park_11, KeyDef.RKEY_res2, Can.CAN_MZD_LUOMU);
                    view.drawImage(R.drawable.can_gm_park_l_02, 467, Can.CAN_AUDI_ZMYT);
                    view.drawImage(R.drawable.can_gm_park_r, 350, KeyDef.RKEY_RADIO_5S);
                    view.drawImage(R.drawable.can_gm_park_b_car, 477, 193);
                    return false;
                case 28:
                    view.drawImage(R.drawable.can_gm_park_18, 496, 126);
                    view.drawImage(R.drawable.can_gm_park_p, KeyDef.RKEY_RDS_PTY, Can.CAN_FORD_WC);
                    view.drawImage(R.drawable.can_gm_park_11, KeyDef.RKEY_res2, Can.CAN_MZD_LUOMU);
                    view.drawImage(R.drawable.can_gm_park_r, 350, KeyDef.RKEY_RADIO_5S);
                    view.drawImage(R.drawable.can_gm_park_r_02, 626, Can.CAN_AUDI_ZMYT);
                    view.drawImage(R.drawable.can_gm_park_b_car, CanCameraUI.BTN_SENOVA_SUB_BJ40_MODE4, 194);
                    return false;
                case 35:
                    view.drawImage(R.drawable.can_gm_park_b_car, 468, 193);
                    view.drawImage(R.drawable.can_gm_park_01, 552, 210);
                    view.drawImage(R.drawable.can_gm_park_r_03, 626, Can.CAN_AUDI_ZMYT);
                    return false;
                case 36:
                    view.drawImage(R.drawable.can_gm_park_b_car, 656, 193);
                    view.drawImage(R.drawable.can_gm_park_02, CanCameraUI.BTN_GOLF_WC_MODE2, 210);
                    view.drawImage(R.drawable.can_gm_park_l_03, 467, Can.CAN_AUDI_ZMYT);
                    return false;
                case 37:
                    view.drawImage(R.drawable.can_gm_park_b_car, 468, 204);
                    view.drawImage(R.drawable.can_gm_park_01, 552, 221);
                    view.drawImage(R.drawable.can_gm_park_h_r_03, CanCameraUI.BTN_CCH9_MODE14, Can.CAN_AUDI_ZMYT);
                    return false;
                case 38:
                    view.drawImage(R.drawable.can_gm_park_b_car, 656, 204);
                    view.drawImage(R.drawable.can_gm_park_02, CanCameraUI.BTN_GOLF_WC_MODE2, 221);
                    view.drawImage(R.drawable.can_gm_park_h_l_03, 434, 149);
                    return false;
                case 39:
                case 40:
                case 41:
                case 42:
                    view.drawImage(R.drawable.can_gm_park_b_car, 468, 204);
                    view.drawImage(R.drawable.can_gm_park_19, 489, 148);
                    view.drawImage(R.drawable.can_gm_park_r_03, 626, Can.CAN_AUDI_ZMYT);
                    return false;
                default:
                    return false;
            }
        }
    };
    private Button mParkRight;
    private TextView mParkType;
    private Button mParkVertical;

    public CanGMActivityParkView(Activity activity) {
        super(activity);
    }

    public boolean onTouch(View arg0, MotionEvent arg1) {
        return false;
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case 1:
                CanJni.GMCarCtrl(95, 1);
                return;
            case 2:
                CanJni.GMCarCtrl(95, 2);
                return;
            case 3:
                CanJni.GMCarCtrl(95, 3);
                return;
            case 4:
                CanJni.GMCarCtrl(95, 4);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        setBackgroundResource(R.drawable.can_gm_park_bg);
        this.mParkInfoArray = getActivity().getResources().getStringArray(R.array.can_gmrzc_active_park);
        this.mManager = getRelativeManager();
        this.mParkInfo = this.mManager.AddText(Can.CAN_BENZ_SMART_OD, 8, 763, 100);
        this.mParkInfo.setTextColor(-1);
        this.mParkInfo.setGravity(17);
        this.mParkInfo.setTextSize(0, 30.0f);
        this.mParkType = this.mManager.AddText(28, 8, 189, 73);
        this.mParkType.setText(R.string.can_park_type_c);
        this.mParkType.setTextColor(-1);
        this.mParkType.setGravity(17);
        this.mParkType.setTextSize(0, 30.0f);
        this.mParkDir = this.mManager.AddText(28, Can.CAN_SE_DX7_RZC, 189, 73);
        this.mParkDir.setText(R.string.can_park_dir_c);
        this.mParkDir.setTextColor(-1);
        this.mParkDir.setGravity(17);
        this.mParkDir.setTextSize(0, 30.0f);
        this.mParkHorizonal = this.mManager.AddButton(145, 86, 60, 137);
        this.mParkHorizonal.setBackgroundResource(R.drawable.btn_gm_park_horizonal);
        this.mParkHorizonal.setId(1);
        this.mParkVertical = this.mManager.AddButton(18, 90, 88, 115);
        this.mParkVertical.setBackgroundResource(R.drawable.btn_gm_park_vertical);
        this.mParkVertical.setId(2);
        this.mImgCarDir = this.mManager.AddImageEx(89, KeyDef.RKEY_RADIO_3S, 67, 64, R.drawable.can_gm_park_08);
        this.mParkLeft = this.mManager.AddButton(18, KeyDef.RKEY_LOC, 88, 115);
        this.mParkLeft.setBackgroundResource(R.drawable.btn_gm_park_vertical);
        this.mParkLeft.setId(3);
        this.mParkRight = this.mManager.AddButton(137, KeyDef.RKEY_LOC, 88, 115);
        this.mParkRight.setBackgroundResource(R.drawable.btn_gm_park_vertical_r);
        this.mParkRight.setId(4);
        this.mParkHorizonal.setOnClickListener(this);
        this.mParkVertical.setOnClickListener(this);
        this.mParkLeft.setOnClickListener(this);
        this.mParkRight.setOnClickListener(this);
    }

    public void doOnResume() {
        this.mImgPark = this.mManager.AddImage(0, 0, 1024, CanCameraUI.BTN_NISSAN_XTRAL_RVS_ASSIST6);
        this.mImgPark.setUserPaint(this.mParkPaint);
        super.doOnResume();
    }

    public void ResetData(boolean check) {
        boolean z;
        boolean z2;
        boolean z3 = true;
        CanJni.GmDealAutoPark(this.mAutoPark);
        if (!i2b(this.mAutoPark.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mAutoPark.Update)) {
            this.mAutoPark.Update = 0;
            Log.d("lq", "mAutoPark.Sta == " + this.mAutoPark.Sta);
            if (this.mAutoPark.Sta == 0) {
                getActivity().finish();
                return;
            }
            this.mImgPark.invalidate();
            this.mParkHorizonal.setSelected(this.mAutoPark.Type == 0);
            Button button = this.mParkVertical;
            if (this.mAutoPark.Type != 0) {
                z = true;
            } else {
                z = false;
            }
            button.setSelected(z);
            Button button2 = this.mParkLeft;
            if (this.mAutoPark.Dir == 0) {
                z2 = true;
            } else {
                z2 = false;
            }
            button2.setSelected(z2);
            Button button3 = this.mParkRight;
            if (this.mAutoPark.Dir == 0) {
                z3 = false;
            }
            button3.setSelected(z3);
            this.mParkLeft.setBackgroundResource(this.mAutoPark.Type == 0 ? R.drawable.btn_gm_park_horizonal_l : R.drawable.btn_gm_park_vertical);
            this.mParkRight.setBackgroundResource(this.mAutoPark.Type == 0 ? R.drawable.btn_gm_park_horizonal_r : R.drawable.btn_gm_park_vertical_r);
            this.mImgCarDir.setImageResource(this.mAutoPark.Dir == 0 ? R.drawable.can_gm_park_09 : R.drawable.can_gm_park_08);
            initParkInfo(this.mAutoPark.TsMsg);
            if (this.mAutoPark.TsMsg == 27 || this.mAutoPark.TsMsg == 28) {
                this.mParkType.setVisibility(8);
                this.mParkVertical.setVisibility(8);
                this.mParkHorizonal.setVisibility(8);
                this.mImgCarDir.setVisibility(8);
                this.mParkLeft.setVisibility(8);
                this.mParkRight.setVisibility(8);
                return;
            }
            this.mParkType.setVisibility(0);
            this.mParkVertical.setVisibility(0);
            this.mParkHorizonal.setVisibility(0);
            this.mImgCarDir.setVisibility(0);
            this.mParkLeft.setVisibility(0);
            this.mParkRight.setVisibility(0);
        }
    }

    private void initParkInfo(int tsMsg) {
        switch (tsMsg) {
            case 1:
            case 3:
                this.mParkInfo.setText(this.mParkInfoArray[0]);
                return;
            case 2:
            case 4:
                this.mParkInfo.setText(this.mParkInfoArray[1]);
                return;
            case 9:
                this.mParkInfo.setText(this.mParkInfoArray[6]);
                return;
            case 13:
                this.mParkInfo.setText(this.mParkInfoArray[10]);
                return;
            case 17:
                this.mParkInfo.setText(this.mParkInfoArray[11]);
                return;
            case 18:
                this.mParkInfo.setText(this.mParkInfoArray[12]);
                return;
            case 19:
                this.mParkInfo.setText(this.mParkInfoArray[13]);
                return;
            case 22:
                this.mParkInfo.setText(this.mParkInfoArray[14]);
                return;
            case 27:
                this.mParkInfo.setText(this.mParkInfoArray[17]);
                return;
            case 28:
                this.mParkInfo.setText(this.mParkInfoArray[18]);
                return;
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
                this.mParkInfo.setText(this.mParkInfoArray[tsMsg - 10]);
                return;
            default:
                this.mParkInfo.setText("");
                return;
        }
    }

    public void QueryData() {
    }
}
