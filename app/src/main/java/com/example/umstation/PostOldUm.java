package com.example.umstation;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostOldUm {
    @SerializedName("station_num")
    private String station_num;
    @SerializedName("state")
    private String state;
    @SerializedName("user_id")
    private String user_id;

    public PostOldUm(String ReadStationNum, String ReadState, String ReadUserID){
        station_num = ReadStationNum;
        state = ReadState;
        user_id = ReadUserID;
    }

    //아이디에 따른 상태 리턴
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
