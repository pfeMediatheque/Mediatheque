package com.example.mediatheque.ui.connection;

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
import com.example.mediatheque.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String USERNAME_KEY = "username";                                                          // name of the USERNAME_KEY column in the table
    private static final String EMAIL_KEY = "email";                                                                // name of the EMAIL_KEY column in the table
    private FirebaseFirestore db;                                                                                   // object reference for Firebase Firestore
    private FirebaseAuth mAuth;                                                                                     // object reference for Firebase Authenticator
    private FirebaseUser mailVerificationCurrentUser;
    private EditText editTextUsername_RegisterPage,editTextMail_RegisterPage,
            editTextPassword_RegisterPage,editTextCnfPassword_RegisterPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = FirebaseFirestore.getInstance();                                                                       // initialise instance for the Firebase Firestore
        mAuth = FirebaseAuth.getInstance();                                                                         // initialise instance for the Firebase Authenticator
        mailVerificationCurrentUser = FirebaseAuth.getInstance().getCurrentUser();                                  // initialise the current user for the mail verification

        editTextUsername_RegisterPage = findViewById(R.id.editTextUsername_RegisterPage);                           // initialise editTextUsername_RegisterPage
        editTextMail_RegisterPage = findViewById(R.id.editTextMail_RegisterPage);                                   // initialise editTextMail_RegisterPage
        editTextPassword_RegisterPage = findViewById(R.id.editTextPassword_RegisterPage);                           // initialise editTextPassword_RegisterPage
        editTextCnfPassword_RegisterPage = findViewById(R.id.editTextCnfPassword_RegisterPage);                     // initialise editTextCnfPassword_RegisterPage

        Button buttonRegister = findViewById(R.id.buttonRegister_RegisterPage);                                     // initialise buttonRegister_RegisterPage
        buttonRegister.setOnClickListener(this);

        TextView alreadyRegistered = findViewById(R.id.textViewLogin_RegisterPage);                                 // initialise textViewLogin_RegisterPage
        alreadyRegistered.setOnClickListener(this);

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.textViewLogin_RegisterPage:                                                                   // when the user clicks on the already registered button on the register page
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));               // it sends them to the login page
                break;

            case R.id.buttonRegister_RegisterPage:                                                                  // when the user clicks on the register button
                buttonRegisterClicked();                                                                            // start the registration process
                break;
        }
    }

    private void buttonRegisterClicked() {
        String username = editTextUsername_RegisterPage.getText().toString().trim();                                //  get the value of the username
        String email = editTextMail_RegisterPage.getText().toString().trim();                                       //  get the value of the email
        String pwd = editTextPassword_RegisterPage.getText().toString().trim();                                     //  get the value of the password
        String cnf_pwd = editTextCnfPassword_RegisterPage.getText().toString().trim();                              //  get the value of the confirmation password

        if (username.isEmpty()){                                                                                    // if the username is empty
            editTextUsername_RegisterPage.setError("Username is required.");                                        // shows an error message
            editTextUsername_RegisterPage.requestFocus();                                                           // focus on the input field username
            return;
        }

        if (email.isEmpty()){                                                                                        // if the email is empty
            editTextMail_RegisterPage.setError("Email is required.");                                                // shows an error message
            editTextMail_RegisterPage.requestFocus();                                                                // focus on the input field email
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){                                                       // if the email not match with the structure of a email
            editTextMail_RegisterPage.setError("Please provide valid email.");                                       // shows an error message
            editTextMail_RegisterPage.requestFocus();                                                                // focus on the input field email
            return;
        }

        if (pwd.isEmpty()){                                                                                          // if the password is empty
            editTextPassword_RegisterPage.setError("Password is required.");                                         // shows an error message
            editTextPassword_RegisterPage.requestFocus();                                                            // focus on the input field password
            return;
        }

        if (pwd.length()<8){                                                                                         // if the password lower than 8
            editTextPassword_RegisterPage.setError("Give a password with more than 8 characters.");                  // shows an error message
            editTextPassword_RegisterPage.requestFocus();                                                            // focus on the input field password
            return;
        }

        if (cnf_pwd.isEmpty()){                                                                                      // if the confirmation password is empty
            editTextCnfPassword_RegisterPage.setError("Please reconfirm your password.");                            // shows an error message
            editTextCnfPassword_RegisterPage.requestFocus();                                                         // focus on the input field password
            return;
        }

        if (!pwd.equals(cnf_pwd)){                                                                                   // if the passwords do not match
            editTextCnfPassword_RegisterPage.setError("Password is not matching.");                                  // shows an error message
            editTextCnfPassword_RegisterPage.requestFocus();                                                         // focus on the input field confirmation password
            return;
        }

        mAuth.createUserWithEmailAndPassword(email,pwd).
                addOnCompleteListener(task -> {                                                                         // create user with him email and password
                    Map<String,Object> mapUser = new HashMap<>();                                                // hashmap will save the name of the fields on the database
                    mapUser.put(USERNAME_KEY,username);                                                          // put the value of username in the USERNAME_KEY column
                    mapUser.put(EMAIL_KEY,email);                                                                // put the value of email in the EMAIL_KEY column

                    db.collection("userCollection").add(mapUser)                                    // in database adds collection (table) named userCollection
                            .addOnSuccessListener(documentReference -> {                                                // when the task was successfully finish
                                Toast.makeText(RegisterActivity.this,                                           // show a successfully message
                                        "Verify your email address to stay connect on the application.",
                                        Toast.LENGTH_LONG).show();
                                assert mailVerificationCurrentUser != null;
                                mailVerificationCurrentUser.sendEmailVerification();                                                // sends an email to the current user's address
                                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));    // redirects the user to the login page

                            }).addOnFailureListener(documentReference -> {                                              // when the task was failed
                        Toast.makeText(RegisterActivity.this,                                                   // show a failure message
                                "Registration failed - Connection error with the database.",
                                Toast.LENGTH_LONG).show();
                    });
                });
        
    }
}