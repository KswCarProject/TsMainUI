package com.ts.can.nissan.wc.teana;

import android.app.Activity;
import android.os.SystemClock;
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

public class CanTeanaWcCarDeviceView extends CanRelativeCarInfoView {
    public static final int[] mDsicNumArr = {R.drawable.can_klz_num01, R.drawable.can_klz_num02, R.drawable.can_klz_num03, R.drawable.can_klz_num04, R.drawable.can_klz_num05, R.drawable.can_klz_num06};
    private static int mLastDiscSta = 0;
    private static long mLastTick = 0;
    private static int mVolb = -1;
    private static int m_AudioFlg1 = 0;
    private CanDataInfo.TeanaOldWcAudioInfo audioInfo;
    private CanDataInfo.TeanaOldWcCdInfo cdInfo;
    private CanDataInfo.TeanaOldWcCdText cdText;
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
    private CanDataInfo.TeanaOldWcModeInfo modeInfo;
    private CanDataInfo.TeanaOldWcRadioInfo radioInfo;
    private CanDataInfo.TeanaOldWcRadioText radioText;

    public CanTeanaWcCarDeviceView(Activity activity) {
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
        this.audioInfo = new CanDataInfo.TeanaOldWcAudioInfo();
        this.cdInfo = new CanDataInfo.TeanaOldWcCdInfo();
        this.cdText = new CanDataInfo.TeanaOldWcCdText();
        this.modeInfo = new CanDataInfo.TeanaOldWcModeInfo();
        this.radioInfo = new CanDataInfo.TeanaOldWcRadioInfo();
        this.radioText = new CanDataInfo.TeanaOldWcRadioText();
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
        this.m_AuxMenu.setText("AUX");
        this.m_PwrMenu = AddMenuText(130, Can.CAN_NISSAN_XFY, 767, 50);
        this.m_PwrMenu.setText("Power Off");
        this.m_AudioMenu = AddMenuText(130, Can.CAN_NISSAN_XFY, 767, 50);
    }

    public void doOnResume() {
        super.doOnResume();
        Evc.GetInstance().evol_workmode_set(12);
    }

