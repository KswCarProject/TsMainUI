package com.ts.set.setview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.ts.MainUI.R;
import com.ts.other.CustomDialog;
import com.ts.set.setview.SetItemPopupList;

/* compiled from: SetItemPopupList */
class CanPopupMenu extends CustomDialog implements View.OnClickListener {
    private static final int[] mBk = {R.drawable.setup_comm_msgbox_line2, R.drawable.setup_comm_msgbox_line3, R.drawable.setup_comm_msgbox_line4, R.drawable.setup_comm_msgbox_line5, R.drawable.setup_comm_msgbox_line6};
    private SetItemPopupList.onPopItemClick mCallBack;
    private int mId;
    private TextView[] mTextViewArray;
    private String[] mTitleArray;
    private View[] mViewArray;
    private int mselect;
    private ImageView select_img;

    public CanPopupMenu(int Id, Context context, int y, String[] text, int sel, SetItemPopupList.onPopItemClick cb) {
        createDlg(Id, context, y, text, sel, cb);
    }

    public CanPopupMenu() {
    }

    public void createDlg(int Id, Context context, int y, String[] text, int sel, SetItemPopupList.onPopItemClick cb) {
        this.mselect = sel;
        this.mId = Id;
        this.mCallBack = cb;
        if (text.length >= 2) {
            super.create(R.layout.set_popup_bk, context);
            int y2 = (y - GetStatusBarHeight(context)) - 15;
            WindowManager.LayoutParams wmlp = this.mWindow.getAttributes();
            wmlp.gravity = 53;
            wmlp.x = 0;
            wmlp.y = y2;
            this.mWindow.setAttributes(wmlp);
            int line = text.length;
            ScrollView scrView = (ScrollView) this.mWindow.findViewById(R.id.can_comm_scrview);
            if (line >= 7 || line <= 1) {
                scrView.setBackgroundResource(R.drawable.setup_comm_msgbox_line6);
            } else {
                scrView.setBackgroundResource(mBk[line - 2]);
            }
            LinearLayout layout = (LinearLayout) this.mWindow.findViewById(R.id.can_comm_lineview);
            this.mViewArray = new View[line];
            this.mTextViewArray = new TextView[line];
            LayoutInflater li = LayoutInflater.from(context);
            for (int i = 0; i < line; i++) {
                this.mViewArray[i] = li.inflate(R.layout.set_item_popup_menu, (ViewGroup) null);
                this.mViewArray[i].setTag(Integer.valueOf(i));
                this.mTextViewArray[i] = (TextView) this.mViewArray[i].findViewById(R.id.val);
                this.mTextViewArray[i].setText(text[i]);
                layout.addView(this.mViewArray[i]);
                this.mViewArray[i].setOnClickListener(this);
            }
            if (sel >= 0 && sel < line) {
                this.mTextViewArray[sel].setSelected(true);
                this.mViewArray[sel].setBackgroundResource(R.drawable.setup_comm_msgbox_dot_dn);
            }
        }
    }

    private int GetStatusBarHeight(Context context) {
        int sbar = 0;
        try {
            Class<?> c = Class.forName("com.android.internal.R$dimen");
            sbar = context.getResources().getDimensionPixelSize(Integer.parseInt(c.getField("status_bar_height").get(c.newInstance()).toString()));
        } catch (Exception e1) {
            Log.d("", "get status bar height fail");
            e1.printStackTrace();
        }
        Log.d("statusBarHeight = ", new StringBuilder().append(sbar).toString());
        return sbar;
    }

    public void onClick(View v) {
        dismiss();
        if (this.mCallBack != null) {
            this.mCallBack.onItem(this.mId, ((Integer) v.getTag()).intValue());
        }
    }
}
