package com.cloud.server.client.dao;

import com.cloud.server.client.entity.CloudAuthRecordInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface CloudAuthRecordInfoMapper {

    /**
     *
     * @mbggenerated 2018-06-05
     */
    int insertSelective(CloudAuthRecordInfo record);

    /**
     *
     * @mbggenerated 2018-06-05
     */
    int updateByPrimaryKeySelective(CloudAuthRecordInfo record);

	CloudAuthRecordInfo selectByRequestNo(String requestNo);
}