package com.core.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;


/**
 * <p> HTTP请求工具类
 *
 * @author Gray
 * @Description </p>
 * @since 2020/12/12
 */
public class HttpUtil {

    public String doPost(String httpUrl, Map<String, Object> parameter) throws IOException {

        HttpURLConnection connection = null;
        InputStream is = null;
        OutputStream os = null;
        BufferedReader br = null;
        String result = null;
        URL url = new URL(httpUrl);
        // 通过远程url连接对象打开连接
        connection = (HttpURLConnection) url.openConnection();
        // 设置连接请求方式
        connection.setRequestMethod("POST");
        // 设置连接主机服务器超时时间：15000毫秒
        connection.setConnectTimeout(15000);
        // 设置读取主机服务器返回数据超时时间：60000毫秒
        connection.setReadTimeout(60000);

        // 默认值为：false，当向远程服务器传送数据/写数据时，需要设置为true
        connection.setDoOutput(true);
        // 默认值为：true，当前向远程服务读取数据时，设置为true，该参数可有可无
        connection.setDoInput(true);
        // 设置传入参数的格式:请求参数应该是 name1=value1&name2=value2 的形式。
        connection.setRequestProperty("Content-Type", "application/json");
        // 设置鉴权信息：Authorization: Bearer da3efcbf-0845-4fe3-8aba-ee040be542c0
        connection.setRequestProperty("Authorization", "Bearer da3efcbf-0845-4fe3-8aba-ee040be542c0");
        // 通过连接对象获取一个输出流
        os = connection.getOutputStream();
        // 通过输出流对象将参数写出去/传输出去
        os.write(createParamter(parameter).getBytes());
        // 通过连接对象获取一个输入流，向远程读取
        if (connection.getResponseCode() == 200) {

            is = connection.getInputStream();
            // 对输入流对象进行包装:charset根据工作项目组的要求来设置
            br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

            StringBuffer sbf = new StringBuffer();
            String temp = null;
            // 循环遍历一行一行读取数据
            while ((temp = br.readLine()) != null) {
                sbf.append(temp);
                sbf.append("\r\n");
            }
            result = sbf.toString();
        }
        // 关闭资源
        if (null != br) {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (null != os) {
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (null != is) {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 断开与远程地址url的连接
        connection.disconnect();
        return result;
    }

    /**
     * 参数写在URL后面的
     * @param httpUrl
     * @return
     * @throws IOException
     */
    public String doPostString(String httpUrl) throws IOException {

        HttpURLConnection connection = null;
        InputStream is = null;
        OutputStream os = null;
        BufferedReader br = null;
        String result = null;
        URL url = new URL(httpUrl);
        // 通过远程url连接对象打开连接
        connection = (HttpURLConnection) url.openConnection();
        // 设置连接请求方式
        connection.setRequestMethod("POST");
        // 设置连接主机服务器超时时间：15000毫秒
        connection.setConnectTimeout(15000);
        // 设置读取主机服务器返回数据超时时间：60000毫秒
        connection.setReadTimeout(60000);

        // 默认值为：false，当向远程服务器传送数据/写数据时，需要设置为true
        connection.setDoOutput(true);
        // 默认值为：true，当前向远程服务读取数据时，设置为true，该参数可有可无
        connection.setDoInput(true);
        // 设置传入参数的格式:请求参数应该是 name1=value1&name2=value2 的形式。
        connection.setRequestProperty("Content-Type", "application/json");
        // 设置鉴权信息：Authorization: Bearer da3efcbf-0845-4fe3-8aba-ee040be542c0
        connection.setRequestProperty("Authorization", "Bearer da3efcbf-0845-4fe3-8aba-ee040be542c0");
        // 通过连接对象获取一个输出流
        os = connection.getOutputStream();
        // 通过输出流对象将参数写出去/传输出去
        //os.write(createParamterString(parameter).getBytes());
        // 通过连接对象获取一个输入流，向远程读取
        if (connection.getResponseCode() == 200) {

            is = connection.getInputStream();
            // 对输入流对象进行包装:charset根据工作项目组的要求来设置
            br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

            StringBuffer sbf = new StringBuffer();
            String temp = null;
            // 循环遍历一行一行读取数据
            while ((temp = br.readLine()) != null) {
                sbf.append(temp);
                sbf.append("\r\n");
            }
            result = sbf.toString();
        }
        // 关闭资源
        if (null != br) {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (null != os) {
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (null != is) {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 断开与远程地址url的连接
        connection.disconnect();
        return result;
    }


    public String doGet(String httpUrl, Map<String, Object> parameter) throws IOException {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = httpUrl + "?" + createParamter(parameter);
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    private String createParamter(Map<String, Object> paramater) {
        StringBuffer buffer = new StringBuffer();
        boolean first = true;
        for (String key : paramater.keySet()) {
            if (first) first = false;
            else buffer.append("&");
            String strValue = paramater.get(key).toString();
            String encodeValue;
            try {
                encodeValue = URLEncoder.encode(strValue, "utf-8");
            } catch (UnsupportedEncodingException e) {
                encodeValue = strValue;
            }
            buffer.append(key).append("=").append(encodeValue);
        }
        return buffer.toString();
    }
    private String createParamterString(Map<String, String> paramater) {
        StringBuffer buffer = new StringBuffer();
        boolean first = true;
        for (String key : paramater.keySet()) {
            if (first) first = false;
            else buffer.append("&");
            String strValue = paramater.get(key);
            String encodeValue;
            try {
                encodeValue = URLEncoder.encode(strValue, "utf-8");
            } catch (UnsupportedEncodingException e) {
                encodeValue = strValue;
            }
            buffer.append(key).append("=").append(encodeValue);
        }
        return buffer.toString();
    }
}
