package com.qinaqian.game.baby_login.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dream.budejie.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FanKuiActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText et1,et2;
    private Button bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fan_kui);
        init();
    }
    public void init(){
        et1= (EditText) findViewById(R.id.fankui_et);
        et2= (EditText) findViewById(R.id.fankui_email);
        bt= (Button) findViewById(R.id.fankui_ok);
        bt.setOnClickListener(this);

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
            case R.id.fankui_ok:
                if (TextUtils.isEmpty(et1.getText().toString())){
                    Toast.makeText(this,"求建议，求侮辱",Toast.LENGTH_LONG).show();
                }
                else if (TextUtils.isEmpty(et2.getText().toString())){
                    Toast.makeText(this,"给个邮箱，思密达~",Toast.LENGTH_LONG).show();
                }
                else{
                    if (isEmail(et2.getText().toString())){
                        Intent intent =new Intent(getBaseContext(),SettingActivity.class);
                        Toast.makeText(this,"提交成功，感谢你的意见",Toast.LENGTH_LONG).show();
                        startActivity(intent);
                        finish();
                    }else {
                        Toast.makeText(this,"请输入正确的邮箱",Toast.LENGTH_LONG).show();
                    }
                }

                break;
        }
    }


    public boolean isMobileNO(String mobiles) {
        Pattern p = Pattern
                .compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);

        return m.matches();
    }
    //判断email格式是否正确
    public boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    //判断是否全是数字
    public boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }
}
