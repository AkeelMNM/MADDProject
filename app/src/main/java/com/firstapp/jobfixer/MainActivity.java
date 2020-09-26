package com.firstapp.jobfixer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;

import com.firstapp.jobfixer.Database.DBMaster;
import com.firstapp.jobfixer.ViewAdapters.AdRecycleViewAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG ="Main Activity" ;
    private ArrayList<String> mJobName = new ArrayList<>();
    private ArrayList<String> mCompName = new ArrayList<>();
    private ArrayList<String> mCompLocation = new ArrayList<>();
    private ArrayList<String> mJobQualification = new ArrayList<>();
    private ArrayList<String> mJobSalary = new ArrayList<>();
    private ArrayList<String> mJobType = new ArrayList<>();

    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initImageBitmaps();


    }

    private void initImageBitmaps() {
        /** Retrieving Data's from Firebase Database According to the user specification**/

        dbRef=FirebaseDatabase.getInstance().getReference().child(DBMaster.Advertisement.TABLE_NAME);
        Query data = dbRef.orderByChild(DBMaster.Advertisement.COLUMN_NAME_JOB_TITLE).equalTo("Software Engineer");

        data.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot st: dataSnapshot.getChildren()){
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
        initRecyclerView();

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    private void initRecyclerView() {
        Log.d(TAG, "initRecyclerView: started");
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        AdRecycleViewAdapter adapter = new AdRecycleViewAdapter(mJobName,mCompName,mCompLocation,mJobQualification,mJobSalary,mJobType,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));


    }
}