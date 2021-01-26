package com.mediatek.galleryfeature.pq.filter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.mediatek.miravision.setting.MiraVisionJni;
import com.ts.MainUI.R;
import com.ts.bt.ContactInfo;
import com.txznet.sdk.TXZResourceManager;

public class FilterWindow {
    private static final String SAVE_PATH = "/storage/emulated/0/Ts/filter.txt";
    private static final int[] mDefaultValues = {3, 5, 125, 4, 4, 9, 9, 9, 9, 9, 9};
    private static final int[] mDefaultValues2 = {3, 5, 125, 6, 4, 9, 9, 4, 9, 9, 9};
    private static FilterWindow mInstance = null;
    public final int FIRST_SYSTEM_WINDOW = 2000;
    public final String TAG = "BtPinDialog";
    public final int TYPE_NAVIGATION_BAR = 2019;
    public final int TYPE_STATUS_BAR_SUB_PANEL = 2017;
    boolean isShow = false;
    private Button mBtnCancel;
    private Button mBtnReset;
    Context mContext;
    private int[] mFilerRanges = new int[this.mFilterStrs.length];
    /* access modifiers changed from: private */
    public int[] mFilerValues = new int[this.mFilterStrs.length];
    Filter mFilter = new Filter();
    private final String[] mFilterStrs = {"Brightness", "Contrast", "Hue", "Sat", "Sharp", "GrassToneH", "GrassToneS", "SkinToneH", "SkinToneS", "SkyToneH", "SkyToneS"};
    LinearLayout mFilterView;
    SeekBar mSeekBar;
    private SeekBar[] mSeekBars = new SeekBar[this.mFilterStrs.length];
    private long mShowViewTime = 0;
    float mTouchX;
    float mTouchY;
    TextView mTvGarmaValue;
    /* access modifiers changed from: private */
    public TextView[] mTvValues = new TextView[this.mFilterStrs.length];
    private WindowManager.LayoutParams mWindowLayoutParams;
    private WindowManager mWindowManager;
    int nNUm = 0;
    int startX;
    int startY;
    float x;
    float y;

    public static FilterWindow getInstance() {
        if (mInstance == null) {
            mInstance = new FilterWindow();
        }
        return mInstance;
    }

