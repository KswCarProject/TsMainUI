package com.ts.can.nissan;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.Evc;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanBaseActivity;
import com.ts.can.CanCameraUI;
import com.ts.other.CustomImgView;
import com.ts.other.RelativeLayoutManager;
import com.yyw.ts70xhw.KeyDef;
import java.io.UnsupportedEncodingException;

public class CanNissanOldDeviceActivity extends CanBaseActivity implements UserCallBack, View.OnClickListener {
    public static final String TAG = "CanNissanOldDeviceActivity";
    public static final int[] mDsicNumArr = {R.drawable.can_klz_num01, R.drawable.can_klz_num02, R.drawable.can_klz_num03, R.drawable.can_klz_num04, R.drawable.can_klz_num05, R.drawable.can_klz_num06};
    private static int mEqb = -1;
    private static int mLastDiscSta = 0;
    private static long mLastTick = 0;
    private static int mVolb = -1;
    private static int m_AudioFlg1 = 0;
    private static int m_AudioFlg2 = 0;
    private CanDataInfo.TeanaCarMode mCarModeData = new CanDataInfo.TeanaCarMode();
    private CanDataInfo.TeanaCdInfo mCdInfoData = new CanDataInfo.TeanaCdInfo();
    private CanDataInfo.TeanaCdSta mCdStaData = new CanDataInfo.TeanaCdSta();
    private CanDataInfo.TeanaCdText mCdTextData = new CanDataInfo.TeanaCdText();
    private CanDataInfo.TeanaClock mClockData = new CanDataInfo.TeanaClock();
    private CanDataInfo.TeanaEQ mEQData = new CanDataInfo.TeanaEQ();
    protected RelativeLayoutManager mManager;
    private CanDataInfo.TeanaRadInfo mRadInfoData = new CanDataInfo.TeanaRadInfo();
    private CanDataInfo.TeanaRadSta mRadStaData = new CanDataInfo.TeanaRadSta();
    private CanDataInfo.TeanaRadText mRadTextData = new CanDataInfo.TeanaRadText();
    private CanDataInfo.TeanaVol mVolData = new CanDataInfo.TeanaVol();
    private TextView m_AudioMenu;
    private TextView m_AuxMenu;
    private TextView m_CdMenu;
    private CustomImgView[] m_CdSta = new CustomImgView[6];
    private TextView m_CdText;
    private TextView m_FreqText;
    private TextView m_PwrMenu;
    private TextView m_RadioMenu;
    private TextView m_fgAutop;
    private TextView m_fgCdFolder;
    private TextView m_fgCdMp3;
    private TextView m_fgCdScane;
    private TextView m_fgCdWma;
    private TextView m_fgRds;
    private TextView m_fgScane;
    private TextView m_fgSt;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_can_comm_relative);
        this.mManager = new RelativeLayoutManager(this, R.id.can_comm_layout);
        this.mManager.GetLayout().setBackgroundResource(R.drawable.can_klz_bg);
        this.m_fgRds = AddFlagText(200, 175, 100, 40);
        this.m_fgScane = AddFlagText(KeyDef.RKEY_MEDIA_SLOW, 175, 100, 40);
        this.m_fgSt = AddFlagText(473, 175, 100, 40);
        this.m_fgAutop = AddFlagText(CanCameraUI.BTN_CHANA_CS75_MODE4, 175, 100, 40);
        this.m_RadioMenu = AddMenuText(130, Can.CAN_NISSAN_XFY, 767, 50);
        this.m_FreqText = AddMsgText(130, KeyDef.RKEY_MEDIA_SUBT, 767, 45);
        this.m_fgCdFolder = AddFlagText(200, 175, 130, 40);
        this.m_fgCdWma = AddFlagText(KeyDef.RKEY_RDS_TA, 175, 100, 40);
        this.m_fgCdMp3 = AddFlagText(424, 175, 100, 40);
        this.m_fgCdScane = AddFlagText(509, 175, 100, 40);
        for (int i = 0; i < 6; i++) {
            this.m_CdSta[i] = this.mManager.AddImage((i * 35) + 626, 183, mDsicNumArr[i]);
            this.m_CdSta[i].Show(false);
        }
        this.m_CdMenu = AddMenuText(277, Can.CAN_NISSAN_XFY, CanCameraUI.BTN_TRUMPCHI_GS4_MODE1, 50);
        this.m_CdText = AddMsgText(130, KeyDef.RKEY_MEDIA_SUBT, 767, 45);
        this.m_AuxMenu = AddMenuText(130, Can.CAN_NISSAN_XFY, 767, 50);
        this.m_AuxMenu.setText("AUX");
        this.m_PwrMenu = AddMenuText(130, Can.CAN_NISSAN_XFY, 767, 50);
        this.m_PwrMenu.setText("Power Off");
        this.m_AudioMenu = AddMenuText(130, Can.CAN_NISSAN_XFY, 767, 50);
    }

    private TextView AddMenuText(int x, int y, int w, int h) {
        new TextView(this);
        TextView text = this.mManager.AddText(x, y, w, h);
        text.setTextSize(0, 40.0f);
        text.setTextColor(-1);
        text.setGravity(17);
        text.setVisibility(4);
        return text;
    }

    private TextView AddFlagText(int x, int y, int w, int h) {
        new TextView(this);
        TextView text = this.mManager.AddText(x, y, w, h);
        text.setTextSize(0, 28.0f);
        text.setTextColor(-1);
        text.setGravity(19);
        text.setVisibility(4);
        return text;
    }

    private TextView AddMsgText(int x, int y, int w, int h) {
        new TextView(this);
        TextView text = this.mManager.AddText(x, y, w, h);
        text.setTextSize(0, 34.0f);
        text.setTextColor(-1);
        text.setGravity(17);
        text.setVisibility(4);
        return text;
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        Evc.GetInstance().evol_workmode_set(12);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
    }

    private String CurBand(int data) {
        if (data == 1) {
            return "AM";
        }
        if (data == 2) {
            return "AMAP";
        }
        if (data == 3) {
            return "FM1";
        }
        if (data == 4) {
            return "FM2";
        }
        if (data == 5) {
            return "FMAP";
        }
        return " ";
    }

    private String CurCdSta(int data) {
        if (data == 1) {
            return "Read  Disc";
        }
        if (data == 2) {
            return "Loading  Disc";
        }
        if (data == 3) {
            return "Insert  Disc";
        }
        if (data == 4) {
            return "Busy";
        }
        if (data == 5) {
            return "Eject  Disc";
        }
        if (data == 6) {
            return "Select  Disc  To  Load";
        }
        if (data == 7) {
            return "Select  Disc  To  Eject";
        }
        if (data == 8) {
            return "Disc  Error";
        }
        return " ";
    }

    private String CurEQ(int data, int val) {
        String stringEQ;
        String stringEQ2;
        String stringEQ3;
        String stringEQ4;
        String stringEQ5;
        if (data == 1) {
            if (val > 7) {
                stringEQ5 = "+" + (val - 7);
            } else if (val == 7) {
                stringEQ5 = "  " + (val - 7);
            } else {
                stringEQ5 = "-" + (7 - val);
            }
            return "Bass     " + stringEQ5;
        } else if (data == 2) {
            if (val > 7) {
                stringEQ4 = "+" + (val - 7);
            } else if (val == 7) {
                stringEQ4 = "  " + (val - 7);
            } else {
                stringEQ4 = "-" + (7 - val);
            }
            return "Treble     " + stringEQ4;
        } else if (data == 3) {
            if (val > 7) {
                stringEQ3 = "F" + (val - 7);
            } else if (val == 7) {
                stringEQ3 = "  " + (val - 7);
            } else {
                stringEQ3 = "R" + (7 - val);
            }
            return "Fade     " + stringEQ3;
        } else if (data == 4) {
            if (val > 7) {
                stringEQ2 = "R" + (val - 7);
            } else if (val == 7) {
                stringEQ2 = "  " + (val - 7);
            } else {
                stringEQ2 = "L" + (7 - val);
            }
            return "Balance     " + stringEQ2;
        } else if (data != 5) {
            return " ";
        } else {
            if (val == 0) {
                stringEQ = "OFF";
            } else {
                stringEQ = " ON";
            }
            return "Beep     " + stringEQ;
        }
    }

    private String byte2ASCIIString(byte[] data, int len) {
        try {
            return new String(data, 0, len, "GBK");
        } catch (UnsupportedEncodingException e) {
            return "";
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
        if (this.mRadStaData.Text == 0 || !disp) {
            this.m_FreqText.setVisibility(4);
        } else {
            this.m_FreqText.setVisibility(0);
        }
    }

    private void CdView(boolean disp) {
        int it = 4;
        if (disp) {
            it = 0;
        }
        this.m_fgCdFolder.setVisibility(it);
        this.m_fgCdWma.setVisibility(it);
        this.m_fgCdMp3.setVisibility(it);
        this.m_fgCdScane.setVisibility(it);
        this.m_CdMenu.setVisibility(it);
        for (int i = 0; i < 6; i++) {
            this.m_CdSta[i].Show(false);
        }
        if (this.mCdStaData.Text == 0 || !disp) {
            this.m_CdText.setVisibility(4);
        } else {
            this.m_CdText.setVisibility(0);
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
        if (this.mCarModeData.PWR <= 0) {
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
        if (this.mCarModeData.PWR <= 0) {
            CdView(false);
            RadioView(false);
            AuxView(false);
            this.m_PwrMenu.setVisibility(0);
            this.m_AudioMenu.setVisibility(4);
        } else if (this.mVolData.Show == 0 && this.mEQData.Type == 0) {
            this.m_PwrMenu.setVisibility(4);
            this.m_AudioMenu.setVisibility(4);
            if (this.mCarModeData.Mode == 1) {
                CdView(false);
                AuxView(false);
                RadioView(true);
            } else if (this.mCarModeData.Mode == 2) {
                CdView(true);
                AuxView(false);
                RadioView(false);
            } else if (this.mCarModeData.Mode == 3) {
                CdView(false);
                AuxView(true);
                RadioView(false);
            }
        } else {
            m_AudioFlg1 = 1;
            m_AudioFlg2 = 1;
        }
    }

    private void ResetData(boolean check) {
        String string2;
        String string3;
        CanJni.TeanOldGetCarMode(this.mCarModeData);
        if (i2b(this.mCarModeData.UpdateOnce) && (!check || i2b(this.mCarModeData.Update))) {
            this.mCarModeData.Update = 0;
            MenuVis();
        }
        CanJni.TeanOldGetVol(this.mVolData);
        if (i2b(this.mVolData.UpdateOnce) && (!check || i2b(this.mVolData.Update) || m_AudioFlg1 != 0)) {
            this.mVolData.Update = 0;
            m_AudioFlg1 = 0;
            if (this.mVolData.Show != 0) {
                mVolb = 1;
                AudioView(true);
                this.m_AudioMenu.setText(String.format("Volume           %02d", new Object[]{Integer.valueOf(this.mVolData.Val)}));
            } else if (mVolb != 0) {
                mVolb = 0;
                AudioView(false);
            }
        }
        CanJni.TeanOldGetEQ(this.mEQData);
        if (i2b(this.mEQData.UpdateOnce) && (!check || i2b(this.mEQData.Update) || m_AudioFlg2 != 0)) {
            this.mEQData.Update = 0;
            m_AudioFlg2 = 0;
            if (this.mEQData.Type != 0) {
                mEqb = 1;
                AudioView(true);
                this.m_AudioMenu.setText(String.format("%s", new Object[]{CurEQ(this.mEQData.Type, this.mEQData.Val)}));
            } else if (mEqb != 0) {
                mEqb = 0;
                AudioView(false);
            }
        }
        CanJni.TeanOldGetRadSta(this.mRadStaData);
        if (i2b(this.mRadStaData.UpdateOnce) && (!check || i2b(this.mRadStaData.Update))) {
            this.mRadStaData.Update = 0;
            if (this.mRadStaData.Rds == 0) {
                this.m_fgRds.setText(" ");
            } else {
                this.m_fgRds.setText("RDS");
            }
            if (this.mRadStaData.Scane == 0) {
                this.m_fgScane.setText(" ");
            } else {
                this.m_fgScane.setText("SCANE");
            }
            if (this.mRadStaData.AutoP == 0) {
                this.m_fgAutop.setText(" ");
            } else {
                this.m_fgAutop.setText("AUTO.P");
            }
            if (this.mRadStaData.ST == 0) {
                this.m_fgSt.setText(" ");
            } else {
                this.m_fgSt.setText("ST");
            }
            if (this.mRadStaData.Text == 0 || this.mCarModeData.Mode != 1) {
                this.m_FreqText.setVisibility(4);
            } else {
                this.m_FreqText.setVisibility(0);
            }
        }
        CanJni.TeanOldGetRadInfo(this.mRadInfoData);
        if (i2b(this.mRadInfoData.UpdateOnce) && (!check || i2b(this.mRadInfoData.Update))) {
            this.mRadInfoData.Update = 0;
            String string1 = CurBand(this.mRadInfoData.Band);
            if (this.mRadInfoData.Preset == 0) {
                string2 = "     ";
            } else {
                string2 = "P" + this.mRadInfoData.Preset;
            }
            if (this.mRadInfoData.Band >= 3) {
                string3 = String.format("%.2f MHz", new Object[]{Double.valueOf((((double) (this.mRadInfoData.Freq - 1)) * 0.05d) + 87.5d)});
            } else if (this.mRadInfoData.AM530 == 0) {
                string3 = String.format("%d KHz", new Object[]{Integer.valueOf(((this.mRadInfoData.Freq - 1) * 9) + CanCameraUI.BTN_CHANA_ALSVINV7_MODE2)});
            } else {
                string3 = String.format("%d KHz", new Object[]{Integer.valueOf(((this.mRadInfoData.Freq - 1) * 10) + CanCameraUI.BTN_CHANA_ALSVINV7_MODE1)});
            }
            this.m_RadioMenu.setText(String.format("%s             %s                   %s", new Object[]{string1, string2, string3}));
        }
        CanJni.TeanOldGetRadText(this.mRadTextData);
        if (i2b(this.mRadTextData.UpdateOnce) && (!check || i2b(this.mRadTextData.Update))) {
            this.mRadTextData.Update = 0;
            this.m_FreqText.setText(byte2ASCIIString(this.mRadTextData.szText, 16));
        }
        CanJni.TeanOldGetCdSta(this.mCdStaData);
        if (i2b(this.mCdStaData.UpdateOnce) && (!check || i2b(this.mCdStaData.Update))) {
            this.mCdStaData.Update = 0;
            if (this.mCdStaData.Folder == 0) {
                this.m_fgCdFolder.setText(" ");
            } else {
                this.m_fgCdFolder.setText("FOLDER");
            }
            if (this.mCdStaData.Wma == 0) {
                this.m_fgCdWma.setText(" ");
            } else {
                this.m_fgCdWma.setText("WMA");
            }
            if (this.mCdStaData.Mp3 == 0) {
                this.m_fgCdMp3.setText(" ");
            } else {
                this.m_fgCdMp3.setText("MP3");
            }
            if (this.mCdStaData.Scane == 0) {
                this.m_fgCdScane.setText(" ");
            } else {
                this.m_fgCdScane.setText("SCANE");
            }
            if (this.mCdStaData.Text == 0 || this.mCarModeData.Mode != 2) {
                this.m_CdText.setVisibility(4);
            } else {
                this.m_CdText.setVisibility(0);
            }
        }
        if (this.mCarModeData.PWR > 0 && this.mCarModeData.Mode == 2) {
            long curTick = GetTickCount();
            if (curTick > mLastTick + 666) {
                mLastTick = curTick;
                if (mLastDiscSta != 0) {
                    mLastDiscSta = 0;
                } else {
                    mLastDiscSta = 1;
                }
                for (int i = 0; i < 6; i++) {
                    if (this.mCdStaData.DiscSta[i] == 0) {
                        this.m_CdSta[i].Show(false);
                    } else if (this.mCdStaData.DiscSta[i] == 1) {
                        this.m_CdSta[i].Show(true);
                    } else if (this.mCdStaData.DiscSta[i] == 2) {
                        if (mLastDiscSta != 0) {
                            this.m_CdSta[i].Show(true);
                        } else {
                            this.m_CdSta[i].Show(false);
                        }
                    }
                }
            }
        }
        CanJni.TeanOldGetCdInfo(this.mCdInfoData);
        if (i2b(this.mCdInfoData.UpdateOnce) && (!check || i2b(this.mCdInfoData.Update))) {
            this.mCdInfoData.Update = 0;
            if (this.mCdInfoData.CdSta == 0) {
                this.m_CdMenu.setText(String.format("CD%d-T%d        %02d:%02d", new Object[]{Integer.valueOf(this.mCdInfoData.CurDisc), Integer.valueOf(this.mCdInfoData.CurTrack), Integer.valueOf(this.mCdInfoData.Hour), Integer.valueOf(this.mCdInfoData.Min)}));
            } else {
                this.m_CdMenu.setText(CurCdSta(this.mCdInfoData.CdSta));
            }
        }
        CanJni.TeanOldGetCdText(this.mCdTextData);
        if (!i2b(this.mCdTextData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mCdTextData.Update)) {
            this.mCdTextData.Update = 0;
            this.m_CdText.setText(byte2ASCIIString(this.mCdTextData.szText, 16));
        }
    }

    public void onClick(View v) {
        int intValue = ((Integer) v.getTag()).intValue();
    }

    public void UserAll() {
        ResetData(true);
    }
}
