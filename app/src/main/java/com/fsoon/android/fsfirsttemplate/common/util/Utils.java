package com.fsoon.android.fsfirsttemplate.common.util;

import static android.content.Context.WINDOW_SERVICE;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.Application;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.fsoon.android.fsfirsttemplate.R;
import com.google.android.material.tabs.TabLayout;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

public class Utils {

    public static final int MSG_NONE = 0;
    public static final int MSG_OK = 11;
    public static final int MSG_CANCEL = 12;
    public static final int MSG_NEXT = 13;

    /**
     * Application 종료
     *
     * @param activity
     */
    public static void quitApp(Activity activity) {
        activity.moveTaskToBack(true);
        ActivityCompat.finishAffinity(activity);
        //android.os.Process.killProcess(android.os.Process.myPid());
    }

    public static boolean isEmpty(Object s) {
        if (s == null) {
            return true;
        }

        if ((s instanceof String) && (((String) s).trim().length() == 0)) {
            return true;
        }

        if ((s instanceof String) && ("null".equals(s) || "<null>".equals(s))) {
            return true;
        }

        if (s instanceof Map) {
            return ((Map<?, ?>) s).isEmpty();
        }

        if (s instanceof List) {
            return ((List<?>) s).isEmpty();
        }

        if (s instanceof Object[]) {
            return (((Object[]) s).length == 0);
        }

        return false;
    }

    public static void DialogShowOneBt(Activity context, String str_msg, String str_ok, final DialogInterface.OnClickListener listener) {
        DialogShow(true, context, null, str_msg, str_ok, "", listener);
    }

    public static void DialogShowOneBt(Activity context, String str_title, String str_msg, String str_ok, final DialogInterface.OnClickListener listener) {
        DialogShow(true, context, str_title, str_msg, str_ok, "", listener);
    }

    public static void DialogShow(Activity context, String str_msg, String str_ok, String str_cancel, final DialogInterface.OnClickListener listener) {
        DialogShow(false, context, null, str_msg, str_ok, str_cancel, listener);
    }

    public static void DialogShow(Activity context, String str_title, String str_msg, String str_ok, String str_cancel, final DialogInterface.OnClickListener listener) {
        DialogShow(false, context, str_title, str_msg, str_ok, str_cancel, listener);
    }

    /*public static void DialogShowTextColor(Activity context, String str_title, String str_msg, String coloredText, String color, String str_ok, String str_cancel, final DialogInterface.OnClickListener listener) {
        DialogShowTextColor(true, context, str_title, str_msg, coloredText, color, str_ok, str_cancel, listener);
    }*/

