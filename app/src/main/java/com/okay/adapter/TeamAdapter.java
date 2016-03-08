package com.okay.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.okay.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Administrator on 2015/4/9.
 */
public class TeamAdapter extends BaseAdapter {
    /**
     * 得到一个LayoutInfalter对象用来导入布局
     */
    private LayoutInflater mInflater;
    private Context context;
    /**
     * list的数据
     */
    private ArrayList<HashMap<String, Object>> listitem;

    /**
     * 构造函数
     */
    public TeamAdapter(Context context,
                       ArrayList<HashMap<String, Object>> listitem) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.listitem = listitem;
    }

    @Override
    public int getCount() {
        return listitem.size();// 返回数组的长度

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
            convertView = mInflater.inflate(R.layout.layout_team_item, null);
            holder = new ViewHolder();
            /** 得到各个控件的对象 */

            holder.pic = (ImageView) convertView
                    .findViewById(R.id.team_pic);
            holder.tag = (ImageView) convertView.findViewById(R.id.team_tag);
            holder.name = (TextView) convertView
                    .findViewById(R.id.team_name);
            holder.num = (TextView) convertView.findViewById(R.id.team_num);
            convertView.setTag(holder);// 绑定ViewHolder对象
        } else {
            holder = (ViewHolder) convertView.getTag();// 取出ViewHolder对象

        }


        return convertView;
    }

    /**
     * 存放控件
     */
    public final class ViewHolder {
        public TextView name;
        public TextView num;
        public ImageView pic;
        public ImageView tag;

    }

}
