package com.farm.mbaabu.greens;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.farm.mbaabu.greens.Model.Users;
import com.google.android.gms.common.api.Api;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Sign_in extends AppCompatActivity {

    EditText edt_phone,edt_password;

    Button btn_signIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        edt_password=findViewById(R.id.edt_password);
        edt_phone=findViewById(R.id.edt_phone);
        btn_signIn=findViewById(R.id.btn_signIn);


        //initiating firbase to start up

        final FirebaseDatabase database =FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        btn_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog mDialog =new ProgressDialog(Sign_in.this);
                mDialog.setMessage("Please wait...");
                mDialog.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        // Check if user does not exist in the database.


                        if (dataSnapshot.child(edt_phone.getText().toString()).exists()){

                            //Getting the user information
                            mDialog.dismiss();

                            Users users =dataSnapshot.child(edt_phone.getText().toString()).getValue(Users.class);

                            if (users.getPassword().equals(edt_password.getText().toString())) {

                                Toast.makeText(Sign_in.this,"Sign in successfully done!!",Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(Sign_in.this,"Sign in failed", Toast.LENGTH_SHORT).show(); }



                        }

                        else {
                            mDialog.dismiss();
                            Toast.makeText(Sign_in.this, "User does not exist", Toast.LENGTH_SHORT).show();
                        }







                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });

    }
}
