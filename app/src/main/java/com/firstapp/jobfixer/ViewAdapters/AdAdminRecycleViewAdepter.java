package com.firstapp.jobfixer.ViewAdapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firstapp.jobfixer.JobAdAdminViewActivity;
import com.firstapp.jobfixer.JobAdUpdateActivity;
import com.firstapp.jobfixer.R;

import java.util.ArrayList;

public class AdAdminRecycleViewAdepter extends RecyclerView.Adapter<AdAdminRecycleViewAdepter.ViewHolder> {

    private ArrayList<String> jobCat = new ArrayList<>();
    private ArrayList<String> jobName = new ArrayList<>();
    private ArrayList<String> compName = new ArrayList<>();
    private ArrayList<String> compLocation = new ArrayList<>();
    private ArrayList<String> jobQualification = new ArrayList<>();
    private ArrayList<String> jobSalary = new ArrayList<>();
    private ArrayList<String> jobType = new ArrayList<>();
    private ArrayList<String> jobID = new ArrayList<>();
    private Context mContext;

    public AdAdminRecycleViewAdepter(ArrayList<String> jobCat, ArrayList<String> jobName, ArrayList<String> compName, ArrayList<String> compLocation, ArrayList<String> jobQualification, ArrayList<String> jobSalary, ArrayList<String> jobType, ArrayList<String> jobID, Context mContext) {
        this.jobCat = jobCat;
        this.jobName = jobName;
        this.compName = compName;
        this.compLocation = compLocation;
        this.jobQualification = jobQualification;
        this.jobSalary = jobSalary;
        this.jobType = jobType;
        this.jobID = jobID;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Create view from xml layout(layoutList)
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_ad_view_recyleview_layout,parent,false);

        //Create ViewHolder
        ViewHolder viewHolder = new ViewHolder(view);

        //return View Holder
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        //set Texts
        holder.jobCato.setText(jobCat.get(position));
        holder.jobTitle.setText(jobName.get(position));
        holder.comName.setText(compName.get(position));
        holder.comLoc.setText(compLocation.get(position));
        holder.jobSal.setText(jobSalary.get(position));
        holder.jobT.setText(jobType.get(position));
        holder.jobQua.setText(jobQualification.get(position));
        holder.jobId.setText(jobID.get(position));

        holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, JobAdUpdateActivity.class);
                intent.putExtra("jCat",jobCat.get(position));
                intent.putExtra("jTitle",jobName.get(position));
                intent.putExtra("cName",compName.get(position));
                intent.putExtra("cLoc",compLocation.get(position));
                intent.putExtra("jSal",jobSalary.get(position));
                intent.putExtra("jType",jobType.get(position));
                intent.putExtra("jQua",jobQualification.get(position));
                intent.putExtra("jID",jobID.get(position));
                mContext.startActivity(intent);


            }
        });

        holder.btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }


    @Override
    public int getItemCount() {
        return jobName.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView jobTitle,comName,comLoc,jobSal,jobT,jobQua,jobId,jobCato;
        RelativeLayout adminViewLayout;
        Button btnUpdate,btnRemove;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            jobTitle = itemView.findViewById(R.id.textViewJobTit);
            comName = itemView.findViewById(R.id.textCompany);
            comLoc = itemView.findViewById(R.id.textViewCL);
            jobQua = itemView.findViewById(R.id.textQualifuc);
            jobSal = itemView.findViewById(R.id.textViewJS);
            jobT = itemView.findViewById(R.id.textViewJT);
            jobId=itemView.findViewById(R.id.txtJobID);
            jobCato=itemView.findViewById(R.id.txtJobCat);
            adminViewLayout = itemView.findViewById(R.id.adminAdViewLayout);

            btnRemove = itemView.findViewById(R.id.btnRemove);
            btnUpdate = itemView.findViewById(R.id.btnUpdate);
        }
    }
}
