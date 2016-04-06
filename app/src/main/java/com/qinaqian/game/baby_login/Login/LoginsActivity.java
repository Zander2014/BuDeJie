package com.qinaqian.game.baby_login.Login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dream.budejie.R;
import com.qinaqian.game.baby_login.database.DatabaseAdapter;
import com.umeng.analytics.MobclickAgent;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 一个登录屏幕,提供通过电子邮件/密码登录。
 */
public class LoginsActivity extends AppCompatActivity implements OnClickListener {

    // UI的引用。
    private AutoCompleteTextView mEmailView;  //  账号
    private EditText mPasswordView;         // 密码
    private View mProgressView;
    private View mLoginFormView;
    private Button mEmailSignInButton;

    // 手动加UI
    private String email;
    private String password;
    private ImageView left;
    private ImageView right;
    private CheckBox rememberDetails;
    private CheckBox Autologin;
    private CheckBox showpwd;
    private TextView register;
    private ImageView mImageView_sj;

    // Register 布局 UI  数据库
    public static final String MY_PREFS = "SharedPreferences";// 轻量级存储
    private DatabaseAdapter dbHelper;
    private TextView forgetPwd;
    private ImageView qq_login;
    private ImageView wx_login;
    private ImageView wb_login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  存储 机制  提示      SharedPreferences----------------------------------
        SharedPreferences mySharedPreferences = getSharedPreferences(MY_PREFS, 0);
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putLong("uid", 0);
        editor.commit();
      //   数据库开启  在加载布局之前打开
        dbHelper = new DatabaseAdapter(this);
        dbHelper.open();

        setContentView(R.layout.activity_logins);  //  加载布局-------------------
        // ActionBar 回退
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);// 左上角回退键

        // 初始化登录页面
        initfind();
        initListenerData(); // 监听事件
        imageChange();// 图片变化
        checkBox(); // 三个checkBox 事件
        check(); // 登录是否可点击
