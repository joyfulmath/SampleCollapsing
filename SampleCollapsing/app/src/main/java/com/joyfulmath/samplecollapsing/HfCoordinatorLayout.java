package com.joyfulmath.samplecollapsing;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by @joyfulmath.lu on 2016-08-25.
 */
public class HfCoordinatorLayout extends CoordinatorLayout {
    onNestedActionListener listener = null;


    public HfCoordinatorLayout(Context context) {
        super(context);
    }

    public HfCoordinatorLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HfCoordinatorLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setOnNestActionListener(onNestedActionListener listener)
    {
        this.listener = listener;
    }


    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        TraceLog.i("nestedScrollAxes:"+nestedScrollAxes);
        boolean flag = super.onStartNestedScroll(child, target, nestedScrollAxes);
        TraceLog.i(flag+"");
        if(listener!=null)
        {
            listener.onStartScroll();
        }
        return flag;
    }

    @Override
    public void onNestedScrollAccepted(View child, View target, int nestedScrollAxes) {
        super.onNestedScrollAccepted(child, target, nestedScrollAxes);
        TraceLog.i("nestedScrollAxes:"+nestedScrollAxes);
    }

    @Override
    public void onStopNestedScroll(View target) {
        super.onStopNestedScroll(target);
        TraceLog.i();
        if(listener!=null)
        {
            listener.onStopScroll();
        }
    }

    @Override
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
        TraceLog.i("dyConsumed:"+dyConsumed+" dyUnconsumed:"+dyUnconsumed);
        if(listener!=null)
        {
            listener.onNestedScroll(dyConsumed,dyUnconsumed);
        }
    }

    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        super.onNestedPreScroll(target, dx, dy, consumed);
        TraceLog.i("dy:"+dy);
    }

    @Override
    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
        TraceLog.i("velocityY:"+velocityY+" consumed"+consumed);
        return super.onNestedFling(target, velocityX, velocityY, consumed);
    }

    @Override
    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
        TraceLog.i("velocityY:"+velocityY);
        return super.onNestedPreFling(target, velocityX, velocityY);
    }

    @Override
    public boolean dispatchNestedPrePerformAccessibilityAction(int action, Bundle arguments) {
        return super.dispatchNestedPrePerformAccessibilityAction(action, arguments);
    }

    public interface onNestedActionListener{
        void onNestedScroll(int dyConsumed, int dyUnconsumed);
        void onStopScroll();
        void onStartScroll();
    }
}
