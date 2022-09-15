package com.syahkhay.mathgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.Random;

public class Addition extends AppCompatActivity {

    TextView tvScore, tvLive, tvTime, tvQuestion, tvReview;
    EditText editAnswer;
    Button btnSubmit, btnNext;
    Random random = new Random();
    int num1, num2, intAnswer, realAnswer, userScore = 0, intLive=3;

    CountDownTimer timer;
//  start time at input seconds
    private static final long START_TIMER_IN_MILIS=30000;
    Boolean timer_running, answer = false;
    long time_left_in_milis=START_TIMER_IN_MILIS;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addition);

        tvScore=findViewById(R.id.tvScore);
        tvLive=findViewById(R.id.tvLive);
        tvTime=findViewById(R.id.tvTime);
        tvReview=findViewById(R.id.tvReview);
        tvQuestion=findViewById(R.id.tvQuestion);
        editAnswer=findViewById(R.id.editAnswer);
        btnSubmit=findViewById(R.id.btnSubmit);
        btnNext=findViewById(R.id.btnNext);

        gameContinue();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editAnswer.getText() == null){
                    Toast.makeText(Addition.this, getString(R.string.beg), Toast.LENGTH_LONG).show();
                }else{
                    answer=true;

                    pauseTimer();
//              Get answer from user
                    intAnswer=Integer.valueOf(editAnswer.getText().toString());

//              Check answer
                    if(intAnswer == realAnswer){
                        userScore += 10;
                        tvScore.setText(userScore+"");
                        tvQuestion.setText(getString(R.string.congrats));
                    }else{
                        intLive -= 1;
                        tvLive.setText(intLive+"");
                        tvQuestion.setText(getString(R.string.wrong));
                    }

                    tvReview.setText(num1 + " + " + num2 + " = " + realAnswer + " (Your Answer = " + intAnswer + ")");

                    btnSubmit.setEnabled(false);
                    btnSubmit.setBackgroundColor(getResources().getColor(R.color.noSubmit));
                    enableNext();

                }

            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetTimer();
                if(answer = true){
//                  If Live 0
                    if(intLive <= 0){
                        Toast.makeText(getApplicationContext(), getString(R.string.gameOver), Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(Addition.this, Result.class);
//                  To pass value through activity
                        intent.putExtra("score", userScore);
                        startActivity(intent);
                        finish();

                    }else{
                        gameContinue();
                    }
                }else{
                    disableNext();
                }
            }
        });
    }

    public void gameContinue(){

        startTimer();
        answer=false;

        num1 = random.nextInt(100);
        num2 = random.nextInt(100);
        realAnswer=num1+num2;

//      Show Random question
        tvQuestion.setText(num1 +" + " + num2);

        editAnswer.setText("");
        tvReview.setText("");
        btnSubmit.setEnabled(true);
        btnSubmit.setBackgroundColor(getResources().getColor(R.color.btnSubmit));
        disableNext();

    }

    public void startTimer(){

        //(Start Time, Interval)
        timer = new CountDownTimer(time_left_in_milis,1000) {
//          To do when timer start
            @Override
            public void onTick(long l) {

//              update timer attribute with l(the countdown value)
                time_left_in_milis=l;
//              update the textview every tick
                updateText();

            }

//          To do when timer finish
            @Override
            public void onFinish() {

                btnSubmit.setEnabled(false);
                btnSubmit.setBackgroundColor(getResources().getColor(R.color.noSubmit));
                answer=true;
                timer_running=false;
//              stop the countdown
                pauseTimer();
                resetTimer();
                updateText();

                intLive -= 1;
                tvLive.setText(intLive+"");
                tvQuestion.setText(getString(R.string.timeout));
                tvReview.setText(num1 + " + " + num2 + " = " + realAnswer + " (Your Answer = " + intAnswer + ")" );

                enableNext();
            }
        }.start();

//      edit back the boolean to default
        timer_running = true;

    }

    public void updateText(){
        int second = (int)(time_left_in_milis/1000) % 60;
//      Change time value into string with format of 2 Digit (%02d)
        String time_left = String.format(Locale.getDefault(),"%02d",second);
        tvTime.setText(time_left);
    }

    public void pauseTimer(){

        timer.cancel();
        timer_running=false;

    }

    public void resetTimer(){
//      Set the timer to 6000 again
        time_left_in_milis=START_TIMER_IN_MILIS;
        updateText();

    }

    public void enableNext(){
        //Enable Next Button again
        btnNext.setEnabled(true);
        btnNext.setBackgroundColor(getResources().getColor(R.color.btnSubmit));

    }

    public void disableNext(){
        btnNext.setEnabled(false);
        btnNext.setBackgroundColor(getResources().getColor(R.color.noSubmit));
    }
}