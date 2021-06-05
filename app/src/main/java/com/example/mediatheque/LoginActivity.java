package com.example.mediatheque;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        register = (TextView) findViewById(R.id.textViewRegister_LoginPage);
        register.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            /* When the user clicks on the registration button on the home page it sends them to the registration page */
            case R.id.textViewRegister_LoginPage:
                Intent registerPage = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(registerPage);
                break;
        }
    }
}