    public void initWindow(Context context) {
        Log.d("BtPinDialog", "showWindow");
        if (this.mContext == null) {
            this.mContext = context;
            this.mWindowManager = (WindowManager) context.getSystemService("window");
            this.mWindowLayoutParams = new WindowManager.LayoutParams();
            this.mWindowLayoutParams.type = 2003;
            this.mWindowLayoutParams.flags = 8;
            this.mWindowLayoutParams.format = 1;
            this.mWindowLayoutParams.gravity = 51;
            this.mWindowLayoutParams.width = 500;
            this.mWindowLayoutParams.height = -2;
            this.mFilterView = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.filter_layout, (ViewGroup) null);
            this.mFilterView.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    FilterWindow.this.x = event.getRawX();
                    FilterWindow.this.y = event.getRawY() - 55.0f;
                    switch (event.getAction()) {
                        case 0:
                            FilterWindow.this.mTouchX = event.getX();
                            FilterWindow.this.mTouchY = event.getY();
                            FilterWindow.this.startX = (int) event.getRawX();
                            FilterWindow.this.startY = (int) event.getRawY();
                            return false;
                        case 2:
                            FilterWindow.this.updateViewPosition();
                            return false;
                        default:
                            return false;
                    }
                }
            });
            this.mBtnCancel = (Button) this.mFilterView.findViewById(R.id.filter_cancel);
            this.mBtnCancel.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    FilterWindow.this.hideView();
                }
            });
            updateRange();
            addFilterViews();
            addResetButton();
        }
    }

    private void updateRange() {
        this.mFilerRanges[0] = this.mFilter.nativeGetBrightnessAdjRange() - 1;
        this.mFilerRanges[1] = this.mFilter.nativeGetContrastAdjRange() - 1;
        this.mFilerRanges[2] = this.mFilter.nativeGetHueAdjRange() - 1;
        this.mFilerRanges[3] = this.mFilter.nativeGetSatAdjRange() - 1;
        this.mFilerRanges[4] = this.mFilter.nativeGetSharpAdjRange() - 1;
        this.mFilerRanges[5] = this.mFilter.nativeGetGrassToneHRange() - 1;
        this.mFilerRanges[6] = this.mFilter.nativeGetGrassToneSRange() - 1;
        this.mFilerRanges[7] = this.mFilter.nativeGetSkinToneHRange() - 1;
        this.mFilerRanges[8] = this.mFilter.nativeGetSkinToneSRange() - 1;
        this.mFilerRanges[9] = this.mFilter.nativeGetSkyToneHRange() - 1;
        this.mFilerRanges[10] = this.mFilter.nativeGetSkyToneSRange() - 1;
    }

    private void updateValues(boolean isFile, String s) {
        Log.d("filter", "isFile: " + isFile + " s: " + s);
        if (isFile) {
            String[] strs = s.split(ContactInfo.COMBINATION_SEPERATOR);
            int length = strs.length;
            for (int i = 0; i < length; i++) {
                if (i < this.mFilerValues.length) {
                    this.mFilerValues[i] = Integer.valueOf(strs[i]).intValue();
                }
            }
            return;
        }
        this.mFilerValues[0] = this.mFilter.nativeGetBrightnessAdjIndex();
        this.mFilerValues[1] = this.mFilter.nativeGetContrastAdjIndex();
        this.mFilerValues[2] = this.mFilter.nativeGetHueAdjIndex();
        this.mFilerValues[3] = this.mFilter.nativeGetSatAdjIndex();
        this.mFilerValues[4] = this.mFilter.nativeGetSharpAdjIndex();
        this.mFilerValues[5] = this.mFilter.nativeGetGrassToneHIndex();
        this.mFilerValues[6] = this.mFilter.nativeGetGrassToneSIndex();
        this.mFilerValues[7] = this.mFilter.nativeGetSkinToneHIndex();
        this.mFilerValues[8] = this.mFilter.nativeGetSkinToneSIndex();
        this.mFilerValues[9] = this.mFilter.nativeGetSkyToneHIndex();
        this.mFilerValues[10] = this.mFilter.nativeGetSkyToneSIndex();
    }

    /* access modifiers changed from: package-private */
    public void setFilterValues(int tag, int progress) {
        switch (tag) {
            case 0:
                this.mFilter.nativeSetBrightnessAdjIndex(progress);
                return;
            case 1:
                this.mFilter.nativeSetContrastAdjIndex(progress);
                return;
            case 2:
                this.mFilter.nativeSetHueAdjIndex(progress);
                return;
            case 3:
                this.mFilter.nativeSetSatAdjIndex(progress);
                return;
            case 4:
                this.mFilter.nativeSetSharpAdjIndex(progress);
                return;
            case 5:
                this.mFilter.nativeSetGrassToneHIndex(progress);
                return;
            case 6:
                this.mFilter.nativeSetGrassToneSIndex(progress);
                return;
            case 7:
                this.mFilter.nativeSetSkinToneHIndex(progress);
                return;
            case 8:
                this.mFilter.nativeSetSkinToneSIndex(progress);
                return;
            case 9:
                this.mFilter.nativeSetSkyToneHIndex(progress);
                return;
            case 10:
                this.mFilter.nativeSetSkyToneSIndex(progress);
                return;
            default:
                return;
        }
    }

    public void addFilterViews() {
        updateRange();
        int length = this.mFilterStrs.length;
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        LinearLayout ll = (LinearLayout) LayoutInflater.from(this.mContext).inflate(R.layout.filter_item, (ViewGroup) null);
        this.mSeekBar = (SeekBar) ll.findViewById(R.id.filter_seekbar);
        this.mSeekBar.setMax(3);
        ((TextView) ll.findViewById(R.id.filter_tv_flag)).setText("Gamma");
        this.mTvGarmaValue = (TextView) ll.findViewById(R.id.filter_tv_value);
        this.mTvGarmaValue.setText("0");
        this.mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStopTrackingTouch(SeekBar arg0) {
            }

            public void onStartTrackingTouch(SeekBar arg0) {
            }

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                FilterWindow.this.mTvGarmaValue.setText(new StringBuilder(String.valueOf(progress)).toString());
                if (fromUser) {
                    Log.d("filter", "gamma==" + progress);
                    if (progress < 3) {
                        MiraVisionJni.nativeSetPictureMode(progress);
                    } else {
                        MiraVisionJni.nativeSetPictureMode(1);
                    }
                }
            }
        });
        this.mFilterView.addView(ll, layoutParams);
        for (int i = 0; i < length; i++) {
            LinearLayout ll2 = (LinearLayout) LayoutInflater.from(this.mContext).inflate(R.layout.filter_item, (ViewGroup) null);
            this.mSeekBars[i] = (SeekBar) ll2.findViewById(R.id.filter_seekbar);
            this.mSeekBars[i].setTag(Integer.valueOf(i));
            this.mSeekBars[i].setMax(this.mFilerRanges[i]);
            ((TextView) ll2.findViewById(R.id.filter_tv_flag)).setText(this.mFilterStrs[i]);
            this.mTvValues[i] = (TextView) ll2.findViewById(R.id.filter_tv_value);
            this.mSeekBars[i].setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                public void onStopTrackingTouch(SeekBar seekBar) {
                }

                public void onStartTrackingTouch(SeekBar seekBar) {
                }

                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    int tag = ((Integer) seekBar.getTag()).intValue();
                    FilterWindow.this.mTvValues[tag].setText(new StringBuilder(String.valueOf(progress)).toString());
                    if (fromUser) {
                        Log.d("filter", "onProgressChanged");
                        FilterWindow.this.mFilerValues[tag] = progress;
                        FilterWindow.this.setFilterValues(tag, progress);
                    }
                }
            });
            layoutParams.topMargin = 3;
            this.mFilterView.addView(ll2, layoutParams);
        }
    }

    /* access modifiers changed from: package-private */
    public void addResetButton() {
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(this.mContext).inflate(R.layout.filter_reset_layout, (ViewGroup) null);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.topMargin = 3;
        this.mBtnReset = (Button) linearLayout.findViewById(R.id.filter_reset);
        this.mBtnReset.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (FilterWindow.this.nNUm % 2 == 0) {
                    FilterWindow.this.updateFilter(1);
                } else {
                    FilterWindow.this.updateFilter(0);
                }
                FilterWindow.this.nNUm++;
            }
        });
        this.mFilterView.addView(linearLayout, layoutParams);
    }

    /* access modifiers changed from: package-private */
    public void updateFilter(int nState) {
        Log.d("filter", "updateFilter nState=" + nState);
        if (nState == 1) {
            for (int i = 0; i < mDefaultValues.length; i++) {
                setFilterValues(i, mDefaultValues[i]);
            }
            for (int i2 = 0; i2 < this.mFilerValues.length; i2++) {
                this.mFilerValues[i2] = mDefaultValues[i2];
                this.mSeekBars[i2].setProgress(mDefaultValues[i2]);
                this.mTvValues[i2].setText(new StringBuilder(String.valueOf(mDefaultValues[i2])).toString());
            }
            return;
        }
        for (int i3 = 0; i3 < mDefaultValues2.length; i3++) {
            setFilterValues(i3, mDefaultValues2[i3]);
        }
        for (int i4 = 0; i4 < mDefaultValues2.length; i4++) {
            this.mFilerValues[i4] = mDefaultValues2[i4];
            this.mSeekBars[i4].setProgress(mDefaultValues2[i4]);
            this.mTvValues[i4].setText(new StringBuilder(String.valueOf(mDefaultValues2[i4])).toString());
        }
    }

    /* access modifiers changed from: package-private */
    public void readFilter() {
        String s = readFilterFile();
        if (s != null) {
            String[] strs = s.split(ContactInfo.COMBINATION_SEPERATOR);
            int length = strs.length;
            for (int i = 0; i < length; i++) {
                if (i < this.mFilerValues.length) {
                    this.mFilerValues[i] = Integer.valueOf(strs[i]).intValue();
                    setFilterValues(i, this.mFilerValues[i]);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void updateViewPosition() {
        this.mWindowLayoutParams.x = (int) (this.x - this.mTouchX);
        this.mWindowLayoutParams.y = (int) (this.y - this.mTouchY);
        this.mWindowManager.updateViewLayout(this.mFilterView, this.mWindowLayoutParams);
    }

    public void showView() {
        if (!this.isShow && this.mFilterView != null) {
            Log.d("filter", "showView");
            this.isShow = true;
            String s = readFilterFile();
            if (s == null) {
                updateValues(false, TXZResourceManager.STYLE_DEFAULT);
            } else {
                updateValues(true, s);
            }
            for (int i = 0; i < this.mFilerValues.length; i++) {
                this.mSeekBars[i].setProgress(this.mFilerValues[i]);
                this.mTvValues[i].setText(new StringBuilder(String.valueOf(this.mFilerValues[i])).toString());
            }
            this.mWindowManager.addView(this.mFilterView, this.mWindowLayoutParams);
        }
    }

    public void hideView() {
        if (this.isShow && this.mFilterView != null) {
            Log.d("filter", "hideView");
            saveFilterFile();
            this.mWindowManager.removeView(this.mFilterView);
            this.isShow = false;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x006d A[SYNTHETIC, Splitter:B:27:0x006d] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x007f A[SYNTHETIC, Splitter:B:35:0x007f] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x008e A[SYNTHETIC, Splitter:B:41:0x008e] */
    /* JADX WARNING: Removed duplicated region for block: B:58:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:60:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:24:0x0068=Splitter:B:24:0x0068, B:32:0x007a=Splitter:B:32:0x007a} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void saveFilterFile() {
        /*
            r9 = this;
            java.io.File r3 = new java.io.File
            java.lang.String r7 = "/storage/emulated/0/Ts/filter.txt"
            r3.<init>(r7)
            boolean r7 = r3.exists()
            if (r7 != 0) goto L_0x001b
            java.io.File r5 = r3.getParentFile()
            boolean r7 = r5.exists()
            if (r7 != 0) goto L_0x001b
            r5.mkdir()
        L_0x001b:
            r0 = 0
            java.io.BufferedWriter r1 = new java.io.BufferedWriter     // Catch:{ FileNotFoundException -> 0x00a6, IOException -> 0x0079 }
            java.io.FileWriter r7 = new java.io.FileWriter     // Catch:{ FileNotFoundException -> 0x00a6, IOException -> 0x0079 }
            r7.<init>(r3)     // Catch:{ FileNotFoundException -> 0x00a6, IOException -> 0x0079 }
            r1.<init>(r7)     // Catch:{ FileNotFoundException -> 0x00a6, IOException -> 0x0079 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ FileNotFoundException -> 0x0066, IOException -> 0x00a3, all -> 0x00a0 }
            r6.<init>()     // Catch:{ FileNotFoundException -> 0x0066, IOException -> 0x00a3, all -> 0x00a0 }
            r4 = 0
        L_0x002c:
            int[] r7 = r9.mFilerValues     // Catch:{ FileNotFoundException -> 0x0066, IOException -> 0x00a3, all -> 0x00a0 }
            int r7 = r7.length     // Catch:{ FileNotFoundException -> 0x0066, IOException -> 0x00a3, all -> 0x00a0 }
            if (r4 < r7) goto L_0x0042
            java.lang.String r7 = r6.toString()     // Catch:{ FileNotFoundException -> 0x0066, IOException -> 0x00a3, all -> 0x00a0 }
            r1.write(r7)     // Catch:{ FileNotFoundException -> 0x0066, IOException -> 0x00a3, all -> 0x00a0 }
            if (r1 == 0) goto L_0x009e
            r1.flush()     // Catch:{ IOException -> 0x009a }
            r1.close()     // Catch:{ IOException -> 0x009a }
            r0 = r1
        L_0x0041:
            return
        L_0x0042:
            if (r4 != 0) goto L_0x004e
            int[] r7 = r9.mFilerValues     // Catch:{ FileNotFoundException -> 0x0066, IOException -> 0x00a3, all -> 0x00a0 }
            r7 = r7[r4]     // Catch:{ FileNotFoundException -> 0x0066, IOException -> 0x00a3, all -> 0x00a0 }
            r6.append(r7)     // Catch:{ FileNotFoundException -> 0x0066, IOException -> 0x00a3, all -> 0x00a0 }
        L_0x004b:
            int r4 = r4 + 1
            goto L_0x002c
        L_0x004e:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ FileNotFoundException -> 0x0066, IOException -> 0x00a3, all -> 0x00a0 }
            java.lang.String r8 = ","
            r7.<init>(r8)     // Catch:{ FileNotFoundException -> 0x0066, IOException -> 0x00a3, all -> 0x00a0 }
            int[] r8 = r9.mFilerValues     // Catch:{ FileNotFoundException -> 0x0066, IOException -> 0x00a3, all -> 0x00a0 }
            r8 = r8[r4]     // Catch:{ FileNotFoundException -> 0x0066, IOException -> 0x00a3, all -> 0x00a0 }
            java.lang.StringBuilder r7 = r7.append(r8)     // Catch:{ FileNotFoundException -> 0x0066, IOException -> 0x00a3, all -> 0x00a0 }
            java.lang.String r7 = r7.toString()     // Catch:{ FileNotFoundException -> 0x0066, IOException -> 0x00a3, all -> 0x00a0 }
            r6.append(r7)     // Catch:{ FileNotFoundException -> 0x0066, IOException -> 0x00a3, all -> 0x00a0 }
            goto L_0x004b
        L_0x0066:
            r2 = move-exception
            r0 = r1
        L_0x0068:
            r2.printStackTrace()     // Catch:{ all -> 0x008b }
            if (r0 == 0) goto L_0x0041
            r0.flush()     // Catch:{ IOException -> 0x0074 }
            r0.close()     // Catch:{ IOException -> 0x0074 }
            goto L_0x0041
        L_0x0074:
            r2 = move-exception
            r2.printStackTrace()
            goto L_0x0041
        L_0x0079:
            r2 = move-exception
        L_0x007a:
            r2.printStackTrace()     // Catch:{ all -> 0x008b }
            if (r0 == 0) goto L_0x0041
            r0.flush()     // Catch:{ IOException -> 0x0086 }
            r0.close()     // Catch:{ IOException -> 0x0086 }
            goto L_0x0041
        L_0x0086:
            r2 = move-exception
            r2.printStackTrace()
            goto L_0x0041
        L_0x008b:
            r7 = move-exception
        L_0x008c:
            if (r0 == 0) goto L_0x0094
            r0.flush()     // Catch:{ IOException -> 0x0095 }
            r0.close()     // Catch:{ IOException -> 0x0095 }
        L_0x0094:
            throw r7
        L_0x0095:
            r2 = move-exception
            r2.printStackTrace()
            goto L_0x0094
        L_0x009a:
            r2 = move-exception
            r2.printStackTrace()
        L_0x009e:
            r0 = r1
            goto L_0x0041
        L_0x00a0:
            r7 = move-exception
            r0 = r1
            goto L_0x008c
        L_0x00a3:
            r2 = move-exception
            r0 = r1
            goto L_0x007a
        L_0x00a6:
            r2 = move-exception
            goto L_0x0068
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mediatek.galleryfeature.pq.filter.FilterWindow.saveFilterFile():void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x003e A[SYNTHETIC, Splitter:B:24:0x003e] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x004a A[SYNTHETIC, Splitter:B:30:0x004a] */
    /* JADX WARNING: Removed duplicated region for block: B:47:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:21:0x0039=Splitter:B:21:0x0039, B:13:0x002a=Splitter:B:13:0x002a} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String readFilterFile() {
        /*
            r7 = this;
            java.io.File r4 = new java.io.File
            java.lang.String r6 = "/storage/emulated/0/Ts/filter.txt"
            r4.<init>(r6)
            boolean r6 = r4.exists()
            if (r6 != 0) goto L_0x0010
            r5 = 0
        L_0x000f:
            return r5
        L_0x0010:
            r0 = 0
            java.lang.String r5 = ""
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch:{ FileNotFoundException -> 0x0029, IOException -> 0x0038 }
            java.io.FileReader r6 = new java.io.FileReader     // Catch:{ FileNotFoundException -> 0x0029, IOException -> 0x0038 }
            r6.<init>(r4)     // Catch:{ FileNotFoundException -> 0x0029, IOException -> 0x0038 }
            r1.<init>(r6)     // Catch:{ FileNotFoundException -> 0x0029, IOException -> 0x0038 }
            java.lang.String r5 = r1.readLine()     // Catch:{ FileNotFoundException -> 0x005f, IOException -> 0x005c, all -> 0x0059 }
            if (r1 == 0) goto L_0x0057
            r1.close()     // Catch:{ IOException -> 0x0053 }
            r0 = r1
            goto L_0x000f
        L_0x0029:
            r3 = move-exception
        L_0x002a:
            r3.printStackTrace()     // Catch:{ all -> 0x0047 }
            if (r0 == 0) goto L_0x000f
            r0.close()     // Catch:{ IOException -> 0x0033 }
            goto L_0x000f
        L_0x0033:
            r2 = move-exception
            r2.printStackTrace()
            goto L_0x000f
        L_0x0038:
            r2 = move-exception
        L_0x0039:
            r2.printStackTrace()     // Catch:{ all -> 0x0047 }
            if (r0 == 0) goto L_0x000f
            r0.close()     // Catch:{ IOException -> 0x0042 }
            goto L_0x000f
        L_0x0042:
            r2 = move-exception
            r2.printStackTrace()
            goto L_0x000f
        L_0x0047:
            r6 = move-exception
        L_0x0048:
            if (r0 == 0) goto L_0x004d
            r0.close()     // Catch:{ IOException -> 0x004e }
        L_0x004d:
            throw r6
        L_0x004e:
            r2 = move-exception
            r2.printStackTrace()
            goto L_0x004d
        L_0x0053:
            r2 = move-exception
            r2.printStackTrace()
        L_0x0057:
            r0 = r1
            goto L_0x000f
        L_0x0059:
            r6 = move-exception
            r0 = r1
            goto L_0x0048
        L_0x005c:
            r2 = move-exception
            r0 = r1
            goto L_0x0039
        L_0x005f:
            r3 = move-exception
            r0 = r1
            goto L_0x002a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mediatek.galleryfeature.pq.filter.FilterWindow.readFilterFile():java.lang.String");
    }
}
