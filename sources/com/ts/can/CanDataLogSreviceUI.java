package com.ts.can;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.other.CustomImgView;
import com.ts.other.CustomTextView;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;
import com.yyw.ts70xhw.KeyDef;

public class CanDataLogSreviceUI implements UserCallBack {
    public static final String TAG = "CanMGRX3PM25UI";
    protected static Context mContext;
    static CanDataLogSreviceUI mInstance;
    private CustomImgView mCanIv;
    private CustomTextView mCanTxt;
    private RelativeLayout mLayout;
    private RelativeLayoutManager mManager;
    private WindowManager wManager;
    private WindowManager.LayoutParams wmParams = new WindowManager.LayoutParams();

    protected CanDataLogSreviceUI() {
    }

    public static CanDataLogSreviceUI GetInstance() {
        if (mInstance == null) {
            mInstance = new CanDataLogSreviceUI();
        }
        return mInstance;
    }

    public void InintWinManage(int nSizeX, int nSizeY, int nPosX, int nPosY, Context MyContext) {
        this.wManager = (WindowManager) MyContext.getSystemService("window");
        this.wmParams = new WindowManager.LayoutParams();
        this.wmParams.type = 2003;
        this.wmParams.format = 1;
        this.wmParams.flags = 8;
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
            InintWinManage(181, 95, KeyDef.SKEY_MUTE_3, 417, mContext);
            this.wManager.addView(this.mLayout, this.wmParams);
            this.mManager = new RelativeLayoutManager(this.mLayout);
            this.mCanIv = this.mManager.AddImage(48, 0, 95, 95);
            this.mCanIv.setStateDrawable(R.drawable.can_psa_yuan_gray, R.drawable.can_psa_yuan_gray);
            this.mCanTxt = AddText(48, 0, 95, 95);
            this.mCanTxt.setTextColor(-16777216);
            this.mCanTxt.setText("TS");
            this.mCanIv.setOnClickListener(new View.OnClickListener() {
                public void onClick(View arg0) {
                    Log.d("lq", "onclick");
                    CanDataLogSreviceUI.mContext.startService(new Intent(CanDataLogSreviceUI.mContext, CanDataLogService.class));
                    CanDataLogSreviceUI.this.Destroy();
                }
            });
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
        temp.setGravity(17);
        return temp;
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        this.mLayout.setVisibility(0);
        Log.d("CanMGRX3PM25UI", "-----onResume-----");
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        Log.d("CanMGRX3PM25UI", "-----onPause-----");
        this.mLayout.setVisibility(4);
    }

    public void UserAll() {
        onResume();
    }
}
