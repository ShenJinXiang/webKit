package com.qysoft.platform.services;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

/**
 * Created by shenjinxiang on 2017/9/11.
 */
public class FwhService {

    public Record getFwuById(String id) {
        Record fwh = Db.findById("wx_fwh", "id", id);
        return fwh;
    }
}
