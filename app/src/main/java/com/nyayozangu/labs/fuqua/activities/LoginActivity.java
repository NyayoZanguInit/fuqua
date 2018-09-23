package com.nyayozangu.labs.fuqua.activities;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.nyayozangu.labs.fuqua.R;

public class LoginActivity extends AppCompatActivity {
    Button Login;
    EditText Employee_id, password;
    TextView forgot_password, register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }

    public void login(View view){
        Intent startMain =new Intent(this, MainActivity.class);
        startActivity(startMain);
    }

}
