package com.vuchfi.dcalc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private Button calcButton;
    private Button quitButton;
    private TextView xField;
    private TextView ugenField;
    private TextView deltaTField;
    private TextView upeakField;
    private TextView rField;
    private RadioButton ohmButton;
    private RadioButton kOhmButton;
    private TextView eField;
    private CheckBox sButton;
    private TextView sField;
    private CheckBox dButton;
    private TextView dField;
    private double e0 = 8.85e-12; //[F/m]
    private double e = 3.5; //default value;
    private double Rapkr; //Ω
    private double factorR;
    private double Ugen; //V
    private double Upeak; //mV
    private double S; //mm^2
    private double S_;
    private double d; //mm
    private double deltaT; //μs
    private double dmkm = -1;
    private double dmkm_ = 0;

    //listener'iai:

    private final View.OnClickListener calcButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //code:
            calcButtonFunction();
        }
    };

    private final View.OnClickListener quitButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            quitButtonFunction();
        }
    };

    private final View.OnClickListener dButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (dButton.isChecked())
            {
                sButton.setChecked(false);
            }
            else
            {
                dButton.setChecked(true);
            }
        }
    };

    private final View.OnClickListener sButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (sButton.isChecked())
            {
                dButton.setChecked(false);
            }
            else
            {
                sButton.setChecked(true);
            }
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //get all controls:
        calcButton = findViewById(R.id.calc_button);
        quitButton = findViewById(R.id.quit_button);
        xField = findViewById(R.id.x_field);
        ugenField = findViewById(R.id.ugen_field);
        deltaTField = findViewById(R.id.delta_t_field);
        upeakField = findViewById(R.id.upeak_field);
        rField = findViewById(R.id.rapkr_field);
        ohmButton = findViewById(R.id.ohms_button);
        kOhmButton = findViewById(R.id.kohms_button);
        eField = findViewById(R.id.epsilon_field);
        sButton = findViewById(R.id.s_button);
        sField = findViewById(R.id.s_field);
        dButton = findViewById(R.id.d_button);
        dField = findViewById(R.id.d_field);
        //listenerių priskyrimas:
        quitButton.setOnClickListener(quitButtonClickListener);
        calcButton.setOnClickListener(calcButtonClickListener);
        sButton.setOnClickListener(sButtonClickListener);
        dButton.setOnClickListener(dButtonClickListener);
    }

    //naudingos funkcijos:
    private void calcButtonFunction()
    {
        try
        {


            xField.setText("Atsakymas :) μm");
            Ugen = Double.parseDouble(ugenField.getText().toString());
            Upeak = Double.parseDouble(upeakField.getText().toString());
            Rapkr = Double.parseDouble(rField.getText().toString());
            e = Double.parseDouble(eField.getText().toString());
            d = Double.parseDouble(dField.getText().toString());
            S = Double.parseDouble(sField.getText().toString());
            deltaT = Double.parseDouble(deltaTField.getText().toString());
            get_dmkm();
            String ss = "Atsakymas x μm";
            xField.setText(ss.replace("x", String.valueOf(dmkm_)));
        }
        catch (Exception ex)
        {
            Toast.makeText(this, "Įvesta ne skaičiai?", Toast.LENGTH_SHORT).show();
        }

    }

    private void quitButtonFunction()
    {
        Toast.makeText(this, "Išjungiama", Toast.LENGTH_SHORT).show();
        finishAndRemoveTask();
    }

    private void get_dmkm ()
    {
        if (ohmButton.isChecked())
        {
            factorR = 1;
        }
        else if (kOhmButton.isChecked())
        {
            factorR = 1000;
        }
        else
        {
            factorR = 1;
        }
        if (dButton.isChecked())
        {
            S_ = 1e-6*Math.PI * d*d/4;
        }
        else if (sButton.isChecked())
        {
            S_ = 1e-6*S;
        }
        dmkm = e*e0*Ugen/((deltaT*1e-6)*((Upeak*1e-3)/((Rapkr*factorR)*S_)));
        dmkm_ = Math.round((dmkm / 1e-6)*1000d)/1000d; //μm...
    }

}