package com.ts.can.mzd.rzc;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.Evc;
import com.ts.MainUI.R;
import com.ts.can.CanBaseCarDeviceActivity;
import com.ts.can.CanCameraUI;
import com.ts.can.CanFunc;
import com.ts.can.CanIF;
import com.ts.can.CanRelativeCarInfoView;
import com.ts.main.common.MainSet;
import com.ts.other.CustomTextView;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;
import com.yyw.ts70xhw.KeyDef;
import java.util.Arrays;

public class CanMzdRzcCDView extends CanRelativeCarInfoView {
    public static final int ITEM_MODE = 7;
    public static final int ITEM_NEXT = 4;
    public static final int ITEM_PAUSE = 3;
    public static final int ITEM_PLAY = 2;
    public static final int ITEM_PREV = 1;
    public static final int ITEM_RDM = 6;
    public static final int ITEM_RPT = 5;
    protected static int mOldSta = -1;
    protected static String[] mStrSta = {"停止/关闭", "暂停", "播放", "读取中", "出碟", "入碟"};
    protected CustomTextView mAlbum;
    protected CustomTextView mArtist;
    protected ParamButton mBtnMode;
    protected ParamButton mBtnNext;
    protected ParamButton mBtnPause;
    protected ParamButton mBtnPlay;
    protected ParamButton mBtnPrev;
    protected ParamButton mBtnRdm;
    protected ParamButton mBtnRpt;
    protected CanDataInfo.Cx4_Cd_Id3 mCdId;
    protected CanDataInfo.Cx4_Dev_Info mDevInfo;
    protected RelativeLayoutManager mManager;
    protected CanDataInfo.Cx4_Cd_PlayInfo mPlayInfo;
    protected CustomTextView mSong;
    protected CustomTextView mSta;
    protected int mStartCount = 0;
    protected String[] mStrDiscSta;
    protected CustomTextView mTime;
    protected CustomTextView mTrack;
    private PopupWindow popupMenuWindow;
    private PopupWindow popupStaWindow;

    public CanMzdRzcCDView(Activity activity) {
        super(activity);
    }

