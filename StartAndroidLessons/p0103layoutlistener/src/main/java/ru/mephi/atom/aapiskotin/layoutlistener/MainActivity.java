package ru.mephi.atom.aapiskotin.layoutlistener;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tvOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvOut = (TextView) findViewById(R.id.tvOut);
    }

    public void onClickOk(View v)   {
        tvOut.setText("It is OK");
    }

    public void onClickCancel(View v)   {
        tvOut.setText("It is Cancelled");
    }
}
