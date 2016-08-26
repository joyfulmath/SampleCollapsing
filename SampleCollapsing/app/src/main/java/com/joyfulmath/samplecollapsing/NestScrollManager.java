package com.joyfulmath.samplecollapsing;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.NestedScrollView;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * Created by @joyfulmath.lu on 2016-08-26.
 * an manager for NestScrollView
 */
public class NestScrollManager {

    float MAX_HEIGHT = 0.0f;
    float DEFAULT_HEIGHT = 0.0f;
    public float textSizeFlag = 0;
    private NestedScrollView nestedScrollView;
    private AppBarLayout appBar;
    private TextView toolBarText;
    private Context context;
    private Window parent;
    public NestScrollManager(Context context, Window parent, int appBarId, int nestedScrollViewId, int toolBarId) {
        this.context = context;
        this.parent = parent;
        appBar = (AppBarLayout) parent.findViewById(appBarId);
        nestedScrollView = (NestedScrollView) parent.findViewById(nestedScrollViewId);
        toolBarText = (TextView) parent.findViewById(toolBarId);
    }

    public void initManager(float maxHeight,float minHeight,int textSizeId)
    {
        MAX_HEIGHT = maxHeight;
        DEFAULT_HEIGHT = minHeight;
        textSizeFlag = context.getResources().getDimension(textSizeId);
        initView();
    }

    private void initView() {
        toolBarText.setVisibility(View.GONE);
        appBar.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                TraceLog.i(state.name());
                if( state == State.EXPANDED ) {
                    //展开状态
                    toolBarText.setVisibility(View.GONE);
                    full(false);
                }else if(state == State.COLLAPSED){
                    //折叠状态
                    toolBarText.setVisibility(View.VISIBLE);
                    full(true);
                }else {
                    //中间状态
                    toolBarText.setVisibility(View.GONE);
                    full(true);
                }
            }
        });
        toolBarText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expandAppbar();
            }
        });
        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                TraceLog.i("scrollX:%d scrollY:%d oldScrollX:%d oldScrollY:%d",scrollX,scrollY,oldScrollX,oldScrollY);
                drawSuspendView(scrollY);
            }
        });
    }

    private void drawSuspendView(int scrollY) {
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
        float textRadio = height/MAX_HEIGHT;
        toolBarText.setTextSize(TypedValue.COMPLEX_UNIT_PX,textSizeFlag*textRadio);
        ViewGroup.LayoutParams params =toolBarText.getLayoutParams();
        params.height = (int) height;
        toolBarText.setLayoutParams(params);
        toolBarText.requestLayout();
    }

    private void expandAppbar() {
        nestedScrollView.smoothScrollTo(0,0);
        appBar.setExpanded(true,true);
        nestedScrollView.dispatchNestedPreScroll(0,0,null,null);
        nestedScrollView.dispatchNestedScroll(0,0,0,0,null);
    }

    private void full(boolean enable) {
        if (enable) {
            WindowManager.LayoutParams lp = parent.getAttributes();
            lp.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
            parent.setAttributes(lp);
            parent.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        } else {
            WindowManager.LayoutParams attr = parent.getAttributes();
            attr.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
            parent.setAttributes(attr);
            parent.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
    }

}
