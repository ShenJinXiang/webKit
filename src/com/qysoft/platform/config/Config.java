package com.qysoft.platform.config;

import com.jfinal.config.Interceptors;
import com.jfinal.config.Routes;
import com.qysoft.platform.actions.AreaAction;
import com.qysoft.platform.actions.FwhAction;
import com.qysoft.rapid.core.Rapid;
import com.qysoft.rapid.core.RapidConifg;
import com.qysoft.rapid.plugin.dbtype.PlatformDbType;

/**
 * Created by shenjinxiang on 2017/9/9.
 */
public class Config extends RapidConifg {

    @Override
    public void configAction(Routes me) {

        me.add("/area", AreaAction.class);
        me.add("/fwh", FwhAction.class);
    }

    @Override
    public PlatformDbType configDbType() {
        return PlatformDbType.MYSQL;
    }

    @Override
    public String configErrorMSg() {
        return "系统错误，请联系管理员";
    }

    /**
     * 系统启动后执行，可以做一些初始化数据的工作
     */
    @Override
    public void afterRapidStart() {

    }

    /**
     * 项目中涉及到的全局Service拦截器在此配置
     * 尽量不在RaidConfig中配置全局拦截器
     * RapidConfig中应该配置适用于不同项目的框架及全局拦截器
     * @param interceptors
     */
    @Override
    public void configGlobalServiceInterceptor(Interceptors interceptors) {

    }

    /**
     * 项目中涉及到的全局Action拦截器在此配置
     * 尽量不在RaidConfig中配置全局拦截器
     * RapidConfig中应该配置适用于不同项目的框架及全局拦截器
     * @param interceptors
     */
    @Override
    public void configGlobalActionInterceptor(Interceptors interceptors) {

    }

    public static void main(String[] args) throws Exception {
        Rapid.getRapidInstance().start("web", 8888, "/webKit", 5);
    }

}
