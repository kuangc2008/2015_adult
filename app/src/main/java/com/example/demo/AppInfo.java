package com.example.demo;

import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;

public class AppInfo {

    private ActivityInfo info;

    public String label;

    public AppInfo(ResolveInfo activity, PackageManager pm) throws Exception {
        this.info = activity.activityInfo;
        this.label = (String) info.loadLabel(pm);
        if(TextUtils.isEmpty(label)) {
            throw new Exception();
        }
    }
    
    public ActivityInfo getActivityInfo() {
        return info;
    }

    /**
     * get the title according to prefix
     * @param prefix if the prefix is null or "", return first prefix.
     * @return the title for listview
     */
    public String getTitle(String prefix) {
        if(prefix == null || prefix.equals("")) {
            if(label.indexOf('/') != -1) {
                return label.substring(0, label.indexOf('/'));
            } else {
                return label;
            }
        }
        
        String title = null;
        if(isPrefix(prefix)) {
            int end = label.indexOf('/', prefix.length()+1);
            end = ( end == -1 ? label.length()  : end);
            title = (String) label.subSequence(prefix.length()+1, end);
        }
        return title;
    }
    
    public String getNextPrefix(String prefix) {
        if(prefix == null || prefix.equals("")) {
            if( label.indexOf('/') != -1) {
                return label.substring(0, label.indexOf('/'));
            } else {
                return label;
            }
        }
        
        String currentPre = null;
        if(isPrefix(prefix)) {
            int end = label.indexOf('/', prefix.length()+1);
            end = ( end == -1 ? label.length() : end);
            currentPre = (String) label.subSequence(0, end);
        }
        return currentPre;
    }

    public boolean isPrefix(String prefix) {
        if(prefix == null || prefix.equals("")) {
            return true;
        }
        return label.startsWith(prefix);
    }
    
    public boolean isLastPrefix(String prefix) {
//        if(prefix == null || prefix.equals("")) {
//            return false;
//        }
        
        int end = label.indexOf('/', prefix.length()+1);
        return end == -1;
    }
    
}
