package com.example.umstation;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ReturnNormalAPI {
    @POST("/test/doRE")
    Call<PostStation> StationData(@Body PostStation body
    );
}
