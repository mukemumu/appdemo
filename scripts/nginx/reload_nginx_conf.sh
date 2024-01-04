#!/bin/bash

set -e

# Usage:
# bash /home/buercorp/deploy/scripts/nginx/reload_nginx_conf.sh admin-api beta

# PROJECT [admin-api, portal-api]
PROJECT=$1
# PROFILE [dev, test, real]
PROFILE=$2

NGINX_HOME=/home1/irteam/apps/nginx
CONF_NGINX_DIR=/home/buercorp/deploy/scripts/nginx
CONF_NGINX_CONF=/home/buercorp/deploy/scripts/nginx/$PROFILE/$PROJECT/nginx.conf
SSL_DIR_PATH=/home/buercorp/deploy/scripts/nginx/$PROFILE/ssl

#Config nginx
echo "Config nginx: copy nginx.conf"
cp $CONF_NGINX_CONF $NGINX_HOME/conf/

#Config ssl
mkdir -p  $NGINX_HOME/conf/ssl/
cp -r $SSL_DIR_PATH/* $NGINX_HOME/conf/ssl/
chmod -R 775 $NGINX_HOME/conf/ssl

sleep 1

#Start service
echo "Reload nginx"
$NGINX_HOME/sbin/nginx -s reload
echo "Reload nginx done"
echo -e "\n"