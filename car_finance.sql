-- phpMyAdmin SQL Dump
-- version 4.1.6
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: 2015-03-22 05:47:41
-- 服务器版本： 5.6.16
-- PHP Version: 5.5.9

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
  `customer_email` varchar(64) NOT NULL COMMENT '客户邮箱地址',
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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='客户信息表' AUTO_INCREMENT=3 ;

--
-- 转存表中的数据 `customer_info`
--

INSERT INTO `customer_info` (`id`, `customer_name`, `certificate_type`, `certificate_no`, `certificate_url`, `certificate_name`, `customer_dn`, `customer_email`, `create_at`, `create_by`, `update_at`, `update_by`, `customer_type`, `customer_house`, `customer_vehicle`, `customer_guarantee`, `vip_no`, `identity_name`, `identity_url`, `house_property_name`, `house_property_url`, `driving_license_name`, `driving_license_url`, `other_name`, `other_url`) VALUES
(2, 'sfdsa', '身份证', '12313123213', NULL, NULL, '3213', '', '2015-03-15 00:17:32', 100000, '2015-03-15 00:17:32', 100000, '个人用户', '123213', '132123', '12321312', '0001231231', 'IMG_0017', '/files/upload/customer/annex/2/ef2868a8-c6fd-4fce-9811-bd3a374f429c.JPG', NULL, NULL, 'IMG_0027', '/files/upload/customer/annex/2/76723978-4bf6-49b8-9182-e66b26b1a776.JPG', NULL, NULL);

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
  `montyly_payment` decimal(10,2) DEFAULT NULL COMMENT '月付款',
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

INSERT INTO `property_contrace` (`id`, `contrace_no`, `customer_name`, `customer_type`, `customer_dn`, `customer_cer_type`, `customer_cer_no`, `remark`, `employee_id`, `employee_name`, `create_at`, `create_by`, `update_at`, `update_by`, `status`, `shopowner_update_by`, `shopowner_update_at`, `city_shopowner_update_by`, `city_shopowner_update_at`, `finance_update_by`, `finance_update_at`, `org_id`, `reservation_id`, `regional_manager_update_by`, `regional_manager_update_at`, `isovertop`, `sign_at`, `period_number`, `down_payment`, `lease_price`, `montyly_payment`, `arrange_payment`, `monthly_day`, `final_payment`, `received_periods`, `already_back_amount`, `payment_type`, `reserv_to_contrace_status`) VALUES
(100044, '00123213', '22131', '个人用户', '12313', '身份证', '12321313', '132213aaa', '012313', '123213', '2015-03-16 08:21:59', 100000, '2015-03-16 08:21:59', 0, 6, 100000, '2015-03-17 01:50:27', 100000, '2015-03-17 02:37:00', 100000, '2015-03-17 02:40:17', 1, 34, 100000, '2015-03-17 02:40:06', 1, '2015-03-17', 1231231, '12313.00', '123213.00', '1231.00', '123213.00', 123213, '-124813.00', 123219, '371239.00', '123213', 1);

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COMMENT='产权租月还款详细表' AUTO_INCREMENT=6 ;

--
-- 转存表中的数据 `property_contrace_repayment_log`
--

