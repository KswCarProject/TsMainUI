package com.ts.can.chery.tiggo;

import android.os.Bundle;
import android.util.Log;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanBaseActivity;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanScrollList;

public class CanCheryTiggoCarInfoActivity extends CanBaseActivity implements UserCallBack, CanItemPopupList.onPopItemClick {
    public static final int ITEM_LANG = 1;
    private static final String TAG = "CanCheryTiggoCarInfoActivity";
    protected CanItemPopupList mItemLang;
    protected String[] mLangArr = {"中文", "English"};
    private CanScrollList mManager;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_can_comm_list);
        Log.d(TAG, "onCreate");
        InitUI();
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mManager = new CanScrollList(this);
        this.mItemLang = AddPopupItem(R.string.can_language, R.drawable.can_icon_setup, this.mLangArr, 1);
    }

    /* access modifiers changed from: protected */
    public CanItemPopupList AddPopupItem(int text, int icon, String[] val, int id) {
        CanItemPopupList item = new CanItemPopupList(this, text, icon, val, this);
        item.SetId(id);
        this.mManager.AddView(item.GetView());
        return item;
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
    }

    public void UserAll() {
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 1:
                CanJni.TiggoCarLangSet(item);
                return;
            default:
                return;
        }
    }
}
