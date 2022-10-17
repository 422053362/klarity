create database IF NOT EXISTS `klarity` default character set utf8 collate utf8_general_ci;

USE klarity;

DROP TABLE IF EXISTS `tbl_klarity_hospital`;
CREATE TABLE `tbl_klarity_hospital`
(
    `id`            bigint(11) NOT NULL AUTO_INCREMENT COMMENT '行记录Id',
    `hospital_id`   varchar(64)   NOT NULL COMMENT '医院id',
    `hospital_name` varchar(128)   NOT NULL COMMENT '医院名称',
    `ext_info`      varchar(1024) NOT NULL COMMENT '扩展信息',
    `deleted`       bigint(64) NOT NULL COMMENT '删除标记',
    `tenant_id`     varchar(64)   NOT NULL COMMENT '租户Id',
    `creator`       varchar(64)   NOT NULL COMMENT '创建者',
    `modifier`      varchar(64)   NOT NULL COMMENT '修改者',
    `create_time`   datetime(3) NOT NULL COMMENT '创建时间',
    `modify_time`   datetime(3) NOT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY (`hospital_id`,`deleted`)
) COMMENT '医院数据信息表';

DROP TABLE IF EXISTS `tbl_klarity_employee`;
CREATE TABLE `tbl_klarity_employee`
(
    `id`                 bigint(11) NOT NULL AUTO_INCREMENT COMMENT '行记录Id',
    `employee_id`        varchar(64)    NOT NULL COMMENT '员工Id',
    `employee_name`      varchar(64)    NOT NULL COMMENT '员工名称',
    `email`              varchar(128)   NOT NULL COMMENT '员工邮箱',
    `deleted`            bigint(64)     NOT NULL COMMENT '删除标记',
    `tenant_id`          varchar(64)    NOT NULL COMMENT '租户Id',
    `creator`            varchar(64)    NOT NULL COMMENT '创建者',
    `modifier`           varchar(64)    NOT NULL COMMENT '修改者',
    `create_time`        datetime(3) NOT NULL COMMENT '创建时间',
    `modify_time`        datetime(3) NOT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`),
    unique KEY (`employee_id`,`deleted`),
    unique KEY (`email`,`tenant_id`,`deleted`)
) COMMENT '员工信息表';

DROP TABLE IF EXISTS `tbl_klarity_m2m_hospital_employee`;
CREATE TABLE `tbl_klarity_m2m_hospital_employee`
(
    `id`            bigint(64)     NOT NULL AUTO_INCREMENT COMMENT '记录Id',
    `hospital_id`   varchar(64)    NOT NULL COMMENT '医院Id',
    `employee_id`   varchar(64)    NOT NULL COMMENT '员工Id',
    `status`        varchar(64)    NOT NULL COMMENT '雇佣关系状态',
    `deleted`       bigint(64)     NOT NULL COMMENT '删除标记',
    `tenant_id`     varchar(64)    NOT NULL COMMENT '租户Id',
    `creator`       varchar(64)    NOT NULL COMMENT '创建者',
    `modifier`      varchar(64)    NOT NULL COMMENT '修改者',
    `create_time`   datetime(3)    NOT NULL COMMENT '创建时间',
    `modify_time`   datetime(3)    NOT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`)
) COMMENT '医院与员工之间的关系表';

