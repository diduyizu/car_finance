-- phpMyAdmin SQL Dump
-- version 4.1.4
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: 2015-03-06 10:30:08
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
-- 表的结构 `customer_info`
--

CREATE TABLE IF NOT EXISTS `customer_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `customer_name` varchar(64) NOT NULL COMMENT '客户姓名',
  `certificate_type` varchar(32) NOT NULL DEFAULT '身份证' COMMENT '身份证，国际护照，回乡证，台胞证',
  `certificate_no` varchar(32) NOT NULL COMMENT '证件号码',
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
  PRIMARY KEY (`id`),
  KEY `identity_id` (`certificate_no`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='客户信息表' AUTO_INCREMENT=5 ;

--
-- 转存表中的数据 `customer_info`
--

INSERT INTO `customer_info` (`id`, `customer_name`, `certificate_type`, `certificate_no`, `customer_dn`, `customer_email`, `create_at`, `create_by`, `update_at`, `update_by`, `customer_type`, `customer_house`, `customer_vehicle`, `customer_guarantee`) VALUES
(1, '你好123', '身份证', '111111', '122222', 'aaa@aaa.com', '2015-02-25 19:59:13', 100000, '2015-02-25 19:59:13', 100000, '企业用户', '无111', '无222', '无333'),
(2, '测试用户', '身份证', '1234567890', '1231212312', '1213@qq.com', '2015-03-03 09:16:41', 100000, '2015-03-03 09:16:41', 100000, '个人用户', '房产一套', '奔驰车一辆', '你好'),
(3, '测试姓名', '身份证', '测试号码', '1234', '11@11.com', '2015-03-04 00:32:06', 100000, '2015-03-04 00:32:06', 0, '个人用户', '有', '有', '有'),
(4, '测试2', '国际护照', '12345', '12335', '11@11.com', '2015-03-04 00:46:23', 100000, '2015-03-04 00:46:23', 0, '个人用户', '11', '11', '11');

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
('StoreSeq', 100004, 1),
('UsersSeq', 100008, 1);

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
(25, '南京', 100025, 1),
(510, '无锡', 100025, 1),
(511, '镇江', 100025, 1),
(512, '苏州', 100025, 1),
(513, '南通', 100025, 1),
(514, '扬州', 100025, 1),
(515, '盐城', 100025, 1),
(516, '徐州', 100025, 1),
(517, '淮安', 100025, 1),
(518, '连云港', 100025, 1),
(519, '常州', 100025, 1),
(523, '泰州', 100025, 1),
(527, '宿迁', 100025, 1);

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
(10000, '人员管理', '', 0, 0, '人员管理', 'page', 'nav-home', 1, 10001),
(10001, '角色管理', 'carfinance/people/role/index', 10000, 0, '角色管理', 'page', NULL, 1, 0),
(10002, '人员管理', 'carfinance/people/people/index', 10000, 0, '人员管理', 'page', NULL, 1, 0),
(10003, '人员角色配置', 'carfinance/people/peoplerole/index', 10000, 0, '人员角色配置', 'page', NULL, 1, 0),
(10004, '角色权限配置', 'carfinance/people/rolemenu/index', 10000, 0, '角色权限配置', 'page', NULL, 1, 0),
(20000, '门店管理', '', 0, 0, '门店管理', 'page', 'nav-order', 1, 20001),
(20001, '门店查询', 'carfinance/store/query/index', 20000, 0, '门店查询', 'page', NULL, 1, 0),
(20002, '门店新增', 'carfinance/store/add/index', 20000, 0, '门店新增', 'page', NULL, 1, 0),
(30000, '车辆管理', '', 0, 0, '车辆管理', 'page', 'nav-storage', 1, 30001),
(30001, '车辆库存状态', 'carfinance/vehicle/register/index', 30000, 0, '车辆入库登记', 'page', NULL, 1, 0),
(30002, '车辆GPS地图暂时', '', 30000, 0, '车辆GPS地图暂时', 'page', NULL, 1, 0),
(30003, '车辆库存状态', 'carfinance/vehicle/leasestatus/index', 30000, 0, '车辆状态查询', 'page', NULL, 1, 0),
(30004, '车辆保险记录', 'carfinance/vehicle/insurance/index', 30000, 0, '车辆保险记录录入', 'page', NULL, 1, 0),
(30005, '车辆违章记录', 'carfinance/vehicle/peccancy/index', 30000, 0, '车辆违章记录录入', 'page', NULL, 1, 0),
(30006, '车辆保险到期提醒', 'carfinance/vehicle/insuranceremind/index', 30000, 0, '车辆保险到期提醒', 'page', NULL, 1, 1),
(30007, '车辆违章处理提醒', 'carfinance/vehicle/peccancyremind/index', 30000, 0, '车辆违章处理提醒', 'page', NULL, 1, 0),
(30008, '车辆保养提醒', 'carfinance/vehicle/maintainremind/index', 30000, 0, '车辆保养提醒', 'page', NULL, 1, 0),
(40000, '车辆业务办理', '', 0, 0, '车辆业务办理', 'page', 'nav-product', 1, 40001),
(40001, '业务办理审核工作流', 'http://www.xici.net', 40000, 0, '业务办理审核工作流', 'page', NULL, 1, 0),
(40002, '产权租赁登记', 'http://www.xici.net', 40000, 0, '产权租赁登记', 'page', NULL, 1, 0),
(40003, '车辆自用记录登记', 'http://www.xici.net', 40000, 0, '车辆自用记录登记', 'page', NULL, 1, 0),
(40004, '车辆配驾记录登记', 'http://www.xici.net', 40000, 0, '车辆配驾记录登记', 'page', NULL, 1, 0),
(40005, '产权租赁完结', 'http://www.xici.net', 40000, 0, '产权租赁完结', 'page', NULL, 1, 0),
(40006, '车辆自用记录完结', 'http://www.xici.net', 40000, 0, '车辆自用记录完结', 'page', NULL, 1, 0),
(40007, '车辆配架记录完结', 'http://www.xici.net', 40000, 0, '车辆配架记录完结', 'page', NULL, 1, 0),
(40008, '车辆预约单管理', 'carfinance/vehicleservice/reservation/index', 40000, 0, '车辆预约单管理', 'page', NULL, 1, 0),
(50000, '财务管理', '', 0, 0, '财务管理', 'page', 'nav-cost', 1, 50001),
(60000, '客户管理', '', 0, 0, '客户管理', 'page', 'nav-user', 1, 60001),
(60001, '客户资料维护', 'carfinance/customer/info/index', 60000, 0, '客户录入登记', 'page', NULL, 1, 0),
(60002, '客户业务开通查询', 'http://www.xici.net', 60000, 0, '客户业务开通查询', 'page', NULL, 1, 0),
(60003, '客户资料查询', 'http://www.xici.net', 60000, 0, '客户资料查询', 'page', NULL, 1, 0),
(60004, '客户资料修改', 'http://www.xici.net', 60000, 0, '客户资料修改', 'page', NULL, 1, 0),
(70000, '统计模块', '', 0, 0, '统计模块', 'page', 'nav-monitor', 1, 70001),
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
  PRIMARY KEY (`org_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `sys_org`
