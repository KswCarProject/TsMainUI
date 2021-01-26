package com.txznet.record.setting;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.txznet.T.T;
import com.txznet.comm.Tr.Tn;
import com.txznet.comm.Tr.Tr.Tn;
import com.txznet.comm.Tr.Tr.Tr;
import com.txznet.comm.base.BaseActivity;
import com.txznet.comm.ui.dialog2.WinConfirm;
import com.txznet.comm.ui.view.CheckedImageView;
import com.txznet.sdk.TXZConfigManager;
import com.txznet.sdk.TXZResourceManager;
import com.txznet.sdk.TXZTtsManager;
import com.txznet.txz.comm.R;
import java.util.ArrayList;
import org.json.JSONObject;

/* compiled from: Proguard */
public class MainActivity extends BaseActivity {

    /* renamed from: T  reason: collision with root package name */
    public static String f672T = (Environment.getExternalStorageDirectory() + "/txz/commandConfig.properties");
    private static int Tf = 0;
    private View.OnClickListener T0 = new View.OnClickListener() {
        public void onClick(View v) {
            MainActivity.this.T5();
        }
    };
    /* access modifiers changed from: private */
    public CheckedImageView T5;
    private RelativeLayout T6;
    final String[] T9 = {"极快", "快", "正常", "慢", "极慢"};
    private View.OnClickListener TA = new View.OnClickListener() {
        public void onClick(View v) {
            MainActivity.this.finish();
        }
    };
    private TextView TB;
    /* access modifiers changed from: private */
    public int TD = 2;
    Tr.T TE = new Tr.T() {
        public void onConfigChanged(final String data) {
            T.Ty(new Runnable() {
                public void run() {
                    try {
                        JSONObject config = new JSONObject(data);
                        Float wakeupThreshhold = Tr.Ty(config);
                        if (wakeupThreshhold != null) {
                            MainActivity.this.T(wakeupThreshhold.floatValue());
                            MainActivity.this.TG.setText(MainActivity.this.Ty[MainActivity.this.Tt]);
                        }
                        Integer speedVoice = Tr.Tn(config);
                        if (speedVoice != null) {
                            Integer unused = MainActivity.this.T(speedVoice);
                            MainActivity.this.Tu.setText(MainActivity.this.T9[MainActivity.this.TD]);
                        }
                        String[] wakeupKeywords = Tr.Tk(config);
                        if (!(wakeupKeywords == null || ChangeCommandActivity.f667T == null)) {
                            ArrayList<String> wakeupKeywordList = new ArrayList<>();
                            for (String keyword : wakeupKeywords) {
                                wakeupKeywordList.add(keyword);
                            }
                            ChangeCommandActivity.Tr = wakeupKeywordList;
                            ChangeCommandActivity.f667T.notifyDataSetChanged();
                        }
                        MainActivity.this.T5.setChecked(Tr.T9(config).booleanValue());
                        if (Tr.TZ() == null) {
                            return;
                        }
                        if (Tr.TZ().equals("FLOAT_NONE")) {
                            MainActivity.this.Tv.setChecked(false);
                        } else {
                            MainActivity.this.Tv.setChecked(true);
                        }
                    } catch (Exception e) {
                    }
                }
            }, 0);
        }
    };
    private RelativeLayout TF;
    /* access modifiers changed from: private */
    public TextView TG;
    private Button TK;
    private View.OnClickListener TM = new View.OnClickListener() {
        public void onClick(View v) {
            Tn.Tr(" wakeConfigListener");
            TXZConfigManager.getInstance().enableWakeup(!MainActivity.this.T5.isChecked());
        }
    };
    private Button TN;
    private Button TO;
    private View.OnClickListener TV = new View.OnClickListener() {
        public void onClick(View v) {
            MainActivity.this.TE();
        }
    };
    private View.OnClickListener TX = new View.OnClickListener() {
        public void onClick(View v) {
            Tn.Tr(" windowConfigListener");
            TXZConfigManager.getInstance().showFloatTool(MainActivity.this.Tv.isChecked() ? TXZConfigManager.FloatToolType.FLOAT_NONE : TXZConfigManager.FloatToolType.FLOAT_TOP);
        }
    };
    String TZ = TXZResourceManager.STYLE_DEFAULT;
    private View.OnClickListener Tb = new View.OnClickListener() {
        public void onClick(View v) {
            WinConfirm.T buildData = new WinConfirm.T();
            buildData.TZ("重置设置参数？");
            new WinConfirm(buildData) {
                public void onClickOk() {
                    com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "txz.config.restore", (byte[]) null, (Tn.Tr) null);
                }

                public String getReportDialogId() {
                    return "txz_config_restore";
                }
            }.show();
        }
    };
    private RelativeLayout Te;
    private RelativeLayout Th;
    private RelativeLayout Tj;
    String Tk = TXZResourceManager.STYLE_DEFAULT;
    final String[] Tn = {"极快（适合急性子，极容易漏听内容）", "快（适合急性子，容易漏听内容）", "正常（推荐）", "慢（适合慢性子，容易不耐烦）", "极慢（适合慢性子，极容易不耐烦）"};
    private RelativeLayout Tq;
    final String[] Tr = {"极高（适合嘈杂环境，极易被唤醒）", "高（适合噪音环境，容易被唤醒）", "正常（适合普通环境，推荐）", "低（适合安静环境，较难被唤醒）", "极低（适合安静环境，极难被唤醒）"};
    private Button Ts;
    /* access modifiers changed from: private */
    public int Tt = 2;
    /* access modifiers changed from: private */
    public TextView Tu;
    /* access modifiers changed from: private */
    public CheckedImageView Tv;
    private View.OnClickListener Tx = new View.OnClickListener() {
        public void onClick(View v) {
            Intent forwordToAddCommand = new Intent(MainActivity.this, ChangeCommandActivity.class);
            forwordToAddCommand.setFlags(270532608);
            MainActivity.this.startActivity(forwordToAddCommand);
        }
    };
    final String[] Ty = {"极高", "高", "正常", "低", "极低"};

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        Tk();
        TZ();
        Tr.T(this.TE);
    }

    private void Tk() {
        this.Th = (RelativeLayout) findViewById(R.id.layout_wakeSwitch);
        this.T6 = (RelativeLayout) findViewById(R.id.layout_windowSwitch);
        this.Te = (RelativeLayout) findViewById(R.id.layout_arsCommand);
        this.Tq = (RelativeLayout) findViewById(R.id.layout_recogdB);
        this.TF = (RelativeLayout) findViewById(R.id.layout_ttsdB);
        this.Tj = (RelativeLayout) findViewById(R.id.layout_reset);
        this.Th.setOnClickListener(this.TM);
        this.T6.setOnClickListener(this.TX);
        this.T5 = (CheckedImageView) findViewById(R.id.iv_wakeSwitch);
        this.Tv = (CheckedImageView) findViewById(R.id.iv_windowSwitch);
        this.TB = (TextView) findViewById(R.id.imgbnt_backToRecord);
        this.TB.setOnClickListener(this.TA);
        this.TK = (Button) findViewById(R.id.bntHint_command);
        this.TK.setOnClickListener(this.Tx);
        this.TO = (Button) findViewById(R.id.bntHint_recogdB);
        this.TO.setOnClickListener(this.T0);
        this.TN = (Button) findViewById(R.id.bntHint_TtsdB);
        this.TN.setOnClickListener(this.TV);
        this.TG = (TextView) findViewById(R.id.txt_recogValue);
        this.Tu = (TextView) findViewById(R.id.txt_ttsValue);
        this.Ts = (Button) findViewById(R.id.bntHint_reset);
        this.Ts.setOnClickListener(this.Tb);
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        if (this.TG.getText().toString().isEmpty() || this.Tu.getText().toString().isEmpty()) {
            TZ();
        }
        this.TG.setText(this.Tk);
        this.Tu.setText(this.TZ);
        super.onNewIntent(intent);
    }

    /* access modifiers changed from: private */
    public void T(float recogValue) {
        if (recogValue <= -3.4f) {
            this.Tt = 0;
        } else if (recogValue <= -3.2f) {
            this.Tt = 1;
        } else if (recogValue <= -3.0f) {
            this.Tt = 2;
        } else if (recogValue <= -2.8f) {
            this.Tt = 3;
        } else {
            this.Tt = 4;
        }
    }

    /* access modifiers changed from: private */
    public Integer T(Integer ttsValue) {
        if (ttsValue.intValue() <= 35) {
            this.TD = 4;
        } else if (ttsValue.intValue() <= 60) {
            this.TD = 3;
        } else if (ttsValue.intValue() <= 80) {
            this.TD = 2;
        } else if (ttsValue.intValue() <= 95) {
            this.TD = 1;
        } else {
            this.TD = 0;
        }
        return ttsValue;
    }

    private void TZ() {
        Float wakeupThreshold = Tr.Ty();
        if (wakeupThreshold != null) {
            T(wakeupThreshold.floatValue());
        }
        Integer voiceSpeed = Tr.T9();
        if (voiceSpeed != null) {
            T(Integer.valueOf(voiceSpeed.intValue()));
        }
        this.Tk = this.Ty[this.Tt];
        this.TZ = this.T9[this.TD];
        this.TG.setText(this.Tk);
        this.Tu.setText(this.TZ);
        if (Tr.Tk() != null) {
            this.T5.setChecked(Tr.Tk().booleanValue());
        }
        if (Tr.TZ() == null) {
            return;
        }
        if (Tr.TZ().equals("FLOAT_NONE")) {
            this.Tv.setChecked(false);
        } else {
            this.Tv.setChecked(true);
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        T(Tf);
        Tr.T(this.TE);
    }

    private void T(int hideOptions) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5 = 8;
        this.Th.setVisibility(hideOptions % 2 == 1 ? 8 : 0);
        RelativeLayout relativeLayout = this.T6;
        if ((hideOptions / 2) % 2 == 1) {
            i = 8;
        } else {
            i = 0;
        }
        relativeLayout.setVisibility(i);
        RelativeLayout relativeLayout2 = this.Te;
        if ((hideOptions / 4) % 2 == 1) {
            i2 = 8;
        } else {
            i2 = 0;
        }
        relativeLayout2.setVisibility(i2);
        RelativeLayout relativeLayout3 = this.Tq;
        if ((hideOptions / 8) % 2 == 1) {
            i3 = 8;
        } else {
            i3 = 0;
        }
        relativeLayout3.setVisibility(i3);
        RelativeLayout relativeLayout4 = this.TF;
        if ((hideOptions / 16) % 2 == 1) {
            i4 = 8;
        } else {
            i4 = 0;
        }
        relativeLayout4.setVisibility(i4);
        RelativeLayout relativeLayout5 = this.Tj;
        if ((hideOptions / 32) % 2 != 1) {
            i5 = 0;
        }
        relativeLayout5.setVisibility(i5);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        Tr.Tr(this.TE);
        super.onDestroy();
    }

    /* access modifiers changed from: private */
    public void TE() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, 4);
        builder.setTitle("语音播报速度");
        Window window = builder.create().getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.alpha = 0.9f;
        window.setAttributes(lp);
        builder.setSingleChoiceItems(this.Tn, this.TD, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                int unused = MainActivity.this.TD = which;
                MainActivity.this.Tu.setText(MainActivity.this.T9[MainActivity.this.TD]);
                int ttsValue = 0;
                switch (MainActivity.this.TD) {
                    case 0:
                        ttsValue = 100;
                        break;
                    case 1:
                        ttsValue = 90;
                        break;
                    case 2:
                        ttsValue = 70;
                        break;
                    case 3:
                        ttsValue = 50;
                        break;
                    case 4:
                        ttsValue = 20;
                        break;
                }
                TXZTtsManager.getInstance().setVoiceSpeed(ttsValue);
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    /* access modifiers changed from: private */
    public void T5() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, 4);
        builder.setTitle("语音唤醒灵敏度");
        Window window = builder.create().getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.alpha = 0.9f;
        lp.width = 800;
        lp.height = 500;
        window.setAttributes(lp);
        builder.setSingleChoiceItems(this.Tr, this.Tt, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                int unused = MainActivity.this.Tt = which;
                MainActivity.this.TG.setText(MainActivity.this.Ty[MainActivity.this.Tt]);
                float recogValue = 0.0f;
                switch (MainActivity.this.Tt) {
                    case 0:
                        recogValue = -3.5f;
                        break;
                    case 1:
                        recogValue = -3.3f;
                        break;
                    case 2:
                        recogValue = -3.1f;
                        break;
                    case 3:
                        recogValue = -2.9f;
                        break;
                    case 4:
                        recogValue = -2.7f;
                        break;
                }
                TXZConfigManager.getInstance().setWakeupThreshhold(recogValue);
                dialog.dismiss();
            }
        });
        builder.create().show();
    }
}
