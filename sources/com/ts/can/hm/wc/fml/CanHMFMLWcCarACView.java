package com.ts.can.hm.wc.fml;

import android.app.Activity;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanBaseACView;
import com.ts.can.CanCameraUI;
import com.ts.other.CustomImgView;
import com.ts.other.ParamButton;
import com.yyw.ts70xhw.KeyDef;

public class CanHMFMLWcCarACView extends CanBaseACView {
    private static final int ITEM_AC = 6;
    private static final int ITEM_AC_STATE = 8;
    private static final int ITEM_INFO = 15;
    private static final int ITEM_MODE_C = 5;
    private static final int ITEM_MODE_J = 4;
    private static final int ITEM_MODE_JC = 3;
    private static final int ITEM_MODE_PX = 1;
    private static final int ITEM_MODE_PXJ = 2;
    private static final int ITEM_RC = 10;
    private static final int ITEM_R_AC = 9;
    private static final int ITEM_TEMP_DEC = 11;
    private static final int ITEM_TEMP_INC = 12;
    private static final int ITEM_WIND_DEC = 13;
    private static final int ITEM_WIND_INC = 14;
    private static final int ITEM_XH = 7;
    public static final String TAG = "CanHMFMLWcCarACView";
    private CanDataInfo.CAN_ACInfo mACInfo;
    private ParamButton mACstate;
    private ParamButton mAcBut;
    private TextView mAuto;
    private TextView mDaul;
    protected CustomImgView[] mImgWind;
    private ParamButton mInfo;
    private TextView mLeftTemp;
    private ParamButton mMode_Px;
    private ParamButton mMode_Pxj;
    private ParamButton mMode_c;
    private ParamButton mMode_j;
    private ParamButton mMode_jc;
    private ParamButton mRac;
    private ParamButton mRc;
    private ParamButton mTempDec;
    private ParamButton mTempInc;
    private ParamButton mWindDec;
    private ParamButton mWindInc;
    private ParamButton mXh;

    public CanHMFMLWcCarACView(Activity activity) {
        super(activity);
    }

    public boolean onTouch(View view, MotionEvent event) {
        int action = event.getAction();
        int Id = ((Integer) view.getTag()).intValue();
        if (action == 0) {
            switch (Id) {
                case 5:
                    sendAirDownKey(5);
                    return false;
                case 6:
                    sendAirDownKey(2);
                    return false;
                case 7:
                    sendAirDownKey(7);
                    return false;
                case 8:
                    sendAirDownKey(1);
                    return false;
                case 9:
                    sendAirDownKey(21);
                    return false;
                case 10:
                    sendAirDownKey(6);
                    return false;
                case 11:
                    if (this.mACInfo.nLeftTemp == 254) {
                        return false;
                    }
                    sendAirDownKey(14);
                    return false;
                case 12:
                    if (this.mACInfo.nLeftTemp == 255) {
                        return false;
                    }
                    sendAirDownKey(13);
                    return false;
                case 13:
                    sendAirDownKey(12);
                    return false;
                case 14:
                    sendAirDownKey(11);
                    return false;
                default:
                    return false;
            }
        } else if (1 != action) {
            return false;
        } else {
            Log.d(TAG, "****ACTION_UP*****");
            switch (Id) {
                case 5:
                    sendAirUpKey(5);
                    return false;
                case 6:
                    sendAirUpKey(2);
                    return false;
                case 7:
                    sendAirUpKey(7);
                    return false;
                case 8:
                    sendAirUpKey(1);
                    return false;
                case 9:
                    sendAirUpKey(21);
                    return false;
                case 10:
                    sendAirUpKey(6);
                    return false;
                case 11:
                    if (this.mACInfo.nLeftTemp == 254) {
                        return false;
                    }
                    sendAirUpKey(14);
                    return false;
                case 12:
                    if (this.mACInfo.nLeftTemp == 255) {
                        return false;
                    }
                    sendAirUpKey(13);
                    return false;
                case 13:
                    sendAirUpKey(12);
                    return false;
                case 14:
                    sendAirUpKey(11);
                    return false;
                default:
                    return false;
            }
        }
    }

    private void sendAirDownKey(int cmd) {
        CanJni.HmWcFmlAirKey(cmd, 1);
    }

