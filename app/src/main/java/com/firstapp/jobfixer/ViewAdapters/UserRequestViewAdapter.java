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

import com.firstapp.jobfixer.AdminReplyActivity;
import com.firstapp.jobfixer.R;
import com.firstapp.jobfixer.UserReplyActivity;

import java.util.ArrayList;

public class UserRequestViewAdapter extends RecyclerView.Adapter<UserRequestViewAdapter.ViewHolder> {


    private ArrayList<String> Adminname = new ArrayList<>();
    private ArrayList<String> Adminmessage = new ArrayList<>();
    private ArrayList<String> Adminemail = new ArrayList<>();
    private Context mContext;

    public UserRequestViewAdapter(ArrayList<String> adminname, ArrayList<String> adminmessage, ArrayList<String> adminemail, Context mContext) {
        Adminname = adminname;
        Adminmessage = adminmessage;
        Adminemail = adminemail;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public UserRequestViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_view_requests_layout,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }



    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        holder.msg1.setText(Adminname.get(position));
        holder.msg2.setText(Adminmessage.get(position));
        holder.Email.setText(Adminemail.get(position));

        holder.viewMsgLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent msg=new Intent(mContext.getApplicationContext(), UserReplyActivity.class);
                msg.putExtra("adminname",Adminname.get(position));
                msg.putExtra("adminreplymessage",Adminmessage.get(position));
                msg.putExtra("adminemail",Adminemail.get(position));


                mContext.startActivity(msg);
            }
        });
    }

    @Override
    public int getItemCount() {
        return Adminmessage.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView msg1,msg2,Email;
        RelativeLayout viewMsgLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            msg1=itemView.findViewById(R.id.userViewMsg);
            Email=itemView.findViewById(R.id.textView7);
            msg2=itemView.findViewById(R.id.textViewUserSee);
            viewMsgLayout=itemView.findViewById(R.id.relativeLayout);
        }



    }









}
