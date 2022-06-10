package com.example.umstation;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostStation {
    @SerializedName("station_num")
    private String station_num;
    @SerializedName("rental_umCode")
    private String rental_umCode ;
    @SerializedName("state")
    private String state;
    @SerializedName("user_id")
    private String user_id;

    public PostStation(String ReadStationNum, String ReadUmNum, String ReadState, String ReadUserID){
        station_num = ReadStationNum;
        rental_umCode = ReadUmNum;
        state = ReadState;
        user_id = ReadUserID;
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
