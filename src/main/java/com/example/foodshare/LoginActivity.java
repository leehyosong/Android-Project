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

public class LoginActivity extends AppCompatActivity {

    Button btnlogin, btnmanage;
    EditText etmemid, etpasswd;
    String memid, pwd, mangid, mangpwd;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        handler = new Handler();

        btnlogin = (Button)findViewById(R.id.btnlogin);
        btnmanage = (Button)findViewById(R.id.btnmanage);

        etmemid = (EditText)findViewById(R.id.etmemid);
        etpasswd = (EditText)findViewById(R.id.etpasswd);

        Button btnlogin = (Button)findViewById(R.id.btnlogin);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                memid = etmemid.getText().toString();
                pwd = etpasswd.getText().toString();

                Login(memid, pwd);
            }
        });
        Button btnmanage = (Button)findViewById(R.id.btnmanage);
        btnmanage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mangid = etmemid.getText().toString();
                mangpwd = etpasswd.getText().toString();

                ManageLogin(mangid, mangpwd);
            }
        });

    }
    // 로그인
    private void Login(String memid, String pwd){
        new Thread(){
            public void run(){
                try {
                    URL setURL = new URL("Http://10.0.2.2/login.php/");
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

                    while((str=reader.readLine()) != null) {
                        //System.out.println(reader.readLine());
                        builder.append(str);
                    }
                    String resultData = builder.toString();
                    final String Result[] = resultData.split("/");

                    handler.post(new Runnable() {
                        @Override
                        public void run() {

                            if(memid.equals(Result[0]) && pwd.equals(Result[1])) {
                                Toast.makeText(LoginActivity.this, Result[0]+"님 어서오세요.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
                                intent.putExtra("id", memid);
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(LoginActivity.this, "아이디, 비밀번호를 다시한번 확인해주세요.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }catch (Exception e){
                    Log.e("Error", "오류발생", e);
                }
            }
        }.start();
    }
    // 관리자 로그인
    private void ManageLogin(String mangid, String mangpwd){
        new Thread(){
            public void run(){
                try {
                    URL setURL = new URL("Http://10.0.2.2/manageLogin.php/");
                    HttpURLConnection http;
                    http = (HttpURLConnection)setURL.openConnection();
                    http.setDefaultUseCaches(false);
                    http.setDoInput(true);
                    http.setRequestMethod("POST");
                    http.setRequestProperty("content-type", "application/x-www-form-urlencoded");

                    StringBuffer buffer = new StringBuffer();
                    buffer.append("mangid").append("=").append(mangid);
                    OutputStreamWriter outStream = new OutputStreamWriter(http.getOutputStream(), "utf-8");
                    outStream.write(buffer.toString());
                    outStream.flush();

                    InputStreamReader tmp = new InputStreamReader(http.getInputStream(), "utf-8");
                    final BufferedReader reader = new BufferedReader(tmp);
                    StringBuilder builder = new StringBuilder();
                    String str;

                    while((str=reader.readLine()) != null) {
                        //System.out.println(reader.readLine());
                        builder.append(str);
                    }
                    String resultData = builder.toString();
                    final String Result[] = resultData.split("/");

                    handler.post(new Runnable() {
                        @Override
                        public void run() {

                            if(mangid.equals(Result[0]) && mangpwd.equals(Result[1])) {
                                Toast.makeText(LoginActivity.this, "관리자 모드로 접속되었습니다.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), ManagerActivity.class);
                                intent.putExtra("mngid", mangid);
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(LoginActivity.this, "아이디, 비밀번호를 다시한번 확인해주세요.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }catch (Exception e){
                    Log.e("Error", "오류발생", e);
                }
            }
        }.start();
    }
}