package com.gqq.viewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textview);
        Log.e("TAG", "onCreate:"+ textView.getWidth()+","+ textView.getHeight());

        /**
         * 1. view.getViewTreeObserver().addOnGlobalLayoutListener()
         * 会被多次调用，所以在获取到之后需要移除监听
         */
        textView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Log.e("TAG", "OnGlobalLayoutListener:"+ textView.getWidth()+","+ textView.getHeight());
                textView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });

        /**
         * 2. view.post()
         * 推荐使用，单次调用
         */
        textView.post(new Runnable() {
            @Override
            public void run() {
                Log.e("TAG", "View post:"+ textView.getWidth()+","+ textView.getHeight());
            }
        });

        /**
         * 3. View.addOnLayoutChangeListener（）
         * 可能会多次调用，所以需要在获取到之后移除监听
         */
        textView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                Log.e("TAG", "OnLayoutChangeListener:"+ textView.getWidth()+","+ textView.getHeight());
                textView.removeOnLayoutChangeListener(this);
            }
        });

        /**
         * 4. View.getViewTreeObserver().addOnPreDrawListener()
         * 从绘制角度获取，可能会多次调用，需要在获取到之后进行移除监听
         */
        textView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                Log.e("TAG", "OnPreDrawListener:"+ textView.getWidth()+","+ textView.getHeight());
                textView.getViewTreeObserver().removeOnPreDrawListener(this);
                return true;
            }
        });

    }
}
