package com.joyfulmath.samplecollapsing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity{

    public static final float MAX_HEIGHT = 300.0f;
    public static final float DEFAULT_HEIGHT = 200.0f;
    public NestScrollManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = new NestScrollManager(this,
                getWindow(),
                R.id.appbar,
                R.id.nestscroll_view,
                R.id.tv_lock_fab);
        manager.initManager(MAX_HEIGHT,DEFAULT_HEIGHT,R.dimen.text_size);
//        appBar = (AppBarLayout) findViewById(R.id.appbar);
//        appBar.addOnOffsetChangedListener(new AppBarStateChangeListener() {
//            @Override
//            public void onStateChanged(AppBarLayout appBarLayout, State state) {
//                TraceLog.i(state.name());
//                if( state == State.EXPANDED ) {
//
//                    //展开状态
//                    demoTv.setVisibility(View.GONE);
//
//                }else if(state == State.COLLAPSED){
//
//                    //折叠状态
//                    demoTv.setVisibility(View.VISIBLE);
//
//                }else {
//                    //中间状态
//                    demoTv.setVisibility(View.GONE);
//
//                }
//            }
//        });
//        textSizeFlag = getResources().getDimension(R.dimen.text_size);
//        //设置工具栏标题
//        demoTv = (TextView) findViewById(R.id.tv_lock_fab);
//        demoTv.setVisibility(View.GONE);
//        demoTv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                expandAppbar();
//            }
//        });
//        nestedScrollView = (NestedScrollView) findViewById(R.id.nestscroll_view);
//        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
//            @Override
//            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//                TraceLog.i("scrollX:%d scrollY:%d oldScrollX:%d oldScrollY:%d",scrollX,scrollY,oldScrollX,oldScrollY);
//                drawFoatView(scrollY);
//            }
//        });
    }

//    private void expandAppbar() {
//        nestedScrollView.smoothScrollTo(0,0);
//        appBar.setExpanded(true,true);
//        nestedScrollView.dispatchNestedPreScroll(0,0,null,null);
//        nestedScrollView.dispatchNestedScroll(0,0,0,0,null);
//    }
//
//    private void drawFoatView(int scrollY) {
//        if(scrollY>=(MAX_HEIGHT-DEFAULT_HEIGHT))
//        {
//            layoutTextView(DEFAULT_HEIGHT);
//        }
//        else if(scrollY>0)
//        {
//            layoutTextView(MAX_HEIGHT-scrollY);
//        }else
//        {
//            layoutTextView(MAX_HEIGHT);
//        }
//    }
//
//    private void layoutTextView(float height) {
//        float textRadio = height/MAX_HEIGHT;
//        demoTv.setTextSize(TypedValue.COMPLEX_UNIT_PX,textSizeFlag*textRadio);
//        ViewGroup.LayoutParams params =demoTv.getLayoutParams();
//        params.height = (int) height;
//        demoTv.setLayoutParams(params);
//        demoTv.requestLayout();
//    }

//    private void full(boolean enable) {
//        if (enable) {
//            WindowManager.LayoutParams lp = getWindow().getAttributes();
//            lp.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
//            getWindow().setAttributes(lp);
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
//        } else {
//            WindowManager.LayoutParams attr = getWindow().getAttributes();
//            attr.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
//            getWindow().setAttributes(attr);
//            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
//        }
//    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        full(true);
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        full(false);
//    }
}
