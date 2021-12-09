package com.example.mvoca_p;

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

public class Login extends AppCompatActivity {

    EditText editId, editPwd;
    Button MLogin, MNLogin;
    String memid, mempwd, mngid, mngpwd;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        handler = new Handler();

        MLogin = (Button)findViewById(R.id.MLogin);
        MNLogin = (Button)findViewById(R.id.MNLogin);

        editId = (EditText)findViewById(R.id.editId);
        editPwd = (EditText)findViewById(R.id.editPwd);

        Button MLogin = (Button)findViewById(R.id.MNLogin);
        MLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                memid = editId.getText().toString();
                mempwd = editPwd.getText().toString();

                Login(memid, mempwd);
            }
        });

        Button MNLogin = (Button)findViewById(R.id.MNLogin);
        MNLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mngid = editId.getText().toString();
                mngpwd = editPwd.getText().toString();

                MNLogin(mngid, mngpwd);
            }
        });
    }

    // 회원로그인
    private void Login(String memid, String mempwd){
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

                            if(memid.equals(Result[0]) && mempwd.equals(Result[1])) {
                                Toast.makeText(Login.this, Result[0]+"님 어서오세요.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);

                            }
                            else{
                                Toast.makeText(Login.this, "아이디, 비밀번호를 다시한번 확인해주세요.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }catch (Exception e){
                    Log.e("Error", "오류발생", e);
                }
            }
        }.start();
    }

    // 관리자로그인
    private void MNLogin(String mngid, String mngpwd){
        new Thread(){
            public void run(){
                try {
                    URL setURL = new URL("Http://10.0.2.2/mnglogin.php/");
                    HttpURLConnection http;
                    http = (HttpURLConnection)setURL.openConnection();
                    http.setDefaultUseCaches(false);
                    http.setDoInput(true);
                    http.setRequestMethod("POST");
                    http.setRequestProperty("content-type", "application/x-www-form-urlencoded");

                    StringBuffer buffer = new StringBuffer();
                    buffer.append("mngid").append("=").append(mngid);
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

                            if(mngid.equals(Result[0]) && mngpwd.equals(Result[1])) {
                                Toast.makeText(Login.this, "관리자 계정으로 접속되었습니다.", Toast.LENGTH_SHORT).show();

                            }
                            else{
                                Toast.makeText(Login.this, "아이디, 비밀번호를 다시한번 확인해주세요.", Toast.LENGTH_SHORT).show();
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
