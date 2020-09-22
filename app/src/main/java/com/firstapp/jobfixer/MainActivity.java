package com.firstapp.jobfixer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;

import com.firstapp.jobfixer.ViewAdapters.AdRecycleViewAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG ="Main Activity" ;
    private ArrayList<String> mJobName = new ArrayList<>();
    private ArrayList<String> mCompName = new ArrayList<>();
    private ArrayList<String> mCompLocation = new ArrayList<>();
    private ArrayList<String> mJobQualification = new ArrayList<>();
    private ArrayList<String> mJobSalary = new ArrayList<>();
    private ArrayList<String> mJobType = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initImageBitmaps();


    }

    private void initImageBitmaps() {
        mJobName.add("SE");
        mCompName.add("IFS");
        mCompLocation.add("Colombo");
        mJobSalary.add("100000");
        mJobType.add("FullTime");
        mJobQualification.add("2 Years");

        mJobName.add("IT");
        mCompName.add("IFS");
        mCompLocation.add("Colombo 03");
        mJobSalary.add("100000");
        mJobType.add("FullTime");
        mJobQualification.add("3 Years");
        initRecyclerView();

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    private void initRecyclerView() {
        Log.d(TAG, "initRecyclerView: started");
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        AdRecycleViewAdapter adapter = new AdRecycleViewAdapter(mJobName,mCompName,mCompLocation,mJobQualification,mJobSalary,mJobType,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        for(int i=0;i<4;i++){ recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));}


    }
}