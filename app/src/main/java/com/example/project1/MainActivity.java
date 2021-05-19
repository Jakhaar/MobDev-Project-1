package com.example.project1;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    final int maxBound = 1000;
    int guessedNumber;
    int randomNumber;
    int lowerBoundCurrentNum; //has to be changed to the Values in EditText lower section
    int upperBoundCurrentNum; //has to be changed to the Values in EditText higher section
    int duration = Toast.LENGTH_SHORT; //duration of the message that will be shown
    Random random = new Random();
    Toast toast;

    SeekBar seekbarLower;
    SeekBar seekbarUpper;
    Button numberGeneratorButton;
    EditText editTextLower;
    EditText editTextUpper;
    Button evaluateButton;
    Button hintButton;
    Context context;
    Button divisibilityHintButton;
    Button primeNumberHintButton;
    Button digitSumHintButton;
    Button digitProductHintButton;
    TextView currentScoreTextView;
    TextView guessedNumberTextField;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
                        toast = Toast.makeText(context,"A secret number has been generated randomly. Go, guess it! " + randomNumber, duration);
                        toast.show();

                        //Making the Buttons Clickable for the user
                        evaluateButton.setEnabled(true);
                        hintButton.setEnabled(true);
                        divisibilityHintButton.setEnabled(true);
                        primeNumberHintButton.setEnabled(true);
                        digitSumHintButton.setEnabled(true);
                        digitProductHintButton.setEnabled(true);

                        //Setting the Score
                        currentScoreTextView.setText(String.valueOf(upperBoundCurrentNum - lowerBoundCurrentNum));
                    }
            }
        });

        evaluateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Integer.parseInt((String) guessedNumberTextField.getText()) <= 0){
                    guessedNumber = Integer.parseInt((String) guessedNumberTextField.getText());
                    //TODO: Check if the guessed number is equal the generated number, if not return a Toast
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