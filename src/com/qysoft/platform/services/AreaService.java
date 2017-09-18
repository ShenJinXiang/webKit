package com.qysoft.platform.services;

import com.jfinal.aop.Before;
import com.qysoft.platform.mappers.AreaMapper;
import com.qysoft.rapid.aop.annotation.AutoMapper;
import com.qysoft.rapid.aop.interceptor.MyBatisDbConn;
import com.qysoft.rapid.domain.Bean;

import java.util.List;

/**
 * Created by shenjinxiang on 2017/9/7.
 */
public class AreaService {

    @AutoMapper
    private AreaMapper areaMapper;

    @Before(MyBatisDbConn.class)
    public List<Bean> queryAreaList() {
        return areaMapper.queryAreaList();
    }

    public Bean queryById(Bean param) {
        Bean bean = areaMapper.queryById(param);
        queryAreaList();
        Bean p = new Bean();
        p.set("id", 2363);
        areaMapper.queryById(p);
        return bean;
    }

    public int getInt() {
        return 1;
    }
}
