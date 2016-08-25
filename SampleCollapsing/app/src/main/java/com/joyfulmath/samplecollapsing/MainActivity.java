package com.joyfulmath.samplecollapsing;

import android.graphics.Color;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements HfCoordinatorLayout.onNestedActionListener {

    public static final float MAX_HEIGHT = 300.0f;
    public static final float DEFAULT_HEIGHT = 200.0f;
    public static final int DEFAULT_Y = 75;
    public TextView demoTv;
    public float textSizeFlag = 0;
    public HfCoordinatorLayout layout =null;
    public NestedScrollView nestedScrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textSizeFlag = getResources().getDimension(R.dimen.text_size);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        //设置工具栏标题
        final CollapsingToolbarLayout collapsingToolbarLayout= (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("");
        demoTv = (TextView) findViewById(R.id.tv_lock_fab);
        demoTv.setVisibility(View.GONE);
        TraceLog.i("GONE");
        nestedScrollView = (NestedScrollView) findViewById(R.id.nestscroll_view);
        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                TraceLog.i("scrollX:%d scrollY:%d oldScrollX:%d oldScrollY:%d",scrollX,scrollY,oldScrollX,oldScrollY);
                drawFoatView(scrollY);
            }
        });

        layout = (HfCoordinatorLayout) findViewById(R.id.hf_coordinator);
        layout.setOnNestActionListener(this);
    }

    private void setFloatVisible() {
        if(DEFAULT_Y == getNestScrollViewY())
        {
            //move to update
            demoTv.setVisibility(View.VISIBLE);
        }else if(getNestScrollViewY()>DEFAULT_Y)
        {
            demoTv.setVisibility(View.GONE);
        }
    }

    private void drawFoatView(int scrollY) {
        if(scrollY>=(MAX_HEIGHT-DEFAULT_HEIGHT))
        {
            layoutTextView(DEFAULT_HEIGHT);
        }
        else if(scrollY>0)
        {
            layoutTextView(MAX_HEIGHT-scrollY);
        }else
        {
            layoutTextView(MAX_HEIGHT);
        }
    }

    private void layoutTextView(float height) {
        float textRadio = height*1.0f/MAX_HEIGHT;
        demoTv.setTextSize(TypedValue.COMPLEX_UNIT_PX,textSizeFlag*textRadio);
        ViewGroup.LayoutParams params =demoTv.getLayoutParams();
        params.height = (int) height;
        demoTv.setLayoutParams(params);
        demoTv.requestLayout();
    }

    @Override
    public void onNestedScroll(int dyConsumed, int dyUnconsumed) {
        setFloatVisible();
    }

    @Override
    public void onStopScroll() {
    }

    private int getNestScrollViewY() {
        int[] a = new int[2];
        nestedScrollView.getLocationOnScreen(a);
        TraceLog.i("Y:"+a[1]);
        return a[1];
    }

    @Override
    public void onStartScroll() {

        if(DEFAULT_Y < getNestScrollViewY())
        {
            //first show
            demoTv.setVisibility(View.GONE);
            TraceLog.i("GONE");
        }
    }
}
