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
import com.firstapp.jobfixer.Database.DBMaster;
import com.firstapp.jobfixer.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.ObjectInputStream;
import java.util.ArrayList;

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

        holder.txtTi.setText(mJobTitle.get(position));
        holder.txtComNa.setText(mComName.get(position));
        holder.txtComAdd.setText(mJobAdd.get(position));
        holder.txtDes.setText(mJobDes.get(position));
        holder.txtSaary.setText(mJobSal.get(position));
        holder.txtType.setText(mJobType.get(position));

        holder.btnViewMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(mContext.getApplicationContext(), CompanyEditJobActivity.class);
                intent.putExtra("jTitle",mJobTitle.get(position));
                intent.putExtra("JComName",mComName.get(position));
                intent.putExtra("comAdd",mJobAdd.get(position));
                intent.putExtra("jType",mJobType.get(position));
                intent.putExtra("jSal",mJobSal.get(position));
                intent.putExtra("jDes",mJobDes.get(position));
                intent.putExtra("jCate",mJobCate.get(position));
                intent.putExtra("jID",mJobID.get(position));

                mContext.startActivity(intent);

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