INSERT INTO `property_contrace_repayment_log` (`id`, `contrace_id`, `should_payment`, `actual_payment`, `create_at`, `create_by`) VALUES
(1, 100044, '123213.00', '123213.00', '2015-03-17', 100000),
(2, 100044, '123213.00', '123.00', '2015-03-20', 100000),
(3, 100044, '-246549.00', '123.00', '2015-03-20', 100000),
(4, 100044, '-246672.00', '1231.00', '2015-03-20', 100000),
(5, 100044, '-247903.00', '123.00', '2015-03-20', 100000);

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
('ContraceSeq', 100044, 1),
('StoreSeq', 100014, 1),
('UsersSeq', 100021, 1);

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
(70001, '台帐', 'http://www.xici.net', 70000, 0, '台帐', 'page', NULL, 1, 0);

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
(1, '全国总公司', 0, 10, 100025, 25, 25001, '雄狮对面', '全国总公司', '江苏省', '南京市', '玄武区', 0),
(10, '华东区公司', 1, 11, 100025, 25, 25001, '雄狮旁边', '华东区公司', '江苏省', '南京市', '玄武区', 0),
(20, '江苏省公司', 10, 12, 100025, 25, 25001, '雄狮右边', '江苏省公司', '江苏省', '南京市', '玄武区', 0),
(100010, '扬州市门店123', 20, 13, 100025, 514, 0, '扬州市汶河路321', '市公司（一类门店）', '江苏省', '扬州', '', 0),
(100011, '扬州高邮市二类门店', 100010, 14, 100025, 514, 0, '高邮市', '二类门店', '江苏省', '扬州', '', 0),
(100012, '泰州市门店', 20, 13, 100025, 523, 0, '泰州市', '市公司（一类门店）', '江苏省', '泰州', '', 0),
(100013, '泰州兴化三类门店', 100012, 15, 100025, 523, 0, '兴化市', '三类门店', '江苏省', '泰州', '', 0),
(100014, 'test', 100012, 14, 100025, 523, 0, 'test', '二类门店', '江苏省', '泰州', '', 1);

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
(20001, '城市负责人', 1, '城市负责人'),
(20002, '门店负责人', 1, '门店负责人'),
(20003, '业务员', 1, '业务员'),
(20004, '业务副经理', 1, '业务副经理'),
(20005, '财务', 1, '财务'),
(20006, '风控', 1, '风控'),
(20007, '配架司机', 1, '配架司机'),
(20008, '店长', 1, '店长'),
(20009, '市店长', 1, '市店长'),
(20010, '区域经理', 1, '区域经理');

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
(20008, 40001, 1),
(20008, 40002, 1),
(20008, 40003, 1),
(20008, 40006, 1),
(20008, 40000, 1),
(20009, 40001, 1),
(20009, 40002, 1),
(20009, 40003, 1),
(20009, 40007, 1),
(20009, 40000, 1),
(20010, 40008, 1),
(20010, 40000, 1),
(20005, 40009, 1),
(20005, 40000, 1),
(20003, 30001, 1),
(20003, 30003, 1),
(20003, 30004, 1),
(20003, 30005, 1),
(20003, 30006, 1),
(20003, 30007, 1),
(20003, 40001, 1),
(20003, 40002, 1),
(20003, 40003, 1),
(20003, 60001, 1),
(20003, 30000, 1),
(20003, 40000, 1),
(20003, 60000, 1),
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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=100022 ;

--
-- 转存表中的数据 `users`
--

