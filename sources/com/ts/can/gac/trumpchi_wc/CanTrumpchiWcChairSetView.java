package com.ts.can.gac.trumpchi_wc;

import android.app.Activity;
import android.view.View;
import android.widget.RelativeLayout;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanCameraUI;
import com.ts.can.CanScrollCarInfoView;
import com.ts.other.CustomImgView;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;

public class CanTrumpchiWcChairSetView extends CanScrollCarInfoView {
    private static final int LT_HOT = 1000;
    private static final int RT_HOT = 2000;
    private ParamButton mBtnLtHot;
    private ParamButton mBtnRtHot;
    private CanDataInfo.GCWcCarSet mCarSet;
    private CanDataInfo.GCWcChairHot mChairHot;
    private CustomImgView[] mLtHotVal;
    private CustomImgView[] mRtHotVal;

    public CanTrumpchiWcChairSetView(Activity activity) {
        super(activity, 4);
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                CanJni.TrumpchiWcCarSet(5, Neg(this.mCarSet.Jszyzdjrsz));
                return;
            case 1:
                CanJni.TrumpchiWcCarSet(6, Neg(this.mCarSet.Fjszyzdjrsz));
                return;
            case 2:
                CanJni.TrumpchiWcCarSet(23, Neg(this.mCarSet.Zyybgnsz));
                return;
            case 3:
                CanJni.TrumpchiWcCarSet(24, Neg(this.mCarSet.Znyszdsbzywz));
                return;
            case 1000:
                CanJni.TrumpchiWcChairHotSet(15, checkHot(this.mChairHot.LtChairHot));
                return;
            case 2000:
                CanJni.TrumpchiWcChairHotSet(16, checkHot(this.mChairHot.RtChairHot));
                return;
            default:
                return;
        }
    }

    private int checkHot(int hot) {
        int hot2 = hot + 1;
        if (hot2 > 3) {
            return 0;
        }
        return hot2;
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_jszyzdjr, R.string.can_fjszyzdjr, R.string.can_chair_yinbing, R.string.can_chair_smartkey};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH};
        this.mCarSet = new CanDataInfo.GCWcCarSet();
        this.mChairHot = new CanDataInfo.GCWcChairHot();
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        super.InitUI();
        RelativeLayout layout = new RelativeLayout(getActivity());
        RelativeLayoutManager manager = new RelativeLayoutManager(layout);
        getScrollManager().getLayout().addView(layout, -1, -2);
        this.mBtnLtHot = manager.AddButton(Can.CAN_MZD_LUOMU, 50);
        this.mBtnLtHot.setTag(1000);
        this.mBtnLtHot.setOnClickListener(this);
        this.mBtnLtHot.setStateUpDn(R.drawable.can_chairhot_lt_up, R.drawable.can_chairhot_lt_dn);
        this.mBtnRtHot = manager.AddButton(CanCameraUI.BTN_CCH9_MODE3, 50);
        this.mBtnRtHot.setTag(2000);
        this.mBtnRtHot.setOnClickListener(this);
        this.mBtnRtHot.setStateUpDn(R.drawable.can_chairhot_rt_up, R.drawable.can_chairhot_rt_dn);
        this.mLtHotVal = new CustomImgView[3];
        this.mRtHotVal = new CustomImgView[3];
        for (int i = 0; i < 3; i++) {
            this.mLtHotVal[i] = manager.AddImage((i * 40) + 269, 135);
            this.mLtHotVal[i].setStateDrawable(R.drawable.conditioning_rect_up, R.drawable.conditioning_rect_dn);
            this.mRtHotVal[i] = manager.AddImage((i * 40) + 639, 135);
            this.mRtHotVal[i].setStateDrawable(R.drawable.conditioning_rect_up, R.drawable.conditioning_rect_dn);
        }
    }

    public void ResetData(boolean check) {
        CanJni.TrumpchiWcGetCarSet(this.mCarSet);
        if (i2b(this.mCarSet.UpdateOnce) && (!check || i2b(this.mCarSet.Update))) {
            this.mCarSet.Update = 0;
            updateItem(0, this.mCarSet.Jszyzdjrsz);
            updateItem(1, this.mCarSet.Fjszyzdjrsz);
            updateItem(2, this.mCarSet.Zyybgnsz);
            updateItem(3, this.mCarSet.Znyszdsbzywz);
        }
        CanJni.TrumpchiWcGetChairHot(this.mChairHot);
        if (!i2b(this.mChairHot.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mChairHot.Update)) {
            this.mChairHot.Update = 0;
            updateHot(this.mChairHot.LtChairHot, this.mChairHot.RtChairHot);
        }
    }

    private void updateHot(int ltHot, int rtHot) {
        int lthot = ltHot & 3;
        int rthot = rtHot & 3;
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

    public void QueryData() {
    }
}
