package com.example.testp;

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


public class MainActivity extends AppCompatActivity {

    EditText etName, etTelNo, etEmail;
    String name, telNo, email;
    Button btnSave, btnClear, btnSearch, btnModify;
    Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = (EditText) findViewById(R.id.etName);
        etTelNo = (EditText) findViewById(R.id.etTelNo);
        etEmail = (EditText) findViewById(R.id.etEmail);

        btnSave = (Button) findViewById(R.id.btnSave);
        btnClear = (Button) findViewById(R.id.btnClear);
        btnSearch = (Button) findViewById(R.id.btnSearch);
        btnModify = (Button) findViewById(R.id.btnModify);

        handler = new Handler();

        Button btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name = etName.getText().toString();
                telNo = etTelNo.getText().toString();
                email = etEmail.getText().toString();

                dataInsert(name, telNo, email);
            }
        });

        Button btnSearch = (Button) findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataSearch();
            }
        });

        Button btnModify = (Button) findViewById(R.id.btnModify);
        btnModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = etName.getText().toString();
                telNo = etTelNo.getText().toString();
                email = etEmail.getText().toString();

                dataUpdate(name, telNo, email);
            }
        });

        Button btnClear = (Button) findViewById(R.id.btnClear);
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataClear();
            }
        });
    }

    // 저장기능
    private void dataInsert(String name, String telNo, String email) {
        new Thread(){
            public void run() {
                try {
                    URL setURL = new URL("Http://10.0.2.2/insert02.php/");
                    HttpURLConnection http;
                    http = (HttpURLConnection)setURL.openConnection();
                    http.setDefaultUseCaches(false);
                    http.setDoInput(true);
                    http.setRequestMethod("POST");
                    http.setRequestProperty("content-type", "application/x-www-form-urlencoded");

                    StringBuffer buffer = new StringBuffer();
                    buffer.append("name").append("=").append(name).append("/").append(telNo).append("/").append(email).append("/");
                    OutputStreamWriter outStream = new OutputStreamWriter(http.getOutputStream(), "utf-8");
                    outStream.write(buffer.toString());
                    outStream.flush();

                    InputStreamReader tmp = new InputStreamReader(http.getInputStream(), "utf-8");
                    final BufferedReader reader = new BufferedReader(tmp);
                    StringBuilder builder = new StringBuilder();
                    String str = "";
                    while(reader.readLine() != null) {
                        //System.out.println(reader.readLine());
                        builder.append(str + "\n");
                    }
                    String resultData = builder.toString();
                    final String Result[] = resultData.split("/");

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), name + "님의 정보를 저장했습니다.", Toast.LENGTH_SHORT).show();
                        }
                    });
                }catch (Exception e){
                    Log.e("Error","오류발생",e);
                }
            }
        }.start();
    }

    // 검색기능
    public void dataSearch() {
        new Thread(){
            public void run() {
                try {
                    String name = etName.getText().toString();

                    URL setURL = new URL("Http://10.0.2.2/selectSearch.php/");
                    HttpURLConnection http;
                    http = (HttpURLConnection)setURL.openConnection();
                    http.setDefaultUseCaches(false);
                    http.setDoInput(true);
                    http.setRequestMethod("POST");
                    http.setRequestProperty("content-type", "application/x-www-form-urlencoded");

                    StringBuffer buffer = new StringBuffer();
                    buffer.append("name").append("=").append(name);

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
                            Toast.makeText(MainActivity.this, Result[0] + "\n" + Result[1] + "\n" + Result[2], Toast.LENGTH_SHORT).show();
                            etName.setText(Result[0]);
                            etTelNo.setText(Result[1]);
                            etEmail.setText(Result[2]);
                        }
                    });
                    etName.setText(str);

                }catch (Exception e){
                    Log.e("", "Error", e);
                }
            }
        }.start();
    }

    // 수정기능
    public void dataUpdate(String name, String telNo, String email) {
        new Thread(){
            public void run() {
                try {
                    URL setURL = new URL("Http://10.0.2.2/update.php/");
                    HttpURLConnection http;
                    http = (HttpURLConnection)setURL.openConnection();
                    http.setDefaultUseCaches(false);
                    http.setDoInput(true);
                    http.setRequestMethod("POST");
                    http.setRequestProperty("content-type", "application/x-www-form-urlencoded");

                    StringBuffer buffer = new StringBuffer();
                    buffer.append("name").append("=").append(name).append("/").append(telNo).append("/").append(email);

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
                    final String Result[] = resultData.split("/");

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "수정 성공", Toast.LENGTH_SHORT).show();
                        }
                    });
                }catch (Exception e){
                    Log.e("", "Error", e);
                }
            }
        }.start();
    }

    // 삭제기능
    public void dataClear() {
        new Thread(){
            public void run() {
                try {
                    final String name = etName.getText().toString();

                    URL setURL = new URL("Http://10.0.2.2/delete.php/");
                    HttpURLConnection http;
                    http = (HttpURLConnection)setURL.openConnection();
                    http.setDefaultUseCaches(false);
                    http.setDoInput(true);
                    http.setRequestMethod("POST");
                    http.setRequestProperty("content-type", "application/x-www-form-urlencoded");

                    StringBuffer buffer = new StringBuffer();
                    buffer.append("name").append("=").append(name);

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
                            Toast.makeText(MainActivity.this, name + "님의 정보가 삭제되었습니다.", Toast.LENGTH_SHORT).show();
                        }
                    });
                }catch (Exception e){
                    Log.e("", "Error", e);
                }
            }
        }.start();
    }

}
