package com.qiner.appinfo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import com.ts.MainUI.R;
import java.util.List;

public class AppListActivity extends Activity {
    AppListAdapter mAppListAdapter;
    ListView mAppListView;
    private CheckBox mBtnSelectAll;
    private List<AppInfo> mlistAppInfo;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applist);
        AppListUtil.getInstance().init(this);
        this.mBtnSelectAll = (CheckBox) findViewById(R.id.btn_selectall);
        this.mBtnSelectAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    AppListUtil.getInstance().selectAllAppList(1);
                } else {
                    AppListUtil.getInstance().selectAllAppList(0);
                }
                AppListActivity.this.mAppListAdapter.notifyDataSetChanged();
            }
        });
        this.mAppListView = (ListView) findViewById(R.id.list);
        this.mlistAppInfo = AppListUtil.getInstance().queryFilterAppInfo();
        this.mAppListAdapter = new AppListAdapter(this, this.mlistAppInfo);
        this.mAppListView.setAdapter(this.mAppListAdapter);
    }
}
