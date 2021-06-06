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
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material
        .snackbar
        .Snackbar;

import androidx.appcompat.widget.Toolbar;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    final int maxBound = 1000, primeNumberHintCost = 10, digitProductHintCost = 3,
            digitSumHintCost = 5, divisibilityHintCost = 1;
    int hintCost;
    static int guessedNumber, randomNumber, score, attempts = 0,
            lowerBoundCurrentNum = 0, upperBoundCurrentNum = 100, duration = Toast.LENGTH_SHORT;;
    String hintText, endOfGameText;
    Random random = new Random();
    Toast toast;
    ConstraintLayout constraintLayout;
    SeekBar seekbarLower, seekbarUpper;
    Button numberGeneratorButton, evaluateButton, hintButton;
    EditText editTextLower, editTextUpper;
    static Context context;
    RadioButton divisibilityHintButton, primeNumberHintButton,
            digitSumHintButton, digitProductHintButton;
    static TextView currentScoreTextView;
    TextView guessedNumberTextField;
    static boolean gameIsOver = false, won = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Guess My Number");

        constraintLayout = findViewById(R.id.coordinatorLayout2);
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
        seekbarUpper.setProgress(100);
        editTextUpper.setText(String.valueOf(seekbarUpper.getProgress()));
        seekbarLower.setMax(maxBound);
        seekbarUpper.setMax(maxBound);



        //Button pressed
        numberGeneratorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    if(lowerBoundCurrentNum < upperBoundCurrentNum) {
                        gameIsOver = false;
                        won = false;

                        //Generates a random Number between the selected numbers
                        randomNumber = random.nextInt(
                                (upperBoundCurrentNum - lowerBoundCurrentNum) + 1)
                                + lowerBoundCurrentNum;
                        toast = Toast.makeText(context,
                                "A secret number has been generated randomly. Go, guess it!",
                                duration);
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
                        attempts = 0;
                        currentScoreTextView.setText(String.valueOf(score));
                    }
            }
        });

        evaluateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Incase the game is over
                if(gameIsOver){
                    gameIsOver();
                    return;
                } else if(won){
                    Toast toast = Toast.makeText(context, "YOU ALREADY WON!\n" +
                            "You solved the number game with " + attempts
                            + " attempts, achieving a score of " + score + ".", duration);
                    toast.show();
                    return;
                }

                //Incase something went wrong with the user input
                try{
                    guessedNumber = Integer.parseInt(String.valueOf(guessedNumberTextField.getText()));
                }catch (Exception e){
                    toast = Toast.makeText(context,
                            "Please Enter a valid Number before pressing the Button", duration);
                    toast.show();
                    return;
                }

                if (guessedNumber < lowerBoundCurrentNum || guessedNumber > upperBoundCurrentNum) {
                    toast = Toast.makeText(context,
                            "Your guess is beyond range (the secret number was generated from ["
                            + lowerBoundCurrentNum + ", " + upperBoundCurrentNum + "].", duration);
                    toast.show();
                } else {
                    if(guessedNumber != randomNumber){
                        --score;
                        currentScoreTextView.setText(String.valueOf(score));
                    }
                    ++attempts;
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
                int value = 0;

                //Incase the game is over
                if(gameIsOver){
                    gameIsOver();
                    return;
                }else if(won){
                    Toast toast = Toast.makeText(context, "YOU ALREADY WON!\n" +
                            "You solved the number game with " + attempts
                            + " attempts, achieving a score of " + score + ".", duration);
                    toast.show();
                    return;
                }

                if(!digitProductHintButton.isChecked() &&
                !divisibilityHintButton.isChecked() &&
                !primeNumberHintButton.isChecked() &&
                !digitSumHintButton.isChecked()){
                    toast = Toast.makeText(context, "You should select the type of hint first!",
                            duration);
                    toast.show();
                } else {
                    if(digitProductHintButton.isChecked()){
                        hintCost = digitProductHintCost;
                    } else if(divisibilityHintButton.isChecked()){
                        hintCost = divisibilityHintCost;
                    } else if(primeNumberHintButton.isChecked()){
                        hintCost = primeNumberHintCost;
                    } else if(digitSumHintButton.isChecked()){
                        hintCost = digitSumHintCost;
                    }
                    Snackbar snackBar;
                    snackBar = Snackbar.make(constraintLayout, "This hints costs " + hintCost +
                            " point(s)!", Snackbar.LENGTH_LONG).setAction("I WANT IT!",
                            new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(digitProductHintButton.isChecked() && (score > hintCost)){
                                hintText = "The digit product is " + String.valueOf(
                                        digitProduct(randomNumber));
                            } else if(divisibilityHintButton.isChecked() && (score >= hintCost)){
                                hintText = dividable(randomNumber);
                            } else if(primeNumberHintButton.isChecked() && (score >= hintCost)){
                                hintText = isPrimeNumber(randomNumber) ?
                                        "It is a prime number" : "It is NOT a prime number";
                            } else if(digitSumHintButton.isChecked() && (score >= hintCost)){
                                hintText = "The digit product is " + String.valueOf(
                                        digitSum(randomNumber));
                            } else{
                                toast = Toast.makeText(context,
                                        "You don't have enough points for this hint", duration);
                                toast.show();
                                return;
                            }
                            toast = Toast.makeText(context, hintText, duration);
                            score -= hintCost;
                            currentScoreTextView.setText(String.valueOf(score));
                            toast.show();
                        }
                    });
                    snackBar.setActionTextColor(Color.WHITE);
                    snackBar.show();
                }
            }
        });
    }

    public void openNewScreen(){
        Intent intent = new Intent(this, EvaluationScreen.class);
        startActivity(intent);
    }

    public static void endOfGame(){
        if(score <= 0){
            gameIsOver = true;
            currentScoreTextView.setText("LOST");
            Toast toast = Toast.makeText(context, "You Lost!\nYou don't have any Points left.\n" +
                    "Please start again by generating a new number", duration);
            toast.show();
        }
    }

    public static void gameIsOver(){
        if(gameIsOver){
            Toast toast = Toast.makeText(context, "You Lost!\nYou don't have any Points left.\n" +
                    "Please start again by generating a new number", duration);
            toast.show();
        }
    }

    public static boolean isPrimeNumber(final int value) {
        if (value <= 2) {
            return (value == 2);
        }
        for (int i = 2; i * i <= value; i++) {
            if (value % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static int digitProduct(final int value){
        int sum = 1;
        for(int i = 0; i < String.valueOf(value).length(); i++){
            sum *= Integer.parseInt(String.valueOf(String.valueOf(value).charAt(i)));
        }
        return sum;
    }

    public static int digitSum(final int value){
        int sum = 0;
        for(int i = 0; i < String.valueOf(value).length(); i++){
            sum += Integer.parseInt(String.valueOf(String.valueOf(value).charAt(i)));
        }
        return sum;
    }

    public static String dividable(final int value){
            Random random = new Random();
            if(value == 0) return "It has NO dividable number";

            int num = random.nextInt(random.nextInt(20 - 1) + 1);
            return (value % num == 1) ? "It is not dividable by " + num : "It is dividable by " + num;
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