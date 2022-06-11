package com.core.utils;

import java.util.Random;

/**
 * Description: 随机生成字符串
 * Auth: Frank
 * Date: 2017-10-26
 * Time: 上午 10:25
 */
public class StringRandomUtil {
    private static final String CHAR = "char";//字母
    private static final String NUM = "num";//数字

    /**
     * 获得指定长度的随机字符串
     * 参照ASCII码表 65到90为大写字母，97到122为小写字母
     *
     * @param length 要生成的字符串长度
     * @return 返回生成的随机字符串
     */
    public static String getStringRandom(int length) {
        StringBuffer valSb = new StringBuffer();
        Random random = new Random();

        //参数length，表示生成几位随机数
        for (int i = 0; i < length; i++) {
            //输出是数字还是字母
            String charOrNum = random.nextInt(2) % 2 == 0 ? CHAR : NUM;
            switch (charOrNum) {
                case CHAR:
                    //输出是大写字母还是小写字母
                    int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                    valSb.append((char) (random.nextInt(26) + temp));
                    break;
                case NUM:
                    valSb.append(random.nextInt(10));
                    break;
                default:
                    break;
            }
        }
        return valSb.toString();
    }
}
