package com.ts.can.vw.golf.wc;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanBaseActivity;
import com.ts.canview.CanItemIcoList;
import com.ts.canview.CanItemMsgBox;
import com.ts.canview.CanScrollList;

public class CanGolfWcSetFactoryActivity extends CanBaseActivity implements View.OnClickListener, CanItemMsgBox.onMsgBoxClick {
    public static final int ITEM_ALL_SET = 12;
    public static final int ITEM_DRIVE_ASS = 3;
    public static final int ITEM_LIGHT = 5;
    public static final int ITEM_MFD = 8;
    public static final int ITEM_MW = 6;
    public static final int ITEM_OC = 7;
    public static final int ITEM_PM = 4;
    protected View mCurItem;
    protected CanItemIcoList mItemAllSetup;
    protected CanItemIcoList mItemDriveAss;
    protected CanItemIcoList mItemLight;
    protected CanItemIcoList mItemMFD;
    protected CanItemIcoList mItemMW;
    protected CanItemIcoList mItemOC;
    protected CanItemIcoList mItemPM;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        this.mItemAllSetup = new CanItemIcoList(this, R.drawable.can_golf_icon14, R.string.can_all_settings);
        this.mItemDriveAss = new CanItemIcoList(this, R.drawable.can_golf_icon02, R.string.can_drivet_assist);
        this.mItemPM = new CanItemIcoList(this, R.drawable.can_golf_icon04, R.string.can_park_and_m);
        this.mItemLight = new CanItemIcoList(this, R.drawable.can_golf_icon05, R.string.can_light);
        this.mItemMW = new CanItemIcoList(this, R.drawable.can_golf_icon06, R.string.can_mirr_and_wiper);
        this.mItemOC = new CanItemIcoList(this, R.drawable.can_golf_icon07, R.string.can_open_and_close);
        this.mItemMFD = new CanItemIcoList(this, R.drawable.can_golf_icon08, R.string.can_mfd);
        this.mItemAllSetup.SetIdClickListener(this, 12);
        this.mItemDriveAss.SetIdClickListener(this, 3);
        this.mItemPM.SetIdClickListener(this, 4);
        this.mItemLight.SetIdClickListener(this, 5);
        this.mItemMW.SetIdClickListener(this, 6);
        this.mItemOC.SetIdClickListener(this, 7);
        this.mItemMFD.SetIdClickListener(this, 8);
        CanScrollList sl = new CanScrollList(this);
        sl.AddView(this.mItemAllSetup.GetView());
        sl.AddView(this.mItemDriveAss.GetView());
        sl.AddView(this.mItemPM.GetView());
        sl.AddView(this.mItemLight.GetView());
        sl.AddView(this.mItemMW.GetView());
        sl.AddView(this.mItemOC.GetView());
        sl.AddView(this.mItemMFD.GetView());
    }

    public void onClick(View v) {
        CanItemMsgBox msgbox = new CanItemMsgBox(((Integer) v.getTag()).intValue(), this, R.string.can_sure_setup, this);
        v.setSelected(true);
        this.mCurItem = v;
        msgbox.getDlg().setOnDismissListener(new DialogInterface.OnDismissListener() {
            public void onDismiss(DialogInterface arg0) {
                if (CanGolfWcSetFactoryActivity.this.mCurItem != null) {
                    CanGolfWcSetFactoryActivity.this.mCurItem.setSelected(false);
                }
            }
        });
    }

    public void onOK(int param) {
        switch (param) {
            case 3:
                CanJni.GolfWcSetDefault(64);
                return;
            case 4:
                CanJni.GolfWcSetDefault(32);
                return;
            case 5:
                CanJni.GolfWcSetDefault(16);
                return;
            case 6:
                CanJni.GolfWcSetDefault(8);
                return;
            case 7:
                CanJni.GolfWcSetDefault(4);
                return;
            case 8:
                CanJni.GolfWcSetDefault(2);
                return;
            case 12:
                CanJni.GolfWcSetDefault(128);
                return;
            default:
                return;
        }
    }
}
