package com.vuchfi.dcalc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button pressButton;
    private TextView tInfoView;
    private Button quitButton;

    private View.OnClickListener pressButtonClick = new View.OnClickListener()
    {
        @Override
        public void onClick(View v) {
            pressButtonClickVoid();
        }
    };
    private View.OnClickListener quitButtonClick = new View.OnClickListener()
    {
        @Override
        public void onClick(View v) {
            quitButtonClickVoid();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pressButton = (Button) findViewById(R.id.calculate_button);
//        quitButton = (Button) findViewById(R.id.quit_button);
//        tInfoView = (TextView) findViewById(R.id.tInfoView);
//        pressButton.setOnClickListener(pressButtonClick);
//        quitButton.setOnClickListener(quitButtonClick);

    }

    private void pressButtonClickVoid()
    {
        tInfoView.setText("I was clicked!");
    }
    private void quitButtonClickVoid()
    {
        finishAffinity();
    }
}