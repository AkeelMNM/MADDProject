package com.firstapp.jobfixer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firstapp.jobfixer.Database.DBMaster;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class ViewResume extends AppCompatActivity {
    TextView vfname ,vlname , vloc ,vphone ,vEmail , vJob ,vAbout ,vWorkEx ,vEdu;
    Button UpdateinV , DeleteinV ;
    DatabaseReference dBRead;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_resume);

        vfname = findViewById(R.id.vFname);
        vlname = findViewById(R.id.vLname);
        vloc = findViewById(R.id.vLocation);
        vphone = findViewById(R.id.vPhone);
        vEmail = findViewById(R.id.vEmail);
        vJob = findViewById(R.id.vJobs);
        vAbout = findViewById(R.id.vAboutme);
        vWorkEx = findViewById(R.id.vWorkExp);
        vEdu = findViewById(R.id.vEduc);

        UpdateinV = findViewById(R.id.btnvUpdate);
        DeleteinV = findViewById(R.id.btnvDelete);




                dBRead = FirebaseDatabase.getInstance().getReference().child(DBMaster.Resume.TABLE_NAME);
                 Query data = dBRead.orderByChild(DBMaster.Resume.COLUMN_NAME_USER_ID).equalTo(SessionApplication.getUserID());

                data.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        for(DataSnapshot st:dataSnapshot.getChildren()){
                            vfname.setText(st.child("firstName").getValue().toString());
                            vlname.setText(st.child("lastName").getValue().toString());
                            vloc.setText(st.child("location").getValue().toString());
                            vphone.setText(st.child("phone").getValue().toString());
                            vEmail.setText(st.child("email").getValue().toString());
                            vJob.setText(st.child("jobTit").getValue().toString());
                            vAbout.setText(st.child("aboutMe").getValue().toString());
                            vWorkEx.setText(st.child("workExp").getValue().toString());

                        }
                        /**  if( dataSnapshot.hasChildren()){
                            vfname.setText(dataSnapshot.child("firstName").getValue().toString());
                            vlname.setText(dataSnapshot.child("lastName").getValue().toString());
                            vloc.setText(dataSnapshot.child("location").getValue().toString());
                            vphone.setText(dataSnapshot.child("phone").getValue().toString());
                            vEmail.setText(dataSnapshot.child("email").getValue().toString());
                            vJob.setText(dataSnapshot.child("jobTit").getValue().toString());
                            vAbout.setText(dataSnapshot.child("aboutMe").getValue().toString());
                            vWorkEx.setText(dataSnapshot.child("workExp").getValue().toString());
                            vEdu.setText(dataSnapshot.child("education").getValue().toString());
                        }
                        else{
                            Toast.makeText(ViewResume.this, "No Source Avilable", Toast.LENGTH_SHORT).show();
                        }**/
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                UpdateinV.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(ViewResume.this,UpdateResume.class);
                        startActivity(intent);
                    }
                });

                DeleteinV.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final DatabaseReference dre =FirebaseDatabase.getInstance().getReference().child(DBMaster.Resume.TABLE_NAME);
                        dre.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if(dataSnapshot.hasChild(SessionApplication.getResumeID())){

                                   dBRead =FirebaseDatabase.getInstance().getReference().child(DBMaster.Resume.TABLE_NAME).child(SessionApplication.getResumeID());
                                   dBRead.removeValue();
                                    Toast.makeText(ViewResume.this, "Data Deleted Successfully", Toast.LENGTH_SHORT).show();


                                }
                                else{
                                    Toast.makeText(ViewResume.this, "No sources Available", Toast.LENGTH_SHORT).show();
                                }

                                Intent intent = new Intent(ViewResume.this,ResumeMain.class);
                                startActivity(intent);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }
                });






//retriveing the infos



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

        Intent intent = new Intent(ViewResume.this,LoginActivity.class);
        startActivity(intent);


    }

    private void helpCenter() {
        Intent intent = new Intent(ViewResume.this,HelpCenterActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        /** check user is log in**/
        if(SessionApplication.getUserName().equals("")){
            Intent intent = new Intent(ViewResume.this,LoginActivity.class);
            startActivity(intent);
        }

    }

}