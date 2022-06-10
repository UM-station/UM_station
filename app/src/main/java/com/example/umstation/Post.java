package com.example.umstation;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Post {
    @SerializedName("user_name")
    private String user_name;
    @SerializedName("user_id")
    private String user_id;
    @SerializedName("user_pw")
    private String user_pw;
    @SerializedName("user_hp")
    private String user_hp;
    @SerializedName("user_birth")
    private String user_birth;

    public Post(String inputName, String inputid, String inputpw,String inputPhone, String inputBirth) {
        user_name = inputName;
        user_id = inputid;
        user_pw = inputpw;
        user_hp = inputPhone;
        user_birth = inputBirth;

    }

    //상태 리턴
    @Expose
    @SerializedName("response_type")
    private String response_type;

    @Expose
    @SerializedName("response_msg")
    private String response_msg;

    @Expose
    @SerializedName("response_value")
    private String response_value;


    public String response_type() { return response_type; }

    public String response_msg() { return response_msg; }

    public String response_value() { return response_value; }
}
