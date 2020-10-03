package com.firstapp.jobfixer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ArrayAdapter;
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

import java.util.ArrayList;

public class CompanyAddJobActivity extends AppCompatActivity {

    private ArrayList<String> JobID = new ArrayList<>();
    public static final String JOBS_ID_PREFIX ="job0";

    EditText txtComName,txtComAdd,txtComSal,txtComDes;
    Button AddJobBtn;
    Button CanBtn;
    Spinner jobCategory,JobTitle,JobType;
    String[] JobCategoryArray,JobTitleArray,JobTypeArray;
    ArrayAdapter CategoryAdapter,TileAdapter,TypeAdapter;
    DatabaseReference DBRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_add_job);

        jobCategory =(Spinner) findViewById(R.id.AddJobCatogorySpinner);
        JobTitle =(Spinner) findViewById(R.id.AddJobTitleSpinner);
        JobType =(Spinner) findViewById(R.id.AddJobTypeSpinner);

        JobCategoryArray = getResources().getStringArray(R.array.JobCategory);
        JobTitleArray = getResources().getStringArray(R.array.JobTitle);
        JobTypeArray = getResources().getStringArray(R.array.JobType);

        txtComName = (EditText) findViewById(R.id.AddComName);
        txtComAdd = (EditText) findViewById(R.id.AddCompanyAddress);
        txtComSal = (EditText) findViewById(R.id.AddJobSalary);
        txtComDes = (EditText) findViewById(R.id.AddtxtDesc);

        AddJobBtn = (Button) findViewById(R.id.AddJobPostBtn);
        CanBtn = (Button) findViewById(R.id.AddJobCancelBtn);

        CategoryAdapter = new ArrayAdapter(CompanyAddJobActivity.this,android.R.layout.simple_spinner_item,JobCategoryArray);
        CategoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        jobCategory.setAdapter(CategoryAdapter);

        TileAdapter = new ArrayAdapter(CompanyAddJobActivity.this,android.R.layout.simple_spinner_item,JobTitleArray);
        TileAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        JobTitle.setAdapter(TileAdapter);

        TypeAdapter = new ArrayAdapter(CompanyAddJobActivity.this,android.R.layout.simple_spinner_item,JobTypeArray);
        TypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        JobType.setAdapter(TypeAdapter);

        AddJobBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBRef = FirebaseDatabase.getInstance().getReference().child(DBMaster.Job.TABLE_NAME);
                DBRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        Jobs job = new Jobs();

                        job.setUserID("01");
                        job.setCategory(jobCategory.getSelectedItem().toString().trim());
                        job.setTitle(JobTitle.getSelectedItem().toString().trim());
                        job.setCompanyName(txtComName.getText().toString().trim());
                        job.setCompanyAddress(txtComAdd.getText().toString().trim());
                        job.setType(JobType.getSelectedItem().toString().trim());
                        job.setSalary(txtComSal.getText().toString().trim());
                        job.setDescription(txtComDes.getText().toString().trim());

                        for(DataSnapshot jb: snapshot.getChildren())
                        {
                            JobID.add(jb.getValue().toString());
                        }

                        String id = null ;
                        int alSize = JobID.size();
                        alSize++;
                        id = JOBS_ID_PREFIX + alSize;

                        if(JobID.contains(id))
                        {
                            alSize++;
                            id = JOBS_ID_PREFIX + alSize;
                        }

                        if(TextUtils.isEmpty(txtComName.getText())){
                            Toast.makeText(CompanyAddJobActivity.this,"Enter Company Name",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        else if(TextUtils.isEmpty(txtComAdd.getText())){
                            Toast.makeText(CompanyAddJobActivity.this,"Enter Company Address",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        else if(TextUtils.isEmpty(txtComSal.getText())){
                            Toast.makeText(CompanyAddJobActivity.this,"Enter Salary",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        else if(TextUtils.isEmpty(txtComDes.getText())){
                            Toast.makeText(CompanyAddJobActivity.this,"Enter Description",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        else
                        {
                            //Insert into Database
                            DBRef.child(id).setValue(job);

                            Toast.makeText(CompanyAddJobActivity.this, "Your Job Posted successfully..!! ",Toast.LENGTH_LONG).show();

                            Intent intent = new Intent(CompanyAddJobActivity.this,CompanyViewJobActivity.class);
                            startActivity(intent);
                        }

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

                Toast.makeText(CompanyAddJobActivity.this, "Cancel Add Job successfully..!!",Toast.LENGTH_SHORT).show();

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