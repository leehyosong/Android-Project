package com.example.mvoca_p;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toolbar;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private ImageView ivMenu;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivMenu=findViewById(R.id.iv_menu);
        drawerLayout=findViewById(R.id.drawer);
        toolbar=findViewById(R.id.toolbar);


        setSupportActionBar(toolbar);

        ivMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClik : 클릭됨");
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });


        ListView list;

        list = (ListView) findViewById(R.id.lvMusic);
        ListViewAdapter adapter = new ListViewAdapter();
        list.setAdapter(adapter);

        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.anne_marie), "2002", "Anne-Marie");
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.justinbieber), "Peaches", "Justin Bieber");
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.coldplaybts), "My Universe", "Coldplay&BTS");
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.badhabits), "Bad Habits", "Ed Sheeran");
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.adele), "Easy On Me", "Adele");
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.samryder), "Tiny Riot", "Sam Ryder");

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListViewItem item = (ListViewItem) parent.getItemAtPosition(position);

                Intent intent = new Intent(getApplicationContext(), Music.class);
                startActivity(intent);

                String title = item.getTitle();
                String singer = item.getSinger();
                Drawable icon = item.getIcon();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu) ;

        return true ;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.m_search :
                Intent intent = new Intent(getApplicationContext(), Search.class);
                //startActivity(intent);
        }
        return false;
    }


    private void setSupportActionBar(Toolbar toolbar) {
    }

}