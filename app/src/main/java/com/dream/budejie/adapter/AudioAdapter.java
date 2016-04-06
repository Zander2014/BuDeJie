package com.dream.budejie.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dream.budejie.R;
import com.dream.budejie.entity.Content;
import com.dream.budejie.entity.Tags;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;

/**
 * Created by Administrator on 2015/12/25.
 */
public class AudioAdapter extends BaseAdapter{
    ImageOptions imageOptions1;
    ImageOptions imageOptions2;
    private Context context;
    private List<Content> list;

    public AudioAdapter(Context context, List<Content> list) {
        this.context = context;
        this.list = list;
        imageOptions1 = new ImageOptions.Builder()
                .setFailureDrawableId(R.mipmap.head_portrait)
                .setCrop(true)
                .setConfig(Bitmap.Config.ARGB_8888)
                .setImageScaleType(ImageView.ScaleType.CENTER)
                .setRadius(90)//圆角弧度
                .build();
        imageOptions2=new ImageOptions.Builder()
                .setFailureDrawableId(R.mipmap.baisibudejie)
                .setLoadingDrawableId(R.mipmap.baisibudejie)
                .setConfig(Bitmap.Config.ARGB_8888)
                .setImageScaleType(ImageView.ScaleType.CENTER)
                .build();

    }

    @Override
    public int getCount() {
        return list != null ? list.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();

            convertView=View.inflate(context, R.layout.audio_item,null);

            holder.item_comment_comment = (TextView) convertView.findViewById(R.id.item_comment_comment);
            holder.item_comment_up = (TextView) convertView.findViewById(R.id.item_comment_up);
            holder.item_comment_down = (TextView) convertView.findViewById(R.id.item_comment_down);
            holder.item_comment_forward = (TextView) convertView.findViewById(R.id.item_comment_forward);
            holder.item_user_passTime = (TextView) convertView.findViewById(R.id.item_user_passTime);
            holder.item_user_name = (TextView) convertView.findViewById(R.id.item_user_name);
            holder.item_user_header = (ImageView) convertView.findViewById(R.id.item_user_header);
            holder.content_title= (TextView) convertView.findViewById(R.id.item_content_title);
            holder.tags1 = (TextView) convertView.findViewById(R.id.item_comment_tag1);
            holder.tags2 = (TextView) convertView.findViewById(R.id.item_comment_tag2);
            holder.tags3 = (TextView) convertView.findViewById(R.id.item_comment_tag3);
            holder.tags4 = (TextView) convertView.findViewById(R.id.item_comment_tag4);
            holder.tags5 = (TextView) convertView.findViewById(R.id.item_comment_tag5);
            holder.item_content_audio= (ImageView) convertView.findViewById(R.id.content_audio);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.item_comment_comment.setText(list.get(position).getComment());
        holder.item_comment_down.setText(list.get(position).getDown());
        holder.item_comment_forward.setText(list.get(position).getForward());
        holder.item_comment_up.setText(list.get(position).getUp());
        holder.item_user_passTime.setText(list.get(position).getPasstime());
        holder.content_title.setText(list.get(position).getText());
        holder.item_user_name.setText(list.get(position).getUser().getName());
        x.image().bind(holder.item_user_header, list.get(position).getUser().getHeader(), imageOptions1);
        x.image().bind(holder.item_content_audio, list.get(position).getAudio().getThumbnail(),imageOptions2);
        System.out.println("--audio--"+list.get(position).getAudio().getThumbnail());

        List<Tags> tagsList = list.get(position).getTags();
        for (int i = 0; i < tagsList.size(); i++) {
            if (tagsList.size() >= 1 && tagsList.get(0) != null) {
                holder.tags1.setText(tagsList.get(0).getTagsName());
                if (tagsList.size() >= 2 && tagsList.get(1) != null) {
                    holder.tags2.setText(tagsList.get(1).getTagsName());
                    if (tagsList.size() >= 3 && tagsList.get(2) != null) {
                        holder.tags3.setText(tagsList.get(2).getTagsName());
                        if (tagsList.size() >= 4 && tagsList.get(3) != null) {
                            holder.tags4.setText(tagsList.get(3).getTagsName());
                            if (tagsList.size() >= 5 && tagsList.get(4) != null) {
                                holder.tags5.setText(tagsList.get(4).getTagsName());
                            }
                        }
                    }
                }
            }
        }
        return convertView;
    }

    class ViewHolder {
        TextView item_comment_comment;
        TextView content_title;
        TextView item_comment_up;
        TextView item_comment_down;
        TextView item_user_passTime;
        TextView item_user_name;
        TextView item_comment_forward;
        ImageView item_user_header;
        ImageView item_content_audio;

        TextView tags1;
        TextView tags2;
        TextView tags3;
        TextView tags4;
        TextView tags5;
    }
}
