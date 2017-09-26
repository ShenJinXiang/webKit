package com.qysoft.platform.mappers.system;

import com.qysoft.rapid.domain.Bean;

import java.util.List;

/**
 * Created by shenjinxiang on 2017-09-25.
 */
public interface ZyxxMapper {

    List<Bean> queryYjzyxxList();

    List<Bean> queryEjzyxxListBySjzyid(String sjzyid);

    List<Bean> queryZyxxListBySjzyid(Bean params);
}
