package com.qysoft.platform.actions;

import com.jfinal.aop.Before;
import com.qysoft.platform.services.AreaService;
import com.qysoft.rapid.actions.RapidAction;
import com.qysoft.rapid.aop.annotation.AutoService;
import com.qysoft.rapid.aop.interceptor.MyBatisDbConn;
import com.qysoft.rapid.domain.Bean;
import com.qysoft.rapid.aop.interceptor.JsonResultInterceptor;

import java.util.List;

/**
 * Created by shenjinxiang on 2017/9/7.
 */
public class AreaAction extends RapidAction {

    @AutoService
    private AreaService areaService;

    @Before(MyBatisDbConn.class)
    public void index() {
        List<Bean> areaList = areaService.queryAreaList();
        renderJsp("/WEB-INF/pages/area.jsp");
    }

    @Before(JsonResultInterceptor.class)
    public void findById() {
        Bean param = getBean();
        Bean area = areaService.queryById(param);
        setJson(area);
    }

    @Before(JsonResultInterceptor.class)
    public void findInt() {
        int i = areaService.getInt();
        setJson(i);
    }
}
