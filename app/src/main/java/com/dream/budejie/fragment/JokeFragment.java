package com.dream.budejie.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dream.budejie.R;
import com.dream.budejie.utils.JsonUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class JokeFragment extends Fragment {
    private View joke_view;

    public JokeFragment() {
    }

    public View getJoke_view() {
        return joke_view;
    }

    public void setJoke_view(View joke_view) {
        this.joke_view = joke_view;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        joke_view = inflater.inflate(R.layout.fragment_listview, container, false);
        setJoke_view(joke_view);
        return joke_view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
