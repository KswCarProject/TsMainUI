package com.ts.factoryset;

import android.os.Bundle;
import android.view.View;
import com.ts.MainUI.R;
import com.ts.other.ParamButton;
import com.yyw.ts70xhw.FtSet;

public class FsTVActivity extends FsBaseActivity {
    public static final String TAG = "FsTVActivity";
    private ParamButton[] mBtnTv;
    private String[] mStrTvType = {"TV_PAL_I", "TV_PAL_DK", "TV_PAL_BG", "TV_PAL_M", "TV_PAL_N", "TV_SECAM_DK", "TV_SECAM_BG", "TV_NTSC_MN", "RES", "RES"};
    private int[] mType = {R.id.fstv_mode0, R.id.fstv_mode1, R.id.fstv_mode2, R.id.fstv_mode3, R.id.fstv_mode4, R.id.fstv_mode5, R.id.fstv_mode6, R.id.fstv_mode7, R.id.fstv_mode8, R.id.fstv_mode9};

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fs_tv);
        topBtnInit(R.string.str_fsmain_atv);
        this.mBtnTv = new ParamButton[10];
        for (int i = 0; i < this.mType.length; i++) {
            this.mBtnTv[i] = (ParamButton) findViewById(this.mType[i]);
            this.mBtnTv[i].setOnClickListener(this);
            this.mBtnTv[i].setIntParam(i);
            this.mBtnTv[i].setText(this.mStrTvType[i]);
        }
    }

    public void onClick(View v) {
        int id = ((ParamButton) v).getIntParam();
        if (id < this.mBtnTv.length) {
            FtSet.SetTvFormat(id);
            updateTvType();
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        updateTvType();
    }

    /* access modifiers changed from: package-private */
    public void updateTvType() {
        int type = FtSet.GetTvFormat();
        if (type < this.mBtnTv.length) {
            for (ParamButton selected : this.mBtnTv) {
                selected.setSelected(false);
            }
            this.mBtnTv[type].setSelected(true);
        }
    }
}
