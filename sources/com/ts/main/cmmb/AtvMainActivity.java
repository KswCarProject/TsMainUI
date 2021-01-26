package com.ts.main.cmmb;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import com.ts.MainUI.CstTv;
import com.ts.MainUI.Evc;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.TsDisplay;
import com.ts.MainUI.UserCallBack;
import com.ts.backcar.AutoFitTextureView;
import com.ts.backcar.BackcarService;
import com.ts.can.CanCameraUI;
import com.ts.main.avin.AvShowMainItem;
import com.ts.main.common.MainSet;
import com.ts.main.common.MainVolume;
import com.ts.main.common.ScreenSet;
import com.ts.main.common.WinShow;
import com.ts.other.ParamButton;
import com.yyw.ts70xhw.KeyDef;
import java.util.ArrayList;
import java.util.HashMap;

public class AtvMainActivity extends Activity implements UserCallBack {
    private static final int ATV_DELAY_LIST = 150;
    private static final int ATV_DELAY_NUM = 50;
    static int nOldMode = 255;
    ParamButton AtvFre = null;
    AvShowMainItem AtvShow = new AvShowMainItem();
    private int[] Atv_btn_Icondn = {R.drawable.cmmb_search_dn, R.drawable.cmmb_mute_dn, R.drawable.cmmb_prev_dn, R.drawable.cmmb_down_dn, R.drawable.cmmb_eq_dn, R.drawable.cmmb_system_dn, R.drawable.cmmb_list_dn, R.drawable.cmmb_set_dn};
    private int[] Atv_btn_Iconup = {R.drawable.cmmb_search_up, R.drawable.cmmb_mute_up, R.drawable.cmmb_prev_up, R.drawable.cmmb_down_up, R.drawable.cmmb_eq_up, R.drawable.cmmb_system_up, R.drawable.cmmb_list_up, R.drawable.cmmb_set_up};
    ParamButton[] BtnAtv = new ParamButton[7];
    RelativeLayout MyRelativeLayout = null;
    String[] TvMode = {"PAL_I", "PAL_DK", "PAL_BG", "PAL_M", "PAL_N", "SECAM_DK", "SECAM_BG", "NTSC_MN"};
    ListView TvPlayList;
    boolean bListShow = false;
    boolean bLongClick = false;
    boolean bModeShow = false;
    ArrayList<HashMap<String, Object>> listItem = new ArrayList<>();
    CstTv mCstTv = CstTv.GetInstance();
    private Evc mEvc = Evc.GetInstance();
    int nListDelayTime = 0;

    /* access modifiers changed from: protected */
    public void setViewPos(View view, int x, int y, int w, int h) {
        RelativeLayout.LayoutParams rl = new RelativeLayout.LayoutParams(w, h);
        rl.leftMargin = x;
        rl.topMargin = y;
        view.setLayoutParams(rl);
        this.MyRelativeLayout.addView(view);
    }

    /* access modifiers changed from: protected */
    public TextView TvCreateRelative(int x, int y, int w, int h) {
        TextView tv = new TextView(this);
        setViewPos(tv, x, y, w, h);
        return tv;
    }

