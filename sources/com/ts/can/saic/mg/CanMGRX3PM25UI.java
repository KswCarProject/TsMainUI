package com.ts.can.saic.mg;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.other.CustomImgView;
import com.ts.other.CustomTextView;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;
import com.yyw.ts70xhw.KeyDef;

public class CanMGRX3PM25UI implements UserCallBack {
    public static final String TAG = "CanMGRX3PM25UI";
    protected static Context mContext;
    static CanMGRX3PM25UI mInstance;
    protected static boolean mIsPM;
    private RelativeLayout mLayout;
    private RelativeLayoutManager mManager;
    private CanDataInfo.MG_RX3_PM mPMInfo = new CanDataInfo.MG_RX3_PM();
    private CustomImgView mPMIv;
    private CustomTextView mPMTxt;
    private WindowManager wManager;
    private WindowManager.LayoutParams wmParams = new WindowManager.LayoutParams();

    protected CanMGRX3PM25UI() {
    }

    public static CanMGRX3PM25UI GetInstance() {
        if (mInstance == null) {
            mInstance = new CanMGRX3PM25UI();
        }
        return mInstance;
    }

    public void InintWinManage(int nSizeX, int nSizeY, int nPosX, int nPosY, Context MyContext) {
        this.wManager = (WindowManager) MyContext.getSystemService("window");
        this.wmParams = new WindowManager.LayoutParams();
        this.wmParams.type = 2003;
        this.wmParams.format = 1;
        this.wmParams.flags = 24;
        this.wmParams.gravity = 51;
        this.wmParams.x = nPosX;
        this.wmParams.y = nPosY;
        this.wmParams.width = nSizeX;
        this.wmParams.height = nSizeY;
    }

    public void InitPM(Context context) {
        if (this.mLayout == null && context != null) {
            mContext = context;
            this.mLayout = (RelativeLayout) LayoutInflater.from(mContext).inflate(R.layout.activity_can_lexus_vol, (ViewGroup) null);
            InintWinManage(181, 91, KeyDef.SKEY_MUTE_3, 17, mContext);
            this.wManager.addView(this.mLayout, this.wmParams);
            this.mManager = new RelativeLayoutManager(this.mLayout);
            this.mPMIv = this.mManager.AddImage(0, 0);
            this.mPMTxt = AddText(48, 15, 181, 91);
            this.mPMTxt.setTextColor(-1);
            this.mPMTxt.setText(R.string.can_pm_25);
            onPause();
        }
    }

    public void Destroy() {
        Log.i("CanMGRX3PM25UI", "Destroy");
        if (this.mLayout != null) {
            this.wManager.removeView(this.mLayout);
            this.mLayout = null;
        }
    }

    /* access modifiers changed from: protected */
    public ParamButton AddImage(int x, int y, int w, int h, int up, int dn) {
        ParamButton btn = this.mManager.AddButton(x, y, w, h);
        btn.setDrawable(up, dn);
        return btn;
    }

    /* access modifiers changed from: protected */
    public CustomTextView AddText(int x, int y, int w, int h) {
        CustomTextView temp = this.mManager.AddCusText(x, y, w, h);
        temp.SetPxSize(40);
        temp.setText("");
        temp.setGravity(49);
        return temp;
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        ResetData(false);
        mIsPM = true;
        this.mLayout.setVisibility(0);
        Log.d("CanMGRX3PM25UI", "-----onResume-----");
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        Log.d("CanMGRX3PM25UI", "-----onPause-----");
        mIsPM = false;
        this.mLayout.setVisibility(4);
    }

    private void setPM(int val) {
        if (val > 0 && val <= 37) {
            this.mPMIv.setImageResource(R.drawable.can_fan_green);
        } else if (val >= 38 && val <= 75) {
            this.mPMIv.setImageResource(R.drawable.can_fan_yellow);
        } else if (val >= 76 && val <= 125) {
            this.mPMIv.setImageResource(R.drawable.can_fan_orange);
        } else if (val >= 126 && val <= 250) {
            this.mPMIv.setImageResource(R.drawable.can_fan_red);
        } else if (val >= 251 && val <= 255) {
            this.mPMIv.setImageResource(R.drawable.can_fan_gray);
        }
    }

    private void ResetData(boolean check) {
        CanJni.RwMgRx3RzcGetPm25(this.mPMInfo);
        if (this.mPMInfo.UpdateOnce == 0) {
            return;
        }
        if (!check || this.mPMInfo.Update != 0) {
            this.mPMInfo.Update = 0;
            setPM(this.mPMInfo.Val);
        }
    }

    public void UserAll() {
        if (this.mPMIv != null && this.mPMTxt != null) {
            if (mIsPM) {
                ResetData(true);
                return;
            }
            CanJni.RwMgRx3RzcGetPm25(this.mPMInfo);
            if (this.mPMInfo.UpdateOnce != 0) {
                onResume();
            }
        }
    }
}
