-- phpMyAdmin SQL Dump
-- version 4.1.4
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: 2015-02-12 05:17:33
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
('StoreSeq', 100000, 1),
('UsersSeq', 100004, 1);

-- --------------------------------------------------------

--
-- 表的结构 `sys_city`
--

CREATE TABLE IF NOT EXISTS `sys_city` (
  `city_id` int(11) NOT NULL COMMENT '地市id，以区号明明',
  `city_name` varchar(11) NOT NULL COMMENT '地市名称',
  `province_id` int(11) NOT NULL COMMENT '该地市对应的省份id，省份信息，存在sys_enum表中',
  PRIMARY KEY (`city_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='地市表';

--
-- 转存表中的数据 `sys_city`
--

INSERT INTO `sys_city` (`city_id`, `city_name`, `province_id`) VALUES
(25, '南京', 100025),
(510, '无锡', 100025),
(511, '镇江', 100025),
(512, '苏州', 100025),
(513, '南通', 100025),
(514, '扬州', 100025),
(515, '盐城', 100025),
(516, '徐州', 100025),
(517, '淮安', 100025),
(518, '连云港', 100025),
(519, '常州', 100025),
(523, '泰州', 100025),
(527, '宿迁', 100025);

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
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `sys_menu`
--

INSERT INTO `sys_menu` (`menu_id`, `menu_name`, `menu_url`, `pid`, `level`, `menu_desc`, `type`, `css`, `status`) VALUES
(10000, '人员管理', '', 0, 0, '人员管理', 'page', 'nav-home', 1),
(10001, '角色管理', 'people/role/index', 10000, 0, '角色管理', 'page', NULL, 1),
(10002, '人员管理', 'people/people/index', 10000, 0, '人员管理', 'page', NULL, 1),
(10003, '人员角色配置', 'people/peoplerole/index', 10000, 0, '人员角色配置', 'page', NULL, 1),
(10004, '角色权限配置', 'people/rolemenu/index', 10000, 0, '角色权限配置', 'page', NULL, 1),
(20000, '门店管理', '', 0, 0, '门店管理', 'page', 'nav-order', 1),
(20001, '门店查询', 'store/query/index', 20000, 0, '门店查询', 'page', NULL, 1),
(20002, '门店新增', 'store/add/index', 20000, 0, '门店新增', 'page', NULL, 1),
(30000, '车辆管理', '', 0, 0, '车辆管理', 'page', NULL, 1),
(30001, '车辆入库登记', 'vehicle/register/index', 30000, 0, '车辆入库登记', 'page', NULL, 1),
(30002, '车辆GPS地图暂时', '', 30000, 0, '车辆GPS地图暂时', 'page', NULL, 1),
(30003, '车辆状态查询', '', 30000, 0, '车辆状态查询', 'page', NULL, 1),
(30004, '车辆保险记录录入', 'vehicle/insurance/index', 30000, 0, '车辆保险记录录入', 'page', NULL, 1),
(30005, '车辆违章记录录入', '', 30000, 0, '车辆违章记录录入', 'page', NULL, 1),
(30006, '车辆保险到期提醒', '', 30000, 0, '车辆保险到期提醒', 'page', NULL, 1),
(30007, '车辆违章处理提醒', 'http://www.xici.net', 30000, 0, '车辆违章处理提醒', 'page', NULL, 1),
(40000, '车辆业务办理', '', 0, 0, '车辆业务办理', 'page', NULL, 1),
(40001, '业务办理审核工作流', 'http://www.xici.net', 40000, 0, '业务办理审核工作流', 'page', NULL, 1),
(40002, '产权租赁登记', 'http://www.xici.net', 40000, 0, '产权租赁登记', 'page', NULL, 1),
(40003, '车辆自用记录登记', 'http://www.xici.net', 40000, 0, '车辆自用记录登记', 'page', NULL, 1),
(40004, '车辆配驾记录登记', 'http://www.xici.net', 40000, 0, '车辆配驾记录登记', 'page', NULL, 1),
(40005, '产权租赁完结', 'http://www.xici.net', 40000, 0, '产权租赁完结', 'page', NULL, 1),
(40006, '车辆自用记录完结', 'http://www.xici.net', 40000, 0, '车辆自用记录完结', 'page', NULL, 1),
(40007, '车辆配架记录完结', 'http://www.xici.net', 40000, 0, '车辆配架记录完结', 'page', NULL, 1),
(50000, '财务管理', '', 0, 0, '财务管理', 'page', NULL, 1),
(60000, '客户管理', '', 0, 0, '客户管理', 'page', NULL, 1),
(60001, '客户录入登记', 'http://www.xici.net', 60000, 0, '客户录入登记', 'page', NULL, 1),
(60002, '客户业务开通查询', 'http://www.xici.net', 60000, 0, '客户业务开通查询', 'page', NULL, 1),
(60003, '客户资料查询', 'http://www.xici.net', 60000, 0, '客户资料查询', 'page', NULL, 1),
(60004, '客户资料修改', 'http://www.xici.net', 60000, 0, '客户资料修改', 'page', NULL, 1),
(70000, '统计模块', '', 0, 0, '统计模块', 'page', NULL, 1),
(70001, '台帐', 'http://www.xici.net', 70000, 0, '台帐', 'page', NULL, 1);

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
  PRIMARY KEY (`org_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `sys_org`
--

INSERT INTO `sys_org` (`org_id`, `org_name`, `pid`, `org_type`, `org_province`, `org_city`, `org_country`, `org_address`) VALUES
(1, '全国总公司', 0, 10, 100025, 25, 25001, '雄狮对面'),
(10, '华东区公司', 1, 11, 100025, 25, 25001, '雄狮旁边'),
(20, '江苏省公司', 10, 12, 100025, 25, 25001, '雄狮右边'),
(50, '南京市公司', 20, 13, 100025, 25, 25001, '雄狮左边');

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
(10000, 10001, 1),
(10000, 10002, 1),
(10000, 10003, 1),
(10000, 20000, 1),
(10000, 20001, 1),
(10000, 20002, 1),
(10000, 20003, 1),
(10000, 30000, 1),
(10000, 30001, 1),
(10000, 30002, 1),
(10000, 30003, 1),
(10000, 30004, 1),
(10000, 30005, 1),
(10000, 30006, 1),
(10000, 30007, 1),
(10000, 40000, 1),
(10000, 40001, 1),
(10000, 40002, 1),
(10000, 40003, 1),
(10000, 40004, 1),
(10000, 40005, 1),
(10000, 40006, 1),
(10000, 40007, 1),
(10000, 50000, 1),
(10000, 60000, 1),
(10000, 60001, 1),
(10000, 60002, 1),
(10000, 60003, 1),
(10000, 60004, 1),
(10000, 70000, 1),
(10000, 70001, 1),
(10000, 10000, 1),
(10000, 10004, 1),
(20003, 40006, 1),
(20003, 40007, 1),
(20003, 60001, 1),
(20003, 60002, 1),
(20003, 60003, 1),
(20003, 60004, 1),
(20003, 40000, 1),
(20003, 60000, 1);

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
  `head_url` varchar(128) DEFAULT NULL,
  `birthday` varchar(64) DEFAULT NULL,
  `address` varchar(128) DEFAULT NULL,
  `email` varchar(128) DEFAULT NULL,
  `create_by` int(11) NOT NULL DEFAULT '10000' COMMENT '创建人id',
  `create_at` varchar(32) NOT NULL COMMENT '创建时间',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态：1－正常；0-删除',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=100005 ;

--
-- 转存表中的数据 `users`
--

INSERT INTO `users` (`user_id`, `login_name`, `login_pwd`, `user_name`, `nick_name`, `head_url`, `birthday`, `address`, `email`, `create_by`, `create_at`, `status`) VALUES
(100000, 'sysadmin', '96E79218965EB72C92A549DD5A330112', '系统管理员', '系统管理员', NULL, NULL, NULL, NULL, 10000, '', 1),
(100004, 'jiangyintest', '1BBD886460827015E5D605ED44252251', 'jiang', 'did', NULL, NULL, NULL, NULL, 100000, '2015-02-05 15:02:779', 1);

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
(100004, 20003, 1, 1);

-- --------------------------------------------------------

--
-- 表的结构 `vehicle_info`
--

CREATE TABLE IF NOT EXISTS `vehicle_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `archive_no` varchar(64) NOT NULL COMMENT '档案编号',
  `inventory_no` varchar(64) NOT NULL COMMENT '存货编码',
  `brand` varchar(32) NOT NULL COMMENT '品牌',
  `model` varchar(128) NOT NULL COMMENT '车型',
  `color` varchar(32) NOT NULL COMMENT '颜色',
  `carframe_no` varchar(64) NOT NULL COMMENT '车架号',
  `engine_no` varchar(64) NOT NULL COMMENT '发动机号',
  `registry_certificate` varchar(256) NOT NULL COMMENT '登记证书',
  `certificate_direction` varchar(64) NOT NULL COMMENT '登记证书去向',
  `loan_bank` varchar(64) NOT NULL COMMENT '贷款银行',
  `consistency_cer` varchar(256) NOT NULL COMMENT '关单/合格/一致性证书',
  `check_list` varchar(256) NOT NULL COMMENT '检验单',
  `duty_paid_proof` varchar(128) NOT NULL COMMENT '完税证明/小本',
  `record` varchar(128) NOT NULL COMMENT '记录',
  `buy_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '购买日期',
  `supplier` varchar(128) NOT NULL COMMENT '供应商名称',
  `license_plate` varchar(32) NOT NULL COMMENT '车牌号',
  `card_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '上牌登记日期',
  `limited_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '年审日期',
  `guide_price` double NOT NULL COMMENT '市场指导价',
  `vehicle_price` double NOT NULL COMMENT '车购价',
  `vehicle_tax` double NOT NULL COMMENT '车购税',
  `insurance_company` varchar(128) NOT NULL COMMENT '保险公司',
  `strong_insurance` double NOT NULL COMMENT '交强险',
  `vehicle_vessel_tax` double NOT NULL COMMENT '车船税',
  `strong_insurance_expire_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '交强险到期日期',
  `business_insurance` double NOT NULL COMMENT '商业险',
  `business_insurance_expire_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '商业险到期日期',
  `remark` varchar(128) NOT NULL COMMENT '备注',
  `create_by` int(11) NOT NULL COMMENT '创建人id',
  `create_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `original_org` int(11) NOT NULL COMMENT '归属门店',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='车辆信息表' AUTO_INCREMENT=115 ;

--
-- 转存表中的数据 `vehicle_info`
--

INSERT INTO `vehicle_info` (`id`, `archive_no`, `inventory_no`, `brand`, `model`, `color`, `carframe_no`, `engine_no`, `registry_certificate`, `certificate_direction`, `loan_bank`, `consistency_cer`, `check_list`, `duty_paid_proof`, `record`, `buy_at`, `supplier`, `license_plate`, `card_at`, `limited_at`, `guide_price`, `vehicle_price`, `vehicle_tax`, `insurance_company`, `strong_insurance`, `vehicle_vessel_tax`, `strong_insurance_expire_at`, `business_insurance`, `business_insurance_expire_at`, `remark`, `create_by`, `create_at`, `original_org`) VALUES
(111, 'C-0005', '020007', '奔驰', '奔驰GLK300  2.996L', '灰色', 'WDDRJ7HA0BA001002', '15998060001027', '复（无第二页）', '在银行', '交行(周本浩)', '关单复印件', '2张原件', '无', '复印件', '2011-11-30 16:00:00', '南京宁星', '苏A8GK20', '2014-11-04 16:00:00', '2015-02-17 16:00:00', 1000000, 428782, 40160.15, '紫金原件', 900, 1200, '2015-02-03 16:00:00', 200, '2015-02-02 16:00:00', '登记证书原件在银行', 100000, '2015-02-07 12:43:00', 1),
(112, '1212', '1212', '121', '12', '12', '12', '12', '12', '12', '12', '12', '12', '12', '12', '2015-01-31 16:00:00', '12', '12', '2015-02-10 16:00:00', '2015-02-18 16:00:00', 12, 12, 121, '12', 12, 12, '2015-02-01 16:00:00', 12, '2015-02-03 16:00:00', '12', 100000, '2015-02-08 01:31:44', 1),
(113, '111', '111', '111', '111', '111', '111', '111', '111', '111', '111', '111', '111', '111', '111', '2015-02-01 16:00:00', '111', '111', '2015-02-17 16:00:00', '2015-02-16 16:00:00', 111, 111, 111, '111', 111, 111, '2015-02-10 16:00:00', 111, '2015-02-11 16:00:00', '111', 100000, '2015-02-09 05:38:25', 1),
(114, '12', '12', '12', '12', '12', '12', '12', '12', '12', '12', '12', '12', '12', '12', '2015-02-08 16:00:00', '12', '121', '2015-02-15 16:00:00', '2015-02-16 16:00:00', 12, 12, 12, '12', 12, 12, '2015-02-10 16:00:00', 12, '2015-02-10 16:00:00', '12', 100000, '2015-02-09 06:10:10', 1);

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
  `vehicle_vessel_tax` double NOT NULL COMMENT '车船税',
  `strong_insurance_expire_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '交强险到期时间',
  `business_insurance` double NOT NULL COMMENT '商业险',
  `business_insurance_expire_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '商业险到期时间',
  `remark` varchar(128) NOT NULL COMMENT '备注',
  `create_by` int(11) NOT NULL COMMENT '创建人',
  `create_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `carframe_no` (`carframe_no`,`engine_no`),
  KEY `license_plate` (`license_plate`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='车辆保险' AUTO_INCREMENT=2 ;

--
-- 转存表中的数据 `vehicle_insurance`
--

INSERT INTO `vehicle_insurance` (`id`, `carframe_no`, `engine_no`, `license_plate`, `insurance_company`, `strong_insurance`, `vehicle_vessel_tax`, `strong_insurance_expire_at`, `business_insurance`, `business_insurance_expire_at`, `remark`, `create_by`, `create_at`) VALUES
(1, '12', '12', '121', '12', 12, 12, '2015-02-10 16:00:00', 12, '2015-02-10 16:00:00', '12', 100000, '2015-02-09 06:10:10');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