DROP TABLE IF EXISTS `tbl_klarity_task`;
CREATE TABLE `tbl_klarity_task`
(
    `id`                 bigint(11)     NOT NULL AUTO_INCREMENT COMMENT '行记录Id',
    `task_id`            varchar(64)    NOT NULL COMMENT '任务Id',
    `hospital_id`        varchar(64)    NOT NULL COMMENT '医院Id',
    `owner_id`           varchar(64)    NOT NULL COMMENT '员工Id',
    `title`              varchar(64)    NOT NULL COMMENT '任务标题',
    `description`        varchar(512)    NOT NULL COMMENT '任务描述',
    `priority`           varchar(64)    NOT NULL COMMENT '任务优先级 URGENT, HIGHT, and LOW',
    `status`             varchar(64)    NOT NULL COMMENT '任务状态 OPEN, FAILED, or COMPLETED.',
    `deleted`            bigint(64)     NOT NULL COMMENT '删除标记',
    `tenant_id`          varchar(64)    NOT NULL COMMENT '租户Id',
    `creator`            varchar(64)    NOT NULL COMMENT '创建者',
    `modifier`           varchar(64)    NOT NULL COMMENT '修改者',
    `create_time`        datetime(3) NOT NULL COMMENT '创建时间',
    `modify_time`        datetime(3) NOT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`),
    unique KEY (`task_id`,`deleted`)
) COMMENT '任务记录表';

DROP TABLE IF EXISTS `tbl_klarity_task_assignment`;
CREATE TABLE `tbl_klarity_task_assignment`
(
    `id`                 bigint(11) NOT NULL AUTO_INCREMENT COMMENT '行记录Id',
    `task_id`            varchar(64)    NOT NULL COMMENT '任务Id',
    `employee_id`        varchar(64)    NOT NULL COMMENT '员工Id',
    `state`              varchar(32)    NOT NULL COMMENT '分配记录状态',
    `deleted`            bigint(64) NOT NULL COMMENT '删除标记',
    `tenant_id`          varchar(64)    NOT NULL COMMENT '租户Id',
    `creator`            varchar(64)    NOT NULL COMMENT '创建者',
    `modifier`           varchar(64)    NOT NULL COMMENT '修改者',
    `create_time`        datetime(3) NOT NULL COMMENT '创建时间',
    `modify_time`        datetime(3) NOT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`)
) COMMENT '任务操作记录表';


DROP TABLE IF EXISTS `tbl_klarity_task_action`;
CREATE TABLE `tbl_klarity_task_action`
(
    `id`                 bigint(11) NOT NULL AUTO_INCREMENT COMMENT '行记录Id',
    `task_id`            varchar(64)    NOT NULL COMMENT '任务Id，一个任务多个执行记录',
    `action_name`        varchar(64)    NOT NULL COMMENT '操作名称',
    `action_info`        varchar(256)    NOT NULL COMMENT '操作名称',
    `deleted`            bigint(64) NOT NULL COMMENT '删除标记',
    `tenant_id`          varchar(64)    NOT NULL COMMENT '租户Id',
    `creator`            varchar(64)    NOT NULL COMMENT '创建者',
    `modifier`           varchar(64)    NOT NULL COMMENT '修改者',
    `create_time`        datetime(3) NOT NULL COMMENT '创建时间',
    `modify_time`        datetime(3) NOT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`)
) COMMENT '任务操作记录表';



DROP TABLE IF EXISTS `tbl_flow_task`;
CREATE TABLE `tbl_flow_task`
(
    `id`           bigint(64) NOT NULL AUTO_INCREMENT COMMENT '记录Id',
    `flow_task_id` varchar(64) NOT NULL COMMENT '业务流程实例id',
    `flow_id`      varchar(64) NOT NULL COMMENT '业务流程id',
    `flow_name`    varchar(64) NOT NULL COMMENT '业务流程名称',
    `flow_type`    varchar(64) NOT NULL COMMENT '业务流程类型',
    `start_time`   datetime(3) NOT NULL COMMENT '业务流程开始时间',
    `end_time`     datetime(3) NULL COMMENT '业务流程结束时间',
    `status`       varchar(64) NOT NULL COMMENT '实例状态',
    `ext_value`    varchar(1024) NULL COMMENT '扩展字段',
    `deleted`      bigint(64) NOT NULL COMMENT '删除标记',
    `tenant_id`    varchar(64) NOT NULL COMMENT '租户Id',
    `creator`      varchar(64) NOT NULL COMMENT '创建者',
    `modifier`     varchar(64) NOT NULL COMMENT '修改者',
    `create_time`  datetime(3) NOT NULL COMMENT '创建时间',
    `modify_time`  datetime(3) NOT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`),
    unique KEY (`flow_task_id`)
) COMMENT '业务流程实例';