--

INSERT INTO `sys_org` (`org_id`, `org_name`, `pid`, `org_type`, `org_province`, `org_city`, `org_country`, `org_address`, `org_type_name`, `org_province_name`, `org_city_name`, `org_country_name`) VALUES
(1, '全国总公司', 0, 10, 100025, 25, 25001, '雄狮对面', NULL, NULL, NULL, NULL),
(10, '华东区公司', 1, 11, 100025, 25, 25001, '雄狮旁边', NULL, NULL, NULL, NULL),
(20, '江苏省公司', 10, 12, 100025, 25, 25001, '雄狮右边', NULL, NULL, NULL, NULL),
(50, '南京市公司', 20, 13, 100025, 25, 25001, '雄狮左边', NULL, NULL, NULL, NULL),
(100001, '二类门店测试店名', 50, 14, 100025, 25, 25002, '二类门店测试地址', NULL, NULL, NULL, NULL),
(100002, '苏州一类门店', 20, 13, 100025, 512, 0, '干将路', '市公司（一类门店）', '江苏省', '苏州', ''),
(100003, '苏州二类门店', 100002, 14, 100025, 512, 0, '人民路', '二类门店', '江苏省', '苏州', ''),
(100004, '苏州三类门店', 100002, 15, 100025, 512, 0, '狮山路', '三类门店', '江苏省', '苏州', '');

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
(20007, '配架司机', 1, '配架司机');

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
(20004, 20001, 1),
(20004, 20002, 1),
(20004, 30002, 1),
(20004, 30003, 1),
(20004, 20000, 1),
(20004, 30000, 1),
(20003, 30001, 1),
(20003, 30004, 1),
(20003, 30005, 1),
(20003, 40006, 1),
(20003, 40007, 1),
(20003, 60001, 1),
(20003, 60002, 1),
(20003, 30000, 1),
(20003, 40000, 1),
(20003, 60000, 1),
(10000, 10001, 1),
(10000, 10002, 1),
(10000, 10003, 1),
(10000, 10004, 1),
(10000, 20001, 1),
(10000, 20002, 1),
(10000, 30001, 1),
(10000, 30002, 1),
(10000, 30003, 1),
(10000, 30004, 1),
(10000, 30005, 1),
(10000, 30006, 1),
(10000, 30007, 1),
(10000, 30008, 1),
(10000, 40001, 1),
(10000, 40002, 1),
(10000, 40003, 1),
(10000, 40004, 1),
(10000, 40005, 1),
(10000, 40006, 1),
(10000, 40007, 1),
(10000, 40008, 1),
(10000, 60001, 1),
(10000, 60002, 1),
(10000, 60003, 1),
(10000, 60004, 1),
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
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=100009 ;