    public static void DialogShow(final boolean oneButton, final Activity context, String str_title, final String str_msg,
                                  final String str_ok, final String str_cancel, final DialogInterface.OnClickListener listener) {
        if (context.isFinishing()) {
            return;
        }
        String title = setDialogTitle(context, str_title);
        final AlertDialog.Builder alertBuilder = new AlertDialog.Builder(new ContextThemeWrapper(context, androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog_Alert));

        // 커스텀 타이틀 2줄 이상 표시 위해
        View titleTextView = View.inflate(context, R.layout.dialog_title, null);
        TextView textview_title = (TextView) titleTextView.findViewById(R.id.textview_title);
        textview_title.setText(title);

        alertBuilder.setCustomTitle(titleTextView);
//        alertBuilder.setTitle(title);

        alertBuilder.setMessage(str_msg);
        alertBuilder.setCancelable(false);
        alertBuilder.setPositiveButton(str_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (listener != null)
                    listener.onClick(dialog, MSG_OK);
            }
        });

        if (!oneButton)
            alertBuilder.setNegativeButton(str_cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (listener != null)
                        listener.onClick(dialog, MSG_CANCEL);
                }
            });
        AlertDialog alert = alertBuilder.create();
        alert.show();
        alert.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                /*if (listener != null)
                    listener.onClick(dialog, DIALOG_DISSMISS);*/
            }
        });
    }

    private static String setDialogTitle(Context c, String str_title) {
        if (str_title == null) {
            str_title = c.getString(R.string.app_name);
        } else {
            if (str_title.equalsIgnoreCase("")) {
                str_title = c.getString(R.string.app_name);
            }
        }
        return str_title;
    }

    static public int getScreenWidth(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (context instanceof Activity) {
            ((Activity) context).getWindowManager()
                    .getDefaultDisplay().getMetrics(displayMetrics);

        } else if (context instanceof Application) {
            WindowManager windowManager = (WindowManager) context.getSystemService
                    (WINDOW_SERVICE);
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        } else if (context instanceof Service) {
            WindowManager windowManager = (WindowManager) context.getSystemService
                    (WINDOW_SERVICE);
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        }

        return displayMetrics.widthPixels;
    }

    static public int getScreenHeight(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (context instanceof Activity) {
            ((Activity) context).getWindowManager()
                    .getDefaultDisplay().getMetrics(displayMetrics);

        } else if (context instanceof Application) {
            WindowManager windowManager = (WindowManager) context.getSystemService
                    (WINDOW_SERVICE);
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        } else if (context instanceof Service) {
            WindowManager windowManager = (WindowManager) context.getSystemService
                    (WINDOW_SERVICE);
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        }

        return displayMetrics.heightPixels;
    }

    static public float getRatioLength(float length, float ratioWidth, float ratioHeight) {
        return length * ratioHeight / ratioWidth;

    }

    public static int getPixelToDp(Context context, float dp) {
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                context.getResources().getDisplayMetrics());
        return px;
    }

    public static int getStatusBarHeight(final Context context) {
        final Resources resources = context.getResources();
        final int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            return resources.getDimensionPixelSize(resourceId);
        } else {
            return (int) Math.ceil((Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ? 24 : 25) *
                    resources.getDisplayMetrics().density);
        }
    }

    public static String getAndroidId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    public static boolean isTablet(Context context) {
        boolean bTablet = false;
        int screenSizeType = context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK;

        switch (screenSizeType) {
            case Configuration.SCREENLAYOUT_SIZE_XLARGE:
            case Configuration.SCREENLAYOUT_SIZE_LARGE:
                bTablet = true;
                break;
            default:
                break;
        }

        return bTablet;
    }

    public static int getScreenOrientation(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (context instanceof Activity) {
            ((Activity) context).getWindowManager()
                    .getDefaultDisplay().getMetrics(displayMetrics);

        } else if (context instanceof Application) {
            WindowManager windowManager = (WindowManager) context.getSystemService
                    (WINDOW_SERVICE);
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        } else if (context instanceof Service) {
            WindowManager windowManager = (WindowManager) context.getSystemService
                    (WINDOW_SERVICE);
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        }

        return (displayMetrics.widthPixels < displayMetrics.heightPixels) ?
                Configuration.ORIENTATION_PORTRAIT : Configuration.ORIENTATION_LANDSCAPE;
    }

    public static void downKeyboard(Context context, EditText editText) {
        if (editText != null)
            editText.clearFocus();
        InputMethodManager mInputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        mInputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }


    /**
     * 문자열내 특정 문자색 지정
     *
     * @param src
     * @param target
     * @param baseColor
     * @param targetColor
     * @return
     */
    public static String getHighlightedText(String src, String target, String baseColor, String targetColor) {
        String html = "";


        while (src != null && src.length() > 0 && src.indexOf(target) > -1) {
            int targetIdx = src.indexOf(target);

            if (targetIdx == 0) {
                html += "<font color=" + targetColor + ">";
                html += target;
                html += "</font>";
            } else {
                html += "<font color=" + baseColor + ">";
                html += src.substring(0, targetIdx);
                html += "</font>";

                html += "<font color=" + targetColor + ">";
                html += target;
                html += "</font>";
            }

            src = src.substring(targetIdx + target.length(), src.length());
        }

        if (src != null && src.length() > 0) {
            html += "<font color=" + baseColor + ">";
            html += src;
            html += "</font>";
        }

        return html;
    }

