-- ----------------------------------------------------
-- Table xtwh_zyxx
-- ----------------------------------------------------
CREATE TABLE `xtwh_zyxx` (
  `zyid` varchar(36) NOT NULL COMMENT '资源id',
  `zymc` varchar(200) DEFAULT NULL COMMENT '资源名称',
  `sjzyid` varchar(36) DEFAULT NULL COMMENT '上级资源id',
  `zylj` varchar(500) DEFAULT NULL COMMENT '资源链接',
  `zytb` varchar(20) DEFAULT NULL COMMENT '图标',
  `zyms` varchar(200) DEFAULT NULL COMMENT '资源描述',
  `zylx` int(11) DEFAULT NULL COMMENT '资源类型0-菜单  1-功能',
  `yxbz` tinyint(1) DEFAULT NULL COMMENT '有效标志 0-无效 1-有效',
  `zypx` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`zyid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

insert into xtwh_zyxx
(`zyid`, `zymc`, `sjzyid`, `zylj`, `zytb`, `zyms`, `zylx`, `yxbz`, `zypx`)
VALUES
('001', '系统维护', null, '#', null, '系统维护', 0, 1, 1),
('001001', '机构管理', '001', '/system/jggl', null, '机构管理', 0, 1, 1),
('001002', '人员管理', '001', '/system/rygl', null, '人员管理', 0, 1, 2),
('001003', '角色管理', '001', '/system/jsgl', null, '角色管理', 0, 1, 3),
('001004', '资源管理', '001', '/system/zygl', null, '资源管理', 0, 1, 4)