package com.dream.budejie.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.dream.budejie.R;
import com.dream.budejie.entity.Content;
import com.dream.budejie.entity.Tags;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.io.IOException;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

/**
 * Created by Administrator on 2015/12/21.
 */
public class ImageAdapter extends BaseAdapter {
    ImageOptions imageOptions1;
    ImageOptions imageOptions2;
    private Context context;
    private List<Content> list;

    public ImageAdapter(Context context, List<Content> list) {
        this.context = context;
        this.list = list;
        imageOptions1 = new ImageOptions.Builder()
                .setFailureDrawableId(R.mipmap.head_portrait)
                .setCrop(true)
                .setConfig(Bitmap.Config.ARGB_8888)
                .setImageScaleType(ImageView.ScaleType.CENTER)
                .setRadius(90)//圆角弧度
                .build();

        imageOptions2 = new ImageOptions.Builder()
                .setFailureDrawableId(R.mipmap.baisibudejie)
                .setConfig(Bitmap.Config.ARGB_8888)
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        System.out.println("--ImageAdapter_getView正在执行");
        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();

            convertView=View.inflate(context,R.layout.image_item,null);
            holder.content_image= (GifImageView) convertView.findViewById(R.id.content_image);
            System.out.println("--setTag:" + list.get(position).getPicture().getImages());
            holder.progress_textview1 = (TextView) convertView.findViewById(R.id.progress_textview1);

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
        switch (list.get(position).getType()) {
            case "gif":
                if(holder.progress_textview1.getText()!=null){
                holder.progress_textview1.setText("");
            }
                holder.progress_textview1.setTag(list.get(position).getPicture().getImages());
                holder.content_image.setImageResource(R.mipmap.baisibudejie);
                holder.progress_textview1.setVisibility(View.VISIBLE);
                AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
                asyncHttpClient.get(context, list.get(position).getPicture().getImages(), new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                        try {
                            System.out.println("--asyncHttp成功方法正在执行");
                            if(holder.progress_textview1.getTag()!=null&&holder.progress_textview1.getTag().equals(list.get(position).getPicture().getImages())) {
                                GifDrawable gifDrawable = new GifDrawable(responseBody);
                                holder.content_image.setImageDrawable(gifDrawable);
                                holder.progress_textview1.setVisibility(View.GONE);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                    }

                    @Override
                    public void onProgress(long bytesWritten, long totalSize) {
                        System.out.println("--onProgress");
                        if(holder.progress_textview1.getTag()!=null&&holder.progress_textview1.getTag().equals(list.get(position).getPicture().getImages())){
                            float num = (float) bytesWritten / totalSize;
                            String n = String.valueOf((int) (num * 100)) + "%";
                            holder.progress_textview1.setText(n);
                        }

                    }
                });
                break;
            case "image":
                holder.progress_textview1.setTag(list.get(position).getPicture().getBig());
                //               holder.progress_textview.setVisibility(View.GONE);
                if(holder.progress_textview1.getTag()!=null&&holder.progress_textview1.getTag().equals(list.get(position).getPicture().getBig())) {
                    holder.progress_textview1.setVisibility(View.GONE);
                    x.image().bind(holder.content_image, list.get(position).getPicture().getBig(), imageOptions2);
                }
                break;
            default:
                break;
        }

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
        GifImageView content_image;
        TextView progress_textview1;

        TextView tags1;
        TextView tags2;
        TextView tags3;
        TextView tags4;
        TextView tags5;
    }

}

