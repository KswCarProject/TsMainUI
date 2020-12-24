package com.ts.factoryset;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.ts.MainUI.R;
import com.ts.factoryset.FsInputDlg;
import com.ts.main.common.MainSet;
import com.ts.other.ParamButton;
import com.ts.other.ValCal;
import com.yyw.ts70xhw.FtSet;

public class FsRadioActivity extends FsBaseActivity {
    public static final String TAG = "FsRadioActivity";
    private FsRadioAdapter mAdapater;
    /* access modifiers changed from: private */
    public FsInputDlg mDlg;
    private ListView mListRad;

    public enum FsRadioSetType {
        FsRadioSet_ictype,
        FsRadioSet_zone,
        FsRadioSet_fmsense,
        FsRadioSet_amsense,
        FsRadioSet_OSC_6686,
        FsRadioSet_fmsort,
        FsRadioSet_otband,
        FsRadioSet_fmband,
        FsRadioSet_mwband,
        FsRadioSet_rdssw
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fs_radio);
        topBtnInit(R.string.str_fsmain_rad);
        this.mListRad = (ListView) findViewById(R.id.fs_rad_list);
        this.mAdapater = new FsRadioAdapter(this);
        this.mListRad.setAdapter(this.mAdapater);
        this.mDlg = new FsInputDlg();
    }

    public void onClick(View v) {
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }

    public class FsRadioAdapter extends BaseAdapter {
        /* access modifiers changed from: private */
        public int RDS_VALUE = 5;
        private int TOTAL_ITEM = (FsRadioSetType.values().length + 1);
        private View.OnClickListener adjClick = new View.OnClickListener() {
            public void onClick(View v) {
                ParamButton curBtn = (ParamButton) v;
                int id = curBtn.getIntParam();
                if (FsRadioSetType.FsRadioSet_ictype.ordinal() == curBtn.getIntParam2()) {
                    FsRadioAdapter.this.changeData(id, false);
                } else {
                    FsRadioAdapter.this.changeData(id, true);
                }
            }
        };
        private View.OnClickListener adjTextClick = new View.OnClickListener() {
            public void onClick(View v) {
                if (((Integer) v.getTag()).intValue() == FsRadioSetType.FsRadioSet_fmsense.ordinal()) {
                    FsRadioActivity.this.mDlg.createDlg(v.getContext(), FsRadioAdapter.this.onFmOK, true);
                } else {
                    FsRadioActivity.this.mDlg.createDlg(v.getContext(), FsRadioAdapter.this.onAmOK, true);
                }
            }
        };
        private ParamButton[] mBtnAMBand = new ParamButton[3];
        private ParamButton[] mBtnFmBand = new ParamButton[3];
        private ParamButton[] mBtnFmSort = new ParamButton[2];
        private ParamButton[] mBtnOTSw = new ParamButton[3];
        private ParamButton[] mBtnRds = new ParamButton[5];
        public LayoutInflater mLayoutInflater;
        private int[] mSWId = {R.id.fssw_sw0, R.id.fssw_sw1, R.id.fssw_sw2};
        private String[] mStrIc = {"ST7786", "NX6686", "Si4702", "QN8035", "474X", "7708", "7703"};
        private String[] mStrZone;
        private int[] mTitle = {R.string.str_fsrad_ictype, R.string.str_fsrad_zone, R.string.str_fsrad_fmsense, R.string.str_fsrad_amsense, R.string.str_fsrad_osc_6686, R.string.str_fsrad_fmsort, R.string.str_fsrad_otband, R.string.str_fsrad_fmband, R.string.str_fsrad_mwband, R.string.str_fsrad_rdssw};
        private TextView mTvAmSense;
        private TextView mTvFmSense;
        private TextView mTvOSC6686;
        private TextView[] mTvTitle = new TextView[this.TOTAL_ITEM];
        private TextView mTvType;
        private TextView mTvTypeText;
        private TextView mTvZone;
        private TextView mTvZoneText;
        private View[] mView = new View[this.TOTAL_ITEM];
        /* access modifiers changed from: private */
        public FsInputDlg.onInputOK onAmOK = new FsInputDlg.onInputOK() {
            public void onOK(String strVal) {
                int i;
                Log.e(FsRadioActivity.TAG, "Input val = " + strVal);
                if (!strVal.isEmpty() && (i = Integer.parseInt(strVal)) <= 255) {
                    FtSet.SetAMsd(i);
                    FsRadioAdapter.this.updateItem(FsRadioSetType.FsRadioSet_amsense.ordinal());
                }
            }
        };
        /* access modifiers changed from: private */
        public FsInputDlg.onInputOK onFmOK = new FsInputDlg.onInputOK() {
            public void onOK(String strVal) {
                int i;
                Log.e(FsRadioActivity.TAG, "Input val = " + strVal);
                if (!strVal.isEmpty() && (i = Integer.parseInt(strVal)) <= 255) {
                    FtSet.SetFMsd(i);
                    FsRadioAdapter.this.updateItem(FsRadioSetType.FsRadioSet_fmsense.ordinal());
                }
            }
        };
        private String[] str6686Freq = {"4M", "9.216M", "12M"};
        private View.OnClickListener swClick = new View.OnClickListener() {
            public void onClick(View v) {
                ParamButton curBtn = (ParamButton) v;
                int id = curBtn.getIntParam();
                int val = curBtn.getIntParam2();
                if (id == FsRadioSetType.FsRadioSet_fmband.ordinal()) {
                    FtSet.SetFMnum(val);
                } else if (id == FsRadioSetType.FsRadioSet_mwband.ordinal()) {
                    FtSet.SetMWnum(val);
                } else if (id == FsRadioSetType.FsRadioSet_otband.ordinal()) {
                    FtSet.SetOTnum(val);
                } else if (id == FsRadioSetType.FsRadioSet_rdssw.ordinal()) {
                    FtSet.SetRDSen(val);
                } else if (FsRadioAdapter.this.RDS_VALUE == 5 && id == FsRadioSetType.FsRadioSet_rdssw.ordinal() + 1) {
                    FtSet.SetRDSen(val + 3);
                } else if (id == FsRadioSetType.FsRadioSet_fmsort.ordinal()) {
                    FtSet.SetFmSort(val);
                }
                FsRadioAdapter.this.updateItem(id);
            }
        };

        public FsRadioAdapter(Context context) {
            this.mLayoutInflater = LayoutInflater.from(context);
            if (MainSet.GetInstance().Is3561()) {
                this.RDS_VALUE = 3;
                this.TOTAL_ITEM--;
            }
            try {
                this.mStrZone = context.getResources().getStringArray(R.array.str_fsrad_zonearray);
            } catch (Exception e) {
            }
        }

        /* access modifiers changed from: private */
        public void updateItem(int id) {
            if (id == FsRadioSetType.FsRadioSet_ictype.ordinal()) {
                int val = FtSet.GetRadioIc();
                this.mTvType.setText(new StringBuilder(String.valueOf(val)).toString());
                if (val < this.mStrIc.length) {
                    this.mTvTypeText.setText(this.mStrIc[val]);
                }
            } else if (id == FsRadioSetType.FsRadioSet_zone.ordinal()) {
                int val2 = FtSet.GetRadioArea();
                this.mTvZone.setText(new StringBuilder(String.valueOf(val2)).toString());
                if (this.mStrZone != null && val2 < this.mStrZone.length) {
                    this.mTvZoneText.setText(this.mStrZone[val2]);
                }
            } else if (id == FsRadioSetType.FsRadioSet_fmband.ordinal()) {
                for (int i = 0; i < 3; i++) {
                    this.mBtnFmBand[i].setSelected(false);
                }
                int val3 = FtSet.GetFMnum();
                if (val3 < 3) {
                    this.mBtnFmBand[val3].setSelected(true);
                }
            } else if (id == FsRadioSetType.FsRadioSet_mwband.ordinal()) {
                for (int i2 = 0; i2 < 3; i2++) {
                    this.mBtnAMBand[i2].setSelected(false);
                }
                int val4 = FtSet.GetMWnum();
                if (val4 < 3) {
                    this.mBtnAMBand[val4].setSelected(true);
                }
            } else if (id == FsRadioSetType.FsRadioSet_otband.ordinal()) {
                for (int i3 = 0; i3 < 2; i3++) {
                    this.mBtnOTSw[i3].setSelected(false);
                }
                this.mBtnOTSw[FtSet.GetOTnum() & 1].setSelected(true);
            } else if (id == FsRadioSetType.FsRadioSet_rdssw.ordinal()) {
                for (int i4 = 0; i4 < 5; i4++) {
                    if (this.mBtnRds[i4] != null) {
                        this.mBtnRds[i4].setSelected(false);
                    }
                }
                int rds = FtSet.GetRDSen();
                if (rds < 3) {
                    this.mBtnRds[rds].setSelected(true);
                }
            } else if (this.RDS_VALUE == 5 && id == FsRadioSetType.FsRadioSet_rdssw.ordinal() + 1) {
                for (int i5 = 0; i5 < 5; i5++) {
                    if (this.mBtnRds[i5] != null) {
                        this.mBtnRds[i5].setSelected(false);
                    }
                }
                int rds2 = FtSet.GetRDSen();
                if (rds2 < 5) {
                    this.mBtnRds[rds2].setSelected(true);
                }
            } else if (id == FsRadioSetType.FsRadioSet_fmsense.ordinal()) {
                this.mTvFmSense.setText(new StringBuilder(String.valueOf(FtSet.GetFMsd())).toString());
            } else if (id == FsRadioSetType.FsRadioSet_amsense.ordinal()) {
                this.mTvAmSense.setText(new StringBuilder(String.valueOf(FtSet.GetAMsd())).toString());
            } else if (id == FsRadioSetType.FsRadioSet_fmsort.ordinal()) {
                for (int i6 = 0; i6 < 2; i6++) {
                    this.mBtnFmSort[i6].setSelected(false);
                }
                this.mBtnFmSort[FtSet.GetFmSort() & 1].setSelected(true);
            } else if (id == FsRadioSetType.FsRadioSet_OSC_6686.ordinal()) {
                int val5 = FtSet.GetOsc6686();
                if (val5 < this.str6686Freq.length) {
                    this.mTvOSC6686.setText(this.str6686Freq[val5]);
                    return;
                }
                FtSet.SetOsc6686(0);
                this.mTvOSC6686.setText(this.str6686Freq[0]);
            }
        }

        /* access modifiers changed from: private */
        public void changeData(int id, boolean inc) {
            if (id == FsRadioSetType.FsRadioSet_ictype.ordinal()) {
                FtSet.SetRadioIc(ValCal.dataStepNoLoop(FtSet.GetRadioIc(), 0, 6, inc));
            } else if (id == FsRadioSetType.FsRadioSet_zone.ordinal()) {
                FtSet.SetRadioArea(ValCal.dataStepNoLoop(FtSet.GetRadioArea(), 0, 8, inc));
            } else if (id == FsRadioSetType.FsRadioSet_fmsense.ordinal()) {
                FtSet.SetFMsd(ValCal.dataStepNoLoop(FtSet.GetFMsd(), 0, 255, inc));
            } else if (id == FsRadioSetType.FsRadioSet_amsense.ordinal()) {
                FtSet.SetAMsd(ValCal.dataStepNoLoop(FtSet.GetAMsd(), 0, 255, inc));
            } else if (id == FsRadioSetType.FsRadioSet_OSC_6686.ordinal()) {
                FtSet.SetOsc6686(ValCal.dataStepNoLoop(FtSet.GetOsc6686(), 0, 2, inc));
            }
            updateItem(id);
        }

        private View initView(int position) {
            View view;
            Log.e("getAdjView", "index = " + position);
            if (position == FsRadioSetType.FsRadioSet_ictype.ordinal() || position == FsRadioSetType.FsRadioSet_zone.ordinal() || position == FsRadioSetType.FsRadioSet_fmsense.ordinal() || position == FsRadioSetType.FsRadioSet_amsense.ordinal() || position == FsRadioSetType.FsRadioSet_OSC_6686.ordinal()) {
                view = initAdj(position);
            } else if (position == FsRadioSetType.FsRadioSet_otband.ordinal() || position == FsRadioSetType.FsRadioSet_fmsort.ordinal()) {
                view = init2SW(position);
            } else if (this.RDS_VALUE == 5 && position == FsRadioSetType.FsRadioSet_rdssw.ordinal() + 1) {
                view = init2SW(position);
            } else {
                view = init3SW(position);
            }
            view.setLayoutParams(new AbsListView.LayoutParams(-1, 54));
            this.mView[position] = view;
            updateItem(position);
            return view;
        }

        /* access modifiers changed from: package-private */
        public View initAdj(int position) {
            View view = this.mLayoutInflater.inflate(R.layout.fs_item_data_adj, (ViewGroup) null);
            ((TextView) view.findViewById(R.id.fsadj_title)).setText(this.mTitle[position]);
            TextView val = (TextView) view.findViewById(R.id.fsadj_val);
            TextView text = (TextView) view.findViewById(R.id.fsadj_comment);
            if (position == FsRadioSetType.FsRadioSet_ictype.ordinal()) {
                this.mTvType = val;
                this.mTvTypeText = text;
            } else if (position == FsRadioSetType.FsRadioSet_zone.ordinal()) {
                this.mTvZone = val;
                this.mTvZoneText = text;
            } else if (position == FsRadioSetType.FsRadioSet_fmsense.ordinal()) {
                val.setTag(Integer.valueOf(position));
                val.setOnClickListener(this.adjTextClick);
                this.mTvFmSense = val;
            } else if (position == FsRadioSetType.FsRadioSet_amsense.ordinal()) {
                val.setTag(Integer.valueOf(position));
                val.setOnClickListener(this.adjTextClick);
                this.mTvAmSense = val;
            } else if (position == FsRadioSetType.FsRadioSet_OSC_6686.ordinal()) {
                this.mTvOSC6686 = val;
                this.mTvOSC6686.setTextSize(16.0f);
            }
            ParamButton btnCut = (ParamButton) view.findViewById(R.id.fsadj_cut);
            btnCut.setIntParam(position);
            btnCut.setIntParam2(0);
            btnCut.setStateDrawable(R.drawable.factory_reduce_up, R.drawable.factory_reduce_dn, R.drawable.factory_reduce_dn);
            btnCut.setOnClickListener(this.adjClick);
            ParamButton btnAdd = (ParamButton) view.findViewById(R.id.fsadj_add);
            btnAdd.setIntParam(position);
            btnAdd.setIntParam2(1);
            btnAdd.setStateDrawable(R.drawable.factory_add_up, R.drawable.factory_add_dn, R.drawable.factory_add_dn);
            btnAdd.setOnClickListener(this.adjClick);
            return view;
        }

        private View init2SW(int position) {
            View view = this.mLayoutInflater.inflate(R.layout.fs_item_3sw, (ViewGroup) null);
            ParamButton[] btnSW = new ParamButton[3];
            TextView tvTitle = (TextView) view.findViewById(R.id.fssw_title);
            if (this.RDS_VALUE == 5 && position == FsRadioSetType.FsRadioSet_rdssw.ordinal() + 1) {
                tvTitle.setText("");
            } else {
                tvTitle.setText(this.mTitle[position]);
            }
            for (int i = 0; i < 3; i++) {
                btnSW[i] = (ParamButton) view.findViewById(this.mSWId[i]);
                btnSW[i].setIntParam(position);
                btnSW[i].setIntParam2(i);
                btnSW[i].setOnClickListener(this.swClick);
            }
            btnSW[2].setVisibility(4);
            if (position == FsRadioSetType.FsRadioSet_otband.ordinal()) {
                btnSW[0].setText(R.string.str_fsrad_no);
                btnSW[1].setText("OT");
                for (int i2 = 0; i2 < 2; i2++) {
                    this.mBtnOTSw[i2] = btnSW[i2];
                }
            } else if (this.RDS_VALUE == 5 && position == FsRadioSetType.FsRadioSet_rdssw.ordinal() + 1) {
                btnSW[0].setText("AF ON ~");
                btnSW[1].setText("AF OFF ~");
                for (int i3 = 0; i3 < 2; i3++) {
                    this.mBtnRds[i3 + 3] = btnSW[i3];
                }
            } else if (position == FsRadioSetType.FsRadioSet_fmsort.ordinal()) {
                btnSW[0].setText(R.string.str_fsrad_sort0);
                btnSW[1].setText(R.string.str_fsrad_sort1);
                for (int i4 = 0; i4 < 2; i4++) {
                    this.mBtnFmSort[i4] = btnSW[i4];
                }
            }
            return view;
        }

        private View init3SW(int position) {
            View view = this.mLayoutInflater.inflate(R.layout.fs_item_3sw, (ViewGroup) null);
            ParamButton[] btnSW = new ParamButton[3];
            ((TextView) view.findViewById(R.id.fssw_title)).setText(this.mTitle[position]);
            for (int i = 0; i < 3; i++) {
                btnSW[i] = (ParamButton) view.findViewById(this.mSWId[i]);
                btnSW[i].setIntParam(position);
                btnSW[i].setIntParam2(i);
                btnSW[i].setOnClickListener(this.swClick);
                if (FsRadioSetType.FsRadioSet_fmband.ordinal() == position) {
                    this.mBtnFmBand[i] = btnSW[i];
                } else if (FsRadioSetType.FsRadioSet_rdssw.ordinal() == position) {
                    this.mBtnRds[i] = btnSW[i];
                } else {
                    this.mBtnAMBand[i] = btnSW[i];
                }
            }
            if (FsRadioSetType.FsRadioSet_fmband.ordinal() == position) {
                btnSW[0].setText("FM1");
                btnSW[1].setText("FM1/2");
                btnSW[2].setText("FM1/2/3");
            } else if (FsRadioSetType.FsRadioSet_rdssw.ordinal() == position) {
                btnSW[0].setText(R.string.str_fs_off);
                btnSW[1].setText("AF ON");
                btnSW[2].setText("AF OFF");
            } else {
                btnSW[0].setText(R.string.str_fsrad_no);
                btnSW[1].setText("MW1");
                btnSW[2].setText("MW1/2");
            }
            return view;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            if (this.mView[position] == null) {
                return initView(position);
            }
            return this.mView[position];
        }

        public final int getCount() {
            return this.TOTAL_ITEM;
        }

        public final Object getItem(int position) {
            return null;
        }

        public final long getItemId(int position) {
            return (long) position;
        }
    }
}
