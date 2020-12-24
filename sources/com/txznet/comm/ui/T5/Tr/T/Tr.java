package com.txznet.comm.ui.T5.Tr.T;

import android.annotation.SuppressLint;
import android.text.Html;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.txznet.comm.ui.T5.T.TM;
import com.txznet.comm.ui.T5.T.TZ;
import com.txznet.comm.ui.T5.Tn;
import com.txznet.comm.ui.T5.Tr.Tk;
import com.txznet.comm.ui.Tr.T;

@SuppressLint({"NewApi"})
/* compiled from: Proguard */
public class Tr extends Tk {
    private static Tr Tn = new Tr();
    private float T9;
    private int Tk;

    private Tr() {
    }

    public static Tr T9() {
        return Tn;
    }

    public Tn.T T(TM data) {
        TZ viewData = (TZ) data;
        LinearLayout mLayout = (LinearLayout) com.txznet.comm.ui.TE.Tr.Tr("chat_from_sys_text_interrupt");
        TextView content = (TextView) com.txznet.comm.ui.TE.Tr.T("tv_chat_msg_interrupt", mLayout);
        TextView title = (TextView) com.txznet.comm.ui.TE.Tr.T("tv_chat_interrupt_tips", mLayout);
        content.setBackground(com.txznet.comm.ui.TE.Tr.Ty("chat_bg_from_sys"));
        content.setLayoutParams((LinearLayout.LayoutParams) content.getLayoutParams());
        content.setMinHeight((int) com.txznet.comm.ui.TE.Tr.Tn("y70"));
        com.txznet.comm.Ty.Tk.T(content, this.T9);
        com.txznet.comm.Ty.Tk.T(content, this.Tk);
        content.setText(viewData.f442T);
        title.setPadding(content.getPaddingLeft(), 0, 0, 0);
        com.txznet.comm.Ty.Tk.T(title, this.T9 - 2.0f);
        title.setText(Html.fromHtml(viewData.Tr));
        if (viewData.Ty != null) {
            mLayout.setOnClickListener(viewData.Ty);
        }
        Tn.T adapter = new Tn.T();
        adapter.f462T = data.Ty();
        adapter.Tr = mLayout;
        adapter.Tn = T9();
        return adapter;
    }

    public void Tr() {
        this.T9 = ((Float) T.Tr().T("fromSys_size1.chat_size1.base_size2")).floatValue();
        this.Tk = ((Integer) T.Tr().T("fromSys_color1.chat_color2.base_color2")).intValue();
    }
}
