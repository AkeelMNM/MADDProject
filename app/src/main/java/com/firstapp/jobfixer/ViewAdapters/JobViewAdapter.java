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

import com.firstapp.jobfixer.CompanyEditJobActivity;
import com.firstapp.jobfixer.R;

import java.util.ArrayList;

public class JobViewAdapter extends RecyclerView.Adapter<JobViewAdapter.ViewHolder>{

    private ArrayList<String> mJobID = new ArrayList<>();
    private ArrayList<String> mJobName = new ArrayList<>();
    private ArrayList<String> mComName = new ArrayList<>();
    private ArrayList<String> mJobAdd = new ArrayList<>();
    private ArrayList<String> mJobType = new ArrayList<>();
    private ArrayList<String> mJobSal = new ArrayList<>();
    private Context mContext;

    public JobViewAdapter(ArrayList<String> mJobID, ArrayList<String> mJobName, ArrayList<String> mComName, ArrayList<String> mJobAdd, ArrayList<String> mJobType, ArrayList<String> mJobSal, Context mContext) {
        this.mJobID = mJobID;
        this.mJobName = mJobName;
        this.mComName = mComName;
        this.mJobAdd = mJobAdd;
        this.mJobType = mJobType;
        this.mJobSal = mJobSal;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.company_view_layout,parent,false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(mContext.getApplicationContext(), CompanyEditJobActivity.class);
                intent.putExtra("jName",mJobName.get(position));
                intent.putExtra("JCom",mComName.get(position));
                intent.putExtra("comAdd",mJobAdd.get(position));
                intent.putExtra("jType",mJobType.get(position));
                intent.putExtra("jSal",mJobSal.get(position));
                intent.putExtra("jID",mJobID.get(position));

                mContext.startActivity(intent);

            }
        });

        holder.btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtJN,txtCN,txtCA,txtJT,txtJS;
        Button btnEdit,btnRemove;
        RelativeLayout parentLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtJN=itemView.findViewById(R.id.Cv1);
            txtCN=itemView.findViewById(R.id.Cv2);
            txtCA=itemView.findViewById(R.id.Cv3);
            txtJS=itemView.findViewById(R.id.Cv4);
            txtJT=itemView.findViewById(R.id.Cv5);

            btnEdit = itemView.findViewById(R.id.bEdit);
            btnRemove=itemView.findViewById(R.id.btRemove);
            parentLayout=itemView.findViewById(R.id.parent_layout);
        }
    }

}