    public boolean onTouch(View arg0, MotionEvent arg1) {
        return false;
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                if (CanJni.GetSubType() == 0) {
                    CanJni.MzdCx4CdCmd(5);
                    return;
                } else {
                    CanJni.MzdCx4CdCmd(131);
                    return;
                }
            case 2:
                if (CanJni.GetSubType() == 0) {
                    CanJni.MzdCx4CdCmd(0);
                    return;
                } else {
                    CanJni.MzdCx4CdCmd(128);
                    return;
                }
            case 3:
                if (CanJni.GetSubType() == 0) {
                    CanJni.MzdCx4CdCmd(1);
                    return;
                } else {
                    CanJni.MzdCx4CdCmd(129);
                    return;
                }
            case 4:
                if (CanJni.GetSubType() == 0) {
                    CanJni.MzdCx4CdCmd(4);
                    return;
                } else {
                    CanJni.MzdCx4CdCmd(132);
                    return;
                }
            case 5:
                CanJni.MzdCx4CdCmd(204);
                return;
            case 6:
                CanJni.MzdCx4CdCmd(187);
                return;
            case 7:
                CanJni.MzdRzcCarAudioSet(4, 5);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mManager = getRelativeManager();
        this.mDevInfo = new CanDataInfo.Cx4_Dev_Info();
        this.mPlayInfo = new CanDataInfo.Cx4_Cd_PlayInfo();
        this.mCdId = new CanDataInfo.Cx4_Cd_Id3();
        DisplayMetrics mDisplayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(mDisplayMetrics);
        if (MainSet.GetScreenType() == 5) {
            BaseUI_1280x480();
        } else if (mDisplayMetrics.heightPixels == 400 && mDisplayMetrics.widthPixels == 1024) {
            FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) this.mManager.GetLayout().getLayoutParams();
            lp.width = 1024;
            lp.height = CanCameraUI.BTN_NISSAN_XTRAL_RVS_ASSIST6;
            lp.gravity = 17;
            this.mManager.GetLayout().setLayoutParams(lp);
            BaseUI();
            this.mManager.GetLayout().setScaleY(0.63f);
        } else {
            BaseUI();
        }
        this.mStrDiscSta = getActivity().getResources().getStringArray(R.array.can_mzd_car_disc_sta);
        if (CanJni.GetSubType() == 0 || 1 == CanJni.GetSubType()) {
            this.mBtnMode.setVisibility(8);
        }
        this.mManager.GetLayout().setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                if (CanMzdRzcCDView.this.mPlayInfo.DiscType != 1) {
                    return;
                }
                if (CanMzdRzcCDView.this.mPlayInfo.DVDSta == 1) {
                    CanMzdRzcCDView.this.showMenuPopupWindow(CanMzdRzcCDView.this.mBtnPause);
                } else if (CanMzdRzcCDView.this.mPlayInfo.DVDSta == 0) {
                    CanMzdRzcCDView.this.showStaPopupWindow(CanMzdRzcCDView.this.mBtnPause);
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    @SuppressLint({"InflateParams"})
    public void showStaPopupWindow(View view) {
        View convertView = LayoutInflater.from(getActivity()).inflate(R.layout.pop_stawindow, (ViewGroup) null);
        this.popupStaWindow = new PopupWindow(convertView, -2, -2, true);
        this.popupStaWindow.setTouchable(true);
        this.popupStaWindow.setTouchInterceptor(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
        this.popupStaWindow.showAtLocation((View) view.getParent(), 51, 96, 161);
        ((ImageView) convertView.findViewById(R.id.pop_ivtop)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                CanJni.MzdCx4CdCmd(177);
            }
        });
        ((ImageView) convertView.findViewById(R.id.pop_ivdn)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                CanJni.MzdCx4CdCmd(178);
            }
        });
        ((ImageView) convertView.findViewById(R.id.pop_ivpause)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                CanJni.MzdCx4CdCmd(1);
            }
        });
    }

    /* access modifiers changed from: protected */
    @SuppressLint({"InflateParams"})
    public void showMenuPopupWindow(View view) {
        View convertView = LayoutInflater.from(getActivity()).inflate(R.layout.pop_menuwindow, (ViewGroup) null);
        this.popupMenuWindow = new PopupWindow(convertView, -2, -2, true);
        this.popupMenuWindow.setTouchable(true);
        this.popupMenuWindow.setTouchInterceptor(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
        this.popupMenuWindow.showAtLocation((View) view.getParent(), 51, 96, 161);
        ((ImageView) convertView.findViewById(R.id.pop_ivleft)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                CanJni.MzdCx4CdCmd(179);
            }
        });
        ((ImageView) convertView.findViewById(R.id.pop_ivright)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                CanJni.MzdCx4CdCmd(180);
            }
        });
        ((ImageView) convertView.findViewById(R.id.pop_ivtop)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                CanJni.MzdCx4CdCmd(177);
            }
        });
        ((ImageView) convertView.findViewById(R.id.pop_ivdn)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                CanJni.MzdCx4CdCmd(178);
            }
        });
        ((ImageView) convertView.findViewById(R.id.pop_ivselect)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                CanJni.MzdCx4CdCmd(181);
            }
        });
    }

    private void BaseUI() {
        this.mManager.GetLayout().setBackgroundResource(R.drawable.can_jeep_ycsb_bg);
        this.mBtnPrev = AddBtn(1, 257, CanCameraUI.BTN_TRUMPCHI_GS4_MODE5, R.drawable.can_jeep_ycsb_prv_up, R.drawable.can_jeep_ycsb_prv_dn);
        this.mBtnPlay = AddBtn(2, 391, CanCameraUI.BTN_TRUMPCHI_GS4_MODE5, R.drawable.can_jeep_ycsb_play_up, R.drawable.can_jeep_ycsb_play_dn);
        this.mBtnPause = AddBtn(3, CanCameraUI.BTN_GEELY_YJX6_GJ, CanCameraUI.BTN_TRUMPCHI_GS4_MODE5, R.drawable.can_jeep_ycsb_pause_up, R.drawable.can_jeep_ycsb_pause_dn);
        this.mBtnNext = AddBtn(4, 660, CanCameraUI.BTN_TRUMPCHI_GS4_MODE5, R.drawable.can_jeep_ycsb_next_up, R.drawable.can_jeep_ycsb_next_dn);
        this.mBtnRpt = AddBtn(5, 46, 25, R.drawable.can_jeep_ycsb_cycle_up, R.drawable.can_jeep_ycsb_cycle_dn);
        this.mBtnRdm = AddBtn(6, 180, 25, R.drawable.can_jeep_ycsb_random_up, R.drawable.can_jeep_ycsb_random_dn);
        this.mBtnMode = AddBtn(7, 25, 464, 103, 64, R.drawable.can_jeep_but_ac_mode_dn, R.drawable.can_jeep_but_ac_mode_dn);
        this.mManager.AddImage(44, 127, R.drawable.can_jeep_ycsb_sjx);
        this.mManager.AddImage(44, 184, R.drawable.can_jeep_ycsb_music);
        this.mManager.AddImage(44, KeyDef.RKEY_MEDIA_PP, R.drawable.can_jeep_ycsb_artist);
        this.mManager.AddImage(44, Can.CAN_MZD_LUOMU, R.drawable.can_jeep_ycsb_disc);
        this.mSta = AddLeftText(84, 119, 300, 42);
        this.mSong = AddLeftText(84, 176, 300, 42);
        this.mAlbum = AddLeftText(84, Can.CAN_LIEBAO_WC, 300, 42);
        this.mArtist = AddLeftText(84, KeyDef.RKEY_NEXT, 300, 42);
        this.mTrack = AddLeftText(430, 23, Can.CAN_NISSAN_XFY, 55);
        this.mTrack.SetPixelSize(40);
        this.mTime = AddCenterText(362, KeyDef.RKEY_res4, CanCameraUI.BTN_TRUMPCHI_GS4_MODE1, 35);
    }

    private void BaseUI_1280x480() {
        this.mManager.GetLayout().setBackgroundResource(R.drawable.can_hpjeep_ycsb_bg);
        this.mBtnPrev = AddBtn(1, 384, KeyDef.RKEY_ANGLEDN, 90, 95, R.drawable.can_jeep_ycsb_prv_up, R.drawable.can_jeep_ycsb_prv_dn);
        this.mBtnPlay = AddBtn(2, 534, KeyDef.RKEY_ANGLEDN, 90, 95, R.drawable.can_jeep_ycsb_play_up, R.drawable.can_jeep_ycsb_play_dn);
        this.mBtnPause = AddBtn(3, 685, KeyDef.RKEY_ANGLEDN, 90, 95, R.drawable.can_jeep_ycsb_pause_up, R.drawable.can_jeep_ycsb_pause_dn);
        this.mBtnNext = AddBtn(4, KeyDef.SKEY_HOME_2, KeyDef.RKEY_ANGLEDN, 90, 95, R.drawable.can_jeep_ycsb_next_up, R.drawable.can_jeep_ycsb_next_dn);
        this.mBtnRpt = AddBtn(5, 18, 10, 103, 56, R.drawable.can_jeep_ycsb_cycle_up, R.drawable.can_jeep_ycsb_cycle_dn);
        this.mBtnRdm = AddBtn(6, 126, 10, 103, 56, R.drawable.can_jeep_ycsb_random_up, R.drawable.can_jeep_ycsb_random_dn);
        this.mBtnMode = AddBtn(7, 25, 353, 103, 64, R.drawable.can_jeep_but_ac_mode_dn, R.drawable.can_jeep_but_ac_mode_dn);
        this.mManager.AddImage(18, 80, R.drawable.can_jeep_ycsb_sjx);
        this.mManager.AddImage(18, 137, R.drawable.can_jeep_ycsb_music);
        this.mManager.AddImage(18, 195, R.drawable.can_jeep_ycsb_artist);
        this.mManager.AddImage(18, Can.CAN_CHRYSLER_TXB, R.drawable.can_jeep_ycsb_disc);
        this.mSta = AddLeftText(58, 72, 300, 42);
        this.mSong = AddLeftText(58, 129, 300, 42);
        this.mAlbum = AddLeftText(58, 187, 300, 42);
        this.mArtist = AddLeftText(58, Can.CAN_SE_DX7_RZC, 300, 42);
        this.mTrack = AddLeftText(558, 17, Can.CAN_NISSAN_XFY, 55);
        this.mTrack.SetPixelSize(40);
        this.mTime = AddCenterText(440, 268, CanCameraUI.BTN_TRUMPCHI_GS4_MODE1, 35);
    }

    public void doOnResume() {
        super.doOnResume();
        Evc.GetInstance().evol_workmode_set(12);
    }

    public void ResetData(boolean check) {
        CanJni.MzdCx4GetDevInfo(this.mDevInfo);
        CanJni.MzdCx4GetCdPlayInfo(this.mPlayInfo);
        CanJni.MzdCx4GetCdId3(this.mCdId);
        if (i2b(this.mDevInfo.UpdateOnce) && (!check || i2b(this.mDevInfo.Update))) {
            this.mDevInfo.Update = 0;
            if (!i2b(this.mDevInfo.fgCd)) {
                this.mSta.setText(this.mStrDiscSta[2]);
                resetText();
            } else if (this.mDevInfo.CdState != 0) {
                this.mSta.setText(this.mStrDiscSta[0]);
                resetText();
            } else {
                this.mSta.setText(this.mStrDiscSta[1]);
            }
        }
        if (i2b(this.mPlayInfo.UpdateOnce) && (!check || i2b(this.mPlayInfo.Update))) {
            this.mPlayInfo.Update = 0;
            if (this.mDevInfo.CdState == 0) {
                if (this.mPlayInfo.WorkMode >= mStrSta.length) {
                    this.mSta.setText(" ");
                } else {
                    this.mSta.setText(mStrSta[this.mPlayInfo.WorkMode]);
                }
                if (this.mPlayInfo.WorkMode == 1 || this.mPlayInfo.WorkMode == 2) {
                    String trackTime = formatTime(this.mPlayInfo.TrackTime);
                    String totalTime = formatTime(this.mPlayInfo.TotalTime);
                    this.mTrack.setText(String.format("TRACK %d/%d", new Object[]{Integer.valueOf(this.mPlayInfo.CurTrack), Integer.valueOf(this.mPlayInfo.TotalTrack)}));
                    this.mTime.setText(String.valueOf(trackTime) + "/" + totalTime);
                } else {
                    resetText();
                }
                this.mBtnRpt.SetSel(this.mPlayInfo.fgRpt);
                this.mBtnRdm.SetSel(this.mPlayInfo.fgRdm);
            }
            if (this.mPlayInfo.DiscType != 1) {
                if (this.popupStaWindow != null && this.popupStaWindow.isShowing()) {
                    this.popupStaWindow.dismiss();
                }
                if (this.popupMenuWindow != null && this.popupMenuWindow.isShowing()) {
                    this.popupMenuWindow.dismiss();
                }
            } else if (this.mPlayInfo.DVDSta == 1) {
                if (this.popupStaWindow != null && this.popupStaWindow.isShowing()) {
                    this.popupStaWindow.dismiss();
                }
            } else if (this.mPlayInfo.DVDSta == 0 && this.popupMenuWindow != null && this.popupMenuWindow.isShowing()) {
                this.popupMenuWindow.dismiss();
            }
        }
        if (!i2b(this.mCdId.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mCdId.Update)) {
            this.mCdId.Update = 0;
            String encode = "";
            switch (this.mCdId.EncoderMode) {
                case 0:
                    encode = "GBK";
                    break;
                case 1:
                    encode = "UTF-16";
                    break;
                case 2:
                    encode = "UTF-16";
                    break;
                case 3:
                    encode = "UTF-8";
                    break;
            }
            if (this.mDevInfo.CdState == 0) {
                this.mSong.setText(byte2String(this.mCdId.szTitle, encode));
                this.mAlbum.setText(byte2String(this.mCdId.szDisc, encode));
                this.mArtist.setText(byte2String(this.mCdId.szArtist, encode));
            }
        }
    }

    private void resetText() {
        this.mSong.setText("");
        this.mAlbum.setText("");
        this.mArtist.setText("");
        this.mTrack.setText("");
        this.mTime.setText("");
    }

    private String formatTime(int time) {
        int hour = time / 3600;
        return String.format("%02d : %02d : %02d", new Object[]{Integer.valueOf(hour), Integer.valueOf((time - (hour * 3600)) / 60), Integer.valueOf(time % 60)});
    }

    public String byte2String(byte[] b, String encode) {
        int Datalen = b.length;
        int i = 0;
        while (true) {
            if (i >= b.length) {
                break;
            } else if (b[i] == 0) {
                Datalen = i;
                break;
            } else {
                i++;
            }
        }
        if (Datalen == 0) {
            return "";
        }
        try {
            if (Datalen == b.length) {
                return new String(b, encode);
            }
            return new String(Arrays.copyOf(b, Datalen), encode);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public void QueryData() {
        CanJni.MzdCx4Query(96, 0);
        Sleep(1);
        CanJni.MzdCx4Query(97, 0);
        Sleep(1);
        CanJni.MzdCx4Query(98, 0);
    }

    /* access modifiers changed from: protected */
    public CustomTextView AddLeftText(int x, int y, int w, int h) {
        CustomTextView item = this.mManager.AddCusText(x, y, w, h);
        item.SetPixelSize(30);
        item.setGravity(19);
        return item;
    }

    /* access modifiers changed from: protected */
    public CustomTextView AddCenterText(int x, int y, int w, int h) {
        CustomTextView item = AddLeftText(x, y, w, h);
        item.setGravity(17);
        return item;
    }

    /* access modifiers changed from: protected */
    public ParamButton AddBtn(int id, int x, int y, int up, int dn) {
        ParamButton btn = this.mManager.AddButton(x, y);
        btn.setTag(Integer.valueOf(id));
        btn.setOnClickListener(this);
        btn.setDrawable(up, dn);
        return btn;
    }

    /* access modifiers changed from: protected */
    public ParamButton AddBtn(int id, int x, int y, int w, int h, int up, int dn) {
        ParamButton btn = this.mManager.AddButton(x, y, w, h);
        btn.setTag(Integer.valueOf(id));
        btn.setOnClickListener(this);
        btn.setDrawable(up, dn);
        return btn;
    }

    public static void DealStatusChanged() {
        int curStatus = CanJni.MzdCx4GetCdSta();
        System.out.println("curStatus = " + curStatus);
        if (curStatus != mOldSta) {
            mOldSta = curStatus;
            switch (curStatus) {
                case 3:
                    if (CanIF.IsExdMode()) {
                        CanJni.MzdCx4CdCmd(14);
                        return;
                    } else if (CanFunc.IsCamMode() == 0) {
                        CanFunc.showCanActivity(CanBaseCarDeviceActivity.class, -2);
                        return;
                    } else {
                        return;
                    }
                case 4:
                    CanJni.MzdCx4CdCmd(15);
                    return;
                default:
                    return;
            }
        }
    }
}
