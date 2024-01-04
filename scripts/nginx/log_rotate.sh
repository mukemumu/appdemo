#!/bin/bash
set -e

# nginx log rotate
# 1 0 * * * bash /home/buercorp/deploy/scripts/nginx/log_rotate.sh
# echo -e '1 0 * * * bash /home/buercorp/deploy/scripts/nginx/log_rotate.sh' | crontab -

LOG_PATH=/home/buercorp/logs/nginx
PID_PATH=/home/buercorp/apps/nginx/logs/nginx.pid

# Rename
YESTERDAY=$(date -d "yesterday" +%Y-%m-%d)
mv ${LOG_PATH}/access.log ${LOG_PATH}/access-${YESTERDAY}.log
mv ${LOG_PATH}/error.log  ${LOG_PATH}/error-${YESTERDAY}.log

# Send USR1 Signal to reopen the log file
kill -USR1 `cat ${PID_PATH}`

# Delete log files which over 5 days.
find ${LOG_PATH} -mtime +5 -name "*.log" | xargs rm -f
