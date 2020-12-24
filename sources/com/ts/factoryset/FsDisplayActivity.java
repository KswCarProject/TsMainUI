package com.ts.factoryset;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.ts.MainUI.R;
import com.ts.other.ParamButton;
import com.yyw.ts70xhw.FtSet;

public class FsDisplayActivity extends FsBaseActivity {
    public static final int CENTER_ITEM = 5;
    public static final String TAG = "FsDisplayActivity";
    public static final int TOTAL_ITEM = 10;
    View.OnClickListener addClick = new View.OnClickListener() {
        public void onClick(View v) {
            int id = ((ParamButton) v).getIntParam();
            if (FsDisplayActivity.this.mTconVal[(FsDisplayActivity.this.mCurItem * 5) + id] < 99) {
                int[] access$0 = FsDisplayActivity.this.mTconVal;
                int access$1 = (FsDisplayActivity.this.mCurItem * 5) + id;
                access$0[access$1] = access$0[access$1] + 1;
                FtSet.SetTconVal(FsDisplayActivity.this.mTconVal);
                FsDisplayActivity.this.updateItem(FsDisplayActivity.this.mCurItem);
            }
        }
    };
    View.OnClickListener cutClick = new View.OnClickListener() {
        public void onClick(View v) {
            int id = ((ParamButton) v).getIntParam();
            if (FsDisplayActivity.this.mTconVal[(FsDisplayActivity.this.mCurItem * 5) + id] > 0) {
                int[] access$0 = FsDisplayActivity.this.mTconVal;
                int access$1 = (FsDisplayActivity.this.mCurItem * 5) + id;
                access$0[access$1] = access$0[access$1] - 1;
                FtSet.SetTconVal(FsDisplayActivity.this.mTconVal);
                FsDisplayActivity.this.updateItem(FsDisplayActivity.this.mCurItem);
            }
        }
    };
    View.OnClickListener itemClick = new View.OnClickListener() {
        public void onClick(View v) {
            FsDisplayActivity.this.updateTcon(((ParamButton) v).getIntParam());
        }
    };
    private ParamButton[] mBtnAdd = new ParamButton[5];
    private int[] mBtnAddId = {R.id.fstcon_add0, R.id.fstcon_add1, R.id.fstcon_add2, R.id.fstcon_add3, R.id.fstcon_add4};
    private ParamButton[] mBtnCut = new ParamButton[5];
    private int[] mBtnCutId = {R.id.fstcon_cut0, R.id.fstcon_cut1, R.id.fstcon_cut2, R.id.fstcon_cut3, R.id.fstcon_cut4};
    private int[] mBtnDn = {R.drawable.factory_other_box01_dn, R.drawable.factory_other_box02_dn, R.drawable.factory_other_box03_dn, R.drawable.factory_other_box04_dn, R.drawable.factory_other_box05_dn, R.drawable.factory_other_box06_dn, R.drawable.factory_other_box07_dn, R.drawable.factory_other_box08_dn, R.drawable.factory_other_box09_dn, R.drawable.factory_other_box10_dn};
    private ParamButton[] mBtnSW = new ParamButton[2];
    private int[] mBtnSWId = {R.id.fstcon_off, R.id.fstcon_on};
    private ParamButton[] mBtnSub = new ParamButton[10];
    private int[] mBtnSubId = {R.id.fstcon_sub0, R.id.fstcon_sub1, R.id.fstcon_sub2, R.id.fstcon_sub3, R.id.fstcon_sub4, R.id.fstcon_sub5, R.id.fstcon_sub6, R.id.fstcon_sub7, R.id.fstcon_sub8, R.id.fstcon_sub9};
    private int[] mBtnUp = {R.drawable.factory_other_box01_up, R.drawable.factory_other_box02_up, R.drawable.factory_other_box03_up, R.drawable.factory_other_box04_up, R.drawable.factory_other_box05_up, R.drawable.factory_other_box06_up, R.drawable.factory_other_box07_up, R.drawable.factory_other_box08_up, R.drawable.factory_other_box09_up, R.drawable.factory_other_box10_up};
    /* access modifiers changed from: private */
    public int mCurItem = 0;
    /* access modifiers changed from: private */
    public int[] mTconVal = new int[40];
    private TextView[] mTvVal = new TextView[5];
    private int[] mTvValId = {R.id.fstcon_val0, R.id.fstcon_val1, R.id.fstcon_val2, R.id.fstcon_val3, R.id.fstcon_val4};
    View.OnClickListener swClick = new View.OnClickListener() {
        public void onClick(View v) {
            FtSet.SetTconAdj(((ParamButton) v).getIntParam());
            FsDisplayActivity.this.updateSW();
        }
    };

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fs_display);
        topBtnInit(R.string.str_fsmain_display);
        for (int i = 0; i < 10; i++) {
            this.mBtnSub[i] = (ParamButton) findViewById(this.mBtnSubId[i]);
            this.mBtnSub[i].setIntParam(i);
            this.mBtnSub[i].setStateDrawable(this.mBtnUp[i], this.mBtnUp[i], this.mBtnDn[i]);
        }
        for (int i2 = 0; i2 < 7; i2++) {
            this.mBtnSub[i2].setOnClickListener(this.itemClick);
        }
        for (int i3 = 0; i3 < 5; i3++) {
            this.mBtnCut[i3] = (ParamButton) findViewById(this.mBtnCutId[i3]);
            this.mBtnCut[i3].setIntParam(i3);
            this.mBtnCut[i3].setOnClickListener(this.cutClick);
            this.mBtnCut[i3].setStateDrawable(R.drawable.factory_reduce_up, R.drawable.factory_reduce_dn, R.drawable.factory_reduce_dn);
            this.mBtnAdd[i3] = (ParamButton) findViewById(this.mBtnAddId[i3]);
            this.mBtnAdd[i3].setIntParam(i3);
            this.mBtnAdd[i3].setOnClickListener(this.addClick);
            this.mTvVal[i3] = (TextView) findViewById(this.mTvValId[i3]);
        }
        for (int i4 = 0; i4 < 2; i4++) {
            this.mBtnSW[i4] = (ParamButton) findViewById(this.mBtnSWId[i4]);
            this.mBtnSW[i4].setIntParam(i4);
            this.mBtnSW[i4].setOnClickListener(this.swClick);
        }
    }

    public void onClick(View v) {
        updateTcon(((ParamButton) v).getIntParam());
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        updateTcon(0);
        updateSW();
    }

    /* access modifiers changed from: package-private */
    public void updateTcon(int curItem) {
        for (int i = 0; i < 10; i++) {
            this.mBtnSub[i].setSelected(false);
        }
        if (curItem < 10) {
            this.mBtnSub[curItem].setSelected(true);
            updateItem(curItem);
        }
    }

    /* access modifiers changed from: package-private */
    public void updateItem(int curItem) {
        FtSet.GetTconVal(this.mTconVal);
        if (curItem < 8) {
            this.mCurItem = curItem;
            int nStart = curItem * 5;
            for (int i = 0; i < 5; i++) {
                this.mTvVal[i].setText(new StringBuilder(String.valueOf(this.mTconVal[nStart + i])).toString());
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void updateSW() {
        int nSel = FtSet.GetTconAdj() & 1;
        this.mBtnSW[nSel].setSelected(true);
        this.mBtnSW[1 - nSel].setSelected(false);
    }
}
