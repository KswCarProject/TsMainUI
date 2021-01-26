package com.ts.can.chana.cs75;

import android.content.Context;
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
import com.ts.canview.CanItemProgressList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanScrollList;

public class CanCs75CarAirSetActivity extends CanBaseActivity implements View.OnClickListener, UserCallBack, CanItemPopupList.onPopItemClick, CanItemProgressList.onPosChange {
    public static final int ITEM_JSJCTF = 2;
    public static final int ITEM_JSZDHQ = 1;
    public static final int ITEM_KQJHXTZDKQ = 4;
    public static final int ITEM_KQYTJ = 6;
    public static final int ITEM_KQYTJ_SW = 7;
    public static final int ITEM_KQYTJ_WS = 9;
    public static final int ITEM_KQYTJ_XW = 8;
    public static final int ITEM_KTZGZ = 3;
    public static final int ITEM_LYTHJFS = 5;
    private static final int ITEM_MAX = 9;
    private static final int ITEM_MIN = 1;
    public static final String TAG = "CanCs75CarAirSetActivity";
    private static final int[] mKqytjArr = {R.string.can_mzd_cx4_mode_off, R.string.can_default, R.string.can_individual};
    protected CanItemSwitchList mItemJsjctf;
    protected CanItemSwitchList mItemJszdhq;
    protected CanItemSwitchList mItemKqjhxtzdkq;
    protected CanItemPopupList mItemKqytj;
    protected CanItemProgressList mItemKqytjSw;
    protected CanItemProgressList mItemKqytjWs;
    protected CanItemProgressList mItemKqytjXw;
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
    public String Kqzdy(int time, int val) {
        switch (time) {
            case 0:
                if (val == 1) {
                    return "06:30";
                }
                if (val == 2) {
                    return "07:00";
                }
                if (val == 3) {
                    return "07:30";
                }
                if (val == 4) {
                    return "08:00";
                }
                if (val == 5) {
                    return "08:30";
                }
                if (val == 6) {
                    return "09:00";
                }
                if (val == 7) {
                    return "09:30";
                }
                if (val == 8) {
                    return "10:00";
                }
                if (val == 9) {
                    return "10:30";
                }
                if (val == 10) {
                    return "11:00";
                }
                if (val == 11) {
                    return "11:30";
                }
                if (val == 12) {
                    return "12:00";
                }
                return " ";
            case 1:
                if (val == 1) {
                    return "12:30";
                }
                if (val == 2) {
                    return "13:00";
                }
                if (val == 3) {
                    return "13:30";
                }
                if (val == 4) {
                    return "14:00";
                }
                if (val == 5) {
                    return "14:30";
                }
                if (val == 6) {
                    return "15:00";
                }
                if (val == 7) {
                    return "15:30";
                }
                if (val == 8) {
                    return "16:00";
                }
                if (val == 9) {
                    return "16:30";
                }
                if (val == 10) {
                    return "17:00";
                }
                if (val == 11) {
                    return "17:30";
                }
                if (val == 12) {
                    return "18:00";
                }
                if (val == 13) {
                    return "18:30";
                }
                return " ";
            case 2:
                if (val == 1) {
                    return "19:00";
                }
                if (val == 2) {
                    return "19:30";
                }
                if (val == 3) {
                    return "20:00";
                }
                if (val == 4) {
                    return "20:30";
                }
                if (val == 5) {
                    return "21:00";
                }
                if (val == 6) {
                    return "21:30";
                }
                if (val == 7) {
                    return "22:00";
                }
                if (val == 8) {
                    return "22:30";
                }
                return " ";
            default:
                return " ";
        }
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
        if (i2b(this.mSetData.LythjfsUpdateOnce) && (!check || i2b(this.mSetData.LythjfsUpdate))) {
            this.mSetData.LythjfsUpdate = 0;
            this.mItemLythjfs.SetCheck(SwSet(this.mSetData.Lythjfs));
        }
        if (i2b(this.mSetData.KqytjUpdateOnce) && (!check || i2b(this.mSetData.KqytjUpdate))) {
            this.mSetData.KqytjUpdate = 0;
            this.mItemKqytj.SetSel(this.mSetData.Kqytj - 1);
            if (this.mSetData.Kqytj == 3) {
                this.mItemKqytjSw.ShowGone(true);
                this.mItemKqytjXw.ShowGone(true);
                this.mItemKqytjWs.ShowGone(true);
            } else {
                this.mItemKqytjSw.ShowGone(false);
                this.mItemKqytjXw.ShowGone(false);
                this.mItemKqytjWs.ShowGone(false);
            }
        }
        if (i2b(this.mSetData.KqytjSwUpdateOnce) && (!check || i2b(this.mSetData.KqytjSwUpdate))) {
            this.mSetData.KqytjSwUpdate = 0;
            this.mItemKqytjSw.SetCurVal(this.mSetData.KqytjSw);
            this.mItemKqytjSw.SetValText(Kqzdy(0, this.mSetData.KqytjSw));
        }
        if (i2b(this.mSetData.KqytjXwUpdateOnce) && (!check || i2b(this.mSetData.KqytjXwUpdate))) {
            this.mSetData.KqytjXwUpdate = 0;
            this.mItemKqytjXw.SetCurVal(this.mSetData.KqytjXw);
            this.mItemKqytjXw.SetValText(Kqzdy(1, this.mSetData.KqytjXw));
        }
        if (!i2b(this.mSetData.KqytjWsUpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.KqytjWsUpdate)) {
            this.mSetData.KqytjWsUpdate = 0;
            this.mItemKqytjWs.SetCurVal(this.mSetData.KqytjWs);
            this.mItemKqytjWs.SetValText(Kqzdy(2, this.mSetData.KqytjWs));
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
        this.mItemKqytj = AddPopupItem(R.string.can_kqytj, mKqytjArr, 6);
        this.mItemKqytjSw = this.mManager.addItemProgressList(R.string.can_morning, 7, (CanItemProgressList.onPosChange) this);
        this.mItemKqytjSw.SetStep(1);
        this.mItemKqytjSw.SetMinMax(1, 12);
        this.mItemKqytjSw.ShowGone(false);
        this.mItemKqytjXw = this.mManager.addItemProgressList(R.string.can_afternoon, 8, (CanItemProgressList.onPosChange) this);
        this.mItemKqytjXw.SetStep(1);
        this.mItemKqytjXw.SetMinMax(1, 13);
        this.mItemKqytjXw.ShowGone(false);
        this.mItemKqytjWs = this.mManager.addItemProgressList(R.string.can_at_night, 9, (CanItemProgressList.onPosChange) this);
        this.mItemKqytjWs.SetStep(1);
        this.mItemKqytjWs.SetMinMax(1, 8);
        this.mItemKqytjWs.ShowGone(false);
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        for (int i = 1; i <= 9; i++) {
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
            case 6:
                if (CanJni.GetSubType() == 17) {
                    ret = 1;
                    break;
                }
                break;
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
            case 6:
                this.mItemKqytj.ShowGone(show);
                return;
            case 7:
                this.mItemKqytjSw.ShowGone(show);
                return;
            case 8:
                this.mItemKqytjXw.ShowGone(show);
                return;
            case 9:
                this.mItemKqytjWs.ShowGone(show);
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

    /* access modifiers changed from: protected */
    public CanItemPopupList AddPopupItem(int resId, int[] arry, int Id) {
        CanItemPopupList item = new CanItemPopupList((Context) this, resId, arry, (CanItemPopupList.onPopItemClick) this);
        item.SetId(Id);
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
        switch (id) {
            case 6:
                CanJni.Cs75CarSet(67, item + 1);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
        switch (id) {
            case 7:
                CanJni.Cs75CarSet(68, pos);
                return;
            case 8:
                CanJni.Cs75CarSet(69, pos);
                return;
            case 9:
                CanJni.Cs75CarSet(70, pos);
                return;
            default:
                return;
        }
    }
}
