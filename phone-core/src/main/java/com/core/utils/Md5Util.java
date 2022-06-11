package com.core.utils;

import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Description: MD5加密工具
 * Auth: Frank
 * Date: 2017-10-26
 * Time: 上午 10:08
 */
public class Md5Util {
    /**
     * 获取字节数组形式的MD5摘要
     *
     * @param data 需要获取摘要的字节数组
     * @return 获取到的MD5摘要数组
     * @throws Exception
     */
    public static byte[] md5Byte(byte[] data) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(data);
        return md5.digest();
    }

    /**
     * 获取十六进制字符串形式的MD5摘要
     *
     * @param src 需要获得MD5摘要的字符串
     * @return 获取到的十六进制的MD5摘要
     */
    public static String md5Hex(String src) throws NoSuchAlgorithmException {
        byte[] bs = md5Byte(src.getBytes());
        return new String(new Hex().encode(bs));
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        System.out.println(md5Hex("bc9d57ca-4104-4a12-9c07-a3a1be0fbd96CZ1607682449956689994100"));
    }

}
