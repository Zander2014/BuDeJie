package com.dream.budejie.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.dream.budejie.R;
import com.dream.budejie.entity.Content;
import com.dream.budejie.entity.Tags;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.xutils.common.Callback;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.io.IOException;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

/**
 * Created by jerry on 2015/12/25.
 */
public class GifAdapter extends BaseAdapter {
    ImageOptions imageOptions1;
    ImageOptions imageOptions2;
    private Context context;
    private List<Content> list;
    private ViewHoldGif hold;

    public GifAdapter(Context context, List<Content> list) {
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
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.gif_item, null);
            hold = new ViewHoldGif();
            initFind(convertView);
            hold.content_gif = (GifImageView) convertView.findViewById(R.id.content_gif);
            hold.progress_gif= (TextView) convertView.findViewById(R.id.progress_gif);
            convertView.setTag(hold);
        } else {
            hold = (ViewHoldGif) convertView.getTag();
        }
        hold.user_name.setText(list.get(position).getUser().getName());
        hold.passtime.setText(list.get(position).getPasstime());
        hold.title.setText(list.get(position).getText());
        //---------------------------tags-start----------------
        if (list.get(position).getTags() != null && list.get(position).getTags().size() > 0) {
            List<Tags> tagsList = list.get(position).getTags();
            for (int i = 0; i < tagsList.size(); i++) {
                if (tagsList.size() >= 1 && tagsList.get(0) != null) {
                    hold.tags1.setText(tagsList.get(0).getTagsName());
                    if (tagsList.size() >= 2 && tagsList.get(1) != null) {
                        hold.tags2.setText(tagsList.get(1).getTagsName());
                        if (tagsList.size() >= 3 && tagsList.get(2) != null) {
                            hold.tags3.setText(tagsList.get(2).getTagsName());
                            if (tagsList.size() >= 4 && tagsList.get(3) != null) {
                                hold.tags4.setText(tagsList.get(3).getTagsName());
                                if (tagsList.size() >= 5 && tagsList.get(4) != null) {
                                    hold.tags5.setText(tagsList.get(4).getTagsName());
                                }
                            }
                        }
                    }
                }
            }
        }
        //-----------------------tags end----------------------
        hold.up.setText(list.get(position).getUp());
        hold.down.setText(list.get(position).getDown());
        hold.forward.setText(list.get(position).getForward());
        hold.comment.setText(list.get(position).getComment());
        if (list.get(position).getUser().getHeader() != null && list.get(position).getUser().getHeader().length() > 0) {
            x.image().bind(hold.user_header, list.get(position).getUser().getHeader(), imageOptions1, new CallbackProgress(hold));
        }
//        ---------------------------------gif
        if(list.get(position).getType().equals("gif")){
            hold.progress_gif.setTag(list.get(position).getGif().getGif_images().get(0));
            AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
            asyncHttpClient.get(context, list.get(position).getGif().getGif_images().get(0), new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                  if(hold.progress_gif.getTag()!=null && hold.progress_gif.getTag().equals(list.get(position).getGif().getGif_images().get(0))){
                      try {
                          GifDrawable gifDrawable = new GifDrawable(responseBody);
                          hold.content_gif.setImageDrawable(gifDrawable);
                      } catch (IOException e) {
                          e.printStackTrace();
                      }
                  }
                }
                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                error.printStackTrace();
                }
            });
        }else if(list.get(position).getType().equals("image")){
            hold.progress_gif.setTag(list.get(position).getImage().getImage_big().get(0));
            if(hold.progress_gif.getTag().equals(list.get(position).getImage().getImage_big().get(0))){
                if(list.get(position).getImage().getImage_big()!=null && list.get(position).getImage().getImage_big().size() >0){
                    x.image().bind(hold.content_gif,list.get(position).getImage().getImage_big().get(0),imageOptions2);
                }
            }
        }
        hold.up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String upcount = (String) hold.up.getText();
                hold.up.setText(String.valueOf(Integer.parseInt(upcount) + 1));
                hold.up.setTextColor(Color.RED);
                Drawable drawable1 = context.getResources().getDrawable(R.mipmap.ding_has_clicked);
                drawable1.setBounds(0,0,drawable1.getMinimumWidth(),drawable1.getMinimumHeight());
                hold.up.setCompoundDrawables(drawable1, null, null, null);
                hold.up.setEnabled(false);
                hold.down.setEnabled(false);
            }
        });
        hold.down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"down is clicked",Toast.LENGTH_SHORT).show();
                String downcount = (String) hold.up.getText();
                hold.down.setText(String.valueOf(Integer.parseInt(downcount) + 1));
                hold.down.setTextColor(Color.RED);
                Drawable drawable1 = context.getResources().getDrawable(R.mipmap.cai_has_clicked);
                drawable1.setBounds(0,0,drawable1.getMinimumWidth(),drawable1.getMinimumHeight());
                hold.down.setCompoundDrawables(drawable1, null, null, null);
                hold.up.setEnabled(false);
                hold.down.setEnabled(false);
            }
        });
        return convertView;
    }

    private void initFind(View convertView) {
        hold.tags1 = (TextView) convertView.findViewById(R.id.item_comment_tag1);
        hold.tags2 = (TextView) convertView.findViewById(R.id.item_comment_tag2);
        hold.tags3 = (TextView) convertView.findViewById(R.id.item_comment_tag3);
        hold.tags4 = (TextView) convertView.findViewById(R.id.item_comment_tag4);
        hold.tags5 = (TextView) convertView.findViewById(R.id.item_comment_tag5);
        hold.up = (TextView) convertView.findViewById(R.id.item_comment_up);
        hold.down = (TextView) convertView.findViewById(R.id.item_comment_down);
        hold.forward = (TextView) convertView.findViewById(R.id.item_comment_forward);
        hold.comment = (TextView) convertView.findViewById(R.id.item_comment_comment);
        hold.user_header = (ImageView) convertView.findViewById(R.id.item_user_header);
        hold.user_name = (TextView) convertView.findViewById(R.id.item_user_name);
        hold.passtime = (TextView) convertView.findViewById(R.id.item_user_passTime);
        hold.title = (TextView) convertView.findViewById(R.id.item_content_title);
    }

    class ViewHoldGif {
        public ViewHoldGif() {
        }
        TextView up;
        TextView down;
        TextView forward;
        TextView comment;
        ImageView user_header;
        TextView user_name;
        TextView passtime;
        TextView title;
        TextView tags1;
        TextView tags2;
        TextView tags3;
        TextView tags4;
        TextView tags5;
        GifImageView content_image;
        GifImageView content_gif;
        TextView progress_gif;
    }

    private class CallbackProgress implements Callback.CommonCallback<Drawable> {
        private ViewHoldGif hold;

        public CallbackProgress(ViewHoldGif hold) {
            this.hold = hold;
        }

        @Override
        public void onSuccess(Drawable result) {
            hold.user_header.setImageDrawable(result);
        }

        @Override
        public void onError(Throwable ex, boolean isOnCallback) {
            ex.printStackTrace();
        }

        @Override
        public void onCancelled(CancelledException cex) {
            cex.printStackTrace();
        }

        @Override
        public void onFinished() {

        }
    }
}
