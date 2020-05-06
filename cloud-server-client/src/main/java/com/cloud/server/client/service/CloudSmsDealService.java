package com.cloud.server.client.service;

import com.cloud.server.client.entity.CloudSmsDeal;

import java.util.List;

/**
 * @author wentao.qiao
 * @date 2020/04/30.
 */
public interface CloudSmsDealService {

    /**
     * 新增一条发送短信记录
     * @param smsDeal
     * @return
     */
    boolean insertSmsDeal(CloudSmsDeal smsDeal);

    /**
     * 根据条件查询短信信息
     * @param smsDeal
     * @return
     */
    List<CloudSmsDeal> selectWithCondition(CloudSmsDeal smsDeal);
}
