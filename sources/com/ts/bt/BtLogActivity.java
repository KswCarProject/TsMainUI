package com.ts.bt;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.txznet.sdk.TXZResourceManager;
import java.util.ArrayList;
import java.util.HashMap;

@SuppressLint({"NewApi", "Override"})
public class BtLogActivity extends BtBaseActivity implements View.OnClickListener, UserCallBack {
    public static final int BT_ACTIVITY_ID = 6;
    private static final String TAG = "BtCallHistoryActivity";
    public Button[] logButton = new Button[4];
    /* access modifiers changed from: private */
    public String mDialNum;
    public HistoryListAdapter mHistoryListAdapter;
    public HistoryListAdapter1 mHistoryListAdapter1;
    private AdapterView.OnItemClickListener mHistoryListClickListener = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView<?> adapterView, View V, int position, long arg3) {
            BtLogActivity.this.dial(position, false);
            BtLogActivity.this.mHistoryListAdapter.setSelect(position);
            BtLogActivity.this.mHistoryListAdapter.notifyDataSetChanged();
            BtLogActivity.this.updateFocus(BtLogActivity.this.mLtView[BtLogActivity.this.mIcoSel]);
            BtLogActivity.this.mbSubFocus = 1;
            BtLogActivity.this.mListLog.setItemChecked(position, true);
        }
    };
    private AdapterView.OnItemClickListener mHistoryListClickListener1 = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView<?> adapterView, View V, int position, long arg3) {
            BtLogActivity.this.dial1(position);
            BtLogActivity.this.mHistoryListAdapter1.setSelect(position);
            BtLogActivity.this.mHistoryListAdapter1.notifyDataSetChanged();
        }
    };
    public ListView mListLog;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mIsInMultiWindowMode = isInMultiWindowMode();
        updateLayout(this.mIsInMultiWindowMode);
    }

    public void onMultiWindowModeChanged(boolean isInMultiWindowMode, Configuration newConfig) {
        super.onMultiWindowModeChanged(isInMultiWindowMode, newConfig);
        this.mIsInMultiWindowMode = isInMultiWindowMode;
        updateMultiChange(isInMultiWindowMode);
    }

    /* access modifiers changed from: package-private */
    public void updateMultiChange(boolean isInMultiWindowMode) {
        updateLayout(isInMultiWindowMode);
        SubItemsInit(this, 6);
    }

    /* access modifiers changed from: package-private */
    public void updateLayout(boolean isInMultiWindowMode) {
        if (isInMultiWindowMode) {
            setContentView(R.layout.activity_bt_log_s);
            initView1();
            return;
        }
        setContentView(R.layout.activity_bt_log);
        initView();
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        SubItemsInit(this, 6);
        if (this.isShowActivity && !isInMultiWindowMode()) {
            EnterSubFocus();
        }
        MainTask.GetInstance().SetUserCallBack(this);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
    }

    public boolean onLongClick(View view) {
        return false;
    }

    private void initView() {
        this.logButton[0] = (Button) findViewById(R.id.btn_missed);
        this.logButton[1] = (Button) findViewById(R.id.btn_received);
        this.logButton[2] = (Button) findViewById(R.id.btn_dialed);
        this.logButton[3] = (Button) findViewById(R.id.btn_delete_log);
        this.logButton[0].setOnClickListener(this);
        this.logButton[1].setOnClickListener(this);
        this.logButton[2].setOnClickListener(this);
        this.logButton[3].setOnClickListener(this);
        this.mListLog = (ListView) findViewById(R.id.history_listview);
        this.mHistoryListAdapter = new HistoryListAdapter(this);
        this.mListLog.setAdapter(this.mHistoryListAdapter);
        this.mListLog.setOnItemClickListener(this.mHistoryListClickListener);
        BtExe.updateHistory();
        BtExe.flushFilterList();
        this.mHistoryListAdapter.updateData(BtExe.mHistoryList);
        checkSelected();
        this.mFocusView = new View[3];
        this.mFocusView[0] = this.logButton[0];
        this.mFocusView[1] = this.logButton[1];
        this.mFocusView[2] = this.logButton[2];
    }

    private void initView1() {
        this.logButton[0] = (Button) findViewById(R.id.btn_missed);
        this.logButton[1] = (Button) findViewById(R.id.btn_received);
        this.logButton[2] = (Button) findViewById(R.id.btn_dialed);
        this.logButton[3] = (Button) findViewById(R.id.btn_delete_log);
        this.logButton[0].setOnClickListener(this);
        this.logButton[1].setOnClickListener(this);
        this.logButton[2].setOnClickListener(this);
        this.logButton[3].setOnClickListener(this);
        this.mListLog = (ListView) findViewById(R.id.history_listview);
        this.mHistoryListAdapter1 = new HistoryListAdapter1(this);
        this.mListLog.setAdapter(this.mHistoryListAdapter1);
        this.mListLog.setOnItemClickListener(this.mHistoryListClickListener1);
        BtExe.updateHistory();
        BtExe.flushFilterList();
        this.mHistoryListAdapter1.updateData(BtExe.mHistoryList);
        checkSelected();
    }

    /* access modifiers changed from: package-private */
    public void dial(int id) {
        String message;
        HashMap<String, Object> list = this.mHistoryListAdapter.mList.get(id);
        this.mDialNum = (String) list.get(BtExe.ITEM_HISTORY_NUMBER);
        String name = (String) list.get(BtExe.ITEM_HISTORY_NAME);
        String number = (String) list.get(BtExe.ITEM_HISTORY_NUMBER);
        if (name == null || name.isEmpty()) {
            message = number;
        } else {
            message = name;
        }
        AlertDialog dialog = new AlertDialog.Builder(this).setTitle(R.string.str_bt_dial).setMessage(message).setPositiveButton(R.string.str_bt_ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if (BtLogActivity.this.mDialNum != null && !BtLogActivity.this.mDialNum.isEmpty()) {
                    BtLogActivity.this.bt.dial(BtLogActivity.this.mDialNum);
                }
            }
        }).setNegativeButton(R.string.str_bt_cancel, (DialogInterface.OnClickListener) null).create();
        dialog.show();
        Window dialogWindow = dialog.getWindow();
        Display d = getWindowManager().getDefaultDisplay();
        WindowManager.LayoutParams p = dialogWindow.getAttributes();
        p.width = (int) (((double) d.getWidth()) * 0.5d);
        p.height = (int) (((double) d.getHeight()) * 0.5d);
        p.gravity = 17;
        dialogWindow.setAttributes(p);
    }

    /* access modifiers changed from: package-private */
    public void dial1(int id) {
        String message;
        HashMap<String, Object> list = this.mHistoryListAdapter1.mList.get(id);
        this.mDialNum = (String) list.get(BtExe.ITEM_HISTORY_NUMBER);
        String name = (String) list.get(BtExe.ITEM_HISTORY_NAME);
        String number = (String) list.get(BtExe.ITEM_HISTORY_NUMBER);
        if (name == null || name.isEmpty()) {
            message = number;
        } else {
            message = name;
        }
        AlertDialog dialog = new AlertDialog.Builder(this).setTitle(R.string.str_bt_dial).setMessage(message).setPositiveButton(R.string.str_bt_ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if (BtLogActivity.this.mDialNum != null && !BtLogActivity.this.mDialNum.isEmpty()) {
                    BtLogActivity.this.bt.dial(BtLogActivity.this.mDialNum);
                }
            }
        }).setNegativeButton(R.string.str_bt_cancel, (DialogInterface.OnClickListener) null).create();
        dialog.show();
        Window dialogWindow = dialog.getWindow();
        Display d = getWindowManager().getDefaultDisplay();
        WindowManager.LayoutParams p = dialogWindow.getAttributes();
        p.width = (int) (((double) d.getWidth()) * 0.8d);
        p.height = (int) (((double) d.getHeight()) * 0.5d);
        p.gravity = 17;
        dialogWindow.setAttributes(p);
    }

    public void checkSelected() {
        for (int i = 0; i < 3; i++) {
            this.logButton[i].setSelected(false);
        }
        if (BtExe.mlistFilter == 2) {
            this.logButton[1].setSelected(true);
        }
        if (BtExe.mlistFilter == 1) {
            this.logButton[2].setSelected(true);
        }
        if (BtExe.mlistFilter == 4) {
            this.logButton[0].setSelected(true);
        }
    }

    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_received) {
            if (!this.mIsInMultiWindowMode) {
                this.mbSubFocus = 2;
                updateFocus(this.mFocusView[1]);
                logFocusClear();
            }
            BtExe.mlistFilter = 2;
            BtExe.flushFilterList();
        } else if (id == R.id.btn_dialed) {
            if (!this.mIsInMultiWindowMode) {
                this.mbSubFocus = 2;
                updateFocus(this.mFocusView[2]);
                logFocusClear();
            }
            BtExe.mlistFilter = 1;
            BtExe.flushFilterList();
        } else if (id == R.id.btn_missed) {
            if (!this.mIsInMultiWindowMode) {
                this.mbSubFocus = 2;
                updateFocus(this.mFocusView[0]);
                logFocusClear();
            }
            BtExe.mlistFilter = 4;
            BtExe.flushFilterList();
        } else if (id == R.id.btn_delete_log) {
            if (BtExe.mlistFilter == 2) {
                this.bt.delete("diallog", "type=?", new String[]{BtExe.INCOMING_TYPE});
            }
            if (BtExe.mlistFilter == 1) {
                this.bt.delete("diallog", "type=?", new String[]{BtExe.OUTGOING_TYPE});
            }
            if (BtExe.mlistFilter == 4) {
                this.bt.delete("diallog", "type=?", new String[]{BtExe.MISSED_TYPE});
            }
            BtExe.updateHistory();
            BtExe.flushFilterList();
        }
        if (this.mHistoryListAdapter != null) {
            this.mHistoryListAdapter.updateData(BtExe.mHistoryList);
        }
        if (this.mHistoryListAdapter1 != null) {
            this.mHistoryListAdapter1.updateData(BtExe.mHistoryList);
        }
        checkSelected();
    }

    class HistoryListAdapter extends BaseAdapter {
        private LayoutInflater mInflater;
        protected ArrayList<HashMap<String, Object>> mList = new ArrayList<>();
        private int mResource;
        private int mSelectIdx = -1;

        public HistoryListAdapter(Context context) {
            this.mInflater = LayoutInflater.from(context);
            this.mSelectIdx = -1;
        }

        public void updateData(ArrayList<HashMap<String, Object>> list) {
            this.mList.clear();
            this.mList.addAll(list);
            notifyDataSetChanged();
        }

        public void setSelect(int index) {
            this.mSelectIdx = index;
        }

        public int getSelect() {
            return this.mSelectIdx;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = this.mInflater.inflate(R.layout.bt_log_item, (ViewGroup) null);
                holder.phoneTextView = (TextView) convertView.findViewById(R.id.btLogNum);
                holder.timeTextView = (TextView) convertView.findViewById(R.id.btLogTime);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            if (BtLogActivity.this.mListLog.isItemChecked(position)) {
                convertView.setBackgroundResource(R.drawable.bt_list_dn);
            } else {
                convertView.setBackgroundResource(R.drawable.bt_list_btn);
            }
            String name = TXZResourceManager.STYLE_DEFAULT;
            String number = TXZResourceManager.STYLE_DEFAULT;
            String time = TXZResourceManager.STYLE_DEFAULT;
            if (position < this.mList.size()) {
                name = (String) this.mList.get(position).get(BtExe.ITEM_HISTORY_NAME);
                number = (String) this.mList.get(position).get(BtExe.ITEM_HISTORY_NUMBER);
                time = (String) this.mList.get(position).get(BtExe.ITEM_HISTORY_TIME);
            }
            if (name == null || name.isEmpty()) {
                holder.phoneTextView.setText(number);
            } else {
                holder.phoneTextView.setText(name);
            }
            holder.timeTextView.setText(time);
            return convertView;
        }

        public final class ViewHolder {
            public ImageView imageView;
            public TextView phoneTextView;
            public TextView timeTextView;

            public ViewHolder() {
            }
        }

        public int getCount() {
            if (this.mList != null) {
                return this.mList.size();
            }
            return 0;
        }

        public Object getItem(int arg0) {
            return null;
        }

        public long getItemId(int position) {
            return (long) position;
        }
    }

    class HistoryListAdapter1 extends BaseAdapter {
        private LayoutInflater mInflater;
        protected ArrayList<HashMap<String, Object>> mList = new ArrayList<>();
        private int mResource;
        private int mSelectIdx = -1;

        public HistoryListAdapter1(Context context) {
            this.mInflater = LayoutInflater.from(context);
            this.mSelectIdx = -1;
        }

        public void updateData(ArrayList<HashMap<String, Object>> list) {
            this.mList.clear();
            this.mList.addAll(list);
            notifyDataSetChanged();
        }

        public void setSelect(int index) {
            this.mSelectIdx = index;
        }

        public int getSelect() {
            return this.mSelectIdx;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = this.mInflater.inflate(R.layout.bt_log_item_s, (ViewGroup) null);
                holder.phoneTextView = (TextView) convertView.findViewById(R.id.btLogNum);
                holder.timeTextView = (TextView) convertView.findViewById(R.id.btLogTime);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            String name = TXZResourceManager.STYLE_DEFAULT;
            String number = TXZResourceManager.STYLE_DEFAULT;
            String time = TXZResourceManager.STYLE_DEFAULT;
            if (position < this.mList.size()) {
                name = (String) this.mList.get(position).get(BtExe.ITEM_HISTORY_NAME);
                number = (String) this.mList.get(position).get(BtExe.ITEM_HISTORY_NUMBER);
                time = (String) this.mList.get(position).get(BtExe.ITEM_HISTORY_TIME);
            }
            if (name == null || name.isEmpty()) {
                holder.phoneTextView.setText(number);
            } else {
                holder.phoneTextView.setText(name);
            }
            holder.timeTextView.setText(time);
            return convertView;
        }

        public final class ViewHolder {
            public ImageView imageView;
            public TextView phoneTextView;
            public TextView timeTextView;

            public ViewHolder() {
            }
        }

        public int getCount() {
            if (this.mList != null) {
                return this.mList.size();
            }
            return 0;
        }

        public Object getItem(int arg0) {
            return null;
        }

        public long getItemId(int position) {
            return (long) position;
        }
    }

    public void UserAll() {
        if (BtExe.isRefreshLog) {
            BtExe.flushFilterList();
            if (this.mHistoryListAdapter != null) {
                this.mHistoryListAdapter.updateData(BtExe.mHistoryList);
            }
            if (this.mHistoryListAdapter1 != null) {
                this.mHistoryListAdapter1.updateData(BtExe.mHistoryList);
            }
            BtExe.isRefreshLog = false;
        }
        if (!this.bt.isConnected()) {
            showActivity(1);
            finish();
        }
    }

    /* access modifiers changed from: package-private */
    public void dial(int id, boolean force) {
        String message;
        HashMap<String, Object> list = this.mHistoryListAdapter.mList.get(id);
        this.mDialNum = (String) list.get(BtExe.ITEM_HISTORY_NUMBER);
        if (force) {
            this.bt.dial(this.mDialNum);
            return;
        }
        this.mfgDialDlg = true;
        String name = (String) list.get(BtExe.ITEM_HISTORY_NAME);
        String number = (String) list.get(BtExe.ITEM_HISTORY_NUMBER);
        if (name == null || name.isEmpty()) {
            message = number;
        } else {
            message = name;
        }
        AlertDialog dialog = new AlertDialog.Builder(this).setTitle(R.string.str_bt_dial).setMessage(message).setPositiveButton(R.string.str_bt_ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if (BtExe.mCallSta > 1) {
                    BtCallMsgbox.GetInstance().Show(1);
                } else if (BtLogActivity.this.mDialNum != null && !BtLogActivity.this.mDialNum.isEmpty()) {
                    BtLogActivity.this.bt.dial(BtLogActivity.this.mDialNum);
                }
            }
        }).setNegativeButton(R.string.str_bt_cancel, (DialogInterface.OnClickListener) null).setOnDismissListener(new DialogInterface.OnDismissListener() {
            public void onDismiss(DialogInterface arg0) {
                BtLogActivity.this.mfgDialDlg = false;
            }
        }).create();
        dialog.show();
        Window dialogWindow = dialog.getWindow();
        Display d = getWindowManager().getDefaultDisplay();
        WindowManager.LayoutParams p = dialogWindow.getAttributes();
        p.width = (int) (((double) d.getWidth()) * 0.5d);
        p.height = (int) (((double) d.getHeight()) * 0.5d);
        p.gravity = 17;
        dialogWindow.setAttributes(p);
    }

    public void logFocusClear() {
        int position = this.mListLog.getCheckedItemPosition();
        if (position != -1) {
            this.mListLog.setItemChecked(position, false);
        }
    }

    /* access modifiers changed from: protected */
    public void EnterSubFocus() {
        this.mbSubFocus = 2;
        updateFocus(this.mFocusView[2]);
        BtExe.mlistFilter = 1;
        checkSelected();
        int position = this.mListLog.getCheckedItemPosition();
        if (position != -1) {
            this.mListLog.setItemChecked(position, false);
        }
    }

    /* access modifiers changed from: protected */
    public void LogPrev() {
        int position;
        Log.d(TAG, "LogPrev");
        if (this.mListLog.getVisibility() == 0 && this.mListLog.getCount() >= 1 && (position = this.mListLog.getCheckedItemPosition()) > 0) {
            int firstVisiable = this.mListLog.getFirstVisiblePosition();
            int position2 = position - 1;
            View firstView = this.mListLog.getChildAt(position2 - firstVisiable);
            this.mListLog.setItemChecked(position2, true);
            if (position2 < firstVisiable || firstView == null || firstView.getY() < 0.0f) {
                this.mListLog.setSelection(position2);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void LogNext() {
        Log.d(TAG, "LogNext");
        if (this.mListLog.getVisibility() == 0 && this.mListLog.getCount() >= 1) {
            int position = this.mListLog.getCheckedItemPosition();
            if (position + 1 < this.mListLog.getCount()) {
                int firstVisiable = this.mListLog.getFirstVisiblePosition();
                int lastVisiable = this.mListLog.getLastVisiblePosition();
                int position2 = position + 1;
                View lastView = this.mListLog.getChildAt(position2 - firstVisiable);
                this.mListLog.setItemChecked(position2, true);
                if (lastView != null) {
                    int y1 = ((int) this.mListLog.getY()) + lastView.getBottom();
                    Log.d(TAG, "lastVisiable = " + lastVisiable + ", getBottom = " + lastView.getBottom() + ", y1 = " + y1 + ", y2 = " + this.mListLog.getBottom());
                }
                if (position2 > lastVisiable || lastView == null || lastView.getBottom() > this.mListLog.getBottom()) {
                    this.mListLog.setSelection(position2);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void LogFocus() {
        Log.d(TAG, "LogFocus");
        if (this.mListLog.getVisibility() == 0 && this.mListLog.getCount() >= 1) {
            int position = this.mListLog.getCheckedItemPosition();
            if (position != -1) {
                this.mListLog.setItemChecked(this.mListLog.getFirstVisiblePosition(), true);
            } else if (position + 1 < this.mListLog.getCount()) {
                int firstVisiable = this.mListLog.getFirstVisiblePosition();
                int lastVisiable = this.mListLog.getLastVisiblePosition();
                int position2 = position + 1;
                View lastView = this.mListLog.getChildAt(position2 - firstVisiable);
                this.mListLog.setItemChecked(position2, true);
                if (lastView != null) {
                    int y1 = ((int) this.mListLog.getY()) + lastView.getBottom();
                    Log.d(TAG, "lastVisiable = " + lastVisiable + ", getBottom = " + lastView.getBottom() + ", y1 = " + y1 + ", y2 = " + this.mListLog.getBottom());
                }
                if (position2 > lastVisiable || lastView == null || lastView.getBottom() > this.mListLog.getBottom()) {
                    this.mListLog.setSelection(position2);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void LogCenter() {
        int position;
        Log.d(TAG, "LogCenter");
        if (this.mListLog.getVisibility() == 0 && this.mListLog.getCount() >= 1 && (position = this.mListLog.getCheckedItemPosition()) >= 0) {
            dial(position, true);
        }
    }

    /* access modifiers changed from: protected */
    public int GetCallHistoryFocusIndex() {
        if (this.mOldFocusView == null) {
            return 2;
        }
        for (int j = 0; j < this.mFocusView.length; j++) {
            if (this.mOldFocusView == this.mFocusView[j]) {
                return j;
            }
        }
        return 2;
    }

    /* access modifiers changed from: protected */
    public void CallHistoryPrev() {
        int index;
        int index2 = GetCallHistoryFocusIndex();
        if (index2 > 0) {
            index = index2 - 1;
        } else {
            index = this.mFocusView.length - 1;
        }
        Log.d(TAG, "GetConnectFocusIndex LtPrev = " + index);
        updateFocus(this.mFocusView[index]);
    }

    /* access modifiers changed from: protected */
    public void CallHistoryNext() {
        int index;
        int index2 = GetCallHistoryFocusIndex();
        if (index2 < this.mFocusView.length - 1) {
            index = index2 + 1;
        } else {
            index = 0;
        }
        Log.d(TAG, "GetConnectFocusIndex LtNext = " + index);
        updateFocus(this.mFocusView[index]);
    }

    /* access modifiers changed from: protected */
    public void CallHistoryCenter() {
        this.mFocusView[GetCallHistoryFocusIndex()].performClick();
    }

    /* access modifiers changed from: protected */
    public boolean DealSubKey(int key) {
        int position;
        if (this.mfgDialDlg) {
            return true;
        }
        if (this.mOrientation == 1) {
            return false;
        }
        switch (key) {
            case 19:
                if (this.mbSubFocus == 1) {
                    LogPrev();
                    return true;
                } else if (this.mbSubFocus != 2) {
                    return true;
                } else {
                    CallHistoryPrev();
                    CallHistoryCenter();
                    return true;
                }
            case 20:
                if (this.mbSubFocus == 1) {
                    LogNext();
                    return true;
                } else if (this.mbSubFocus != 2) {
                    return true;
                } else {
                    CallHistoryNext();
                    CallHistoryCenter();
                    return true;
                }
            case 21:
                if (this.mbSubFocus == 2) {
                    updateFocus(this.mFocusView[2]);
                    BtExe.mlistFilter = 1;
                    checkSelected();
                    int position2 = this.mListLog.getCheckedItemPosition();
                    if (position2 == -1) {
                        return true;
                    }
                    this.mListLog.setItemChecked(position2, false);
                    return true;
                } else if (this.mbSubFocus != 3 || (position = this.mListLog.getCheckedItemPosition()) == -1) {
                    return true;
                } else {
                    this.mListLog.setItemChecked(position, false);
                    return true;
                }
            case 22:
                if (this.mbSubFocus == 1) {
                    LogFocus();
                    return true;
                } else if (this.mbSubFocus != 2) {
                    return true;
                } else {
                    updateFocus(this.mFocusView[2]);
                    BtExe.mlistFilter = 1;
                    checkSelected();
                    int position3 = this.mListLog.getCheckedItemPosition();
                    if (position3 == -1) {
                        return true;
                    }
                    this.mListLog.setItemChecked(position3, false);
                    return true;
                }
            case 23:
                if (this.mbSubFocus == 1) {
                    LogCenter();
                    return true;
                } else if (this.mbSubFocus != 2) {
                    return true;
                } else {
                    CallHistoryCenter();
                    return true;
                }
            default:
                return true;
        }
    }
}
