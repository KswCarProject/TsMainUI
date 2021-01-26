package com.ts.set;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.text.format.Formatter;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.ts.MainUI.R;
import com.ts.main.common.tool;
import com.yyw.ts70xhw.FtSet;

public class SettingStorageActivity extends Activity {
    private int ImgProgressCloer = Color.parseColor("#039083");
    private int ImgProgressWidth = 5;
    private Button btn_back;
    private double cur;
    private ImageView img_pro;
    private Bitmap mBmpBg;
    private Bitmap mBmpPro;
    private double total;
    private TextView tv_cur;
    private TextView tv_pro;
    private TextView tv_total;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);
        if (this.mBmpBg == null || this.mBmpBg.isRecycled()) {
            this.mBmpBg = BitmapFactory.decodeResource(getResources(), R.drawable.setup_storage_box_up);
        }
        if (this.mBmpPro == null || this.mBmpPro.isRecycled()) {
            this.mBmpPro = Bitmap.createBitmap(this.mBmpBg.getWidth(), this.mBmpBg.getHeight(), this.mBmpBg.getConfig());
        }
        if (FtSet.GetRom() == 0) {
            this.total = (double) tool.GetInstance().GetnEmmcSize();
        } else {
            this.total = (double) (((FtSet.GetRom() - 1) * 16) + 16);
        }
        this.cur = ((double) tool.GetInstance().GetnEmmcSize()) - getPrintSize(getAvailableInternalMemorySize()).doubleValue();
        this.cur = ((double) Math.round(this.cur * 100.0d)) / 100.0d;
        initView();
        setData(this.cur, this.total);
        setImgProgress(this.cur, this.total);
    }

    private void initView() {
        this.img_pro = (ImageView) findViewById(R.id.img_pro);
        this.tv_cur = (TextView) findViewById(R.id.tv_cur);
        this.tv_total = (TextView) findViewById(R.id.tv_total);
        this.tv_pro = (TextView) findViewById(R.id.tv_pro);
        this.btn_back = (Button) findViewById(R.id.btn_back);
        this.btn_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                SettingStorageActivity.this.onBackPressed();
            }
        });
    }

    private void setData(double cur2, double total2) {
        this.tv_cur.setText(String.valueOf(cur2) + " " + getString(R.string.cur_zijie));
        this.tv_total.setText("(" + getString(R.string.totle_zijie1) + " " + total2 + " " + getString(R.string.totle_zijie2) + ")");
        this.tv_pro.setText(String.valueOf((int) ((cur2 / total2) * 100.0d)) + "%");
    }

    private void setImgProgress(double current, double total2) {
        if (total2 != 0.0d) {
            double degree = (360.0d * current) / total2;
            if (degree > 0.0d) {
                Canvas canvas1 = new Canvas(this.mBmpPro);
                canvas1.drawColor(0, PorterDuff.Mode.CLEAR);
                Paint paint = new Paint();
                paint.setAntiAlias(true);
                canvas1.drawBitmap(this.mBmpBg, 0.0f, 0.0f, paint);
                RectF oval = new RectF(3.0f + 0.0f, 2.0f + 0.0f, (float) (this.mBmpBg.getWidth() - 3), (float) (this.mBmpBg.getHeight() - 2));
                paint.setColor(this.ImgProgressCloer);
                paint.setStyle(Paint.Style.STROKE);
                paint.setStrokeWidth((float) this.ImgProgressWidth);
                canvas1.drawArc(oval, -90.0f, (float) degree, false, paint);
                this.img_pro.setImageBitmap(this.mBmpPro);
                return;
            }
            Canvas canvas12 = new Canvas(this.mBmpPro);
            canvas12.drawColor(0, PorterDuff.Mode.CLEAR);
            canvas12.drawBitmap(this.mBmpBg, 0.0f, 0.0f, new Paint());
            this.img_pro.setImageBitmap(this.mBmpPro);
            return;
        }
        Canvas canvas13 = new Canvas(this.mBmpPro);
        canvas13.drawColor(0, PorterDuff.Mode.CLEAR);
        canvas13.drawBitmap(this.mBmpBg, 0.0f, 0.0f, new Paint());
        this.img_pro.setImageBitmap(this.mBmpPro);
    }

    public static long getAvailableInternalMemorySize() {
        StatFs stat = new StatFs(Environment.getDataDirectory().getPath());
        return ((long) stat.getAvailableBlocks()) * ((long) stat.getBlockSize());
    }

    public static Double getPrintSize(long size) {
        return Double.valueOf(((double) ((100 * ((size / 1024) / 1024)) / 1024)) / 100.0d);
    }

    public static String getInternalMemorySize(Context context) {
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        return Formatter.formatFileSize(context, statFs.getBlockCountLong() * statFs.getBlockSizeLong());
    }

    public static String getAvailableInternalMemorySize(Context context) {
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        return Formatter.formatFileSize(context, statFs.getAvailableBlocksLong() * statFs.getBlockSizeLong());
    }

    public static String getExternalMemorySize(Context context) {
        StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        return Formatter.formatFileSize(context, statFs.getBlockCountLong() * statFs.getBlockSizeLong());
    }
}
