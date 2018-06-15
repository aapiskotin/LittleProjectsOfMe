package com.example.p0121logandmess;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView textView;
    Button btnOk;
    Button btnCancel;

    private static final String TAG = "Standart Teg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("Moi hui", "Ya rodilsya");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.tvOut);
        btnOk = (Button) findViewById(R.id.btnOk);
        btnCancel = (Button) findViewById(R.id.btnCancel);

        btnCancel.setOnClickListener(this);
        btnOk.setOnClickListener(this);
        Log.d(TAG, "Sdelano kak nado");

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnCancel:
                textView.setText("Canclelled!");
                Log.d(TAG, "Cancel pressed");
                Toast.makeText(this, "Cancel???", Toast.LENGTH_LONG).show();
                break;
            case R.id.btnOk:
                textView.setText("Oked");
                Log.d(TAG, "Ok pressed");
                Toast.makeText(this, "OK!!!", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
