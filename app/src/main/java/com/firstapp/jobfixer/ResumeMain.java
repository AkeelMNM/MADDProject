package com.firstapp.jobfixer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;

public class ResumeMain extends AppCompatActivity {
    Button mcreateButton,mViewButton ,btnBHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume_main);
        mcreateButton = findViewById(R.id.btnCreateRes);
        mViewButton = findViewById(R.id.btnViewResM);
        btnBHome = findViewById(R.id.btnBHome);

        mViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResumeMain.this,ViewResume.class);
                startActivity(intent);

            }
        });
        mcreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResumeMain.this,CreateResume.class);
                startActivity(intent);
            }
        });
        btnBHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResumeMain.this,MainActivity.class);
            }
        });



    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }
}