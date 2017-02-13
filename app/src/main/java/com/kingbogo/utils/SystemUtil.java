package com.kingbogo.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.Display;
import android.view.Gravity;
import android.view.WindowManager;

import java.util.List;

/**
 * 系统功能调用工具类
 */
public class SystemUtil
{

    private SystemUtil()
    {
        throw new AssertionError();
    }

    public static void sendSMS(Context cxt, String smsBody)
    {
        Uri smsToUri = Uri.parse("smsto:");
        Intent intent = new Intent(Intent.ACTION_SENDTO, smsToUri);
        intent.putExtra("sms_body", smsBody);
        cxt.startActivity(intent);
    }

    public static void forwardToDial(Context context, String phoneNumber)
    {
        if (context != null && !TextUtils.isEmpty(phoneNumber))
        {
            context.startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber)));
        }
    }

    public static void sendMail(Context mContext, String mailID)
    {
        Uri uri = Uri.parse("mailto:" + mailID);
        mContext.startActivity(new Intent(Intent.ACTION_SENDTO, uri));
    }

    public static boolean isBackground(Context context)
    {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses)
        {
            if (appProcess.processName.equals(context.getPackageName()))
            {
                return appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_BACKGROUND;
            }
        }
        return false;
    }

    public static boolean isRunningOnEmulator()
    {
        return Build.BRAND.contains("generic")
                || Build.DEVICE.contains("generic")
                || Build.PRODUCT.contains("sdk")
                || Build.HARDWARE.contains("goldfish")
                || Build.MANUFACTURER.contains("Genymotion")
                || Build.PRODUCT.contains("vbox86p")
                || Build.DEVICE.contains("vbox86p")
                || Build.HARDWARE.contains("vbox86");
    }

    public static void goHome(Context context)
    {
        Intent mHomeIntent = new Intent(Intent.ACTION_MAIN);
        mHomeIntent.addCategory(Intent.CATEGORY_HOME);
        mHomeIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
        context.startActivity(mHomeIntent);
    }

    public static void createDeskShortCut(Context cxt, String shortCutName, int icon, Class<?> cls)
    {
        Intent shortcutIntent = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
        shortcutIntent.putExtra("duplicate", false);
        shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, shortCutName);
        Parcelable ico = Intent.ShortcutIconResource.fromContext(cxt.getApplicationContext(), icon);
        shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, ico);
        Intent intent = new Intent(cxt, cls);
        intent.setAction("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, intent);
        cxt.sendBroadcast(shortcutIntent);
    }

    public static void createShortcut(Context ctx, String shortCutName, int iconId, Intent presentIntent)
    {
        Intent shortcutIntent = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
        shortcutIntent.putExtra("duplicate", false);
        shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, shortCutName);
        shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, Intent.ShortcutIconResource.fromContext(ctx, iconId));
        shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, presentIntent);
        ctx.sendBroadcast(shortcutIntent);
    }

    public static void shareText(Context ctx, String title, String text)
    {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        //intent.putExtra(Intent.EXTRA_SUBJECT, title);
        intent.putExtra(Intent.EXTRA_TEXT, text);
        ctx.startActivity(Intent.createChooser(intent, title));
       /* List<ResolveInfo> ris = getShareTargets(ctx);
        if (ris != null && ris.size() > 0) {
            ctx.startActivity(Intent.createChooser(intent, title));
        }*/
    }

//    public static void shareFile(Context context, String title, String filePath) {
//        Intent intent = new Intent(Intent.ACTION_SEND);
//        Uri uri = Uri.parse("file://" + filePath);
//        intent.setType("*/*");
//        intent.putExtra(Intent.EXTRA_STREAM, uri);
//        context.startActivity(Intent.createChooser(intent, title));
//    }

//    public static void showSoftInputMethod(Context context, EditText editText)
//    {
//        if (context != null && editText != null)
//        {
//            editText.requestFocus();
//            InputMethodManager inputManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
//            inputManager.showSoftInput(editText, 0);
//        }
//    }


//    public static void closeSoftInputMethod(Context context, EditText editText)
//    {
//        if (context != null && editText != null)
//        {
//            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
//            if (imm != null)
//            {
//                imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
//            }
//        }
//    }
//
//    public static void showSoftInput(Context context)
//    {
//        InputMethodManager inputMethodManager = (InputMethodManager) context
//                .getSystemService(Context.INPUT_METHOD_SERVICE);
//        inputMethodManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
//    }
//
//
//    public static void closeSoftInput(Context context)
//    {
//        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
//        if (inputMethodManager != null && ((Activity) context).getCurrentFocus() != null)
//        {
//            inputMethodManager.hideSoftInputFromWindow(((Activity) context).getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
//        }
//    }

    public static void quit()
    {
        LogUtil.d("#### quit()...");

        android.os.SystemClock.sleep(100);
        android.os.Process.killProcess(android.os.Process.myPid());
    }


    /**
     * <pre>
     * 设置activity的宽、高 比例
     * </pre>
     *
     * @param activity
     */
    @SuppressWarnings("deprecation")
    public static void setDialogActiviWidthPercent(Activity activity)
    {
        WindowManager m = activity.getWindowManager();
        Display d = m.getDefaultDisplay();
        WindowManager.LayoutParams layoutParams = activity.getWindow().getAttributes();
        layoutParams.width = (int) (d.getWidth() * 0.9);
        activity.getWindow().setAttributes(layoutParams);
        activity.getWindow().setGravity(Gravity.CENTER);
    }


    /**
     * 打开浏览器
     */
    public static void openUrlByBrowser(Activity context, String url)
    {
        if (!CheckUtil.isEmpty(url))
        {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            intent.setData(Uri.parse(url));
            context.startActivity(intent);
        }
        else
        {
            LogUtil.w("url is null....");
        }
    }


}
