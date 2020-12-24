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

public class CanItemPopupCheckList implements View.OnClickListener {
    /* access modifiers changed from: private */
    public Button mBtn;
    private onPopCheckItemClick mCbClick = null;
    private Context mContext;
    private int mId;
    private int[] mSel;
    private String[] mStrValArr;
    private TextView mVal;
    private View mView;

    public interface onPopCheckItemClick {
        void onPositiveItem(int i, int[] iArr);
    }

    public CanItemPopupCheckList() {
    }

    public CanItemPopupCheckList(Context context, int text, int[] valarr, onPopCheckItemClick callBack) {
        Create(context, text, valarr, callBack);
    }

    public CanItemPopupCheckList(Context context, int text, String[] valarr, onPopCheckItemClick callBack) {
        Create(context, text, valarr, callBack);
    }

    public CanItemPopupCheckList(Context context, int text, int icon, String[] valarr, onPopCheckItemClick callBack) {
        Create(context, text, icon, valarr, callBack);
    }

    public void Create(Context context, int text, String[] valarr, onPopCheckItemClick callBack) {
        this.mStrValArr = valarr;
        Init(context, text, callBack);
    }

    public void Create(Context context, int text, int icon, String[] valarr, onPopCheckItemClick callBack) {
        this.mStrValArr = valarr;
        Init(context, text, icon, callBack);
    }

    public void Create(Context context, int text, int[] valarr, onPopCheckItemClick callBack) {
        Resources res = context.getResources();
        this.mStrValArr = new String[valarr.length];
        for (int i = 0; i < valarr.length; i++) {
            this.mStrValArr[i] = res.getString(valarr[i]);
        }
        Init(context, text, callBack);
    }

    private void Init(Context context, int text, onPopCheckItemClick callBack) {
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

    private void Init(Context context, int text, int icon, onPopCheckItemClick callBack) {
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

    public void SetSel(int[] sel) {
        this.mSel = sel;
        StringBuilder selStr = new StringBuilder("");
        for (int i = 0; i < this.mSel.length; i++) {
            if (sel[i] >= 0 && sel[i] < this.mStrValArr.length) {
                selStr.append(String.valueOf(this.mStrValArr[this.mSel[i]]) + " ");
            }
        }
        this.mVal.setText(selStr.toString());
    }

    public String GetItemStr(int item) {
        if (item < 0 || item >= this.mStrValArr.length) {
            return "";
        }
        return this.mStrValArr[item];
    }

    public int[] GetSel() {
        return this.mSel;
    }

    public void onClick(View arg0) {
        int[] location = new int[2];
        this.mView.getLocationOnScreen(location);
        this.mBtn.setSelected(true);
        new CanPopupCheck(this.mId, this.mContext, location[1], this.mStrValArr, this.mSel, this.mCbClick).getDlg().setOnDismissListener(new DialogInterface.OnDismissListener() {
            public void onDismiss(DialogInterface arg0) {
                CanItemPopupCheckList.this.mBtn.setSelected(false);
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
