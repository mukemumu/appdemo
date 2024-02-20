#!/bin/bash

# 工作目录
work_dir="/home/li-dongdong"

# 项目目录
work_home="${work_dir}/repository/appdemo-be"

# maven mvn命令执行脚本
wrapper="${work_home}/scripts/package"

# 存放jar包的目录
jar_home="${work_dir}/deploy/appdemo-be"

# 源码目录
code_home="${work_dir}/repository"

# Git仓库地址
git_repo="https://github.com/175T/appdemo-be.git"

# 排除的文件，可以减少cp命令的开销
excludes=(*.log *.tmp /path/to/exclude)

# 打包和部署函数
package_and_deploy() {
    local service_name=$1
    local jar_name=$2
    local target_dir="${work_home}/${service_name}/target"
    local jar_path="${target_dir}/${jar_name}"

    if [ -f "$jar_path" ]; then
        cp -rf "$jar_path" $jar_home/
        echo "--- $service_name --- OK! ---"
    else
        echo "Error: ${jar_path} does not exist."
        exit 1
    fi
}

# 主函数
main() {
    if [ -d "$work_home" ]; then
        echo "— — 工作目录存在 — —"
        cd "$work_home"
        echo "— — 正在更新项目... — —"
        git pull origin master
        echo "— — — — 项目已更新— — — —"
    else
        echo "未检测到需要打包的api"
        echo "正在为你拉取最新的项目"
        git clone $git_repo "$code_home"
        echo "— — 项目已克隆— — — —"
    fi

    echo "----正在执行打包命令...-----------"
    $wrapper/maven-wrapper.sh clean package -Dmaven.test.skip=true -Dcheckstyle.skip=true
    echo "所有项目打包完成"

    mkdir -p "$jar_home"

    # 这里根据实际的jar包名和位置进行拷贝
    package_and_deploy "admin-api" "admin-api.jar"
    package_and_deploy "common" "common.jar"
    package_and_deploy "portal-api" "portal-api.jar"
    package_and_deploy "repository" "repository.jar"
    package_and_deploy "service" "service.jar"
    package_and_deploy "task" "task.jar"

    echo "所有jar包均已存放至部署目录"
}

main "$@"