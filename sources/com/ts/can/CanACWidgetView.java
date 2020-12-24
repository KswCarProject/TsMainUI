package com.ts.can;

import android.content.Context;
import android.widget.RelativeLayout;
import com.ts.can.toyota.corolla.CanCorollaACBottomView;

public class CanACWidgetView extends RelativeLayout {
    /* access modifiers changed from: private */
    public CanBaseACWidgetView mBaseView;
    /* access modifiers changed from: private */
    public OnUpdateListener mListener;
    private Runnable mUpdateTask = new Runnable() {
        public void run() {
            if (CanFunc.mCanInit == 1 && CanACWidgetView.this.mBaseView != null) {
                CanACWidgetView.this.mBaseView.updateAC();
                if (CanACWidgetView.this.mListener != null) {
                    CanACWidgetView.this.mListener.onUpdateFinish();
                }
            }
            if (CanACWidgetView.this.mBaseView != null) {
                CanACWidgetView.this.mBaseView.doOnRun();
            }
            CanACWidgetView.this.postDelayed(this, 200);
        }
    };

    public interface OnUpdateListener {
        void onOpenAC();

        void onUpdateFinish();
    }

    public CanACWidgetView(Context context, int type) {
        super(context);
        Init(type);
    }

    private void Init(int canType) {
        switch (canType) {
            case 3:
                this.mBaseView = new CanCorollaACBottomView(this);
                return;
            default:
                return;
        }
    }

    public void setOnUpdateListener(OnUpdateListener listener) {
        this.mListener = listener;
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.mBaseView != null) {
            this.mBaseView.doOnAttachedToWindow();
            this.mBaseView.InitUI();
            this.mBaseView.QueryData();
            postDelayed(this.mUpdateTask, 200);
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mBaseView != null) {
            this.mBaseView.doOnDetachedFromWindow();
        }
        removeCallbacks(this.mUpdateTask);
    }
}
