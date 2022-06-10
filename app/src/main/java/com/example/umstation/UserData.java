package com.example.umstation;

import com.google.gson.annotations.SerializedName;


public class UserData {
    @SerializedName("user_id")
    private String user_id;
    public UserData(String UserID) {
        user_id = UserID;
    }


    //GET위한 선언
    @SerializedName("user_name")
    private String user_name;

    @SerializedName("user_pw")
    private String user_pw;

    @SerializedName("user_hp")
    private String user_hp; //폰 번호

    @SerializedName("user_birth")
    private String user_birth;

    @SerializedName("user_umCode")
    private String user_umCode;

    @SerializedName("user_rental")
    private String user_rental;

    @SerializedName("user_ap")
    private int user_ap; //누적 포인트

    @SerializedName("user_lp")
    private int user_lp; //잔여 포인트

    public void userData (String user_name, String user_pw, String user_hp, String user_birth, String user_umCode,
                          String user_rental, int user_ap, int user_lp) {
        this.user_name = user_name;
        this.user_pw = user_pw;
        this.user_hp = user_hp;
        this.user_birth = user_birth;
        this.user_umCode = user_umCode;
        this.user_rental = user_rental;
        this.user_ap = user_ap;
        this.user_lp = user_lp;
    }

    public String user_name() { return user_name; }

    public String user_pw() { return user_pw; }

    public String user_hp() { return user_hp; }

    public String user_birth() { return user_birth; }

    public String user_umCode() { return user_umCode; }

    public String user_rental() { return user_rental; }

    public int user_ap() { return user_ap; }

    public int user_lp() { return user_lp; }

}
