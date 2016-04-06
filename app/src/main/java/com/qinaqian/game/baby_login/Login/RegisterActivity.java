package com.qinaqian.game.baby_login.Login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dream.budejie.R;
import com.qinaqian.game.baby_login.database.DatabaseAdapter;
import com.umeng.analytics.MobclickAgent;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    private DatabaseAdapter dbHelper;// 数据库登录

    // 处理 登录不可用  以及登录启用后的 UI控件
    private AutoCompleteTextView mEmailView;//  邮件
    private EditText mPasswordView;  //    密码
    private Button Registerbutton;//   注册按钮
    private CheckBox mCheckBox;//     用户同意按钮

    //  处理 密码账号 输入不对的提示 信息 的变量
    private String email;
    private String password;

    private View mProgressView;
    private View mLoginFormView;
    private TextView textView_user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //-------------------------------------------------
        SharedPreferences settings = getSharedPreferences(LoginsActivity.MY_PREFS, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putLong("uid", 0);
        editor.commit();
        //-------------------------------------------------   数据库加载
        dbHelper = new DatabaseAdapter(this);
        dbHelper.open();
        //-------------------------------------------------  数据库加载
        setContentView(R.layout.activity_register);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // 左上角回退键

        initFind();
        Registerbutton.setEnabled(false);// 预设不可用
        initCheck();// 登录不可用
        initLogin();// 登录启用后的登录事件

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
    private void initLogin() {
        Registerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin(view);
            }
        });
    }
    /**
     * 返回 /回退键
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void initCheck() {
        // checkBox 监听
        mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked && mEmailView.getText().toString().length() > 0 && mPasswordView.getText().toString().length() > 0) {
                    Registerbutton.setEnabled(true);
                } else {
                    Registerbutton.setEnabled(false);
                }
            }
        });
        //     邮件监听
        mEmailView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.length() > 0 && mEmailView.getText().toString().length() > 0 && mPasswordView.getText().toString().length() > 0 && mCheckBox.isChecked()) {
                    Registerbutton.setEnabled(true);
                } else {
                    Registerbutton.setEnabled(false);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        //    密码监听
        mPasswordView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0 && mPasswordView.getText().toString().length() > 0 && mEmailView.getText().toString().length() > 0 && mCheckBox.isChecked()) {
                    Registerbutton.setEnabled(true);
                } else {
                    Registerbutton.setEnabled(false);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });

    }

    private void initFind() {
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email_zc);
        mPasswordView = (EditText) findViewById(R.id.password_zc);
        Registerbutton = ((Button) findViewById(R.id.email_register_in_button));
        mCheckBox = ((CheckBox) findViewById(R.id.check_agress));
        textView_user = ((TextView) findViewById(R.id.textview_user));

      //直接监听 用户协议 并跳转
        textView_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getBaseContext(),UserProActivity.class);
                startActivity(intent);

            }
        });
    }

    /**
     * 试图登录或注册帐户指定的登录表单。
     * 如果有形式错误(无效的电子邮件,缺少字段等),
     * 错误了,没有实际的登录尝试。
     */
    private void attemptLogin(View v) {

//        / /重置错误。
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // 提取输入的值。
        email = mEmailView.getText().toString();
        password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // 检查有效的密码,如果用户输入一个。
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // 检查一个有效的电子邮件地址。
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmail(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            // 有一个错误,不能登录
            focusView.requestFocus();
        } else {
            RegisterMe(v);//   具体注册事件-----------------！！！！！
            finish();
        }
    }

    //   具体注册事件-----------------！！！！！
    private void RegisterMe(View v) {

        String username = mEmailView.getText().toString();
        String passwords = mPasswordView.getText().toString();

        //用MD5加密密码。
        passwords = md5(passwords);

        //检查数据库的现有用户。    ok!
        Cursor user = dbHelper.fetchUser(username, passwords);
        Log.e("baby",user.toString());
        if (user == null) {
            Toast.makeText(getApplicationContext(), "用户不存在",
                    Toast.LENGTH_SHORT).show();
        } else {
            startManagingCursor(user);

            //检查重复的用户名
            if (user.getCount() > 0) {
                Toast.makeText(getApplicationContext(), "用户名已经注册",
                        Toast.LENGTH_SHORT).show();
                stopManagingCursor(user);
                user.close();
                return;
            }
            stopManagingCursor(user);
            user.close();
            user = dbHelper.fetchUser(username, password);

            if (user == null) {
                Toast.makeText(getApplicationContext(), "用户不存在",
                        Toast.LENGTH_SHORT).show();
                return;
            } else {
                startManagingCursor(user);
                if (user.getCount() > 0) {
                    Toast.makeText(getApplicationContext(), "用户名已经注册",
                            Toast.LENGTH_SHORT).show();
                    stopManagingCursor(user);
                    user.close();
                    return;
                }
                stopManagingCursor(user);
                user.close();
            }
            //创建新的用户名。
            long id = dbHelper.createUser(username, passwords);
            if (id > 0) {
                String[] str = username.split("@");
                Toast.makeText(getBaseContext(),"注册成功:"+ "亲爱的" + str[0] +"用户", Toast.LENGTH_SHORT).show();
                saveLoggedInUId(id, username, mPasswordView.getText().toString());
                Intent intent = new Intent(v.getContext(), com.dream.budejie.MainActivity.class);
                startActivity(intent);
                finish();
            }
        }
    }
    //判断email格式是否正确 正则表达式
    public boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    }
    //----------------------------------------------------------------

    private void saveLoggedInUId(long id, String username, String password) {
        SharedPreferences settings = getSharedPreferences(LoginsActivity.MY_PREFS, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putLong("uid", id);
        editor.putString("username", username);
        editor.putString("password", password);
        editor.commit();
    }
//    原始的邮箱验证方法
//    private boolean isEmailValid(String email) {
//        //TODO: Replace this with your own logic
//        return email.contains("@");
//    }
    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 6;
    }

    /**
     * 用MD5散列密码。
     * @param s
     * @return
     */
    private String md5(String s) {
        try {

            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();


            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++)
                hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            return s;
        }
    }
}
