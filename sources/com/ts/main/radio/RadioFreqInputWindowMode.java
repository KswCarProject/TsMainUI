package com.ts.main.radio;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.widget.RelativeLayout;
import com.lgb.canmodule.Can;
import com.ts.MainUI.R;
import com.ts.main.common.MainSet;
import com.ts.other.CustomDialog;
import com.ts.other.CustomTextView;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;
import com.txznet.sdk.TXZResourceManager;
import com.yyw.ts70xhw.Radio;

public class RadioFreqInputWindowMode extends CustomDialog implements View.OnClickListener {
    private static final int ITEM_BK = 11;
    private static final int ITEM_CLOSE = 12;
    private static final int ITEM_DOT = 10;
    private static final int ITEM_NUM_BASE = 0;
    private static final int ITEM_OK = 13;
    private int[] mBtnNumDn = {R.drawable.radio_popup_num01_dn_s, R.drawable.radio_popup_num02_dn_s, R.drawable.radio_popup_num03_dn_s, R.drawable.radio_popup_num04_dn_s, R.drawable.radio_popup_num05_dn_s, R.drawable.radio_popup_num06_dn_s, R.drawable.radio_popup_num07_dn_s, R.drawable.radio_popup_num08_dn_s, R.drawable.radio_popup_num09_dn_s};
    private int[] mBtnNumUp = {R.drawable.radio_popup_num01_up_s, R.drawable.radio_popup_num02_up_s, R.drawable.radio_popup_num03_up_s, R.drawable.radio_popup_num04_up_s, R.drawable.radio_popup_num05_up_s, R.drawable.radio_popup_num06_up_s, R.drawable.radio_popup_num07_up_s, R.drawable.radio_popup_num08_up_s, R.drawable.radio_popup_num09_up_s};
    private Context mContext;
    private RelativeLayout mLayout;
    private RelativeLayoutManager mManager;
    protected String mStrFreq;
    private String[] mStrNum = {"0", "1", "2", "3", MainSet.SP_KS_QOROS, MainSet.SP_TW_CJW, MainSet.SP_XS_DZ, MainSet.SP_PCBA_VOL, "8", "9"};
    private CustomTextView mTvInput;

    public RadioFreqInputWindowMode(Context context) {
        createDlg(context);
    }

    public void createDlg(Context context) {
        this.mStrFreq = TXZResourceManager.STYLE_DEFAULT;
        this.mContext = context;
        super.create(R.layout.radio_input_dlg_windowmode, context);
        this.mLayout = (RelativeLayout) this.mWindow.findViewById(R.id.radio_input_layout);
        for (int i = 0; i < 9; i++) {
            AddButton(i + 0 + 1, ((i % 3) * 113) + 15, ((i / 3) * 65) + 81, this.mBtnNumUp[i], this.mBtnNumDn[i]);
        }
        AddButton(11, Can.CAN_MZD_TXB, 16, R.drawable.radio_popup_del_up_s, R.drawable.radio_popup_del_dn_s);
        AddButton(13, 357, 16, R.drawable.radio_popup_enter_up_s, R.drawable.radio_popup_enter_dn_s);
        AddButton(10, 357, 81, R.drawable.radio_popup_point_up_s, R.drawable.radio_popup_point_dn_s);
        AddButton(0, 357, 146, R.drawable.radio_popup_num00_up_s, R.drawable.radio_popup_num00_dn_s);
        AddButton(12, 357, 211, R.drawable.radio_popup_close_up_s, R.drawable.radio_popup_close_dn_s);
        this.mTvInput = AddText(31, 10, 173, 60);
        this.mTvInput.SetPixelSize(50);
        this.mTvInput.setPadding(0, 0, 0, 0);
        this.mTvInput.setGravity(17);
    }

    public CustomTextView AddText(int x, int y, int w, int h) {
        CustomTextView text = new CustomTextView(this.mContext);
        AddView(text, x, y, w, h);
        return text;
    }

    public void AddView(View view, int x, int y, int w, int h) {
        RelativeLayout.LayoutParams rl = new RelativeLayout.LayoutParams(w, h);
        rl.leftMargin = x;
        rl.topMargin = y;
        view.setLayoutParams(rl);
        this.mLayout.addView(view);
    }

