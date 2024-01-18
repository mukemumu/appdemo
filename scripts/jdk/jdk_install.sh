#!/bin/bash


#工作目录
work_home="/home/li-dongdong"
#OpenJDK压缩包位置
jdkTargz="${work_home}/downloads/OpenJDK17U-jdk_x64_linux_hotspot_17.0.9_9.tar.gz"
#jdk安装目录
jdk_package="${work_home}/apps/"




set_jdk(){
	# 查询是否有jdk.tar.gz
	if [ -e $jdkTargz ];
	then
	echo "— — 存在jdk压缩包 — —"
		
		echo "正在解压jdk压缩包..."
		tar -zxvf ${jdkTargz} -C ${jdk_package}

    cd $jdk_package
		echo "正在建立jdk软链接..."
		ln -sf jdk-17.0.9+9 jdk
		
		echo "---------------------------------"
		echo "---------------------------------"
		echo "--------JDKInstall,OK!-------------"
		echo "---配置版本信息如下：---"
	    jdk/bin/java -version
		
		
	else
		echo "未检测到安装包"
		exit 1
	fi
}

####main
set_jdk
