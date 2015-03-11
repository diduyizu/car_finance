-- phpMyAdmin SQL Dump
-- version 4.1.6
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: 2015-03-11 14:59:09
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户信息表' AUTO_INCREMENT=1 ;

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
('ContraceSeq', 100015, 1),
('StoreSeq', 100009, 1),
('UsersSeq', 100019, 1);

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
(510, '无锡', 100025, 0),
(511, '镇江', 100025, 1),
(512, '苏州', 100025, 1),
(513, '南通', 100025, 0),
(514, '扬州', 100025, 0),
(515, '盐城', 100025, 0),
(516, '徐州', 100025, 0),
(517, '淮安', 100025, 0),
(518, '连云港', 100025, 0),
(519, '常州', 100025, 0),
(523, '泰州', 100025, 0),
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
(10000, '人员管理', '', 0, 0, '人员管理', 'page', 'nav-home', 1, 0),
(10001, '角色管理', 'carfinance/people/role/index', 10000, 0, '角色管理', 'page', NULL, 1, 0),
(10002, '人员管理', 'carfinance/people/people/index', 10000, 0, '人员管理', 'page', NULL, 1, 0),
(10003, '人员角色配置', 'carfinance/people/peoplerole/index', 10000, 0, '人员角色配置', 'page', NULL, 1, 0),
(10004, '角色权限配置', 'carfinance/people/rolemenu/index', 10000, 0, '角色权限配置', 'page', NULL, 1, 0),
(20000, '门店管理', '', 0, 0, '门店管理', 'page', 'nav-order', 1, 0),
(20001, '门店查询', 'carfinance/store/query/index', 20000, 0, '门店查询', 'page', NULL, 1, 0),
(20002, '门店新增', 'carfinance/store/add/index', 20000, 0, '门店新增', 'page', NULL, 1, 0),
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
(40003, '合同管理', 'carfinance/vehicleservice/contrace/index', 40000, 0, '合同管理', 'page', NULL, 1, 0),
(40006, '合同店长审核', 'carfinance/vehicleservice/contrace/shopowner/audit', 40000, 0, '预约单业务经理审核', 'page', NULL, 1, 0),
(40007, '合同市店长审核', 'carfinance/vehicleservice/contrace/cityshopowner/audit', 40000, 0, '合同市店长审核', 'page', NULL, 1, 0),
(40008, '合同区域经理审核', 'carfinance/vehicleservice/contrace/regionalmanager/audit', 40000, 0, '合同区域经理审核', 'page', NULL, 1, 0),
(40009, '合同财务审核', 'carfinance/vehicleservice/contrace/finance/audit', 40000, 0, '预约单财务审核', 'page', NULL, 1, 0),
(40020, '业务办理审核工作流', 'http://www.xici.net', 40000, 0, '业务办理审核工作流', 'page', NULL, 0, 0),
(40021, '产权租赁登记', 'http://www.xici.net', 40000, 0, '产权租赁登记', 'page', NULL, 0, 0),
(40022, '车辆自用记录登记', 'http://www.xici.net', 40000, 0, '车辆自用记录登记', 'page', NULL, 0, 0),
(40023, '车辆配驾记录登记', 'http://www.xici.net', 40000, 0, '车辆配驾记录登记', 'page', NULL, 0, 0),
(40024, '产权租赁完结', 'http://www.xici.net', 40000, 0, '产权租赁完结', 'page', NULL, 0, 0),
(40025, '车辆自用记录完结', 'http://www.xici.net', 40000, 0, '车辆自用记录完结', 'page', NULL, 0, 0),
(40026, '车辆配架记录完结', 'http://www.xici.net', 40000, 0, '车辆配架记录完结', 'page', NULL, 0, 0),
(40027, '预约单风控审核', 'carfinance/vehicleservice/riskcontrol/audit', 40000, 0, '预约单凤控审核', 'page', NULL, 0, 0),
(50000, '财务管理', '', 0, 0, '财务管理', 'page', 'nav-cost', 1, 0),
(60000, '客户管理', '', 0, 0, '客户管理', 'page', 'nav-user', 1, 0),
(60001, '客户资料维护', 'carfinance/customer/info/index', 60000, 0, '客户录入登记', 'page', NULL, 1, 0),
(60002, '客户业务开通查询', 'http://www.xici.net', 60000, 0, '客户业务开通查询', 'page', NULL, 1, 0),
(60003, '客户资料查询', 'http://www.xici.net', 60000, 0, '客户资料查询', 'page', NULL, 1, 0),
(60004, '客户资料修改', 'http://www.xici.net', 60000, 0, '客户资料修改', 'page', NULL, 1, 0),
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
  PRIMARY KEY (`org_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `sys_org`
--

INSERT INTO `sys_org` (`org_id`, `org_name`, `pid`, `org_type`, `org_province`, `org_city`, `org_country`, `org_address`, `org_type_name`, `org_province_name`, `org_city_name`, `org_country_name`) VALUES
(1, '全国总公司', 0, 10, 100025, 25, 25001, '雄狮对面', '全国总公司', '江苏省', '南京市', '玄武区'),
(10, '华东区公司', 1, 11, 100025, 25, 25001, '雄狮旁边', '华东区公司', '江苏省', '南京市', '玄武区'),
(20, '江苏省公司', 10, 12, 100025, 25, 25001, '雄狮右边', '江苏省公司', '江苏省', '南京市', '玄武区'),
(50, '南京市公司', 20, 13, 100025, 25, 25001, '雄狮左边', '南京市公司', '江苏省', '南京市', '玄武区'),
(100005, '苏州市公司', 20, 13, 100025, 512, 0, '干将路183号', '市公司（一类门店）', '江苏省', '苏州', ''),
(100006, '苏州市二类门店', 100005, 14, 100025, 512, 0, '苏州市狮山路', '二类门店', '江苏省', '苏州', ''),
(100007, '宿迁市公司', 20, 13, 100025, 527, 0, '宿迁市地址', '市公司（一类门店）', '江苏省', '宿迁', ''),
(100008, '镇江市公司', 20, 13, 100025, 511, 0, '镇江市', '市公司（一类门店）', '江苏省', '镇江', ''),
(100009, '镇江二类门店', 100008, 14, 100025, 511, 0, '镇江市', '二类门店', '江苏省', '镇江', '');

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
(10000, 10001, 1),
(10000, 10002, 1),
(10000, 10003, 1),
(10000, 10004, 1),
(10000, 20001, 1),
(10000, 20002, 1),
(10000, 30001, 1),
(10000, 30003, 1),
(10000, 30004, 1),
(10000, 30005, 1),
(10000, 30006, 1),
(10000, 30007, 1),
(10000, 40001, 1),
(10000, 40002, 1),
(10000, 40003, 1),
(10000, 40006, 1),
(10000, 40007, 1),
(10000, 40008, 1),
(10000, 40009, 1),
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
(10000, 70000, 1),
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
  `employee_id` varchar(64) DEFAULT NULL COMMENT '员工工号，员工id',
  `head_url` varchar(128) DEFAULT NULL,
  `birthday` varchar(64) DEFAULT NULL,
  `address` varchar(128) DEFAULT NULL,
  `email` varchar(128) DEFAULT NULL,
  `create_by` int(11) NOT NULL DEFAULT '10000' COMMENT '创建人id',
  `create_at` varchar(32) NOT NULL COMMENT '创建时间',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态：1－正常；0-删除',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=100020 ;

--
-- 转存表中的数据 `users`
--

INSERT INTO `users` (`user_id`, `login_name`, `login_pwd`, `user_name`, `nick_name`, `employee_id`, `head_url`, `birthday`, `address`, `email`, `create_by`, `create_at`, `status`) VALUES
(100000, 'sysadmin', '96E79218965EB72C92A549DD5A330112', '系统管理员a', '系统管理员', NULL, NULL, NULL, NULL, NULL, 10000, '', 1),
(100011, 'jiangyinyewuyuan', '96E79218965EB72C92A549DD5A330112', '僵硬业务员', '僵硬业务员', '1234', NULL, NULL, NULL, NULL, 100000, '2015-03-07 15:03:949', 1),
(100012, 'jiangyinyewujingli', '96E79218965EB72C92A549DD5A330112', '僵硬业务经理', '僵硬业务经理', '2345', NULL, NULL, NULL, NULL, 100000, '2015-03-07 15:03:292', 1),
(100013, 'jiangyinfengkong', '96E79218965EB72C92A549DD5A330112', '僵硬风控', '僵硬风控', '3456', NULL, NULL, NULL, NULL, 100000, '2015-03-07 15:03:214', 1),
(100014, 'jiangyingcaiwu', '96E79218965EB72C92A549DD5A330112', '僵硬财务', '僵硬财务', '4567', NULL, NULL, NULL, NULL, 100000, '2015-03-07 15:03:529', 1),
(100015, 'zhenjiangyewuyuan', '96E79218965EB72C92A549DD5A330112', '镇江业务员', '镇江业务员', '1234', NULL, NULL, NULL, NULL, 100000, '2015-03-11 18:03:726', 1),
(100016, 'zhenjiangdianzhang', '96E79218965EB72C92A549DD5A330112', '镇江店长', '镇江店长', '1234', NULL, NULL, NULL, NULL, 100000, '2015-03-11 18:03:609', 1),
(100017, 'zhenjiangshidianzhang', '96E79218965EB72C92A549DD5A330112', '镇江市店长', '镇江市店长', '1234', NULL, NULL, NULL, NULL, 100000, '2015-03-11 18:03:404', 1),
(100018, 'quyujingli', '96E79218965EB72C92A549DD5A330112', '华东区经理', '华东区经理', '1234', NULL, NULL, NULL, NULL, 100000, '2015-03-11 18:03:625', 1),
(100019, 'zhenjiangcaiwu', '96E79218965EB72C92A549DD5A330112', '镇江财务', '镇江财务', '1234', NULL, NULL, NULL, NULL, 100000, '2015-03-11 18:03:326', 1);

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
(100011, 20003, 100006, 1),
(100012, 20004, 100006, 1),
(100013, 20006, 100006, 1),
(100014, 20005, 100006, 1),
(100015, 20003, 100009, 1),
(100016, 20008, 100009, 1),
(100017, 20009, 100008, 1),
(100018, 20010, 10, 1),
(100019, 20005, 100009, 1);

-- --------------------------------------------------------

--
-- 表的结构 `vehicle_contrace`
--

CREATE TABLE IF NOT EXISTS `vehicle_contrace` (
  `id` int(11) NOT NULL COMMENT '主键',
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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='合同表';

--
-- 转存表中的数据 `vehicle_contrace`
--

INSERT INTO `vehicle_contrace` (`id`, `customer_name`, `customer_type`, `customer_dn`, `customer_cer_type`, `customer_cer_no`, `remark`, `employee_id`, `employee_name`, `create_at`, `create_by`, `update_at`, `update_by`, `status`, `shopowner_update_by`, `shopowner_update_at`, `city_shopowner_update_by`, `city_shopowner_update_at`, `finance_update_by`, `finance_update_at`, `org_id`, `reservation_id`, `regional_manager_update_by`, `regional_manager_update_at`, `use_begin`, `use_end`, `isovertop`) VALUES
(100003, 'asfdsa', '企业用户', 'asfsadf', '回乡证', 'wr2r32r', '1111111111111', 'wre', 'sadfdsaf', '2015-03-09 07:19:21', 100000, '2015-03-09 07:19:21', 0, 6, 100000, '2015-03-11 01:17:06', 100000, '2015-03-11 01:29:32', 100000, '2015-03-11 01:36:25', 1, 10, 100000, '2015-03-11 01:33:31', '2017-10-10 00:03:00', '2017-10-10 00:03:00', 0),
(100004, '“”', '个人用户', '“”', '身份证', '“”', '“”', '0', '“”', '2015-03-09 07:26:52', 100000, '2015-03-09 07:26:52', 0, -2, 100000, '2015-03-11 01:46:50', 0, '2015-03-09 07:26:52', 0, '2015-03-09 07:26:52', 1, 11, 0, '2015-03-09 07:26:52', '2015-03-10 00:34:34', '2015-03-10 00:34:34', 0),
(100005, '“”', '个人用户', '“”', '身份证', '“”', '“”', '0', '“”', '2015-03-09 07:43:39', 100000, '2015-03-09 07:43:39', 0, -3, 100000, '2015-03-11 01:47:38', 100000, '2015-03-11 01:47:43', 0, '2015-03-09 07:43:39', 1, 12, 0, '2015-03-09 07:43:39', '2015-03-10 00:34:34', '2015-03-10 00:34:34', 0),
(100006, '“”', '个人用户', '“”', '身份证', '“”', '“”', '0', '“”', '2015-03-09 07:48:10', 100000, '2015-03-09 07:48:10', 0, -4, 100000, '2015-03-11 01:47:58', 100000, '2015-03-11 01:48:01', 0, '2015-03-09 07:48:10', 1, 13, 100000, '2015-03-11 01:48:05', '2015-03-10 00:34:34', '2015-03-10 00:34:34', 0),
(100007, '定时发送', '个人用户', '123213213', '国际护照', '12313213', '艾丝凡萨芬', '1231', '1asdfsadf', '2015-03-09 07:49:38', 100000, '2015-03-09 07:49:38', 0, -5, 100000, '2015-03-11 01:48:14', 100000, '2015-03-11 01:48:22', 100000, '2015-03-11 01:48:30', 1, 14, 100000, '2015-03-11 01:48:26', '2017-10-10 00:03:00', '2017-10-10 00:03:00', 0),
(100008, '“”', '个人用户', '“”', '身份证', '“”', '“”', '0', '“”', '2015-03-09 13:05:36', 100000, '2015-03-09 13:05:36', 0, 0, 0, '2015-03-09 13:05:36', 0, '2015-03-09 13:05:36', 0, '2015-03-09 13:05:36', 1, 15, 0, '2015-03-09 13:05:36', '2015-03-10 00:34:34', '2015-03-10 00:34:34', 0),
(100009, NULL, '个人用户', NULL, '身份证', NULL, NULL, '0', NULL, '2015-03-10 03:19:16', 100000, '2015-03-10 03:19:16', 0, 0, 0, '2015-03-10 03:19:16', 0, '2015-03-10 03:19:16', 0, '2015-03-10 03:19:16', 1, 16, 0, '2015-03-10 03:19:16', '2015-03-10 03:19:16', '2015-03-10 03:19:16', 0),
(100010, NULL, '个人用户', NULL, '身份证', NULL, NULL, '0', NULL, '2015-03-11 10:57:36', 100015, '2015-03-11 10:57:36', 0, 0, 0, '2015-03-11 10:57:36', 0, '2015-03-11 10:57:36', 0, '2015-03-11 10:57:36', 100009, 26, 0, '2015-03-11 10:57:36', '2015-03-11 10:57:36', '2015-03-11 10:57:36', 0),
(100011, NULL, '个人用户', NULL, '身份证', NULL, NULL, '0', NULL, '2015-03-11 12:23:03', 100015, '2015-03-11 12:23:03', 0, 0, 0, '2015-03-11 12:23:03', 0, '2015-03-11 12:23:03', 0, '2015-03-11 12:23:03', 100009, 26, 0, '2015-03-11 12:23:03', '2015-03-11 12:23:03', '2015-03-11 12:23:03', 0),
(100012, 'zddfsf', '个人用户', 'sdfsafas', '身份证', 'asdfsafdsaf', 'sfdsafdsaf', '0adfsaf', 'sadfsaf', '2015-03-11 12:26:51', 100015, '2015-03-11 12:26:51', 0, 0, 0, '2015-03-11 12:26:51', 0, '2015-03-11 12:26:51', 0, '2015-03-11 12:26:51', 100009, 27, 0, '2015-03-11 12:26:51', '2015-03-11 03:00:00', '2015-03-12 04:30:00', 0),
(100013, NULL, '个人用户', NULL, '身份证', NULL, NULL, '0', NULL, '2015-03-11 12:27:07', 100015, '2015-03-11 12:27:07', 0, 0, 0, '2015-03-11 12:27:07', 0, '2015-03-11 12:27:07', 0, '2015-03-11 12:27:07', 100009, 27, 0, '2015-03-11 12:27:07', '2015-03-11 12:27:07', '2015-03-11 12:27:07', 0),
(100014, 'jiangyin', '个人用户', '15380897663', '身份证', '人人2224324', '德国方法范儿儿儿人人人过分的人工费登革热个人头人儿童二', '1234', 'jiangyin', '2015-03-11 12:37:59', 100015, '2015-03-11 12:37:59', 0, 0, 0, '2015-03-11 12:37:59', 0, '2015-03-11 12:37:59', 0, '2015-03-11 12:37:59', 100009, 28, 0, '2015-03-11 12:37:59', '2015-05-01 04:00:00', '2015-05-10 04:00:00', 0),
(100015, NULL, '个人用户', NULL, '身份证', NULL, NULL, '0', NULL, '2015-03-11 12:38:23', 100015, '2015-03-11 12:38:23', 0, 0, 0, '2015-03-11 12:38:23', 0, '2015-03-11 12:38:23', 0, '2015-03-11 12:38:23', 100009, 28, 0, '2015-03-11 12:38:23', '2015-03-11 12:38:23', '2015-03-11 12:38:23', 0);

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
  `isother` int(11) NOT NULL DEFAULT '0' COMMENT '是否是外借车辆：0-不是；1-是',
  `driving_user_id` varchar(128) DEFAULT '0' COMMENT '配驾员工号/id',
  `driving_user_name` varchar(64) DEFAULT NULL COMMENT '配驾员姓名',
  `create_by` int(11) NOT NULL COMMENT '创建人',
  `create_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` int(11) NOT NULL DEFAULT '0' COMMENT '更新人',
  `update_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `vehicle_price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '车购价，用来计算本次合同车辆总价，决定是否需要市门店经理审核',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='合同对应车辆详细表' AUTO_INCREMENT=3 ;

--
-- 转存表中的数据 `vehicle_contrace_vehs`
--

INSERT INTO `vehicle_contrace_vehs` (`id`, `contrace_id`, `vehicle_id`, `license_plate`, `model`, `company`, `isother`, `driving_user_id`, `driving_user_name`, `create_by`, `create_at`, `update_by`, `update_at`, `vehicle_price`) VALUES
(1, 100015, 2, NULL, NULL, NULL, 0, '0', NULL, 100015, '2015-03-11 13:57:04', 0, '2015-03-11 13:57:04', '678954.00'),
(2, 100015, 2, NULL, NULL, NULL, 0, '0', NULL, 100015, '2015-03-11 13:58:40', 0, '2015-03-11 13:58:40', '678954.00');

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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='车辆信息表' AUTO_INCREMENT=3 ;

--
-- 转存表中的数据 `vehicle_info`
--

INSERT INTO `vehicle_info` (`id`, `brand`, `model`, `color`, `carframe_no`, `engine_no`, `buy_at`, `supplier`, `license_plate`, `card_at`, `limited_at`, `guide_price`, `vehicle_price`, `vehicle_tax`, `insurance_company`, `strong_insurance`, `strong_insurance_expire_at`, `vehicle_vessel_tax`, `business_insurance`, `business_insurance_expire_at`, `km`, `maintian_on_km`, `gps`, `current_city`, `current_shop`, `lease_status`, `peccancy_status`, `archive_no`, `inventory_no`, `registry_certificate`, `certificate_direction`, `loan_bank`, `consistency_cer`, `check_list`, `duty_paid_proof`, `record`, `remark`, `update_at`, `update_by`, `create_by`, `create_at`, `original_org`, `next_main_km`) VALUES
(1, '奔驰', 's600', '黑色', '1231sdfs23424', '3432424wefwef', '2015-03-15 16:00:00', '12121', '苏ad12345', '2015-03-08 16:00:00', '2015-03-08 16:00:00', '12121.00', '12121.00', '121212.00', '1212', '1212.00', '2015-03-23 16:00:00', '1212.00', '121.00', '2015-03-23 16:00:00', 11111, 22222, '正常', 512, 100006, '在库', 0, '', '121212', '12121', '12121', '21212', '12121', '12121', '12121', '12121', '12121', '2015-03-09 12:24:14', 0, 100000, '2015-03-09 12:24:14', 1, 33333),
(2, '宝马', '535', '黑色', '234werw2342', '343424wewff', '2015-03-11 13:58:40', '1213', 'ad12345', '2015-03-16 16:00:00', '2015-03-17 16:00:00', '678954.00', '678954.00', '12313.00', '123', '1123.00', '2015-03-24 16:00:00', '123.00', '1231.00', '2015-03-17 16:00:00', 11111, 22222, '正常', 511, 100009, '出库中', 0, '', '12313', '12313', '12313', '12321', '123213', '12313', '13213', '12313', '12313', '2015-03-11 13:20:23', 0, 100015, '2015-03-11 13:20:23', 100009, 33333);

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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='车辆保险' AUTO_INCREMENT=3 ;

--
-- 转存表中的数据 `vehicle_insurance`
--

INSERT INTO `vehicle_insurance` (`id`, `carframe_no`, `engine_no`, `license_plate`, `insurance_company`, `strong_insurance`, `strong_insurance_expire_at`, `vehicle_vessel_tax`, `business_insurance`, `business_insurance_expire_at`, `remark`, `create_by`, `create_at`, `update_by`, `update_at`) VALUES
(1, '1231sdfs23424', '3432424wefwef', '苏ad12345', '1212', 1212, '2015-03-23 16:00:00', 1212, 121, '2015-03-23 16:00:00', '12121', 100000, '2015-03-09 12:24:14', 0, '2015-03-09 12:24:14'),
(2, '234werw2342', '343424wewff', 'ad12345', '123', 1123, '2015-03-24 16:00:00', 123, 1231, '2015-03-17 16:00:00', '12313', 100015, '2015-03-11 13:20:23', 0, '2015-03-11 13:20:23');

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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='预约单' AUTO_INCREMENT=29 ;

--
-- 转存表中的数据 `vehicle_reservation`
--

INSERT INTO `vehicle_reservation` (`id`, `customer_name`, `customer_dn`, `use_begin`, `use_end`, `model`, `unit_price`, `quantity`, `with_driver`, `expenses_self`, `employee_id`, `employee_name`, `org_id`, `status`, `create_by`, `create_at`, `risk_control_update_by`, `risk_control_update_at`, `business_manager_update_by`, `business_manager_update_at`, `finance_update_by`, `finance_update_at`, `remark`) VALUES
(4, '测试客户', '15380897663', '2017-06-11 07:03:00', '2017-06-23 07:03:00', '奔驰s600', '200.00', 2, 0, 1, '1234', '僵硬业务员', 100006, 0, 100011, '2015-03-07 07:24:53', 100011, '2015-03-07 08:11:55', NULL, '2015-03-07 07:24:53', NULL, '2015-03-07 07:24:53', ''),
(5, '测试客户不通过', '15380897663', '2016-03-11 07:03:00', '2014-12-10 09:03:00', '宝马725', '120.00', 2, 1, 1, '1234', '僵硬业务员', 100006, 0, 100011, '2015-03-07 07:27:10', 100013, '2015-03-07 08:20:27', NULL, '2015-03-07 07:27:10', NULL, '2015-03-07 07:27:10', ''),
(6, '姜寅', '15380897663', '2017-06-18 06:03:00', '2016-03-23 01:03:00', NULL, NULL, NULL, NULL, NULL, '1231', 'asdf', 1, 2, 100000, '2015-03-09 03:17:50', NULL, '2015-03-09 03:17:50', NULL, '2015-03-09 03:17:50', NULL, '2015-03-09 03:17:50', '需要5辆车\n1两宝马，2两奔驰，2两奥迪\n宝马要配驾，奔驰、奥迪不要配驾\n自理油费过路费\n总共支付2万元'),
(7, '姜寅', '15380897663', '2018-09-09 05:03:00', '2016-03-09 05:03:00', NULL, NULL, NULL, NULL, NULL, '123123', '啊撒范德萨发', 100006, 2, 100000, '2015-03-09 06:59:06', NULL, '2015-03-09 06:59:06', NULL, '2015-03-09 06:59:06', NULL, '2015-03-09 06:59:06', '需要5辆车 1两宝马，2两奔驰，2两奥迪 宝马要配驾，奔驰、奥迪不要配驾 自理油费过路费 总共支付2万元'),
(8, '按时发生', '123123321', '2017-06-09 02:03:00', '2016-03-10 07:03:00', NULL, NULL, NULL, NULL, NULL, '01212', '123213sadf', 100006, 1, 100000, '2015-03-09 07:09:38', NULL, '2015-03-09 07:09:38', NULL, '2015-03-09 07:09:38', NULL, '2015-03-09 07:09:38', '啊发顺丰'),
(9, '沃尔沃', '温热污染', '2016-03-02 21:03:00', '2014-12-08 16:03:00', NULL, NULL, NULL, NULL, NULL, '0123', '123佛挡杀佛', 100006, 1, 100000, '2015-03-09 07:16:41', NULL, '2015-03-09 07:16:41', NULL, '2015-03-09 07:16:41', NULL, '2015-03-09 07:16:41', 'asfafs'),
(10, '撒范德萨', '鞍山发大水发', '2017-06-19 06:03:00', '2016-03-10 01:03:00', NULL, NULL, NULL, NULL, NULL, '0阿发', '阿双方打算', 1, 1, 100000, '2015-03-09 07:19:17', NULL, '2015-03-09 07:19:17', NULL, '2015-03-09 07:19:17', NULL, '2015-03-09 07:19:17', '暗示法斯蒂芬'),
(11, '啊撒范德萨', '按时发达', '2016-03-11 01:03:00', '2014-12-02 00:03:00', NULL, NULL, NULL, NULL, NULL, '0阿凡达', '萨芬的', 1, 1, 100000, '2015-03-09 07:26:49', NULL, '2015-03-09 07:26:49', NULL, '2015-03-09 07:26:49', NULL, '2015-03-09 07:26:49', '阿斯顿发萨菲'),
(12, '啊撒范德萨', '阿萨德发的是', '2016-03-11 01:03:00', '2016-03-08 17:03:00', NULL, NULL, NULL, NULL, NULL, '0阿范德萨发', '啊事发地点时', 1, 1, 100000, '2015-03-09 07:43:19', NULL, '2015-03-09 07:43:19', NULL, '2015-03-09 07:43:19', NULL, '2015-03-09 07:43:19', '阿萨德发'),
(13, '啊撒飞洒', '阿萨德发的是', '2016-03-10 01:03:00', '2016-03-08 17:03:00', NULL, NULL, NULL, NULL, NULL, '0阿发', '啊sfd', 1, 1, 100000, '2015-03-09 07:48:06', NULL, '2015-03-09 07:48:06', NULL, '2015-03-09 07:48:06', NULL, '2015-03-09 07:48:06', '艾丝凡'),
(14, '撒范德萨', '按时发达', '2016-03-09 00:03:00', '2016-03-09 07:03:00', NULL, NULL, NULL, NULL, NULL, '0阿发', '爱的发', 1, 1, 100000, '2015-03-09 07:49:31', NULL, '2015-03-09 07:49:31', NULL, '2015-03-09 07:49:31', NULL, '2015-03-09 07:49:31', '按时发达'),
(15, '阿地方打发的身份', '12312313', '2016-03-11 01:03:00', '2014-12-09 01:03:00', NULL, NULL, NULL, NULL, NULL, '01213', '12313', 1, 1, 100000, '2015-03-09 13:02:36', NULL, '2015-03-09 13:02:36', NULL, '2015-03-09 13:02:36', NULL, '2015-03-09 13:02:36', '大大方方'),
(16, 'werewolf', '沃尔热温热污染', '2016-03-10 01:03:00', '2016-03-10 03:03:00', NULL, NULL, NULL, NULL, NULL, '1212', '温热污染', 1, 1, 100000, '2015-03-10 03:05:50', NULL, '2015-03-10 03:05:50', NULL, '2015-03-10 03:05:50', NULL, '2015-03-10 03:05:50', '阿发范围'),
(17, 'dfdsafa', 'asdfas', '2016-03-09 01:03:00', '2016-03-09 05:03:00', NULL, NULL, NULL, NULL, NULL, '0asfdas', 'asfdasf', 1, 0, 100000, '2015-03-10 05:31:25', NULL, '2015-03-10 05:31:25', NULL, '2015-03-10 05:31:25', NULL, '2015-03-10 05:31:25', 'asfddsaf'),
(18, 'asfsaf', 'asfdsa', '2016-03-25 02:03:00', '2016-03-02 00:03:00', NULL, NULL, NULL, NULL, NULL, '0asfdsaf', 'asfdsaf', 1, 0, 100000, '2015-03-10 05:31:34', NULL, '2015-03-10 05:31:34', NULL, '2015-03-10 05:31:34', NULL, '2015-03-10 05:31:34', 'asfdsaf'),
(19, 'adsfsaf', 'asfdsaf', '2016-03-10 02:03:00', '2016-03-17 06:03:00', NULL, NULL, NULL, NULL, NULL, '0asdfsaf', 'asfdsaf', 1, 0, 100000, '2015-03-10 05:31:44', NULL, '2015-03-10 05:31:44', NULL, '2015-03-10 05:31:44', NULL, '2015-03-10 05:31:44', 'asdfa'),
(20, '测试2', '123213213', '2014-12-10 02:03:00', '2014-12-11 03:03:00', NULL, NULL, NULL, NULL, NULL, '123213', '123213', 1, 0, 100000, '2015-03-10 06:27:57', NULL, '2015-03-10 06:27:57', NULL, '2015-03-10 06:27:57', NULL, '2015-03-10 06:27:57', '213213213'),
(21, '3e3e', '3r32', '2014-12-10 02:03:00', '2014-12-11 02:03:00', NULL, NULL, NULL, NULL, NULL, '023r322', '234234', 1, 0, 100000, '2015-03-10 06:30:36', NULL, '2015-03-10 06:30:36', NULL, '2015-03-10 06:30:36', NULL, '2015-03-10 06:30:36', '234234'),
(22, 'asfddsaf', 'asfdsaf', '2014-12-10 02:03:00', '2014-12-11 03:03:00', NULL, NULL, NULL, NULL, NULL, '0asdfsaf', 'asdfdsaf', 1, 0, 100000, '2015-03-10 06:32:53', NULL, '2015-03-10 06:32:53', NULL, '2015-03-10 06:32:53', NULL, '2015-03-10 06:32:53', 'asdfdsafad'),
(23, 'fadsfa', 'adsfsaf', '2014-12-10 02:03:00', '2014-12-11 03:03:00', NULL, NULL, NULL, NULL, NULL, '0sadfasf', 'asfdadsf', 1, 0, 100000, '2015-03-10 06:40:43', NULL, '2015-03-10 06:40:43', NULL, '2015-03-10 06:40:43', NULL, '2015-03-10 06:40:43', 'asfddsaf'),
(24, 'adfa', 'sadfdsaf', '2015-03-10 02:00:00', '2015-03-11 03:00:00', NULL, NULL, NULL, NULL, NULL, '0asfdasf', 'asfdsaf', 1, 0, 100000, '2015-03-10 06:42:37', NULL, '2015-03-10 06:42:37', NULL, '2015-03-10 06:42:37', NULL, '2015-03-10 06:42:37', 'asfdsa'),
(25, '测试2', 'dfsadf', '2015-03-17 09:00:00', '2015-03-18 10:00:00', NULL, NULL, NULL, NULL, NULL, '0asfda', 'asfdsadf', 1, 0, 100000, '2015-03-10 06:43:56', NULL, '2015-03-10 06:43:56', NULL, '2015-03-10 06:43:56', NULL, '2015-03-10 06:43:56', 'asdf'),
(26, '姜寅', '15380897663', '2015-03-16 08:00:00', '2015-03-17 09:00:00', NULL, NULL, NULL, NULL, NULL, '1234', '镇江业务员', 100009, 1, 100015, '2015-03-11 10:56:51', NULL, '2015-03-11 10:56:51', NULL, '2015-03-11 10:56:51', NULL, '2015-03-11 10:56:51', '大发放大法师发生阿萨德发的 阿萨德发阿萨德发时代发生fads啊多少分'),
(27, 'asdfsa', 'asdfsafs', '2015-03-11 03:00:00', '2015-03-12 04:00:00', NULL, NULL, NULL, NULL, NULL, '0asdfasf', 'asdfsaf', 100009, 1, 100015, '2015-03-11 12:26:47', NULL, '2015-03-11 12:26:47', NULL, '2015-03-11 12:26:47', NULL, '2015-03-11 12:26:47', 'sadfaf'),
(28, 'jiangyin', '15380897663', '2015-05-01 04:00:00', '2015-05-10 04:00:00', NULL, NULL, NULL, NULL, NULL, '1234', 'jiangyin', 100009, 1, 100015, '2015-03-11 12:37:43', NULL, '2015-03-11 12:37:43', NULL, '2015-03-11 12:37:43', NULL, '2015-03-11 12:37:43', '德国方法范儿儿儿人人人过分的人工费登革热个人头人儿童二');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