    private void sendAirUpKey(int cmd) {
        CanJni.HmWcFmlAirKey(cmd, 0);
    }

    public void onClick(View arg0) {
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        setBackgroundResource(R.drawable.can_haima_bg);
        this.mImgWind = new CustomImgView[7];
        this.mMode_Px = AddBtn(79, 15, 1);
        this.mMode_Px.setDrawable(R.drawable.can_haima_icon01_up, R.drawable.can_haima_icon01_dn);
        this.mMode_Pxj = AddBtn(255, 15, 2);
        this.mMode_Pxj.setDrawable(R.drawable.can_haima_icon02_up, R.drawable.can_haima_icon02_dn);
        this.mMode_jc = AddBtn(431, 15, 3);
        this.mMode_jc.setDrawable(R.drawable.can_haima_icon03_up, R.drawable.can_haima_icon03_dn);
        this.mMode_j = AddBtn(CanCameraUI.BTN_CCH9_MODE14, 15, 4);
        this.mMode_j.setDrawable(R.drawable.can_haima_icon04_up, R.drawable.can_haima_icon04_dn);
        this.mMode_c = AddBtn(800, 15, 5);
        this.mMode_c.setDrawable(R.drawable.can_haima_icon05_up, R.drawable.can_haima_icon05_dn);
        this.mAcBut = AddBtn(79, 453, 6);
        this.mAcBut.setDrawable(R.drawable.can_haima_icon06_up, R.drawable.can_haima_icon06_dn);
        this.mXh = AddBtn(255, 453, 7);
        this.mXh.setDrawable(R.drawable.can_haima_icon08_dn, R.drawable.can_haima_icon07_dn);
        this.mACstate = AddBtn(431, 453, 8);
        this.mACstate.setDrawable(R.drawable.can_haima_icon09_up, R.drawable.can_haima_icon09_dn);
        this.mRc = AddBtn(800, 453, 10);
        this.mRc.setDrawable(R.drawable.can_haima_icon11_up, R.drawable.can_haima_icon11_dn);
        this.mRac = AddBtn(CanCameraUI.BTN_CCH9_MODE14, 453, 9);
        this.mRac.setDrawable(R.drawable.can_haima_mode_up, R.drawable.can_haima_mode_dn);
        this.mTempDec = AddBtn(28, 255, 11);
        this.mTempDec.setDrawable(R.drawable.can_haima_jian_up, R.drawable.can_haima_jian_dn);
        this.mTempInc = AddBtn(468, 255, 12);
        this.mTempInc.setDrawable(R.drawable.can_haima_jia_up, R.drawable.can_haima_jia_dn);
        this.mWindDec = AddBtn(586, 262, 13);
        this.mWindDec.setDrawable(R.drawable.can_haima_xfs_up, R.drawable.can_haima_xfs_dn);
        this.mWindInc = AddBtn(906, 262, 14);
        this.mWindInc.setDrawable(R.drawable.can_haima_dfs_up, R.drawable.can_haima_dfs_dn);
        this.mImgWind[0] = getRelativeManager().AddImage(673, 184);
        this.mImgWind[0].setStateDrawable(R.drawable.can_haima_fl01_up, R.drawable.can_haima_fl01_dn);
        this.mImgWind[1] = getRelativeManager().AddImage(706, 184);
        this.mImgWind[1].setStateDrawable(R.drawable.can_haima_fl02_up, R.drawable.can_haima_fl02_dn);
        this.mImgWind[2] = getRelativeManager().AddImage(739, 184);
        this.mImgWind[2].setStateDrawable(R.drawable.can_haima_fl03_up, R.drawable.can_haima_fl03_dn);
        this.mImgWind[3] = getRelativeManager().AddImage(KeyDef.SKEY_POWEWR_4, 184);
        this.mImgWind[3].setStateDrawable(R.drawable.can_haima_fl04_up, R.drawable.can_haima_fl04_dn);
        this.mImgWind[4] = getRelativeManager().AddImage(KeyDef.SKEY_MUTE_2, 184);
        this.mImgWind[4].setStateDrawable(R.drawable.can_haima_fl05_up, R.drawable.can_haima_fl05_dn);
        this.mImgWind[5] = getRelativeManager().AddImage(KeyDef.SKEY_HOME_5, 184);
        this.mImgWind[5].setStateDrawable(R.drawable.can_haima_fl06_up, R.drawable.can_haima_fl06_dn);
        this.mImgWind[6] = getRelativeManager().AddImage(871, 184);
        this.mImgWind[6].setStateDrawable(R.drawable.can_haima_fl07_up, R.drawable.can_haima_fl07_dn);
        this.mLeftTemp = FMLaddText(130, 259, KeyDef.RKEY_MEDIA_10, 61);
        this.mAuto = FMLaddText(47, 126, 100, 40);
        this.mAuto.setTextSize(0, 38.0f);
        this.mDaul = FMLaddText(Can.CAN_FORD_EDGE_XFY, 126, 100, 40);
        this.mDaul.setTextSize(0, 38.0f);
    }

