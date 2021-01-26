package com.ts.main.auth;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.widget.Toast;
import com.txznet.sdk.TXZResourceManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class WifiAdmin {
    private Context mContext;
    private List<WifiConfiguration> mWifiConfiguration;
    private WifiInfo mWifiInfo = this.mWifiManager.getConnectionInfo();
    private List<ScanResult> mWifiList;
    WifiManager.WifiLock mWifiLock;
    private WifiManager mWifiManager;

    public WifiAdmin(Context context) {
        this.mContext = context;
        this.mWifiManager = (WifiManager) context.getSystemService("wifi");
    }

    public void openWifi(Context context) {
        if (!this.mWifiManager.isWifiEnabled()) {
            this.mWifiManager.setWifiEnabled(true);
        } else if (this.mWifiManager.getWifiState() == 2) {
            Toast.makeText(context, "亲，Wifi正在开启，不用再开了", 0).show();
        } else {
            Toast.makeText(context, "亲，Wifi已经开启,不用再开了", 0).show();
        }
    }

    public void closeWifi(Context context) {
        if (this.mWifiManager.isWifiEnabled()) {
            this.mWifiManager.setWifiEnabled(false);
        } else if (this.mWifiManager.getWifiState() == 1) {
            Toast.makeText(context, "亲，Wifi已经关闭，不用再关了", 0).show();
        } else if (this.mWifiManager.getWifiState() == 0) {
            Toast.makeText(context, "亲，Wifi正在关闭，不用再关了", 0).show();
        } else {
            Toast.makeText(context, "请重新关闭", 0).show();
        }
    }

    public void checkState(Context context) {
        if (this.mWifiManager.getWifiState() == 0) {
            Toast.makeText(context, "Wifi正在关闭", 0).show();
        } else if (this.mWifiManager.getWifiState() == 1) {
            Toast.makeText(context, "Wifi已经关闭", 0).show();
        } else if (this.mWifiManager.getWifiState() == 2) {
            Toast.makeText(context, "Wifi正在开启", 0).show();
        } else if (this.mWifiManager.getWifiState() == 3) {
            Toast.makeText(context, "Wifi已经开启", 0).show();
        } else {
            Toast.makeText(context, "没有获取到WiFi状态", 0).show();
        }
    }

    public void acquireWifiLock() {
        this.mWifiLock.acquire();
    }

    public void releaseWifiLock() {
        if (this.mWifiLock.isHeld()) {
            this.mWifiLock.acquire();
        }
    }

    public void creatWifiLock() {
        this.mWifiLock = this.mWifiManager.createWifiLock("Test");
    }

    public List<WifiConfiguration> getConfiguration() {
        return this.mWifiConfiguration;
    }

    public void connectConfiguration(int index) {
        if (index <= this.mWifiConfiguration.size()) {
            this.mWifiManager.enableNetwork(this.mWifiConfiguration.get(index).networkId, true);
        }
    }

    public void updateWifiList() {
        List<ScanResult> results = this.mWifiManager.getScanResults();
        this.mWifiConfiguration = this.mWifiManager.getConfiguredNetworks();
        if (results != null) {
            this.mWifiList = new ArrayList();
            for (ScanResult result : results) {
                if (!(result.SSID == null || result.SSID.length() == 0)) {
                    boolean found = false;
                    Iterator<ScanResult> it = this.mWifiList.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            ScanResult item = it.next();
                            if (item.SSID.equals(result.SSID) && item.capabilities.equals(result.capabilities)) {
                                found = true;
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                    if (!found) {
                        this.mWifiList.add(result);
                    }
                }
            }
        } else if (this.mWifiManager.getWifiState() == 3) {
            Toast.makeText(this.mContext, "当前区域没有无线网络", 0).show();
        } else if (this.mWifiManager.getWifiState() == 2) {
            Toast.makeText(this.mContext, "wifi正在开启，请稍后扫描", 0).show();
        } else {
            Toast.makeText(this.mContext, "WiFi没有开启", 0).show();
        }
    }

    public void startScan(Context context) {
        this.mWifiManager.startScan();
        updateWifiList();
    }

    public List<ScanResult> getWifiList() {
        return this.mWifiList;
    }

    public StringBuilder lookUpScan() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < this.mWifiList.size(); i++) {
            stringBuilder.append("Index_" + new Integer(i + 1).toString() + ":");
            stringBuilder.append(this.mWifiList.get(i).toString());
            stringBuilder.append("/n");
        }
        return stringBuilder;
    }

    public String getMacAddress() {
        return this.mWifiInfo == null ? "NULL" : this.mWifiInfo.getMacAddress();
    }

    public String getBSSID() {
        return this.mWifiInfo == null ? "NULL" : this.mWifiInfo.getBSSID();
    }

    public int getIPAddress() {
        if (this.mWifiInfo == null) {
            return 0;
        }
        return this.mWifiInfo.getIpAddress();
    }

    public int getNetworkId() {
        if (this.mWifiInfo == null) {
            return 0;
        }
        return this.mWifiInfo.getNetworkId();
    }

    public String getWifiInfo() {
        return this.mWifiInfo == null ? "NULL" : this.mWifiInfo.toString();
    }

    public void addNetwork(WifiConfiguration wcg) {
        int wcgID = this.mWifiManager.addNetwork(wcg);
        boolean b = this.mWifiManager.enableNetwork(wcgID, true);
        System.out.println("a--" + wcgID);
        System.out.println("b--" + b);
    }

    public void disconnectWifi(int netId) {
        this.mWifiManager.disableNetwork(netId);
        this.mWifiManager.disconnect();
    }

    public void removeWifi(int netId) {
        disconnectWifi(netId);
        this.mWifiManager.removeNetwork(netId);
    }

    public WifiConfiguration isExsits(String SSID) {
        for (WifiConfiguration existingConfig : this.mWifiManager.getConfiguredNetworks()) {
            if (existingConfig.SSID.equals("\"" + SSID + "\"")) {
                return existingConfig;
            }
        }
        return null;
    }

    public boolean isExsit(String SSID) {
        for (WifiConfiguration existingConfig : this.mWifiManager.getConfiguredNetworks()) {
            if (existingConfig.SSID.equals("\"" + SSID + "\"")) {
                return true;
            }
        }
        return false;
    }

    public void connect(WifiConfiguration config) {
        this.mWifiManager.enableNetwork(this.mWifiManager.addNetwork(config), true);
    }

    public void connect(int netid) {
        this.mWifiManager.enableNetwork(netid, true);
    }

    public WifiConfiguration CreateWifiInfo(String SSID, String Password, int Type) {
        WifiConfiguration config = new WifiConfiguration();
        config.allowedAuthAlgorithms.clear();
        config.allowedGroupCiphers.clear();
        config.allowedKeyManagement.clear();
        config.allowedPairwiseCiphers.clear();
        config.allowedProtocols.clear();
        config.SSID = "\"" + SSID + "\"";
        WifiConfiguration tempConfig = IsExsits(SSID);
        if (tempConfig != null) {
            this.mWifiManager.removeNetwork(tempConfig.networkId);
        }
        if (Type == 1) {
            config.wepKeys[0] = TXZResourceManager.STYLE_DEFAULT;
            config.allowedKeyManagement.set(0);
            config.wepTxKeyIndex = 0;
        }
        if (Type == 2) {
            config.hiddenSSID = true;
            config.wepKeys[0] = "\"" + Password + "\"";
            config.allowedAuthAlgorithms.set(1);
            config.allowedGroupCiphers.set(3);
            config.allowedGroupCiphers.set(2);
            config.allowedGroupCiphers.set(0);
            config.allowedGroupCiphers.set(1);
            config.allowedKeyManagement.set(0);
            config.wepTxKeyIndex = 0;
        }
        if (Type == 3) {
            config.preSharedKey = "\"" + Password + "\"";
            config.hiddenSSID = true;
            config.allowedKeyManagement.set(1);
        }
        return config;
    }

    private WifiConfiguration IsExsits(String SSID) {
        for (WifiConfiguration existingConfig : this.mWifiManager.getConfiguredNetworks()) {
            if (existingConfig.SSID.equals("\"" + SSID + "\"")) {
                return existingConfig;
            }
        }
        return null;
    }
}
