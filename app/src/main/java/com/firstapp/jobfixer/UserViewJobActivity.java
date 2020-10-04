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
import android.view.View;
import android.widget.Button;

import com.firstapp.jobfixer.Database.DBMaster;
import com.firstapp.jobfixer.ViewAdapters.JobUserViewAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UserViewJobActivity extends AppCompatActivity {

    private static final String TAG ="UserViewJobActivity" ;
    private ArrayList<String> mJobID = new ArrayList<>();
    private ArrayList<String> mJobName = new ArrayList<>();
    private ArrayList<String> mComName = new ArrayList<>();
    private ArrayList<String> mJobAdd = new ArrayList<>();
    private ArrayList<String> mJobType = new ArrayList<>();
    private ArrayList<String> mJobSal = new ArrayList<>();
    private ArrayList<String> mJobDes = new ArrayList<>();
    private ArrayList<String> mJobCate = new ArrayList<>();

    Button Request_Btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_view_job);

        Request_Btn = findViewById(R.id.ReqBtn);

        initImageBitmaps();

        Request_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserViewJobActivity.this,UserViewAppliedCompanyRequestActivity.class);
                startActivity(intent);
            }
        });

    }

    private void initImageBitmaps() {

        Query data = FirebaseDatabase.getInstance().getReference().child(DBMaster.Job.TABLE_NAME);

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
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.userJobTable);
        JobUserViewAdapter adapter = new JobUserViewAdapter(mJobID,mJobName,mComName,mJobAdd,mJobType,mJobSal,mJobDes,mJobCate,this);
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

}