package ru.mephi.atom.aapiskotin.onclickbutton;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView myText;
    Button myBtnOk;
    Button myBtnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myText = (TextView) findViewById(R.id.tvOut);
        myBtnOk = (Button) findViewById(R.id.btnOk);
        myBtnCancel = (Button) findViewById(R.id.btnCancel);

        View.OnClickListener oclBtnOk = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myText.setText("Ok button pressed");
            }
        };

        myBtnOk.setOnClickListener(oclBtnOk);

        View.OnClickListener oclBtnCancel = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myText.setText("CANCELLED");
            }
        };

        myBtnCancel.setOnClickListener(oclBtnCancel);
    }
}
