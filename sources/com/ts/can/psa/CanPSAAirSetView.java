package com.ts.can.psa;

import android.app.Activity;
import android.view.View;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;
import com.yyw.ts70xhw.FtSet;

public class CanPSAAirSetView extends CanScrollCarInfoView {
    public static final int ITEM_MAX = 1;
    public static final int ITEM_TEMP_ADJ = 0;
    public static int nTempOld = 255;

    public CanPSAAirSetView(Activity activity) {
        super(activity, 1);
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
        switch (id) {
            case 0:
                FtSet.Setyw7((FtSet.Getyw7() & 65520) | pos);
                return;
            default:
                return;
        }
    }

    public void onClick(View v) {
        ((Integer) v.getTag()).intValue();
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_cnwdpyl};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.PROGRESS};
        int[][] iArr = this.mProgressAttrs;
        int[] iArr2 = new int[4];
        iArr2[1] = 2;
        iArr2[2] = 2;
        iArr[0] = iArr2;
    }

    public void ResetData(boolean check) {
        if (nTempOld != (FtSet.Getyw7() & 15)) {
            nTempOld = FtSet.Getyw7() & 15;
            if (nTempOld > 0) {
                updateItem(0, nTempOld, "-" + nTempOld);
            } else {
                updateItem(0, nTempOld, " " + nTempOld);
            }
        }
    }

    public void QueryData() {
    }
}
