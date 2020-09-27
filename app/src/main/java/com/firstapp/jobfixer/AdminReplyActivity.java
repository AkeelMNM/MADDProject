package com.firstapp.jobfixer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class AdminReplyActivity extends AppCompatActivity {

    TextView display2,display3;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_reply);


        display2=findViewById(R.id.displayname);
        display3=findViewById(R.id.displaymessage);


        Bundle bundle =getIntent().getExtras();
        display2.setText(bundle.getString("name"));
        display3.setText(bundle.getString("msg"));

    }




    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }
}