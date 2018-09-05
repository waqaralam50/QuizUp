package com.example.waqar.quizup;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class QuestionBank {
//declare list of Question objects
    List<Question> list=new ArrayList<>();
    MyDataBaseHelper myDataBaseHelper;
    //method returns number of questions in list
    public int getLength(){return list.size();}

    //method returns question from list based on list index
    public String getQuestion(int a){return list.get(a).getQuestion();}
    //method return & single multiple choice item based on list index
    //based on number of multiple choice item in the list -1,2,3,or 4
    //as an argument
    public String getChoice(int index,int num){return list.get(index).getChoice(num-1);}
    //method returns corret answer for the question basesd on list index
    public String getCorrectAnswer(int a){return list.get(a).getAnswer();}
public void initQuestions(Context context) {
    myDataBaseHelper = new MyDataBaseHelper(context);
    list = myDataBaseHelper.getAllQuestionsList();//get questions/choices/answers from db
    if (list.isEmpty()) {   //if list is empty,populate db with default questions/choices/answers
        myDataBaseHelper.addInitialQuestion(new Question("1.When did Google acquire Android ?",
                new String[]{"2001", "2003", "2004", "2005"}, "2005"));//correct answer
        myDataBaseHelper.addInitialQuestion(new Question("2.What is the name of build toolkit for android studio",
                new String[]{"JVM", "Gradle", "Dalvik", "HAXM"}, "Gradle"));
        myDataBaseHelper.addInitialQuestion(new Question("3.What widget can replace any use of radio button",
                new String[]{"Toggle Button", "Spinner", "Switch Button", "Image Button"}, "Spinner"));
        myDataBaseHelper.addInitialQuestion(new Question("4.What is a widget in android app?",
                new String[]{"reusable GUI element", "Layout for Activity", "device placed in can of beer", "Kuch b"}, "kuch b"));
        list = myDataBaseHelper.getAllQuestionsList();//get list from db again
    }
}
}
