package com.txznet.comm.ui.T5.Tr.T;

import android.annotation.SuppressLint;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.txznet.comm.Ty.Tk;
import com.txznet.comm.ui.T5.T.T5;
import com.txznet.comm.ui.T5.T.TM;
import com.txznet.comm.ui.T5.Tn;
import com.txznet.comm.ui.T5.Tr.TZ;
import com.txznet.comm.ui.TE.Tr;
import com.txznet.comm.ui.Tr.T;

@SuppressLint({"NewApi"})
/* compiled from: Proguard */
public class Ty extends TZ {
    private static Ty T9 = new Ty();
    private int TZ;
    private float Tk;
    int Tn = 0;

    private Ty() {
    }

    public static Ty T9() {
        return T9;
    }

    public Tn.T T(TM data) {
        LinearLayout mLayout = (LinearLayout) Tr.Tr("chat_to_sys_text");
        TextView content = (TextView) Tr.T("txtChat_Msg_Text", mLayout);
        content.setBackground(Tr.Ty("chat_bg_to_sys"));
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) content.getLayoutParams();
        params.topMargin = (int) Tr.Tn("y20");
        params.bottomMargin = (int) Tr.Tn("y20");
        content.setLayoutParams(params);
        content.setMinHeight((int) Tr.Tn("y70"));
        content.setText(com.txznet.txz.util.Tr.T(((T5) data).f426T));
        Tk.T(content, this.Tk);
        Tk.T(content, this.TZ);
        Tn.T adapter = new Tn.T();
        adapter.f462T = data.Ty();
        adapter.Tr = mLayout;
        adapter.Tn = T9();
        return adapter;
    }

    public void Tr() {
        super.Tr();
        this.Tk = ((Float) T.Tr().T("toSys_part_size1.chat_size2.base_size2")).floatValue();
        this.TZ = ((Integer) T.Tr().T("toSys_part_color1.chat_color3.base_color3")).intValue();
    }
}
