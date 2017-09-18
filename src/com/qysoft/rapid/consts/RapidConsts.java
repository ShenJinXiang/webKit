package com.qysoft.rapid.consts;

import com.qysoft.rapid.plugin.dbtype.PlatformDbType;

/**
 * Created by shenjinxiang on 2017-08-23.
 */
public final class RapidConsts {

    /**
     * 平台名称
     */
    public final static String RAPID_NAME = "RAPID";

    /**
     * 平台版本
     */
    public final static String RAPID_VERSION = "1.0";


    /**
     * 数据库类型， 默认为mysql数据库
     */
    public static PlatformDbType DB_TYPE = PlatformDbType.MYSQL;

    /**
     * 是否开发者模式
     */
    private static boolean IS_DEV_MODE = false;

    /**
     * 加密盐值
     */
    public final static String MD5_SALT = "SXQYRJ_ACCOUNTCLOUD_2015";

    /**
     * 错误提示
     */
    private static String ERROR_MSG = "操作失败，请联系管理员！";


    public static void setDbType(PlatformDbType dbType) {
        DB_TYPE = dbType;
    }

    /**
     * 获取默认错误提示
     * @return
     */
    public static String getERROR_MSG() {
        return ERROR_MSG;
    }

    /**
     * 设置错误提示
     * @param eRROR_MSG
     */
    public static void setERROR_MSG(String eRROR_MSG) {
        ERROR_MSG = eRROR_MSG;
    }

    /**
     * 是否开发模式
     * @return
     */
    public static boolean isIS_DEV_MODE() {
        return IS_DEV_MODE;
    }

    /**
     * 设置是否开发模式
     * @param iS_DEV_MODE
     */
    public static void setIS_DEV_MODE(boolean iS_DEV_MODE) {
        IS_DEV_MODE = iS_DEV_MODE;
    }
}
