package com.qysoft.rapid.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

/**
 * Created by shenjinxiang on 2017/9/15.
 */
public class ClassUtil {

    public static boolean canNewInstance(Class clazz) {
        if (clazz.isInterface()) {
            return false;
        }
        Constructor[] constructors = clazz.getConstructors();
        if (constructors.length <= 0) {
            return false;
        }
        clazz.getModifiers();

        return !Modifier.isAbstract(clazz.getModifiers());
    }

}
