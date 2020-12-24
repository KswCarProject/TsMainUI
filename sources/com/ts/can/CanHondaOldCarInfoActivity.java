package com.ts.can;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.ts.MainUI.R;
import com.ts.canview.CanItemTextBtnList;
import com.ts.canview.CanScrollList;

public class CanHondaOldCarInfoActivity extends CanBaseActivity implements View.OnClickListener {
    public static final int ITEM_COMPASS = 1;
    public static final String TAG = "CanHondaOldCarInfoActivity";
    protected CanItemTextBtnList mItemCompass;
    protected CanScrollList mManager;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        this.mManager = new CanScrollList(this);
        this.mItemCompass = new CanItemTextBtnList((Context) this, R.string.can_compass);
        this.mItemCompass.SetIdClickListener(this, 1);
        this.mManager.AddView(this.mItemCompass.GetView());
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                enterSubWin(CanHondaCompassActivity.class);
                return;
            default:
                return;
        }
    }
}
