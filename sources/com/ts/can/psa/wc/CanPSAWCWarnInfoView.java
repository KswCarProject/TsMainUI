package com.ts.can.psa.wc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;
import com.ts.canview.CanItemTextBtnList;

public class CanPSAWCWarnInfoView extends CanScrollCarInfoView {
    private CanDataInfo.PsaWcWarnInfo mPsaWcWarnInfo = new CanDataInfo.PsaWcWarnInfo();
    private String[] mWarnArr;
    private int[] mWarnId;

    public CanPSAWCWarnInfoView(Activity activity) {
        super(activity, 1);
    }

    public void onClick(View arg0) {
    }

    public void ResetData(boolean check) {
        CanJni.PsaWcGetWarnInfo(this.mPsaWcWarnInfo);
        if (!i2b(this.mPsaWcWarnInfo.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mPsaWcWarnInfo.Update)) {
            this.mPsaWcWarnInfo.Update = 0;
            int i = 0;
            while (true) {
                if (i >= this.mWarnId.length) {
                    break;
                } else if (this.mPsaWcWarnInfo.Id == this.mWarnId[i]) {
                    ((CanItemTextBtnList) this.mItemObjects[0]).SetVal(this.mWarnArr[i]);
                    break;
                } else {
                    i++;
                }
            }
            if (i == this.mWarnId.length) {
                ((CanItemTextBtnList) this.mItemObjects[0]).SetVal(this.mWarnArr[this.mWarnArr.length - 1]);
            }
        }
    }

    public void QueryData() {
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_check_end};
        this.mWarnArr = getActivity().getResources().getStringArray(R.array.can_psa_wc_warn);
        int[] iArr = new int[77];
        iArr[1] = 1;
        iArr[2] = 3;
        iArr[3] = 4;
        iArr[4] = 5;
        iArr[5] = 8;
        iArr[6] = 10;
        iArr[7] = 11;
        iArr[8] = 13;
        iArr[9] = 15;
        iArr[10] = 17;
        iArr[11] = 18;
        iArr[12] = 19;
        iArr[13] = 20;
        iArr[14] = 97;
        iArr[15] = 98;
        iArr[16] = 100;
        iArr[17] = 103;
        iArr[18] = 104;
        iArr[19] = 105;
        iArr[20] = 106;
        iArr[21] = 107;
        iArr[22] = 108;
        iArr[23] = 109;
        iArr[24] = 110;
        iArr[25] = 111;
        iArr[26] = 129;
        iArr[27] = 131;
        iArr[28] = 136;
        iArr[29] = 137;
        iArr[30] = 138;
        iArr[31] = 141;
        iArr[32] = 154;
        iArr[33] = 155;
        iArr[34] = 156;
        iArr[35] = 157;
        iArr[36] = 158;
        iArr[37] = 159;
        iArr[38] = 160;
        iArr[39] = 215;
        iArr[40] = 216;
        iArr[41] = 217;
        iArr[42] = 222;
        iArr[43] = 223;
        iArr[44] = 224;
        iArr[45] = 225;
        iArr[46] = 227;
        iArr[47] = 229;
        iArr[48] = 231;
        iArr[49] = 232;
        iArr[50] = 240;
        iArr[51] = 241;
        iArr[52] = 247;
        iArr[53] = 248;
        iArr[54] = 249;
        iArr[55] = 303;
        iArr[56] = 304;
        iArr[57] = 305;
        iArr[58] = 306;
        iArr[59] = 307;
        iArr[60] = 308;
        iArr[61] = 506;
        iArr[62] = 507;
        iArr[63] = 508;
        iArr[64] = 509;
        iArr[65] = 510;
        iArr[66] = 511;
        iArr[67] = 512;
        iArr[68] = 514;
        iArr[69] = 515;
        iArr[70] = 516;
        iArr[71] = 517;
        iArr[72] = 518;
        iArr[73] = 521;
        iArr[74] = 522;
        iArr[75] = 523;
        iArr[76] = 32767;
        this.mWarnId = iArr;
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.TITLE};
    }
}
