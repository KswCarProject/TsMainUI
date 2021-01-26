package com.ts.main.tpms;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.ts.MainUI.R;
import com.ts.MainUI.StTpms;
import com.ts.MainUI.StTpmsCallBack;
import com.ts.main.tpms.TpmsPopup;
import com.txznet.sdk.TXZResourceManager;
import java.util.ArrayList;
import java.util.List;

public class TpmsStudyActivity extends Activity implements View.OnClickListener, TpmsPopup.Callback, StTpmsCallBack {
    public static final String KEY_TYPE = "key_type";
    public static final int TYPE_TPMS_STUDY = 1;
    public static final int TYPE_TPMS_SWITCH = 0;
    /* access modifiers changed from: private */
    public boolean isLearning;
    /* access modifiers changed from: private */
    public Button[] mBtnTpms = new Button[4];
    private int mId = -1;
    /* access modifiers changed from: private */
    public List<Integer> mIdList = new ArrayList();
    private int[] mIds = {R.id.btn_lf, R.id.btn_rf, R.id.btn_rr, R.id.btn_lr};
    private int mPos = -1;
    /* access modifiers changed from: private */
    public int mType;
    /* access modifiers changed from: private */
    public Runnable mUpdateTask = new Runnable() {
        public void run() {
            int index;
            if (!TpmsStudyActivity.this.isLearning) {
                int i = 0;
                while (true) {
                    if (i >= TpmsStudyActivity.this.mBtnTpms.length) {
                        break;
                    }
                    if (TpmsStudyActivity.this.mType == 1) {
                        index = TpmsStudyActivity.this.swPos(i);
                    } else {
                        index = i;
                    }
                    int id = StTpms.GetInstance().TpmsDisp.info[index].nID;
                    if (id != 0) {
                        if (TpmsStudyActivity.this.mIdList.contains(Integer.valueOf(id))) {
                            StTpms.GetInstance().GetID();
                            Log.d("TpmsStudy", "包含相同ID: 发送D1");
                            break;
                        }
                        TpmsStudyActivity.this.mIdList.add(Integer.valueOf(id));
                        String idText = Integer.toHexString(id).toUpperCase();
                        Log.d("TpmsStudy", "i = +" + i + " , idText = " + idText);
                        TpmsStudyActivity.this.mBtnTpms[i].setText(idText);
                    }
                    i++;
                }
                TpmsStudyActivity.this.mIdList.clear();
                TpmsStudyActivity.this.mBtnTpms[0].postDelayed(TpmsStudyActivity.this.mUpdateTask, 1000);
            }
        }
    };

