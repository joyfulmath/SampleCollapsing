package com.joyfulmath.samplecollapsing;

import android.graphics.Color;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final int MAX_HEIGHT = 300;
    public static final int DEFAULT_HEIGHT = 200;
    public TextView demoTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        demoTv = (TextView) findViewById(R.id.tv_height_demo);
        NestedScrollView view = (NestedScrollView) findViewById(R.id.nestscroll_view);
        view.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                TraceLog.i("scrollX:%d scrollY:%d oldScrollX:%d oldScrollY:%d",scrollX,scrollY,oldScrollX,oldScrollY);
                if(scrollY>oldScrollY)
                {
                    //up
                    if(scrollY>=100)
                    {
                        demoTv.setHeight(DEFAULT_HEIGHT);
                    }
                    else if(scrollY>=0)
                    {
                        demoTv.setHeight(MAX_HEIGHT-scrollY);
                    }
                }else if(oldScrollY>scrollY)
                {
                    //down
                    if(scrollY>=100)
                    {
                        demoTv.setHeight(DEFAULT_HEIGHT);
                    }
                    else if(scrollY>=0)
                    {
                        demoTv.setHeight(MAX_HEIGHT-scrollY);
                    }else
                    {
                        demoTv.setHeight(MAX_HEIGHT);
                    }
                }
            }
        });
        //设置工具栏标题
        CollapsingToolbarLayout collapsingToolbarLayout= (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("蒙奇-D-路飞");
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
    }

}
