package com.example.josephmarrero.cantv.data.api.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by marre on 15/3/2017.
 */

public class LoginBody {
    @SerializedName("id")
    private String userId;
    private String password;

    public LoginBody(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
