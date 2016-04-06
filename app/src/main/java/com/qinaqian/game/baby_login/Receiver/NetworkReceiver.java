package com.qinaqian.game.baby_login.Receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

/**
 * Created by kuku on 2015/12/24.
 *           WIFi  服务监听  切勿忘在配置文件中加服务
 */
public class NetworkReceiver extends BroadcastReceiver {
    private ConnectivityManager manager;

    @Override
    public void onReceive(Context context, Intent intent) {
        // 链接管理器
        manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        // 得到联网信息
        NetworkInfo info = manager.getActiveNetworkInfo();
        if (info != null && info.isAvailable()) {// isAvailable()   可用的，可行的！
            Toast.makeText(context, "WiFi-已经可以使用,祝君上网愉快~", Toast.LENGTH_SHORT).show();
        }
    }
}
