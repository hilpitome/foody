package com.example.hilary.foody;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hilary.foody.R;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

/**
 * Created by hilary on 4/5/17.
 */

public class SignInAcitivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, View.OnClickListener {
    GoogleApiClient mGoogleApiClient;

    private static final int RC_SIGN_IN = 9001;
    private static final String TAG = "GoogleActivity";


    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]

    // [START declare_auth_listener]
    private FirebaseAuth.AuthStateListener mAuthListener;
    // [END declare_auth_listener]

    ProgressDialog progressDialog;
    private EditText emailEditTextView;
    private EditText passwordEditTextView;
    private Button btnSignUp;
    private String email;
    private String password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        // Views
        emailEditTextView = (EditText) findViewById(R.id.input_email);
        passwordEditTextView = (EditText) findViewById(R.id.input_email);
        btnSignUp = (Button) findViewById(R.id.btn_signup);

        btnSignUp.setOnClickListener(this);

        // Set the dimensions of the sign-in button.
        SignInButton signInButton = (SignInButton) findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        signInButton.setOnClickListener(this);

        // [START initialize_auth]
        mAuth = FirebaseAuth.getInstance();
        // [END initialize_auth]
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        // Build a GoogleApiClient with access to the Google Sign-In API and the
        // options specified by gso.
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* AptCompactActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

        System.out.println(connectionResult);

    }


    private void signInWithGoogle() {

        showProgressDialogue("Authenticating...");
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d("successResult", "handleSignInResult:" + result.getStatus());
        removeProgressDialogue();
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.

            GoogleSignInAccount acct = result.getSignInAccount();

            firebaseAuthWithGoogle(acct);

            //Intent i = new Intent(this, MainActivity.class);

            //startActivity(i);


        } else {
            // Signed out, show unauthenticated UI.
            updateUI(false);
        }
    }

    // [START signOut]
    private void signOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        // [START_EXCLUDE]
                        updateUI(false);
                        // [END_EXCLUDE]
                    }
                });
    }
    // [END signOut]
    private void updateUI(boolean signedIn) {
        if (signedIn) {
            Toast.makeText(this, "see created user", Toast.LENGTH_SHORT).show();
        } else {

            Toast.makeText(this, "there seems to be a problem", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }
    // [START auth_with_google]
    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getFamilyName());
        Log.d(TAG, String.valueOf(mAuth.getCurrentUser()));

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        Log.d(TAG, credential.getProvider());
        //        mAuth.getCurrentUser().linkWithCredential(credential)
        mAuth.signInWithCredential(credential)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithCredential:onComplete:" + task.isSuccessful());

//                        FirebaseUser user = task.getResult().getUser();

//                        Log.d(TAG, user.getUid());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithCredential", task.getException());
                            Toast.makeText(SignInAcitivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }
    public void registerWithEmailPassword(){
            email = emailEditTextView.getText().toString().trim();
            password = passwordEditTextView.getText().toString().trim();
            boolean cancel = false;
            View focusView = null;
            // Check for a valid password, if the user entered one.
            if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
                passwordEditTextView.setError("enter password");
                focusView = passwordEditTextView;
                cancel = true;
            }
        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            emailEditTextView.setError("enter email");
            focusView = emailEditTextView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            emailEditTextView.setError("enter valid email");
            focusView = emailEditTextView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            showProgressDialogue("creating user account");
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "createUserWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                Toast.makeText(SignInAcitivity.this, user.getUid(),
                                        Toast.LENGTH_SHORT).show();
                                //updateUI(user);
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(SignInAcitivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                                //updateUI(null);
                            }

                            // ...
                        }
                    });
            }
        }



    public  void  showProgressDialogue(String message){
        progressDialog = new ProgressDialog(SignInAcitivity.this, R.style.AppTheme_Dark_Dialog);
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

        return password.length() > 5;
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in_button:
                signInWithGoogle();
                break;
            case R.id.btn_signup:
                registerWithEmailPassword();
                break;

        }

    }
}
