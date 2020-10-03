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

import java.util.ArrayList;

public class AdminRequestViewAdapter extends RecyclerView.Adapter<AdminRequestViewAdapter.ViewHolder>{

        private ArrayList<String> username = new ArrayList<>();
        private ArrayList<String> message = new ArrayList<>();
        private ArrayList<String> email = new ArrayList<>();


        private Context mContext;

    public AdminRequestViewAdapter(ArrayList<String> username, ArrayList<String> message, ArrayList<String> email, Context mContext) {
        this.username = username;
        this.message = message;
        this.email = email;
        this.mContext = mContext;




    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_view_requests_layout,parent,false);
      ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

            holder.msg1.setText(username.get(position));
            holder.msg2.setText(message.get(position));
            holder.Email.setText(email.get(position));

            holder.viewMsgLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent msg=new Intent(mContext.getApplicationContext(),AdminReplyActivity.class);
                    msg.putExtra("name",username.get(position));
                    msg.putExtra("msg",message.get(position));
                    msg.putExtra("email",email.get(position));


                    mContext.startActivity(msg);
                }
            });
    }

    @Override
    public int getItemCount() {
        return message.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
            TextView msg1,msg2,Email;
            RelativeLayout viewMsgLayout;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                msg1=itemView.findViewById(R.id.AdminViewMsg);
                Email=itemView.findViewById(R.id.usermail);
                msg2=itemView.findViewById(R.id.textViewAdminSee);
                viewMsgLayout=itemView.findViewById(R.id.requestLayoutList);
            }



        }

}

