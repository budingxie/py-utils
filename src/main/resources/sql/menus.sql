SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- 菜单表
-- ----------------------------
DROP TABLE IF EXISTS `menus`;
CREATE TABLE `menus`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '菜单主键',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '菜单名称',
  `pid` int(11) NOT NULL DEFAULT 0 COMMENT '父级 ID, 最顶级为 0',
  `order` int(11) NOT NULL COMMENT '排序, 序号越大, 越靠前',
  `url` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '前端跳转',
  `desc` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '描述字段',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Compact;

-- ----------------------------
-- 菜单表，测试数据
-- ----------------------------
INSERT INTO `cms`.`menus`(`id`, `name`, `pid`, `order`, `url`, `desc`) VALUES (1, 'CMS', -1, 1, '', '');
INSERT INTO `cms`.`menus`(`id`, `name`, `pid`, `order`, `url`, `desc`) VALUES (2, '内容管理', 1, 1, '', '');
INSERT INTO `cms`.`menus`(`id`, `name`, `pid`, `order`, `url`, `desc`) VALUES (3, '专题管理', 1, 2, '', '');
INSERT INTO `cms`.`menus`(`id`, `name`, `pid`, `order`, `url`, `desc`) VALUES (4, '菜单管理', 1, 3, '', '');
INSERT INTO `cms`.`menus`(`id`, `name`, `pid`, `order`, `url`, `desc`) VALUES (5, '菜谱管理', 1, 4, '', '');
INSERT INTO `cms`.`menus`(`id`, `name`, `pid`, `order`, `url`, `desc`) VALUES (6, '广告管理', 1, 5, '', '');
INSERT INTO `cms`.`menus`(`id`, `name`, `pid`, `order`, `url`, `desc`) VALUES (7, 'B2C商城设置', 2, 1, '', '');
INSERT INTO `cms`.`menus`(`id`, `name`, `pid`, `order`, `url`, `desc`) VALUES (8, '标签和说明设置', 7, 1, '', '');
INSERT INTO `cms`.`menus`(`id`, `name`, `pid`, `order`, `url`, `desc`) VALUES (9, '门店显示设置', 7, 2, '', '');
INSERT INTO `cms`.`menus`(`id`, `name`, `pid`, `order`, `url`, `desc`) VALUES (10, '热搜词统计', 2, 2, '', '');


SET FOREIGN_KEY_CHECKS = 1;
