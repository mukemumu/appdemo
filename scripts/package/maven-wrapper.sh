#!/bin/bash

# 替换下面的路径为你机器上对应的实际路径
JAVA_HOME="/home/li-dongdong/apps/jdk"
MAVEN_HOME="/home/li-dongdong/apps/maven"

# 为Java和Maven执行指定使用的路径
PATH=$JAVA_HOME/bin:$MAVEN_HOME/bin:$PATH

# 执行Maven命令，$@表示传递给脚本的所有参数
mvn $@