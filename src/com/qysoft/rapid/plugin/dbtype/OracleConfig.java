package com.qysoft.rapid.plugin.dbtype;

import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.wall.WallFilter;
import com.jfinal.config.Plugins;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.CaseInsensitiveContainerFactory;
import com.jfinal.plugin.activerecord.dialect.OracleDialect;
import com.qysoft.rapid.consts.RapidConsts;
import com.qysoft.rapid.plugin.druid.DruidPlugin;
import com.qysoft.rapid.plugin.mybatis.MyBatisPlugin;

/**
 * Created by shenjinxiang on 2017/9/15.
 */
public class OracleConfig implements DbTypeConfig {


    @Override
    public void configPlugin(Plugins plugins) {
        //读取数据库配置
        PropKit.use("config/oracle.properties");
        DruidPlugin dp = new DruidPlugin(PropKit.get("jdbcUrl"), PropKit.get("user"), PropKit.get("password"));
        dp.setDriverClass(PropKit.get("driverClass"));
        dp.addFilter(new StatFilter());
        dp.setMaxActive(PropKit.getInt("dbMaxActive"));
        if (RapidConsts.isIS_DEV_MODE()) {
            dp.setInitialSize(1);
        }else {
            dp.setInitialSize(PropKit.getInt("dbInitSize"));
        }
        WallFilter wall = new WallFilter();
        wall.setDbType(PropKit.get("dbType"));
        dp.addFilter(wall);
        plugins.add(dp);

        //配置数据库操作工具
        //配置ActiveRecord插件
        ActiveRecordPlugin arp = new ActiveRecordPlugin(dp);
        plugins.add(arp);
        arp.setShowSql(RapidConsts.isIS_DEV_MODE());
        // 配置Oracle方言
        arp.setDialect(new OracleDialect());
        // 配置属性名(字段名)大小写不敏感容器工厂
        arp.setContainerFactory(new CaseInsensitiveContainerFactory(true));

        MyBatisPlugin myBatisPlugin = new MyBatisPlugin(dp);
        plugins.add(myBatisPlugin);
        myBatisPlugin.setShowSql(RapidConsts.isIS_DEV_MODE());
    }
}
