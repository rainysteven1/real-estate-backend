-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `tb_comment`;
CREATE TABLE `tb_comment`
(
    `id`        INTEGER(20) unsigned NOT NULL AUTO_INCREMENT,
    `content`   VARCHAR(512)         NOT NULL DEFAULT '' COMMENT '评论内容',
    `created`   DATE                 NOT NULL COMMENT '发布时间',
    `user_id`   bigint(20)           NOT NULL COMMENT '评论用户',
    `house_id`  INTEGER(20)          NOT NULL COMMENT '房屋id',
    `parent_id` INTEGER(20) COMMENT '父评论id',
    PRIMARY KEY (`id`)
);