package com.txznet.txz.util.recordcenter;

import android.os.Environment;
import android.os.Process;
import com.Tn.Tr.TZ.T;
import com.txznet.comm.Tr.Tr.Tn;
import com.txznet.txz.util.Ty;
import com.txznet.txz.util.recordcenter.T.T;
import com.txznet.txz.util.recordcenter.T.Tr;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/* compiled from: Proguard */
public class TXZAudioRecorder {

    /* renamed from: T  reason: collision with root package name */
    Tr f907T;
    private boolean T9;
    private byte[] Tk;
    private Selector Tn;
    private int Tr;
    private SocketChannel Ty;

    private void T() {
        try {
            this.Ty = SocketChannel.open();
            this.Ty.configureBlocking(true);
            this.Ty.connect(new InetSocketAddress("127.0.0.1", Tn.Tr));
            this.Ty.finishConnect();
            this.Ty.configureBlocking(false);
            this.Tn = Selector.open();
            Tn.T("record client create: " + this.Ty.socket().getLocalSocketAddress());
        } catch (IOException e) {
            e.printStackTrace();
            Tn.Tn("TXZAudioRecorder error : " + e.toString());
        }
    }

    public TXZAudioRecorder(boolean needAEC) {
        int i = 1;
        this.Tr = 1;
        this.Ty = null;
        this.Tn = null;
        this.T9 = false;
        this.Tk = new byte[9];
        this.f907T = null;
        this.Tr = needAEC ? 3 : i;
        T();
    }

    public TXZAudioRecorder(int type) {
        this.Tr = 1;
        this.Ty = null;
        this.Tn = null;
        this.T9 = false;
        this.Tk = new byte[9];
        this.f907T = null;
        this.Tr = type;
        T();
    }

    public TXZAudioRecorder() {
        this(1);
    }

    public synchronized void setType(int type) {
        this.Tr = type;
        if (this.T9) {
            startRecording();
        }
    }

    public int startRecording() {
        return startRecording((Long) null);
    }

    public synchronized int startRecording(Long startTime) {
        int i = 0;
        synchronized (this) {
            try {
                Tn.T("record client start cmd: " + this.Ty.socket().getLocalSocketAddress() + ", startTime = " + startTime);
                if (startTime == null || startTime.longValue() <= 0) {
                    switch (this.Tr) {
                        case 1:
                            this.Tk[0] = 3;
                            break;
                        case 2:
                            this.Tk[0] = 5;
                            break;
                        case 3:
                            this.Tk[0] = 3;
                            break;
                        case 4:
                            this.Tk[0] = 6;
                            break;
                        default:
                            throw new RuntimeException("unknow recorder type");
                    }
                } else {
                    this.Tk[0] = 4;
                    System.arraycopy(Ty.T(startTime.longValue()), 0, this.Tk, 1, 8);
                }
                this.Ty.write(ByteBuffer.wrap(this.Tk));
                this.T9 = true;
            } catch (IOException e) {
                e.printStackTrace();
                Tn.Tn("TXZAudioRecorder error : " + e.toString());
                Ty();
                i = -1;
            }
        }
        return i;
    }

