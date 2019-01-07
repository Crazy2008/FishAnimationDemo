package com.example.administrator.animationdemo;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private static final int TYPE_1 = 1;//从左到右 带旋转角度
    private static final int TYPE_2 = 2;//从右到左 带旋转角度
    private static final int TYPE_3 = 3;//从左到右  直线
    private static final int TYPE_4 = 4;//从右到左  直线
    private static final int HANDLER_100 = 100;//从右到左  直线
    private static final int HANDLER_101 = 101;//从右到左  直线

    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.iv_right2)
    ImageView ivRight2;
    @BindView(R.id.iv_left)
    ImageView ivLeft;
    @BindView(R.id.iv_left2)
    ImageView ivLeft2;
    @BindView(R.id.iv_center)
    ImageView ivCenter;
    @BindView(R.id.iv_center1)
    ImageView ivCenter1;
    @BindView(R.id.iv_center2)
    ImageView ivCenter2;
    @BindView(R.id.iv_center3)
    ImageView ivCenter3;
    @BindView(R.id.iv_bottom)
    ImageView ivBottom;
    @BindView(R.id.iv_bottom1)
    ImageView ivBottom1;


    private int screen_x; // 屏幕宽度（像素）
    private int screen_y; // 屏幕高度（像素）

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case HANDLER_100:
                    initAnimation(TYPE_2, newWidth, ivLeft2);
                    initAnimation(TYPE_3, -screen_y, ivCenter);
                    initAnimation(TYPE_4, screen_y, ivBottom);
                    break;
                case HANDLER_101:
                    initAnimation(TYPE_3, -screen_y, ivCenter1);
                    initAnimation(TYPE_3, -screen_y, ivCenter2);
                    initAnimation(TYPE_1, -newWidth, ivRight);
                    break;
            }
        }
    };

    private int newWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setStatusBarFullTransparent();
        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        screen_x = metric.widthPixels;
        screen_y = metric.heightPixels;


        /**
         * wyt鱼游泳代码
         */
        newWidth = screen_x + 800;
        ivLeft.setImageResource(R.drawable.sha_animlist);
        ivLeft2.setImageResource(R.drawable.hong_animlist);
        setFrameAnimation(ivLeft);
        setFrameAnimation(ivLeft2);


        ivRight.setImageResource(R.drawable.jin_animlist);
        ivRight2.setImageResource(R.drawable.shark_animlist);
        setFrameAnimation(ivRight);
        setFrameAnimation(ivRight2);


        ivBottom.setImageResource(R.drawable.sha_animlist);
        ivBottom1.setImageResource(R.drawable.hong_animlist);
        setFrameAnimation(ivBottom);
        setFrameAnimation(ivBottom1);


        ivCenter.setImageResource(R.drawable.jin_animlist);
        ivCenter1.setImageResource(R.drawable.shark_animlist);
        ivCenter2.setImageResource(R.drawable.jin_animlist);
        ivCenter3.setImageResource(R.drawable.jin_animlist);
        setFrameAnimation(ivCenter);
        setFrameAnimation(ivCenter1);
        setFrameAnimation(ivCenter2);
        setFrameAnimation(ivCenter3);


        initAnimation(TYPE_2, newWidth, ivLeft);

        initAnimation(TYPE_4, screen_y, ivBottom1);


        initAnimation(TYPE_1, -newWidth, ivRight2);

        initAnimation(TYPE_3, -screen_y, ivCenter3);

        mHandler.sendEmptyMessageDelayed(HANDLER_100, 8000);
        mHandler.sendEmptyMessageDelayed(HANDLER_101, 4000);

        mHandler.sendEmptyMessage(0);

        //读取文件权限


    }

    private void setFrameAnimation(ImageView iv) {
        AnimationDrawable drawable = (AnimationDrawable) iv.getDrawable();
        drawable.start();
    }

    private void initAnimation(int type, int height, ImageView iv) {
        iv.setVisibility(View.VISIBLE);
        float v = 1;
//        int degress = 40;
        /**
         * 旋转
         */
        RotateAnimation rotateAnimation = null;
        TranslateAnimation translateAnimation = null;
        Random random = new Random();
        int value = random.nextInt(50) + 1;
        if (type == TYPE_1) {
            translateAnimation = new TranslateAnimation(0f, height, 0f, 0f);
            rotateAnimation = new RotateAnimation(0, value, RotateAnimation.RELATIVE_TO_SELF, v, RotateAnimation.RELATIVE_TO_SELF, v);
        } else if (type == TYPE_2) {
            translateAnimation = new TranslateAnimation(0f, height, 0f, 0f);
            rotateAnimation = new RotateAnimation(0, -value, RotateAnimation.RELATIVE_TO_SELF, v, RotateAnimation.RELATIVE_TO_SELF, v);
        } else if (type == TYPE_3) {
            translateAnimation = new TranslateAnimation(0f, height, 0f, 0f);
            rotateAnimation = new RotateAnimation(0, -value, RotateAnimation.RELATIVE_TO_SELF, v, RotateAnimation.RELATIVE_TO_SELF, v);
        } else if (type == TYPE_4) {
            translateAnimation = new TranslateAnimation(0f, height, 0f, 0f);
            rotateAnimation = new RotateAnimation(0, value, RotateAnimation.RELATIVE_TO_SELF, v, RotateAnimation.RELATIVE_TO_SELF, v);
        }

        /**
         * 平移
         */


        AnimationSet animationSet = new AnimationSet(true);
        animationSet.setDuration(15000);

        animationSet.setInterpolator(new LinearInterpolator());

        if (translateAnimation != null) {
            animationSet.addAnimation(translateAnimation);
            translateAnimation.setRepeatCount(Animation.INFINITE);
        }
        if (rotateAnimation != null) {
            animationSet.addAnimation(rotateAnimation);
        }

        iv.startAnimation(animationSet);
    }








    /**
     * 全透状态栏
     */
    protected void setStatusBarFullTransparent() {
        if (Build.VERSION.SDK_INT >= 21) {//21表示5.0
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= 19) {//19表示4.4
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //虚拟键盘也透明
            //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }














}
