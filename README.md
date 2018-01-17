# vatProject

首先将quartz.sql的文件放到自己的数据库（mysql）中初始化数据表。

然后将项目导入到eclipse或者Idea中直接运行即可,访问路径：http://localhost:8080/vatTemplate/scheduleJob

此项目是一个简单的集合了spring，springmvc,mybatis并且引入了一个可以执行可视化配置的quartz框架。

该框架集成了redis的配置，及一些简单操作。

框架引入了aop的配置，可以拦截相应请求，便于对请求，响应做日志记录。

提供了一种基于注解的导出excel的功能，用起来非常方便。

该项目引入了javamelody，用于监控项目运行的各个指标。

此项目做了配置文件的环境隔离（dev，test，product）使用maven命令，mvn -Pdev就可以针对不同环境打包项目。此配置为jenkins自动化部署提供了基础。

项目运行环境：jdk1.7+tomcat7

本人的博客地址，[传送门](http://172.96.249.153:8080/) ,欢迎访问~
