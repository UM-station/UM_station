package com.example.umstation;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitAPI {

    @POST("/test/doJOIN")
    Call<Post> postData(@Body Post body

    );
}

//    @Field("user_id") String user_id,
//    @Field("user_name") String user_name,
//    @Field("user_pw") String user_pw,
//    @Field("user_hp") String user_hp,
//    @Field("user_birth") String user_birth
