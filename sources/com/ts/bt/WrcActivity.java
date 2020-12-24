package com.ts.bt;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.main.txz.Wrc;
import com.yyw.ts70xhw.FtSet;

public class WrcActivity extends Activity implements UserCallBack {
    private static final String TAG = "lh";
    private Button mConnect;
    private View.OnClickListener mConnectListener = new View.OnClickListener() {
        public void onClick(View v) {
            Wrc.GetInstance().startScan();
        }
    };
    private ImageView mIvCode;
    private ImageView mIvConnect;
    private ImageView mIvLabel1;
    private ImageView mIvLabel2;
    private ImageView mIvPic;
    private ImageView mIvSmallPic;
    private RelativeLayout mLayout;
    private Switch mSwitch;
    private CompoundButton.OnCheckedChangeListener mSwitchCheckedListener = new CompoundButton.OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
            Log.d(WrcActivity.TAG, "checked=" + checked);
            if (checked) {
                FtSet.SetUartDbg(0);
            } else {
                FtSet.SetUartDbg(1);
            }
            WrcActivity.this.initLayout();
        }
    };

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bt_wrc);
        initView();
        initLayout();
    }

    private void initView() {
        this.mLayout = (RelativeLayout) findViewById(R.id.rv_layout);
        this.mConnect = (Button) findViewById(R.id.btn_connect);
        this.mConnect.setOnClickListener(this.mConnectListener);
        this.mIvConnect = (ImageView) findViewById(R.id.iv_watch_break);
        this.mIvPic = (ImageView) findViewById(R.id.iv_watch_pic);
        this.mIvSmallPic = (ImageView) findViewById(R.id.iv_watch_small_pic);
        this.mIvCode = (ImageView) findViewById(R.id.iv_watch_code);
        this.mIvLabel1 = (ImageView) findViewById(R.id.iv_watch_label1);
        this.mIvLabel2 = (ImageView) findViewById(R.id.iv_watch_label2);
        this.mSwitch = (Switch) findViewById(R.id.switch_watch);
        this.mSwitch.setOnCheckedChangeListener(this.mSwitchCheckedListener);
    }

    /* access modifiers changed from: private */
    public void initLayout() {
        if (Wrc.GetInstance().isConnectWrc()) {
            this.mIvConnect.setImageResource(R.drawable.watch_connect);
            if (FtSet.GetUartDbg() == 0) {
                this.mIvPic.setImageResource(R.drawable.watch_pic_r_dn);
            } else {
                this.mIvPic.setImageResource(R.drawable.watch_pic_l_dn);
            }
        } else {
            this.mIvConnect.setImageResource(R.drawable.watch_break);
            if (FtSet.GetUartDbg() == 0) {
                this.mIvPic.setImageResource(R.drawable.watch_pic_r_up);
            } else {
                this.mIvPic.setImageResource(R.drawable.watch_pic_l_up);
            }
        }
        Resources res = getResources();
        if (res.getConfiguration().locale.getCountry().equals("CN")) {
            Log.d(TAG, "CN");
            setViewVisibility(this.mIvCode, true);
            setViewVisibility(this.mIvSmallPic, false);
            this.mIvLabel2.setImageResource(R.drawable.watch_pic_label01);
            if (FtSet.GetUartDbg() == 0) {
                this.mSwitch.setChecked(true);
                this.mIvLabel1.setImageResource(R.drawable.watch_pic_r_label);
                RelativeLayout.LayoutParams layoutParams1 = (RelativeLayout.LayoutParams) this.mIvLabel2.getLayoutParams();
                layoutParams1.leftMargin = (int) res.getDimension(R.dimen.bt_wrc_ivlable2_cn_left_marginleft);
                this.mIvLabel2.setLayoutParams(layoutParams1);
                ((RelativeLayout.LayoutParams) this.mIvConnect.getLayoutParams()).leftMargin = (int) res.getDimension(R.dimen.bt_wrc_ivconnect_cn_left_marginleft);
                this.mLayout.invalidate();
                return;
            }
            this.mSwitch.setChecked(false);
            this.mIvLabel1.setImageResource(R.drawable.watch_pic_l_label);
            RelativeLayout.LayoutParams layoutParams12 = (RelativeLayout.LayoutParams) this.mIvLabel2.getLayoutParams();
            layoutParams12.leftMargin = (int) res.getDimension(R.dimen.bt_wrc_ivlable2_cn_right_marginleft);
            this.mIvLabel2.setLayoutParams(layoutParams12);
            ((RelativeLayout.LayoutParams) this.mIvConnect.getLayoutParams()).leftMargin = (int) res.getDimension(R.dimen.bt_wrc_ivlable2_cn_right_marginleft);
            this.mLayout.invalidate();
            return;
        }
        Log.d(TAG, "else");
        setViewVisibility(this.mIvCode, false);
        setViewVisibility(this.mIvSmallPic, true);
        this.mIvLabel2.setImageResource(R.drawable.watch_pic_label02);
        if (FtSet.GetUartDbg() == 0) {
            this.mSwitch.setChecked(true);
            this.mIvLabel1.setImageResource(R.drawable.watch_pic_r_label);
            this.mIvSmallPic.setImageResource(R.drawable.watch_pic_l01);
            RelativeLayout.LayoutParams layoutParams13 = (RelativeLayout.LayoutParams) this.mIvLabel2.getLayoutParams();
            layoutParams13.leftMargin = (int) res.getDimension(R.dimen.bt_wrc_ivlable2_nocn_left_marginleft);
            this.mIvLabel2.setLayoutParams(layoutParams13);
            ((RelativeLayout.LayoutParams) this.mIvConnect.getLayoutParams()).leftMargin = (int) res.getDimension(R.dimen.bt_wrc_ivconnect_nocn_left_marginleft);
            RelativeLayout.LayoutParams layoutParams3 = (RelativeLayout.LayoutParams) this.mIvLabel1.getLayoutParams();
            layoutParams3.leftMargin = (int) res.getDimension(R.dimen.bt_wrc_ivlable1_nocn_left_marginleft);
            this.mIvLabel1.setLayoutParams(layoutParams3);
            RelativeLayout.LayoutParams layoutParams4 = (RelativeLayout.LayoutParams) this.mIvPic.getLayoutParams();
            layoutParams4.leftMargin = (int) res.getDimension(R.dimen.bt_wrc_ivpic_nocn_left_marginleft);
            this.mIvPic.setLayoutParams(layoutParams4);
            RelativeLayout.LayoutParams layoutParams5 = (RelativeLayout.LayoutParams) this.mIvSmallPic.getLayoutParams();
            layoutParams5.leftMargin = (int) res.getDimension(R.dimen.bt_wrc_ivsmallpic_nocn_left_marginleft);
            this.mIvSmallPic.setLayoutParams(layoutParams5);
            this.mLayout.invalidate();
            return;
        }
        this.mSwitch.setChecked(false);
        this.mIvLabel1.setImageResource(R.drawable.watch_pic_l_label);
        this.mIvSmallPic.setImageResource(R.drawable.watch_pic_r01);
        RelativeLayout.LayoutParams layoutParams14 = (RelativeLayout.LayoutParams) this.mIvLabel2.getLayoutParams();
        layoutParams14.leftMargin = (int) res.getDimension(R.dimen.bt_wrc_ivlable2_nocn_right_marginleft);
        this.mIvLabel2.setLayoutParams(layoutParams14);
        ((RelativeLayout.LayoutParams) this.mIvConnect.getLayoutParams()).leftMargin = (int) res.getDimension(R.dimen.bt_wrc_ivconnect_nocn_right_marginleft);
        ((RelativeLayout.LayoutParams) this.mIvLabel1.getLayoutParams()).leftMargin = (int) res.getDimension(R.dimen.bt_wrc_ivlable1_nocn_right_marginleft);
        RelativeLayout.LayoutParams layoutParams42 = (RelativeLayout.LayoutParams) this.mIvPic.getLayoutParams();
        layoutParams42.leftMargin = (int) res.getDimension(R.dimen.bt_wrc_ivpic_nocn_right_marginleft);
        this.mIvPic.setLayoutParams(layoutParams42);
        RelativeLayout.LayoutParams layoutParams52 = (RelativeLayout.LayoutParams) this.mIvSmallPic.getLayoutParams();
        layoutParams52.leftMargin = (int) res.getDimension(R.dimen.bt_wrc_ivsmallpic_nocn_right_marginleft);
        this.mIvSmallPic.setLayoutParams(layoutParams52);
        this.mLayout.invalidate();
    }

    private void setViewVisibility(View view, boolean show) {
        if (show) {
            view.setVisibility(0);
        } else {
            view.setVisibility(8);
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        MainTask.GetInstance().SetUserCallBack(this);
        super.onResume();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
    }

    public void UserAll() {
        if (Wrc.GetInstance().isConnectWrc()) {
            this.mIvConnect.setImageResource(R.drawable.watch_connect);
            if (FtSet.GetUartDbg() == 0) {
                this.mIvPic.setImageResource(R.drawable.watch_pic_r_dn);
            } else {
                this.mIvPic.setImageResource(R.drawable.watch_pic_l_dn);
            }
        } else {
            this.mIvConnect.setImageResource(R.drawable.watch_break);
            if (FtSet.GetUartDbg() == 0) {
                this.mIvPic.setImageResource(R.drawable.watch_pic_r_up);
            } else {
                this.mIvPic.setImageResource(R.drawable.watch_pic_l_up);
            }
        }
    }
}
