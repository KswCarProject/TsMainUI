package com.ts.set.setview;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class UISetSroView {
    public static LayoutInflater inflater = null;
    public static FrameLayout m_contentView = null;
    public static LinearLayout m_llSetMain = null;
    public static ScrollView m_svSetMain = null;

    public static void Inint(Context tx) {
        m_contentView = new FrameLayout(tx);
        m_svSetMain = new ScrollView(tx);
        m_llSetMain = new LinearLayout(tx);
        m_llSetMain.setOrientation(1);
        m_llSetMain.setBackgroundColor(-1);
        m_svSetMain.setBackgroundColor(-1);
        m_svSetMain.addView(m_llSetMain);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(-1, -1);
        params.setMargins(0, 0, 0, 0);
        m_contentView.addView(m_svSetMain, params);
        m_contentView.setBackgroundColor(-1);
        inflater = (LayoutInflater) tx.getSystemService("layout_inflater");
    }

    public static void Inint(Context tx, int id) {
        m_contentView = new FrameLayout(tx);
        m_svSetMain = new ScrollView(tx);
        m_llSetMain = new LinearLayout(tx);
        m_llSetMain.setOrientation(1);
        m_llSetMain.setBackgroundColor(0);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(-1, -1);
        params.setMargins(0, 0, 0, 0);
        m_contentView.addView(m_svSetMain, params);
        m_contentView.setBackgroundColor(-1);
        inflater = (LayoutInflater) tx.getSystemService("layout_inflater");
    }

    public static void Show(Context tx) {
        ((Activity) tx).requestWindowFeature(1);
        ((Activity) tx).setContentView(m_contentView);
    }

    public static void AddView(View vw) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(1024, 79);
        params.setMargins(0, 0, 0, 0);
        m_llSetMain.addView(vw, params);
    }

    public static void AddView(View vw, int nWidth, int nHeight) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(nWidth, nHeight);
        params.setMargins(0, 0, 0, 0);
        m_llSetMain.addView(vw, params);
    }

    public static int b2i(boolean bData) {
        if (bData) {
            return 1;
        }
        return 0;
    }
}
