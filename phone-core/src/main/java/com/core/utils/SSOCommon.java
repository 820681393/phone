package com.core.utils;

public class SSOCommon {

    public static final String SSO_BASE_URL = "http://192.168.25.136:8082";
    public static final int SSO_SESSION_EXPIRE = 1800;
    public static final String REDIS_USER_SESSION_KEY = "REDIS_USER_SESSION";
    public static final String TOKEN_AES_KEY = "78B0EDB97D27C402B9AFD8C26E84D1EF";
    public static final long SSO_EXP = 172800000;//设置token有效期，单位毫秒 2天
}
