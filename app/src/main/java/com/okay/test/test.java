package com.okay.test;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.support.v4.app.Fragment;

import com.okay.R;

/**
 * Created by Administrator on 2015/4/16.
 */
public class test extends Fragment {


    private static Context mcontext;
    private static int  resid;

    public static test newInstance(int resId, Context context) {
        mcontext = context;
        test tests = new test();
        resid=resId;
        return tests;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.user, container, false);
        initView(rootView);
        return rootView;
    }

    private void initView(View courentView) {
        ImageView imageView=(ImageView) courentView.findViewById(R.id.imageView);
        imageView.setImageResource(resid);
    }
}