    public void AddViewWrapContent(View view, int x, int y) {
        RelativeLayout.LayoutParams rl = new RelativeLayout.LayoutParams(-2, -2);
        rl.leftMargin = x;
        rl.topMargin = y;
        view.setLayoutParams(rl);
        this.mLayout.addView(view);
    }

    public ParamButton AddButton(int id, int x, int y, int up, int dn) {
        ParamButton btn = new ParamButton(this.mContext);
        btn.setTag(Integer.valueOf(id));
        btn.setDrawable(up, dn);
        AddViewWrapContent(btn, x, y);
        btn.setOnClickListener(this);
        if (id == 13) {
            btn.setTextSize(24.0f);
            btn.setText(17039370);
            btn.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        }
        return btn;
    }

    public void Input() {
        if (this.mStrFreq != null && this.mStrFreq.length() != 0) {
            int tLen = this.mStrFreq.length();
            int Freq = 0;
            int dot = 0;
            if (IsCurAmBand()) {
                int i = 0;
                while (true) {
                    if (i >= tLen) {
                        break;
                    } else if (this.mStrFreq.charAt(i) == '.') {
                        dot = 0 + 1;
                        break;
                    } else {
                        i++;
                    }
                }
                if (dot == 0) {
                    Freq = Integer.parseInt(this.mStrFreq);
                }
            } else {
                for (int i2 = 0; i2 < tLen; i2++) {
                    if (this.mStrFreq.charAt(i2) != '.') {
                        Freq = ((Freq * 10) + this.mStrFreq.charAt(i2)) - 48;
                    } else {
                        dot++;
                    }
                }
                if (dot == 0) {
                    if ('1' == this.mStrFreq.charAt(0)) {
                        if (3 == tLen) {
                            Freq *= 100;
                        }
                    } else if (2 == tLen) {
                        Freq *= 100;
                    }
                } else if (tLen > 2 && '.' == this.mStrFreq.charAt(tLen - 2)) {
                    Freq *= 10;
                }
            }
            Radio.TuneFsset(Freq);
        }
    }

    /* access modifiers changed from: protected */
    public boolean IsCurAmBand() {
        return Radio.GetDisp(2) >= 4;
    }

    /* access modifiers changed from: protected */
    public void AddNum(int id) {
        if (10 == id) {
            this.mStrFreq = String.valueOf(this.mStrFreq) + ".";
        } else {
            this.mStrFreq = String.valueOf(this.mStrFreq) + this.mStrNum[id + 0];
        }
        this.mTvInput.setText(this.mStrFreq);
    }

    /* access modifiers changed from: protected */
    public void decStr() {
        if (this.mStrFreq.length() <= 1) {
            this.mStrFreq = TXZResourceManager.STYLE_DEFAULT;
        } else {
            this.mStrFreq = this.mStrFreq.substring(0, this.mStrFreq.length() - 1);
        }
        this.mTvInput.setText(this.mStrFreq);
    }

    public void onClick(View v) {
        int id = ((Integer) v.getTag()).intValue();
        switch (id) {
            case 11:
                decStr();
                return;
            case 12:
                dismiss();
                return;
            case 13:
                Input();
                dismiss();
                return;
            default:
                int len = this.mStrFreq.length();
                if (this.mStrFreq.length() == 0) {
                    AddNum(id);
                    return;
                } else if (IsCurAmBand()) {
                    if (id == 10) {
                        return;
                    }
                    if (this.mStrFreq.charAt(0) == '1') {
                        if (len < 4) {
                            AddNum(id);
                            return;
                        }
                        return;
                    } else if (len < 3) {
                        AddNum(id);
                        return;
                    } else {
                        return;
                    }
                } else if (this.mStrFreq.charAt(0) == '1') {
                    if (len < 6) {
                        AddNum(id);
                        return;
                    }
                    return;
                } else if (len < 5) {
                    AddNum(id);
                    return;
                } else {
                    return;
                }
        }
    }
}
