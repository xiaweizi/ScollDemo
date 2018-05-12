package com.xiaweizi.scrolldemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.touch.GestureTouchUtils;

public class GestureScrollActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture_scroll);
        findViewById(R.id.bt_simulate_scroll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GestureTouchUtils.simulateScroll(findViewById(R.id.my_scroll_view), 300, 300, 500, 500, 2);
            }
        });
    }
}
