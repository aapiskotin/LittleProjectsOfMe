package com.example.p0171_dynamiclayout2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    RadioGroup rgGravity;
    EditText etName;
    Button btnCreate;
    Button btnClear;
    LinearLayout llMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rgGravity = (RadioGroup) findViewById(R.id.rgGravity);
        etName = (EditText) findViewById(R.id.etName);
        llMain = (LinearLayout) findViewById(R.id.llMain);

        btnCreate = (Button) findViewById(R.id.btnCreate);
        btnCreate.setOnClickListener(this);

        btnClear = (Button) findViewById(R.id.btnClear);
        btnClear.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnCreate:
                LinearLayout.LayoutParams viewParams = new LinearLayout.LayoutParams
                        (ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                viewParams.gravity = Gravity.LEFT;

                switch (rgGravity.getCheckedRadioButtonId()) {
                    case R.id.rgLeft:
                        viewParams.gravity = Gravity.LEFT;
                        break;
                    case R.id.rgCenter:
                        viewParams.gravity = Gravity.CENTER_HORIZONTAL;
                        break;
                    case R.id.rgRight:
                        viewParams.gravity = Gravity.RIGHT;
                }

                Button btnNew = new Button(this);
                btnNew.setText(etName.getText().toString());

                llMain.addView(btnNew, viewParams);
                break;
            case R.id.btnClear:
                llMain.removeAllViews();
                Toast.makeText(this,"Deleted", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
