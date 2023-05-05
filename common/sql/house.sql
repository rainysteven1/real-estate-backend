DROP TABLE IF EXISTS `tb_house`;
CREATE TABLE `tb_house`
(
    `id`          BIGINT(20)   NOT NULL AUTO_INCREMENT COMMENT '房产ID',
    `title`       VARCHAR(150) NOT NULL COMMENT '标题',
    `preview`     VARCHAR(255) NOT NULL COMMENT '房产照片URL',
    `detail`      VARCHAR(255) NOT NULL COMMENT '房产百科URL',
    `community`   VARCHAR(40)  NOT NULL COMMENT '所属小区名',
    `street`      VARCHAR(20)  NOT NULL COMMENT '所属街道名',
    `district`    VARCHAR(20)  NOT NULL COMMENT '所属区名',
    `total_price` VARCHAR(30)  NOT NULL COMMENT '总价',
    `unit_price`  VARCHAR(30)  NOT NULL COMMENT '每平米价格',
    `room_number` VARCHAR(20)  NOT NULL COMMENT '房型',
    `area`        VARCHAR(20)  NOT NULL COMMENT '面积',
    `direction`   VARCHAR(10)  NOT NULL COMMENT '朝向',
    `renovation`  VARCHAR(10)  NOT NULL COMMENT '装修',
    `floor`       VARCHAR(10)  NOT NULL COMMENT '楼层',
    `year`        VARCHAR(4) COMMENT '建造日期',
    `house_type`  VARCHAR(10) COMMENT '房产类型',
    `created`     DATETIME     NOT NULL COMMENT '创建时间',
    `state`       BOOLEAN      NOT NULL DEFAULT (1) COMMENT '是否上架',
    `deleted`     BOOLEAN      NOT NULL DEFAULT (0) COMMENT '逻辑删除1已删除,0未删除',
    PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for house_files
-- ----------------------------
DROP TABLE IF EXISTS `tb_house_files`;
CREATE TABLE `tb_house_files`
(
    `id`       BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    `house_id` BIGINT(20)          NOT NULL COMMENT '房屋id',
    `imageURL` VARCHAR(255)        NOT NULL COMMENT '上传图片存储URL',
    `type`     TINYINT(1)          NOT NULL COMMENT 'true-房屋图片，false-户型图片',
    `created`  DATETIME            NOT NULL COMMENT '创建时间',
    `modified` DATETIME            NOT NULL COMMENT '创建时间',
    `deleted`  BOOLEAN             NOT NULL DEFAULT (0) COMMENT '逻辑删除1已删除,0未删除',
    PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for house_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_house_user`;
CREATE TABLE `tb_house_user`
(
    `id`       BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    `house_id` BIGINT(20)          NOT NULL COMMENT '房屋id',
    `user_id`  VARCHAR(32)         NOT NULL COMMENT '用户id',
    `type`     TINYINT(1)          NOT NULL COMMENT 'true-售卖，false-收藏',
    `created`  DATETIME            NOT NULL COMMENT '创建时间',
    `modified` DATETIME            NOT NULL COMMENT '创建时间',
    `deleted`  BOOLEAN             NOT NULL DEFAULT (0) COMMENT '逻辑删除1已删除,0未删除',
    PRIMARY KEY (`id`),
    UNIQUE KEY `house_id_user_id_type` (`house_id`, `user_id`, `type`)
);

-- ----------------------------
-- Table structure for house_agent
-- ----------------------------
DROP TABLE IF EXISTS `tb_house_agent`;
CREATE TABLE `tb_house_agent`
(
    `id`       BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    `house_id` BIGINT(20)          NOT NULL COMMENT '房屋id',
    `agent_id` BIGINT(20)          NOT NULL COMMENT '经纪机构id',
    `created`  DATETIME            NOT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `tb_comment`;
CREATE TABLE `tb_comment`
(
    `id`             BIGINT(20) unsigned NOT NULL AUTO_INCREMENT,
    `content`        TEXT                NOT NULL COMMENT '评论内容',
    `created`        DATETIME               NOT NULL COMMENT '发布时间',
    `deleted`        BOOLEAN             NOT NULL default (0) COMMENT '逻辑删除1已删除,0未删除',
    `user_id`        VARCHAR(32)         NOT NULL COMMENT '评论用户id',
    `user_avatar`    VARCHAR(255)        NOT NULL COMMENT '评论用户头像',
    `user_name`      VARCHAR(30) COMMENT '评论用户名',
    `house_id`       BIGINT(20)          NOT NULL COMMENT '房屋id',
    `parent_id`      BIGINT(20) COMMENT '父评论id',
    `root_parent_id` BIGINT(20) COMMENT '根评论id',
    PRIMARY KEY (`id`)
);