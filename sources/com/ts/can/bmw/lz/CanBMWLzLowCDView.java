package com.ts.can.bmw.lz;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.Evc;
import com.ts.MainUI.R;
import com.ts.can.CanCameraUI;
import com.ts.can.CanRelativeCarInfoView;
import com.ts.other.CustomImgView;
import com.ts.other.CustomTextView;
import com.ts.other.ParamButton;
import com.yyw.ts70xhw.KeyDef;
import java.util.ArrayList;

public class CanBMWLzLowCDView extends CanRelativeCarInfoView {
    public static final int ITEM_DISC1 = 9;
    public static final int ITEM_DISC2 = 10;
    public static final int ITEM_DISC3 = 11;
    public static final int ITEM_DISC4 = 12;
    public static final int ITEM_DISC5 = 13;
    public static final int ITEM_DISC6 = 14;
    public static final int ITEM_FF = 6;
    public static final int ITEM_FR = 1;
    public static final int ITEM_NEXT = 5;
    public static final int ITEM_PAUSE = 4;
    public static final int ITEM_PLAY = 3;
    public static final int ITEM_PREV = 2;
    public static final int ITEM_RDM = 8;
    public static final int ITEM_SCAN = 7;
    protected ArrayList<CustomImgView> CDList;
    protected ArrayList<CustomTextView> CDTXTList;
    protected ParamButton mBtnFF;
    protected ParamButton mBtnFR;
    protected ParamButton mBtnNext;
    protected ParamButton mBtnPause;
    protected ParamButton mBtnPlay;
    protected ParamButton mBtnPrev;
    protected ParamButton mBtnRdm;
    protected ParamButton mBtnScan;
    protected CustomImgView mCD1;
    protected CustomImgView mCD2;
    protected CustomImgView mCD3;
    protected CustomImgView mCD4;
    protected CustomImgView mCD5;
    protected CustomImgView mCD6;
    private CanDataInfo.BmwLz_CdcSta mCdInfoData;
    protected int mOldFFSta = 0;
    protected int mOldFRSta = 0;
    protected CustomTextView mSta;
    protected String[] mStrSta = {"停止", "Play", "Pause", "Loading", "Reading"};
    protected CustomTextView mTrack;
    protected CustomTextView mTxtCD1;
    protected CustomTextView mTxtCD2;
    protected CustomTextView mTxtCD3;
    protected CustomTextView mTxtCD4;
    protected CustomTextView mTxtCD5;
    protected CustomTextView mTxtCD6;

    public CanBMWLzLowCDView(Activity activity) {
        super(activity);
    }

    public boolean onTouch(View arg0, MotionEvent arg1) {
        return false;
    }

