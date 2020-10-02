package com.firstapp.jobfixer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Button;
import android.widget.EditText;

import com.firstapp.jobfixer.Database.DBMaster;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    private ArrayList<String> seJobID = new ArrayList<>();
    private ArrayList<String> seJobName = new ArrayList<>();
    private ArrayList<String> seComName = new ArrayList<>();
    private ArrayList<String> seJobAdd = new ArrayList<>();
    private ArrayList<String> seJobType = new ArrayList<>();
    private ArrayList<String> seJobSal = new ArrayList<>();
    private ArrayList<String> seJobDes = new ArrayList<>();
    private ArrayList<String> seJobCate = new ArrayList<>();

    DatabaseReference dbRef;

    EditText seBox;
    Button btnSe;
    String seText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initImageBitmaps();

        seBox = findViewById(R.id.searchBox);
        btnSe=findViewById(R.id.btnsearch);

        seText = seBox.getText().toString();

    }

    private void initImageBitmaps() {
        //Query data = FirebaseDatabase.getInstance().getReference().child(DBMaster.Job.TABLE_NAME);
        Query data =dbRef.orderByChild(DBMaster.Job.TABLE_NAME).startAt(seText).endAt(seText +"\uf8ff");
        data.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot jb : snapshot.getChildren()) {
                    seJobID.add(jb.getKey().toString());
                    seJobName.add(jb.child(DBMaster.Job.COLUMN_NAME_TITLE).getValue().toString());
                    seComName.add(jb.child(DBMaster.Job.COLUMN_NAME_COMPANY_NAME).getValue().toString());
                    seJobAdd.add(jb.child(DBMaster.Job.COLUMN_NAME_COMPANY_ADDRESS).getValue().toString());
                    seJobType.add(jb.child(DBMaster.Job.COLUMN_NAME_TYPE).getValue().toString());
                    seJobSal.add(jb.child(DBMaster.Job.COLUMN_NAME_SALARY).getValue().toString());
                    seJobDes.add((jb.child(DBMaster.Job.COLUMN_NAME_DESCRIPTION).getValue().toString()));
                    seJobCate.add(jb.child(DBMaster.Job.COLUMN_NAME_CATEGORY).getValue().toString());
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
        JobViewAdapter adapter = new JobViewAdapter(seJobID,seJobName,seComName,seJobAdd,seJobType,seJobSal,seJobDes,seJobCate,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }
}