DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `cid` tinyint(3) unsigned NOT NULL AUTO_INCREMENT COMMENT '分类标识符，自增主键。',
  `left` smallint(5) unsigned NOT NULL COMMENT '分类左值。',
  `right` smallint(5) unsigned NOT NULL COMMENT '分类右值。',
  `name` char(32) NOT NULL COMMENT '分类名称。',
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='SpringBoot 预排序遍历树算法演示。';
