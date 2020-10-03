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
import android.widget.Toast;

import com.firstapp.jobfixer.Database.DBMaster;
import com.firstapp.jobfixer.Model.Jobs;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CompanyEditJobActivity extends AppCompatActivity {

    EditText txtComName,txtComAddress,txtSalary,txtDes,txtJobID;
    Button UpdateBtn;
    Button CancelBtn;
    Spinner spJobCategory,spJobTitle,spJobType;
    String jCa,jTitle,jTyp;

    DatabaseReference DBRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_edit_job);

        spJobTitle =(Spinner) findViewById(R.id.EditJobTitleSpinner);
        spJobCategory =(Spinner) findViewById(R.id.EditJobCatogorySpinner);
        spJobType =(Spinner) findViewById(R.id.EditJobTypeSpinner);

        txtComName =findViewById(R.id.EditComName);
        txtComAddress =findViewById(R.id.EditCompanyAddress);
        txtSalary =findViewById(R.id.EditJobSalary);
        txtDes =findViewById(R.id.EditTxtDesc);
        txtJobID = findViewById(R.id.EditJobID);

        UpdateBtn = (Button) findViewById(R.id.EditJobUpdateBtn);
        CancelBtn = (Button) findViewById(R.id.EditJobCancelBtn);

        /** Retrieving the RecycleView item details from CompanyViewActivity.java class via Intent **/
        Bundle bundle = getIntent().getExtras();
        txtComName.setText(bundle.getString("JComName"));
        txtComAddress.setText(bundle.getString("comAdd"));
        txtSalary.setText(bundle.getString("jSal"));
        txtDes.setText(bundle.getString("jDes"));
        txtJobID.setText(bundle.getString("jID"));

        /** Assigning the job title detail to the spinner got from the Intent**/
        ArrayList<String> jTit = new ArrayList<String>();
        jTit.add(bundle.getString("jTitle"));
        ArrayAdapter<String> arrayAdapterTitle = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, jTit);
        arrayAdapterTitle.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spJobTitle.setAdapter(arrayAdapterTitle);

        /** Assigning the job category detail to the spinner got from the Intent**/
        ArrayList<String> jCate = new ArrayList<String>();
        jCate.add(bundle.getString("jCate"));
        ArrayAdapter<String> arrayAdapterC = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, jCate);
        arrayAdapterC.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spJobCategory.setAdapter(arrayAdapterC);

        /** Assigning the job Type detail to the spinner got from the Intent**/
        ArrayList<String> jType = new ArrayList<String>();
        jType.add(bundle.getString("jType"));
        ArrayAdapter<String> arrayAdapterType = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, jType);
        arrayAdapterType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spJobType.setAdapter(arrayAdapterType);

        /** capturing the details in the spinner from the update form**/
        jTitle = spJobTitle.getSelectedItem().toString();
        jCa = spJobCategory.getSelectedItem().toString();
        jTyp = spJobType.getSelectedItem().toString();

        UpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DBRef = FirebaseDatabase.getInstance().getReference().child(DBMaster.Job.TABLE_NAME);
                DBRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        Jobs job = new Jobs();

                        if(snapshot.hasChild(txtJobID.getText().toString().trim())){

                            job.setCategory(jCa);
                            job.setTitle(jTitle);
                            job.setCompanyName(txtComName.getText().toString().trim());
                            job.setCompanyAddress(txtComAddress.getText().toString().trim());
                            job.setType(jTyp);
                            job.setSalary(txtSalary.getText().toString().trim());
                            job.setDescription(txtDes.getText().toString().trim());

                        }

                        if(TextUtils.isEmpty(txtComName.getText())){
                            Toast.makeText(CompanyEditJobActivity.this,"Enter Company Name",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        else if(TextUtils.isEmpty(txtComAddress.getText())){
                            Toast.makeText(CompanyEditJobActivity.this,"Enter Company Address",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        else if(TextUtils.isEmpty(txtSalary.getText())){
                            Toast.makeText(CompanyEditJobActivity.this,"Enter Salary",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        else if(TextUtils.isEmpty(txtDes.getText())){
                            Toast.makeText(CompanyEditJobActivity.this,"Enter Description",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        else
                        {
                            DBRef = FirebaseDatabase.getInstance().getReference().child(DBMaster.Job.TABLE_NAME).child(txtJobID.getText().toString().trim());
                            DBRef.setValue(job);

                            Intent intent = new Intent(CompanyEditJobActivity.this,CompanyViewJobActivity.class);
                            startActivity(intent);
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        CancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(CompanyEditJobActivity.this, "Cancel Update Job successfully..!! ",Toast.LENGTH_SHORT).show();

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