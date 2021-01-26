package com.ts.bt;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.bt.BtExe;
import com.txznet.sdk.TXZResourceManager;
import java.util.ArrayList;

@SuppressLint({"NewApi", "Override"})
public class BtPhoneBookActivity extends BtBaseActivity implements View.OnClickListener, View.OnLongClickListener, UserCallBack {
    public static final int BT_ACTIVITY_ID = 4;
    private static final String TAG = "BtPhonebookActivity";
    /* access modifiers changed from: private */
    public BtExe bt = BtExe.getBtInstance();
    /* access modifiers changed from: private */
    public boolean isBtCountry = true;
    private Button mBtnClear;
    private Button mBtnSearch;
    private Button mBtnSync;
    /* access modifiers changed from: private */
    public String mDialNum;
    private EditText mEdit;
    ListView mList;
    /* access modifiers changed from: private */
    public PBListAdapter mPBListAdapter;
    private int mPbSta;
    private AdapterView.OnItemClickListener mPhonebookListClickListener = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView<?> adapterView, View V, int position, long arg3) {
            BtPhoneBookActivity.this.dial(position, false);
            BtPhoneBookActivity.this.mPBListAdapter.setSelect(position);
            BtPhoneBookActivity.this.mPBListAdapter.notifyDataSetChanged();
            BtPhoneBookActivity.this.updateFocus(BtPhoneBookActivity.this.mLtView[BtPhoneBookActivity.this.mIcoSel]);
            BtPhoneBookActivity.this.mbSubFocus = 1;
            BtPhoneBookActivity.this.mList.setItemChecked(position, true);
        }
    };
    /* access modifiers changed from: private */
    public BtSearchAdapter1 mSearchAdapter;
    private RelativeLayout mSyncLayout;
    private int mSyncNum;
    private TextView mSyncTextView;
    private Toast mToast = null;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mIsInMultiWindowMode = isInMultiWindowMode();
        updateLayout(this.mIsInMultiWindowMode);
    }

    /* access modifiers changed from: package-private */
    public void updateLayout(boolean isInMultiWindowMode) {
        if (isInMultiWindowMode) {
            getWindow().setSoftInputMode(48);
            setContentView(R.layout.activity_bt_phonebook_s);
            initView1();
            return;
        }
        setContentView(R.layout.activity_bt_phonebook);
        initView();
    }

    public void onMultiWindowModeChanged(boolean isInMultiWindowMode, Configuration newConfig) {
        super.onMultiWindowModeChanged(isInMultiWindowMode, newConfig);
        this.mIsInMultiWindowMode = isInMultiWindowMode;
        updateMultiChange(isInMultiWindowMode);
    }

    /* access modifiers changed from: package-private */
    public void updateMultiChange(boolean isInMultiWindowMode) {
        updateLayout(isInMultiWindowMode);
        SubItemsInit(this, 4);
        this.isBtCountry = this.bt.isBtCountry();
        resetData();
        updateUI();
        MainTask.GetInstance().SetUserCallBack(this);
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        SubItemsInit(this, 4);
        if (this.isShowActivity && !this.mIsInMultiWindowMode) {
            EnterSubFocus();
        }
        this.isBtCountry = this.bt.isBtCountry();
        resetData();
        updateUI();
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

    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.bt_phonebook_dele) {
            if (BtExe.getAddr() != null && !BtExe.getAddr().isEmpty()) {
                this.bt.delete("phonebook", "addr=?", new String[]{BtExe.getAddr()});
            }
            this.bt.pbClear();
        } else if (id == R.id.bt_phonebook_download) {
            if (BtExe.getAddr() != null && !BtExe.getAddr().isEmpty()) {
                this.bt.delete("phonebook", "addr=?", new String[]{BtExe.getAddr()});
            }
            this.bt.downLoad();
        } else if (id == R.id.bt_phonebook_search && BtExe.mListPb != null && BtExe.mListPb.size() > 0) {
            showActivity(8);
        }
        if (!this.mIsInMultiWindowMode) {
            this.mbSubFocus = 2;
            updateFocus(v);
        }
    }

    private void initView() {
        this.mBtnClear = (Button) findViewById(R.id.bt_phonebook_dele);
        this.mBtnSync = (Button) findViewById(R.id.bt_phonebook_download);
        this.mBtnSearch = (Button) findViewById(R.id.bt_phonebook_search);
        this.mBtnClear.setOnClickListener(this);
        this.mBtnSync.setOnClickListener(this);
        this.mBtnSearch.setOnClickListener(this);
        this.mSyncLayout = (RelativeLayout) findViewById(R.id.download_phonebook_layout);
        ((ProgressBar) findViewById(R.id.progressbar_download_phonebook)).setIndeterminate(false);
        this.mSyncTextView = (TextView) findViewById(R.id.tv_download_phonebook_text);
        this.mList = (ListView) findViewById(R.id.phonebook_listview);
        this.mPBListAdapter = new PBListAdapter(this);
        this.mList.setAdapter(this.mPBListAdapter);
        this.mList.setOnItemClickListener(this.mPhonebookListClickListener);
        this.mFocusView = new View[3];
        this.mFocusView[0] = this.mBtnSync;
        this.mFocusView[1] = this.mBtnSearch;
        this.mFocusView[2] = this.mBtnClear;
    }

    /* access modifiers changed from: package-private */
    public void updateSearchList() {
        Log.d(TAG, "PbSearch begin");
        ArrayList<BtExe.SearchItem> list = new ArrayList<>();
        for (int i = 0; i < BtExe.mListPb.size(); i++) {
            BtExe.SearchItem si = new BtExe.SearchItem();
            si.pb = BtExe.mListPb.get(i);
            si.MatchPos = 0;
            list.add(si);
        }
        this.mSearchAdapter.updateData(list);
    }

    private void initView1() {
        this.mList = (ListView) findViewById(R.id.phonebook_listview);
        this.mSearchAdapter = new BtSearchAdapter1(this);
        this.mList.setAdapter(this.mSearchAdapter);
        updateSearchList();
        this.mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                BtPhoneBookActivity.this.dial1(position);
            }
        });
        this.mEdit = (EditText) findViewById(R.id.search);
        this.mEdit.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(s)) {
                    BtPhoneBookActivity.this.updateSearchList();
                    return;
                }
                Log.d(BtPhoneBookActivity.TAG, "onTextChanged " + s);
                BtExe.PbSearch(s.toString());
                BtPhoneBookActivity.this.mSearchAdapter.updateData(BtExe.mListSearch);
                BtPhoneBookActivity.this.showMessage(" " + BtExe.mListSearch.size());
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void afterTextChanged(Editable s) {
            }
        });
        this.mSyncLayout = (RelativeLayout) findViewById(R.id.download_phonebook_layout);
        ((ProgressBar) findViewById(R.id.progressbar_download_phonebook)).setIndeterminate(false);
        this.mSyncTextView = (TextView) findViewById(R.id.tv_download_phonebook_text);
    }

    /* access modifiers changed from: protected */
    public void showMessage(String msg) {
        if (this.mToast == null) {
            this.mToast = Toast.makeText(this, msg, 0);
        } else {
            this.mToast.setText(msg);
        }
        this.mToast.show();
    }

    public boolean onLongClick(View view) {
        return false;
    }

    /* access modifiers changed from: package-private */
    public void dial(int id) {
        String name;
        String name2;
        BtExe.PbItem list = (BtExe.PbItem) this.mPBListAdapter.mList.get(id);
        if (this.isBtCountry) {
            name2 = list.name;
        } else {
            String first_name = list.first_name;
            String middle_name = list.middle_name;
            String name3 = String.valueOf(TXZResourceManager.STYLE_DEFAULT) + list.given_name;
            if (!name3.isEmpty()) {
                name = String.valueOf(name3) + " " + middle_name;
            } else {
                name = String.valueOf(name3) + middle_name;
            }
            if (!name.isEmpty()) {
                name2 = String.valueOf(name) + " " + first_name;
            } else {
                name2 = String.valueOf(name) + first_name;
            }
        }
        this.mDialNum = list.num;
        AlertDialog dialog = new AlertDialog.Builder(this).setTitle(R.string.str_bt_dial).setMessage(name2).setPositiveButton(R.string.str_bt_ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if (BtPhoneBookActivity.this.mDialNum != null && !BtPhoneBookActivity.this.mDialNum.isEmpty()) {
                    BtPhoneBookActivity.this.bt.dial(BtPhoneBookActivity.this.mDialNum);
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
        String name;
        String name2;
        BtExe.PbItem list = this.mSearchAdapter.mSearchList.get(id).pb;
        if (this.isBtCountry) {
            name2 = list.name;
        } else {
            String first_name = list.first_name;
            String middle_name = list.middle_name;
            String name3 = String.valueOf(TXZResourceManager.STYLE_DEFAULT) + list.given_name;
            if (!name3.isEmpty()) {
                name = String.valueOf(name3) + " " + middle_name;
            } else {
                name = String.valueOf(name3) + middle_name;
            }
            if (!name.isEmpty()) {
                name2 = String.valueOf(name) + " " + first_name;
            } else {
                name2 = String.valueOf(name) + first_name;
            }
        }
        this.mDialNum = list.num;
        AlertDialog dialog = new AlertDialog.Builder(this).setTitle(R.string.str_bt_dial).setMessage(name2).setPositiveButton(R.string.str_bt_ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if (BtPhoneBookActivity.this.mDialNum != null && !BtPhoneBookActivity.this.mDialNum.isEmpty()) {
                    BtPhoneBookActivity.this.bt.dial(BtPhoneBookActivity.this.mDialNum);
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

    class PBListAdapter extends BaseAdapter {
        private LayoutInflater mInflater;
        /* access modifiers changed from: private */
        public ArrayList<BtExe.PbItem> mList = new ArrayList<>();
        private int mSelectIdx = -1;

        public PBListAdapter(Context context) {
            this.mInflater = LayoutInflater.from(context);
            this.mSelectIdx = -1;
        }

        public void updateData(ArrayList<BtExe.PbItem> list) {
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
            String name;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = this.mInflater.inflate(R.layout.bt_pb_item, (ViewGroup) null);
                holder.nameTextView = (TextView) convertView.findViewById(R.id.item_phonebook_name);
                holder.phoneTextView = (TextView) convertView.findViewById(R.id.item_phonebook_number);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            if (BtPhoneBookActivity.this.mList.isItemChecked(position)) {
                convertView.setBackgroundResource(R.drawable.bt_list_dn);
            } else {
                convertView.setBackgroundResource(R.drawable.bt_list_btn);
            }
            String name2 = TXZResourceManager.STYLE_DEFAULT;
            String number = TXZResourceManager.STYLE_DEFAULT;
            if (position < this.mList.size()) {
                number = this.mList.get(position).num;
                if (BtPhoneBookActivity.this.isBtCountry) {
                    name2 = this.mList.get(position).name;
                } else {
                    String first_name = this.mList.get(position).first_name;
                    String middle_name = this.mList.get(position).middle_name;
                    String name3 = String.valueOf(name2) + this.mList.get(position).given_name;
                    if (!name3.isEmpty()) {
                        name = String.valueOf(name3) + " " + middle_name;
                    } else {
                        name = String.valueOf(name3) + middle_name;
                    }
                    if (!name.isEmpty()) {
                        name2 = String.valueOf(name) + " " + first_name;
                    } else {
                        name2 = String.valueOf(name) + first_name;
                    }
                }
            }
            holder.nameTextView.setText(name2);
            holder.phoneTextView.setText(number);
            return convertView;
        }

        public final class ViewHolder {
            public TextView nameTextView;
            public TextView phoneTextView;

            public ViewHolder() {
            }
        }

        public int getCount() {
            if (this.mList != null) {
                return this.mList.size();
            }
            return 0;
        }

        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
            return (long) position;
        }
    }

    /* access modifiers changed from: package-private */
    public void showProgress(boolean show) {
        if (this.mSyncLayout == null) {
            return;
        }
        if (show) {
            this.mList.setVisibility(4);
            this.mSyncLayout.setVisibility(0);
            if (this.mBtnSync != null) {
                this.mBtnSync.setVisibility(4);
                this.mBtnClear.setVisibility(4);
                this.mBtnSearch.setVisibility(4);
                updateFocus(this.mLtView[this.mIcoSel]);
                return;
            }
            return;
        }
        this.mList.setVisibility(0);
        this.mSyncLayout.setVisibility(8);
        if (this.mBtnSync != null) {
            this.mBtnSync.setVisibility(0);
            this.mBtnClear.setVisibility(0);
            this.mBtnSearch.setVisibility(0);
            this.mbSubFocus = 2;
            updateFocus(this.mFocusView[0]);
        }
    }

    public void UserAll() {
        if (!this.bt.isConnected()) {
            showActivity(1);
            finish();
            return;
        }
        updateUI();
    }

    /* access modifiers changed from: package-private */
    public void resetData() {
        this.mSyncNum = -1;
        this.mPbSta = 255;
        this.mDialNum = null;
        showProgress(false);
    }

    /* access modifiers changed from: package-private */
    public void updateUI() {
        if (this.mSyncTextView != null) {
            if (this.mPbSta != BtExe.mPbStatus) {
                if (BtExe.mPbStatus == 1) {
                    showProgress(true);
                    this.mSyncTextView.setText(new StringBuilder(String.valueOf(BtExe.mSyncNum)).toString());
                } else {
                    showProgress(false);
                }
                this.mPbSta = BtExe.mPbStatus;
                Log.e(TAG, "updateUI " + BtExe.mPbStatus);
                Log.d("lh", "size = " + BtExe.mListPb.size());
                if (this.mPBListAdapter != null) {
                    this.mPBListAdapter.updateData(BtExe.mListPb);
                }
                if (this.mSearchAdapter != null) {
                    updateSearchList();
                }
            }
            if (BtExe.mPbStatus == 1 && this.mSyncNum != BtExe.mSyncNum) {
                this.mSyncNum = BtExe.mSyncNum;
                this.mSyncTextView.setText(new StringBuilder(String.valueOf(BtExe.mSyncNum)).toString());
            }
        }
    }

    class BtSearchAdapter1 extends BaseAdapter {
        public LayoutInflater layoutInflater;
        public ArrayList<BtExe.SearchItem> mSearchList = new ArrayList<>();
        private int nSel = -1;

        public BtSearchAdapter1(Context context) {
            this.layoutInflater = (LayoutInflater) context.getSystemService("layout_inflater");
        }

        public void updateData(ArrayList<BtExe.SearchItem> list) {
            this.mSearchList.clear();
            this.mSearchList.addAll(list);
            notifyDataSetChanged();
        }

        /* access modifiers changed from: package-private */
        public void setSel(int sel) {
            this.nSel = sel;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            String name;
            String name2;
            Log.e("getView", "index = " + position);
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = this.layoutInflater.inflate(R.layout.bt_pb_item_s, (ViewGroup) null);
                holder.nameTextView = (TextView) convertView.findViewById(R.id.item_phonebook_name);
                holder.phoneTextView = (TextView) convertView.findViewById(R.id.item_phonebook_number);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            if (this.mSearchList == null || this.mSearchList.size() <= position) {
                holder.nameTextView.setText("10086");
                holder.phoneTextView.setText(TXZResourceManager.STYLE_DEFAULT);
            } else {
                String number = this.mSearchList.get(position).pb.num;
                if (BtPhoneBookActivity.this.isBtCountry) {
                    name2 = this.mSearchList.get(position).pb.name;
                } else {
                    String first_name = this.mSearchList.get(position).pb.first_name;
                    String middle_name = this.mSearchList.get(position).pb.middle_name;
                    String name3 = String.valueOf(TXZResourceManager.STYLE_DEFAULT) + this.mSearchList.get(position).pb.given_name;
                    if (!name3.isEmpty()) {
                        name = String.valueOf(name3) + " " + middle_name;
                    } else {
                        name = String.valueOf(name3) + middle_name;
                    }
                    if (!name.isEmpty()) {
                        name2 = String.valueOf(name) + " " + first_name;
                    } else {
                        name2 = String.valueOf(name) + first_name;
                    }
                }
                holder.nameTextView.setText(name2);
                holder.phoneTextView.setText(number);
            }
            return convertView;
        }

        public final class ViewHolder {
            public TextView nameTextView;
            public TextView phoneTextView;

            public ViewHolder() {
            }
        }

        public final int getCount() {
            if (this.mSearchList != null) {
                return this.mSearchList.size();
            }
            return 0;
        }

        public final Object getItem(int position) {
            return null;
        }

        public final long getItemId(int position) {
            return (long) position;
        }
    }

    /* access modifiers changed from: package-private */
    public void dial(int id, boolean force) {
        String name;
        String name2;
        BtExe.PbItem list = (BtExe.PbItem) this.mPBListAdapter.mList.get(id);
        if (this.isBtCountry) {
            name2 = list.name;
        } else {
            String first_name = list.first_name;
            String middle_name = list.middle_name;
            String name3 = String.valueOf(TXZResourceManager.STYLE_DEFAULT) + list.given_name;
            if (!name3.isEmpty()) {
                name = String.valueOf(name3) + " " + middle_name;
            } else {
                name = String.valueOf(name3) + middle_name;
            }
            if (!name.isEmpty()) {
                name2 = String.valueOf(name) + " " + first_name;
            } else {
                name2 = String.valueOf(name) + first_name;
            }
        }
        this.mDialNum = list.num;
        if (force) {
            Log.d("lh", "num = " + this.mDialNum);
            this.bt.dial(this.mDialNum);
            return;
        }
        this.mfgDialDlg = true;
        AlertDialog dialog = new AlertDialog.Builder(this).setTitle(R.string.str_bt_dial).setMessage(name2).setPositiveButton(R.string.str_bt_ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if (BtExe.mCallSta > 1) {
                    BtCallMsgbox.GetInstance().Show(1);
                } else if (BtPhoneBookActivity.this.mDialNum != null && !BtPhoneBookActivity.this.mDialNum.isEmpty()) {
                    BtPhoneBookActivity.this.bt.dial(BtPhoneBookActivity.this.mDialNum);
                }
            }
        }).setNegativeButton(R.string.str_bt_cancel, (DialogInterface.OnClickListener) null).setOnDismissListener(new DialogInterface.OnDismissListener() {
            public void onDismiss(DialogInterface arg0) {
                BtPhoneBookActivity.this.mfgDialDlg = false;
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

    public void enterSubWin(Class<?> cls) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        startActivity(intent);
        overridePendingTransition(0, 0);
    }

    /* access modifiers changed from: protected */
    public void EnterSubFocus() {
        this.mbSubFocus = 2;
        updateFocus(this.mFocusView[0]);
        int position = this.mList.getCheckedItemPosition();
        if (position != -1) {
            this.mList.setItemChecked(position, false);
        }
    }

    /* access modifiers changed from: protected */
    public void PbPrev() {
        int position;
        Log.d(TAG, "PbPrev");
        if (this.mList.getVisibility() == 0 && this.mList.getCount() >= 1 && (position = this.mList.getCheckedItemPosition()) > 0) {
            int firstVisiable = this.mList.getFirstVisiblePosition();
            int position2 = position - 1;
            View firstView = this.mList.getChildAt(position2 - firstVisiable);
            this.mList.setItemChecked(position2, true);
            if (position2 < firstVisiable || firstView == null || firstView.getY() < 0.0f) {
                this.mList.setSelection(position2);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void PbNext() {
        Log.d(TAG, "PbNext");
        if (this.mList.getVisibility() == 0 && this.mList.getCount() >= 1) {
            int position = this.mList.getCheckedItemPosition();
            if (position + 1 < this.mList.getCount()) {
                int firstVisiable = this.mList.getFirstVisiblePosition();
                int lastVisiable = this.mList.getLastVisiblePosition();
                int position2 = position + 1;
                View lastView = this.mList.getChildAt(position2 - firstVisiable);
                this.mList.setItemChecked(position2, true);
                if (lastView != null) {
                    int y1 = ((int) this.mList.getY()) + lastView.getBottom();
                    Log.d(TAG, "lastVisiable = " + lastVisiable + ", getBottom = " + lastView.getBottom() + ", y1 = " + y1 + ", y2 = " + this.mList.getBottom());
                }
                if (position2 > lastVisiable || lastView == null || lastView.getBottom() > this.mList.getBottom()) {
                    this.mList.setSelection(position2);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void PbFocus() {
        Log.d(TAG, "PbFocus");
        if (this.mList.getVisibility() == 0 && this.mList.getCount() >= 1) {
            int position = this.mList.getCheckedItemPosition();
            if (position == -1) {
                position = this.mList.getFirstVisiblePosition();
                this.mList.setItemChecked(position, true);
            } else if (position < this.mList.getCount()) {
                int firstVisiable = this.mList.getFirstVisiblePosition();
                int lastVisiable = this.mList.getLastVisiblePosition();
                View lastView = this.mList.getChildAt(position - firstVisiable);
                this.mList.setItemChecked(position, true);
                if (lastView != null) {
                    int y1 = ((int) this.mList.getY()) + lastView.getBottom();
                    Log.d(TAG, "lastVisiable = " + lastVisiable + ", getBottom = " + lastView.getBottom() + ", y1 = " + y1 + ", y2 = " + this.mList.getBottom());
                }
                if (position > lastVisiable || lastView == null || lastView.getBottom() > this.mList.getBottom()) {
                    this.mList.setSelection(position);
                }
            }
            Log.d(TAG, "position = " + position);
        }
    }

    /* access modifiers changed from: protected */
    public void PbCenter() {
        int position;
        Log.d(TAG, "PbCenter");
        if (this.mList.getVisibility() == 0 && this.mList.getCount() >= 1 && (position = this.mList.getCheckedItemPosition()) >= 0) {
            dial(position, true);
        }
    }

    /* access modifiers changed from: protected */
    public int GetPhoneBookFocusIndex() {
        if (this.mOldFocusView == null) {
            return 0;
        }
        for (int j = 0; j < this.mFocusView.length; j++) {
            if (this.mOldFocusView == this.mFocusView[j]) {
                return j;
            }
        }
        return 0;
    }

    /* access modifiers changed from: protected */
    public void PhoneBookPrev() {
        int index;
        int index2 = GetPhoneBookFocusIndex();
        if (index2 > 0) {
            index = index2 - 1;
        } else {
            index = this.mFocusView.length - 1;
        }
        Log.d(TAG, "GetConnectFocusIndex LtPrev = " + index);
        updateFocus(this.mFocusView[index]);
    }

    /* access modifiers changed from: protected */
    public void PhoneBookNext() {
        int index;
        int index2 = GetPhoneBookFocusIndex();
        if (index2 < this.mFocusView.length - 1) {
            index = index2 + 1;
        } else {
            index = 0;
        }
        Log.d(TAG, "GetConnectFocusIndex LtNext = " + index);
        updateFocus(this.mFocusView[index]);
    }

    /* access modifiers changed from: protected */
    public void PhoneBookCenter() {
        this.mFocusView[GetPhoneBookFocusIndex()].performClick();
    }

    /* access modifiers changed from: protected */
    public boolean DealSubKey(int key) {
        int position;
        if (this.mfgDialDlg || this.mIsInMultiWindowMode) {
            return false;
        }
        switch (key) {
            case 19:
                if (this.mbSubFocus != 1) {
                    if (this.mbSubFocus == 2) {
                        PhoneBookPrev();
                        break;
                    }
                } else {
                    PbPrev();
                    break;
                }
                break;
            case 20:
                if (this.mbSubFocus != 1) {
                    if (this.mbSubFocus == 2) {
                        PhoneBookNext();
                        break;
                    }
                } else {
                    PbNext();
                    break;
                }
                break;
            case 21:
                if (this.mbSubFocus != 2) {
                    if (this.mbSubFocus == 3 && (position = this.mList.getCheckedItemPosition()) != -1) {
                        this.mList.setItemChecked(position, false);
                        break;
                    }
                } else {
                    updateFocus(this.mFocusView[0]);
                    int position2 = this.mList.getCheckedItemPosition();
                    if (position2 != -1) {
                        this.mList.setItemChecked(position2, false);
                        break;
                    }
                }
                break;
            case 22:
                if (this.mbSubFocus != 2) {
                    if (this.mbSubFocus == 1) {
                        PbFocus();
                        break;
                    }
                } else {
                    updateFocus(this.mFocusView[0]);
                    int position3 = this.mList.getCheckedItemPosition();
                    if (position3 != -1) {
                        this.mList.setItemChecked(position3, false);
                        break;
                    }
                }
                break;
            case 23:
                if (this.mbSubFocus != 1) {
                    if (this.mbSubFocus == 2) {
                        PhoneBookCenter();
                        break;
                    }
                } else {
                    PbCenter();
                    break;
                }
                break;
        }
        return true;
    }
}
