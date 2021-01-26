package com.ts.can.chana.wc.cos;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;
import com.ts.canview.CanItemMsgBox;

public class CanChanACosDvrSetView extends CanScrollCarInfoView implements CanItemMsgBox.onMsgBoxClick {
    private static final int ITEM_CCKGSH = 4;
    private static final int ITEM_DVR_LXXHSJ = 2;
    private static final int ITEM_DVR_SPLY = 1;
    private static final int ITEM_DVR_SPLZ = 0;
    private static final int ITEM_PZ = 3;
    private CanDataInfo.Cos1WcRecordSet mSetData;

    public CanChanACosDvrSetView(Activity activity) {
        super(activity, 5);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 2:
                CanJni.ChanAWcCos1RecordSet(3, item);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                CanJni.ChanAWcCos1RecordSet(1, Neg(this.mSetData.Splz));
                return;
            case 1:
                CanJni.ChanAWcCos1RecordSet(2, Neg(this.mSetData.Sply));
                return;
            case 3:
                CanJni.ChanAWcCos1RecordSet(4, 1);
                Sleep(20);
                CanJni.ChanAWcCos1RecordSet(4, 0);
                return;
            case 4:
                new CanItemMsgBox(4, getActivity(), R.string.can_cmp_reset_notice, this);
                return;
            default:
                return;
        }
    }

    public void onOK(int param) {
        switch (param) {
            case 4:
                CanJni.ChanAWcCos1RecordSet(5, 1);
                Sleep(20);
                CanJni.ChanAWcCos1RecordSet(5, 0);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_vedio_recording, R.string.can_sound_recording, R.string.can_video_cycle_time, R.string.can_take_a_picture, R.string.can_memory_card_format};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE};
        this.mPopValueIds[2] = new int[]{R.string.can_1min, R.string.can_3min, R.string.can_5min};
        this.mSetData = new CanDataInfo.Cos1WcRecordSet();
    }

    public void ResetData(boolean check) {
        CanJni.ChanAWcCos1GetRecordSet(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            updateItem(0, this.mSetData.Splz);
            updateItem(1, this.mSetData.Sply);
            updateItem(2, this.mSetData.Lxxhsj);
        }
    }

    public void QueryData() {
        CanJni.ChanAWcCos1Query(5, 1, Can.CAN_SGMW_WC);
    }
}
