package com.example.mediatheque;

import androidx.annotation.NonNull;
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

import com.example.mediatheque.ui.book.BookFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextMail_LoginPage,editTextPassword_LoginPage;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextMail_LoginPage = findViewById(R.id.editTextMail_LoginPage);                                         // initialise editTextMail_LoginPage
        editTextPassword_LoginPage = findViewById(R.id.editTextPassword_LoginPage);                                 // initialise editTextPassword_LoginPage

        Button buttonLogin = findViewById(R.id.buttonLogin_LoginPage);                                              // initialise buttonLogin_LoginPage
        buttonLogin.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();                                                                         // initialise instance for the Firebase Authenticator

        TextView textViewRegister_LoginPage = findViewById(R.id.textViewRegister_LoginPage);                        // initialise textViewRegister_LoginPage
        textViewRegister_LoginPage.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.textViewRegister_LoginPage:                                                                   // when the user clicks on the registration button on the home page
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));               // it sends them to the registration page
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

        if (pwd.isEmpty()){                                                                                         // if the password is empty
            editTextPassword_LoginPage.setError("Password is required.");                                           // shows an error message
            editTextPassword_LoginPage.requestFocus();                                                              // focus on the input field password
            return;
        }

        mAuth.signInWithEmailAndPassword(email,pwd).addOnCompleteListener(task -> {                                 // connect the user with his email and password
            if (task.isSuccessful()){                                                                               // when the task was successfully finish
                startActivity(new Intent(LoginActivity.this, MainActivity.class));                    // redirects the user to the main page
            }else {                                                                                                 // when the task was failed
                Toast.makeText(LoginActivity.this,                                                          // show a failure message
                        "Failed to login, please check your information.",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}