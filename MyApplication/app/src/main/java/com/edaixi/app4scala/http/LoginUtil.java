package com.edaixi.app4scala.http;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.edaixi.app4scala.moudle.UserBean;
import com.edaixi.app4scala.utils.PrefUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by weichunsheng on 15/8/12.
 */
public class LoginUtil {

    private Context mContext;
    PrefUtils prefUtils;

    public LoginUtil(Context context) {
        mContext = context;
        prefUtils = new PrefUtils();
    }

    private String join(List<String> strings, String join) {
        StringBuilder sb = new StringBuilder();
        for (String string : strings) {
            sb.append(string);
            sb.append(join);
        }
        return sb.substring(0, sb.length() - 1);
    }

    private String sign(List<String> strings) {
        Collections.sort(strings);
        String str = join(strings, "&");
        if (strings.contains("user_id")) {
            return MD5Util.string2MD5(str + "LSEUa4APd5"
                    + XAuathUtil.getUserAccessToken(mContext));
        } else {
            return MD5Util.string2MD5(str + "LSEUa4APd5");
        }
    }

    private String requestByPost(String path, Map<String, Object> params)
            throws Exception {
        String markFlag = "Default";
        params.put("app_key", "app_client");
        params.put("version", getVersion());
        params.put("user_type", 3);
        params.put("market", "");
        params.put("mark", markFlag);
        List<String> pairs = new ArrayList<String>();
        for (String key : params.keySet()) {
            pairs.add(key + "=" + params.get(key));
        }

        String sign = sign(pairs);
        pairs.clear();
        pairs.add("sign=" + sign);
        for (String key : params.keySet()) {
            pairs.add(key
                    + "="
                    + URLEncoder.encode(String.valueOf(params.get(key)),
                    "UTF-8"));
        }
        String param = join(pairs, "&");

        String result = "-1";
        // 请求的参数转换为byte数组
        // 新建一个URL对象
        URL url = new URL(path + param);
        // 打开一个HttpURLConnection连接
        HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
        // 设置连接超时时间
        urlConn.setConnectTimeout(10 * 7000);
        // Post请求必须设置允许输出
        urlConn.setDoOutput(true);
        // Post请求不能使用缓存
        urlConn.setUseCaches(false);
        // 设置为Post请求
        urlConn.setRequestMethod("POST");
        urlConn.setInstanceFollowRedirects(true);
        // 配置请求Content-Type
        urlConn.setRequestProperty("content-type", "text/html;charset=utf-8");
        // 开始连接
        urlConn.connect();
        // 发送请求参数
        // 判断请求是否成功
        if (urlConn.getResponseCode() == 200
                || urlConn.getResponseCode() == 201) {
            // 获取返回的数据
            byte[] data = readStream(urlConn.getInputStream());
            result = new String(data, "UTF-8");
        } else {
            result = "-1";
        }
        urlConn.disconnect();
        return result;
    }

    // 获取连接返回的数据
    private static byte[] readStream(InputStream inputStream) throws Exception {
        byte[] buffer = new byte[4096];
        int len = -1;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int count = 1;
        while ((len = inputStream.read(buffer)) != -1) {
            baos.write(buffer, 0, len);
            buffer = new byte[4096];
        }
        byte[] data = baos.toByteArray();
        inputStream.close();
        baos.close();
        return data;
    }

    public String getlogin(String phone, String code) {
        String path = Constants.GET_LOGIN;
        String str = "";
        String result = "";
        try {
            HashMap<String, Object> params = new HashMap<String, Object>();
            params.put("phone", phone);
            params.put("code", code);
            params.put("push_token", "");
            try {
                result = requestByPost(path, params);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (null != result && !"".equals(result) && !"-1".equals(result)) {
                JSONObject json = new JSONObject(result);
                if (json.getBoolean("ret")) {
                    str = "true";
                    String datajson = json.getString("data");
                    UserBean userbean = JSON.parseObject(datajson, UserBean.class);
                    prefUtils.saveToPrefs(mContext, "Is_Logined", "true");
                    prefUtils.saveToPrefs(mContext, "User_Token", userbean.getUser_token());
                    prefUtils.saveToPrefs(mContext, "User_Id", userbean.getUser_id());
                } else {
                    try {
                        json = new JSONObject(result);
                        if (!json.getBoolean("ret")) {
                            str = json.getString("error");
                        }
                        if (!json.getBoolean("ret")
                                && json.getString("error").contains("401")) {
                            prefUtils.saveToPrefs(mContext, "Is_Logined", "false");
                            prefUtils.saveToPrefs(mContext, "User_Token", "");
                            prefUtils.saveToPrefs(mContext, "User_Id", "");
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }


    public String getsms(String phone) {
        String path = Constants.GET_VERIFY_CODE;
        String str = "";
        String result = "";
        try {
            HashMap<String, Object> params = new HashMap<String, Object>();
            params.put("phone", phone);
            System.out.println(path + params);
            try {
                result = requestByPost(path, params);
            } catch (Exception e) {

                e.printStackTrace();
            }
            if (null != result && !"".equals(result) && !"-1".equals(result)) {
                JSONObject object = new JSONObject(result);
                if (object.get("ret").equals(true)) {
                    str = "true";
                } else {
                    str = object.getString("error");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(str);
        return str;
    }

    public String getVersion() {
        PackageManager pm = mContext.getPackageManager();
        try {
            // 代表的就是清单文件的信息。
            PackageInfo packageInfo = pm.getPackageInfo(
                    mContext.getPackageName(), 0);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            // 肯定不会发生。
            return "";
        }
    }
}
