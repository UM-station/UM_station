package com.example.umstation;

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
}
