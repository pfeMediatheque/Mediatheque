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

public class ResetPasswordActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextMail_ResetPasswordPage;
    private FirebaseAuth rAuth;
    private FirebaseUser mailVerificationCurrentUserForPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        editTextMail_ResetPasswordPage = findViewById(R.id.editTextMail_ResetPasswordPage);                         // initialise editTextMail_ResetPasswordPage

        Button buttonResetPassword_ResetPasswordPage = findViewById(R.id.buttonResetPassword_ResetPasswordPage);    // initialise buttonResetPassword_ResetPasswordPage
        buttonResetPassword_ResetPasswordPage.setOnClickListener(this);

        TextView textViewLogin_ResetPasswordPage = findViewById(R.id.textViewLogin_ResetPasswordPage);              // initialise textViewLogin_ResetPasswordPage
        textViewLogin_ResetPasswordPage.setOnClickListener(this);

        rAuth = FirebaseAuth.getInstance();                                                                         // initialise instance for the Firebase Authenticator

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonResetPassword_ResetPasswordPage:                                                        // when the user clicks on the reset password button
                buttonResetPasswordClicked();                                                                       // start the reset password process
                break;

            case R.id.textViewLogin_ResetPasswordPage:                                                              // when the user clicks on textViewLogin_ResetPasswordPage
                startActivity(new Intent(ResetPasswordActivity.this, LoginActivity.class));          // redirects the user to the login page
                break;

        }
    }

    private void buttonResetPasswordClicked() {
        String emailForResetPassword = editTextMail_ResetPasswordPage.getText().toString().trim();                  //  get the value of the email

        if (emailForResetPassword.isEmpty()){                                                                       // if the email is empty
            editTextMail_ResetPasswordPage.setError("Email is required.");                                          // shows an error message
            editTextMail_ResetPasswordPage.requestFocus();                                                          // focus on the input field email
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(emailForResetPassword).matches()){                                      // if the email not match with the structure of a email
            editTextMail_ResetPasswordPage.setError("Please provide valid email.");                                 // shows an error message
            editTextMail_ResetPasswordPage.requestFocus();                                                          // focus on the input field email
        }

        rAuth.sendPasswordResetEmail(emailForResetPassword).addOnCompleteListener(task -> {
            if (task.isSuccessful()){                                                                                  // when the task was successfully finish
                Toast.makeText(ResetPasswordActivity.this,                                                     // show a successfully message
                        "Please check your email address.",
                        Toast.LENGTH_LONG).show();
                startActivity(new Intent(ResetPasswordActivity.this, LoginActivity.class));
            }else{                                                                                                     // if the email address does not exist
                Toast.makeText(ResetPasswordActivity.this,                                                     // show a error message
                        "This email address does not exist, please check your information.",
                        Toast.LENGTH_LONG).show();
            }
        });

    }
}