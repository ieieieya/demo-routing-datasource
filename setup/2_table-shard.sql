CREATE TABLE `routing-datasource-1`.`chat_message_1` (
  `msgId` varchar(36) NOT NULL,
  `tenantId` int(11) NOT NULL,
  `body` varchar(100) NOT NULL,
  `updatedAt` datetime(6) DEFAULT NULL,
  `createdAt` datetime(6) DEFAULT NULL,
  `time` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`msgId`),
  KEY `tenantId_index` (`tenantId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
CREATE TABLE `routing-datasource-1`.`chat_message_2` (
  `msgId` varchar(36) NOT NULL,
  `tenantId` int(11) NOT NULL,
  `body` varchar(100) NOT NULL,
  `updatedAt` datetime(6) DEFAULT NULL,
  `createdAt` datetime(6) DEFAULT NULL,
  `time` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`msgId`),
  KEY `tenantId_index` (`tenantId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
CREATE TABLE `routing-datasource-1`.`chat_message_3` (
  `msgId` varchar(36) NOT NULL,
  `tenantId` int(11) NOT NULL,
  `body` varchar(100) NOT NULL,
  `updatedAt` datetime(6) DEFAULT NULL,
  `createdAt` datetime(6) DEFAULT NULL,
  `time` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`msgId`),
  KEY `tenantId_index` (`tenantId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `routing-datasource-2`.`chat_message_4` (
  `msgId` varchar(36) NOT NULL,
  `tenantId` int(11) NOT NULL,
  `body` varchar(100) NOT NULL,
  `updatedAt` datetime(6) DEFAULT NULL,
  `createdAt` datetime(6) DEFAULT NULL,
  `time` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`msgId`),
  KEY `tenantId_index` (`tenantId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
CREATE TABLE `routing-datasource-2`.`chat_message_5` (
  `msgId` varchar(36) NOT NULL,
  `tenantId` int(11) NOT NULL,
  `body` varchar(100) NOT NULL,
  `updatedAt` datetime(6) DEFAULT NULL,
  `createdAt` datetime(6) DEFAULT NULL,
  `time` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`msgId`),
  KEY `tenantId_index` (`tenantId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
CREATE TABLE `routing-datasource-2`.`chat_message_6` (
  `msgId` varchar(36) NOT NULL,
  `tenantId` int(11) NOT NULL,
  `body` varchar(100) NOT NULL,
  `updatedAt` datetime(6) DEFAULT NULL,
  `createdAt` datetime(6) DEFAULT NULL,
  `time` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`msgId`),
  KEY `tenantId_index` (`tenantId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `routing-datasource-3`.`chat_message_7` (
  `msgId` varchar(36) NOT NULL,
  `tenantId` int(11) NOT NULL,
  `body` varchar(100) NOT NULL,
  `updatedAt` datetime(6) DEFAULT NULL,
  `createdAt` datetime(6) DEFAULT NULL,
  `time` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`msgId`),
  KEY `tenantId_index` (`tenantId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
CREATE TABLE `routing-datasource-3`.`chat_message_8` (
  `msgId` varchar(36) NOT NULL,
  `tenantId` int(11) NOT NULL,
  `body` varchar(100) NOT NULL,
  `updatedAt` datetime(6) DEFAULT NULL,
  `createdAt` datetime(6) DEFAULT NULL,
  `time` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`msgId`),
  KEY `tenantId_index` (`tenantId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
CREATE TABLE `routing-datasource-3`.`chat_message_9` (
  `msgId` varchar(36) NOT NULL,
  `tenantId` int(11) NOT NULL,
  `body` varchar(100) NOT NULL,
  `updatedAt` datetime(6) DEFAULT NULL,
  `createdAt` datetime(6) DEFAULT NULL,
  `time` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`msgId`),
  KEY `tenantId_index` (`tenantId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



insert into `routing-datasource-3`.`chat_message_7`(msgId, tenantId, body, updatedAt, createdAt) VALUE ('07a32d5a-2ef6-425d-b289-b2af3dc3a794', 1410, '1410 body', now(), now());
insert into `routing-datasource-3`.`chat_message_8`(msgId, tenantId, body, updatedAt, createdAt) VALUE ('07a32d5a-2ef6-425d-b289-b2af3dc3a795', 1411, '1411 body', now(), now());
insert into `routing-datasource-3`.`chat_message_9`(msgId, tenantId, body, updatedAt, createdAt) VALUE ('07a32d5a-2ef6-425d-b289-b2af3dc3a796', 1412, '1412 body', now(), now());