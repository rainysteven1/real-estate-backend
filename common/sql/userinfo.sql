CREATE TABLE `userinfo`
(
    `id`       int                    NOT NULL AUTO_INCREMENT COMMENT '用户ID',
    `username` varchar(30)            NOT NULL COMMENT '用户名',
    `password` varchar(50)            NOT NULL COMMENT '密码',
    `mail`     varchar(50)            NOT NULL COMMENT '用户邮箱',
    `avatar`   varchar(255)           NOT NULL COMMENT '用户头像',
    `created`  DATE                   NOT NULL COMMENT '创建时间',
    `modified` DATE                   NOT NULL COMMENT '修改时间',
    `deleted`  tinyint(1) default (0) NOT NULL COMMENT '是否删除',
    PRIMARY KEY (`id`)
);