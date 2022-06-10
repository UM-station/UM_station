package com.example.umstation;

import android.app.AlertDialog;
import android.content.Intent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    private Button button_register;
    private Button button_login;
    EditText id, pw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://b6a8-27-117-234-165.jp.ngrok.io")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();

        LoginAPI loginAPI = retrofit.create(LoginAPI.class);



        button_register = findViewById(R.id.button_register);
        button_login = findViewById(R.id.button_login);
        id = findViewById(R.id.et_id);
        pw = findViewById(R.id.et_pw);



        //[회원가입]버튼 클릭 -> 회원가입 화면
        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        //[로그인]버튼 클릭 -> 메인 화면
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String inputid = id.getText().toString();
                String inputpw = pw.getText().toString();

                Check check = new Check(
                        inputid,
                        inputpw
                );

                Call<Check> call = loginAPI.LoginData(check);

                call.enqueue(new Callback<Check>() {
                    @Override
                    public void onResponse(Call<Check> call, Response<Check> response) {
                        if(response.isSuccessful()) {

                            //상태코드
                            Check resource = response.body();
                            System.out.println(resource.response_type());

                            int type = Integer.parseInt(resource.response_type());

                            switch (type) {
                                case 401 : //ID 중복 오류

                                    break;

                                case 402 : //ID 존재하지 않음 오류

                                    break;

                                case 201 : //로그인 성공

                                    break;
                            }

                           showButton1();
                           Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            intent.putExtra("UserID", inputid);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<Check> call, Throwable t) { showButton2();}
                });
            }
        });

        }

    void showButton1() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("UmStation");
        builder.setMessage("로그인 성공!!");
        builder.show();
    }

    void showButton2() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("UmStation");
        builder.setMessage("다시 입력해주세요");
        builder.show();
    }


}



