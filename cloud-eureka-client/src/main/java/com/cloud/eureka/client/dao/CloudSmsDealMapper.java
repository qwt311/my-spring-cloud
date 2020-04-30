package com.cloud.eureka.client.dao;

import com.cloud.eureka.client.entity.CloudSmsDeal;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface CloudSmsDealMapper {

    /**
     *
     * @mbggenerated 2020-04-30
     */
    int insertSelective(CloudSmsDeal record);

    /**
     * 根据条件查询短信信息
     * @param smsDeal
     * @return
     */
    List<CloudSmsDeal> selectWithCondition(CloudSmsDeal smsDeal);
}