    /* access modifiers changed from: package-private */
    public void ShowMode() {
        if (this.bModeShow) {
            this.bModeShow = false;
            this.TvPlayList.setVisibility(4);
            return;
        }
        this.bModeShow = true;
        this.TvPlayList.setVisibility(0);
        this.nListDelayTime = 150;
        ArrayList<HashMap<String, Object>> listItem2 = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("tv_save", this.TvMode[i]);
            listItem2.add(map);
        }
        this.TvPlayList.setAdapter(new SimpleAdapter(this, listItem2, R.layout.activitytvlist, new String[]{"tv_save"}, new int[]{R.id.btn_tvlist}));
        this.TvPlayList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View arg1, int arg2, long arg3) {
                AtvMainActivity.this.mCstTv.ChgMode((byte) arg2);
                AtvMainActivity.this.nListDelayTime = 150;
                AtvMainActivity.this.AtvShow.SetBtnDelay();
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void ShowList() {
        this.nListDelayTime = 150;
        if (this.bListShow) {
            this.bListShow = false;
            this.TvPlayList.setVisibility(4);
            return;
        }
        this.bListShow = true;
        this.TvPlayList.setVisibility(0);
        ArrayList<HashMap<String, Object>> listItem2 = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("tv_save", "CH " + i + ":  " + (((float) this.mCstTv.tvSave.nSaveCh[i]) / 20.0f));
            listItem2.add(map);
        }
        this.TvPlayList.setAdapter(new SimpleAdapter(this, listItem2, R.layout.activitytvlist, new String[]{"tv_save"}, new int[]{R.id.btn_tvlist}));
        this.TvPlayList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View arg1, int arg2, long arg3) {
                AtvMainActivity.this.mCstTv.PlayFre(arg2 + 1);
                AtvMainActivity.this.nListDelayTime = 150;
                AtvMainActivity.this.AtvShow.SetBtnDelay();
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avin_main);
        this.AtvShow.Inint(this, (RelativeLayout) findViewById(R.id.activity_avin_mainid), 2);
        this.AtvShow.SetIsHaveVol(true);
        this.AtvShow.InintCommonBtn();
        this.AtvShow.GetVideoName().setText(R.string.title_activity_atv_main);
        this.MyRelativeLayout = (RelativeLayout) findViewById(R.id.activity_avin_mainid);
        ParamButton.initFactory(this, this.MyRelativeLayout, 0);
        this.AtvFre = ParamButton.fsCreateRelative(10, 80, 260, 80);
        this.AtvFre.setStateDrawable(R.drawable.cmmb_box, R.drawable.cmmb_box, R.drawable.cmmb_box);
        this.AtvFre.setTextColor(-256);
        this.AtvFre.setTextSize(0, 30.0f);
        for (int i = 0; i < 7; i++) {
            int i2 = 198 / 8;
            this.BtnAtv[i] = ParamButton.fsCreateRelative((i * 142) + 24, 500, 118, 78);
            this.BtnAtv[i].setStateDrawable(this.Atv_btn_Iconup[i], this.Atv_btn_Icondn[i], this.Atv_btn_Icondn[i]);
            this.BtnAtv[i].setIntParam(i + 1);
            this.BtnAtv[i].setOnLongClickListener(new View.OnLongClickListener() {
                public boolean onLongClick(View v) {
                    switch (((ParamButton) v).getIntParam()) {
                        case 3:
                            AtvMainActivity.this.mCstTv.TvStep((byte) 0);
                            AtvMainActivity.this.bLongClick = true;
                            break;
                        case 4:
                            AtvMainActivity.this.mCstTv.TvStep((byte) 1);
                            AtvMainActivity.this.bLongClick = true;
                            break;
                    }
                    return false;
                }
            });
            this.BtnAtv[i].setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    AtvMainActivity.this.AtvShow.SetBtnDelay();
                    switch (((ParamButton) v).getIntParam()) {
                        case 1:
                            AtvMainActivity.this.mCstTv.AutoSearch();
                            return;
                        case 2:
                            MainVolume.GetInstance().VolWinShow();
                            return;
                        case 3:
                            if (!AtvMainActivity.this.bLongClick) {
                                AtvMainActivity.this.mCstTv.PlayChg((byte) 0);
                            }
                            AtvMainActivity.this.bLongClick = false;
                            return;
                        case 4:
                            if (!AtvMainActivity.this.bLongClick) {
                                AtvMainActivity.this.mCstTv.PlayChg((byte) 1);
                            }
                            AtvMainActivity.this.bLongClick = false;
                            return;
                        case 5:
                            WinShow.TurnToEq();
                            return;
                        case 6:
                            AtvMainActivity.this.ShowMode();
                            return;
                        case 7:
                            AtvMainActivity.this.ShowList();
                            return;
                        default:
                            return;
                    }
                }
            });
        }
        this.TvPlayList = new ListView(this);
        this.TvPlayList.setBackgroundResource(R.drawable.cmmb_list_box);
        RelativeLayout.LayoutParams rl = new RelativeLayout.LayoutParams(KeyDef.RKEY_POWER, 427);
        rl.leftMargin = CanCameraUI.BTN_CC_WC_DIRECTION1;
        rl.topMargin = 65;
        this.TvPlayList.setLayoutParams(rl);
        this.MyRelativeLayout.addView(this.TvPlayList);
        this.TvPlayList.setVisibility(4);
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == 0) {
            Log.i("onTouchEvent", "x = " + event.getX() + "y=" + event.getY());
            this.AtvShow.DealKeyTouch();
            return true;
        }
        event.getAction();
        return true;
    }

    /* access modifiers changed from: package-private */
    public void ShowTvBtn(boolean bShow) {
        for (int i = 0; i < 7; i++) {
            if (bShow) {
                this.BtnAtv[i].setVisibility(0);
            } else {
                this.BtnAtv[i].setVisibility(4);
            }
        }
        if (!bShow) {
            this.AtvFre.setVisibility(4);
        } else {
            this.AtvFre.setVisibility(0);
        }
    }

    public void UserAll() {
        if (this.AtvShow.bCameraReady) {
            this.AtvShow.SignalDetect();
            if (nOldMode != this.AtvShow.nShowMode) {
                this.TvPlayList.setVisibility(4);
                this.bListShow = false;
                this.bModeShow = false;
                nOldMode = this.AtvShow.nShowMode;
                switch (this.AtvShow.nShowMode) {
                    case 1:
                        ShowTvBtn(true);
                        break;
                    case 2:
                        ShowTvBtn(true);
                        break;
                    case 3:
                        ShowTvBtn(true);
                        break;
                    case 4:
                        ShowTvBtn(false);
                        break;
                }
            }
            if (this.AtvFre == null) {
                return;
            }
            if (this.mCstTv.isAutoSearch()) {
                this.AtvFre.setText(String.format("CH:%d %.2f", new Object[]{Integer.valueOf(this.mCstTv.tvSave.nSaveNum + 1), Float.valueOf(((float) this.mCstTv.nCurNum) / 20.0f)}));
                this.AtvShow.SetBtnDelay();
                return;
            }
            this.AtvFre.setText("CH:" + (this.mCstTv.tvSave.nWatchNum + 1) + "  " + (((float) this.mCstTv.nCurNum) / 20.0f));
            return;
        }
        EnterAtv();
    }

    /* access modifiers changed from: package-private */
    public void EnterAtv() {
        if (BackcarService.getInstance().bIninOK) {
            this.mEvc.evol_workmode_set(7);
            MainSet.GetInstance().SetVideoChannel(1);
            BackcarService.getInstance().StartCamera((AutoFitTextureView) findViewById(R.id.textureView), false);
            TsDisplay.GetInstance().SetDispParat(2);
            this.AtvShow.bCameraReady = BackcarService.getInstance().bIninOK;
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        this.AtvShow.nDelayNum = 50;
        this.AtvShow.bCameraReady = BackcarService.getInstance().bIninOK;
        MainTask.GetInstance().SetUserCallBack(this);
        this.AtvShow.ShowMode(1, true);
        this.AtvShow.nShowMode = 0;
        EnterAtv();
        super.onResume();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        ScreenSet.GetInstance().Hide();
        this.AtvShow.ShowMode(5, true);
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        BackcarService.getInstance().StopCamera();
        TsDisplay.GetInstance().SetDispParat(-1);
        if (BackcarService.getInstance().bIsAvm360()) {
            MainSet.GetInstance().SetVideoChannel(0);
        }
        super.onPause();
    }
}
