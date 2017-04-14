package com.example.vuminhduc.crazymath;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton img ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fullAct();
        setContentView(R.layout.activity_main);
        initsComponent() ;
        setEvents();
        findViewByIds();

    }

    private void fullAct() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    private void findViewByIds() {

    }

    private void setEvents() {
        findViewById(R.id.imv_play).setOnClickListener(this);
    }

    private void initsComponent() {
    }

    private void goPlay() {
        Intent intent = new Intent(this, playActivity.class) ;
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        goPlay();
    }
}
