package com.dream.budejie;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.dream.budejie.fragment.AttentionFragment;
import com.dream.budejie.fragment.EliteFragment;
import com.dream.budejie.fragment.MineFragment;
import com.dream.budejie.fragment.NewPostFragment;
import com.umeng.analytics.MobclickAgent;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private RadioGroup rg;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.action_bar));
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(R.layout.actionbar_elite);
        initTab_bottom();
        initEliteActionBar();
        initData();
    }

    private void initData() {
        ImageButton middle_plus= (ImageButton) findViewById(R.id.writeposts);
        middle_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getBaseContext(),Middle_plusActivity.class);
                startActivity(intent);
            }
        });
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

    private void initEliteActionBar() {
        ImageView elite_game = (ImageView) findViewById(R.id.actionbar_elite_game);
        TextView elite_title = (TextView) findViewById(R.id.actionbar_elite_title);
        ImageView elite_random = (ImageView) findViewById(R.id.actionbar_elite_random);
        elite_game.setOnClickListener(this);
        elite_title.setOnClickListener(this);
        elite_random.setOnClickListener(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitBy2Click();
        }
        return false;
    }

    private boolean isExit = false;

    private void exitBy2Click() {
        Timer tExit = null;
        if (isExit == false) {
            isExit = true;
            Toast.makeText(getBaseContext(), "再次点击退出", Toast.LENGTH_SHORT).show();
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false;
                }
            }, 2000);
        } else {
            finish();
            System.exit(0);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.actionbar_elite_game:
                Toast.makeText(getBaseContext(), "elite game", Toast.LENGTH_SHORT).show();
                break;
            case R.id.actionbar_attention_title:
                Toast.makeText(getBaseContext(), "elite title", Toast.LENGTH_SHORT).show();
                break;
            case R.id.actionbar_elite_random:
                Toast.makeText(getBaseContext(), "elite random", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    private void initTab_bottom() {
        rg = (RadioGroup) findViewById(R.id.rg);
        EliteFragment eleteFragment = new EliteFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fl, eleteFragment).commit();
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.elite:
                        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.action_bar));
                        actionBar.setCustomView(R.layout.actionbar_elite);
                        EliteFragment eliteFragment = new EliteFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fl, eliteFragment).commit();
                        break;
                    case R.id.newpost:
                        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.action_bar));
                        actionBar.setCustomView(R.layout.acationbar_new_post);
                        NewPostFragment newPostFragment = new NewPostFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fl, newPostFragment).commit();
                        break;
                    case R.id.attention:
                        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.action_bar));
                        actionBar.setCustomView(R.layout.actionbar_attention);
                        AttentionFragment attentionFragment = new AttentionFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fl, attentionFragment).commit();
                        break;
                    case R.id.mine:
//                        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.edit_bg));
                        actionBar.setCustomView(R.layout.acationbar_mine);
                        MineFragment mineFragment = new MineFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fl, mineFragment).commit();
                        break;
                    default:
                        break;
                }
            }
        });
    }
}
