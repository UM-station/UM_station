package com.example.umstation;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StationInfoData {

    @Expose
    @SerializedName("station_num")
    private String station_num;

    @Expose
    @SerializedName("station_latitude")
    private double station_latitude;

    @Expose
    @SerializedName("station_longitude")
    private double station_longitude;

    @Expose
    @SerializedName("station_haveUm")
    private int station_haveUm;


    public String station_num() { return station_num; }

    public Double station_latitude() { return station_latitude; }

    public Double station_longitude() { return station_longitude; }

    public Integer station_haveUm() { return station_haveUm; }
}
