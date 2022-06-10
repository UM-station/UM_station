package com.example.umstation;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface CouponAPI {
    @POST("/test/doCoupID")
    Call<List<CouponData>> couponData(@Body CouponData body
    );
}
