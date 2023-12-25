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
                wget https://github.com/AdoptOpenJDK/openjdk8-binaries/releases/download/jdk8u232-b09/OpenJDK8U-jdk_x64_linux_hotspot_8u232b09.tar.gz
                tar -zxvf OpenJDK8U-jdk_x64_linux_hotspot_8u232b09.tar.gz

                echo -n " - JDK Installtaion =>"

                ln -sfn jdk8u232-b09 jdk

                rm OpenJDK8U-jdk_x64_linux_hotspot_8u232b09.tar.gz
                echo "OK!!"
        else
                echo "This OS version not supported"
        fi

}

#### main ###
check_os
set_openjdk
