package com.qysoft.platform.actions.system;

import com.jfinal.aop.Before;
import com.qysoft.rapid.actions.RapidAction;
import com.qysoft.rapid.aop.interceptor.JsonResultInterceptor;
import com.qysoft.rapid.domain.Bean;

/**
 * Created by shenjinxiang on 2017/9/20.
 */
public class ZyxxAction extends RapidAction {

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
}
