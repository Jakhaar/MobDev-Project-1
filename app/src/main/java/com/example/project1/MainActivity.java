package com.example.project1;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout
        .widget.CoordinatorLayout;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material
        .snackbar
        .Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.snackbar.Snackbar;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    final int maxBound = 1000;
    static int guessedNumber;
    static int randomNumber;
    int lowerBoundCurrentNum = 0;
    int upperBoundCurrentNum = 1000;
    static int score;
    int duration = Toast.LENGTH_SHORT; //duration of the message that will be shown
    Random random = new Random();
    Toast toast;
    Snackbar snackbar;

    RadioGroup hintRadioGroup;
    SeekBar seekbarLower;
    SeekBar seekbarUpper;
    Button numberGeneratorButton;
    EditText editTextLower;
    EditText editTextUpper;
    Button evaluateButton;
    Button hintButton;
    Context context;
    RadioButton divisibilityHintButton, primeNumberHintButton,
            digitSumHintButton, digitProductHintButton;
    TextView currentScoreTextView, guessedNumberTextField;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Guess My Number");

        hintRadioGroup = findViewById(R.id.hintRadioGroup);
        seekbarLower = findViewById(R.id.seekBarLowerBound);
        seekbarUpper = findViewById(R.id.seekBarUpperBound);
        currentScoreTextView = findViewById(R.id.currentScoreTextView);
        guessedNumberTextField = findViewById(R.id.guessedNumberTextField);
        editTextLower = findViewById(R.id.seekbarLowerText);
        editTextUpper = findViewById(R.id.seekbarUpperText);
        evaluateButton = findViewById(R.id.evaluateButton);
        hintButton = findViewById(R.id.hintButton);
        numberGeneratorButton = findViewById(R.id.numberGeneratorButton);
        divisibilityHintButton = findViewById(R.id.divisibilityHintButton);
        primeNumberHintButton = findViewById(R.id.primeNumberHintButton);
        digitSumHintButton = findViewById(R.id.digitSumHintButton);
        digitProductHintButton = findViewById(R.id.digitProductHintButton);
        context = getApplicationContext();



        //Setting Maximum Seekbar Values
        seekbarLower.setProgress(0);
        editTextLower.setText(String.valueOf(seekbarLower.getProgress()));
        seekbarUpper.setProgress(1000);
        editTextUpper.setText(String.valueOf(seekbarUpper.getProgress()));
        seekbarLower.setMax(maxBound);
        seekbarUpper.setMax(maxBound);



        //Button pressed
        numberGeneratorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    if(lowerBoundCurrentNum < upperBoundCurrentNum) {
                        randomNumber = random.nextInt((upperBoundCurrentNum - lowerBoundCurrentNum) + 1) + lowerBoundCurrentNum; //Generates a random Number between the selected numbers
                        toast = Toast.makeText(context,"A secret number has been generated randomly. Go, guess it!", duration);
                        toast.show();

                        //Making the Buttons Clickable for the user
                        evaluateButton.setEnabled(true);
                        hintButton.setEnabled(true);
                        divisibilityHintButton.setEnabled(true);
                        primeNumberHintButton.setEnabled(true);
                        digitSumHintButton.setEnabled(true);
                        digitProductHintButton.setEnabled(true);

                        //Setting the Score
                        score = upperBoundCurrentNum - lowerBoundCurrentNum;
                        currentScoreTextView.setText(String.valueOf(score));
                    }
            }
        });

        evaluateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Incase something went wrong with the user input
                try{
                    guessedNumber = Integer.parseInt(String.valueOf(guessedNumberTextField.getText()));
                }catch (Exception e){
                    toast = Toast.makeText(context, "Please Enter a valid Number before pressing the Button", duration);
                    toast.show();
                }


                if (guessedNumber < lowerBoundCurrentNum || guessedNumber > upperBoundCurrentNum) {
                    toast = Toast.makeText(context, "Your guess is beyond range (the secret number was generated from ["
                            + lowerBoundCurrentNum + ", " + upperBoundCurrentNum + "].", duration);
                    toast.show();
                } else {
                    if(guessedNumber != randomNumber){
                        //Updating the score if needed
                        --score;
                        currentScoreTextView.setText(String.valueOf(score));
                    }
                    openNewScreen();
                }
            }
        });


        //Lower Seekbar
        seekbarLower.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                editTextLower.setText(String.valueOf(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                lowerBoundCurrentNum = seekBar.getProgress();
            }
        });

        //Upper Seekbar
        seekbarUpper.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                editTextUpper.setText(String.valueOf(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                upperBoundCurrentNum = seekBar.getProgress();
            }
        });

        hintButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!digitProductHintButton.isChecked() &&
                !divisibilityHintButton.isChecked() &&
                !primeNumberHintButton.isChecked() &&
                !digitSumHintButton.isChecked()){
                    toast = Toast.makeText(context, "You should select the type of hint first!", duration);
                    toast.show();
                } else {
                            Snackbar snackBar = Snackbar.make(v, "text!", Snackbar.LENGTH_LONG)
                                    .setAction("action!", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                }
                            });
                            snackBar.setActionTextColor(Color.BLUE);
                            View snackBarView = snackBar.getView();
                            TextView textView = snackBarView.findViewById(android.support.design.R.id.snackbar_text);
                            textView.setTextColor(Color.RED);
                            snackBar.show();
                }
            }
        });
    }

    public void openNewScreen(){
        Intent intent = new Intent(this, EvaluationScreen.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}