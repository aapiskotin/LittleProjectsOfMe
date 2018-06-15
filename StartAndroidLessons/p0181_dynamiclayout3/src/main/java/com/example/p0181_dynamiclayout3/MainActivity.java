package com.example.p0181_dynamiclayout3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    Button btnLeft;
    Button btnRight;
    SeekBar sbWeight;
    LinearLayout.LayoutParams btnLeftParams;
    LinearLayout.LayoutParams btnRightParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLeft = (Button) findViewById(R.id.btn1);
        btnRight = (Button) findViewById(R.id.btn2);

        sbWeight = (SeekBar) findViewById(R.id.sbWeight);

        btnLeftParams = (LinearLayout.LayoutParams) btnLeft.getLayoutParams();
        btnRightParams = (LinearLayout.LayoutParams) btnRight.getLayoutParams();

        sbWeight.setOnSeekBarChangeListener(this);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
        int leftValue = progress;
        int rightValue = seekBar.getMax() - progress;

        btnLeftParams.weight = leftValue;
        btnRightParams.weight = rightValue;

        btnLeft.setText(String.valueOf(leftValue));
        btnRight.setText(String.valueOf(rightValue));

        btnLeft.requestLayout();
        btnRight.requestLayout();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