    /* access modifiers changed from: protected */
    public void onResume() {
        StTpms.GetInstance().GetID();
        super.onResume();
    }

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tpms_study);
        this.mType = getIntent().getIntExtra(KEY_TYPE, 0);
        initViews();
        initData();
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        this.mBtnTpms[0].post(this.mUpdateTask);
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        this.mBtnTpms[0].removeCallbacks(this.mUpdateTask);
    }

    private void initViews() {
        if (this.mType == 1) {
            this.mIds = new int[]{R.id.btn_lf, R.id.btn_rf, R.id.btn_rr, R.id.btn_lr};
        } else {
            this.mIds = new int[]{R.id.btn_lf, R.id.btn_rf, R.id.btn_lr, R.id.btn_rr};
        }
        for (int i = 0; i < this.mIds.length; i++) {
            this.mBtnTpms[i] = (Button) findViewById(this.mIds[i]);
            this.mBtnTpms[i].setOnClickListener(this);
            this.mBtnTpms[i].setTextColor(-1);
            this.mBtnTpms[i].setText(TXZResourceManager.STYLE_DEFAULT);
            this.mBtnTpms[i].setTextSize(0, 40.0f);
            this.mBtnTpms[i].setBackground(getStateDrawable(R.drawable.tpms_rect_up, R.drawable.tpms_rect_dn));
        }
        Button btnStudy = (Button) findViewById(R.id.btn_study);
        btnStudy.setOnClickListener(this);
        btnStudy.setBackground(getStateDrawable(R.drawable.tpms_pdxx_up, R.drawable.tpms_pdxx_dn));
        if (this.mType == 1) {
            btnStudy.setVisibility(0);
        } else {
            btnStudy.setVisibility(8);
        }
    }

    private void initData() {
        TpmsPopup.getInstance().init(this, this);
        StTpms.GetInstance().SetCstTvCallBack(this);
    }

    private void cancelSelected() {
        for (Button btn : this.mBtnTpms) {
            btn.setSelected(false);
        }
    }

    private int getLastSelectedIndex() {
        for (int i = 0; i < this.mBtnTpms.length; i++) {
            if (this.mBtnTpms[i].isSelected()) {
                return i;
            }
        }
        return -1;
    }

    private int getIndexById(int id) {
        for (int i = 0; i < this.mIds.length; i++) {
            if (this.mIds[i] == id) {
                return i;
            }
        }
        return -1;
    }

    private boolean hasTwoSelected() {
        int count = 0;
        for (Button isSelected : this.mBtnTpms) {
            if (isSelected.isSelected()) {
                count++;
            }
        }
        if (count >= 2) {
            return true;
        }
        return false;
    }

    private byte[] getByteData(int lastIndex, int currentIndex) {
        byte[] data = {16, 32, 48, 64};
        for (int i = 0; i < data.length; i++) {
            if (i == lastIndex) {
                data[i] = (byte) (data[i] + currentIndex + 1);
            } else if (i == currentIndex) {
                data[i] = (byte) (data[i] + lastIndex + 1);
            } else {
                data[i] = (byte) (data[i] + i + 1);
            }
        }
        return data;
    }

    private boolean hasExistID(int id) {
        String idText = Integer.toHexString(id).toUpperCase();
        for (Button btn : this.mBtnTpms) {
            if (TextUtils.equals(idText, btn.getText().toString())) {
                return true;
            }
        }
        return false;
    }

    public void onClick(View v) {
        int id = v.getId();
        if (!TpmsPopup.getInstance().isShowing()) {
            if (id == R.id.btn_study) {
                tpmsStudy();
            } else if (this.mType == 1) {
                cancelSelected();
                v.setSelected(true);
            } else {
                tpmsSwitch(v);
            }
        }
    }

    private void tpmsSwitch(View v) {
        int lastIndex = getLastSelectedIndex();
        int currentIndex = getIndexById(v.getId());
        v.setSelected(!v.isSelected());
        if (hasTwoSelected()) {
            TpmsPopup.getInstance().switchTpmsID(this.mBtnTpms[lastIndex].getText(), this.mBtnTpms[currentIndex].getText(), new int[]{lastIndex, currentIndex}, getByteData(lastIndex, currentIndex));
            TpmsPopup.getInstance().show(false);
        }
    }

    private void tpmsStudy() {
        if (!this.isLearning) {
            for (Button btn : this.mBtnTpms) {
                btn.setText(TXZResourceManager.STYLE_DEFAULT);
            }
            TpmsPopup.getInstance().show(true);
            sendStudyCmd(0);
            this.isLearning = true;
        }
    }

    public void onOk(int[] index, byte[] data) {
        sendSwitchCmd(data);
        cancelSelected();
    }

    public void onCancel() {
        cancelSelected();
        this.isLearning = false;
    }

    public void onLearningSuccess(int pos) {
        this.mBtnTpms[pos].setSelected(false);
        int pos2 = pos + 1;
        if (pos2 >= 0 && pos2 <= 3) {
            TpmsPopup.getInstance().show(true);
            sendStudyCmd(pos2);
        }
    }

    public void ChangeState(int data0, int data1, int data2, int data3) {
        Log.d("TpmsStudy", "ChangeState: data0 = " + data0 + " -> data1 = " + data1 + " -> data2 = " + data2 + " -> data3 = " + data3);
        this.mBtnTpms[0].postDelayed(new Runnable() {
            public void run() {
                StTpms.GetInstance().GetID();
            }
        }, 500);
    }

    public void StudyState(final int position, int id) {
        int pos = swPos(position);
        Log.d("TpmsStudy", "pos = " + position + ", swPos = " + pos + ", id = " + id);
        if (this.mPos == pos && this.mId == id) {
            Log.d("TpmsStudy", "屏蔽一次B1");
            return;
        }
        this.mPos = pos;
        this.mId = id;
        if (hasExistID(id)) {
            Log.d("TpmsStudy", "已经存在相同ID");
            this.mBtnTpms[0].postDelayed(new Runnable() {
                public void run() {
                    Log.d("TpmsStudy", "已经存在相同ID : 重新发送StudyCmd position = " + position);
                    StTpms.GetInstance().Study(position);
                }
            }, 1000);
            return;
        }
        this.mBtnTpms[pos].setText(Integer.toHexString(id).toUpperCase());
        if (pos == 3) {
            TpmsPopup.getInstance().allLearningSuccess();
        } else {
            TpmsPopup.getInstance().singleLearningSuccess(pos);
        }
    }

    private void sendStudyCmd(int position) {
        cancelSelected();
        this.mBtnTpms[position].setSelected(true);
        int swPos = swPos(position);
        StTpms.GetInstance().Study(swPos);
        Log.d("TpmsStudy", "sendStudyCmd: pos = " + position + " , swPos = " + swPos);
    }

    private void sendSwitchCmd(byte[] data) {
        StTpms.GetInstance().Change(data[0], data[1], data[2], data[3]);
    }

    /* access modifiers changed from: private */
    public int swPos(int position) {
        if (position == 2) {
            return 3;
        }
        if (position == 3) {
            return 2;
        }
        return position;
    }

    @SuppressLint({"NewApi"})
    private Drawable getStateDrawable(int up, int dn) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{16842913}, getDrawable(dn));
        stateListDrawable.addState(new int[]{16842919}, getDrawable(dn));
        stateListDrawable.addState(new int[0], getDrawable(up));
        return stateListDrawable;
    }
}
