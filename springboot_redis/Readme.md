
[TOC]

## 说明

1.springboot
2.redis

## 通过docker启动redis实例
1. 搜索redis资源
`docker search redis`

2. 拉取
`docker pull reisd`

3. 查看reids镜像
`docker images`

4. 运行
`docker run -d --name myredis -p 6379:6379 redis:latest`

5. 查看启动
`docker ps `

6. 查看绑定端口
`docker port myredis `
`docker port myredis 6379`

## 访问
CityController.java
1. 设定
http://localhost:8888/saveCity?cityName=北京&cityIntroduce=中国首都&cityId=1
2. 读取
http://localhost:8888/getCityById?cityId=1
