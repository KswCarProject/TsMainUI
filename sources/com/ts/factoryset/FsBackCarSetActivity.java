package com.ts.factoryset;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.ts.MainUI.R;
import com.ts.other.ParamButton;
import com.ts.other.ValCal;
import com.yyw.ts70xhw.FtSet;

public class FsBackCarSetActivity extends FsBaseActivity implements View.OnClickListener {
    public static final int BACKCAR_BACK_SW = 9;
    public static final int BACKCAR_CAM_FIX = 6;
    public static final int BACKCAR_CAM_LINE_POS = 3;
    public static final int BACKCAR_CAM_LINE_TYPE = 2;
    public static final int BACKCAR_CAM_MIR = 7;
    public static final int BACKCAR_CAM_MUTE = 1;
    public static final int BACKCAR_CAM_TYPE = 4;
    public static final int BACKCAR_FAST_AVM = 5;
    public static final int BACKCAR_FCAM_MIR = 8;
    public static final int BACKCAR_MAX = 9;
    public static final String TAG = "FsBackCarSetActivity";
    String[] camShowType;
    private Fs2SelItem mItemBackLineOpt;
    private Fs2SelItem mItemCamDetect;
    private Fs2SelItem mItemCamMirror;
    private FsAdjItem mItemCamMute;
    private FsAdjItem mItemCamType;
    private Fs2SelItem mItemCamfastavm;
    private Fs2SelItem mItemFCamMirror;
    private FsAdjItem mItemLinePos;
    private FsAdjItem mItemLineType;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fs_other);
        topBtnInit(R.string.str_fsmain_backcar);
        LinearLayout rl = (LinearLayout) findViewById(R.id.lineView);
        this.camShowType = getResources().getStringArray(R.array.str_fsother_camtype);
        this.mItemCamMute = new FsAdjItem((ViewGroup) rl, 1, R.string.fs_other_cam_mute, R.array.str_fsother_arrparkmute, (View.OnClickListener) this);
        this.mItemLineType = new FsAdjItem((ViewGroup) rl, 2, R.string.fs_other_Line_type, 0, (View.OnClickListener) this);
        this.mItemLinePos = new FsAdjItem((ViewGroup) rl, 3, R.string.fs_other_Line_pos, 0, (View.OnClickListener) this);
        this.mItemCamType = new FsAdjItem((ViewGroup) rl, 4, getResources().getString(R.string.fs_other_cam_type), this.camShowType, (View.OnClickListener) this);
        this.mItemCamfastavm = new Fs2SelItem((ViewGroup) rl, 5, R.string.fs_other_cam_fastavm, R.string.str_fs_off, R.string.str_fs_on, (View.OnClickListener) this);
        this.mItemCamDetect = new Fs2SelItem((ViewGroup) rl, 6, R.string.fs_other_cam_detect, R.string.fs_set_not_detect, R.string.fs_set_detect, (View.OnClickListener) this);
        this.mItemCamMirror = new Fs2SelItem((ViewGroup) rl, 7, R.string.fs_other_cam_mirror, R.string.fs_set_not_mirror, R.string.fs_set_mirror, (View.OnClickListener) this);
        this.mItemFCamMirror = new Fs2SelItem((ViewGroup) rl, 8, R.string.fs_other_fcam_mirror, R.string.fs_set_not_mirror, R.string.fs_set_mirror, (View.OnClickListener) this);
        this.mItemBackLineOpt = new Fs2SelItem((ViewGroup) rl, 9, R.string.fs_other_back_opt, R.string.fs_set_no, R.string.fs_set_have, (View.OnClickListener) this);
        for (int i = 0; i <= 9; i++) {
            UpdateItem(i);
        }
    }

    /* access modifiers changed from: package-private */
    public String GetCamTypeInfo() {
        if (FtSet.GetCamType() > this.camShowType.length) {
            return " ";
        }
        String Str = String.valueOf(String.valueOf(String.valueOf(this.camShowType[FtSet.GetCamType()]) + "\r\n") + "SrcType=" + AtcDisplaySettingsUtils.getInstance().getBackcarSourceMode() + "\r\n") + "ARM2 DISP=" + AtcDisplaySettingsUtils.getInstance().GetBackCarW() + "x" + AtcDisplaySettingsUtils.getInstance().GetBackCarH() + "\r\n";
        if (FtSet.GetCamType() == 1 || FtSet.GetCamType() == 6 || FtSet.GetCamType() == 2 || FtSet.GetCamType() == 7 || FtSet.GetCamType() == 5) {
            Str = String.valueOf(Str) + "AHD TYPE=" + AtcDisplaySettingsUtils.getInstance().getAHDCameraMode() + "\r\n";
        } else if (FtSet.GetCamType() == 3 || FtSet.GetCamType() == 4 || FtSet.GetCamType() == 10) {
            Str = String.valueOf(Str) + "AVM TYPE=" + AtcDisplaySettingsUtils.getInstance().getAVMCameraMode() + "\r\n";
        }
        return String.valueOf(Str) + "AHD SRC=" + AtcDisplaySettingsUtils.getInstance().GetMipiUserScrX() + "x" + AtcDisplaySettingsUtils.getInstance().GetMipiUserScrY();
    }

    public void onClick(View v) {
        boolean inc;
        ParamButton btn = (ParamButton) v;
        int id = btn.getIntParam();
        int val = btn.getIntParam2();
        if (val != 0) {
            inc = true;
        } else {
            inc = false;
        }
        switch (id) {
            case 1:
                FtSet.SetParkMuteDef(ValCal.dataStepNoLoop(FtSet.GetParkMuteDef(), 0, 3, inc));
                break;
            case 2:
                FtSet.SetLineType(ValCal.dataStepNoLoop(FtSet.GetLineType(), 0, 2, inc));
                break;
            case 3:
                FtSet.SetLinePos(ValCal.dataStepNoLoop(FtSet.GetLinePos(), -200, 200, inc));
                break;
            case 4:
                int val2 = ValCal.dataStepNoLoop(FtSet.GetCamType(), 0, 10, inc);
                FtSet.SetCamType(val2);
                if (val2 != 3) {
                    FtSet.SetFastAVM(0);
                    break;
                } else {
                    FtSet.SetFastAVM(1);
                    break;
                }
            case 5:
                FtSet.SetFastAVM(val);
                break;
            case 6:
                FtSet.SetCamFix(val);
                break;
            case 7:
                FtSet.SetCamMir(val);
                break;
            case 8:
                FtSet.SetFcamMir(val);
                break;
            case 9:
                FtSet.SetOptionCam(val);
                break;
        }
        UpdateItem(id);
    }

    /* access modifiers changed from: protected */
    public void UpdateItem(int id) {
        switch (id) {
            case 1:
                this.mItemCamMute.SetVal(FtSet.GetParkMuteDef());
                return;
            case 2:
                this.mItemLineType.SetVal(FtSet.GetLineType());
                return;
            case 3:
                this.mItemLinePos.SetVal(FtSet.GetLinePos());
                return;
            case 4:
                if (this.mItemCamType != null) {
                    this.mItemCamType.SetVal(FtSet.GetCamType());
                    this.mItemCamType.SetText(GetCamTypeInfo());
                    return;
                }
                return;
            case 5:
                if (this.mItemCamfastavm != null) {
                    this.mItemCamfastavm.SetVal(FtSet.GetFastAVM());
                    return;
                }
                return;
            case 6:
                this.mItemCamDetect.SetVal(FtSet.GetCamFix());
                return;
            case 7:
                this.mItemCamMirror.SetVal(FtSet.GetCamMir());
                return;
            case 8:
                this.mItemFCamMirror.SetVal(FtSet.GetFcamMir());
                return;
            case 9:
                this.mItemBackLineOpt.SetVal(FtSet.GetOptionCam());
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }
}