INSERT INTO `users` (`user_id`, `login_name`, `login_pwd`, `user_name`, `nick_name`, `employee_id`, `head_url`, `birthday`, `address`, `email`, `create_by`, `create_at`, `status`, `driver_status`, `driver_license_no`) VALUES
(100000, 'sysadmin', '96E79218965EB72C92A549DD5A330112', '系统管理员a', '系统管理员', NULL, NULL, NULL, NULL, NULL, 10000, '', 1, 0, NULL),
(100021, '111111', '96E79218965EB72C92A549DD5A330112', '111111', '', '111111', NULL, NULL, NULL, NULL, 100000, '2015-03-16 10:03:583', 1, 0, '111111');

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
(100021, 20003, 100013, 1);

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
  `daily_price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '合同车辆日单价',
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
(100038, '000001', '姜寅', '个人用户', '15380897663', '身份证', '321281198609041875', '11111\n22222\n33333\n44444\n55555\n66666', '12345', '11111', '2015-03-14 12:00:34', 100000, '2015-03-14 12:00:34', 0, 6, 100000, '2015-03-14 12:19:07', 0, '2015-03-14 12:00:34', 100000, '2015-03-14 12:28:27', 1, 29, 0, '2015-03-14 12:00:34', '2015-03-15 07:00:00', '2015-03-22 07:00:00', 0, '200.00', 80, 10, 5, 0, 0, '11111.00', NULL, '33333.00', '22222.00', 1, '29130.00', '29130.00', '29130.00', '0.00', 0, '2015-03-16 05:33:05', 1),
(100039, '0012312312', '123123', '个人用户', '13213', '身份证', '1231313', '123213213', '123123', '123123', '2015-03-16 01:41:43', 100000, '2015-03-16 01:41:43', 0, 0, 0, '2015-03-16 01:41:43', 0, '2015-03-16 01:41:43', 0, '2015-03-16 01:41:43', 1, 30, 0, '2015-03-16 01:41:43', '2015-03-16 01:30:00', '2015-03-17 01:30:00', 0, '200.00', 80, 5, 10, 0, 0, '2000000.00', NULL, '2000000.00', '1000000.00', 1, NULL, NULL, NULL, NULL, 0, '2015-03-14 23:00:00', 1),
(100041, '1232131313', '123213', '个人用户', '123123', '身份证', '1231311', '1232132132132', '0123213', '12313132213', '2015-03-16 03:34:19', 100000, '2015-03-16 03:34:19', 0, 6, 100000, '2015-03-16 03:35:38', 0, '2015-03-16 03:34:19', 100000, '2015-03-16 03:35:44', 1, 32, 0, '2015-03-16 03:34:19', '2015-03-16 03:00:00', '2015-03-18 03:00:00', 0, '300.00', 80, 5, 10, 0, 0, '1111111.00', NULL, '2222222.00', '3333333.00', 1, '840.00', '840.00', '840.00', '0.00', 0, '2015-03-16 05:47:27', 1),
(100042, NULL, NULL, '个人用户', NULL, '身份证', NULL, NULL, '0', NULL, '2015-03-16 06:21:31', 100000, '2015-03-16 06:21:31', 0, 0, 0, '2015-03-16 06:21:31', 0, '2015-03-16 06:21:31', 0, '2015-03-16 06:21:31', 1, 33, 0, '2015-03-16 06:21:31', '2015-03-16 06:21:31', '2015-03-16 06:21:31', 0, '0.00', 0, 0, 0, 0, 0, '0.00', NULL, '0.00', '0.00', 1, NULL, NULL, NULL, NULL, 0, '2015-03-16 06:21:31', 0);

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
  `other_vehicle_km` int(11) DEFAULT NULL COMMENT '外援车辆里程数',
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
  `over_price` decimal(10,2) DEFAULT NULL COMMENT '超额费用（根据还车时间、还车里程以及合同规定还车时间、还车里程计算得出）',
  `status` int(11) DEFAULT '0' COMMENT '车辆状态0-未还；1-已还',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='合同对应车辆详细表' AUTO_INCREMENT=11 ;

--
-- 转存表中的数据 `vehicle_contrace_vehs`
--

INSERT INTO `vehicle_contrace_vehs` (`id`, `contrace_id`, `vehicle_id`, `license_plate`, `model`, `company`, `other_vehicle_km`, `isother`, `driving_user_id`, `driving_user_name`, `driving_user_license_no`, `create_by`, `create_at`, `update_by`, `update_at`, `vehicle_price`, `return_time`, `return_km`, `over_price`, `status`) VALUES
(5, 100038, 4, '苏KEY991', 'GL8商务', NULL, NULL, 0, '0', NULL, NULL, 100000, '2015-03-14 12:16:33', 0, '2015-03-14 12:16:33', '160000.00', '2015-03-01 16:00:00', 14444, '27730.00', 0),
(6, 100039, 4, '苏KEY991', 'GL8商务', NULL, NULL, 0, '0', NULL, NULL, 100000, '2015-03-16 01:44:28', 0, '2015-03-16 01:44:28', '160000.00', NULL, NULL, NULL, 0),
(7, 100041, 4, '苏KEY991', 'GL8商务', NULL, NULL, 0, '0', NULL, NULL, 100000, '2015-03-16 03:35:23', 0, '2015-03-16 03:35:23', '160000.00', '2015-03-19 03:00:00', 12345, '240.00', 1),
(8, 100044, 4, '苏KEY991', 'GL8商务', NULL, NULL, 0, '0', NULL, NULL, 100000, '2015-03-17 01:02:12', 0, '2015-03-17 01:02:12', '160000.00', NULL, NULL, NULL, 0),
(9, 100039, 0, '苏K23D23', '宝马', '扬州公司', 34215, 1, '0', NULL, NULL, 100000, '2015-03-17 12:48:33', 0, '2015-03-17 12:48:33', '650430.00', NULL, NULL, NULL, 0),
(10, 100039, 0, '苏A321DF', '宝马750', '你好', 34567, 1, '0', NULL, NULL, 100000, '2015-03-17 13:07:36', 0, '2015-03-17 13:07:36', '908765.00', NULL, NULL, NULL, 0);

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
  `maintian_on_km` bigint(20) NOT NULL COMMENT '保养剩余公里数',
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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='车辆信息表' AUTO_INCREMENT=6 ;

--
-- 转存表中的数据 `vehicle_info`
--

INSERT INTO `vehicle_info` (`id`, `brand`, `model`, `color`, `carframe_no`, `engine_no`, `buy_at`, `supplier`, `license_plate`, `card_at`, `limited_at`, `guide_price`, `vehicle_price`, `vehicle_tax`, `insurance_company`, `strong_insurance`, `strong_insurance_expire_at`, `vehicle_vessel_tax`, `business_insurance`, `business_insurance_expire_at`, `km`, `maintian_on_km`, `gps`, `current_city`, `current_shop`, `lease_status`, `peccancy_status`, `archive_no`, `inventory_no`, `registry_certificate`, `certificate_direction`, `loan_bank`, `consistency_cer`, `check_list`, `duty_paid_proof`, `record`, `remark`, `update_at`, `update_by`, `create_by`, `create_at`, `original_org`, `next_main_km`, `financing_rent_company`, `financing_rent_price`, `bail`, `monthly_payment`) VALUES
(4, '别克', 'GL8商务', '蓝色', 'LSGUD82C07E028992', '77310255', '2015-03-20 08:08:43', '孙涛（男）', '苏KEY991', '2007-10-31 16:00:00', '2014-10-31 16:00:00', '160000.00', '350000.00', '0.00', '人保', '791.00', '2015-09-12 16:00:00', '1200.00', '5239.89', '2015-09-14 16:00:00', 12345, 22222, '正常', 514, 100011, '售出', 1, '', '020080', '', '', '', '', '', '', '', '', '2015-03-14 11:52:24', 0, 100000, '2015-03-14 11:52:24', 1, 33333, NULL, NULL, NULL, NULL),
(5, '12313', '123213', '13213', '123123', '13123', '2015-03-21 16:00:00', '123', '12313', '2015-03-21 16:00:00', '2015-03-21 16:00:00', '1234512.00', '1231.00', '123.00', '123', '123.00', '2015-03-21 16:00:00', '123.00', '123.00', '2015-03-21 16:00:00', 13213, 123, '正常', 514, 1, '在库', 0, '', '13123', '12313', '12313', '123', '12313', '12313', '12313', '', '', '2015-03-21 23:26:59', 0, 100000, '2015-03-21 23:26:59', 1, 123, '123', '123.00', '0.00', '0.00');

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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='车辆保险' AUTO_INCREMENT=6 ;

--
-- 转存表中的数据 `vehicle_insurance`
--

INSERT INTO `vehicle_insurance` (`id`, `carframe_no`, `engine_no`, `license_plate`, `insurance_company`, `strong_insurance`, `strong_insurance_expire_at`, `vehicle_vessel_tax`, `business_insurance`, `business_insurance_expire_at`, `remark`, `create_by`, `create_at`, `update_by`, `update_at`) VALUES
(4, 'LSGUD82C07E028992', '77310255', '苏KEY991', '人保', 791, '2015-09-12 16:00:00', 1200, 5239.89, '2015-09-14 16:00:00', '', 100000, '2015-03-14 11:52:24', 0, '2015-03-14 11:52:24'),
(5, '123123', '13123', '12313', '123', 123, '2015-03-21 16:00:00', 123, 123, '2015-03-21 16:00:00', '', 100000, '2015-03-21 23:26:59', 0, '2015-03-21 23:26:59');

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
  `customer_id` int(11) DEFAULT '0' COMMENT '客户id（客户造成违章时录入）',
  `customer_name` varchar(64) DEFAULT NULL COMMENT '客户姓名（客户造成违章时录入）',
  PRIMARY KEY (`id`),
  KEY `carframe_no` (`carframe_no`,`engine_no`,`license_plate`,`status`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='车辆违章表' AUTO_INCREMENT=2 ;

--
-- 转存表中的数据 `vehicle_peccancy`
--

INSERT INTO `vehicle_peccancy` (`id`, `carframe_no`, `engine_no`, `license_plate`, `peccancy_at`, `peccancy_place`, `peccancy_reason`, `peccancy_price`, `score`, `status`, `arbitration`, `create_at`, `create_by`, `update_at`, `update_by`, `employee_id`, `employee_name`, `customer_id`, `customer_name`) VALUES
(1, 'LSGUD82C07E028992', '77310255', '苏KEY991', '2015-03-16 16:00:00', '213', '123132', '200.00', 0, 0, '123', '2015-03-17 08:32:34', 100000, '2015-03-17 08:32:34', 0, '123', '123', 123, '123');

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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='预约单' AUTO_INCREMENT=35 ;

--
-- 转存表中的数据 `vehicle_reservation`
--

INSERT INTO `vehicle_reservation` (`id`, `customer_name`, `customer_dn`, `use_begin`, `use_end`, `model`, `unit_price`, `quantity`, `with_driver`, `expenses_self`, `employee_id`, `employee_name`, `org_id`, `status`, `create_by`, `create_at`, `risk_control_update_by`, `risk_control_update_at`, `business_manager_update_by`, `business_manager_update_at`, `finance_update_by`, `finance_update_at`, `remark`, `contrace_type`) VALUES
(29, '姜寅', '15380897663', '2015-03-15 07:00:00', '2015-03-22 07:00:00', NULL, NULL, NULL, NULL, NULL, '12345', '11111', 100011, 1, 100000, '2015-03-14 11:59:19', NULL, '2015-03-14 11:59:19', NULL, '2015-03-14 11:59:19', NULL, '2015-03-14 11:59:19', '11111\n22222\n33333\n44444\n55555\n66666', 1),
(30, '123123', '13213', '2015-03-16 01:30:00', '2015-03-17 01:30:00', NULL, NULL, NULL, NULL, NULL, '123123', '123123', 1, 1, 100000, '2015-03-16 01:41:40', NULL, '2015-03-16 01:41:40', NULL, '2015-03-16 01:41:40', NULL, '2015-03-16 01:41:40', '123213213', 1),
(31, '12312313', '1231313', '2015-03-16 03:00:00', '2015-03-18 03:00:00', NULL, NULL, NULL, NULL, NULL, '0123213', '12313', 1, 1, 100000, '2015-03-16 03:32:24', NULL, '2015-03-16 03:32:24', NULL, '2015-03-16 03:32:24', NULL, '2015-03-16 03:32:24', '123131', 1),
(32, '123213', '123123', '2015-03-16 03:00:00', '2015-03-18 03:00:00', NULL, NULL, NULL, NULL, NULL, '0123213', '12313132213', 1, 1, 100000, '2015-03-16 03:34:17', NULL, '2015-03-16 03:34:17', NULL, '2015-03-16 03:34:17', NULL, '2015-03-16 03:34:17', '1232132132132', 1),
(33, '12313', '123213', '2015-03-16 06:00:00', '2015-03-20 06:00:00', NULL, NULL, NULL, NULL, NULL, '01231', '1231', 1, 0, 100000, '2015-03-16 06:21:12', NULL, '2015-03-16 06:21:12', NULL, '2015-03-16 06:21:12', NULL, '2015-03-16 06:21:12', '12312321', 1),
(34, '22131', '12313', '2015-03-15 16:00:00', '2015-03-16 04:00:00', NULL, NULL, NULL, NULL, NULL, '012313', '123213', 1, 1, 100000, '2015-03-16 08:20:25', NULL, '2015-03-16 08:20:25', NULL, '2015-03-16 08:20:25', NULL, '2015-03-16 08:20:25', '132213', 2);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
