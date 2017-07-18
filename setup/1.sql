create DATABASE `routing-datasource` DEFAULT CHARSET utf8mb4;
use `routing-datasource`;
CREATE TABLE `chat_message` (
  `msgId` VARCHAR(36) NOT NULL,
  `tenantId` int(11) NOT NULL,
  `body` VARCHAR(100) NOT NULL,
  `updatedAt` DATETIME(6) DEFAULT NULL,
  `createdAt` DATETIME(6) DEFAULT NULL,
  PRIMARY KEY (`msgId`),
  KEY `tenantId_index` (`tenantId`)
) ENGINE=InnoDB DEFAULT CHARACTER SET=utf8mb4;
insert into `chat_message`(msgId, tenantId, body, updatedAt, createdAt) VALUES (uuid(), 1410, 'origin-1-1', now(), now());
insert into `chat_message`(msgId, tenantId, body, updatedAt, createdAt) VALUES (uuid(), 1410, 'origin-1-2', now(), now());
insert into `chat_message`(msgId, tenantId, body, updatedAt, createdAt) VALUES (uuid(), 1410, 'origin-1-3', now(), now());
insert into `chat_message`(msgId, tenantId, body, updatedAt, createdAt) VALUES (uuid(), 1410, 'origin-1-4', now(), now());

insert into `chat_message`(msgId, tenantId, body, updatedAt, createdAt) VALUES (uuid(), 1411, 'origin-2-1', now(), now());
insert into `chat_message`(msgId, tenantId, body, updatedAt, createdAt) VALUES (uuid(), 1411, 'origin-2-2', now(), now());
insert into `chat_message`(msgId, tenantId, body, updatedAt, createdAt) VALUES (uuid(), 1411, 'origin-2-3', now(), now());
insert into `chat_message`(msgId, tenantId, body, updatedAt, createdAt) VALUES (uuid(), 1411, 'origin-2-4', now(), now());

insert into `chat_message`(msgId, tenantId, body, updatedAt, createdAt) VALUES (uuid(), 1412, 'origin-3-1', now(), now());
insert into `chat_message`(msgId, tenantId, body, updatedAt, createdAt) VALUES (uuid(), 1412, 'origin-3-2', now(), now());
insert into `chat_message`(msgId, tenantId, body, updatedAt, createdAt) VALUES (uuid(), 1412, 'origin-3-3', now(), now());
insert into `chat_message`(msgId, tenantId, body, updatedAt, createdAt) VALUES (uuid(), 1412, 'origin-3-4', now(), now());


create DATABASE `routing-datasource-1` DEFAULT CHARSET utf8mb4;
use `routing-datasource-1`;
CREATE TABLE `chat_message` (
  `msgId` VARCHAR(36) NOT NULL,
  `tenantId` int(11) NOT NULL,
  `body` VARCHAR(100) NOT NULL,
  `updatedAt` DATETIME(6) DEFAULT NULL,
  `createdAt` DATETIME(6) DEFAULT NULL,
  PRIMARY KEY (`msgId`),
  KEY `tenantId_index` (`tenantId`)
) ENGINE=InnoDB DEFAULT CHARACTER SET=utf8mb4;
insert into `chat_message`(msgId, tenantId, body, updatedAt, createdAt) VALUES (uuid(), 1410, '1-1', now(), now());
insert into `chat_message`(msgId, tenantId, body, updatedAt, createdAt) VALUES (uuid(), 1410, '1-2', now(), now());
insert into `chat_message`(msgId, tenantId, body, updatedAt, createdAt) VALUES (uuid(), 1410, '1-3', now(), now());
insert into `chat_message`(msgId, tenantId, body, updatedAt, createdAt) VALUES (uuid(), 1410, '1-4', now(), now());


create DATABASE `routing-datasource-2` DEFAULT CHARSET utf8mb4;
use `routing-datasource-2`;
CREATE TABLE `chat_message` (
  `msgId` VARCHAR(36) NOT NULL,
  `tenantId` int(11) NOT NULL,
  `body` VARCHAR(100) NOT NULL,
  `updatedAt` DATETIME(6) DEFAULT NULL,
  `createdAt` DATETIME(6) DEFAULT NULL,
  PRIMARY KEY (`msgId`),
  KEY `tenantId_index` (`tenantId`)
) ENGINE=InnoDB DEFAULT CHARACTER SET=utf8mb4;
insert into `chat_message`(msgId, tenantId, body, updatedAt, createdAt) VALUES (uuid(), 1411, '2-1', now(), now());
insert into `chat_message`(msgId, tenantId, body, updatedAt, createdAt) VALUES (uuid(), 1411, '2-2', now(), now());
insert into `chat_message`(msgId, tenantId, body, updatedAt, createdAt) VALUES (uuid(), 1411, '2-3', now(), now());
insert into `chat_message`(msgId, tenantId, body, updatedAt, createdAt) VALUES (uuid(), 1411, '2-4', now(), now());


create DATABASE `routing-datasource-3` DEFAULT CHARSET utf8mb4;
use `routing-datasource-3`;
CREATE TABLE `chat_message` (
  `msgId` VARCHAR(36) NOT NULL,
  `tenantId` int(11) NOT NULL,
  `body` VARCHAR(100) NOT NULL,
  `updatedAt` DATETIME(6) DEFAULT NULL,
  `createdAt` DATETIME(6) DEFAULT NULL,
  PRIMARY KEY (`msgId`),
  KEY `tenantId_index` (`tenantId`)
) ENGINE=InnoDB DEFAULT CHARACTER SET=utf8mb4;
insert into `chat_message`(msgId, tenantId, body, updatedAt, createdAt) VALUES (uuid(), 1412, '3-1', now(), now());
insert into `chat_message`(msgId, tenantId, body, updatedAt, createdAt) VALUES (uuid(), 1412, '3-2', now(), now());
insert into `chat_message`(msgId, tenantId, body, updatedAt, createdAt) VALUES (uuid(), 1412, '3-3', now(), now());
insert into `chat_message`(msgId, tenantId, body, updatedAt, createdAt) VALUES (uuid(), 1412, '3-4', now(), now());
