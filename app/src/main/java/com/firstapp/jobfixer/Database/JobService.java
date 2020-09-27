package com.firstapp.jobfixer.Database;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.firstapp.jobfixer.Model.Jobs;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class JobService {

    DatabaseReference dbRef ;

/* +++++++++++++++++++++++++++++++++++++++ Add Job +++++++++++++++++++++++++++++ */
    public void addJob (Jobs job)
    {
        dbRef = FirebaseDatabase.getInstance().getReference().child(DBMaster.Job.TABLE_NAME);

        //Insert into database
        dbRef.child("JB01").setValue(job);
    }


/* +++++++++++++++++++++++++++++++++++++++ Show Job +++++++++++++++++++++++++++++ */
    public Jobs ShowJobs(String jobId)
    {

        DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("Jobs").child(jobId);
        Jobs job = new Jobs();

        readRef.addListenerForSingleValueEvent(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

             /*   if(snapshot.hasChildren()) {
                    job.setJobID(snapshot.child(DBMaster.Job._ID).getValue().toString());
                    job.setUserID(snapshot.child(DBMaster.Job.COLUMN_NAME_USER_ID).getValue().toString());
                    job.setCategory(snapshot.child(DBMaster.Job.COLUMN_NAME_CATEGORY).getValue().toString());
                    job.setTitle(snapshot.child(DBMaster.Job.COLUMN_NAME_TITLE).getValue().toString());
                    job.setCompanyAddress(snapshot.child(DBMaster.Job.COLUMN_NAME_COMPANY_ADDRESS).getValue().toString());
                    job.setType(snapshot.child(DBMaster.Job.COLUMN_NAME_TYPE).getValue().toString());
                    job.setSalary(snapshot.child(DBMaster.Job.COLUMN_NAME_SALARY).getValue().toString());
                    job.setDescription(snapshot.child(DBMaster.Job.COLUMN_NAME_DESCRIPTION).getValue().toString());

                }
              */
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return job;
    }

    public void UpdateJob(final Jobs job, final String jobID)
    {
        DatabaseReference upRef = FirebaseDatabase.getInstance().getReference().child(DBMaster.Job.TABLE_NAME);
        upRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}
