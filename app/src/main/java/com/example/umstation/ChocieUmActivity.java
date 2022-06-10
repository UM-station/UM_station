package com.example.umstation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChocieUmActivity extends AppCompatActivity {
    private Button button_Normal;
    private Button button_Wrong;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chocie_um);

        String StationQR = getIntent().getStringExtra("StationQR");
        String State = getIntent().getStringExtra("ChoiceMenu");
        String UserID = getIntent().getStringExtra("UserID");
        button_Normal = findViewById(R.id.NormalUm);
        button_Normal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //정상적인 우산 정보
                Intent intent = new Intent(ChocieUmActivity.this, ReturnNormalQRActivity.class);
                intent.putExtra("StationNum", StationQR);
                intent.putExtra("State", "normalReturn");
                intent.putExtra("UserID", UserID);
                startActivity(intent);
            }
        });

        button_Wrong = findViewById(R.id.WrongUm);
        button_Wrong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChocieUmActivity.this, ReturnWorngQRActivity.class);
                intent.putExtra("StationNum", StationQR);
                intent.putExtra("State", "wrongReturn");
                intent.putExtra("UserID", UserID);
                startActivity(intent);
            }
        });

    }

}