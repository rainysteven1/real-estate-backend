CREATE TABLE `second_hand_house`
(
    `id`          int          NOT NULL AUTO_INCREMENT COMMENT '房产ID',
    `title`       varchar(150) NOT NULL COMMENT '标题',
    `image`       varchar(255) NOT NULL COMMENT '房产照片URL',
    `detail`      varchar(255) NOT NULL COMMENT '房产百科URL',
    `community`   varchar(40)  NOT NULL COMMENT '所属小区名',
    `street`      varchar(20)  NOT NULL COMMENT '所属街道名',
    `disctrict`   varchar(20)  NOT NULL COMMENT '所属区名',
    `total_price` varchar(30)  NOT NULL COMMENT '总价',
    `unit_price`  varchar(30)  NOT NULL COMMENT '每平米价格',
    `room_number` varchar(20)  NOT NULL COMMENT '房型',
    `area`        varchar(20)  NOT NULL COMMENT '面积',
    `direction`   varchar(10)  NOT NULL COMMENT '朝向',
    `renovation`  varchar(10)  NOT NULL COMMENT '装修',
    `floor`       varchar(10)  NOT NULL COMMENT '楼层',
    `year`        varchar(4)   NOT NULL COMMENT '建造日期',
    `house_type`  varchar(10)  NOT NULL COMMENT '房产类型',
    PRIMARY KEY (`id`)
);