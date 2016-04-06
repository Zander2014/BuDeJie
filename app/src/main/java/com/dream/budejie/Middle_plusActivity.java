package com.dream.budejie;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

public class Middle_plusActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_middle_plus);
        initData();
    }

    private void initData() {
        Button cancel_button= (Button) findViewById(R.id.cancel_button);
        RadioButton send_video= (RadioButton) findViewById(R.id.send_video);
        RadioButton send_link= (RadioButton) findViewById(R.id.send_link);
        RadioButton send_picture= (RadioButton) findViewById(R.id.send_picture);
        RadioButton send_text= (RadioButton) findViewById(R.id.send_text);
        RadioButton send_voice= (RadioButton) findViewById(R.id.send_voice);
        RadioButton audit= (RadioButton) findViewById(R.id.audit);
        View.OnClickListener onClickListener=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.cancel_button:
                        finish();
                        break;
                    case R.id.send_video:
                        Toast.makeText(getBaseContext(),"发视频",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.send_picture:
                        Toast.makeText(getBaseContext(),"发图片",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.send_voice:
                        Toast.makeText(getBaseContext(),"发声音",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.audit:
                        Toast.makeText(getBaseContext(),"审贴",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.send_link:
                        Toast.makeText(getBaseContext(),"发连接",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.send_text:
                        Toast.makeText(getBaseContext(),"发段子",Toast.LENGTH_SHORT).show();
                    default:
                        break;
                }

            }
        };
        cancel_button.setOnClickListener(onClickListener) ;
        send_link.setOnClickListener(onClickListener);
        send_picture.setOnClickListener(onClickListener);
        send_text.setOnClickListener(onClickListener);
        send_video.setOnClickListener(onClickListener);
        send_voice.setOnClickListener(onClickListener);
        audit.setOnClickListener(onClickListener);
    }
}
