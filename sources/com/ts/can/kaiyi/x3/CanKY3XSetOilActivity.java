package com.ts.can.kaiyi.x3;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanCameraUI;
import com.ts.can.CanCommonActivity;
import com.ts.canview.RelativeLayoutContainer;
import com.ts.other.ParamButton;
import com.yyw.ts70xhw.KeyDef;

public class CanKY3XSetOilActivity extends CanCommonActivity {
    private ParamButton mBtnAqd;
    private RelativeLayoutContainer mContainer;
    private ImageView[] mOilIcons = new ImageView[8];
    private CanDataInfo.KaiYi3X_CarSet mSetData = new CanDataInfo.KaiYi3X_CarSet();
    private TextView mTvOilValue;

    public void onClick(View arg0) {
    }

    /* access modifiers changed from: protected */
    public int GetContentLayoutId() {
        return R.layout.activity_can_comm_relative;
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mContainer = new RelativeLayoutContainer(this);
        TextView aqdLabel = this.mContainer.addText(450, 40);
        this.mBtnAqd = this.mContainer.addButton(480, 90);
        TextView oilLabel = this.mContainer.addText(470, 200);
        this.mContainer.addImage(480, Can.CAN_FORD_MONDEO_XFY, R.drawable.can_ky3x_oil);
        this.mContainer.addImage(425, KeyDef.RKEY_POWER_OFF, R.drawable.can_ky3x_e);
        this.mContainer.addImage(CanCameraUI.BTN_CCH9_MODE5, KeyDef.RKEY_POWER_OFF, R.drawable.can_ky3x_f);
        TextView avgLabel = this.mContainer.addText(473, CanCameraUI.BTN_TRUMPCHI_GS4_MODE1);
        this.mTvOilValue = this.mContainer.addText(450, 450);
        this.mContainer.setTextStyle(aqdLabel, "安全带警示", 17, -1, 22).setTextStyle(oilLabel, "油量显示", 17, -1, 22).setTextStyle(avgLabel, "平均油耗", 17, -1, 22).setTextStyle(this.mTvOilValue, 0, 17, -1, 24).setDrawableUpSel(this.mBtnAqd, R.drawable.can_ky3x_aqd_dn, R.drawable.can_ky3x_aqd_up);
        for (int i = 0; i < this.mOilIcons.length; i++) {
            this.mOilIcons[i] = this.mContainer.addImage((i * 15) + 480, 344, R.drawable.can_ky3x_rect);
        }
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        CanJni.KaiYi3xGetInfo(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            setViewSelected((View) this.mBtnAqd, this.mSetData.Aqd);
            this.mTvOilValue.setText(String.format("%.1fL/100KM", new Object[]{Float.valueOf(((float) this.mSetData.Pjyh) / 10.0f)}));
            setOilRest(this.mSetData.Syyh);
        }
    }

    private void setOilRest(int syyh) {
        int i = 0;
        while (i < this.mOilIcons.length) {
            showGoneView((View) this.mOilIcons[i], i <= syyh / 32);
            i++;
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
    }
}
