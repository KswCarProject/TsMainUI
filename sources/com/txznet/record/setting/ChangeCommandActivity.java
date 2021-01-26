package com.txznet.record.setting;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.txznet.comm.Tr.Tr.Tr;
import com.txznet.comm.base.BaseActivity;
import com.txznet.sdk.TXZConfigManager;
import com.txznet.sdk.TXZResourceManager;
import com.txznet.txz.comm.R;
import java.util.ArrayList;

/* compiled from: Proguard */
public class ChangeCommandActivity extends BaseActivity {

    /* renamed from: T  reason: collision with root package name */
    static Tr f667T;
    static ArrayList<String> Tr = new ArrayList<>();
    private static boolean Ty = true;
    private View.OnClickListener T5 = new View.OnClickListener() {
        public void onClick(View v) {
            synchronized (v) {
                if (ChangeCommandActivity.Tr.size() >= 4) {
                    Toast.makeText(ChangeCommandActivity.this, "亲，唤醒词太多，体验效果更不好哦", 1).show();
                } else {
                    ChangeCommandActivity.T(TXZResourceManager.STYLE_DEFAULT, ChangeCommandActivity.this, "添加唤醒词", -1);
                }
            }
        }
    };
    private RelativeLayout T9;
    private View.OnClickListener TE = new View.OnClickListener() {
        public void onClick(View v) {
            ChangeCommandActivity.this.finish();
        }
    };
    private TextView TZ;
    private ImageButton Tk;
    private T Tn;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_asrcommand);
        Tk();
        TZ();
        f667T = new Tr(this);
        this.Tn.setAdapter(f667T);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        T(Ty);
        super.onResume();
    }

    private void T(boolean editable) {
        this.T9.setVisibility(editable ? 0 : 8);
        if (Tr.size() >= 4) {
            this.T9.setVisibility(8);
        }
        f667T.T(editable);
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        if (Tr.isEmpty()) {
            TZ();
        }
        if (f667T == null) {
            f667T = new Tr(this);
        }
        f667T.notifyDataSetChanged();
        super.onNewIntent(intent);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        f667T = null;
        super.onDestroy();
    }

    private void Tk() {
        this.Tn = (T) findViewById(R.id.lv_command);
        this.Tk = (ImageButton) findViewById(R.id.imgbnt_add);
        this.Tk.setOnClickListener(this.T5);
        this.T9 = (RelativeLayout) findViewById(R.id.layout_addcommand);
        this.T9.setOnClickListener(this.T5);
        this.TZ = (TextView) findViewById(R.id.imgbnt_backToSetting);
        this.TZ.setOnClickListener(this.TE);
    }

    private void TZ() {
        String[] keywords = Tr.Tn();
        if (keywords != null) {
            Tr.clear();
            for (int i = 0; i < keywords.length; i++) {
                if (i < 4) {
                    Tr.add(keywords[i]);
                }
            }
        }
    }

    public static void T(String command, Context context, String title, int position) {
        final Ty dialog = new Ty(context);
        dialog.setCanceledOnTouchOutside(false);
        final EditText editText = (EditText) dialog.T();
        editText.setText(command);
        ((TextView) dialog.Tr()).setText(title);
        final Context context2 = context;
        final String str = command;
        final int i = position;
        dialog.T((View.OnClickListener) new View.OnClickListener() {
            public void onClick(View v) {
                if (editText == null || editText.getText().toString().trim().isEmpty()) {
                    synchronized (v) {
                        Toast.makeText(context2, "唤醒词不能为空", 1).show();
                    }
                } else if (editText == null || editText.getText().toString().trim().length() >= 4) {
                    if (ChangeCommandActivity.Tr == null) {
                        ChangeCommandActivity.Tr = new ArrayList<>();
                    }
                    if (!str.isEmpty() || i != -1) {
                        if (!str.isEmpty() && i != -1) {
                            ChangeCommandActivity.Tr.set(i, editText.getText().toString().trim());
                            ChangeCommandActivity.f667T.notifyDataSetChanged();
                        }
                    } else if (ChangeCommandActivity.Tr.contains(editText.getText().toString().trim())) {
                        synchronized (v) {
                            Toast.makeText(context2, "您添加的唤醒词已存在", 1).show();
                        }
                    } else {
                        ChangeCommandActivity.Tr.add(editText.getText().toString().trim());
                        ChangeCommandActivity.f667T.notifyDataSetChanged();
                    }
                    TXZConfigManager.getInstance().setWakeupKeywordsNew((String[]) ChangeCommandActivity.Tr.toArray(new String[ChangeCommandActivity.Tr.size()]));
                    dialog.dismiss();
                } else {
                    synchronized (v) {
                        Toast.makeText(context2, "亲，唤醒词字数太少，识别效果不好哦", 1).show();
                    }
                }
            }
        });
        dialog.Tr(new View.OnClickListener() {
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
