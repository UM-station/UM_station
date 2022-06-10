package com.example.umstation;

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

}
