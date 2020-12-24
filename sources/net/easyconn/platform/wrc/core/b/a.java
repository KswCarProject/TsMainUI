package net.easyconn.platform.wrc.core.b;

import android.content.Context;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import net.easyconn.platform.wrc.core.WrcDevice;

/* compiled from: IWrcConnectHelper */
public interface a {
    public static final List<UUID> a = Arrays.asList(new UUID[]{UUID.fromString("00001c00-d102-11e1-9b23-000efb0000b2"), UUID.fromString("00001c00-d102-11e1-9b23-000efb0000c3"), UUID.fromString("0000474d-0000-1000-8000-00805f9b34fb")});
    public static final List<UUID> b = Arrays.asList(new UUID[]{UUID.fromString("00001c0f-d102-11e1-9b23-000efb0000b2"), UUID.fromString("00004b59-0000-1000-8000-00805f9b34fb")});
    public static final List<UUID> c = Arrays.asList(new UUID[]{UUID.fromString("0000180a-0000-1000-8000-00805f9b34fb")});
    public static final List<UUID> d = Arrays.asList(new UUID[]{UUID.fromString("00002a26-0000-1000-8000-00805f9b34fb")});
    public static final List<UUID> e = Arrays.asList(new UUID[]{UUID.fromString("00002a27-0000-1000-8000-00805f9b34fb")});
    public static final List<UUID> f = Arrays.asList(new UUID[]{UUID.fromString("00002a28-0000-1000-8000-00805f9b34fb")});

    void a(Context context, WrcDevice wrcDevice, net.easyconn.platform.wrc.core.a.a aVar);

    void b();

    void c();
}
