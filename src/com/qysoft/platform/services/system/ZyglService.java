package com.qysoft.platform.services.system;

import com.jfinal.aop.Before;
import com.qysoft.platform.mappers.system.ZyxxMapper;
import com.qysoft.rapid.aop.annotation.AutoMapper;
import com.qysoft.rapid.aop.interceptor.MyBatisDbConnTx;
import com.qysoft.rapid.domain.Bean;

import java.util.List;

/**
 * Created by shenjinxiang on 2017-09-25.
 */
@Before(MyBatisDbConnTx.class)
public class ZyglService {

    @AutoMapper
    private ZyxxMapper zyxxMapper;

    public List<Bean> queryZyxxList() {
        List<Bean> list = zyxxMapper.queryYjzyxxList();
        for (Bean bean : list) {
            List<Bean> ejZyList = zyxxMapper.queryEjzyxxListBySjzyid(bean.getStr("zyid"));
            bean.set("ejzyList", ejZyList);
        }
        return list;
    }
}
