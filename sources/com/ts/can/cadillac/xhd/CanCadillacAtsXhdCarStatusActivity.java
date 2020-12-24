package com.ts.can.cadillac.xhd;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanBaseActivity;
import com.ts.can.CanCameraUI;
import com.ts.other.CustomImgView;
import com.ts.other.CustomTextView;
import com.ts.other.RelativeLayoutManager;
import com.yyw.ts70xhw.KeyDef;

public class CanCadillacAtsXhdCarStatusActivity extends CanBaseActivity implements View.OnClickListener, UserCallBack {
    public static final String TAG = "CanCadillacAtsXhdCarStatusActivity";
    private int mCsYoubiao = 0;
    private CanDataInfo.CadillacInfo1 mInfo1 = new CanDataInfo.CadillacInfo1();
    private CanDataInfo.CadillacInfo2 mInfo2 = new CanDataInfo.CadillacInfo2();
    private CanDataInfo.CadillacInfo3 mInfo3 = new CanDataInfo.CadillacInfo3();
    private CustomImgView mItemAqd;
    private CustomImgView mItemCs;
    private CustomImgView mItemHwd;
    private CustomImgView mItemIll;
    private CustomImgView mItemJgd;
    private CustomImgView mItemNearLight;
    private CustomImgView mItemQwd;
    private CustomTextView mItemSyyl;
    private CustomImgView mItemSyylZz;
    private CustomTextView mItemXhlc;
    private CustomTextView mItemXhlcChar;
    private CustomTextView mItemXszlc;
    private CustomTextView mItemXszlcChar;
    private CustomImgView mItemYgd;
    private CustomImgView mItemZs;
    private CustomImgView mItemZxd;
    private RelativeLayoutManager mManager;
    private int mSyylYoubiao = 0;
    private int mZsYoubiao = 0;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        InitUI();
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        setContentView(R.layout.activity_can_comm_relative);
        this.mManager = new RelativeLayoutManager(this, R.id.can_comm_layout);
        this.mManager.GetLayout().setBackgroundResource(R.drawable.clxx_bg);
        this.mItemZs = this.mManager.AddImage(47, 124);
        this.mItemZs.setStateDrawable(R.drawable.clxx_pointer, R.drawable.clxx_pointer);
        this.mItemCs = this.mManager.AddImage(663, 124);
        this.mItemCs.setStateDrawable(R.drawable.clxx_pointer, R.drawable.clxx_pointer);
        this.mItemIll = this.mManager.AddImage(451, 177);
        this.mItemIll.setStateDrawable(R.drawable.clxx_car_light, R.drawable.clxx_car_light);
        this.mItemIll.Show(false);
        this.mItemAqd = this.mManager.AddImage(266, 17);
        this.mItemAqd.setStateDrawable(R.drawable.clxx_aqd, R.drawable.clxx_aqd);
        this.mItemAqd.Show(false);
        this.mItemZxd = this.mManager.AddImage(KeyDef.RKEY_RADIO_3S, 17);
        this.mItemZxd.setStateDrawable(R.drawable.clxx_lzxd, R.drawable.clxx_rzxd);
        this.mItemZxd.Show(false);
        this.mItemJgd = this.mManager.AddImage(385, 17);
        this.mItemJgd.setStateDrawable(R.drawable.clxx_jgd, R.drawable.clxx_jgd);
        this.mItemJgd.Show(false);
        this.mItemQwd = this.mManager.AddImage(445, 17);
        this.mItemQwd.setStateDrawable(R.drawable.clxx_qwd, R.drawable.clxx_qwd);
        this.mItemQwd.Show(false);
        this.mItemHwd = this.mManager.AddImage(CanCameraUI.BTN_HMS7_ESC, 17);
        this.mItemHwd.setStateDrawable(R.drawable.clxx_hwd, R.drawable.clxx_hwd);
        this.mItemHwd.Show(false);
        this.mItemYgd = this.mManager.AddImage(CanCameraUI.BTN_TRUMPCHI_GS7_MODE4, 17);
        this.mItemYgd.setStateDrawable(R.drawable.clxx_fgd, R.drawable.clxx_fgd);
        this.mItemYgd.Show(false);
        this.mItemNearLight = this.mManager.AddImage(CanCameraUI.BTN_CCH9_MODE14, 17);
        this.mItemNearLight.setStateDrawable(R.drawable.clxx_ngd, R.drawable.clxx_ngd);
        this.mItemNearLight.Show(false);
        this.mItemXszlcChar = AddTemp(Can.CAN_DF_WC, Can.CAN_LUXGEN_WC, 90, 30);
        this.mItemXszlcChar.setText(R.string.can_dis_trav);
        this.mItemXszlc = AddTemp(Can.CAN_DF_WC, 271, 90, 60);
        this.mItemXhlcChar = AddTemp(KeyDef.SKEY_VOLUP_3, Can.CAN_LUXGEN_WC, 90, 30);
        this.mItemXhlcChar.setText(R.string.can_range_xhlc);
        this.mItemXhlc = AddTemp(KeyDef.SKEY_VOLUP_3, 281, 90, 30);
        this.mItemSyyl = AddTemp(KeyDef.SKEY_VOLUP_4, 373, 90, 30);
        this.mItemSyylZz = this.mManager.AddImage(655, 116);
        this.mItemSyylZz.setStateDrawable(R.drawable.clxx_pointer_short, R.drawable.clxx_pointer_short);
    }

    /* access modifiers changed from: protected */
    public CustomTextView AddTemp(int x, int y, int w, int h) {
        CustomTextView temp = this.mManager.AddCusText(x, y, w, h);
        temp.SetPxSize(25);
        temp.setText("--");
        temp.setGravity(17);
        return temp;
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        CanJni.CadillacWithCDGetInfo1(this.mInfo1);
        CanJni.CadillacWithCDGetInfo2(this.mInfo2);
        CanJni.CadillacWithCDGetInfo3(this.mInfo3);
        if (i2b(this.mInfo1.UpdateOnce) && (!check || i2b(this.mInfo1.Update))) {
            this.mInfo1.Update = 0;
        }
        if (i2b(this.mInfo2.UpdateOnce) && (!check || i2b(this.mInfo2.Update))) {
            this.mInfo2.Update = 0;
            this.mItemXszlc.setText(String.format("%d Km", new Object[]{Integer.valueOf(this.mInfo2.Xszlc)}));
            if (this.mInfo2.Xhlc > 2000) {
                this.mItemXhlc.setText("--");
            } else {
                this.mItemXhlc.setText(String.format("%d Km", new Object[]{Integer.valueOf(this.mInfo2.Xhlc)}));
            }
            if (this.mInfo2.Syyl > 100) {
                this.mItemSyyl.setText("--");
            } else {
                this.mItemSyyl.setText(String.format("%d L", new Object[]{Integer.valueOf(this.mInfo2.Syyl)}));
            }
        }
        if (!i2b(this.mInfo3.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mInfo3.Update)) {
            this.mInfo3.Update = 0;
            if (this.mInfo3.Ill > 0) {
                this.mItemIll.Show(true);
            } else {
                this.mItemIll.Show(false);
            }
            if (this.mInfo3.PassageSafe > 0 || this.mInfo3.DriveSafe > 0) {
                this.mItemAqd.Show(true);
            } else {
                this.mItemAqd.Show(false);
            }
            if (this.mInfo3.RightTurnLed > 0) {
                this.mItemZxd.Show(true);
                this.mItemZxd.SetSel(1);
            } else if (this.mInfo3.LeftTurnLed > 0) {
                this.mItemZxd.Show(true);
                this.mItemZxd.SetSel(0);
            } else {
                this.mItemZxd.Show(false);
            }
            if (this.mInfo3.WarnLed > 0) {
                this.mItemJgd.Show(true);
            } else {
                this.mItemJgd.Show(false);
            }
            if (this.mInfo3.FrontFogLed > 0) {
                this.mItemQwd.Show(true);
            } else {
                this.mItemQwd.Show(false);
            }
            if (this.mInfo3.RearFogLed > 0) {
                this.mItemHwd.Show(true);
            } else {
                this.mItemHwd.Show(false);
            }
            if (this.mInfo3.FarLight > 0) {
                this.mItemYgd.Show(true);
            } else {
                this.mItemYgd.Show(false);
            }
            if (this.mInfo3.NearLight > 0) {
                this.mItemNearLight.Show(true);
            } else {
                this.mItemNearLight.Show(false);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void YbiaoUpt() {
        float curCs;
        if (this.mInfo1.Zs != this.mZsYoubiao) {
            this.mZsYoubiao = NumApproach(this.mInfo1.Zs, this.mZsYoubiao, 20);
            float curX = ((((float) this.mZsYoubiao) * 240.0f) / 8000.0f) - 120.0f;
            if (curX > 120.0f) {
                curX = -120.0f;
            }
            Log.d(TAG, "curX = " + curX);
            this.mItemZs.setRotation(curX);
        }
        if (this.mInfo1.Cs != this.mCsYoubiao) {
            this.mCsYoubiao = NumApproach(this.mInfo1.Cs, this.mCsYoubiao, 20);
            if (this.mInfo1.Cs <= 60) {
                curCs = ((((float) this.mCsYoubiao) * 90.0f) / 60.0f) - 120.0f;
            } else {
                curCs = ((((float) this.mCsYoubiao) * 150.0f) / 200.0f) - 75.0f;
            }
            Log.d(TAG, "curCs = " + curCs);
            if (curCs > 120.0f) {
                curCs = -120.0f;
            }
            this.mItemCs.setRotation(curCs);
        }
        if (this.mInfo2.Syyl != this.mSyylYoubiao) {
            this.mSyylYoubiao = NumApproach(this.mInfo2.Syyl, this.mSyylYoubiao, 20);
            float curSyyl = ((((float) this.mSyylYoubiao) * 60.0f) / 65.0f) - 30.0f;
            Log.d(TAG, "curSyyl = " + curSyyl);
            if (curSyyl > 30.0f) {
                curSyyl = 30.0f;
            }
            this.mItemSyylZz.setRotation(-curSyyl);
        }
    }

    public static int AbsSub(int a, int b) {
        if (a > b) {
            return a - b;
        }
        return b - a;
    }

    public static int NumApproach(int des, int src, int chk) {
        if (des == src) {
            return src;
        }
        if (AbsSub(des, src) > chk) {
            return src + ((des - src) / chk);
        }
        if (src < des) {
            return src + 1;
        }
        return src - 1;
    }

    /* access modifiers changed from: protected */
    public boolean IsHaveItem(int item) {
        return i2b(0);
    }

    /* access modifiers changed from: protected */
    public void ShowItem(int item) {
        boolean IsHaveItem = IsHaveItem(item);
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
        CanJni.CadillacWithCDQuery(55);
        Sleep(20);
        CanJni.CadillacWithCDConfigSet(54, 1);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        CanJni.CadillacWithCDConfigSet(54, 0);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
        QueryData();
        LayoutUI();
        this.mZsYoubiao = 1;
        this.mCsYoubiao = 1;
        this.mSyylYoubiao = 1;
    }

    public void onClick(View v) {
    }

    public void UserAll() {
        ResetData(true);
        YbiaoUpt();
    }
}
