package ru.mephi.atom.aapiskotin.viewbyid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView myTextView = (TextView) findViewById(R.id.sanya);
        myTextView.setText("Vanya sosi hui");

        Button myButton = (Button) findViewById(R.id.button);
        myButton.setText("Touch Me");
        myButton.setEnabled(false);

        CheckBox myChkBx = (CheckBox) findViewById(R.id.myChkBx);
        myChkBx.setChecked(true);

    }
}
