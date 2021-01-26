package com.ts.canview;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.ts.MainUI.R;
import com.txznet.sdk.TXZResourceManager;

public class CanItemPopupList implements View.OnClickListener {
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

    public CanItemPopupList() {
    }

    public CanItemPopupList(Context context, int text, int[] valarr, onPopItemClick callBack) {
        Create(context, text, valarr, callBack);
    }

    public CanItemPopupList(Context context, int text, String[] valarr, onPopItemClick callBack) {
        Create(context, text, valarr, callBack);
    }

    public CanItemPopupList(Context context, int text, int icon, String[] valarr, onPopItemClick callBack) {
        Create(context, text, icon, valarr, callBack);
    }

    public void Create(Context context, int text, String[] valarr, onPopItemClick callBack) {
        this.mStrValArr = valarr;
        Init(context, text, callBack);
    }

    public void Create(Context context, int text, int icon, String[] valarr, onPopItemClick callBack) {
        this.mStrValArr = valarr;
        Init(context, text, icon, callBack);
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
        this.mView = LayoutInflater.from(context).inflate(R.layout.can_item_popup_list, (ViewGroup) null);
        this.mBtn = (Button) this.mView.findViewById(R.id.btn);
        if (text != 0) {
            this.mBtn.setText(text);
        }
        this.mVal = (TextView) this.mView.findViewById(R.id.val);
        this.mView.setOnClickListener(this);
    }

    private void Init(Context context, int text, int icon, onPopItemClick callBack) {
        this.mContext = context;
        this.mCbClick = callBack;
        this.mView = LayoutInflater.from(context).inflate(R.layout.can_item_popup_icon_list, (ViewGroup) null);
        this.mBtn = (Button) this.mView.findViewById(R.id.btn);
        if (text != 0) {
            this.mBtn.setText(text);
        }
        ((ImageView) this.mView.findViewById(R.id.ico)).setImageResource(icon);
        this.mVal = (TextView) this.mView.findViewById(R.id.val);
        this.mView.setOnClickListener(this);
    }

    public void SetId(int id) {
        this.mId = id;
    }

    public int GetId() {
        return this.mId;
    }

    public View GetView() {
        return this.mView;
    }

    public Button GetBtn() {
        return this.mBtn;
    }

    public Context GetContext() {
        return this.mContext;
    }

    public void SetVal(int resId) {
        this.mVal.setText(resId);
    }

    public void SetVal(String val) {
        this.mVal.setText(val);
    }

    public void SetSel(int sel) {
        this.mSel = sel;
        if (sel < 0 || sel >= this.mStrValArr.length) {
            this.mVal.setText(TXZResourceManager.STYLE_DEFAULT);
        } else {
            this.mVal.setText(this.mStrValArr[this.mSel]);
        }
    }

    public String GetItemStr(int item) {
        if (item < 0 || item >= this.mStrValArr.length) {
            return TXZResourceManager.STYLE_DEFAULT;
        }
        return this.mStrValArr[item];
    }

    public int GetSel() {
        return this.mSel;
    }

    public void onClick(View arg0) {
        int[] location = new int[2];
        this.mView.getLocationOnScreen(location);
        this.mBtn.setSelected(true);
        new CanPopupMenu(this.mId, this.mContext, location[1], this.mStrValArr, this.mSel, this.mCbClick).getDlg().setOnDismissListener(new DialogInterface.OnDismissListener() {
            public void onDismiss(DialogInterface arg0) {
                CanItemPopupList.this.mBtn.setSelected(false);
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
