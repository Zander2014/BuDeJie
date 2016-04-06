package com.dream.budejie.utils;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dream.budejie.R;
import com.dream.budejie.adapter.CustomAdapter;
import com.dream.budejie.adapter.GifAdapter;
import com.dream.budejie.entity.Content;
import com.dream.budejie.entity.Gif;
import com.dream.budejie.entity.Image;
import com.dream.budejie.entity.Picture;
import com.dream.budejie.entity.Tags;
import com.dream.budejie.entity.User;
import com.dream.budejie.entity.Video;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jerry on 2015/12/21.
 * This utils is commonly for all Json your JsonData;
 */
public class JsonUtils {
    public JsonUtils() {
    }
    public void loadDataImage(String url, View view, final Context context){
        final ListView mListView = (ListView) view.findViewById(R.id.normal_listView);
        final List<Content> contents = new ArrayList<Content>();
//        final CustomAdapter videoAdapter = new CustomAdapter(contents, context);
        RequestParams requestParams = new RequestParams(url);
        Callback.Cancelable cancelable = x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                JSONArray jsonArray = JSON.parseObject(result).getJSONArray("list");
                Content content;
                for (int i = 0; i < jsonArray.size(); i++) {
                    content = jsonArray.getObject(i, Content.class);
                    if (content != null) {
                        JSONObject contentJsonObj = jsonArray.getJSONObject(i);//获取每一条content的json数据
                        JSONObject userJsonObj = contentJsonObj.getJSONObject("u");//根据标签"u"获取 user的json数据
                        JSONArray tagsJsonObj = contentJsonObj.getJSONArray("tags");
                        //////////////////user////////////////////////
                        User user = new User();
                        String user_name = userJsonObj.getString("name");//通过标签"name"获取user的name
                        JSONArray headers = userJsonObj.getJSONArray("header");
                        String user_header = headers.get(0).toString();
                        user.setName(user_name);
                        user.setHeader(user_header);
                        //////////////////tags/////////////////
                        List<Tags> tagsList = new ArrayList<Tags>();
                        Tags tagsObject;
                        for (int j = 0; j < tagsJsonObj.size(); j++) {
                            tagsObject = tagsJsonObj.getObject(j, Tags.class);
                            if (tagsObject != null) {
                                tagsList.add(tagsObject);
                            }
                        }
                        switch (content.getType()) {
                            case "gif":
                                Gif gif=new Gif();
                                JSONObject gifJsonObj = contentJsonObj.getJSONObject("gif");
                                JSONArray imagesJson = gifJsonObj.getJSONArray("images");
                                JSONArray gif_thumbnailJson = gifJsonObj.getJSONArray("gif_thumbnail");
                                String gif_images=imagesJson.getObject(0, String.class);
                                String gif_gif_thumbnail=gif_thumbnailJson.getObject(0,String.class);
                                List<String> imagesList=new ArrayList<String>();
                                List<String> gif_thumbnailList=new ArrayList<String>();
                                imagesList.add(gif_images);
                                gif_thumbnailList.add(gif_gif_thumbnail);
                                gif.setGif_images(imagesList);
                                gif.setGif_gif_thumbnail(gif_thumbnailList);
                                content.setGif(gif);
                                break;
                            case "image":
                                Image image=new Image();
                                JSONObject imageJsonObj = contentJsonObj.getJSONObject("image");
                                JSONArray bigJsonArray = imageJsonObj.getJSONArray("big");
                                String image_big = bigJsonArray.getObject(0, String.class);
                                List<String> image_bigList=new ArrayList<String>();
                                image_bigList.add(image_big);
                                image.setImage_big(image_bigList);
                                content.setImage(image);
                                break;
                        }
                        //把集合放进content中
                        content.setUser(user);
                        content.setTags(tagsList);
                        contents.add(content);
                    }
                }
                mListView.setAdapter(new GifAdapter(context,contents));
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
        });
    }
    public void initData(String url, View view, final Context context) {
        final ListView mListView = (ListView) view.findViewById(R.id.normal_listView);
        final List<Content> contents = new ArrayList<Content>();
//        final CustomAdapter videoAdapter = new CustomAdapter(contents, context);
        RequestParams requestParams = new RequestParams(url);
        Callback.Cancelable cancelable = x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                JSONArray jsonArray = JSON.parseObject(result).getJSONArray("list");
                Content content;
                for (int i = 0; i <jsonArray.size(); i++) {
                    content = jsonArray.getObject(i, Content.class);
                    if (content != null) {
                        JSONObject contentJsonObj = jsonArray.getJSONObject(i);//获取每一条content的json数据
                        JSONObject userJsonObj = contentJsonObj.getJSONObject("u");//根据标签"u"获取 user的json数据
                        JSONArray tagsJsonObj = contentJsonObj.getJSONArray("tags");
                        //////////////////user////////////////////////
                        User user = new User();
                        String user_name = userJsonObj.getString("name");//通过标签"name"获取user的name
                        JSONArray headers = userJsonObj.getJSONArray("header");
                        String user_header = headers.get(0).toString();
                        user.setName(user_name);
                        user.setHeader(user_header);
                        //////////////////tags/////////////////
                        List<Tags> tagsList = new ArrayList<Tags>();
                        Tags tagsObject;
                        for (int j = 0; j < tagsJsonObj.size(); j++) {
                            tagsObject = tagsJsonObj.getObject(j, Tags.class);
                            if (tagsObject != null) {
                                tagsList.add(tagsObject);
                            }
                        }
                        Picture picture = new Picture();//new one picture
                        switch (content.getType()) {
                            case "video":
                                //******************video
                                JSONObject videoJsonObj = contentJsonObj.getJSONObject("video");
                                Log.i("jerry video", videoJsonObj.toString());
                                Video video = new Video();
                                String video_playcount = videoJsonObj.getString("playcount");
                                String video_width = videoJsonObj.getString("width");
                                String video_height = videoJsonObj.getString("height");
                                String video_duration = videoJsonObj.getString("duration");
                                JSONArray video_download_Urls = videoJsonObj.getJSONArray("download");
                                JSONArray thumbnails = videoJsonObj.getJSONArray("thumbnail");
                                String video_download = video_download_Urls.getObject(0, String.class);
                                String video_thumbnail = thumbnails.getObject(0, String.class);
                                //
                                List<String> thumbnailList = new ArrayList<String>();
                                List<String> downloadList = new ArrayList<String>();
                                thumbnailList.add(video_thumbnail);
                                downloadList.add(video_download);
                                //
                                video.setThumbnail(thumbnailList);
                                video.setPlaycount(video_playcount);
                                video.setDuration(video_duration);
                                video.setWidth(video_width);
                                video.setHeight(video_height);
                                video.setDownload(downloadList);
                                content.setVideo(video);
                                break;
                            case "gif":
                                Gif gif=new Gif();
                                JSONObject gifJsonObj = contentJsonObj.getJSONObject("gif");
                                JSONArray imagesJson = gifJsonObj.getJSONArray("images");
                                JSONArray gif_thumbnailJson = gifJsonObj.getJSONArray("gif_thumbnail");
                                String gif_images=imagesJson.getObject(0, String.class);
                                String gif_gif_thumbnail=gif_thumbnailJson.getObject(0,String.class);
                                List<String> imagesList=new ArrayList<String>();
                                List<String> gif_thumbnailList=new ArrayList<String>();
                                imagesList.add(gif_images);
                                gif_thumbnailList.add(gif_gif_thumbnail);
                                gif.setGif_images(imagesList);
                                gif.setGif_gif_thumbnail(gif_thumbnailList);
                                content.setGif(gif);
                                break;
                            case "image":
                                Image image=new Image();
                                JSONObject imageJsonObj = contentJsonObj.getJSONObject("image");
                                JSONArray bigJsonArray = imageJsonObj.getJSONArray("big");
                                String image_big = bigJsonArray.getObject(0, String.class);
                                List<String> image_bigList=new ArrayList<String>();
                                image_bigList.add(image_big);
                                image.setImage_big(image_bigList);
                                content.setImage(image);
                                break;
                            case "text":

                                break;
                        }
                        //把集合放进content中
                        content.setPicture(picture);
                        content.setUser(user);
                        content.setTags(tagsList);
                        contents.add(content);
                    }
                }
                mListView.setAdapter(new CustomAdapter(contents,context));
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
        });
    }
}
