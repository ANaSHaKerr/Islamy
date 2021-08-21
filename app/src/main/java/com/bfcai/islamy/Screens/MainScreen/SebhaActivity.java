package com.bfcai.islamy.Screens.MainScreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bfcai.islamy.R;

import java.util.Arrays;


public class SebhaActivity extends AppCompatActivity {
    private long initialValue = 0;
    TextView textView;
    Button add,minus,reset;
    Handler handler = new Handler();
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sebha);
        toolbar = findViewById(R.id.sebhaToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("مسبحة");

        textView = findViewById(R.id.textView);
        add = findViewById(R.id.add);
        minus = findViewById(R.id.minus);
        reset = findViewById(R.id.reset);

        add.setOnClickListener(v->{
            addCounter.run();
        });
        minus.setOnClickListener( v->{
            minusCounter.run();
        });
        reset.setOnClickListener(v -> {
            initialValue = 0;
            textView.setText(String.valueOf(initialValue));
        });
    }
    Runnable addCounter =  new Runnable(){
        @Override
        public void run(){
            textView.setText(String.valueOf(++initialValue));
        }
    };
    Runnable minusCounter =  new Runnable(){
        @Override
        public void run(){
            textView.setText(String.valueOf(--initialValue));
        }
    };

}
