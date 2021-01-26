package com.txznet.comm.ui.T5.Tr.T;

import android.annotation.SuppressLint;
import android.text.Html;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.txznet.comm.ui.T5.T.TM;
import com.txznet.comm.ui.T5.T.Tk;
import com.txznet.comm.ui.T5.Tn;
import com.txznet.comm.ui.T5.Tr.T9;
import com.txznet.comm.ui.TE.Tr;

@SuppressLint({"NewApi"})
/* compiled from: Proguard */
public class T extends T9 {
    private static T Tn = new T();
    private float T9;
    private int Tk;

    private T() {
    }

    public static T T9() {
        return Tn;
    }

    public Tn.T T(TM data) {
        Tk viewData = (Tk) data;
        LinearLayout mLayout = (LinearLayout) Tr.Tr("chat_from_sys_text");
        TextView content = (TextView) Tr.T("txtChat_Msg_Text", mLayout);
        content.setBackground(Tr.Ty("chat_bg_from_sys"));
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) content.getLayoutParams();
        params.topMargin = (int) Tr.Tn("y20");
        params.bottomMargin = (int) Tr.Tn("y20");
        content.setLayoutParams(params);
        content.setMinHeight((int) Tr.Tn("y70"));
        com.txznet.comm.Ty.Tk.T(content, this.T9);
        com.txznet.comm.Ty.Tk.T(content, this.Tk);
        content.setText(Html.fromHtml(viewData.f451T));
        if (viewData.Tr != null) {
            mLayout.setOnClickListener(viewData.Tr);
        }
        Tn.T adapter = new Tn.T();
        adapter.f466T = data.Ty();
        adapter.Tr = mLayout;
        adapter.Tn = T9();
        return adapter;
    }

    public void Tr() {
        this.T9 = ((Float) com.txznet.comm.ui.Tr.T.Tr().T("fromSys_size1.chat_size1.base_size2")).floatValue();
        this.Tk = ((Integer) com.txznet.comm.ui.Tr.T.Tr().T("fromSys_color1.chat_color2.base_color2")).intValue();
    }
}
