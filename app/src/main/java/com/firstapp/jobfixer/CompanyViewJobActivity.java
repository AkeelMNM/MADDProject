package com.firstapp.jobfixer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.firstapp.jobfixer.Database.DBMaster;
import com.firstapp.jobfixer.ViewAdapters.JobViewAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CompanyViewJobActivity extends AppCompatActivity {

    private static final String TAG ="CompanyViewJobActivity" ;
    private ArrayList<String> mJobID = new ArrayList<>();
    private ArrayList<String> mJobName = new ArrayList<>();
    private ArrayList<String> mComName = new ArrayList<>();
    private ArrayList<String> mJobAdd = new ArrayList<>();
    private ArrayList<String> mJobType = new ArrayList<>();
    private ArrayList<String> mJobSal = new ArrayList<>();
    private ArrayList<String> mJobDes = new ArrayList<>();
    private ArrayList<String> mJobCate = new ArrayList<>();

    Button AddJob;
    DatabaseReference dBRead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_view_job);

        AddJob =findViewById(R.id.ViewAddJobBtn);

        initImageBitmaps();

        AddJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(CompanyViewJobActivity.this,CompanyAddJobActivity.class);
                startActivity(intent);
            }
        });

    }

    private void initImageBitmaps() {

        dBRead = FirebaseDatabase.getInstance().getReference().child(DBMaster.Job.TABLE_NAME);
        Query data = dBRead.orderByChild(DBMaster.Job.COLUMN_NAME_USER_ID).equalTo(SessionApplication.getUserID());

        data.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot jb : snapshot.getChildren())
                {
                    mJobID.add(jb.getKey().toString());
                    mJobName.add(jb.child(DBMaster.Job.COLUMN_NAME_TITLE).getValue().toString());
                    mComName.add(jb.child(DBMaster.Job.COLUMN_NAME_COMPANY_NAME).getValue().toString());
                    mJobAdd.add(jb.child(DBMaster.Job.COLUMN_NAME_COMPANY_ADDRESS).getValue().toString());
                    mJobType.add(jb.child(DBMaster.Job.COLUMN_NAME_TYPE).getValue().toString());
                    mJobSal.add(jb.child(DBMaster.Job.COLUMN_NAME_SALARY).getValue().toString());
                    mJobDes.add((jb.child(DBMaster.Job.COLUMN_NAME_DESCRIPTION).getValue().toString()));
                    mJobCate.add(jb.child(DBMaster.Job.COLUMN_NAME_CATEGORY).getValue().toString());

                    initRecyclerView();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void initRecyclerView() {
        Log.d(TAG, "initRecyclerView: started");
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.viewComTable);
        JobViewAdapter adapter = new JobViewAdapter(mJobID,mJobName,mComName,mJobAdd,mJobType,mJobSal,mJobDes,mJobCate,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        for(int i = 0 ; i<2;i++) {
            recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        }
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

        Intent intent = new Intent(CompanyViewJobActivity.this,AdminLoginActivity.class);
        startActivity(intent);


    }

    private void helpCenter() {
        Intent intent = new Intent(CompanyViewJobActivity.this,HelpCenterActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        /** check user is log in**/
        if(SessionApplication.getUserName().equals("")){
            Intent intent = new Intent(CompanyViewJobActivity.this,AdminLoginActivity.class);
            startActivity(intent);
        }

    }

}