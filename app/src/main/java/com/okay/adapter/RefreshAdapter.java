package com.okay.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.okay.R;
import com.okay.utils.MainDisplayNextView;
import com.okay.utils.Rotate3dAnimation;
import com.serchinastico.coolswitch.CoolSwitch;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Administrator on 2015/4/11.
 */
public class RefreshAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private Context context;
    public static final String KEY_NAME = "name";
    public static final String KEY_COLOR = "color";
    private ArrayList<HashMap<String, Integer>> listitem;
    private MainDisplayNextView displayNextView;

    public RefreshAdapter(Context context,
                          ArrayList<HashMap<String, Integer>> listitem) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.listitem = listitem;
        displayNextView = new MainDisplayNextView();
    }

    @Override
    public int getCount() {
        return listitem.size();

    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;


        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.layout_list_item, parent, false);
            holder.textViewName = (TextView) convertView.findViewById(R.id.text_view_name);
            holder.enabled = (LinearLayout) convertView.findViewById(R.id.enabled_view_foo);
            holder.disable = (LinearLayout) convertView.findViewById(R.id.disabled_view_foo);
            holder.coolSwitch = (CoolSwitch) convertView.findViewById(R.id.cool_switch_foo);
            holder.main_layout = (RelativeLayout) convertView.findViewById(R.id.main_layout);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.textViewName.setText(listitem.get(position).get(KEY_NAME));
        holder.coolSwitch.addView(holder.disable, holder.enabled);
        holder.enabled.setBackgroundColor(context.getResources().getColor(listitem.get(position).get(KEY_COLOR)));

        holder.coolSwitch.addAnimationListener(new CoolSwitch.AnimationListener() {

            @Override
            public void onCheckedAnimationFinished() {
                holder.textViewName.setText("_______>");
                // Empty
            }

            @Override
            public void onUncheckedAnimationFinished() {
                // Empty
                holder.textViewName.setText(listitem.get(position).get(KEY_NAME));
            }
        });
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                applyRotation(view, 1, 0, 90);//点击后先旋转90度
            }
        });

        return convertView;
    }


    class ViewHolder {
        TextView textViewName;
        LinearLayout enabled;
        LinearLayout disable;
        CoolSwitch coolSwitch;
        RelativeLayout main_layout;
    }

    private void applyRotation(View view, int position, float start, float end) {
        Log.i("_________________", "--------------------->>开始动画");
        // 计算中心点         
        final float centerX = view.getWidth() / 2.0f;
        final float centerY = view.getHeight() / 2.0f;
        final Rotate3dAnimation rotation = new Rotate3dAnimation(start, end, centerX, centerY, 310.0f, true);
        rotation.setDuration(500);
        rotation.setInterpolator(new AccelerateInterpolator());
        //设置监听         
        rotation.setAnimationListener(displayNextView);
        view.startAnimation(rotation);
    }


}
