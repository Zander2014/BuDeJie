package com.qinaqian.game.baby_login.Login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.dream.budejie.R;
import com.umeng.analytics.MobclickAgent;

public class forgetActivity extends AppCompatActivity {

    private EditText mEmiltext;
    private Spinner mSpinner;
    private Button mButton;
    private String user;
    private String [] SpinnerData;
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
        initFind(); // ActionBar 回退事件和UI组件
        initData();//  Spinner 读取数据
        initButton();// Button 事件
        initSpinnerEvent();// Spinner 监听事件
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

    private void initButton() {
        mEmiltext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0 && mEmiltext.getText().toString().length() > 0) {
                    mButton.setEnabled(true);
                } else {
                    mButton.setEnabled(false);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });

            mEmiltext.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }
                @Override
                public void afterTextChanged(Editable s) {
                    user = mEmiltext.getText().toString();
                }
            });
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext()
                        ,"重置链接已经发送到:"+user+SpinnerData[index].toString()+"邮箱"
                        ,Toast.LENGTH_LONG).show();
                finish();
            }
        });

    }

    private void initData() {

        SpinnerData = getResources().getStringArray(R.array.spinner_email);//  从values中读取数据
//        mSpinner.setPrompt(getResources().getString(R.string.spinner_help)); // 读取标题
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getBaseContext(), android.R.layout.simple_spinner_dropdown_item, SpinnerData);
        mSpinner.setAdapter(adapter);
        mSpinner.setPrompt("选着邮箱类型");
    }

    private void initFind() {

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // 左上角回退键

        mEmiltext = ((EditText) findViewById(R.id.email_cz));
        mSpinner = ((Spinner) findViewById(R.id.spinner_cz));
        mButton = ((Button) findViewById(R.id.email_cz_button));
        mButton.setEnabled(false);
        mEmiltext.setInputType(InputType.TYPE_CLASS_NUMBER);//  数字键盘
    }

    /**
     * 返回 /回退键
     *
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
  // 提取 点击Spinner的 位置
    private void initSpinnerEvent() {

        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            private String str01;

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                index = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
}
