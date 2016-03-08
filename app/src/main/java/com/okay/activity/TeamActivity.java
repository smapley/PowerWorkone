package com.okay.activity;


import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ListView;

import com.okay.R;
import com.okay.adapter.TeamAdapter;

import java.util.ArrayList;
import java.util.HashMap;


public class TeamActivity extends Activity  {


    private ListView listView;
    private TeamAdapter adapter;
    private ArrayList<HashMap<String, Object>> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.team);

        initView();

    }

    private void initView(){
        list=new ArrayList<>();
        for (int i=0;i<10;i++){
            HashMap<String, Object> map=new HashMap<>();
            list.add( map);
        }
        adapter=new TeamAdapter(this,list);
        listView=(ListView)findViewById(R.id.team_list);
        listView.setAdapter(adapter);


    }

}
