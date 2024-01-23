#!/bin/bash

# 组名、用户名、密码
GROUPNAME="buercorp"
USERNAME="buercorp"

# 文件路径
BASE_DIR="/home/${USERNAME}/apps"
DOWNLOADS="/home/${USERNAME}/downloads"
DATA_DIR="/home/${USERNAME}/apps/mysql/data"
CORE_DIR="/home/${USERNAME}/repository/appdemo-be/scripts/mysql"
CONF_DIR="/home/${USERNAME}/apps/mysql/buercorp"

# 端口
PORT="3306"

# mysql 版本
MYSQLVERSION="mysql-8.0.30"

# mysql 配置文件源地址
SOURCE_FILE=${CORE_DIR}/dev

# 需要使用使用 root 权限执行
if [ $UID -eq 0 ]; then
  echo ""
else
  echo "权限不足！需要 root 权限"
  exit
fi

# 删除现有的 Mysql
base CORE_DIR/mysql_remove.sh

# 下载MySQL
if [ -d $DOWNLOADS ]; then
  cd $DOWNLOADS
else
  mkdir $DOWNLOADS
  chown -R ${USERNAME}:${GROUPNAME} ${DOWNLOADS}
  cd $DOWNLOADS
fi

wget https://downloads.mysql.com/archives/get/p/23/file/${MYSQLVERSION}-linux-glibc2.12-x86_64.tar.xz

# 解压到指定文件路径
if [ -d "${BASE_DIR}" ]; then
  echo
else
  mkdir ${BASE_DIR}
  chown -R ${USERNAME}:${GROUPNAME} ${BASE_DIR}
fi

tar -Jxvf ${DOWNLOADS}/${MYSQLVERSION}-linux-glibc2.12-x86_64.tar.xz -C ${BASE_DIR}
rm -rf ${MYSQLVERSION}-linux-glibc2.12-x86_64.tar.xz

# 重命名
cd ${BASE_DIR}
mv ${MYSQLVERSION}-linux-glibc2.12-x86_64 ${MYSQLVERSION}
chown -R ${USERNAME}:${GROUPNAME} ${MYSQLVERSION}

# 软链接
ln -s ${MYSQLVERSION} mysql
chown -R ${USERNAME}:${GROUPNAME} mysql

# 创建数据目录
mkdir -p $DATA_DIR

# 设置属主和权限
chown -R ${USERNAME}:${GROUPNAME} ${DATA_DIR}
chmod -R 777 ${DATA_DIR}

# 创建配置文件目录和启动脚本目录
mkdir -p ${CONF_DIR}/conf
mkdir -p ${CONF_DIR}/bin

chown -R ${USERNAME}:${GROUPNAME} ${CONF_DIR}
chmod -R 777 ${CONF_DIR}

# 复制配置文件内容到目标文件
cd ${CONF_DIR}/conf
touch my.cnf
cp SOURCE_FILE/my.txt ${CONF_DIR}/conf/my.cnf
chown -R ${USERNAME}:${GROUPNAME} my.cnf
#chmod -R 777 my.cnf

# 初始化
${BASE_DIR}/mysql/bin/mysqld --defaults-file=${CONF_DIR}/conf/my.cnf --basedir=${BASE_DIR}/mysql --datadir=${DATA_DIR} --user=${USERNAME} --initialize-insecure

echo "mysql 安装成功!"
