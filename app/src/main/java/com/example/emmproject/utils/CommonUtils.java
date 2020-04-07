package com.example.emmproject.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.emmproject.R;
import com.example.emmproject.app.Constants;
import com.example.emmproject.app.EmmApplication;
import com.example.emmproject.core.http.EmmApis;
import com.google.android.material.snackbar.Snackbar;

import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.MediaType;
import okhttp3.RequestBody;

import static com.luck.picture.lib.permissions.RxPermissions.TAG;

public class CommonUtils {
    public static void showMessage(Activity activity, String message) {
        Toast.makeText(activity,message,Toast.LENGTH_SHORT).show();
    }

    public static void showSnackMessage(Activity activity, String message) {

        final Snackbar snackbar = Snackbar.make(activity.getWindow().getDecorView(), message, Snackbar.LENGTH_SHORT);
        snackbar.show();
        View view=snackbar.getView();
        view.setBackgroundColor(activity.getResources().getColor(R.color.snackbarcolor));
        ViewGroup.LayoutParams layoutParams=view.getLayoutParams();

        RelativeLayout.LayoutParams layoutParams1=new RelativeLayout.LayoutParams(layoutParams.width,layoutParams.height);
        layoutParams1.topMargin=getStatusBarHeight(activity);
        snackbar.getView().setLayoutParams(layoutParams1);

    }
    /**
     * 检查是否有可用网络
     */
    public static boolean isNetworkConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) EmmApplication.getInstance().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        assert connectivityManager != null;
        return connectivityManager.getActiveNetworkInfo() != null;
    }


    public static void hideKeyboard(View view) {
        InputMethodManager manager = (InputMethodManager) view.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(view.getWindowToken(), 0);

    }

    public static int getStatusBarHeight(Context context) {

        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        int height = resources.getDimensionPixelSize(resourceId);
        return height;
    }


    public static MediaType guessMimeType(String path) {
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        path = path.replace("#", "");   //解决文件名中含有#号异常的问题
        String contentType = fileNameMap.getContentTypeFor(path);
        Log.d(TAG, "guessMimeType: "+contentType);
        if (contentType == null) {
            contentType = "application/octet-stream";
        }
        return MediaType.parse(contentType);
    }





   /*public static RequestOptions getRequestOptions(){// 设置圆角
       RoundedCorners roundedCorners = new RoundedCorners(6);//数字为圆角度数
       RequestOptions coverRequestOptions = new RequestOptions()               .transforms(new CenterCrop(),roundedCorners);
       return coverRequestOptions;
   }*/


   public static float stringToFloat(String s){
    return  Math.round(Float.parseFloat(s)*100)/100.0f;
   }

    /**
     * dip-->px
     */
    public static int dip2Px(int dip,Activity activity) {
        // px/dip = density;
        // density = dpi/160
        // 320*480 density = 1 1px = 1dp
        // 1280*720 density = 2 2px = 1dp

        float density =activity.getResources().getDisplayMetrics().density;
        int px = (int) (dip * density + 0.5f);
        return px;
    }

    public static int px2dip(int px,Activity activity) {

        float density = activity.getResources().getDisplayMetrics().density;
        int dip = (int) (px / density + 0.5f);
        return dip;
    }


    /**
     * 检测邮箱地址是否合法
     * @param email 邮箱
     * @return true合法 false不合法
     */
    public static boolean isEmail(String email){
        if (null == email || "".equals(email))
            return false;
        Pattern p =  Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
        Matcher m = p.matcher(email);
        return m.matches();
    }

    /**
     * 检测账号是否满足3到9个任意数字或字母组成的账号
     * @param username 账号
     * @return  true合法 false不合法
     */
    public static boolean isStandardFormUsername(String username) {
        if (null == username || "".equals(username))
            return false;
        Pattern p = Pattern.compile("^[\\u4e00-\\u9fa5_a-zA-Z0-9]{3,9}$");
        Matcher m = p.matcher(username);
        return m.matches();
    }

    /**
     * 检测密码是否满足数字和字母组成的任意6到9位
     * @param password 密码
     * @return true合法 false不合法
     */
    public static boolean isStandardFormPassword(String password) {
        if (null == password || "".equals(password))
            return false;
        Pattern p = Pattern.compile("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z_]{6,9}$");
        Matcher m = p.matcher(password);
        return m.matches();
    }

    public static RequestOptions getRoundOption(){

        RoundedCorners roundedCorners=new RoundedCorners(Constants.ROUND_CORNERS);

        RequestOptions coverRequestOptions = new RequestOptions()
            .transforms(new CenterCrop(),roundedCorners);
        return coverRequestOptions;

    }

}
