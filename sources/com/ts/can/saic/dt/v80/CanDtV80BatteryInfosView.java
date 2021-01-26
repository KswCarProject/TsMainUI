package com.ts.can.saic.dt.v80;

import android.app.Activity;
import android.support.v4.internal.view.SupportMenu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanCameraUI;
import com.ts.can.CanRelativeCarInfoView;
import com.ts.factoryset.FsCanActivity;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;

public class CanDtV80BatteryInfosView extends CanRelativeCarInfoView {
    private static final int ITEM_TEMP = 1;
    private static final int ITEM_V = 0;
    private static final int ITEM_V_BOX = 2;
    private static boolean isShowTemp = false;
    private static int mCurrentBox = 1;
    private ParamButton mBtnVBox;
    private RelativeLayoutManager mManager;
    private int[] mMaxArrays;
    private int[] mMaxJHArrays;
    private int[] mMinArrays;
    private int[] mMinJHArrays;
    private CanDataInfo.DT_V80_BMS_MSG_3 mMsg3;
    private CanDataInfo.DT_V80_BMS_MSG_4 mMsg4;
    private TextView mTvMaxV;
    private TextView mTvMinV;
    private TextView[] mTvTempArrays;
    private TextView[] mTvVArrays;
    private int[] mVCArrays;

    public CanDtV80BatteryInfosView(Activity activity) {
        super(activity);
    }

    public boolean onTouch(View arg0, MotionEvent arg1) {
        return false;
    }

    public void onClick(View v) {
        int id = ((Integer) v.getTag()).intValue();
        if (id == 0) {
            showTvVArrays(true);
        } else if (id == 1) {
            showTvVArrays(false);
        } else if (id == 2) {
            mCurrentBox++;
            if (mCurrentBox > 4) {
                mCurrentBox = 1;
            }
            this.mBtnVBox.setText("电压箱" + mCurrentBox);
            ResetData(false);
        }
    }

    private void showTvVArrays(boolean show) {
        int i;
        int i2;
        isShowTemp = !show;
        for (TextView tv : this.mTvVArrays) {
            if (show) {
                i2 = 0;
            } else {
                i2 = 8;
            }
            tv.setVisibility(i2);
        }
        for (TextView tv2 : this.mTvTempArrays) {
            if (!show) {
                i = 0;
            } else {
                i = 8;
            }
            tv2.setVisibility(i);
        }
        ResetData(false);
    }

    private boolean isDtV80Type1() {
        return CanJni.GetSubType() == 0;
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        AddTextBtn(0, 40, 30, "单体电压");
        AddTextBtn(1, 218, 30, "温度");
        this.mBtnVBox = AddTextBtn(2, 396, 30, "电压箱1");
        this.mBtnVBox.Show(isDtV80Type1());
        this.mTvMaxV = AddText(CanCameraUI.BTN_TRUMPCHI_GS7_MODE5, 20, "最大电压与节号： - -        - -");
        this.mTvMinV = AddText(CanCameraUI.BTN_TRUMPCHI_GS7_MODE5, 70, "最小电压与节号： - -        - -");
        setShowGone((View) this.mTvMaxV, isDtV80Type1());
        setShowGone((View) this.mTvMinV, isDtV80Type1());
        ScrollView scrollView = new ScrollView(getActivity());
        getRelativeManager().AddView(scrollView, 0, 150, -1, -1);
        this.mManager = new RelativeLayoutManager(new RelativeLayout(getActivity()));
        scrollView.addView(this.mManager.GetLayout(), -1, -1);
        if (isDtV80Type1()) {
            this.mTvVArrays = new TextView[88];
            this.mTvTempArrays = new TextView[22];
        } else {
            this.mTvVArrays = new TextView[36];
            this.mTvTempArrays = new TextView[36];
        }
        for (int i = 0; i < this.mTvVArrays.length; i++) {
            this.mTvVArrays[i] = AddVText(((i % 6) * 160) + 50, ((i / 6) * 80) + 10, String.format("%02d：- -", new Object[]{Integer.valueOf(i + 1)}));
        }
        for (int i2 = 0; i2 < this.mTvTempArrays.length; i2++) {
            this.mTvTempArrays[i2] = AddVText(((i2 % 6) * 160) + 50, ((i2 / 6) * 80) + 10, String.format("%02d：- -", new Object[]{Integer.valueOf(i2 + 1)}));
        }
        this.mMsg3 = new CanDataInfo.DT_V80_BMS_MSG_3();
        this.mMsg4 = new CanDataInfo.DT_V80_BMS_MSG_4();
        showTvVArrays(true);
    }

