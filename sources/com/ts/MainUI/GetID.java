package com.ts.MainUI;

import android.util.Base64;
import com.google.zxing.common.StringUtils;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class GetID {
    private static GetID mGetid = new GetID();
    public String mDevice = null;
    public int mNum = 0;

    public static GetID getInstance() {
        return mGetid;
    }

    private String getString(String url, List<BasicNameValuePair> formparams) {
        String result = null;
        UrlEncodedFormEntity entity = null;
        try {
            entity = new UrlEncodedFormEntity(formparams, StringUtils.GB2312);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (entity == null) {
            return null;
        }
        HttpPost httppost = new HttpPost(url);
        httppost.setEntity(entity);
        HttpClient httpclient = new DefaultHttpClient();
        httpclient.getParams().setParameter("http.connection.timeout", 6000);
        httpclient.getParams().setParameter("http.socket.timeout", 6000);
        HttpResponse response = null;
        try {
            response = httpclient.execute(httppost);
        } catch (ClientProtocolException e2) {
            e2.printStackTrace();
        } catch (IOException e3) {
            e3.printStackTrace();
        }
        if (response == null) {
            return null;
        }
        HttpEntity httpentity = response.getEntity();
        if (httpentity != null) {
            try {
                result = EntityUtils.toString(httpentity, StringUtils.GB2312);
            } catch (ParseException e4) {
                e4.printStackTrace();
            } catch (IOException e5) {
                e5.printStackTrace();
            }
        }
        return result;
    }

    public int getIDAuto(String sLicense, String Imei) {
        List<BasicNameValuePair> formparams = new ArrayList<>();
        formparams.add(new BasicNameValuePair("LICENSE", sLicense));
        formparams.add(new BasicNameValuePair("MODE", "A"));
        if (Imei != null && Imei.length() > 5) {
            formparams.add(new BasicNameValuePair("IMEI", Imei));
        }
        String s = getString("http://xfzs.forfan.com.cn/xfzs/getid.php", formparams);
        if (s == null || s.isEmpty()) {
            return 2;
        }
        if (s.indexOf("invalid") == 0 || s.indexOf("error") == 0) {
            return 3;
        }
        try {
            s = new String(Base64.decode(s, 0), StringUtils.GB2312);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        int n = s.lastIndexOf("##*");
        if (n > 0) {
            String sNum = s.substring(n + 3);
            try {
                this.mDevice = new String(Base64.decode(s.substring(0, n), 0), StringUtils.GB2312);
            } catch (UnsupportedEncodingException e2) {
                e2.printStackTrace();
            }
            int n2 = sLicense.lastIndexOf("##*") + 3;
            this.mNum = Integer.valueOf(sNum).intValue();
        }
        if (this.mDevice != null) {
            return 1;
        }
        return 2;
    }

    public int getID(String sLicense, String Imei) {
        List<BasicNameValuePair> formparams = new ArrayList<>();
        formparams.add(new BasicNameValuePair("LICENSE", sLicense));
        formparams.add(new BasicNameValuePair("MODE", "G"));
        if (Imei != null && Imei.length() > 5) {
            formparams.add(new BasicNameValuePair("IMEI", Imei));
        }
        String s = getString("http://xfzs.forfan.com.cn/xfzs/getid.php", formparams);
        if (s == null || s.isEmpty()) {
            return 2;
        }
        if (s.indexOf("invalid") == 0 || s.indexOf("error") == 0) {
            return 3;
        }
        try {
            s = new String(Base64.decode(s, 0), StringUtils.GB2312);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        int n = s.lastIndexOf("##*");
        if (n > 0) {
            String substring = s.substring(n + 3);
            try {
                this.mDevice = new String(Base64.decode(s.substring(0, n), 0), StringUtils.GB2312);
            } catch (UnsupportedEncodingException e2) {
                e2.printStackTrace();
            }
        }
        if (this.mDevice != null) {
            return 1;
        }
        return 2;
    }

    public String GetIccidStateqp(String deviceid, String SCCID, String Imei) {
        List<BasicNameValuePair> formparams = new ArrayList<>();
        formparams.add(new BasicNameValuePair("c", SCCID));
        formparams.add(new BasicNameValuePair("d", deviceid));
        formparams.add(new BasicNameValuePair("m", Imei));
        return getString("http://xfzs.forfan.com.cn/qpy_getmsg", formparams);
    }

    public String GetSccidState(String deviceid, String SCCID, String Imei) {
        List<BasicNameValuePair> formparams = new ArrayList<>();
        formparams.add(new BasicNameValuePair("c", SCCID));
        formparams.add(new BasicNameValuePair("d", deviceid));
        formparams.add(new BasicNameValuePair("m", Imei));
        return getString("http://xfzs.forfan.com.cn/ysj_getmsg", formparams);
    }
}