//    public static void changeTabsFont(Context context, TabLayout tabLayout) {
//        ViewGroup vg = (ViewGroup) tabLayout.getChildAt(0);
//
//        int tabsCount = vg.getChildCount();
//
//        Typeface typeface = ResourcesCompat.getFont(context, R.font.nanumsquare_l);
//
//        for (int j = 0; j < tabsCount; j++) {
//            ViewGroup vgTab = (ViewGroup) vg.getChildAt(j);
//            int tabChildsCount = vgTab.getChildCount();
//            for (int i = 0; i < tabChildsCount; i++) {
//                View tabViewChild = vgTab.getChildAt(i);
//                if (tabViewChild instanceof TextView) {
//                    ((TextView) tabViewChild).setTypeface(typeface/*Typeface.createFromAsset(context.getAssets(), "fonts/nanumsquarebold.ttf")*/);
//                }
//            }
//        }
//    }

    /**
     * 가격 콤마 추가
     */
    public static String setConvertPrice(String value, String unit) {
        int v = 0;
        if (Pattern.matches("^[0-9]*$", value)) {
            v = Integer.parseInt(value);
        }
        return NumberFormat.getNumberInstance(Locale.KOREA).format(v) + unit;
    }

    /**
     * 세자리 컴마 + 소수점 두자리
     *
     * @param num
     * @param decimal 소수점 표시
     * @return
     */
    public static String setConvertComma(float num, boolean decimal) {
        if (decimal) {
            DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
            return decimalFormat.format(num);
        } else {
            DecimalFormat decimalFormat = new DecimalFormat("#,###");
            return decimalFormat.format(num);
        }
    }

    public static String setConvertComma(double num, boolean decimal) {
        if (decimal) {
            DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
            return decimalFormat.format(num);
        } else {
            DecimalFormat decimalFormat = new DecimalFormat("#,###");
            return decimalFormat.format(num);
        }
    }

    public static String setConvertComma(int num, boolean decimal) {
        if (decimal) {
            DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
            return decimalFormat.format(num);
        } else {
            DecimalFormat decimalFormat = new DecimalFormat("#,###");
            return decimalFormat.format(num);
        }
    }

    public static boolean isIntegerFromStr(String num) {
        try {
            Integer.parseInt(num);
        } catch (NumberFormatException e) {
            return false;
        } catch (NullPointerException e) {
            return false;
        }
        return true;
    }

    /**
     * TextView 취소선 적용
     *
     * @param tv
     */
    public static void setCancelLine(TextView tv) {
        tv.setPaintFlags(tv.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
    }

    /**
     * Statusbar Drawable 및 Icon 밝기 적용
     *
     * @param activity
     * @param drawableId
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void setStatusBarDrawable(Activity activity, int drawableId, boolean isDark, boolean isFullLayout) {
        Window window = activity.getWindow();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            if (isFullLayout) {
                window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                        WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
                window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
                window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            }

            Drawable background = activity.getResources().getDrawable(drawableId);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(activity.getResources().getColor(R.color.transparent));
            window.setNavigationBarColor(activity.getResources().getColor(R.color.transparent));
            window.setBackgroundDrawable(background);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View decor = window.getDecorView();
            if (isDark) {
                decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            } else {
                decor.setSystemUiVisibility(0);
            }
        }
    }

    /**
     * Status bar color 및 Icon 밝기 적용
     */
    public static void setChangeStatusBar(Activity act, int colorId, boolean isDark) {
        Window window = act.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(act, colorId));
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View decor = window.getDecorView();
            if (isDark) {
                decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            } else {
                decor.setSystemUiVisibility(0);
            }
        }
    }

    public static void hiddenSoftKey(Context c, EditText editText) {
        if (editText == null)
            return;
        editText.clearFocus();
        InputMethodManager im = (InputMethodManager) c.getSystemService(Context.INPUT_METHOD_SERVICE);
        im.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    /**
     * 성인인증 1년 지났는지 체크
     *
     * @param adultDay
     * @return
     */
    public static long doDiffOfDate(String adultDay) {
        if (adultDay != null && !"".equals(adultDay)) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

            long now = System.currentTimeMillis();
            Date date = new Date(now);
            String nowTime = formatter.format(date);

            try {
                Date beginDate = formatter.parse(adultDay);
                Date endDate = formatter.parse(nowTime);

                // 시간차이를 시간,분,초를 곱한 값으로 나누면 하루 단위가 나옴
                long diff = endDate.getTime() - beginDate.getTime();
                long diffDays = diff / (24 * 60 * 60 * 1000);

                return diffDays;

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return 366;
    }


    public interface AdultCheckListener {
        void onPlay();

        void onPopUpPlay();

        void onFinish();

        void onPopUp();
    }

    /***
     * 페이저 내에서의 indicator
     */
    public static void setPagerIndicator(Context c, LinearLayout lay, int total, int posi, int img_on, int img_off, int padding_dp) {
        if (lay == null)
            return;
        lay.removeAllViews();
        if (total < 2)
            return;
        int padding = getPixelToDp(c, padding_dp);
        for (int index = 0; index < /*PAGES*/total; index++) {
            ImageView iv = new ImageView(c);
            if (index == posi) {
                iv.setImageResource(img_on);
            } else {
                iv.setImageResource(img_off);
            }
            iv.setPadding(padding, 0, padding, 0);
            lay.addView(iv);
        }
    }


    /**
     * 소프트키 유무체크
     *
     * @param context
     * @return
     */
    public static boolean hasSoftMenu(Context context) {
        //메뉴버튼 유무
        boolean hasMenuKey = ViewConfiguration.get(context.getApplicationContext()).hasPermanentMenuKey();

        //뒤로가기 버튼 유무
        boolean hasBackKey = KeyCharacterMap.deviceHasKey(KeyEvent.KEYCODE_BACK);

        if (!hasMenuKey && !hasBackKey) { // lg폰 소프트키일 경우
            return true;
        } else { // 삼성폰 등.. 메뉴 버튼, 뒤로가기 버튼 존재
            return false;
        }
    }


    /**
     * 소프트키 높이 가져오기
     *
     * @param context
     * @return
     */
    public static int getSoftMenuHeight(Context context) {
        if (!hasSoftMenu(context)) {
            return 0;
        }

        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        int softKeyHeight = 0;
        if (resourceId > 0) {
            softKeyHeight = resources.getDimensionPixelSize(resourceId);
        }

        return softKeyHeight;
    }

    /**
     * 현재 보여지는 액티비티 클래스 이름 가져오기
     *
     * @param context
     * @return
     */
    public static String getRunActivityClassName(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> taskInfo = activityManager.getRunningTasks(1);
        ComponentName topActivity = taskInfo.get(0).topActivity;
        String runClassName = topActivity.getClassName();

        if (runClassName == null) {
            return "";
        } else {
            return runClassName;
        }
    }



    public static Bitmap fastblur(Bitmap sentBitmap, float scale, int radius) {

        int width = Math.round(sentBitmap.getWidth() * scale);
        int height = Math.round(sentBitmap.getHeight() * scale);
        sentBitmap = Bitmap.createScaledBitmap(sentBitmap, width, height, false);

        Bitmap bitmap = sentBitmap.copy(sentBitmap.getConfig(), true);

        if (radius < 1) {
            return (null);
        }

        int w = bitmap.getWidth();
        int h = bitmap.getHeight();

        int[] pix = new int[w * h];
        Log.e("pix", w + " " + h + " " + pix.length);
        bitmap.getPixels(pix, 0, w, 0, 0, w, h);

        int wm = w - 1;
        int hm = h - 1;
        int wh = w * h;
        int div = radius + radius + 1;

        int r[] = new int[wh];
        int g[] = new int[wh];
        int b[] = new int[wh];
        int rsum, gsum, bsum, x, y, i, p, yp, yi, yw;
        int vmin[] = new int[Math.max(w, h)];

        int divsum = (div + 1) >> 1;
        divsum *= divsum;
        int dv[] = new int[256 * divsum];
        for (i = 0; i < 256 * divsum; i++) {
            dv[i] = (i / divsum);
        }

        yw = yi = 0;

        int[][] stack = new int[div][3];
        int stackpointer;
        int stackstart;
        int[] sir;
        int rbs;
        int r1 = radius + 1;
        int routsum, goutsum, boutsum;
        int rinsum, ginsum, binsum;

        for (y = 0; y < h; y++) {
            rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
            for (i = -radius; i <= radius; i++) {
                p = pix[yi + Math.min(wm, Math.max(i, 0))];
                sir = stack[i + radius];
                sir[0] = (p & 0xff0000) >> 16;
                sir[1] = (p & 0x00ff00) >> 8;
                sir[2] = (p & 0x0000ff);
                rbs = r1 - Math.abs(i);
                rsum += sir[0] * rbs;
                gsum += sir[1] * rbs;
                bsum += sir[2] * rbs;
                if (i > 0) {
                    rinsum += sir[0];
                    ginsum += sir[1];
                    binsum += sir[2];
                } else {
                    routsum += sir[0];
                    goutsum += sir[1];
                    boutsum += sir[2];
                }
            }
            stackpointer = radius;

            for (x = 0; x < w; x++) {

                r[yi] = dv[rsum];
                g[yi] = dv[gsum];
                b[yi] = dv[bsum];

                rsum -= routsum;
                gsum -= goutsum;
                bsum -= boutsum;

                stackstart = stackpointer - radius + div;
                sir = stack[stackstart % div];

                routsum -= sir[0];
                goutsum -= sir[1];
                boutsum -= sir[2];

                if (y == 0) {
                    vmin[x] = Math.min(x + radius + 1, wm);
                }
                p = pix[yw + vmin[x]];

                sir[0] = (p & 0xff0000) >> 16;
                sir[1] = (p & 0x00ff00) >> 8;
                sir[2] = (p & 0x0000ff);

                rinsum += sir[0];
                ginsum += sir[1];
                binsum += sir[2];

                rsum += rinsum;
                gsum += ginsum;
                bsum += binsum;

                stackpointer = (stackpointer + 1) % div;
                sir = stack[(stackpointer) % div];

                routsum += sir[0];
                goutsum += sir[1];
                boutsum += sir[2];

                rinsum -= sir[0];
                ginsum -= sir[1];
                binsum -= sir[2];

                yi++;
            }
            yw += w;
        }
        for (x = 0; x < w; x++) {
            rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
            yp = -radius * w;
            for (i = -radius; i <= radius; i++) {
                yi = Math.max(0, yp) + x;

                sir = stack[i + radius];

                sir[0] = r[yi];
                sir[1] = g[yi];
                sir[2] = b[yi];

                rbs = r1 - Math.abs(i);

                rsum += r[yi] * rbs;
                gsum += g[yi] * rbs;
                bsum += b[yi] * rbs;

                if (i > 0) {
                    rinsum += sir[0];
                    ginsum += sir[1];
                    binsum += sir[2];
                } else {
                    routsum += sir[0];
                    goutsum += sir[1];
                    boutsum += sir[2];
                }

                if (i < hm) {
                    yp += w;
                }
            }
            yi = x;
            stackpointer = radius;
            for (y = 0; y < h; y++) {
                // Preserve alpha channel: ( 0xff000000 & pix[yi] )
                pix[yi] = (0xff000000 & pix[yi]) | (dv[rsum] << 16) | (dv[gsum] << 8) | dv[bsum];

                rsum -= routsum;
                gsum -= goutsum;
                bsum -= boutsum;

                stackstart = stackpointer - radius + div;
                sir = stack[stackstart % div];

                routsum -= sir[0];
                goutsum -= sir[1];
                boutsum -= sir[2];

                if (x == 0) {
                    vmin[y] = Math.min(y + r1, hm) * w;
                }
                p = x + vmin[y];

                sir[0] = r[p];
                sir[1] = g[p];
                sir[2] = b[p];

                rinsum += sir[0];
                ginsum += sir[1];
                binsum += sir[2];

                rsum += rinsum;
                gsum += ginsum;
                bsum += binsum;

                stackpointer = (stackpointer + 1) % div;
                sir = stack[stackpointer];

                routsum += sir[0];
                goutsum += sir[1];
                boutsum += sir[2];

                rinsum -= sir[0];
                ginsum -= sir[1];
                binsum -= sir[2];

                yi += w;
            }
        }

        Log.e("pix", w + " " + h + " " + pix.length);
        bitmap.setPixels(pix, 0, w, 0, 0, w, h);

        return (bitmap);
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap = null;

        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if (bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }

        if (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888); // Single color bitmap will be created of 1x1 pixel
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    public static void calling(Context context, String phonenumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phonenumber));
        context.startActivity(intent);
    }

    /**
     * 화면에 탭 가로 사이즈가 균등하게 마진 주기
     *
     * @param tabLayout
     * @param externalMargin
     * @param internalMargin
     */
    public static void wrapTabIndicatorToTitle(TabLayout tabLayout, int externalMargin, int internalMargin) {
        View tabStrip = tabLayout.getChildAt(0);
        int externalMarginPx = Utils.getPixelToDp(tabLayout.getContext(), externalMargin);
        int internalMarginPx = Utils.getPixelToDp(tabLayout.getContext(), internalMargin);
//        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        if (tabStrip instanceof ViewGroup) {
            ViewGroup tabStripGroup = (ViewGroup) tabStrip;
            int childCount = ((ViewGroup) tabStrip).getChildCount();
            for (int i = 0; i < childCount; i++) {
                View tabView = tabStripGroup.getChildAt(i);
                //set minimum width to 0 for instead for small texts, indicator is not wrapped as expected
                tabView.setMinimumWidth(0);
                // set padding to 0 for wrapping indicator as title
                tabView.setPadding(0, tabView.getPaddingTop(), 0, tabView.getPaddingBottom());
                // setting custom margin between tabs
                if (tabView.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
                    ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) tabView.getLayoutParams();
                    if (i == 0) {
                        // left
                        settingMargin(layoutParams, externalMarginPx, internalMarginPx);
                    } else if (i == childCount - 1) {
                        // right
                        settingMargin(layoutParams, internalMarginPx, externalMarginPx);
                    } else {
                        // internal
                        settingMargin(layoutParams, internalMarginPx, internalMarginPx);
                    }
                }
            }

            tabLayout.requestLayout();
        }
    }

    private static void settingMargin(ViewGroup.MarginLayoutParams layoutParams, int start, int end) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            layoutParams.setMarginStart(start);
            layoutParams.setMarginEnd(end);
        } else {
            layoutParams.leftMargin = start;
            layoutParams.rightMargin = end;
        }
    }

    public static String resizeImagePath(String originUrl, int level) {
        if (TextUtils.isEmpty(originUrl)) {
            return originUrl;
        }

        String extention = originUrl.substring(originUrl.lastIndexOf('.'), originUrl.length());
        String prefixUrl = originUrl.substring(0, originUrl.lastIndexOf('.'))
                + "_" + Integer.toString(level) + extention;

        return prefixUrl;
    }

    public static boolean isAppOnForeground(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcesses =
                activityManager.getRunningAppProcesses();
        if (appProcesses == null) {
            return false;
        }
        final String packageName = context.getPackageName();
        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND
                    && appProcess.processName.equals(packageName)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isAppIsInBackground(Context context) {
        boolean isInBackground = true;
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT_WATCH) {
            List<ActivityManager.RunningAppProcessInfo> runningProcesses = am.getRunningAppProcesses();

            for (ActivityManager.RunningAppProcessInfo processInfo : runningProcesses) {
                if (processInfo.importance == ActivityManager.RunningAppProcessInfo
                        .IMPORTANCE_FOREGROUND ||
                        processInfo.importance == ActivityManager.RunningAppProcessInfo
                                .IMPORTANCE_FOREGROUND_SERVICE) {
                    for (String activeProcess : processInfo.pkgList) {
                        if (activeProcess.equals(context.getPackageName())) {
                            isInBackground = false;
                        }
                    }
                }
            }
        } else {
            List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(1);
            ComponentName componentInfo = taskInfo.get(0).topActivity;
            if (componentInfo.getPackageName().equals(context.getPackageName())) {
                isInBackground = false;
            }
        }

        return isInBackground;
    }

    public static String getEncodedUr(String urlVaule) {
        return Uri.encode(urlVaule, "UTF-8");
    }

    public static void darkenStatusBar(Activity activity, int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
                && Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP_MR1) {
            activity.getWindow().setStatusBarColor(
                    darkenColor(
                            ContextCompat.getColor(activity, color)));
        }

    }

    // Code to darken the color supplied (mostly color of toolbar)
    private static int darkenColor(int color) {
        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv);
        hsv[2] *= 0.8f;
        return Color.HSVToColor(hsv);
    }

}