    public void ResetData(boolean check) {
        String string2;
        String string3;
        CanJni.TeanaOldWcGetModeInfo(this.modeInfo);
        if (i2b(this.modeInfo.UpdateOnce) && (!check || i2b(this.modeInfo.Update))) {
            this.modeInfo.Update = 0;
            MenuVis();
        }
        CanJni.TeanaOldWcGetAudioInfo(this.audioInfo);
        if (i2b(this.audioInfo.UpdateOnce) && (!check || i2b(this.audioInfo.Update) || m_AudioFlg1 != 0)) {
            this.audioInfo.Update = 0;
            m_AudioFlg1 = 0;
            if (this.audioInfo.Disp != 0) {
                mVolb = 1;
                AudioView(true);
                this.m_AudioMenu.setText(String.format("%s", new Object[]{CurAudio(this.audioInfo.Disp)}));
            } else if (mVolb != 0) {
                mVolb = 0;
                AudioView(false);
            }
        }
        CanJni.TeanaOldWcGetRadioInfo(this.radioInfo);
        if (i2b(this.radioInfo.UpdateOnce) && (!check || i2b(this.radioInfo.Update))) {
            this.radioInfo.Update = 0;
            if ((this.radioInfo.Flag & 128) == 0) {
                this.m_fgRds.setText(" ");
            } else {
                this.m_fgRds.setText("RDS");
            }
            if ((this.radioInfo.Flag & 64) == 0) {
                this.m_fgScane.setText(" ");
            } else {
                this.m_fgScane.setText("SCANE");
            }
            if ((this.radioInfo.Flag & 16) == 0) {
                this.m_fgAutop.setText(" ");
            } else {
                this.m_fgAutop.setText("AUTO.P");
            }
            if ((this.radioInfo.Flag & 32) == 0) {
                this.m_fgSt.setText(" ");
            } else {
                this.m_fgSt.setText("ST");
            }
            if ((this.radioInfo.Flag & 8) == 0 || this.modeInfo.Mode != 1) {
                this.m_FreqText.setVisibility(4);
            } else {
                this.m_FreqText.setVisibility(0);
            }
            String string1 = CurBand(this.radioInfo.Band);
            if (this.radioInfo.Men == 0) {
                string2 = "     ";
            } else {
                string2 = "P" + this.radioInfo.Men;
            }
            if (this.radioInfo.Band == 1 || this.radioInfo.Band == 7) {
                string3 = String.valueOf(this.radioInfo.Frq) + " KHz";
            } else {
                string3 = String.valueOf(tranalateIntoString(this.radioInfo.Frq)) + " MHz";
            }
            this.m_RadioMenu.setText(String.format("%s             %s                   %s", new Object[]{string1, string2, string3}));
        }
        CanJni.TeanaOldWcGetRadioText(this.radioText);
        if (i2b(this.radioText.UpdateOnce) && (!check || i2b(this.radioText.Update))) {
            this.radioText.Update = 0;
            this.m_FreqText.setText(byte2ASCIIString(this.radioText.szText, 16));
        }
        CanJni.TeanaOldWcGetCdInfo(this.cdInfo);
        if (i2b(this.cdInfo.UpdateOnce) && (!check || i2b(this.cdInfo.Update))) {
            this.cdInfo.Update = 0;
            if (this.cdInfo.Floder == 0) {
                this.m_fgCdFolder.setText(" ");
            } else if (this.cdInfo.Floder == 1) {
                this.m_fgCdFolder.setText("RPT FLODER");
            } else {
                this.m_fgCdFolder.setText("RDM FLODER ");
            }
            if (this.cdInfo.Repeat == 0) {
                this.m_fgCdRepeat.setText(" ");
            } else if (this.cdInfo.Repeat == 1) {
                this.m_fgCdRepeat.setText("Repeat");
            } else if (this.cdInfo.Repeat == 2) {
                this.m_fgCdRepeat.setText("Disc RPT");
            } else {
                this.m_fgCdRepeat.setText("All CD RPT");
            }
            if (this.cdInfo.Random == 0) {
                this.m_fgCdRandom.setText(" ");
            } else if (this.cdInfo.Random == 1) {
                this.m_fgCdRandom.setText("Random");
            } else if (this.cdInfo.Random == 2) {
                this.m_fgCdRandom.setText("CD RDM");
            } else {
                this.m_fgCdRandom.setText("All CD RDM");
            }
            if (this.cdInfo.Scan == 0) {
                this.m_fgCdScane.setText(" ");
            } else if (this.cdInfo.Scan == 1) {
                this.m_fgCdScane.setText("SCAN");
            } else {
                this.m_fgCdScane.setText("CD SCAN");
            }
            if (this.cdInfo.Text == 0 || this.modeInfo.Mode != 2) {
                this.m_CdText.setVisibility(4);
            } else {
                this.m_CdText.setVisibility(0);
            }
            if (this.cdInfo.Status == 2) {
                this.m_CdMenu.setText(String.format("CD%d-T%d        %02d:%02d", new Object[]{Integer.valueOf(this.cdInfo.CurCd), Integer.valueOf(this.cdInfo.Track), Integer.valueOf(this.cdInfo.Min), Integer.valueOf(this.cdInfo.Sec)}));
            } else {
                this.m_CdMenu.setText(CurCdSta(this.cdInfo.Status));
            }
        }
        CanJni.TeanaOldWcGetCdText(this.cdText);
        if (i2b(this.cdText.UpdateOnce) && (!check || i2b(this.cdText.Update))) {
            this.cdText.Update = 0;
            this.m_CdText.setText(byte2ASCIIString(this.cdText.szText, 16));
        }
        if (this.modeInfo.Power > 0 && this.modeInfo.Mode == 2) {
            long curTick = SystemClock.uptimeMillis();
            if (curTick > mLastTick + 666) {
                mLastTick = curTick;
                if (mLastDiscSta != 0) {
                    mLastDiscSta = 0;
                } else {
                    mLastDiscSta = 1;
                }
                for (int i = 0; i < 6; i++) {
                    if (this.cdInfo.Disc[i] == 0) {
                        this.m_CdSta[i].Show(false);
                    } else if (this.cdInfo.Disc[i] == 1) {
                        this.m_CdSta[i].Show(true);
                    }
                }
                if (this.cdInfo.CurCd > 0 && this.cdInfo.CurCd <= 6) {
                    if (mLastDiscSta != 0) {
                        this.m_CdSta[this.cdInfo.CurCd - 1].Show(true);
                    } else {
                        this.m_CdSta[this.cdInfo.CurCd - 1].Show(false);
                    }
                }
            }
        }
    }

    public static void DealStatusChanged() {
        CanDataInfo.TeanaOldWcModeInfo modeInfo2 = new CanDataInfo.TeanaOldWcModeInfo();
        CanJni.TeanaOldWcGetModeInfo(modeInfo2);
        if (modeInfo2.UpdateOnce > 0 && modeInfo2.Update > 0) {
            modeInfo2.Update = 0;
            if (modeInfo2.Mode == 1 || modeInfo2.Mode == 2 || modeInfo2.Mode == 3) {
                CanFunc.showCanActivity(CanBaseCarDeviceActivity.class, -1);
            }
        }
    }

