package com.ts.can.ford;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.ts.MainUI.R;
import com.ts.other.CustomImgView;
import com.ts.other.CustomTextView;

/* compiled from: CanFordSyncUIActivity */
class SyncLine {
    private CustomImgView mLtIco;
    private CustomImgView mRtIco;
    private CustomTextView mTitle;
    private View mView;

    public SyncLine(Context context, int id, View.OnClickListener l) {
        this.mView = LayoutInflater.from(context).inflate(R.layout.can_item_sync_cnline, (ViewGroup) null);
        this.mTitle = (CustomTextView) this.mView.findViewById(R.id.sync_line_txt);
        this.mLtIco = (CustomImgView) this.mView.findViewById(R.id.sync_line_ltico);
        this.mRtIco = (CustomImgView) this.mView.findViewById(R.id.sync_line_rtico);
        this.mView.setTag(Integer.valueOf(id));
        this.mView.setOnClickListener(l);
        SetBg(R.drawable.can_sync_rect_up);
    }

    public SyncLine(Context context) {
        this.mView = LayoutInflater.from(context).inflate(R.layout.can_item_sync_usline, (ViewGroup) null);
        this.mTitle = (CustomTextView) this.mView.findViewById(R.id.sync_line_txt);
        SetBg(R.drawable.can_sync_rect_up);
    }

    public void SetCanSel(int cansel) {
        this.mView.setClickable(cansel != 0);
    }

    public void SetSel(boolean sel) {
        if (sel) {
            SetBg(R.drawable.can_sync_rect_dn);
        } else {
            SetBg(R.drawable.can_sync_rect_up);
        }
    }

    public void Show(int val) {
        if (val != 0) {
            this.mView.setVisibility(0);
        } else {
            this.mView.setVisibility(4);
        }
    }

    public void Show(boolean show) {
        if (show) {
            this.mView.setVisibility(0);
        } else {
            this.mView.setVisibility(4);
        }
    }

    public void SetIco(int lt, int rt) {
        if (this.mLtIco != null) {
            this.mLtIco.setImageResource(lt);
            this.mRtIco.setImageResource(rt);
        }
    }

    public void SetText(String txt) {
        this.mTitle.setText(txt);
    }

    public void SetText(String txt, int color) {
        this.mTitle.setText(txt);
        this.mTitle.setTextColor(color);
    }

    public void SetBg(int bg) {
        this.mView.setBackgroundResource(bg);
    }

    public View GetView() {
        return this.mView;
    }

    public void SetTextSize(int size) {
        this.mTitle.setTextSize(0, (float) size);
    }

    public void SetRtIconLeftMargin(int leftMargin) {
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) this.mRtIco.getLayoutParams();
        lp.leftMargin = leftMargin;
        this.mRtIco.setLayoutParams(lp);
    }
}
