package com.ts.main.common;

import android.content.Context;
import android.hardware.input.InputManager;
import android.os.StatFs;
import android.os.SystemClock;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.Toast;
import com.autochips.storage.EnvironmentATC;
import com.ts.MainUI.R;
import com.ts.MainUI.TsFile;
import com.ts.bt.ContactInfo;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.Iop;
import com.yyw.ts70xhw.Mcu;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class KeyTouch {
    private static final String COUTCH_CONFIG = "touch.cfg";
    private static final String CTOUCH_FILE = "i2c_touch.cfg";
    public static final int GAMMA_MAX_NUM = 366;
    static KeyTouch MyKeyTouch = null;
    private static final String TAG = "KeyTouch";
    static int TickNum = 100;
    private static final String screenpath = "abc腾实截图/";
    EnvironmentATC EnvATC;
    int TickStep = 0;
    private int TouchTime = 0;
    boolean bDown;
    long clicktime = 0;
    int inputSource = 0;
    Context mContext = null;
    private String[] mGarmarData = {"arm_gamma", "cam_gamma", "dvd_gamma", "usb_gamma", "aux_gamma", "tv_gamma", "fcam_gamma"};
    int nDelaytime = 0;
    private int[] nPoint = new int[3];
    private int[] nPointOld = new int[3];
    private int[] nPointOld2 = new int[3];
    boolean otherDn;

    public static KeyTouch GetInstance() {
        if (MyKeyTouch == null) {
            MyKeyTouch = new KeyTouch();
        }
        return MyKeyTouch;
    }

    /* access modifiers changed from: package-private */
    public int GetFileData(String sPath, byte[] Buf) {
        int nReadNum = 0;
        File file = new File(sPath);
        if (!file.exists() || file.isDirectory()) {
            return 0;
        }
        try {
            FileInputStream fis = new FileInputStream(file);
            nReadNum = fis.read(Buf);
            fis.close();
            return nReadNum;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return nReadNum;
        } catch (IOException e2) {
            e2.printStackTrace();
            return nReadNum;
        }
    }

    public static boolean copyFile(String srcFileName, String destFileName, boolean reWrite) throws IOException {
        Log.d(TAG, "copyFile, begin");
        File srcFile = new File(srcFileName);
        File destFile = new File(destFileName);
        if (!srcFile.exists()) {
            Log.d(TAG, "copyFile, source file not exist.");
            return false;
        } else if (!srcFile.isFile()) {
            Log.d(TAG, "copyFile, source file not a file.");
            return false;
        } else if (!srcFile.canRead()) {
            Log.d(TAG, "copyFile, source file can't read.");
            return false;
        } else {
            if (destFile.exists() && reWrite) {
                Log.d(TAG, "copyFile, before copy File, delete first.");
                destFile.delete();
            }
            try {
                InputStream inStream = new FileInputStream(srcFile);
                FileOutputStream outStream = new FileOutputStream(destFile);
                byte[] buf = new byte[1024];
                while (true) {
                    int byteRead = inStream.read(buf);
                    if (byteRead == -1) {
                        break;
                    }
                    outStream.write(buf, 0, byteRead);
                }
                outStream.flush();
                outStream.close();
                inStream.close();
            } catch (IOException e) {
                throw e;
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            Log.d(TAG, "copyFile, success");
            return true;
        }
    }

    public void SetTouConfig() {
        String[] mCtouchPath = MainSet.GetInstance().GetMountedStorage();
        if (mCtouchPath != null) {
            for (int i = 0; i < mCtouchPath.length; i++) {
                if (TsFile.fileIsExists(String.valueOf(mCtouchPath[i]) + "/" + COUTCH_CONFIG)) {
                    try {
                        String text = TsFile.readFileSdcardFile(String.valueOf(mCtouchPath[i]) + "/" + COUTCH_CONFIG);
                        if (text.indexOf(ContactInfo.COMBINATION_SEPERATOR) >= 0) {
                            String[] stringCFG = text.split(ContactInfo.COMBINATION_SEPERATOR);
                            if (!stringCFG[0].startsWith("0x") || stringCFG.length != 186) {
                                Toast.makeText(this.mContext, "电容参数错误", 0).show();
                            } else {
                                byte[] touchdata = new byte[186];
                                for (int j = 0; j < stringCFG.length; j++) {
                                    if (stringCFG[i].startsWith("0x")) {
                                        touchdata[j] = (byte) Integer.parseInt(stringCFG[j].substring(2, stringCFG[j].length()), 16);
                                        Log.i(TAG, "touchdata[==" + j + "]=" + touchdata[j]);
                                    }
                                }
                                Iop.WriteTouch(touchdata);
                                Toast.makeText(this.mContext, "电容屏参数写入成功", 0).show();
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void Inint(Context MyContext) {
        this.mContext = MyContext;
        this.EnvATC = new EnvironmentATC(this.mContext);
        this.inputSource = getSource(this.inputSource, 4098);
    }

    /* access modifiers changed from: package-private */
    public int CacuLen(int nx1, int ny1, int nx2, int ny2) {
        int nDetx = nx1 - nx2;
        int nDety = ny1 - ny2;
        return (int) Math.sqrt((double) ((nDetx * nDetx) + (nDety * nDety)));
    }

    public void DealTask() {
        if (Iop.GetTouchMul(this.nPoint) != 0) {
            if (this.nPoint[0] != this.nPointOld[0]) {
                if (MainUI.bIsScreenMode) {
                    Log.i(TAG, "nPoint[0]==" + this.nPoint[0]);
                    Log.i(TAG, "nPoint[1]==" + this.nPoint[1]);
                    Log.i(TAG, "nPoint[2]==" + this.nPoint[2]);
                    long dettime = SystemClock.uptimeMillis() - this.clicktime;
                    if (this.nPoint[0] == 1) {
                        if (dettime > 1000) {
                            this.TickStep = 0;
                            Log.i(TAG, "time up==");
                        }
                        if (CacuLen(this.nPointOld2[1], this.nPointOld2[2], this.nPoint[1], this.nPoint[2]) > 50) {
                            this.TickStep = 0;
                            Log.i(TAG, "dis up==");
                        }
                        for (int i = 0; i < 3; i++) {
                            this.nPointOld2[i] = this.nPoint[i];
                        }
                        if (this.TickStep == 0) {
                            this.clicktime = SystemClock.uptimeMillis();
                            this.TickStep = 1;
                        } else if (this.TickStep == 1 && SystemClock.uptimeMillis() - this.clicktime < 500) {
                            takeScreenShot("");
                        }
                    }
                }
                for (int i2 = 0; i2 < 3; i2++) {
                    this.nPointOld[i2] = this.nPoint[i2];
                }
                this.TouchTime = 0;
                if (this.nPointOld[0] > 0) {
                    Log.i(TAG, "nPointOld[0]==" + this.nPointOld[0]);
                    Log.i(TAG, "nPointOld[1]==" + this.nPointOld[1]);
                    Log.i(TAG, "nPointOld[2]==" + this.nPointOld[2]);
                    if (MainSet.IsFlkj()) {
                        if (Mcu.BklisOn() == 0) {
                            Mcu.BklTurn();
                            Log.i(TAG, "Mcu.BklTurn();==");
                        }
                    } else if (this.nPointOld[1] > 0 && this.nPointOld[1] < MainUI.mScrW && this.nPointOld[2] > 0 && this.nPointOld[2] < MainUI.mScrH && Mcu.BklisOn() == 0) {
                        Mcu.BklTurn();
                        Log.i(TAG, "Mcu.BklTurn();==");
                    }
                }
            } else if (this.nPointOld[0] >= 4) {
                this.TouchTime++;
                if (this.TouchTime == 130) {
                    WinShow.show("com.ts.MainUI", "com.ts.main.touch.TouchActivity");
                }
                Log.i(TAG, "TouchTime==" + this.TouchTime);
                Log.i(TAG, "nPoint[0]==" + this.nPoint[0]);
                Log.i(TAG, "nPoint[1]==" + this.nPoint[1]);
                Log.i(TAG, "nPoint[2]==" + this.nPoint[2]);
            } else if (this.nPoint[0] == 1) {
                this.TouchTime++;
                if (this.TouchTime == 130) {
                    boolean bShow = false;
                    if (FtSet.GetCtXYswap() == 3) {
                        if (this.nPoint[1] > 0 && MainUI.mScrW - this.nPoint[1] < 50 && this.nPoint[2] > 0 && this.nPoint[2] < 50) {
                            bShow = true;
                        }
                    } else if (this.nPoint[1] > 0 && this.nPoint[1] < 50 && this.nPoint[2] > 0 && this.nPoint[2] < 50) {
                        bShow = true;
                    }
                    if (bShow && !MainSet.IsFlkj()) {
                        MainAlterwin.GetInstance().ShowMessageWin(String.valueOf(this.mContext.getResources().getString(R.string.custom_num)) + MainSet.GetHMIVersion());
                    }
                }
                Log.i(TAG, "TouchTime==" + this.TouchTime);
                Log.i(TAG, "nPoint[0]==" + this.nPoint[0]);
                Log.i(TAG, "nPoint[1]==" + this.nPoint[1]);
                Log.i(TAG, "nPoint[2]==" + this.nPoint[2]);
            }
        }
    }

    public void SendTouchXY() {
    }

    /* access modifiers changed from: private */
    public void injectMotionEvent(int inputSource2, int action, long when, float x, float y, float pressure) {
        MotionEvent event = MotionEvent.obtain(when, when, action, x, y, pressure, 1.0f, 0, 1.0f, 1.0f, 0, 0);
        event.setSource(inputSource2);
        InputManager.getInstance().injectInputEvent(event, 2);
    }

    private static final int getSource(int inputSource2, int defaultSource) {
        return inputSource2 == 0 ? defaultSource : inputSource2;
    }

    public void SendXYClick(final float x, final float y) {
        new Thread() {
            public void run() {
                KeyTouch.this.inputSource = 4098;
                Log.i(KeyTouch.TAG, "inputSource=" + KeyTouch.this.inputSource);
                KeyTouch.this.injectMotionEvent(KeyTouch.this.inputSource, 0, SystemClock.uptimeMillis(), x, y, 1.0f);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                KeyTouch.this.injectMotionEvent(KeyTouch.this.inputSource, 1, SystemClock.uptimeMillis(), x, y, 0.0f);
            }
        }.start();
    }

    public void sendTap(float x, float y, int State) {
        if (State != 1) {
            this.bDown = false;
            VirTouchManager.clickedUp((int) x, (int) y, VirTouchManager.MUL_ACTION1);
        } else if (this.bDown) {
            VirTouchManager.movepoint((int) x, (int) y, VirTouchManager.MUL_ACTION1);
        } else {
            this.bDown = true;
            VirTouchManager.clickedDown((int) x, (int) y, VirTouchManager.MUL_ACTION1);
        }
    }

    public void sendMulTap(float x, float y, int State) {
        if (State != 1) {
            this.otherDn = false;
            VirTouchManager.clickedUp((int) x, (int) y, VirTouchManager.MUL_ACTION2);
        } else if (this.otherDn) {
            VirTouchManager.movepoint((int) x, (int) y, VirTouchManager.MUL_ACTION2);
        } else {
            VirTouchManager.clickedDown((int) x, (int) y, VirTouchManager.MUL_ACTION2);
            this.otherDn = true;
        }
    }

    private void sendToucXYSync(MotionEvent event) {
    }

    public void sendKeyClick(int keyCode) {
        final int nkeyCode = keyCode;
        new Thread() {
            public void run() {
                try {
                    long now = SystemClock.uptimeMillis();
                    KeyTouch.sendKeySync(new KeyEvent(now, now, 0, nkeyCode, 0, 0, -1, 0, 8, 257));
                    KeyTouch.sendKeySync(new KeyEvent(now, now, 1, nkeyCode, 0, 0, -1, 0, 8, 257));
                } catch (Exception e) {
                    Log.e("Exception when sendPointerSync", e.toString());
                }
            }
        }.start();
    }

    /* access modifiers changed from: private */
    public static void sendKeySync(KeyEvent event) {
        InputManager.getInstance().injectInputEvent(event, 2);
    }

    public long getSDAllSize(String path) {
        StatFs sf = new StatFs(path);
        return ((((long) sf.getBlockCount()) * ((long) sf.getBlockSize())) / 1024) / 1024;
    }

    /* access modifiers changed from: package-private */
    public boolean FathIsExits(String path) {
        if (!TsFile.fileIsExists(String.valueOf(path) + screenpath + "Screeanshot.dat")) {
            TsFile.NewDir(String.valueOf(path) + screenpath);
            try {
                TsFile.writeFileSdcardFile(String.valueOf(path) + screenpath + "Screeanshot.dat", "abc");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (TsFile.fileIsExists(String.valueOf(path) + screenpath + "Screeanshot.dat")) {
            return true;
        }
        return false;
    }

    public boolean takeScreenShot(String imagePath) {
        String[] strSDMountedPath = this.EnvATC.getStorageMountedPaths();
        Log.i(TAG, "strSDMountedPath.length ==" + strSDMountedPath.length);
        int i = 0;
        while (true) {
            if (i < strSDMountedPath.length) {
                Log.i(TAG, "strSDMountedPath[i] ==" + strSDMountedPath[i]);
                if (!strSDMountedPath[i].contains("emulated") && !strSDMountedPath[i].contains("cdfs")) {
                    TsFile.NewDir(String.valueOf(strSDMountedPath[i]) + "/" + screenpath);
                    imagePath = String.valueOf(strSDMountedPath[i]) + "/" + screenpath + new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss", Locale.US).format(new Date()) + ".png";
                    Log.i(TAG, "imagePath ==" + imagePath);
                    break;
                }
                i++;
            } else {
                break;
            }
        }
        if (imagePath.equals("")) {
            Toast.makeText(this.mContext, "截图找不到盘符", 0).show();
            return false;
        }
        try {
            Runtime.getRuntime().exec("screencap -p " + imagePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e2) {
            e2.printStackTrace();
        }
        Toast.makeText(this.mContext, imagePath, 0).show();
        return true;
    }
}
