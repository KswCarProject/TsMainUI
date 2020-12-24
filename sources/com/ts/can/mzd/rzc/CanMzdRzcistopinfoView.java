package com.ts.can.mzd.rzc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;
import com.ts.canview.CanItemMsgBox;

public class CanMzdRzcistopinfoView extends CanScrollCarInfoView {
    private static final int ISTOP_BFB = 10;
    private static final int ISTOP_FDJJXZT = 4;
    private static final int ISTOP_GNYX = 1;
    private static final int ISTOP_GNZBZT = 2;
    private static final int ISTOP_KTJXZT = 5;
    private static final int ISTOP_ONSJ = 8;
    private static final int ISTOP_PJ = 13;
    private static final int ISTOP_RESET = 14;
    private static final int ISTOP_TSZF = 7;
    private static final int ISTOP_TZSJ = 9;
    private static final int ISTOP_XDCJXZT = 3;
    private static final int ISTOP_ZJDZXC = 11;
    private static final int ISTOP_ZSJ = 12;
    private CanDataInfo.Mzd_Rzc_IStop mIStopInfo;

    public CanMzdRzcistopinfoView(Activity activity) {
        super(activity, 15);
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        int id = ((Integer) v.getTag()).intValue();
        if (id == 14) {
            new CanItemMsgBox(id, getActivity(), R.string.can_sure_reset, new CanItemMsgBox.onMsgBoxClick() {
                public void onOK(int param) {
                    CanJni.MzdCx4CarSet(21, 0);
                }
            }, (CanItemMsgBox.onMsgBoxClick2) null);
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_istop_zt, R.string.can_istop_gnyx, R.string.can_istop_gnzbzt, R.string.can_istop_xdcjxzt, R.string.can_istop_fdjjxzt, R.string.can_istop_ktjxzt, R.string.can_istop_info, R.string.can_istop_tszf, R.string.can_istop_onsj, R.string.can_istop_tzsj, R.string.can_istop_bfb, R.string.can_istop_zjdzxc, R.string.can_istop_zsj, R.string.can_istop_pj, R.string.can_reset};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.TEXT, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.TEXT, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.TITLE};
        this.mIStopInfo = new CanDataInfo.Mzd_Rzc_IStop();
    }

    public void ResetData(boolean check) {
        CanJni.MzdRzcGetCarIStopData(this.mIStopInfo);
        if (!i2b(this.mIStopInfo.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mIStopInfo.Update)) {
            this.mIStopInfo.Update = 0;
            updateItem(1, Neg(this.mIStopInfo.Gnyx));
            updateItem(2, this.mIStopInfo.Gnzbzt);
            updateItem(3, this.mIStopInfo.Xdcjxzt);
            updateItem(4, this.mIStopInfo.Fdjjxzt);
            updateItem(5, this.mIStopInfo.Ktjxzt);
            updateItem(7, this.mIStopInfo.Tszf, getStringArray(R.array.can_istop_tszf)[this.mIStopInfo.Tszf]);
            if (this.mIStopInfo.BcjsZsj == 0) {
                updateItem(8, this.mIStopInfo.BcjsZsj, "--");
            } else {
                updateItem(8, this.mIStopInfo.BcjsZsj, String.format("%02dm%02ds", new Object[]{Integer.valueOf(this.mIStopInfo.BcjsZsj / 60), Integer.valueOf(this.mIStopInfo.BcjsZsj % 60)}));
            }
            if (this.mIStopInfo.BcjsTzsj == 0) {
                updateItem(9, this.mIStopInfo.BcjsTzsj, "--");
            } else {
                updateItem(9, this.mIStopInfo.BcjsTzsj, String.format("%02dm%02ds", new Object[]{Integer.valueOf(this.mIStopInfo.BcjsTzsj / 60), Integer.valueOf(this.mIStopInfo.BcjsTzsj % 60)}));
            }
            updateItem(10, this.mIStopInfo.BcjsBfb, String.format("%d %%", new Object[]{Integer.valueOf(this.mIStopInfo.BcjsBfb)}));
            updateItem(11, this.mIStopInfo.Zjdzxc, String.format("%.1f KM", new Object[]{Double.valueOf(((double) this.mIStopInfo.Zjdzxc) * 0.1d)}));
            updateItem(12, this.mIStopInfo.Zsj, String.format("%02dh%02dm", new Object[]{Integer.valueOf(this.mIStopInfo.Zsj / 60), Integer.valueOf(this.mIStopInfo.Zsj % 60)}));
            if (this.mIStopInfo.Pj == 0) {
                updateItem(13, this.mIStopInfo.Pj, "--");
                return;
            }
            updateItem(13, this.mIStopInfo.Pj, String.format("%.1f L/100KM", new Object[]{Double.valueOf(((double) this.mIStopInfo.Pj) * 0.1d)}));
        }
    }

    public void QueryData() {
        CanJni.MzdCx4Query(82, 0);
    }
}
