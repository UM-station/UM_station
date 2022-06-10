package com.example.umstation;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.TextView;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import org.json.JSONException;
import org.json.JSONObject;

public class StationQRActivity2 extends AppCompatActivity {
    private IntentIntegrator qrScan;
    private TextView text_StationName = null, text_StationLocation = null;
    private Button cameraButton;
    private Button ChoiceMenu;
    int flag = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_qractivity2);
        String UserID = getIntent().getStringExtra("UserID");

        text_StationName = (TextView) findViewById(R.id.StationName);
        text_StationLocation = (TextView) findViewById(R.id.StationLocation);
        qrScan = new IntentIntegrator(this);

        cameraButton = findViewById(R.id.QRCamera);
        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qrScan.setOrientationLocked(false);
                qrScan.setPrompt("Station의 QR을 인식하세요!");
                qrScan.initiateScan();
            }
        });

        //qr인식 후 station이용하러 가기 선택 시 station번호 정보가 server로 이동 후 화면 전환
        ChoiceMenu = findViewById(R.id.button_ChoiceMenu);
        ChoiceMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag == 1){
                    String ReadStationNum = text_StationName.getText().toString();
                    Intent intent = new Intent(StationQRActivity2.this, ChoiceMenuActivity.class);
                    intent.putExtra("ReadStationNum", ReadStationNum);
                    intent.putExtra("UserID", UserID);
                    startActivity(intent);
                }else{
                    showButton3();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        //QR코드 인식 성공 시
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
                try {
                    //data를 json으로 변환
                    JSONObject obj = new JSONObject(result.getContents());
                    text_StationName.setText(obj.getString("name"));
                    text_StationLocation.setText(obj.getString("address"));
                    flag = 1;

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    void showButton3() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("UmStation");
        builder.setMessage("스테이션의 QR을 인식해주세요");
        builder.show();
    }

}