package com.qysoft.rapid.aop.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.qysoft.rapid.aop.FieldGenerator;

/**
 * Created by shenjinxiang on 2017/9/10.
 */
public class ActionInterceptor implements Interceptor {

    @Override
    public void intercept(Invocation invocation) {
        Class contolllerCalss = invocation.getController().getClass();
        try {
            FieldGenerator generator = new FieldGenerator(invocation.getController(), invocation.getMethod().getDeclaringClass());
            generator.generate();

            invocation.invoke();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
