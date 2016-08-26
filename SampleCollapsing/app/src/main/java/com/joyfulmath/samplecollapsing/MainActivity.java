package com.joyfulmath.samplecollapsing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements NestScrollManager.onTextSizeChangeListener{

    public static final float MAX_HEIGHT = 300.0f;
    public static final float DEFAULT_HEIGHT = 200.0f;
    public NestScrollManager manager;
    TextView toolbarText = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbarText = (TextView) findViewById(R.id.tv_lock_fab);
        manager = new NestScrollManager(this,
                getWindow(),
                R.id.appbar,
                R.id.nestscroll_view,
                R.id.tv_lock_fab,
                this);
        manager.initManager(MAX_HEIGHT,DEFAULT_HEIGHT,R.dimen.text_size);
    }

    @Override
    public void onTextSizeChanged(float textSize, float radio) {
        toolbarText.setTextSize(TypedValue.COMPLEX_UNIT_PX,textSize);
    }

}
