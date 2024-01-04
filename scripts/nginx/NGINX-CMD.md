# NGINX COMMAND
## 1. reload_nginx_conf.sh 
- usage

reload_nginx_conf.sh $PROJECT $PROFILE

reload_nginx_conf.sh admin-api beta
```$xslt
PROJECT: [admin-api, portal-api, task]
PROFILE: [dev, test, real]
```


- comments

update nginx.conf

reload nginx

## 2. restart_nginx_conf.sh
- usage

restart_nginx_conf.sh $PROJECT $PROFILE

restart_nginx_conf.sh admin-api test
```$xslt
PROJECT: [admin-api, portal-api, task]
PROFILE: [dev, test, real]
```

- comments

update nginx.conf

stop nginx

start nginx

## 3. start_nginx_conf.sh
- usage

start_nginx_conf.sh $PROJECT $PROFILE

start_nginx_conf.sh admin-api test
```$xslt
PROJECT: [admin-api, portal-api, task]
PROFILE: [dev, test, real]
```

- comments

update nginx.conf

start nginx