DROP TABLE IF EXISTS `tbl_flow_node_task`;
CREATE TABLE `tbl_flow_node_task`
(
    `id`           bigint(64) NOT NULL AUTO_INCREMENT COMMENT '记录Id',
    `flow_task_id` varchar(64) NOT NULL COMMENT '业务流程实例id',
    `node_name`    varchar(64) NOT NULL COMMENT '业务节点名称',
    `start_time`   datetime(3) NOT NULL COMMENT '业务节点开始时间',
    `end_time`     datetime(3) NULL COMMENT '业务节点结束时间',
    `status`       varchar(64) NOT NULL COMMENT '实例状态',
    `ext_value`    varchar(1024) NULL COMMENT '扩展字段',
    `deleted`      bigint(64) NOT NULL COMMENT '删除标记',
    `tenant_id`    varchar(64) NOT NULL COMMENT '租户Id',
    `creator`      varchar(64) NOT NULL COMMENT '创建者',
    `modifier`     varchar(64) NOT NULL COMMENT '修改者',
    `create_time`  datetime(3) NOT NULL COMMENT '创建时间',
    `modify_time`  datetime(3) NOT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`),
    unique KEY (`flow_task_id`, `node_name`,`deleted`)
) COMMENT '业务节点实例id';

DROP TABLE IF EXISTS `tbl_sequence`;
CREATE TABLE `tbl_sequence`
(
    `id`          bigint(64) NOT NULL AUTO_INCREMENT COMMENT '记录Id',
    `seq_name`    varchar(64) NOT NULL COMMENT '业务节点名称',
    `seq_value`   bigint(64) NOT NULL COMMENT '业务节点开始时间',
    `min_value`   bigint(64) NOT NULL COMMENT '业务节点结束时间',
    `max_value`   bigint(64) NOT NULL COMMENT '实例状态',
    `step`        bigint(64) NOT NULL COMMENT '扩展字段',
    `deleted`     bigint(64) NOT NULL COMMENT '删除标记',
    `tenant_id`   varchar(64) NOT NULL COMMENT '租户Id',
    `creator`     varchar(64) NOT NULL COMMENT '创建者',
    `modifier`    varchar(64) NOT NULL COMMENT '修改者',
    `create_time` datetime(3) NOT NULL COMMENT '创建时间',
    `modify_time` datetime(3) NOT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`),
    unique key (`seq_name`,`deleted`)
) COMMENT '全局唯一Id生成表';

DROP TABLE IF EXISTS `tbl_topic_message`;
CREATE TABLE `tbl_topic_message`
(
    `id`             bigint(64) NOT NULL AUTO_INCREMENT COMMENT '记录Id',
    `transaction_id` varchar(64)   NOT NULL COMMENT '业务节点名称',
    `topic`          varchar(128)  NOT NULL COMMENT '消息Topic',
    `tag`            varchar(128)  NOT NULL COMMENT '消息Tag',
    `payload`        varchar(2048) NOT NULL COMMENT '消息内容',
    `trigger_time`   datetime(3) NOT NULL COMMENT '消息发送时间',
    `retry_times`    int(4) NOT NULL COMMENT '消息重试时间',
    `status`         varchar(64)   NOT NULL COMMENT '消息状态',
    `deleted`        bigint(64) NOT NULL COMMENT '删除标记',
    `tenant_id`      varchar(64)   NOT NULL COMMENT '租户Id',
    `creator`        varchar(64)   NOT NULL COMMENT '创建者',
    `modifier`       varchar(64)   NOT NULL COMMENT '修改者',
    `create_time`    datetime(3) NOT NULL COMMENT '创建时间',
    `modify_time`    datetime(3) NOT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`),
    unique key (`transaction_id`,`deleted`)
) COMMENT '事务消息记录表';

DROP TABLE IF EXISTS `tbl_idempotent`;
CREATE TABLE `tbl_idempotent`
(
    `id`          bigint(64) NOT NULL AUTO_INCREMENT COMMENT '记录Id',
    `txn_id`      varchar(64)  NOT NULL COMMENT '事务Id',
    `txn_st`      varchar(128) NOT NULL COMMENT '事务状态',
    `deleted`     bigint(64) NOT NULL COMMENT '删除标记',
    `tenant_id`   varchar(64)  NOT NULL COMMENT '租户Id',
    `creator`     varchar(64)  NOT NULL COMMENT '创建者',
    `modifier`    varchar(64)  NOT NULL COMMENT '修改者',
    `create_time` datetime(3) NOT NULL COMMENT '创建时间',
    `modify_time` datetime(3) NOT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`),
    unique key (`txn_id`,`deleted`)
) COMMENT '幂等记录表';

INSERT INTO klarity.tbl_sequence (id, seq_name, seq_value, min_value, max_value, step, deleted, tenant_id, creator,
                                  modifier, create_time, modify_time)
VALUES (1, 'klarity-server', 10000, 10000, 9999999999, 1000, 0, 'system', '0', '0', '2022-10-16 22:04:41.000',
        '2022-10-16 22:04:43.000');



