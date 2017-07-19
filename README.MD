## Setup

执行setup文件夹中的1.sql创建数据库和基础数据

## 试验

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