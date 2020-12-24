package net.easyconn.platform.wrc.core;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanRecord;
import android.bluetooth.le.ScanResult;
import java.util.List;
import java.util.UUID;
import net.easyconn.platform.wrc.core.a.c;
import net.easyconn.platform.wrc.core.b.b;

@TargetApi(21)
/* compiled from: LollipopScanHelper */
public class e implements b {
    private BluetoothAdapter b;
    private BluetoothLeScanner c;
    private boolean d = false;
    /* access modifiers changed from: private */
    public c e;
    private ScanCallback f = new ScanCallback() {
        public void onScanResult(int i, ScanResult scanResult) {
            UUID a2;
            if (i == 1) {
                BluetoothDevice device = scanResult.getDevice();
                ScanRecord scanRecord = scanResult.getScanRecord();
                if (device != null && scanRecord != null && scanRecord.getBytes() != null && (a2 = l.a(scanRecord.getBytes())) != null && e.this.e != null) {
                    List<UUID> c = g.a().c();
                    if (c == null || c.isEmpty()) {
                        e.this.e.a((int) ICode.ERROR_SCAN_UUID_NULL);
                    } else if (c.contains(a2)) {
                        WrcDevice wrcDevice = new WrcDevice();
                        wrcDevice.setName(device.getName());
                        wrcDevice.setAddress(device.getAddress());
                        wrcDevice.setRssi(scanResult.getRssi());
                        wrcDevice.setUuid(a2);
                        e.this.e.a(wrcDevice);
                    }
                }
            }
        }
    };

    e() {
    }

    public void a() {
        if (this.b == null) {
            this.b = BluetoothAdapter.getDefaultAdapter();
        }
        if (this.c == null) {
            this.c = this.b.getBluetoothLeScanner();
        }
    }

    public void a(c cVar) {
        List<UUID> uuidFilter;
        this.e = cVar;
        if (l.a()) {
            if (cVar != null && ((uuidFilter = cVar.getUuidFilter()) == null || uuidFilter.isEmpty())) {
                cVar.a((int) ICode.ERROR_SCAN_UUID_NULL);
            } else if (!this.d) {
                a();
                if (this.c != null) {
                    this.c.startScan(this.f);
                    this.d = true;
                }
            }
        } else if (cVar != null) {
            cVar.a(2);
        }
    }

    public void b() {
        if (this.d) {
            try {
                if (!(this.b == null || this.c == null)) {
                    if (this.b.isEnabled()) {
                        this.c.stopScan(this.f);
                    } else if (this.e != null) {
                        this.e.a(2);
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            this.e = null;
            this.d = false;
        }
    }

    public void c() {
        if (this.d) {
            b();
        }
        this.b = null;
        this.c = null;
    }
}
