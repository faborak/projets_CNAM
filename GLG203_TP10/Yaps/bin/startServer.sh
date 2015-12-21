#!/bin/sh

cygwin=false
case "`uname`" in
CYGWIN*) cygwin=true;;
esac

JAVA=$JAVA_HOME/bin/java
RMIREGISTRY=$JAVA_HOME/bin/rmiregistry
CLASSPATH=

$RMIREGISTRY &


DEPLOY_DIR="../build"
LIB_DIR="../../lib"

# copy RMI required jars to a web server (http://localhost:8080/petstore/rmi)
if [ ! -d $TOMCAT_HOME/webapps/petstore/rmi ]
then
    mkdir $TOMCAT_HOME/webapps/petstore/rmi
fi
cp $DEPLOY_DIR/server.jar $TOMCAT_HOME/webapps/petstore/rmi
cp $DEPLOY_DIR/common.jar $TOMCAT_HOME/webapps/petstore/rmi


XML_JAR=$LIB_DIR/dom4j.jar:$LIB_DIR/jaxen.jar
CLASSPATH=$DEPLOY_DIR/server.jar:$DEPLOY_DIR/common.jar:$MYSQL_HOME/lib/mysql-connector-java-5.1.21-bin.jar::$XML_JAR

if $cygwin; then
# CLASSPATH=`cygpath --path --windows "$CLASSPATH"`
CLASSPATH="$DEPLOY_DIR/server.jar;$DEPLOY_DIR/common.jar;$MYSQL_HOME/lib/mysql-connector-java-5.1.21-bin.jar;:$XML_JAR"
fi



$JAVA -Djava.rmi.server.useLocalHostname=true  -Djava.rmi.server.codebase="http://localhost:8080/petstore/rmi/server.jar http://localhost:8080/petstore/rmi/common.jar" -classpath "$CLASSPATH" com.yaps.petstore.server.RegisterServices


