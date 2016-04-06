package com.dream.budejie.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dream.budejie.R;
import com.dream.budejie.adapter.ImageAdapter;
import com.dream.budejie.entity.Content;
import com.dream.budejie.entity.Picture;
import com.dream.budejie.entity.User;
import com.dream.budejie.utils.JsonUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jerry on 2015/12/18.
 */
public class ImageFragment extends Fragment {
    private String url_image="http://s.budejie.com/topic/list/zuixin/10/budejie-android-6.2.4/0-20.json?";
    private View image_view;

    public ImageFragment() {
    }

    public View getImage_view() {
        return image_view;
    }

    public void setImage_view(View image_view) {
        this.image_view = image_view;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        image_view = inflater.inflate(R.layout.fragment_listview, container, false);
        setImage_view(image_view);
        return image_view;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

}
