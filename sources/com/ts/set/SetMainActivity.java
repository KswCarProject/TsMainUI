package com.ts.set;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ts.MainUI.R;
import com.ts.main.common.MainSet;
import com.ts.main.common.WinShow;
import com.ts.set.setview.UISetSroView;
import com.yyw.ts70xhw.FtSet;

public class SetMainActivity extends Activity implements View.OnClickListener {
    private int[] setIcon = {R.drawable.setup_main_common, R.drawable.setup_main_show, R.drawable.setup_main_volume, R.drawable.setup_main_eq, R.drawable.setup_main_video, R.drawable.setup_main_navi, R.drawable.setup_main_bt, R.drawable.setup_main_swc, R.drawable.setup_main_about};
    private String[] setOptions;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainSet.GetInstance().FtSetInint();
        UISetSroView.Inint(this);
        this.setOptions = getResources().getStringArray(R.array.set_main_options);
        initSetOptions();
        UISetSroView.Show(this);
    }

    private void initSetOptions() {
        for (int i = 0; i < 9; i++) {
            if (isHaveOption(i)) {
                addSetOption(i);
            }
        }
    }

    private void addSetOption(int setOpt) {
        ViewGroup layout = (ViewGroup) UISetSroView.inflater.inflate(R.layout.set_main_item, (ViewGroup) null);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(1280, 79);
        params.setMargins(0, 5, 0, 0);
        layout.setOnClickListener(this);
        layout.setTag(Integer.valueOf(setOpt));
        ((ImageView) layout.findViewById(R.id.set_icon)).setBackgroundResource(this.setIcon[setOpt]);
        ((TextView) layout.findViewById(R.id.set_text)).setText(this.setOptions[setOpt]);
        ((ImageView) layout.findViewById(R.id.set_arrow)).setBackgroundResource(R.drawable.setup_arrow);
        UISetSroView.m_llSetMain.addView(layout, params);
    }

    private boolean isHaveOption(int setOption) {
        if (setOption == 7 && FtSet.GetOptionSW() == 0) {
            return false;
        }
        return true;
    }

    public void onClick(View v) {
        WinShow.GotoWin(11, ((Integer) v.getTag()).intValue());
    }
}
