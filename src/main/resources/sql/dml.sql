ALTER TABLE `collector_adlink`
    ADD COLUMN `type`  varchar(16) NOT NULL DEFAULT '0' COMMENT '采集器类型（默认0类型）' AFTER `MachinePara`;