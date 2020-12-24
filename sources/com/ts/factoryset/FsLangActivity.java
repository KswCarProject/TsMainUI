package com.ts.factoryset;

import android.os.Bundle;
import android.view.View;
import com.lgb.canmodule.Can;
import com.ts.MainUI.R;
import com.ts.main.common.MainSet;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;
import com.yyw.ts70xhw.FtSet;

public class FsLangActivity extends FsBaseActivity {
    public static final String TAG = "FsLangActivity";
    private ParamButton[] mBtnLang;
    private RelativeLayoutManager mManager;
    private String[] mStrLang;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fs_lang);
        this.mStrLang = getResources().getStringArray(R.array.fs_languages);
        topBtnInit(R.string.str_fsmain_lang);
        this.mManager = new RelativeLayoutManager(this, R.id.fs_lang_layout);
        this.mBtnLang = new ParamButton[this.mStrLang.length];
        int numPerRow = getResources().getInteger(R.integer.fs_lang_num_per_row);
        for (int i = 0; i < this.mBtnLang.length; i++) {
            this.mBtnLang[i] = this.mManager.AddButton(((i % numPerRow) * Can.CAN_ZH_WC) + 50, ((i / numPerRow) * 100) + 86);
            this.mBtnLang[i].setTag(Integer.valueOf(i));
            this.mBtnLang[i].setOnClickListener(this);
            SetCommBtn(this.mBtnLang[i], this.mStrLang[i]);
        }
    }

    /* access modifiers changed from: protected */
    public void UpdateLang() {
        for (ParamButton selected : this.mBtnLang) {
            selected.setSelected(false);
        }
        int lang = FtSet.GetLangDef();
        if (lang < 0 || lang >= this.mBtnLang.length) {
            this.mBtnLang[0].setSelected(true);
        } else {
            this.mBtnLang[lang].setSelected(true);
        }
    }

    public void onClick(View v) {
        FtSet.SetLangDef(((Integer) v.getTag()).intValue());
        UpdateLang();
        MainSet.UpdateSysLanguage();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        UpdateLang();
    }

    /* access modifiers changed from: protected */
    public void SetCommBtn(ParamButton btn, String text) {
        btn.setDrawable(R.drawable.fs_rect_up, R.drawable.fs_rect_dn);
        if (text != null) {
            btn.setText(text);
        }
        btn.setPadding(0, 3, 0, 0);
        btn.setTextColor(-1);
        btn.setGravity(17);
        btn.setTextSize(0, 28.0f);
    }
}
