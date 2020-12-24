package com.ts.can.honda.accord8;

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

public class CanAccord8VolUI implements UserCallBack {
    public static final String TAG = "CanAccord8VolUI";
    protected static Context mContext;
    static CanAccord8VolUI mInstance;
    protected static boolean mIsVol;
    private RelativeLayout mLayout;
    private RelativeLayoutManager mManager;
    private CustomImgView mMuteSta;
    private CanDataInfo.Accord8_VolInfo mVolInfo = new CanDataInfo.Accord8_VolInfo();
    private CustomTextView mVolVal;
    private WindowManager wManager;
    private WindowManager.LayoutParams wmParams = new WindowManager.LayoutParams();

    protected CanAccord8VolUI() {
    }

    public static CanAccord8VolUI GetInstance() {
        if (mInstance == null) {
            mInstance = new CanAccord8VolUI();
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

    public void InitVol(Context context) {
        if (this.mLayout == null && context != null) {
            mContext = context;
            this.mLayout = (RelativeLayout) LayoutInflater.from(mContext).inflate(R.layout.activity_can_lexus_vol, (ViewGroup) null);
            InintWinManage(91, 91, KeyDef.SKEY_MUTE_3, 17, mContext);
            this.wManager.addView(this.mLayout, this.wmParams);
            this.mManager = new RelativeLayoutManager(this.mLayout);
            this.mMuteSta = this.mManager.AddImage(0, 0);
            this.mMuteSta.setStateDrawable(R.drawable.can_sound_dn, R.drawable.can_mute_up);
            this.mVolVal = AddText(0, 5, 91, 91);
            this.mVolVal.setTextColor(-1);
            onPause();
        }
    }

    public void Destroy() {
        Log.i("CanAccord8VolUI", "Destroy");
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
        mIsVol = true;
        this.mLayout.setVisibility(0);
        Log.d("CanAccord8VolUI", "-----onResume-----");
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        Log.d("CanAccord8VolUI", "-----onPause-----");
        mIsVol = false;
        this.mLayout.setVisibility(4);
    }

    private void updateACUI() {
        this.mVolInfo.Update = 0;
        Log.d("CanAccord8VolUI", "mVolInfo.Vol =" + this.mVolInfo.Vol);
        if (this.mVolInfo.VolSta == 0) {
            onPause();
            Log.d("CanAccord8VolUI", "Lexus UserAll Exit vol");
        } else if (this.mVolInfo.Vol == 0) {
            this.mVolVal.setText("");
            this.mMuteSta.setSelected(true);
        } else {
            this.mVolVal.setText(String.format("%02d", new Object[]{Integer.valueOf(this.mVolInfo.Vol)}));
            this.mMuteSta.setSelected(false);
        }
    }

    private void ResetData(boolean check) {
        CanJni.Accord8GetVolInfo(this.mVolInfo);
        if (this.mVolInfo.UpdateOnce == 0) {
            return;
        }
        if (!check || this.mVolInfo.Update != 0) {
            this.mVolInfo.Update = 0;
            updateACUI();
        }
    }

    public void UserAll() {
        if (mIsVol) {
            ResetData(true);
        }
    }

    public void ShowVol() {
        if (!mIsVol) {
            onResume();
        }
    }
}
