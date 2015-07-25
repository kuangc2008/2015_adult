package com.annotation.util;

import android.app.Activity;
import android.util.Log;
import android.view.View;

import com.annotation.ContentView;
import com.annotation.DynamicHandler;
import com.annotation.EventBase;
import com.annotation.OnClick;
import com.annotation.ViewInjet;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by chengkuang on 15/7/24.
 */
public class ViewInjectUtils {
    private static final String METHOD_SET_CONTENT_VIEW = "setContentView";
    private static final String METHOD_FIND_VIEW_BY_ID = "findViewById";

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


    public static void injectViews(Activity activity) {
        Class<? extends Activity> clazz = activity.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for(Field field : fields) {
            ViewInjet fileAnnotation = field.getAnnotation(ViewInjet.class);
            if(fileAnnotation != null) {
                int resId = fileAnnotation.value();
                try {
                    Method method = clazz.getMethod(METHOD_FIND_VIEW_BY_ID, int.class);
                    method.setAccessible(true);
                    View view = (View) method.invoke(activity, resId);

                    field.setAccessible(true);
                    field.set(activity, view);
                } catch (Exception e) {
                    Log.w("kcc", "method", e);
                }
            }
        }
    }

    public static void injectClicks(final Activity activity) {
        final Class<? extends Activity> clazz = activity.getClass();
        Method[]  methods  = clazz.getDeclaredMethods();
        for(final Method method : methods) {
            Annotation[]  animations = method.getAnnotations();
            for(Annotation annotation : animations) {
                if(annotation instanceof OnClick) {
                    OnClick clickAnntotion = (OnClick) annotation;
                    int[] resIds = clickAnntotion.value();
                    for(int resId : resIds) {
                        View view = activity.findViewById(resId);
                        view.setOnClickListener(new View.OnClickListener(){
                            @Override
                            public void onClick(View v) {
                                try {
                                    method.invoke(activity, v);
                                } catch (Exception e) {
                                    Log.w("kcc", "clcik", e);
                                }
                            }
                        });
                    }
                }

            }
        }
    }


    public static void injectClicks2(final Activity activity) {
        final Class<? extends Activity> clazz = activity.getClass();
        Method[]  methods  = clazz.getDeclaredMethods();
        for(final Method method : methods) {
            OnClick animation = method.getAnnotation(OnClick.class);
            if(animation != null) {
                Class<? extends Annotation>  annotationType = animation.annotationType();
                EventBase eventBaseAnnotation = annotationType.getAnnotation(EventBase.class);
                if(eventBaseAnnotation != null) {
                    String listenerSetter = eventBaseAnnotation.listenerSetter();
                    Class<?> listenerType = eventBaseAnnotation.listenerType();
                    String methodName = eventBaseAnnotation.methodName();

                    try {
                        Method aMethod = annotationType.getDeclaredMethod("value");
                        int[] viewIds = (int[]) aMethod.invoke(animation, null);

                        DynamicHandler handler = new DynamicHandler(activity);
                        handler.addMethod(methodName, method);


                        Object listener = Proxy.newProxyInstance(listenerType.getClassLoader(),
                                new Class<?>[]{listenerType}, handler);

                        for(int viewid : viewIds) {
                            View view = activity.findViewById(viewid);
                            Method setEventListenerMethod = view.getClass().getMethod(listenerSetter, listenerType);
                            setEventListenerMethod.invoke(view, listener);
                        }



                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }

                }

            }
        }
    }
}
