package com.ts.set.setview;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.ts.MainUI.R;

public class SetItemPopupList implements View.OnClickListener {
    /* access modifiers changed from: private */
    public Button mBtn;
    private onPopItemClick mCbClick = null;
    private Context mContext;
    private int mId;
    private int mSel = -1;
    private String[] mStrValArr;
    private TextView mVal;
    private View mView;

    public interface onPopItemClick {
        void onItem(int i, int i2);
    }

    public SetItemPopupList() {
    }

    public SetItemPopupList(Context context, int text, int[] valarr, onPopItemClick callBack) {
        Create(context, text, valarr, callBack);
    }

    public SetItemPopupList(Context context, int text, String[] valarr, onPopItemClick callBack) {
        Create(context, text, valarr, callBack);
    }

    public void Create(Context context, int text, String[] valarr, onPopItemClick callBack) {
        this.mStrValArr = valarr;
        Init(context, text, callBack);
    }

    public void Create(Context context, int text, int[] valarr, onPopItemClick callBack) {
        Resources res = context.getResources();
        this.mStrValArr = new String[valarr.length];
        for (int i = 0; i < valarr.length; i++) {
            this.mStrValArr[i] = res.getString(valarr[i]);
        }
        Init(context, text, callBack);
    }

    private void Init(Context context, int text, onPopItemClick callBack) {
        this.mContext = context;
        this.mCbClick = callBack;
        this.mView = LayoutInflater.from(context).inflate(R.layout.set_item_popup_list, (ViewGroup) null);
        this.mBtn = (Button) this.mView.findViewById(R.id.btn);
        if (text != 0) {
            this.mBtn.setText(text);
        }
        this.mVal = (TextView) this.mView.findViewById(R.id.val);
        this.mView.setOnClickListener(this);
    }

    public void SetId(int id) {
        this.mId = id;
    }

    public View GetView() {
        return this.mView;
    }

    public Button GetBtn() {
        return this.mBtn;
    }

    public void SetVal(int resId) {
        this.mVal.setText(resId);
    }

    public void SetVal(String val) {
        this.mVal.setText(val);
    }

    public void SetSel(int sel) {
        this.mSel = sel;
        if (this.mSel >= 0 && this.mSel < this.mStrValArr.length) {
            this.mVal.setText(this.mStrValArr[this.mSel]);
        }
    }

    public void onClick(View arg0) {
        int[] location = new int[2];
        this.mView.getLocationOnScreen(location);
        this.mBtn.setSelected(true);
        new CanPopupMenu(this.mId, this.mContext, location[1], this.mStrValArr, this.mSel, this.mCbClick).getDlg().setOnDismissListener(new DialogInterface.OnDismissListener() {
            public void onDismiss(DialogInterface arg0) {
                SetItemPopupList.this.mBtn.setSelected(false);
            }
        });
    }

    public void ShowGone(int show) {
        ShowGone(show != 0);
    }

    public void ShowGone(boolean show) {
        if (show) {
            this.mView.setVisibility(0);
        } else {
            this.mView.setVisibility(8);
        }
    }
}
