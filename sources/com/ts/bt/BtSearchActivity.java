package com.ts.bt;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.ts.MainUI.R;
import com.ts.bt.BtExe;
import java.util.ArrayList;

@SuppressLint({"NewApi"})
public class BtSearchActivity extends BtBaseActivity implements View.OnClickListener {
    public static final int BT_ACTIVITY_ID = 8;
    private static final String TAG = "BtSearchActivity";
    /* access modifiers changed from: private */
    public boolean isBtCountry = true;
    /* access modifiers changed from: private */
    public BtSearchAdapter mAdapter;
    private int mDialId;
    /* access modifiers changed from: private */
    public String mDialNum;
    private EditText mEdit;
    /* access modifiers changed from: private */
    public ListView mList;
    /* access modifiers changed from: private */
    public BtSearchAdapter1 mSearchAdapter;
    private String mStrSiTotal;
    private Toast mToast = null;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(48);
        setContentView(R.layout.activity_bt_search);
        initView();
    }

    /* access modifiers changed from: package-private */
    public void initView() {
        this.mList = (ListView) findViewById(R.id.btListSearch);
        this.mAdapter = new BtSearchAdapter(this);
        this.mList.setAdapter(this.mAdapter);
        this.mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                BtSearchActivity.this.dail(position, false);
                BtSearchActivity.this.mAdapter.notifyDataSetChanged();
                BtSearchActivity.this.updateFocus(BtSearchActivity.this.mLtView[BtSearchActivity.this.mIcoSel]);
                BtSearchActivity.this.mbSubFocus = 2;
                BtSearchActivity.this.mList.setItemChecked(position, true);
            }
        });
        this.mEdit = (EditText) findViewById(R.id.search);
        this.mEdit.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d(BtSearchActivity.TAG, "onTextChanged " + s);
                BtExe.PbSearch(s.toString());
                BtSearchActivity.this.mAdapter.updateData(BtExe.mListSearch);
                if (BtSearchActivity.this.mbSubFocus == 2 && BtExe.mListSearch.size() > 0) {
                    BtSearchActivity.this.mList.setItemChecked(0, true);
                }
                BtSearchActivity.this.showMessage(" " + BtExe.mListSearch.size());
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void afterTextChanged(Editable s) {
            }
        });
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
                BtSearchActivity.this.dail1(position);
            }
        });
        this.mEdit = (EditText) findViewById(R.id.search);
        this.mEdit.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d(BtSearchActivity.TAG, "onTextChanged " + s);
                BtExe.PbSearch(s.toString());
                BtSearchActivity.this.mSearchAdapter.updateData(BtExe.mListSearch);
                BtSearchActivity.this.showMessage(" " + BtExe.mListSearch.size());
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void afterTextChanged(Editable s) {
            }
        });
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

    /* access modifiers changed from: package-private */
    public void dail1(int id) {
        BtExe.SearchItem item;
        String name;
        String name2;
        this.mDialId = id;
        if (this.mSearchAdapter != null && this.mSearchAdapter.mSearchList.size() != 0 && id <= this.mSearchAdapter.mSearchList.size() && (item = this.mSearchAdapter.mSearchList.get(id)) != null) {
            String str = "闂備浇顫夐悘姘跺礉瀹ュ棙娅犻柨鐕傛嫹 " + item.pb.name + " ?";
            if (this.isBtCountry) {
                name2 = item.pb.name;
            } else {
                String first_name = item.pb.first_name;
                String middle_name = item.pb.middle_name;
                String name3 = String.valueOf("") + item.pb.given_name;
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
            this.mDialNum = item.pb.num;
            new AlertDialog.Builder(this).setTitle(R.string.str_bt_dial).setMessage(name2).setPositiveButton(R.string.str_bt_ok, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    if (BtSearchActivity.this.mDialNum != null) {
                        BtSearchActivity.this.bt.dial(BtSearchActivity.this.mDialNum);
                    }
                }
            }).setNegativeButton(R.string.str_bt_cancel, (DialogInterface.OnClickListener) null).show();
        }
    }

    /* access modifiers changed from: package-private */
    public void dail(int id) {
        BtExe.SearchItem item;
        String name;
        String name2;
        this.mDialId = id;
        if (BtExe.mListSearch != null && id <= BtExe.mListSearch.size() && (item = BtExe.mListSearch.get(id)) != null) {
            String str = "闂備浇顫夐悘姘跺礉瀹ュ棙娅犻柨鐕傛嫹 " + item.pb.name + " ?";
            if (this.isBtCountry) {
                name2 = item.pb.name;
            } else {
                String first_name = item.pb.first_name;
                String middle_name = item.pb.middle_name;
                String name3 = String.valueOf("") + item.pb.given_name;
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
            this.mDialNum = item.pb.num;
            new AlertDialog.Builder(this).setTitle(R.string.str_bt_dial).setMessage(name2).setPositiveButton(R.string.str_bt_ok, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    if (BtSearchActivity.this.mDialNum != null) {
                        BtSearchActivity.this.bt.dial(BtSearchActivity.this.mDialNum);
                    }
                }
            }).setNegativeButton(R.string.str_bt_cancel, (DialogInterface.OnClickListener) null).show();
        }
    }

    public void enterSubWin(Class<?> cls) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        startActivity(intent);
        overridePendingTransition(0, 0);
    }

    public void onClick(View v) {
        int id = v.getId();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        SubItemsInit(this, 8);
        if (this.isShowActivity) {
            EnterSubFocus();
        }
        this.isBtCountry = this.bt.isBtCountry();
        resetData();
        super.onResume();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
    }

    /* access modifiers changed from: package-private */
    public void resetData() {
        this.mDialNum = null;
    }

    class BtSearchAdapter extends BaseAdapter {
        public LayoutInflater layoutInflater;
        public ArrayList<BtExe.SearchItem> mSearchList = new ArrayList<>();
        private int nSel = -1;

        public BtSearchAdapter(Context context) {
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
                convertView = this.layoutInflater.inflate(R.layout.bt_search_item, (ViewGroup) null);
                holder.nameTextView = (TextView) convertView.findViewById(R.id.btPbName);
                holder.phoneTextView = (TextView) convertView.findViewById(R.id.btPbNum);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            if (BtSearchActivity.this.mList.isItemChecked(position)) {
                convertView.setBackgroundResource(R.drawable.bt_search_list_dn);
            } else {
                convertView.setBackgroundResource(R.drawable.bt_search_list);
            }
            if (this.mSearchList == null || this.mSearchList.size() <= position) {
                holder.nameTextView.setText("10086");
                holder.phoneTextView.setText("");
            } else {
                String number = this.mSearchList.get(position).pb.num;
                if (BtSearchActivity.this.isBtCountry) {
                    name2 = this.mSearchList.get(position).pb.name;
                } else {
                    String first_name = this.mSearchList.get(position).pb.first_name;
                    String middle_name = this.mSearchList.get(position).pb.middle_name;
                    String name3 = String.valueOf("") + this.mSearchList.get(position).pb.given_name;
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
                holder.phoneTextView.setText("");
            } else {
                String number = this.mSearchList.get(position).pb.num;
                if (BtSearchActivity.this.isBtCountry) {
                    name2 = this.mSearchList.get(position).pb.name;
                } else {
                    String first_name = this.mSearchList.get(position).pb.first_name;
                    String middle_name = this.mSearchList.get(position).pb.middle_name;
                    String name3 = String.valueOf("") + this.mSearchList.get(position).pb.given_name;
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
    public void dail(int id, boolean force) {
        BtExe.SearchItem item;
        String name;
        String name2;
        this.mDialId = id;
        if (BtExe.mListSearch != null && id <= BtExe.mListSearch.size() && (item = BtExe.mListSearch.get(id)) != null) {
            String str = "閹枫劍澧� " + item.pb.name + " ?";
            if (this.isBtCountry) {
                name2 = item.pb.name;
            } else {
                String first_name = item.pb.first_name;
                String middle_name = item.pb.middle_name;
                String name3 = String.valueOf("") + item.pb.given_name;
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
            this.mDialNum = item.pb.num;
            if (force) {
                this.bt.dial(this.mDialNum);
                return;
            }
            this.mfgDialDlg = true;
            new AlertDialog.Builder(this).setTitle(R.string.str_bt_dial).setMessage(name2).setPositiveButton(R.string.str_bt_ok, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    if (BtSearchActivity.this.mDialNum != null) {
                        BtSearchActivity.this.bt.dial(BtSearchActivity.this.mDialNum);
                    }
                }
            }).setNegativeButton(R.string.str_bt_cancel, (DialogInterface.OnClickListener) null).setOnDismissListener(new DialogInterface.OnDismissListener() {
                public void onDismiss(DialogInterface arg0) {
                    BtSearchActivity.this.mfgDialDlg = false;
                }
            }).show();
        }
    }

    /* access modifiers changed from: package-private */
    public void dail1(int id, boolean force) {
        BtExe.SearchItem item;
        String name;
        String name2;
        this.mDialId = id;
        if (this.mSearchAdapter != null && this.mSearchAdapter.mSearchList.size() != 0 && id <= this.mSearchAdapter.mSearchList.size() && (item = this.mSearchAdapter.mSearchList.get(id)) != null) {
            String str = "闂備浇顫夐悘姘跺礉瀹ュ棙娅犻柨鐕傛嫹 " + item.pb.name + " ?";
            if (this.isBtCountry) {
                name2 = item.pb.name;
            } else {
                String first_name = item.pb.first_name;
                String middle_name = item.pb.middle_name;
                String name3 = String.valueOf("") + item.pb.given_name;
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
            this.mDialNum = item.pb.num;
            if (force) {
                this.bt.dial(this.mDialNum);
            } else {
                new AlertDialog.Builder(this).setTitle(R.string.str_bt_dial).setMessage(name2).setPositiveButton(R.string.str_bt_ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (BtSearchActivity.this.mDialNum != null) {
                            BtSearchActivity.this.bt.dial(BtSearchActivity.this.mDialNum);
                        }
                    }
                }).setNegativeButton(R.string.str_bt_cancel, (DialogInterface.OnClickListener) null).show();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void EnterSubFocus() {
        this.mbSubFocus = 2;
        SearchFocus();
    }

    /* access modifiers changed from: protected */
    public void SearchPrev() {
        int position;
        Log.d(TAG, "SearchPrev");
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
    public void SearchNext() {
        Log.d(TAG, "SearchNext");
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
    public void SearchFocus() {
        Log.d(TAG, "SearchFocus");
        if (this.mList.getVisibility() == 0 && this.mList.getCount() >= 1) {
            int position = this.mList.getCheckedItemPosition();
            if (position != -1) {
                this.mList.setItemChecked(this.mList.getFirstVisiblePosition(), true);
            } else if (position + 1 < this.mList.getCount()) {
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
    public void SearchCenter() {
        int position;
        Log.d(TAG, "SearchCenter");
        if (this.mList.getVisibility() == 0 && this.mList.getCount() >= 1 && (position = this.mList.getCheckedItemPosition()) >= 0) {
            dail(position, true);
        }
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
                SearchPrev();
                return true;
            case 20:
                SearchNext();
                return true;
            case 21:
                if (this.mbSubFocus != 2) {
                    return true;
                }
                SearchFocus();
                return true;
            case 22:
                if (this.mbSubFocus != 3 || (position = this.mList.getCheckedItemPosition()) == -1) {
                    return true;
                }
                this.mList.setItemChecked(position, false);
                return true;
            case 23:
                SearchCenter();
                return true;
            default:
                return true;
        }
    }
}
