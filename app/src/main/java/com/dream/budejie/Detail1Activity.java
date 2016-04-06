package com.dream.budejie;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;


public class Detail1Activity extends AppCompatActivity implements View.OnClickListener {

    private WebView wb;
    private String url,name,pic;
    private ActionBar actionBar;
    private TextView back, forward,actionbar_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail1);
        ShareSDK.initSDK(this);
        initView();

        Intent it = getIntent();
        url = it.getStringExtra("url");
        name = it.getStringExtra("name");
        pic = it.getStringExtra("pic");
        wb.loadUrl(url);
        actionbar_name.setText(name);

    }

    private void initView() {
        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.edit_bg));
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(R.layout.acationbar_detail1);


        wb = (WebView) findViewById(R.id.webView);
        back = (TextView) findViewById(R.id.actionbar_detail1_back);
        forward = (TextView) findViewById(R.id.actionbar_detail1_forward);
        actionbar_name = (TextView) findViewById(R.id.actionbar_detail1_title);

        back.setOnClickListener(this);
        forward.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.actionbar_detail1_back:
                finish();
                break;
            case R.id.actionbar_detail1_forward:
                showShare();
                break;
        }
    }

    /**
     * 第三方分享
     */
    private void showShare() {

        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

// 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
//        oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
//         title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
//        oks.setTitle("测试");
//        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
//        oks.setTitleUrl("http://sharesdk.cn");
//        // text是分享文本，所有平台都需要这个字段
        oks.setText(url);
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数

        oks.setImagePath(url);//确保SDcard下面存在此张图片

//        // url仅在微信（包括好友和朋友圈）中使用
//        oks.setUrl("http://sharesdk.cn");
//        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
//        oks.setComment("我是测试评论文本");
//        // site是分享此内容的网站名称，仅在QQ空间使用
//        oks.setSite(getString(R.string.app_name));
//        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
//        oks.setSiteUrl("http://sharesdk.cn");

// 启动分享GUI
        oks.show(this);
    }
}
