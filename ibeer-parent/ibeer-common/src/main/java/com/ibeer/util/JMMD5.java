package com.ibeer.util;

import java.security.MessageDigest;

/**
 * Created by Administrator on 11/26/2017.
 */
public class JMMD5 {

    /**
     * Md5加密
     *
     * @param s
     * @return
     */
    public static String getMd5(String s) {
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        try {
            byte[] strTemp = s.getBytes("UTF-8");
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(strTemp);
            byte[] md = mdTemp.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }
    public static void main(String[] args){
        String str=JMMD5.getMd5("taotao141421");
        System.out.println(str+"---"+str.length());
        System.out.println("1ED3484C19345E22C7A2B4841B13C938".length());
    }
}
