package com.firstapp.jobfixer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.firstapp.jobfixer.Database.DBMaster;
import com.firstapp.jobfixer.Model.Jobs;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CompanyEditJobActivity extends AppCompatActivity {

    EditText t1,t2,t3,t4,JobID;
    Button UpdateBtn;
    Button CancelBtn;
    Spinner EditjobCategory,EditJobTitle,EditJobType;
    String[] JobCategoryArray,JobTitleArray,JobTypeArray;
    ArrayAdapter CategoryAdapter,TileAdapter,TypeAdapter;

    DatabaseReference DBRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_edit_job);

        EditjobCategory =(Spinner) findViewById(R.id.EditJobCatogorySpinner);
        EditJobTitle =(Spinner) findViewById(R.id.EditJobTitleSpinner);
        EditJobType =(Spinner) findViewById(R.id.EditJobTypeSpinner);

        JobCategoryArray = getResources().getStringArray(R.array.JobCategory);
        JobTitleArray = getResources().getStringArray(R.array.JobTitle);
        JobTypeArray = getResources().getStringArray(R.array.JobType);

        t1=findViewById(R.id.EditComName);
        t2=findViewById(R.id.EditCompanyAddress);
        t3=findViewById(R.id.EditJobSalary);
        t4=findViewById(R.id.EditTxtDesc);

        UpdateBtn = (Button) findViewById(R.id.EditJobUpdateBtn);
        CancelBtn = (Button) findViewById(R.id.EditJobCancelBtn);

        UpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DBRef = FirebaseDatabase.getInstance().getReference().child(DBMaster.Job.TABLE_NAME);
                DBRef.addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        Jobs job = new Jobs();

                        if(snapshot.hasChild(JobID.getText().toString().trim())){

                            job.setCategory("IT");
                            job.setTitle("Engineering");
                            job.setCompanyName(t1.getText().toString().trim());
                            job.setCompanyAddress(t2.getText().toString().trim());
                            job.setType("Full Time");
                            job.setSalary(t3.getText().toString().trim());
                            job.setDescription(t4.getText().toString().trim());

                        }

                        DBRef = FirebaseDatabase.getInstance().getReference().child(DBMaster.Job.TABLE_NAME).child(JobID.getText().toString().trim());
                        DBRef.setValue(job);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                Intent intent = new Intent(CompanyEditJobActivity.this,CompanyViewJobActivity.class);
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