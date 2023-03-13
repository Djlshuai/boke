/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 5.5.40 
*********************************************************************
*/
/*!40101 SET NAMES utf8 */;

create table `m_user` (
	`id` bigint (20),
	`username` varchar (192),
	`avatar` varchar (765),
	`email` varchar (192),
	`password` varchar (192),
	`status` int (5),
	`created` datetime ,
	`last_login` datetime 
); 
insert into `m_user` (`id`, `username`, `avatar`, `email`, `password`, `status`, `created`, `last_login`) values('1','markerhub','https://image-1300566513.cos.ap-guangzhou.myqcloud.com/upload/images/5a9f48118166308daba8b6da7e466aab.jpg',NULL,'111111','0','2020-04-20 10:44:01',NULL);
