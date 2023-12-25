#!/bin/bash

declare -A config

FILE=$1

while IFS=$'[ \t]*=[ \t]*' read -r name value
do
    if [[ -z ${name} ]] || [[ -z ${value} ]] ; then
        continue
    fi

    value=$(eval "echo ${value}")
    export ${name}="${value}"

    echo "[INFO] Read configuration: name=${name}, value=${value}" >&2
done < ${FILE}