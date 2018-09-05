package com.example.waqar.quizup;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MyDataBaseHelper extends SQLiteOpenHelper {
    //Db name
    public static String DATABASE_QUESTION = "questionBank.db";
    //Current version of database
    private static final int DATABASE_VERSION = 1;
    //Database table name
    private static final String TABLE_QUESTION = "QuestionBank";
    //All fields used in databse table
    private static final String KEY_ID = "id";
    private static final String QUESTION = "question";
    private static final String CHOICE1 = "choice1";
    private static final String CHOICE2 = "choice2";
    private static final String CHOICE3 = "choice3";
    private static final String CHOICE4 = "choice4";
    private static final String ANSWER = "answer";
    //Question Table Create Query in this String
    private static final String CREATE_TABLE_QUESTION = "CREATE TABLE "
            + TABLE_QUESTION + "(" + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT," + " TEXT, "
            + CHOICE1 + " TEXT " + CHOICE2 + " TEXT " + CHOICE3 + " TEXT, "
            + CHOICE4 + " TEXT," + ANSWER + " TEXT);";

    public MyDataBaseHelper(Context context) {
        super(context, DATABASE_QUESTION, null, DATABASE_VERSION);
    }

    //This method is called by system if the database is accessed but not yet created
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_QUESTION);//create question table
    }

    //This method is called when any modifications in database are done like version is updated or db schema is changed
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_QUESTION);//drop table if exists
        onCreate(db);
    }

    //this method is used to add question detail in question table
    public long addInitialQuestion(Question question) {
        SQLiteDatabase db = this.getWritableDatabase();
        //Creating content values
        ContentValues values = new ContentValues();
        values.put(QUESTION, question.getQuestion());
        values.put(CHOICE1, question.getChoice(0));
        values.put(CHOICE2, question.getChoice(1));
        values.put(CHOICE3, question.getChoice(2));
        values.put(CHOICE4, question.getChoice(3));
        values.put(ANSWER, question.getAnswer());
        //insert row in question table
        long insert = db.insert(TABLE_QUESTION, null, values);
        return insert;
    }
//to extract data from db and save it arraylist of data type question
    public List<Question> getAllQuestionsList(){
        List<Question> questionArrayList=new ArrayList<>();
        String selectQuery="SELECT * FROM " + TABLE_QUESTION;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor c=db.rawQuery(selectQuery,null);

        //looping through all records and adding to the list
        if (c.moveToFirst()){
            do {
                Question question = new Question();
                String questText = c.getString(c.getColumnIndex(QUESTION));
                question.setQuestion(questText);

                String choice1Text = c.getString(c.getColumnIndex(CHOICE1));
                question.setQuestion(choice1Text);

                String choice2Text = c.getString(c.getColumnIndex(CHOICE2));
                question.setQuestion(choice2Text);
                String choice3Text = c.getString(c.getColumnIndex(CHOICE3));
                question.setQuestion(choice3Text);
                String choice4Text = c.getString(c.getColumnIndex(CHOICE4));
                question.setQuestion(choice4Text);

                String answerText = c.getString(c.getColumnIndex(ANSWER));
                question.setQuestion(answerText);
                //adding to question list
                questionArrayList.add(question);
            }while (c.moveToNext());
            Collections.shuffle(questionArrayList);
            }
            return questionArrayList;
        }
    }

