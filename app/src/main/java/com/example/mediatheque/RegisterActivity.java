package com.example.mediatheque;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String USERNAME_KEY = "username";
    private static final String EMAIL_KEY = "email";
    private static final String PWD_KEY = "pwd";
    private final DocumentReference userDocument = FirebaseFirestore.getInstance().document("userCollection/user");

    private EditText editTextUsername_RegisterPage,editTextMail_RegisterPage,editTextPassword_RegisterPage,editTextCnfPassword_RegisterPage;

    private Button buttonRegister;
    private TextView alreadyRegistered;

    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = FirebaseFirestore.getInstance();

        editTextUsername_RegisterPage = (EditText)findViewById(R.id.editTextUsername_RegisterPage);
        editTextMail_RegisterPage = (EditText)findViewById(R.id.editTextMail_RegisterPage);
        editTextPassword_RegisterPage = (EditText)findViewById(R.id.editTextPassword_RegisterPage);
        editTextCnfPassword_RegisterPage = (EditText)findViewById(R.id.editTextCnfPassword_RegisterPage);

        buttonRegister = (Button) findViewById(R.id.buttonRegister_RegisterPage);
        buttonRegister.setOnClickListener(this);

        alreadyRegistered = (TextView) findViewById(R.id.textViewLogin_RegisterPage);
        alreadyRegistered.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            /* When the user clicks on the already registered button on the register page it sends them to the home page */
            case R.id.textViewLogin_RegisterPage:
                Intent loginPage = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(loginPage);
                break;

            /* When the user clicks on the register button */
            case R.id.buttonRegister_RegisterPage:
                buttonRegisterClicked();
                break;
        }
    }

    private void buttonRegisterClicked() {
        String username = editTextUsername_RegisterPage.getText().toString().trim();                                 //  get the value of the username
        String email = editTextMail_RegisterPage.getText().toString().trim();                                        //  get the value of the email
        String pwd = editTextPassword_RegisterPage.getText().toString().trim();                                      //  get the value of the password
        String cnf_pwd = editTextCnfPassword_RegisterPage.getText().toString().trim();                               //  get the value of the confirmation password

        /* If the username is empty */
        if (username.isEmpty()){
            editTextUsername_RegisterPage.setError("Username is required.");                                         // shows an error message
            editTextUsername_RegisterPage.requestFocus();                                                            // focus on the input field username
            return;
        }

        /* If the email is empty */
        if (email.isEmpty()){
            editTextMail_RegisterPage.setError("Email is required.");                                                // shows an error message
            editTextMail_RegisterPage.requestFocus();                                                                // focus on the input field email
            return;
        }

        /* If the email not match with the structure of a email */
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextMail_RegisterPage.setError("Please provide valid email.");                                       // shows an error message
            editTextMail_RegisterPage.requestFocus();                                                                // focus on the input field email
            return;
        }

        /* If the password is empty */
        if (pwd.isEmpty()){
            editTextPassword_RegisterPage.setError("Password is required.");                                         // shows an error message
            editTextPassword_RegisterPage.requestFocus();                                                            // focus on the input field password
            return;
        }

        /* If the password lower than 8 */
        if (pwd.length()<8){
            editTextPassword_RegisterPage.setError("Give a password with more than 8 characters.");                  // shows an error message
            editTextPassword_RegisterPage.requestFocus();                                                            // focus on the input field password
            return;
        }

        /* If the confirmation password is empty */
        if (cnf_pwd.isEmpty()){
            editTextCnfPassword_RegisterPage.setError("Please reconfirm your password.");                            // shows an error message
            editTextCnfPassword_RegisterPage.requestFocus();                                                         // focus on the input field password
            return;
        }

        /* If the passwords do not match */
        if (!pwd.equals(cnf_pwd)){
            editTextCnfPassword_RegisterPage.setError("Password is not matching.");                                  // shows an error message
            editTextCnfPassword_RegisterPage.requestFocus();                                                         // focus on the input field confirmation password
            return;
        }

        Map<String,Object> userCollection = new HashMap<String, Object>();
        userCollection.put(USERNAME_KEY,username);
        userCollection.put(EMAIL_KEY,email);
        userCollection.put(PWD_KEY,pwd);

        userDocument.set(userCollection).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(RegisterActivity.this,"Successful",Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @NotNull Exception e) {
                Toast.makeText(RegisterActivity.this,"Failed",Toast.LENGTH_SHORT).show();
            }
        });
    }
}