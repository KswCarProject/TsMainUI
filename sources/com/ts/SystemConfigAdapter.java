package com.ts;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.internal.view.SupportMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.ts.MainUI.R;
import com.txznet.sdk.tongting.IConstantData;
import java.util.ArrayList;
import java.util.List;

public class SystemConfigAdapter extends BaseAdapter {
    /* access modifiers changed from: private */
    public List<ConfigInfo> mConfigList = new ArrayList();
    /* access modifiers changed from: private */
    public Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            int what = msg.what;
            if (what >= 0 && what < SystemConfigAdapter.this.mConfigList.size()) {
                ConfigInfo info = (ConfigInfo) SystemConfigAdapter.this.mConfigList.get(what);
                if (msg.arg1 == -1) {
                    info.setConfigStatus(-2);
                } else if (msg.arg1 == 0) {
                    info.setConfigStatus(2);
                }
                SystemConfigAdapter.this.notifyDataSetChanged();
            }
            super.handleMessage(msg);
        }
    };
    private LayoutInflater mInflater;

    public Handler getHandler() {
        return this.mHandler;
    }

    public static class ConfigInfo {
        public static final int CONFIG_STATUS_FAILED = -2;
        public static final int CONFIG_STATUS_INITED = 0;
        public static final int CONFIG_STATUS_SETTING = 1;
        public static final int CONFIG_STATUS_SUCCESS = 2;
        public static final int CONFIG_TYPE_BOOTANIMATION = 1;
        public static final int CONFIG_TYPE_LOGO = 2;
        public static final int CONFIG_TYPE_WALLPAPER = 0;
        public int mConfigStatus;
        public int mConfigType;
        public String mPath;

        public ConfigInfo(String path) {
            if (path.toLowerCase().contains("wallpaper")) {
                this.mConfigType = 0;
            } else if (path.toLowerCase().contains("bootanimation")) {
                this.mConfigType = 1;
            } else if (path.toLowerCase().contains(IConstantData.KEY_LOGO)) {
                this.mConfigType = 2;
            }
            this.mConfigStatus = 0;
            this.mPath = path;
        }

        public int getConfigType() {
            return this.mConfigType;
        }

        public int getConfigStatus() {
            return this.mConfigStatus;
        }

        public void setConfigStatus(int status) {
            this.mConfigStatus = status;
        }

        public String getConfigPath() {
            return this.mPath;
        }
    }

    public class ViewHolder {
        public Button btnConfig;
        public ProgressBar pbProgress;
        public int position;
        public TextView tvConfigName;
        public TextView tvConfigStatus;

        public ViewHolder(ViewGroup rootview) {
            this.tvConfigName = (TextView) rootview.findViewById(R.id.config_name);
            this.tvConfigStatus = (TextView) rootview.findViewById(R.id.config_status);
            this.pbProgress = (ProgressBar) rootview.findViewById(R.id.config_progressbar);
            this.btnConfig = (Button) rootview.findViewById(R.id.config_btn);
            this.btnConfig.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    view.setEnabled(false);
                    ConfigUtils.configBtnClick(SystemConfigAdapter.this, SystemConfigAdapter.this.mHandler, ViewHolder.this.position);
                }
            });
        }
    }

    public SystemConfigAdapter(Context context, String[] updateFiles) {
        this.mConfigList.clear();
        for (String path : updateFiles) {
            if (path != null) {
                this.mConfigList.add(new ConfigInfo(path));
            }
        }
        this.mInflater = LayoutInflater.from(context);
    }

    public int getCount() {
        if (this.mConfigList == null) {
            return 0;
        }
        return this.mConfigList.size();
    }

    public Object getItem(int position) {
        if (this.mConfigList == null || this.mConfigList.size() <= position) {
            return null;
        }
        return this.mConfigList.get(position);
    }

    public long getItemId(int position) {
        return (long) position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            convertView = this.mInflater.inflate(R.layout.update_item, parent, false);
            vh = new ViewHolder((ViewGroup) convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        ConfigInfo info = this.mConfigList.get(position);
        vh.tvConfigName.setText(info.getConfigPath());
        vh.position = position;
        vh.tvConfigStatus.setTextColor(-1);
        if (info.getConfigStatus() == 1) {
            vh.btnConfig.setEnabled(false);
            vh.tvConfigStatus.setText("配置中");
            vh.pbProgress.setVisibility(0);
        } else if (info.getConfigStatus() == -2) {
            vh.btnConfig.setEnabled(true);
            vh.tvConfigStatus.setText("配置失败");
            vh.tvConfigStatus.setTextColor(SupportMenu.CATEGORY_MASK);
            vh.pbProgress.setVisibility(4);
        } else if (info.getConfigStatus() == 2) {
            vh.btnConfig.setEnabled(true);
            vh.tvConfigStatus.setText("配置成功");
            vh.tvConfigStatus.setTextColor(-16711936);
            vh.pbProgress.setVisibility(4);
        } else {
            vh.btnConfig.setEnabled(true);
            vh.tvConfigStatus.setText("等待配置");
            vh.pbProgress.setVisibility(4);
        }
        return convertView;
    }
}
