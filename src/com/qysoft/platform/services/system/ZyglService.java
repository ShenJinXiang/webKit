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

    public void queryZyxxList(String sjzyid, List<Bean> list) {
        Bean params = new Bean();
        params.set("sjzyid", sjzyid);
        List<Bean> list1 = zyxxMapper.queryZyxxListBySjzyid(params);
        for (Bean zyxx : list1) {
            list.add(zyxx);
            long xjzy_size = zyxx.getLong("xjzy_size");
            if (xjzy_size > 0) {
                queryZyxxList(zyxx.getStr("zyid"), list);
            }
        }

    }
}
