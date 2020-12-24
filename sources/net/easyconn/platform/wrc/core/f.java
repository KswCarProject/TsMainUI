package net.easyconn.platform.wrc.core;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import java.util.List;
import java.util.UUID;
import net.easyconn.platform.wrc.core.a.c;
import net.easyconn.platform.wrc.core.b.b;

@TargetApi(18)
/* compiled from: NormalScanHelper */
public class f implements b {
    private BluetoothAdapter b;
    private boolean c = false;
    /* access modifiers changed from: private */
    public c d;
    private BluetoothAdapter.LeScanCallback e = new BluetoothAdapter.LeScanCallback() {
        public void onLeScan(BluetoothDevice bluetoothDevice, int i, byte[] bArr) {
            UUID a2;
            if (bluetoothDevice != null && bArr != null && (a2 = l.a(bArr)) != null && f.this.d != null) {
                List<UUID> c = g.a().c();
                if (c == null || c.isEmpty()) {
                    f.this.d.a((int) ICode.ERROR_SCAN_UUID_NULL);
                } else if (c.contains(a2)) {
                    WrcDevice wrcDevice = new WrcDevice();
                    wrcDevice.setName(bluetoothDevice.getName());
                    wrcDevice.setAddress(bluetoothDevice.getAddress());
                    wrcDevice.setRssi(i);
                    wrcDevice.setUuid(a2);
                    f.this.d.a(wrcDevice);
                }
            }
        }
    };

    f() {
    }

    public void a() {
        if (this.b == null) {
            this.b = BluetoothAdapter.getDefaultAdapter();
        }
    }

    public void a(c cVar) {
        List<UUID> uuidFilter;
        this.d = cVar;
        if (l.a()) {
            if (cVar != null && ((uuidFilter = cVar.getUuidFilter()) == null || uuidFilter.isEmpty())) {
                cVar.a((int) ICode.ERROR_SCAN_UUID_NULL);
            } else if (!this.c) {
                a();
                if (this.b != null) {
                    this.b.startLeScan(this.e);
                    this.c = true;
                }
            }
        } else if (cVar != null) {
            cVar.a(2);
        }
    }

    public void b() {
        if (this.c) {
            try {
                if (this.b != null) {
                    if (this.b.isEnabled()) {
                        this.b.stopLeScan(this.e);
                    } else if (this.d != null) {
                        this.d.a(2);
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            this.d = null;
            this.c = false;
        }
    }

    public void c() {
        b();
        this.b = null;
    }
}
