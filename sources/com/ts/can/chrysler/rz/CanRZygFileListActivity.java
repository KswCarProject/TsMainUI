package com.ts.can.chrysler.rz;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanBaseActivity;
import com.ts.can.CanIF;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanItemTextBtnList;
import com.ts.canview.CanScrollList;
import com.ts.dvdplayer.definition.MediaDef;

public class CanRZygFileListActivity extends CanBaseActivity implements View.OnClickListener, UserCallBack {
    public static final int ITEM_AC_LINK_AUTO = 1;
    public static final int ITEM_LOOP_LINK_AUTO = 2;
    private static final int ITEM_MAX = 2;
    private static final int ITEM_MIN = 1;
    public static final String TAG = "CanRZygFileListActivity";
    private FileListAdapter mAdapter;
    protected int mCurTopIndex = 0;
    private int mFirstV = -1;
    protected CanDataInfo.ChrOthCdInfo mInfoData = new CanDataInfo.ChrOthCdInfo();
    protected boolean mIsLoadOK = false;
    private CanItemTextBtnList[] mItemList = new CanItemTextBtnList[MediaDef.PROGRESS_MAX];
    protected LinearLayout mLineLayout;
    protected CanDataInfo.ChrOthText mListItemData = new CanDataInfo.ChrOthText();
    private ListView mLvFile;
    private CanScrollList mManager;
    protected CanDataInfo.ChrOthCdSta mStaData = new CanDataInfo.ChrOthCdSta();
    protected ScrollView mSvLayout;
    protected int mTotal = 0;
    private int[] mUpdate = new int[MediaDef.PROGRESS_MAX];
    private boolean mbLayout;

