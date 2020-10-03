package com.firstapp.jobfixer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firstapp.jobfixer.Database.DBMaster;
//import com.firstapp.jobfixer.Model.UserRequest;
import com.firstapp.jobfixer.Model.UserRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminReplyActivity extends AppCompatActivity {

    TextView display2,display3;
    EditText replyname,replymsg,replyemail;

    EditText editTextAdminToName,editTextAdminToemail,editTextAdminmsg;
    Button btnadminsend;

    DatabaseReference dbRef;
    //  Helpcenter AdminReplymsg;

    UserRequest Adminsend;


    private void clearControls(){

        editTextAdminmsg.setText("");

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_reply);


        display2=findViewById(R.id.displayname);
        display3=findViewById(R.id.displaymessage);
        replyname=findViewById(R.id.AdminSendTo);
        replymsg=findViewById(R.id.AdminSendMsg);
        replyemail=findViewById(R.id.Adminsendemail);


        editTextAdminToName =findViewById(R.id.AdminSendTo);
        editTextAdminToemail=findViewById(R.id.Adminsendemail);
        editTextAdminmsg=findViewById(R.id.AdminSendMsg);
        btnadminsend=findViewById(R.id.btnAdminSend);

        Adminsend = new UserRequest();





        Bundle bundle =getIntent().getExtras();
        display2.setText(bundle.getString("name"));
        display3.setText(bundle.getString("msg"));

        replyname.setText(bundle.getString("name"));
       // replyemail.setText(bundle.getString("email"));

        editTextAdminToName.setText(bundle.getString("name"));







        btnadminsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef= FirebaseDatabase.getInstance().getReference().child(DBMaster.UserRequest.TABLE_NAME);
                //take inputs from user and assigning them to this instance (hlpc) of the student...
                Adminsend.setAdminname(editTextAdminToName.getText().toString().trim());
                Adminsend.setAdminemail(editTextAdminToemail.getText().toString().trim());
                Adminsend.setAdminmsg(editTextAdminmsg.getText().toString().trim());


                //insert into the database
                dbRef.child("MSG1").setValue(Adminsend);

                //feedback to the user via toast

                Toast.makeText(AdminReplyActivity.this, "Message Sent Succesfully", Toast.LENGTH_SHORT).show();


            }
        });



    }




    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }
}