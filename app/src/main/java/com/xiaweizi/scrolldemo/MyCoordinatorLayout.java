package com.xiaweizi.scrolldemo;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

/**
 * <pre>
 *     author : xiaweizi
 *     class  : com.xiaweizi.scrolldemo.MyCoordinatorLayout
 *     e-mail : 1012126908@qq.com
 *     time   : 2018/04/23
 *     desc   :
 * </pre>
 */

public class MyCoordinatorLayout extends CoordinatorLayout implements Pullable{

    private static final String TAG = "MyCoordinatorLayout:";

    public MyCoordinatorLayout(Context context) {
        this(context, null);
    }

    public MyCoordinatorLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyCoordinatorLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context, attrs, defStyle);
    }

    private void initView(Context context, AttributeSet attrs, int defStyle) {

    }
    private boolean canPulldown = true;

    @Override
    public boolean canPullDown() {
        View childAt = getChildAt(0);
        if (childAt != null) {
            if (childAt instanceof AppBarLayout) {
                ((AppBarLayout) childAt).addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
                    @Override
                    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                        if (verticalOffset >= -10) {
                            canPulldown = true;
                        } else {
                            canPulldown = false;
                        }
                    }
                });
            }
        }
        return canPulldown;
    }

    @Override
    public boolean canPullUp() {
        return false;
    }
}
