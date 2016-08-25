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
    public int currentScrollY = 0;
    public int currentSOldScrollY = 0;
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
        collapsingToolbarLayout.setExpandedTitleColor(Color.YELLOW);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
        collapsingToolbarLayout.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                TraceLog.i();
                return false;
            }
        });
        collapsingToolbarLayout.setOnHoverListener(new View.OnHoverListener() {
            @Override
            public boolean onHover(View v, MotionEvent event) {
                TraceLog.i();
                return false;
            }
        });
        demoTv = (TextView) findViewById(R.id.tv_lock_fab);
        nestedScrollView = (NestedScrollView) findViewById(R.id.nestscroll_view);
        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                TraceLog.i("scrollX:%d scrollY:%d oldScrollX:%d oldScrollY:%d",scrollX,scrollY,oldScrollX,oldScrollY);
                currentScrollY = scrollY;
                currentSOldScrollY = oldScrollY;
            }
        });

        layout = (HfCoordinatorLayout) findViewById(R.id.hf_coordinator);
        layout.setOnNestActionListener(this);
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
        if(currentScrollY == 0 && currentSOldScrollY == 0)
        {
            demoTv.setVisibility(View.GONE);
        }

        if(currentScrollY>currentSOldScrollY)
        {
            //up
            drawUpView(dyConsumed,dyUnconsumed);
        }else if(currentSOldScrollY>currentScrollY)
        {
            //down
            drawDownView(dyConsumed,dyUnconsumed);
        }
    }

    private void drawDownView(int dyConsumed, int dyUnconsumed) {
        if(currentScrollY <= 0)
        {
            //invisible
            demoTv.setVisibility(View.GONE);
            TraceLog.i("gone");
        }

        if(currentScrollY>=100)
        {
            layoutTextView(DEFAULT_HEIGHT);
        }
        else if(currentScrollY>0)
        {
            layoutTextView(MAX_HEIGHT-currentScrollY);
        }else
        {
            layoutTextView(MAX_HEIGHT);
        }
    }

    private void drawUpView(int dyConsumed, int dyUnconsumed) {

        if(currentScrollY>0)
        {
            if(DEFAULT_Y == getNestScrollViewY())
            {
                //first show
                demoTv.setVisibility(View.VISIBLE);
                TraceLog.i("visible");
            }
        }else if(currentScrollY<=0)
        {
            demoTv.setVisibility(View.GONE);
            TraceLog.i("gone");
        }

        if(currentScrollY>=100)
        {
            layoutTextView(DEFAULT_HEIGHT);
        }
        else if(currentScrollY>=0)
        {
            layoutTextView(MAX_HEIGHT-currentScrollY);
        }else if(currentScrollY<0)
        {
            layoutTextView(MAX_HEIGHT);
        }
    }

    @Override
    public void onStopScroll() {
        currentScrollY = 0;
        currentSOldScrollY = 0;
        getNestScrollViewY();
    }

    private int getNestScrollViewY() {
        int[] a = new int[2];
        nestedScrollView.getLocationOnScreen(a);
        return a[1];
    }

    @Override
    public void onStartScroll() {

        if(currentScrollY <= 0 )
        {
            demoTv.setVisibility(View.GONE);
        }
    }
}
