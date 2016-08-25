package com.joyfulmath.samplecollapsing;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements HfCoordinatorLayout.onNestedActionListener {

    public static final float MAX_HEIGHT = 300.0f;
    public static final float DEFAULT_HEIGHT = 200.0f;
    public int DEFAULT_Y;
    public TextView demoTv;
    public float textSizeFlag = 0;
    public HfCoordinatorLayout layout =null;
    public NestedScrollView nestedScrollView;
    public AppBarLayout appBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        appBar = (AppBarLayout) findViewById(R.id.appbar);
        appBar.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                TraceLog.i(state.name());
                if( state == State.EXPANDED ) {

                    //展开状态

                }else if(state == State.COLLAPSED){

                    //折叠状态

                }else {

                    //中间状态

                }
            }
        });
        textSizeFlag = getResources().getDimension(R.dimen.text_size);
        DEFAULT_Y = getResources().getDimensionPixelSize(R.dimen.foat_margin);
        //设置工具栏标题
        final CollapsingToolbarLayout collapsingToolbarLayout= (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("");
        demoTv = (TextView) findViewById(R.id.tv_lock_fab);
        demoTv.setVisibility(View.GONE);
        demoTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nestedScrollView.smoothScrollTo(0,0);
                appBar.setExpanded(true,true);
                nestedScrollView.dispatchNestedPreScroll(0,0,null,null);
                nestedScrollView.dispatchNestedScroll(0,0,0,0,null);
            }
        });
        TraceLog.i("GONE");
        nestedScrollView = (NestedScrollView) findViewById(R.id.nestscroll_view);
        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                TraceLog.i("scrollX:%d scrollY:%d oldScrollX:%d oldScrollY:%d",scrollX,scrollY,oldScrollX,oldScrollY);
                setFloatVisible();
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
        setFloatVisible();
    }

    private int getNestScrollViewY() {
        int[] a = new int[2];
        int[] b = new int[2];
        nestedScrollView.getLocationOnScreen(a);
        nestedScrollView.getLocationInWindow(b);
//        TraceLog.i("Y:"+a[1]);
//        TraceLog.i("Y2:"+b[1]);
        return a[1];
    }

    @Override
    public void onStartScroll() {
        setFloatVisible();
    }

    private void full(boolean enable) {
        if (enable) {
            WindowManager.LayoutParams lp = getWindow().getAttributes();
            lp.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
            getWindow().setAttributes(lp);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        } else {
            WindowManager.LayoutParams attr = getWindow().getAttributes();
            attr.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getWindow().setAttributes(attr);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        full(true);
    }

    @Override
    protected void onPause() {
        super.onPause();
        full(false);
    }
}
