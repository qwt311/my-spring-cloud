package com.cloud.server.client.dao;

import com.cloud.server.client.entity.CloudAuthUserPersonal;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface CloudAuthUserPersonalMapper {
    
    /**
     * 根据条件查询个人用户信息鉴权数据
     * @param personal
     * @return
     */
	List<CloudAuthUserPersonal> selectListWithConditionNew(CloudAuthUserPersonal personal);

	int insertSelectiveNew(CloudAuthUserPersonal personal);
}