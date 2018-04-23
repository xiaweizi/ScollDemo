package com.xiaweizi.scrolldemo;

import android.app.Activity;
import android.os.SystemClock;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * <pre>
 *     author : xiaweizi
 *     class  : com.xiaweizi.scrolldemo.TouchEvent
 *     e-mail : 1012126908@qq.com
 *     time   : 2018/04/23
 *     desc   :
 * </pre>
 */

public class TouchEvent {
    private static final String TAG = "TouchEvent";

    /**
     * 模仿手指点击控件事件
     *
     * @param view 控件
     * @param x    相对控件的X坐标
     * @param y    相对控件的Y坐标
     */
    public static void simulateClick(View view, float x, float y) {
        long downTime = SystemClock.uptimeMillis();
        final MotionEvent downEvent = MotionEvent.obtain(downTime, downTime, MotionEvent.ACTION_DOWN, x, y, 0);
        downTime += 1000;
        final MotionEvent upEvent = MotionEvent.obtain(downTime, downTime, MotionEvent.ACTION_UP, x, y, 0);
        view.onTouchEvent(downEvent);
        view.onTouchEvent(upEvent);
        downEvent.recycle();
        upEvent.recycle();
    }

    /**
     * 模仿手机点击屏幕事件
     *
     * @param x        X坐标
     * @param y        Y坐标
     * @param activity 传进去的活动对象
     */
    public static void setFingerClick(int x, int y, Activity activity) {
        MotionEvent evenDownt = MotionEvent.obtain(System.currentTimeMillis(),
                System.currentTimeMillis() + 100, MotionEvent.ACTION_DOWN, x, y, 0);
        activity.dispatchTouchEvent(evenDownt);
        MotionEvent eventUp = MotionEvent.obtain(System.currentTimeMillis(),
                System.currentTimeMillis() + 100, MotionEvent.ACTION_UP, x, y, 0);
        activity.dispatchTouchEvent(eventUp);
        evenDownt.recycle();
        eventUp.recycle();
        Log.d(TAG, "setFingerClick: ");
    }

    /**
     * 模拟向下滑动事件
     *
     * @param distance 滑动的距离
     * @param activity 传进去的活动对象
     */
    public static void setMoveToBottom(int distance, Activity activity) {
        long downTime = SystemClock.uptimeMillis();
        int tempDistance = distance / 5;
        int time = 1;
        activity.dispatchTouchEvent(MotionEvent.obtain(downTime, downTime + time,
                MotionEvent.ACTION_DOWN, 0, 400, 0));
        activity.dispatchTouchEvent(MotionEvent.obtain(downTime + time, time * 2,
                MotionEvent.ACTION_MOVE, 0, 400 + tempDistance, 0));
        activity.dispatchTouchEvent(MotionEvent.obtain(downTime + time * 2, downTime + time * 3,
                MotionEvent.ACTION_MOVE, 0, 400 + tempDistance * 2, 0));
        activity.dispatchTouchEvent(MotionEvent.obtain(downTime + time * 3, downTime + time * 4,
                MotionEvent.ACTION_MOVE, 0, 400 + tempDistance * 3, 0));
        activity.dispatchTouchEvent(MotionEvent.obtain(downTime + time * 4, downTime + time * 5,
                MotionEvent.ACTION_MOVE, 0, 400 + tempDistance * 4, 0));
        activity.dispatchTouchEvent(MotionEvent.obtain(downTime + time * 5, downTime + time * 6,
                MotionEvent.ACTION_UP, 0, 400 + tempDistance * 5, 0));
        Log.d(TAG, "setMoveToBottom: ");
    }

    /**
     * 模拟向上滑动事件
     *
     * @param distance 滑动的距离
     * @param activity 传进去的活动对象
     */
    public static void setMoveToTop(int distance, Activity activity) {
        activity.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(),
                MotionEvent.ACTION_DOWN, 400, 500, 0));
        activity.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(),
                MotionEvent.ACTION_MOVE, 400, 500 + distance, 0));
        activity.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(),
                MotionEvent.ACTION_UP, 400, 500 + distance, 0));
        Log.d(TAG, "setMoveToTop: ");
    }
}
