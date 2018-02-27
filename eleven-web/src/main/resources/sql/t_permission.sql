CREATE TABLE `t_permission` (
  `id` int(11) NOT NULL,
  `permission_name` varchar(100) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  `gmt_create` datetime DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8