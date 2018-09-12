package com.example.waqar.quizup;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signup extends AppCompatActivity implements View.OnClickListener{

    private Button buttonSignIn;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignup;
private ProgressDialog progressDialog;
private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        firebaseAuth=FirebaseAuth.getInstance();
        //track if user is already logged in or not
        if (firebaseAuth.getCurrentUser()!=null){
            finish();
            startActivity(new Intent(getApplicationContext(),level.class));
        }

editTextEmail=(EditText) findViewById(R.id.editText);
editTextPassword=(EditText) findViewById(R.id.editText3);
textViewSignup=(TextView) findViewById(R.id.textView);
buttonSignIn=(Button)findViewById(R.id.button);
progressDialog=new ProgressDialog(this);
buttonSignIn.setOnClickListener(this);
        textViewSignup.setOnClickListener(this);
    }
private void userLogin(){
        String email=editTextEmail.getText().toString().trim();
    String password=editTextPassword.getText().toString().trim();
    if (TextUtils.isEmpty(email)){
        //email is empty
        Toast.makeText(this,"plese enter email",Toast.LENGTH_SHORT).show();
        //stopping the function execution further
        return;
    }
    if (TextUtils.isEmpty(password)){
        //password is empty
        Toast.makeText(this,"plese enter Password",Toast.LENGTH_SHORT).show();
        //stopping the function execution further
        return;
    }
    progressDialog.setMessage("Login in u to app........");
    progressDialog.show();
    firebaseAuth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                   //this method will be called when sign in will b completed
                    progressDialog.dismiss();
                    if (task.isSuccessful()){
                        //start the profile activity
                        //Intent i=new Intent(signup.this,level.class);
                        finish();
                        startActivity(new Intent(getApplicationContext(),level.class));
                    }
                }
            });
}
    @Override
    public void onClick(View v) {
if(v==buttonSignIn){
    userLogin();
}
if (v==textViewSignup){
    finish();
    Intent i=new Intent(signup.this,HomeActivity.class);
    startActivity(i);
}
    }
}
