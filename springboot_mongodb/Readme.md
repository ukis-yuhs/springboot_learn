[TOC]

## 说明

1. Springboot
2. mongodb

## docker启动Mongodb
1. 查找MongoDB镜像
`docker search mongodb`
2. 拉取MongoDB镜像
`docker pull mogo:latest`
3. 启动
`docker run -itd --name mongo -p 27017:27017 mongo:latest`
4. 查看运行状况
`dockers ps`

## 访问
UserController.java

1. http://localhost:8888/save
2. http://localhost:8888/getUserList
3. http://localhost:8888/update?id=1617846154290&username=111&password=222
4. http://localhost:8888/delete?id=1617846154290