    /* access modifiers changed from: protected */
    public void InitViews() {
    }

    protected static int uint2Bool(int val) {
        if (val == 0) {
            return 1;
        }
        return 0;
    }

    protected static boolean int2Bool(int val) {
        if (val == 0) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void updateACUI() {
        this.mACInfo = Can.mACInfo;
        Can.mACInfo.Update = 0;
        this.mLeftTemp.setText(this.mACInfo.szLtTemp);
        this.mAcBut.SetSel(this.mACInfo.fgAC);
        this.mXh.SetSel(this.mACInfo.fgInnerLoop);
        this.mACstate.SetSel(uint2Bool(this.mACInfo.PWR));
        this.mRac.SetSel(this.mACInfo.fgRac);
        this.mRc.SetSel(this.mACInfo.fgRearLight);
        this.mMode_c.SetSel(this.mACInfo.fgDFBL);
        if (int2Bool(this.mACInfo.fgParallelWind) && int2Bool(this.mACInfo.fgDownWind)) {
            this.mMode_Pxj.setSelected(true);
            this.mMode_jc.setSelected(false);
            this.mMode_Px.setSelected(false);
            this.mMode_j.setSelected(false);
        } else if (int2Bool(this.mACInfo.fgForeWindMode) && int2Bool(this.mACInfo.fgDownWind)) {
            this.mMode_jc.setSelected(true);
            this.mMode_Pxj.setSelected(false);
            this.mMode_Px.setSelected(false);
            this.mMode_j.setSelected(false);
        } else if (int2Bool(this.mACInfo.fgParallelWind)) {
            this.mMode_Px.setSelected(true);
            this.mMode_Pxj.setSelected(false);
            this.mMode_jc.setSelected(false);
            this.mMode_j.setSelected(false);
        } else if (int2Bool(this.mACInfo.fgDownWind)) {
            this.mMode_j.setSelected(true);
            this.mMode_Pxj.setSelected(false);
            this.mMode_jc.setSelected(false);
            this.mMode_Px.setSelected(false);
        } else {
            this.mMode_Pxj.setSelected(false);
            this.mMode_jc.setSelected(false);
            this.mMode_Px.setSelected(false);
            this.mMode_j.setSelected(false);
        }
        for (int i = 0; i < 7; i++) {
            if (i < this.mACInfo.nWindValue) {
                this.mImgWind[i].setSelected(true);
            } else {
                this.mImgWind[i].setSelected(false);
            }
        }
        if (int2Bool(this.mACInfo.nAutoLight)) {
            this.mAuto.setText("AUTO");
        } else {
            this.mAuto.setText(" ");
        }
        if (int2Bool(this.mACInfo.fgDual)) {
            this.mDaul.setText("DUAL");
        } else {
            this.mDaul.setText(" ");
        }
    }

    private ParamButton AddBtn(int x, int y, int id) {
        ParamButton btn = getRelativeManager().AddButton(x, y);
        btn.setOnTouchListener(this);
        btn.setTag(Integer.valueOf(id));
        return btn;
    }

    private TextView FMLaddText(int x, int y, int w, int h) {
        TextView text = getRelativeManager().AddText(x, y, w, h);
        text.setTextColor(Color.parseColor("#08D2D3"));
        text.setTextSize(0, 45.0f);
        text.setGravity(17);
        return text;
    }
}
