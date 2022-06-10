package com.example.umstation;

import android.app.DownloadManager;

import java.util.zip.CheckedInputStream;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface LoginAPI {

    @POST("/test/doLOGIN")
    Call<Check> LoginData( @Body Check body
    );
}
