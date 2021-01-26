package com.ts.can.faw.dj.b70;

import android.app.Activity;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.Evc;
import com.ts.MainUI.R;
import com.ts.can.CanCameraUI;
import com.ts.can.CanRelativeCarInfoView;
import com.ts.other.CustomTextView;
import com.ts.other.ParamButton;
import com.txznet.sdk.TXZResourceManager;
import com.yyw.ts70xhw.KeyDef;

public class CanB70DjSetView extends CanRelativeCarInfoView {
    public static final int ITEM_BT = 6;
    public static final int ITEM_CSBC = 1;
    public static final int ITEM_DXD = 2;
    public static final int ITEM_SYSZ = 4;
    public static final int ITEM_TIME = 5;
    public static final int ITEM_TSY = 3;
    protected static int[] mBtArr = {R.string.can_btpp, R.string.can_phonevol, R.string.can_ldxs, R.string.can_return};
    protected static int[] mBtnList1Arr = {R.string.can_csbc, R.string.can_amp_dxd, R.string.can_tsysy, R.string.can_sysz, R.string.can_szsz, R.string.can_btsz, R.string.can_exit};
    protected static int[] mCsbsArr = {R.string.can_trunk_close, R.string.can_level1, R.string.can_level2, R.string.can_level3};
    protected static int[] mKGArr = {R.string.can_off, R.string.can_on};
    protected static int[] mNullArr = new int[0];
    protected static int[] mSyszArr = {R.string.can_eq, R.string.can_sc, R.string.can_balance_front_rear, R.string.can_balance_left_right, R.string.can_zjtyw, R.string.can_return};
    protected static int[] mSyszArr_EQ = {R.string.can_mode_normal, R.string.can_psa_eq_Jazz, R.string.can_eq_pop, R.string.can_psa_eq_classic, R.string.can_gxh};
    protected static int[] mSyszArr_EQ_GX = {R.string.can_vol_high, R.string.can_vol_middle, R.string.can_vol_low, R.string.can_return};
    protected static int[] mSyszArr_SC = {R.string.can_mode_normal, R.string.can_theater, R.string.can_concert_hall, R.string.can_cinema, R.string.can_gwt};
    protected static int[] mSyszArr_ZJTYW = {R.string.can_zjs, R.string.can_fjs, R.string.can_hpz, R.string.can_hpy};
    protected static int[] mTimeArr = {R.string.can_hourset, R.string.can_minset, R.string.can_time_format, R.string.can_return};
    protected static int[] mTimeArr_SJGS = {R.string.can24_hours, R.string.can12_hours};
    protected static int[] mTimeArr_XSSZ = {R.string.can_increase, R.string.can_reduce, R.string.can_return};
    private ParamButton mBtnCurVal;
    private ParamButton mBtnInfo;
    private ParamButton[] mBtnList1;
    private ParamButton[] mBtnList2;
    private ParamButton[] mBtnList3;
    private ParamButton[] mBtnList4;
    protected CanDataInfo.B70_Dj_SetInfo mSetData;

    public CanB70DjSetView(Activity activity) {
        super(activity);
    }

    public boolean onTouch(View arg0, MotionEvent arg1) {
        return false;
    }

