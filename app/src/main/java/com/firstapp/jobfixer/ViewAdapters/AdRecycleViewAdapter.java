package com.firstapp.jobfixer.ViewAdapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firstapp.jobfixer.Database.DBMaster;
import com.firstapp.jobfixer.JobAdAdminViewActivity;
import com.firstapp.jobfixer.JobAdCreateActivity;
import com.firstapp.jobfixer.JobAdUpdateActivity;
import com.firstapp.jobfixer.JobViewForUserActivity;
import com.firstapp.jobfixer.Model.Advertisement;
import com.firstapp.jobfixer.Model.SendRequestToCompany;
import com.firstapp.jobfixer.R;
import com.firstapp.jobfixer.SessionApplication;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdRecycleViewAdapter  extends RecyclerView.Adapter<AdRecycleViewAdapter.ViewHolder> {

    private ArrayList<String> jobID = new ArrayList<>();
    private ArrayList<String> jobCat = new ArrayList<>();
    private ArrayList<String> jobName = new ArrayList<>();
    private ArrayList<String> compName = new ArrayList<>();
    private ArrayList<String> compLocation = new ArrayList<>();
    private ArrayList<String> jobQualification = new ArrayList<>();
    private ArrayList<String> jobSalary = new ArrayList<>();
    private ArrayList<String> jobType = new ArrayList<>();
    private Context mContext;

    DatabaseReference dbRef;

    public AdRecycleViewAdapter(ArrayList<String> jobID, ArrayList<String> jobCat, ArrayList<String> jobName, ArrayList<String> compName, ArrayList<String> compLocation, ArrayList<String> jobQualification, ArrayList<String> jobSalary, ArrayList<String> jobType, Context mContext) {
        this.jobID = jobID;
        this.jobCat = jobCat;
        this.jobName = jobName;
        this.compName = compName;
        this.compLocation = compLocation;
        this.jobQualification = jobQualification;
        this.jobSalary = jobSalary;
        this.jobType = jobType;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //Create view from xml layout(layoutList)
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_home_ad_recyclerview_layout,parent,false);

        //Create ViewHolder
        ViewHolder viewHolder = new ViewHolder(view);

        //return View Holder
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        //set Texts
        holder.jobTitle.setText(jobName.get(position));
        holder.comName.setText(compName.get(position));
        holder.comLoc.setText(compLocation.get(position));
        holder.jobSal.setText(jobSalary.get(position));
        holder.jobT.setText(jobType.get(position));
        holder.jobQua.setText(jobQualification.get(position));
        holder.jobId.setText(jobID.get(position));

        holder.btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, JobViewForUserActivity.class);
                intent.putExtra("jFID",jobID.get(position));
                intent.putExtra("jFCat",jobCat.get(position));
                intent.putExtra("jFTitle",jobName.get(position));
                intent.putExtra("cFName",compName.get(position));
                intent.putExtra("cFLoc",compLocation.get(position));
                intent.putExtra("jFSal",jobSalary.get(position));
                intent.putExtra("jFType",jobType.get(position));
                intent.putExtra("jFQua",jobQualification.get(position));
                mContext.startActivity(intent);
            }
        });

        holder.btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dbRef = FirebaseDatabase.getInstance().getReference().child(DBMaster.SendRequestToCompany.TABLE_NAME);
                dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        SendRequestToCompany req = new SendRequestToCompany();
                        req.setJobID(jobID.get(position));
                        req.setUserID(SessionApplication.getUserID());
                        req.setApplicantName(SessionApplication.getUserName());
                        req.setApplicantEmail(SessionApplication.getUserEmail());
                        req.setApplyDate(new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date()));
                        req.setJobName(jobName.get(position));
                        req.setCompName(compName.get(position));
                        req.setResumeID(SessionApplication.getResumeID());

                        //Insert into Database
                        dbRef.setValue(req);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                Toast.makeText(mContext.getApplicationContext(), "Request Send Successfully", Toast.LENGTH_SHORT).show();


            }
        });

    }

    @Override
    public int getItemCount() {
        return jobName.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView jobTitle,comName,comLoc,jobSal,jobT,jobQua,jobId;
        RelativeLayout userViewLayout;
        Button btnView,btnApply;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            jobId = itemView.findViewById(R.id.userViewForJobID);
            jobTitle = itemView.findViewById(R.id.textViewJobTit);
            comName = itemView.findViewById(R.id.textCompany);
            comLoc = itemView.findViewById(R.id.textViewCL);
            jobQua = itemView.findViewById(R.id.textQualifuc);
            jobSal = itemView.findViewById(R.id.textViewJS);
            jobT = itemView.findViewById(R.id.textViewJT);
            userViewLayout = itemView.findViewById(R.id.userAdViewLayout);


            btnView = itemView.findViewById(R.id.btnView);
            btnApply = itemView.findViewById(R.id.btnApply);
        }
    }
}
