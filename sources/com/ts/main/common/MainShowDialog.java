package com.ts.main.common;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;
import com.ts.MainUI.R;
import com.ts.other.CustomDialog;

public class MainShowDialog extends CustomDialog {
    private int mHeight = 355;
    private ImageView mImageView;
    private TextView mTextView;
    private int mWidth = 300;

    public MainShowDialog() {
    }

    public MainShowDialog(Context context, String mcuid, String product) {
        createDlg(context, mcuid, product);
    }

    public void createDlg(Context context, String mcuid, String product) {
        super.create(R.layout.product_layout, context);
        this.mWindow.setBackgroundDrawableResource(17170445);
        this.mWindow.setLayout(this.mWidth, this.mHeight);
        this.mWindow.setGravity(17);
        this.mImageView = (ImageView) this.mWindow.findViewById(R.id.iv);
        this.mTextView = (TextView) this.mWindow.findViewById(R.id.txt);
        if (!product.isEmpty()) {
            this.mImageView.setImageBitmap(TwoDimension.GetInstance().createQRImage(product, this.mWidth, 300));
        }
        this.mTextView.setText(mcuid);
    }
}