    public void onClick(View v) {
        int intValue = ((Integer) v.getTag()).intValue();
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
        this.mSetData = new CanDataInfo.B70_Dj_SetInfo();
        setBackgroundResource(R.drawable.can_jeep_ycsb_bg02);
        this.mBtnList1 = new ParamButton[mBtnList1Arr.length];
        for (int i = 0; i < this.mBtnList1.length; i++) {
            this.mBtnList1[i] = AddBtn(i, 10, (i * 75) + 10, R.drawable.can_box_btn_up, R.drawable.can_box_btn_dn);
            this.mBtnList1[i].setText(getString(mBtnList1Arr[i]));
        }
        this.mBtnList2 = new ParamButton[5];
        for (int i2 = 0; i2 < this.mBtnList2.length; i2++) {
            this.mBtnList2[i2] = AddBtn(i2 + 20, 170, (i2 * 75) + 10, R.drawable.can_box_btn_up, R.drawable.can_box_btn_dn);
            this.mBtnList2[i2].setVisibility(8);
        }
        this.mBtnList3 = new ParamButton[5];
        for (int i3 = 0; i3 < this.mBtnList3.length; i3++) {
            this.mBtnList3[i3] = AddBtn(i3 + 30, KeyDef.RKEY_RDS_TA, (i3 * 75) + 10, R.drawable.can_box_btn_up, R.drawable.can_box_btn_dn);
            this.mBtnList3[i3].setVisibility(8);
        }
        this.mBtnList4 = new ParamButton[3];
        for (int i4 = 0; i4 < this.mBtnList4.length; i4++) {
            this.mBtnList4[i4] = AddBtn(i4 + 40, 490, (i4 * 75) + 10, R.drawable.can_box_btn_up, R.drawable.can_box_btn_dn);
            this.mBtnList4[i4].setVisibility(8);
        }
        this.mBtnInfo = AddBtn(60, CanCameraUI.BTN_LANDWIND_3D_LEFT_UP, 10, R.drawable.can_box_btn_up, R.drawable.can_box_btn_dn);
        this.mBtnCurVal = AddBtn(60, 810, 10, R.drawable.can_box_btn_up, R.drawable.can_box_btn_dn);
    }

    public void ResetData(boolean check) {
        CanJni.B70DjGetSetInfo(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            initBtnList2(this.mSetData.Val[0], this.mSetData.Val[1], this.mSetData.Val[2], this.mSetData.Val[3], this.mSetData.Val[4]);
            initBtnCurVal(this.mSetData.Val[0], this.mSetData.Val[1], this.mSetData.Val[2], this.mSetData.Val[3], this.mSetData.Val[4], this.mSetData.Val[7]);
        }
    }

    private void initBtnCurVal(int data0, int data1, int data2, int data3, int data4, int data7) {
        switch (data0) {
            case 1:
                if (data1 > 0) {
                    setDefaultVal(data7);
                    return;
                } else if (data7 - 1 >= 0) {
                    this.mBtnCurVal.setText(mCsbsArr[data7 - 1]);
                    return;
                } else {
                    setDefaultVal(data7);
                    return;
                }
            case 2:
            case 3:
                if (data1 > 0) {
                    setDefaultVal(data7);
                    return;
                } else if (data7 - 1 >= 0) {
                    this.mBtnCurVal.setText(mKGArr[data7 - 1]);
                    return;
                } else {
                    setDefaultVal(data7);
                    return;
                }
            case 4:
                switch (data1) {
                    case 0:
                        if (data7 - 1 >= 0) {
                            this.mBtnCurVal.setText(mSyszArr[data7 - 1]);
                            return;
                        } else {
                            setDefaultVal(data7);
                            return;
                        }
                    case 1:
                        if (data2 == 5) {
                            if (data3 > 0 && data3 < 4) {
                                this.mBtnCurVal.setText(String.format("%d", new Object[]{Integer.valueOf(data7 - 7)}));
                                return;
                            } else if (data3 != 0) {
                                return;
                            } else {
                                if (data7 - 1 >= 0) {
                                    this.mBtnCurVal.setText(mSyszArr_EQ_GX[data7 - 1]);
                                    return;
                                } else {
                                    setDefaultVal(data7);
                                    return;
                                }
                            }
                        } else if (data2 != 0) {
                            setDefaultVal(data7);
                            return;
                        } else if (data7 - 1 >= 0) {
                            this.mBtnCurVal.setText(mSyszArr_EQ[data7 - 1]);
                            return;
                        } else {
                            setDefaultVal(data7);
                            return;
                        }
                    case 2:
                        if (data2 != 0) {
                            setDefaultVal(data7);
                            return;
                        } else if (data7 - 1 >= 0) {
                            this.mBtnCurVal.setText(mSyszArr_SC[data7 - 1]);
                            return;
                        } else {
                            setDefaultVal(data7);
                            return;
                        }
                    case 3:
                    case 4:
                        if (data2 != 0) {
                            setDefaultVal(data7);
                            return;
                        } else if (data7 < 7) {
                            initCurInfo(String.format("F%d", new Object[]{Integer.valueOf(7 - data7)}));
                            return;
                        } else if (data7 > 7) {
                            initCurInfo(String.format("R%d", new Object[]{Integer.valueOf(data7 - 7)}));
                            return;
                        } else {
                            initCurInfo("0");
                            return;
                        }
                    case 5:
                        if (data2 != 0) {
                            setDefaultVal(data7);
                            return;
                        } else if (data7 - 1 >= 0) {
                            this.mBtnCurVal.setText(mSyszArr_ZJTYW[data7 - 1]);
                            return;
                        } else {
                            setDefaultVal(data7);
                            return;
                        }
                    default:
                        return;
                }
            case 5:
                setDefaultVal(data7);
                return;
            case 6:
                switch (data1) {
                    case 0:
                        if (data7 - 1 >= 0) {
                            this.mBtnCurVal.setText(mBtArr[data7 - 1]);
                            return;
                        } else {
                            setDefaultVal(data7);
                            return;
                        }
                    case 1:
                        setDefaultVal(data7);
                        return;
                    case 2:
                        if (data2 == 0) {
                            this.mBtnCurVal.setText(String.format("VOL: %d", new Object[]{Integer.valueOf(data7)}));
                            return;
                        }
                        setDefaultVal(data7);
                        return;
                    case 3:
                        if (data2 != 0) {
                            setDefaultVal(data7);
                            return;
                        } else if (data7 - 1 >= 0) {
                            this.mBtnCurVal.setText(mKGArr[data7 - 1]);
                            return;
                        } else {
                            setDefaultVal(data7);
                            return;
                        }
                    default:
                        return;
                }
            default:
                return;
        }
    }

