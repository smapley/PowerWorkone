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
     * �õ�һ��LayoutInfalter�����������벼��
     */
    private LayoutInflater mInflater;
    private Context context;
    /**
     * list������
     */
    private ArrayList<HashMap<String, Object>> listitem;

    /**
     * ���캯��
     */
    public TeamAdapter(Context context,
                       ArrayList<HashMap<String, Object>> listitem) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.listitem = listitem;
    }

    @Override
    public int getCount() {
        return listitem.size();// ��������ĳ���

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
            /** �õ������ؼ��Ķ��� */

            holder.pic = (ImageView) convertView
                    .findViewById(R.id.team_pic);
            holder.tag = (ImageView) convertView.findViewById(R.id.team_tag);
            holder.name = (TextView) convertView
                    .findViewById(R.id.team_name);
            holder.num = (TextView) convertView.findViewById(R.id.team_num);
            convertView.setTag(holder);// ��ViewHolder����
        } else {
            holder = (ViewHolder) convertView.getTag();// ȡ��ViewHolder����

        }


        return convertView;
    }

    /**
     * ��ſؼ�
     */
    public final class ViewHolder {
        public TextView name;
        public TextView num;
        public ImageView pic;
        public ImageView tag;

    }

}
