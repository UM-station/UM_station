package com.example.umstation;

import com.google.gson.annotations.SerializedName;

public class PostUm {
    @SerializedName("rental_umCode")
    private String rental_umCode;

    public PostUm(String ReadUmNum){
        rental_umCode = ReadUmNum;
    }
}
