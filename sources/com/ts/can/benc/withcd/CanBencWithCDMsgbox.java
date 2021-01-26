package com.ts.can.benc.withcd;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.lgb.canmodule.Can;
import com.ts.MainUI.R;
import com.ts.can.CanCameraUI;
import com.ts.can.CanFunc;
import com.ts.main.common.MainSet;
import com.ts.other.CustomImgView;
import com.ts.other.CustomTextView;
import com.ts.other.RelativeLayoutManager;
import com.yyw.ts70xhw.KeyDef;

public class CanBencWithCDMsgbox implements DialogInterface.OnDismissListener, DialogInterface.OnCancelListener {
    public static final String TAG = "CanBencWithCDMsgbox";
    protected AlertDialog mDlg;
    protected boolean mIsShow;
    private CustomTextView mIvWait;
    private CustomImgView[] mIvdot = new CustomImgView[7];
    private RelativeLayoutManager mManager;
    protected Window mWindow;
    private int nCnt = 0;
    private int nCntTime = 0;
    private int nDispCnt = 0;

    public void Update() {
        Log.d(TAG, "CanBencWithCDMsgbox Update");
        Show(1);
    }

    public void Hide() {
        Show(0);
    }

    public void Init(Context context) {
        if (this.mDlg != null) {
            Log.d(TAG, "Already have instance");
            return;
        }
        Log.d(TAG, "Init");
        this.mDlg = new AlertDialog.Builder(context).create();
        this.mDlg.getWindow().setType(2003);
        this.mDlg.setOnDismissListener(this);
        this.mDlg.setOnCancelListener(this);
        this.mDlg.show();
        this.mDlg.setContentView(R.layout.can_benc_withcd_msgbox);
        this.mWindow = this.mDlg.getWindow();
        WindowManager.LayoutParams wmlp = this.mWindow.getAttributes();
        wmlp.gravity = 51;
        if (MainSet.GetScreenType() == 5) {
            wmlp.x = KeyDef.RKEY_RADIO_3S;
        } else if (MainSet.GetScreenType() == 9) {
            wmlp.x = CanCameraUI.BTN_LANDWIND_3D_FRONT;
        } else {
            wmlp.gravity = 49;
        }
        wmlp.y = 109;
        wmlp.width = 678;
        wmlp.height = 199;
        this.mWindow.setAttributes(wmlp);
        this.mWindow.clearFlags(131080);
        this.mDlg.hide();
        RelativeLayout rl = (RelativeLayout) this.mWindow.findViewById(R.id.can_benc_withcd_box);
        this.mManager = new RelativeLayoutManager(rl);
        rl.getBackground().setAlpha(220);
        this.mIvdot[0] = AddImage(108, 97, R.drawable.can_1280popup_pt04);
        this.mIvdot[1] = AddImage(128, 97, R.drawable.can_1280popup_pt03);
        this.mIvdot[2] = AddImage(148, 97, R.drawable.can_1280popup_pt02);
        this.mIvdot[3] = AddImage(168, 97, R.drawable.can_1280popup_pt01);
        this.mIvdot[4] = AddImage(188, 97, R.drawable.can_1280popup_pt02);
        this.mIvdot[5] = AddImage(208, 97, R.drawable.can_1280popup_pt03);
        this.mIvdot[6] = AddImage(Can.CAN_TEANA_OLD_DJ, 97, R.drawable.can_1280popup_pt04);
        this.mIvWait = this.mManager.AddCusText(0, 0, CanCameraUI.BTN_SENOVA_SUB_BJ40_MODE1, 100);
        this.mIvWait.SetPixelSize(45);
        this.mIvWait.setGravity(17);
        this.mIvWait.setText(R.string.can_audio_change_loading);
        this.nCntTime = 0;
        this.nCnt = 0;
        this.nDispCnt = 0;
    }

    private CustomImgView AddImage(int x, int y, int res) {
        return this.mManager.AddImage(x, y, res);
    }

    private CustomImgView AddImage(int x, int y, int resup, int resdn) {
        CustomImgView img = this.mManager.AddImage(x - 377, y - 92);
        img.setStateDrawable(resup, resdn);
        return img;
    }

    public void Show(int val) {
        Init(CanFunc.mContext);
        if (this.mDlg == null) {
            return;
        }
        if (val != 0) {
            this.mDlg.show();
            SetShowFlg(true);
            return;
        }
        this.mDlg.hide();
        SetShowFlg(false);
        this.nCntTime = 0;
        this.nCnt = 0;
        this.nDispCnt = 0;
    }

    public void OnTimer() {
        int temp;
        if (this.mIsShow) {
            this.nCntTime++;
            if (this.nCntTime > 2) {
                this.nCntTime = 0;
                if (this.nCnt <= 14) {
                    temp = this.nCnt;
                } else if (this.nCnt > 29) {
                    this.nCnt = 0;
                    temp = 0;
                } else {
                    temp = 29 - this.nCnt;
                }
                if (this.mIvdot != null) {
                    this.mIvdot[0].setX((float) ((temp * 20) + 108));
                    this.mIvdot[1].setX((float) ((temp * 20) + 128));
                    this.mIvdot[2].setX((float) ((temp * 20) + 148));
                    this.mIvdot[3].setX((float) ((temp * 20) + 168));
                    this.mIvdot[4].setX((float) ((temp * 20) + 188));
                    this.mIvdot[5].setX((float) ((temp * 20) + 208));
                    this.mIvdot[6].setX((float) ((temp * 20) + Can.CAN_TEANA_OLD_DJ));
                    for (int i = 0; i < 7; i++) {
                        this.mIvdot[i].Show(true);
                    }
                    if (temp == 0) {
                        this.mIvdot[0].Show(false);
                        this.mIvdot[1].Show(false);
                        this.mIvdot[2].Show(false);
                    } else if (temp == 1) {
                        this.mIvdot[0].Show(false);
                        this.mIvdot[1].Show(false);
                    } else if (temp == 2) {
                        this.mIvdot[0].Show(false);
                    } else if (temp == 14) {
                        this.mIvdot[6].Show(false);
                        this.mIvdot[5].Show(false);
                        this.mIvdot[4].Show(false);
                    } else if (temp == 13) {
                        this.mIvdot[6].Show(false);
                        this.mIvdot[5].Show(false);
                    } else if (temp == 12) {
                        this.mIvdot[6].Show(false);
                    }
                }
                this.nCnt++;
            }
            this.nDispCnt++;
            if (this.nDispCnt > 500 || CanFunc.IsCamMode() > 0) {
                Show(0);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void showDoor(ImageView view, int val) {
        if (val != 0) {
            view.setVisibility(0);
        } else {
            view.setVisibility(4);
        }
    }

    private void SetShowFlg(boolean show) {
        if (this.mIsShow != show) {
            this.mIsShow = show;
            Log.d(TAG, "SetShowFlg = " + show);
        }
    }

    public void onDismiss(DialogInterface dialog) {
        SetShowFlg(false);
    }

    public void onCancel(DialogInterface dialog) {
        SetShowFlg(false);
    }
}
