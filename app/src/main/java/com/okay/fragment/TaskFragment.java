package com.okay.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.okay.R;

/**
 * Created by Administrator on 2015/4/17.
 */
public class TaskFragment extends Fragment {
    
    private View countView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        countView = inflater.inflate(R.layout.task, container, false);
        return countView;
    }
}
