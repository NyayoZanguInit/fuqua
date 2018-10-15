package com.nyayozangu.labs.fuqua.activities;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.nyayozangu.labs.fuqua.R;
import com.nyayozangu.labs.fuqua.common.Common;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    final static Map<String, String> loginCredentials = new HashMap<>();

    private static final String TAG = "LoginActivity";
    // instantiate common functions
    private Common common = new Common();
    private Button loginBtn;
    private EditText employeeIdEditText, passwordEditText;
    private TextView forgot_passwordTextView, registerTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        employeeIdEditText = findViewById(R.id.employee_id);
        passwordEditText = findViewById(R.id.password);
        loginBtn = findViewById(R.id.login_button);

        //sample login cred to populate
        loginCredentials.put("001", "xyz");
        loginCredentials.put("002","abc");
        loginCredentials.put("003","qwert");

        loginBtn.setOnClickListener(this);

    }

    public void login(View view){
        Intent startMain =new Intent(this, MainActivity.class);
        startActivity(startMain);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_button:
                handleLogin();
                break;
            default:
                Log.d(TAG, "onClick: user clicked other view");
        }
    }

    private void handleLogin() {

        String employeeId = employeeIdEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        for (String idKey : loginCredentials.keySet()){
            if (employeeId.equals(idKey)){
                // id exists
                String passwordFromKey = loginCredentials.get(idKey);
                //check password
                if (passwordFromKey.equals(password)){
                    //password is correct
                    startActivity(new Intent(this, MainActivity.class));
                    finish();
                }else{
                    // password is wrong
                    Toast.makeText(getApplicationContext(), "Wrong Password", Toast.LENGTH_SHORT).show();
                }

            }else{
                //id does not exist
                //prompt warning
               // Toast.makeText(getApplicationContext(),"Employee ID doesn't exist", Toast.LENGTH_SHORT).show();
            }
        }


    }
}