    public void ResetData(boolean check) {
        CanJni.SaicDtV80GetBmsMsg3(this.mMsg3);
        CanJni.SaicDtV80GetBmsMsg4(this.mMsg4);
        updateData();
    }

    private void updateData() {
        initArrays();
        if (isShowTemp) {
            for (int i = 0; i < this.mTvTempArrays.length; i++) {
                updateC(i, this.mVCArrays[i]);
            }
        } else {
            for (int i2 = 0; i2 < this.mTvVArrays.length; i2++) {
                updateV(i2, this.mVCArrays[i2]);
            }
        }
        String minVC = getVCStr(this.mMinArrays[mCurrentBox - 1]);
        String maxVC = getVCStr(this.mMaxArrays[mCurrentBox - 1]);
        String minJH = getJHStr(this.mMinJHArrays[mCurrentBox - 1]);
        String maxJH = getJHStr(this.mMaxJHArrays[mCurrentBox - 1]);
        if (isShowTemp) {
            this.mTvMaxV.setText(String.format("最大温度与节号： %s        %s", new Object[]{maxVC, maxJH}));
            this.mTvMinV.setText(String.format("最小温度与节号： %s        %s", new Object[]{minVC, minJH}));
            return;
        }
        this.mTvMaxV.setText(String.format("最大电压与节号： %s        %s", new Object[]{maxVC, maxJH}));
        this.mTvMinV.setText(String.format("最小电压与节号： %s        %s", new Object[]{minVC, minJH}));
    }

    private String getVCStr(int value) {
        if (isShowTemp) {
            if (value == 254 || value == 255) {
                return "- -";
            }
            return String.valueOf(value - 40) + "°C";
        } else if (value == 65534 || value == 65535) {
            return "- -";
        } else {
            if (isDtV80Type1()) {
                return String.format("%.3fV", new Object[]{Float.valueOf(((float) value) * 0.001f)});
            }
            return String.format("%.3fV", new Object[]{Float.valueOf(((float) (value + 1000)) * 0.001f)});
        }
    }

    private String getJHStr(int value) {
        if (value == 254 || value == 255) {
            return "- -";
        }
        int min = isDtV80Type1() ? 1 : 0;
        int max = isDtV80Type1() ? Can.CAN_NISSAN_XFY : 32;
        if (value < min || value > max) {
            return "- -";
        }
        return new StringBuilder(String.valueOf(value)).toString();
    }

