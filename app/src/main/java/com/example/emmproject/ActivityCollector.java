package com.example.emmproject;

import android.app.Activity;

import java.util.HashSet;
import java.util.Set;

public class ActivityCollector {
    private static ActivityCollector activityCollector;
    public synchronized static ActivityCollector getInstance(){
        if (activityCollector==null)
        {
            activityCollector=new ActivityCollector();
        }
        return activityCollector;
    }
    private Set<Activity> activities;

    public void addActivity(Activity activity)
    {   if (activities==null)
       {
        activities=new HashSet<>();
       }
        activities.add(activity);
    }

    public void removeActivity(Activity activity)
    {
        if (activities!=null)
        {
            activities.add(activity);
        }
    }

    public void exitApp(){
        if (activities!=null){
            synchronized (activities){
                for (Activity activity:activities){
                    activity.finish();
                }
            }
        }

        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

}
