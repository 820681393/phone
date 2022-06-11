package com.core.utils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by ben on 2017-03-14 0014.
 * 移动端交互传递实体
 */
@ApiModel(value = "返回数据")
public class ResponseResult<T> implements Serializable {

    @ApiModelProperty(value = "请求状态码")
    private int statusCode;

    @ApiModelProperty(value = "返回信息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private T data;

    public ResponseResult() {

    }

    public static ResponseResult successResult(Object object) {
        ResponseResult responseResult = new ResponseResult();
        responseResult.setStatusCode(ResponseCode.FAIL);
        responseResult.setMessage("请求成功");
        responseResult.setData(object);
        return responseResult;
    }


    /**
     * 返回失败
     *
     * @param err
     * @return
     */
    public static ResponseResult failResult(String err) {
        ResponseResult responseResult = new ResponseResult();
        responseResult.setStatusCode(ResponseCode.FAIL);
        responseResult.setMessage(err);
        return responseResult;
    }
    /**
     * 返回失败
     * @return
     */
    public static ResponseResult userKeyExpireResult() {
        ResponseResult responseResult = new ResponseResult();
        responseResult.setStatusCode(ResponseCode.USER_KEY_EXPIRE);
        responseResult.setMessage("token失效");
        return responseResult;
    }





    public ResponseResult(int statusCode, T data) {
        this.statusCode = statusCode;
        this.data = data;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
