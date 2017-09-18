package com.qysoft.rapid.aop.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.qysoft.rapid.consts.RapidConsts;
import com.qysoft.rapid.domain.JsonResult;
import com.qysoft.rapid.exceptions.BizException;

/**
 * Created by shenjinxiang on 2017-08-24.
 */
public class JsonResultInterceptor implements Interceptor {

    @Override
    public void intercept(Invocation ai) {
        JsonResult jsonResult = new JsonResult();
        try {
            ai.invoke();
            jsonResult.setSuccess(true);
            Object result = ai.getController().getAttr("result");
            jsonResult.setMessage(result);
        } catch (Exception e) {
            jsonResult.setSuccess(false);
            if (!(e instanceof BizException)) {
                if (RapidConsts.isIS_DEV_MODE()) {
                    jsonResult.setMessage(e.getMessage());
                }else {
                    jsonResult.setMessage(RapidConsts.getERROR_MSG());
                }
                e.printStackTrace();
            }else {
                jsonResult.setMessage(e.getMessage());
            }
        }
        ai.getController().renderJson(jsonResult);
    }
}
