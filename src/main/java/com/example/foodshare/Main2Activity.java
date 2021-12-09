package com.example.foodshare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Main2Activity extends AppCompatActivity {

    Button btnshare, btnboard, btnmypage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        btnshare = (Button)findViewById(R.id.btnshare);
        btnboard = (Button)findViewById(R.id.btnboard);
        btnmypage = (Button)findViewById(R.id.btnmypage);

        Button btnmypage = (Button)findViewById(R.id.btnmypage);
        btnmypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MypageActivity.class);
                startActivity(intent);
            }
        });

    }
}
