package com.firstapp.jobfixer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.firstapp.jobfixer.Model.Helpcenter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.app.ProgressDialog.show;

public class MainActivity extends AppCompatActivity {

    EditText editTextPName,editTextPemail,editTextPmsg;
    Button btnsend,btnupdate,btndelete,btnview;
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
        setContentView(R.layout.activity_main);

        editTextPName=findViewById(R.id.editTextPName);
        editTextPemail=findViewById(R.id.editTextPemail);
        editTextPmsg=findViewById(R.id.editTextPmsg);

        btnsend=findViewById(R.id.btnsend);
        btnupdate=findViewById(R.id.btnupdate);
        btndelete=findViewById(R.id.btndelete);
        btnview=findViewById(R.id.btnview);


        hlpc = new Helpcenter();



        btnsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef= FirebaseDatabase.getInstance().getReference().child("Helpcenter");
                //take inputs from user and assigning them to this instance (hlpc) of the student...
                hlpc.setName(editTextPName.getText().toString().trim());
                hlpc.setEmail(editTextPemail.getText().toString().trim());
                hlpc.setMessage(editTextPmsg.getText().toString().trim());

                //insert into the database
                dbRef.push().setValue(hlpc);

                //feedback to the user via toast
               // Toast.makeText(getApplicationContext(), text:"Data Saved Succesfully",Toast.LENGTH_SHORT).show();
                clearControls();
            }
        });

















        }




    //}

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }
}