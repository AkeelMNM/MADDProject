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

import com.firstapp.jobfixer.R;

import java.util.ArrayList;

public class JobViewRequestForComAdapter extends RecyclerView.Adapter<JobViewRequestForComAdapter.ViewHolder> {

    private ArrayList<String> ReqUserName = new ArrayList<>();
    private ArrayList<String> ReqUserEmail = new ArrayList<>();
    private ArrayList<String> ReqUserDate = new ArrayList<>();
    private ArrayList<String> ReqUserJobName = new ArrayList<>();


    private Context mContext;

    public JobViewRequestForComAdapter(ArrayList<String> reqUserName, ArrayList<String> reqUserEmail, ArrayList<String> reqUserDate, ArrayList<String> reqUserJobName, Context mContext) {
        ReqUserName = reqUserName;
        ReqUserEmail = reqUserEmail;
        ReqUserDate = reqUserDate;
        ReqUserJobName = reqUserJobName;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        /** Create view from xml layout(layoutList)**/
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.resume_req_comp_view_layout,parent,false);

        /** Create ViewHolder **/
        ViewHolder viewHolder = new ViewHolder(view);

        /** return View Holder **/
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.ReqUserN.setText(ReqUserName.get(position));
        holder.ReqUserMail.setText(ReqUserEmail.get(position));
        holder.ReqUserDate.setText(ReqUserDate.get(position));
        holder.ReqUserJobTitle.setText(ReqUserJobName.get(position));

        holder.compReqViewLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
            }
        });
    }

    @Override
    public int getItemCount() {
        return ReqUserName.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView ReqUserN,ReqUserMail,ReqUserDate,ReqUserJobTitle;
        RelativeLayout compReqViewLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            /** Accessing the Views and Button of Recycle View **/
            ReqUserN = itemView.findViewById(R.id.textViewUName);
            ReqUserMail = itemView.findViewById(R.id.textViewUnEmail);
            ReqUserDate = itemView.findViewById(R.id.textViewDate);
            ReqUserJobTitle=itemView.findViewById(R.id.applyJobName);
            compReqViewLayout = itemView.findViewById(R.id.JobRequestLayout);

        }
    }
}
