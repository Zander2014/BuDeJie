package com.dream.budejie.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.dream.budejie.R;
import com.dream.budejie.VideoActivity;
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
 * Created by jerry on 2015/12/19.
 * This adapter is commonly for all fragment;
 */
public class CustomAdapter extends BaseAdapter {
    private List<Content> mList;
    private Context mContext;
    private ImageOptions imageOptions;
    private ImageOptions imageOptionsVideo;
    private ImageOptions imageOptionsImage;
    private ViewHold hold;

    public CustomAdapter(List<Content> mList, Context mContext) {
        this.mList = mList;
        this.mContext = mContext;
        imageOptions = new ImageOptions.Builder()
                .setFailureDrawableId(R.mipmap.ic_launcher)
                .setCrop(true)
                .setRadius(90)
                .setImageScaleType(ImageView.ScaleType.CENTER)
                .setConfig(Bitmap.Config.ARGB_8888)
                .build();
        imageOptionsVideo = new ImageOptions.Builder()
                .setFailureDrawableId(R.mipmap.baisibudejie)
                .setConfig(Bitmap.Config.ARGB_8888)
                .setImageScaleType(ImageView.ScaleType.CENTER)
                .build();
        imageOptionsImage = new ImageOptions.Builder()
                .setConfig(Bitmap.Config.ARGB_8888)
                .setImageScaleType(ImageView.ScaleType.CENTER)
                .setCrop(true)
                .setFailureDrawableId(R.mipmap.baisibudejie)
                .build();
    }

    @Override
    public int getCount() {
        return mList != null ? mList.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            hold = new ViewHold();
            switch (mList.get(position).getType()) {
                case "video":
                    convertView = LayoutInflater.from(mContext).inflate(R.layout.video_item, null);
                    initFind(convertView);
                    hold.video_image = (ImageView) convertView.findViewById(R.id.video_image);
                    convertView.setTag(hold);
                    break;
                case "image":
                    convertView = LayoutInflater.from(mContext).inflate(R.layout.image_item, null);
                    initFind(convertView);
                    hold.content_image = (GifImageView) convertView.findViewById(R.id.content_image);
                    convertView.setTag(hold);
                    break;
                case "gif":
                    convertView = LayoutInflater.from(mContext).inflate(R.layout.gif_item, null);
                    initFind(convertView);
                    hold.content_gif = (GifImageView) convertView.findViewById(R.id.content_gif);
                    convertView.setTag(hold);
                    break;
                case "text":
                    convertView = LayoutInflater.from(mContext).inflate(R.layout.joke_item, null);
                    initFind(convertView);
                    convertView.setTag(hold);
                    break;
                case "html":
                    convertView = LayoutInflater.from(mContext).inflate(R.layout.joke_item, null);
                    initFind(convertView);
                    convertView.setTag(hold);
                    break;
                default:
                    break;
            }
        } else {
            hold = (ViewHold) convertView.getTag();
        }
//        convertView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                switch (v.getId()) {
//                    case R.id.video_image:
//                        Intent intent = new Intent(mContext, VideoActivity.class);
//                        intent.putExtra("videoUrl", mList.get(position).getVideo().getDownload().get(0));
//                        Log.i("jerry,videoUrl", mList.get(position).getVideo().getDownload().get(0));
//                        mContext.startActivity(intent);
//                        break;
//                    case R.id.item_user_header:
//
//                        break;
//                }
//            }
//        });
        hold.user_name.setText(mList.get(position).getUser().getName());
        hold.passtime.setText(mList.get(position).getPasstime());
        hold.title.setText(mList.get(position).getText());
        //---------------------------tags-start----------------
        if (mList.get(position).getTags() != null && mList.get(position).getTags().size() > 0) {
            List<Tags> tagsList = mList.get(position).getTags();
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
        hold.up.setText(mList.get(position).getUp());
        hold.down.setText(mList.get(position).getDown());
        hold.forward.setText(mList.get(position).getForward());
        hold.comment.setText(mList.get(position).getComment());
        if (mList.get(position).getUser().getHeader() != null && mList.get(position).getUser().getHeader().length() > 0) {
            x.image().bind(hold.user_header, mList.get(position).getUser().getHeader(), imageOptions, new CallbackProgress(hold));
        }
//       --------------------getType-------------------
        /**
         * Setting correct iamge  when type is different;
         */
        switch (mList.get(position).getType()) {
            case "video":
                if (mList.get(position).getVideo().getThumbnail() != null && mList.get(position).getVideo().getThumbnail().size() > 0) {
                    x.image().bind(hold.video_image, mList.get(position).getVideo().getThumbnail().get(0), imageOptionsVideo, new CallbackProgressVideo(hold));
                    Log.i("jerry,video,adapter", mList.get(position).getVideo().getThumbnail().get(0));
                }
//                hold.video_image.setOnTouchListener(new View.OnTouchListener() {
//                    @Override
//                    public boolean onTouch(View v, MotionEvent event) {
//                        if (MotionEvent.ACTION_DOWN == event.getAction()) {
//                            hold.video_image.setSelected(true);
//                        } else if (MotionEvent.ACTION_MOVE != event.getAction()) {
//                            hold.video_image.setSelected(false);
//                        }
//                        return false;
//                    }
//                });
                break;
            case "gif":
                if (mList.get(position).getGif().getGif_gif_thumbnail() != null && mList.get(position).getGif().getGif_gif_thumbnail().size() > 0) {
                    x.image().bind(hold.content_gif, mList.get(position).getGif().getGif_gif_thumbnail().get(0), imageOptionsImage);
                }
                break;
            case "image":
                if (mList.get(position).getImage().getImage_big() != null && mList.get(position).getImage().getImage_big().size() > 0) {
                    x.image().bind(hold.content_image, mList.get(position).getImage().getImage_big().get(0), imageOptionsImage);
                }
                break;
        }
        if(mList.get(position).getType().equals("video")){
            hold.video_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, VideoActivity.class);
                    intent.putExtra("videoUrl", mList.get(position).getVideo().getDownload().get(0));
                    Log.i("jerry,videoUrl", mList.get(position).getVideo().getDownload().get(0));
                    mContext.startActivity(intent);
                }
            });
        }
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


    class ViewHold {
        public ViewHold() {
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
        ImageView video_image;
        GifImageView content_image;
        GifImageView content_gif;
    }

    /**
     * user  header iamge  do  a callback
     */
    private class CallbackProgress implements Callback.CommonCallback<Drawable> {
        private CustomAdapter.ViewHold hold;

        public CallbackProgress(ViewHold hold) {
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

    /**
     * iamgeFragment   this callback is for ImageFragment when it is loading picture;
     */
    private class CallbackProgressVideo implements Callback.CommonCallback<Drawable> {
        private CustomAdapter.ViewHold hold;

        public CallbackProgressVideo(ViewHold hold) {
            this.hold = hold;
        }

        @Override
        public void onSuccess(Drawable result) {
            hold.video_image.setImageDrawable(result);
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

    /**
     * VideoFragment
     * this callback is for VideoFragment when it is loading default image before it download
     * image from internet successfully;
     */
    private class CallbackProgressImage implements Callback.CommonCallback<Drawable> {
        private CustomAdapter.ViewHold hold;

        public CallbackProgressImage(ViewHold hold) {
            this.hold = hold;
        }

        @Override
        public void onSuccess(Drawable result) {
            hold.content_image.setImageDrawable(result);
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
