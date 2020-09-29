package com.firstapp.jobfixer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firstapp.jobfixer.Database.DBMaster;
import com.firstapp.jobfixer.Model.Helpcenter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.app.ProgressDialog.show;

public class HelpCenterActivity extends AppCompatActivity {


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

        hlpc = new Helpcenter();



        btnsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef= FirebaseDatabase.getInstance().getReference().child(DBMaster.HelpCenter.TABLE_NAME);
                //take inputs from user and assigning them to this instance (hlpc) of the student...
                hlpc.setName(editTextPName.getText().toString().trim());
                hlpc.setEmail(editTextPemail.getText().toString().trim());
                hlpc.setMessage(editTextPmsg.getText().toString().trim());

                //insert into the database
                dbRef.child("H02").setValue(hlpc);

                //feedback to the user via toast

                Toast.makeText(HelpCenterActivity.this, "Message Sent Succesfully", Toast.LENGTH_SHORT).show();

                clearControls();
            }
        });

        btnview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(HelpCenterActivity.this,AdminRequestsActivity.class);
                startActivity(intent);
            }
        });


    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }
}