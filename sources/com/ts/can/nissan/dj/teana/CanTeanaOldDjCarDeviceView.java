package com.ts.can.nissan.dj.teana;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import com.android.SdkConstants;
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
import com.txznet.sdk.TXZResourceManager;
import com.yyw.ts70xhw.KeyDef;
import java.io.UnsupportedEncodingException;

public class CanTeanaOldDjCarDeviceView extends CanRelativeCarInfoView {
    public static final int[] mDsicNumArr = {R.drawable.can_klz_num01, R.drawable.can_klz_num02, R.drawable.can_klz_num03, R.drawable.can_klz_num04, R.drawable.can_klz_num05, R.drawable.can_klz_num06};
    private static int mLastDiscSta = 0;
    private static long mLastTick = 0;
    private static int mVolb = -1;
    private static int m_AudioFlg1 = 0;
    private CanDataInfo.TeanaOldDjAmpInfo mAmpInfo;
    private CanDataInfo.TeanaOldDjMediaInfo mMediaInfo;
    private TextView m_AudioMenu;
    private TextView m_AuxMenu;
    private TextView m_CdMenu;
    private CustomImgView[] m_CdSta;
    private TextView m_CdText;
    private TextView m_FreqText;
    private TextView m_PwrMenu;
    private TextView m_RadioMenu;
    private TextView m_fgAutop;
    private TextView m_fgCdFolder;
    private TextView m_fgCdRandom;
    private TextView m_fgCdRepeat;
    private TextView m_fgCdScane;
    private TextView m_fgRds;
    private TextView m_fgScane;
    private TextView m_fgSt;

    public CanTeanaOldDjCarDeviceView(Activity activity) {
        super(activity);
    }

    public boolean onTouch(View v, MotionEvent event) {
        int intValue = ((Integer) v.getTag()).intValue();
        return false;
    }

    public void onClick(View v) {
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        setBackgroundResource(R.drawable.can_klz_bg);
        this.mMediaInfo = new CanDataInfo.TeanaOldDjMediaInfo();
        this.mAmpInfo = new CanDataInfo.TeanaOldDjAmpInfo();
        initCommonScreen();
    }

    private void initCommonScreen() {
        this.m_CdSta = new CustomImgView[6];
        this.m_fgRds = AddFlagText(200, 175, 100, 40);
        this.m_fgScane = AddFlagText(KeyDef.RKEY_MEDIA_SLOW, 175, 100, 40);
        this.m_fgSt = AddFlagText(473, 175, 100, 40);
        this.m_fgAutop = AddFlagText(CanCameraUI.BTN_CHANA_CS75_MODE4, 175, 100, 40);
        this.m_RadioMenu = AddMenuText(130, Can.CAN_NISSAN_XFY, 767, 50);
        this.m_FreqText = AddMsgText(130, 310, 767, 45);
        this.m_fgCdFolder = AddFlagText(Can.CAN_BENC_ZMYT, 175, Can.CAN_BENC_ZMYT, 40);
        this.m_fgCdFolder.setTextSize(0, 22.0f);
        this.m_fgCdRepeat = AddFlagText(280, 175, 130, 40);
        this.m_fgCdRepeat.setTextSize(0, 22.0f);
        this.m_fgCdRandom = AddFlagText(410, 175, 130, 40);
        this.m_fgCdRandom.setTextSize(0, 22.0f);
        this.m_fgCdScane = AddFlagText(540, 175, 120, 40);
        this.m_fgCdScane.setTextSize(0, 22.0f);
        for (int i = 0; i < 6; i++) {
            this.m_CdSta[i] = getRelativeManager().AddImage((i * 35) + 666, 183, mDsicNumArr[i]);
            this.m_CdSta[i].Show(false);
        }
        this.m_CdMenu = AddMenuText(277, Can.CAN_NISSAN_XFY, CanCameraUI.BTN_TRUMPCHI_GS4_MODE1, 50);
        this.m_CdText = AddMsgText(130, 310, 767, 45);
        this.m_AuxMenu = AddMenuText(130, Can.CAN_NISSAN_XFY, 767, 50);
        this.m_PwrMenu = AddMenuText(130, Can.CAN_NISSAN_XFY, 767, 50);
        this.m_AudioMenu = AddMenuText(130, Can.CAN_NISSAN_XFY, 767, 50);
    }

    public void doOnResume() {
        super.doOnResume();
        Evc.GetInstance().evol_workmode_set(12);
    }

