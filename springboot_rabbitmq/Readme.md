## 说明

1. Springboot
2. Rabbitmq

## docker启动Rabbitmq
1. 查找镜像
`docker search rabbitmq`
2. 拉取进行
`docker pull rabbitmq:3.7.16-management`
默认情况下，会拉取rabbitmq的latest版本。这里拉取 Web浏览器管理页面的tag 3.7.16-management
3. 启动镜像
`
docker run -p 15672:15672  -p  5672:5672 -d --hostname dnmp-rabbitmq \
--name dnmp-rabbitmq --network dnmp_backend \
-e RABBITMQ_DEFAULT_USER=admin \
-e RABBITMQ_DEFAULT_PASS=admin \
rabbitmq:3.7.16-management
`

```
参数解释
* 15672 ：表示 RabbitMQ 控制台端口号，可以在浏览器中通过控制台来执行 RabbitMQ 的相关操作。
* 5672: 表示 RabbitMQ 所监听的 TCP 端口号，应用程序可通过该端口与 RabbitMQ 建立 TCP 连接，完成后续的异步消息通信
* RABBITMQ_DEFAULT_USER：用于设置登陆控制台的用户名，这里我设置 admin
* RABBITMQ_DEFAULT_PASS：用于设置登陆控制台的密码，这里我设置 admin容器启动成功后，可以在浏览器输入地址：http://ip:15672/访问控制台
```

4. 创建message队列
登录http://ip:15672/，创建message队列

## 访问
TestController.java

http://localhost:8888/hello
