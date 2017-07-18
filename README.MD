* 执行setup中的sql脚本

* 试验

### 原始数据

* curl http://localhost:8070/v5/1410/messages
* curl http://localhost:8070/v5/1411/messages
* curl http://localhost:8070/v5/1412/messages

### 分库数据

* curl http://localhost:8070/v5/1410/routing-messages
* curl http://localhost:8070/v5/1411/routing-messages
* curl http://localhost:8070/v5/1412/routing-messages
