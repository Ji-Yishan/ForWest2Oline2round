/*
SQLyog Community v13.2.0 (64 bit)
MySQL - 8.0.31 : Database - forjava3rd
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`forjava3rd` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `forjava3rd`;

/*Table structure for table `class` */

DROP TABLE IF EXISTS `class`;

CREATE TABLE `class` (
  `order` int NOT NULL AUTO_INCREMENT COMMENT '序号',
  `classOrder` varchar(10) DEFAULT NULL COMMENT '班级名称',
  `studentName` varchar(10) DEFAULT NULL COMMENT '学生姓名',
  `enterTime` datetime DEFAULT NULL COMMENT '进入班级时间',
  PRIMARY KEY (`order`),
  KEY `sN` (`studentName`),
  CONSTRAINT `sN` FOREIGN KEY (`studentName`) REFERENCES `student` (`studentName`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `class` */

insert  into `class`(`order`,`classOrder`,`studentName`,`enterTime`) values 
(1,'22级1班','什么是早八','2022-09-01 00:00:00'),
(2,'22级2班','不想早起','2022-09-01 00:00:00'),
(3,'21级1班','中午吃什么','2021-09-01 00:00:00'),
(9,'20级1班','寒假为什么要早起','2023-01-09 00:00:00');

/*Table structure for table `student` */

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `studentID` int DEFAULT NULL COMMENT '学号',
  `studentName` varchar(10) NOT NULL COMMENT '学生姓名',
  `sex` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '性别',
  `idCard` varchar(28) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '身份证号',
  PRIMARY KEY (`studentName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `student` */

insert  into `student`(`studentID`,`studentName`,`sex`,`idCard`) values 
(743303180,'不想早起','女','359561047461057204'),
(743203165,'中午吃什么','男','359482347562018573'),
(743303110,'什么是早八','女','359482347562018573'),
(743303190,'寒假为什么要早起','女','35029457294837501');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
