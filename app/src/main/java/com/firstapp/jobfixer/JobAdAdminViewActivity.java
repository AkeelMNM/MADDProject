package com.firstapp.jobfixer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firstapp.jobfixer.Database.DBMaster;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class JobAdAdminViewActivity extends AppCompatActivity {



    TextView txtTit,txtJobComp,txtComLoc,txtJobQua,txtJobSal,txtJobType;
    Button updateBtn,DeleteBtn;
    String ID = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_ad_admin_view);

        txtTit = findViewById(R.id.textViewJobTit);
        txtJobComp = findViewById(R.id.textCompany);
        txtComLoc = findViewById(R.id.textViewCL);
        txtJobQua = findViewById(R.id.textQualifuc);
        txtJobSal = findViewById(R.id.textViewJS);
        txtJobType = findViewById(R.id.textViewJT);
        updateBtn = findViewById(R.id.btnView);
        DeleteBtn = findViewById(R.id.btnApply);

        DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child(DBMaster.Advertisement.TABLE_NAME).child("Ad1");
        readRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.hasChildren()) {
                    txtComLoc.setText(dataSnapshot.child(DBMaster.Advertisement.COLUMN_NAME_COMPANY_ADDRESS).getValue().toString());
                    txtJobComp.setText(dataSnapshot.child(DBMaster.Advertisement.COLUMN_NAME_COMPANY_NAME).getValue().toString());
                    txtJobSal.setText(dataSnapshot.child(DBMaster.Advertisement.COLUMN_NAME_JOB_SALARY).getValue().toString());
                    txtTit.setText(dataSnapshot.child(DBMaster.Advertisement.COLUMN_NAME_JOB_TITLE).getValue().toString());
                    txtJobType.setText(dataSnapshot.child(DBMaster.Advertisement.COLUMN_NAME_JOB_TYPE).getValue().toString());
                    txtJobQua.setText(dataSnapshot.child(DBMaster.Advertisement.COLUMN_NAME_JOB_QUALIFICATION).getValue().toString());
                    ID = dataSnapshot.child(DBMaster.Advertisement._ID).getValue().toString();
                } else {
                    Toast.makeText(getApplicationContext(), "No Source to Display", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(JobAdAdminViewActivity.this, JobAdUpdateActivity.class);
                intent.putExtra("KEY", ID);
                startActivity(intent);
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

}