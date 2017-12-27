## 试验1

#### Setup

执行setup文件夹中的1.sql创建数据库和基础数据

#### 原始数据

##### 查询

```
curl http://localhost:8070/v5/1410/messages
curl http://localhost:8070/v5/1411/messages
curl http://localhost:8070/v5/1412/messages
```

#### 分库数据

目前支持保存、查询、修改、删除，需要使用ShardOn注解



##### 保存

```
curl -X POST \
  http://localhost:8070/v5/1412/routing-messages \
  -H 'content-type: application/json' \
  -d '{
	"body": "3-8"
}'
```

##### 查询

```
curl http://localhost:8070/v5/1410/routing-messages
curl http://localhost:8070/v5/1411/routing-messages
curl http://localhost:8070/v5/1412/routing-messages
```

##### 修改

```
curl -X PUT \
    http://localhost:8070/v5/1412/routing-messages/a8289de7-8c22-4fe0-9c2d-f8eccd035ce0 \
    -H 'content-type: application/json' \
    -d '{
  	"body": "3-8-u1"
  }'
 ```

##### 删除

```
curl -X DELETE \
  http://localhost:8070/v5/1412/routing-messages/a8289de7-8c22-4fe0-9c2d-f8eccd035ce0 \
```

## 试验2

#### Setup

执行setup中的2_table-shard.sql

#### 查询

```
curl http://localhost:8070/v5/mybatis/t1?tenantId=1410&msgId=07a32d5a-2ef6-425d-b289-b2af3dc3a794
curl http://localhost:8070/v5/mybatis/t1?tenantId=1411&msgId=07a32d5a-2ef6-425d-b289-b2af3dc3a795
curl http://localhost:8070/v5/mybatis/t1?tenantId=1412&msgId=07a32d5a-2ef6-425d-b289-b2af3dc3a796
```

## 更新

### 2017-07-24

初始版本完成，支持基础分库功能

### 2017-08-02

支持全注解配置，支持读写分离

### 2017-12-27

由于之前做实验增加了一堆服务间调用和服务注册的东西，如要本地启动consul。如果不需要可以在bootstrap.yml中关闭

使用mybatis支持在同一个库中对table进行shard