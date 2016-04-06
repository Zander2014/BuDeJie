package com.dream.budejie.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.dream.budejie.R;
import com.dream.budejie.utils.JsonUtils;
import com.dream.budejie.utils.RefreshAanMore;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class EliteFragment extends Fragment implements RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener {
    private ViewPager eliteViewPager;
    private List<Fragment> eliteFragments;
    private ElitePagerAdapter elitePagerAdapter;
    private RadioButton total;
    private RadioButton video;
    private RadioButton image;
    private RadioButton joke;
    private RadioButton ranking;
    private RadioButton society;
    private RadioButton longText;
    private RadioGroup eliteTop;
    private HorizontalScrollView eliteScrollView;
    private static JsonUtils jsonUtils = new JsonUtils();
    private static String url_total = "http://s.budejie.com/topic/list/jingxuan/1/budejie-android-6.2.4/0-20.json?";
    private static String url_video = "http://s.budejie.com/topic/list/jingxuan/41/budejie-android-6.2.4/0-20.json?";
    private static String url_image = "http://s.budejie.com/topic/list/zuixin/10/budejie-android-6.2.4/0-20.json?";
    private static String url_joke = "http://s.budejie.com/topic/list/jingxuan/29/budejie-android-6.2.4/0-20.json?";
    private static String url_ranking="http://s.budejie.com/topic/list/remen/1/budejie-android-6.2.4/0-20.json?";
    private static String url_society="http://s.budejie.com/topic/tag-topic/473/new/budejie-android-6.2.4/0-20.json?";
    private static String url_html="http://s.budejie.com/topic/list/jingxuan/29/budejie-android-6.2.4/0-20.json?";
    private TotalFragment totalFragment;
    private VideoFragment videoFragment;
    private ImageFragment imageFragment;
    private JokeFragment jokeFragment;
    private RankingFragment rankingFragment;
    private SocietyFragment societyFragment;
    private LongTextFragment longTextFragment;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_elite, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        eliteViewPager = (ViewPager) view.findViewById(R.id.elite_viewPager);
        total = (RadioButton) view.findViewById(R.id.elite_top_total);
        video = (RadioButton) view.findViewById(R.id.elite_top_video);
        image = (RadioButton) view.findViewById(R.id.elite_top_image);
        joke = (RadioButton) view.findViewById(R.id.elite_top_joke);
        ranking = (RadioButton) view.findViewById(R.id.elite_top_ranking);
        society = (RadioButton) view.findViewById(R.id.elite_top_society);
        longText = (RadioButton) view.findViewById(R.id.elite_top_longText);
        eliteTop = (RadioGroup) view.findViewById(R.id.elite_menu_top);
        eliteScrollView = (HorizontalScrollView) view.findViewById(R.id.elite_hscrollView);
        eliteTop.setOnCheckedChangeListener(this);
        eliteViewPager.setOnPageChangeListener(this);
        initData();
    }

    private void initData() {
        eliteFragments = new ArrayList<Fragment>();
        totalFragment = new TotalFragment();
        videoFragment = new VideoFragment();
        imageFragment = new ImageFragment();
        jokeFragment = new JokeFragment();
        rankingFragment = new RankingFragment();
        societyFragment = new SocietyFragment();
        longTextFragment = new LongTextFragment();
        eliteFragments.add(totalFragment);
        eliteFragments.add(videoFragment);
        eliteFragments.add(imageFragment);
        eliteFragments.add(jokeFragment);
        eliteFragments.add(rankingFragment);
        eliteFragments.add(societyFragment);
        eliteFragments.add(longTextFragment);
        elitePagerAdapter = new ElitePagerAdapter(getChildFragmentManager(), eliteFragments);
        eliteViewPager.setAdapter(elitePagerAdapter);
        eliteViewPager.setOffscreenPageLimit(2);
        eliteViewPager.setCurrentItem(0);
        total.setChecked(true);
    }

    private void eliteTopColor() {
        total.setTextColor(Color.BLACK);
        total.setTextSize(16);
        video.setTextColor(Color.BLACK);
        video.setTextSize(16);
        image.setTextColor(Color.BLACK);
        image.setTextSize(16);
        joke.setTextColor(Color.BLACK);
        joke.setTextSize(16);
        ranking.setTextColor(Color.BLACK);
        ranking.setTextSize(16);
        society.setTextColor(Color.BLACK);
        society.setTextSize(16);
        longText.setTextColor(Color.BLACK);
        longText.setTextSize(16);
    }

    private void eliteTopSelect(RadioButton radioButton) {
        radioButton.setTextColor(Color.RED);
        radioButton.setTextSize(20);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.elite_top_total:
                eliteTopColor();
                eliteTopSelect(total);
                eliteViewPager.setCurrentItem(0);
                break;
            case R.id.elite_top_video:
                eliteTopColor();
                eliteTopSelect(video);
                eliteViewPager.setCurrentItem(1);
                View video_view = videoFragment.getVideo_view();
                jsonUtils.initData(url_video, video_view, getContext());
                new RefreshAanMore().refresh(url_video,video_view,getContext());
                break;
            case R.id.elite_top_image:
                eliteTopColor();
                eliteTopSelect(image);
                eliteViewPager.setCurrentItem(2);
                View image_view = imageFragment.getImage_view();
//                jsonUtils.initData(url_image, image_view, getContext());
                jsonUtils.loadDataImage(url_image,image_view,getContext());
                new RefreshAanMore().refresh(url_image, image_view, getContext());
                break;
            case R.id.elite_top_joke:
                eliteTopColor();
                eliteTopSelect(joke);
                eliteViewPager.setCurrentItem(3);
                View joke_view = jokeFragment.getJoke_view();
                jsonUtils.initData(url_joke, joke_view, getContext());
                new RefreshAanMore().refresh(url_joke, joke_view, getContext());
                break;
            case R.id.elite_top_ranking:
                eliteTopColor();
                eliteTopSelect(ranking);
                eliteViewPager.setCurrentItem(4);
                View view_ranking = rankingFragment.getView_ranking();
                jsonUtils.initData(url_ranking,view_ranking,getContext());
                new RefreshAanMore().refresh(url_ranking,view_ranking,getContext());
                break;
            case R.id.elite_top_society:
                eliteTopColor();
                eliteTopSelect(society);
                eliteViewPager.setCurrentItem(5);
                View view_society = societyFragment.getView_society();
                jsonUtils.initData(url_society,view_society,getContext());
                new RefreshAanMore().refresh(url_society,view_society,getContext());
                break;
            case R.id.elite_top_longText:
                eliteTopColor();
                eliteTopSelect(longText);
                eliteViewPager.setCurrentItem(6);
                View view_html = longTextFragment.getView_html();
                jsonUtils.initData(url_html,view_html,getContext());
                new RefreshAanMore().refresh(url_html,view_html,getContext());
                break;
        }
    }

    @Override
    public void onPageSelected(int position) {
        RadioButton localRadioButton = (RadioButton) eliteTop.getChildAt(position);
        localRadioButton.setChecked(true);
//        DisplayMetrics displayMetrics = new DisplayMetrics();
//
//        int radioButtonPiexls=localRadioButton.getWidth();
//        int distance= (int) ((position + 0.5)* radioButtonPiexls - displayMetrics.widthPixels/2);
//        eliteScrollView.scrollTo(distance,0);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }


    class ElitePagerAdapter extends FragmentPagerAdapter {
        private List<Fragment> mList;

        public ElitePagerAdapter(FragmentManager fm, List<Fragment> list) {
            super(fm);
            this.mList = list;
        }

        @Override
        public Fragment getItem(int position) {
            return eliteFragments.get(position);
        }

        @Override
        public int getCount() {
            return eliteFragments.size();
        }
    }

}
