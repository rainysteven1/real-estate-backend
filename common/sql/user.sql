DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`
(
    `id`        VARCHAR(32)  NOT NULL COMMENT '用户ID',
    `name`      VARCHAR(30)  NOT NULL COMMENT '用户名',
    `password`  VARCHAR(50)  NOT NULL COMMENT 'md5加密后的密码',
    `about`     VARCHAR(255)          default '' COMMENT '个人介绍',
    `email`     VARCHAR(50)  NOT NULL COMMENT '用户邮箱',
    `avatar`    VARCHAR(255) NOT NULL COMMENT '用户头像',
    `created`   DATE         NOT NULL COMMENT '创建时间',
    `type`      tinyint(1)   NOT NULL DEFAULT '0' COMMENT '1:普通用户，2:房产经纪人',
    `agency_id` int(11)      NOT NULL DEFAULT '0' COMMENT '所属经纪机构',
    `enabled`   BOOLEAN      NOT NULL DEFAULT (0) COMMENT '1已激活,0未激活',
    `deleted`   BOOLEAN      NOT NULL DEFAULT (0) COMMENT '逻辑删除1已删除,0未删除',
    PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for agency
-- ----------------------------
DROP TABLE IF EXISTS `tb_agency`;
CREATE TABLE `tb_agency`
(
    `id`      INTEGER(20) unsigned NOT NULL AUTO_INCREMENT,
    `name`    VARCHAR(255)         NOT NULL DEFAULT '' COMMENT '经纪机构名称',
    `address` VARCHAR(255)         NOT NULL DEFAULT '' COMMENT '地址',
    `phone`   VARCHAR(255)         NOT NULL DEFAULT '' COMMENT '手机',
    `email`   VARCHAR(255)         NOT NULL DEFAULT '' COMMENT '电子邮件',
    `about`   VARCHAR(255)         NOT NULL DEFAULT '' COMMENT '描述',
    `mobile`  VARCHAR(255)         NOT NULL DEFAULT '' COMMENT '电话',
    `web`     VARCHAR(255)         NOT NULL DEFAULT '' COMMENT '网站',
    PRIMARY KEY (`id`)
);

