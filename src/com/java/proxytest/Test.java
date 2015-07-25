package com.java.proxytest;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by chengkuang on 15/7/26.
 */
public class Test {

    public static void main(String[] args) {

        BusinessProcessorImpl bpimpl = new BusinessProcessorImpl();
        BusinessProcessorHandler handler = new BusinessProcessorHandler(bpimpl);

        /**
         * 继承于第二个interface，重写了equals, toString  hacode
         *
         * 第三个接口，实际上调用了Proxy(Hander)，接下来调用相关方法的时候，反而会调用invoke
         */
        BusinessProcessor bp = (BusinessProcessor) Proxy.newProxyInstance(bpimpl.getClass().getClassLoader(),
                bpimpl.getClass().getInterfaces(), handler);
        bp.processBusiness();


        System.out.println("bp class->" + bp.getClass().getCanonicalName());

        Class clazz = bp.getClass().getSuperclass();
        System.out.println("extend:" + clazz.getCanonicalName());

        Class[]  interfaces = bp.getClass().getInterfaces();
        for(Class interface1 : interfaces) {
            System.out.println("implements:"  + interface1.getCanonicalName());
        }


        Method[] method = bp.getClass().getDeclaredMethods();
        for(Method method1: method) {
            System.out.println("method:"  + method1.getName());
        }



    }
}
