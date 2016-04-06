package com.dream.budejie.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dream.budejie.R;
import com.dream.budejie.adapter.CustomAdapter;
import com.dream.budejie.entity.Content;
import com.dream.budejie.entity.Tags;
import com.dream.budejie.entity.User;
import com.dream.budejie.entity.Video;
import com.dream.budejie.utils.JsonUtils;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jerry on 2015/12/18.
 */
public class VideoFragment extends Fragment {
    private View video_view;

    public VideoFragment() {
    }

    public View getVideo_view() {
        return video_view;
    }

    public void setVideo_view(View video_view) {
        this.video_view = video_view;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        video_view = inflater.inflate(R.layout.fragment_listview, container, false);
        setVideo_view(video_view);
        return video_view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        JsonUtils jsonUtils=new JsonUtils();
//        jsonUtils.initData(url,view,getContext());
    }
}