    private void initArrays() {
        if (isShowTemp) {
            switch (mCurrentBox) {
                case 2:
                    this.mVCArrays = this.mMsg4.Xncywd2;
                    break;
                case 3:
                    this.mVCArrays = this.mMsg4.Xncywd3;
                    break;
                case 4:
                    this.mVCArrays = this.mMsg4.Xncywd4;
                    break;
                default:
                    if (!isDtV80Type1()) {
                        this.mVCArrays = new int[36];
                        for (int i = 0; i < this.mVCArrays.length; i++) {
                            int length = this.mMsg4.Xncywd1.length;
                            if (i < length) {
                                this.mVCArrays[i] = this.mMsg4.Xncywd1[i];
                            } else {
                                this.mVCArrays[i] = this.mMsg4.Xncywd2[i - length];
                            }
                        }
                        break;
                    } else {
                        this.mVCArrays = this.mMsg4.Xncywd1;
                        break;
                    }
            }
            this.mMinArrays = this.mMsg4.XncywdMin;
            this.mMaxArrays = this.mMsg4.XncywdMax;
            this.mMinJHArrays = this.mMsg4.XncywdMinJh;
            this.mMaxJHArrays = this.mMsg4.XncywdMaxJh;
            return;
        }
        switch (mCurrentBox) {
            case 2:
                this.mVCArrays = this.mMsg3.Dcdy2;
                break;
            case 3:
                this.mVCArrays = this.mMsg3.Dcdy3;
                break;
            case 4:
                this.mVCArrays = this.mMsg3.Dcdy4;
                break;
            default:
                this.mVCArrays = this.mMsg3.Dcdy1;
                break;
        }
        this.mMinArrays = this.mMsg3.DyMin;
        this.mMaxArrays = this.mMsg3.DyMax;
        this.mMinJHArrays = this.mMsg3.DyMinJh;
        this.mMaxJHArrays = this.mMsg3.DyMaxJh;
    }

    public void QueryData() {
    }

    /* access modifiers changed from: protected */
    public void updateV(int i, int value) {
        int max = isDtV80Type1() ? 60000 : FsCanActivity.DOOR_UPDATE_ALL;
        if (value < 0 || value > max) {
            this.mTvVArrays[i].setText(String.format("%02d：- -", new Object[]{Integer.valueOf(i + 1)}));
            return;
        }
        for (int error : new int[]{65534, SupportMenu.USER_MASK}) {
            if (value == error) {
                this.mTvVArrays[i].setText(String.format("%02d：- -", new Object[]{Integer.valueOf(i + 1)}));
                return;
            }
        }
        if (isDtV80Type1()) {
            this.mTvVArrays[i].setText(String.format("%02d : %.3fV", new Object[]{Integer.valueOf(i + 1), Float.valueOf(((float) value) * 0.001f)}));
        } else {
            this.mTvVArrays[i].setText(String.format("%02d : %.3fV", new Object[]{Integer.valueOf(i + 1), Float.valueOf(((float) (value + 1000)) * 0.001f)}));
        }
    }

    /* access modifiers changed from: protected */
    public void updateC(int i, int value) {
        for (int error : new int[]{Can.CAN_FLAT_RZC, 255}) {
            if (value == error) {
                this.mTvTempArrays[i].setText(String.format("%02d：- -", new Object[]{Integer.valueOf(i + 1)}));
                return;
            }
        }
        this.mTvTempArrays[i].setText(String.format("%02d：%d°C", new Object[]{Integer.valueOf(i + 1), Integer.valueOf(value - 40)}));
    }

    /* access modifiers changed from: protected */
    public ParamButton AddTextBtn(int id, int x, int y, String text) {
        ParamButton btn = getRelativeManager().AddButton(x, y);
        btn.setOnClickListener(this);
        btn.setStateUpDn(R.drawable.can_mzd_rect_up, R.drawable.can_mzd_rect_dn);
        btn.setTag(Integer.valueOf(id));
        btn.setText(text);
        btn.setTextSize(0, 30.0f);
        btn.setTextColor(-1);
        btn.setPadding(0, 0, 0, 0);
        return btn;
    }

    /* access modifiers changed from: protected */
    public TextView AddText(int x, int y, String text) {
        TextView tv = addText(x, y, text);
        setTextStyle(tv, -1, 20, 3);
        return tv;
    }

    /* access modifiers changed from: protected */
    public TextView AddVText(int x, int y, String text) {
        TextView tv = this.mManager.AddText(x, y);
        tv.setText(text);
        setTextStyle(tv, -1, 18, 3);
        return tv;
    }
}
