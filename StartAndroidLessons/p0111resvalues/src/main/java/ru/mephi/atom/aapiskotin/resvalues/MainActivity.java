package ru.mephi.atom.aapiskotin.resvalues;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tvBottom = (TextView) findViewById(R.id.tvBottom);
        Button btnButton = (Button) findViewById(R.id.btnBottom);
        LinearLayout llBottom = (LinearLayout) findViewById(R.id.llBottom);

        llBottom.setBackgroundResource(R.color.llBottomColor);
        btnButton.setText(R.string.btnBottomText);
        tvBottom.setText(R.string.tvBottomText);

    }
}
