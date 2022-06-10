package com.example.umstation;

import com.google.gson.annotations.SerializedName;

public class Check {
    @SerializedName("user_id")
    private String user_id;
    @SerializedName("user_pw")
    private String user_pw;

    public Check(String inputid, String inputpw) {
        user_id = inputid;
        user_pw = inputpw;
    }
}
