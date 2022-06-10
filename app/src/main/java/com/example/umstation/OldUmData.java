package com.example.umstation;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface OldUmData {
    @POST("/test/doRE")
    Call<PostOldUm> OldUmData(@Body PostOldUm body

    );
}
