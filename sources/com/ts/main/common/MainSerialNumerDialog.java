package com.ts.main.common;

import android.content.Context;
import android.os.SystemProperties;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.ts.MainUI.R;
import com.ts.other.CustomDialog;
import com.yyw.ts70xhw.FtSet;

public class MainSerialNumerDialog extends CustomDialog {
    private Button mButton;
    /* access modifiers changed from: private */
    public EditText mEditTxtSerialNumber;
    private int mHeight = 209;
    private TextView mTxtModelNumber;
    private int mWidth = 536;

    public MainSerialNumerDialog() {
    }

    public MainSerialNumerDialog(Context context, String serialNumber, String modelName) {
        createDlg(context, serialNumber, modelName);
    }

    /* access modifiers changed from: package-private */
    public byte[] StrToByte32(String str) {
        byte[] naviname2 = new byte[32];
        byte[] naviname = str.getBytes();
        for (int i = 0; i < naviname.length; i++) {
            naviname2[i] = naviname[i];
        }
        return naviname2;
    }

    public void createDlg(Context context, String serialNumber, String modelName) {
        super.create(R.layout.serial_number_layout, context);
        this.mWindow.setBackgroundDrawableResource(17170445);
        this.mWindow.setLayout(this.mWidth, this.mHeight);
        this.mWindow.setGravity(17);
        this.mTxtModelNumber = (TextView) this.mWindow.findViewById(R.id.txt_model_number);
        this.mTxtModelNumber.setText(modelName);
        this.mEditTxtSerialNumber = (EditText) this.mWindow.findViewById(R.id.edittxt_input);
        this.mEditTxtSerialNumber.setText(serialNumber);
        this.mEditTxtSerialNumber.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View arg0, MotionEvent arg1) {
                MainSerialNumerDialog.this.mEditTxtSerialNumber.setText("");
                return false;
            }
        });
        this.mEditTxtSerialNumber.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                return false;
            }
        });
        this.mButton = (Button) this.mWindow.findViewById(R.id.btn_setup);
        this.mButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("lh", "onClick" + MainSerialNumerDialog.this.mEditTxtSerialNumber.getText());
                MainSet.GetInstance();
                if (!MainSet.IsRxfKoren() || MainSerialNumerDialog.this.mEditTxtSerialNumber.getText().toString().length() == 12) {
                    FtSet.SetProId(MainSerialNumerDialog.this.StrToByte32(MainSerialNumerDialog.this.mEditTxtSerialNumber.getText().toString()), 32);
                    MainSerialNumerDialog.this.dismiss();
                    SystemProperties.set("forfan.serial.number", MainSet.GetInstance().GetProid());
                    return;
                }
                MainSerialNumerDialog.this.mEditTxtSerialNumber.setText("please enter again");
            }
        });
    }
}
