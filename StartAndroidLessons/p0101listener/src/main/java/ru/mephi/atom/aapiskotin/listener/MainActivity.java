package ru.mephi.atom.aapiskotin.listener;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView theText;
    Button btnOk;
    Button btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        theText = (TextView) findViewById(R.id.tvOut);
        btnOk = (Button) findViewById(R.id.btnOk);
        btnCancel = (Button) findViewById(R.id.btnCancel);

        View.OnClickListener oclListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId())  {
                    case R.id.btnOk :
                        theText.setText("It is OK");
                        break;
                    case R.id.btnCancel :
                        theText.setText("Cancelled");
                        break;
                }
            }
        };

        btnOk.setOnClickListener(oclListener);
        btnCancel.setOnClickListener(oclListener);
    }
}
