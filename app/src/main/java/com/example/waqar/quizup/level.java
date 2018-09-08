package com.example.waqar.quizup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class level extends AppCompatActivity {
    TextView text;
    TextView text1;
    TextView text2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);
        text=findViewById(R.id.textView3);
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(level.this,QuizActivity.class);
                startActivity(i);
            }
        });
        text1=findViewById(R.id.textView4);
        text1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(level.this,QuizActivity.class);
                startActivity(i);
            }
        });
        text2=findViewById(R.id.textView5);
        text2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(level.this,QuizActivity.class);
                startActivity(i);
            }
        });
    }
}
