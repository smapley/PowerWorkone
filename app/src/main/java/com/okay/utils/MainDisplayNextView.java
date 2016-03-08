package com.okay.utils;


import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;

import com.okay.R;
import com.okay.fragment.MainFragment;
import com.okay.fragment.TaskFragment;

/**
 * Created by Administrator on 2015/4/17.
 */
public class MainDisplayNextView implements Animation.AnimationListener {

    private static TaskFragment fragment;
    private static FragmentManager manager;
    private static Context context;
    private static MainFragment mainFragment;

    public static void initView(Context cont, FragmentManager mana, MainFragment mainfragment) {
        fragment = new TaskFragment();
        manager = mana;
        context = cont;
        mainFragment = mainfragment;
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        // TODO Auto-generated method stub
        manager.beginTransaction().hide(mainFragment).add(R.id.countview, fragment).addToBackStack(null).commit();
        applyRotation(((Activity) context).findViewById(R.id.countview), -90, 0);
    }

    @Override
    public void onAnimationRepeat(Animation animation) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onAnimationStart(Animation animation) {
        // TODO Auto-generated method stub

    }

    private void applyRotation(View view, float start, float end) {
        Log.i("_________________", "--------------------->>��ʼ����");
        // �������ĵ�         
        final float centerX = view.getWidth() / 2.0f;
        final float centerY = view.getHeight() / 2.0f;
        final Rotate3dAnimation rotation = new Rotate3dAnimation(start, end, centerX, centerY, 0f, true);
        rotation.setDuration(500);
        rotation.setFillAfter(true);
        rotation.setInterpolator(new AccelerateInterpolator());
        view.startAnimation(rotation);
    }
}