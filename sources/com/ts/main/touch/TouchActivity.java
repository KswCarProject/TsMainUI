package com.ts.main.touch;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.main.common.MainSet;
import com.ts.main.common.MainUI;
import com.yyw.ts70xhw.FtSet;

public class TouchActivity extends Activity implements UserCallBack {
    boolean bTouch = false;
    ImageView finglueImageView;
    int[] nCacu = new int[4];
    int nNum = 0;
    int nSetp = 0;
    TextView touchButtonView;
    TextView touchZB;
    TextView touchinfo;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_main);
        this.touchZB = (TextView) findViewById(R.id.touch_zuobiao);
        this.touchinfo = (TextView) findViewById(R.id.touch_info);
        this.touchButtonView = (TextView) findViewById(R.id.touch_button);
        this.touchButtonView.setTextSize(0, 45.0f);
        this.touchinfo.setText(R.string.touch_mes_info);
        int nTouchX = MainUI.GetTouchX();
        int nTouchY = MainUI.GetTouchY();
        this.touchZB.setTextSize(0, 25.0f);
        this.touchZB.setText("SrcX=" + MainUI.GetSrcW() + " SrcY=" + MainUI.GetSrcH() + " nTouchX=" + nTouchX + " nTouchY=" + nTouchY);
    }

    /* access modifiers changed from: package-private */
    public void UpdateButton(int nState) {
        RelativeLayout.LayoutParams parat = new RelativeLayout.LayoutParams(81, 81);
        switch (nState) {
            case 0:
                parat.leftMargin = 90;
                parat.topMargin = 50;
                parat.addRule(9);
                parat.addRule(10);
                break;
            case 1:
                parat.leftMargin = 90;
                parat.bottomMargin = 50;
                parat.addRule(9);
                parat.addRule(12);
                break;
            case 2:
                parat.rightMargin = 90;
                parat.bottomMargin = 50;
                parat.addRule(11);
                parat.addRule(12);
                break;
            case 3:
                parat.rightMargin = 90;
                parat.topMargin = 50;
                parat.addRule(11);
                parat.addRule(10);
                break;
        }
        this.touchButtonView.setLayoutParams(parat);
        this.touchButtonView.setText(new StringBuilder().append(nState + 1).toString());
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        if (MainSet.GetInstance().bCeleb) {
            Toast.makeText(this, getResources().getString(R.string.touch_mes_successful), 0).show();
            finish();
        }
        this.nSetp = 0;
        UpdateButton(this.nSetp);
        MainTask.GetInstance().SetUserCallBack(this);
        super.onResume();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
    }

    /* access modifiers changed from: package-private */
    public int CacuXYSwape(int X, int Y, int nStep) {
        int mScrW = MainUI.GetSrcW();
        int mScrH = MainUI.GetSrcH();
        if (MainSet.GetScreenType() == 3) {
            mScrW = MainUI.GetSrcH();
            mScrH = MainUI.GetSrcW();
        }
        switch (nStep) {
            case 1:
                Y = mScrH - Y;
                break;
            case 2:
                Y = mScrH - Y;
                X = mScrW - X;
                break;
            case 3:
                X = mScrW - X;
                break;
        }
        if (FtSet.GetCtXYswap() != 0) {
            switch (FtSet.GetCtXYswap()) {
                case 1:
                    Y = mScrH - Y;
                    break;
                case 2:
                    Y = mScrH - Y;
                    X = mScrW - X;
                    break;
                case 3:
                    X = mScrW - X;
                    break;
            }
        }
        if (X < mScrW / 2 && Y < mScrH / 2) {
            return 0;
        }
        if (X > mScrW / 2 && Y < mScrH / 2) {
            return 3;
        }
        if (X < mScrW / 2 && Y > mScrH / 2) {
            return 1;
        }
        if (X <= mScrW / 2 || Y <= mScrH / 2) {
            return 0;
        }
        return 2;
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() != 0 || this.nSetp >= 4) {
            event.getAction();
        } else {
            this.nCacu[this.nSetp] = CacuXYSwape((int) event.getX(), (int) event.getY(), this.nSetp);
            Toast.makeText(this, "x=" + ((int) event.getX()) + "  y=" + ((int) event.getY()), 0).show();
            this.nSetp++;
            if (this.nSetp == 4) {
                if (this.nCacu[0] == this.nCacu[1] && this.nCacu[0] == this.nCacu[2] && this.nCacu[0] == this.nCacu[3]) {
                    MainSet.GetInstance().bCeleb = true;
                    FtSet.SetCtXYswap(this.nCacu[0]);
                    FtSet.SetCtXYrange(MainSet.GetInstance().GetXYRange());
                    Toast.makeText(getApplicationContext(), String.valueOf(getResources().getString(R.string.touch_mes_successful)) + this.nCacu[0], 0).show();
                } else {
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.touch_mes_fail), 0).show();
                }
                finish();
            } else {
                UpdateButton(this.nSetp);
            }
        }
        return true;
    }

    public void UserAll() {
    }
}
