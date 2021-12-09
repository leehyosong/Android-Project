package com.example.foodshare;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MemupdateActivity extends AppCompatActivity {

    Button btncancel, btnedit;
    EditText editpwd, editname, edittelNo, editnname, edittown;
    String memid, pwd, name, telNo, nickname, town;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memupdate);

        btncancel = (Button)findViewById(R.id.btncancel);
        btnedit = (Button)findViewById(R.id.btnedit);

        editpwd = (EditText)findViewById(R.id.editpwd);
        editname = (EditText)findViewById(R.id.editname);
        edittelNo = (EditText)findViewById(R.id.edittelNo);
        editnname = (EditText)findViewById(R.id.editnname);
        edittown = (EditText)findViewById(R.id.edittown);

        handler = new Handler();

        

    }
}
