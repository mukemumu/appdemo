#!/bin/bash

load_configuration() {
    SCRIPT_DIR=$(dirname "$0")
    cd ${SCRIPT_DIR}

    echo "[INFO] config file path=$CONFIG_FILE"
    source load_config.sh ${CONFIG_FILE}
}

check_started() {
    echo "[INFO] Wait ($SERVICE_NAME) start.(Delay $TEST_WAIT_SECONDS seconds)"
    sleep ${TEST_WAIT_SECONDS}

    for (( try_cnt=1; try_cnt<=${TEST_TRIES}; try_cnt++ ))
    do
            echo "[INFO] Checking HTTP port. ("${try_cnt}"/$TEST_TRIES)"

            http_status_code=`curl -sL -o /dev/null -I -w "%{http_code}" ${TEST_URL} --max-time 10` || true
            if [[ ${http_status_code} -gt 199 ]] && [[ ${http_status_code} -lt 300 ]] ; then
                    echo "[INFO] process ($SERVICE_NAME) has started successfully."
                    break
            fi
            if [[ ${try_cnt} = ${TEST_TRIES} ]] ; then
                    echo "[ERROR] process ($SERVICE_NAME) failed to start."
                    exit 1
            fi
            sleep ${TEST_INTERVAL}
    done
}

start_service() {
    echo "[INFO] start service: $SERVICE_NAME"

    echo "[INFO] First, try to stop the running process: [$SERVICE_NAME]"

    stop_service

    echo "[INFO] Starting [$SERVICE_NAME]"
    JAVA_BASIC_OPTS=" -server "
    JAVA_OPTS=" ${JAVA_BASIC_OPTS} ${JAVA_OPTS}"
    echo "[INFO] exec: ${JAVA_HOME}/bin/java ${JAVA_OPTS} -jar ${JAR_OPTS} ${JAR_PATH}"
    nohup ${JAVA_HOME}/bin/java ${JAVA_OPTS} -jar ${JAR_OPTS} ${JAR_PATH} > /dev/zero 2>&1 &

    check_started
}


stop_service() {
    echo "[INFO] Stop ($SERVICE_NAME)"

    echo "[INFO] first, try to invoke url to stop it gracefully"
    curl -X POST ${SHUTDOWN_URL} || true

    sleep 5s

    echo "[INFO] try to kill the process by pid"
    kill_service_pid
}

kill_service_pid(){
    pid_numbers=`pgrep -f ${JAR_PATH}`
    if [[ $? == 0 ]]; then
         echo "[INFO] kill pid: $pid_numbers"
         for pid_n in ${pid_numbers}
             do
               kill -9 ${pid_n}
             done
    fi
}

function usage() {
    echo "This is the script for starting/stoping service"
    echo ""
    echo "Usage: bash service_deploy.sh  {-c | --config <config file>} {start|stop}"
}


POSITIONAL=()
while [[ $# -gt 0 ]]
do
    key="$1"
    case ${key} in
        "start"|"stop")
            ACTION=${key}
            shift
            ;;
        "-c"|"--config")
            CONFIG_FILE="$2"
            shift
            shift
            ;;
        "-h"|"--help")
            usage
            exit 0
            ;;
        *)
            POSITIONAL+=("$1") # unknown parameters
            shift
            ;;
    esac
done
set -- "${POSITIONAL[@]}" # restore positional parameters


load_configuration
case "${ACTION}" in
    "start")
        start_service
        ;;
    "stop")
        echo "stop"
        stop_service
        ;;
    *)
        echo "Error: only support start or stop as param 1"
        exit 1
        ;;
esac

exit 0