--
-- 转存表中的数据 `users`
--

INSERT INTO `users` (`user_id`, `login_name`, `login_pwd`, `user_name`, `nick_name`, `employee_id`, `head_url`, `birthday`, `address`, `email`, `create_by`, `create_at`, `status`) VALUES
(100000, 'sysadmin', '96E79218965EB72C92A549DD5A330112', '系统管理员a', '系统管理员', NULL, NULL, NULL, NULL, NULL, 10000, '', 1),
(100004, 'jiangyintest', '1BBD886460827015E5D605ED44252251', 'jiang', 'did', NULL, NULL, NULL, NULL, NULL, 100000, '2015-02-05 15:02:779', 1),
(100005, 'ce', '202CB962AC59075B964B07152D234B70', '12', '12', NULL, NULL, NULL, NULL, NULL, 100000, '2015-03-04 13:03:27', 1),
(100006, 'aa', '4124BC0A9335C27F086F24BA207A4912', 'aa', 'aa', NULL, NULL, NULL, NULL, NULL, 100000, '2015-03-04 13:03:926', 1),
(100007, 'aaa', '47BCE5C74F589F4867DBD57E9CA9F808', 'aaa', 'aaa', NULL, NULL, NULL, NULL, NULL, 100000, '2015-03-04 13:03:47', 1),
(100008, 'test1', '96E79218965EB72C92A549DD5A330112', 'test1', 'test1', NULL, NULL, NULL, NULL, NULL, 100000, '2015-03-04 13:03:558', 1);

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
(100004, 20004, 1, 1),
(100007, 20003, 100001, 1),
(100008, 20003, 100001, 1),
(100000, 10000, 1, 1),
(100004, 20002, 100001, 1),
(100004, 20003, 100001, 1),
(100004, 20004, 100001, 1);

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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='车辆信息表' AUTO_INCREMENT=117 ;

