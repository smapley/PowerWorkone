package com.okay.activity;


import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.okay.R;
import com.okay.fragment.MainFragment;
import com.okay.utils.MainDisplayNextView;

/**
 * Created by Smapley on 2015/4/9.
 */
public class MainActivity extends ActionBarActivity {


    private MainFragment mainFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_layout);
        initView();
    }

    private void initView() {

        
        mainFragment = new MainFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.countview, mainFragment).commit();

        MainDisplayNextView.initView(this,getSupportFragmentManager(),mainFragment);
    }
    
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mainFragment.onPostCreate();
    }
}
