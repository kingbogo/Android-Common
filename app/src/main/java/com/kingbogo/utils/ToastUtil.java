package com.kingbogo.utils;

import android.content.Context;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


/**
 * <pre>
 * ToastUtil (可统一定义Toast样式)
 * </pre>
 *
 * @author kingbo
 */
public class ToastUtil
{
    private static boolean IS_SHOW_CUSTOMTOAST = false;
    private static boolean IS_CENTER = true;

    private static Toast mToast;

    private static Handler mHandler = new Handler();
    private static Runnable r = new Runnable()
    {
        public void run()
        {
            mToast.cancel();
            mToast = null;//toast隐藏后，将其置为null
        }
    };

    public static void init(boolean isShowCustom, boolean isCenter)
    {
        IS_SHOW_CUSTOMTOAST = isShowCustom;
        IS_CENTER = isCenter;
    }

    public static void cancel()
    {
        mHandler.post(r);
    }

    public static void showShortText(Context context, int resId)
    {
        showCustomToast(context, context.getString(resId), Toast.LENGTH_SHORT);
    }

    public static void showShortText(Context context, CharSequence text)
    {
        showCustomToast(context, text, Toast.LENGTH_SHORT);
    }

    public static void showLongText(Context context, int resId)
    {
        showCustomToast(context, context.getString(resId), Toast.LENGTH_LONG);
    }

    public static void showLongText(Context context, CharSequence text)
    {
        showCustomToast(context, text, Toast.LENGTH_LONG);
    }

    private static void showCustomToast(Context context, CharSequence text, int duration)
    {
        if (text != null && text.length() > 0)
        {
            mHandler.removeCallbacks(r);

            if (mToast == null)
            {
                if (IS_SHOW_CUSTOMTOAST)
                {
                    mToast = new Toast(context);
                    mToast.setDuration(duration);
                    View rootView = LinearLayout.inflate(context, R.layout.toast, null);
                    TextView msgTxt = (TextView) rootView.findViewById(R.id.toast_txt);
                    msgTxt.setText(text);
                    mToast.setView(rootView);
                }
                else
                {
                    mToast = Toast.makeText(context, text, duration);
                }

                if (IS_CENTER)
                {
                    mToast.setGravity(Gravity.CENTER, 0, 0);
                }
            }

            if (duration == Toast.LENGTH_SHORT)
            {
                mHandler.postDelayed(r, 1800);
            }
            else
            {
                mHandler.postDelayed(r, 3500);
            }

            mToast.show();

        }
        else
        {
            LogUtil.e("Toast内容为空。。。");
        }
    }

}
