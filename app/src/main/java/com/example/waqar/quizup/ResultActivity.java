package com.example.waqar.quizup;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ResultActivity extends AppCompatActivity implements View.OnClickListener{
private FirebaseAuth firebaseAuth;
private TextView textViewUserEmail;
    private TextView fetch;
private Button buttonLogout;
    private Button tryagain;
    private Button highscore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        firebaseAuth=FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser()==null){
            finish();
            startActivity(new Intent(this,signup.class));
        }

FirebaseUser user=firebaseAuth.getCurrentUser();
        textViewUserEmail=(TextView)findViewById(R.id.email);
        fetch=(TextView)findViewById(R.id.fetch);
        textViewUserEmail.setText("Welcome"+"  "+user.getEmail());
        buttonLogout=(Button)findViewById(R.id.logout);
        tryagain=(Button)findViewById(R.id.tryagain);
        highscore=(Button)findViewById(R.id.highscore);
        //get rating bar objectr
        RatingBar bar=(RatingBar)findViewById(R.id.ratingBar1);
        bar.setNumStars(5);
        bar.setStepSize(0.5f);
        //get text view
        TextView t=(TextView)findViewById(R.id.textResult);
        //get score
        Bundle b = getIntent().getExtras();
        int score= b.getInt("score");
        //display score
        bar.setRating(score);
        switch (score)
        {
            case 0: t.setText("You Scored 0%, keep learning");
                break;
            case 1: t.setText("You Scored 20%, study better");
                break;
            case 2: t.setText("You Scored 40%, keep learning");
                break;
            case 3: t.setText("You Scored 60%, good attempt");
                break;
            case 4:t.setText("You Scored 80% Hmmmm.. maybe you have been reading a lot of AndroidProgramming quiz");
                break;
            case 5:t.setText(" Whao, you Scored 100%, Who are you? An Android Jet brain");
                break;
        }
        buttonLogout.setOnClickListener(this);
        tryagain.setOnClickListener(this);
        highscore.setOnClickListener(this);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_result, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent settingsIntent = new Intent(this, com.example.waqar.quizup.QuizActivity.class);
            startActivity(settingsIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
if (v==buttonLogout){
    firebaseAuth.signOut();
    finish();
    startActivity(new Intent(this,signup.class));
}
if (v==tryagain){
    Intent i=new Intent(this,level.class);
    startActivity(i);

}
        if (v==highscore){
            FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("score");
            myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        fetch.setText((CharSequence) dataSnapshot.getValue());

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
            });

        }
    }
}
