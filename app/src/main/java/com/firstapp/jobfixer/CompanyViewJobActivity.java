package com.firstapp.jobfixer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DownloadManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;

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

    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_view_job);

        initImageBitmaps();

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
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        JobViewAdapter adapter = new JobViewAdapter(mJobID,mJobName,mComName,mJobAdd,mJobType,mJobSal,this);
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        for(int i=0;i<3;i++){recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));}

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

}