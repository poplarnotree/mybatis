/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50719
Source Host           : localhost:3306
Source Database       : mybatis

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2017-08-05 17:29:39
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_student
-- ----------------------------
DROP TABLE IF EXISTS `tb_student`;
CREATE TABLE `tb_student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(18) NOT NULL,
  `sex` varchar(18) NOT NULL,
  `age` int(11) NOT NULL,
  `class_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `class_id` (`class_id`),
  CONSTRAINT `tb_student_ibfk_1` FOREIGN KEY (`class_id`) REFERENCES `tb_class` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_student
-- ----------------------------
INSERT INTO `tb_student` VALUES ('1', '杨昌敏', '男', '21', '1');
INSERT INTO `tb_student` VALUES ('2', '张珊', '女', '21', '1');
INSERT INTO `tb_student` VALUES ('3', '李世', '男', '19', '1');
INSERT INTO `tb_student` VALUES ('4', '王武', '男', '22', '1');
