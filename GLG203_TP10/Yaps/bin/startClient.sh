# start a local client (which does not use RMI)

cygwin=false
case "`uname`" in
CYGWIN*) cygwin=true;;
esac

JAVA=$JAVA_HOME/bin/java
DEPLOY_DIR=../build

CLASSPATH=$DEPLOY_DIR/client.jar:$DEPLOY_DIR/common.jar:$DEPLOY_DIR/server.jar
if $cygwin; then
CLASSPATH="$DEPLOY_DIR/client.jar;$DEPLOY_DIR/common.jar;$DEPLOY_DIR/server.jar"
fi

$JAVA -classpath "$CLASSPATH" com.yaps.petstore.client.ui.Menu