--
-- 转存表中的数据 `vehicle_info`
--

INSERT INTO `vehicle_info` (`id`, `brand`, `model`, `color`, `carframe_no`, `engine_no`, `buy_at`, `supplier`, `license_plate`, `card_at`, `limited_at`, `guide_price`, `vehicle_price`, `vehicle_tax`, `insurance_company`, `strong_insurance`, `strong_insurance_expire_at`, `vehicle_vessel_tax`, `business_insurance`, `business_insurance_expire_at`, `km`, `maintian_on_km`, `gps`, `current_city`, `current_shop`, `lease_status`, `peccancy_status`, `archive_no`, `inventory_no`, `registry_certificate`, `certificate_direction`, `loan_bank`, `consistency_cer`, `check_list`, `duty_paid_proof`, `record`, `remark`, `update_at`, `update_by`, `create_by`, `create_at`, `original_org`, `next_main_km`) VALUES
(111, '奔驰', '奔驰GLK300  2.996L', '灰色', 'WDDRJ7HA0BA001002', '15998060001027', '2015-03-05 06:13:44', '南京宁星', '苏A8GK20', '2014-11-04 16:00:00', '2015-02-17 16:00:00', '1000000.00', '428782.00', '40160.15', '紫金原件', '900.00', '2015-02-03 16:00:00', '1200.00', '200.00', '2015-02-02 16:00:00', 111, 0, '', 25, 0, '', 1, 'C-0005', '020007', '复（无第二页）', '在银行', '交行(周本浩)', '关单复印件', '2张原件', '无', '复印件', '登记证书原件在银行', '2015-02-27 07:04:50', 0, 100000, '2015-02-07 12:43:00', 1, 0),
(112, '121', '12', '12', '12', '12', '2015-03-05 06:13:42', '12', '12', '2015-02-10 16:00:00', '2015-02-18 16:00:00', '12.00', '12.00', '121.00', '12', '12.00', '2015-02-21 16:00:00', '12.00', '12.00', '2015-02-03 16:00:00', 234, 0, '', 510, 0, '', 1, '1212', '1212', '12', '12', '12', '12', '12', '12', '12', '12', '2015-02-27 07:04:50', 0, 100000, '2015-02-08 01:31:44', 1, 0),
(113, '111', '111', '111', '111', '111', '2015-03-05 06:13:39', '111', '111', '2015-02-17 16:00:00', '2015-02-16 16:00:00', '111.00', '111.00', '111.00', '111', '111.00', '2015-02-22 16:00:00', '111.00', '111.00', '2015-02-11 16:00:00', 12222, 0, '', 511, 0, '', 1, '111', '111', '111', '111', '111', '111', '111', '111', '111', '111', '2015-02-27 07:04:50', 0, 100000, '2015-02-09 05:38:25', 1, 0),
(114, '12', '12', '12', '12', '12', '2015-03-05 06:13:37', '12', '121', '2015-02-15 16:00:00', '2015-02-16 16:00:00', '12.00', '12.00', '12.00', '12', '12.00', '2015-02-23 16:00:00', '12.00', '12.00', '2015-02-10 16:00:00', 12305, 0, '', 512, 0, '', 1, '12', '12', '12', '12', '12', '12', '12', '12', '12', '12', '2015-02-27 07:04:50', 0, 100000, '2015-02-09 06:10:10', 1, 0),
(115, '宝马', '525Li', '白色', '1234567890', '1234567890', '2015-03-05 07:46:37', '南京', 'AP876D', '2015-02-25 16:00:00', '2015-03-25 16:00:00', '514367.87', '514367.87', '13.56', '你好', '123.12', '2015-03-29 16:00:00', '12.12', '12.12', '2015-03-24 16:00:00', 11111, 55555, '正常', 513, 1, '零租', 1, '1231213', '123131', '1231231', '1213213', '123123', '123213', '123123', '123213', '123213', '123123', '2015-02-28 05:17:07', NULL, 100000, '2015-02-28 05:17:07', 1, 66666),
(116, '宝马', '525', '红色', '23dsfwe3r22dsfr', 'wer3434fs', '2015-03-05 06:13:31', '11111', '苏ade123', '2015-03-03 16:00:00', '2015-03-03 16:00:00', '111111.00', '1111.00', '1111.00', '1111', '1111.00', '2015-03-03 16:00:00', '11111.00', '1111.00', '2015-03-03 16:00:00', 1111, 2222, '正常', 25, 1, '在库', 1, '', '2323', '1231', '1231', '111111', '123', '1231', '123', '123', '123', '2015-03-04 13:14:08', 0, 100000, '2015-03-04 13:14:08', 1, 3333);

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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='车辆保险' AUTO_INCREMENT=5 ;

