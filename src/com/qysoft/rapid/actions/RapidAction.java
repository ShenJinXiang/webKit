package com.qysoft.rapid.actions;

import com.jfinal.core.Controller;
import com.jfinal.kit.JsonKit;
import com.qysoft.rapid.domain.Bean;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by shenjinxiang on 2017-08-24.
 */
public class RapidAction extends Controller {

    /**
     * 获取所有请求中的参数并封装成MAP
     * @return
     */
    protected Map<String, Object> getParams() {
        HttpServletRequest request = getRequest();
        Map<String, Object> params = new HashMap<String, Object>();
        for (Enumeration<String> iter = request.getParameterNames(); iter.hasMoreElements();) {
            String paraName = (String) iter.nextElement();
            params.put(paraName, request.getParameter(paraName));
        }
        return params;
    }

    /**
     * 获取所有请求中的参数并封装成Bean
     * @return
     */
    protected Bean getBean() {
        HttpServletRequest request = getRequest();
        Bean bean = new Bean();
        for (Enumeration<String> iter = request.getParameterNames(); iter.hasMoreElements();) {
            String paraName = (String) iter.nextElement();
            bean.set(paraName, request.getParameter(paraName));
        }
        return bean;
    }

    /**
     * 清除无效的存库参数
     * @param parameters
     * @param parNames
     */
    public static void clearPar(HashMap<String, Object> parameters,String... parNames){
        for (String string : parNames) {
            parameters.remove(string);
        }
    }

    public void setJson(Object object){
        setAttr("result", object);
    }

    public String toJson(Object object) {
        return JsonKit.toJson(object);
    }
}
