package com.example.android.inclass0213;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckedTextView;
import android.widget.TextView;
import android.widget.Button;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView mQuestionTextView;
    Button mTrueButton;
    Button mFalseButton;
    Button mPreviousButton;
    Button mNextButton;
    Button mCheatButton;
    boolean isCheated = false;


    private Question[] mQuestionBank = new Question[]{
            new Question(R.string.question1,false),
            new Question(R.string.question2,true),
            new Question(R.string.question3,true),
            new Question(R.string.question4,false),
            new Question(R.string.question5,true),
            new Question(R.string.question6,false),
    };

    private int mCurrentIndex = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mQuestionTextView = findViewById(R.id.text_view);
        int question = mQuestionBank[mCurrentIndex].getmTextResId();
        mQuestionTextView.setText(question);


        mTrueButton = findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                checkAnswer(true);

            }

        });
        mFalseButton = findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){

                checkAnswer(false);

            }

        });
        mPreviousButton = findViewById(R.id.previous_button);
        mPreviousButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                mCurrentIndex = (mCurrentIndex - 1) % mQuestionBank.length;
                int question = mQuestionBank[mCurrentIndex].getmTextResId();
                mQuestionTextView.setText(question);

            }

        });
        mNextButton = findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                int question = mQuestionBank[mCurrentIndex].getmTextResId();
                mQuestionTextView.setText(question);

            }

        });

        mCheatButton = findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
               Intent intent = new Intent(MainActivity.this,CheatActivity.class);
               boolean isAnswerTrue = mQuestionBank[mCurrentIndex].ismAnswerTrue();
               intent.putExtra(Keys.EXTRA_BOOLEAN,isAnswerTrue);
               startActivityForResult(intent,Keys.REQUEST);

            }

        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == Keys.REQUEST && resultCode == RESULT_OK){
            isCheated = CheatActivity.wasAnswerShown(data);
        }
    }

    private void checkAnswer(boolean userPressedTrue){
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].ismAnswerTrue();
        int messageResId = 0;

        if(isCheated == true){
            Toast.makeText(this, "Cheating is wrong", Toast.LENGTH_SHORT).show();
        }else {
            if(userPressedTrue == answerIsTrue){
                messageResId = R.string.correct_toast;
            }else{
                messageResId = R.string.incorrect_toast;
            }
            Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
        }
        isCheated = false;

    }



}
