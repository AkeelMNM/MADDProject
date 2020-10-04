package com.firstapp.jobfixer.ViewAdapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firstapp.jobfixer.CompanyEditJobActivity;
import com.firstapp.jobfixer.CompanyViewJobActivity;
import com.firstapp.jobfixer.Database.DBMaster;
import com.firstapp.jobfixer.JobViewForUserActivity;
import com.firstapp.jobfixer.Model.SendRequestToCompany;
import com.firstapp.jobfixer.R;
import com.firstapp.jobfixer.SessionApplication;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.ObjectInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class JobUserViewAdapter extends RecyclerView.Adapter<JobUserViewAdapter.ViewHolder>{

    private ArrayList<String> mJobID = new ArrayList<>();
    private ArrayList<String> mJobTitle = new ArrayList<>();
    private ArrayList<String> mComName = new ArrayList<>();
    private ArrayList<String> mJobAdd = new ArrayList<>();
    private ArrayList<String> mJobType = new ArrayList<>();
    private ArrayList<String> mJobSal = new ArrayList<>();
    private ArrayList<String> mJobDes = new ArrayList<>();
    private ArrayList<String> mJobCate = new ArrayList<>();
    private Context mContext;
    DatabaseReference dbRef;

    public JobUserViewAdapter(ArrayList<String> mJobID, ArrayList<String> mJobTitle, ArrayList<String> mComName, ArrayList<String> mJobAdd, ArrayList<String> mJobType, ArrayList<String> mJobSal, ArrayList<String> mJobDes , ArrayList<String> mJobCate, Context mContext) {
        this.mJobID = mJobID;
        this.mJobTitle = mJobTitle;
        this.mComName = mComName;
        this.mJobAdd = mJobAdd;
        this.mJobType = mJobType;
        this.mJobSal = mJobSal;
        this.mJobDes = mJobDes;
        this.mJobCate = mJobCate;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_view_job_layout,parent,false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        //Set text
        holder.txtTi.setText(mJobTitle.get(position));
        holder.txtComNa.setText(mComName.get(position));
        holder.txtComAdd.setText(mJobAdd.get(position));
        holder.txtDes.setText(mJobDes.get(position));
        holder.txtSaary.setText(mJobSal.get(position));
        holder.txtType.setText(mJobType.get(position));

        holder.btnViewMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(mContext, JobViewForUserActivity.class);
                intent.putExtra("jFCat",mJobCate.get(position));
                intent.putExtra("jFTitle",mJobTitle.get(position));
                intent.putExtra("cFName",mComName.get(position));
                intent.putExtra("cFLoc",mJobAdd.get(position));
                intent.putExtra("jFSal",mJobSal.get(position));
                intent.putExtra("jFType",mJobType.get(position));
                intent.putExtra("jFQua",mJobDes.get(position));

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
                        req.setUserID(SessionApplication.getUserID());
                        req.setApplicantName(SessionApplication.getUserName());
                        req.setApplicantEmail(SessionApplication.getUserEmail());
                        req.setApplyDate(new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date()));
                        req.setJobName(mJobTitle.get(position));
                        req.setCompName(mComName.get(position));
                        req.setResumeID(SessionApplication.getResumeID());

                        //Insert into Database
                        dbRef.push().setValue(req);

                        Toast.makeText(mContext.getApplicationContext(), "Request Send Successfully", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

    }


    @Override
    public int getItemCount()
    {
        return mJobTitle.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtTi,txtComNa,txtComAdd,txtSaary,txtType,txtDes;
        Button btnApply,btnViewMore;
        RelativeLayout parentLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTi =itemView.findViewById(R.id.CV_tit);
            txtComNa =itemView.findViewById(R.id.Cv_Nam);
            txtComAdd =itemView.findViewById(R.id.Cv_Add);
            txtSaary =itemView.findViewById(R.id.Cv_Sal);
            txtType =itemView.findViewById(R.id.Cv_typ);
            txtDes =itemView.findViewById(R.id.CV_Des);

            btnViewMore = itemView.findViewById(R.id.userViewMoreBtn);
            btnApply=itemView.findViewById(R.id.userApplyBtn);
            parentLayout=itemView.findViewById(R.id.user_parent_layout);
        }
    }

}

