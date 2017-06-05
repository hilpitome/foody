package com.example.hilary.foody;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by hilary on 6/6/17.
 */

public class SignInActivity extends AppCompatActivity implements  View.OnClickListener{
    private FirebaseAuth mAuth;
    private String TAG = "SignInActivity";
    ProgressDialog progressDialog;
    private EditText emailEditTextView, passwordEditTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mAuth = FirebaseAuth.getInstance();

    }

    public void signIn(){
        String email = emailEditTextView.getText().toString().trim();
        String password = passwordEditTextView.getText().toString().trim();

        View focusView = null;
        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            emailEditTextView.setError("enter email");
            focusView = emailEditTextView;
            focusView.requestFocus();
            return;

        } else if (!isEmailValid(email)) {
            emailEditTextView.setError("enter valid email");
            focusView = emailEditTextView;
            focusView.requestFocus();
            return;

        }
        // Check for a valid password, if the user entered one.

        if(!isPasswordValid(password)) {
            passwordEditTextView.setError("must be more than five characters");
            focusView = passwordEditTextView;
            focusView.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(password)) {
            passwordEditTextView.setError("enter password");
            focusView = passwordEditTextView;
            focusView.requestFocus();
            return;
        }
        showProgressDialogue("creating user account");
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            removeProgressDialogue();
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent i = new Intent(SignInActivity.this, MainActivity.class);
                            startActivity(i);

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(SignInActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });
    }
    public  void  showProgressDialogue(String message){
        progressDialog = new ProgressDialog(SignInActivity.this, R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage(message);
        progressDialog.show();

    }

    public void removeProgressDialogue(){
        if (progressDialog!=null){
            progressDialog.dismiss();
        }
    }

    private boolean isEmailValid(String email) {

        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {

        if(password.length() < 6){
            return false;
        } else {
            return true;
        }
    }

    public void signUp(){
        Intent i = new Intent(SignInActivity.this, SignUpAcitivity.class);
        startActivity(i);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_LogIn:
                signIn();
                break;
            case R.id.link_register:
                signUp();
                break;

        }

    }
}
