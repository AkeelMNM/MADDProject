package com.firstapp.jobfixer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firstapp.jobfixer.Database.DBMaster;
//import com.firstapp.jobfixer.Model.UserRequest;
import com.firstapp.jobfixer.Model.UserRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdminReplyActivity extends AppCompatActivity {

    public static final String UPID_PREFIX ="MSG";

    private ArrayList<String> UPID = new ArrayList<>();

    TextView display2,display3;
    EditText replyname,replymsg,replyemail;

    EditText editTextAdminToName,editTextAdminToemail,editTextAdminmsg;
    Button btnadminsend;
    UserRequest ur;
    String uid;

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
        uid=bundle.getString("uid");

        replyname.setText(bundle.getString("name"));
       // replyemail.setText(bundle.getString("email"));

        editTextAdminToName.setText(bundle.getString("name"));
        editTextAdminToemail.setText(SessionApplication.getUserEmail());

      /*  btnadminsend.setOnClickListener(new View.OnClickListener() {
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
        });*/

////////////////////////////////////////////////////////////////////////////////////////////////////////

        btnadminsend.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                dbRef= FirebaseDatabase.getInstance().getReference().child(DBMaster.UserRequest.TABLE_NAME);
                dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        ur = new UserRequest();
                        //take inputs from user and assigning them to this instance (hlpc) of the student...
                        ur.setAdminname(editTextAdminToName.getText().toString().trim());
                        ur.setAdminemail(editTextAdminToemail.getText().toString().trim());
                        ur.setAdminmsg(editTextAdminmsg.getText().toString().trim());
                        ur.setUserId(uid);


                        for (DataSnapshot st:dataSnapshot.getChildren()){

                            UPID.add(st.getKey().toString());

                        }


                        String id = null ;
                        int alSize = UPID.size();
                        alSize++;
                        id = UPID_PREFIX + alSize;
                        if(UPID.contains(id))
                        {
                            alSize++;
                            id = UPID_PREFIX + alSize;
                        }


                        //insert into the database
                        dbRef.child(id).setValue(ur);
                        //feedback to the user via toast

                        Toast.makeText(AdminReplyActivity.this, "Message Reply Succesfully", Toast.LENGTH_SHORT).show();




                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });



            }
        });








        //////////////////////////////////////////////////////////////////////////////////////////////////////////////

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

        Intent intent = new Intent(AdminReplyActivity.this,AdminLoginActivity.class);
        startActivity(intent);


    }

    private void helpCenter() {
        Intent intent = new Intent(AdminReplyActivity.this,HelpCenterActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        /** check user is log in**/
        if(SessionApplication.getUserName().equals("")){
            Intent intent = new Intent(AdminReplyActivity.this,AdminLoginActivity.class);
            startActivity(intent);
        }

    }
}