--
-- 转存表中的数据 `vehicle_insurance`
--

INSERT INTO `vehicle_insurance` (`id`, `carframe_no`, `engine_no`, `license_plate`, `insurance_company`, `strong_insurance`, `strong_insurance_expire_at`, `vehicle_vessel_tax`, `business_insurance`, `business_insurance_expire_at`, `remark`, `create_by`, `create_at`, `update_by`, `update_at`) VALUES
(1, '12', '12', '121', '12', 12, '2015-02-10 16:00:00', 12, 12, '2015-02-10 16:00:00', '12', 100000, '2015-02-09 06:10:10', 0, '2015-02-27 07:08:23'),
(2, 'WDDRJ7HA0BA001002', '15998060001027', '苏A8GK20', '江阴', 12.12, '2015-02-23 16:00:00', 12.11, 13.13, '2015-02-26 16:00:00', '你好', 100000, '2015-02-24 12:00:47', 0, '2015-02-27 07:08:23'),
(3, '12', '12', '121', '1212', 12, '2015-02-08 16:00:00', 12, 12, '2015-02-09 16:00:00', '12', 100000, '2015-02-24 12:01:17', 0, '2015-02-27 07:08:23'),
(4, '23dsfwe3r22dsfr', 'wer3434fs', '苏ade123', '1111', 1111, '2015-03-03 16:00:00', 11111, 1111, '2015-03-03 16:00:00', '123', 100000, '2015-03-04 13:14:08', 0, '2015-03-04 13:14:08');

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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='车辆保养记录表' AUTO_INCREMENT=2 ;

--
-- 转存表中的数据 `vehicle_maintail_record`
--

INSERT INTO `vehicle_maintail_record` (`id`, `carframe_no`, `engine_no`, `license_plate`, `maintain_date`, `maintain_content`, `maintain_price`, `current_km`, `next_maintain_km`, `user_id`, `user_name`, `update_at`, `update_by`, `create_at`, `create_by`) VALUES
(1, '1234567890', '1234567890', 'AP876D', '2015-03-04 16:00:00', '机油', '300.00', 11111, 66666, 110, 'jiangyin', '2015-03-05 07:46:37', 0, '2015-03-05 07:46:37', 100000);

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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='车辆违章表' AUTO_INCREMENT=5 ;

--
-- 转存表中的数据 `vehicle_peccancy`
--

