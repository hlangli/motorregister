#!/bin/bash

SCRIPT=`readlink -f $0`
SCRIPT_DIR=`dirname ${SCRIPT}`
BASE_DIR=${SCRIPT_DIR}/..

unset CLASSPATH
CLASSPATH=${BASE_DIR}/etc
for jar in ${BASE_DIR}/lib/*.jar; do
	CLASSPATH=$CLASSPATH:$jar
done
export CLASSPATH

exec java ${JVM_OPTIONS} dk.langli.aula.contactlist.Main $*

