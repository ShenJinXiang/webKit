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

