package com.ts.can.toyota.wc;

import android.app.Activity;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.Evc;
import com.ts.MainUI.R;
import com.ts.can.CanCameraUI;
import com.ts.can.CanFunc;
import com.ts.can.CanRelativeCarInfoView;
import com.ts.other.CustomImgView;
import com.ts.other.ParamButton;
import com.txznet.sdk.TXZResourceManager;
import java.io.UnsupportedEncodingException;

public class CanLexusWcCarDeviceView extends CanRelativeCarInfoView {
    public static final int[] mDsicNumArr = {R.drawable.can_klz_num01, R.drawable.can_klz_num02, R.drawable.can_klz_num03, R.drawable.can_klz_num04, R.drawable.can_klz_num05, R.drawable.can_klz_num06};
    private static int mLastDiscSta = 0;
    private static long mLastTick = 0;
    private static int mVolb = -1;
    private static int m_AudioFlg1 = 0;
    private CanDataInfo.ToyotaWcCdcInfo cdInfo;
    private CanDataInfo.ToyotaWcId3Info cdText;
    private TextView m_CdMenu;
    private CustomImgView[] m_CdSta;
    private TextView m_CdText;
    private TextView m_fgCdFolder;
    private TextView m_fgCdRandom;
    private TextView m_fgCdRepeat;
    private TextView m_fgCdScane;

    public CanLexusWcCarDeviceView(Activity activity) {
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
        this.cdInfo = new CanDataInfo.ToyotaWcCdcInfo();
        this.cdText = new CanDataInfo.ToyotaWcId3Info();
        initCommonScreen();
    }

    private void initCommonScreen() {
        this.m_CdSta = new CustomImgView[6];
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
    }

    public void doOnResume() {
        super.doOnResume();
        Evc.GetInstance().evol_workmode_set(12);
    }

    public void ResetData(boolean check) {
        CanJni.ToyotaWcGetCdcInfo(this.cdInfo);
        if (i2b(this.cdInfo.UpdateOnce) && (!check || i2b(this.cdInfo.Update))) {
            this.cdInfo.Update = 0;
            if (this.cdInfo.ChangerStatus == 2) {
                this.m_CdMenu.setText(String.format("CD%d-T%d        %02d:%02d", new Object[]{Integer.valueOf(this.cdInfo.DiscNum), Integer.valueOf(this.cdInfo.Track), Integer.valueOf(this.cdInfo.PlayMin), Integer.valueOf(this.cdInfo.PlaySec)}));
            } else {
                this.m_CdMenu.setText(CurCdSta(this.cdInfo.ChangerStatus));
            }
            if (this.cdInfo.Scan == 1) {
                this.m_fgCdScane.setText("SCAN");
            } else if (this.cdInfo.DiscScan == 1) {
                this.m_fgCdScane.setText("CD SCAN");
            } else {
                this.m_fgCdScane.setText(" ");
            }
            if (this.cdInfo.Repeat == 1) {
                this.m_fgCdRepeat.setText("Repeat");
            } else if (this.cdInfo.DiscRepeat == 1) {
                this.m_fgCdRepeat.setText("Disc RPT");
            } else {
                this.m_fgCdRepeat.setText(" ");
            }
            if (this.cdInfo.Random == 1) {
                this.m_fgCdRandom.setText("Random");
            } else if (this.cdInfo.DiscRandom == 1) {
                this.m_fgCdRandom.setText("CD RDM");
            } else {
                this.m_fgCdRandom.setText(" ");
            }
        }
        CanJni.ToyotaWcGetId3Info(this.cdText);
        if (i2b(this.cdText.UpdateOnce) && (!check || i2b(this.cdText.Update))) {
            this.cdText.Update = 0;
            this.m_CdText.setText(CanFunc.byte2UnicodeString(this.cdText.NameText, 32));
        }
        long curTick = SystemClock.uptimeMillis();
        if (curTick > mLastTick + 666) {
            mLastTick = curTick;
            if (mLastDiscSta != 0) {
                mLastDiscSta = 0;
            } else {
                mLastDiscSta = 1;
            }
            for (int i = 0; i < 6; i++) {
                if (this.cdInfo.Vaild[i] == 0) {
                    this.m_CdSta[i].Show(false);
                } else if (this.cdInfo.Vaild[i] == 1) {
                    this.m_CdSta[i].Show(true);
                }
            }
            if (this.cdInfo.DiscNum > 0 && this.cdInfo.DiscNum <= 6) {
                if (mLastDiscSta != 0) {
                    this.m_CdSta[this.cdInfo.DiscNum - 1].Show(true);
                } else {
                    this.m_CdSta[this.cdInfo.DiscNum - 1].Show(false);
                }
            }
        }
    }

    public void QueryData() {
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
        return text;
    }

    private TextView AddFlagText(int x, int y, int w, int h) {
        new TextView(getActivity());
        TextView text = getRelativeManager().AddText(x, y, w, h);
        text.setTextSize(0, 28.0f);
        text.setTextColor(-1);
        text.setGravity(19);
        return text;
    }

    private TextView AddMsgText(int x, int y, int w, int h) {
        new TextView(getActivity());
        TextView text = getRelativeManager().AddText(x, y, w, h);
        text.setTextSize(0, 34.0f);
        text.setTextColor(-1);
        text.setGravity(17);
        return text;
    }

    private String CurCdSta(int data) {
        if (data == 0) {
            return "Reading  TOC";
        }
        if (data == 3) {
            return "Fast";
        }
        if (data == 4) {
            return "User  search";
        }
        if (data == 5) {
            return "Internal  search";
        }
        if (data == 6) {
            return "Stop";
        }
        if (data == 7) {
            return "Rom  read";
        }
        if (data == 8) {
            return "Rom  search";
        }
        if (data == 9) {
            return "Retrieving";
        }
        if (data == 10) {
            return "Disc changing(User)";
        }
        if (data == 11) {
            return "Disc changing(Inter)";
        }
        if (data == 12) {
            return "Eject";
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
