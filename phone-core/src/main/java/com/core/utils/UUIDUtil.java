package com.core.utils;

import java.util.UUID;

/**
 * UUID生成
 *
 * @author pangu
 */
public class UUIDUtil {

    //字符库
    public static String[] chars = new String[] { "a", "b", "c", "d", "e", "f",
            "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
            "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z", "-", "_"  };

    public static String getUuid(){
        String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
        return uuid;
    }

    /**
     * 生成8位UUID
     *
     * @return
     */
    public static String shortUuid() {
        //调用Java提供的生成随机字符串的对象：32位，十六进制，中间包含-
        String uuid = UUID.randomUUID().toString().replace("-", "");
        StringBuffer shortBuffer = new StringBuffer();
        //分为8组
        for (int i = 0; i < 8; i++) {
            //每组4位
            String str = uuid.substring(i * 4, i * 4 + 4);
            //输出str在16进制下的表示
            int x = Integer.parseInt(str, 16);
            //用该16进制数取模62（十六进制表示为314（14即E）），结果作为索引取出字符
            shortBuffer.append(chars[x % 0x3E]);
        }
        //生成8位字符
        return shortBuffer.toString();
    }

    /**
     * 生成16位UUID
     *
     * @return
     */
    public static String getShortUuid() {
        StringBuffer shortBuffer = new StringBuffer();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        // 每3个十六进制字符转换成为2个字符
        for (int i = 0; i < 10; i++) {
            String str = uuid.substring(i * 3, i * 3 + 3);
            // 转成十六进制
            int x = Integer.parseInt(str, 16);
            // 除64得到前面6个二进制数的
            shortBuffer.append(chars[x / 0x40]);
            // 对64求余得到后面6个二进制数1
            shortBuffer.append(chars[x % 0x40]);
        }
        // 加上后面两个没有改动的
        shortBuffer.append(uuid.charAt(30));
        shortBuffer.append(uuid.charAt(31));
        return shortBuffer.toString();
    }

}