    public synchronized void stop() {
        if (this.Ty == null || this.Tn == null) {
            Tn.Ty("record client stop warnning: Client has released");
        } else {
            this.T9 = false;
            try {
                Tn.T("record client stop cmd: " + this.Ty.socket().getLocalSocketAddress());
                this.Tk[0] = 1;
                this.Ty.write(ByteBuffer.wrap(this.Tk));
                this.Tn.wakeup();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return;
    }

    public int getRecordingState() {
        return this.T9 ? 3 : 1;
    }

    public synchronized void release() {
        this.T9 = false;
        if (this.Tn != null) {
            try {
                this.Tn.close();
            } catch (IOException e) {
            }
            this.Tn = null;
        }
        if (this.Ty != null) {
            Tn.T("record client release: " + this.Ty.socket().getLocalSocketAddress());
            try {
                this.Ty.close();
            } catch (Exception e2) {
            }
            this.Ty = null;
        }
    }

    public void beginSaveCache(int cacheSize) {
        this.f907T = new Tr(cacheSize);
    }

    public boolean endSaveCache(String voiceId, T.C0004T recordData, boolean bEncrypt) {
        com.txznet.T.T.Tr(new com.txznet.txz.util.T.Ty<String, T.C0004T, Boolean>(voiceId, recordData, Boolean.valueOf(bEncrypt)) {
            public void run() {
                String rawFileName;
                if (((Boolean) this.Tn).booleanValue()) {
                    rawFileName = "txz_asr_" + ((String) this.Tr) + ".pcm";
                } else {
                    rawFileName = "." + Process.myPid() + ((String) this.Tr);
                }
                File rawFile = new File(Environment.getExternalStorageDirectory() + "/txz/voice/" + rawFileName);
                if (Ty.Tr) {
                    File voiceDir = new File(Environment.getExternalStorageDirectory(), "/txz/voice1/");
                    if (!voiceDir.exists()) {
                        voiceDir.mkdirs();
                    }
                    rawFile = new File(Environment.getExternalStorageDirectory() + "/txz/voice1/" + Ty.f911T + ((String) this.Tr) + ".pcm");
                }
                try {
                    OutputStream out = new FileOutputStream(rawFile);
                    if (TXZAudioRecorder.this.endSaveCache(out)) {
                        out.close();
                        Ty unused = TXZAudioRecorder.this.T(Environment.getExternalStorageDirectory() + "/txz/voice/" + ("txz_asr_" + ((String) this.Tr) + ".rf"), rawFile, (T.C0004T) this.Ty);
                        if (rawFile != null && !((Boolean) this.Tn).booleanValue()) {
                            rawFile.delete();
                        }
                    } else if (rawFile != null && !((Boolean) this.Tn).booleanValue()) {
                        rawFile.delete();
                    }
                } catch (Exception e) {
                    if (rawFile != null && !((Boolean) this.Tn).booleanValue()) {
                        rawFile.delete();
                    }
                } catch (Throwable th) {
                    Throwable th2 = th;
                    if (rawFile != null && !((Boolean) this.Tn).booleanValue()) {
                        rawFile.delete();
                    }
                    throw th2;
                }
            }
        }, 0);
        return true;
    }

    /* access modifiers changed from: private */
    public Ty T(String filePath, File rawFile, T.C0004T recordData) {
        recordData.Tk = Integer.valueOf(Tr());
        Ty rf = Ty.T(new File(filePath), recordData);
        if (rf == null) {
            return null;
        }
        rf.T(rawFile);
        return rf;
    }

    private int Tr() {
        switch (this.Tr) {
            case 1:
                return 5;
            case 2:
                return 1;
            case 3:
                return 2;
            case 4:
                return 3;
            default:
                return 0;
        }
    }

    public boolean endSaveCache(OutputStream out) {
        try {
            if (this.f907T != null) {
                this.f907T.T(new T.C0023T(out), (Runnable) null);
                return true;
            }
            this.f907T = null;
            return false;
        } catch (IOException e) {
        } finally {
            this.f907T = null;
        }
    }

    public int read(byte[] data, int offset, int len) {
        while (this.T9) {
            try {
                this.Ty.register(this.Tn, 1);
                int n = this.Tn.select();
                if (n < 0) {
                    return n;
                }
                if (n != 0) {
                    Iterator<SelectionKey> keyIter = this.Tn.selectedKeys().iterator();
                    while (keyIter.hasNext()) {
                        SelectionKey key = keyIter.next();
                        keyIter.remove();
                        if (key.isValid() && key.isReadable()) {
                            int r = this.Ty.read(ByteBuffer.wrap(data, offset, len));
                            if (this.f907T != null && r > 0) {
                                this.f907T.T(data, offset, r);
                            }
                            return r;
                        }
                    }
                    continue;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return -1;
            }
        }
        return 0;
    }

    public int getState() {
        return 1;
    }

    public void rebuild() {
    }

    private synchronized void Ty() {
        Tn.T("TXZAudioRecorder reConnectCenter");
        release();
        try {
            Thread.sleep(200);
        } catch (Exception e) {
        }
        T();
    }
}
