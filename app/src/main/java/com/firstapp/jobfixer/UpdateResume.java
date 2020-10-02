package com.firstapp.jobfixer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firstapp.jobfixer.Model.Resume;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import DataBase.DBMaster;

public class UpdateResume extends AppCompatActivity {
    EditText UpfirstName,UplastName,Uplocation ,Upphone , Upemail ,UpaboutMe ,UpworkExperience , Upeducation;
    String UJobCat ,UJobTit ,ResumID;
    Resume resu;
    DatabaseReference dbRef;
    Button BtnupdateRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_resume);
        UpfirstName = findViewById(R.id.Upfname);
        UplastName = findViewById(R.id.txtUpReLastName);
        Uplocation = findViewById(R.id.txtReUpLocation);
        Upphone = findViewById(R.id.txtREUpPhone);
        Upemail = findViewById(R.id.txtReUpEmail);
        UpaboutMe = findViewById(R.id.txtReUpAboutMe);
        UpworkExperience = findViewById(R.id.txtReUpWorkExp);
        Upeducation = findViewById(R.id.txtReUpEdu);
        BtnupdateRes = findViewById(R.id.btnReUpdate);


        dbRef = FirebaseDatabase.getInstance().getReference().child(DBMaster.Resume.TABLE_NAME);
        Query data = dbRef.orderByChild(DBMaster.Resume.COLUMN_NAME_USER_ID).equalTo("Us02");

        data.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot st:dataSnapshot.getChildren()){
                    ResumID = st.getKey();
                    UpfirstName.setText(st.child("firstName").getValue().toString());
                    UplastName.setText(st.child("lastName").getValue().toString());
                    Uplocation.setText(st.child("location").getValue().toString());
                    Upphone.setText(st.child("phone").getValue().toString());
                    Upemail.setText(st.child("email").getValue().toString());
                    UpaboutMe.setText(st.child("aboutMe").getValue().toString());
                    UpworkExperience.setText(st.child("workExp").getValue().toString());
                    Upeducation.setText(st.child("education").getValue().toString());

                }
                /** if(dataSnapshot.hasChildren()){
                 vfname.setText(dataSnapshot.child("firstName").getValue().toString());
                 vlname.setText(dataSnapshot.child("lastName").getValue().toString());
                 vloc.setText(dataSnapshot.child("location").getValue().toString());
                 vphone.setText(dataSnapshot.child("phone").getValue().toString());
                 vEmail.setText(dataSnapshot.child("email").getValue().toString());
                 vJob.setText(dataSnapshot.child("jobTit").getValue().toString());
                 vAbout.setText(dataSnapshot.child("aboutMe").getValue().toString());
                 vWorkEx.setText(dataSnapshot.child("WorkEx").getValue().toString());
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
        BtnupdateRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateResume();
               /** Intent intent = new Intent(UpdateResume.this,ViewResume.class);
                startActivity(intent);**/

            }
        });


    }

    private void updateResume() {

    dbRef = FirebaseDatabase.getInstance().getReference().child(DBMaster.Resume.TABLE_NAME);
    dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            Resume resume =new Resume();
            resume.setUserId("Us02");
            resume.setFirstName(UpfirstName.getText().toString().trim());
            resume.setLastName(UplastName.getText().toString().trim());
            resume.setPhone(Upphone.getText().toString().trim());
            resume.setLocation(Uplocation.getText().toString().trim());
            resume.setEmail(Upemail.getText().toString().trim());
            resume.setEducation(Upeducation.getText().toString().trim());
            resume.setWorkExp(UpworkExperience.getText().toString().trim());
            resume.setAboutMe(UpaboutMe.getText().toString().trim());
            resume.setJobCat("IT");
            resume.setJobTit("Software Eng");
//tis code is not working properly need to check
            dbRef= FirebaseDatabase.getInstance().getReference().child(DBMaster.Resume.TABLE_NAME).child(ResumID);
            dbRef.setValue(ResumID);
            Toast.makeText(UpdateResume.this, "Data Changed Successfully", Toast.LENGTH_SHORT).show();
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
}