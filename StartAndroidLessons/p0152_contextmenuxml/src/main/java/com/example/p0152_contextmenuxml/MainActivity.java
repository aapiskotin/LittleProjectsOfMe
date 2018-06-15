package com.example.p0152_contextmenuxml;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tvColor, tvSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvColor = (TextView) findViewById(R.id.tvColored);
        tvSize = (TextView) findViewById(R.id.tvSize);

        registerForContextMenu(tvColor);
        registerForContextMenu(tvSize);
    }

    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {
        switch (view.getId()) {
            case R.id.tvColored:
                getMenuInflater().inflate(R.menu.menu_colors, menu);
                break;
            case R.id.tvSize:
                getMenuInflater().inflate(R.menu.menu_size, menu);
        }
    }

    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.SET_40:
                tvSize.setTextSize(40);
                tvSize.append("is now 40dp");
                break;
            case R.id.SET_50:
                tvSize.setTextSize(50);
                tvSize.append("is now 50dp");
                break;
            case R.id.SET_60:
                tvSize.setTextSize(60);
                tvSize.append("is now 60dp");
                break;
            case R.id.SET_BLUE:
                tvColor.setTextColor(Color.BLUE);
                tvColor.setText("Bozhe moi");
                break;
            case R.id.SET_GREEN:
                tvColor.setTextColor(Color.GREEN);
                break;
            case R.id.SET_RED:
                tvColor.setTextColor(Color.RED);
        }

        return super.onContextItemSelected(item);
    }
}
