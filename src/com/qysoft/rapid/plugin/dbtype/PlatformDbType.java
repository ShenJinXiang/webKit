package com.qysoft.rapid.plugin.dbtype;

/**
 * Created by shenjinxiang on 2017-08-23.
 */
public enum PlatformDbType {

    /**
     * ORACLE数据库
     */
    ORACLE(new OracleConfig()),

    /**
     * MYSQL数据库
     */
    MYSQL(new MySqlConfig());


    private DbTypeConfig dbTypeConfig;

    private PlatformDbType(DbTypeConfig dbTypeConfig) {
        this.dbTypeConfig = dbTypeConfig;
    }

    public DbTypeConfig getDbTypeConfig() {
        return this.dbTypeConfig;
    }

}
