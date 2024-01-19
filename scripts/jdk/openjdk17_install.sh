#!/bin/bash

WORK_HOME="/home/buercorp"
APP_HOME="${WORK_HOME}/apps"

check_os() {
   OS_VERSION=$(sed -r 's/.*release ([0-9]).*/\1/' /etc/redhat-release)
   OS_ARCH=$(uname -i)
   echo "OS VERSION : ${OS_ARCH}"
}


set_openjdk() {
cd $APP_HOME
if [ "$OS_ARCH" == "x86_64" ];
   then
      https://github.com/AdoptOpenJDK/semeru17-binaries/releases/download/jdk-17.0.9%2B9_openj9-0.41.0/ibm-semeru-open-jdk_x64_linux_17.0.9_9_openj9-0.41.0.tar.gz
      tar -zxvf ibm-semeru-open-jdk_x64_linux_17.0.9_9_openj9-0.41.0.tar.gz
      echo -n " - JDK Installtaion =>"
      ln -sfn ibm-semeru-open-jdk_x64_linux_17.0.9_9_openj9-0.41.0 jdk
      echo "OK!!"
   else
      echo "This OS version not supported"
   fi
}

#### main ###
check_os
set_openjdk
