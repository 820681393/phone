package com.core.utils;

import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ResponseMessage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2020/7/21.
 */
public class ResponseMessageUtils {

    /**
     * 返回状态码
     *
     * @return
     */
    public static List<ResponseMessage> getResponseState() {
        List<ResponseMessage> responseMessageList = new ArrayList<>();
        Arrays.stream(ResponseCode.values()).forEach(errorEnum -> {
            responseMessageList.add(
                    new ResponseMessageBuilder().code(errorEnum.getCode()).message(errorEnum.getMsg()).responseModel(
                            new ModelRef(errorEnum.getMsg())).build()
            );
        });
        return responseMessageList;

    }

    public enum ResponseCode {

        code1(200, "请求成功"),
        code2(201, "请求失败"),
        code3(202, "用户登录过期");

        private Integer code;
        private String msg;

        ResponseCode(Integer code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public Integer getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }
    }

}
