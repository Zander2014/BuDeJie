package com.dream.budejie;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.dream.budejie.fragment.DownloadFragment;
import com.dream.budejie.fragment.FinishFragment;
import com.umeng.analytics.MobclickAgent;


public class BendiActivity extends AppCompatActivity implements View.OnClickListener{

    private ActionBar actionBar;
    private TextView downloading,finish,delete,back;
    private DownloadFragment downloadFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bendi);

        setActionBar();
        initVew();


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

    private void initVew() {
        downloading = (TextView) findViewById(R.id.actionbar_bendi_downloading);
        finish = (TextView) findViewById(R.id.actionbar_bendi_finish);
        back = (TextView) findViewById(R.id.actionbar_bendi_back);
        delete = (TextView) findViewById(R.id.actionbar_bendi_delete);
        downloading.setOnClickListener(this);
        downloading.setBackgroundColor(0xffd00000);
        downloadFragment = new DownloadFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.bendi_fl, downloadFragment).commit();
        finish.setOnClickListener(this);
        back.setOnClickListener(this);
        delete.setOnClickListener(this);
    }

    private void setActionBar() {
        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.edit_bg));
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(R.layout.acationbar_bendi);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.actionbar_bendi_downloading:
                setColor();
                downloading.setBackgroundColor(0xffd00000);
                downloadFragment = new DownloadFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.bendi_fl, downloadFragment).commit();
                break;
            case R.id.actionbar_bendi_finish:
                setColor();
                finish.setBackgroundColor(0xffd00000);
                FinishFragment finishFragment = new FinishFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.bendi_fl, finishFragment).commit();
                break;
            case R.id.actionbar_bendi_back:
//                Intent it = new Intent(BendiActivity.this, MainActivity.class);
//                startActivity(it);
                finish();
                break;
            case R.id.actionbar_bendi_delete:
                Toast.makeText(this, "删除成功", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void setColor() {
        finish.setBackgroundColor(Color.WHITE);
        downloading.setBackgroundColor(Color.WHITE);
    }

}
