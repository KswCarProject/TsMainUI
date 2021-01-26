package com.ts.factoryset;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ListView;
import com.ts.MainUI.R;
import com.ts.other.ParamButton;
import com.ts.other.ParamTextView;
import com.txznet.sdk.TXZResourceManager;
import com.yyw.ts70xhw.FtSet;

public class FsIconActivity extends FsBaseActivity {
    public static final int FocusLeft = 1;
    public static final int FocusNull = 0;
    public static final int FocusRight = 2;
    public static final String TAG = "FsIconActivity";
    public int mFocusFlg;
    private int[] mFtIco = new int[100];
    private int mFtIcoNum = 40;
    /* access modifiers changed from: private */
    public FsIconGridAdapter mGridAdapater;
    private GridView mGridView;
    /* access modifiers changed from: private */
    public FsIconListAdapter mListAdapater;
    private ListView mListView;
    private String[] mStrIcoArr;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fs_icon);
        topBtnInit(R.string.str_fsmain_ico);
        this.mGridView = (GridView) findViewById(R.id.fsicon_grid);
        this.mGridAdapater = new FsIconGridAdapter(this, 30);
        this.mGridView.setAdapter(this.mGridAdapater);
        this.mStrIcoArr = getResources().getStringArray(R.array.menu_ico_arr);
        this.mListView = (ListView) findViewById(R.id.fsicon_list);
        this.mListAdapater = new FsIconListAdapter(this, this.mStrIcoArr.length);
        this.mListView.setAdapter(this.mListAdapater);
        this.mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                FsIconActivity.this.mListAdapater.onLeftClick(position);
            }
        });
    }

    public void onClick(View v) {
    }

    /* access modifiers changed from: private */
    public String getIconName(int id) {
        if (id <= 0 || id > this.mStrIcoArr.length) {
            return "Icon " + id;
        }
        return this.mStrIcoArr[id - 1];
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        this.mFocusFlg = 0;
        FtSet.GetIcon(this.mFtIco);
        this.mListAdapater.setAll();
        this.mGridAdapater.resetAll();
        for (int i = 0; i < this.mFtIcoNum; i++) {
            if (this.mFtIco[i] > 0 && this.mFtIco[i] <= this.mListAdapater.getMaxIcoVal()) {
                this.mGridAdapater.setIndexVal(i, this.mFtIco[i]);
                this.mListAdapater.setIndexVal(this.mFtIco[i] - 1, 0);
            }
        }
        super.onResume();
    }

    /* access modifiers changed from: protected */
    public void onSave() {
        for (int i = 0; i < this.mFtIcoNum; i++) {
            this.mFtIco[i] = this.mGridAdapater.getIndexVal(i);
        }
        FtSet.SetIcon(this.mFtIco);
        Log.e(TAG, "onSave");
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        onSave();
        super.onPause();
    }

    private class FsIconGridAdapter extends BaseAdapter {
        private View.OnClickListener iconClick = new View.OnClickListener() {
            public void onClick(View v) {
                FsIconGridAdapter.this.onRightClick(((ParamButton) v).getIntParam2());
            }
        };
        private ParamButton[] mBtnIco;
        public LayoutInflater mLayoutInflater;
        private int mSel = -1;
        private int mTotalNum = 30;
        private View[] mView;

        public FsIconGridAdapter(Context context, int num) {
            if (num > 0) {
                this.mTotalNum = num;
            }
            this.mLayoutInflater = LayoutInflater.from(context);
            initView();
        }

        public int getIndexVal(int index) {
            if (index < 0 || index >= this.mTotalNum) {
                return 0;
            }
            return this.mBtnIco[index].getIntParam();
        }

        public void setIndexVal(int index, int val) {
            if (index >= 0 && index < this.mTotalNum) {
                this.mBtnIco[index].setIntParam(val);
            }
        }

        public void resetAll() {
            for (int position = 0; position < this.mTotalNum; position++) {
                this.mBtnIco[position].setIntParam(0);
            }
        }

        public void onRightClick(int pos) {
            int curPosVal = getIndexVal(pos);
            switch (FsIconActivity.this.mFocusFlg) {
                case 0:
                    if (curPosVal > 0) {
                        FsIconActivity.this.mFocusFlg = 2;
                        setSel(pos);
                        break;
                    }
                    break;
                case 1:
                    if (curPosVal > 0) {
                        FsIconActivity.this.mListAdapater.setIndexVal(curPosVal - 1, 1);
                    }
                    FsIconActivity.this.mListAdapater.setIndexVal(FsIconActivity.this.mListAdapater.mSel, 0);
                    FsIconActivity.this.mFocusFlg = 0;
                    setIndexVal(pos, FsIconActivity.this.mListAdapater.mSel + 1);
                    setSel(-1);
                    break;
                case 2:
                    if (this.mSel == pos) {
                        FsIconActivity.this.mListAdapater.setIndexVal(curPosVal - 1, 1);
                        setIndexVal(pos, 0);
                    } else {
                        int selVal = getIndexVal(this.mSel);
                        setIndexVal(this.mSel, curPosVal);
                        setIndexVal(pos, selVal);
                    }
                    FsIconActivity.this.mFocusFlg = 0;
                    setSel(-1);
                    break;
            }
            FsIconActivity.this.mListAdapater.notifyDataSetChanged();
        }

        public boolean addIcon(int id) {
            for (int i = 0; i < this.mTotalNum; i++) {
                if (getIndexVal(i) == 0) {
                    setIndexVal(i, id);
                    notifyDataSetChanged();
                    return true;
                }
            }
            return false;
        }

        public void setSel(int sel) {
            this.mSel = sel;
            notifyDataSetChanged();
        }

        /* access modifiers changed from: package-private */
        public void initView() {
            this.mView = new View[this.mTotalNum];
            this.mBtnIco = new ParamButton[this.mTotalNum];
            for (int position = 0; position < this.mTotalNum; position++) {
                Log.e("FsIconGridAdapter.getView", "index = " + position);
                View view = this.mLayoutInflater.inflate(R.layout.fs_icon_right_item, (ViewGroup) null);
                ParamButton btn = (ParamButton) view.findViewById(R.id.fsicon_rtbtn);
                btn.setIntParam2(position);
                btn.setOnClickListener(this.iconClick);
                this.mBtnIco[position] = btn;
                this.mView[position] = view;
            }
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ParamButton curIcon = this.mBtnIco[position];
            if (this.mSel == position && 2 == FsIconActivity.this.mFocusFlg) {
                curIcon.setSelected(true);
            } else {
                curIcon.setSelected(false);
            }
            int iconVal = curIcon.getIntParam();
            if (iconVal == 0) {
                curIcon.setText(TXZResourceManager.STYLE_DEFAULT);
            } else {
                curIcon.setText(FsIconActivity.this.getIconName(iconVal));
            }
            Log.e("Can list getview", new StringBuilder(String.valueOf(position)).toString());
            return this.mView[position];
        }

        public final int getCount() {
            return this.mTotalNum;
        }

        public final Object getItem(int position) {
            return null;
        }

        public final long getItemId(int position) {
            return (long) position;
        }
    }

    private class FsIconListAdapter extends BaseAdapter {
        private int TOTAL_ITEM = 40;
        private ParamTextView[] mIco;
        public LayoutInflater mLayoutInflater;
        /* access modifiers changed from: private */
        public int mSel = -1;
        private View[] mView;

        public FsIconListAdapter(Context context, int total) {
            this.TOTAL_ITEM = total;
            this.mView = new View[this.TOTAL_ITEM];
            this.mIco = new ParamTextView[this.TOTAL_ITEM];
            this.mLayoutInflater = LayoutInflater.from(context);
            initView();
        }

        public int getIndexVal(int index) {
            if (index < 0 || index >= this.TOTAL_ITEM) {
                return 0;
            }
            return this.mIco[index].getIntParam();
        }

        public int getMaxIcoVal() {
            return this.TOTAL_ITEM;
        }

        public void setIndexVal(int index, int val) {
            if (index >= 0 && index < this.TOTAL_ITEM) {
                this.mIco[index].setIntParam(val);
            }
        }

        /* access modifiers changed from: package-private */
        public void setAll() {
            for (int i = 0; i < this.TOTAL_ITEM; i++) {
                this.mIco[i].setIntParam(1);
            }
        }

        public void onLeftClick(int pos) {
            int curPosVal = getIndexVal(pos);
            switch (FsIconActivity.this.mFocusFlg) {
                case 0:
                    if (curPosVal > 0) {
                        FsIconActivity.this.mFocusFlg = 1;
                        setSel(pos);
                        break;
                    }
                    break;
                case 1:
                    if (curPosVal > 0) {
                        if (this.mSel == pos) {
                            if (FsIconActivity.this.mGridAdapater.addIcon(pos + 1)) {
                                FsIconActivity.this.mFocusFlg = 0;
                                setIndexVal(pos, 0);
                                setSel(-1);
                                break;
                            }
                        } else {
                            setSel(pos);
                            break;
                        }
                    }
                    break;
                case 2:
                    if (curPosVal == 0) {
                        FsIconActivity.this.mFocusFlg = 0;
                        notifyDataSetChanged();
                    } else {
                        FsIconActivity.this.mFocusFlg = 1;
                    }
                    FsIconActivity.this.mFocusFlg = 0;
                    setSel(-1);
                    break;
            }
            FsIconActivity.this.mGridAdapater.notifyDataSetChanged();
        }

        public void setSel(int sel) {
            this.mSel = sel;
            notifyDataSetChanged();
        }

        /* access modifiers changed from: package-private */
        public void initView() {
            for (int position = 0; position < this.TOTAL_ITEM; position++) {
                Log.e("FsIconGridAdapter.getView", "index = " + position);
                View view = this.mLayoutInflater.inflate(R.layout.fs_icon_left_item, (ViewGroup) null);
                this.mIco[position] = (ParamTextView) view.findViewById(R.id.fsicon_title);
                this.mView[position] = view;
            }
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ParamTextView curText = this.mIco[position];
            if (this.mSel == position && 1 == FsIconActivity.this.mFocusFlg) {
                curText.setSelected(true);
            } else {
                curText.setSelected(false);
            }
            if (curText.getIntParam() == 0) {
                curText.setText(TXZResourceManager.STYLE_DEFAULT);
            } else {
                curText.setText(FsIconActivity.this.getIconName(position + 1));
            }
            Log.e("Can list getview", new StringBuilder(String.valueOf(position)).toString());
            return this.mView[position];
        }

        public final int getCount() {
            return this.TOTAL_ITEM;
        }

        public final Object getItem(int position) {
            return null;
        }

        public final long getItemId(int position) {
            return (long) position;
        }
    }
}
