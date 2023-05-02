DROP TABLE IF EXISTS `tb_house`;
CREATE TABLE `tb_house`
(
    `id`          BIGINT(20)   NOT NULL AUTO_INCREMENT COMMENT '房产ID',
    `title`       VARCHAR(150) NOT NULL COMMENT '标题',
    `image`       VARCHAR(255) NOT NULL COMMENT '房产照片URL',
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
    `deleted`     BOOLEAN      NOT NULL DEFAULT (0) COMMENT '逻辑删除1已删除,0未删除',
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
    `user_id`  BIGINT(20)          NOT NULL COMMENT '用户id',
    `created`  DATE                NOT NULL COMMENT '创建时间',
    `type`     TINYINT(1)          NOT NULL COMMENT '1-售卖，2-收藏',
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
    `created`  date                NOT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`)
);