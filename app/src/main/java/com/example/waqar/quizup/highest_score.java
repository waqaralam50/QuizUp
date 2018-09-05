package com.example.waqar.quizup;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class highest_score extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highest_score);
        TextView txtScore=(TextView) findViewById(R.id.textscore);
TextView txtHighScore=(TextView) findViewById(R.id.textHighScore);
//receive the score from last activity by intent
        Intent intent=getIntent();
        int score=intent.getIntExtra("score",0);
        //display current score
        txtScore.setText("your score:"+ score);

        //use shared preferences to save th best score
        SharedPreferences mypref=getPreferences(MODE_PRIVATE);
        int highscore=mypref.getInt("highscore",0);
        if(highscore>=score)
            txtHighScore.setText("High Score: "+ highscore);
else
        {
            txtHighScore.setText("New highscore: "+score);
        SharedPreferences.Editor editor=mypref.edit();
        editor.putInt("highscore",score);
        editor.commit();
        }

    }
    public void onRepeatClick(View view){
        Intent intent=new Intent(highest_score.this,MainActivity.class);
        startActivity(intent);
    }
}
