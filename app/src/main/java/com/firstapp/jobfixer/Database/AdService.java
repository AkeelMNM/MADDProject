package com.firstapp.jobfixer.Database;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.firstapp.jobfixer.Model.Advertisement;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdService {

    DatabaseReference dbRef;

    public void addADDS(Advertisement ad){
        dbRef  = FirebaseDatabase.getInstance().getReference().child(DBMaster.Advertisement.TABLE_NAME);

        //Insert into Database
        //dbRef.push().setValue(std);
        dbRef.child("Ad1").setValue(ad);
    }

    /*public Advertisement showAd(String Acid){

        DatabaseReference readRef= FirebaseDatabase.getInstance().getReference().child(DBMaster.Advertisement.TABLE_NAME).child(Acid);

        Advertisement ad = new Advertisement();

        String id;
        readRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                try {

                    if(dataSnapshot.hasChildren()){

                        id =(dataSnapshot.child(DBMaster.Advertisement._ID).getValue().toString());

                        ad.setJobCategory(dataSnapshot.child(DBMaster.Advertisement.COLUMN_NAME_JOB_CATEGORY).getValue().toString());
                        ad.setJobTitle(dataSnapshot.child(DBMaster.Advertisement.COLUMN_NAME_JOB_TITLE).getValue().toString());
                        ad.setCompanyName(dataSnapshot.child(DBMaster.Advertisement.COLUMN_NAME_COMPANY_NAME).getValue().toString());
                        ad.setCompanyAddress(dataSnapshot.child(DBMaster.Advertisement.COLUMN_NAME_COMPANY_ADDRESS).getValue().toString());
                        ad.setJobType(dataSnapshot.child(DBMaster.Advertisement.COLUMN_NAME_JOB_TYPE).getValue().toString());
                        ad.setJobSalary(dataSnapshot.child(DBMaster.Advertisement.COLUMN_NAME_JOB_SALARY).getValue().toString());
                        ad.setQualification(dataSnapshot.child(DBMaster.Advertisement.COLUMN_NAME_JOB_QUALIFICATION).getValue().toString());
                    }
                }catch(NullPointerException e){
                    e.getStackTrace();
                }
           }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        return ad;
    }*/

    public void UpdateAdd(final Advertisement advertisement, final String AdId){

        DatabaseReference upRef= FirebaseDatabase.getInstance().getReference().child(DBMaster.Advertisement.TABLE_NAME);
        upRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild(AdId)){
                    try {

                        dbRef= FirebaseDatabase.getInstance().getReference().child("Student").child(AdId);
                        dbRef.setValue(advertisement);

                    } catch (NumberFormatException e) {

                    }
                }
                else{

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
