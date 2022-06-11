package com.core.utils;

import net.sf.json.JSONObject;

import java.net.URLDecoder;
import java.util.Base64;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUilts {

    /**
     * 判断字符串是否为null或者""
     */
    public static boolean isEmptyOrNull(String content) {
        return content == null || content.equals("") || content.trim().equals("null");
    }

    public static String get32UUID() {
        String uuid = UUID.randomUUID().toString();    //获取UUID并转化为String对象
        uuid = uuid.replace("-", "");
        return uuid;
    }

    /**
     * 获取订单号
     * @param orderTitle
     * @return
     */
    public static String getOrderNumber(String orderTitle) {
        Random myr=new Random();
        return orderTitle+new Date().getTime()+"" +(myr.nextInt(99999)+100000);
    }
    /**
     * 获取订单号
     * @param
     * @return
     */
    public static Integer getRandomNumber(Integer number) {
        Random myr=new Random();
        return myr.nextInt(number-1)+number;
    }



    /**
     * 解决编码问题，去掉换行
     */
    public static String replaceBlank(String str) {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

    /**
     * 目前用于
     * 处理请求报文，取数据base64编码字符串
     * 随机字符（数字+字母）* 位 + 数据base64编码字符串 + 随机字符（数字+字母）* 位
     */
    public static String getStrData(String str, int start, int end) {
        String strData = "";
        try {
            String strs = URLDecoder.decode(str, "utf-8");
            JSONObject jsonData = JSONObject.fromObject(strs);
            String objData = jsonData.get("obj").toString();
            System.out.println("objData=" + objData);
            String newStr = objData.substring(start, objData.length());
            newStr = newStr.substring(0, newStr.length() - end);
            Base64.Decoder decoder = Base64.getDecoder();
            strData = new String(decoder.decode(newStr), "utf-8");
//            BASE64Decoder decoder = new BASE64Decoder();
//            strData=new String(decoder.decodeBuffer(newStr),"utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strData;
    }


    public static String getRandomNubmer(int num) {
        char[] codeSequence = {'0', '1', '2', '3', '4', '5', '6', '7', '8',
                '9'};
        Random random = new Random();
        StringBuffer str = new StringBuffer();
        for (int i = 0; i < num; i++) {
            String strRand = String.valueOf(codeSequence[random
                    .nextInt(codeSequence.length)]);
            str.append(strRand);
        }
        return str.toString();
    }
    public static boolean checkPwd(String pwd) {
        String patternStr ="[a-zA-Z0-9!\"#$%&'()*+,-./:;<=>?@\\]\\[^_`{|}~\"]{6,20}$";
        boolean isMatch = Pattern.matches(patternStr, pwd);
        return isMatch;
    }


    /**
     * TokenHeader
     * Token随机数 数字加字母
     */
    public static String getHeaderNumber(int n) {
        String val = "";
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            String str = random.nextInt(2) % 2 == 0 ? "num" : "char";
            if ("char".equalsIgnoreCase(str)) { // 产生字母
                int nextInt = random.nextInt(2) % 2 == 0 ? 65 : 97;
                // System.out.println(nextInt + "!!!!"); 1,0,1,1,1,0,0
                val += (char) (nextInt + random.nextInt(26));
            } else if ("num".equalsIgnoreCase(str)) { // 产生数字
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }

    public static String delHtmlTags(String htmlStr) {
        //定义script的正则表达式，去除js可以防止注入
        String scriptRegex="<script[^>]*?>[\\s\\S]*?<\\/script>";
        //定义style的正则表达式，去除style样式，防止css代码过多时只截取到css样式代码
        String styleRegex="<style[^>]*?>[\\s\\S]*?<\\/style>";
        //定义HTML标签的正则表达式，去除标签，只提取文字内容
        String htmlRegex="<[^>]+>";
        //定义空格,回车,换行符,制表符
        String spaceRegex = "\\s*|\t|\r|\n";

        //定义空格
        String nbspRegex = "\\&nbsp;";

        // 过滤script标签
        htmlStr = htmlStr.replaceAll(scriptRegex, "");
        // 过滤style标签
        htmlStr = htmlStr.replaceAll(styleRegex, "");
        // 过滤html标签
        htmlStr = htmlStr.replaceAll(htmlRegex, "");
        // 过滤空格等
        htmlStr = htmlStr.replaceAll(spaceRegex, "");
        // 过滤空格
        htmlStr = htmlStr.replaceAll(nbspRegex, "");
        return htmlStr.trim(); // 返回文本字符串
    }

    public static boolean checkHtml(String content) {
        //定义script的正则表达式，去除js可以防止注入
        String scriptRegex="<script[^>]*?>[\\s\\S]*?<\\/script>";
        //定义style的正则表达式，去除style样式，防止css代码过多时只截取到css样式代码
        String styleRegex="<style[^>]*?>[\\s\\S]*?<\\/style>";
        //定义HTML标签的正则表达式，去除标签，只提取文字内容
        String htmlRegex="<[^>]+>";
        //定义空格,回车,换行符,制表符
        String spaceRegex = "\\s*|\r|\n";
        //定义空格
        String nbspRegex = "\\&nbsp;";

        if(Pattern.matches(scriptRegex, content)){
            return true;
        }
        if(Pattern.matches(styleRegex, content)){
            return true;
        }
        if(Pattern.matches(htmlRegex, content)){
            return true;
        }
        if(Pattern.matches(spaceRegex, content)){
            return true;
        }
        if(Pattern.matches(nbspRegex, content)){
            return true;
        }
        return false;
    }

    /**
     * 获取随机字符串
     * @param length
     * @return
     */
    public static String getInviteCode(int length) {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "figure";
            if("char".equalsIgnoreCase(charOrNum)) {
                int choice = random.nextInt(2) % 2 == 0 ? 65 : 97;
                sb.append((char)(choice + random.nextInt(26)));
            } else if("figure".equalsIgnoreCase(charOrNum)) {
                sb.append(random.nextInt(10));
            }
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        System.out.println(checkPwd("111111"));
    }



}
