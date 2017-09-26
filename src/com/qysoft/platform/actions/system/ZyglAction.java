package com.qysoft.platform.actions.system;

import com.jfinal.aop.Before;
import com.qysoft.platform.services.system.ZyglService;
import com.qysoft.rapid.actions.RapidAction;
import com.qysoft.rapid.aop.annotation.AutoService;
import com.qysoft.rapid.aop.interceptor.JsonResultInterceptor;
import com.qysoft.rapid.aop.interceptor.MyBatisDbConnTx;
import com.qysoft.rapid.domain.Bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shenjinxiang on 2017/9/20.
 */
public class ZyglAction extends RapidAction {

    @AutoService
    private ZyglService zyglService;

    public void index() {
        renderJsp("/WEB-INF/pages/system/zyxx.jsp");
    }

    @Before(JsonResultInterceptor.class)
    public void query() {
        Bean bean = new Bean();
        bean.set("name", "张三");
        bean.set("address", "中华人民共和国");
        bean.set("age", 22);
        setJson(bean);
    }

    @Before({JsonResultInterceptor.class, MyBatisDbConnTx.class})
    public void queryAllZyList() {
        List<Bean> zyxxList = new ArrayList<>();
        zyglService.queryZyxxList(null, zyxxList);
        System.out.println(toJson(zyxxList));
        setJson(zyxxList);
    }
}