    public static class ViewHolder {
        public CanItemTextBtnList mFileItem;
        public int mPos;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_listview);
        this.mLvFile = (ListView) findViewById(R.id.list_view);
        this.mAdapter = new FileListAdapter(this);
        this.mLvFile.setAdapter(this.mAdapter);
        this.mLvFile.setOnScrollListener(new AbsListView.OnScrollListener() {
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                CanRZygFileListActivity.this.mCurTopIndex = firstVisibleItem;
                Log.d("CanRZygFileListActivity", "firstVisibleItem = " + firstVisibleItem + ", visibleItemCount = " + visibleItemCount + ", totalItemCount = " + totalItemCount);
            }
        });
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        int curTop;
        int updateNum;
        CanJni.ChrOthGetCdInfo(this.mInfoData);
        CanJni.ChrOthGetCdSta(this.mStaData);
        boolean ok = IsLoadOK();
        if (ok != this.mIsLoadOK) {
            this.mIsLoadOK = ok;
            if (this.mIsLoadOK) {
                this.mLvFile.setVisibility(0);
            } else {
                this.mLvFile.setVisibility(4);
                this.mTotal = 0;
            }
        }
        if (this.mIsLoadOK) {
            if (this.mTotal != this.mInfoData.TotalTrack) {
                this.mTotal = this.mInfoData.TotalTrack;
                if (this.mTotal > 1000) {
                    this.mTotal = MediaDef.PROGRESS_MAX;
                }
                this.mAdapter.SetSize(this.mTotal);
            }
            if (this.mTotal > 0 && (curTop = this.mCurTopIndex) >= 0 && curTop < this.mTotal && (updateNum = CanJni.ChrOthGetListUpdateItem(this.mUpdate, 7, curTop)) > 0) {
                this.mAdapter.notifyDataSetChanged();
                Log.d("CanRZygFileListActivity", "curTop = " + curTop + ", updateNum = " + updateNum);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        this.mIsLoadOK = false;
        this.mTotal = 0;
        this.mFirstV = -1;
        ResetData(false);
        QueryData();
        Log.d("CanRZygFileListActivity", "-----onResume-----");
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
        Log.d("CanRZygFileListActivity", "-----onPause-----");
    }

    /* access modifiers changed from: protected */
    public boolean IsLoadOK() {
        if (1 == this.mStaData.Sta || 5 == this.mStaData.Sta || 6 == this.mStaData.Sta || 8 == this.mStaData.Sta) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void AddItem(int i) {
        if (i <= this.mItemList.length) {
            this.mUpdate[i] = 0;
            if (this.mItemList[i] == null) {
                this.mItemList[i] = new CanItemTextBtnList((Context) this, 0);
                this.mItemList[i].ShowArrow(false);
                this.mItemList[i].SetIdClickListener(this, i);
            }
            this.mItemList[i].SetVal(String.valueOf(i + 1) + "、 Track " + (i + 1));
            this.mManager.AddView(this.mItemList[i].GetView());
        }
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mManager = new CanScrollList(this);
        GetLayout();
        this.mManager.ShowHide(false);
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        for (int i = 1; i <= 2; i++) {
            ShowItem(i);
        }
    }

    /* access modifiers changed from: protected */
    public boolean IsHaveItem(int item) {
        return i2b(0);
    }

    /* access modifiers changed from: protected */
    public void ShowItem(int item) {
        boolean IsHaveItem = IsHaveItem(item);
    }

    /* access modifiers changed from: protected */
    public CanItemSwitchList AddCheckItem(int resId, int Id) {
        CanItemSwitchList item = new CanItemSwitchList(this, resId);
        item.SetIdClickListener(this, Id);
        this.mManager.AddView(item.GetView());
        item.ShowGone(false);
        return item;
    }

    public void onClick(View v) {
        int intValue = ((Integer) v.getTag()).intValue();
    }

    public void UserAll() {
        ResetData(true);
    }

    /* access modifiers changed from: protected */
    public void GetLayout() {
        this.mSvLayout = (ScrollView) findViewById(R.id.can_comm_scrview);
        this.mLineLayout = (LinearLayout) findViewById(R.id.can_comm_lineview);
    }

    public int GetFirstView() {
        Rect scrollBounds = new Rect();
        this.mSvLayout.getHitRect(scrollBounds);
        int total = this.mLineLayout.getChildCount();
        for (int i = 0; i < total; i++) {
            View v = this.mLineLayout.getChildAt(i);
            if (v.getLocalVisibleRect(scrollBounds)) {
                if (-1 == this.mFirstV) {
                    Log.d("CanRZygFileListActivity", "total = " + total + ", first visible = " + ((Integer) v.getTag()));
                }
                return ((Integer) v.getTag()).intValue();
            }
        }
        return -1;
    }

    private class FileListAdapter extends BaseAdapter implements View.OnClickListener {
        protected Context mContext;
        protected int mSize = 0;

        public FileListAdapter(Context context) {
            this.mContext = context;
        }

        public void addItem(String item) {
            notifyDataSetChanged();
        }

        public void SetSize(int size) {
            this.mSize = size;
            notifyDataSetChanged();
        }

        public int getCount() {
            return this.mSize;
        }

        public String getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
            return (long) position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            System.out.println("getView " + position + " " + convertView);
            if (convertView == null) {
                holder = new ViewHolder();
                CanItemTextBtnList item = new CanItemTextBtnList(this.mContext, 0);
                item.ShowArrow(false);
                convertView = item.GetView();
                holder.mFileItem = item;
                convertView.setTag(holder);
                convertView.setLayoutParams(new AbsListView.LayoutParams(-2, 85));
                holder.mFileItem.GetView().setOnClickListener(this);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.mPos = position;
            holder.mFileItem.SetVal(String.valueOf(position + 1) + "、 Track " + (position + 1));
            if (CanJni.ChrOthGetList(position, CanRZygFileListActivity.this.mListItemData) != 0) {
                holder.mFileItem.SetVal(String.format("%d、 %s", new Object[]{Integer.valueOf(position + 1), CanIF.byte2UnicodeStr(CanRZygFileListActivity.this.mListItemData.szData)}));
            } else {
                holder.mFileItem.SetVal(String.valueOf(position + 1) + "、 Track " + (position + 1));
            }
            return convertView;
        }

        public void onClick(View v) {
            ViewHolder holder = (ViewHolder) v.getTag();
            int index = holder.mPos + 1;
            System.out.println("onClick item =  " + holder.mPos);
            CanJni.RZChrOthCDCtrl(15, (index >> 8) & 255, index & 255);
        }
    }
}
