package com.annotation.util;

import android.app.Activity;
import android.util.Log;

import com.annotation.ContentView;

import java.lang.reflect.Method;

/**
 * Created by chengkuang on 15/7/24.
 */
public class ViewInjectUtils {
    private static final String METHOD_SET_CONTENT_VIEW = "setContentView";

    public static void injectContentView(Activity activity) {

        Class<? extends Activity> clazz = activity.getClass();
        ContentView cv = clazz.getAnnotation(ContentView.class);
        if(cv != null) {
            int layoutId = cv.value3();
      //      activity.setContentView(layoutId);

            try {
                Method method = clazz.getMethod(METHOD_SET_CONTENT_VIEW, int.class);
                method.setAccessible(true);
                method.invoke(activity, layoutId);
            } catch (Exception e) {
                Log.w("kcc", "method", e);
            }
        }

    }
}
