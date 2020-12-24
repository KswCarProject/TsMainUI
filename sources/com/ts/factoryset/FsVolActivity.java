package com.ts.factoryset;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import com.ts.MainUI.R;
import com.ts.other.ParamButton;
import com.ts.other.ValCal;
import com.yyw.ts70xhw.FtSet;

public class FsVolActivity extends FsBaseActivity {
    public static final String TAG = "FsVolActivity";
    private FsVolAdapter mAdapater;
    private GridView mGridView;
    public LayoutInflater mLayoutInflater;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fs_vol);
        topBtnInit(R.string.str_fsmain_vol);
        this.mLayoutInflater = LayoutInflater.from(this);
        this.mGridView = (GridView) findViewById(R.id.fs_vol_grid);
        this.mAdapater = new FsVolAdapter();
        this.mGridView.setAdapter(this.mAdapater);
    }

    public void onClick(View v) {
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }

    public class FsVolAdapter extends BaseAdapter {
        private static final int MODEMAX = 12;
        private static final int TOTAL_ITEM = 17;
        private View.OnClickListener btnClick = new View.OnClickListener() {
            public void onClick(View v) {
                ParamButton curBtn = (ParamButton) v;
                int id = curBtn.getIntParam();
                if (curBtn.getIntParam2() == 0) {
                    FsVolAdapter.this.changeData(id, false);
                } else {
                    FsVolAdapter.this.changeData(id, true);
                }
            }
        };
        private String[] mTitle;
        private TextView[] mTvVal = new TextView[17];
        private View[] mView = new View[17];
        private int[] mVol = new int[20];

        public FsVolAdapter() {
            FtSet.GetVolPset(this.mVol);
            this.mTitle = FsVolActivity.this.getResources().getStringArray(R.array.fs_vol_item_arr);
        }

        private void updateItem(boolean refresh, int id) {
            if (refresh) {
                FtSet.GetVolPset(this.mVol);
            }
            if (id < 12) {
                int val = this.mVol[id] - 20;
                if (val <= 0) {
                    this.mTvVal[id].setText(new StringBuilder().append(val).toString());
                } else {
                    this.mTvVal[id].setText("+" + val);
                }
            } else if (12 == id) {
                this.mTvVal[id].setText(new StringBuilder().append(this.mVol[18]).toString());
            } else if (13 == id) {
                this.mTvVal[id].setText(new StringBuilder().append(this.mVol[19]).toString());
            } else if (14 == id) {
                this.mTvVal[id].setText(new StringBuilder(String.valueOf(FtSet.GetVolSafe())).toString());
            } else if (15 == id) {
                this.mTvVal[id].setText(new StringBuilder(String.valueOf(FtSet.GetBtMicGain())).toString());
            } else if (16 == id) {
                int val2 = this.mVol[17] - 20;
                if (val2 <= 0) {
                    this.mTvVal[id].setText(new StringBuilder().append(val2).toString());
                } else {
                    this.mTvVal[id].setText("+" + val2);
                }
            }
        }

        /* access modifiers changed from: private */
        public void changeData(int id, boolean inc) {
            if (id < 12) {
                this.mVol[id] = ValCal.dataStepNoLoop(this.mVol[id], 0, 40, inc);
                FtSet.SetVolPset(this.mVol);
            } else if (12 == id) {
                this.mVol[18] = ValCal.dataStepNoLoop(this.mVol[18], 0, 100, inc);
                FtSet.SetVolPset(this.mVol);
            } else if (13 == id) {
                this.mVol[19] = ValCal.dataStepNoLoop(this.mVol[19], 0, 25, inc);
                FtSet.SetVolPset(this.mVol);
            } else if (14 == id) {
                FtSet.SetVolSafe(ValCal.dataStepNoLoop(FtSet.GetVolSafe(), 10, 30, inc));
            } else if (15 == id) {
                FtSet.SetBtMicGain(ValCal.dataStepNoLoop(FtSet.GetBtMicGain(), 0, 63, inc));
            } else if (16 == id) {
                this.mVol[17] = ValCal.dataStepNoLoop(this.mVol[17], 0, 40, inc);
                FtSet.SetVolPset(this.mVol);
            }
            updateItem(true, id);
        }

        /* access modifiers changed from: package-private */
        public int GettheBtMicGain() {
            int nGain = FtSet.GetBtMicGain() - 33;
            if (nGain < 0) {
                return 0;
            }
            if (nGain > 15) {
                return 15;
            }
            return nGain;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            if (this.mView[position] != null) {
                return this.mView[position];
            }
            Log.e("getView", "index = " + position);
            View view2 = FsVolActivity.this.mLayoutInflater.inflate(R.layout.fs_vol_item, (ViewGroup) null);
            this.mView[position] = view2;
            ((TextView) view2.findViewById(R.id.fsvol_title)).setText(this.mTitle[position]);
            this.mTvVal[position] = (TextView) view2.findViewById(R.id.fsvol_val);
            ParamButton btnCut = (ParamButton) view2.findViewById(R.id.fsvol_cut);
            btnCut.setIntParam(position);
            btnCut.setIntParam2(0);
            btnCut.setOnClickListener(this.btnClick);
            ParamButton btnAdd = (ParamButton) view2.findViewById(R.id.fsvol_add);
            btnAdd.setIntParam(position);
            btnAdd.setIntParam2(1);
            btnAdd.setOnClickListener(this.btnClick);
            updateItem(false, position);
            return view2;
        }

        public final int getCount() {
            return 17;
        }

        public final Object getItem(int position) {
            return null;
        }

        public final long getItemId(int position) {
            return (long) position;
        }
    }
}
