package com.farm.mbaabu.greens;

import android.app.Activity;
import android.content.Intent;
import android.provider.SyncStateContract;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;

public class login extends AppCompatActivity {

    Button btn_signin,btn_signup;
    TextView text_slogan;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        btn_signin=findViewById(R.id.btn_signin);

        btn_signup=findViewById(R.id.btn_signup);
        //have to change the font of the text
        text_slogan=findViewById(R.id.text_slogan);

        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Sign_in =new Intent(login.this, com.farm.mbaabu.greens.Sign_in.class);
                startActivity(Sign_in);

            }
        });

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Registration =new Intent(login.this, com.farm.mbaabu.greens.Registration.class);
                startActivity(Registration);


            }
        });




    }
}
