package com.ts;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ListView;
import com.ts.MainUI.R;

public class FsConfigActivity extends Activity {
    /* access modifiers changed from: private */
    public SystemConfigAdapter mAdapter;
    private ListView mlvUpdateList;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle saveInstance) {
        super.onCreate(saveInstance);
        setupViews();
    }

    private void setupViews() {
        setContentView(R.layout.system_config_activity);
        String[] files = getIntent().getStringArrayExtra("files");
        if (files != null && files.length > 0) {
            this.mlvUpdateList = (ListView) findViewById(R.id.config_list);
            this.mlvUpdateList.setLayoutAnimation(getAnimationController());
            this.mAdapter = new SystemConfigAdapter(this, files);
            this.mlvUpdateList.setAdapter(this.mAdapter);
            this.mAdapter.notifyDataSetChanged();
            ((Button) findViewById(R.id.config_all)).setOnClickListener(new View.OnClickListener() {
                public void onClick(View arg0) {
                    int count = FsConfigActivity.this.mAdapter.getCount();
                    for (int i = 0; i < count; i++) {
                        ConfigUtils.configBtnClick(FsConfigActivity.this.mAdapter, FsConfigActivity.this.mAdapter.getHandler(), i);
                    }
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public LayoutAnimationController getAnimationController() {
        AnimationSet set = new AnimationSet(true);
        Animation animation = new AlphaAnimation(0.0f, 1.0f);
        animation.setDuration((long) 300);
        set.addAnimation(animation);
        Animation animation2 = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, -1.0f, 1, 0.0f);
        animation2.setDuration((long) 300);
        set.addAnimation(animation2);
        LayoutAnimationController controller = new LayoutAnimationController(set, 0.5f);
        controller.setOrder(0);
        return controller;
    }
}
