package com.qysoft.rapid.core;

import com.jfinal.config.*;
import com.jfinal.core.ActionReporter;
import com.jfinal.render.ViewType;
import com.jfinal.template.Engine;
import com.qysoft.rapid.consts.RapidConsts;
import com.qysoft.rapid.aop.interceptor.ActionInterceptor;
import com.qysoft.rapid.aop.interceptor.ServiceInterceptor;
import com.qysoft.rapid.plugin.dbtype.PlatformDbType;

/**
 * Created by shenjinxiang on 2017-08-23.
 */
public abstract class RapidConifg extends JFinalConfig {

    public abstract void configAction(Routes me);

    public abstract PlatformDbType configDbType();

    public abstract String configErrorMSg();

    public abstract void afterRapidStart();

    public abstract void configGlobalServiceInterceptor(Interceptors interceptors);

    public abstract void configGlobalActionInterceptor(Interceptors interceptors);

    @Override
    public void configEngine(Engine engine) {

    }

    @Override
    public void afterJFinalStart() {
        super.afterJFinalStart();
        afterRapidStart();
    }

    @Override
    public void configConstant(Constants constants) {
        loadPropertyFile("config/rapid.properties");
        boolean devMode = getPropertyToBoolean("devMode");
        RapidConsts.setIS_DEV_MODE(devMode);
        constants.setDevMode(RapidConsts.isIS_DEV_MODE());
        constants.setViewType(ViewType.JSP);
        ActionReporter.setReportAfterInvocation(false);
        RapidConsts.setERROR_MSG(configErrorMSg());
    }

    @Override
    public void configRoute(Routes routes) {
        configAction(routes);
    }

    @Override
    public void configPlugin(Plugins plugins) {
        PlatformDbType dbType = configDbType();
        RapidConsts.setDbType(dbType);
        dbType.getDbTypeConfig().configPlugin(plugins);
    }

    @Override
    public void configInterceptor(Interceptors interceptors) {
        interceptors.addGlobalActionInterceptor(new ActionInterceptor());
        configGlobalActionInterceptor(interceptors);
        interceptors.addGlobalServiceInterceptor(new ServiceInterceptor());
        configGlobalServiceInterceptor(interceptors);
    }

    @Override
    public void configHandler(Handlers handlers) {

    }

}
