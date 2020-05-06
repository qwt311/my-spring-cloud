/*
Navicat MySQL Data Transfer

Source Server         : 私有数据库
Source Server Version : 50728
Source Host           : 39.107.44.66:3306
Source Database       : project

Target Server Type    : MYSQL
Target Server Version : 50728
File Encoding         : 65001

Date: 2020-05-06 13:51:04
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for cloud_auth_details
-- ----------------------------
DROP TABLE IF EXISTS `cloud_auth_details`;
CREATE TABLE `cloud_auth_details` (
  `id` varchar(64) NOT NULL COMMENT '鉴权信息记录表主键id',
  `user_req_json` text NOT NULL COMMENT '用户请求报文',
  `auth_req_json` text COMMENT '鉴权请求报文',
  `auth_req_time` datetime DEFAULT NULL COMMENT '鉴权请求时间',
  `auth_res_json` text COMMENT '鉴权响应报文',
  `auth_res_time` datetime DEFAULT NULL COMMENT '鉴权响应时间',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='鉴权记录详情表';

-- ----------------------------
-- Table structure for cloud_auth_key
-- ----------------------------
DROP TABLE IF EXISTS `cloud_auth_key`;
CREATE TABLE `cloud_auth_key` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `code` varchar(64) NOT NULL COMMENT '鉴权编号（LIMIT_2-二要素，LIMIT_3-三要素，LIMIT-四要素）',
  `msg` varchar(64) NOT NULL COMMENT '鉴权描述',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `status` int(1) NOT NULL DEFAULT '0' COMMENT '鉴权接口状态(0-禁用，1-正常)',
  `remarks1` varchar(64) DEFAULT NULL COMMENT '备注1',
  `remarks2` varchar(64) DEFAULT NULL COMMENT '备注2',
  `remarks3` varchar(64) DEFAULT NULL COMMENT '备注3',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='鉴权要素配置表';

-- ----------------------------
-- Table structure for cloud_auth_record_info
-- ----------------------------
DROP TABLE IF EXISTS `cloud_auth_record_info`;
CREATE TABLE `cloud_auth_record_info` (
  `id` varchar(64) NOT NULL COMMENT '自定义主键',
  `request_no` varchar(64) NOT NULL COMMENT '请求流水号',
  `customer_no` varchar(64) NOT NULL COMMENT '平台编号',
  `auth_type` int(1) NOT NULL COMMENT '鉴权类型(0-个人，1-企业)',
  `status` int(2) NOT NULL DEFAULT '3' COMMENT '认证状态(0-成功，1-认证中，2-认证失败，3-未认证，4-认证出错)',
  `auth_code` varchar(64) NOT NULL COMMENT '鉴权编号（LIMIT_2-二要素，LIMIT_3-三要素，LIMIT-四要素）',
  `create_time` datetime NOT NULL COMMENT '请求时间',
  `response_time` datetime DEFAULT NULL COMMENT '鉴权响应时间',
  `use_channel` int(1) DEFAULT '0' COMMENT '是否使用鉴权渠道(0-未使用，1-使用)',
  `request_data` varchar(512) DEFAULT NULL COMMENT '请求数据',
  `charge_status` int(1) NOT NULL DEFAULT '0' COMMENT '计费状态(0-初始化，1-计费成功，2-欠费)',
  `charge_time` datetime DEFAULT NULL COMMENT '计费时间',
  `remarks1` varchar(64) DEFAULT NULL COMMENT '备注1',
  `remarks2` varchar(64) DEFAULT NULL COMMENT '备注2',
  `remarks3` varchar(64) DEFAULT NULL COMMENT '备注3',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='鉴权信息调用记录表';

-- ----------------------------
-- Table structure for cloud_auth_user_ent
-- ----------------------------
DROP TABLE IF EXISTS `cloud_auth_user_ent`;
CREATE TABLE `cloud_auth_user_ent` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `code` varchar(64) DEFAULT NULL COMMENT '企业统一社会信用代码(统一社会信用代码和印业执照号码必须有一个有值)',
  `name` varchar(64) NOT NULL COMMENT '企业名称',
  `legal_name` varchar(64) NOT NULL COMMENT '法人姓名',
  `org_code` varchar(64) DEFAULT NULL COMMENT '组织机构代码',
  `licence_code` varchar(64) DEFAULT NULL COMMENT '营业执照号码(统一社会信用代码和印业执照号码必须有一个有值)',
  `auth_time` datetime NOT NULL COMMENT '通过鉴权时间',
  `remarks1` varchar(64) DEFAULT NULL COMMENT '备注1',
  `remarks2` varchar(64) DEFAULT NULL COMMENT '备注2',
  `remarks3` varchar(64) DEFAULT NULL COMMENT '备注3',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='鉴权企业用户黑库表';

-- ----------------------------
-- Table structure for cloud_auth_user_personal
-- ----------------------------
DROP TABLE IF EXISTS `cloud_auth_user_personal`;
CREATE TABLE `cloud_auth_user_personal` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '  自增主键',
  `id_card_no` varchar(64) NOT NULL COMMENT '身份证号码',
  `name` varchar(64) NOT NULL COMMENT '姓名',
  `phone` varchar(64) DEFAULT NULL COMMENT '手机号码',
  `bank` varchar(32) NOT NULL COMMENT '银行编码(银行开户行对应的银行编码)',
  `bank_card_no` varchar(64) NOT NULL COMMENT '银行卡号码',
  `bank_phone` varchar(64) NOT NULL COMMENT '银行预留手机号码',
  `auth_time` datetime NOT NULL COMMENT '通过鉴权时间',
  `remarks1` varchar(64) DEFAULT NULL COMMENT '备注1',
  `remarks2` varchar(64) DEFAULT NULL COMMENT '备注2',
  `remarks3` varchar(64) DEFAULT NULL COMMENT '备注3',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='鉴权个人用户黑库表';

-- ----------------------------
-- Table structure for cloud_sms_deal
-- ----------------------------
DROP TABLE IF EXISTS `cloud_sms_deal`;
CREATE TABLE `cloud_sms_deal` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `request_no` varchar(64) DEFAULT NULL COMMENT '请求流水号',
  `product_id` bigint(64) DEFAULT NULL COMMENT '产品编号，对应lm_dic_product产品字典表主键',
  `customer_no` varchar(64) DEFAULT NULL COMMENT '懒猫分配开发者编号',
  `mobile` varchar(20) DEFAULT NULL COMMENT '用户手机号码',
  `code` varchar(16) DEFAULT NULL COMMENT '验证码',
  `start_time` datetime DEFAULT NULL COMMENT '验证码生效时间',
  `end_time` datetime DEFAULT NULL COMMENT '验证码失效时间',
  `request_time` datetime DEFAULT NULL COMMENT '请求时间',
  `status` int(2) DEFAULT NULL COMMENT '0-失败，1-成功',
  `charge_status` int(2) DEFAULT NULL COMMENT '0-初始化，1-计费成功，2-欠费',
  `charge_time` datetime DEFAULT NULL COMMENT '计费时间',
  `biz_name` varchar(64) DEFAULT NULL COMMENT '短信通道名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='短信调用成功交易表';
SET FOREIGN_KEY_CHECKS=1;
