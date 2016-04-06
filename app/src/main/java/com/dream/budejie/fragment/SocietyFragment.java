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
public class SocietyFragment extends Fragment {
    private View view_society;

    public SocietyFragment() {
    }

    public View getView_society() {
        return view_society;
    }

    public void setView_society(View view_society) {
        this.view_society = view_society;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view_society = inflater.inflate(R.layout.fragment_listview, container, false);
        setView_society(view_society);
        return view_society;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
