package com.ts.can.porsche.od;

import android.app.Activity;
import android.support.v4.internal.view.SupportMenu;
import android.support.v4.view.ViewCompat;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanPorscheOdStatueView extends CanScrollCarInfoView {
    private CanDataInfo.PorscheCarSet mData;

    public CanPorscheOdStatueView(Activity activity) {
        super(activity, 5);
    }

    public void onItem(int id, int item) {
        switch (id) {
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_eps, R.string.can_trav_time, R.string.can_driving_mileage, R.string.can_consumption, R.string.can_avg_spped};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE};
        this.mData = new CanDataInfo.PorscheCarSet();
    }

    /* access modifiers changed from: package-private */
    public String Byte2String(int item, int data) {
        String str = "";
        switch (data) {
            case SupportMenu.USER_MASK:
            case ViewCompat.MEASURED_SIZE_MASK:
                return "--";
            default:
                switch (item) {
                    case 0:
                        str = String.format("%d °", new Object[]{Integer.valueOf(data)});
                        break;
                    case 1:
                        str = String.format("%d h", new Object[]{Integer.valueOf(data)});
                        break;
                    case 2:
                        str = String.format("%d km", new Object[]{Integer.valueOf(data)});
                        break;
                    case 3:
                        str = String.format("%.1f l/100km", new Object[]{Double.valueOf(((double) data) * 0.1d)});
                        break;
                    case 4:
                        str = String.format("%d km", new Object[]{Integer.valueOf(data)});
                        break;
                }
                return str;
        }
    }

    public void ResetData(boolean check) {
        CanJni.PorscheOdGetCarSet(this.mData);
        if (i2b(this.mData.AngleUpdateOnce) && (!check || i2b(this.mData.AngleUpdate))) {
            this.mData.AngleUpdate = 0;
            int temp = this.mData.Angle;
            if (this.mData.AngleSta > 0) {
                temp = -this.mData.Angle;
            }
            updateItem(0, this.mData.Angle, Byte2String(0, temp));
        }
        if (i2b(this.mData.XssjUpdateOnce) && (!check || i2b(this.mData.XssjUpdate))) {
            this.mData.XssjUpdate = 0;
            updateItem(1, this.mData.Xsss, Byte2String(1, this.mData.Xsss));
        }
        if (i2b(this.mData.DistanceUpdateOnce) && (!check || i2b(this.mData.DistanceUpdate))) {
            this.mData.DistanceUpdate = 0;
            updateItem(2, this.mData.Distance, Byte2String(2, this.mData.Distance));
        }
        if (i2b(this.mData.ZyhUpdateOnce) && (!check || i2b(this.mData.ZyhUpdate))) {
            this.mData.ZyhUpdate = 0;
            updateItem(3, this.mData.Zyh, Byte2String(3, this.mData.Zyh));
        }
        if (!i2b(this.mData.LxpjsdUpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mData.LxpjsdUpdate)) {
            this.mData.LxpjsdUpdate = 0;
            updateItem(4, this.mData.Lxpjsd, Byte2String(4, this.mData.Lxpjsd));
        }
    }

    public void QueryData() {
        CanJni.PorscheOdQuery(64, 0);
        Sleep(5);
        CanJni.PorscheOdQuery(64, 1);
        Sleep(5);
        CanJni.PorscheOdQuery(64, 2);
        Sleep(5);
        CanJni.PorscheOdQuery(64, 3);
        Sleep(5);
        CanJni.PorscheOdQuery(64, 4);
    }
}
