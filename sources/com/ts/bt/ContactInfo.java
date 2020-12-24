package com.ts.bt;

import android.text.TextUtils;
import com.txznet.sdk.tongting.IConstantData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ContactInfo {
    public static final String COMBINATION_SEPERATOR = ",";
    private String contactId;
    private String name;
    private String[] nameNorm;
    private String[] namePinyin;
    private final List<PhoneInfo> phoneInfos = new ArrayList();
    private String photoThumbUri;
    private String tag;

    public String[] getNameNorm() {
        return this.nameNorm;
    }

    public void setNameNorm(String[] nameNorm2) {
        this.nameNorm = nameNorm2;
    }

    public List<PhoneInfo> getPhoneInfos() {
        return this.phoneInfos;
    }

    public String getContactId() {
        return this.contactId;
    }

    public void setContactId(String contactId2) {
        this.contactId = contactId2;
    }

    public String getPhotoThumbUri() {
        return this.photoThumbUri;
    }

    public void setPhotoThumbUri(String photoThumbUri2) {
        this.photoThumbUri = photoThumbUri2;
    }

    public void addPhoneInfo(PhoneInfo phoneInfo) {
        this.phoneInfos.add(phoneInfo);
    }

    public PhoneInfo getTopPhoneInfo() {
        if (this.phoneInfos == null || this.phoneInfos.size() <= 0) {
            return null;
        }
        return this.phoneInfos.get(0);
    }

    public String getTag() {
        return this.tag;
    }

    public void setTag(String tag2) {
        this.tag = tag2;
    }

    public String toString() {
        return toJson2().toString();
    }

    public JSONObject toJson() {
        try {
            JSONObject jsonObj = new JSONObject();
            jsonObj.put(IConstantData.KEY_NAME, getName());
            JSONArray pinyinArray = new JSONArray();
            String[] pinyins = getPinyin();
            if (pinyins != null) {
                for (String pinyin : pinyins) {
                    pinyinArray.put(pinyin);
                }
            }
            jsonObj.put("pinyin", pinyinArray);
            JSONArray phoneInfoArray = new JSONArray();
            for (PhoneInfo phoneInfo : this.phoneInfos) {
                phoneInfoArray.put(phoneInfo.toJson());
            }
            jsonObj.put("phone_info", phoneInfoArray);
            JSONArray nameNormArray = new JSONArray();
            if (this.nameNorm != null) {
                for (String name2 : this.nameNorm) {
                    nameNormArray.put(name2);
                }
            }
            jsonObj.put("name_norm", nameNormArray);
            return jsonObj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public JSONObject toJson2() {
        try {
            JSONObject jsonObj = new JSONObject();
            jsonObj.put(IConstantData.KEY_NAME, getName());
            JSONArray phoneInfoArray = new JSONArray();
            for (PhoneInfo phoneInfo : this.phoneInfos) {
                phoneInfoArray.put(phoneInfo.toJson());
            }
            jsonObj.put("phone_info", phoneInfoArray);
            return jsonObj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name2) {
        this.name = name2;
    }

    public String[] getPinyin() {
        return this.namePinyin;
    }

    public void setPinyin(String[] fullPinyin) {
        this.namePinyin = fullPinyin;
    }

    public static class PhoneInfo {
        static final Map<String, String> OPERATOR_MAP = new HashMap<String, String>() {
            {
                put("130", "閼辨棃锟斤拷");
                put("131", "閼辨棃锟斤拷");
                put("132", "閼辨棃锟斤拷");
                put("133", "閻㈠吀淇�");
                put("134", "閻㈠吀淇�");
                put("134", "缁夎濮�");
                put("135", "缁夎濮�");
                put("136", "缁夎濮�");
                put("137", "缁夎濮�");
                put("138", "缁夎濮�");
                put("139", "缁夎濮�");
                put("145", "閼辨棃锟斤拷");
                put("147", "缁夎濮�");
                put("150", "缁夎濮�");
                put("151", "缁夎濮�");
                put("152", "缁夎濮�");
                put("153", "閻㈠吀淇�");
                put("155", "閼辨棃锟斤拷");
                put("156", "閼辨棃锟斤拷");
                put("157", "缁夎濮�");
                put("158", "缁夎濮�");
                put("159", "缁夎濮�");
                put("180", "閻㈠吀淇�");
                put("181", "閻㈠吀淇�");
                put("182", "缁夎濮�");
                put("183", "缁夎濮�");
                put("185", "閼辨棃锟斤拷");
                put("186", "閼辨棃锟斤拷");
                put("187", "缁夎濮�");
                put("188", "缁夎濮�");
                put("189", "閻㈠吀淇�");
            }
        };
        public static final String PHONE_REGEX = "(^(\\+86)|[\\(\\)-[\\s]])";
        private final String LANDLINE_NUM_REGEX = "[0]{0,1}[0-9]{2,3}[0-9]{7,8}";
        private final String MOBILE_NUM_REGEX = "[1]{1}[3,4,5,7,8]{1}[0-9]{9}";
        private String flag = "";
        private String location = "";
        private Pattern mLandlineNumPattern = Pattern.compile("[0]{0,1}[0-9]{2,3}[0-9]{7,8}");
        private Pattern mMobileNumPattern = Pattern.compile("[1]{1}[3,4,5,7,8]{1}[0-9]{9}");
        private String numId = "";
        private String number = "";
        private String operator = "";
        private String type = "";

        public String getNumber() {
            return this.number;
        }

        public void setNumber(String number2) {
            if (!TextUtils.isEmpty(number2)) {
                this.number = number2.replaceAll(PHONE_REGEX, "");
            }
        }

        public String getNumId() {
            return this.numId;
        }

        public void setNumId(String numId2) {
            this.numId = numId2;
        }

        public String getLocation() {
            return this.location;
        }

        public void setLocation(String callerCity) {
            this.location = callerCity;
        }

        public String getOperator() {
            return this.operator;
        }

        public void setOperator(String operator2) {
            this.operator = operator2;
        }

        public String getType() {
            return this.type;
        }

        public void setType(String type2) {
            this.type = type2;
        }

        public String getFlag() {
            return this.flag;
        }

        public void setFlag(String flag2) {
            this.flag = flag2;
        }

        public void setLocationAndOperator(String area) {
            if (!TextUtils.isEmpty(area)) {
                this.location = area;
            }
            this.operator = OPERATOR_MAP.get(this.number.substring(0, 3));
        }

        public void setTypeAndTagByTypeId(int typeId, String number2) {
            if (this.mMobileNumPattern.matcher(number2).find()) {
                this.type = "閹靛婧�";
            } else if (this.mLandlineNumPattern.matcher(number2).find()) {
                this.type = "鎼囱勬簚";
            }
        }

        public String toString() {
            return "number: " + this.number + " callerloc: " + this.location + " operator: " + this.operator + " type: " + this.type + " flag: " + this.flag;
        }

        public JSONObject toJson() throws JSONException {
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("number", this.number);
            jsonObj.put("location", this.location);
            jsonObj.put("operator", this.operator);
            jsonObj.put(IConstantData.KEY_TYPE, this.type);
            jsonObj.put(IConstantData.KEY_FLAG, this.flag);
            return jsonObj;
        }
    }
}
