package com.example.android.inclass0213;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {
    Button show_answer;
    TextView answer_view;
    boolean isAnswerShown = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);


        answer_view = findViewById(R.id.answer);


        show_answer = findViewById(R.id.show_button);
        show_answer.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                boolean isAnswerTrue = getIntent().getBooleanExtra(Keys.EXTRA_BOOLEAN,false);
                answer_view.setText("The answer is: "+isAnswerTrue);
                setIsAnswerShown(true);

            }

        });


    }

    public static boolean wasAnswerShown(Intent result){
        return result.getBooleanExtra(Keys.EXTRA_CHEATED,false);
    }
    private void setIsAnswerShown(boolean isAnswerShown){
        Intent data = new Intent(CheatActivity.this,MainActivity.class);
        data.putExtra(Keys.EXTRA_CHEATED,isAnswerShown);
        setResult(RESULT_OK, data);

    }
}
