package com.ts.factoryset;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.support.v4.internal.view.SupportMenu;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanIF;
import com.ts.factoryset.FsInputDlg;
import com.ts.main.common.MainSet;
import com.ts.other.ParamButton;
import com.ts.other.ValCal;
import com.yyw.ts70xhw.FtSet;
import java.util.ArrayList;
import java.util.List;

public class FsCanActivity extends FsBaseActivity {
    public static final int DOOR_ID_AVM = 128;
    public static final int DOOR_ID_FRONT = 1;
    public static final int DOOR_ID_HOOD = 8;
    public static final int DOOR_ID_OUT_TEMP = 256;
    public static final int DOOR_ID_REAR = 2;
    public static final int DOOR_ID_SWKEY = 16;
    public static final int DOOR_ID_TEMP = 32;
    public static final int DOOR_ID_TEMP_UNIT = 64;
    public static final int DOOR_ID_TRUNK = 4;
    public static final int DOOR_P_RADRA = 512;
    public static final int DOOR_UPDATE_ALL = 4095;
    public static final String TAG = "FsCanActivity";
    public static final int[] mAvmStr = {R.string.str_fstcon_off, R.string.str_fstcon_on};
    public static final int[] mDoorStr1 = {R.string.str_fs_normal, R.string.str_fs_swap, R.string.str_fs_hide, R.string.str_fs_hide};
    public static final int[] mDoorStr2 = {R.string.str_fs_normal, R.string.str_fs_hide};
    public static final int[] mOutTempStr = {R.string.str_fstcon_on, R.string.str_fstcon_off};
    public static final int[] mPRadarStr = {R.string.str_fstcon_on, R.string.str_fstcon_off, R.string.str_fs_hide};
    public static final int[] mTempUnits = {R.string.str_fs_temp_c, R.string.str_fs_temp_f};
    private View.OnClickListener doorClick = new View.OnClickListener() {
        public void onClick(View v) {
            ParamButton curBtn = (ParamButton) v;
            FsCanActivity.this.doorAdjust(curBtn.getIntParam(), ValCal.int2Bool(curBtn.getIntParam2()));
        }
    };
    /* access modifiers changed from: private */
    public boolean isBmwMiniEnabled;
    /* access modifiers changed from: private */
    public boolean isCanDis;
    /* access modifiers changed from: private */
    public FsCanAdapter mAdapater;
    /* access modifiers changed from: private */
    public ParamButton mBtnCan;
    private ParamButton mBtnClose;
    /* access modifiers changed from: private */
    public ParamButton mBtnCollapseItem;
    /* access modifiers changed from: private */
    public int[] mCanArray = null;
    /* access modifiers changed from: private */
    public CarTypeAdapter mCarListAdapter;
    /* access modifiers changed from: private */
    public ListView mCarListView;
    /* access modifiers changed from: private */
    public int mCurrentCanType = -1;
    private FsInputDlg mDlg;
    private View[] mDoorViews = new View[10];
    private ListView mListCan;
    private TextView[] mTvDoorVal = new TextView[10];
    private FsInputDlg.onInputOK onOK = new FsInputDlg.onInputOK() {
        public void onOK(String strVal) {
            Log.e(FsCanActivity.TAG, "Input val = " + strVal);
            if (!strVal.isEmpty()) {
                int i = Integer.parseInt(strVal);
                if (!FsCanActivity.this.isBmwMiniEnabled && i == 65) {
                    return;
                }
                if (!FsCanActivity.this.isCanDis || i != 241) {
                    if (FsCanActivity.this.mCanArray != null) {
                        i = FsCanActivity.this.indexOfCanArray(i);
                    }
                    FsCanActivity.this.mAdapater.setSel(i);
                }
            }
        }
    };

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fs_can);
        topBtnInit(R.string.str_fsmain_can);
        this.mListCan = (ListView) findViewById(R.id.fscan_list);
        this.mListCan.getParent().requestDisallowInterceptTouchEvent(true);
        this.mBtnCan = (ParamButton) findViewById(R.id.fscan_cur_val);
        this.mBtnCan.setOnClickListener(this);
        this.mCanArray = MainSet.GetInstance().GetCanEnable();
        this.isBmwMiniEnabled = MainSet.GetInstance().IsCustom("MINI");
        this.isCanDis = !MainSet.GetInstance().bIsEnableCan();
        this.mAdapater = new FsCanAdapter(this);
        this.mListCan.setAdapter(this.mAdapater);
        this.mDlg = new FsInputDlg();
        this.mListCan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View v, int position, long id) {
                Item item = FsCanActivity.this.mAdapater.getItem(position);
                if (item.type == 1) {
                    FsCanActivity.this.mAdapater.dealKindItem(position);
                    FsCanActivity.this.showCarInfos(false);
                } else if (item.isEnabled) {
                    FsCanActivity.this.mAdapater.setSubSel(position);
                    int position2 = item.index;
                    String[] carTypes = CanIF.GetTsResStrArray(FsCanActivity.this, "can_fs_declare_" + position2);
                    if (carTypes == null || carTypes.length == 0) {
                        FsCanActivity.this.showCarInfos(false);
                    } else if (FsCanActivity.this.mCarListView.getVisibility() != 0 || FsCanActivity.this.mCurrentCanType != position2) {
                        FsCanActivity.this.mCurrentCanType = position2;
                        FsCanActivity.this.showCarInfos(true);
                        FsCanActivity.this.mCarListAdapter.clear();
                        FsCanActivity.this.mCarListAdapter.addAll(carTypes);
                        if (CanJni.GetCanType() == position2) {
                            FsCanActivity.this.mCarListAdapter.setSelected(CanJni.GetSubType());
                            FsCanActivity.this.mCarListView.setSelection(CanJni.GetSubType());
                            return;
                        }
                        FsCanActivity.this.mCarListAdapter.setSelected(0);
                    }
                }
            }
        });
        LayoutInflater mLayoutInflater = LayoutInflater.from(this);
        RelativeLayout rl = (RelativeLayout) findViewById(R.id.fscan_main);
        String[] strTitle = getResources().getStringArray(R.array.str_fsdoor_title);
        int integer = getResources().getInteger(R.integer.fs_can_door_item_starty);
        int fsDoorItemMarginY = getResources().getInteger(R.integer.fs_can_door_item_marginy);
        for (int i = 0; i < this.mDoorViews.length; i++) {
            RelativeLayout view = (RelativeLayout) mLayoutInflater.inflate(R.layout.fs_door_item, (ViewGroup) null);
            rl.addView(view);
            this.mDoorViews[i] = view;
            RelativeLayout.LayoutParams rp2 = (RelativeLayout.LayoutParams) view.getLayoutParams();
            rp2.topMargin = i * fsDoorItemMarginY;
            view.setLayoutParams(rp2);
            ((TextView) view.findViewById(R.id.fsdoor_title)).setText(strTitle[i]);
            this.mTvDoorVal[i] = (TextView) view.findViewById(R.id.fsdoor_val);
            ParamButton btnCut = (ParamButton) view.findViewById(R.id.fsdoor_cut);
            btnCut.setIntParam(1 << i);
            btnCut.setIntParam2(0);
            btnCut.setOnClickListener(this.doorClick);
            ParamButton btnAdd = (ParamButton) view.findViewById(R.id.fsdoor_add);
            btnAdd.setIntParam(1 << i);
            btnAdd.setIntParam2(1);
            btnAdd.setOnClickListener(this.doorClick);
        }
        addCollapseBtn();
        addCarInfos();
    }

    private void addCollapseBtn() {
        this.mBtnCollapseItem = (ParamButton) findViewById(R.id.btn_collapse_kind_item);
        this.mBtnCollapseItem.setVisibility(8);
        this.mBtnCollapseItem.setStateUpDn(R.drawable.ic_collapse_kind_item_up, R.drawable.ic_collapse_kind_item_dn);
        this.mBtnCollapseItem.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                FsCanActivity.this.mAdapater.collapseKinItem();
            }
        });
    }

    private void addCarInfos() {
        this.mCarListView = (ListView) findViewById(R.id.fs_carinfo_list);
        this.mCarListAdapter = new CarTypeAdapter(this, 17367043);
        this.mCarListView.setAdapter(this.mCarListAdapter);
        this.mCarListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long arg3) {
                FsCanActivity.this.mCarListAdapter.setSelected(position);
            }
        });
        this.mBtnClose = (ParamButton) findViewById(R.id.btn_close_carinfo);
        this.mBtnClose.setStateUpDn(R.drawable.ic_close_carinfos_up, R.drawable.ic_close_carinfos_dn);
        this.mBtnClose.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                FsCanActivity.this.showCarInfos(false);
            }
        });
        this.mCarListView.setVisibility(4);
        this.mBtnClose.setVisibility(4);
    }

    /* access modifiers changed from: private */
    public void showCarInfos(boolean show) {
        int i;
        int i2;
        this.mCarListView.setVisibility(show ? 0 : 4);
        ParamButton paramButton = this.mBtnClose;
        if (show) {
            i = 0;
        } else {
            i = 4;
        }
        paramButton.setVisibility(i);
        for (View view : this.mDoorViews) {
            if (show) {
                i2 = 4;
            } else {
                i2 = 0;
            }
            view.setVisibility(i2);
        }
    }

    /* access modifiers changed from: private */
    public Drawable getStatusDrawable() {
        StateListDrawable sd = new StateListDrawable();
        Drawable iNormal = new ColorDrawable(0);
        int[] iArr = {16842919};
        sd.addState(iArr, new ColorDrawable(SupportMenu.CATEGORY_MASK));
        sd.addState(new int[0], iNormal);
        return sd;
    }

    class CarTypeAdapter extends ArrayAdapter<String> {
        private int mSelectedPosition = 0;

        public CarTypeAdapter(Context context, int resource) {
            super(context, resource);
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            TextView view = (TextView) super.getView(position, convertView, parent);
            view.setTextSize(0, 24.0f);
            view.setTextColor(-1);
            view.setGravity(17);
            view.setMinHeight(64);
            if (this.mSelectedPosition == position) {
                view.setBackgroundColor(SupportMenu.CATEGORY_MASK);
            } else {
                view.setBackground(FsCanActivity.this.getStatusDrawable());
            }
            return view;
        }

        public void setSelected(int position) {
            this.mSelectedPosition = position;
            notifyDataSetChanged();
        }

        public int getSelected() {
            return this.mSelectedPosition;
        }
    }

    /* access modifiers changed from: protected */
    public void onSave() {
        super.onSave();
        if (this.mCurrentCanType != -1) {
            FtSet.SetCanSubT(this.mCarListAdapter.getSelected());
        } else {
            FtSet.SetCanSubT(CanJni.GetSubType());
        }
    }

    public void onClick(View v) {
        if (R.id.fscan_cur_val == v.getId()) {
            this.mDlg.createDlg(this, this.onOK, true);
        }
    }

    /* access modifiers changed from: private */
    public void doorAdjust(int id, boolean fgInc) {
        switch (id) {
            case 1:
                FtSet.SetFdoor(ValCal.dataStepLoop(FtSet.GetFdoor(), 0, 2, fgInc));
                break;
            case 2:
                FtSet.SetBdoor(ValCal.dataStepLoop(FtSet.GetBdoor(), 0, 2, fgInc));
                break;
            case 4:
                FtSet.SetTrunk(1 - (FtSet.GetTrunk() & 1));
                break;
            case 8:
                FtSet.SetHood(1 - (FtSet.GetHood() & 1));
                break;
            case 16:
                FtSet.SetSWnpSwap(1 - (FtSet.GetSWnpSwap() & 1));
                break;
            case 32:
                FtSet.SetACTempSwap(1 - (FtSet.GetACTempSwap() & 1));
                break;
            case 64:
                FtSet.Setyw3(1 - (FtSet.Getyw3() & 1));
                break;
            case 128:
                FtSet.Setyw4(1 - (FtSet.Getyw4() & 1));
                break;
            case 256:
                FtSet.Setyw8(1 - (FtSet.Getyw8() & 1));
                break;
            case 512:
                FtSet.Setyw14(ValCal.dataStepLoop(FtSet.Getyw14(), 0, 2, fgInc));
                break;
        }
        updateDoor(id);
    }

    private void updateDoor(int item) {
        if ((item & 1) != 0) {
            this.mTvDoorVal[0].setText(mDoorStr1[FtSet.GetFdoor() & 3]);
        }
        if ((item & 2) != 0) {
            this.mTvDoorVal[1].setText(mDoorStr1[FtSet.GetBdoor() & 3]);
        }
        if ((item & 4) != 0) {
            this.mTvDoorVal[2].setText(mDoorStr2[FtSet.GetTrunk() & 1]);
        }
        if ((item & 8) != 0) {
            this.mTvDoorVal[3].setText(mDoorStr2[FtSet.GetHood() & 1]);
        }
        if ((item & 16) != 0) {
            this.mTvDoorVal[4].setText(mDoorStr1[FtSet.GetSWnpSwap() & 1]);
        }
        if ((item & 32) != 0) {
            this.mTvDoorVal[5].setText(mDoorStr1[FtSet.GetACTempSwap() & 1]);
        }
        if ((item & 64) != 0) {
            this.mTvDoorVal[6].setText(mTempUnits[FtSet.Getyw3() & 1]);
        }
        if ((item & 128) != 0) {
            this.mTvDoorVal[7].setText(mAvmStr[FtSet.Getyw4() & 1]);
        }
        if ((item & 256) != 0) {
            this.mTvDoorVal[8].setText(mOutTempStr[FtSet.Getyw8() & 1]);
        }
        if ((item & 512) != 0) {
            this.mTvDoorVal[9].setText(mPRadarStr[FtSet.Getyw14() & 3]);
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        if (this.mCanArray == null) {
            this.mBtnCan.setText(new StringBuilder(String.valueOf(FtSet.GetCanTp())).toString());
        } else {
            this.mBtnCan.setText(new StringBuilder(String.valueOf(indexOfCanArray(FtSet.GetCanTp()))).toString());
        }
        updateDoor(DOOR_UPDATE_ALL);
        super.onResume();
    }

    /* access modifiers changed from: private */
    public int indexOfCanArray(int value) {
        if (this.mCanArray == null) {
            return -1;
        }
        for (int i = 0; i < this.mCanArray.length; i++) {
            if (this.mCanArray[i] == value) {
                return i;
            }
        }
        return -1;
    }

    /* access modifiers changed from: private */
    public void scrollToPosition(int sel) {
        if (sel != -1) {
            int first = this.mListCan.getFirstVisiblePosition();
            int last = this.mListCan.getLastVisiblePosition();
            if (sel < first || sel > last) {
                this.mListCan.setSelection(sel);
            }
        }
    }

    public class FsCanAdapter extends BaseAdapter {
        private String[] mAllCarArrays;
        private Context mContext;
        private String[] mKindArrays;
        private int mKindSel = -1;
        private List<Item> mList;
        private int mSubSel = -1;

        public FsCanAdapter(Context context) {
            this.mContext = context;
            this.mList = new ArrayList();
            this.mAllCarArrays = CanIF.GetTsResStrArray(context, "can_auto_array");
            addKindItems();
        }

        private void addKindItems() {
            int length;
            this.mKindArrays = CanIF.GetTsResStrArray(this.mContext, "can_car_kinds_array");
            if (FsCanActivity.this.mCanArray == null) {
                length = this.mKindArrays.length;
            } else {
                length = 1;
            }
            for (int i = 0; i < length; i++) {
                this.mList.add(new Item(-1, 1, this.mKindArrays[i]));
            }
        }

        public void collapseKinItem() {
            if (this.mKindSel != -1 && this.mList.get(this.mKindSel).isExpand) {
                this.mList.clear();
                addKindItems();
                this.mKindSel = -1;
                notifyDataSetChanged();
                FsCanActivity.this.mBtnCollapseItem.setVisibility(8);
            }
        }

        public void dealKindItem(int index) {
            int length;
            int position;
            Item item = this.mList.get(index);
            this.mSubSel = -1;
            if (item.isExpand) {
                this.mList.clear();
                addKindItems();
                this.mKindSel = -1;
                FsCanActivity.this.mBtnCollapseItem.setVisibility(8);
            } else {
                if (this.mKindSel != -1 && this.mList.get(this.mKindSel).isExpand) {
                    this.mList.clear();
                    addKindItems();
                    index = this.mList.indexOf(item);
                }
                if (index == 0) {
                    String[] subArrays = this.mAllCarArrays;
                    if (FsCanActivity.this.mCanArray == null) {
                        length = subArrays.length;
                        this.mSubSel = FtSet.GetCanTp() + 1;
                    } else {
                        length = FsCanActivity.this.mCanArray.length;
                        this.mSubSel = FsCanActivity.this.indexOfCanArray(FtSet.GetCanTp()) + 1;
                    }
                    for (int i = 0; i < length; i++) {
                        if (FsCanActivity.this.mCanArray == null) {
                            position = i;
                        } else {
                            position = FsCanActivity.this.mCanArray[i];
                        }
                        if (position < subArrays.length) {
                            Item carItem = new Item(position, 2, String.valueOf(position) + "." + subArrays[position]);
                            if (position == 65) {
                                carItem.isEnabled = FsCanActivity.this.isBmwMiniEnabled;
                            } else if (position == 241) {
                                carItem.isEnabled = !FsCanActivity.this.isCanDis;
                            }
                            this.mList.add(index + i + 1, carItem);
                        }
                    }
                } else {
                    String[] subArrays2 = CanIF.GetTsResStrArray(this.mContext, "can_kind_sub_array_" + index);
                    for (int i2 = 0; i2 < subArrays2.length; i2++) {
                        int position2 = Integer.parseInt(subArrays2[i2]);
                        if (position2 == FtSet.GetCanTp()) {
                            this.mSubSel = index + i2 + 1;
                        }
                        if (position2 < this.mAllCarArrays.length) {
                            this.mList.add(index + i2 + 1, new Item(position2, 2, String.valueOf(position2) + "." + this.mAllCarArrays[position2]));
                        }
                    }
                }
                this.mKindSel = index;
                this.mList.get(this.mKindSel).isExpand = true;
                FsCanActivity.this.mBtnCollapseItem.setVisibility(0);
            }
            notifyDataSetChanged();
            FsCanActivity.this.scrollToPosition(this.mSubSel);
        }

        public void setSubSel(int sel) {
            this.mSubSel = sel;
            notifyDataSetChanged();
            FsCanActivity.this.scrollToPosition(sel);
            int index = this.mList.get(sel).index;
            FsCanActivity.this.mBtnCan.setText(new StringBuilder(String.valueOf(index)).toString());
            FtSet.SetCanTp(index);
        }

        public void setSel(int sel) {
            int length;
            int position;
            if (sel >= 0 && sel < this.mAllCarArrays.length) {
                if (this.mList.get(0).isExpand) {
                    setSubSel(sel + 1);
                    return;
                }
                if (this.mKindSel != -1 && this.mList.get(this.mKindSel).isExpand) {
                    this.mList.clear();
                    addKindItems();
                }
                if (FsCanActivity.this.mCanArray == null) {
                    length = this.mAllCarArrays.length;
                } else {
                    length = FsCanActivity.this.mCanArray.length;
                }
                for (int i = 0; i < length; i++) {
                    if (FsCanActivity.this.mCanArray == null) {
                        position = i;
                    } else {
                        position = FsCanActivity.this.mCanArray[i];
                    }
                    if (position < this.mAllCarArrays.length) {
                        Item carItem = new Item(position, 2, String.valueOf(position) + "." + this.mAllCarArrays[position]);
                        if (position == 65) {
                            carItem.isEnabled = FsCanActivity.this.isBmwMiniEnabled;
                        } else if (position == 241) {
                            carItem.isEnabled = !FsCanActivity.this.isCanDis;
                        }
                        this.mList.add(i + 1, carItem);
                    }
                }
                this.mKindSel = 0;
                this.mList.get(0).isExpand = true;
                setSubSel(sel + 1);
                FsCanActivity.this.mBtnCollapseItem.setVisibility(0);
            }
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder mHolder;
            if (convertView == null) {
                mHolder = new ViewHolder();
                convertView = LayoutInflater.from(this.mContext).inflate(R.layout.fs_can_item, (ViewGroup) null);
                mHolder.mTvVal = (TextView) convertView.findViewById(R.id.fscan_id);
                convertView.setTag(mHolder);
            } else {
                mHolder = (ViewHolder) convertView.getTag();
            }
            Item item = this.mList.get(position);
            mHolder.mTvVal.setText(item.text);
            RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) mHolder.mTvVal.getLayoutParams();
            if (item.type == 2) {
                if (this.mSubSel == position) {
                    mHolder.mTvVal.setSelected(true);
                } else {
                    mHolder.mTvVal.setSelected(false);
                }
                lp.leftMargin = 50;
                if (item.isEnabled) {
                    mHolder.mTvVal.setTextColor(this.mContext.getResources().getColorStateList(R.drawable.fs_can_item_color));
                } else {
                    mHolder.mTvVal.setTextColor(Color.parseColor("#515151"));
                }
                mHolder.mTvVal.setTextSize(0, 30.0f);
            } else {
                mHolder.mTvVal.setSelected(false);
                lp.leftMargin = 32;
                mHolder.mTvVal.setTextColor(getColorState());
                mHolder.mTvVal.setTextSize(0, 34.0f);
            }
            mHolder.mTvVal.setLayoutParams(lp);
            return convertView;
        }

        private ColorStateList getColorState() {
            return new ColorStateList(new int[][]{new int[]{16842919}, new int[0]}, new int[]{ViewCompat.MEASURED_STATE_MASK, -3355444});
        }

        public int getCount() {
            return this.mList.size();
        }

        public Item getItem(int position) {
            return this.mList.get(position);
        }

        public long getItemId(int position) {
            return (long) position;
        }
    }

    static class ViewHolder {
        /* access modifiers changed from: private */
        public TextView mTvVal;

        ViewHolder() {
        }
    }

    class Item {
        public static final int TYPE_CAR = 2;
        public static final int TYPE_KIND = 1;
        public int index;
        public boolean isEnabled = true;
        public boolean isExpand = false;
        public String text;
        public int type;

        public Item(int index2, int type2, String text2) {
            this.index = index2;
            this.type = type2;
            this.text = text2;
        }

        public boolean equals(Object o) {
            if (this == null || o == null || !(o instanceof Item)) {
                return false;
            }
            Item item = (Item) o;
            if (item.index == this.index && item.type == this.type && TextUtils.equals(item.text, this.text)) {
                return true;
            }
            return false;
        }
    }
}
