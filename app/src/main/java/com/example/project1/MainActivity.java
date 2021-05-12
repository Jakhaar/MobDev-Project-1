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
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SeekBar seekbarLower = findViewById(R.id.seekBarLowerBound);
        SeekBar seekbarUpper = findViewById(R.id.seekBarUpperBound);
        final Button numberGeneratorButton = findViewById(R.id.numberGeneratorButton);
        final EditText editTextLower = findViewById(R.id.seekbarLower);
        final EditText editTextUpper = findViewById(R.id.seekbarUpper);

        final int maxBound = 1000;
        int lowerBoundCurrentNum = 1; //has to be changed to the Values in EditText lower section
        int upperBoundCurrentNum = 4; //has to be changed to the Values in EditText higher section
        int guessedNumber;
        int duration = Toast.LENGTH_SHORT;
        Context context = getApplicationContext();
        Random random = new Random();

        //Setting Maximum Seekbar Values
        seekbarLower.setMax(maxBound);
        seekbarUpper.setMax(maxBound);

        //Toast toast = Toast.makeText(context,"A secret number has been generated randomly. Go, guess it! ", duration);

        //Button pressed
        numberGeneratorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int randomNumber = random.nextInt((upperBoundCurrentNum - lowerBoundCurrentNum) + 1) + lowerBoundCurrentNum; //Generates a random Number between the selected numbers
                Toast toast = Toast.makeText(context,"A secret number has been generated randomly. Go, guess it! ", duration);
                toast.show();
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