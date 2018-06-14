package com.farm.mbaabu.greens;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Call;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.farm.mbaabu.greens.Model.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class Registration extends AppCompatActivity {

     //Creating an EditText
     EditText UserName, Contact, Password;

     //Creating a button
     Button register_btn;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

         //Assigning ID to the EditText.

         UserName= findViewById(R.id.edt_name);
         Contact = findViewById(R.id.edt_phone);

         Password =findViewById(R.id.edt_password);

         //Assigning ID to the Button
         register_btn =findViewById(R.id.reg2_btn);
        //initiating firbase to start up

        final FirebaseDatabase database =FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final ProgressDialog mDialog =new ProgressDialog(Registration.this);
                mDialog.setMessage("Please wait...");
                mDialog.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        //checking if the user already exists.

                        if(dataSnapshot.child(Contact.getText().toString()).exists()){
                            mDialog.dismiss();

                            Toast.makeText(Registration.this,"phone number already exists", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            mDialog.dismiss();

                            Users users =new Users(UserName.getText().toString(),Password.getText().toString());
                            table_user.child(Contact.getText().toString()).setValue(users);
                            Toast.makeText(Registration.this,"Sign up successful", Toast.LENGTH_SHORT).show();
                            finish();

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
