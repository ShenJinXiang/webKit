package com.qysoft.platform.mappers;

import com.qysoft.rapid.domain.Bean;

import java.util.List;

/**
 * Created by shenjinxiang on 2017/9/7.
 */
public interface AreaMapper {

    List<Bean> queryAreaList();

    Bean queryById(Bean param);
}
