#!/bin/bash

WORK_HOME="/home1/irteam"
APP_HOME="${WORK_HOME}/apps"

REPO_ADDR="repo.nhnsystem.com";

check_os() {
   OS_VERSION=$(sed -r 's/.*release ([0-9]).*/\1/' /etc/redhat-release)
   OS_ARCH=$(uname -i)
   echo "OS VERSION : ${OS_ARCH}"
}


set_openjdk() {
cd $APP_HOME
if [ "$OS_ARCH" == "x86_64" ];
   then
      # https://github.com/AdoptOpenJDK/openjdk11-binaries/releases/download/jdk11u-2020-01-20-01-40/OpenJDK11U-jdk_x64_linux_hotspot_2020-01-20-01-40.tar.gz
      wget http://www.dev-ncloudworkbox.com/workbox-supports/openjdk-11/OpenJDK11U-jdk_x64_linux_hotspot_2020-01-20-01-40.tar.gz
      tar -zxvf OpenJDK11U-jdk_x64_linux_hotspot_2020-01-20-01-40.tar.gz

      echo -n " - JDK Installtaion =>"
      ln -sfn jdk-11.0.6+9 jdk

      rm OpenJDK11U-jdk_x64_linux_hotspot_2020-01-20-01-40.tar.gz
      echo "OK!!"
   else
      echo "This OS version not supported"
   fi
}

#### main ###
check_os
set_openjdk
