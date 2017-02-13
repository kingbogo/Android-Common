package com.kingbogo.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;

import java.lang.reflect.Field;

/**
 * @author kingbo
 */
public class NetUtil
{

    public static boolean isConnected(Context context)
    {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info != null && info.isConnected())
        {
            if (info.getState() == NetworkInfo.State.CONNECTED)
            {
                return true;
            }
        }
        return false;
    }

//    public static boolean isWiFi(Context cxt)
//    {
//        ConnectivityManager cm = (ConnectivityManager) cxt.getSystemService(Context.CONNECTIVITY_SERVICE);
//        // wifi的状态：ConnectivityManager.TYPE_WIFI
//        // 3G的状态：ConnectivityManager.TYPE_MOBILE
//        return cm.getActiveNetworkInfo().getType() == ConnectivityManager.TYPE_WIFI;
//    }

    public static boolean isWifi(Context context)
    {
        boolean result = false;

        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE))
                .getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isAvailable() && activeNetworkInfo.isConnected())
        {
            int type = activeNetworkInfo.getType();
            if (type == ConnectivityManager.TYPE_WIFI)
            {
                result = true;
            }
        }

        return result;
    }

//    public static void openNetSetting(Activity act)
//    {
//        Intent intent = new Intent();
//        ComponentName cm = new ComponentName("com.android.settings", "com.android.settings.WirelessSettings");
//        intent.setComponent(cm);
//        intent.setAction("android.intent.action.VIEW");
//        act.startActivityForResult(intent, 0);
//    }

    private static final String ANDROID_PROVIDER_SETTINGS = "android.provider.Settings";

    public static void openNetSetting(Activity act)
    {
        if (Build.VERSION.SDK_INT > 10)
            openSetting(act, "ACTION_WIFI_SETTINGS");
        else
            openSetting(act, "ACTION_WIRELESS_SETTINGS");
    }

    private static void openSetting(Activity act, String ActionName)
    {
        try
        {
            Class<?> settingsClass = Class.forName(ANDROID_PROVIDER_SETTINGS);
            Field actionWifiSettingsField = settingsClass.getDeclaredField(ActionName);
            Intent settingIntent = new Intent(actionWifiSettingsField.get(null).toString());
            settingIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            act.startActivity(settingIntent);
        }
        catch (Throwable e)
        {
            LogUtil.e(e);
        }
    }
}
