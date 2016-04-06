package com.dream.budejie;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.dream.budejie.fragment.NewPostFragment;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {

    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.action_bar));
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(R.layout.acationbar_detail);
        initTab_bottom();
        initEliteActionBar();

    }

    private void initEliteActionBar() {
        TextView elite_back = (TextView) findViewById(R.id.actionbar_detail_back);
        TextView elite_refush = (TextView) findViewById(R.id.actionbar_detail_forward);
        elite_back.setOnClickListener(this);
        elite_refush.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.actionbar_detail_back:
                finish();
                break;
            case R.id.actionbar_detail_forward:
                Toast.makeText(getBaseContext(), "elite random", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    private void initTab_bottom() {
        NewPostFragment newPostFragment = new NewPostFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.detail_fl, newPostFragment).commit();

    }
}
