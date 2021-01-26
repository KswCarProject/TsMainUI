package com.autochips.camera;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.ts.MainUI.R;
import com.txznet.sdk.TXZResourceManager;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MIPIDVRDisplayActivity extends Activity {
    private static final String DIR = "/storage/udisk2/AtcCamera";
    private Comparator<DisplayItem> mComparator = new Comparator<DisplayItem>() {
        public int compare(DisplayItem f1, DisplayItem f2) {
            return f2.getName().compareTo(f1.getName());
        }
    };
    /* access modifiers changed from: private */
    public DisplayAdapter mDisplayAdapter;
    private List<DisplayItem> mFileList = new ArrayList();
    private GridView mGridView;
    /* access modifiers changed from: private */
    public AlertDialog mInfoDialog;
    /* access modifiers changed from: private */
    public List<DisplayItem> mList;

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mipidvr_display);
        initViews();
        initData();
    }

    private void initViews() {
        this.mGridView = (GridView) findViewById(R.id.grid_view);
        this.mDisplayAdapter = new DisplayAdapter(this);
        this.mGridView.setAdapter(this.mDisplayAdapter);
        this.mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                if (MIPIDVRDisplayActivity.this.mInfoDialog == null || !MIPIDVRDisplayActivity.this.mInfoDialog.isShowing()) {
                    DisplayItem item = MIPIDVRDisplayActivity.this.mDisplayAdapter.getItem(position);
                    if (item.isDir()) {
                        if (MIPIDVRDisplayActivity.this.mDisplayAdapter.getLevel() == 0) {
                            MIPIDVRDisplayActivity.this.addDirs(new String[]{"Video", "Image"});
                            MIPIDVRDisplayActivity.this.mDisplayAdapter.updateList(MIPIDVRDisplayActivity.this.mList, 1);
                            MIPIDVRDisplayActivity.this.mDisplayAdapter.setFrontCamera("FrontCamera".equals(item.getName()));
                        } else if (MIPIDVRDisplayActivity.this.mDisplayAdapter.getLevel() == 1) {
                            MIPIDVRDisplayActivity.this.displayFileList(item);
                        }
                    } else if (new File(item.getPath()).exists()) {
                        MIPIDVRDisplayActivity.this.openFile(item.getName(), item.getPath());
                    } else {
                        Toast.makeText(MIPIDVRDisplayActivity.this, "文件已不存在", 0).show();
                    }
                }
            }
        });
        this.mGridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                DisplayItem item = MIPIDVRDisplayActivity.this.mDisplayAdapter.getItem(position);
                if (!item.isDir()) {
                    if (new File(item.getPath()).exists()) {
                        MIPIDVRDisplayActivity.this.showInfoDialog(item);
                    } else {
                        Toast.makeText(MIPIDVRDisplayActivity.this, "文件已不存在", 0).show();
                    }
                }
                return false;
            }
        });
    }

    /* access modifiers changed from: private */
    public void showInfoDialog(DisplayItem item) {
        String name = "Name:   " + item.getName() + "\n\n";
        String msg = String.valueOf(name) + ("Path:   " + item.getPath() + "\n\n");
        String msg2 = String.valueOf(msg) + ("Size:   " + getFileSize(item.getPath()));
        if (this.mInfoDialog == null) {
            this.mInfoDialog = new AlertDialog.Builder(this, 16974374).setTitle("Details").setCancelable(true).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            }).create();
        }
        this.mInfoDialog.setMessage(msg2);
        this.mInfoDialog.show();
    }

    private String getFileSize(String path) {
        File file = new File(path);
        if (!file.exists()) {
            return "0KB";
        }
        float size = ((float) file.length()) / 1024.0f;
        String unit = "KB";
        if (size > 1024.0f) {
            size /= 1024.0f;
            unit = "MB";
        }
        return String.format("%.2f %s", new Object[]{Float.valueOf(size), unit});
    }

    /* access modifiers changed from: private */
    public void displayFileList(DisplayItem item) {
        this.mList = new ArrayList();
        String str = String.valueOf(item.getName().toLowerCase()) + "_" + (this.mDisplayAdapter.isFrontCamera() ? "cam9" : "cam11");
        for (DisplayItem displayItem : this.mFileList) {
            if (displayItem.getPath().contains(str)) {
                this.mList.add(displayItem);
            }
        }
        this.mDisplayAdapter.updateList(this.mList, 2);
    }

    /* access modifiers changed from: private */
    public void openFile(String name, String path) {
        try {
            String mediaType = name.contains(".ts") ? "video/*" : "image/*";
            Intent i = new Intent("android.intent.action.VIEW");
            i.addCategory("android.intent.category.DEFAULT");
            i.setDataAndType(Uri.parse(path), mediaType);
            startActivity(i);
        } catch (Exception e) {
            Log.d("MIPIDVRDisplayActivity", "openFile: e = " + e.getMessage());
        }
    }

    private void initData() {
        addFile(new File(DIR));
        addDirs(new String[]{"FrontCamera", "RearCamera"});
        this.mDisplayAdapter.updateList(this.mList, 0);
    }

    /* access modifiers changed from: private */
    public void addDirs(String[] array) {
        this.mList = new ArrayList();
        for (String dir : array) {
            DisplayItem item = new DisplayItem(this, (DisplayItem) null);
            item.setDir(true);
            item.setName(dir);
            item.setPath(TXZResourceManager.STYLE_DEFAULT);
            this.mList.add(item);
        }
    }

    private void addFile(File file) {
        if (file != null && file.exists()) {
            if (file.isDirectory()) {
                for (File f : file.listFiles()) {
                    addFile(f);
                }
            } else {
                DisplayItem item = new DisplayItem(this, (DisplayItem) null);
                item.setDir(false);
                item.setName(file.getName());
                item.setPath(file.getPath());
                this.mFileList.add(item);
            }
        }
        Collections.sort(this.mFileList, this.mComparator);
    }

    public void onBackPressed() {
        if (this.mDisplayAdapter.getLevel() == 2) {
            addDirs(new String[]{"Video", "Image"});
            this.mDisplayAdapter.updateList(this.mList, 1);
        } else if (this.mDisplayAdapter.getLevel() == 1) {
            addDirs(new String[]{"FrontCamera", "RearCamera"});
            this.mDisplayAdapter.updateList(this.mList, 0);
        } else {
            super.onBackPressed();
        }
    }

    private class DisplayAdapter extends BaseAdapter {
        private boolean isFrontCamera;
        private Context mContext;
        private List<DisplayItem> mFileList = new ArrayList();
        private int mLevel;

        public DisplayAdapter(Context context) {
            this.mContext = context;
        }

        public void updateList(List<DisplayItem> list, int level) {
            clear();
            this.mFileList.addAll(list);
            notifyDataSetChanged();
            this.mLevel = level;
        }

        public boolean isFrontCamera() {
            return this.isFrontCamera;
        }

        public void setFrontCamera(boolean frontCamera) {
            this.isFrontCamera = frontCamera;
        }

        public int getLevel() {
            return this.mLevel;
        }

        public void clear() {
            this.mFileList.clear();
        }

        public int getCount() {
            return this.mFileList.size();
        }

        public DisplayItem getItem(int position) {
            return this.mFileList.get(position);
        }

        public long getItemId(int position) {
            return (long) position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = View.inflate(this.mContext, R.layout.display_item, (ViewGroup) null);
                holder = new ViewHolder();
                holder.mTvName = (TextView) convertView.findViewById(R.id.tv_name);
                holder.mIvPhoto = (ImageView) convertView.findViewById(R.id.iv_photo);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            DisplayItem item = getItem(position);
            holder.mTvName.setText(item.getName());
            if (item.isDir()) {
                holder.mIvPhoto.setImageResource(R.drawable.ic_list_file);
            } else if (item.getName().endsWith(".ts")) {
                holder.mIvPhoto.setImageResource(R.drawable.ic_list_video_default);
            } else {
                holder.mIvPhoto.setImageResource(R.drawable.ic_list_pic_default);
            }
            return convertView;
        }

        class ViewHolder {
            public ImageView mIvPhoto;
            public TextView mTvName;

            ViewHolder() {
            }
        }
    }

    private class DisplayItem {
        private boolean isDir;
        private String name;
        private String path;

        private DisplayItem() {
        }

        /* synthetic */ DisplayItem(MIPIDVRDisplayActivity mIPIDVRDisplayActivity, DisplayItem displayItem) {
            this();
        }

        public boolean isDir() {
            return this.isDir;
        }

        public void setDir(boolean dir) {
            this.isDir = dir;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name2) {
            this.name = name2;
        }

        public String getPath() {
            return this.path;
        }

        public void setPath(String path2) {
            this.path = path2;
        }
    }
}
