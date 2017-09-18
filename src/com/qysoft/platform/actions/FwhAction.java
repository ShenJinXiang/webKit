package com.qysoft.platform.actions;

import com.qysoft.platform.services.FwhService;
import com.qysoft.rapid.actions.RapidAction;
import com.qysoft.rapid.aop.annotation.AutoService;

/**
 * Created by shenjinxiang on 2017/9/11.
 */
public class FwhAction extends RapidAction {

    @AutoService
    private FwhService fwhService;

    public void index() {

        renderJsp("/WEB-INF/pages/fwh.jsp");
    }
}
