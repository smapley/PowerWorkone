package com.okay.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import com.okay.R;
import com.okay.test.test;
import com.okay.utils.Rotate3dAnimation;

/**
 * Created by Administrator on 2015/4/9.
 */
public class UserInfoActivity extends ActionBarActivity {

    private ImageView image, image2;
    private Button button;
    private test tests;
    FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userinfo);

        tests = test.newInstance(R.drawable.anastasia, this);
        
        fm=getSupportFragmentManager();
        fm.beginTransaction()
                .replace(R.id.content_overlay, tests)
                .commit();

        initView();
    }

    private void initView() {
        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                applyRotation(1, 0, 90);//点击后先旋转90度
            }
        });
    }

    private void applyRotation(int position, float start, float end) {
        View image = findViewById(R.id.content_overlay);
        // 计算中心点         
        final float centerX = image.getWidth() / 2.0f;
        final float centerY = image.getHeight() / 2.0f;
        final Rotate3dAnimation rotation = new Rotate3dAnimation(start, end, centerX, centerY, 310.0f, true);
        rotation.setDuration(500);
        rotation.setFillAfter(true);
        rotation.setInterpolator(new AccelerateInterpolator());
        //设置监听         
        rotation.setAnimationListener(new DisplayNextView(position));
        image.startAnimation(rotation);
    }

    private final class DisplayNextView implements Animation.AnimationListener {
        private final int mPosition;

        private DisplayNextView(int position) {
            mPosition = position;
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            // TODO Auto-generated method stub
            tests = test.newInstance(R.drawable.dmitriy, UserInfoActivity.this);
            fm.beginTransaction().replace(R.id.content_overlay,tests).commit();
            applyRotation(1, 90, 180);//点击后先旋转90度
           // image.post(new SwapViews(mPosition));//监听当旋转到90度的那个动画结束时，把后半段的动画交给线程处理
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onAnimationStart(Animation animation) {
            // TODO Auto-generated method stub

        }

    }

    private final class SwapViews implements Runnable {
        private final int mPosition;

        public SwapViews(int position) {
            mPosition = position;
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            final float centerX = image.getWidth() / 2.0f;
            final float centerY = image.getHeight() / 2.0f;
            Rotate3dAnimation rotation;
            rotation = new Rotate3dAnimation(90, 180, centerX, centerY, 310.0f, false);//从90度旋转到180度
            rotation.setDuration(500);
            rotation.setFillAfter(true);
            rotation.setInterpolator(new DecelerateInterpolator());              //开始动画             
            image2.startAnimation(rotation);
            image2.setVisibility(View.VISIBLE);
        }

    }
}