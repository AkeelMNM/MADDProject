package com.firstapp.jobfixer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firstapp.jobfixer.Database.DBMaster;
import com.firstapp.jobfixer.ViewAdapters.AdAdminRecycleViewAdepter;
import com.firstapp.jobfixer.ViewAdapters.AdRecycleViewAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class JobAdAdminViewActivity extends AppCompatActivity {

    private static final String TAG ="Main Activity" ;
    private ArrayList<String> mJobCat = new ArrayList<>();
    private ArrayList<String> mJobName = new ArrayList<>();
    private ArrayList<String> mCompName = new ArrayList<>();
    private ArrayList<String> mCompLocation = new ArrayList<>();
    private ArrayList<String> mJobQualification = new ArrayList<>();
    private ArrayList<String> mJobSalary = new ArrayList<>();
    private ArrayList<String> mJobType = new ArrayList<>();
    private ArrayList<String> mJobID = new ArrayList<>();

    Button btnCreate;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_ad_admin_view);
        initImageBitmaps();

        btnCreate=findViewById(R.id.btnCreateAd);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /**Redirecting to Create ad from via Intent**/
                Intent intent = new Intent(JobAdAdminViewActivity.this,JobAdCreateActivity.class);
                startActivity(intent);
            }
        });

    }

    private void initImageBitmaps() {

        /** Retrieving All Data's from Firebase Database **/

        Query data = FirebaseDatabase.getInstance().getReference().child(DBMaster.Advertisement.TABLE_NAME);

        data.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot st: dataSnapshot.getChildren()){
                    mJobID.add(st.getKey().toString());
                    mJobCat.add(st.child(DBMaster.Advertisement.COLUMN_NAME_JOB_CATEGORY).getValue().toString());
                    mJobName.add(st.child(DBMaster.Advertisement.COLUMN_NAME_JOB_TITLE).getValue().toString());
                    mCompName.add(st.child(DBMaster.Advertisement.COLUMN_NAME_COMPANY_NAME).getValue().toString());
                    mCompLocation.add(st.child(DBMaster.Advertisement.COLUMN_NAME_COMPANY_ADDRESS).getValue().toString());
                    mJobSalary.add(st.child(DBMaster.Advertisement.COLUMN_NAME_JOB_SALARY).getValue().toString());
                    mJobType.add(st.child(DBMaster.Advertisement.COLUMN_NAME_JOB_TYPE).getValue().toString());
                    mJobQualification.add(st.child(DBMaster.Advertisement.COLUMN_NAME_JOB_QUALIFICATION).getValue().toString());
                    initRecyclerView();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void initRecyclerView() {
        Log.d(TAG, "initRecyclerView: started");
        RecyclerView recyclerView = findViewById(R.id.adminRecyleView);

        /** Assigning the Retrieved Data's to admin_ad_recycleView_layout.xml via Constructor of AdAdminRecycleViewAdepter.java **/
        AdAdminRecycleViewAdepter adapter = new AdAdminRecycleViewAdepter(mJobCat,mJobName,mCompName,mCompLocation,mJobQualification,mJobSalary,mJobType,mJobID,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        for(int i=0;i<3;i++){ recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));}


    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

}