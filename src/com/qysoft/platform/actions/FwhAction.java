package com.qysoft.platform.actions;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Record;
import com.qysoft.platform.services.FwhService;
import com.qysoft.rapid.actions.RapidAction;
import com.qysoft.rapid.aop.annotation.AutoService;
import com.qysoft.rapid.aop.interceptor.JsonResultInterceptor;

/**
 * Created by shenjinxiang on 2017/9/11.
 */
public class FwhAction extends RapidAction {

    @AutoService
    private FwhService fwhService;

    @Before(JsonResultInterceptor.class)
    public void index() {
        String id = getPara("id");
        Record record = fwhService.getFwuById(id);
        setJson(record);
    }
}
