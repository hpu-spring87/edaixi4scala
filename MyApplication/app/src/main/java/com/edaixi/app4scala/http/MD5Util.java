package com.edaixi.app4scala.http;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by weichunsheng on 16/1/1.
 */
public class MD5Util {
    public static String string2MD5(String inStr) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        md.update(inStr.getBytes());
        byte[] bs = md.digest();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < bs.length; i++) {
            sb.append(String.format("%02x", bs[i] & 0xff));
        }
        return sb.toString();
    }

}
