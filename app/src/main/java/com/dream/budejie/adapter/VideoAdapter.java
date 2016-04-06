package com.dream.budejie.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.dream.budejie.R;
import com.dream.budejie.entity.Content;

import java.util.List;

/**
 * Created by jerry on 2015/12/19.
 */
public class VideoAdapter extends BaseAdapter {
    private List<String> mList;
    private Context mContext;

    public VideoAdapter(List<String> mList, Context mContext) {
        this.mList = mList;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    ViewHold hold = null;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.video_item, null);
            hold = new ViewHold();
            hold.up = (RadioButton) convertView.findViewById(R.id.item_comment_up);
            hold.down = (RadioButton) convertView.findViewById(R.id.item_comment_down);
            hold.forward = (RadioButton) convertView.findViewById(R.id.item_comment_forward);
            hold.comment = (RadioButton) convertView.findViewById(R.id.item_comment_comment);
            convertView.setTag(hold);
        } else {
            hold = (ViewHold) convertView.getTag();
        }

        return convertView;
    }


    class ViewHold {
        RadioButton up;
        RadioButton down;
        RadioButton forward;
        RadioButton comment;
    }
}
