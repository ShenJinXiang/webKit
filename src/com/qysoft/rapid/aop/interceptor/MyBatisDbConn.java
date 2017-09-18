package com.qysoft.rapid.aop.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.qysoft.rapid.aop.FieldGenerator;
import com.qysoft.rapid.consts.RapidConsts;
import com.qysoft.rapid.exceptions.BizException;
import com.qysoft.rapid.exceptions.RapidException;
import com.qysoft.rapid.plugin.mybatis.MyBatisSessionManager;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

/**
 * Created by shenjinxiang on 2017/9/17.
 */
public class MyBatisDbConn implements Interceptor {

    private final Logger log = Logger.getLogger(MyBatisDbConn.class);

    @Override
    public void intercept(Invocation invocation) {

        boolean createSession = false;
        SqlSession session = MyBatisSessionManager.getSession();
        if (null == session) {
            MyBatisSessionManager.setSession(true);
            createSession = true;
            doLog("MyBatisDbConn: 已获取数据库连接...");
        }
        try {
            FieldGenerator fieldGenerator = new FieldGenerator(invocation.getTarget(), invocation.getMethod().getDeclaringClass());
            fieldGenerator.generate();
            if (createSession) {
                doLog("MyBatisDbConn: 准备执行业务...");
            }
            invocation.invoke();
            if (createSession) {
                doLog("MyBatisDbConn: 业务已执行...");
            }
        } catch (BizException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RapidException(e);
        } finally {
            if (createSession) {
                MyBatisSessionManager.closeSession();
                doLog("MyBatisDbConn: 连接已释放...");
            }
        }
    }

    private void doLog(String message){
        if (RapidConsts.isIS_DEV_MODE()) {
            log.warn(message);
        }
    }
}
