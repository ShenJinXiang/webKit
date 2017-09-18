package com.qysoft.rapid.plugin.mybatis;

import com.jfinal.plugin.IPlugin;
import org.apache.ibatis.datasource.DataSourceFactory;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import java.io.Reader;

/**
 * Created by shenjinxiang on 2017-08-23.
 */
public class MyBatisPlugin implements IPlugin {

    private static SqlSessionFactory sqlSessionFactory;
    private static Reader reader;
    private DataSourceFactory ds = null;

    public MyBatisPlugin(DataSourceFactory ds){
        this.ds = ds;
    }

    public static SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }

    @Override
    public boolean start() {
        try {
            reader = Resources.getResourceAsReader("config/mybatis/Configuration.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            Environment environment = new Environment("druid", new JdbcTransactionFactory(), ds.getDataSource());
            sqlSessionFactory.getConfiguration().setEnvironment(environment);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean stop() {
        sqlSessionFactory = null;
        return true;
    }

    /**
     * 是否打印sql
     * @param isShowSql
     */
    public void setShowSql(boolean isShowSql){
        if (isShowSql) {
            org.apache.ibatis.logging.LogFactory.useSlf4jLogging();
            org.apache.ibatis.logging.LogFactory.useLog4JLogging();
            org.apache.ibatis.logging.LogFactory.useJdkLogging();
            org.apache.ibatis.logging.LogFactory.useCommonsLogging();
            org.apache.ibatis.logging.LogFactory.useStdOutLogging();
        }
    }
}
