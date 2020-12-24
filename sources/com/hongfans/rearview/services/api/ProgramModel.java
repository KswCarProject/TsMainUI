package com.hongfans.rearview.services.api;

import android.os.Parcel;
import android.os.Parcelable;
import com.hongfans.carmedia.StringUtils;
import com.ts.can.bmw.mini.CanBMWMiniServiceDetailActivity;
import com.txznet.sdk.tongting.IConstantData;
import org.json.JSONException;
import org.json.JSONObject;

public class ProgramModel implements Parcelable {
    public static final Parcelable.Creator<ProgramModel> CREATOR = new Parcelable.Creator<ProgramModel>() {
        public ProgramModel createFromParcel(Parcel in) {
            return new ProgramModel(in);
        }

        public ProgramModel[] newArray(int size) {
            return new ProgramModel[size];
        }
    };
    private int _id;
    private String description;
    private int id;
    private int isnews;
    private boolean issub;
    private String latestprogram;
    private int manipulate;
    public int modelPm;
    private int num;
    private int qtid;
    private int qtpid;
    private String smallpic;
    private int star;
    private String thumbs;
    private String title;
    private int type;

    public ProgramModel() {
    }

    public ProgramModel(Parcel pl) {
        this._id = pl.readInt();
        this.id = pl.readInt();
        this.qtid = pl.readInt();
        this.qtpid = pl.readInt();
        this.title = pl.readString();
        this.thumbs = pl.readString();
        this.star = pl.readInt();
        this.latestprogram = pl.readString();
        this.description = pl.readString();
        this.manipulate = pl.readInt();
        this.isnews = pl.readInt();
        this.issub = pl.readByte() != 0;
        this.num = pl.readInt();
        this.smallpic = pl.readString();
        this.type = pl.readInt();
        this.modelPm = pl.readInt();
    }

    public static ProgramModel parse(JSONObject json) throws JSONException {
        String[] temp;
        boolean z = false;
        ProgramModel pm = new ProgramModel();
        if (json.has(IConstantData.KEY_ID)) {
            pm.id = StringUtils.toInt(json.get(IConstantData.KEY_ID).toString());
        }
        if (json.has("qtid")) {
            pm.qtid = StringUtils.toInt(json.get("qtid").toString());
        }
        if (json.has("qtpid")) {
            pm.qtpid = StringUtils.toInt(json.get("qtpid").toString());
        }
        if (json.has(IConstantData.KEY_TITLE)) {
            pm.title = json.get(IConstantData.KEY_TITLE).toString();
        }
        if (json.has("thumbs")) {
            pm.thumbs = json.get("thumbs").toString();
            if (pm.thumbs.length() > 0 && (temp = pm.thumbs.split("\\|")) != null) {
                pm.smallpic = temp[0].toString();
            }
        }
        if (json.has("star")) {
            pm.star = StringUtils.toInt(json.get("star").toString());
        }
        if (json.has("latestprogram")) {
            pm.latestprogram = json.get("latestprogram").toString();
        }
        if (json.has("description")) {
            pm.description = json.get("description").toString();
        }
        if (json.has("manipulate")) {
            pm.manipulate = StringUtils.toInt(json.get("manipulate").toString());
        }
        if (json.has("isnews")) {
            pm.isnews = StringUtils.toInt(json.get("isnews").toString());
        }
        if (json.has(CanBMWMiniServiceDetailActivity.KEY_NUM)) {
            pm.num = StringUtils.toInt(json.get(CanBMWMiniServiceDetailActivity.KEY_NUM).toString());
        }
        if (json.has("issub")) {
            if (json.getInt("issub") != 0) {
                z = true;
            }
            pm.issub = z;
        }
        return pm;
    }

    public int get_id() {
        return this._id;
    }

    public void set_id(int _id2) {
        this._id = _id2;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id2) {
        this.id = id2;
    }

    public int getQtid() {
        return this.qtid;
    }

    public void setQtid(int qtid2) {
        this.qtid = qtid2;
    }

    public int getQtpid() {
        return this.qtpid;
    }

    public void setQtpid(int qtpid2) {
        this.qtpid = qtpid2;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title2) {
        this.title = title2;
    }

    public String getThumbs() {
        return this.thumbs;
    }

    public void setThumbs(String thumbs2) {
        this.thumbs = thumbs2;
    }

    public int getStar() {
        return this.star;
    }

    public void setStar(int star2) {
        this.star = star2;
    }

    public String getLatestprogram() {
        return this.latestprogram;
    }

    public void setLatestprogram(String latestprogram2) {
        this.latestprogram = latestprogram2;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description2) {
        this.description = description2;
    }

    public int getManipulate() {
        return this.manipulate;
    }

    public void setManipulate(int manipulate2) {
        this.manipulate = manipulate2;
    }

    public boolean getIssub() {
        return this.issub;
    }

    public void setIssub(boolean issub2) {
        this.issub = issub2;
    }

    public int getIsnews() {
        return this.isnews;
    }

    public void setIsnews(int isnews2) {
        this.isnews = isnews2;
    }

    public String getSmallpic() {
        return this.smallpic;
    }

    public void setSmallpic(String smallpic2) {
        this.smallpic = smallpic2;
    }

    public int getNum() {
        return this.num;
    }

    public void setNum(int num2) {
        this.num = num2;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type2) {
        this.type = type2;
    }

    public int getModelPm() {
        return this.modelPm;
    }

    public void setModelPm(int modelPm2) {
        this.modelPm = modelPm2;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this._id);
        dest.writeInt(this.id);
        dest.writeInt(this.qtid);
        dest.writeInt(this.qtpid);
        dest.writeString(this.title);
        dest.writeString(this.thumbs);
        dest.writeInt(this.star);
        dest.writeString(this.latestprogram);
        dest.writeString(this.description);
        dest.writeInt(this.manipulate);
        dest.writeInt(this.isnews);
        dest.writeByte((byte) (this.issub ? 1 : 0));
        dest.writeInt(this.num);
        dest.writeString(this.smallpic);
        dest.writeInt(this.type);
        dest.writeInt(this.modelPm);
    }
}
