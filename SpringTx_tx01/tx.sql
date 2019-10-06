/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80013
 Source Host           : localhost:3306
 Source Schema         : tx

 Target Server Type    : MySQL
 Target Server Version : 80013
 File Encoding         : 65001

 Date: 06/10/2019 10:37:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account`  (
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `balance` int(255) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES ('Jerry', 800);
INSERT INTO `account` VALUES ('Tom', 100000);

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book`  (
  `isbn` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `book_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `price` int(10) NULL DEFAULT NULL,
  PRIMARY KEY (`isbn`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES ('ISBN-001', 'book01', 100);
INSERT INTO `book` VALUES ('ISBN-002', 'book02', 200);
INSERT INTO `book` VALUES ('ISBN-003', 'book03', 300);
INSERT INTO `book` VALUES ('ISBN-004', 'book04', 400);
INSERT INTO `book` VALUES ('ISBN-005', 'book05', 500);

-- ----------------------------
-- Table structure for book_stock
-- ----------------------------
DROP TABLE IF EXISTS `book_stock`;
CREATE TABLE `book_stock`  (
  `isbn` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `stock` int(255) NULL DEFAULT NULL,
  PRIMARY KEY (`isbn`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book_stock
-- ----------------------------
INSERT INTO `book_stock` VALUES ('ISBN-001', 1000);
INSERT INTO `book_stock` VALUES ('ISBN-002', 2000);
INSERT INTO `book_stock` VALUES ('ISBN-003', 3000);
INSERT INTO `book_stock` VALUES ('ISBN-004', 4000);

SET FOREIGN_KEY_CHECKS = 1;