//        forget2register();// 忘记密码和注册事件
        initButtonEvent();// Clear 账号密码事件
        initRemerber();  //  记住偏好功能
        initSanfangLogin();// 三方登录事件


        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
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

    private void initSanfangLogin() {
        register.setOnClickListener(this);
        forgetPwd.setOnClickListener(this);
        qq_login.setOnClickListener(this);
        wx_login.setOnClickListener(this);
        wb_login.setOnClickListener(this);
    }

    //处理记住偏好功能
    private void initRemerber() {
        SharedPreferences prefs = getSharedPreferences(MY_PREFS, 0);
        String thisUsername = prefs.getString("username", "");
        String thisPassword = prefs.getString("password", "");
        boolean thisRemember = prefs.getBoolean("remember", false);
        boolean thisAutologin =prefs.getBoolean("Autologin",false);
        // 如果上次选了记住密码，那进入登录页面也自动勾选记住密码，并填写上用户名字和密码
        if (thisRemember) {
            mEmailView.setText(thisUsername);
            mPasswordView.setText(thisPassword);
            rememberDetails.setChecked(thisRemember);
        }
        //如果上次选了自动登录，那进入登录页面也自动勾选自动登录
        if (thisAutologin) {
            Autologin.setChecked(true);
            login();//  如果上次勾选，这次直接验证密码登录
        }
    }

    /**
     * 返回 /回退键
     *
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

    private void initButtonEvent() {
        mImageView_sj.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mEmailView.setText("");
                mPasswordView.setText("");
            }
        });
    }

    private void forget2register() {
        register.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });
        forgetPwd.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(getBaseContext(),forgetActivity.class);
                startActivity(intent);
            }
        });
    }

    //    三个checkBox 事件
    private void checkBox() {

        //创建侦听器【记住密码】复选框。
        rememberDetails.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                RememberMe();
            }
        });

        //  checkBox显示密码
        Log.d("TAG", "--------------------checkbox3方法");
        showpwd.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (showpwd.isChecked()) {
                    //   可见
                    mPasswordView.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    //  图片显示是睁开眼
                    left.setImageResource(R.mipmap.ic_22_hide);
                    right.setImageResource(R.mipmap.ic_33_hide);
                } else {// 隐藏
                    mPasswordView.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    //  图片显示是闭眼
                    left.setImageResource(R.mipmap.ic_22);
                    right.setImageResource(R.mipmap.ic_33);
                }
            }
        });

    }

    /**
     * 处理记住密码选项。
     */
    private void RememberMe() {
        boolean thisRemember = rememberDetails.isChecked();
        boolean thisAutologin = Autologin.isChecked();
        SharedPreferences prefs = getSharedPreferences(MY_PREFS, 0);// 登录记住机制
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("remember", thisRemember);
        editor.putBoolean("Autologin",thisAutologin);
        editor.commit();
    }

    private void initListenerData() {
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });
//      登录监听器
        mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setEnabled(false);// 禁用，不能提交
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });
    }

    // 寻找UI控件
    private void initfind() {
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
//        mEmailView.setInputType(InputType.TYPE_CLASS_NUMBER); //调用数字键盘
        mPasswordView = (EditText) findViewById(R.id.password);

        rememberDetails = ((CheckBox) findViewById(R.id.check_keep));
        Autologin = ((CheckBox) findViewById(R.id.check_Autologin));
        showpwd = ((CheckBox) findViewById(R.id.check_showPwd));
        register = ((TextView) findViewById(R.id.txt_register));
        mImageView_sj = ((ImageView) findViewById(R.id.image_sj));
        forgetPwd = ((TextView) findViewById(R.id.txt_forgot));

        /**
         * 三方登录UI
         */
        qq_login = ((ImageView) findViewById(R.id.qq_login));
        wx_login = ((ImageView) findViewById(R.id.wx_login));
        wb_login = ((ImageView) findViewById(R.id.wb_login));

    }

    // 图片变化
    private void imageChange() {
        left = (ImageView) findViewById(R.id.image_zz);
        right = (ImageView) findViewById(R.id.image_yy);

        mPasswordView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if (hasFocus && showpwd.isChecked()) {
                    left.setImageResource(R.mipmap.ic_22_hide);
                    right.setImageResource(R.mipmap.ic_33_hide);
                }
            }
        });


        mEmailView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    left.setImageResource(R.mipmap.ic_22);
                    right.setImageResource(R.mipmap.ic_33);
                }
            }
        });
    }

    // 登录可用不可用
    private void check() {

        //     邮件监听
        mEmailView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0 && mEmailView.getText().toString().length() > 0 && mPasswordView.getText().toString().length() > 0) {
                    mEmailSignInButton.setEnabled(true);
                } else {
                    mEmailSignInButton.setEnabled(false);
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
                if (s.length() > 0 && mPasswordView.getText().toString().length() > 0 && mEmailView.getText().toString().length() > 0) {
                    mEmailSignInButton.setEnabled(true);
                } else {
                    mEmailSignInButton.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    /**
     * 试图登录或注册帐户指定的登录表单。
     * 如果有形式错误(无效的电子邮件,缺少字段等),
     * 错误了,没有实际的登录尝试。
     */
    private void attemptLogin() {


//        / /设置错误。
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
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();  // 有一个错误,不要登录
        } else {    // 执行用户的登录尝试

            login();//  验证密码 并执行登录
        }
    }

    //  验证密码 并执行登录
    private void login() {
        //获得用户名和密码
        String thisUsername = mEmailView.getText().toString();
        String thisPassword = mPasswordView.getText().toString();

        //指定的密码散列
        thisPassword = md5(thisPassword);

        // 检查现有的用户名和密码数据库
        Cursor theUser = dbHelper.fetchUser(thisUsername, thisPassword);
        if (theUser != null) {
            startManagingCursor(theUser);
            if (theUser.getCount() > 0) {
                saveLoggedInUId(theUser.getLong(theUser.getColumnIndex(DatabaseAdapter.COL_ID)), thisUsername, mPasswordView.getText().toString());
                stopManagingCursor(theUser);
                theUser.close(); //  一定要关闭数据库

                Intent intent = new Intent(LoginsActivity.this, com.dream.budejie.MainActivity.class);
                startActivity(intent);
                Toast.makeText(getBaseContext(),"登录成功",Toast.LENGTH_SHORT).show();
                finish();//  合代码的时候一定要注意打开，并要进一步处理
            }
            //如果没有匹配返回适当的消息
            else {
                Toast.makeText(getApplicationContext(),
                        "账号或者密码不对",
                        Toast.LENGTH_SHORT).show();
                saveLoggedInUId(0, "", "");
            }
            stopManagingCursor(theUser);
            theUser.close();
        }

    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 5;
    }
   //  保存登录偏好  【记住密码，自动登录】
     private void saveLoggedInUId(long id, String username, String password) {
        SharedPreferences settings = getSharedPreferences(MY_PREFS, 0);
        SharedPreferences.Editor myEditor = settings.edit();
        myEditor.putLong("uid", id);
        myEditor.putString("username", username);
        myEditor.putString("password", password);
        boolean rememberThis = rememberDetails.isChecked();
        myEditor.putBoolean("rememberThis", rememberThis);
        boolean AutologinThis =Autologin.isChecked();
        myEditor.putBoolean("Autologin",AutologinThis);
        myEditor.commit();
    }

    /**
     * 处理加密的密码。
     *
     * @param s The password.
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


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.qq_login:

            break;
            case R.id.wx_login:
 Toast.makeText(getBaseContext(),"微信快速登录尚未开启！",Toast.LENGTH_SHORT).show();
            break;
            case R.id.wb_login:

            break;
            case R.id.txt_register:
                Intent intent = new Intent(getBaseContext(), RegisterActivity.class);
                startActivity(intent);
            break;
            case R.id.txt_forgot:
                Intent intent2  = new Intent(getBaseContext(),forgetActivity.class);
                startActivity(intent2);
            break;
        }
    }
}

