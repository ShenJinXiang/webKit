package com.qysoft.rapid.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by shenjinxiang on 2017/9/10.
 * 标记注解， service中的成员变量使用此注解，根据类型创建对应的mapper对象
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoMapper {
}
