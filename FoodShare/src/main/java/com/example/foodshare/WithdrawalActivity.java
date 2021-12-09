package com.example.foodshare;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class WithdrawalActivity extends AppCompatActivity {

    Button btnwithdrawal;
    EditText etmemid;
    String memid;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.withdrawal);

        btnwithdrawal = (Button)findViewById(R.id.btnwithdrawal);
        etmemid = (EditText)findViewById(R.id.etmemid);

        handler = new Handler();

        Button btnwithdrawal = (Button)findViewById(R.id.btnwithdrawal);
        btnwithdrawal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                memid = etmemid.getText().toString();
                if(!memid.equals(etmemid)){
                    Toast.makeText(WithdrawalActivity.this, "다름", Toast.LENGTH_SHORT).show();
                }else{
                    dataDelete();
                }
            }
        });
    }
    public void dataDelete() {
        new Thread(){
            public void run() {
                try {
                    String memid = etmemid.getText().toString();

                    URL setURL = new URL("Http://10.0.2.2/withdrawal.php/");
                    HttpURLConnection http;
                    http = (HttpURLConnection)setURL.openConnection();
                    http.setDefaultUseCaches(false);
                    http.setDoInput(true);
                    http.setRequestMethod("POST");
                    http.setRequestProperty("content-type", "application/x-www-form-urlencoded");

                    StringBuffer buffer = new StringBuffer();
                    buffer.append("memid").append("=").append(memid);

                    OutputStreamWriter outStream = new OutputStreamWriter(http.getOutputStream(), "utf-8");
                    outStream.write(buffer.toString());
                    outStream.flush();
                    InputStreamReader tmp = new InputStreamReader(http.getInputStream(), "utf-8");
                    final BufferedReader reader = new BufferedReader(tmp);
                    StringBuilder builder = new StringBuilder();
                    String str;

                    while((str = reader.readLine()) != null) {
                        builder.append(str + "\n");
                    }
                    String resultData = builder.toString();
                    final String[] Result = resultData.split("/");

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(WithdrawalActivity.this, "탈퇴가 완료되었습니다.", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                        }
                    });
                }catch (Exception e){
                    Log.e("", "Error", e);
                }
            }
        }.start();
    }
}
