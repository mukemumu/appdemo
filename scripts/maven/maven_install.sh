#!/bin/bash

#工作目录
work_home="/home/li-dongdong"

#Maven安装包
mavenTarGz="${work_home}/downloads/apache-maven-3.9.6-bin.tar.gz"

#Maven下载位置
maven="${work_home}/apps/"

#Maveb配置文件位置
maven_setting="${maven}/maven-3.9.6/conf/settings.xml"

#自定义Maven仓库地址
maven_repo="${maven}/maven-3.9.6/maven-repo"

set_maven(){
	# 查询是否有maven.tar.gz安装包
	if [ -e $mavenTarGz ]
	then
	    echo "— — 存在maven压缩包 — —"
        echo "正在解压maven压缩包..."
		tar -zxvf ${mavenTarGz} -C ${maven}
		echo "---------------------------------"
		echo "Maven解压已完成..."
		cd $maven
		echo "----正在建立软链接--------"
        mv apache-maven-3.9.6 maven-3.9.6
        ln -sf maven-3.9.6 maven
		set_mirror
		set_repo
	else
		echo "未检测到安装包"
		exit 1
	fi
}

set_repo(){
    echo "正在配置maven仓库..."
	echo "---------------------------------"
    mkdir -p $maven_repo
    repo_num=$(grep -n "</localRepository>" "${maven_setting}" | cut -f1 -d':')
    sed -i "$((${repo_num} + 1))a\\  \\<localRepository\\>\\$maven_repo\\<\\/localRepository\\>" "$maven_setting"
    echo "maven仓库配置完成!配置路径为：${maven_repo}"
	echo "---------------------------------"
    echo "--请手动执行"mvn -v"检查maven信息--"
}

set_mirror(){
	echo "正在配置阿里云镜像..."
    echo "---------------------------------"
    num=$(sed -n -e "/<mirrors>/=" "$maven_setting")
    sed -i "${num}a\\    \\<mirror\\>" "$maven_setting"
    sed -i "$(($num + 1))a\\      \\<id\\>aliyunmaven\\<\\/id\\>" "$maven_setting"
    sed -i "$(($num + 2))a\\      \\<mirrorOf\\>*\\<\\/mirrorOf\\>" "$maven_setting"
    sed -i "$(($num + 3))a\\      \\<name\\>阿里云公共仓库\\<\\/name\\>" "$maven_setting"
    sed -i "$(($num + 4))a\\      \\<url\\>https://maven.aliyun.com/repository/public\\<\\/url\\>" "$maven_setting"
    sed -i "$(($num + 5))a\\    \\<\\/mirror\\>" "$maven_setting"
    echo "阿里云镜像配置完成"
	echo "---------------------------------"
}

set_maven

