package com.firstapp.jobfixer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.firstapp.jobfixer.Database.DBMaster;
import com.firstapp.jobfixer.Model.Jobs;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CompanyAddJobActivity extends AppCompatActivity {

    EditText t1,t2,t3,t4;
    Button AddJobBtn;
    Button CanBtn;
    DatabaseReference DBRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_add_job);

        Spinner sp=(Spinner) findViewById(R.id.AddJobCatogorySpinner);
        Spinner sp1=(Spinner) findViewById(R.id.AddJobTitleSpinner);
        Spinner sp2=(Spinner) findViewById(R.id.AddJobTypeSpinner);

        t1=findViewById(R.id.AddComName);
        t2=findViewById(R.id.AddCompanyAddress);
        t3=findViewById(R.id.AddJobSalary);
        t4=findViewById(R.id.AddtxtDesc);

        AddJobBtn = findViewById(R.id.AddJobPostBtn);
        CanBtn = findViewById(R.id.AddJobCancelBtn);

        AddJobBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBRef = FirebaseDatabase.getInstance().getReference().child(DBMaster.Job.TABLE_NAME);
                DBRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        Jobs job = new Jobs();

                        job.setUserID("01");
                        job.setCategory("IT");
                        job.setTitle("Engineering");
                        job.setCompanyName(t1.getText().toString().trim());
                        job.setCompanyAddress(t2.getText().toString().trim());
                        job.setType("Full Time");
                        job.setSalary(t3.getText().toString().trim());
                        job.setDescription(t4.getText().toString().trim());

                        DBRef.child("J01").setValue(job);

                        Toast.makeText(CompanyAddJobActivity.this, "Your Job Posted...!",Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(CompanyAddJobActivity.this,CompanyViewJobActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        CanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(CompanyAddJobActivity.this, "Cancel Add job",Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(CompanyAddJobActivity.this,CompanyViewJobActivity.class);
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