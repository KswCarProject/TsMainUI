package com.ts.can.bmw.lz;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.Evc;
import com.ts.MainUI.R;
import com.ts.can.CanIF;
import com.ts.can.CanRelativeCarInfoView;
import com.ts.other.CustomTextView;
import com.ts.other.ParamButton;

public class CanBMWLzHighCDView extends CanRelativeCarInfoView {
    public static final int ITEM_AM = 2;
    public static final int ITEM_BT = 5;
    public static final int ITEM_EJECT = 6;
    public static final int ITEM_FM = 1;
    public static final int ITEM_MODE = 3;
    public static final int ITEM_NEXT = 8;
    public static final int ITEM_NUM1 = 9;
    public static final int ITEM_NUM2 = 10;
    public static final int ITEM_NUM3 = 11;
    public static final int ITEM_NUM4 = 12;
    public static final int ITEM_NUM5 = 13;
    public static final int ITEM_NUM6 = 14;
    public static final int ITEM_POWER = 4;
    public static final int ITEM_PREV = 7;
    protected ParamButton mBtnAm;
    protected ParamButton mBtnBt;
    protected ParamButton mBtnEject;
    protected ParamButton mBtnFm;
    protected ParamButton mBtnMode;
    protected ParamButton mBtnNext;
    protected ParamButton mBtnPower;
    protected ParamButton mBtnPrev;
    protected CustomTextView mLine1;
    protected CustomTextView mLine2;
    protected CustomTextView mLine3;
    protected ParamButton mNUM1;
    protected ParamButton mNUM2;
    protected ParamButton mNUM3;
    protected ParamButton mNUM4;
    protected ParamButton mNUM5;
    protected ParamButton mNUM6;
    private CanDataInfo.BmwLz_TextData mTextData;

    public CanBMWLzHighCDView(Activity activity) {
        super(activity);
    }

    public boolean onTouch(View arg0, MotionEvent arg1) {
        return false;
    }

