package net.easyconn.platform.wrc.core.a;

import java.util.UUID;
import net.easyconn.platform.wrc.core.WrcDevice;

/* compiled from: IWrcCallback */
public interface a {
    void a();

    void a(byte b, byte b2);

    void a(int i);

    void a(UUID uuid, byte[] bArr);

    void a(WrcDevice wrcDevice);

    void b(int i);

    void b(WrcDevice wrcDevice);
}
