package com.example.umstation;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RentAPI {
    @POST("/test/doRENT")
    Call<PostStation> StationData(@Body PostStation body

    );
}
