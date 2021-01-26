package com.ts.bt;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Handler;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.ts.MainUI.R;
import com.ts.bt.BtExe;
import com.ts.bt.OBDPiniInputDialog;
import java.util.ArrayList;
import java.util.List;

public class BtUnBonedListDialog extends CustomDialog implements AdapterView.OnItemClickListener, DialogInterface.OnDismissListener {
    private static final String TAG = "BtUnBonedListDialog";
    int count = 1;
    /* access modifiers changed from: private */
    public boolean isConnectAddr = false;
    /* access modifiers changed from: private */
    public boolean isFirstAdd = true;
    BtUnbonedListAdapter mAdapter;
    private Context mContext;
    Handler mHandler;
    private boolean mIsFocus = false;
    ListView mListView;
    ProgressBar mPbBar;
    Runnable mRunnable;

    public BtUnBonedListDialog(Context context, boolean isFocus) {
        initDialog(context);
        this.mIsFocus = isFocus;
        if (isFocus) {
            getDlg().setOnKeyListener(new DialogInterface.OnKeyListener() {
                public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                    int action = event.getAction();
                    int keycode = event.getKeyCode();
                    Log.d(BtUnBonedListDialog.TAG, "onKey: action = " + action);
                    Log.d(BtUnBonedListDialog.TAG, "onKey: keycode = " + keycode);
                    if (action != 1) {
                        return false;
                    }
                    if (keycode == 20) {
                        BtUnBonedListDialog.this.btListNext();
                        return false;
                    } else if (keycode == 19) {
                        BtUnBonedListDialog.this.btListPrev();
                        return false;
                    } else if (keycode != 23) {
                        return false;
                    } else {
                        BtUnBonedListDialog.this.btListCenter();
                        return false;
                    }
                }
            });
        }
    }

    public BtUnBonedListDialog(Context context) {
        initDialog(context);
    }

    public void initDialog(Context context) {
        this.mContext = context;
        super.create(R.layout.bt_unboned_list_layout, context);
        this.mPbBar = (ProgressBar) this.mWindow.findViewById(R.id.progressbar_scanning);
        this.mListView = (ListView) this.mWindow.findViewById(R.id.lv);
        this.mAdapter = new BtUnbonedListAdapter();
        this.mListView.setAdapter(this.mAdapter);
        this.mListView.setOnItemClickListener(this);
        this.mDlg.setOnDismissListener(this);
        this.mHandler = new Handler();
        this.mRunnable = new Runnable() {
            public void run() {
                if (BtUnBonedListDialog.this.isConnectAddr) {
                    if (BtExe.mHideUnBonedListDialog) {
                        BtExe.mHideUnBonedListDialog = false;
                        BtUnBonedListDialog.this.dismiss();
                    }
                } else if (!BtExe.mLocalAdapter.isDiscovering()) {
                    BtUnBonedListDialog btUnBonedListDialog = BtUnBonedListDialog.this;
                    int i = btUnBonedListDialog.count;
                    btUnBonedListDialog.count = i + 1;
                    if (i > 5) {
                        BtExe.mLocalAdapter.startScanning(true);
                        BtUnBonedListDialog.this.count = 1;
                    }
                }
                if (BtExe.mScaningDevice) {
                    if (BtUnBonedListDialog.this.mPbBar.getVisibility() != 0) {
                        BtUnBonedListDialog.this.mPbBar.setVisibility(0);
                    }
                } else if (BtUnBonedListDialog.this.mPbBar.getVisibility() == 0) {
                    BtUnBonedListDialog.this.mPbBar.setVisibility(8);
                }
                if (BtExe.mDeviceAdd) {
                    BtUnBonedListDialog.this.mAdapter.updateData(BtExe.mUnBonedLists);
                    BtExe.mDeviceAdd = false;
                    if (BtUnBonedListDialog.this.isFirstAdd && BtExe.mUnBonedLists.size() > 0) {
                        BtUnBonedListDialog.this.mListView.setItemChecked(0, true);
                        BtUnBonedListDialog.this.isFirstAdd = false;
                    }
                }
                BtUnBonedListDialog.this.mHandler.postDelayed(BtUnBonedListDialog.this.mRunnable, 1000);
            }
        };
        this.mHandler.post(this.mRunnable);
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        connectDevice(position);
    }

    public class BtUnbonedListAdapter extends BaseAdapter {
        List<BtExe.BonedDevice> mBonedDevices = new ArrayList();

        public BtUnbonedListAdapter() {
        }

        /* access modifiers changed from: package-private */
        public void updateData(List<BtExe.BonedDevice> bonedDevices) {
            Log.d("lh8", "updateData");
            this.mBonedDevices.clear();
            this.mBonedDevices.addAll(bonedDevices);
            notifyDataSetChanged();
        }

        public int getCount() {
            return this.mBonedDevices.size();
        }

        public Object getItem(int arg0) {
            return null;
        }

        public long getItemId(int position) {
            return (long) position;
        }

        public View getView(int position, View convertView, ViewGroup viewGroup) {
            int resId;
            ViewHolder holder = new ViewHolder();
            if (convertView == null) {
                convertView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.bt_unboned_list_item, (ViewGroup) null, false);
                holder.imageView = (ImageView) convertView.findViewById(R.id.iv_device_type);
                holder.textView = (TextView) convertView.findViewById(R.id.tv);
                holder.pB = (ProgressBar) convertView.findViewById(R.id.progressbar_bonding);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            if (BtUnBonedListDialog.this.mListView.isItemChecked(position)) {
                holder.textView.setTextColor(Color.parseColor("#54d0e9"));
            } else {
                holder.textView.setTextColor(ViewCompat.MEASURED_STATE_MASK);
            }
            holder.textView.setText(this.mBonedDevices.get(position).name);
            switch (this.mBonedDevices.get(position).type) {
                case 512:
                    resId = R.drawable.bt_device_major_phone;
                    break;
                case 1024:
                    resId = R.drawable.bt_device_major_audio_video;
                    break;
                case 7936:
                    resId = R.drawable.bt_device_major_uncategorized;
                    break;
                default:
                    resId = R.drawable.bt_device_major_uncategorized;
                    break;
            }
            holder.imageView.setImageResource(resId);
            if (this.mBonedDevices.get(position).state == 11) {
                holder.pB.setVisibility(0);
            } else {
                holder.pB.setVisibility(8);
            }
            return convertView;
        }

        class ViewHolder {
            ImageView imageView;
            ProgressBar pB;
            TextView textView;

            ViewHolder() {
            }
        }
    }

    public void onDismiss(DialogInterface dialog) {
        Log.d("lh8", "onDismiss");
        if (BtExe.mLocalAdapter != null && BtExe.mLocalAdapter.isDiscovering()) {
            BtExe.mLocalAdapter.cancelDiscovery();
        }
        this.mHandler.removeCallbacksAndMessages((Object) null);
        this.mHandler = null;
        dismiss();
    }

    /* access modifiers changed from: protected */
    public void btListPrev() {
        int position;
        Log.d(TAG, "btListPrev");
        if (this.mListView.getVisibility() == 0 && this.mListView.getCount() >= 1 && (position = this.mListView.getCheckedItemPosition()) > 0) {
            int firstVisiable = this.mListView.getFirstVisiblePosition();
            int position2 = position - 1;
            View firstView = this.mListView.getChildAt(position2 - firstVisiable);
            this.mListView.setItemChecked(position2, true);
            if (position2 < firstVisiable || firstView == null || firstView.getY() < 0.0f) {
                this.mListView.setSelection(position2);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void btListNext() {
        Log.d(TAG, "btListNext");
        if (this.mListView.getVisibility() == 0 && this.mListView.getCount() >= 1) {
            int position = this.mListView.getCheckedItemPosition();
            if (position + 1 < this.mListView.getCount()) {
                int firstVisiable = this.mListView.getFirstVisiblePosition();
                int lastVisiable = this.mListView.getLastVisiblePosition();
                int position2 = position + 1;
                View lastView = this.mListView.getChildAt(position2 - firstVisiable);
                this.mListView.setItemChecked(position2, true);
                if (lastView != null) {
                    int y1 = ((int) this.mListView.getY()) + lastView.getBottom();
                    Log.d(TAG, "lastVisiable = " + lastVisiable + ", getBottom = " + lastView.getBottom() + ", y1 = " + y1 + ", y2 = " + this.mListView.getBottom());
                }
                if (position2 > lastVisiable || lastView == null || lastView.getBottom() > this.mListView.getBottom()) {
                    this.mListView.setSelection(position2);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void btListCenter() {
        int position;
        Log.d(TAG, "btListCenter");
        if (this.mListView.getVisibility() == 0 && this.mListView.getCount() >= 1 && (position = this.mListView.getCheckedItemPosition()) >= 0) {
            connectDevice(position);
        }
    }

    /* access modifiers changed from: package-private */
    public void connectDevice(int position) {
        BtExe.mHideUnBonedListDialog = false;
        final BtExe.BonedDevice bonedDevice = this.mAdapter.mBonedDevices.get(position);
        String name = bonedDevice.name;
        String str = bonedDevice.addr;
        if (name == null || !name.startsWith("OBD")) {
            BtExe.getBtInstance().connect(bonedDevice);
            this.isConnectAddr = true;
            return;
        }
        new OBDPiniInputDialog().createDlg(this.mContext, new OBDPiniInputDialog.onInputOK() {
            public void onOK(String val) {
                BtExe.mObdPin = val;
                BtExe.getBtInstance().connect(bonedDevice);
                BtUnBonedListDialog.this.isConnectAddr = true;
            }
        }, 4, this.mIsFocus);
    }
}
