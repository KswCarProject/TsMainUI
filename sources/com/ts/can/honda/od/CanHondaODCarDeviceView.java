package com.ts.can.honda.od;

import android.app.Activity;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.Evc;
import com.ts.MainUI.R;
import com.ts.can.CanBaseCarDeviceActivity;
import com.ts.can.CanCameraUI;
import com.ts.can.CanFunc;
import com.ts.can.CanRelativeCarInfoView;
import com.ts.other.CustomImgView;
import com.ts.other.ParamButton;

public class CanHondaODCarDeviceView extends CanRelativeCarInfoView {
    public static final int ITEM_FF = 4;
    public static final int ITEM_FR = 1;
    public static final int ITEM_NEXT = 3;
    public static final int ITEM_PREV = 2;
    public static final int[] mDsicNumArr = {R.drawable.can_klz_num01, R.drawable.can_klz_num02, R.drawable.can_klz_num03, R.drawable.can_klz_num04, R.drawable.can_klz_num05, R.drawable.can_klz_num06};
    public static final String[] mDsicTextArr = {"LOAD", "READ", "EJECT", "NO DISC", "CHECK DISC", "BUSY"};
    private static int mLastDiscSta = 0;
    private static long mLastTick = 0;
    private int mLastDiscStaNum = 255;
    private TextView m_AuxMenu;
    private Button m_BtnFF;
    private Button m_BtnFR;
    private Button m_BtnNEXT;
    private Button m_BtnPRE;
    private TextView m_CdMenu;
    private CustomImgView[] m_CdSta;
    private TextView m_CdTrack;
    private TextView m_RadioMenu;
    private CanDataInfo.HondaOd_Media mediaInfo;

    public CanHondaODCarDeviceView(Activity activity) {
        super(activity);
    }

