package com.example.touch;

import android.app.Activity;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;

/**
 * <pre>
 *     author : xiaweizi
 *     class  : com.example.touch.GestureTouchUtils
 *     e-mail : 1012126908@qq.com
 *     time   : 2018/05/08
 *     desc   : 模拟手势的触摸滑动操作
 * </pre>
 */

public class GestureTouchUtils {

    /**
     * 模拟手指触摸操作
     * @param view 一般为 ViewGroup
     */
    public static void simulateClick(View view, float x, float y) {
        dealSimulateClick(view, x, y);
    }

    /**
     * 模拟手指触摸操作
     * @param activity 当前 Activity
     */
    public static void simulateClick(Activity activity, float x, float y) {
        dealSimulateClick(activity, x, y);
    }

    public static void simulateScroll(View view, int startX, int startY, int endX, int endY, long duration) {
        dealSimulateScroll(view, startX, startY, endX, endY, duration);
    }

    public static void simulateScroll(Activity activity, int startX, int startY, int endX, int endY, long duration) {
        dealSimulateScroll(activity, startX, startY, endX, endY, duration);
    }

    private static void dealSimulateScroll(Object object, int startX, int startY, int endX, int endY, long duration) {
        long downTime = SystemClock.uptimeMillis();
        long temDuration = duration / 5;
        int tempX = (endX - startX) / 5;
        int tempY = (endY - startY) / 5;
        if (object instanceof View) {
            ((View) object).onTouchEvent(MotionEvent.obtain(downTime, downTime + temDuration, MotionEvent.ACTION_DOWN, startX, startY, 0));
            ((View) object).onTouchEvent(MotionEvent.obtain(downTime + temDuration, downTime + temDuration * 2, MotionEvent.ACTION_MOVE, startX + tempX * 2, startY + tempY * 2, 0));
            ((View) object).onTouchEvent(MotionEvent.obtain(downTime + temDuration * 2, downTime + temDuration * 3, MotionEvent.ACTION_MOVE, startX + tempX * 3, startY + tempY * 3, 0));
            ((View) object).onTouchEvent(MotionEvent.obtain(downTime + temDuration * 3, downTime + temDuration * 4, MotionEvent.ACTION_MOVE, startX + tempX * 4, startY + tempY * 4, 0));
            ((View) object).onTouchEvent(MotionEvent.obtain(downTime + temDuration * 4, downTime + temDuration * 5, MotionEvent.ACTION_UP, endX, endY, 0));

        } else if (object instanceof Activity) {
            ((Activity) object).dispatchTouchEvent(MotionEvent.obtain(downTime, downTime + temDuration, MotionEvent.ACTION_DOWN, startX, startY, 0));
            ((Activity) object).dispatchTouchEvent(MotionEvent.obtain(downTime + temDuration, downTime + temDuration * 2, MotionEvent.ACTION_MOVE, startX + tempX * 2, startY + tempY * 2, 0));
            ((Activity) object).dispatchTouchEvent(MotionEvent.obtain(downTime + temDuration * 2, downTime + temDuration * 3, MotionEvent.ACTION_MOVE, startX + tempX * 3, startY + tempY * 3, 0));
            ((Activity) object).dispatchTouchEvent(MotionEvent.obtain(downTime + temDuration * 3, downTime + temDuration * 4, MotionEvent.ACTION_MOVE, startX + tempX * 4, startY + tempY * 4, 0));
            ((Activity) object).dispatchTouchEvent(MotionEvent.obtain(downTime + temDuration * 4, downTime + temDuration * 5, MotionEvent.ACTION_UP, endX, endY, 0));
        }
    }

        /**
         * 处理手势点击的操作
         */
    private static void dealSimulateClick(Object object, float x, float y) {
        long downTime = SystemClock.uptimeMillis();
        final MotionEvent downEvent = MotionEvent.obtain(downTime, downTime, MotionEvent.ACTION_DOWN, x, y, 0);
        downTime += 500;
        final MotionEvent upEvent = MotionEvent.obtain(downTime, downTime, MotionEvent.ACTION_UP, x, y, 0);
        if (object instanceof View) {
            ((View) object).onTouchEvent(downEvent);
            ((View) object).onTouchEvent(upEvent);
        } else if (object instanceof Activity) {
            ((Activity) object).dispatchTouchEvent(downEvent);
            ((Activity) object).dispatchTouchEvent(upEvent);
        }
        downEvent.recycle();
        upEvent.recycle();
    }
}
