package com.example.umstation;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private Button button_stationQR;  //스테이션 QR 인식버튼
    private Button button_map;  //지도
    private Button button_point;

    //날씨
    TextView cityView;
    TextView weatherView;
    TextView tempView;
    ImageView imgWeather;

    //대여상태 확인
    TextView text_UserName;
    TextView text_state;
    TextView text_rentaldate;

    String name;
    String rental;

    static RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            String UserID = getIntent().getStringExtra("UserID");


            //[Station QR]버튼 클릭 -> QR 인식 화면
            button_stationQR = findViewById(R.id.button_stationQR);
            button_stationQR.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, StationQRActivity2.class);
                    intent.putExtra("UserID", UserID);
                    startActivity(intent);
                }
            });

            //[우산 Station찾기] 버튼 클릭 -> 지도 화면
            button_map = findViewById(R.id.button_FindStation);
            button_map.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, StationLocationActivity.class);
                    startActivity(intent);
                }
            });

            //[POINT 전환] 버튼 클릭 -> 포인트 화면
            button_point = findViewById(R.id.button_point);
            button_point.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, PointActivity.class);
                    intent.putExtra("UserID", UserID);
                    startActivity(intent);
                }
            });

            //[오늘의 날씨] 버튼 -> 현재 날씨로 변경
            cityView = findViewById(R.id.cityView);
            weatherView = findViewById(R.id.weatherView);
            tempView = findViewById(R.id.tempView);
            imgWeather = (ImageView) findViewById(R.id.imgWeather);
            Button button = findViewById(R.id.button);
            button.setOnClickListener(v -> {
                CurrentCall();
            });

            //volley를 쓸 때 큐가 비어있으면 새로운 큐 생성하기
            if (requestQueue == null) {
                requestQueue = Volley.newRequestQueue(getApplicationContext());
            }

            //레트로핏 객체 생성
            Retrofit.Builder builder = new Retrofit.Builder()
                    .baseUrl("https://b6a8-27-117-234-165.jp.ngrok.io")
                    .addConverterFactory(GsonConverterFactory.create());
            Retrofit retrofit = builder.build();
            UserAPI UserAPI = retrofit.create(UserAPI.class);
            String ReadUserID = UserID;
            UserData usersPost = new UserData(
                    ReadUserID
            );
            Call<List<UserData>> call = UserAPI.usersData(usersPost);
            call.enqueue(new Callback<List<UserData>>() {
                @Override
                public void onResponse(Call<List<UserData>> call, retrofit2.Response<List<UserData>> response) {
                    if (response.isSuccessful()) {

                        List<UserData> resource = response.body();
                        System.out.println("main**************************************");

                        ArrayList<String> arrayUserName = new ArrayList<>();
                        ArrayList<String> arrayUserRental = new ArrayList<>();

                        //각 정보 리스트에 저장
                        for(UserData re : resource){
                            arrayUserName.add(re.user_name());
                            arrayUserRental.add(re.user_rental());
                        }
                        name = arrayUserName.get(0);
                        rental = arrayUserRental.get(0);

                        System.out.println(name);
                        System.out.println(rental);

                        text_UserName = findViewById(R.id.text_UserName);
                        text_rentaldate = findViewById(R.id.text_rentaldate);
                        text_state = findViewById(R.id.text_state);

                        if (rental == "") {
                            text_UserName.setText(name);
                            text_rentaldate.setText("-");
                            text_state.setText("-");
                        } else {
                            text_UserName.setText(name);
                            text_rentaldate.setText(rental);
                            text_state.setText("대여 중");
                        }
                    }
                }

                @Override
                public void onFailure(Call<List<UserData>> call, Throwable t) {
                    System.out.println("main####################################");
                }
            });

    }
    //날씨 정보 불러오기
    private void CurrentCall(){

        //OpenWeather API 사용
        String url = "http://api.openweathermap.org/data/2.5/weather?q=Asan&appid=5dc3fcc91e6c4ff3704c827f0072bd45";

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(String response) {

                try {

                    //api로 받은 파일 jsonobject로 새로운 객체 선언
                    JSONObject jsonObject = new JSONObject(response);

                    //도시 키값 받기
                    String city = jsonObject.getString("name");
                    cityView.setText(city);

                    //날씨 키값 받기
                    JSONArray weatherJson = jsonObject.getJSONArray("weather");
                    JSONObject weatherObj = weatherJson.getJSONObject(0);

                    String weather = weatherObj.getString("description");

                    weatherView.setText(weather);
                    switch (weather) {
                        case "few clouds" :
                        case "overcast clouds" :
                            weatherView.setText("구름 많음");
                            imgWeather.setImageResource(R.drawable.few_clouds); //날씨에 맞는 이미지 가져옴
                            break;

                        case "broken clouds" :
                            weatherView.setText("흐림");
                            imgWeather.setImageResource(R.drawable.broken_clouds);

                        case "shower rain" :
                        case "light rain" :
                        case "moderate rain" :
                        case "Rain" :
                            weatherView.setText("비");
                            imgWeather.setImageResource(R.drawable.rain);
                            break;

                        case "snow" :
                            weatherView.setText("눈");
                            imgWeather.setImageResource(R.drawable.snow);

                        case "snow_rain" :
                            weatherView.setText("눈비");
                            imgWeather.setImageResource(R.drawable.snow_rain);

                        case "clear sky" :
                            weatherView.setText("맑음");
                            imgWeather.setImageResource(R.drawable.sunny);
                    }

                    //기온 키값 받기
                    JSONObject tempK = new JSONObject(jsonObject.getString("main"));

                    //기온 받고 켈빈 온도를 섭씨 온도로 변경
                    double tempDo = (Math.round((tempK.getDouble("temp")-273.15)*100)/100.0);
                    tempView.setText(tempDo +  "°C");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                return params;
            }

        };

        request.setShouldCache(false);
        requestQueue.add(request);
    }


}