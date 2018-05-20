package com.xiaweizi.scrolldemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.touch.GestureTouchUtils;

public class GestureScrollActivity extends AppCompatActivity {

    private MyScrollView mView;
    private int[][] points = new int[][]{{70, 130}, {160, 70}, {260, 130}, {100, 250}, {220, 250}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture_scroll);
        mView = findViewById(R.id.my_scroll_view);
        findViewById(R.id.bt1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GestureTouchUtils.simulateScroll(mView, points[0][0], points[0][1], points[2][0], points[2][1], 2000, GestureTouchUtils.NORMAL);
            }
        });

        findViewById(R.id.bt2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GestureTouchUtils.simulateScroll(mView, points[2][0], points[2][1], points[3][0], points[3][1], 1000, GestureTouchUtils.HIGH);
            }
        });
        findViewById(R.id.bt3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GestureTouchUtils.simulateScroll(mView, points[3][0], points[3][1], points[1][0], points[1][1], 3000, GestureTouchUtils.LOW);
            }
        });
        findViewById(R.id.bt4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GestureTouchUtils.simulateScroll(mView, points[1][0], points[1][1], points[4][0], points[4][1], 2000, GestureTouchUtils.HIGH);
            }
        });
        findViewById(R.id.bt5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GestureTouchUtils.simulateScroll(mView, points[4][0], points[4][1], points[0][0], points[0][1], 3000, GestureTouchUtils.LOW);
            }
        });
        findViewById(R.id.bt6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.bt6).setBackgroundColor(mView.changeColor());
            }
        });

    }
}
