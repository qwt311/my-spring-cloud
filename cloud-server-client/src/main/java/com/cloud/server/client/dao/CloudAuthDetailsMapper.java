package com.cloud.server.client.dao;

import com.cloud.server.client.entity.CloudAuthDetailsWithBLOBs;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface CloudAuthDetailsMapper {

    /**
     *
     * @mbggenerated 2020-04-30
     */
    int insertSelective(CloudAuthDetailsWithBLOBs record);

    /**
     *
     * @mbggenerated 2020-04-30
     */
    int updateByPrimaryKeySelective(CloudAuthDetailsWithBLOBs record);


}