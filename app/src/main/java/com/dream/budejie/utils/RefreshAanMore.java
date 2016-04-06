package com.dream.budejie.utils;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import com.dream.budejie.R;

/**
 * Created by Administrator on 2015/12/24.
 *
 */
public class RefreshAanMore {

    public void refresh(final String url, final View view, final Context context) {
        final SwipeRefreshLayout refresh = ((SwipeRefreshLayout) view.findViewById(R.id.content_refresh));
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            public void onRefresh() {
                new JsonUtils().initData(url, view,context);
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


