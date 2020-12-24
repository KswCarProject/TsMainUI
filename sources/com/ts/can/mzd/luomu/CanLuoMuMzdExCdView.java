package com.ts.can.mzd.luomu;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import com.ts.MainUI.Evc;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanRelativeCarInfoView;
import com.ts.other.CustomImgView;

public class CanLuoMuMzdExCdView extends CanRelativeCarInfoView {
    private TextView mIvCd;
    private CustomImgView mIvCdIcon;

    public CanLuoMuMzdExCdView(Activity activity) {
        super(activity);
    }

    public void onClick(View v) {
        int intValue = ((Integer) v.getTag()).intValue();
    }

    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        return false;
    }

    public void doOnResume() {
        super.doOnResume();
        Evc.GetInstance().evol_workmode_set(6);
    }

    public void doOnPause() {
        super.doOnPause();
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        setBackgroundResource(R.drawable.can_jeep_ycsb_bg);
        this.mIvCd = AddText(100, 30, 200, 60);
        this.mIvCd.setText(R.string.can_cd);
    }

    private TextView AddText(int x, int y, int w, int h) {
        new TextView(getActivity());
        TextView text = getRelativeManager().AddText(x, y, w, h);
        text.setTextSize(0, 40.0f);
        text.setTextColor(-1);
        text.setGravity(19);
        return text;
    }

    public void ResetData(boolean check) {
    }

    public void QueryData() {
    }
}
