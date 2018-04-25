package com.xiaweizi.scrolldemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.LinearLayout;

/**
 * <pre>
 *     author : xiaweizi
 *     class  : com.xiaweizi.scrolldemo.ShadowBackLayout
 *     e-mail : 1012126908@qq.com
 *     time   : 2018/04/24
 *     desc   :
 * </pre>
 */

public class ShadowBackLayout extends LinearLayout {

    private int bg_color;
    private int paddingTop;
    private int space;
    private int triangle_height;
    private Paint paint;
    private RectF rectF;
    int height,width;

    private float shadowLen;

    public ShadowBackLayout(Context context) {
        super(context);
        init(context);
    }

    public ShadowBackLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    void init(Context context){
        space= (int) dip2px(context,2);
        triangle_height=space*3;
        shadowLen = dip2px(context,5);
        paddingTop = dip2px(context,4);
        bg_color= Color.WHITE;
        paint=new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);
        paint.setShadowLayer(shadowLen, 0, 0, Color.parseColor("#330a3f80"));
        paint.setColor(bg_color);
        rectF=new RectF();
        setLayerType(View.LAYER_TYPE_SOFTWARE,null);
    }

    public void setBg_color(int bg_color) {
        this.bg_color = bg_color;
        paint.setColor(bg_color);
    }


    @Override
    protected void dispatchDraw(Canvas canvas) {
        if(width==0){
            return;
        }
        rectF.left=shadowLen;
        rectF.top=paddingTop;
        rectF.right=width-shadowLen;
        rectF.bottom=height-shadowLen;
        canvas.drawRoundRect(rectF,11,11,paint);
        super.dispatchDraw(canvas);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width=w;
        height=h;
    }

    private  int dip2px(Context context, float dpValue) {
        if (context == null) {
            return 0;
        }
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        int px = (int) (dpValue * (metrics.densityDpi / 160f));
        return px;
    }
}
