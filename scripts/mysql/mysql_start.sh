#!/bin/bash

USERNAME="buercorp"
PASSWORD="buercorp@20240104"

BASE_DIR="/home/${USERNAME}/apps"
CONF_DIR="/home/${USERNAME}/apps/mysql/buercorp"

# 获取正在执行的 MySQL 进程的 PID
mysql_pid=$(pgrep mysql)

# 检查是否存在正在执行的 MySQL 进程
if [ -n "$mysql_pid" ]; then
  echo "正在执行的 MySQL 进程 PID: $mysql_pid"

  # 关闭 MySQL 进程
  kill "$mysql_pid"
  echo "MySQL 进程已关闭"
else
  echo "没有正在执行的 MySQL 进程"
fi

# 启动
# mysqld_safe --defaults-file=/home/buercorp/apps/mysql/buercorp/conf/my.cnf &
${BASE_DIR}/mysql/bin/mysqld --defaults-file=${CONF_DIR}/conf/my.cnf &

# 建立链接
${BASE_DIR}/mysql/bin/mysql -u root --skip-password

# 修改密码
ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY ${PASSWORD};
alter user 'root'@'localhost' password expire never;
FLUSH PRIVILEGES;
