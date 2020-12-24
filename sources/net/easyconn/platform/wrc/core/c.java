package net.easyconn.platform.wrc.core;

import com.txznet.sdk.TXZPoiSearchManager;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: HttpUtils */
public class c {
    public static String a(String str, String str2, String str3, String str4) {
        try {
            JSONObject b = b(str, str2, str3, str4);
            DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(a.a("6y9m0ia1F/0E6k+iWzON6zw8W/xCF=28FwLL") + a.a("J/2d0TxM4zOc4uB86u21T4LL"));
            StringEntity stringEntity = new StringEntity(b.toString(), "UTF-8");
            httpPost.addHeader("Connection", "close");
            httpPost.setEntity(stringEntity);
            HttpResponse execute = defaultHttpClient.execute(httpPost);
            if (execute.getStatusLine().getStatusCode() == 200) {
                return EntityUtils.toString(execute.getEntity(), "UTF-8");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        return null;
    }

    public static String a(String str, Map<String, Object> map) {
        try {
            StringEntity stringEntity = new StringEntity(a(map).toString(), "UTF-8");
            HttpPost httpPost = new HttpPost(a.a("6y9m0ia1F/0E6k+iWzON6zw8W/xCF=28FwLL") + a.a(str));
            httpPost.addHeader("Connection", "close");
            httpPost.setEntity(stringEntity);
            DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
            defaultHttpClient.getParams().setParameter("http.connection.timeout", Integer.valueOf(TXZPoiSearchManager.DEFAULT_SEARCH_TIMEOUT));
            HttpResponse execute = defaultHttpClient.execute(httpPost);
            if (execute.getStatusLine().getStatusCode() == 200) {
                return EntityUtils.toString(execute.getEntity(), "UTF-8");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        return null;
    }

    private static JSONObject b(String str, String str2, String str3, String str4) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(a.a("0Y0i6/0y4vx8WMvn"), str);
            jSONObject.put(a.a("WzWEe/CnuELL"), str2);
            jSONObject.put(a.a("TMxN6M3n"), str3);
            jSONObject.put(a.a("J/2d04LL"), str4);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    public static JSONObject a(Map<String, Object> map) {
        JSONObject jSONObject = new JSONObject();
        try {
            for (Map.Entry next : map.entrySet()) {
                jSONObject.put(a.a((String) next.getKey()), next.getValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jSONObject;
    }
}
