        package com.dream.budejie.fragment;


        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.support.v4.view.ViewPager;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.HorizontalScrollView;
        import android.widget.RadioButton;
        import android.widget.RadioGroup;
        import android.widget.TextView;

        import com.dream.budejie.R;
        import com.dream.budejie.adapter.MyVpAdapter;

        import java.util.ArrayList;
        import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewPostFragment extends Fragment {
    private View view;
    private HorizontalScrollView horizontalScrollView;
    private RadioGroup radioGroup;
    private TextView indicator;
    private ViewPager viewPager;
    public NewPostFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_new_post, container, false);
        initTab_top();
        initView();
        return view;
    }

    private void initTab_top() {
        horizontalScrollView= (HorizontalScrollView) view.findViewById(R.id.scroll);
        radioGroup= (RadioGroup) view.findViewById(R.id.rgp);
//        indicator= (TextView) view.findViewById(R.id.tv);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                for(int i = 0; i < group.getChildCount(); i++){
                    RadioButton radioBtn = (RadioButton) group.getChildAt(i);
                    if(radioBtn.isChecked()){
                        radioBtn.setTextSize(20);
                    }else{
                        radioBtn.setTextSize(16);
                    }
                }

                switch (checkedId) {
                    case R.id.textView1:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.textView2:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.textView3:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.textView4:
                        viewPager.setCurrentItem(3);
                        break;
                    case R.id.textView5:
                        viewPager.setCurrentItem(4);
                        break;
                    default:
                        break;
                }
            }
        });
    }


    private void initView() {
        List<Fragment> fList=new ArrayList<Fragment>();
        for (int i = 0; i < 5; i++) {
            Common_NewPost_Fragment f=new Common_NewPost_Fragment();
            Bundle bundle=new Bundle();
            bundle.putInt("index", i);
            f.setArguments(bundle);
            fList.add(f);
        }
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        viewPager.setAdapter(new MyVpAdapter(getChildFragmentManager(), fList));
        viewPager.setCurrentItem(0);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            //			@Override
            public void onPageSelected(int arg0) {


                // 获取对应位置的RadioButton
                RadioButton radioBtn = (RadioButton) radioGroup.getChildAt(arg0);
                // 设置对应位置的RadioButton为选中的状态
                radioBtn.setChecked(true);


            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub

            }
        });
    }

}

