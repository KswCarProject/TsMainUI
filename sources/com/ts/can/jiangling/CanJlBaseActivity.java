package com.ts.can.jiangling;

import android.os.Bundle;
import android.support.v4.internal.view.SupportMenu;
import android.util.Log;
import android.view.View;
import android.widget.ScrollView;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanBaseActivity;
import com.ts.canview.CanScrollList;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;

public class CanJlBaseActivity extends CanBaseActivity implements View.OnClickListener {
    public static final int ITEM_STA = 1;
    public static final int ITEM_WARN = 2;
    public static final String TAG = "CanJlBaseActivity";
    protected ParamButton mBtnStatus;
    protected ParamButton mBtnWarn;
    protected CanJianglingItem[] mItemWarn = new CanJianglingItem[30];
    protected RelativeLayoutManager mManager;
    protected CanScrollList mStaManager;
    protected ScrollView mSvSta;
    protected ScrollView mSvWarn;
    protected CanDataInfo.JL_WARN_ENTER mWarnData = new CanDataInfo.JL_WARN_ENTER();
    protected CanScrollList mWarnManager;
    protected int mWarnNum;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_can_jiangling_base);
        this.mManager = new RelativeLayoutManager(this, R.id.can_comm_layout);
        this.mBtnWarn = this.mManager.AddButton(0, 96, 267, 92);
        this.mBtnStatus = this.mManager.AddButton(0, 2, 267, 92);
        this.mBtnWarn.setDrawable(R.drawable.can_jl_xx_icon_up, R.drawable.can_jl_xx_icon_dn);
        this.mBtnWarn.setText(R.string.can_error_event);
        this.mBtnWarn.setTextSize(0, 40.0f);
        this.mBtnStatus.setTextSize(0, 40.0f);
        this.mBtnStatus.setTextColor(-1);
        this.mBtnWarn.setTextColor(-7829368);
        this.mBtnStatus.setTag(1);
        this.mBtnWarn.setTag(2);
        this.mStaManager = new CanScrollList(this, R.id.line_sta, 81);
        this.mWarnManager = new CanScrollList(this, R.id.line_warn, 81);
        this.mSvSta = (ScrollView) findViewById(R.id.scr_sta);
        this.mSvWarn = (ScrollView) findViewById(R.id.scr_warn);
        for (int i = 0; i < this.mItemWarn.length; i++) {
            this.mItemWarn[i] = new CanJianglingItem(this);
            this.mItemWarn[i].GetTitleTv().setTextColor(SupportMenu.CATEGORY_MASK);
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        Log.d(TAG, "-----onResume-----");
        ShowStatus(true);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        Log.d(TAG, "-----onPause-----");
    }

    public void onClick(View v) {
        int intValue = ((Integer) v.getTag()).intValue();
    }

    /* access modifiers changed from: protected */
    public void ShowStatus(boolean show) {
        boolean z;
        boolean z2 = false;
        ShowHide((View) this.mSvSta, show);
        ScrollView scrollView = this.mSvWarn;
        if (show) {
            z = false;
        } else {
            z = true;
        }
        ShowHide((View) scrollView, z);
        this.mBtnStatus.setSelected(show);
        ParamButton paramButton = this.mBtnWarn;
        if (!show) {
            z2 = true;
        }
        paramButton.setSelected(z2);
    }

    public void UpdateStatus(int num) {
        if (num > 0) {
            this.mBtnWarn.setTextColor(-1);
        } else {
            this.mBtnWarn.setTextColor(-12303292);
        }
    }

    /* access modifiers changed from: protected */
    public void RemoveAllWarn() {
        this.mWarnManager.RemoveAllViews();
        this.mWarnNum = 0;
    }

    public void ShowHide(View v, boolean show) {
        if (show) {
            v.setVisibility(0);
        } else {
            v.setVisibility(4);
        }
    }

    public void ShowHide(View v, int show) {
        ShowHide(v, show != 0);
    }

    public CanJianglingItem AddStaLine(String strTitle) {
        CanJianglingItem item = new CanJianglingItem(this);
        item.SetText(strTitle);
        item.GetTitleTv().setTextColor(-16711681);
        item.GetValTv().setTextColor(-16776961);
        this.mStaManager.AddView(item.GetView(), 684, 67);
        return item;
    }

    public void AddWarn(String strWarn) {
        if (this.mWarnNum < this.mItemWarn.length) {
            this.mItemWarn[this.mWarnNum].SetText(strWarn);
            this.mWarnManager.AddView(this.mItemWarn[this.mWarnNum].GetView(), 684, 67);
            this.mWarnNum++;
        }
    }

    public void AddWarn(int val, String strWarn) {
        if (val != 0 && this.mWarnNum < this.mItemWarn.length) {
            this.mItemWarn[this.mWarnNum].SetText(strWarn);
            this.mWarnManager.AddView(this.mItemWarn[this.mWarnNum].GetView(), 684, 67);
            this.mWarnNum++;
        }
    }

    public void AddWarn2(int val, String strWarn1, String strWarn2) {
        if (val != 0 && this.mWarnNum < this.mItemWarn.length) {
            if (1 == val) {
                this.mItemWarn[this.mWarnNum].SetText(strWarn1);
                this.mWarnManager.AddView(this.mItemWarn[this.mWarnNum].GetView(), 684, 67);
                this.mWarnNum++;
            } else if (2 == val) {
                this.mItemWarn[this.mWarnNum].SetText(strWarn2);
                this.mWarnManager.AddView(this.mItemWarn[this.mWarnNum].GetView(), 684, 67);
                this.mWarnNum++;
            }
        }
    }

    public void UpdateWarn(boolean chk) {
        CanJni.JLGetWarn(this.mWarnData);
    }

    public void UpdateWarnNum(int num) {
        UpdateStatus(num);
    }
}
