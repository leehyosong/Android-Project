package com.example.finaltest_p;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.progressindicator.BaseProgressIndicator;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    EditText editId, editPwd, editname, edittelNo, editemail, editaddress;
    String memid, mempwd, name, telNo, email, address;
    Button Join;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editId = (EditText)findViewById(R.id.editId);
        editPwd = (EditText)findViewById(R.id.editPwd);
        editname = (EditText)findViewById(R.id.editname);
        edittelNo = (EditText)findViewById(R.id.edittelNo);
        editemail = (EditText)findViewById(R.id.editemail);
        editaddress = (EditText)findViewById(R.id.editaddress);

        Join = (Button)findViewById(R.id.Join);

        handler = new Handler();

        Button Join = (Button)findViewById(R.id.Join) ;
        Join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                memid = editId.getText().toString();
                mempwd = editPwd.getText().toString();
                name = editname.getText().toString();
                telNo = edittelNo.getText().toString();
                email = editemail.getText().toString();
                address = editaddress.getText().toString();

                dataInsert(memid, mempwd, name, telNo, email, address);
            }
        });

    }

    //회원가입(저장기능)
    private void dataInsert(String memid, String mempwd, String name, String telNo, String email, String address) {
        new Thread(){
            public void run() {
                try {
                    URL setURL = new URL("Http://10.0.2.2/insert.php/");
                    HttpURLConnection http;
                    http = (HttpURLConnection)setURL.openConnection();
                    http.setDefaultUseCaches(false);
                    http.setDoInput(true);
                    http.setRequestMethod("POST");
                    http.setRequestProperty("content-type", "application/x-www-form-urlencoded");

                    StringBuffer buffer = new StringBuffer();
                    buffer.append("memid").append("=").append(memid).append(mempwd).append(name).append("/").append(telNo).append("/").append(email).append(address).append("/");
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
                            Toast.makeText(getApplicationContext(), name + "님 회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), Login.class);
                            startActivity(intent);
                        }
                    });
                }catch (Exception e){
                    Log.e("Error","오류발생",e);
                }
            }
        }.start();
    }

}