package com.dream.budejie.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.dream.budejie.R;
import com.qinaqian.game.baby_login.Login.LoginsActivity;
import com.qinaqian.game.baby_login.Login.RegisterActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class AttentionFragment extends Fragment {

    private Button button_dl;
    private Button button_zc;

    public AttentionFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.activity_main2,null);
        button_dl = ((Button) view.findViewById(R.id.button_dl));
        button_zc = ((Button) view.findViewById(R.id.button_zc));
//        button_dl.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if(MotionEvent.ACTION_DOWN == event.getAction()){
//                    button_dl.setSelected(true);
//                }else if(MotionEvent.ACTION_MOVE != event.getAction()){
//                    button_dl.setSelected(false);
//                }
//                return false;
//            }
//        });


        button_dl.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 =new Intent(getActivity(), LoginsActivity.class);
                startActivity(intent1);
            }
        });
        button_zc.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 =new Intent(getActivity(), RegisterActivity.class);
                startActivity(intent2);
            }
        });
        return view;
    }

//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            exitBy2Click();
//        }
//        return false;
//    }
//    /**
//     * 双击退出函数
//     */
//    private static Boolean isExit = false;
//
//    private void exitBy2Click() {
//        Timer tExit = null;
//        if (isExit == false) {
//            isExit = true; // 准备退出
//            Toast.makeText(getActivity(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
//            tExit = new Timer();
//            tExit.schedule(new TimerTask() {
//                @Override
//                public void run() {
//                    isExit = false; // 取消退出
//                }
//            }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务
//
//        } else {
//            finish();
//            System.exit(0);
//        }
//    }
}
