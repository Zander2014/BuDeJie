package com.dream.budejie.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dream.budejie.R;
import com.dream.budejie.utils.JsonUtils;
import com.dream.budejie.utils.RefreshAanMore;

/**
 * Created by jerry on 2015/12/18.
 */
public class TotalFragment extends Fragment {
    private static String url = "http://s.budejie.com/topic/list/jingxuan/1/budejie-android-6.2.4/0-20.json?";

    public TotalFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_listview, container, false);

    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        JsonUtils jsonUtils=new JsonUtils();
        jsonUtils.initData(url, view, getContext());
        new RefreshAanMore().refresh(url,view,getContext());
    }

}