    public void QueryData() {
        CanJni.TeanaOldWcQuery(5, 1, 169);
        CanJni.TeanaOldWcQuery(5, 1, 166);
        CanJni.TeanaOldWcQuery(5, 1, 168);
        CanJni.TeanaOldWcQuery(5, 1, 134);
        CanJni.TeanaOldWcQuery(5, 1, 167);
        CanJni.TeanaOldWcQuery(5, 1, 132);
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
            return "AM";
        }
        if (data == 7) {
            return "AMAP";
        }
        if (data == 2) {
            return "FM1";
        }
        if (data == 3) {
            return "FM2";
        }
        if (data == 6) {
            return "FMAP";
        }
        if (data == 0) {
            return "FM";
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
        String stringAudio5;
        if (data == 1) {
            return String.format("Volume           %02d", new Object[]{Integer.valueOf(this.audioInfo.Vol)});
        } else if (data == 2) {
            int val = this.audioInfo.Bal;
            if (val > 5) {
                stringAudio5 = "R" + (val - 5);
            } else if (val == 5) {
                stringAudio5 = "  " + (val - 5);
            } else {
                stringAudio5 = "L" + (5 - val);
            }
            return "Balance     " + stringAudio5;
        } else if (data == 3) {
            int val2 = this.audioInfo.Fad;
            if (val2 > 5) {
                stringAudio4 = "F" + (val2 - 5);
            } else if (val2 == 5) {
                stringAudio4 = "  " + (val2 - 5);
            } else {
                stringAudio4 = "R" + (5 - val2);
            }
            return "Fade     " + stringAudio4;
        } else if (data == 4) {
            int val3 = this.audioInfo.Bas;
            if (val3 > 5) {
                stringAudio3 = "+" + (val3 - 5);
            } else if (val3 == 5) {
                stringAudio3 = "  " + (val3 - 5);
            } else {
                stringAudio3 = SdkConstants.RES_QUALIFIER_SEP + (5 - val3);
            }
            return "Bass     " + stringAudio3;
        } else if (data == 5) {
            int val4 = this.audioInfo.Tre;
            if (val4 > 5) {
                stringAudio2 = "+" + (val4 - 5);
            } else if (val4 == 5) {
                stringAudio2 = "  " + (val4 - 5);
            } else {
                stringAudio2 = SdkConstants.RES_QUALIFIER_SEP + (5 - val4);
            }
            return "Treble     " + stringAudio2;
        } else if (data != 6) {
            return " ";
        } else {
            if (this.audioInfo.Beep == 0) {
                stringAudio = "OFF";
            } else {
                stringAudio = " ON";
            }
            return "Beep     " + stringAudio;
        }
    }

    private void AuxView(boolean disp) {
        int it = 4;
        if (disp) {
            it = 0;
        }
        this.m_AuxMenu.setVisibility(it);
    }

    private void AudioView(boolean disp) {
        if (this.modeInfo.Power <= 0) {
            this.m_AudioMenu.setVisibility(4);
        } else if (!disp) {
            MenuVis();
        } else {
            this.m_AudioMenu.setVisibility(0);
            this.m_RadioMenu.setVisibility(4);
            this.m_AuxMenu.setVisibility(4);
            this.m_CdMenu.setVisibility(4);
            this.m_PwrMenu.setVisibility(4);
        }
    }

    private void MenuVis() {
        if (this.modeInfo.Power <= 0) {
            CdView(false);
            RadioView(false);
            AuxView(false);
            this.m_PwrMenu.setVisibility(0);
            this.m_AudioMenu.setVisibility(4);
        } else if (this.audioInfo.Disp != 0) {
            m_AudioFlg1 = 1;
        } else {
            this.m_PwrMenu.setVisibility(4);
            this.m_AudioMenu.setVisibility(4);
            if (this.modeInfo.Mode == 1) {
                CdView(false);
                AuxView(false);
                RadioView(true);
            } else if (this.modeInfo.Mode == 2) {
                CdView(true);
                AuxView(false);
                RadioView(false);
            } else if (this.modeInfo.Mode == 3) {
                CdView(false);
                AuxView(true);
                RadioView(false);
            }
        }
    }

    private void RadioView(boolean disp) {
        int it = 4;
        if (disp) {
            it = 0;
        }
        this.m_fgRds.setVisibility(it);
        this.m_fgScane.setVisibility(it);
        this.m_fgAutop.setVisibility(it);
        this.m_fgSt.setVisibility(it);
        this.m_RadioMenu.setVisibility(it);
        this.m_FreqText.setVisibility(it);
        if ((this.radioInfo.Flag & 8) == 0 || !disp) {
            this.m_FreqText.setVisibility(4);
        } else {
            this.m_FreqText.setVisibility(0);
        }
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
        this.m_fgCdFolder.setVisibility(it);
        this.m_fgCdRepeat.setVisibility(it);
        this.m_fgCdRandom.setVisibility(it);
        this.m_fgCdScane.setVisibility(it);
        this.m_CdMenu.setVisibility(it);
        this.m_CdText.setVisibility(it);
        if (this.cdInfo.Text == 0 || !disp) {
            this.m_CdText.setVisibility(4);
        } else {
            this.m_CdText.setVisibility(0);
        }
    }

    public String tranalateIntoString(int freq) {
        String text = new StringBuilder(String.valueOf(freq)).toString();
        StringBuilder sb = new StringBuilder(text);
        if (text.length() < 3) {
            return TXZResourceManager.STYLE_DEFAULT;
        }
        sb.insert(text.length() - 2, ".");
        return sb.toString();
    }
}
