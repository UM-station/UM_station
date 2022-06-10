package com.example.umstation;

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
}
