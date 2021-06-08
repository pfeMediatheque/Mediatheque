package com.example.mediatheque;


import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextMail_LoginPage,editTextPassword_LoginPage;
    private FirebaseAuth lAuth;
    private FirebaseUser mailVerificationCurrentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextMail_LoginPage = findViewById(R.id.editTextMail_LoginPage);                                         // initialise editTextMail_LoginPage
        editTextPassword_LoginPage = findViewById(R.id.editTextPassword_LoginPage);                                 // initialise editTextPassword_LoginPage

        Button buttonLogin = findViewById(R.id.buttonLogin_LoginPage);                                              // initialise buttonLogin_LoginPage
        buttonLogin.setOnClickListener(this);

        TextView textViewRegister_LoginPage = findViewById(R.id.textViewRegister_LoginPage);                        // initialise textViewRegister_LoginPage
        textViewRegister_LoginPage.setOnClickListener(this);

        TextView textViewResetPassword_LoginPage = findViewById(R.id.textViewResetPassword_LoginPage);              // initialise textViewResetPassword_LoginPage
        textViewResetPassword_LoginPage.setOnClickListener(this);

        lAuth = FirebaseAuth.getInstance();                                                                         // initialise instance for the Firebase Authenticator

        mailVerificationCurrentUser = FirebaseAuth.getInstance().getCurrentUser();                                  // initialise the current user for the mail verification

        if (lAuth.getCurrentUser() != null){                                                                        // if the user is already registered so if the current user is not empty
            if (Objects.requireNonNull(mailVerificationCurrentUser).isEmailVerified()){                             // if the user has verified his email address
                startActivity(new Intent(LoginActivity.this, MainActivity.class));                    // launches the application directly on the main page - the user will not have to log in each time
            }
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.textViewRegister_LoginPage:                                                                   // when the user clicks on the registration button on the home page
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));               // redirects the user to the registration page
                break;

            case R.id.textViewResetPassword_LoginPage:                                                              // when the user clicks on the reset password button on the home page
                startActivity(new Intent(LoginActivity.this, ResetPasswordActivity.class));          // redirects the user to the reset password page
                break;

            case R.id.buttonLogin_LoginPage:                                                                        //  when the user clicks on the login button on the home page
                buttonLoginClicked();                                                                               // start the login process
                break;
        }
    }

    private void buttonLoginClicked() {
        String email = editTextMail_LoginPage.getText().toString().trim();                                          //  get the value of the email
        String pwd = editTextPassword_LoginPage.getText().toString().trim();                                        //  get the value of the password

        if (email.isEmpty()){                                                                                       // if the email is empty
            editTextMail_LoginPage.setError("Email is required.");                                                  // shows an error message
            editTextMail_LoginPage.requestFocus();                                                                  // focus on the input field email
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){                                                       // if the email not match with the structure of a email
            editTextMail_LoginPage.setError("Please provide valid email.");                                         // shows an error message
            editTextMail_LoginPage.requestFocus();                                                                  // focus on the input field email
            return;
        }

        if (pwd.isEmpty()){                                                                                         // if the password is empty
            editTextPassword_LoginPage.setError("Password is required.");                                           // shows an error message
            editTextPassword_LoginPage.requestFocus();                                                              // focus on the input field password
            return;
        }

        lAuth.signInWithEmailAndPassword(email,pwd).addOnCompleteListener(task -> {                                // connect the user with his email and password
            if (task.isSuccessful()) {                                                                             // when the task was successfully finish
                if (mailVerificationCurrentUser.isEmailVerified()){                                                // if the user has verified his email address
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));               // redirects the user to the main page
                }else {                                                                                            // if email is not verified
                    Toast.makeText(LoginActivity.this,                                                     // show a failure message
                            "Please verify your email address.",
                            Toast.LENGTH_LONG).show();
                }
            }else{                                                                                                 // if the information provided does not correspond to any registered account
                Toast.makeText(LoginActivity.this,                                                         // show a failure message
                        "Please check the information provided.",
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}