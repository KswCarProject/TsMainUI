package com.ts.can.faw.dj.b70;

import android.app.Activity;
import android.support.v4.internal.view.SupportMenu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.Evc;
import com.ts.MainUI.R;
import com.ts.can.CanCameraUI;
import com.ts.can.CanCarInfoSub1Activity;
import com.ts.can.CanRelativeCarInfoView;
import com.ts.other.CustomTextView;
import com.ts.other.ParamButton;

public class CanB70DjCarDevView extends CanRelativeCarInfoView {
    public static final int ITEM_CD = -1;
    public static final int ITEM_RADIO = -2;
    public static final int ITEM_SET = -3;
    protected ParamButton mBtnCD;
    protected ParamButton mBtnRadio;
    protected ParamButton mBtnSet;
    protected CanDataInfo.B70_Dj_VolInfo mInfoData;
    protected TextView mTxtVol;

    public CanB70DjCarDevView(Activity activity) {
        super(activity);
    }

    public boolean onTouch(View arg0, MotionEvent arg1) {
        return false;
    }

    public void onClick(View v) {
        enterSubWin(CanCarInfoSub1Activity.class, ((Integer) v.getTag()).intValue());
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        initCommonScreen();
    }

    public void doOnResume() {
        super.doOnResume();
        Evc.GetInstance().evol_workmode_set(12);
    }

    private void initCommonScreen() {
        setBackgroundResource(R.drawable.can_jeep_ycsb_bg02);
        this.mTxtVol = AddTxtLt(40, 80, 300, 35);
        this.mBtnCD = AddBtn(-1, Can.CAN_FORD_ESCORT_LY, Can.CAN_LIEBAO_WC, R.drawable.lkss_cd_logo, R.drawable.lkss_cd_logo);
        this.mBtnRadio = AddBtn(-2, 433, Can.CAN_LIEBAO_WC, R.drawable.lkss_radio_logo, R.drawable.lkss_radio_logo);
        this.mBtnSet = AddBtn(-3, CanCameraUI.BTN_CCH9_MODE4, Can.CAN_LIEBAO_WC, R.drawable.lkss_set_logo, R.drawable.lkss_set_logo);
        this.mInfoData = new CanDataInfo.B70_Dj_VolInfo();
    }

    public void ResetData(boolean check) {
        CanJni.B70DjGetVolInfo(this.mInfoData);
        if (!i2b(this.mInfoData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mInfoData.Update)) {
            this.mInfoData.Update = 0;
            if (this.mInfoData.Mute != 2) {
                this.mTxtVol.setText("VOL:" + String.format("%d", new Object[]{Integer.valueOf(this.mInfoData.Val)}));
                this.mTxtVol.setTextColor(-1);
                return;
            }
            this.mTxtVol.setText("MUTE");
            this.mTxtVol.setTextColor(SupportMenu.CATEGORY_MASK);
        }
    }

    /* access modifiers changed from: protected */
    public CustomTextView AddTxtLt(int x, int y, int w, int h) {
        CustomTextView temp = getRelativeManager().AddCusText(x, y, w, h);
        temp.SetPixelSize(30);
        temp.setGravity(19);
        return temp;
    }

    /* access modifiers changed from: protected */
    public CustomTextView AddTxtCenter(int x, int y, int w, int h) {
        CustomTextView temp = getRelativeManager().AddCusText(x, y, w, h);
        temp.SetPixelSize(26);
        temp.setGravity(17);
        return temp;
    }

    /* access modifiers changed from: protected */
    public ParamButton AddBtn(int id, int x, int y, int up, int dn) {
        ParamButton btn = getRelativeManager().AddButton(x, y);
        btn.setTag(Integer.valueOf(id));
        btn.setOnClickListener(this);
        btn.setDrawable(up, dn);
        btn.setTextColor(-1);
        btn.setGravity(17);
        return btn;
    }

    /* access modifiers changed from: protected */
    public ParamButton AddBtn(int id, int x, int y, int w, int h, int up, int dn) {
        ParamButton btn = getRelativeManager().AddButton(x, y, w, h);
        btn.setTag(Integer.valueOf(id));
        btn.setOnClickListener(this);
        btn.setDrawable(up, dn);
        return btn;
    }

    public void QueryData() {
    }
}
