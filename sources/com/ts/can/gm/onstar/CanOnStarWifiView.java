package com.ts.can.gm.onstar;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanOnStarWifiView extends CanScrollCarInfoView {
    boolean isRefreshPassword = true;
    boolean isRefreshPoint = true;
    int mCount = 0;
    CanDataInfo.GM_OnStar_Wifi_PassWord mOnStar_Wifi_PassWord = new CanDataInfo.GM_OnStar_Wifi_PassWord();
    CanDataInfo.GM_OnStar_Wifi_Point mOnStar_Wifi_Point = new CanDataInfo.GM_OnStar_Wifi_Point();

    public CanOnStarWifiView(Activity activity) {
        super(activity, 2);
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View arg0) {
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_onstar_wifi_point, R.string.can_onstar_wifi_password};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE};
    }

    public void ResetData(boolean check) {
        CanJni.GmRzcGetOnStarWifiPoint(this.mOnStar_Wifi_Point);
        if (i2b(this.mOnStar_Wifi_Point.UpdateOnce) && (!check || i2b(this.mOnStar_Wifi_Point.Update))) {
            this.mOnStar_Wifi_Point.Update = 0;
            this.isRefreshPoint = false;
            Log.d("lh", "resetdate1");
            updateItem(0, 0, new String(this.mOnStar_Wifi_Point.szData));
        }
        CanJni.GmRzcGetOnStarWifiPassWord(this.mOnStar_Wifi_PassWord);
        if (i2b(this.mOnStar_Wifi_PassWord.UpdateOnce) && (!check || i2b(this.mOnStar_Wifi_PassWord.Update))) {
            this.mOnStar_Wifi_PassWord.Update = 0;
            this.isRefreshPassword = false;
            String strPassword = new String(this.mOnStar_Wifi_PassWord.szData);
            Log.d("lh", "resetdate2");
            updateItem(1, 0, strPassword);
        }
        if (this.isRefreshPoint) {
            int i = this.mCount;
            this.mCount = i + 1;
            if (i > 50) {
                this.mCount = 0;
                CanJni.GMQuery(65);
            }
        }
        if (this.isRefreshPassword) {
            int i2 = this.mCount;
            this.mCount = i2 + 1;
            if (i2 > 50) {
                this.mCount = 0;
                CanJni.GMQuery(66);
            }
        }
    }

    public void QueryData() {
        if (this.mOnStar_Wifi_Point.UpdateOnce == 0 || this.mOnStar_Wifi_PassWord.UpdateOnce == 0) {
            Log.d("lh", "querydate");
            CanJni.GMQuery(65);
            CanJni.GMQuery(66);
        }
    }
}
