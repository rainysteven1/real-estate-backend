CREATE TABLE `userinfo`
(
    `id`       INT                 NOT NULL AUTO_INCREMENT COMMENT '用户ID',
    `username` VARCHAR(30)         NOT NULL COMMENT '用户名',
    `password` VARCHAR(50)         NOT NULL COMMENT '密码',
    `mail`     VARCHAR(50)         NOT NULL COMMENT '用户邮箱',
    `avatar`   VARCHAR(255)        NOT NULL COMMENT '用户头像',
    `created`  DATETIME            NOT NULL COMMENT '创建时间',
    `modified` DATETIME            NOT NULL COMMENT '修改时间',
    `deleted`  TINYINT(1) UNSIGNED NOT NULL DEFAULT (0) COMMENT '逻辑删除1已删除,0未删除',
    PRIMARY KEY (`id`)
);