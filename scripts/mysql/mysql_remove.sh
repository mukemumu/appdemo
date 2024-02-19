#!/bin/bash

# 组名、用户名、密码
GROUPNAME="buercorp"
USERNAME="buercorp"

# 文件路径
BASE_DIR="/home/$USERNAME/apps"

# MySQL 版本
MYSQLVERSION="mysql-8.0.30"

# 需要使用使用 root 权限执行
if [ $UID -eq 0 ]; then
  echo ""
else
  echo "权限不足！需要 root 权限"
  exit
fi

# 检查是否存在正在执行的 MySQL 进程
if [ -n "$mysql_pid" ]; then
  echo "正在执行的 MySQL 进程 PID: $mysql_pid"

  # 关闭 MySQL 进程
  kill "$mysql_pid"
  echo "MySQL 进程已关闭"
else
  echo "没有正在执行的 MySQL 进程"
fi

# 删除MySQL的配置文件和数据目录
rm -rf ${BASE_DIR}/mysql ${BASE_DIR}/${MYSQLVERSION}
echo "mysql 已经彻底删除！"