    private void setDefaultVal(int data7) {
        this.mBtnCurVal.setText(String.valueOf(getString(R.string.can_curval)) + String.format(":  %d", new Object[]{Integer.valueOf(data7)}));
    }

    private void initBtnList2(int data0, int data1, int data2, int data3, int data4) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        for (int i = 0; i < this.mBtnList1.length; i++) {
            ParamButton paramButton = this.mBtnList1[i];
            if (i + 1 == data0) {
                z4 = true;
            } else {
                z4 = false;
            }
            paramButton.setSelected(z4);
        }
        for (int i2 = 0; i2 < this.mBtnList2.length; i2++) {
            ParamButton paramButton2 = this.mBtnList2[i2];
            if (i2 + 1 == data1) {
                z3 = true;
            } else {
                z3 = false;
            }
            paramButton2.setSelected(z3);
        }
        for (int i3 = 0; i3 < this.mBtnList3.length; i3++) {
            ParamButton paramButton3 = this.mBtnList3[i3];
            if (i3 + 1 == data2) {
                z2 = true;
            } else {
                z2 = false;
            }
            paramButton3.setSelected(z2);
        }
        for (int i4 = 0; i4 < this.mBtnList4.length; i4++) {
            ParamButton paramButton4 = this.mBtnList4[i4];
            if (i4 + 1 == data3) {
                z = true;
            } else {
                z = false;
            }
            paramButton4.setSelected(z);
        }
        switch (data0) {
            case 1:
                setBtnList(this.mBtnList2, mCsbsArr);
                setBtnList(this.mBtnList3, mNullArr);
                setBtnList(this.mBtnList4, mNullArr);
                initCurInfo(TXZResourceManager.STYLE_DEFAULT);
                return;
            case 2:
            case 3:
                setBtnList(this.mBtnList2, mKGArr);
                setBtnList(this.mBtnList3, mNullArr);
                setBtnList(this.mBtnList4, mNullArr);
                initCurInfo(TXZResourceManager.STYLE_DEFAULT);
                return;
            case 4:
                setBtnList(this.mBtnList2, mSyszArr);
                switch (data1) {
                    case 1:
                        setBtnList(this.mBtnList3, mSyszArr_EQ);
                        if (data2 == 5) {
                            setBtnList(this.mBtnList4, mSyszArr_EQ_GX);
                            if (data3 <= 0 || data3 >= 4) {
                                initCurInfo(TXZResourceManager.STYLE_DEFAULT);
                                return;
                            }
                            initCurInfo(String.format("%d", new Object[]{Integer.valueOf(data4 - 7)}));
                            return;
                        }
                        setBtnList(this.mBtnList4, mNullArr);
                        initCurInfo(TXZResourceManager.STYLE_DEFAULT);
                        return;
                    case 2:
                        setBtnList(this.mBtnList3, mSyszArr_SC);
                        setBtnList(this.mBtnList4, mNullArr);
                        initCurInfo(TXZResourceManager.STYLE_DEFAULT);
                        return;
                    case 3:
                    case 4:
                        setBtnList(this.mBtnList3, mNullArr);
                        setBtnList(this.mBtnList4, mNullArr);
                        if (data2 < 7) {
                            initCurInfo(String.format("F%d", new Object[]{Integer.valueOf(7 - data2)}));
                            return;
                        } else if (data2 > 7) {
                            initCurInfo(String.format("R%d", new Object[]{Integer.valueOf(data2 - 7)}));
                            return;
                        } else {
                            initCurInfo("0");
                            return;
                        }
                    case 5:
                        setBtnList(this.mBtnList3, mSyszArr_ZJTYW);
                        setBtnList(this.mBtnList4, mNullArr);
                        initCurInfo(TXZResourceManager.STYLE_DEFAULT);
                        return;
                    default:
                        setBtnList(this.mBtnList3, mNullArr);
                        setBtnList(this.mBtnList4, mNullArr);
                        initCurInfo(TXZResourceManager.STYLE_DEFAULT);
                        return;
                }
            case 5:
                setBtnList(this.mBtnList2, mTimeArr);
                switch (data1) {
                    case 1:
                    case 2:
                        setBtnList(this.mBtnList3, mTimeArr_XSSZ);
                        break;
                    case 3:
                        setBtnList(this.mBtnList3, mTimeArr_SJGS);
                        break;
                    default:
                        setBtnList(this.mBtnList3, mNullArr);
                        break;
                }
                initCurInfo(TXZResourceManager.STYLE_DEFAULT);
                setBtnList(this.mBtnList4, mNullArr);
                return;
            case 6:
                setBtnList(this.mBtnList2, mBtArr);
                switch (data1) {
                    case 1:
                        setBtnList(this.mBtnList3, mNullArr);
                        if (data2 != 1) {
                            initCurInfo(TXZResourceManager.STYLE_DEFAULT);
                            break;
                        } else {
                            initCurInfo(getString(R.string.can_teramont_open));
                            break;
                        }
                    case 2:
                        setBtnList(this.mBtnList3, mNullArr);
                        initCurInfo(String.format("VOL: %d", new Object[]{Integer.valueOf(data2)}));
                        break;
                    case 3:
                        setBtnList(this.mBtnList3, mKGArr);
                        initCurInfo(TXZResourceManager.STYLE_DEFAULT);
                        break;
                    default:
                        setBtnList(this.mBtnList3, mNullArr);
                        initCurInfo(TXZResourceManager.STYLE_DEFAULT);
                        break;
                }
                setBtnList(this.mBtnList4, mNullArr);
                return;
            default:
                return;
        }
    }

    private void initCurInfo(String info) {
        if (TextUtils.isEmpty(info)) {
            this.mBtnInfo.setVisibility(8);
        } else {
            this.mBtnInfo.setVisibility(0);
        }
        this.mBtnInfo.setText(info);
    }

    private void setBtnList(ParamButton[] mBtnList, int[] mStringArr) {
        for (int i = 0; i < mBtnList.length; i++) {
            mBtnList[i].setVisibility(8);
            if (i < mStringArr.length) {
                mBtnList[i].setVisibility(0);
                mBtnList[i].setText(getString(mStringArr[i]));
            }
        }
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
        ParamButton btn = getRelativeManager().AddButton(x, y, 142, 68);
        btn.setTag(Integer.valueOf(id));
        btn.setOnClickListener(this);
        btn.setMaxLines(1);
        btn.setEllipsize(TextUtils.TruncateAt.END);
        btn.setDrawable(up, dn);
        btn.setTextColor(-1);
        return btn;
    }

    /* access modifiers changed from: protected */
    public void CdCtrl(int cmd, int part1) {
        CanJni.CrownWcCdSet(cmd, part1);
    }

    public void QueryData() {
        CanJni.B70DjQueryInfo(4, 0);
    }
}
