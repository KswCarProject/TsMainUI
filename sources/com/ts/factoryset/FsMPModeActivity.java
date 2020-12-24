package com.ts.factoryset;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.ts.MainUI.R;
import com.ts.factoryset.FsInputDlg;
import com.ts.main.common.tool;
import com.ts.other.ParamButton;
import com.yyw.ts70xhw.FtSet;

public class FsMPModeActivity extends FsBaseActivity {
    public static final String TAG = "FsMPModeActivity";
    private Button mBtnCancle;
    private ParamButton mBtnExportSd;
    private ParamButton mBtnExportUsb;
    private Button mBtnOK;
    private FsInputDlg mDlg;
    private EditText mEditVal;
    /* access modifiers changed from: private */
    public int m_Id;
    private FsInputDlg.onInputOK onOK = new FsInputDlg.onInputOK() {
        public void onOK(String strVal) {
            Log.e(FsMPModeActivity.TAG, "Input val = " + strVal);
            if (strVal.equals("1001")) {
                FtSet.Destroy();
                Log.e(FsMPModeActivity.TAG, "FtSetDestroy");
            } else if (!strVal.equals("2002")) {
            } else {
                if (FsMPModeActivity.this.m_Id == R.id.fsmp_export_sd) {
                    Log.i(FsMPModeActivity.TAG, "FtSetExportToSd ret = " + FtSet.ExportToSd(0));
                    return;
                }
                Log.i(FsMPModeActivity.TAG, "FtSetExportToUSB ret = " + FtSet.ExportToSd(1));
            }
        }
    };

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fs_mpmode);
        topBtnInit(R.string.str_fsmain_mpmode);
        this.mBtnExportSd = (ParamButton) findViewById(R.id.fsmp_export_sd);
        this.mBtnExportSd.setOnClickListener(this);
        this.mBtnExportUsb = (ParamButton) findViewById(R.id.fsmp_export_usb);
        this.mBtnExportUsb.setOnClickListener(this);
        this.mDlg = new FsInputDlg();
    }

    public void onClick(View v) {
        int ret = 0;
        int id = v.getId();
        if (id == R.id.fsmp_export_sd) {
            ret = FtSet.ExportToSd(0);
            Log.i(TAG, "FtSetExportToSd(0) ret = " + ret);
            tool.GetInstance().exportLauncherInfo(0);
        } else if (id == R.id.fsmp_export_usb) {
            ret = FtSet.ExportToSd(1);
            Log.i(TAG, "FtSetExportToSd(1) ret = " + ret);
            tool.GetInstance().exportLauncherInfo(1);
        }
        if (ret == 0) {
            Toast.makeText(this, R.string.fs_mpm_export_failed, 0).show();
        } else {
            Toast.makeText(this, R.string.fs_mpm_export_success, 0).show();
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }

    /* access modifiers changed from: package-private */
    public void showInputBox() {
        this.mDlg.createDlg(this, this.onOK, true);
    }
}
