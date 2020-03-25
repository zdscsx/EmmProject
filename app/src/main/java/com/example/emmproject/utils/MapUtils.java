package com.example.emmproject.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;

public class MapUtils {

    public static boolean isAppInstall(Context context,String pagename) {
        PackageInfo packageInfo = null;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(pagename, 0);

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (packageInfo == null)
            return false;
        else return true;

    }

    public static boolean startMapActivity(Context context,String address){

        try {
            String pathUrl = "qqmap://map/routeplan?type=drive&to=" + address + "&policy=2&referer=myapp";
            Intent intent = new Intent();
            intent.setData(Uri.parse(pathUrl));
            context.startActivity(intent);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

      try {
            String act = "android.intent.action.VIEW";
            String dat = "androidamap://keywordNavi?sourceApplication=softname&keyword=" + address + " &style=2";
            String pkg = "com.autonavi.minimap";
            Intent intent = new Intent(act, Uri.parse(dat));
            intent.setPackage(pkg);
            context.startActivity(intent);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
            Intent i1 = new Intent();
            i1.setData(Uri.parse("baidumap://map/geocoder?src=openApiDemo&address=" + address));
           context. startActivity(i1);
           return true;
        } catch (Exception e) {
            e.printStackTrace();
        }



        return false;


    }

    public static void startDownLoadMapApp(Context context){ //下载腾讯地图

        Uri uri = Uri.parse("market://details?id=com.tencent.map");
        Intent  intent = new Intent(Intent.ACTION_VIEW, uri);
        context.startActivity(intent);
    }

}
