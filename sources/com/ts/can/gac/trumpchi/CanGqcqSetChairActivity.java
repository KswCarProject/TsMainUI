package com.ts.can.gac.trumpchi;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import com.lgb.canmodule.Can;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanCameraUI;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemProgressList;
import com.ts.other.CustomImgView;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;

public class CanGqcqSetChairActivity extends CanGqcqBaseActivity implements UserCallBack, View.OnClickListener, CanItemProgressList.onPosChange, CanItemPopupList.onPopItemClick, View.OnTouchListener {
    public static final int ITEM_FJSZY_BTN = 4;
    public static final int ITEM_FJSZY_ZDJR = 2;
    public static final int ITEM_JSZY_BTN = 3;
    public static final int ITEM_JSZY_ZDJR = 1;
    private static final int ITEM_MAX = 6;
    private static final int ITEM_MIN = 1;
    public static final int ITEM_ZNYS = 6;
    public static final int ITEM_ZYYB = 5;
    public static final String TAG = "CanGqcqSetChairActivity";
    private ParamButton mBtnLtHot;
    private ParamButton mBtnRtHot;
    protected int mCurLine = 0;
    private CanItemPopupList mItemFjszyjr;
    private CanItemPopupList mItemJszyjr;
    private CanItemPopupList mItemZnys;
    private CanItemPopupList mItemZyyb;
    private CustomImgView[] mLtHotVal = new CustomImgView[3];
    protected RelativeLayoutManager mManager;
    private CustomImgView[] mRtHotVal = new CustomImgView[3];

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_can_comm_relative);
        this.mManager = new RelativeLayoutManager(this, R.id.can_comm_layout);
        this.mItemJszyjr = AddPopupItem(R.string.can_jszyzdjr, this.mSWArr, 1);
        this.mItemFjszyjr = AddPopupItem(R.string.can_fjszyzdjr, this.mSWArr, 2);
        this.mItemZyyb = AddPopupItem(R.string.can_chair_yinbing, this.mSWArr, 5);
        this.mItemZnys = AddPopupItem(R.string.can_chair_smartkey, this.mSWArr, 6);
        this.mBtnLtHot = AddImgBtn(3, Can.CAN_MZD_LUOMU, CanCameraUI.BTN_TRUMPCHI_GS4_MODE1, R.drawable.can_chairhot_lt_up, R.drawable.can_chairhot_lt_dn);
        this.mBtnRtHot = AddImgBtn(4, CanCameraUI.BTN_CCH9_MODE3, CanCameraUI.BTN_TRUMPCHI_GS4_MODE1, R.drawable.can_chairhot_rt_up, R.drawable.can_chairhot_rt_dn);
        for (int i = 0; i < 3; i++) {
            this.mLtHotVal[i] = this.mManager.AddImage((i * 40) + 269, 485);
            this.mLtHotVal[i].setStateDrawable(R.drawable.conditioning_rect_up, R.drawable.conditioning_rect_dn);
            this.mRtHotVal[i] = this.mManager.AddImage((i * 40) + 639, 485);
            this.mRtHotVal[i].setStateDrawable(R.drawable.conditioning_rect_up, R.drawable.conditioning_rect_dn);
        }
        LayoutUI();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
        Log.d(TAG, "-----onResume-----");
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
    }

    private void ResetData(boolean check) {
        GetSetData();
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            this.mItemJszyjr.SetSel(this.mSetData.Jszyzdjr - 1);
            this.mItemFjszyjr.SetSel(this.mSetData.Fjszyzdjr - 1);
            this.mItemZyyb.SetSel(this.mSetData.Zyyb - 1);
            this.mItemZnys.SetSel(this.mSetData.Znys - 1);
            int lthot = this.mSetData.Jswd & 3;
            int rthot = this.mSetData.Fjswd & 3;
            for (int i = 0; i < lthot; i++) {
                this.mLtHotVal[i].setSelected(true);
            }
            for (int i2 = lthot; i2 < 3; i2++) {
                this.mLtHotVal[i2].setSelected(false);
            }
            for (int i3 = 0; i3 < rthot; i3++) {
                this.mRtHotVal[i3].setSelected(true);
            }
            for (int i4 = rthot; i4 < 3; i4++) {
                this.mRtHotVal[i4].setSelected(false);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        for (int i = 1; i <= 6; i++) {
            ShowItem(i);
        }
    }

    /* access modifiers changed from: protected */
    public boolean IsHaveItem(int item) {
        return i2b(1);
    }

    /* access modifiers changed from: protected */
    public void ShowItem(int item) {
        boolean show = IsHaveItem(item);
        switch (item) {
            case 1:
                this.mItemJszyjr.ShowGone(show);
                return;
            case 2:
                this.mItemFjszyjr.ShowGone(show);
                return;
            case 5:
                this.mItemZyyb.ShowGone(show);
                return;
            case 6:
                this.mItemZnys.ShowGone(show);
                return;
            default:
                return;
        }
    }

    public void onClick(View v) {
        int intValue = ((Integer) v.getTag()).intValue();
    }

    public void UserAll() {
        ResetData(true);
    }

    /* access modifiers changed from: protected */
    public CanItemPopupList AddPopupItem(int text, int[] val, int id) {
        CanItemPopupList item = new CanItemPopupList((Context) this, text, val, (CanItemPopupList.onPopItemClick) this);
        item.SetId(id);
        this.mManager.AddCanItem(item.GetView(), this.mCurLine);
        item.ShowGone(false);
        this.mCurLine++;
        return item;
    }

    /* access modifiers changed from: protected */
    public ParamButton AddImgBtn(int id, int x, int y, int up, int dn) {
        ParamButton btn = this.mManager.AddButton(x, y);
        btn.setTag(Integer.valueOf(id));
        btn.setOnTouchListener(this);
        btn.setStateUpDn(up, dn);
        return btn;
    }

    public void onChanged(int id, int pos) {
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 1:
                CarSet(5, item);
                return;
            case 2:
                CarSet(6, item);
                return;
            case 5:
                CarSet(25, item);
                return;
            case 6:
                CarSet(26, item);
                return;
            default:
                return;
        }
    }

    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        int id = ((Integer) v.getTag()).intValue();
        if (action != 0) {
            if (1 == action) {
                switch (id) {
                    case 3:
                        CarSet(22, 0);
                        break;
                    case 4:
                        CarSet(23, 0);
                        break;
                }
            }
        } else {
            Log.d(TAG, "****ACTION_DOWN*****");
            switch (id) {
                case 3:
                    CarSet(22, 1);
                    break;
                case 4:
                    CarSet(23, 1);
                    break;
            }
        }
        return false;
    }
}
