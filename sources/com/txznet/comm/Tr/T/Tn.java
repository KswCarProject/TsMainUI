package com.txznet.comm.Tr.T;

/* compiled from: Proguard */
public class Tn {

    /* compiled from: Proguard */
    public static class T {

        /* renamed from: T  reason: collision with root package name */
        public int f363T = 0;
        public byte[] T9;
        public int Tn = 0;
        public int Tr = 1;
        public int Ty = 0;

        public T() {
        }

        public T(int udpId, int invokeType, int cmd, byte[] data) {
            this.f363T = udpId;
            this.Tr = invokeType;
            this.Ty = cmd;
            this.T9 = data;
        }

        public static boolean T(int cmd) {
            switch (cmd) {
                case 0:
                case 1:
                case 2:
                case 10:
                case 11:
                case 12:
                case 13:
                    return true;
                default:
                    return false;
            }
        }
    }

    public static byte[] T(int type, byte[] data) {
        byte[] addedData = new byte[(data.length + 4)];
        byte[] typeData = T(type);
        addedData[0] = typeData[0];
        addedData[1] = typeData[1];
        addedData[2] = typeData[2];
        addedData[3] = typeData[3];
        for (int i = 0; i < data.length; i++) {
            addedData[i + 4] = data[i];
        }
        return addedData;
    }

    public static byte[] T(T udpData) {
        return T(udpData.f363T, udpData.Tr, udpData.Ty, udpData.T9);
    }

    public static byte[] T(int udpId, int invokeType, int cmd, byte[] originalData) {
        byte b;
        if (originalData == null) {
            originalData = new byte[0];
        }
        byte[] bytes = new byte[(originalData.length <= Ty.T().Tr() ? originalData.length + Ty.T().Tn() : Ty.T().Tr() + Ty.T().Tn())];
        bytes[0] = (byte) udpId;
        if (invokeType == 1) {
            b = 1;
        } else {
            b = 0;
        }
        bytes[1] = b;
        byte[] cmdByte = T(cmd);
        bytes[2] = cmdByte[0];
        bytes[3] = cmdByte[1];
        bytes[4] = cmdByte[2];
        bytes[5] = cmdByte[3];
        byte[] lengthByte = T(originalData.length);
        bytes[6] = lengthByte[0];
        bytes[7] = lengthByte[1];
        bytes[8] = lengthByte[2];
        bytes[9] = lengthByte[3];
        int i = 0;
        while (i < originalData.length && i < Ty.T().Tr()) {
            bytes[Ty.T().Tn() + i] = originalData[i];
            i++;
        }
        return bytes;
    }

    public static T T(byte[] transferData) {
        int i;
        if (transferData.length < Ty.T().Tn()) {
            return null;
        }
        T udpData = new T();
        udpData.f363T = transferData[0];
        if (udpData.f363T < 0 || (transferData[1] != 0 && transferData[1] != 1)) {
            return null;
        }
        if (transferData[1] == 0) {
            i = 2;
        } else {
            i = 1;
        }
        udpData.Tr = i;
        udpData.Ty = Tr(new byte[]{transferData[2], transferData[3], transferData[4], transferData[5]});
        if (!T.T(udpData.Ty)) {
            return null;
        }
        udpData.Tn = Tr(new byte[]{transferData[6], transferData[7], transferData[8], transferData[9]});
        if (udpData.Tn <= 0 || udpData.Tn > Ty.T().Tr()) {
            return null;
        }
        byte[] bytes = new byte[udpData.Tn];
        for (int i2 = 0; i2 < bytes.length; i2++) {
            bytes[i2] = transferData[Ty.T().Tn() + i2];
        }
        udpData.T9 = bytes;
        return udpData;
    }

    private static int Tr(byte[] b) {
        return (b[3] & 255) | ((b[2] & 255) << 8) | ((b[1] & 255) << 16) | ((b[0] & 255) << 24);
    }

    private static byte[] T(int a) {
        return new byte[]{(byte) ((a >> 24) & 255), (byte) ((a >> 16) & 255), (byte) ((a >> 8) & 255), (byte) (a & 255)};
    }
}
