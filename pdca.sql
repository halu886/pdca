/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Type    : MySQL
Source Server Version : 50721
Source Host           : localhost:3306
Source Schema         : pdca

Target Server Type    : MySQL
Target Server Version : 50721
File Encoding         : 65001

Date: 01/02/2018 09:41:59
*/ 
SET NAMES utf8mb4;

SET FOREIGN_KEY_CHECKS = 0;-- ----------------------------
-- Table structure for keyword
-- ----------------------------
DROP TABLE
IF
	EXISTS `keyword`;
CREATE TABLE `keyword` (
`KeywordID` INT ( 12 ) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
`keywordValue` VARCHAR ( 25 ) CHARACTER 
SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
PRIMARY KEY ( `KeywordID` ) USING BTREE 
) ENGINE = INNODB CHARACTER 
SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;-- ----------------------------
-- Table structure for mid_keyword_file
-- ----------------------------

-- Table structure for project
-- ----------------------------
DROP TABLE
IF
	EXISTS `project`;
CREATE TABLE `project` (
	`ProjectID` INT ( 12 ) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
	`Name` VARCHAR ( 25 ) CHARACTER 
	SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
	`CreateDate` TIMESTAMP ( 1 ) NULL DEFAULT NULL,
	PRIMARY KEY ( `ProjectID` ) USING BTREE 
) ENGINE = INNODB CHARACTER 
SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;-- ----------------------------
-- Table structure for task
-- ----------------------------
DROP TABLE
IF
	EXISTS `task`;
CREATE TABLE `task` (
	`TaskID` INT ( 12 ) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
	`TaskName` VARCHAR ( 25 ) CHARACTER 
	SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
	`CreateDate` TIMESTAMP ( 1 ) NULL DEFAULT NULL,
	`UpdateDate` TIMESTAMP ( 1 ) NULL DEFAULT NULL,
	`TaskType` VARCHAR ( 5 ) CHARACTER 
	SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
	`Description` VARCHAR ( 250 ) CHARACTER 
	SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
	`PTaskID` INT ( 12 ) UNSIGNED ZEROFILL NOT NULL ,
	`ProjectID` INT ( 12 ) UNSIGNED ZEROFILL NOT NULL ,
	PRIMARY KEY ( `TaskID` ) USING BTREE,
	INDEX `FK_task_ptask` ( `PTaskID` ) USING BTREE,
	INDEX `FK_project_task` ( `ProjectID` ) USING BTREE,
	CONSTRAINT `FK_project_task` FOREIGN KEY ( `ProjectID` ) REFERENCES `project` ( `ProjectID` ) ON DELETE RESTRICT ON UPDATE RESTRICT,
	CONSTRAINT `FK_task_ptask` FOREIGN KEY ( `PTaskID` ) REFERENCES `task` ( `TaskID` ) ON DELETE RESTRICT ON UPDATE RESTRICT 
) ENGINE = INNODB CHARACTER 
SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE
IF
	EXISTS `user`;
CREATE TABLE `user` (
	`UserID` INT ( 12 ) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
	`UserName` VARCHAR ( 25 ) CHARACTER 
	SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
	`password` VARCHAR ( 25 ) CHARACTER 
	SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
	PRIMARY KEY ( `UserID` ) USING BTREE 
) ENGINE = INNODB CHARACTER 
SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

DROP TABLE
IF
	EXISTS `mid_keyword_file`;
CREATE TABLE `mid_keyword_file` (
	`FileID` INT ( 12 ) UNSIGNED ZEROFILL NOT NULL ,
	`KeywordID` INT ( 12 ) UNSIGNED ZEROFILL NOT NULL ,
	PRIMARY KEY ( `FileID`, `KeywordID` ) USING BTREE,
	INDEX `FK_mid_keyword` ( `KeywordID` ) USING BTREE,
	CONSTRAINT `FK_mid_file` FOREIGN KEY ( `FileID` ) REFERENCES `file` ( `FileID` ) ON DELETE RESTRICT ON UPDATE RESTRICT,
	CONSTRAINT `FK_mid_keyword` FOREIGN KEY ( `KeywordID` ) REFERENCES `keyword` ( `KeywordID` ) ON DELETE RESTRICT ON UPDATE RESTRICT 
) ENGINE = INNODB CHARACTER 
SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;-- ----------------------------
-- Table structure for mid_task_keyword
-- ----------------------------
DROP TABLE
IF
	EXISTS `mid_task_keyword`;
CREATE TABLE `mid_task_keyword` (
	`TaskID` INT ( 12 ) UNSIGNED ZEROFILL NOT NULL,
	`KeywordID` INT ( 12 ) UNSIGNED ZEROFILL NOT NULL,
	PRIMARY KEY ( `TaskID`, `KeywordID` ) USING BTREE,
	INDEX `FK_mid_keyword_task` ( `KeywordID` ) USING BTREE,
	CONSTRAINT `FK_mid_keyword_task` FOREIGN KEY ( `KeywordID` ) REFERENCES `keyword` ( `KeywordID` ) ON DELETE RESTRICT ON UPDATE RESTRICT,
	CONSTRAINT `FK_mid_task` FOREIGN KEY ( `TaskID` ) REFERENCES `task` ( `TaskID` ) ON DELETE RESTRICT ON UPDATE RESTRICT 
) ENGINE = INNODB CHARACTER 
SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;-- ----------------------------
-- Table structure for mid_user_project
-- ----------------------------
DROP TABLE
IF
	EXISTS `mid_user_project`;
CREATE TABLE `mid_user_project` (
	`UserID` INT ( 12 ) UNSIGNED ZEROFILL NOT NULL ,
	`ProjectID` INT ( 12 ) UNSIGNED ZEROFILL NOT NULL,
	PRIMARY KEY ( `UserID`, `ProjectID` ) USING BTREE,
	INDEX `FK_mid_project` ( `ProjectID` ) USING BTREE,
	CONSTRAINT `FK_mid_project` FOREIGN KEY ( `ProjectID` ) REFERENCES `project` ( `ProjectID` ) ON DELETE NO ACTION ON UPDATE NO ACTION 
) ENGINE = INNODB CHARACTER 
SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;