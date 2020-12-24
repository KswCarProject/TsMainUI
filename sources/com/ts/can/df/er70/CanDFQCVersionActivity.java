package com.ts.can.df.er70;

import android.widget.TextView;
import com.lgb.canmodule.Can;
import com.ts.MainUI.R;

public class CanDFQCVersionActivity extends CanDFQCBaseActivity {
    private String mFactoryVersion;
    private String mHardVersion;
    private String mSoftVersion;
    private TextView mTvFactoryVersion;
    private TextView mTvHardVersion;
    private TextView mTvSoftVersion;

    /* access modifiers changed from: protected */
    public void InitLayout() {
        SetBackground(R.drawable.can_dfqc_banben_bg);
        this.mTvFactoryVersion = AddText(120, 187, Can.CAN_CC_HF_DJ, 25);
        this.mTvHardVersion = AddText(115, Can.CAN_TEANA_OLD_DJ, Can.CAN_CC_HF_DJ, 25);
        this.mTvSoftVersion = AddText(115, 270, Can.CAN_CC_HF_DJ, 25);
        initValue(true);
    }

    private void initValue(boolean isSet) {
        this.mFactoryVersion = String.valueOf(getString(R.string.can_dfqc_version_factory)) + "  ";
        this.mHardVersion = String.valueOf(getString(R.string.can_dfqc_version_hard)) + "  ";
        this.mSoftVersion = String.valueOf(getString(R.string.can_dfqc_version_soft)) + "  ";
        if (isSet) {
            this.mTvFactoryVersion.setText(this.mFactoryVersion);
            this.mTvHardVersion.setText(this.mHardVersion);
            this.mTvSoftVersion.setText(this.mSoftVersion);
        }
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        VenuciaGetBmsVersion();
        initValue(false);
        if (!i2b(this.mBmsVersion.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mBmsVersion.Update)) {
            this.mBmsVersion.Update = 0;
            int[] factoryV = this.mBmsVersion.BMS_SupplierManufacturersNo;
            int[] hV = this.mBmsVersion.BMS_HardVersion;
            int[] sV = this.mBmsVersion.BMS_SoftVersion;
            for (int hexString : factoryV) {
                String hexString2 = Integer.toHexString(hexString);
                if (hexString2.length() == 1) {
                    hexString2 = "0" + hexString2;
                }
                this.mFactoryVersion = String.valueOf(this.mFactoryVersion) + toASCIIString(hexString2);
            }
            for (int i = 0; i < hV.length; i++) {
                this.mHardVersion = String.valueOf(this.mHardVersion) + hV[i];
                if (i < hV.length - 1) {
                    this.mHardVersion = String.valueOf(this.mHardVersion) + ".";
                }
            }
            for (int i2 = 0; i2 < sV.length; i2++) {
                this.mSoftVersion = String.valueOf(this.mSoftVersion) + sV[i2];
                if (i2 < sV.length - 1) {
                    this.mSoftVersion = String.valueOf(this.mSoftVersion) + ".";
                }
            }
            this.mTvFactoryVersion.setText(this.mFactoryVersion);
            this.mTvHardVersion.setText(this.mHardVersion);
            this.mTvSoftVersion.setText(this.mSoftVersion);
        }
    }

    /* access modifiers changed from: protected */
    public void Query() {
    }

    private String toASCIIString(String s) {
        String s2;
        byte[] bytes = new byte[(s.length() / 2)];
        int i = 0;
        while (true) {
            try {
                s2 = s;
                if (i >= bytes.length) {
                    break;
                }
                bytes[i] = (byte) (Integer.parseInt(s2.substring(i * 2, (i * 2) + 2), 16) & 255);
                s = new String(bytes, "ASCII");
                i++;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return s2;
    }
}
