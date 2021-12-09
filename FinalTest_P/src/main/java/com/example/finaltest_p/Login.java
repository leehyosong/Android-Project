package com.example.finaltest_p;

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

    Button Login;
    EditText editId, editPwd;
    String memid, mempwd;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        handler = new Handler();

        Login = (Button)findViewById(R.id.Login);

        editId = (EditText)findViewById(R.id.editId);
        editPwd = (EditText)findViewById(R.id.editPwd);

        Button Login = (Button)findViewById(R.id.Login);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                memid = editId.getText().toString();
                mempwd = editPwd.getText().toString();

                Login(memid, mempwd);
            }
        });

    }
    // 로그인(검색기능)
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