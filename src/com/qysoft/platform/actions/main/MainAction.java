package com.qysoft.platform.actions.main;

import com.qysoft.platform.services.system.ZyglService;
import com.qysoft.rapid.actions.RapidAction;
import com.qysoft.rapid.aop.annotation.AutoService;
import com.qysoft.rapid.domain.Bean;

import java.util.List;

/**
 * Created by shenjinxiang on 2017/9/19.
 */
public class MainAction extends RapidAction {

    @AutoService
    private ZyglService zyglService;

    public void index() {
        List<Bean> zyxxList = zyglService.queryZyxxList();
        setAttr("zyxxList", zyxxList);
        renderJsp("/WEB-INF/index.jsp");
    }
}
