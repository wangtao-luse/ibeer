package com.ibeer.util;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class ShiroMD5 {

    //加密方式
    private final static String HASHMODE = "Md5";
    //加密次数
    private final static int SALTNUM = 1024;

    public static Object shiroMD5(String mySalt, String pwd) {
        String hashAlgorithmName = HASHMODE;
        //"123456";
        Object credentials = pwd;
        //以用户名作为盐加密
        Object salt = ByteSource.Util.bytes(mySalt);
        int hashIterations = SALTNUM;
        Object result = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
        return result;
    }

    public static void main(String[] args) {
        System.out.println(shiroMD5("5623","1"));
    }

}
