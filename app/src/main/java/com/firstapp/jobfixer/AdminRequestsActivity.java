package com.firstapp.jobfixer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;

import com.firstapp.jobfixer.Database.DBMaster;
import com.firstapp.jobfixer.ViewAdapters.AdminRequestViewAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdminRequestsActivity extends AppCompatActivity {

    private static final String TAG ="AdminRequestsActivity";
    private ArrayList<String> name = new ArrayList<>();
    private ArrayList<String> message = new ArrayList<>();
    private ArrayList<String> email = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_requests);
        initImageBitmaps();
    }

    private void initImageBitmaps() {

        Query data= FirebaseDatabase.getInstance().getReference().child(DBMaster.HelpCenter.TABLE_NAME);
        data.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    name.add(ds.child(DBMaster.HelpCenter.COLUM_NAME_USERNAME).getValue().toString());
                    message.add(ds.child(DBMaster.HelpCenter.COLUM_NAME_MESSAGE).getValue().toString());
                    email.add(ds.child(DBMaster.HelpCenter.COLUM_NAME_EMAIL).getValue().toString());
                    initRecyclerView();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: started");
        RecyclerView recyclerView =(RecyclerView) findViewById(R.id.reqrecycler_view);
        AdminRequestViewAdapter adapter = new AdminRequestViewAdapter(name,message,email,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
    }


}