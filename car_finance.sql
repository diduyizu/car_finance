-- phpMyAdmin SQL Dump
-- version 4.1.4
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: 2015-04-01 14:07:40
-- 服务器版本： 5.6.11
-- PHP Version: 5.5.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `car_finance`
--

DELIMITER $$
--
-- 函数
--
CREATE DEFINER=`root`@`localhost` FUNCTION `currval`(`seq_name` VARCHAR(50) CHARSET utf8) RETURNS int(11)
BEGIN   
  DECLARE value INTEGER;   
  SET value = 0;   
  SELECT current_value INTO value   
  FROM sequence   
  WHERE name = seq_name;   
  RETURN value;   
END$$

CREATE DEFINER=`root`@`localhost` FUNCTION `nextval`(`seq_name` VARCHAR(50) CHARSET utf8) RETURNS int(11)
BEGIN   
   UPDATE sequence   
   SET          current_value = current_value + increment   
   WHERE name = seq_name;   
   RETURN currval(seq_name);   
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- 表的结构 `customer_annex`
--

CREATE TABLE IF NOT EXISTS `customer_annex` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `customer_id` int(11) NOT NULL COMMENT '客户id',
  `annex_name` varchar(64) DEFAULT NULL COMMENT '附件名',
  `url` text COMMENT '附件地址',
  PRIMARY KEY (`id`),
  KEY `customer_id` (`customer_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='客户附件表' AUTO_INCREMENT=9 ;

--
-- 转存表中的数据 `customer_annex`
--

INSERT INTO `customer_annex` (`id`, `customer_id`, `annex_name`, `url`) VALUES
(5, 2, 'Chrysanthemum_副本', '/files/upload/customer/2/a29fdbc2-86eb-4a7b-bdd2-7bc54f8f9f01.jpg'),
(6, 2, 'Desert_副本', '/files/upload/customer/2/88bfefc8-1e89-4228-8025-394b0f3dc739.jpg'),
(7, 2, 'Koala_副本', '/files/upload/customer/2/ac98b22a-2150-42c3-9468-e9fc0a70809a.jpg'),
(8, 2, 'Penguins_副本', '/files/upload/customer/2/d2d2a80e-96c4-4142-833c-b67046d732db.jpg');

-- --------------------------------------------------------

--
-- 表的结构 `customer_info`
--

CREATE TABLE IF NOT EXISTS `customer_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `customer_name` varchar(64) NOT NULL COMMENT '客户姓名',
  `certificate_type` varchar(32) NOT NULL DEFAULT '身份证' COMMENT '身份证，国际护照，回乡证，台胞证',
  `certificate_no` varchar(32) NOT NULL COMMENT '证件号码',
  `certificate_url` varchar(128) DEFAULT NULL,
  `certificate_name` varchar(64) DEFAULT NULL,
  `customer_dn` varchar(32) NOT NULL COMMENT '客户手机号',
  `customer_email` varchar(64) DEFAULT NULL COMMENT '客户邮箱地址',
  `create_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` int(11) NOT NULL COMMENT '创建人',
  `update_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `update_by` int(11) DEFAULT '0' COMMENT '修改人',
  `customer_type` varchar(32) NOT NULL DEFAULT '个人用户',
  `customer_house` varchar(64) DEFAULT '无' COMMENT '客户房产',
  `customer_vehicle` varchar(64) DEFAULT '无' COMMENT '客户车辆',
  `customer_guarantee` varchar(64) DEFAULT '无' COMMENT '客户担保人或担保公司',
  `vip_no` varchar(32) DEFAULT NULL COMMENT '会员号',
  `identity_name` varchar(128) DEFAULT NULL,
  `identity_url` varchar(128) DEFAULT NULL,
  `house_property_name` varchar(128) DEFAULT NULL,
  `house_property_url` varchar(128) DEFAULT NULL,
  `driving_license_name` varchar(128) DEFAULT NULL,
  `driving_license_url` varchar(128) DEFAULT NULL,
  `other_name` varchar(128) DEFAULT NULL,
  `other_url` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `identity_id` (`certificate_no`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='客户信息表' AUTO_INCREMENT=4 ;

--
-- 转存表中的数据 `customer_info`
--

INSERT INTO `customer_info` (`id`, `customer_name`, `certificate_type`, `certificate_no`, `certificate_url`, `certificate_name`, `customer_dn`, `customer_email`, `create_at`, `create_by`, `update_at`, `update_by`, `customer_type`, `customer_house`, `customer_vehicle`, `customer_guarantee`, `vip_no`, `identity_name`, `identity_url`, `house_property_name`, `house_property_url`, `driving_license_name`, `driving_license_url`, `other_name`, `other_url`) VALUES
(2, 'sfdsa', '回乡证', '12313123213', NULL, NULL, '3213', NULL, '2015-03-15 00:17:32', 100000, '2015-03-15 00:17:32', 100000, '个人用户', '123213', '132123', '12321312', '0001231231', 'IMG_0017', '/files/upload/customer/annex/2/ef2868a8-c6fd-4fce-9811-bd3a374f429c.JPG', NULL, NULL, 'IMG_0027', '/files/upload/customer/annex/2/76723978-4bf6-49b8-9182-e66b26b1a776.JPG', NULL, NULL),
(3, '123', '身份证', '321281198609041875', NULL, NULL, '15380897663', NULL, '2015-03-23 05:47:41', 100000, '2015-03-23 05:47:41', 100000, '个人用户', '1', '', '', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- 表的结构 `property_contrace`
--

CREATE TABLE IF NOT EXISTS `property_contrace` (
  `id` int(11) NOT NULL COMMENT '主键',
  `contrace_no` varchar(64) DEFAULT NULL COMMENT '合同编号',
  `customer_name` varchar(64) DEFAULT NULL COMMENT '客户姓名',
  `customer_type` varchar(64) NOT NULL DEFAULT '个人用户' COMMENT '客户类型',
  `customer_dn` varchar(64) DEFAULT NULL COMMENT '客户手机号',
  `customer_cer_type` varchar(64) NOT NULL DEFAULT '身份证' COMMENT '证件类型',
  `customer_cer_no` varchar(64) DEFAULT NULL COMMENT '证件号码',
  `remark` varchar(256) DEFAULT NULL COMMENT '备注',
  `employee_id` varchar(64) NOT NULL DEFAULT '0' COMMENT '业务员工号/id',
  `employee_name` varchar(64) DEFAULT NULL COMMENT '业务员名称',
  `create_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` int(11) DEFAULT '0' COMMENT '创建人',
  `update_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` int(11) DEFAULT '0' COMMENT '更新人',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '审核状态：0-初始状态;1－待审核;2-店长审核通过;3-市公司店长审核通过;4-区域经理审核通过;5-财务通过;6-结单;-1店长驳回；-2:门店店长审核不通过',
  `shopowner_update_by` int(11) NOT NULL DEFAULT '0' COMMENT '店长审核人',
  `shopowner_update_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '店长审核时间',
  `city_shopowner_update_by` int(11) DEFAULT '0' COMMENT '市店长审核人',
  `city_shopowner_update_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '市店长审核时间',
  `finance_update_by` int(11) NOT NULL DEFAULT '0' COMMENT '财务审核人',
  `finance_update_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '财务审核时间',
  `org_id` int(11) NOT NULL DEFAULT '0' COMMENT '该合同归属组织id',
  `reservation_id` int(11) NOT NULL DEFAULT '0' COMMENT '该合同对应预约单id',
  `regional_manager_update_by` int(11) DEFAULT '0' COMMENT '区域经理审核人',
  `regional_manager_update_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '区域经理审核时间',
  `isovertop` int(11) NOT NULL DEFAULT '0' COMMENT '是否超过限价0-否;1-是；如果是，则需要市门店经理及以上角色审核',
  `sign_at` date DEFAULT NULL COMMENT '合同签订日期',
  `period_number` int(11) DEFAULT '1' COMMENT '合同期限，x个月',
  `down_payment` decimal(10,2) DEFAULT NULL COMMENT '首付款',
  `lease_price` decimal(10,2) DEFAULT NULL COMMENT '合同租赁价格',
  `monthly_payment` decimal(10,2) DEFAULT NULL COMMENT '月付款',
  `arrange_payment` decimal(10,2) DEFAULT NULL COMMENT '协商月付',
  `monthly_day` int(11) DEFAULT '1' COMMENT '月付款日',
  `final_payment` decimal(10,2) DEFAULT NULL COMMENT '尾款',
  `received_periods` int(11) DEFAULT NULL COMMENT '已收期数',
  `already_back_amount` decimal(10,2) DEFAULT NULL COMMENT '已回款金额',
  `payment_type` varchar(32) DEFAULT NULL COMMENT '付款方式',
  `reserv_to_contrace_status` int(11) NOT NULL DEFAULT '0' COMMENT '预约单未转成合同',
  PRIMARY KEY (`id`),
  KEY `reserv_to_contrace_status` (`reserv_to_contrace_status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='产权租赁合同表';

--
-- 转存表中的数据 `property_contrace`
--

INSERT INTO `property_contrace` (`id`, `contrace_no`, `customer_name`, `customer_type`, `customer_dn`, `customer_cer_type`, `customer_cer_no`, `remark`, `employee_id`, `employee_name`, `create_at`, `create_by`, `update_at`, `update_by`, `status`, `shopowner_update_by`, `shopowner_update_at`, `city_shopowner_update_by`, `city_shopowner_update_at`, `finance_update_by`, `finance_update_at`, `org_id`, `reservation_id`, `regional_manager_update_by`, `regional_manager_update_at`, `isovertop`, `sign_at`, `period_number`, `down_payment`, `lease_price`, `monthly_payment`, `arrange_payment`, `monthly_day`, `final_payment`, `received_periods`, `already_back_amount`, `payment_type`, `reserv_to_contrace_status`) VALUES
(100044, '00123213', '22131', '个人用户', '12313', '身份证', '12321313', '132213aaa', '012313', '123213', '2015-03-16 08:21:59', 100000, '2015-03-16 08:21:59', 0, 6, 100000, '2015-03-17 01:50:27', 100000, '2015-03-17 02:37:00', 100000, '2015-03-17 02:40:17', 1, 34, 100000, '2015-03-17 02:40:06', 1, '2015-03-17', 1231231, '12313.00', '123213.00', '1231.00', '123213.00', 123213, '-124813.00', 123219, '371239.00', '123213', 1),
(100046, '0012313', '12313', '个人用户', '11313', '身份证', '321281198609041875', '12313123', '111111', '111111', '2015-03-23 05:31:35', 100000, '2015-03-23 05:31:35', 0, 6, 100000, '2015-03-29 01:06:36', 100000, '2015-03-29 01:06:49', 100000, '2015-03-29 01:07:11', 1, 36, 100000, '2015-03-29 01:06:59', 1, NULL, 1231, '132.00', '123.00', '132.00', '123.00', 123, '21.00', 133, '234.00', '132', 1),
(100049, NULL, NULL, '个人用户', NULL, '身份证', NULL, NULL, '0', NULL, '2015-03-23 07:36:58', 100000, '2015-03-23 07:36:58', 0, 0, 0, '2015-03-23 07:36:58', 0, '2015-03-23 07:36:58', 0, '2015-03-23 07:36:58', 1, 39, 0, '2015-03-23 07:36:58', 0, NULL, 1, NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, 0),
(100051, '0012321313', '123', '个人用户', '123', '身份证', '321281198609041875', '123', '111111', '111111', '2015-03-23 09:59:22', 100000, '2015-03-23 09:59:22', 0, 6, 100000, '2015-03-23 10:00:42', 0, '2015-03-23 09:59:22', 100000, '2015-03-23 10:00:50', 1, 41, 0, '2015-03-23 09:59:22', 0, NULL, 123, '123.00', '123.00', '123.00', '123.00', 123, '12.00', 124, '234.00', '123', 1),
(100052, '20150329100052', '123', '个人用户', '1213', '身份证', '321281198609041875', '1231313', '111111', '111111', '2015-03-29 02:00:35', 100000, '2015-03-29 02:00:35', 0, 6, 100000, '2015-03-29 02:01:35', 100000, '2015-03-29 02:01:41', 100000, '2015-03-29 02:01:57', 1, 42, 100000, '2015-03-29 02:01:51', 1, NULL, 12, '12.00', '12.00', '12.00', '12.00', 12, '0.00', 13, '24.00', '12', 1);

-- --------------------------------------------------------

--
-- 表的结构 `property_contrace_repayment_log`
--

CREATE TABLE IF NOT EXISTS `property_contrace_repayment_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `contrace_id` int(11) NOT NULL COMMENT '合同id',
  `should_payment` decimal(10,2) NOT NULL COMMENT '应付月租',
  `actual_payment` decimal(10,2) NOT NULL COMMENT '实付月租',
  `create_at` date NOT NULL COMMENT '创建时间',
  `create_by` int(11) NOT NULL COMMENT '创建人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COMMENT='产权租月还款详细表' AUTO_INCREMENT=9 ;

--
-- 转存表中的数据 `property_contrace_repayment_log`
--

INSERT INTO `property_contrace_repayment_log` (`id`, `contrace_id`, `should_payment`, `actual_payment`, `create_at`, `create_by`) VALUES
(1, 100044, '123213.00', '123213.00', '2015-03-17', 100000),
(2, 100044, '123213.00', '123.00', '2015-03-20', 100000),
(3, 100044, '-246549.00', '123.00', '2015-03-20', 100000),
(4, 100044, '-246672.00', '1231.00', '2015-03-20', 100000),
(5, 100044, '-247903.00', '123.00', '2015-03-20', 100000),
(6, 100051, '0.00', '111.00', '2015-03-23', 100000),
(7, 100046, '0.00', '111.00', '2015-03-29', 100000),
(8, 100052, '0.00', '12.00', '2015-03-29', 100000);

-- --------------------------------------------------------

--
-- 表的结构 `sequence`
--

CREATE TABLE IF NOT EXISTS `sequence` (
  `name` varchar(50) NOT NULL,
  `current_value` int(11) NOT NULL,
  `increment` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `sequence`
--

INSERT INTO `sequence` (`name`, `current_value`, `increment`) VALUES
('ContraceSeq', 100053, 1),
('ForeignSeq', 100001, 1),
('StoreSeq', 100014, 1),
('UsersSeq', 100023, 1);

-- --------------------------------------------------------

--
-- 表的结构 `sys_city`
--

CREATE TABLE IF NOT EXISTS `sys_city` (
  `city_id` int(11) NOT NULL COMMENT '地市id，以区号明明',
  `city_name` varchar(11) NOT NULL COMMENT '地市名称',
  `province_id` int(11) NOT NULL COMMENT '该地市对应的省份id，省份信息，存在sys_enum表中',
  `status` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`city_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='地市表';

--
-- 转存表中的数据 `sys_city`
--

INSERT INTO `sys_city` (`city_id`, `city_name`, `province_id`, `status`) VALUES
(25, '南京', 100025, 0),
(510, '无锡', 100025, 0),
(511, '镇江', 100025, 0),
(512, '苏州', 100025, 0),
(513, '南通', 100025, 0),
(514, '扬州', 100025, 1),
(515, '盐城', 100025, 0),
(516, '徐州', 100025, 0),
(517, '淮安', 100025, 0),
(518, '连云港', 100025, 0),
(519, '常州', 100025, 0),
(523, '泰州', 100025, 1),
(527, '宿迁', 100025, 0);

-- --------------------------------------------------------

--
-- 表的结构 `sys_country`
--

CREATE TABLE IF NOT EXISTS `sys_country` (
  `country_id` int(11) NOT NULL COMMENT '区县id',
  `country_name` varchar(11) NOT NULL COMMENT '区县名称',
  `city_id` int(11) NOT NULL COMMENT '该区县归属地市id，与sys_city关联',
  PRIMARY KEY (`country_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `sys_country`
--

INSERT INTO `sys_country` (`country_id`, `country_name`, `city_id`) VALUES
(25001, '玄武区', 25),
(25002, '白下区', 25),
(25003, '秦淮区', 25),
(25004, '建邺区', 25),
(25005, '鼓楼区', 25),
(25006, '下关区', 25),
(25007, '雨花台区', 25),
(25008, '浦口区', 25);

-- --------------------------------------------------------

--
-- 表的结构 `sys_enum`
--

CREATE TABLE IF NOT EXISTS `sys_enum` (
  `fiel_name` varchar(64) NOT NULL COMMENT '枚举名称',
  `enum_value` int(11) NOT NULL COMMENT '枚举值',
  `enum_desc` varchar(64) NOT NULL COMMENT '枚举描述',
  `enum_memo` varchar(64) DEFAULT NULL COMMENT '备注'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `sys_enum`
--

INSERT INTO `sys_enum` (`fiel_name`, `enum_value`, `enum_desc`, `enum_memo`) VALUES
('SYS_PROVINCE', 100025, '江苏省', '江苏省'),
('ORG_TYPE', 10, '总公司', '组织类型-总公司'),
('ORG_TYPE', 11, '区域公司', '组织类型-区域公司'),
('ORG_TYPE', 12, '省公司', '组织类型-省公司'),
('ORG_TYPE', 13, '市公司（一类门店）', '组织类型-市公司（一类门店）'),
('ORG_TYPE', 14, '二类门店', '组织类型-二类门店'),
('ORG_TYPE', 15, '三类门店', '组织类型-三类门店');

-- --------------------------------------------------------

--
-- 表的结构 `sys_menu`
--

CREATE TABLE IF NOT EXISTS `sys_menu` (
  `menu_id` int(11) NOT NULL COMMENT '菜单id',
  `menu_name` varchar(64) NOT NULL COMMENT '菜单名',
  `menu_url` varchar(256) NOT NULL COMMENT '菜单url',
  `pid` int(11) NOT NULL DEFAULT '0' COMMENT '上级菜单，0-根目录',
  `level` int(11) NOT NULL DEFAULT '0' COMMENT '菜单层级，0-最顶层',
  `menu_desc` varchar(64) NOT NULL COMMENT '说明',
  `type` varchar(20) NOT NULL DEFAULT 'page' COMMENT '菜单类型:page，tab，button',
  `css` varchar(20) DEFAULT NULL COMMENT '只有顶层菜单才有',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '0：无效，1：有效',
  `home_page_id` int(11) NOT NULL DEFAULT '0' COMMENT '默认页面id，对应menu_id，只有一级菜单有',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `sys_menu`
--

INSERT INTO `sys_menu` (`menu_id`, `menu_name`, `menu_url`, `pid`, `level`, `menu_desc`, `type`, `css`, `status`, `home_page_id`) VALUES
(10000, '人员管理', '', 0, 0, '人员管理', 'page', 'nav-home', 1, 0),
(10001, '角色管理', 'carfinance/people/role/index', 10000, 0, '角色管理', 'page', NULL, 1, 0),
(10002, '人员管理', 'carfinance/people/people/index', 10000, 0, '人员管理', 'page', NULL, 1, 0),
(10003, '人员角色配置', 'carfinance/people/peoplerole/index', 10000, 0, '人员角色配置', 'page', NULL, 1, 0),
(10004, '角色权限配置', 'carfinance/people/rolemenu/index', 10000, 0, '角色权限配置', 'page', NULL, 1, 0),
(10005, '重置密码', 'carfinance/people/password/reset', 10000, 0, '重置密码', 'page', NULL, 1, 0),
(10006, '修改密码', 'carfinance/people/password/modify', 10000, 0, '修改密码', 'page', NULL, 1, 0),
(20000, '门店管理', '', 0, 0, '门店管理', 'page', 'nav-order', 1, 0),
(20001, '门店查询', 'carfinance/store/query/index', 20000, 0, '门店查询', 'page', NULL, 1, 0),
(20002, '门店新增', 'carfinance/store/add/index', 20000, 0, '门店新增', 'page', NULL, 1, 0),
(20003, '门店修改', 'carfinance/store/modify/index', 20000, 0, '门店修改', 'page', NULL, 1, 0),
(20004, '门店删除', 'carfinance/store/delete/index', 20000, 0, '门店删除', 'page', NULL, 1, 0),
(30000, '车辆管理', '', 0, 0, '车辆管理', 'page', 'nav-storage', 1, 30001),
(30001, '车辆库存状态', 'carfinance/vehicle/register/index', 30000, 0, '车辆入库登记', 'page', NULL, 1, 0),
(30002, '车辆GPS地图暂时', '', 30000, 0, '车辆GPS地图暂时', 'page', NULL, 1, 0),
(30003, '车辆保险记录', 'carfinance/vehicle/insurance/index', 30000, 0, '车辆保险记录录入', 'page', NULL, 1, 0),
(30004, '车辆违章记录', 'carfinance/vehicle/peccancy/index', 30000, 0, '车辆违章记录录入', 'page', NULL, 1, 0),
(30005, '车辆保险到期提醒', 'carfinance/vehicle/insuranceremind/index', 30000, 0, '车辆保险到期提醒', 'page', NULL, 1, 1),
(30006, '车辆违章处理提醒', 'carfinance/vehicle/peccancyremind/index', 30000, 0, '车辆违章处理提醒', 'page', NULL, 1, 0),
(30007, '车辆保养提醒', 'carfinance/vehicle/maintainremind/index', 30000, 0, '车辆保养提醒', 'page', NULL, 1, 0),
(40000, '车辆业务办理', '', 0, 0, '车辆业务办理', 'page', 'nav-product', 1, 0),
(40001, '预约单管理', 'carfinance/vehicleservice/reservation/index', 40000, 0, '车辆预约单管理', 'page', NULL, 1, 0),
(40002, '预约单提醒', 'carfinance/vehicleservice/reservation/remind', 40000, 0, '预约单提醒', 'page', NULL, 1, 0),
(40003, '零租合同管理', 'carfinance/vehicleservice/contrace/index', 40000, 0, '合同管理', 'page', NULL, 1, 0),
(40004, '合同欠租提醒', 'carfinance/vehicleservice/contrace/arrearage/remind', 40000, 0, '合同欠租提醒', 'page', NULL, 1, 0),
(40005, '产权租合同管理', 'carfinance/vehicleservice/contrace/property/index', 40000, 0, '产权租合同管理', 'page', NULL, 1, 0),
(40006, '合同店长审核', 'carfinance/vehicleservice/contrace/shopowner/audit', 40000, 0, '预约单业务经理审核', 'page', NULL, 1, 0),
(40007, '合同市店长审核', 'carfinance/vehicleservice/contrace/cityshopowner/audit', 40000, 0, '合同市店长审核', 'page', NULL, 1, 0),
(40008, '合同区域经理审核', 'carfinance/vehicleservice/contrace/regionalmanager/audit', 40000, 0, '合同区域经理审核', 'page', NULL, 1, 0),
(40009, '合同财务审核', 'carfinance/vehicleservice/contrace/finance/audit', 40000, 0, '预约单财务审核', 'page', NULL, 1, 0),
(40027, '预约单风控审核', 'carfinance/vehicleservice/riskcontrol/audit', 40000, 0, '预约单凤控审核', 'page', NULL, 0, 0),
(50000, '财务管理', '', 0, 0, '财务管理', 'page', 'nav-cost', 1, 0),
(60000, '客户管理', '', 0, 0, '客户管理', 'page', 'nav-user', 1, 0),
(60001, '客户资料维护', 'carfinance/customer/info/index', 60000, 0, '客户录入登记', 'page', NULL, 1, 0),
(70000, '统计模块', '', 0, 0, '统计模块', 'page', 'nav-monitor', 1, 0),
(70001, '台帐', 'carfinance/statistics/standingbook/index', 70000, 0, '台帐', 'page', NULL, 1, 0),
(70002, '报表', 'carfinance/statistics/reportform', 70000, 0, '报表', 'page', NULL, 1, 0),
(70003, '车辆租用/收入信息', 'carfinance/statistics/vehicleincom', 70000, 0, '车辆租用/收入信息', 'page', NULL, 1, 0),
(70004, '业务员绩效', 'carfinance/statistics/achievement', 70000, 0, '业务员绩效', 'page', NULL, 1, 0);

-- --------------------------------------------------------

--
-- 表的结构 `sys_org`
--

CREATE TABLE IF NOT EXISTS `sys_org` (
  `org_id` int(11) NOT NULL COMMENT '组织id',
  `org_name` varchar(64) NOT NULL COMMENT '组织名称',
  `pid` int(11) NOT NULL DEFAULT '0' COMMENT '上层组织id，顶层为0',
  `org_type` int(11) NOT NULL DEFAULT '15' COMMENT '组织类型，sys_enum中ORG_TYPE',
  `org_province` int(11) NOT NULL COMMENT '组织所在省份，sys_enum中SYS_PROVINCE',
  `org_city` int(11) NOT NULL COMMENT '组织所在地市，sys_city',
  `org_country` int(11) NOT NULL COMMENT '组织所在区县，sys_country',
  `org_address` varchar(64) NOT NULL COMMENT '组织所在具体地址，结合省份、地市、区县',
  `org_type_name` varchar(128) DEFAULT NULL,
  `org_province_name` varchar(128) DEFAULT NULL,
  `org_city_name` varchar(128) DEFAULT NULL,
  `org_country_name` varchar(128) DEFAULT NULL,
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '门店状态：0-正常；1-删除',
  PRIMARY KEY (`org_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `sys_org`
--

INSERT INTO `sys_org` (`org_id`, `org_name`, `pid`, `org_type`, `org_province`, `org_city`, `org_country`, `org_address`, `org_type_name`, `org_province_name`, `org_city_name`, `org_country_name`, `status`) VALUES
(1, '总公司', 0, 10, 100025, 514, 51402, '扬州商城国际大厦C-1幢722-723室', '总公司', '江苏省', '扬州市', '邗江区', 0),
(10, '华东区公司', 1, 11, 100025, 25, 25001, '雄狮旁边', '区域公司', '江苏省', '南京', '玄武区', 0),
(20, '江苏省公司', 10, 12, 100025, 25, 25001, '雄狮右边', '省公司', '江苏省', '南京', '玄武区', 0),
(100020, '莱茵店', 20, 13, 100025, 514, 0, '扬州市史可法东路83号', '市公司（一类门店）', '江苏省', '扬州', '', 0);

-- --------------------------------------------------------

--
-- 表的结构 `sys_roles`
--

CREATE TABLE IF NOT EXISTS `sys_roles` (
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `role_name` varchar(64) NOT NULL COMMENT '角色名',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态(0:无效 1:有效)',
  `remark` varchar(64) NOT NULL COMMENT '角色描述',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `sys_roles`
--

INSERT INTO `sys_roles` (`role_id`, `role_name`, `status`, `remark`) VALUES
(10000, '系统管理员', 1, '系统级管理员'),
(20001, '区域经理', 1, '区域经理'),
(20100, '省公司负责人', 1, '省公司负责人'),
(20200, '一类门店店长（城市负责人）', 1, '一类门店店长（城市负责人）'),
(20201, '二类门店店长', 1, '二类门店店长'),
(20202, '三类门店店长', 1, '三类门店店长'),
(20203, '风控', 0, '风控'),
(20204, '业务副经理', 1, '业务副经理'),
(20205, '业务员', 1, '业务员'),
(20206, '财务', 1, '财务'),
(20207, '配驾', 1, '配驾');

-- --------------------------------------------------------

--
-- 表的结构 `sys_role_menu`
--

CREATE TABLE IF NOT EXISTS `sys_role_menu` (
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `menu_id` int(11) NOT NULL COMMENT '菜单id',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '0:无效 1:有效'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `sys_role_menu`
--

INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `status`) VALUES
(20001, 10001, 1),
(20001, 10002, 1),
(20001, 10003, 1),
(20001, 10004, 1),
(20001, 20001, 1),
(20001, 20002, 1),
(20001, 20003, 1),
(20001, 20004, 1),
(20001, 30001, 1),
(20001, 30003, 1),
(20001, 30004, 1),
(20001, 30005, 1),
(20001, 30006, 1),
(20001, 30007, 1),
(20001, 40003, 1),
(20001, 40004, 1),
(20001, 40008, 1),
(20001, 60001, 1),
(20001, 10000, 1),
(20001, 20000, 1),
(20001, 30000, 1),
(20001, 40000, 1),
(20001, 60000, 1),
(20100, 30001, 1),
(20100, 30000, 1),
(20202, 10002, 1),
(20202, 10003, 1),
(20202, 20001, 1),
(20202, 30001, 1),
(20202, 30003, 1),
(20202, 30004, 1),
(20202, 30005, 1),
(20202, 30006, 1),
(20202, 30007, 1),
(20202, 40003, 1),
(20202, 40004, 1),
(20202, 40006, 1),
(20202, 60001, 1),
(20202, 10000, 1),
(20202, 20000, 1),
(20202, 30000, 1),
(20202, 40000, 1),
(20202, 60000, 1),
(20205, 30001, 1),
(20205, 30003, 1),
(20205, 30004, 1),
(20205, 30005, 1),
(20205, 30006, 1),
(20205, 30007, 1),
(20205, 40001, 1),
(20205, 40002, 1),
(20205, 40003, 1),
(20205, 40004, 1),
(20205, 60001, 1),
(20205, 30000, 1),
(20205, 40000, 1),
(20205, 60000, 1),
(20206, 40009, 1),
(20206, 60001, 1),
(20206, 40000, 1),
(20206, 60000, 1),
(20200, 20001, 1),
(20200, 20002, 1),
(20200, 20003, 1),
(20200, 20004, 1),
(20200, 30001, 1),
(20200, 30003, 1),
(20200, 30004, 1),
(20200, 30005, 1),
(20200, 30006, 1),
(20200, 30007, 1),
(20200, 40003, 1),
(20200, 40004, 1),
(20200, 40007, 1),
(20200, 60001, 1),
(20200, 20000, 1),
(20200, 30000, 1),
(20200, 40000, 1),
(20200, 60000, 1),
(10000, 10001, 1),
(10000, 10002, 1),
(10000, 10003, 1),
(10000, 10004, 1),
(10000, 10005, 1),
(10000, 10006, 1),
(10000, 20001, 1),
(10000, 20002, 1),
(10000, 20003, 1),
(10000, 20004, 1),
(10000, 30001, 1),
(10000, 30003, 1),
(10000, 30004, 1),
(10000, 30005, 1),
(10000, 30006, 1),
(10000, 30007, 1),
(10000, 40001, 1),
(10000, 40002, 1),
(10000, 40003, 1),
(10000, 40004, 1),
(10000, 40005, 1),
(10000, 40006, 1),
(10000, 40007, 1),
(10000, 40008, 1),
(10000, 40009, 1),
(10000, 60001, 1),
(10000, 70001, 1),
(10000, 70002, 1),
(10000, 70003, 1),
(10000, 70004, 1),
(10000, 10000, 1),
(10000, 20000, 1),
(10000, 30000, 1),
(10000, 40000, 1),
(10000, 60000, 1),
(10000, 70000, 1);

-- --------------------------------------------------------

--
-- 表的结构 `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `login_name` varchar(64) NOT NULL,
  `login_pwd` varchar(128) NOT NULL,
  `user_name` varchar(64) DEFAULT NULL,
  `nick_name` varchar(64) DEFAULT NULL,
  `employee_id` varchar(64) DEFAULT NULL COMMENT '员工工号，员工id',
  `head_url` varchar(128) DEFAULT NULL,
  `birthday` varchar(64) DEFAULT NULL,
  `address` varchar(128) DEFAULT NULL,
  `email` varchar(128) DEFAULT NULL,
  `create_by` int(11) NOT NULL DEFAULT '10000' COMMENT '创建人id',
  `create_at` varchar(32) NOT NULL COMMENT '创建时间',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态：1－正常；0-删除',
  `driver_status` int(11) DEFAULT '0' COMMENT '配驾状态：0-未分配；1-已分配',
  `driver_license_no` varchar(32) DEFAULT NULL COMMENT '配驾员驾驶证号',
  PRIMARY KEY (`user_id`),
  KEY `driver_status` (`driver_status`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=100018 ;

--
-- 转存表中的数据 `users`
--

INSERT INTO `users` (`user_id`, `login_name`, `login_pwd`, `user_name`, `nick_name`, `employee_id`, `head_url`, `birthday`, `address`, `email`, `create_by`, `create_at`, `status`, `driver_status`, `driver_license_no`) VALUES
(100000, 'sysadmin', '96E79218965EB72C92A549DD5A330112', '系统管理员', '系统管理员', NULL, NULL, NULL, NULL, NULL, 10000, '', 1, 0, NULL),
(100012, 'taizhouyewu', '96E79218965EB72C92A549DD5A330112', '泰州市业务员', '业务员', '0523100', NULL, NULL, NULL, NULL, 100000, '2015-03-16 14:03:638', 1, 0, ''),
(100013, 'taizhou', '96E79218965EB72C92A549DD5A330112', '泰州店管理员', '泰州店管理员', '0523001', NULL, NULL, NULL, NULL, 100000, '2015-03-16 14:03:184', 1, 0, ''),
(100014, 'huadong', '96E79218965EB72C92A549DD5A330112', '华东区管理员', '华东区管理员', '021001', NULL, NULL, NULL, NULL, 100000, '2015-03-16 14:03:587', 1, 0, ''),
(100015, 'taizhoupeijia', '96E79218965EB72C92A549DD5A330112', '泰州配驾', '泰州配驾', '0012345', NULL, NULL, NULL, NULL, 100000, '2015-03-16 18:03:753', 1, 0, '23213123132'),
(100016, 'quanguopeijia', '96E79218965EB72C92A549DD5A330112', '全国配驾', '全国陪驾', '00123100', NULL, NULL, NULL, NULL, 100000, '2015-03-16 18:03:46', 1, 0, '12321313213'),
(100017, 'zhujiank', '96E79218965EB72C92A549DD5A330112', '朱建坤', '朱', '10001111', NULL, NULL, NULL, NULL, 100000, '2015-03-16 21:03:926', 1, 0, '');

-- --------------------------------------------------------

--
-- 表的结构 `user_role`
--

CREATE TABLE IF NOT EXISTS `user_role` (
  `user_id` int(11) NOT NULL COMMENT '用户id，对应users表中user_id',
  `role_id` int(11) NOT NULL COMMENT '角色id，对应sys_roles表中role_id',
  `org_id` int(11) NOT NULL COMMENT '组织id',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态，1-有效；0-无效'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户对应角色表，每个用户可以有多个角色';

--
-- 转存表中的数据 `user_role`
--

INSERT INTO `user_role` (`user_id`, `role_id`, `org_id`, `status`) VALUES
(100000, 10000, 1, 1),
(100014, 20001, 10, 1),
(100015, 20207, 100011, 1),
(100016, 20207, 1, 1),
(100013, 20200, 100011, 1),
(100016, 20207, 100011, 1),
(100012, 20205, 100011, 1),
(100017, 20204, 100012, 1),
(100016, 20200, 100016, 1);

-- --------------------------------------------------------

--
-- 表的结构 `vehicle_contrace`
--

CREATE TABLE IF NOT EXISTS `vehicle_contrace` (
  `id` int(11) NOT NULL COMMENT '主键',
  `contrace_no` varchar(64) DEFAULT NULL COMMENT '合同编号',
  `customer_name` varchar(64) DEFAULT NULL COMMENT '客户姓名',
  `customer_type` varchar(64) NOT NULL DEFAULT '个人用户' COMMENT '客户类型',
  `customer_dn` varchar(64) DEFAULT NULL COMMENT '客户手机号',
  `customer_cer_type` varchar(64) NOT NULL DEFAULT '身份证' COMMENT '证件类型',
  `customer_cer_no` varchar(64) DEFAULT NULL COMMENT '证件号码',
  `remark` varchar(256) DEFAULT NULL COMMENT '备注',
  `employee_id` varchar(64) NOT NULL DEFAULT '0' COMMENT '业务员工号/id',
  `employee_name` varchar(64) DEFAULT NULL COMMENT '业务员名称',
  `create_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` int(11) DEFAULT '0' COMMENT '创建人',
  `update_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` int(11) DEFAULT '0' COMMENT '更新人',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '审核状态：0-初始状态;1－待审核;2-店长审核通过;3-市公司店长审核通过;4-区域经理审核通过;5-财务通过;6-结单;-1店长驳回；-2:门店店长审核不通过',
  `shopowner_update_by` int(11) NOT NULL DEFAULT '0' COMMENT '店长审核人',
  `shopowner_update_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '店长审核时间',
  `city_shopowner_update_by` int(11) DEFAULT '0' COMMENT '市店长审核人',
  `city_shopowner_update_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '市店长审核时间',
  `finance_update_by` int(11) NOT NULL DEFAULT '0' COMMENT '财务审核人',
  `finance_update_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '财务审核时间',
  `org_id` int(11) NOT NULL DEFAULT '0' COMMENT '该合同归属组织id',
  `reservation_id` int(11) NOT NULL DEFAULT '0' COMMENT '该合同对应预约单id',
  `regional_manager_update_by` int(11) DEFAULT '0' COMMENT '区域经理审核人',
  `regional_manager_update_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '区域经理审核时间',
  `use_begin` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '用车开始时间',
  `use_end` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '用车结束时间',
  `isovertop` int(11) NOT NULL DEFAULT '0' COMMENT '是否超过限价0-否;1-是；如果是，则需要市门店经理及以上角色审核',
  `daily_price` decimal(10,2) DEFAULT '0.00' COMMENT '合同车辆日单价',
  `daily_available_km` int(11) NOT NULL DEFAULT '0' COMMENT '日可用公里数',
  `over_km_price` int(11) NOT NULL DEFAULT '0' COMMENT '超公里数加收金额',
  `over_hour_price` int(11) NOT NULL DEFAULT '0' COMMENT '超小时数加收金额',
  `month_price` int(11) NOT NULL DEFAULT '0' COMMENT '包月单价',
  `month_available_km` int(11) NOT NULL DEFAULT '0' COMMENT '包月可用公里数',
  `pre_payment` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '预付款',
  `monthly_day` timestamp NULL DEFAULT NULL COMMENT '月结日',
  `deposit` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '总押金',
  `peccancy_deposit` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '违章押金',
  `contrace_type` int(11) NOT NULL DEFAULT '1' COMMENT '合同类型：1-零租；2-产权租',
  `system_total_price` decimal(10,2) DEFAULT NULL COMMENT '系统计算应得租金',
  `arrange_price` decimal(10,2) DEFAULT NULL COMMENT '约定所得租金',
  `actual_price` decimal(10,2) DEFAULT NULL COMMENT '实际所得租金',
  `late_fee` decimal(10,2) DEFAULT NULL COMMENT '滞纳金',
  `is_arrearage` int(11) NOT NULL DEFAULT '0' COMMENT '是否欠费，0-不欠费；1-欠费。根据约定所得租金和实际所得租金计算得出',
  `arrearage_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '欠费补交时间',
  `reserv_to_contrace_status` int(11) NOT NULL DEFAULT '0' COMMENT '预约单转合同状态0：未转；1-已转',
  PRIMARY KEY (`id`),
  KEY `reserv_to_contrace_status` (`reserv_to_contrace_status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='合同表';

--
-- 转存表中的数据 `vehicle_contrace`
--

INSERT INTO `vehicle_contrace` (`id`, `contrace_no`, `customer_name`, `customer_type`, `customer_dn`, `customer_cer_type`, `customer_cer_no`, `remark`, `employee_id`, `employee_name`, `create_at`, `create_by`, `update_at`, `update_by`, `status`, `shopowner_update_by`, `shopowner_update_at`, `city_shopowner_update_by`, `city_shopowner_update_at`, `finance_update_by`, `finance_update_at`, `org_id`, `reservation_id`, `regional_manager_update_by`, `regional_manager_update_at`, `use_begin`, `use_end`, `isovertop`, `daily_price`, `daily_available_km`, `over_km_price`, `over_hour_price`, `month_price`, `month_available_km`, `pre_payment`, `monthly_day`, `deposit`, `peccancy_deposit`, `contrace_type`, `system_total_price`, `arrange_price`, `actual_price`, `late_fee`, `is_arrearage`, `arrearage_date`, `reserv_to_contrace_status`) VALUES
(100053, 'HT20150331100053', '123', '个人用户', '15380897663', '身份证', '321281198609041875', '1', '泰州市业务员', '0523100', '2015-03-31 00:18:16', 100000, '2015-03-31 00:18:16', 0, 6, 100000, '2015-03-31 07:42:25', 0, '2015-03-31 00:18:16', 100000, '2015-03-31 07:42:51', 1, 43, 0, '2015-03-31 00:18:16', '2015-03-31 00:15:00', '2015-05-01 00:15:00', 0, '0.00', 80, 10, 5, 0, 0, '20000.00', NULL, '10000.00', '1000.00', 1, '60225.00', '60001.00', '60001.00', '0.00', 0, '2015-04-01 01:04:45', 1);

-- --------------------------------------------------------

--
-- 表的结构 `vehicle_contrace_vehs`
--

CREATE TABLE IF NOT EXISTS `vehicle_contrace_vehs` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `contrace_id` int(11) NOT NULL COMMENT '合同id',
  `vehicle_id` int(11) NOT NULL DEFAULT '0' COMMENT '车辆id',
  `license_plate` varchar(128) DEFAULT NULL COMMENT '车牌',
  `model` varchar(128) DEFAULT NULL COMMENT '车型',
  `company` varchar(64) DEFAULT NULL COMMENT '外借公司名',
  `other_vehicle_km` int(11) DEFAULT '0' COMMENT '外援车辆里程数',
  `isother` int(11) NOT NULL DEFAULT '0' COMMENT '是否是外借车辆：0-不是；1-是',
  `driving_user_id` varchar(128) DEFAULT '0' COMMENT '配驾员工号/id',
  `driving_user_name` varchar(64) DEFAULT NULL COMMENT '配驾员姓名',
  `driving_user_license_no` varchar(64) DEFAULT NULL COMMENT '配驾员驾照号',
  `create_by` int(11) NOT NULL COMMENT '创建人',
  `create_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` int(11) NOT NULL DEFAULT '0' COMMENT '更新人',
  `update_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `vehicle_price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '车购价，用来计算本次合同车辆总价，决定是否需要市门店经理审核',
  `return_time` timestamp NULL DEFAULT NULL COMMENT '还车时间',
  `return_km` int(11) DEFAULT NULL COMMENT '还车里程',
  `return_org` int(11) DEFAULT '0',
  `over_price` decimal(10,2) DEFAULT NULL COMMENT '超额费用（根据还车时间、还车里程以及合同规定还车时间、还车里程计算得出）',
  `status` int(11) DEFAULT '0' COMMENT '车辆状态0-未还；1-已还',
  `etc` varchar(1) DEFAULT NULL,
  `etc_money` decimal(10,2) DEFAULT NULL,
  `oil_percent` int(11) DEFAULT NULL,
  `revert_oil_percent` int(11) DEFAULT NULL,
  `revert_etc_money` decimal(10,2) DEFAULT NULL,
  `daily_price` decimal(10,2) DEFAULT '0.00' COMMENT '车辆日租金',
  `settlement_way` varchar(32) DEFAULT NULL,
  `fixed_price` decimal(10,2) DEFAULT '0.00',
  `system_price` decimal(10,2) DEFAULT NULL COMMENT '系统应收租金，按照合同算',
  `reduction_price` decimal(10,2) DEFAULT '0.00' COMMENT '减免金额，根据合同减免金额平分得到',
  `actually_price` decimal(10,2) DEFAULT '0.00' COMMENT '系统金额-减免金额',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='合同对应车辆详细表' AUTO_INCREMENT=28 ;

--
-- 转存表中的数据 `vehicle_contrace_vehs`
--

INSERT INTO `vehicle_contrace_vehs` (`id`, `contrace_id`, `vehicle_id`, `license_plate`, `model`, `company`, `other_vehicle_km`, `isother`, `driving_user_id`, `driving_user_name`, `driving_user_license_no`, `create_by`, `create_at`, `update_by`, `update_at`, `vehicle_price`, `return_time`, `return_km`, `return_org`, `over_price`, `status`, `etc`, `etc_money`, `oil_percent`, `revert_oil_percent`, `revert_etc_money`, `daily_price`, `settlement_way`, `fixed_price`, `system_price`, `reduction_price`, `actually_price`) VALUES
(25, 100053, 10, '苏K196L0', 'VSSCJ61P', NULL, 0, 0, '0', NULL, NULL, 100000, '2015-03-31 06:52:45', 0, '2015-03-31 06:52:45', '27.00', '2015-05-03 17:15:00', 1111, 100020, '325.00', 1, NULL, '0.00', 0, 10, '200.00', '1000.00', '客户自理', '0.00', '31000.00', '74.67', '30925.33'),
(26, 100053, 9, '苏AK1P89', '速腾', NULL, 0, 0, '0', NULL, NULL, 100000, '2015-03-31 06:54:44', 0, '2015-03-31 06:54:44', '14.98', '2015-04-28 17:15:00', 1111, 1, '0.00', 1, '有', '0.00', 0, 20, '150.00', '900.00', '客户自理', '0.00', '27900.00', '74.67', '27825.33'),
(27, 100053, 8, '苏AK1P81', '迈腾', NULL, 0, 0, '0', NULL, NULL, 100000, '2015-03-31 07:31:21', 0, '2015-03-31 07:31:21', '22.00', '2015-04-30 17:15:00', 11111, 1, '0.00', 1, NULL, '0.00', 0, 10, '211.00', '800.00', '一口价', '1000.00', '1000.00', '74.66', '925.34');

-- --------------------------------------------------------

--
-- 表的结构 `vehicle_info`
--

CREATE TABLE IF NOT EXISTS `vehicle_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `brand` varchar(32) NOT NULL COMMENT '品牌',
  `model` varchar(128) NOT NULL COMMENT '车型',
  `color` varchar(32) NOT NULL COMMENT '颜色',
  `carframe_no` varchar(64) NOT NULL COMMENT '车架号',
  `engine_no` varchar(64) NOT NULL COMMENT '发动机号',
  `buy_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '购买日期',
  `supplier` varchar(128) NOT NULL COMMENT '供应商名称',
  `license_plate` varchar(32) NOT NULL COMMENT '车牌号',
  `card_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '上牌登记日期',
  `limited_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '年审日期',
  `guide_price` decimal(10,2) NOT NULL COMMENT '市场指导价',
  `vehicle_price` decimal(10,2) NOT NULL COMMENT '车购价',
  `vehicle_tax` decimal(10,2) NOT NULL COMMENT '车购税',
  `insurance_company` varchar(128) NOT NULL COMMENT '保险公司',
  `strong_insurance` decimal(10,2) NOT NULL COMMENT '交强险',
  `strong_insurance_expire_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '交强险到期日期',
  `vehicle_vessel_tax` decimal(10,2) NOT NULL COMMENT '车船税',
  `business_insurance` decimal(10,2) NOT NULL COMMENT '商业险',
  `business_insurance_expire_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '商业险到期日期',
  `km` bigint(20) NOT NULL COMMENT '公里数',
  `maintian_on_km` bigint(20) DEFAULT NULL COMMENT '保养剩余公里数',
  `gps` varchar(32) NOT NULL COMMENT 'GPS状态:正常、 异常、 未安装',
  `current_city` int(11) NOT NULL COMMENT '当前所在城市',
  `current_shop` int(11) NOT NULL COMMENT '当前所在门店',
  `lease_status` varchar(16) NOT NULL COMMENT '车辆租赁状态:在库、零租、产权租、售出',
  `peccancy_status` int(11) NOT NULL DEFAULT '0' COMMENT '是否有违章待处理:0 无 1 有',
  `archive_no` varchar(64) NOT NULL COMMENT '档案编号',
  `inventory_no` varchar(64) NOT NULL COMMENT '存货编码',
  `registry_certificate` varchar(256) NOT NULL COMMENT '登记证书',
  `certificate_direction` varchar(64) NOT NULL COMMENT '登记证书去向',
  `loan_bank` varchar(64) NOT NULL COMMENT '贷款银行',
  `consistency_cer` varchar(256) NOT NULL COMMENT '关单/合格/一致性证书',
  `check_list` varchar(256) NOT NULL COMMENT '检验单',
  `duty_paid_proof` varchar(128) NOT NULL COMMENT '完税证明/小本',
  `record` varchar(128) NOT NULL COMMENT '记录',
  `remark` varchar(128) NOT NULL COMMENT '备注',
  `update_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` int(11) DEFAULT '0' COMMENT '更新人员id',
  `create_by` int(11) NOT NULL COMMENT '创建人id',
  `create_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `original_org` int(11) NOT NULL COMMENT '归属门店',
  `next_main_km` int(11) NOT NULL DEFAULT '0' COMMENT '下次保养公里数',
  `financing_rent_company` varchar(64) DEFAULT NULL COMMENT '融资租赁公司',
  `financing_rent_price` decimal(10,2) DEFAULT NULL COMMENT '融资租赁总价',
  `bail` decimal(10,2) DEFAULT NULL COMMENT '保证金',
  `monthly_payment` decimal(10,2) DEFAULT NULL COMMENT '月付款',
  `etc` varchar(1) DEFAULT NULL COMMENT '是否有etc',
  `etc_money` decimal(10,2) DEFAULT NULL COMMENT 'etc内金额',
  `oil_percent` int(11) DEFAULT NULL COMMENT '当前油量比',
  `daily_price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '车辆日租价格',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='车辆信息表' AUTO_INCREMENT=11 ;

--
-- 转存表中的数据 `vehicle_info`
--

INSERT INTO `vehicle_info` (`id`, `brand`, `model`, `color`, `carframe_no`, `engine_no`, `buy_at`, `supplier`, `license_plate`, `card_at`, `limited_at`, `guide_price`, `vehicle_price`, `vehicle_tax`, `insurance_company`, `strong_insurance`, `strong_insurance_expire_at`, `vehicle_vessel_tax`, `business_insurance`, `business_insurance_expire_at`, `km`, `maintian_on_km`, `gps`, `current_city`, `current_shop`, `lease_status`, `peccancy_status`, `archive_no`, `inventory_no`, `registry_certificate`, `certificate_direction`, `loan_bank`, `consistency_cer`, `check_list`, `duty_paid_proof`, `record`, `remark`, `update_at`, `update_by`, `create_by`, `create_at`, `original_org`, `next_main_km`, `financing_rent_company`, `financing_rent_price`, `bail`, `monthly_payment`, `etc`, `etc_money`, `oil_percent`, `daily_price`) VALUES
(1, '大众', 'cc', '白色', 'LFV3A23C9E3446030', '402972', '2015-03-31 00:37:21', '', '苏AK1P30', '2014-12-25 16:00:00', '2016-12-30 16:00:00', '20.98', '20.98', '1.00', '1', '1.00', '2015-12-22 16:00:00', '1.00', '1.00', '2015-12-22 16:00:00', 200, 7500, '正常', 514, 1, '在库', 0, '', '', '', '', '', '', '', '', '', '', '2015-03-22 12:38:06', 0, 100000, '2015-03-22 12:38:06', 1, 7500, '', '0.00', '0.00', '0.00', NULL, NULL, NULL, '500.00'),
(2, '大众', 'cc', '黑色', 'LFV3A23C0E3444215', '395089', '2015-03-31 00:37:25', '', '苏AK1P93', '2014-12-25 16:00:00', '2015-12-30 16:00:00', '20.98', '20.98', '1.00', '1', '1.00', '2015-12-22 16:00:00', '1.00', '1.00', '2015-12-22 16:00:00', 200, 7500, '正常', 514, 1, '在库', 0, '', '', '', '', '', '', '', '', '', '', '2015-03-22 12:40:34', 0, 100000, '2015-03-22 12:40:34', 1, 7500, '', '0.00', '0.00', '0.00', NULL, NULL, NULL, '400.00'),
(3, '大众', '高尔夫', '金色', 'LFV2B25G1E5148763', 'G59479', '2015-03-31 00:37:31', '', '苏AK1P83', '2014-12-15 16:00:00', '2016-12-30 16:00:00', '15.98', '15.98', '1.00', '1', '1.00', '2015-12-13 16:00:00', '1.00', '1.00', '2015-12-13 16:00:00', 200, 7500, '正常', 514, 1, '在库', 0, '', '', '', '', '', '', '', '', '', '', '2015-03-22 12:42:47', 0, 100000, '2015-03-22 12:42:47', 1, 7500, '', '0.00', '0.00', '0.00', NULL, NULL, NULL, '300.00'),
(4, '大众', '高尔夫', '白色', 'LFV2B25G6E5101566', 'G39378', '2015-03-31 00:37:39', '', '苏AK1P87', '2014-12-15 16:00:00', '2016-12-30 16:00:00', '14.98', '14.98', '1.00', '1', '1.00', '2015-12-22 16:00:00', '1.00', '1.00', '2015-12-22 16:00:00', 812, 7500, '正常', 514, 100020, '在库', 0, '', '', '', '', '', '', '', '', '', '', '2015-03-22 12:44:48', 0, 100000, '2015-03-22 12:44:48', 1, 7500, '', '0.00', '0.00', '0.00', NULL, NULL, NULL, '200.00'),
(5, '别克', '商务车', '灰色', 'LSGUA84W8EE071151', '142870742', '2015-03-31 00:37:43', '', '苏AK2P38', '2014-12-17 16:00:00', '2016-12-30 16:00:00', '28.00', '28.00', '1.00', '1', '1.00', '2015-12-22 16:00:00', '1.00', '1.00', '2015-12-22 16:00:00', 788, 5000, '正常', 514, 100020, '在库', 0, '', '', '', '', '', '', '', '', '', '', '2015-03-22 12:48:05', 0, 100000, '2015-03-22 12:48:05', 1, 5000, '', '0.00', '0.00', '0.00', NULL, NULL, NULL, '100.00'),
(6, '别克', '商务车', '银色', 'LSGUD84X4EE070823', '142980277', '2015-03-31 00:37:47', '', '苏AQ7C70', '2014-12-28 16:00:00', '2016-12-30 16:00:00', '28.00', '28.00', '1.00', '1', '1.00', '2016-01-10 16:00:00', '1.00', '1.00', '2016-01-10 16:00:00', 523, 5000, '正常', 514, 1, '在库', 0, '', '', '', '', '', '', '', '', '', '', '2015-03-22 12:50:15', 0, 100000, '2015-03-22 12:50:15', 1, 5000, '', '0.00', '0.00', '0.00', NULL, NULL, NULL, '600.00'),
(7, '大众', '帕萨特', '黑色', 'LSVC26A48EN212496', 'Y25664', '2015-03-31 00:37:50', '', '苏ANON59', '2015-01-20 16:00:00', '2017-01-30 16:00:00', '21.00', '21.00', '1.00', '1', '1.00', '2016-01-10 16:00:00', '1.00', '1.00', '2016-01-10 16:00:00', 500, 7500, '正常', 514, 100020, '在库', 1, '', '', '', '', '', '', '', '', '', '', '2015-03-22 12:55:30', 0, 100000, '2015-03-22 12:55:30', 1, 7500, '', '0.00', '0.00', '0.00', NULL, NULL, NULL, '700.00'),
(8, '大众', '迈腾', '金色', 'LFV3A23COE3989159', '394619', '2015-03-31 13:25:41', '', '苏AK1P81', '2014-12-15 16:00:00', '2016-12-30 16:00:00', '22.00', '22.00', '1.00', '1', '1.00', '2016-01-10 16:00:00', '1.00', '1.00', '2016-01-10 16:00:00', 11111, 7500, '正常', 514, 1, '在库', 0, '', '', '', '', '', '', '', '', '', '', '2015-03-22 12:58:36', 0, 100000, '2015-03-22 12:58:36', 1, 7500, '', '0.00', '0.00', '0.00', NULL, '211.00', 10, '800.00'),
(9, '大众', '速腾', '黑色', 'LFV2A21K9E4218402', 'N52335', '2015-03-31 13:27:38', '', '苏AK1P89', '2014-12-15 16:00:00', '2016-12-30 16:00:00', '14.98', '14.98', '1.00', '太平洋', '1.00', '2015-12-13 16:00:00', '1.00', '1.00', '2015-12-13 16:00:00', 1111, 6800, '正常', 514, 1, '在库', 0, '', '', '', '', '', '', '', '', '', '', '2015-03-31 00:40:53', 100000, 100000, '2015-03-22 13:01:33', 1, 7500, '', '0.00', '0.00', '0.00', '有', '150.00', 20, '900.00'),
(10, '西雅特', 'VSSCJ61P', '蓝色', 'VSSCJ61P3CR040094', 'CDA268124', '2015-03-31 13:26:34', '', '苏K196L0', '2013-12-16 16:00:00', '2015-12-30 16:00:00', '27.00', '27.00', '1.00', '1', '1.00', '2015-12-15 16:00:00', '1.00', '1.00', '2015-12-15 16:00:00', 1111, 7500, '正常', 514, 100020, '在库', 0, '', '', '', '', '', '', '', '', '', '', '2015-03-22 13:04:16', 0, 100000, '2015-03-22 13:04:16', 1, 7500, '', '0.00', '0.00', '0.00', NULL, '200.00', 10, '1000.00');

-- --------------------------------------------------------

--
-- 表的结构 `vehicle_insurance`
--

CREATE TABLE IF NOT EXISTS `vehicle_insurance` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `carframe_no` varchar(128) NOT NULL COMMENT '车架号',
  `engine_no` varchar(128) NOT NULL COMMENT '发动机号',
  `license_plate` varchar(64) NOT NULL COMMENT '车牌号',
  `insurance_company` varchar(64) NOT NULL COMMENT '保险公司名称',
  `strong_insurance` double NOT NULL COMMENT '交强险',
  `strong_insurance_expire_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '交强险到期时间',
  `vehicle_vessel_tax` double NOT NULL COMMENT '车船税',
  `business_insurance` double NOT NULL COMMENT '商业险',
  `business_insurance_expire_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '商业险到期时间',
  `remark` varchar(128) NOT NULL COMMENT '备注',
  `create_by` int(11) NOT NULL COMMENT '创建人',
  `create_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` int(11) NOT NULL DEFAULT '0' COMMENT '更新人员',
  `update_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `carframe_no` (`carframe_no`,`engine_no`),
  KEY `license_plate` (`license_plate`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='车辆保险' AUTO_INCREMENT=17 ;

--
-- 转存表中的数据 `vehicle_insurance`
--

INSERT INTO `vehicle_insurance` (`id`, `carframe_no`, `engine_no`, `license_plate`, `insurance_company`, `strong_insurance`, `strong_insurance_expire_at`, `vehicle_vessel_tax`, `business_insurance`, `business_insurance_expire_at`, `remark`, `create_by`, `create_at`, `update_by`, `update_at`) VALUES
(14, 'LFV2A21K9E4218402', 'N52335', '苏AK1P89', '人保', 1, '2015-12-13 16:00:00', 1, 1, '2015-12-13 16:00:00', '', 100000, '2015-03-31 00:39:17', 0, '2015-03-31 00:39:17'),
(15, 'LFV2A21K9E4218402', 'N52335', '苏AK1P89', '人保', 1, '2015-12-13 16:00:00', 1, 1, '2015-12-13 16:00:00', '', 100000, '2015-03-31 00:40:44', 0, '2015-03-31 00:40:44'),
(16, 'LFV2A21K9E4218402', 'N52335', '苏AK1P89', '太平洋', 1, '2015-12-13 16:00:00', 1, 1, '2015-12-13 16:00:00', '', 100000, '2015-03-31 00:40:53', 0, '2015-03-31 00:40:53');

-- --------------------------------------------------------

--
-- 表的结构 `vehicle_maintail_record`
--

CREATE TABLE IF NOT EXISTS `vehicle_maintail_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `carframe_no` varchar(128) NOT NULL COMMENT '车架号',
  `engine_no` varchar(128) NOT NULL COMMENT '发动机号',
  `license_plate` varchar(128) NOT NULL COMMENT '车牌号码',
  `maintain_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '保养时间',
  `maintain_content` varchar(256) NOT NULL COMMENT '保养内容',
  `maintain_price` decimal(10,2) NOT NULL COMMENT '保养金额',
  `current_km` bigint(20) NOT NULL COMMENT '公里数',
  `next_maintain_km` bigint(20) NOT NULL COMMENT '下一次保养里程',
  `user_id` int(11) NOT NULL DEFAULT '0' COMMENT '处理员工ID',
  `user_name` varchar(64) NOT NULL COMMENT '处理员工姓名',
  `update_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` int(11) NOT NULL DEFAULT '0' COMMENT '更新人员ID',
  `create_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` int(11) NOT NULL COMMENT '创建人员ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='车辆保养记录表' AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- 表的结构 `vehicle_peccancy`
--

CREATE TABLE IF NOT EXISTS `vehicle_peccancy` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `carframe_no` varchar(128) NOT NULL COMMENT '车架号',
  `engine_no` varchar(128) NOT NULL COMMENT '发动机号',
  `license_plate` varchar(64) NOT NULL COMMENT '车牌号',
  `peccancy_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '违章时间',
  `peccancy_place` varchar(128) NOT NULL COMMENT '违章地点',
  `peccancy_reason` varchar(128) NOT NULL COMMENT '违章原因',
  `peccancy_price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '违章金额',
  `score` int(11) NOT NULL DEFAULT '0' COMMENT '违章扣分数',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '是否处理：0-未处理；1-已处理',
  `arbitration` varchar(16) NOT NULL COMMENT '处理仲裁:公司交、客户交',
  `create_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '录入时间',
  `create_by` int(11) NOT NULL COMMENT '录入人员',
  `update_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` int(11) NOT NULL DEFAULT '0' COMMENT '更新人员',
  `employee_id` varchar(64) NOT NULL DEFAULT '0' COMMENT '员工id（工号）',
  `employee_name` varchar(64) NOT NULL COMMENT '员工姓名',
  `customer_id` varchar(11) DEFAULT NULL COMMENT '客户id（客户造成违章时录入）',
  `customer_name` varchar(64) DEFAULT NULL COMMENT '客户姓名（客户造成违章时录入）',
  PRIMARY KEY (`id`),
  KEY `carframe_no` (`carframe_no`,`engine_no`,`license_plate`,`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='车辆违章表' AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- 表的结构 `vehicle_reservation`
--

CREATE TABLE IF NOT EXISTS `vehicle_reservation` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `customer_name` varchar(64) NOT NULL COMMENT '客户姓名',
  `customer_dn` varchar(64) NOT NULL COMMENT '客户手机号',
  `use_begin` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '用车开始时间',
  `use_end` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '用车结束时间',
  `model` varchar(128) DEFAULT NULL COMMENT '车型',
  `unit_price` decimal(10,2) DEFAULT NULL COMMENT '单价',
  `quantity` int(11) DEFAULT NULL COMMENT '数量',
  `with_driver` int(11) DEFAULT NULL COMMENT '是否要配架:1-要；0-不要',
  `expenses_self` int(11) DEFAULT NULL COMMENT '是否自理油及过路停车费1：是；0：否',
  `employee_id` varchar(64) NOT NULL COMMENT '业务员工号/id',
  `employee_name` varchar(64) NOT NULL COMMENT '业务员姓名',
  `org_id` int(11) NOT NULL COMMENT '门店id',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '状态：0-初始，1-完结，2-取消',
  `create_by` int(11) NOT NULL COMMENT '创建人',
  `create_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `risk_control_update_by` int(11) DEFAULT NULL COMMENT '风控审核人',
  `risk_control_update_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '风控审核时间',
  `business_manager_update_by` int(11) DEFAULT NULL COMMENT '业务经理审核人',
  `business_manager_update_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '业务经理审核时间',
  `finance_update_by` int(11) DEFAULT NULL COMMENT '财务审核人',
  `finance_update_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '财务经理审核时间',
  `remark` varchar(1024) NOT NULL COMMENT '备注，描述，填写客户所需车辆型号、价格、是否配驾、是否自理油费等信息',
  `contrace_type` int(11) NOT NULL DEFAULT '1' COMMENT '合同类型：1-零租；2-产权租',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='预约单' AUTO_INCREMENT=44 ;

--
-- 转存表中的数据 `vehicle_reservation`
--

INSERT INTO `vehicle_reservation` (`id`, `customer_name`, `customer_dn`, `use_begin`, `use_end`, `model`, `unit_price`, `quantity`, `with_driver`, `expenses_self`, `employee_id`, `employee_name`, `org_id`, `status`, `create_by`, `create_at`, `risk_control_update_by`, `risk_control_update_at`, `business_manager_update_by`, `business_manager_update_at`, `finance_update_by`, `finance_update_at`, `remark`, `contrace_type`) VALUES
(43, '123', '15380897663', '2015-03-31 00:15:00', '2015-05-01 00:15:00', NULL, NULL, NULL, NULL, NULL, '泰州市业务员', '0523100', 1, 1, 100000, '2015-03-31 00:18:11', NULL, '2015-03-31 00:18:11', NULL, '2015-03-31 00:18:11', NULL, '2015-03-31 00:18:11', '1', 1);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
