package com.example.touch;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.lang.ref.WeakReference;

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

    public static int HIGH = 10;
    public static int NORMAL = 100;
    public static int LOW = 1000;

    private static long DEFAULT_DURATION = 2000;

    private static final String TAG = "GestureTouchUtils";

    /**
     * 模拟手指触摸操作
     *
     * @param view 一般为 ViewGroup
     */
    public static void simulateClick(View view, float x, float y) {
        dealSimulateClick(view, x, y);
    }

    /**
     * 模拟手指触摸操作
     *
     * @param activity 当前 Activity
     */
    public static void simulateClick(Activity activity, float x, float y) {
        dealSimulateClick(activity, x, y);
    }

    /**
     * 模拟手势滑动
     *
     * @param view   滑动的 view
     * @param startX 起始位置 x
     * @param startY 起始位置 y
     * @param endX   终点位置 x
     * @param endY   终点位置 y
     */
    public static void simulateScroll(View view, int startX, int startY, int endX, int endY) {
        simulateScroll(view, startX, startY, endX, endY, DEFAULT_DURATION);
    }

    /**
     * 模拟手势滑动
     *
     * @param activity 当前的 activity
     * @param startX   起始位置 x
     * @param startY   起始位置 y
     * @param endX     终点位置 x
     * @param endY     终点位置 y
     */
    public static void simulateScroll(Activity activity, int startX, int startY, int endX, int endY) {
        simulateScroll(activity, startX, startY, endX, endY, DEFAULT_DURATION);
    }

    /**
     * 模拟手势滑动
     *
     * @param view     滑动的 view
     * @param startX   起始位置 x
     * @param startY   起始位置 y
     * @param endX     终点位置 x
     * @param endY     终点位置 y
     * @param duration 滑动时长 单位：ms
     */
    public static void simulateScroll(View view, int startX, int startY, int endX, int endY, long duration) {
        simulateScroll(view, startX, startY, endX, endY, duration, NORMAL);
    }

    /**
     * 模拟手势滑动
     *
     * @param activity 当前的 activity
     * @param startX   起始位置 x
     * @param startY   起始位置 y
     * @param endX     终点位置 x
     * @param endY     终点位置 y
     * @param duration 滑动时长 单位 ms
     */
    public static void simulateScroll(Activity activity, int startX, int startY, int endX, int endY, long duration) {
        simulateScroll(activity, startX, startY, endX, endY, duration, NORMAL);
    }

    /**
     * 模拟手势滑动
     *
     * @param view     滑动的 view
     * @param startX   起始位置 x
     * @param startY   起始位置 y
     * @param endX     终点位置 x
     * @param endY     终点位置 y
     * @param duration 滑动时长 单位：ms
     * @param period   滑动周期
     *                 {@link #LOW} 慢
     *                 {@link #NORMAL} 正常
     *                 {@link #HIGH} 高
     */
    public static void simulateScroll(View view, int startX, int startY, int endX, int endY, long duration, int period) {
        dealSimulateScroll(view, startX, startY, endX, endY, duration, period);
    }

    /**
     * 模拟手势滑动
     *
     * @param activity 当前的 activity
     * @param startX   起始位置 x
     * @param startY   起始位置 y
     * @param endX     终点位置 x
     * @param endY     终点位置 y
     * @param duration 滑动时长 单位 ms
     * @param period   滑动周期
     *                 {@link #LOW} 慢
     *                 {@link #NORMAL} 正常
     *                 {@link #HIGH} 高
     */
    public static void simulateScroll(Activity activity, float startX, float startY, float endX, float endY, long duration, int period) {
        dealSimulateScroll(activity, startX, startY, endX, endY, duration, period);
    }

    private static void dealSimulateScroll(Object object, float startX, float startY, float endX, float endY, long duration, int period) {
        long downTime = SystemClock.uptimeMillis();
        Handler handler;
        if (object instanceof View) {
            View view = (View) object;
            handler = new ViewHandler(view);
            view.onTouchEvent(MotionEvent.obtain(downTime, downTime, MotionEvent.ACTION_DOWN, startX, startY, 0));
            GestureBean bean = new GestureBean(startX, startY, endX, endY, duration, period);
            Message.obtain(handler, 1, bean).sendToTarget();
        } else if (object instanceof Activity) {
            Activity activity = (Activity) object;
            handler = new ActivityHandler(activity);
            activity.dispatchTouchEvent(MotionEvent.obtain(downTime, downTime, MotionEvent.ACTION_DOWN, startX, startY, 0));
            GestureBean bean = new GestureBean(startX, startY, endX, endY, duration, period);
            Message.obtain(handler, 1, bean).sendToTarget();
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

    static class ViewHandler extends Handler {
        WeakReference<View> mView;

        ViewHandler(View activity) {
            super(Looper.getMainLooper());
            mView = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            View theView = mView.get();
            if (theView == null || !theView.isAttachedToWindow()) {
                return;
            }
            long downTime = SystemClock.uptimeMillis();
            GestureBean bean = (GestureBean) msg.obj;
            long count = bean.count;
            if (count >= bean.totalCount) {
                theView.onTouchEvent(MotionEvent.obtain(downTime, downTime, MotionEvent.ACTION_UP, bean.endX, bean.endY, 0));
            } else {
                theView.onTouchEvent(MotionEvent.obtain(downTime, downTime, MotionEvent.ACTION_MOVE, bean.startX + bean.ratioX * count, bean.startY + bean.ratioY * count, 0));
                bean.count++;
                Message message = new Message();
                message.obj = bean;
                sendMessageDelayed(message, bean.period);
            }
        }
    }

    static class ActivityHandler extends Handler {
        WeakReference<Activity> mActivity;

        ActivityHandler(Activity activity) {
            super(Looper.getMainLooper());
            mActivity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            Activity theActivity = mActivity.get();
            if (theActivity == null || theActivity.isFinishing()) {
                return;
            }
            long downTime = SystemClock.uptimeMillis();
            GestureBean bean = (GestureBean) msg.obj;
            long count = bean.count;
            if (count % 10 == 0) {
                Log.i(TAG, "handleMessage: " + count);
            }
            if (count >= bean.totalCount) {
                theActivity.dispatchTouchEvent(MotionEvent.obtain(downTime, downTime, MotionEvent.ACTION_UP, bean.endX, bean.endY, 0));
            } else {
                theActivity.dispatchTouchEvent(MotionEvent.obtain(downTime, downTime, MotionEvent.ACTION_MOVE, bean.startX + bean.ratioX * count, bean.startY + bean.ratioY * count, 0));
                bean.count++;
                Message message = new Message();
                message.obj = bean;
                sendMessageDelayed(message, bean.period);
            }
        }
    }
}
