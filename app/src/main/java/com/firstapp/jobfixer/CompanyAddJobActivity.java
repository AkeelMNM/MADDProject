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

    EditText txtComName,txtComAdd,txtComSal,txtComDes;
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

        txtComName = (EditText) findViewById(R.id.AddComName);
        txtComAdd = (EditText) findViewById(R.id.AddCompanyAddress);
        txtComSal = (EditText) findViewById(R.id.AddJobSalary);
        txtComDes = (EditText) findViewById(R.id.AddtxtDesc);

        AddJobBtn = (Button) findViewById(R.id.AddJobPostBtn);
        CanBtn = (Button) findViewById(R.id.AddJobCancelBtn);

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
                        job.setCompanyName(txtComName.getText().toString().trim());
                        job.setCompanyAddress(txtComAdd.getText().toString().trim());
                        job.setType("Full Time");
                        job.setSalary(txtComSal.getText().toString().trim());
                        job.setDescription(txtComDes.getText().toString().trim());

                        DBRef.child("J01").setValue(job);

                        Toast.makeText(CompanyAddJobActivity.this, "Your Job Posted successfully..!! ",Toast.LENGTH_LONG).show();

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