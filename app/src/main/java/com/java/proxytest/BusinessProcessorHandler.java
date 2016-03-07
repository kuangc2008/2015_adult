package com.java.proxytest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by chengkuang on 15/7/26.
 */
public class BusinessProcessorHandler implements InvocationHandler {
    private Object target = null;

    public BusinessProcessorHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("you can do something before");
        Object result = method.invoke(target, args);
        System.out.println("you can do somthing after");
        return null;
    }
}
