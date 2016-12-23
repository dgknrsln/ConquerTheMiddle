package com.example.dgknrsln.conquerthemiddle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

    }

    public void newGame(View v){

        Intent intent1 = new Intent(this, BoardActivity.class);
        startActivity(intent1);

    }

    public void instructions(View view){

        Intent intent2 = new Intent(this,Instructions.class);
        startActivity(intent2);
    }


}
