package com.example.p0151_contextmenu;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tvColour, tvText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvColour = (TextView) findViewById(R.id.tvColour);
        tvText = (TextView) findViewById(R.id.tvText);

        registerForContextMenu(tvColour);
        registerForContextMenu(tvText);
    }

    final int MENU_COLOUR_RED = 1;
    final int MENU_COLOUR_GREEN = 2;
    final int MENU_COLOUR_BLUE = 3;

    final int MENU_SIZE_40 = 4;
    final int MENU_SIZE_50 = 5;
    final int MENU_SIZE_60 = 6;

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        switch (view.getId()) {
            case R.id.tvColour:
                menu.add(0, MENU_COLOUR_BLUE, 0, "Blue");
                menu.add(0, MENU_COLOUR_GREEN, 0, "Green");
                menu.add(0, MENU_COLOUR_RED, 0, "Red");
                break;
            case R.id.tvText:
                menu.add(0,MENU_SIZE_40, 0, "40dp");
                menu.add(0,MENU_SIZE_50, 0, "50dp");
                menu.add(0, MENU_SIZE_60, 0, "60dp");
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MENU_COLOUR_BLUE:
                tvColour.setTextColor(Color.BLUE);
                break;
            case MENU_COLOUR_GREEN:
                tvColour.setTextColor(Color.GREEN);
                break;
            case MENU_COLOUR_RED:
                tvColour.setTextColor(Color.RED);
                break;
            case MENU_SIZE_40:
                tvText.setTextSize(40);
                break;
            case MENU_SIZE_50:
                tvText.setTextSize(50);
                break;
            case MENU_SIZE_60:
                tvText.setTextSize(60);
        }

        return super.onContextItemSelected(item);
    }

}
