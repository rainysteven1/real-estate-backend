CREATE TABLE `tb_userinfo`
(
    `id`       INTEGER            NOT NULL AUTO_INCREMENT COMMENT '用户ID',
    `username` VARCHAR(30) UNIQUE NOT NULL COMMENT '用户名',
    `password` VARCHAR(50)        NOT NULL COMMENT '密码',
    `mail`     VARCHAR(50) UNIQUE NOT NULL COMMENT '用户邮箱',
    `avatar`   VARCHAR(255)       NOT NULL COMMENT '用户头像',
    `created`  DATE               NOT NULL COMMENT '创建时间',
    `modified` DATE               NOT NULL COMMENT '修改时间',
    `status`   BOOLEAN            NOT NULL DEFAULT (0) COMMENT '是否激活1已激活,0未激活',
    `code`     VARCHAR(100)       NOT NULL COMMENT '校验码 用户传来的code跟我们发送的code一致，则更改状态为“1”来激活用户',
    `deleted`  BOOLEAN            NOT NULL DEFAULT (0) COMMENT '逻辑删除1已删除,0未删除',
    PRIMARY KEY (`id`)
);