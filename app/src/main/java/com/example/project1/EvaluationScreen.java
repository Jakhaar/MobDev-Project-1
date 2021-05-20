package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EvaluationScreen extends AppCompatActivity {

    int guessedNumber = MainActivity.guessedNumber;
    String answerTooHigh = "Your guess of " + guessedNumber + " is TOO HIGH";
    String answerTooLow = "Your guess of " + guessedNumber + " is TOO LOW";
    String answerIsRight = "Excellent!\n" + guessedNumber + " is correct!";

    ConstraintLayout evaluationScreen;
    Button dismissButton;
    TextView answerText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluation_screen);
        setTitle("Guess My Number");

        dismissButton = findViewById(R.id.dissmissButton);
        answerText = findViewById(R.id.textView);
        guessedNumber = getIntent().getIntExtra("guessedNumber", 0);
        evaluationScreen = findViewById(R.id.activityBackground);

        answerText.setText(guessedNumber > MainActivity.randomNumber ? answerTooHigh : guessedNumber < MainActivity.randomNumber ? answerTooLow : answerIsRight);
        //TODO: Color Gradiant is missing
        evaluationScreen.setBackgroundColor(guessedNumber > MainActivity.randomNumber ? Color.RED : guessedNumber < MainActivity.randomNumber ? Color.BLUE : Color.WHITE);


        dismissButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: Update the Score
                if(guessedNumber != MainActivity.randomNumber)
                    MainActivity.score = MainActivity.score - 1;
                    MainActivity.currentScoreTextView.setText(MainActivity.score);
                finish();
            }
        });
    }
}