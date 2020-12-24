package com.ts.can.chana.cs75;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;
import com.yyw.ts70xhw.FtSet;

public class CanCs75CameraSetView extends CanScrollCarInfoView {
    public static final int ITEM_RCAMERA = 0;
    public static final int ITEM_TURN_RIGHT = 1;
    private static int m_RCamerab = 0;
    private static int m_TurnCamerab = 0;

    public CanCs75CameraSetView(Activity activity) {
        super(activity, 2);
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                if (FtSet.Getlgb4() > 0) {
                    FtSet.Setlgb4(0);
                    return;
                } else {
                    FtSet.Setlgb4(1);
                    return;
                }
            case 1:
                if (FtSet.Getyw10() > 0) {
                    FtSet.Setyw10(0);
                    return;
                } else {
                    FtSet.Setyw10(1);
                    return;
                }
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_right_camera, R.string.can_rzxqhsxt};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH};
    }

    public void ResetData(boolean check) {
        if (m_RCamerab != (FtSet.Getlgb4() & 1) || !check) {
            m_RCamerab = FtSet.Getlgb4() & 1;
            updateItem(0, m_RCamerab);
        }
        if (m_TurnCamerab != (FtSet.Getyw10() & 1) || !check) {
            m_TurnCamerab = FtSet.Getyw10() & 1;
            updateItem(1, m_TurnCamerab);
        }
    }

    public void QueryData() {
        Log.d("yw", "CanCs75CameraSetView");
        ResetData(false);
    }
}
