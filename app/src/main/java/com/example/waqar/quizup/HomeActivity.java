package com.example.waqar.quizup;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView text;

    private Button btn;



    private EditText editTextEmail;

    private EditText editTextPassword;
    private EditText repass;

    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;

    CallbackManager callbackManager;
    private TextView loginfb;

    private Button login_button;

    @Override

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        callbackManager.onActivityResult(requestCode, resultCode, data);

        super.onActivityResult(requestCode, resultCode, data);

    }

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        callbackManager = CallbackManager.Factory.create();

        login_button = findViewById(R.id.login_button);


        callbackManager = CallbackManager.Factory.create();


        LoginManager.getInstance().registerCallback(callbackManager,

                new FacebookCallback<LoginResult>() {

                    @Override

                    public void onSuccess(LoginResult loginResult) {

                        // App code

                        progressDialog.setMessage("signing in u to app........");

                        progressDialog.show();

                        startActivity(new Intent(getApplicationContext(), level.class));


                    }


                    @Override

                    public void onCancel() {

                        // App code

                        startActivity(new Intent(HomeActivity.this, signup.class));

                    }


                    @Override

                    public void onError(FacebookException exception) {
                        if (amIConnected()) {
                            progressDialog.setMessage("signing in.......");

                            progressDialog.show();

                            startActivity(new Intent(getApplicationContext(), level.class));
                        } else {
                            progressDialog.setMessage("No Internet");

                            progressDialog.show();

                            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        }
                    }
                });

        FacebookSdk.sdkInitialize(getApplicationContext());

        AppEventsLogger.activateApp(this);


        setContentView(R.layout.activity_home);

        firebaseAuth = firebaseAuth.getInstance();

        //track if user is already logged in or not if yes directly signin

        if (firebaseAuth.getCurrentUser() != null) {

            finish();

            startActivity(new Intent(getApplicationContext(), level.class));

        }

        editTextEmail = findViewById(R.id.editText);

        editTextPassword = findViewById(R.id.editText3);
        repass = findViewById(R.id.repass);
        btn = findViewById(R.id.button);

        text = findViewById(R.id.textView);


        progressDialog = new ProgressDialog(this);

//        btn.setOnClickListener(new View.OnClickListener() {

//            @Override

//            public void onClick(View v) {

//                registerUser();

//                Intent i=new Intent(HomeActivity.this,level.class);

//                startActivity(i);

//            }

//        });


            btn.setOnClickListener(this);

            text.setOnClickListener(this);


//initializecontrols();

//loginwithfb();


    }

//    private void initializecontrols(){

//

//

//    }


//    private void loginwithfb(){

//        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

//            @Override

//            public void onSuccess(LoginResult loginResult) {

//                loginfb.setText("Login Success"+loginResult.getAccessToken());

//Intent i=new Intent(HomeActivity.this,level.class);

//startActivity(i);

//            }

//

//            @Override

//            public void onCancel() {

//loginfb.setText("Login Cancelled");

//            }

//

//            @Override

//            public void onError(FacebookException error) {

//loginfb.setText("Error Occured:"+error.getMessage());

//            }

//        });

//    }


    private boolean amIConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


    private void registerUser() {

        String email = editTextEmail.getText().toString().trim();

        String password = editTextPassword.getText().toString().trim();
        String repassword = repass.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {

            //email is empty

            Toast.makeText(this, "plese enter email", Toast.LENGTH_SHORT).show();

            //stopping the function execution further

            return;

        }

        if (TextUtils.isEmpty(password)) {

            //password is empty

            Toast.makeText(this, "plese enter Password", Toast.LENGTH_SHORT).show();

            //stopping the function execution further

            return;

        }
        if (TextUtils.isEmpty(repassword)) {

            //password is empty

            Toast.makeText(this, "plese Re-enter Password", Toast.LENGTH_SHORT).show();


            //stopping the function execution further

            return;

        } else {
            progressDialog.setMessage("Registering u to app........");

            progressDialog.show();

            //creating a new user

            firebaseAuth.createUserWithEmailAndPassword(email, password)

                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                        @Override

                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {


                                //user is successfully registered and logged in

                                //we will start the profile activity here

                                registerUser();


                                finish();

                                startActivity(new Intent(getApplicationContext(), level.class));


                            } else  {

                                Toast.makeText(HomeActivity.this, "could not register,Try Again", Toast.LENGTH_SHORT).show();







                            }

                                       }

                    });
                }

    }


    //if validations are ok

    //we will first show a progressbar


    @Override

    public void onClick(View v) {

        if (v==btn){

            registerUser();

        }

        if (v == text) {

            //will open login activity

            Intent i = new Intent(HomeActivity.this, signup.class);

            startActivity(i);

        }

    }

}