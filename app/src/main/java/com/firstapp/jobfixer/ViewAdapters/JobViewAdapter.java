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

public class JobViewAdapter extends RecyclerView.Adapter<JobViewAdapter.ViewHolder>{

    private ArrayList<String> mJobID = new ArrayList<>();
    private ArrayList<String> mJobTitle = new ArrayList<>();
    private ArrayList<String> mComName = new ArrayList<>();
    private ArrayList<String> mJobAdd = new ArrayList<>();
    private ArrayList<String> mJobType = new ArrayList<>();
    private ArrayList<String> mJobSal = new ArrayList<>();
    private ArrayList<String> mJobDes = new ArrayList<>();
    private ArrayList<String> mJobCate = new ArrayList<>();
    private Context mContext;

    public JobViewAdapter(ArrayList<String> mJobID, ArrayList<String> mJobTitle, ArrayList<String> mComName, ArrayList<String> mJobAdd, ArrayList<String> mJobType, ArrayList<String> mJobSal, ArrayList<String> mJobDes , ArrayList<String> mJobCate, Context mContext) {
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

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.company_view_layout,parent,false);

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

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
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

        holder.btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String id = mJobID.get(position);
                final DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child(DBMaster.Job.TABLE_NAME);
                dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.hasChild(id))
                        {
                            DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child(DBMaster.Job.TABLE_NAME).child(id);
                            dbRef.removeValue();
                            Toast.makeText(mContext.getApplicationContext(), "Job Deleted Successfully", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

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
        Button btnEdit,btnRemove;
        RelativeLayout parentLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTi =itemView.findViewById(R.id.Cv1);
            txtComNa =itemView.findViewById(R.id.Cv2);
            txtComAdd =itemView.findViewById(R.id.Cv3);
            txtSaary =itemView.findViewById(R.id.Cv4);
            txtType =itemView.findViewById(R.id.Cv5);
            txtDes =itemView.findViewById(R.id.Des_CV);

            btnEdit = itemView.findViewById(R.id.bEdit);
            btnRemove=itemView.findViewById(R.id.btRemove);
            parentLayout=itemView.findViewById(R.id.parent_layout);
        }
    }

}

