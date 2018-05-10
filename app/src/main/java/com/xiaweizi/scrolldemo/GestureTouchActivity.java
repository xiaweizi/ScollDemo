package com.xiaweizi.scrolldemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.touch.GestureTouchUtils;

public class GestureTouchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture_touch);
        findViewById(R.id.bt_click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GestureTouchUtils.simulateClick(findViewById(R.id.my_view_left), 100, 100);
                GestureTouchUtils.simulateClick(findViewById(R.id.my_view_right), 100, 100);
                GestureTouchUtils.simulateClick(GestureTouchActivity.this, 200, 200);
            }
        });
    }
}
