package com.android.settingslib.bluetooth;

import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothUuid;
import android.os.ParcelUuid;
import android.util.Log;

public final class BluetoothDeviceFilter {
    public static final Filter ALL_FILTER = new AllFilter((AllFilter) null);
    public static final Filter BONDED_DEVICE_FILTER = new BondedDeviceFilter((BondedDeviceFilter) null);
    private static final Filter[] FILTERS = {ALL_FILTER, new AudioFilter((AudioFilter) null), new TransferFilter((TransferFilter) null), new PanuFilter((PanuFilter) null), new NapFilter((NapFilter) null)};
    private static final String TAG = "BluetoothDeviceFilter";
    public static final Filter UNBONDED_DEVICE_FILTER = new UnbondedDeviceFilter((UnbondedDeviceFilter) null);

    public interface Filter {
        boolean matches(BluetoothDevice bluetoothDevice);
    }

    private BluetoothDeviceFilter() {
    }

    public static Filter getFilter(int filterType) {
        if (filterType >= 0 && filterType < FILTERS.length) {
            return FILTERS[filterType];
        }
        Log.w(TAG, "Invalid filter type " + filterType + " for device picker");
        return ALL_FILTER;
    }

    private static final class AllFilter implements Filter {
        private AllFilter() {
        }

        /* synthetic */ AllFilter(AllFilter allFilter) {
            this();
        }

        public boolean matches(BluetoothDevice device) {
            return true;
        }
    }

    private static final class BondedDeviceFilter implements Filter {
        private BondedDeviceFilter() {
        }

        /* synthetic */ BondedDeviceFilter(BondedDeviceFilter bondedDeviceFilter) {
            this();
        }

        public boolean matches(BluetoothDevice device) {
            return device.getBondState() == 12;
        }
    }

    private static final class UnbondedDeviceFilter implements Filter {
        private UnbondedDeviceFilter() {
        }

        /* synthetic */ UnbondedDeviceFilter(UnbondedDeviceFilter unbondedDeviceFilter) {
            this();
        }

        public boolean matches(BluetoothDevice device) {
            return device.getBondState() != 12;
        }
    }

    private static abstract class ClassUuidFilter implements Filter {
        /* access modifiers changed from: package-private */
        public abstract boolean matches(ParcelUuid[] parcelUuidArr, BluetoothClass bluetoothClass);

        private ClassUuidFilter() {
        }

        /* synthetic */ ClassUuidFilter(ClassUuidFilter classUuidFilter) {
            this();
        }

        public boolean matches(BluetoothDevice device) {
            return matches(device.getUuids(), device.getBluetoothClass());
        }
    }

    private static final class AudioFilter extends ClassUuidFilter {
        private AudioFilter() {
            super((ClassUuidFilter) null);
        }

        /* synthetic */ AudioFilter(AudioFilter audioFilter) {
            this();
        }

        /* access modifiers changed from: package-private */
        public boolean matches(ParcelUuid[] uuids, BluetoothClass btClass) {
            if (uuids != null) {
                if (BluetoothUuid.containsAnyUuid(uuids, A2dpProfile.SINK_UUIDS) || BluetoothUuid.containsAnyUuid(uuids, HeadsetProfile.UUIDS)) {
                    return true;
                }
            } else if (btClass != null && (btClass.doesClassMatch(1) || btClass.doesClassMatch(0))) {
                return true;
            }
            return false;
        }
    }

    private static final class TransferFilter extends ClassUuidFilter {
        private TransferFilter() {
            super((ClassUuidFilter) null);
        }

        /* synthetic */ TransferFilter(TransferFilter transferFilter) {
            this();
        }

        /* access modifiers changed from: package-private */
        public boolean matches(ParcelUuid[] uuids, BluetoothClass btClass) {
            if (uuids != null && BluetoothUuid.isUuidPresent(uuids, BluetoothUuid.ObexObjectPush)) {
                return true;
            }
            if (btClass == null || !btClass.doesClassMatch(2)) {
                return false;
            }
            return true;
        }
    }

    private static final class PanuFilter extends ClassUuidFilter {
        private PanuFilter() {
            super((ClassUuidFilter) null);
        }

        /* synthetic */ PanuFilter(PanuFilter panuFilter) {
            this();
        }

        /* access modifiers changed from: package-private */
        public boolean matches(ParcelUuid[] uuids, BluetoothClass btClass) {
            if (uuids != null && BluetoothUuid.isUuidPresent(uuids, BluetoothUuid.PANU)) {
                return true;
            }
            if (btClass == null || !btClass.doesClassMatch(4)) {
                return false;
            }
            return true;
        }
    }

    private static final class NapFilter extends ClassUuidFilter {
        private NapFilter() {
            super((ClassUuidFilter) null);
        }

        /* synthetic */ NapFilter(NapFilter napFilter) {
            this();
        }

        /* access modifiers changed from: package-private */
        public boolean matches(ParcelUuid[] uuids, BluetoothClass btClass) {
            if (uuids != null && BluetoothUuid.isUuidPresent(uuids, BluetoothUuid.NAP)) {
                return true;
            }
            if (btClass == null || !btClass.doesClassMatch(5)) {
                return false;
            }
            return true;
        }
    }
}
