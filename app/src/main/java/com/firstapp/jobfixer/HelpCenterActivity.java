package com.firstapp.jobfixer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firstapp.jobfixer.Database.DBMaster;
import com.firstapp.jobfixer.Model.Helpcenter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static android.app.ProgressDialog.show;

public class HelpCenterActivity extends AppCompatActivity {

    public static final String HCID_PREFIX ="H0";

    private ArrayList<String> HCID = new ArrayList<>();

    EditText editTextPName,editTextPemail,editTextPmsg;
    Button btnsend,btnview;
    DatabaseReference dbRef;
    Helpcenter hlpc;

    private void clearControls(){
        editTextPName.setText("");
        editTextPemail.setText("");
        editTextPmsg.setText("");

    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_center);


        editTextPName=findViewById(R.id.editTextPName);
        editTextPemail=findViewById(R.id.editTextPemail);
        editTextPmsg=findViewById(R.id.editTextPmsg);

        btnsend=findViewById(R.id.btnsend);
        btnview=findViewById(R.id.btnview);

        editTextPName.setText(SessionApplication.getUserName());
        editTextPemail.setText(SessionApplication.getUserEmail());



        btnsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef= FirebaseDatabase.getInstance().getReference().child(DBMaster.HelpCenter.TABLE_NAME);
                dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        hlpc = new Helpcenter();
                        //take inputs from user and assigning them to this instance (hlpc) of the student...
                        hlpc.setName(editTextPName.getText().toString().trim());
                        hlpc.setEmail(editTextPemail.getText().toString().trim());
                        hlpc.setMessage(editTextPmsg.getText().toString().trim());

                        for (DataSnapshot st:dataSnapshot.getChildren()){

                            HCID.add(st.getKey().toString());

                        }


                        String id = null ;
                        int alSize = HCID.size();
                        alSize++;
                        id = HCID_PREFIX + alSize;
                        if(HCID.contains(id))
                        {
                            alSize++;
                            id = HCID_PREFIX + alSize;
                        }


                        //insert into the database
                        dbRef.child(id).setValue(hlpc);
                        //feedback to the user via toast

                        Toast.makeText(HelpCenterActivity.this, "Message Sent Successfully", Toast.LENGTH_SHORT).show();

                        clearControls();


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });



            }
        });

        btnview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(HelpCenterActivity.this,HelpCenterActivity.class);
                startActivity(intent);
            }
        });


    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    /** Menu bar actions**/
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_help:
                helpCenter();
                return true;
            case R.id.action_logout:
                logOut();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /** Logout from device**/
    private void logOut() {
        SessionApplication.setUserID("");
        SessionApplication.setUserName("");
        SessionApplication.setUserType("");
        SessionApplication.setUserEmail("");

        Intent intent = new Intent(HelpCenterActivity.this,LoginActivity.class);
        startActivity(intent);


    }

    private void helpCenter() {
        Intent intent = new Intent(HelpCenterActivity.this,HelpCenterActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        /** check user is log in**/
        if(SessionApplication.getUserName().equals("")){
            Intent intent = new Intent(HelpCenterActivity.this,LoginActivity.class);
            startActivity(intent);
        }

    }
}