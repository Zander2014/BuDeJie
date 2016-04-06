package com.qinaqian.game.baby_login.activitys;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.dream.budejie.R;
import com.umeng.analytics.MobclickAgent;
import com.umeng.message.PushAgent;

import java.io.File;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener {
    private SharedPreferences shareSet;
    private ToggleButton Push_message;
    private ToggleButton Check_update;
    private TextView cacheSize;
    private TextView Clear;
    private File[] files;
    private  long size;
    private ToggleButton WiFi;
    private WifiManager Wifi_state;
    private RelativeLayout advice_event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initActionbar();
        shareSet = getSharedPreferences("setting", MODE_PRIVATE);
        initView();
        initData();//  处理图片文件路径问题
        Wifi_state = ((WifiManager) getSystemService(Context.WIFI_SERVICE));//wifi状态
        PushAgent.getInstance(this).onAppStart();// 推送
    }
    //    统计分析
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
    private void initData() {
        File file = new File(getExternalCacheDir() + "/xUtils_img");
        files = file.listFiles();
        for (File filetemp:files){
            size+=filetemp.length();
        }
        if (size>1024*1024){
            cacheSize.setText(size/(1024*1024)+"MB");
        }else {
            cacheSize.setText(size/1024+"KB");
        }
    }

    private void initView() {
        Push_message = (ToggleButton) findViewById(R.id.push);
        Check_update = (ToggleButton) findViewById(R.id.update);
        WiFi = ((ToggleButton) findViewById(R.id.wifi));
        advice_event = ((RelativeLayout) findViewById(R.id.Advice_Event));

        Push_message.setOnClickListener(this);
        Check_update.setOnClickListener(this);
        WiFi.setOnClickListener(this);
        advice_event.setOnClickListener(this);

        Push_message.setChecked(true);
        Check_update.setChecked(true);
        WiFi.setChecked(true);


        cacheSize = (TextView) findViewById(R.id.cache_size);
        Clear = (TextView) findViewById(R.id.clear);
        Clear.setOnClickListener(this);// 清除

    }

    private void initActionbar() {
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);// 左上角回退键
    }
    /**
     * 返回 /回退键
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.update:
                if (!Check_update.isChecked()) {
                    shareSet.edit().putBoolean("update", false).apply();
                    Toast.makeText(this, "关闭更新", Toast.LENGTH_SHORT).show();
                } else {
                    shareSet.edit().putBoolean("update", true).apply();
                    Toast.makeText(this, "打开更新", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.push:
                if (!Push_message.isChecked()) {
                    shareSet.edit().putBoolean("push", false).apply();
                    Toast.makeText(this, "关闭推送", Toast.LENGTH_SHORT).show();
                } else {
                    shareSet.edit().putBoolean("push", true).apply();
                    Toast.makeText(this, "打开推送", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.clear:
                for (File file:files) {
                    file.delete();
                }
                Toast.makeText(this,"清除成功",Toast.LENGTH_SHORT).show();
                cacheSize.setText("0 K");
                break;
            case R.id.wifi:
                if (!WiFi.isChecked()) {
                    shareSet.edit().putBoolean("wiif", false).apply();
                    Wifi_state.setWifiEnabled(false);
                    Toast.makeText(this, "关闭WiFi", Toast.LENGTH_SHORT).show();
                } else {
                    shareSet.edit().putBoolean("wifi", true).apply();
                    Wifi_state.setWifiEnabled(true);
                    Toast.makeText(this, "打开WiFi", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.Advice_Event:
                Intent intent =new Intent(this,FanKuiActivity.class);
                startActivity(intent);
//                finish();
                break;
        }
    }
}
