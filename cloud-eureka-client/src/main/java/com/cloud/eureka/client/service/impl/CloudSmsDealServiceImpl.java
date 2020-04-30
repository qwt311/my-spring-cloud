package com.cloud.eureka.client.service.impl;

import com.cloud.eureka.client.dao.CloudSmsDealMapper;
import com.cloud.eureka.client.entity.CloudSmsDeal;
import com.cloud.eureka.client.service.CloudSmsDealService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author wentao.qiao
 * @date 2020/04/30.
 */
@Service
public class CloudSmsDealServiceImpl implements CloudSmsDealService {
    private static final Logger logger = LoggerFactory.getLogger(CloudSmsDealServiceImpl.class);

    @Autowired
    private CloudSmsDealMapper cloudSmsDealMapper;

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public boolean insertSmsDeal(CloudSmsDeal smsDeal) {
        logger.info("新增发送短信交易:{}",smsDeal);
        return cloudSmsDealMapper.insertSelective(smsDeal) == 1;
    }

    @Override
    @Transactional(readOnly = true,rollbackFor = {Exception.class})
    public List<CloudSmsDeal> selectWithCondition(CloudSmsDeal smsDeal) {
        logger.info("根据条件查询发送短信信息:{}",smsDeal);
        return cloudSmsDealMapper.selectWithCondition(smsDeal);
    }
}
