# appdemo-be 基于 Maven 多模块的基础项目框架
## 项目运行环境
* JDK: 17
* SpringBoot: 3.0.5
* maven: 3.6.3

# 项目功能完善
## 整合 mybatis 逆向工程 
1. 引入 mybatis-generator-plugin-1.0.jar 放在 repository 模块中
2. 在 pom.xml 文件中添加插件
<build>
        <plugins>
            <plugin>
                <groupId>org.mybatis.generator</groupId>
                <artifactId>mybatis-generator-maven-plugin</artifactId>
                <version>1.3.7</version>
                <dependencies>
                    <dependency>
                        <groupId>mysql</groupId>
                        <artifactId>mysql-connector-java</artifactId>
                        <version>8.0.12</version>
                    </dependency>
                    <dependency>
                        <groupId>com.github.greatwqs.mybatisplugin</groupId>
                        <artifactId>mybatis-generator-plugin</artifactId>
                        <version>1.0</version>
                        <scope>system</scope>
                        <systemPath>${basedir}\libs\mybatis-generator-plugin-1.0.jar</systemPath>
                    </dependency>
                </dependencies>
                <configuration>
                    <verbose>true</verbose>
                    <overwrite>false</overwrite>
                    <configurationFile>${basedir}\src\main\resources\generatorConfig.xml</configurationFile>
                </configuration>
            </plugin>
        </plugins>
    </build>
3. 在 resources 中添加 generatorConfig.xml 文件
