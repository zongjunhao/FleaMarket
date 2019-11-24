/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50727
Source Host           : localhost:3306
Source Database       : fleamarket

Target Server Type    : MYSQL
Target Server Version : 50727
File Encoding         : 65001

Date: 2019-11-24 17:02:37
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `a_id` int(11) NOT NULL AUTO_INCREMENT,
  `a_account` varchar(15) NOT NULL,
  `a_pwd` varchar(20) NOT NULL,
  `a_addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `a_updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`a_id`),
  UNIQUE KEY `a_account` (`a_account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `c_id` int(11) NOT NULL AUTO_INCREMENT,
  `c_label` varchar(30) DEFAULT NULL,
  `c_addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `c_updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`c_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of category
-- ----------------------------

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `com_id` int(11) NOT NULL AUTO_INCREMENT,
  `com_g_id` int(11) DEFAULT NULL,
  `com_u_id` int(11) DEFAULT NULL,
  `com_desc` text,
  `com_reply` int(11) DEFAULT '0',
  `com_addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `com_updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`com_id`),
  KEY `fk_comment_goods` (`com_g_id`),
  KEY `fk_comment_user` (`com_u_id`),
  CONSTRAINT `fk_comment_goods` FOREIGN KEY (`com_g_id`) REFERENCES `goods` (`g_id`) ON DELETE CASCADE,
  CONSTRAINT `fk_comment_user` FOREIGN KEY (`com_u_id`) REFERENCES `user` (`u_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment
-- ----------------------------

-- ----------------------------
-- Table structure for follow
-- ----------------------------
DROP TABLE IF EXISTS `follow`;
CREATE TABLE `follow` (
  `f_id` int(11) NOT NULL AUTO_INCREMENT,
  `f_g_id` int(11) DEFAULT NULL,
  `f_u_id` int(11) DEFAULT NULL,
  `f_addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `f_updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`f_id`),
  KEY `fk_follow_goods` (`f_g_id`),
  KEY `fk_follow_user` (`f_u_id`),
  CONSTRAINT `fk_follow_goods` FOREIGN KEY (`f_g_id`) REFERENCES `goods` (`g_id`) ON DELETE CASCADE,
  CONSTRAINT `fk_follow_user` FOREIGN KEY (`f_u_id`) REFERENCES `user` (`u_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of follow
-- ----------------------------

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `g_id` int(11) NOT NULL AUTO_INCREMENT,
  `g_c_id` int(11) DEFAULT NULL,
  `g_u_id` int(11) DEFAULT NULL,
  `g_name` varchar(50) NOT NULL,
  `g_price` int(5) NOT NULL,
  `g_realprice` int(5) NOT NULL,
  `g_describe` text,
  `g_state` tinyint(4) DEFAULT '1',
  `g_addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `g_updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`g_id`),
  KEY `fk_goods_category` (`g_c_id`),
  KEY `fk_goods_user` (`g_u_id`),
  CONSTRAINT `fk_goods_category` FOREIGN KEY (`g_c_id`) REFERENCES `category` (`c_id`) ON DELETE CASCADE,
  CONSTRAINT `fk_goods_user` FOREIGN KEY (`g_u_id`) REFERENCES `user` (`u_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goods
-- ----------------------------

-- ----------------------------
-- Table structure for image
-- ----------------------------
DROP TABLE IF EXISTS `image`;
CREATE TABLE `image` (
  `i_id` int(11) NOT NULL AUTO_INCREMENT,
  `i_g_id` int(11) DEFAULT NULL,
  `i_path` varchar(255) DEFAULT NULL,
  `i_addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `i_updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`i_id`),
  KEY `fk_image_goods` (`i_g_id`),
  CONSTRAINT `fk_image_goods` FOREIGN KEY (`i_g_id`) REFERENCES `goods` (`g_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of image
-- ----------------------------

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice` (
  `n_id` int(11) NOT NULL AUTO_INCREMENT,
  `n_u_id` int(11) DEFAULT NULL,
  `n_name` varchar(50) NOT NULL,
  `n_price` int(5) NOT NULL,
  `n_describe` text,
  `n_state` tinyint(4) DEFAULT '1',
  `n_addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `n_updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`n_id`),
  KEY `fk_notice_user` (`n_u_id`),
  CONSTRAINT `fk_notice_user` FOREIGN KEY (`n_u_id`) REFERENCES `user` (`u_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of notice
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `u_id` int(11) NOT NULL AUTO_INCREMENT,
  `u_stuid` varchar(12) NOT NULL,
  `u_pwd` varchar(20) NOT NULL DEFAULT '123456',
  `u_name` varchar(20) NOT NULL,
  `u_gender` char(2) DEFAULT NULL,
  `u_phone` char(11) DEFAULT NULL,
  `u_qq` varchar(15) NOT NULL,
  `u_addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `u_updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`u_id`),
  UNIQUE KEY `u_stuid` (`u_stuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
