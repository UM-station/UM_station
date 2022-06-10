package com.example.umstation;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface StationInfoAPI {

    @GET("/test/doStatAll")
    Call<List<StationInfoData>> getData();
}
