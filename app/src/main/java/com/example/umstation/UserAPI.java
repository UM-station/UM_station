package com.example.umstation;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserAPI {
    @POST("/test/doIDstate")
    Call<List<UserData>> usersData(@Body UserData body
    );

//    //사용자명
//    @GET("/test/doIDstate/{user_name}")
//    Call<UserData> getData(@Query("user_name") String user_name, @Query("user_rental") String user_rental);
}
