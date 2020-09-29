package com.firstapp.jobfixer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class AdminReplyActivity extends AppCompatActivity {

    TextView display2,display3;
    EditText replyname,replymsg,replyemail;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_reply);


        display2=findViewById(R.id.displayname);
        display3=findViewById(R.id.displaymessage);
        replyname=findViewById(R.id.AdminSendTo);
        replymsg=findViewById(R.id.AdminSendMsg);
        replyemail=findViewById(R.id.editTextTextPersonName5);


        Bundle bundle =getIntent().getExtras();
        display2.setText(bundle.getString("name"));
        display3.setText(bundle.getString("msg"));

        replyname.setText(bundle.getString("name"));
        replyemail.setText(bundle.getString("email"));

    }




    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }
}