package com.txznet.comm.ui.dialog;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.txznet.comm.Tr.T;
import com.txznet.comm.Tr.Tr.T;
import com.txznet.comm.ui.IKeepClass;
import com.txznet.txz.comm.R;
import com.txznet.txz.util.Tk;

/* compiled from: Proguard */
public class CommonDialog extends WinDialog implements IKeepClass {
    /* access modifiers changed from: private */

    /* renamed from: T  reason: collision with root package name */
    public String[] f566T = new String[0];
    private TextView T9;
    private FrameLayout TB;
    private ImageView TF;
    /* access modifiers changed from: private */
    public View.OnClickListener TK;
    /* access modifiers changed from: private */
    public View.OnClickListener TO;
    /* access modifiers changed from: private */
    public TextView Te;
    private Resources Tj;
    private TextView Tn;
    /* access modifiers changed from: private */
    public TextView Tq;
    /* access modifiers changed from: private */
    public String[] Tr = new String[0];
    private ImageView Ty;

    public CommonDialog() {
        super(true);
    }

    /* access modifiers changed from: protected */
    public View T() {
        this.Tj = T.Tr().getResources();
        View view = getLayoutInflater().inflate(R.layout.dialog_common, (ViewGroup) null, false);
        this.Ty = (ImageView) view.findViewById(R.id.iv_icon);
        this.Tn = (TextView) view.findViewById(R.id.tv_title);
        this.T9 = (TextView) view.findViewById(R.id.tv_content);
        this.Te = (TextView) view.findViewById(R.id.tv_confirm);
        this.Tq = (TextView) view.findViewById(R.id.tv_cancel);
        this.TF = (ImageView) view.findViewById(R.id.iv_qrcode);
        this.TB = (FrameLayout) view.findViewById(R.id.fl_custom_content);
        return view;
    }

    private void T9() {
        com.txznet.comm.Tr.Tr.T.TZ(toString());
    }

    private void Tk() {
        int i = 0;
        com.txznet.comm.Tr.Tr.T.Ty();
        T.Tk mWakeupAsrCallback = null;
        int length = this.f566T == null ? 0 : this.f566T.length;
        if (this.Tr != null) {
            i = this.Tr.length;
        }
        if (length + i > 0) {
            mWakeupAsrCallback = new T.Tk() {
                public boolean needAsrState() {
                    return false;
                }

                public String getTaskId() {
                    return CommonDialog.this.toString();
                }

                public boolean onAsrResult(String text) {
                    String[] T2 = CommonDialog.this.f566T;
                    int length = T2.length;
                    int i = 0;
                    while (i < length) {
                        if (!TextUtils.equals(T2[i], text)) {
                            i++;
                        } else if (CommonDialog.this.TK == null) {
                            return true;
                        } else {
                            CommonDialog.this.TK.onClick(CommonDialog.this.Te);
                            return true;
                        }
                    }
                    String[] Tn = CommonDialog.this.Tr;
                    int length2 = Tn.length;
                    int i2 = 0;
                    while (i2 < length2) {
                        if (!TextUtils.equals(Tn[i2], text)) {
                            i2++;
                        } else if (CommonDialog.this.TO == null) {
                            return true;
                        } else {
                            CommonDialog.this.TO.onClick(CommonDialog.this.Tq);
                            return true;
                        }
                    }
                    return false;
                }

                public int getPriority() {
                    return 1;
                }

                public String[] genKeywords() {
                    return CommonDialog.this.T(CommonDialog.this.f566T, CommonDialog.this.Tr);
                }
            };
        }
        if (mWakeupAsrCallback != null) {
            com.txznet.comm.Tr.Tr.T.T(mWakeupAsrCallback);
        }
    }

    /* access modifiers changed from: private */
    public String[] T(String[] sureCmds, String[] cancelCmds) {
        int i = 0;
        int length = sureCmds == null ? 0 : sureCmds.length;
        if (cancelCmds != null) {
            i = cancelCmds.length;
        }
        String[] cmds = new String[(length + i)];
        int k = 0;
        for (String str : sureCmds) {
            cmds[k] = str;
            k++;
        }
        for (String str2 : cancelCmds) {
            cmds[k] = str2;
            k++;
        }
        return cmds;
    }

    public void show() {
        getWindow().setLayout((int) this.Tj.getDimension(R.dimen.x400), -2);
        super.show();
    }

    public void setCustomContentView(View view) {
        this.T9.setVisibility(8);
        this.TF.setVisibility(8);
        this.TB.addView(view);
    }

    public void setTitle(String title) {
        this.Tn.setVisibility(0);
        this.Tn.setText(title);
    }

    public void setTitleIcon(int resId) {
        this.Ty.setVisibility(0);
        this.Ty.setImageResource(resId);
    }

    public void setContent(String content) {
        this.T9.setVisibility(0);
        this.T9.setText(content);
    }

    public void setContentGravity(int gravity) {
        this.T9.setGravity(gravity);
    }

    public void setQrCode(String content, int width) {
        this.TF.setVisibility(0);
        Bitmap bm = null;
        try {
            bm = Tk.T(content, width);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.TF.setImageBitmap(bm);
    }

    public void setQrCode(String content) {
        setQrCode(content, (int) this.Tj.getDimension(R.dimen.y200));
    }

    public void setPositiveButton(String text, View.OnClickListener listener, String... cmds) {
        this.Te.setVisibility(0);
        this.Te.setText(text);
        this.Te.setOnClickListener(listener);
        this.TK = listener;
        if (cmds != null) {
            this.f566T = cmds;
        }
    }

    public void setNegativeButton(String text, View.OnClickListener listener, String... cmds) {
        this.Tq.setVisibility(0);
        this.Tq.setText(text);
        this.Tq.setOnClickListener(listener);
        this.TO = listener;
        if (cmds != null) {
            this.Tr = cmds;
        }
    }

    /* access modifiers changed from: protected */
    public void Tr() {
        Tk();
        super.Tr();
    }

    /* access modifiers changed from: protected */
    public void Ty() {
        T9();
        super.Ty();
    }
}