    public boolean onTouch(View v, MotionEvent event) {
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                if (event.getAction() != 0) {
                    if (event.getAction() == 1) {
                        CanJni.HondaOdTouchKey(145, 0);
                        break;
                    }
                } else {
                    CanJni.HondaOdTouchKey(145, 2);
                    break;
                }
                break;
            case 2:
                if (event.getAction() != 0) {
                    if (event.getAction() == 1) {
                        CanJni.HondaOdTouchKey(145, 0);
                        break;
                    }
                } else {
                    CanJni.HondaOdTouchKey(145, 4);
                    break;
                }
                break;
            case 3:
                if (event.getAction() != 0) {
                    if (event.getAction() == 1) {
                        CanJni.HondaOdTouchKey(145, 0);
                        break;
                    }
                } else {
                    CanJni.HondaOdTouchKey(145, 3);
                    break;
                }
                break;
            case 4:
                if (event.getAction() != 0) {
                    if (event.getAction() == 1) {
                        CanJni.HondaOdTouchKey(145, 0);
                        break;
                    }
                } else {
                    CanJni.HondaOdTouchKey(145, 1);
                    break;
                }
                break;
        }
        return false;
    }

    public void onClick(View v) {
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        setBackgroundResource(R.drawable.can_klz_bg);
        this.mediaInfo = new CanDataInfo.HondaOd_Media();
        initCommonScreen();
    }

    private void initCommonScreen() {
        this.m_CdSta = new CustomImgView[6];
        this.m_RadioMenu = AddMenuText(130, Can.CAN_NISSAN_XFY, 767, 50);
        this.m_CdTrack = AddFlagText(200, 175, 180, 40);
        for (int i = 0; i < 6; i++) {
            this.m_CdSta[i] = getRelativeManager().AddImage((i * 35) + 626, 183, mDsicNumArr[i]);
            this.m_CdSta[i].Show(false);
        }
        this.m_CdMenu = AddMenuText(277, Can.CAN_NISSAN_XFY, CanCameraUI.BTN_TRUMPCHI_GS4_MODE1, 50);
        this.m_BtnFR = AddBtn(1, 267, 435, 100, 105, R.drawable.can_jeep_ycsb_down_up, R.drawable.can_jeep_ycsb_down_dn);
        this.m_BtnFR.setVisibility(4);
        this.m_BtnPRE = AddBtn(2, 397, 435, 100, 105, R.drawable.can_jeep_ycsb_prv_up, R.drawable.can_jeep_ycsb_prv_dn);
        this.m_BtnPRE.setVisibility(4);
        this.m_BtnNEXT = AddBtn(3, CanCameraUI.BTN_GEELY_YJX6_FXP, 435, 100, 105, R.drawable.can_jeep_ycsb_next_up, R.drawable.can_jeep_ycsb_next_dn);
        this.m_BtnNEXT.setVisibility(4);
        this.m_BtnFF = AddBtn(4, 657, 435, 100, 105, R.drawable.can_jeep_ycsb_speed_up, R.drawable.can_jeep_ycsb_speed_dn);
        this.m_BtnFF.setVisibility(4);
        this.m_AuxMenu = AddMenuText(130, Can.CAN_NISSAN_XFY, 767, 50);
        this.m_AuxMenu.setText("AUX");
    }

    public void doOnResume() {
        super.doOnResume();
        Evc.GetInstance().evol_workmode_set(12);
    }

    public void ResetData(boolean check) {
        String string4;
        CanJni.HondaOdGetMediaInfo(this.mediaInfo);
        if (i2b(this.mediaInfo.UpdateOnce) && (!check || i2b(this.mediaInfo.Update))) {
            this.mediaInfo.Update = 0;
            String string1 = CurBand(this.mediaInfo.Band);
            String string2 = String.format("%d", new Object[]{Integer.valueOf(this.mediaInfo.Pre)});
            String string3 = String.format("%d", new Object[]{Integer.valueOf(this.mediaInfo.Frq)});
            if (this.mediaInfo.Band == 3) {
                string4 = "Khz";
            } else {
                string4 = "Mhz";
            }
            this.m_RadioMenu.setText(String.format("%s             %s           %s %s", new Object[]{string1, string2, string3, string4}));
            switch (this.mediaInfo.Mode) {
                case 1:
                    CdView(false);
                    AuxView(false);
                    RadioView(true);
                    break;
                case 2:
                    CdView(true);
                    this.m_CdTrack.setText(String.format("Track:  %d", new Object[]{Integer.valueOf(this.mediaInfo.Track)}));
                    setCdSta(this.mediaInfo.PlaySta);
                    if (this.mLastDiscStaNum != this.mediaInfo.DiscVail) {
                        this.mLastDiscStaNum = this.mediaInfo.DiscVail;
                        setCdDiscSta(this.mediaInfo.DiscVail);
                    }
                    AuxView(false);
                    RadioView(false);
                    break;
                case 3:
                    CdView(false);
                    AuxView(true);
                    RadioView(false);
                    break;
                default:
                    CdView(false);
                    AuxView(false);
                    RadioView(false);
                    break;
            }
        }
        if (this.mediaInfo.Mode == 2) {
            long curTick = SystemClock.uptimeMillis();
            if (curTick > mLastTick + 666) {
                mLastTick = curTick;
                if (mLastDiscSta != 0) {
                    mLastDiscSta = 0;
                } else {
                    mLastDiscSta = 1;
                }
                if (this.mediaInfo.DiscNum <= 0) {
                    return;
                }
                if (mLastDiscSta != 0) {
                    this.m_CdSta[this.mediaInfo.DiscNum - 1].Show(true);
                } else {
                    this.m_CdSta[this.mediaInfo.DiscNum - 1].Show(false);
                }
            }
        }
    }

    private void setCdSta(int playSta) {
        switch (playSta) {
            case 0:
                this.m_CdMenu.setText(mDsicTextArr[0]);
                return;
            case 1:
                this.m_CdMenu.setText(mDsicTextArr[1]);
                return;
            case 2:
                this.m_CdMenu.setText(mDsicTextArr[2]);
                return;
            case 3:
                this.m_CdMenu.setText(mDsicTextArr[3]);
                return;
            case 4:
                this.m_CdMenu.setText(mDsicTextArr[4]);
                return;
            case 5:
                this.m_CdMenu.setText(mDsicTextArr[5]);
                return;
            case 6:
                this.m_CdMenu.setText(String.format("%02d:%02d", new Object[]{Integer.valueOf(this.mediaInfo.PlayMin), Integer.valueOf(this.mediaInfo.PlaySec)}));
                return;
            default:
                return;
        }
    }

    private void setCdDiscSta(int discNum) {
        if ((discNum & 1) == 0) {
            this.m_CdSta[0].Show(false);
        } else if ((discNum & 1) == 1) {
            this.m_CdSta[0].Show(true);
        }
        if ((discNum & 2) == 0) {
            this.m_CdSta[1].Show(false);
        } else if ((discNum & 2) == 2) {
            this.m_CdSta[1].Show(true);
        }
        if ((discNum & 4) == 0) {
            this.m_CdSta[2].Show(false);
        } else if ((discNum & 4) == 4) {
            this.m_CdSta[2].Show(true);
        }
        if ((discNum & 8) == 0) {
            this.m_CdSta[3].Show(false);
        } else if ((discNum & 8) == 8) {
            this.m_CdSta[3].Show(true);
        }
        if ((discNum & 16) == 0) {
            this.m_CdSta[4].Show(false);
        } else if ((discNum & 16) == 16) {
            this.m_CdSta[4].Show(true);
        }
        if ((discNum & 32) == 0) {
            this.m_CdSta[5].Show(false);
        } else if ((discNum & 32) == 32) {
            this.m_CdSta[5].Show(true);
        }
    }

    public static void DealStatusChanged() {
        CanDataInfo.HondaOd_Media mediaInfo2 = new CanDataInfo.HondaOd_Media();
        CanJni.HondaOdGetMediaInfo(mediaInfo2);
        if (mediaInfo2.UpdateOnce > 0 && mediaInfo2.Update > 0) {
            mediaInfo2.Update = 0;
            if (mediaInfo2.Mode == 1 || mediaInfo2.Mode == 2) {
                CanFunc.showCanActivity(CanBaseCarDeviceActivity.class, -1);
            }
        }
    }

    public void QueryData() {
        CanJni.HondaOdQuery(144, 124);
    }

    /* access modifiers changed from: protected */
    public ParamButton AddBtn(int id, int x, int y, int up, int dn) {
        ParamButton btn = getRelativeManager().AddButton(x, y);
        btn.setTag(Integer.valueOf(id));
        btn.setOnClickListener(this);
        btn.setOnTouchListener(this);
        btn.setDrawable(up, dn);
        return btn;
    }

    /* access modifiers changed from: protected */
    public ParamButton AddBtn(int id, int x, int y, int w, int h, int up, int dn) {
        ParamButton btn = getRelativeManager().AddButton(x, y, w, h);
        btn.setTag(Integer.valueOf(id));
        btn.setOnClickListener(this);
        btn.setOnTouchListener(this);
        btn.setDrawable(up, dn);
        return btn;
    }

    private TextView AddMenuText(int x, int y, int w, int h) {
        new TextView(getActivity());
        TextView text = getRelativeManager().AddText(x, y, w, h);
        text.setTextSize(0, 40.0f);
        text.setTextColor(-1);
        text.setGravity(17);
        text.setVisibility(4);
        return text;
    }

    private TextView AddFlagText(int x, int y, int w, int h) {
        new TextView(getActivity());
        TextView text = getRelativeManager().AddText(x, y, w, h);
        text.setTextSize(0, 28.0f);
        text.setTextColor(-1);
        text.setGravity(19);
        text.setVisibility(4);
        return text;
    }

    private TextView AddMsgText(int x, int y, int w, int h) {
        new TextView(getActivity());
        TextView text = getRelativeManager().AddText(x, y, w, h);
        text.setTextSize(0, 34.0f);
        text.setTextColor(-1);
        text.setGravity(17);
        text.setVisibility(4);
        return text;
    }

    private String CurBand(int data) {
        if (data == 0) {
            return "FM1";
        }
        if (data == 1) {
            return "FM2";
        }
        if (data == 3) {
            return "AM";
        }
        return " ";
    }

    private void AuxView(boolean disp) {
        int it = 4;
        int btnIt = 0;
        if (disp) {
            it = 0;
            btnIt = 4;
        }
        this.m_AuxMenu.setVisibility(it);
        this.m_BtnPRE.setVisibility(btnIt);
        this.m_BtnNEXT.setVisibility(btnIt);
        this.m_BtnFF.setVisibility(btnIt);
        this.m_BtnFR.setVisibility(btnIt);
    }

    private void RadioView(boolean disp) {
        int it = 4;
        if (disp) {
            it = 0;
        }
        this.m_RadioMenu.setVisibility(it);
    }

    private void CdView(boolean disp) {
        int it = 4;
        if (disp) {
            it = 0;
        } else {
            for (int i = 0; i < 6; i++) {
                this.m_CdSta[i].Show(false);
            }
        }
        this.m_CdMenu.setVisibility(it);
        this.m_CdTrack.setVisibility(it);
    }
}
