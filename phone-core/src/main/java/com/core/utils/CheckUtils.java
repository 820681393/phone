package com.core.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Miracle
 * @date 2021/1/3 17:30
 */
public class CheckUtils {

    public static boolean isEmail(String string) {
        if (string == null)
            return false;
        String regEx1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern p;
        Matcher m;
        p = Pattern.compile(regEx1);
        m = p.matcher(string);
        if (m.matches()){
            return true;
        } else{
            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println(isEmail("820681393@qq.com"));
    }
}
