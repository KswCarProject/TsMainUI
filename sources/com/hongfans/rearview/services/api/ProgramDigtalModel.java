package com.hongfans.rearview.services.api;

import android.os.Parcel;
import android.os.Parcelable;
import com.hongfans.carmedia.StringUtils;
import com.txznet.sdk.tongting.IConstantData;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ProgramDigtalModel implements Parcelable {
    public static final Parcelable.Creator<ProgramDigtalModel> CREATOR = new Parcelable.Creator<ProgramDigtalModel>() {
        public ProgramDigtalModel createFromParcel(Parcel in) {
            return new ProgramDigtalModel(in);
        }

        public ProgramDigtalModel[] newArray(int size) {
            return new ProgramDigtalModel[size];
        }
    };
    private int _id;
    private ArrayList<ADModel> ad_after;
    private ArrayList<ADModel> ad_before;
    private String album = "";
    private int comeType = 0;
    private String comefrom = "";
    private int curpage;
    private int duration;
    private String filepath;
    private String filepath1;
    private long id;
    private int iscollect;
    private int isnews = 0;
    private String parentname;
    private int pdid;
    private String pic = "";
    private String priority;
    private int programtype = -1;
    private String remark;
    private String secondtitle;
    private String singer = "";
    private String songid;
    private int sourcedataid;
    private int sourceid;
    private String title;
    private int total;
    private int type = 0;
    private int updatetime;

    public ProgramDigtalModel() {
    }

    public ProgramDigtalModel(Parcel pl) {
        this.id = pl.readLong();
        this.pdid = pl.readInt();
        this.curpage = pl.readInt();
        this.updatetime = pl.readInt();
        this.duration = pl.readInt();
        this.sourceid = pl.readInt();
        this.sourcedataid = pl.readInt();
        this.total = pl.readInt();
        this.programtype = pl.readInt();
        this.pic = pl.readString();
        this.parentname = pl.readString();
        this.filepath = pl.readString();
        this.filepath1 = pl.readString();
        this.priority = pl.readString();
        this.title = pl.readString();
        this.comefrom = pl.readString();
        this.album = pl.readString();
        this.singer = pl.readString();
        this.isnews = pl.readInt();
        this.type = pl.readInt();
        this.remark = pl.readString();
        this._id = pl.readInt();
        this.secondtitle = pl.readString();
        this.songid = pl.readString();
        this.iscollect = pl.readInt();
        this.comeType = pl.readInt();
        this.ad_before = pl.readArrayList(ADModel.class.getClassLoader());
        this.ad_after = pl.readArrayList(ADModel.class.getClassLoader());
    }

    public static ProgramDigtalModel parse(JSONObject json) throws JSONException {
        Object pic1;
        ProgramDigtalModel pm = new ProgramDigtalModel();
        if (json.has(IConstantData.KEY_ID)) {
            pm.id = (long) StringUtils.toInt(json.get(IConstantData.KEY_ID).toString());
        }
        if (json.has("sourceid")) {
            pm.sourceid = StringUtils.toInt(json.get("sourceid").toString());
        }
        if (json.has("sourcedataid")) {
            pm.sourcedataid = StringUtils.toInt(json.get("sourcedataid").toString());
        }
        if (json.has(IConstantData.KEY_TITLE)) {
            pm.title = json.get(IConstantData.KEY_TITLE).toString();
        }
        if (json.has(IConstantData.KEY_DURATION)) {
            pm.duration = StringUtils.toInt(json.get(IConstantData.KEY_DURATION).toString());
        }
        if (json.has("filepath1")) {
            pm.filepath1 = json.get("filepath1").toString();
        }
        if (json.has("priority")) {
            pm.priority = json.get("priority").toString();
        }
        if (json.has("pdid")) {
            pm.pdid = StringUtils.toInt(json.get("pdid").toString());
        }
        if (json.has("parentname")) {
            pm.parentname = json.get("parentname").toString();
        }
        if (json.has("programtype")) {
            pm.programtype = StringUtils.toInt(json.get("programtype").toString());
        }
        if (json.has("iscollect")) {
            pm.iscollect = StringUtils.toInt(json.get("iscollect").toString());
        }
        if (json.has("pic") && (pic1 = json.get("pic")) != null) {
            pm.pic = pic1.toString().split("\\|")[0];
        }
        if (json.has("singer")) {
            pm.singer = json.get("singer").toString();
        }
        if (json.has("fromsource")) {
            pm.comefrom = json.getString("fromsource");
        }
        if (json.has("songid")) {
            pm.songid = json.getString("songid");
        }
        if (json.has("comeType")) {
            pm.comeType = StringUtils.toInt(json.get("comeType").toString());
        }
        if (json.has("ad_before")) {
            ArrayList<ADModel> ad = new ArrayList<>();
            JSONArray arr = json.getJSONArray("ad_before");
            for (int i = 0; i < arr.length(); i++) {
                ADModel model = new ADModel();
                JSONObject obj = arr.getJSONObject(i);
                if (obj.has("ad_type")) {
                    model.setAd_type(obj.getString("ad_type"));
                }
                if (obj.has("video_text")) {
                    model.setVideo_text(obj.getString("video_text"));
                }
                if (obj.has("video_url")) {
                    model.setVideo_url(obj.getString("video_url"));
                }
                ad.add(model);
            }
            pm.ad_before = ad;
        }
        if (json.has("ad_after")) {
            ArrayList<ADModel> ad2 = new ArrayList<>();
            JSONArray arr2 = json.getJSONArray("ad_after");
            for (int i2 = 0; i2 < arr2.length(); i2++) {
                ADModel model2 = new ADModel();
                JSONObject obj2 = arr2.getJSONObject(i2);
                if (obj2.has("ad_type")) {
                    model2.setAd_type(obj2.getString("ad_type"));
                }
                if (obj2.has("video_text")) {
                    model2.setVideo_text(obj2.getString("video_text"));
                }
                if (obj2.has("video_url")) {
                    model2.setVideo_url(obj2.getString("video_url"));
                }
                ad2.add(model2);
            }
            pm.ad_after = ad2;
        }
        return pm;
    }

    public ArrayList<ADModel> getBeforeAD() {
        return this.ad_before;
    }

    public void setBeforeAD(ArrayList<ADModel> model) {
        this.ad_before = model;
    }

    public ArrayList<ADModel> getAfterAD() {
        return this.ad_after;
    }

    public void setAfterAD(ArrayList<ADModel> model) {
        this.ad_after = model;
    }

    public String getSecondTitle() {
        return this.secondtitle;
    }

    public void setSecondTitle(String secondtitle2) {
        this.secondtitle = secondtitle2;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark2) {
        this.remark = remark2;
    }

    public int getProgramtype() {
        return this.programtype;
    }

    public void setProgramtype(int programtype2) {
        this.programtype = programtype2;
    }

    public String getSinger() {
        return this.singer;
    }

    public void setSinger(String singer2) {
        this.singer = singer2;
    }

    public String getAlbum() {
        return this.album;
    }

    public void setAlbum(String album2) {
        this.album = album2;
    }

    public int getPdid() {
        return this.pdid;
    }

    public void setPdid(int pid) {
        this.pdid = pid;
    }

    public int getTotal() {
        return this.total;
    }

    public void setTotal(int total2) {
        this.total = total2;
    }

    public String getPic() {
        return this.pic;
    }

    public void setPic(String pic2) {
        this.pic = pic2;
    }

    public int getCurpage() {
        return this.curpage;
    }

    public void setCurpage(int curpage2) {
        this.curpage = curpage2;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id2) {
        this.id = id2;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title2) {
        this.title = title2;
    }

    public int getUpdatetime() {
        return this.updatetime;
    }

    public void setUpdatetime(int updatetime2) {
        this.updatetime = updatetime2;
    }

    public String getFilepath1() {
        return this.filepath1;
    }

    public void setFilepath1(String filepath12) {
        this.filepath1 = filepath12;
    }

    public String getPriority() {
        return this.priority;
    }

    public void setPriority(String priority2) {
        this.priority = priority2;
    }

    public void setFilepath(String filepath2) {
        this.filepath = filepath2;
    }

    public int getDuration() {
        return this.duration;
    }

    public void setDuration(int duration2) {
        this.duration = duration2;
    }

    public int getSourceid() {
        return this.sourceid;
    }

    public void setSourceid(int sourceid2) {
        this.sourceid = sourceid2;
    }

    public int getSourcedataid() {
        return this.sourcedataid;
    }

    public void setSourcedataid(int sourcedataid2) {
        this.sourcedataid = sourcedataid2;
    }

    public String getParentname() {
        return this.parentname;
    }

    public void setParentname(String parentname2) {
        this.parentname = parentname2;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type2) {
        this.type = type2;
    }

    public int getIsnews() {
        return this.isnews;
    }

    public void setIsnews(int isnews2) {
        this.isnews = isnews2;
    }

    public String getComefrom() {
        return this.comefrom;
    }

    public void setComefrom(String comefrom2) {
        this.comefrom = comefrom2;
    }

    public int get_id() {
        return this._id;
    }

    public void set_id(int _id2) {
        this._id = _id2;
    }

    public String getSongid() {
        return this.songid;
    }

    public void setSongid(String songid2) {
        this.songid = songid2;
    }

    public int getIscollect() {
        return this.iscollect;
    }

    public void setIscollect(int iscollect2) {
        this.iscollect = iscollect2;
    }

    public int getComeType() {
        return this.comeType;
    }

    public void setComeType(int comeType2) {
        this.comeType = comeType2;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeInt(this.pdid);
        dest.writeInt(this.curpage);
        dest.writeInt(this.updatetime);
        dest.writeInt(this.duration);
        dest.writeInt(this.sourceid);
        dest.writeInt(this.sourcedataid);
        dest.writeInt(this.total);
        dest.writeInt(this.programtype);
        dest.writeString(this.pic);
        dest.writeString(this.parentname);
        dest.writeString(this.filepath);
        dest.writeString(this.filepath1);
        dest.writeString(this.priority);
        dest.writeString(this.title);
        dest.writeString(this.comefrom);
        dest.writeString(this.album);
        dest.writeString(this.singer);
        dest.writeInt(this.isnews);
        dest.writeInt(this.type);
        dest.writeString(this.remark);
        dest.writeInt(this._id);
        dest.writeString(this.secondtitle);
        dest.writeString(this.songid);
        dest.writeInt(this.iscollect);
        dest.writeInt(this.comeType);
        dest.writeList(this.ad_before);
        dest.writeList(this.ad_after);
    }

    public String toJson() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(IConstantData.KEY_ID, this.id);
            jsonObject.put("pdid", this.pdid);
            jsonObject.put("curpage", this.curpage);
            jsonObject.put("updatetime", this.updatetime);
            jsonObject.put(IConstantData.KEY_DURATION, this.duration);
            jsonObject.put("sourceid", this.sourceid);
            jsonObject.put("sourcedataid", this.sourcedataid);
            jsonObject.put("total", this.total);
            jsonObject.put("programtype", this.programtype);
            jsonObject.put("pic", this.pic);
            jsonObject.put("parentname", this.parentname);
            jsonObject.put("filepath", this.filepath);
            jsonObject.put("filepath1", this.filepath1);
            jsonObject.put("priority", this.priority);
            jsonObject.put(IConstantData.KEY_TITLE, this.title);
            jsonObject.put("comefrom", this.comefrom);
            jsonObject.put("album", this.album);
            jsonObject.put("singer", this.singer);
            jsonObject.put("isnews", this.isnews);
            jsonObject.put(IConstantData.KEY_TYPE, this.type);
            jsonObject.put("remark", this.remark);
            jsonObject.put("_id", this._id);
            jsonObject.put("secondtitle", this.secondtitle);
            jsonObject.put("songid", this.songid);
            jsonObject.put("iscollect", this.iscollect);
            jsonObject.put("comeType", this.comeType);
            if (this.ad_before != null) {
                JSONArray ja = new JSONArray();
                Iterator<ADModel> it = this.ad_before.iterator();
                while (it.hasNext()) {
                    ADModel ad = it.next();
                    JSONObject jo = new JSONObject();
                    jo.put("ad_type", ad.getAd_type());
                    jo.put("video_text", ad.getVideo_text());
                    jo.put("video_url", ad.getVideo_url());
                }
                jsonObject.put("ad_before", ja);
            }
            if (this.ad_after != null) {
                JSONArray ja2 = new JSONArray();
                Iterator<ADModel> it2 = this.ad_after.iterator();
                while (it2.hasNext()) {
                    ADModel ad2 = it2.next();
                    JSONObject jo2 = new JSONObject();
                    jo2.put("ad_type", ad2.getAd_type());
                    jo2.put("video_text", ad2.getVideo_text());
                    jo2.put("video_url", ad2.getVideo_url());
                }
                jsonObject.put("ad_after", ja2);
            }
            return jsonObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }
    }

    public String toString() {
        return "ProgramDigtalModel{_id=" + this._id + ", id=" + this.id + ", pdid=" + this.pdid + ", curpage=" + this.curpage + ", updatetime=" + this.updatetime + ", duration=" + this.duration + ", sourceid=" + this.sourceid + ", sourcedataid=" + this.sourcedataid + ", total=" + this.total + ", programtype=" + this.programtype + ", remark='" + this.remark + '\'' + ", secondtitle='" + this.secondtitle + '\'' + ", ad_before=" + this.ad_before + ", ad_after=" + this.ad_after + ", pic='" + this.pic + '\'' + ", parentname='" + this.parentname + '\'' + ", filepath='" + this.filepath + '\'' + ", filepath1='" + this.filepath1 + '\'' + ", priority='" + this.priority + '\'' + ", title='" + this.title + '\'' + ", comefrom='" + this.comefrom + '\'' + ", album='" + this.album + '\'' + ", singer='" + this.singer + '\'' + ", type=" + this.type + ", isnews=" + this.isnews + ", songid='" + this.songid + '\'' + ", iscollect=" + this.iscollect + ", comeType=" + this.comeType + '}';
    }
}
