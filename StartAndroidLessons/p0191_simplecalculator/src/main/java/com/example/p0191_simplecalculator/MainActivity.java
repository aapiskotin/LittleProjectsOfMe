package com.example.p0191_simplecalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etNumber1;
    EditText etNumber2;
    Button btnAdd;
    Button btnSub;
    Button btnMul;
    Button btnDiv;
    TextView tvOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);
        btnSub = (Button) findViewById(R.id.btnSub);
        btnSub.setOnClickListener(this);
        btnMul = (Button) findViewById(R.id.btnMul);
        btnMul.setOnClickListener(this);
        btnDiv = (Button) findViewById(R.id.btnDiv);
        btnDiv.setOnClickListener(this);

        tvOut = (TextView) findViewById(R.id.tvOut);

        etNumber1 = (EditText) findViewById(R.id.etNumber1);
        etNumber2 = (EditText) findViewById(R.id.etNumber2);
    }

    @Override
    public void onClick(View view) {
        String num1 = etNumber1.getText().toString();
        String num2 = etNumber2.getText().toString();
        float result;
        if(num1.equals("") || num2.equals("")) {
            Toast.makeText(this, "Enter the numbers", Toast.LENGTH_SHORT).show();
        }
        else {
            switch (view.getId()) {
                case R.id.btnAdd:
                    result = Float.valueOf(num1) + Float.valueOf(num2);
                    tvOut.setText(num1 + " + " + num2 + " = " + result);
                    break;
                case R.id.btnSub:
                    result = Float.valueOf(num1) - Float.valueOf(num2);
                    tvOut.setText(num1 + " - " + num2 + " = " + result);
                    break;
                case R.id.btnMul:
                    result = Float.valueOf(num1) * Float.valueOf(num2);
                    tvOut.setText(num1 + " * " + num2 + " = " + result);
                    break;
                case R.id.btnDiv:
                    if (num2.equals("0")) {
                        Toast.makeText(this, "Can't divvide by 0", Toast.LENGTH_SHORT).show();
                    } else {
                        result = Float.valueOf(num1) / Float.valueOf(num2);
                        tvOut.setText(num1 + " / " + num2 + " = " + result);
                        break;
                    }
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.menuReset:
                tvOut.setText("");
                etNumber1.setText("");
                etNumber2.setText("");
                break;
            case R.id.menuQuit:
                finish();
                break;
        }

        return super.onOptionsItemSelected(menuItem);
    }
}