    public void onClick(View v) {
        int id = ((Integer) v.getTag()).intValue();
        switch (id) {
            case 1:
                if (i2b(this.mOldFRSta)) {
                    this.mOldFRSta = 0;
                    CdCtrl(4, 0);
                    return;
                }
                this.mOldFRSta = 1;
                CdCtrl(4, 1);
                return;
            case 2:
                CdCtrl(7, 0);
                return;
            case 3:
                CdCtrl(0, 0);
                return;
            case 4:
                CdCtrl(1, 0);
                return;
            case 5:
                CdCtrl(8, 0);
                return;
            case 6:
                if (i2b(this.mOldFFSta)) {
                    this.mOldFFSta = 0;
                    CdCtrl(3, 0);
                    return;
                }
                this.mOldFFSta = 1;
                CdCtrl(3, 1);
                return;
            case 7:
                if (this.mBtnScan.isSelected()) {
                    CdCtrl(5, 0);
                    return;
                } else {
                    CdCtrl(5, 1);
                    return;
                }
            case 8:
                if (this.mBtnRdm.isSelected()) {
                    CdCtrl(6, 0);
                    return;
                } else {
                    CdCtrl(6, 1);
                    return;
                }
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
        this.mCdInfoData = new CanDataInfo.BmwLz_CdcSta();
        setBackgroundResource(R.drawable.can_jeep_ycsb_bg);
        this.mBtnFR = AddBtn(1, 123, CanCameraUI.BTN_TRUMPCHI_GS4_MODE5, R.drawable.can_jeep_ycsb_down_up, R.drawable.can_jeep_ycsb_down_dn);
        this.mBtnPrev = AddBtn(2, 257, CanCameraUI.BTN_TRUMPCHI_GS4_MODE5, R.drawable.can_jeep_ycsb_prv_up, R.drawable.can_jeep_ycsb_prv_dn);
        this.mBtnPlay = AddBtn(3, 391, CanCameraUI.BTN_TRUMPCHI_GS4_MODE5, R.drawable.can_jeep_ycsb_play_up, R.drawable.can_jeep_ycsb_play_dn);
        this.mBtnPause = AddBtn(4, CanCameraUI.BTN_GEELY_YJX6_GJ, CanCameraUI.BTN_TRUMPCHI_GS4_MODE5, R.drawable.can_jeep_ycsb_pause_up, R.drawable.can_jeep_ycsb_pause_dn);
        this.mBtnNext = AddBtn(5, 660, CanCameraUI.BTN_TRUMPCHI_GS4_MODE5, R.drawable.can_jeep_ycsb_next_up, R.drawable.can_jeep_ycsb_next_dn);
        this.mBtnFF = AddBtn(6, KeyDef.SKEY_CHUP_1, CanCameraUI.BTN_TRUMPCHI_GS4_MODE5, R.drawable.can_jeep_ycsb_speed_up, R.drawable.can_jeep_ycsb_speed_dn);
        this.mBtnScan = AddBtn(7, 46, 25, R.drawable.can_jeep_ycsb_scan_up, R.drawable.can_jeep_ycsb_scan_dn);
        this.mBtnRdm = AddBtn(8, 180, 25, R.drawable.can_jeep_ycsb_random_up, R.drawable.can_jeep_ycsb_random_dn);
        getRelativeManager().AddImage(44, 127, R.drawable.can_jeep_ycsb_sjx);
        this.mSta = AddTxtLt(84, 124, 300, 40);
        this.mTrack = AddTxtLt(44, 184, 200, 35);
        this.mTxtCD1 = AddTxtCenter(604, 200, 81, 30);
        this.mTxtCD2 = AddTxtCenter(748, 200, 81, 30);
        this.mTxtCD3 = AddTxtCenter(892, 200, 81, 30);
        this.mTxtCD4 = AddTxtCenter(604, KeyDef.RKEY_RDS_TA, 81, 30);
        this.mTxtCD5 = AddTxtCenter(748, KeyDef.RKEY_RDS_TA, 81, 30);
        this.mTxtCD6 = AddTxtCenter(892, KeyDef.RKEY_RDS_TA, 81, 30);
        this.CDTXTList = new ArrayList<>();
        this.CDTXTList.add(this.mTxtCD1);
        this.CDTXTList.add(this.mTxtCD2);
        this.CDTXTList.add(this.mTxtCD3);
        this.CDTXTList.add(this.mTxtCD4);
        this.CDTXTList.add(this.mTxtCD5);
        this.CDTXTList.add(this.mTxtCD6);
        this.mCD1 = getRelativeManager().AddImage(604, 113, R.drawable.can_jeep_ycsb_cd03);
        this.mCD2 = getRelativeManager().AddImage(748, 113, R.drawable.can_jeep_ycsb_cd03);
        this.mCD3 = getRelativeManager().AddImage(892, 113, R.drawable.can_jeep_ycsb_cd03);
        this.mCD4 = getRelativeManager().AddImage(604, Can.CAN_BYD_M6_DJ, R.drawable.can_jeep_ycsb_cd03);
        this.mCD5 = getRelativeManager().AddImage(748, Can.CAN_BYD_M6_DJ, R.drawable.can_jeep_ycsb_cd03);
        this.mCD6 = getRelativeManager().AddImage(892, Can.CAN_BYD_M6_DJ, R.drawable.can_jeep_ycsb_cd03);
        this.CDList = new ArrayList<>();
        this.CDList.add(this.mCD1);
        this.CDList.add(this.mCD2);
        this.CDList.add(this.mCD3);
        this.CDList.add(this.mCD4);
        this.CDList.add(this.mCD5);
        this.CDList.add(this.mCD6);
        for (int i = 0; i < this.CDList.size(); i++) {
            this.CDList.get(i).setTag(Integer.valueOf(i + 9));
            this.CDList.get(i).setOnClickListener(this);
        }
    }

    public void ResetData(boolean check) {
        CanJni.BmwLzGetCdcSta(this.mCdInfoData);
        if (!i2b(this.mCdInfoData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mCdInfoData.Update)) {
            this.mCdInfoData.Update = 0;
            if (this.mCdInfoData.Track != 255) {
                this.mTrack.setText(String.format("Track: %d", new Object[]{Integer.valueOf(this.mCdInfoData.Track)}));
            } else {
                this.mTrack.setText("--");
            }
            if (this.mCdInfoData.Sta >= this.mStrSta.length) {
                this.mSta.setText("其他状态");
            } else {
                this.mSta.setText(this.mStrSta[this.mCdInfoData.Sta]);
            }
            this.mBtnRdm.SetSel(this.mCdInfoData.Rdm);
            this.mBtnScan.SetSel(this.mCdInfoData.Scan);
            for (int i = 0; i < this.CDTXTList.size(); i++) {
                this.CDTXTList.get(i).setText("CD" + (i + 1));
            }
            for (int i2 = 0; i2 < this.CDList.size(); i2++) {
                this.CDList.get(i2).setImageResource(R.drawable.can_jeep_ycsb_cd03);
            }
            int cdNum = this.mCdInfoData.DNum;
            if (cdNum <= this.CDList.size() && cdNum > 0) {
                this.CDList.get(cdNum - 1).setImageResource(R.drawable.can_jeep_ycsb_cd02);
                this.CDTXTList.get(cdNum - 1).setText("Play");
            }
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
        CanJni.BmwLzCdcKey(cmd, part1);
    }

    public void QueryData() {
        CanJni.BmwLzQueryData(59, 0);
    }
}
