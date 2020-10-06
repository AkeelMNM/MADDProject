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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.firstapp.jobfixer.Database.DBMaster;
import com.firstapp.jobfixer.ViewAdapters.JobUserViewAdapter;
import com.firstapp.jobfixer.ViewAdapters.JobViewAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    private static final String TAG ="SearchActivity";
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
    String Ty;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        seBox = findViewById(R.id.searchBox);
        btnSe=findViewById(R.id.btnsearch);

        Spinner searchSp = (Spinner) findViewById(R.id.searchSpinner);

        /**Assigning the values to Job Type Spinner in the Activity**/
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.search_cat));
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        searchSp.setAdapter(arrayAdapter);

        Ty = searchSp.getSelectedItem().toString();

        btnSe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    initImageBitmaps();

            }
        });

    }



    private void initImageBitmaps() {
        String SeText = seBox.getText().toString().trim();
        Toast.makeText(this, SeText, Toast.LENGTH_SHORT).show();
      dbRef=FirebaseDatabase.getInstance().getReference().child(DBMaster.Job.TABLE_NAME);
      Query data = null;
      if(Ty.equals("Job Name")){
          data =dbRef.orderByChild(DBMaster.Job.COLUMN_NAME_TITLE).startAt(SeText).endAt(SeText+"\uf8ff");
      }
      else if (Ty.equals("Company Name")){
          data =dbRef.orderByChild(DBMaster.Job.COLUMN_NAME_COMPANY_NAME).startAt(SeText).endAt(SeText+"\uf8ff");
      }
      else if (Ty.equals("Company Address")){
          data =dbRef.orderByChild(DBMaster.Job.COLUMN_NAME_COMPANY_ADDRESS).startAt(SeText).endAt(SeText+"\uf8ff");
      }
      else if (Ty.equals("Salary")){
          data =dbRef.orderByChild(DBMaster.Job.COLUMN_NAME_SALARY).startAt(SeText).endAt(SeText+"\uf8ff");
      }
      else if (Ty.equals("Job Type")){
          data =dbRef.orderByChild(DBMaster.Job.COLUMN_NAME_TYPE).startAt(SeText).endAt(SeText+"\uf8ff");
      }
      else{
          data =FirebaseDatabase.getInstance().getReference().child(DBMaster.Job.TABLE_NAME);
      }

      data.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

               /* seJobID.clear();
                seJobName.clear();
                seComName.clear();
                seJobAdd.clear();
                seJobType.clear();
                seJobSal.clear();
                seJobDes.clear();
                seJobCate.clear();*/

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
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.searchRelativeLayout);
        JobUserViewAdapter adapter = new JobUserViewAdapter(seJobID,seJobName,seComName,seJobAdd,seJobType,seJobSal,seJobDes,seJobCate,this);
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
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

        Intent intent = new Intent(SearchActivity.this,LoginActivity.class);
        startActivity(intent);


    }

    private void helpCenter() {

        Intent intent = new Intent(SearchActivity.this,HelpCenterActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        /** check user is log in**/
        if(SessionApplication.getUserName().equals("")){
            Intent intent = new Intent(SearchActivity.this,LoginActivity.class);
            startActivity(intent);
        }

    }
}