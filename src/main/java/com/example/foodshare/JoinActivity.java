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

public class JoinActivity extends AppCompatActivity {

    EditText editid, editpwd, editname, editnname, edittelNo, edittown;
    Button btndouble, btncancel, btnjoin;
    String memid, pwd, name, telNo, nickname, town;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join);

        editid = (EditText) findViewById(R.id.editid);
        editpwd = (EditText) findViewById(R.id.editpwd);
        editname = (EditText) findViewById(R.id.editname);
        editnname = (EditText) findViewById(R.id.editnname);
        edittelNo = (EditText) findViewById(R.id.edittelNo);
        edittown = (EditText) findViewById(R.id.edittown);

        btndouble = (Button) findViewById(R.id.btndouble);
        btncancel = (Button) findViewById(R.id.btncancel);
        btnjoin = (Button) findViewById(R.id.btnedit);

        handler = new Handler();

        Button btnjoin = (Button) findViewById(R.id.btnedit);
        btnjoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Join(memid, pwd, name, telNo, nickname, town);

                memid = editid.getText().toString();
                pwd = editpwd.getText().toString();
                name = editname.getText().toString();
                telNo = edittelNo.getText().toString();
                nickname = editnname.getText().toString();
                town = edittown.getText().toString();

                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
        Button btncancel = (Button) findViewById(R.id.btncancel);
        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        Button btndouble = (Button) findViewById(R.id.btndouble);
        btndouble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                memid = editid.getText().toString();

                Check(memid);
            }
        });
    }

    // 회원가입
    private void Join(String memid, String pwd, String name, String telNo, String nickname, String town) {
        new Thread() {
            public void run() {
                try {
                    URL setURL = new URL("Http://10.0.2.2/joinsert.php/");
                    HttpURLConnection http;
                    http = (HttpURLConnection) setURL.openConnection();
                    http.setDefaultUseCaches(false);
                    http.setDoInput(true);
                    http.setRequestMethod("POST");
                    http.setRequestProperty("content-type", "application/x-www-form-urlencoded");

                    StringBuffer buffer = new StringBuffer();
                    buffer.append("memid").append("=").append(memid).append("/").append(pwd).append("/")
                            .append(name).append("/").append(telNo).append("/").append(nickname).append("/").append(town).append("/");
                    OutputStreamWriter outStream = new OutputStreamWriter(http.getOutputStream(), "utf-8");
                    outStream.write(buffer.toString());
                    outStream.flush();

                    InputStreamReader tmp = new InputStreamReader(http.getInputStream(), "utf-8");
                    final BufferedReader reader = new BufferedReader(tmp);
                    StringBuilder builder = new StringBuilder();
                    String str;

                    while ((str = reader.readLine()) != null) {
                        //System.out.println(reader.readLine());
                        builder.append(str);
                    }
                    String resultData = builder.toString();
                    final String Result[] = resultData.split("/");

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "회원가입완료", Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (Exception e) {
                    Log.e("Error", "오류발생", e);
                }
            }
        }.start();

    }

    void Check(String memid) {
        new Thread() {
            public void run() {
                try {
                    URL setURL = new URL("Http://10.0.2.2/idcheck.php/");
                    HttpURLConnection http;
                    http = (HttpURLConnection) setURL.openConnection();
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
                    while ((str = reader.readLine()) != null) {
                        builder.append(str);
                    }
                    String resultData = builder.toString();
                    final String[] Result = resultData.split("/");
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            // memid = editid.getText().toString();
                            if (memid.equals(Result[0])) {
                                Toast.makeText(JoinActivity.this, "아이디 중복", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(JoinActivity.this, "사용 가능한 아이디 입니다.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } catch (Exception e) {
                    Log.e("Error", "오류발생", e);
                }
            }
        }.start();
    }
}
