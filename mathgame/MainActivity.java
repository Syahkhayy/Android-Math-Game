package com.syahkhay.mathgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button btnAdd, btnSub, btnMulti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.btnAdd);
        btnSub = findViewById(R.id.btnSub);
        btnMulti = findViewById(R.id.btnMulti);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, Addition.class);
                startActivity(intent);
                finish();
            }
        });

        btnSub.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                Intent intent = new Intent(MainActivity.this, Subtraction.class);
                startActivity(intent);
                finish();
            }
        });

        btnMulti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(MainActivity.this, Multiplication.class);
                startActivity(intent);
                finish();
            }
        });
    }
}