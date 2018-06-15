package com.example.p0161_dynamiclayout;

import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    Button btn;
    Button linearBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout linearLayout = new LinearLayout(this);

        linearLayout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        setContentView(linearLayout, layoutParams);

        ViewGroup.LayoutParams viewParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        tv = new TextView(this);
        tv.setText("Sdelal sam");
        tv.setLayoutParams(viewParams);
        tv.setTextSize(50);
        linearLayout.addView(tv);

        btn = new Button(this);
        btn.setText("Nu nazhimay");
        btn.setLayoutParams(viewParams);
        linearLayout.addView(btn);

        LinearLayout.LayoutParams linearParams = new LinearLayout.LayoutParams
                (ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearParams.leftMargin = 200;

        linearBtn = new Button(this);
        linearBtn.setText("Btn1");
        linearLayout.addView(linearBtn, linearParams);

        LinearLayout.LayoutParams linearParams2 = new LinearLayout.LayoutParams
                (ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearParams2.gravity = Gravity.CENTER;

        Button button2 = new Button(this);
        button2.setText("Button2");
        linearLayout.addView(button2, linearParams2);
    }
}
