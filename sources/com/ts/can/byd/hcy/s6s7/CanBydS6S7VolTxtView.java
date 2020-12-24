package com.ts.can.byd.hcy.s6s7;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.can.CanRelativeCarInfoView;

public class CanBydS6S7VolTxtView extends CanRelativeCarInfoView {
    private static String[] mVolInfo = {"", "蓄电池电压低，三分钟后将关闭多媒体系统", "请常按启动按钮启动", "请将档位切换至 P档域", "请常按启动按钮退电", "请停止车辆再按启动按钮", "请将钥匙紧贴启动按钮", "请确认钥匙是否在车内", "为了您的爱车，请解除驻车制动", "请关好车门", "钥匙电池电量低，请及时更换", "请轻轻转动方向盘，再尝试启动", "请专心驾驶", "自动灯光已开启", "自动灯光已关闭", "疲劳预警系统已开启", "疲劳预警系统已关闭", "疲劳预警系统已进入演示模式", "疲劳预警系统已进入工作模式", "学习完毕", "为了您的安全，请系好安全带", "燃油不足，是否导航至加油站", "无法启动，请联系服务店维修"};
    private CanDataInfo.BYDS6S7VolumnSetData mVolData;
    private TextView mVolTxt;

    public CanBydS6S7VolTxtView(Activity activity) {
        super(activity);
    }

    public boolean onTouch(View arg0, MotionEvent arg1) {
        return false;
    }

    public void onClick(View arg0) {
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mVolData = new CanDataInfo.BYDS6S7VolumnSetData();
        this.mVolTxt = addText(0, 222, 1024, 100);
        this.mVolTxt.setGravity(17);
        this.mVolTxt.setTextSize(0, 50.0f);
        this.mVolTxt.setTextColor(-1);
    }

    public void ResetData(boolean check) {
        CanJni.BYDS6S7GetVolumnSetData(this.mVolData);
        if (!i2b(this.mVolData.UpdateOnce)) {
            return;
        }
        if ((!check || i2b(this.mVolData.UpdateOnce)) && this.mVolData.VolumnText < mVolInfo.length) {
            this.mVolTxt.setText(mVolInfo[this.mVolData.VolumnText]);
        }
    }

    public void QueryData() {
    }
}
