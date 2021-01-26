package com.ts.can;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.benc.withcd.CanBencWithCDSoSync;
import com.ts.main.common.KeyTouch;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;
import com.yyw.ts70xhw.KeyDef;

public class CanSmartSeatActivity extends CanBaseActivity implements View.OnClickListener, UserCallBack {
    private static final int ITEM_BUTTON_1 = 20;
    private static final int ITEM_BUTTON_2 = 21;
    private static final int ITEM_BUTTON_3 = 22;
    private static final int ITEM_FIRST_TOGGLE = 10;
    private static final int ITEM_MIN_1 = 30;
    private static final int ITEM_MIN_2 = 31;
    private static final int ITEM_MIN_3 = 32;
    private static final int ITEM_SECOND_TOGGLE = 11;
    private static int[] mFirstSeatIcons = {R.drawable.can_xhd_chair_01, R.drawable.can_xhd_chair_02, R.drawable.can_xhd_chair_03};
    private ParamButton[] mBtnMinutes = new ParamButton[3];
    private ParamButton[] mBtnNumbers = new ParamButton[3];
    private CanDataInfo.CanBcZmytDevZyam mDevZyam;
    private CanDataInfo.CanBcZmytDevZyyb mDevZyyb;
    private ImageView mIvFirstSeat;
    private ImageView mIvSecondSeat;
    private ParamButton[] mIvToggles = new ParamButton[2];
    private RelativeLayoutManager mManager;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_can_comm_relative);
        this.mManager = new RelativeLayoutManager(this, R.id.can_comm_layout);
        this.mManager.GetLayout().setBackgroundResource(R.drawable.can_xhd_chair_bg);
        DisplayMetrics outMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
        int widthPixels = outMetrics.widthPixels;
        int heightPixels = outMetrics.heightPixels;
        Log.d("HAHA", "widthPixels = " + widthPixels + " , heightPixels = " + heightPixels);
        if (widthPixels == 1920 && heightPixels == 720) {
            InitViews_1920x720();
        } else {
            InitViews();
        }
        initData();
    }

    private void InitViews_1920x720() {
        this.mManager.AddImageEx(273, CanCameraUI.BTN_CHANA_CS75_MODE5, 371, 60, R.drawable.can_xhd_chair_button_box);
        this.mManager.AddImageEx(1149, CanCameraUI.BTN_CHANA_CS75_MODE5, CanCameraUI.BTN_GOLF_WC_MODE3, 60, R.drawable.can_xhd_chair_min_box);
        this.mIvFirstSeat = this.mManager.AddImageEx(217, 114, CanCameraUI.BTN_NISSAN_XTRAL_RVS_ASSIST5, 451, R.drawable.can_xhd_chair_01);
        this.mIvSecondSeat = this.mManager.AddImageEx(1335, 100, Can.CAN_FLAT_WC, 477, R.drawable.can_xhd_chair_zuoyi_qs);
        this.mIvToggles[0] = addButton(CanCameraUI.BTN_SENOVA_SUB_BJ40_MODE3, 274, 134, 59, R.drawable.can_xhd_chair_off, R.drawable.can_xhd_chair_on, 10);
        this.mIvToggles[1] = addButton(CanCameraUI.BTN_NISSAN_XTRAL_RZC_RVS_ASSIST1, 274, 134, 59, R.drawable.can_xhd_chair_off, R.drawable.can_xhd_chair_on, 11);
        this.mBtnNumbers[2] = addTextButton(278, CanCameraUI.BTN_CAMERY_2018_MODE1, 119, 47, R.drawable.can_xhd_chair_button_up, R.drawable.can_xhd_chair_button_dn, 22, "3");
        this.mBtnNumbers[1] = addTextButton(399, CanCameraUI.BTN_CAMERY_2018_MODE1, 119, 47, R.drawable.can_xhd_chair_button_up, R.drawable.can_xhd_chair_button_dn, 21, "2");
        this.mBtnNumbers[0] = addTextButton(CanCameraUI.BTN_GEELY_YJX6_MODE1, CanCameraUI.BTN_CAMERY_2018_MODE1, 119, 47, R.drawable.can_xhd_chair_button_up, R.drawable.can_xhd_chair_button_dn, 20, "1");
        this.mBtnMinutes[0] = addTextButton(1155, CanCameraUI.BTN_CAMERY_2018_MODE1, 195, 47, R.drawable.can_xhd_chair_min_up, R.drawable.can_xhd_chair_min_dn, 30, getString(R.string.can_time_5min));
        this.mBtnMinutes[1] = addTextButton(CanCameraUI.BTN_PRODOC_MODE3, CanCameraUI.BTN_CAMERY_2018_MODE1, 195, 47, R.drawable.can_xhd_chair_min_up, R.drawable.can_xhd_chair_min_dn, 31, getString(R.string.can_time_10min));
        this.mBtnMinutes[2] = addTextButton(1549, CanCameraUI.BTN_CAMERY_2018_MODE1, 195, 47, R.drawable.can_xhd_chair_min_up, R.drawable.can_xhd_chair_min_dn, 32, getString(R.string.can_time_15min));
        addText(620, 40, R.string.can_smart_seat_yb);
        addText(CanCameraUI.BTN_MG_2D, 40, R.string.can_smart_seat_am);
    }

    private void InitViews() {
        this.mManager.AddImageEx(103, 457, 288, 49, R.drawable.can_xhd_chair_button_box);
        this.mManager.AddImageEx(CanCameraUI.BTN_GEELY_YJX6_GJ, 457, 465, 49, R.drawable.can_xhd_chair_min_box);
        this.mIvFirstSeat = this.mManager.AddImageEx(43, 107, 418, 345, R.drawable.can_xhd_chair_01);
        this.mIvSecondSeat = this.mManager.AddImageEx(669, 95, 177, KeyTouch.GAMMA_MAX_NUM, R.drawable.can_xhd_chair_zuoyi);
        this.mIvToggles[0] = addButton(KeyDef.RKEY_POWER_ON, Can.CAN_X80_RZC, 103, 54, R.drawable.can_xhd_chair_off, R.drawable.can_xhd_chair_on, 10);
        this.mIvToggles[1] = addButton(878, Can.CAN_X80_RZC, 103, 54, R.drawable.can_xhd_chair_off, R.drawable.can_xhd_chair_on, 11);
        this.mBtnNumbers[2] = addTextButton(108, 457, 91, 49, R.drawable.can_xhd_chair_button_up, R.drawable.can_xhd_chair_button_dn, 22, "3");
        this.mBtnNumbers[1] = addTextButton(201, 457, 91, 49, R.drawable.can_xhd_chair_button_up, R.drawable.can_xhd_chair_button_dn, 21, "2");
        this.mBtnNumbers[0] = addTextButton(294, 457, 91, 49, R.drawable.can_xhd_chair_button_up, R.drawable.can_xhd_chair_button_dn, 20, "1");
        this.mBtnMinutes[0] = addTextButton(CanCameraUI.BTN_CHANA_ALSVINV7_MODE2, 457, 150, 49, R.drawable.can_xhd_chair_min_up, R.drawable.can_xhd_chair_min_dn, 30, getString(R.string.can_time_5min));
        this.mBtnMinutes[1] = addTextButton(683, 457, 150, 49, R.drawable.can_xhd_chair_min_up, R.drawable.can_xhd_chair_min_dn, 31, getString(R.string.can_time_8min));
        this.mBtnMinutes[2] = addTextButton(KeyDef.SKEY_HOME_2, 457, 150, 49, R.drawable.can_xhd_chair_min_up, R.drawable.can_xhd_chair_min_dn, 32, getString(R.string.can_time_10min));
        addText(KeyDef.RKEY_MEDIA_SLOW, 40, R.string.can_smart_seat_yb);
        addText(KeyDef.SKEY_CALLDN_2, 40, R.string.can_smart_seat_am);
    }

    private void initData() {
        this.mDevZyyb = new CanDataInfo.CanBcZmytDevZyyb();
        this.mDevZyam = new CanDataInfo.CanBcZmytDevZyam();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
        CanJni.BencZmytDevCmd(3, 1, this.mDevZyam.Sta, 1, this.mDevZyam.Bcgzsysj, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        CanJni.BencZmytDevCmd(3, 1, this.mDevZyam.Sta, 0, this.mDevZyam.Bcgzsysj, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        String[] strArr = {CanBencWithCDSoSync.KEY_SEAT_AM_STATUS, CanBencWithCDSoSync.KEY_SEAT_AM_FRONT, CanBencWithCDSoSync.KEY_SEAT_AM_TIME};
        int[] iArr = new int[3];
        iArr[0] = this.mDevZyam.Sta;
        iArr[2] = this.mDevZyam.Bcgzsysj;
        CanBencWithCDSoSync.SetValue(this, strArr, iArr);
    }

    private void ResetData(boolean check) {
        CanJni.BencZmytWithCDGetDevZyyb(this.mDevZyyb);
        if (i2b(this.mDevZyyb.UpdateOnce) && (!check || i2b(this.mDevZyyb.Update))) {
            this.mDevZyyb.Update = 0;
            this.mIvToggles[0].SetSel(this.mDevZyyb.Sta);
            if (this.mDevZyyb.Ybdj == 3 || this.mDevZyyb.Ybdj == 4) {
                updateFirstSeat(0);
            } else if (this.mDevZyyb.Ybdj == 5 || this.mDevZyyb.Ybdj == 6) {
                updateFirstSeat(1);
            } else if (this.mDevZyyb.Ybdj == 7 || this.mDevZyyb.Ybdj == 8) {
                updateFirstSeat(2);
            } else {
                updateFirstSeat(1);
            }
            CanBencWithCDSoSync.SetValue(this, new String[]{CanBencWithCDSoSync.KEY_SEAT_YB_STATUS, CanBencWithCDSoSync.KEY_SEAT_YB_LEVEL}, new int[]{this.mDevZyyb.Sta, this.mDevZyyb.Ybdj});
        }
        CanJni.BencZmytWithCDGetDevZyam(this.mDevZyam);
        if (!i2b(this.mDevZyam.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mDevZyam.Update)) {
            this.mDevZyam.Update = 0;
            this.mIvToggles[1].SetSel(this.mDevZyam.Sta);
            if (this.mDevZyam.Dqgzfx_f == 1) {
                this.mIvSecondSeat.setImageResource(R.drawable.can_xhd_chair_zuoyi_qs);
            } else if (this.mDevZyam.Dqgzfx_u == 1) {
                this.mIvSecondSeat.setImageResource(R.drawable.can_xhd_chair_zuoyi_bb);
            } else if (this.mDevZyam.Dqgzfx_d == 1) {
                this.mIvSecondSeat.setImageResource(R.drawable.can_xhd_chair_zuoyi_yb);
            } else {
                this.mIvSecondSeat.setImageResource(R.drawable.can_xhd_chair_zuoyi);
            }
            if (this.mDevZyam.Bcgzsysj == 0) {
                updateSecondSeat(-1);
            } else if (this.mDevZyam.Bcgzsysj >= 1 && this.mDevZyam.Bcgzsysj <= 5) {
                updateSecondSeat(0);
            } else if (this.mDevZyam.Bcgzsysj > 5 && this.mDevZyam.Bcgzsysj <= 8) {
                updateSecondSeat(1);
            } else if (this.mDevZyam.Bcgzsysj > 8 && this.mDevZyam.Bcgzsysj <= 10) {
                updateSecondSeat(2);
            }
            CanBencWithCDSoSync.SetValue(this, new String[]{CanBencWithCDSoSync.KEY_SEAT_AM_STATUS, CanBencWithCDSoSync.KEY_SEAT_AM_FRONT, CanBencWithCDSoSync.KEY_SEAT_AM_TIME}, new int[]{this.mDevZyam.Sta, 1, this.mDevZyam.Bcgzsysj});
        }
    }

    private void updateFirstSeat(int index) {
        for (int i = 0; i < this.mBtnNumbers.length; i++) {
            this.mBtnNumbers[i].setSelected(false);
            if (i == index) {
                this.mBtnNumbers[i].setSelected(true);
            }
        }
    }

    private void updateSecondSeat(int index) {
        for (int i = 0; i < this.mBtnMinutes.length; i++) {
            this.mBtnMinutes[i].setSelected(false);
            if (i == index) {
                this.mBtnMinutes[i].setSelected(true);
            }
        }
    }

    public void onClick(View v) {
        int id = ((Integer) v.getTag()).intValue();
        if (id == 10) {
            CanJni.BencZmytDevCmd(4, 1, SwValue(this.mDevZyyb.Sta), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        } else if (id == 11) {
            CanJni.BencZmytDevCmd(3, 1, SwValue(this.mDevZyam.Sta), 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        } else if (id == 20) {
            CanJni.BencZmytDevCmd(4, 1, this.mDevZyyb.Sta, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        } else if (id == 21) {
            CanJni.BencZmytDevCmd(4, 1, this.mDevZyyb.Sta, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        } else if (id == 22) {
            CanJni.BencZmytDevCmd(4, 1, this.mDevZyyb.Sta, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        } else if (id == 30) {
            CanJni.BencZmytDevCmd(3, 1, this.mDevZyam.Sta, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        } else if (id == 31) {
            CanJni.BencZmytDevCmd(3, 1, this.mDevZyam.Sta, 1, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        } else if (id == 32) {
            CanJni.BencZmytDevCmd(3, 1, this.mDevZyam.Sta, 1, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        }
    }

    public void UserAll() {
        ResetData(true);
    }

    private int SwValue(int val) {
        return val == 0 ? 1 : 0;
    }

    public ParamButton addButton(int x, int y, int w, int h, int normal, int selected, int id) {
        ParamButton button = this.mManager.AddButton(x, y, w, h);
        button.setStateUpSel(normal, selected);
        button.setOnClickListener(this);
        button.setTag(Integer.valueOf(id));
        return button;
    }

    public ParamButton addTextButton(int x, int y, int w, int h, int normal, int pressed, int id, String text) {
        ParamButton button = this.mManager.AddButton(x, y, w, h);
        button.setStateDrawable(normal, pressed, pressed);
        button.setGravity(17);
        button.setTextColor(-1);
        button.setText(text);
        button.setTextSize(0, 18.0f);
        button.setOnClickListener(this);
        button.setTag(Integer.valueOf(id));
        return button;
    }

    public TextView addText(int x, int y, int textId) {
        TextView tv = this.mManager.AddText(x, y);
        tv.setText(textId);
        tv.setTextColor(-1);
        tv.setTextSize(0, 36.0f);
        return tv;
    }
}
