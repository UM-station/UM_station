package com.example.umstation;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ReturnWrongAPI {
    @POST("/test/doRE")
    Call<PostStation> StationData(@Body PostStation body

    );
}
