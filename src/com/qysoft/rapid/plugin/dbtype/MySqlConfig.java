package com.qysoft.rapid.plugin.dbtype;

import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.wall.WallFilter;
import com.jfinal.config.Plugins;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.qysoft.rapid.consts.RapidConsts;
import com.qysoft.rapid.plugin.druid.DruidPlugin;
import com.qysoft.rapid.plugin.mybatis.MyBatisPlugin;

/**
 * Created by shenjinxiang on 2017/9/15.
 */
public class MySqlConfig implements DbTypeConfig {


    @Override
    public void configPlugin(Plugins plugins) {
        PropKit.use("config/mysql.properties");
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

        MyBatisPlugin myBatisPlugin = new MyBatisPlugin(dp);
        plugins.add(myBatisPlugin);
        myBatisPlugin.setShowSql(RapidConsts.isIS_DEV_MODE());
    }
}
