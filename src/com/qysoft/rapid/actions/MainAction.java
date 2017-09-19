package com.qysoft.rapid.actions;

import com.qysoft.rapid.consts.RapidConsts;

/**
 * Created by shenjinxiang on 2017/9/19.
 */
public class MainAction extends RapidAction {

    public void index() {
        renderJsp("/WEB-INF/index.jsp");
    }
}
