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
import android.widget.Button;
import android.widget.Toast;

import com.firstapp.jobfixer.Database.DBMaster;
import com.firstapp.jobfixer.ViewAdapters.AdRecycleViewAdapter;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firstapp.jobfixer.Model.Helpcenter;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import com.google.firebase.database.ValueEventListener;

import static android.app.ProgressDialog.show;

public class MainActivity extends AppCompatActivity {

    Button HUserJobs,HUserResume,HUserSearch,HUserHelpCenter;
    String SesName,SesUID,SesType,SesEmail;

    private static final String TAG ="Main Activity" ;
    private ArrayList<String> mJobId = new ArrayList<>();
    private ArrayList<String> mJobCat = new ArrayList<>();
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

        HUserJobs=findViewById(R.id.btnUserJobs);
        HUserResume=findViewById(R.id.btnUserResume);
        HUserSearch=findViewById(R.id.btnUserSearch);
        HUserHelpCenter=findViewById(R.id.btnUserHelpCenter);

        /** Session Application class to get user Details**/
        SesUID=SessionApplication.getUserID();
        SesName=SessionApplication.getUserName();
        SesType=SessionApplication.getUserType();
        SesEmail=SessionApplication.getUserEmail();

    }

    private void initImageBitmaps() {
        /** Retrieving Data's from Firebase Database According to the user specification**/

        dbRef=FirebaseDatabase.getInstance().getReference().child(DBMaster.Advertisement.TABLE_NAME);
        Query data = dbRef.orderByChild(DBMaster.Advertisement.COLUMN_NAME_JOB_TITLE).equalTo("Software Engineer");

        data.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot st: dataSnapshot.getChildren()){
                    mJobId.add(st.child(DBMaster.Advertisement.COLUMN_NAME_JOB_ID).getValue().toString());
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

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    private void initRecyclerView() {
        Log.d(TAG, "initRecyclerView: started");
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        AdRecycleViewAdapter adapter = new AdRecycleViewAdapter(mJobId,mJobCat,mJobName,mCompName,mCompLocation,mJobQualification,mJobSalary,mJobType,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));


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

        Intent intent = new Intent(MainActivity.this,LoginActivity.class);
        startActivity(intent);


    }

    private void helpCenter() {

        /*Intent intent = new Intent(MainActivity.this,HelpCenterActivity.class);
        startActivity(intent);*/
    }

    @Override
    protected void onStart() {
        super.onStart();
        /** check user is log in**/
        if(SessionApplication.getUserName().equals("")){
            Intent intent = new Intent(MainActivity.this,LoginActivity.class);
            startActivity(intent);
        }

    }
}