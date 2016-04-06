package com.dream.budejie.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dream.budejie.R;
import com.dream.budejie.adapter.AudioAdapter;
import com.dream.budejie.adapter.CustomAdapter;
import com.dream.budejie.adapter.ImageAdapter;
import com.dream.budejie.entity.Audio;
import com.dream.budejie.entity.Content;
import com.dream.budejie.entity.Picture;
import com.dream.budejie.entity.Tags;
import com.dream.budejie.entity.User;
import com.dream.budejie.entity.Video;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import static com.dream.budejie.R.id.normal_listView;

/**
 * A simple {@link Fragment} subclass.
 */
public class Common_NewPost_Fragment extends Fragment {
    private View view;
    int key;
    private SwipeRefreshLayout refresh;
    ListView mListView;
    List<Content> contents;
    //    private LinearLayout add_more;
//    private ListView listview;
//   private boolean flag=false;
    public Common_NewPost_Fragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initChoose();
        return view;
    }

    private void initChoose() {
        Bundle bundle = getArguments();
        key = bundle.getInt("index");
        switch (key) {
            case 0:
                view = View.inflate(getContext(), R.layout.fragment_listview, null);

                String url4 = "http://s.budejie.com/topic/list/zuixin/1/budejie-android-6.2.4/0-20.json?";
                initData(url4);
                RefreshAndMore(url4);

                break;
            case 1:
                view = View.inflate(getContext(), R.layout.fragment_listview, null);
                String url3 = "http://s.budejie.com/topic/list/zuixin/41/budejie-android-6.2.4/0-20.json?";
                initData(url3);
                RefreshAndMore(url3);
                break;
            case 2:
                view = View.inflate(getContext(), R.layout.fragment_listview, null);
                String url2 = "http://s.budejie.com/topic/list/zuixin/10/budejie-android-6.2.4/0-20.json?";
                initData(url2);
                RefreshAndMore(url2);
                break;
            case 3:
                view = View.inflate(getContext(), R.layout.fragment_listview, null);
                String url1 = "http://s.budejie.com/topic/list/zuixin/29/budejie-android-6.2.4/0-20.json?";
                initData(url1);
                RefreshAndMore(url1);
                break;
            case 4:
                view = View.inflate(getContext(), R.layout.fragment_listview, null);
                String url5="http://s.budejie.com/topic/list/zuixin/31/budejie-android-6.2.4/0-20.json?";
                initData(url5);
                RefreshAndMore(url5);
                break;
            default:
                break;
        }
    }

    private void initData(String url1) {
        System.out.println("--initData已执行");
        RequestParams params = new RequestParams(url1);
        Callback.Cancelable cancelable = x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                System.out.println("--成功方法已执行");
                switch (key) {
                    case 0:
                        Json2all(result);
                        break;
                    case 1:
                        Json2video(result);
                        break;
                    case 2:
                        Json2image(result);
                        break;
                    case 3:
                        Json2joke(result);
                        break;
                    case 4:
                        Json2audio(result);
                        break;
                    default:
                        break;
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                System.out.println("--onError");
            }

            @Override
            public void onCancelled(CancelledException cex) {
                System.out.println("--onCancelled");
            }

            @Override
            public void onFinished() {
                System.out.println("--onFinished");
            }
        });
    }

    private void Json2audio(String result) {

        List<Content> list = new ArrayList<>();
        ListView listView = (ListView) view.findViewById(normal_listView);
        JSONArray data = JSON.parseObject(result).getJSONArray("list");
        Content datas;
        User user;
        for (int i = 0; i < data.size(); i++) {
            if ((datas = data.getObject(i, Content.class)) != null) {
                JSONObject index = data.getJSONObject(i);

                JSONObject u = index.getJSONObject("u");
                user = new User();
                String User_name = u.getString("name");
                JSONArray header = u.getJSONArray("header");
                String s = header.getString(0);
                user.setHeader(s);
                user.setName(User_name);
                datas.setUser(user);

                JSONArray tags = index.getJSONArray("tags");
                List<Tags> tagsList = new ArrayList<>();
                Tags tagsObject;
                for (int j = 0; j < tags.size(); j++) {
                    Tags object = tags.getObject(j, Tags.class);
                    if (object != null) {
                        tagsList.add(object);
                    }
                }
                datas.setTags(tagsList);
                Audio audio=new Audio();
                JSONObject audio1 = index.getJSONObject("audio");
                JSONArray audio_mp3 = audio1.getJSONArray("audio");
                JSONArray audio_img = audio1.getJSONArray("thumbnail");
                audio.setThumbnail(audio_img.getString(0));
                audio.setAudios(audio_mp3.getString(0));
//                System.out.println("--audio:" + audio_img.getString(0));
                System.out.println("--audio:" + audio_mp3.getString(0));
                datas.setAudio(audio);

                list.add(datas);
            }
        }
        listView.setAdapter(new AudioAdapter(getContext(),list));
    }

    private void Json2all(String result) {
        mListView = (ListView) view.findViewById(normal_listView);
        contents = new ArrayList<Content>();
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
                    Log.i("jerry", tagsObject.getTagsName());
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
                        JSONObject gifJsonObj = contentJsonObj.getJSONObject("gif");
                        JSONArray images = gifJsonObj.getJSONArray("images");
                        picture.setImages(images.get(0).toString());
                        break;
                    case "image":
                        JSONObject imageJsonObj = contentJsonObj.getJSONObject("image");
                        JSONArray bigJsonArray = imageJsonObj.getJSONArray("big");
                        picture.setBig(bigJsonArray.get(0).toString());
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
        mListView.setAdapter(new CustomAdapter(contents, getContext()));
    }


    private void Json2video(String result) {
        List<Content> list = new ArrayList<>();
        ListView listView = (ListView) view.findViewById(normal_listView);

        JSONArray data = JSON.parseObject(result).getJSONArray("list");
        Content datas;
        User user;
        for (int i = 0; i < data.size(); i++) {
            if ((datas = data.getObject(i, Content.class)) != null) {
                JSONObject index = data.getJSONObject(i);

                JSONObject u = index.getJSONObject("u");
                user = new User();
                String User_name = u.getString("name");
                JSONArray header = u.getJSONArray("header");
                String s = header.getString(0);
                user.setHeader(s);
                user.setName(User_name);
                datas.setUser(user);

                JSONArray tags = index.getJSONArray("tags");
                List<Tags> tagsList = new ArrayList<>();
                Tags tagsObject;
                for (int j = 0; j < tags.size(); j++) {
                    Tags object = tags.getObject(j, Tags.class);
                    if (object != null) {
                        tagsList.add(object);
                    }
                }
                datas.setTags(tagsList);

                JSONObject videoJsonObj = index.getJSONObject("video");
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
                System.out.println("--video--" + video.getThumbnail().toString());
                datas.setVideo(video);

                list.add(datas);
            }
        }
//        listView.setAdapter(new VideoAdapter(getContext(),list));
        listView.setAdapter(new CustomAdapter(list, getContext()));
    }

    private void Json2joke(String result) {
        List<Content> list = new ArrayList<>();
        ListView listView = (ListView) view.findViewById(normal_listView);
        JSONArray data = JSON.parseObject(result).getJSONArray("list");
        Content datas;
        User user;
        for (int i = 0; i < data.size(); i++) {
            if ((datas = data.getObject(i, Content.class)) != null) {
                JSONObject index = data.getJSONObject(i);

                JSONObject u = index.getJSONObject("u");
                user = new User();
                String User_name = u.getString("name");
                JSONArray header = u.getJSONArray("header");
                String s = header.getString(0);
                user.setHeader(s);
                user.setName(User_name);
                datas.setUser(user);

                JSONArray tags = index.getJSONArray("tags");
                List<Tags> tagsList = new ArrayList<>();
                Tags tagsObject;
                for (int j = 0; j < tags.size(); j++) {
                    Tags object = tags.getObject(j, Tags.class);
                    if (object != null) {
                        tagsList.add(object);
                    }
                }
                datas.setTags(tagsList);
                list.add(datas);
            }
        }
        listView.setAdapter(new CustomAdapter(list, getContext()));
    }

    private void Json2image(String result) {
        List<Content> list = new ArrayList<>();
        ListView listView = (ListView) view.findViewById(normal_listView);
        JSONArray data = JSON.parseObject(result).getJSONArray("list");
        Content datas;
        User user;
        for (int i = 0; i < data.size(); i++) {
            System.out.println("--正在执行For循环");
            if ((datas = data.getObject(i, Content.class)) != null) {
                System.out.println("--正在执行If");
                JSONObject index = data.getJSONObject(i);

                JSONObject u = index.getJSONObject("u");
                user = new User();

                String User_name = u.getString("name");
                JSONArray header = u.getJSONArray("header");
                String s = header.getString(0);

                Picture picture = new Picture();
                if ("gif".equals(datas.getType())) {
                    JSONObject image_type1 = index.getJSONObject("gif");
                    JSONArray gif = image_type1.getJSONArray("images");
                    picture.setImages(gif.getString(0));
                } else {
                    JSONObject image_type2 = index.getJSONObject("image");
                    JSONArray image = image_type2.getJSONArray("big");
                    picture.setBig(image.getString(0));
                }
                datas.setPicture(picture);
                user.setHeader(s);
                user.setName(User_name);
                datas.setUser(user);
                list.add(datas);
                System.out.println("--For循环执行完毕");
            }
        }
        System.out.println("--适配器已执行");
        listView.setAdapter(new ImageAdapter(getContext(), list));

    }

    private void RefreshAndMore(final String url) {
        mListView= (ListView) view.findViewById(R.id.normal_listView);
        refresh = ((SwipeRefreshLayout) view.findViewById(R.id.content_refresh));
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initData(url);
                refresh.setRefreshing(false);
            }
        });
//        mListView.setOnScrollListener(new AbsListView.OnScrollListener(){
//               int lastItem = 0;
//            @Override
//            public void onScrollStateChanged(AbsListView view, int scrollState) {
//
//
//                if(scrollState == NumberPicker.OnScrollListener.SCROLL_STATE_IDLE && lastItem == new CustomAdapter(contents,getContext()).getCount() - 1){
//
//                    initData("http://s.budejie.com/topic/list/zuixin/1/budejie-android-6.2.4/21-40.json?");
//                }
//
//            }
//
//            @Override
//            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//            lastItem=firstVisibleItem+visibleItemCount-1;
//            }
//        });

    }

}


