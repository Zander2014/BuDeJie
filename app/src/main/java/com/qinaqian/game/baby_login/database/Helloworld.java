package com.qinaqian.game.baby_login.database;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.TextView;

import com.dream.budejie.R;


public class Helloworld extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helloworld);

        textView = ((TextView) findViewById(R.id.textView));

    }

    /**
     * 回退键设置
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 监听到回退键
        if (keyCode == KeyEvent.KEYCODE_BACK) {
//              // 创建退出对话框
            AlertDialog dialog = new AlertDialog.Builder(this).create();
//            dialog.setTitle("系统提示");
            dialog.setMessage("乃确定不是手滑了么?");
            dialog.setButton(AlertDialog.BUTTON_POSITIVE, "注销", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            dialog.setButton(AlertDialog.BUTTON_NEGATIVE, "手滑了", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            dialog.show();
        }
        return false;

    }

}
