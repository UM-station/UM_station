package com.example.umstation;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class CouponData {

    @SerializedName("user_id")
    private String user_id;
    public CouponData(String UserID) {
        user_id = UserID;
    }


    //GET 위한 선언
    @SerializedName("coupon_num")
    private String coupon_num;

    @SerializedName("coupon_price")
    private int coupon_price;

    @SerializedName("coupon_date")
    private String coupon_date;

    public void coupondata (String coupon_num, int coupon_price, String coupon_date) {
        this.coupon_num = coupon_num;
        this.coupon_price = coupon_price;
        this.coupon_date = coupon_date;
    }


    public String coupon_num() { return coupon_num; }

    public int coupon_price() { return coupon_price; }

    public String coupon_date() { return coupon_date; }
}
