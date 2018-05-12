package com.xiaweizi.scrolldemo;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.touch.GestureTouchUtils;

import java.lang.ref.WeakReference;

public class GestureTouchActivity extends AppCompatActivity {

    private int count = 0;
    private MyHandler mHandler;
    private MyView bottom;
    private MyView top;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture_touch);
        mHandler = new MyHandler(this);
        findViewById(R.id.bt_click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                top = findViewById(R.id.my_view_top);
                GestureTouchUtils.simulateClick(top, 100, 100);
                bottom = findViewById(R.id.my_view_bottom);
                GestureTouchUtils.simulateClick(bottom, 100, 100);
                mHandler.sendEmptyMessage(1);
            }
        });

    }
    
    static class MyHandler extends Handler {
        WeakReference<GestureTouchActivity> mActivity;
    
        MyHandler(GestureTouchActivity activity) {
            mActivity = new WeakReference<>(activity);
        }
    
        @Override
        public void handleMessage(Message msg) {
            GestureTouchActivity theActivity = mActivity.get();
            if (theActivity == null || theActivity.isFinishing()) {
                return;
            }
            theActivity.count ++;
            if (theActivity.count <= 10) {
                theActivity.mHandler.sendEmptyMessageDelayed(1, 1000);
                GestureTouchUtils.simulateClick(theActivity, 500, 150 * theActivity.count);
            }
        }
    }
}
