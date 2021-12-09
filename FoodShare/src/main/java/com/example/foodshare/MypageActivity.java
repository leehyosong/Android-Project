package com.example.foodshare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MypageActivity extends AppCompatActivity {

    Button btnmodify, btnlogout, btnout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage);

        btnmodify = (Button)findViewById(R.id.btnmodify);
        btnlogout = (Button)findViewById(R.id.btnlogout);
        btnout = (Button)findViewById(R.id.btnout);

        Button btnmodify = (Button)findViewById(R.id.btnmodify);
        btnmodify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MemupdateActivity.class);
                startActivity(intent);
            }
        });

        Button btnout = (Button)findViewById(R.id.btnout);
        btnout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), WithdrawalActivity.class);
                startActivity(intent);
            }
        });

    }
}
