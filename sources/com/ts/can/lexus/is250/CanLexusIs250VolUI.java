package com.ts.can.lexus.is250;

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
import com.ts.can.CanFunc;
import com.ts.other.CustomImgView;
import com.ts.other.CustomTextView;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;
import com.txznet.sdk.TXZResourceManager;
import com.yyw.ts70xhw.KeyDef;

public class CanLexusIs250VolUI implements UserCallBack {
    public static final String TAG = "CanLexusIs250VolUI";
    protected static Context mContext;
    static CanLexusIs250VolUI mInstance;
    protected static boolean mIsVol;
    private RelativeLayout mLayout;
    private RelativeLayoutManager mManager;
    private CustomImgView mMuteSta;
    private CanDataInfo.Is250_Vol mVolInfo = new CanDataInfo.Is250_Vol();
    private CustomTextView mVolVal;
    private WindowManager wManager;
    private WindowManager.LayoutParams wmParams = new WindowManager.LayoutParams();

    protected CanLexusIs250VolUI() {
    }

    public static CanLexusIs250VolUI GetInstance() {
        if (mInstance == null) {
            mInstance = new CanLexusIs250VolUI();
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
        if (this.mLayout != null || context == null) {
            Log.d("CanLexusIs250VolUI", "Already have instance");
            return;
        }
        Log.d("CanLexusIs250VolUI", "Init");
        mContext = context;
        this.mLayout = (RelativeLayout) LayoutInflater.from(mContext).inflate(R.layout.activity_can_lexus_vol, (ViewGroup) null);
        InintWinManage(91, 91, KeyDef.SKEY_MUTE_3, 17, mContext);
        this.wManager.addView(this.mLayout, this.wmParams);
        this.mManager = new RelativeLayoutManager(this.mLayout);
        this.mMuteSta = this.mManager.AddImage(0, 0);
        this.mMuteSta.setStateDrawable(R.drawable.can_sound_dn, R.drawable.can_mute_up);
        this.mVolVal = AddText(0, 5, 91, 91);
        this.mVolVal.setTextColor(-1);
    }

    public void Destroy() {
        Log.i("CanLexusIs250VolUI", "Destroy");
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
        temp.setText(TXZResourceManager.STYLE_DEFAULT);
        temp.setGravity(49);
        return temp;
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        InitVol(CanFunc.mContext);
        ResetData(false);
        mIsVol = true;
        this.mLayout.setVisibility(0);
        Log.d("CanLexusIs250VolUI", "-----onResume-----");
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        Log.d("CanLexusIs250VolUI", "-----onPause-----");
        mIsVol = false;
        this.mLayout.setVisibility(4);
    }

    private void updateACUI() {
        this.mVolInfo.Update = 0;
        Log.d("CanLexusIs250VolUI", "mVolInfo.Vol =" + this.mVolInfo.Vol);
        if (this.mVolInfo.Sta == 0) {
            onPause();
            Log.d("CanLexusIs250VolUI", "Lexus UserAll Exit vol");
        } else if (this.mVolInfo.Vol == 0) {
            this.mVolVal.setText(TXZResourceManager.STYLE_DEFAULT);
            this.mMuteSta.setSelected(true);
        } else {
            this.mVolVal.setText(String.format("%02d", new Object[]{Integer.valueOf(this.mVolInfo.Vol)}));
            this.mMuteSta.setSelected(false);
        }
    }

    private void ResetData(boolean check) {
        CanJni.LexusIs250GetVol(this.mVolInfo);
        if (!check || this.mVolInfo.Update != 0) {
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
