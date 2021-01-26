package com.ts.can;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.text.InputFilter;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.main.common.ShellUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class CanDataLogService extends Service implements View.OnClickListener {
    public static final String LANGUAGE_FILE_DIR = "/storage/emulated/0/TsCarInfo";
    public static final String LANGUAGE_POSITION_FILE = "/storage/emulated/0/TsCarInfo/CanData.txt";
    /* access modifiers changed from: private */
    public static CanDataAdapter canDataAdapter;
    /* access modifiers changed from: private */
    public static ArrayList<String> datalist = new ArrayList<>();
    /* access modifiers changed from: private */
    public static Button mBtnReceive;
    /* access modifiers changed from: private */
    public static Button mBtnSend;
    /* access modifiers changed from: private */
    public static EditText mEdtTxt1;
    /* access modifiers changed from: private */
    public static EditText mEdtTxtID;
    public static Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    Log.d("lq", "mHandler" + msg.obj);
                    if (CanDataLogService.mBtnReceive.isSelected()) {
                        addListData(msg, true);
                    } else if (CanDataLogService.mBtnSend.isSelected()) {
                        addListData(msg, false);
                    }
                    CanDataLogService.canDataAdapter.notifyDataSetChanged();
                    return;
                default:
                    return;
            }
        }

        private void addListData(Message msg, boolean isReceive) {
            if (!TextUtils.isEmpty(CanDataLogService.mEdtTxtID.getText().toString()) && !TextUtils.isEmpty(CanDataLogService.mEdtTxt1.getText().toString())) {
                int num = Integer.parseInt(CanDataLogService.mEdtTxt1.getText().toString()) - 1;
                if (((String) msg.obj).length() >= (num * 3) + 2 && num >= 0) {
                    if (!CanDataLogService.mEdtTxtID.getText().toString().endsWith(((String) msg.obj).substring((num * 3) + 0, (num * 3) + 2))) {
                        return;
                    }
                    if (isReceive) {
                        CanDataLogService.datalist.add(0, "RX:" + ((String) msg.obj));
                    } else {
                        CanDataLogService.datalist.add(0, "TX:" + ((String) msg.obj));
                    }
                }
            } else if (isReceive) {
                CanDataLogService.datalist.add(0, "RX:" + ((String) msg.obj));
            } else {
                CanDataLogService.datalist.add(0, "TX:" + ((String) msg.obj));
            }
        }
    };
    Boolean flag = true;
    private ListView lvCanData;
    private Button mBtnExit;
    private Button mBtnMin;
    private Button mBtnPause;
    private Button mBtnSave;
    private RelativeLayout mFloatLayout;
    int mScreenH;
    int mScreenW;
    /* access modifiers changed from: private */
    public TextView mTxtToast;
    int preId = 0;
    int tempX;
    int tempY;
    float touchX;
    float touchY;
    private Runnable txtRunnable = new Runnable() {
        public void run() {
            if (CanDataLogService.this.mTxtToast.getVisibility() == 0) {
                CanDataLogService.this.mTxtToast.setVisibility(8);
            }
        }
    };
    private WindowManager wManager;
    private WindowManager.LayoutParams wmParams = new WindowManager.LayoutParams();
    float x = 0.0f;
    float y = 0.0f;

    public void onCreate() {
        this.mFloatLayout = (RelativeLayout) LayoutInflater.from(getApplication()).inflate(R.layout.activity_can_data, (ViewGroup) null);
        this.mBtnExit = (Button) this.mFloatLayout.findViewById(R.id.data_exit);
        this.mBtnExit.setOnClickListener(this);
        mBtnReceive = (Button) this.mFloatLayout.findViewById(R.id.data_receive);
        mBtnReceive.setOnClickListener(this);
        mBtnSend = (Button) this.mFloatLayout.findViewById(R.id.data_send);
        mBtnSend.setOnClickListener(this);
        this.mBtnPause = (Button) this.mFloatLayout.findViewById(R.id.data_pause);
        this.mBtnPause.setOnClickListener(this);
        this.mBtnSave = (Button) this.mFloatLayout.findViewById(R.id.data_save);
        this.mBtnSave.setOnClickListener(this);
        this.mBtnMin = (Button) this.mFloatLayout.findViewById(R.id.data_min);
        this.mBtnMin.setOnClickListener(this);
        this.mTxtToast = (TextView) this.mFloatLayout.findViewById(R.id.data_toast);
        mEdtTxt1 = (EditText) this.mFloatLayout.findViewById(R.id.data_edit1);
        mEdtTxt1.setFilters(new InputFilter[]{new InputFilter.LengthFilter(1)});
        mEdtTxtID = (EditText) this.mFloatLayout.findViewById(R.id.data_editid);
        mEdtTxtID.setFilters(new InputFilter[]{new InputFilter.LengthFilter(2)});
        mEdtTxtID.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView view, int actionId, KeyEvent event) {
                if (actionId != 6 && actionId != 3 && event.getKeyCode() != 4) {
                    return true;
                }
                String s = view.getText().toString().toLowerCase();
                if (!TextUtils.isEmpty(s) && s.length() == 1) {
                    s = "0" + s;
                }
                view.setText(s);
                return false;
            }
        });
        this.lvCanData = (ListView) this.mFloatLayout.findViewById(R.id.data_lv);
        canDataAdapter = new CanDataAdapter(getApplicationContext(), datalist);
        this.lvCanData.setAdapter(canDataAdapter);
        this.wManager = (WindowManager) getApplicationContext().getSystemService("window");
        this.wmParams.type = 2003;
        this.wmParams.format = 1;
        this.wmParams.flags = 32;
        this.wmParams.gravity = 51;
        this.wmParams.width = -1;
        this.wmParams.height = -1;
        this.wManager.addView(this.mFloatLayout, this.wmParams);
        this.mScreenW = this.wManager.getDefaultDisplay().getWidth();
        this.mScreenH = this.wManager.getDefaultDisplay().getHeight();
    }

    public void onDestroy() {
        this.wManager.removeView(this.mFloatLayout);
        super.onDestroy();
    }

    public void onStart(Intent intent, int startId) {
        this.mFloatLayout.setVisibility(0);
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onRebind(Intent intent) {
    }

    public boolean onUnbind(Intent intent) {
        return true;
    }

    public void onClick(View v) {
        int id = v.getId();
        if (this.mBtnSave.isSelected()) {
            this.mBtnSave.setSelected(false);
        }
        setSelected(id);
        if (id == R.id.data_exit) {
            CanJni.SetDebugPrint(0, 0);
            if (datalist.size() != 0) {
                datalist.clear();
                canDataAdapter.notifyDataSetChanged();
            }
            stopSelf();
        } else if (id == R.id.data_receive) {
            if (!(id == this.preId || datalist.size() == 0)) {
                datalist.clear();
                canDataAdapter.notifyDataSetChanged();
            }
            this.preId = id;
            CanJni.SetDebugPrint(1, 0);
        } else if (id == R.id.data_send) {
            if (!(id == this.preId || datalist.size() == 0)) {
                datalist.clear();
                canDataAdapter.notifyDataSetChanged();
            }
            this.preId = id;
            CanJni.SetDebugPrint(0, 1);
        } else if (id == R.id.data_pause) {
            CanJni.SetDebugPrint(0, 0);
        } else if (id == R.id.data_save) {
            saveData();
            this.mTxtToast.setVisibility(0);
            mHandler.postDelayed(this.txtRunnable, 3000);
        } else if (id == R.id.data_min) {
            if (this.mFloatLayout.getParent() != null) {
                this.mFloatLayout.setVisibility(4);
            }
            CanDataLogSreviceUI.GetInstance().InitPM(this);
            CanDataLogSreviceUI.GetInstance().UserAll();
        }
    }

    private void setSelected(int id) {
        boolean z;
        boolean z2;
        boolean z3 = true;
        if (this.mBtnMin.getId() != id && this.mBtnSave.getId() != id) {
            Button button = mBtnReceive;
            if (mBtnReceive.getId() == id) {
                z = true;
            } else {
                z = false;
            }
            button.setSelected(z);
            Button button2 = mBtnSend;
            if (mBtnSend.getId() == id) {
                z2 = true;
            } else {
                z2 = false;
            }
            button2.setSelected(z2);
            Button button3 = this.mBtnPause;
            if (this.mBtnPause.getId() != id) {
                z3 = false;
            }
            button3.setSelected(z3);
        }
    }

    private void saveData() {
        File file = new File(LANGUAGE_FILE_DIR);
        File position_file = new File(LANGUAGE_POSITION_FILE);
        if (!file.exists()) {
            file.mkdirs();
        }
        if (position_file.exists()) {
            position_file.delete();
        }
        try {
            position_file.createNewFile();
            FileOutputStream fos = new FileOutputStream(position_file);
            for (int i = 0; i < datalist.size(); i++) {
                fos.write(datalist.get(i).getBytes());
                fos.write(ShellUtils.COMMAND_LINE_END.getBytes());
            }
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
