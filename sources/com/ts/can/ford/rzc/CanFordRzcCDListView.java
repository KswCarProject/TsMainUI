package com.ts.can.ford.rzc;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanBaseCarDeviceActivity;
import com.ts.can.CanIF;
import com.ts.can.CanRelativeCarInfoView;
import com.ts.canview.CanItemTextBtnList;
import com.ts.other.CustomTextView;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;
import com.txznet.sdk.TXZResourceManager;
import java.io.UnsupportedEncodingException;

public class CanFordRzcCDListView extends CanRelativeCarInfoView {
    public static final String TAG = "CanFordRzcCDListView";
    private FileListAdapter mAdapter;
    protected CanDataInfo.FordRzcHostListText mCDList = new CanDataInfo.FordRzcHostListText();
    protected CanDataInfo.FordRzcHostInfo mHostInfo = new CanDataInfo.FordRzcHostInfo();
    private ListView mLvFile;
    protected RelativeLayoutManager mRelativeManager;
    private String[] mUpdate;

    public static class ViewHolder {
        public CanItemTextBtnList mFileItem;
        public int mPos;
    }

    public CanFordRzcCDListView(Activity activity) {
        super(activity);
    }

    public boolean onTouch(View arg0, MotionEvent arg1) {
        return false;
    }

    public void onClick(View v) {
        int intValue = ((Integer) v.getTag()).intValue();
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        getActivity().setContentView(R.layout.activity_can_listview);
        this.mRelativeManager = new RelativeLayoutManager(getActivity(), R.id.can_list_layout);
        this.mUpdate = new String[80];
        this.mLvFile = (ListView) this.mRelativeManager.GetLayout().findViewById(R.id.list_view);
        this.mAdapter = new FileListAdapter(getActivity(), this.mUpdate);
        this.mLvFile.setAdapter(this.mAdapter);
        this.mLvFile.setOnScrollListener(new AbsListView.OnScrollListener() {
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                Log.d(CanFordRzcCDListView.TAG, "firstVisibleItem = " + firstVisibleItem + ", visibleItemCount = " + visibleItemCount + ", totalItemCount = " + totalItemCount);
            }
        });
    }

    public void doOnResume() {
        super.doOnResume();
        ResetData(false);
        QueryData();
    }

    public void doOnPause() {
        super.doOnPause();
    }

    public void ResetData(boolean check) {
        for (int i = 0; i < this.mUpdate.length; i++) {
            CanJni.FordRzcGetCdListInfo(this.mCDList, i);
            if (i2b(this.mCDList.UpdateOnce) && i2b(this.mCDList.Update)) {
                this.mCDList.Update = 0;
                this.mUpdate[i] = byte2String(this.mCDList.Type, this.mCDList.Text, this.mCDList.Text.length);
            }
        }
        this.mAdapter.notifyDataSetChanged();
        CanJni.FordRzcGetHostInfo(this.mHostInfo);
        if (i2b(this.mHostInfo.Update)) {
            this.mHostInfo.Update = 0;
            if (this.mHostInfo.Src == 1) {
                enterSubWin(CanBaseCarDeviceActivity.class, -1);
            }
        }
    }

    /* access modifiers changed from: protected */
    public CustomTextView AddTxtLt(int x, int y, int w, int h) {
        CustomTextView temp = this.mRelativeManager.AddCusText(x, y, w, h);
        temp.SetPixelSize(30);
        temp.setGravity(19);
        return temp;
    }

    /* access modifiers changed from: protected */
    public CustomTextView AddTxtCenter(int x, int y, int w, int h) {
        CustomTextView temp = this.mRelativeManager.AddCusText(x, y, w, h);
        temp.SetPixelSize(30);
        temp.setGravity(17);
        return temp;
    }

    /* access modifiers changed from: protected */
    public ParamButton AddBtn(int id, int x, int y, int up, int dn) {
        ParamButton btn = getRelativeManager().AddButton(x, y);
        btn.setTag(Integer.valueOf(id));
        btn.setOnClickListener(this);
        btn.setDrawable(up, dn);
        return btn;
    }

    /* access modifiers changed from: protected */
    public ParamButton AddBtn(int id, int x, int y, int w, int h, int up, int dn) {
        ParamButton btn = getRelativeManager().AddButton(x, y, w, h);
        btn.setTag(Integer.valueOf(id));
        btn.setOnClickListener(this);
        btn.setDrawable(up, dn);
        return btn;
    }

    public void QueryData() {
        CanJni.FordQuery(104, 0);
    }

    private class FileListAdapter extends BaseAdapter implements View.OnClickListener {
        private String[] mCdText;
        protected Context mContext;

        public FileListAdapter(Context context, String[] mCdText2) {
            this.mContext = context;
            this.mCdText = mCdText2;
        }

        public int getCount() {
            return this.mCdText.length;
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
            holder.mFileItem.SetVal(this.mCdText[position]);
            return convertView;
        }

        public void onClick(View v) {
            ViewHolder holder = (ViewHolder) v.getTag();
            int i = holder.mPos + 1;
            System.out.println("onClick item =  " + holder.mPos);
        }
    }

    private String byte2ASCIIString(byte[] data, int len) {
        try {
            return new String(data, 0, len, "GBK");
        } catch (UnsupportedEncodingException e) {
            return TXZResourceManager.STYLE_DEFAULT;
        }
    }

    private String byte2String(int encode, byte[] data, int len) {
        if (encode == 0) {
            return CanIF.byte2UnicodeStr(data);
        }
        return byte2ASCIIString(data, len);
    }
}
