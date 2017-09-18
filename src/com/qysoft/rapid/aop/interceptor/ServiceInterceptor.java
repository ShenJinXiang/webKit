package com.qysoft.rapid.aop.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.qysoft.rapid.aop.FieldGenerator;

/**
 * Created by shenjinxiang on 2017/9/15.
 */
public class ServiceInterceptor implements Interceptor {

    @Override
    public void intercept(Invocation invocation) {
        try {
            FieldGenerator fieldGenerator = new FieldGenerator(invocation.getTarget(), invocation.getMethod().getDeclaringClass());
            fieldGenerator.generate();
            invocation.invoke();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
