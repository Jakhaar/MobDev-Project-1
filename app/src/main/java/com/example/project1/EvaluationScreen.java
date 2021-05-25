package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class EvaluationScreen extends AppCompatActivity {

    int intesity;
    int guessedNumber = MainActivity.guessedNumber;
    String answerTooHigh = "Your guess of " + guessedNumber + "\nis TOO HIGH",
            answerTooLow = "Your guess of " + guessedNumber + "\nis TOO LOW",
            answerIsRight = "Excellent!\n" + guessedNumber + " is correct!",
            congratsText = "CONGRATULATIONS!\nYou solved the number game with "
            + MainActivity.attempts + " attempts, achieving a score of " + MainActivity.score + ".";

    ConstraintLayout evaluationScreen;
    Button dismissButton;
    TextView answerText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_evaluation_screen);

        dismissButton = findViewById(R.id.dissmissButton);
        answerText = findViewById(R.id.textView);
        guessedNumber = MainActivity.guessedNumber;
        evaluationScreen = findViewById(R.id.activityBackground);
        answerText.setText(guessedNumber > MainActivity.randomNumber ? answerTooHigh :
                guessedNumber < MainActivity.randomNumber ? answerTooLow : answerIsRight);
        //TODO: Color Gradiant is missing
        evaluationScreen.setBackgroundColor(guessedNumber > MainActivity.randomNumber ?
                Color.argb(200,255,0,0) :
                guessedNumber < MainActivity.randomNumber ?
                        Color.argb(200,0,0,255) :
                        Color.argb(200,255,255,255));


        dismissButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                if(MainActivity.guessedNumber == MainActivity.randomNumber){
                    Context context;
                    context = getApplicationContext();
                    Toast toast = Toast.makeText(context, congratsText, Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        Context context = getApplicationContext();
        Toast toast = Toast.makeText(context, "Use the dismiss button to return", Toast.LENGTH_LONG);
        toast.show();
    }
}