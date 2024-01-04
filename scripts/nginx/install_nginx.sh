#!/bin/bash

set -e

cd /home/buercorp
wget --no-check-certificate https://nginx.org/download/nginx-1.24.0.tar.gz
tar xvf nginx-1.24.0.tar.gz
cd nginx-1.24.0
wget --no-check-certificate https://www.openssl.org/source/openssl-1.1.0f.tar.gz
tar xvf openssl-1.1.0f.tar.gz
./configure --prefix=/home/buercorp/apps/nginx-1.24.0 --user=buercorp --group=buercorp --error-log-path=/home/buercorp/logs/nginx/error.log --http-log-path=/home/buercorp/logs/nginx/access.log --with-openssl=openssl-1.1.0f --with-http_realip_module --with-http_ssl_module --without-http_scgi_module --without-http_uwsgi_module --without-http_fastcgi_module
make
make install

cd /home/buercorp/apps
ln -s nginx-1.24.0 nginx

#/sbin/setcap 'cap_net_bind_service=+ep' /home/buercorp/apps/nginx/sbin/nginx
