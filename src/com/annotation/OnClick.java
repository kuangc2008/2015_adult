package com.annotation;

import android.view.View;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by chengkuang on 15/7/25.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@EventBase( listenerType = View.OnClickListener.class, listenerSetter = "setOnClickListener", methodName = "OnClick")
public @interface OnClick {
    int[] value();
}
