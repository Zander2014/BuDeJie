package com.qinaqian.game.baby_login.activitys;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Created by kuku on 2015/12/23.
 */
public class NoSystemApk {
    public static List<PackageInfo> getPackageInfo(Context context) {
        List<PackageInfo> pgs = context.getPackageManager()
                .getInstalledPackages(0);
        List<PackageInfo> list = new ArrayList<PackageInfo>();
        for (int i = 0; i < pgs.size(); i++) {
            PackageInfo info = pgs.get(i);
            if ((info.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
                list.add(info);
            }
        }
        return list;
    }

    public static String getAppInfo(Context context) {
        List<PackageInfo> pgs = context.getPackageManager()
                .getInstalledPackages(0);
//		NLog.i("DownloadLinkActivity", pgs.size() + "");
        // List<PackageInfo> list = new ArrayList<PackageInfo>();
        JSONArray array = new JSONArray();
        for (int i = 0; i < pgs.size(); i++) {
            PackageInfo info = pgs.get(i);
            if ((info.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("appname",
                        info.applicationInfo.loadLabel(
                                context.getPackageManager()).toString());
                map.put("version", info.versionCode + "");
                JSONObject json;
                try {
                    json = new JSONObject(map.toString());
                    array.put(json);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }
//		NLog.i("wdd", array.toString());
        return array.toString();
    }

    public static String getAppName(Context context) {
        List<PackageInfo> pgs = context.getPackageManager()
                .getInstalledPackages(0);
//		NLog.i("DownloadLinkActivity", pgs.size() + "");
        // List<PackageInfo> list = new ArrayList<PackageInfo>();
        JSONArray array = new JSONArray();
        for (int i = 0; i < pgs.size(); i++) {
            PackageInfo info = pgs.get(i);
            if ((info.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("appname",
                        info.applicationInfo.loadLabel(
                                context.getPackageManager()).toString());
                JSONObject json;
                try {
                    json = new JSONObject(map.toString());
                    array.put(json);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }
//		NLog.i("wdd", array.toString());
        return array.toString();
    }
    //判断是否有指定的包
    public	static  boolean isHasPkg(Context context, String pkg){
        List<PackageInfo> list=getPackageInfo(context);
        for (int i=0;i<list.size();i++){
            if (list.get(i).packageName.equals(pkg)){
                return true;
            }
        }
        return  false;
    }
    //获取全部包名
    public static List<String> getPkgs(Context context){
        List<PackageInfo> list=getPackageInfo(context);
        List<String> listPkg=new ArrayList<>();
        for (int i=0;i<list.size();i++){
            listPkg.add(list.get(i).packageName);
        }
        return listPkg;
    }
}
