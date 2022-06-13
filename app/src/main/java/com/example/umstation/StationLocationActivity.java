package com.example.umstation;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.umstation.databinding.ActivityStationLocationBinding;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;

import android.content.DialogInterface;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class StationLocationActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityStationLocationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityStationLocationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        //레트로핏 객체
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://912e-203-230-13-2.jp.ngrok.io")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        StationInfoAPI stationInfoAPI = retrofit.create(StationInfoAPI.class);

        Call<List<StationInfoData>> call = stationInfoAPI.getData();
        call.enqueue(new Callback<List<StationInfoData>>() {

            //통신 성공 시 지도에 마커 표시
            @Override
            public void onResponse(Call<List<StationInfoData>> call, Response<List<StationInfoData>> response) {
                Intent intent = getIntent();
                double latitude = intent.getDoubleExtra("latitude", 0);
                double longitude = intent.getDoubleExtra("longitude", 0); //Intent 를 통해 위도 경도 받아옴

                mMap = googleMap;

                //우산 스테이션 아이콘 불러오기
                BitmapDrawable bitmapDrawable2 = (BitmapDrawable) getResources().getDrawable(R.drawable.marker);
                Bitmap b = bitmapDrawable2.getBitmap();
                Bitmap smallMaker = Bitmap.createScaledBitmap(b, 100, 100, false);

                MarkerOptions ummarker = new MarkerOptions(); //우산 마커

                List<StationInfoData> resource = response.body();

                //리스트 생성
                ArrayList<String> arrayStName = new ArrayList<>();
                ArrayList<Double> arrayStLat = new ArrayList<>();
                ArrayList<Double> arrayStLon = new ArrayList<>();
                ArrayList<Integer> arrayStUM = new ArrayList<>();

                //각 정보 리스트에 저장
                for(StationInfoData re : resource){
                    arrayStName.add(re.station_num());
                    arrayStLat.add(re.station_latitude());
                    arrayStLon.add(re.station_longitude());
                    arrayStUM.add(re.station_haveUm());
                }

                System.out.println(arrayStName.get(1));
                System.out.println(arrayStLat.get(1));
                System.out.println(arrayStLon.get(1));
                System.out.println(arrayStUM.get(1));

                //리스트 크기 측정을 위한 기준 생성
                ArrayList arrayList = new ArrayList<>();
                arrayList.addAll(arrayStName);

                for(int i = 0; i < arrayList.size(); i++) {

                    //리스트 개수만큼 반복문 돌려 마커 생성
                    String stationName = arrayStName.get(i);
                    double lat = arrayStLat.get(i);
                    double lon = arrayStLon.get(i);
                    int num = arrayStUM.get(i);

                    LatLng stationPoint = new LatLng(lat, lon);
                    String UMnum = String.valueOf(num); //int -> String 변환

                    ummarker.position(stationPoint); //스테이션 위치
                    ummarker.title(stationName); //장소 명
                    ummarker.snippet("대여 우산 개수 :" + UMnum + "개"); //설명 (대여 우산 개수)
                    ummarker.icon(BitmapDescriptorFactory.fromBitmap(smallMaker));
                    mMap.addMarker(ummarker); //마커 찍기
                }
                LatLng sunmoonUniv = new LatLng(36.80104098469980, 127.07573698072177);
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sunmoonUniv, 17));
            }

            //통신 실패 시
            @Override
            public void onFailure(Call<List<StationInfoData>> call, Throwable t) {

            }
        });

        //내 위치 불러오기
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            googleMap.setMyLocationEnabled(true);
        } else {
            checkLocationPermissionWithRationale();
        }
    }
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    private void checkLocationPermissionWithRationale() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                new AlertDialog.Builder(this)
                        .setTitle("위치정보")
                        .setMessage("이 앱을 사용하기 위해서는 위치정보에 접근이 필요합니다. 위치정보 접근을 허용하여 주세요.")
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ActivityCompat.requestPermissions(StationLocationActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_LOCATION);
                            }
                        }).create().show();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_LOCATION);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        mMap.setMyLocationEnabled(true);
                    }
                } else {
                    Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }
}