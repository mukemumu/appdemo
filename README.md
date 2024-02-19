# appdemo-be 基于 Maven 多模块的基础项目框架
## 项目运行环境
* JDK: 17
* SpringBoot: 3.0.5
* maven: 3.9.5
* mysql: 8.0.30

## 项目功能完善
### 整合 mybatis 逆向工程 
1. 引入 mybatis-generator-plugin-1.0.jar 放在 repository 模块中
2. 在 pom.xml 文件中添加插件
3. 在 resources 中添加 generatorConfig.xml 文件

### 整合日志框架
