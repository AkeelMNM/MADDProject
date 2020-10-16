package com.firstapp.jobfixer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.firstapp.jobfixer.Database.DBMaster;
import com.firstapp.jobfixer.ViewAdapters.AdAdminRecycleViewAdepter;
import com.firstapp.jobfixer.ViewAdapters.JobViewRequestForComAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CompViewAllApplicantJobRequestActivity extends AppCompatActivity {

    private static final String TAG = "CompViewAllApplicantJobRequestActivity";
    private ArrayList<String> applicantName = new ArrayList<>();
    private ArrayList<String> applicantEmail = new ArrayList<>();
    private ArrayList<String> applyDate = new ArrayList<>();
    private ArrayList<String> applyJob = new ArrayList<>();

    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comp_view_all_applicant_job_request);
        initImageBitmaps();


    }

    private void initImageBitmaps() {
        /** Retrieving All Data's from Firebase Database **/

        dbRef=FirebaseDatabase.getInstance().getReference().child(DBMaster.SendRequestToCompany.TABLE_NAME);
        Query data = dbRef.orderByChild(DBMaster.SendRequestToCompany.COLUMN_NAME_COMPANY_NAME).equalTo(SessionApplication.getUserName());

        data.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot st: dataSnapshot.getChildren()){
                    applicantName.add(st.child(DBMaster.SendRequestToCompany.COLUMN_NAME_APPLICANT_NAME).getValue().toString());
                    applicantEmail.add(st.child(DBMaster.SendRequestToCompany.COLUMN_NAME_APPLICANT_EMAIL).getValue().toString());
                    applyDate.add(st.child(DBMaster.SendRequestToCompany.COLUMN_NAME_APPLY_DATE).getValue().toString());
                    applyJob.add(st.child(DBMaster.SendRequestToCompany.COLUMN_NAME_JOB_NAME).getValue().toString());
                    initRecyclerView();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @SuppressLint("LongLogTag")
    private void initRecyclerView() {
        Log.d(TAG, "initRecyclerView: started");
        RecyclerView recyclerView = findViewById(R.id.applicantJobReqRecycleView);

        /** Assigning the Retrieved Data's to admin_ad_recycleView_layout.xml via Constructor of AdAdminRecycleViewAdepter.java **/
        JobViewRequestForComAdapter adapter = new JobViewRequestForComAdapter(applicantName,applicantEmail,applyDate,applyJob,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        for(int i=0;i<2;i++){ recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));}
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    /** Menu bar actions**/
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_help:
                helpCenter();
                return true;
            case R.id.action_logout:
                logOut();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /** Logout from device**/
    private void logOut() {
        SessionApplication.setUserID("");
        SessionApplication.setUserName("");
        SessionApplication.setUserType("");
        SessionApplication.setUserEmail("");

        Intent intent = new Intent(CompViewAllApplicantJobRequestActivity.this,LoginActivity.class);
        startActivity(intent);


    }

    private void helpCenter() {
        Intent intent = new Intent(CompViewAllApplicantJobRequestActivity.this,HelpCenterActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        /** check user is log in**/
        if(SessionApplication.getUserName().equals("")){
            Intent intent = new Intent(CompViewAllApplicantJobRequestActivity.this,LoginActivity.class);
            startActivity(intent);
        }

    }
}