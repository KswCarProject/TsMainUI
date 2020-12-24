package com.ts.can.ford.fiesta;

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
import com.ts.can.CanBaseActivity;
import com.ts.can.CanIF;
import com.ts.canview.CanItemTextBtnList;
import com.ts.canview.CanScrollList;

public class CanOldFiestaLogActivity extends CanBaseActivity implements View.OnClickListener, UserCallBack {
    public static final String TAG = "CanBZLogInfoActivity";
    protected LogAdapter mAdapter;
    protected CanDataInfo.FiestaCar mLogData = new CanDataInfo.FiestaCar();
    protected CanDataInfo.FiestaInfo mLogInfo = new CanDataInfo.FiestaInfo();
    protected ListView mLvLog;
    protected CanScrollList mManager;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_listview);
        this.mLvLog = (ListView) findViewById(R.id.list_view);
        this.mAdapter = new LogAdapter(this);
        this.mLvLog.setAdapter(this.mAdapter);
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        CanJni.OldFiestaGetCarData(this.mLogData);
        if (i2b(this.mLogData.UpdateOne)) {
            if (!check || i2b(this.mLogData.Update)) {
                this.mLogData.Update = 0;
                this.mAdapter.SetSize(this.mLogData.dwNum);
                this.mLvLog.setVisibility(0);
            }
        } else if (!check) {
            this.mLvLog.setVisibility(4);
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
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
            if (CanJni.OldFiestaGetText(position, CanOldFiestaLogActivity.this.mLogInfo) == 0) {
                holder.mFileItem.SetVal(String.valueOf(position + 1) + "、 ");
            } else {
                holder.mFileItem.SetVal(String.format("%d、 %s", new Object[]{Integer.valueOf(position + 1), CanIF.byte2String(CanOldFiestaLogActivity.this.mLogInfo.szInfo)}));
            }
            return convertView;
        }
    }
}
