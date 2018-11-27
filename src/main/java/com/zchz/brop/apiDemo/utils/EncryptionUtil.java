package com.zchz.brop.apiDemo.utils;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 该类是一个公共算法类，通过此类可以对字符串、文件进行SHA-256算法加密
 *
 * @author zccp
 */
public class EncryptionUtil {

    static final String ENCNAME = "SHA-256";

    /**
     * 对字符串加密,加密算法使用MD5,SHA-1,SHA-256,默认使用SHA-256
     *
     * @param strSrc 要加密的字符串
     * @return
     */
    public static String encryptStr(String strSrc) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        byte[] bt = strSrc.getBytes("UTF-8");
        MessageDigest md = MessageDigest.getInstance(ENCNAME);
        md.update(bt);
        String strDes = bytesToHex(md.digest()); // to HexString
        return strDes;
    }

    public static String bytesToHex(byte[] bts) {
        String des = "";
        String tmp = null;
        for (int i = 0; i < bts.length; i++) {
            tmp = (Integer.toHexString(bts[i] & 0xFF));
            if (tmp.length() == 1) {
                des += "0";
            }
            des += tmp;
        }
        return des;
    }
}