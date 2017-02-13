package com.kingbogo.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.io.DataOutputStream;

public class AppUtil
{

    public static String getAppVersionName(Context context, String packageName)
    {
        String appVersion = null;
        try
        {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
            appVersion = packageInfo.versionName;
        }
        catch (PackageManager.NameNotFoundException e)
        {
            e.printStackTrace();
        }
        return appVersion;
    }

    public static int getAppVersionCode(Context context, String packageName)
    {
        int appVersionCode = 0;
        try
        {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
            appVersionCode = packageInfo.versionCode;
        }
        catch (PackageManager.NameNotFoundException e)
        {
            e.printStackTrace();
        }
        return appVersionCode;
    }

    public static boolean getRootPermission(Context context)
    {
        String packageCodePath = context.getPackageCodePath();
        Process process = null;
        DataOutputStream os = null;
        try
        {
            String cmd = "chmod 777 " + packageCodePath;
            process = Runtime.getRuntime().exec("su");
            os = new DataOutputStream(process.getOutputStream());
            os.writeBytes(cmd + "\n");
            os.writeBytes("exit\n");
            os.flush();
            process.waitFor();
        }
        catch (Exception e)
        {
            return false;
        }
        finally
        {
            try
            {
                if (os != null)
                {
                    os.close();
                }
                process.destroy();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return true;
    }

    /**
     * app是否已安装
     */
    public static boolean appIsInstalled(Context context, String packageName)
    {
        PackageInfo packageInfo;
        try
        {
            packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
        }
        catch (PackageManager.NameNotFoundException e)
        {
            packageInfo = null;
        }

        return packageInfo != null;
    }
}