    public void onClick(View v) {
        int id = ((Integer) v.getTag()).intValue();
        switch (id) {
            case 1:
                CdCtrl(0, 0);
                return;
            case 2:
                CdCtrl(1, 0);
                return;
            case 3:
                CdCtrl(3, 0);
                return;
            case 4:
                CdCtrl(4, 0);
                return;
            case 5:
                CdCtrl(5, 0);
                return;
            case 6:
                CdCtrl(6, 0);
                return;
            case 7:
                CdCtrl(7, 0);
                return;
            case 8:
                CdCtrl(8, 0);
                return;
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
                CdCtrl(2, (id - 9) + 1);
                return;
            default:
                return;
        }
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
        this.mTextData = new CanDataInfo.BmwLz_TextData();
        setBackgroundResource(R.drawable.can_jeep_ycsb_bg02);
        this.mBtnPrev = AddBtn(7, 3, 464, R.drawable.can_yg_radio_rect01_up, R.drawable.can_yg_radio_rect01_dn);
        this.mBtnMode = AddBtn(3, 174, 464, R.drawable.can_yg_radio_rect01_up, R.drawable.can_yg_radio_rect01_dn);
        this.mBtnFm = AddBtn(1, 345, 464, R.drawable.can_yg_radio_rect01_up, R.drawable.can_yg_radio_rect01_dn);
        this.mBtnAm = AddBtn(2, 516, 464, R.drawable.can_yg_radio_rect01_up, R.drawable.can_yg_radio_rect01_dn);
        this.mBtnBt = AddBtn(5, 687, 464, R.drawable.can_yg_radio_rect01_up, R.drawable.can_yg_radio_rect01_dn);
        this.mBtnNext = AddBtn(8, 858, 464, R.drawable.can_yg_radio_rect01_up, R.drawable.can_yg_radio_rect01_dn);
        this.mBtnEject = AddBtn(6, 687, 25, R.drawable.can_yg_radio_rect01_up, R.drawable.can_yg_radio_rect01_dn);
        this.mBtnPower = AddBtn(4, 858, 25, R.drawable.can_yg_radio_rect01_up, R.drawable.can_yg_radio_rect01_dn);
        this.mNUM1 = AddBtn(9, 3, 364, R.drawable.can_yg_radio_rect01_up, R.drawable.can_yg_radio_rect01_dn);
        this.mNUM2 = AddBtn(10, 174, 364, R.drawable.can_yg_radio_rect01_up, R.drawable.can_yg_radio_rect01_dn);
        this.mNUM3 = AddBtn(11, 345, 364, R.drawable.can_yg_radio_rect01_up, R.drawable.can_yg_radio_rect01_dn);
        this.mNUM4 = AddBtn(12, 516, 364, R.drawable.can_yg_radio_rect01_up, R.drawable.can_yg_radio_rect01_dn);
        this.mNUM5 = AddBtn(13, 687, 364, R.drawable.can_yg_radio_rect01_up, R.drawable.can_yg_radio_rect01_dn);
        this.mNUM6 = AddBtn(14, 858, 364, R.drawable.can_yg_radio_rect01_up, R.drawable.can_yg_radio_rect01_dn);
        this.mLine1 = AddTxtCenter(262, 141, 500, 40);
        this.mLine2 = AddTxtCenter(262, 191, 500, 40);
        this.mLine3 = AddTxtCenter(262, Can.CAN_SITECHDEV_CW, 500, 40);
        this.mBtnPrev.setText("<");
        this.mBtnMode.setText("MODE");
        this.mBtnFm.setText("FM");
        this.mBtnAm.setText("AM");
        this.mBtnBt.setText("BT");
        this.mBtnNext.setText(">");
        this.mBtnEject.setText("EJECT");
        this.mBtnPower.setText("POWER");
        this.mNUM1.setText("NUM1");
        this.mNUM2.setText("NUM2");
        this.mNUM3.setText("NUM3");
        this.mNUM4.setText("NUM4");
        this.mNUM5.setText("NUM5");
        this.mNUM6.setText("NUM6");
    }

    public void ResetData(boolean check) {
        CanJni.BmwLzGetTextData(0, this.mTextData);
        if (i2b(this.mTextData.UpdateOnce) && (!check || i2b(this.mTextData.Update))) {
            this.mTextData.Update = 0;
            this.mLine1.setText(CanIF.byte2String(this.mTextData.Lin1));
        }
        CanJni.BmwLzGetTextData(1, this.mTextData);
        if (i2b(this.mTextData.UpdateOnce2) && (!check || i2b(this.mTextData.Update2))) {
            this.mTextData.Update2 = 0;
            this.mLine2.setText(CanIF.byte2String(this.mTextData.Lin2));
        }
        CanJni.BmwLzGetTextData(2, this.mTextData);
        if (!i2b(this.mTextData.UpdateOnce3)) {
            return;
        }
        if (!check || i2b(this.mTextData.Update3)) {
            this.mTextData.Update3 = 0;
            this.mLine3.setText(CanIF.byte2String(this.mTextData.Lin3));
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
        temp.SetPixelSize(35);
        temp.setGravity(17);
        return temp;
    }

    /* access modifiers changed from: protected */
    public ParamButton AddBtn(int id, int x, int y, int up, int dn) {
        ParamButton btn = getRelativeManager().AddButton(x, y);
        btn.setTag(Integer.valueOf(id));
        btn.setOnClickListener(this);
        btn.setDrawable(up, dn);
        btn.setTextSize(0, 25.0f);
        btn.setTextColor(-1);
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

    /* access modifiers changed from: protected */
    public void CdCtrl(int cmd, int part1) {
        CanJni.BmwLzMediaKey(cmd, part1);
    }

    public void QueryData() {
        CanJni.BmwLzQueryData(58, 0);
    }
}
