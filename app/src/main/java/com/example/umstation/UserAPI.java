package com.example.umstation;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserAPI {
    @POST("/test/doIDstate")
    Call<List<UserData>> userData(@Body UserData body);

}
