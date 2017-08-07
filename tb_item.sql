/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50719
Source Host           : localhost:3306
Source Database       : mybatis

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2017-08-07 21:39:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_item
-- ----------------------------
DROP TABLE IF EXISTS `tb_item`;
CREATE TABLE `tb_item` (
  `order_id` int(11) NOT NULL,
  `article_id` int(11) NOT NULL,
  `amount` int(11) NOT NULL COMMENT '数量',
  PRIMARY KEY (`order_id`,`article_id`),
  KEY `article_id` (`article_id`),
  CONSTRAINT `tb_item_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `tb_order` (`id`),
  CONSTRAINT `tb_item_ibfk_2` FOREIGN KEY (`article_id`) REFERENCES `tb_article` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
