package com.ts.can.btobd;

import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import com.autochips.bluetooth.CachedBluetoothDevice;
import com.ts.MainUI.R;
import com.ts.bt.BtExe;
import com.ts.can.MyApplication;
import com.ts.can.btobd.OBDPiniInputDialog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CanBtOBDActivity extends Activity implements View.OnClickListener, OBDPiniInputDialog.onInputOK, AdapterView.OnItemClickListener {
    public static final String ACTION_DISCOVERY_FINISHED = "android.bluetooth.adapter.action.DISCOVERY_FINISHED";
    private static final boolean D = true;
    private static final int DEFAULT_DISCOVERABLE_TIMEOUT = -1;
    private static final String PARING_REQUEST_INTENT = "android.bluetooth.device.action.PAIRING_REQUEST";
    private static final String REMOTE_CONNECT_STATE = "remote_connect_status";
    private static final String REMOTE_DEVICE_MACADDR = "remote_device_macaddr";
    private static final String REMOTE_DEVICE_NAME = "remote_device_name";
    private static final String TAG = "BtPairedHistoryActivity";
    private BroadcastReceiver ODBroadcastReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(CanBtOBDActivity.PARING_REQUEST_INTENT)) {
                CanBtOBDActivity.this.bt.getOBDAddr();
                if (BtExe.mLastOBDAddr != null && !BtExe.mLastOBDAddr.isEmpty()) {
                    BluetoothDevice device = BtExe.mLocalAdapter.getRemoteDevice(BtExe.mLastOBDAddr);
                    if (BtExe.mPin != null && !BtExe.mPin.isEmpty()) {
                        device.setPin(BluetoothDevice.convertPinToBytes(BtExe.mPin));
                    }
                }
            } else if (intent.getAction().equals(CanBtOBDActivity.ACTION_DISCOVERY_FINISHED)) {
                CanBtOBDActivity.this.mIvSearch.setVisibility(8);
            }
        }
    };
    /* access modifiers changed from: private */
    public BtExe bt = BtExe.getBtInstance();
    private boolean isBonded = false;
    private ArrayList<HashMap<String, Object>> mBluetoothPairedDevices;
    /* access modifiers changed from: private */
    public BluetoothPairedDevicesAdapter mBluetoothPairedDevicesAdapter;
    private ArrayList<HashMap<String, Object>> mBluetoothUnpairedDevices;
    /* access modifiers changed from: private */
    public BluetoothUnpairedDevicesAdapter mBluetoothUnpairedDevicesAdapter;
    CachedBluetoothDevice.Callback mDeviceCallback = new CachedBluetoothDevice.Callback() {
        public void onDeviceAttributesChanged(CachedBluetoothDevice cachedDevice) {
            Message msg = CanBtOBDActivity.this.mHandler.obtainMessage();
            msg.what = 16;
            msg.obj = cachedDevice;
            CanBtOBDActivity.this.mHandler.sendMessage(msg);
        }
    };
    private OBDPiniInputDialog mDvdGotoDialog;
    /* access modifiers changed from: private */
    public Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    CanBtOBDActivity.this.refreshBondedDevices(true);
                    break;
                case 1:
                    CanBtOBDActivity.this.refreshBondedDevices(false);
                    break;
                case 10:
                    CanBtOBDActivity.this.onActionBtStateChanged(msg.arg1);
                    break;
                case 12:
                    CachedBluetoothDevice cachedDevice = (CachedBluetoothDevice) msg.obj;
                    if (cachedDevice != null) {
                        cachedDevice.registerCallback(CanBtOBDActivity.this.mDeviceCallback);
                        CanBtOBDActivity.this.onActionDeviceFound(cachedDevice.getDevice());
                        break;
                    }
                    break;
                case 13:
                    CachedBluetoothDevice cachedDevice2 = (CachedBluetoothDevice) msg.obj;
                    if (cachedDevice2 != null) {
                        cachedDevice2.unregisterCallback(CanBtOBDActivity.this.mDeviceCallback);
                        break;
                    }
                    break;
                case 14:
                    CachedBluetoothDevice cachedDevice3 = (CachedBluetoothDevice) msg.obj;
                    if (cachedDevice3 != null) {
                        CanBtOBDActivity.this.onActionBondStateChanged(cachedDevice3.getDevice(), msg.arg1);
                        break;
                    }
                    break;
                case 15:
                    CachedBluetoothDevice cachedDevice4 = (CachedBluetoothDevice) msg.obj;
                    if (cachedDevice4 != null) {
                        CanBtOBDActivity.this.onActionConnectStateChanged(cachedDevice4.getDevice(), msg.arg1);
                        break;
                    }
                    break;
                case 16:
                    CachedBluetoothDevice cachedDevice5 = (CachedBluetoothDevice) msg.obj;
                    if (cachedDevice5 != null) {
                        CanBtOBDActivity.this.onActionDeviceNameChanged(cachedDevice5.getDevice());
                        break;
                    }
                    break;
                case 17:
                    CanBtOBDActivity.this.onActionProfileStateChanged((BluetoothDevice) msg.obj, msg.arg1, msg.arg2);
                    break;
                case 18:
                    BluetoothDevice device = (BluetoothDevice) msg.obj;
                    if (device != null) {
                        CanBtOBDActivity.this.onActionConnectStateChanged(device, msg.arg1);
                        break;
                    }
                    break;
            }
            super.handleMessage(msg);
        }
    };
    /* access modifiers changed from: private */
    public ImageView mIvSearch;
    private AdapterView.OnItemClickListener mPairedClickListener = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            CanBtOBDActivity.this.mBluetoothUnpairedDevicesAdapter.setSelect(-1);
            CanBtOBDActivity.this.mBluetoothUnpairedDevicesAdapter.notifyDataSetChanged();
            CanBtOBDActivity.this.mBluetoothPairedDevicesAdapter.setSelect(arg2);
            CanBtOBDActivity.this.mBluetoothPairedDevicesAdapter.notifyDataSetChanged();
            CanBtOBDActivity.this.onClickSelect((ListView) arg0, arg2);
        }
    };
    private int mPosition = -1;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (IsCore() == 1) {
            finish();
            return;
        }
        Log.d(TAG, "+++  onCreate +++");
        setContentView(R.layout.obd_pairedhistory);
        this.mBluetoothUnpairedDevices = new ArrayList<>();
        this.mBluetoothUnpairedDevicesAdapter = new BluetoothUnpairedDevicesAdapter(this, this.mBluetoothUnpairedDevices, R.layout.obd_listitem, new String[]{REMOTE_DEVICE_NAME, REMOTE_CONNECT_STATE}, new int[]{R.id.item_remote_device_name, R.id.item_remote_connect_status});
        ListView unpairedDeviceListView = (ListView) findViewById(R.id.bluetooth_usable_devices);
        if (unpairedDeviceListView != null) {
            unpairedDeviceListView.setAdapter(this.mBluetoothUnpairedDevicesAdapter);
            unpairedDeviceListView.setOnItemClickListener(this);
            unpairedDeviceListView.setEnabled(true);
        }
        this.mBluetoothPairedDevices = new ArrayList<>();
        this.mBluetoothPairedDevicesAdapter = new BluetoothPairedDevicesAdapter(this, this.mBluetoothPairedDevices, R.layout.obd_listitem, new String[]{REMOTE_DEVICE_NAME, REMOTE_CONNECT_STATE}, new int[]{R.id.item_remote_device_name, R.id.item_remote_connect_status});
        ListView pairedDeviceListView = (ListView) findViewById(R.id.bluetooth_paired_devices);
        if (pairedDeviceListView != null) {
            pairedDeviceListView.setAdapter(this.mBluetoothPairedDevicesAdapter);
            pairedDeviceListView.setOnItemClickListener(this.mPairedClickListener);
            pairedDeviceListView.setEnabled(true);
        }
        this.mIvSearch = (ImageView) findViewById(R.id.iv_searching);
        this.mIvSearch.setVisibility(8);
        ImageButton scanButton = (ImageButton) findViewById(R.id.btn_scan_bt);
        ImageButton unpaireButton = (ImageButton) findViewById(R.id.btn_unpair_bt);
        if (scanButton != null) {
            scanButton.setOnClickListener(this);
        }
        if (unpaireButton != null) {
            unpaireButton.setOnClickListener(this);
        }
        BtExe.addHandler(this.mHandler);
        IntentFilter intent = new IntentFilter();
        intent.addAction(PARING_REQUEST_INTENT);
        intent.addAction(ACTION_DISCOVERY_FINISHED);
        registerReceiver(this.ODBroadcastReceiver, intent);
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        Log.d(TAG, "+++  onStart +++");
        if (BtExe.mLocalAdapter == null) {
            Log.e(TAG, "get LocalBluetoothAdapter fail!");
            finish();
            return;
        }
        onActionBtStateChanged(BtExe.mLocalAdapter.getState());
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle outstate) {
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        Log.d(TAG, "+++  onDestroy +++");
        super.onDestroy();
        if (IsCore() != 1) {
            unregisterReceiver(this.ODBroadcastReceiver);
            BtExe.removeHandler(this.mHandler);
        }
    }

    public void onClick(View v) {
        if (BtExe.mLocalAdapter.getState() == 12) {
            int id = v.getId();
            if (id == R.id.btn_unpair_bt) {
                onClickUnpair();
            } else if (id == R.id.btn_scan_bt) {
                onClickScan();
            }
        }
    }

    private void setBluetoothDiscoverability(boolean isVisible) {
        if (isVisible) {
            if (BtExe.mLocalAdapter != null) {
                BtExe.mLocalAdapter.setScanMode(23, -1);
            }
        } else if (BtExe.mLocalAdapter != null) {
            BtExe.mLocalAdapter.setScanMode(21);
        }
    }

    /* access modifiers changed from: private */
    public void refreshBondedDevices(boolean isOn) {
        if (BtExe.mLocalAdapter != null) {
            if (isOn) {
                this.mBluetoothPairedDevices.clear();
                List<BluetoothDevice> pairedDevices = new ArrayList<>();
                pairedDevices.addAll(BtExe.mLocalAdapter.getBondedDevices());
                for (BluetoothDevice device : pairedDevices) {
                    if (this.bt.isConnected() && BtExe.mLastDeviceAddr != null && !BtExe.mLastDeviceAddr.isEmpty()) {
                        String addr = device.getAddress();
                        if (!TextUtils.isEmpty(addr) && addr.equals(BtExe.mLastDeviceAddr)) {
                        }
                    }
                    String devName = device.getName();
                    String addr2 = device.getAddress();
                    if (!TextUtils.isEmpty(devName) && !TextUtils.isEmpty(addr2)) {
                        HashMap<String, Object> map = new HashMap<>();
                        map.put(REMOTE_DEVICE_NAME, devName);
                        map.put(REMOTE_DEVICE_MACADDR, addr2);
                        this.mBluetoothPairedDevices.add(map);
                    }
                }
            } else {
                this.mBluetoothUnpairedDevices.clear();
                this.mBluetoothUnpairedDevicesAdapter.notifyDataSetChanged();
            }
            this.mBluetoothPairedDevicesAdapter.notifyDataSetChanged();
        }
    }

    class BluetoothPairedDevicesAdapter extends SimpleAdapter {
        List<HashMap<String, Object>> mDeviceList;
        private LayoutInflater mInflater;
        private int mSelectIdx = -1;

        public BluetoothPairedDevicesAdapter(Context context, List<HashMap<String, Object>> data, int resource, String[] from, int[] to) {
            super(context, data, resource, from, to);
            this.mInflater = LayoutInflater.from(context);
            this.mSelectIdx = -1;
            this.mDeviceList = data;
        }

        public void setSelect(int index) {
            this.mSelectIdx = index;
        }

        public int getSelect() {
            if (this.mSelectIdx < this.mDeviceList.size()) {
                return this.mSelectIdx;
            }
            return -1;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = this.mInflater.inflate(R.layout.obd_listitem, (ViewGroup) null);
                holder.nameTextView = (TextView) convertView.findViewById(R.id.item_remote_device_name);
                holder.statusTextView = (TextView) convertView.findViewById(R.id.item_remote_connect_status);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            String name = "";
            if (position < this.mDeviceList.size()) {
                name = (String) this.mDeviceList.get(position).get(CanBtOBDActivity.REMOTE_DEVICE_NAME);
            }
            holder.nameTextView.setText(name);
            if (position == this.mSelectIdx) {
                convertView.setBackgroundResource(R.drawable.obd_line_dn);
            } else {
                convertView.setBackgroundResource(17170445);
            }
            return convertView;
        }

        public final class ViewHolder {
            public TextView nameTextView;
            public TextView statusTextView;

            public ViewHolder() {
            }
        }
    }

    class BluetoothUnpairedDevicesAdapter extends SimpleAdapter {
        List<HashMap<String, Object>> mDeviceList;
        private LayoutInflater mInflater;
        private int mSelectIdx = -1;

        public BluetoothUnpairedDevicesAdapter(Context context, List<HashMap<String, Object>> data, int resource, String[] from, int[] to) {
            super(context, data, resource, from, to);
            this.mInflater = LayoutInflater.from(context);
            this.mSelectIdx = -1;
            this.mDeviceList = data;
        }

        public void setSelect(int index) {
            this.mSelectIdx = index;
        }

        public int getSelect() {
            if (this.mSelectIdx < this.mDeviceList.size()) {
                return this.mSelectIdx;
            }
            return -1;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = this.mInflater.inflate(R.layout.obd_listitem, (ViewGroup) null);
                holder.nameTextView = (TextView) convertView.findViewById(R.id.item_remote_device_name);
                holder.statusTextView = (TextView) convertView.findViewById(R.id.item_remote_connect_status);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            String name = "";
            if (position < this.mDeviceList.size() && ((name = (String) this.mDeviceList.get(position).get(CanBtOBDActivity.REMOTE_DEVICE_NAME)) == null || name.equals(""))) {
                name = (String) this.mDeviceList.get(position).get(CanBtOBDActivity.REMOTE_DEVICE_MACADDR);
            }
            holder.nameTextView.setText(name);
            if (this.mSelectIdx == position) {
                holder.statusTextView.setText(CanBtOBDActivity.this.getResources().getString(R.string.pairing));
            } else {
                holder.statusTextView.setText("");
            }
            return convertView;
        }

        public final class ViewHolder {
            public TextView nameTextView;
            public TextView statusTextView;

            public ViewHolder() {
            }
        }
    }

    /* access modifiers changed from: private */
    public void onActionBtStateChanged(int btState) {
        Log.d(TAG, "onActionBtStateChanged:state=" + btState);
        switch (btState) {
            case 10:
                refreshBondedDevices(false);
                return;
            case 12:
                refreshBondedDevices(true);
                setBluetoothDiscoverability(true);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: private */
    public void onActionDeviceFound(BluetoothDevice device) {
        if (device != null) {
            String devName = device.getName();
            String addr = device.getAddress();
            if (!TextUtils.isEmpty(devName) && !TextUtils.isEmpty(addr)) {
                Log.d(TAG, "find device " + devName + " , addr:" + addr);
                if (!TextUtils.isEmpty(devName) && devName.contains("BT") && devName.length() == 6) {
                    return;
                }
                if (12 == device.getBondState()) {
                    int i = 0;
                    while (i < this.mBluetoothPairedDevices.size()) {
                        if (TextUtils.isEmpty(addr) || !addr.equals(this.mBluetoothPairedDevices.get(i).get(REMOTE_DEVICE_MACADDR))) {
                            i++;
                        } else {
                            this.mBluetoothPairedDevices.get(i).put(REMOTE_DEVICE_NAME, devName);
                            return;
                        }
                    }
                    HashMap<String, Object> map = new HashMap<>();
                    map.put(REMOTE_DEVICE_MACADDR, addr);
                    String nameString = devName;
                    if (nameString == null || nameString.equals("")) {
                        nameString = addr;
                    }
                    map.put(REMOTE_DEVICE_NAME, nameString);
                    if (!this.mBluetoothPairedDevices.contains(map)) {
                        this.mBluetoothPairedDevices.add(map);
                        this.mBluetoothPairedDevicesAdapter.notifyDataSetChanged();
                        return;
                    }
                    return;
                }
                int i2 = 0;
                while (i2 < this.mBluetoothUnpairedDevices.size()) {
                    if (TextUtils.isEmpty(addr) || !addr.equals(this.mBluetoothUnpairedDevices.get(i2).get(REMOTE_DEVICE_MACADDR)) || TextUtils.isEmpty(devName)) {
                        i2++;
                    } else {
                        this.mBluetoothUnpairedDevices.get(i2).put(REMOTE_DEVICE_NAME, devName);
                        return;
                    }
                }
                HashMap<String, Object> map2 = new HashMap<>();
                map2.put(REMOTE_DEVICE_MACADDR, addr);
                String nameString2 = devName;
                if (nameString2 == null || nameString2.equals("")) {
                    nameString2 = addr;
                }
                map2.put(REMOTE_DEVICE_NAME, nameString2);
                this.mBluetoothUnpairedDevices.add(map2);
                this.mBluetoothUnpairedDevicesAdapter.notifyDataSetChanged();
                return;
            }
            return;
        }
        Log.e(TAG, "find device null!");
    }

    /* access modifiers changed from: private */
    public void onActionDeviceNameChanged(BluetoothDevice device) {
        if (device != null) {
            String curAddr = device.getAddress();
            int i = 0;
            while (i < this.mBluetoothUnpairedDevices.size()) {
                HashMap<String, Object> map = this.mBluetoothUnpairedDevices.get(i);
                String addr = (String) map.get(REMOTE_DEVICE_MACADDR);
                if (addr == null || !addr.equals(curAddr)) {
                    i++;
                } else {
                    map.put(REMOTE_DEVICE_NAME, device.getName());
                    this.mBluetoothUnpairedDevicesAdapter.notifyDataSetChanged();
                    return;
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void onActionBondStateChanged(BluetoothDevice device, int bondState) {
        if (device == null) {
            Log.e(TAG, "onActionBondStateChanged, get device fail!");
            return;
        }
        String devName = device.getName();
        String addr = device.getAddress();
        Log.d(TAG, "device:" + addr + " bondState:" + bondState);
        if (!TextUtils.isEmpty(devName) && !TextUtils.isEmpty(addr)) {
            if (bondState == 12) {
                int i = 0;
                while (true) {
                    if (i >= this.mBluetoothUnpairedDevices.size()) {
                        break;
                    } else if (addr.equals(this.mBluetoothUnpairedDevices.get(i).get(REMOTE_DEVICE_MACADDR))) {
                        this.mBluetoothUnpairedDevices.remove(i);
                        this.mBluetoothUnpairedDevicesAdapter.notifyDataSetChanged();
                        break;
                    } else {
                        i++;
                    }
                }
                int i2 = 0;
                while (i2 < this.mBluetoothPairedDevices.size()) {
                    if (!addr.equals(this.mBluetoothPairedDevices.get(i2).get(REMOTE_DEVICE_MACADDR))) {
                        i2++;
                    } else {
                        return;
                    }
                }
                HashMap<String, Object> map = new HashMap<>();
                map.put(REMOTE_DEVICE_NAME, devName);
                map.put(REMOTE_DEVICE_MACADDR, addr);
                this.mBluetoothPairedDevices.add(map);
                this.mBluetoothUnpairedDevicesAdapter.setSelect(-1);
                this.mBluetoothPairedDevicesAdapter.notifyDataSetChanged();
            } else if (bondState == 10) {
                if (this.isBonded) {
                    if (devName.equals("OBDII")) {
                        Toast.makeText(this, getResources().getString(R.string.paired_fail_tips), 0).show();
                        this.mDvdGotoDialog = new OBDPiniInputDialog();
                        this.mDvdGotoDialog.createDlg(this, this, 4);
                    }
                    this.mBluetoothUnpairedDevicesAdapter.setSelect(-1);
                    this.mBluetoothUnpairedDevicesAdapter.notifyDataSetChanged();
                    this.isBonded = false;
                }
                int i3 = 0;
                while (true) {
                    if (i3 >= this.mBluetoothPairedDevices.size()) {
                        break;
                    } else if (addr.equals(this.mBluetoothPairedDevices.get(i3).get(REMOTE_DEVICE_MACADDR))) {
                        this.mBluetoothPairedDevices.remove(i3);
                        this.mBluetoothPairedDevicesAdapter.notifyDataSetChanged();
                        break;
                    } else {
                        i3++;
                    }
                }
                int i4 = 0;
                while (i4 < this.mBluetoothUnpairedDevices.size()) {
                    if (!addr.equals(this.mBluetoothUnpairedDevices.get(i4).get(REMOTE_DEVICE_MACADDR))) {
                        i4++;
                    } else {
                        return;
                    }
                }
                String nameString1 = devName;
                if (nameString1 == null || nameString1.equals("")) {
                    nameString1 = addr;
                }
                HashMap<String, Object> map2 = new HashMap<>();
                map2.put(REMOTE_DEVICE_NAME, nameString1);
                map2.put(REMOTE_DEVICE_MACADDR, addr);
                this.mBluetoothUnpairedDevices.add(map2);
                this.mBluetoothUnpairedDevicesAdapter.notifyDataSetChanged();
            }
        }
    }

    /* access modifiers changed from: private */
    public void onActionConnectStateChanged(BluetoothDevice device, int state) {
        if (device == null) {
            Log.e(TAG, "onActionBondStateChanged, get device fail!");
            return;
        }
        int newState = state;
        String name = device.getName();
        String devAddr = device.getAddress();
        Log.d(TAG, "device:" + devAddr + " connectState:" + newState);
        switch (newState) {
            case 0:
                int i = 0;
                while (i < this.mBluetoothPairedDevices.size()) {
                    String addr = (String) this.mBluetoothPairedDevices.get(i).get(REMOTE_DEVICE_MACADDR);
                    if (addr == null || !addr.equals(devAddr)) {
                        i++;
                    } else {
                        this.mBluetoothPairedDevicesAdapter.notifyDataSetChanged();
                        return;
                    }
                }
                return;
            case 2:
                for (int i2 = 0; i2 < this.mBluetoothPairedDevices.size(); i2++) {
                    if (this.bt.isConnected() && BtExe.mLastDeviceAddr != null && !BtExe.mLastDeviceAddr.isEmpty() && this.mBluetoothPairedDevices.get(i2).get(REMOTE_DEVICE_MACADDR).equals(BtExe.mLastDeviceAddr)) {
                        this.mBluetoothPairedDevices.remove(i2);
                        this.mBluetoothPairedDevicesAdapter.notifyDataSetChanged();
                    }
                }
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: private */
    public void onActionProfileStateChanged(BluetoothDevice device, int profile, int state) {
        switch (profile) {
        }
        String devAddr = device.getAddress();
        int i = 0;
        while (i < this.mBluetoothPairedDevices.size()) {
            String addr = (String) this.mBluetoothPairedDevices.get(i).get(REMOTE_DEVICE_MACADDR);
            if (addr == null || !addr.equals(devAddr)) {
                i++;
            } else {
                this.mBluetoothPairedDevicesAdapter.notifyDataSetChanged();
                return;
            }
        }
    }

    private void onClickScan() {
        Log.d(TAG, "onClick scan!");
        if (BtExe.mLocalAdapter == null) {
            return;
        }
        if (BtExe.mLocalAdapter.isDiscovering()) {
            BtExe.mLocalAdapter.cancelDiscovery();
            return;
        }
        this.mBluetoothUnpairedDevices.clear();
        this.mBluetoothUnpairedDevicesAdapter.notifyDataSetChanged();
        BtExe.mLocalAdapter.startScanning(true);
        this.mIvSearch.setVisibility(0);
    }

    private void onClickConnect() {
        String deviceAddr;
        Log.d(TAG, "onClick cnnect!");
        if (BtExe.mLocalAdapter != null) {
            int selectIndex = this.mBluetoothUnpairedDevicesAdapter.getSelect();
            if (selectIndex == -1 || selectIndex >= this.mBluetoothUnpairedDevices.size()) {
                int selectIndex2 = this.mBluetoothPairedDevicesAdapter.getSelect();
                if (selectIndex2 != -1 && selectIndex2 < this.mBluetoothPairedDevices.size()) {
                    deviceAddr = (String) this.mBluetoothPairedDevices.get(selectIndex2).get(REMOTE_DEVICE_MACADDR);
                } else if (this.mBluetoothPairedDevices.size() > 0) {
                    deviceAddr = (String) this.mBluetoothPairedDevices.get(this.mBluetoothPairedDevices.size() - 1).get(REMOTE_DEVICE_MACADDR);
                } else {
                    return;
                }
            } else {
                deviceAddr = (String) this.mBluetoothUnpairedDevices.get(selectIndex).get(REMOTE_DEVICE_MACADDR);
            }
            this.bt.startPairing(deviceAddr);
        }
    }

    private void onClickUnpair() {
        int selectIndex;
        BluetoothDevice device;
        if (BtExe.mLocalAdapter != null && (selectIndex = this.mBluetoothPairedDevicesAdapter.getSelect()) != -1 && selectIndex < this.mBluetoothPairedDevices.size() && (device = BtExe.mLocalAdapter.getRemoteDevice((String) this.mBluetoothPairedDevices.get(selectIndex).get(REMOTE_DEVICE_MACADDR))) != null && device.getBondState() == 12) {
            this.isBonded = false;
            device.removeBond();
        }
    }

    /* access modifiers changed from: private */
    public void onClickSelect(ListView view, int index) {
        Log.d(TAG, "onClickSelect");
        String deviceAddr = "";
        if (BtExe.mLocalAdapter.getConnectionState() != 2) {
            ListView unpairedDeviceListView = (ListView) findViewById(R.id.bluetooth_usable_devices);
            ListView pairedDeviceListView = (ListView) findViewById(R.id.bluetooth_paired_devices);
            if (view == unpairedDeviceListView && index != -1 && index < this.mBluetoothUnpairedDevices.size()) {
                Log.d(TAG, "select unpaired list");
                deviceAddr = (String) this.mBluetoothUnpairedDevices.get(index).get(REMOTE_DEVICE_MACADDR);
            } else if (view == pairedDeviceListView && index != -1 && index < this.mBluetoothPairedDevices.size()) {
                Log.d(TAG, "select paired list");
                deviceAddr = (String) this.mBluetoothPairedDevices.get(index).get(REMOTE_DEVICE_MACADDR);
            }
            if (deviceAddr != null) {
                BtExe.handleDeviceSelected(BtExe.mLocalAdapter.getRemoteDevice(deviceAddr), true);
            }
        }
    }

    public void onOK(String val) {
        this.mBluetoothUnpairedDevicesAdapter.setSelect(this.mPosition);
        this.mBluetoothUnpairedDevicesAdapter.notifyDataSetChanged();
        this.mBluetoothPairedDevicesAdapter.notifyDataSetChanged();
        this.isBonded = true;
        BtExe.mPin = val;
        onClickConnect();
    }

    public void onItemClick(AdapterView<?> adapterView, View arg1, int position, long arg3) {
        this.mPosition = position;
        String name = "";
        if (position < this.mBluetoothUnpairedDevices.size()) {
            name = (String) this.mBluetoothUnpairedDevices.get(position).get(REMOTE_DEVICE_NAME);
        }
        if ("OBDII".equals(name)) {
            this.mBluetoothUnpairedDevicesAdapter.setSelect(this.mPosition);
            this.mBluetoothUnpairedDevicesAdapter.notifyDataSetChanged();
            this.mBluetoothPairedDevicesAdapter.notifyDataSetChanged();
            this.isBonded = true;
            BtExe.mPin = "1234";
            onClickConnect();
            return;
        }
        this.mDvdGotoDialog = new OBDPiniInputDialog();
        this.mDvdGotoDialog.createDlg(this, this, 4);
    }

    public int IsCore() {
        int id;
        if (MyApplication.mContext != null && (id = MyApplication.mContext.getResources().getIdentifier("core_type_", "string", MyApplication.mContext.getPackageName())) > 0) {
            String str = MyApplication.mContext.getResources().getString(id);
            Log.d(TAG, str);
            if (str.equals("8259")) {
                Log.d(TAG, "IsCore 8259 ");
                return 1;
            }
        }
        return 0;
    }
}
