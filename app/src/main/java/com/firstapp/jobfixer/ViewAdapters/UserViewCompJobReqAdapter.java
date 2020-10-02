package com.firstapp.jobfixer.ViewAdapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firstapp.jobfixer.R;

import java.util.ArrayList;

public class UserViewCompJobReqAdapter extends RecyclerView.Adapter<UserViewCompJobReqAdapter.ViewHolder> {

    private ArrayList<String> ReqSendCompName = new ArrayList<>();
    private ArrayList<String> ReqSendCompEmail = new ArrayList<>();
    private ArrayList<String> ReqSendCompMessage = new ArrayList<>();
    private ArrayList<String> ReqSendCompDate = new ArrayList<>();

    private Context mContext;

    public UserViewCompJobReqAdapter(ArrayList<String> reqSendCompName, ArrayList<String> reqSendCompEmail, ArrayList<String> reqSendCompMessage, ArrayList<String> reqSendCompDate, Context mContext) {
        ReqSendCompName = reqSendCompName;
        ReqSendCompEmail = reqSendCompEmail;
        ReqSendCompMessage = reqSendCompMessage;
        ReqSendCompDate = reqSendCompDate;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        /** Create view from xml layout(layoutList)**/
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_apply_job_req_view_layout,parent,false);

        /** Create ViewHolder **/
        ViewHolder viewHolder = new ViewHolder(view);

        /** return View Holder **/
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.ReqCompN.setText(ReqSendCompName.get(position));
        holder.ReqCompMail.setText(ReqSendCompEmail.get(position));
        holder.ReqCompMsg.setText(ReqSendCompMessage.get(position));
        holder.ReqCompDate.setText(ReqSendCompDate.get(position));
    }

    @Override
    public int getItemCount() {
        return ReqSendCompName.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView ReqCompN,ReqCompMail,ReqCompMsg,ReqCompDate;
        RelativeLayout compReqViewLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            /** Accessing the Views and Button of Recycle View **/
            ReqCompN = itemView.findViewById(R.id.userViewCompName);
            ReqCompMail = itemView.findViewById(R.id.userViewCompEmail);
            ReqCompMsg = itemView.findViewById(R.id.userViewCompMessage);
            ReqCompDate = itemView.findViewById(R.id.userViewCompDate);
            compReqViewLayout = itemView.findViewById(R.id.cmpReqToUserRecycleLayout);

        }
    }
}
