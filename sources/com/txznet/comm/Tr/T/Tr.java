package com.txznet.comm.Tr.T;

import android.util.Log;
import com.txznet.comm.Tr.T;
import com.txznet.comm.Tr.T.Tn;
import com.txznet.comm.Tr.T.Ty;
import com.txznet.comm.Tr.Tr.Tn;
import com.txznet.sdk.TXZPoiSearchManager;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/* compiled from: Proguard */
public class Tr {

    /* renamed from: T  reason: collision with root package name */
    private DatagramSocket f360T;
    private int Tr = Ty.T().T(T.Tr().getPackageName());
    private int Ty = 0;

    public int T() {
        if (this.f360T != null) {
            return this.Tr;
        }
        do {
            try {
                this.f360T = new DatagramSocket();
                this.f360T.setSoTimeout(TXZPoiSearchManager.DEFAULT_NEARBY_RADIUS);
                this.f360T.setSendBufferSize(1048576);
                Tn.T("UdpClient  sendBuffer:" + this.f360T.getSendBufferSize());
                return this.f360T.getPort();
            } catch (SocketException e) {
                Log.e("UdpClient ", "create DatagramSocket exception , need network permission");
                this.Ty++;
                if (this.Ty > 5) {
                    return -2;
                }
            }
        } while (this.Ty > 5);
        return -2;
    }

    public Tn.T T(Tn.T udpData, Ty.T targetAddr) {
        if (udpData.Tr == 1) {
            return T(Tn.T(udpData), targetAddr);
        }
        return Tr(Tn.T(udpData), targetAddr);
    }

    public Tn.T T(byte[] transferData, Ty.T targetAddr) {
        try {
            this.f360T.send(new DatagramPacket(transferData, transferData.length, InetAddress.getByName(targetAddr.f362T), targetAddr.Tr));
            byte[] buffer = new byte[Ty.T().Ty()];
            DatagramPacket dpRecv = new DatagramPacket(buffer, buffer.length);
            this.f360T.receive(dpRecv);
            return Tn.T(dpRecv.getData());
        } catch (UnknownHostException e) {
            Log.e("UdpClient ", "sendInvokeSync  UnknownHostException");
        } catch (IOException e2) {
            Log.e("UdpClient ", "sendInvokeSync  IOException");
        }
        return null;
    }

    public Tn.T Tr(byte[] transferData, Ty.T targetAddr) {
        try {
            this.f360T.send(new DatagramPacket(transferData, transferData.length, InetAddress.getByName(targetAddr.f362T), targetAddr.Tr));
            return null;
        } catch (UnknownHostException e) {
            Log.e("UdpClient ", " sendInvoke UnknownHostException");
            return null;
        } catch (IOException e2) {
            Log.e("UdpClient ", " sendInvoke IOException");
            return null;
        }
    }
}
