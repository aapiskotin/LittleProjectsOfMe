package com.example.p0201_simpleanimation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tvOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvOut = (TextView) findViewById(R.id.tvOut);
        registerForContextMenu(tvOut);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo info) {
        getMenuInflater().inflate(R.menu.context_text_view, menu);
    }

    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.alpha:
                tvOut.startAnimation(AnimationUtils.loadAnimation(this, R.anim.myalpha));
                break;
            case R.id.scale:
                tvOut.startAnimation(AnimationUtils.loadAnimation(this, R.anim.myscale));
                break;
            case R.id.trans:
                tvOut.startAnimation(AnimationUtils.loadAnimation(this, R.anim.mytrans));
                break;
            case R.id.rotate:
                tvOut.startAnimation(AnimationUtils.loadAnimation(this, R.anim.myrotate));
                break;
            case R.id.combo:
                tvOut.startAnimation(AnimationUtils.loadAnimation(this, R.anim.mycombo));
        }
        return super.onContextItemSelected(item);
    }
}
