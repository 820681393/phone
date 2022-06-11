package com.core.utils;

import java.io.Serializable;

/**
 * Created by Owner on 2018/9/27.
 */

public class UserToken implements Serializable {

    private String type;//区分各个模块的token
    private int id;
    private String userName;
    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
