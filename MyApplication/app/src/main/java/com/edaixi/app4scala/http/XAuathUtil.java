package com.edaixi.app4scala.http;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.telephony.TelephonyManager;

import com.edaixi.app4scala.utils.PrefUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Created by weichunsheng on 16/1/1.
 */
public class XAuathUtil {

    static PrefUtils prefUtils = new PrefUtils();

    /***
     * @param mContext   Context
     * @param httpUrl    sign url
     * @param httpParams Hash Params
     * @return
     */
    public static String signHttpUrl(Context mContext, String httpUrl, HashMap<String, String> httpParams) {
        String signHttpUrlString = "";
        httpParams.put("app_key", "app_client");
        httpParams.put("version", getAppVersion(mContext));
        httpParams.put("client_uuid", getAppUUID(mContext));
        httpParams.put("user_type", "3");
        httpParams.put("market", "");
        //应用市场
        httpParams.put("mark", "QiHu360");
        httpParams.put("city_id", "1");
        if (!prefUtils.getFromPrefs(mContext, "User_Id", "0").equals("0"))
            httpParams.put("user_id", prefUtils.getFromPrefs(mContext, "User_Id", "0"));
        httpParams.put("ts", String.valueOf(DateUtil.getTimestamp())); //时间戳
        List<String> paramsList = new ArrayList<>();
        for (String key : httpParams.keySet()) {
            paramsList.add(key + "=" + httpParams.get(key));
        }
        Collections.sort(paramsList);

        StringBuilder sbBuilder = new StringBuilder();
        for (String paramsListItem : paramsList) {
            sbBuilder.append(paramsListItem);
            sbBuilder.append("&");
        }
        String addString = sbBuilder.substring(0, sbBuilder.length() - 1);

        String string2md5 = MD5Util.string2MD5(addString + "LSEUa4APd5"
                + (httpParams.containsKey("user_id") ? getUserAccessToken(mContext) : ""));

        paramsList.add("sign=" + string2md5);

        StringBuilder sbStringBuilder = new StringBuilder();
        for (String paramsListString : paramsList) {
            sbStringBuilder.append(paramsListString);
            sbStringBuilder.append("&");
        }

        String add2String = sbStringBuilder.substring(0, sbStringBuilder.length() - 1);
        signHttpUrlString = httpUrl + add2String;
        return signHttpUrlString;
    }

    public static String getUserAccessToken(Context mContext) {
        return prefUtils.getFromPrefs(mContext, "User_Token", "0");
    }

    public static String getAppVersion(Context context) {
        PackageManager pm = context.getPackageManager();
        try {
            PackageInfo packageInfo = pm.getPackageInfo(
                    context.getPackageName(), 0);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "-1";
        }
    }

    public static String getAppUUID(Context context) {
        String UUIDString = null;
        final TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        UUIDString = "" + tm.getDeviceId();
        return UUIDString;
    }

}