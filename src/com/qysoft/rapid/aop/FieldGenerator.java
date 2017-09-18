package com.qysoft.rapid.aop;

import com.jfinal.aop.Enhancer;
import com.qysoft.rapid.aop.annotation.AutoMapper;
import com.qysoft.rapid.aop.annotation.AutoService;
import com.qysoft.rapid.exceptions.RapidException;
import com.qysoft.rapid.plugin.mybatis.MyBatisSessionManager;
import com.qysoft.rapid.utils.ClassUtil;
import org.apache.ibatis.session.SqlSession;

import java.lang.reflect.Field;

/**
 * Created by shenjinxiang on 2017/9/17.
 */
public class FieldGenerator {

    private Object target;
    private Class clazz;

    public FieldGenerator(Object target, Class clazz) {
        this.target = target;
        this.clazz = clazz;
    }

    public void generate() throws Exception {
        if (null == target) {
            return;
        }
        Field[] fields = this.clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(AutoService.class)) {
                generateService(field);
                continue;
            }
            if (field.isAnnotationPresent(AutoMapper.class)) {
                generateMapper(field);
                continue;
            }
        }
    }

    private void generateMapper(Field field) throws Exception {
        SqlSession session = MyBatisSessionManager.getSession();
        if (null != session) {
            field.setAccessible(true);
            Class clazz = field.getType();
            if (!clazz.isInterface()) {
                throw new RapidException(getErrorMes(field));
            }
            Object obj = session.getMapper(clazz);
            if (null == obj) {
                throw new RapidException(getErrorMes(field));
            }
            field.set(target, obj);
        }
    }

    private void generateService(Field field) throws Exception{
        field.setAccessible(true);
        if (null != field.get(this.target)) {
            return;
        }
        AutoService autoService = field.getAnnotation(AutoService.class);
        Class clazz = autoService.value().equals(Object.class) ? field.getType() : autoService.value();
        if (!ClassUtil.canNewInstance(clazz)) {
            throw new RapidException(getErrorMes(field));
        }
        Object obj = Enhancer.enhance(clazz);
        if (null == obj) {
            throw new RapidException(getErrorMes(field));
        }
        field.set(target, obj);
    }

    private String getErrorMes(Field field) {
        return target.getClass().getName() + "." + field.getName() + "无法初始化";
    }

}
