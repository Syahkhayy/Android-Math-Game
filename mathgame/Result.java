package com.syahkhay.mathgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Result extends AppCompatActivity {

    TextView tvFinalResult;
    Button btnAgain, btnExit;
    int score=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        tvFinalResult=findViewById(R.id.tvFinalScore);
        btnAgain=findViewById(R.id.btnAgain);
        btnExit=findViewById(R.id.btnExit);

//      Take int value from previous activity
        Intent intent=getIntent();
        score=intent.getIntExtra("score", 0);
        tvFinalResult.setText(""+score);

        btnAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Result.this, Addition.class);
                startActivity(intent);
                finish();

            }
        });

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Result.this, MainActivity.class);
                startActivity(intent);
                finish();

            }
        });
    }
}