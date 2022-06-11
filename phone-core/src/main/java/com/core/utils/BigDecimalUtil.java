package com.core.utils;

import java.math.BigDecimal;

/**
 * Created by Miracle on 2020/8/17.
 */
public class BigDecimalUtil {

    /**
     * BigDecimal 大于比较
     *
     * @param a
     * @param b
     * @return
     */
    public static boolean dy(BigDecimal a, BigDecimal b) {
        if (a.compareTo(b) == 1) {
            return true;
        }
        return false;
    }

    /**
     * BigDecimal 大于等于比较
     *
     * @param a
     * @param b
     * @return
     */
    public static boolean dydy(BigDecimal a, BigDecimal b) {
        if (a.compareTo(b) > -1) {
            return true;
        }
        return false;
    }


    /**
     * BigDecimal 小于比较
     *
     * @param a
     * @param b
     * @return
     */
    public static boolean xy(BigDecimal a, BigDecimal b) {
        if (a.compareTo(b) == -1) {
            return true;
        }
        return false;
    }

    /**
     * BigDecimal 小于等于比较
     *
     * @param a
     * @param b
     * @return
     */
    public static boolean xydy(BigDecimal a, BigDecimal b) {
        if (a.compareTo(b) < 1) {
            return true;
        }
        return false;
    }
    /**
     * BigDecimal 等于
     * @param a
     * @param b
     * @return
     */
    public static boolean eq(BigDecimal a, BigDecimal b) {
        if(a.compareTo(b) == 0){
            return true;
        }
        return false;
    }


    /**
     * BigDecimal 加法
     *
     * @param a
     * @param b
     * @return
     */
    public static BigDecimal add(BigDecimal a, BigDecimal b) {
        if (a == null) {
            a = BigDecimal.ZERO;
        }
        if (b == null) {
            b = BigDecimal.ZERO;
        }
        return a.add(b);
    }


    /**
     * BigDecimal 减法
     *
     * @param a
     * @param b
     * @return
     */
    public static BigDecimal down(BigDecimal a, BigDecimal b) {
        if (a == null) {
            a = BigDecimal.ZERO;
        }
        if (b == null) {
            b = BigDecimal.ZERO;
        }
        return a.subtract(b);
    }

    /**
     * BigDecimal 乘法
     *
     * @param a
     * @param b
     * @return
     */
    public static BigDecimal multiply(BigDecimal a, BigDecimal b) {

        if (a == null) {
            a = BigDecimal.ZERO;
        }
        if (b == null) {
            b = BigDecimal.ZERO;
        }
        return a.multiply(b);
    }


    /**
     * BigDecimal 除法
     *
     * @param a
     * @param b
     * @return
     */
    public static BigDecimal divide(BigDecimal a, BigDecimal b) {
        if (a == null) {
            a = BigDecimal.ZERO;
        }
        if (b == null) {
            b = BigDecimal.ZERO;
        }
        return a.divide(b, 8, BigDecimal.ROUND_DOWN);
    }

    public static BigDecimal getRandomRedPacketBetweenMinAndMax(BigDecimal min, BigDecimal max){
        float minF = min.floatValue();
        float maxF = max.floatValue();


        //生成随机数
        BigDecimal db = new BigDecimal(Math.random() * (maxF - minF) + minF);

        //返回保留两位小数的随机数。不进行四舍五入
        return db.setScale(2,BigDecimal.ROUND_DOWN);
    }

    public static void main(String[] args) {
        System.out.println(dy(new BigDecimal("0.012674"),new BigDecimal("0")));
    }

}
