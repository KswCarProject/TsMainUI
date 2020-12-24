package net.easyconn.platform.wrc.core;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.UUID;

public class WrcDevice implements Parcelable {
    public static final Parcelable.Creator<WrcDevice> CREATOR = new Parcelable.Creator<WrcDevice>() {
        /* renamed from: a */
        public WrcDevice createFromParcel(Parcel parcel) {
            return new WrcDevice(parcel);
        }

        /* renamed from: a */
        public WrcDevice[] newArray(int i) {
            return new WrcDevice[i];
        }
    };
    private String address;
    private String firmware;
    private String hardware;
    private String name;
    private int rssi;
    private String software;
    private UUID uuid;

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String str) {
        this.address = str;
    }

    public int getRssi() {
        return this.rssi;
    }

    public void setRssi(int i) {
        this.rssi = i;
    }

    public UUID getUuid() {
        return this.uuid;
    }

    public void setUuid(UUID uuid2) {
        this.uuid = uuid2;
    }

    public String getFirmware() {
        return this.firmware;
    }

    public String getHardware() {
        return this.hardware;
    }

    public String getSoftware() {
        return this.software;
    }

    public void setFirmware(String str) {
        this.firmware = str;
    }

    public void setHardware(String str) {
        this.hardware = str;
    }

    public void setSoftware(String str) {
        this.software = str;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        WrcDevice wrcDevice = (WrcDevice) obj;
        if (this.address != null) {
            return this.address.equals(wrcDevice.address);
        }
        if (wrcDevice.address != null) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        if (this.address != null) {
            return this.address.hashCode();
        }
        return 0;
    }

    public String toString() {
        return "WrcDevice{name='" + this.name + '\'' + ", address='" + this.address + '\'' + ", rssi=" + this.rssi + '}';
    }

    public String getVersion() {
        return "firmware:" + this.firmware + " hardware:" + this.hardware + " software:" + this.software;
    }

    public WrcDevice() {
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.name);
        parcel.writeString(this.address);
        parcel.writeInt(this.rssi);
        parcel.writeSerializable(this.uuid);
        parcel.writeString(this.firmware);
        parcel.writeString(this.hardware);
        parcel.writeString(this.software);
    }

    protected WrcDevice(Parcel parcel) {
        this.name = parcel.readString();
        this.address = parcel.readString();
        this.rssi = parcel.readInt();
        this.uuid = (UUID) parcel.readSerializable();
        this.firmware = parcel.readString();
        this.hardware = parcel.readString();
        this.software = parcel.readString();
    }
}
