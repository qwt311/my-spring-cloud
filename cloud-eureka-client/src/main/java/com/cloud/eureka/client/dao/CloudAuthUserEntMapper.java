package com.cloud.eureka.client.dao;

import com.cloud.eureka.client.entity.CloudAuthUserEnt;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface CloudAuthUserEntMapper {

    /**
     *
     * @mbggenerated 2018-04-10
     */
    int insertSelective(CloudAuthUserEnt record);

    /**
     * 根据条件查询企业实名鉴权黑库数据
     * @param record
     * @return
     */
    List<CloudAuthUserEnt> selectListWithCondition(CloudAuthUserEnt record);
}