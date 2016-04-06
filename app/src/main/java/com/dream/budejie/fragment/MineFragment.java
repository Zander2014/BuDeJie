package com.dream.budejie.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.dream.budejie.Detail1Activity;
import com.dream.budejie.DetailActivity;
import com.dream.budejie.FreeBackActivity;
import com.dream.budejie.R;
import com.dream.budejie.adapter.MineAdapter;
import com.dream.budejie.entity.MineBean;
import com.qinaqian.game.baby_login.Login.LoginsActivity;
import com.qinaqian.game.baby_login.activitys.NativeActivity;
import com.qinaqian.game.baby_login.activitys.SettingActivity;

import org.xutils.DbManager;
import org.xutils.common.Callback;
import org.xutils.ex.DbException;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends Fragment implements View.OnClickListener {

    private RelativeLayout login, download, bendi;
    private GridView gridView;
    private View view;
    private DbManager db;
    DbManager.DaoConfig config = new DbManager.DaoConfig()
            .setDbName("test")//数据库名称
            .setDbDir(new File("/sdcard"))//数据库路径
            .setDbVersion(1)//版本号
                    // .setAllowTransaction(true)//数据库事物提交
            .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
                @Override
                public void onUpgrade(DbManager db, int oldVersion, int newVersion) {

                }
            });
    private List<MineBean> all;
    private RelativeLayout apply;
    private ImageView IconPic;
    private TextView Setting;
    private SharedPreferences shareSet;
    private TextView zhanghao;
    private Boolean thisUsername;


    public MineFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = x.getDb(config);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mine, container, false);

        zhanghao = ((TextView) view.findViewById(R.id.zhanghao));
        IconPic = ((ImageView) view.findViewById(R.id.imageView4));
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        shareSet = getActivity()
                .getSharedPreferences("setting", Activity.MODE_PRIVATE);
        initView();
        initData();

        //添加详情点击时间
        gridView.setOnItemClickListener(new ItemClickListener());

    }

    public void initData() {
        try {
            all = db.selector(MineBean.class).findAll();
        } catch (DbException e) {
            e.printStackTrace();
        }
        if (all != null) {
            gridView.setAdapter(new MineAdapter(getActivity(), all));
        } else {
            RequestParams params = new RequestParams
                    ("http://api.budejie.com/api/api_open.php?" +
                            "market=tencentyingyongbao&udid=352284041097126&a=square&" +
                            "appname=baisibudejie&c=topic&os=4.4.2&client=android&visiting=&" +
                            "mac=00%3A1C%3A25%3AA0%3A30%3A20&ver=6.2.4");
            x.http().request(HttpMethod.GET, params, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    if (result != null) {
                        JSONArray data = JSON.parseObject(result).getJSONArray
                                ("square_list");
                        List<MineBean> list = JSON.parseArray(data.toJSONString(),
                                MineBean.class);
                        for (MineBean bean : list) {
                            try {
                                db.save(bean);
                            } catch (DbException e) {
                                e.printStackTrace();
                            }

                        }
                        MineAdapter adapter = new MineAdapter(getActivity(), list);
                        gridView.setAdapter(adapter);
                    }
                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {
                    ex.printStackTrace();
                }

                @Override
                public void onCancelled(Callback.CancelledException cex) {
                    cex.printStackTrace();
                }

                @Override
                public void onFinished() {

                }
            });
        }
    }

    private void initView() {
        gridView = (GridView) view.findViewById(R.id.gridview);
        login = (RelativeLayout) view.findViewById(R.id.mine_rela1);
        download = (RelativeLayout) view.findViewById(R.id.mine_rela2);
        bendi = (RelativeLayout) view.findViewById(R.id.mine_rela3);
        apply = ((RelativeLayout) view.findViewById(R.id.mine_rela4));

        Setting = (TextView) view.findViewById(R.id.item_text1);
        login.setOnClickListener(this);
        download.setOnClickListener(this);
        bendi.setOnClickListener(this);
        apply.setOnClickListener(this);
        Setting.setOnClickListener(this);
    }

    private AlertDialog dialog;

    @Override
    public void onClick(View view) {
        Intent it;
        thisUsername = shareSet.getBoolean("push", true);
        switch (view.getId()) {
            case R.id.mine_rela1:
                if (thisUsername) {
                    // 创建退出对话框
                    dialog = new AlertDialog.Builder(getActivity()).create();
                    dialog.setMessage("确定不是手滑了么?");
                    dialog.setButton(AlertDialog.BUTTON_POSITIVE, "注销", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            zhanghao.setText("登录");
                            IconPic.setImageResource(R.mipmap.wdl);// 图片
                            Toast.makeText(getActivity(), "当前账号已经退出", Toast.LENGTH_SHORT).show();
                            shareSet.edit().putBoolean("push", false).apply();
                        }
                    });
                    dialog.setButton(AlertDialog.BUTTON_NEGATIVE, "手滑了", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                } else {
                    it = new Intent(getActivity(), LoginsActivity.class);
                    startActivity(it);
//
//                    zhanghao.setText("退出账号");
//                    IconPic.setImageResource(R.mipmap.abc);// 图片
                    shareSet.edit().putBoolean("push", true).apply();
                }
                break;
            case R.id.mine_rela2:
                Log.d("------MineFragment", "----mine_rela2");
                Toast.makeText(getContext(), "已经是最新数据,请稍后缓存", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mine_rela3:
                Log.d("------MineFragment", "----mine_rela3");
                it = new Intent(getActivity(), com.dream.budejie.BendiActivity.class);
                startActivity(it);
                break;
            case R.id.mine_rela4:
                it = new Intent(getActivity(), NativeActivity.class);
                startActivity(it);
                break;
            case R.id.item_text1:
                it = new Intent(getActivity(), SettingActivity.class);
                startActivity(it);
                break;
        }
    }

    //当AdapterView被单击(触摸屏或者键盘)，则返回的Item单击事件
    class ItemClickListener implements AdapterView.OnItemClickListener {
        private Intent it;

        public void onItemClick(AdapterView<?> arg0,//The AdapterView where the click happened
                                View arg1,//The view within the AdapterView that was clicked
                                int arg2,//The position of the view in the adapter
                                long arg3//The row id of the item that was clicked
        ) {
            String str = all.get(arg2).getTitle().toString();
            String url = all.get(arg2).getUrl() != null ? all.get(arg2).getUrl().toString() : "";
            String pic = all.get(arg2).getPic().toString();
            if (str.equals("审贴") || str.equals("我的收藏") || str.equals("搜索")) {
                it = new Intent(getActivity(), LoginsActivity.class);
                startActivity(it);
            } else if (str.equals("排行榜")) {
//                //listView
//                it = new Intent(getActivity(), DetailActivity.class);
//                it.putExtra("name", str);
//                startActivity(it);
            } else if (str.equals("随机穿越")) {
                //listview
                it = new Intent(getActivity(), DetailActivity.class);
                it.putExtra("name", str);
                startActivity(it);
            } else if (str.equals("意见反馈")) {
                it = new Intent(getActivity(), FreeBackActivity.class);
                it.putExtra("name", str);
                startActivity(it);
            } else {
                it = new Intent(getActivity(), Detail1Activity.class);
                it.putExtra("url", url);
                it.putExtra("name", str);
                it.putExtra("pic", pic);
                startActivity(it);
            }
        }
    }
}