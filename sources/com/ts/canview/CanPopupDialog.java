package com.ts.canview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.ts.MainUI.R;
import com.ts.other.CustomDialog;
import com.txznet.sdk.TXZResourceManager;

public class CanPopupDialog extends CustomDialog implements View.OnClickListener {
    private static final int[] mBk = {R.drawable.can_comm_msgbox_line2, R.drawable.can_comm_msgbox_line3, R.drawable.can_comm_msgbox_line4, R.drawable.can_comm_msgbox_line5, R.drawable.can_comm_msgbox_line6};
    private OnItemClick mCallBack;
    private TextView[] mTextViewArray;
    private View[] mViewArray;

    public interface OnItemClick {
        void onItem(int i);
    }

    public CanPopupDialog(Context context, int[] text, int sel, View anchor, OnItemClick cb) {
        String[] array = new String[text.length];
        for (int i = 0; i < text.length; i++) {
            array[i] = context.getResources().getString(text[i]);
        }
        createDlg(context, array, sel, anchor, cb);
    }

    public CanPopupDialog(Context context, String[] text, int sel, View anchor, OnItemClick cb) {
        createDlg(context, text, sel, anchor, cb);
    }

    public void createDlg(Context context, String[] text, int sel, View anchor, OnItemClick cb) {
        this.mCallBack = cb;
        if (text.length >= 2) {
            super.create(R.layout.can_popup_bk, context);
            int[] location = new int[2];
            anchor.getLocationOnScreen(location);
            location[1] = location[1] - GetStatusBarHeight(context);
            WindowManager.LayoutParams wmlp = this.mWindow.getAttributes();
            wmlp.gravity = 51;
            wmlp.x = location[0];
            if ((location[1] - (text.length * 84)) - 15 >= 0) {
                wmlp.y = (location[1] - (text.length * 84)) - 15;
            } else {
                wmlp.y = location[1] + anchor.getHeight() + 15;
            }
            this.mWindow.setAttributes(wmlp);
            int line = text.length;
            ScrollView scrView = (ScrollView) this.mWindow.findViewById(R.id.can_comm_scrview);
            if (line >= 7 || line <= 1) {
                scrView.setBackgroundResource(R.drawable.can_comm_msgbox_line6);
            } else {
                scrView.setBackgroundResource(mBk[line - 2]);
            }
            LinearLayout layout = (LinearLayout) this.mWindow.findViewById(R.id.can_comm_lineview);
            this.mViewArray = new View[line];
            this.mTextViewArray = new TextView[line];
            LayoutInflater li = LayoutInflater.from(context);
            for (int i = 0; i < line; i++) {
                this.mViewArray[i] = li.inflate(R.layout.can_item_popup_menu, (ViewGroup) null);
                this.mViewArray[i].setTag(Integer.valueOf(i));
                this.mTextViewArray[i] = (TextView) this.mViewArray[i].findViewById(R.id.val);
                this.mTextViewArray[i].setText(text[i]);
                layout.addView(this.mViewArray[i]);
                this.mViewArray[i].setOnClickListener(this);
            }
            if (sel >= 0 && sel < line) {
                this.mTextViewArray[sel].setSelected(true);
            }
        }
    }

    private int GetStatusBarHeight(Context context) {
        try {
            Class<?> c = Class.forName("com.android.internal.R$dimen");
            return context.getResources().getDimensionPixelSize(Integer.parseInt(c.getField("status_bar_height").get(c.newInstance()).toString()));
        } catch (Exception e1) {
            Log.d(TXZResourceManager.STYLE_DEFAULT, "get status bar height fail");
            e1.printStackTrace();
            return 0;
        }
    }

    public void onClick(View v) {
        dismiss();
        if (this.mCallBack != null) {
            this.mCallBack.onItem(((Integer) v.getTag()).intValue());
        }
    }
}
