package com.example.waqar.quizup;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.waqar.quizup.data.DbHelper;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class QuizActivity extends AppCompatActivity {
    List<com.example.waqar.quizup.Question> quesList;
    int score=0;
    int qid=0;
    com.example.waqar.quizup.Question currentQ;
    TextView txtQuestion;
    RadioButton rda, rdb, rdc;
    Button butNext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        DbHelper db=new DbHelper(this);
        quesList=db.getAllQuestions();
        currentQ=quesList.get(qid);
        txtQuestion=(TextView)findViewById(R.id.textView1);
        rda=(RadioButton)findViewById(R.id.radio0);
        rdb=(RadioButton)findViewById(R.id.radio1);
        rdc=(RadioButton)findViewById(R.id.radio2);
        butNext=(Button)findViewById(R.id.button1);
        int fvalue;
        setQuestionView();


      butNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioGroup grp=(RadioGroup)findViewById(R.id.radioGroup1);
                RadioButton answer=(RadioButton)findViewById(grp.getCheckedRadioButtonId());
                grp.clearCheck();
                Log.d("yourans", currentQ.getANSWER()+" "+answer.getText());

                if(currentQ.getANSWER().equals(answer.getText()))
                {
                    score++;
                    Log.d("score", "Your score"+score);

                }
                if(qid<5){
                    currentQ=quesList.get(qid);
                    grp.check(R.id.radio1);
                    setQuestionView();
                }


                else{
                    Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
                    Bundle b = new Bundle();
                    b.putInt("score", score); //Your score
                    intent.putExtras(b); //Put your score to your next Intent
                    startActivity(intent);
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("score");
                    DatabaseReference myno = database.getReference("no");

                    myno.addValueEventListener(new ValueEventListener() {

                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                        }


                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                    myRef.setValue("Your HighScore was 100%");
//                    switch (score)
//                    {
//                        case 0:                   myRef.setValue("Your HighScore was 0%");
//                            myno.setValue(score);
//
//                            break;
//                        case 1: myRef.setValue("Your HighScore was 20%");
//                            myno.setValue(score);
//                            break;
//                        case 2: myRef.setValue("Your HighScore was 40%");
//                            myno.setValue(score);
//                            break;
//                        case 3: myRef.setValue("Your HighScore was 60%");
//                            myno.setValue(score);
//                            break;
//                        case 4: myRef.setValue("Your HighScore was 80%");
//                            myno.setValue(score);
//                            break;
//                        case 5:myRef.setValue(" Whao,Your HighScore was 100%");
//                            myno.setValue(score);
//                            break;
//                    }

//                    myRef.child("users").setValue(score);
                    finish();
                }

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_quiz, menu);
        return true;
    }
    private void setQuestionView()
    {
        txtQuestion.setText(currentQ.getQUESTION());
        rda.setText(currentQ.getOPTA());
        rdb.setText(currentQ.getOPTB());
        rdc.setText(currentQ.getOPTC());
        qid++;
    }
}
