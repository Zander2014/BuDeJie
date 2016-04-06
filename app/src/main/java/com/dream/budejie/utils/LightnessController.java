package com.dream.budejie.utils;

import android.app.Activity;
import android.content.ContentResolver;
import android.provider.Settings;
import android.view.WindowManager;
import android.widget.Toast;

/**
 * Created by jerry on 2015/12/26.
 */
public class LightnessController {
    // 判断是否开启了自动亮度调节
    public static boolean isAutoBrightness(Activity act){
        boolean automicBrightness = false;
        ContentResolver aContentResolver = act.getContentResolver();
        try {
            automicBrightness= Settings.System.getInt(aContentResolver,
                    Settings.System.SCREEN_BRIGHTNESS_MODE)== Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC;

        } catch (Settings.SettingNotFoundException e) {
            Toast.makeText(act, "无法获取亮度", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        return automicBrightness;
    }
    // 改变亮度
    public static void setLightness(Activity act, int value){
        Settings.System.putInt(act.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS,value);
        WindowManager.LayoutParams lp = act.getWindow().getAttributes();
        lp.screenBrightness = (value <= 0 ? 1 : value) / 255f;
        act.getWindow().setAttributes(lp);
    }
    // 获取亮度
    public static int getLightness(Activity act) {
        return Settings.System.getInt(act.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, -1);
    }

    // 停止自动亮度调节
    public static void stopAutoBrightness(Activity activity) {
        Settings.System.putInt(activity.getContentResolver(),
                Settings.System.SCREEN_BRIGHTNESS_MODE,
                Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
    }

    // 开启亮度自动调节
    public static void startAutoBrightness(Activity activity) {
        Settings.System.putInt(activity.getContentResolver(),
                Settings.System.SCREEN_BRIGHTNESS_MODE,
                Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC);
    }
}

