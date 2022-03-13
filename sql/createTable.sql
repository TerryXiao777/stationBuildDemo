CREATE TABLE `t_net_inforamtion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `net_individual` varchar(255) DEFAULT NULL,
  `net_type` varchar(100) DEFAULT NULL,
  `net_address` varchar(255) DEFAULT NULL,
  `account` varchar(50) DEFAULT NULL,
  `sign` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `t_news` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type_id` int(11) DEFAULT NULL,
  `type_name` varchar(255) DEFAULT NULL,
  `con_title` varchar(255) DEFAULT NULL,
  `con_content` varchar(255) DEFAULT NULL,
  `con_time` varchar(20) DEFAULT NULL,
  `sign` int(1) DEFAULT NULL,
  `net_id` int(11) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `t_temple` (
  `tem_name` varchar(50) NOT NULL,
  `tem_index` varchar(255) DEFAULT NULL,
  `tem_top` varchar(255) DEFAULT NULL,
  `tem_left` varchar(255) DEFAULT NULL,
  `tem_type` varchar(255) DEFAULT NULL,
  `tem_content` varchar(255) DEFAULT NULL,
  `tem_images` varchar(255) DEFAULT NULL,
  `tem` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`tem_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `t_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(255) DEFAULT NULL,
  `net_id` int(11) DEFAULT NULL,
  `sign` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `t_user_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(50) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `realname` varchar(50) DEFAULT NULL,
  `sex` varchar(10) DEFAULT NULL,
  `age` int(3) DEFAULT NULL,
  `profession` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;