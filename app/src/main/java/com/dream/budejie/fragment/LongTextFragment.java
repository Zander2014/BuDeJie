package com.dream.budejie.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dream.budejie.R;

/**
 * Created by jerry on 2015/12/18.
 */
public class LongTextFragment extends Fragment {
    private View view_html;

    public LongTextFragment() {

    }

    public View getView_html() {
        return view_html;
    }

    public void setView_html(View view_html) {
        this.view_html = view_html;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view_html = inflater.inflate(R.layout.fragment_listview, container, false);
        setView_html(view_html);
        return view_html;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
