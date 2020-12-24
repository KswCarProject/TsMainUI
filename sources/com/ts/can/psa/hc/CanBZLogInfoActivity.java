package com.ts.can.psa.hc;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanIF;
import com.ts.canview.CanItemTextBtnList;
import com.ts.canview.CanScrollList;

public class CanBZLogInfoActivity extends CanBZBaseActivity implements View.OnClickListener, UserCallBack {
    public static final String TAG = "CanBZLogInfoActivity";
    protected LogAdapter mAdapter;
    protected CanDataInfo.PSALogUnit mLogData = new CanDataInfo.PSALogUnit();
    protected CanDataInfo.PSALogInfo mLogInfo = new CanDataInfo.PSALogInfo();
    protected int mLogType;
    protected ListView mLvLog;
    protected CanScrollList mManager;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_listview);
        this.mLvLog = (ListView) findViewById(R.id.list_view);
        this.mAdapter = new LogAdapter(this);
        this.mLvLog.setAdapter(this.mAdapter);
        this.mLogType = 133;
    }

    /* access modifiers changed from: protected */
    public void SetLogType(int type) {
        this.mLogType = type;
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        CanJni.BZGetLogInfo(this.mLogType, this.mLogInfo);
        if (i2b(this.mLogInfo.UpdateOnce)) {
            if (!check || i2b(this.mLogInfo.Update)) {
                this.mLogInfo.Update = 0;
                this.mAdapter.SetSize(this.mLogInfo.Total);
                this.mLvLog.setVisibility(0);
            }
        } else if (!check) {
            this.mLvLog.setVisibility(4);
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
        CanJni.BZStartQueryLog(this.mLogType, 1);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        QueryData();
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        CanJni.BZStopQueryLog(this.mLogType);
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
    }

    public void onClick(View v) {
        int intValue = ((Integer) v.getTag()).intValue();
    }

    public void UserAll() {
        ResetData(true);
    }

    private class LogAdapter extends BaseAdapter {
        protected Context mContext;
        protected int mSize = 0;

        public class ViewHolder {
            public CanItemTextBtnList mFileItem;
            public int mPos;

            public ViewHolder() {
            }
        }

        public LogAdapter(Context context) {
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
            Log.d("CanBZLogInfoActivity", "getView " + position + " " + convertView);
            if (convertView == null) {
                holder = new ViewHolder();
                CanItemTextBtnList item = new CanItemTextBtnList(this.mContext, 0);
                item.ShowArrow(false);
                convertView = item.GetView();
                holder.mFileItem = item;
                convertView.setTag(holder);
                convertView.setLayoutParams(new AbsListView.LayoutParams(-2, 85));
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.mPos = position;
            if (CanJni.BZGetLogData(CanBZLogInfoActivity.this.mLogType, position, CanBZLogInfoActivity.this.mLogData) == 0) {
                holder.mFileItem.SetVal(String.valueOf(position + 1) + "、 ");
            } else {
                holder.mFileItem.SetVal(String.format("%d、 %s", new Object[]{Integer.valueOf(position + 1), CanIF.byte2String(CanBZLogInfoActivity.this.mLogData.szInfo)}));
            }
            return convertView;
        }
    }
}
