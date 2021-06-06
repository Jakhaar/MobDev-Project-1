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

//TODO: FULL SCREEN MODE fehlt zur hälfte
public class EvaluationScreen extends AppCompatActivity {

    int guessedNumber = MainActivity.guessedNumber;
    String answerTooHigh = "Your guess of " + guessedNumber + "\nis TOO HIGH",
            answerTooLow = "Your guess of " + guessedNumber + "\nis TOO LOW",
            answerIsRight = "Excellent!\n" + guessedNumber + " is correct!",
            congratsText = "CONGRATULATIONS!\nYou solved the number game with "
            + MainActivity.attempts + " attempts, achieving a score of " + MainActivity.score + ".";

    ConstraintLayout evaluationScreen;
    Button dismissButton;
    TextView answerText;
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_evaluation_screen);
        hideSystemUI();

        dismissButton = findViewById(R.id.dissmissButton);
        answerText = findViewById(R.id.textView);
        guessedNumber = MainActivity.guessedNumber;
        evaluationScreen = findViewById(R.id.activityBackground);
        answerText.setText(guessedNumber > MainActivity.randomNumber ? answerTooHigh :
                guessedNumber < MainActivity.randomNumber ? answerTooLow : answerIsRight);
        evaluationScreen.setBackgroundColor(guessedNumber > MainActivity.randomNumber ?
                Color.argb(Math.max(Math.abs(MainActivity.randomNumber - MainActivity.guessedNumber),
                        15),255,0,0) :
                guessedNumber < MainActivity.randomNumber ?
                        Color.argb(Math.max(Math.abs(MainActivity.randomNumber - MainActivity.guessedNumber),
                                15),0,0,255) :
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
                    MainActivity.won = true;
                } else {
                    MainActivity.endOfGame();
                }
            }
        });
    }



    @Override
    public void onBackPressed() {
        Context context = getApplicationContext();
        Toast toast = Toast.makeText(context, "Use the dismiss button to return",
                Toast.LENGTH_LONG);
        toast.show();
    }

    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            hideSystemUI();
        }
    }

    private void hideSystemUI() {
        // Enables regular immersive mode.
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }
}