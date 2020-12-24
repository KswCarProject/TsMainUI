package com.ts.canview;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import com.ts.MainUI.R;
import com.ts.canview.CanItemMsgBox;

public class CanItemFsSetList implements View.OnClickListener, CanItemMsgBox.onMsgBoxClick, CanItemMsgBox.onMsgBoxClick2 {
    protected Button mBtn;
    protected onFsSetClick mCb;
    protected ImageView mIco;
    protected int mId;
    protected int mMsgTitle;
    protected View mView;

    public interface onFsSetClick {
        void onFsItem(int i, boolean z);
    }

    public CanItemFsSetList(Context context) {
        Create(context, R.string.can_factory_set);
    }

    public CanItemFsSetList(Context context, int text) {
        Create(context, text);
    }

    public void Create(Context context, int text) {
        this.mMsgTitle = R.string.can_sure_setup;
        this.mView = LayoutInflater.from(context).inflate(R.layout.can_item_textbtn_list, (ViewGroup) null);
        this.mBtn = (Button) this.mView.findViewById(R.id.btn);
        this.mIco = (ImageView) this.mView.findViewById(R.id.arrow);
        if (text != 0) {
            this.mBtn.setText(text);
        }
        GetView().setOnClickListener(this);
    }

    public void ShowArrow(boolean show) {
        if (show) {
            this.mIco.setVisibility(0);
        } else {
            this.mIco.setVisibility(4);
        }
    }

    public View GetView() {
        return this.mView;
    }

    public void SetIdClickListener(int Id, onFsSetClick cb) {
        GetView().setTag(Integer.valueOf(Id));
        this.mId = Id;
        this.mCb = cb;
    }

    public void SetVal(int resId) {
        this.mBtn.setText(resId);
    }

    public void SetVal(String val) {
        this.mBtn.setText(val);
    }

    public void ShowGone(int show) {
        ShowGone(show != 0);
    }

    public void SetMsgText(int msg) {
        this.mMsgTitle = msg;
    }

    public void ShowGone(boolean show) {
        if (show) {
            this.mView.setVisibility(0);
        } else {
            this.mView.setVisibility(8);
        }
    }

    public void SetColor(int color) {
        this.mBtn.setTextColor(color);
    }

    public void onClick(View v) {
        GetView().setSelected(true);
        new AlertDialog.Builder(GetView().getContext()).setTitle(R.string.str_fs_tip).setMessage(this.mMsgTitle).setNegativeButton(R.string.can_cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if (CanItemFsSetList.this.mCb != null) {
                    CanItemFsSetList.this.mCb.onFsItem(CanItemFsSetList.this.mId, false);
                }
            }
        }).setPositiveButton(R.string.can_confirm, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if (CanItemFsSetList.this.mCb != null) {
                    CanItemFsSetList.this.mCb.onFsItem(CanItemFsSetList.this.mId, true);
                }
            }
        }).setOnDismissListener(new DialogInterface.OnDismissListener() {
            public void onDismiss(DialogInterface dialog) {
                CanItemFsSetList.this.GetView().setSelected(false);
            }
        }).show();
    }

    public void onCancel(int param) {
        if (this.mCb != null) {
            this.mCb.onFsItem(param, false);
        }
    }

    public void onOK(int param) {
        this.mCb.onFsItem(param, true);
    }
}
