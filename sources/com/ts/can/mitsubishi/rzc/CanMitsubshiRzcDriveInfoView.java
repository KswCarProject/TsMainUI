package com.ts.can.mitsubishi.rzc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;
import com.ts.canview.CanItemMsgBox;

public class CanMitsubshiRzcDriveInfoView extends CanScrollCarInfoView {
    private static final int ITEM_BASE_TITILE = 0;
    private static final int ITEM_MODE_AVE_FULE = 3;
    private static final int ITEM_SDFW_AVE_FULE = 8;
    private static final int ITEM_SDFW_AVE_FULE_RST = 9;
    private static final int ITEM_SSYH = 2;
    private static final int ITEM_TITLE_SDFW_AVE_FULE = 7;
    private static final int ITEM_TITLE_ZDFW_AVE_FULE = 4;
    private static final int ITEM_XHLC = 1;
    private static final int ITEM_ZDFW_AVE_FULE = 5;
    private static final int ITEM_ZDFW_AVE_FULE_RST = 6;
    private CanDataInfo.MitSubishiRzcFule m_FuleData;
    private CanDataInfo.MitSubishiRzcSet m_SetData;

    public CanMitsubshiRzcDriveInfoView(Activity activity) {
        super(activity, 10);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 3:
                CanJni.MitSubishiRzcCarSet(28, item);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        int id = ((Integer) v.getTag()).intValue();
        switch (id) {
            case 6:
                new CanItemMsgBox(id, getActivity(), R.string.can_sure_reset, new CanItemMsgBox.onMsgBoxClick() {
                    public void onOK(int param) {
                        CanJni.MitSubishiRzcCarSet(29, 1);
                    }
                });
                return;
            case 9:
                new CanItemMsgBox(id, getActivity(), R.string.can_sure_reset, new CanItemMsgBox.onMsgBoxClick() {
                    public void onOK(int param) {
                        CanJni.MitSubishiRzcCarSet(30, 1);
                    }
                });
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_log_info, R.string.can_range_xhlc, R.string.can_ssyh, R.string.can_rst_mode, R.string.can_auto_rst_avg_fule, R.string.can_avg_consump, R.string.can_avg_oil_reset, R.string.can_manual_rst_avg_fule, R.string.can_avg_consump, R.string.can_avg_oil_reset};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.TEXT, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.TEXT, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TEXT, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.TITLE};
        this.mPopValueIds[3] = new int[]{R.string.can_mzd_cx4_drive_owner, R.string.can_mzd_cx4_drive_auto};
        this.m_FuleData = new CanDataInfo.MitSubishiRzcFule();
        this.m_SetData = new CanDataInfo.MitSubishiRzcSet();
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        super.InitUI();
    }

    public void ResetData(boolean check) {
        CanJni.MitSubishiRzcGetFuleData(this.m_FuleData);
        if (i2b(this.m_FuleData.UpdateOnce) && (!check || i2b(this.m_FuleData.Update))) {
            this.m_FuleData.Update = 0;
            updateItem(1, this.m_FuleData.xhlc, String.valueOf(strXhlc(this.m_FuleData.xhlc)) + strDw(this.m_FuleData.dw));
            updateItem(2, this.m_FuleData.ssyh, strSsyh(this.m_FuleData.ssyh));
            updateItem(5, this.m_FuleData.zdfw_ave, strSsyh(this.m_FuleData.zdfw_ave));
            updateItem(8, this.m_FuleData.sdfw_ave, strSsyh(this.m_FuleData.sdfw_ave));
        }
        CanJni.MitSubishiRzcGetSetData(this.m_SetData);
        if (!i2b(this.m_SetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.m_SetData.Update)) {
            this.m_SetData.Update = 0;
            updateItem(3, this.m_SetData.Pjyhfwms);
        }
    }

    public void QueryData() {
        CanJni.MitSubishiRzcQuery(64, 0);
        Sleep(5);
        CanJni.MitSubishiRzcQuery(80, 28);
    }

    private String strDw(int val) {
        if (val == 0 || val == 1) {
            return " Km";
        }
        if (val == 2 || val == 3) {
            return " Mile";
        }
        return " ";
    }

    private String strXhlc(int val) {
        if (val == 65535) {
            return "--";
        }
        return String.format("%d", new Object[]{Integer.valueOf(val)});
    }

    private String strSsyh(int val) {
        if (val == 65535) {
            return "--";
        }
        return String.format("%.1f", new Object[]{Double.valueOf(((double) val) * 0.1d)});
    }
}
