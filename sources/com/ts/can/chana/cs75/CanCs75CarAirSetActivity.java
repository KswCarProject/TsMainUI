package com.ts.can.chana.cs75;

import android.os.Bundle;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanBaseActivity;
import com.ts.canview.CanItemIcoList;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanScrollList;

public class CanCs75CarAirSetActivity extends CanBaseActivity implements View.OnClickListener, UserCallBack, CanItemPopupList.onPopItemClick {
    public static final int ITEM_JSJCTF = 2;
    public static final int ITEM_JSZDHQ = 1;
    public static final int ITEM_KQJHXTZDKQ = 4;
    public static final int ITEM_KTZGZ = 3;
    public static final int ITEM_LYTHJFS = 5;
    private static final int ITEM_MAX = 5;
    private static final int ITEM_MIN = 1;
    public static final String TAG = "CanCs75CarAirSetActivity";
    protected CanItemSwitchList mItemJsjctf;
    protected CanItemSwitchList mItemJszdhq;
    protected CanItemSwitchList mItemKqjhxtzdkq;
    protected CanItemSwitchList mItemKtzgz;
    protected CanItemSwitchList mItemLythjfs;
    private CanScrollList mManager;
    protected CanDataInfo.CS75CarInfo mSetData = new CanDataInfo.CS75CarInfo();
    private boolean mbLayout;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        InitUI();
    }

    /* access modifiers changed from: protected */
    public int SwSet(int val) {
        if (1 == val) {
            return 1;
        }
        return 0;
    }

    /* access modifiers changed from: protected */
    public int NegSwSet(int val) {
        if (1 == val) {
            return 2;
        }
        return 1;
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        CanJni.Cs75GetCarInfo(this.mSetData);
        if (i2b(this.mSetData.JszdhqUpdateOnce) && (!check || i2b(this.mSetData.JszdhqUpdate))) {
            this.mSetData.JszdhqUpdate = 0;
            this.mItemJszdhq.SetCheck(SwSet(this.mSetData.Jszdhq));
        }
        if (i2b(this.mSetData.JsjctfUpdateOnce) && (!check || i2b(this.mSetData.JsjctfUpdate))) {
            this.mSetData.JsjctfUpdate = 0;
            this.mItemJsjctf.SetCheck(SwSet(this.mSetData.Jsjctf));
        }
        if (i2b(this.mSetData.KtzgzUpdateOnce) && (!check || i2b(this.mSetData.KtzgzUpdate))) {
            this.mSetData.KtzgzUpdate = 0;
            this.mItemKtzgz.SetCheck(SwSet(this.mSetData.Ktzgz));
        }
        if (i2b(this.mSetData.KqjhxtzdkqUpdateOnce) && (!check || i2b(this.mSetData.KqjhxtzdkqUpdate))) {
            this.mSetData.KqjhxtzdkqUpdate = 0;
            this.mItemKqjhxtzdkq.SetCheck(SwSet(this.mSetData.Kqjhxtzdkq));
        }
        if (!i2b(this.mSetData.LythjfsUpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.LythjfsUpdate)) {
            this.mSetData.LythjfsUpdate = 0;
            this.mItemLythjfs.SetCheck(SwSet(this.mSetData.Lythjfs));
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
        CanJni.Cs75CarQuery(82, 7);
        Sleep(10);
        CanJni.Cs75CarQuery(82, 8);
        Sleep(10);
        CanJni.Cs75CarQuery(82, 9);
        Sleep(10);
        CanJni.Cs75CarQuery(82, 17);
        Sleep(10);
        CanJni.Cs75CarQuery(82, 23);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        LayoutUI();
        ResetData(false);
        QueryData();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mManager = new CanScrollList(this);
        this.mItemJszdhq = AddCheckItem(R.string.can_jszdhq, 1);
        this.mItemJsjctf = AddCheckItem(R.string.can_jsjctf, 2);
        this.mItemKtzgz = AddCheckItem(R.string.can_ktzgz, 3);
        this.mItemKqjhxtzdkq = AddCheckItem(R.string.can_kqjhxtzdkq, 4);
        this.mItemLythjfs = AddCheckItem(R.string.can_lythjfs, 5);
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        for (int i = 1; i <= 5; i++) {
            ShowItem(i);
        }
    }

    /* access modifiers changed from: protected */
    public boolean IsHaveItem(int item) {
        int ret = 0;
        switch (item) {
            case 1:
                ret = 1;
                break;
            case 2:
                ret = 1;
                break;
            case 3:
                ret = 1;
                break;
            case 4:
                if (CanJni.GetSubType() != 11 && CanJni.GetSubType() != 12) {
                    ret = 1;
                    break;
                } else {
                    ret = 0;
                    break;
                }
                break;
            case 5:
                if (CanJni.GetSubType() != 12) {
                    ret = 1;
                    break;
                } else {
                    ret = 0;
                    break;
                }
        }
        return i2b(ret);
    }

    /* access modifiers changed from: protected */
    public void ShowItem(int item) {
        boolean show = IsHaveItem(item);
        switch (item) {
            case 1:
                this.mItemJszdhq.ShowGone(show);
                return;
            case 2:
                this.mItemJsjctf.ShowGone(show);
                return;
            case 3:
                this.mItemKtzgz.ShowGone(show);
                return;
            case 4:
                this.mItemKqjhxtzdkq.ShowGone(show);
                return;
            case 5:
                this.mItemLythjfs.ShowGone(show);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public CanItemSwitchList AddCheckItem(int resId, int Id) {
        CanItemSwitchList item = new CanItemSwitchList(this, resId);
        item.SetIdClickListener(this, Id);
        this.mManager.AddView(item.GetView());
        item.ShowGone(false);
        return item;
    }

    /* access modifiers changed from: protected */
    public CanItemIcoList AddIcoItem(int ico, int text, int id) {
        CanItemIcoList item = new CanItemIcoList(this, ico, text);
        item.SetIdClickListener(this, id);
        this.mManager.AddView(item.GetView());
        item.ShowGone(false);
        return item;
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                CanJni.Cs75CarSet(7, NegSwSet(this.mSetData.Jszdhq));
                return;
            case 2:
                CanJni.Cs75CarSet(8, NegSwSet(this.mSetData.Jsjctf));
                return;
            case 3:
                CanJni.Cs75CarSet(9, NegSwSet(this.mSetData.Ktzgz));
                return;
            case 4:
                CanJni.Cs75CarSet(17, NegSwSet(this.mSetData.Kqjhxtzdkq));
                return;
            case 5:
                CanJni.Cs75CarSet(23, NegSwSet(this.mSetData.Lythjfs));
                return;
            default:
                return;
        }
    }

    public void UserAll() {
        ResetData(true);
    }

    public void onItem(int id, int item) {
    }
}
