package com.core.utils;


import com.google.gson.Gson;
import com.core.service.IRedisBaseService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.log4j.Log4j2;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * Description:Token生成工具
 * 修改为标准的jwt sub为内容 created为创建时间
 * Auth: Frank
 * Date: 2017-11-02
 * Time: 下午 5:05
 */
@Log4j2
public class TokenUtil {

    //token内容
    private static final String CLAIM_KEY_SUB = "sub";
    //token创建时间
    private static final String CLAIM_KEY_CREATED = "created";


    //密钥
    private static String secret = SSOCommon.TOKEN_AES_KEY;

    //超时时间
    private static Long expiration = SSOCommon.SSO_EXP;


    /**
     * 获取token内容
     * @param data 数据
     * @return token内容
     */
    public static <T> String getToken(T data) throws Exception {
        TokenPlayload<T> userTokenPlayload = new TokenPlayload<>();
        userTokenPlayload.setExpData(data);
        String jwt = createJWT(userTokenPlayload);
        return jwt;
    }

    public static Date getCreatedDateFromToken(String token) {
        Date created;
        try {
            final Claims claims = getClaimsFromToken(token);
            created = new Date((Long) claims.get(CLAIM_KEY_CREATED));
        } catch (Exception e) {
            created = null;
        }
        return created;
    }

    public static Date getExpirationDateFromToken(String token) {
        Date expiration;
        try {
            final Claims claims = getClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (Exception e) {
            expiration = null;
        }
        return expiration;
    }

    private static Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    private static Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    private static Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    private static Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
        return (lastPasswordReset != null && created.before(lastPasswordReset));
    }

    /**
     * 生成JWT
     *
     * @return
     */
    public static <T> String createJWT(TokenPlayload<T> tokenPlayload) throws Exception {
        String subJson = JsonUtils.objectToJson(tokenPlayload);
        String subBase64 = Base64Util.encryptBASE64(subJson.getBytes());

        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_SUB, subBase64);
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }

    public static String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * 获取token里面用户信息
     *
     * @return
     */
    public static TokenPlayload getTokenUserData(String jwt) throws Exception {

        TokenPlayload tTokenPlayload;
        try {
            final Claims claims = getClaimsFromToken(jwt);
            String result = claims.getSubject();
            tTokenPlayload = JsonUtils.jsonToPojo(new String(Base64Util.decryptBASE64(result)), TokenPlayload.class);

        } catch (Exception e) {
            tTokenPlayload = null;
        }
        return tTokenPlayload;
    }

    public static UserToken getUserToken(HttpServletRequest request, IRedisBaseService redisBaiseTakes) throws Exception {
        ResponseResult result = new ResponseResult();
        String token = request.getHeader("authorization");
        UserToken userToken = null;
        if (!StringUilts.isEmptyOrNull(token)) {
            Gson gson = new Gson();
            userToken = gson.fromJson(gson.toJson(TokenUtil.getTokenUserData(token).getExpData()), UserToken.class);

            String tokenInfo = (String) redisBaiseTakes.get(userToken.getType()+userToken.getId() + userToken.getUserName());
            if (tokenInfo == null) {
                result.setStatusCode(ResponseCode.USER_KEY_EXPIRE);
                result.setMessage("token过期");
                return null;
            }
            if (!tokenInfo.equals(token)) {
                result.setStatusCode(ResponseCode.USER_KEY_EXPIRE);
                result.setMessage("token过期");
                return null;
            }
            if (userToken == null) {
                result.setStatusCode(ResponseCode.USER_KEY_EXPIRE);
                result.setMessage("需要令牌");
            } else {
                result.setStatusCode(ResponseCode.SUCCESS);
                result.setMessage("请求成功");
                result.setData(userToken);
            }
        } else {
            result.setStatusCode(ResponseCode.USER_KEY_EXPIRE);
            result.setMessage("需要令牌");
        }
        return userToken;
    }
    public static UserToken getUserToken(String token, IRedisBaseService redisBaiseTakes) throws Exception {
        ResponseResult result = new ResponseResult();
        UserToken userToken = null;
        if (!StringUilts.isEmptyOrNull(token)) {
            Gson gson = new Gson();
            if(TokenUtil.getTokenUserData(token)==null){
                result.setStatusCode(ResponseCode.USER_KEY_EXPIRE);
                result.setMessage("token过期");
                return null;
            }
            userToken = gson.fromJson(gson.toJson(TokenUtil.getTokenUserData(token).getExpData()), UserToken.class);
            String tokenInfo = (String) redisBaiseTakes.get(userToken.getType()+userToken.getId() + userToken.getUserName());
            if (tokenInfo == null) {
                result.setStatusCode(ResponseCode.USER_KEY_EXPIRE);
                result.setMessage("token过期");
                return null;
            }
            if (!tokenInfo.equals(token)) {
                result.setStatusCode(ResponseCode.USER_KEY_EXPIRE);
                result.setMessage("token过期");
                return null;
            }
            if (userToken == null) {
                result.setStatusCode(ResponseCode.USER_KEY_EXPIRE);
                result.setMessage("需要令牌");
            } else {
                result.setStatusCode(ResponseCode.SUCCESS);
                result.setMessage("请求成功");
                result.setData(userToken);
            }
        } else {
            result.setStatusCode(ResponseCode.USER_KEY_EXPIRE);
            result.setMessage("需要令牌");
        }
        return userToken;
    }

    public static ResponseResult verificationUserToken(HttpServletRequest request, IRedisBaseService redisBaiseTakes) {
        ResponseResult responseResult = new ResponseResult();
        UserToken userToken = null;
        try {
            userToken = TokenUtil.getUserToken(request, redisBaiseTakes);
        } catch (Exception e) {
            log.error("验证token失败", e);
        }
        if (userToken == null) {
            responseResult.setStatusCode(ResponseCode.USER_KEY_EXPIRE);
            responseResult.setMessage("用户token过期");
        } else {
            responseResult.setStatusCode(ResponseCode.SUCCESS);
            responseResult.setMessage("验证成功");
            responseResult.setData(userToken);
        }
        return responseResult;
    }


}
