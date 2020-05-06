package com.cloud.eureka.client.controller.sms;

import com.cloud.eureka.client.common.request.sms.SendSmsEntity;
import com.cloud.eureka.client.entity.CloudSmsDeal;
import com.cloud.eureka.client.service.CloudSmsDealService;
import com.cloud.eureka.client.service.SendSmsService;
import com.cloud.service.common.response.ApiResult;
import com.cloud.service.common.response.CommonCodeEnum;
import com.cloud.service.common.utils.BeanUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author wentao.qiao
 * @date 2020/04/30.
 */
@Slf4j
public class BaseSmsController {

    private static final long MINUTES = 60;
    static final ThreadLocal<DateFormat> THREAD_LOCAL = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyyMMddHHmmss"));
    /**
     *  短信验证码。默认五分钟有效
     */
    static final long DEFAULT_SMS_CODE_DURATION = 5 * MINUTES;

    @Autowired
    protected SendSmsService sendSmsService;

    @Autowired
    protected CloudSmsDealService cloudSmsDealService;
    @Value("${sms.ignore}")
    protected String smsIgnore;
    @Value("${sms.send}")
    protected boolean smsSend;

    /**
     *  同一请求幂等性校验
     * @param sendSmsEntity
     * @return
     */
    protected ApiResult checkRequestNo(SendSmsEntity sendSmsEntity){
        CloudSmsDeal deal = new CloudSmsDeal();
        deal.setCustomerNo(sendSmsEntity.getCustomerNo());
        deal.setRequestNo(sendSmsEntity.getRequestNo());
        deal.setMobile(sendSmsEntity.getMobile());
        deal.setBizName(sendSmsEntity.getBizName());
        List<CloudSmsDeal> cloudSmsDealList = cloudSmsDealService.selectWithCondition(deal);
        if(cloudSmsDealList.size() > 0){
            log.error("用户编号:{},交易流水号重复", sendSmsEntity.getCustomerNo());
            return ApiResult.buildApiResult(CommonCodeEnum.COMMON_USE.success(),CommonCodeEnum.COMMON_USE.code(),"请求流水号重复");
        }
        //校验用户是否在一分钟内请求
        deal = new CloudSmsDeal();
        deal.setCustomerNo(sendSmsEntity.getCustomerNo());
        deal.setMobile(sendSmsEntity.getMobile());
        deal.setStatus(1);
        deal.setBizName(sendSmsEntity.getBizName());
        cloudSmsDealList = cloudSmsDealService.selectWithCondition(deal);
        if(cloudSmsDealList.size() > 0){
            deal = cloudSmsDealList.get(0);
            //判断是否在一分钟内
            Date now = new Date();
            Date startTime = deal.getStartTime();
            long betweenTime = (now.getTime() - startTime.getTime())/1000;
            if(betweenTime <= MINUTES){
                log.error("用户编号:{},一分钟之内重复发送", sendSmsEntity.getCustomerNo());
                return ApiResult.buildApiResult(CommonCodeEnum.COMMON_USE.success(),CommonCodeEnum.COMMON_USE.code(),"一分钟内重复发送");
            }
        }
        return null;
    }

    /**
     * 组装短信交易表实体
     *
     * @param sendSmsEntity
     * @param code
     * @param duration
     * @return
     * @throws ParseException
     */
    CloudSmsDeal getSmsDeal(SendSmsEntity sendSmsEntity,String code,
                            long duration,Integer chargeStatus) throws ParseException {
        Date startTime = THREAD_LOCAL.get().parse(THREAD_LOCAL.get().format(new Date()));
        CloudSmsDeal smsDeal = new CloudSmsDeal();
        BeanUtil.copyEntity(sendSmsEntity, smsDeal);
        smsDeal.setCode(code);
        smsDeal.setStartTime(startTime);
        smsDeal.setEndTime(new Date((startTime.getTime() + duration * 1000)));
        smsDeal.setChargeStatus(chargeStatus);
        return smsDeal;
    }

}
