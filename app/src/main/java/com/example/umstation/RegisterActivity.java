package com.example.umstation;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity {
    private Button button_registerStart;
    EditText name, id, pw, pwCheck, phone, birth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //레트로핏 객체 생성
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://b6a8-27-117-234-165.jp.ngrok.io")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

        //각 EditText 매치
        button_registerStart = findViewById(R.id.button_registerStart);
        name = findViewById(R.id.et_Rname);
        id = findViewById(R.id.et_Rid);
        pw = findViewById(R.id.et_Rpw);
        pwCheck = findViewById(R.id.et_RpwCheck);
        phone = findViewById(R.id.et_Rphone);
        birth = findViewById(R.id.et_RbirthDate);




        button_registerStart.setOnClickListener(v -> {

            String inputName = name.getText().toString();
            String inputId = id.getText().toString();
            String inputPw = pw.getText().toString();
            String inputPwCheck = pwCheck.getText().toString();
            String inputPhone = phone.getText().toString();
            String inputBirth = birth.getText().toString();

            Post post = new Post(
                    inputName,
                    inputId,
                    inputPw,
                    inputPhone,
                    inputBirth
            );

            if (inputPw.equals(inputPwCheck)) {
                Call<Post> call = retrofitAPI.postData(post);

                call.enqueue(new Callback<Post>() {
                    @Override
                    public void onResponse(Call<Post> call, Response<Post> response) {
                        if(response.isSuccessful()){

                            //상태코드
                            Post resource = response.body();
                            int type = Integer.parseInt(resource.response_type());
                            if (type == 400) { //ID 중복 오류

                            }

                            showButtonAlertDialog2();
                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }
                    }
                    @Override
                    public void onFailure(Call<Post> call, Throwable t) { }
                });


            } else {
                showButtonAlertDialog1();
            }

        });

    }

    void showButtonAlertDialog1() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("UmStation");
        builder.setMessage("(비밀번호 불일치)비밀번호를 다시 입력해주세요");
        builder.show();
    }

    void showButtonAlertDialog2() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("UmStation");
        builder.setMessage("회원가입이 성공적으로 완료되었습니다.");
        builder.show();
    }
}

