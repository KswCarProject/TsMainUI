package com.ts.can;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.benc.withcd.CanBencWithCDSoSync;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;

public class CanAirPurifyActivity extends CanBaseActivity implements View.OnClickListener, UserCallBack {
    private static final int ITEM_BACK = 1;
    private static final int ITEM_TOGGLE = 0;
    private ParamButton mBtnToggle;
    private CanDataInfo.CanBcZmytDevKqjh mDevKqjh;
    private GifView mGifView;
    private RelativeLayoutManager mManager;
    private TextView mTvPM;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_can_comm_relative);
        RelativeLayout root = (RelativeLayout) findViewById(R.id.can_comm_layout);
        root.setBackgroundResource(R.drawable.can_xhd_purificador_bg);
        this.mManager = new RelativeLayoutManager(root);
        this.mGifView = new GifView(this);
        root.addView(this.mGifView, -1, -1);
        this.mGifView.setGifResource(getResources().getIdentifier("air", "drawable", getPackageName()));
        DisplayMetrics outMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
        int widthPixels = outMetrics.widthPixels;
        int heightPixels = outMetrics.heightPixels;
        addText((widthPixels - 460) / 2, 30, 460, 54, R.string.can_air_pm);
        this.mTvPM = addText((widthPixels - 460) / 2, 85, 460, 54, 0);
        this.mManager.AddImageEx((widthPixels - 355) / 2, 75, 355, 17, R.drawable.can_xhd_purificador_line);
        this.mBtnToggle = addButton((widthPixels - 141) - 40, (heightPixels - 92) - 40, 141, 92, R.drawable.can_xhd_purificador_off, R.drawable.can_xhd_purificador_on, 0);
        addPressedButton(20, 20, 122, 90, R.drawable.can_xhd_purificador_return_up, R.drawable.can_xhd_purificador_return_dn, 1);
        this.mDevKqjh = new CanDataInfo.CanBcZmytDevKqjh();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
        CanJni.BencZmytDevCmd(2, 1, this.mDevKqjh.Sta, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        CanJni.BencZmytDevCmd(2, 1, this.mDevKqjh.Sta, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        String[] strArr = {CanBencWithCDSoSync.KEY_SEAT_PM_STATUS, CanBencWithCDSoSync.KEY_SEAT_PM_FRONT};
        int[] iArr = new int[2];
        iArr[0] = this.mDevKqjh.Sta;
        CanBencWithCDSoSync.SetValue(this, strArr, iArr);
    }

    public void onClick(View v) {
        int id = ((Integer) v.getTag()).intValue();
        if (id == 0) {
            CanJni.BencZmytDevCmd(2, 1, SwValue(this.mDevKqjh.Sta), 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        } else if (id == 1) {
            finish();
            overridePendingTransition(0, 0);
        }
    }

    public void UserAll() {
        ResetData(true);
    }

    private void ResetData(boolean check) {
        CanJni.BencZmytWithCDGetDevKqjh(this.mDevKqjh);
        if (!i2b(this.mDevKqjh.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mDevKqjh.Update)) {
            this.mDevKqjh.Update = 0;
            this.mBtnToggle.SetSel(this.mDevKqjh.Sta);
            this.mTvPM.setText(String.format("%.2f mg/m3", new Object[]{Float.valueOf(((float) this.mDevKqjh.TVOC) * 0.01f)}));
            if (i2b(this.mDevKqjh.Sta)) {
                this.mGifView.play();
            } else {
                this.mGifView.pause();
            }
            CanBencWithCDSoSync.SetValue(this, new String[]{CanBencWithCDSoSync.KEY_SEAT_PM_STATUS, CanBencWithCDSoSync.KEY_SEAT_PM_FRONT}, new int[]{this.mDevKqjh.Sta, 1});
        }
    }

    private int SwValue(int val) {
        return val == 0 ? 1 : 0;
    }

    public ParamButton addButton(int x, int y, int w, int h, int normal, int selected, int id) {
        ParamButton button = this.mManager.AddButton(x, y, w, h);
        button.setStateUpSel(normal, selected);
        button.setOnClickListener(this);
        button.setTag(Integer.valueOf(id));
        return button;
    }

    public ParamButton addPressedButton(int x, int y, int w, int h, int normal, int pressed, int id) {
        ParamButton button = this.mManager.AddButton(x, y, w, h);
        button.setStateUpDn(normal, pressed);
        button.setOnClickListener(this);
        button.setTag(Integer.valueOf(id));
        return button;
    }

    public TextView addText(int x, int y, int w, int h, int textId) {
        TextView tv = this.mManager.AddText(x, y, w, h);
        if (textId > 0) {
            tv.setText(textId);
        }
        tv.setGravity(17);
        tv.setTextColor(-1);
        tv.setTextSize(0, 36.0f);
        return tv;
    }
}
