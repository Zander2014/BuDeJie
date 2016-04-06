package com.dream.budejie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.dream.budejie.adapter.FreeBackAdapter;
import com.dream.budejie.entity.FreeBean;

import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class FreeBackActivity extends AppCompatActivity implements View.OnClickListener {
   private static final List<FreeBean> list = new ArrayList<FreeBean>();
    private TextView text;
    private ListView listView;
    private List<FreeBean> db_list;
    private EditText edittext;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_back);

        initView();
    }

    private void initView() {
        DisplayMetrics dm = new DisplayMetrics();
        //获取屏幕信息
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenWidth = dm.widthPixels;

        text = (TextView) findViewById(R.id.activity_freeback_textivew);
        listView = (ListView) findViewById(R.id.activity_freeback_listview);

        edittext = (EditText) findViewById(R.id.activity_freeback_edit);
        btn = (Button) findViewById(R.id.activity_freeback_btn);
        text.setWidth((int) (screenWidth * 0.6));

        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String text = edittext.getText().toString();
        String time = getTime();
        if (!TextUtils.isEmpty(edittext.getText())) {
            FreeBean freeBean = new FreeBean();
            freeBean.setText(text);
            freeBean.setTime(time);

            list.add(freeBean);
            listView.setAdapter(new FreeBackAdapter(this, list));

        } else {
            Toast.makeText(FreeBackActivity.this, "忘记填写意见了", Toast.LENGTH_SHORT).show();
        }

    }

    private String getTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());
        return formatter.format(curDate);
    }
}