    public void ResetData(boolean check) {
        String string2;
        String string3;
        CanJni.TeanaOldDjGetMediaInfo(this.mMediaInfo);
        if (!i2b(this.mMediaInfo.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mMediaInfo.Update)) {
            this.mMediaInfo.Update = 0;
            if (this.mMediaInfo.Mode == 1) {
                this.m_RadioMenu.setVisibility(0);
                this.m_fgCdRepeat.setVisibility(8);
                this.m_fgCdRandom.setVisibility(8);
                this.m_CdMenu.setVisibility(8);
                for (int i = 0; i < 6; i++) {
                    this.m_CdSta[i].Show(false);
                }
            } else if (this.mMediaInfo.Mode == 2) {
                this.m_fgCdRepeat.setVisibility(0);
                this.m_fgCdRandom.setVisibility(0);
                this.m_CdMenu.setVisibility(0);
                this.m_RadioMenu.setVisibility(8);
                for (int i2 = 0; i2 < 6; i2++) {
                    if (this.mMediaInfo.DiscNum == i2 + 1) {
                        this.m_CdSta[i2].Show(true);
                    } else {
                        this.m_CdSta[i2].Show(false);
                    }
                }
            } else {
                this.m_fgCdRepeat.setVisibility(8);
                this.m_fgCdRandom.setVisibility(8);
                this.m_CdMenu.setVisibility(8);
                this.m_RadioMenu.setVisibility(8);
                for (int i3 = 0; i3 < 6; i3++) {
                    this.m_CdSta[i3].Show(false);
                }
            }
            String string1 = CurBand(this.mMediaInfo.Band);
            if (this.mMediaInfo.Pre == 0) {
                string2 = "     ";
            } else {
                string2 = "P" + this.mMediaInfo.Pre;
            }
            if (this.mMediaInfo.Band == 1 || this.mMediaInfo.Band == 2) {
                string3 = String.valueOf(tranalateIntoString(this.mMediaInfo.Frq)) + " MHz";
            } else {
                string3 = String.valueOf(this.mMediaInfo.Frq / 10) + " KHz";
            }
            this.m_RadioMenu.setText(String.format("%s\t\t\t\t%s\t\t\t\t%s", new Object[]{string1, string2, string3}));
            if (this.mMediaInfo.Rpt == 0) {
                this.m_fgCdRepeat.setText(" ");
            } else if (this.mMediaInfo.Rpt == 1) {
                this.m_fgCdRepeat.setText("Repeat");
            }
            if (this.mMediaInfo.Rand == 0) {
                this.m_fgCdRandom.setText(" ");
            } else if (this.mMediaInfo.Rand == 1) {
                this.m_fgCdRandom.setText("Random");
            }
            this.m_CdMenu.setText(String.format("CD-T%d        %02d:%02d", new Object[]{Integer.valueOf(this.mMediaInfo.CurTrack), Integer.valueOf(this.mMediaInfo.PlayMin), Integer.valueOf(this.mMediaInfo.PlaySec)}));
        }
    }

    public static void DealStatusChanged() {
        CanDataInfo.TeanaOldWcModeInfo modeInfo = new CanDataInfo.TeanaOldWcModeInfo();
        CanJni.TeanaOldWcGetModeInfo(modeInfo);
        if (modeInfo.UpdateOnce > 0 && modeInfo.Update > 0) {
            modeInfo.Update = 0;
            if (modeInfo.Mode == 1 || modeInfo.Mode == 2 || modeInfo.Mode == 3) {
                CanFunc.showCanActivity(CanBaseCarDeviceActivity.class, -1);
            }
        }
    }

    public void QueryData() {
        CanJni.TeanaOldDjQuery(28);
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
        if (data == 1) {
            return "FM";
        }
        if (data == 2) {
            return "FM2";
        }
        if (data == 17) {
            return "AM";
        }
        return " ";
    }

    private String CurCdSta(int data) {
        if (data == 0) {
            return "Reading  Disc";
        }
        if (data == 6) {
            return "Stop";
        }
        if (data == 12) {
            return "Eject";
        }
        if (data == 17) {
            return "Loading  Disc";
        }
        if (data == 18) {
            return "Insert  Disc";
        }
        if (data == 19) {
            return "Wait";
        }
        if (data == 20) {
            return "Busy";
        }
        if (data == 21) {
            return "Select  Disc  To  Load";
        }
        if (data == 22) {
            return "Select  Disc  To  Eject";
        }
        if (data == 23) {
            return "Disc  Error";
        }
        return " ";
    }

    private String byte2ASCIIString(byte[] data, int len) {
        try {
            return new String(data, 0, len, "GBK");
        } catch (UnsupportedEncodingException e) {
            return TXZResourceManager.STYLE_DEFAULT;
        }
    }

    private String CurAudio(int data) {
        String stringAudio;
        String stringAudio2;
        String stringAudio3;
        String stringAudio4;
        if (data == 1) {
            return String.format("Volume           %02d", new Object[]{Integer.valueOf(this.mAmpInfo.Vol)});
        } else if (data == 2) {
            int val = this.mAmpInfo.Bal;
            if (val > 5) {
                stringAudio4 = "R" + (val - 5);
            } else if (val == 5) {
                stringAudio4 = "  " + (val - 5);
            } else {
                stringAudio4 = "L" + (5 - val);
            }
            return "Balance     " + stringAudio4;
        } else if (data == 3) {
            int val2 = this.mAmpInfo.Fad;
            if (val2 > 5) {
                stringAudio3 = "F" + (val2 - 5);
            } else if (val2 == 5) {
                stringAudio3 = "  " + (val2 - 5);
            } else {
                stringAudio3 = "R" + (5 - val2);
            }
            return "Fade     " + stringAudio3;
        } else if (data == 4) {
            int val3 = this.mAmpInfo.Bass;
            if (val3 > 5) {
                stringAudio2 = "+" + (val3 - 5);
            } else if (val3 == 5) {
                stringAudio2 = "  " + (val3 - 5);
            } else {
                stringAudio2 = SdkConstants.RES_QUALIFIER_SEP + (5 - val3);
            }
            return "Bass     " + stringAudio2;
        } else if (data != 5) {
            return " ";
        } else {
            int val4 = this.mAmpInfo.Tre;
            if (val4 > 5) {
                stringAudio = "+" + (val4 - 5);
            } else if (val4 == 5) {
                stringAudio = "  " + (val4 - 5);
            } else {
                stringAudio = SdkConstants.RES_QUALIFIER_SEP + (5 - val4);
            }
            return "Treble     " + stringAudio;
        }
    }

    public String tranalateIntoString(int freq) {
        return String.format("%.02f", new Object[]{Float.valueOf((float) (((double) freq) * 0.1d))});
    }
}