INSERT INTO `vehicle_peccancy` (`id`, `carframe_no`, `engine_no`, `license_plate`, `peccancy_at`, `peccancy_place`, `peccancy_reason`, `peccancy_price`, `score`, `status`, `arbitration`, `create_at`, `create_by`, `update_at`, `update_by`, `employee_id`, `employee_name`, `customer_id`, `customer_name`) VALUES
(1, '1', '1', '1', '2015-02-24 14:09:40', '1', '1', '0.00', 0, 0, '', '2015-02-24 14:09:40', 1, '2015-02-24 16:00:00', 0, '0', '', 0, NULL),
(2, 'WDDRJ7HA0BA001002', '15998060001027', '苏A8GK20', '2015-02-16 16:00:00', '南京', '红灯', '0.00', 3, 0, '', '2015-02-24 14:37:16', 100000, '2015-02-17 16:00:00', 0, '0', '', 0, NULL),
(3, 'WDDRJ7HA0BA001002', '15998060001027', '12', '2015-02-17 16:00:00', 'q w', '请问', '0.00', 3, 1, 'aaa', '2015-02-24 14:38:03', 100000, '2015-02-01 16:00:00', 100000, '0', '', 0, NULL),
(4, '1234567890', '1234567890', 'AP876D', '2015-02-12 16:00:00', '鞍山发大水发', '刷的发顺丰', '50.00', 3, 0, 'chuli', '2015-02-28 05:37:05', 100000, '2015-02-09 16:00:00', 100000, '0', '', 0, NULL);

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
  `model` varchar(128) NOT NULL COMMENT '车型',
  `unit_price` decimal(10,2) NOT NULL COMMENT '单价',
  `quantity` int(11) NOT NULL COMMENT '数量',
  `with_driver` int(11) NOT NULL COMMENT '是否要配架:1-要；0-不要',
  `expenses_self` int(11) NOT NULL COMMENT '是否自理油及过路停车费1：是；0：否',
  `employee_id` varchar(64) NOT NULL COMMENT '业务员工号/id',
  `employee_name` varchar(64) NOT NULL COMMENT '业务员姓名',
  `org_id` int(11) NOT NULL COMMENT '门店id',
  `status` varchar(11) NOT NULL DEFAULT '待审核' COMMENT '状态：待审核，风控通过，业务经理通过，财务通过，完结',
  `create_by` int(11) NOT NULL COMMENT '创建人',
  `create_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `risk_control_update_by` int(11) DEFAULT NULL COMMENT '风控审核人',
  `risk_control_update_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '风控审核时间',
  `business_manager_update_by` int(11) DEFAULT NULL COMMENT '业务经理审核人',
  `business_manager_update_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '业务经理审核时间',
  `finance_update_by` int(11) DEFAULT NULL COMMENT '财务审核人',
  `finance_update_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '财务经理审核时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='预约单' AUTO_INCREMENT=4 ;

--
-- 转存表中的数据 `vehicle_reservation`
--

INSERT INTO `vehicle_reservation` (`id`, `customer_name`, `customer_dn`, `use_begin`, `use_end`, `model`, `unit_price`, `quantity`, `with_driver`, `expenses_self`, `employee_id`, `employee_name`, `org_id`, `status`, `create_by`, `create_at`, `risk_control_update_by`, `risk_control_update_at`, `business_manager_update_by`, `business_manager_update_at`, `finance_update_by`, `finance_update_at`) VALUES
(1, 'jiangyin', '15380897663', '2016-03-26 20:00:00', '2016-03-01 17:00:00', '宝马535', '120.00', 2, 1, 0, '0', 'test', 100001, '待审核', 100000, '2015-03-06 08:16:22', NULL, '2015-03-06 08:16:22', NULL, '2015-03-06 08:16:22', NULL, '2015-03-06 08:16:22'),
(2, 'afdsaf', 'asdfasf', '2018-09-16 02:03:00', '2018-10-01 00:03:00', 'asdfsa', '12.00', 12, 1, 1, '0', 'asfd', 1, '待审核', 100000, '2015-03-06 08:21:48', NULL, '2015-03-06 08:21:48', NULL, '2015-03-06 08:21:48', NULL, '2015-03-06 08:21:48'),
(3, 'asfd', 'asf', '2017-06-11 05:03:00', '2018-09-15 21:03:00', 'asfd', '123.00', 2, 0, 0, '0', '212121', 1, '待审核', 100000, '2015-03-06 08:23:37', NULL, '2015-03-06 08:23:37', NULL, '2015-03-06 08:23:37', NULL, '2015-03-06 08:23:37');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
