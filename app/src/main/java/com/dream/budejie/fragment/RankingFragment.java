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
public class RankingFragment extends Fragment {
    private View view_ranking;

    public View getView_ranking() {
        return view_ranking;
    }

    public void setView_ranking(View view_ranking) {
        this.view_ranking = view_ranking;
    }

    public RankingFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view_ranking = inflater.inflate(R.layout.fragment_listview, container, false);
        setView_ranking(view_ranking);
        return view_ranking;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
