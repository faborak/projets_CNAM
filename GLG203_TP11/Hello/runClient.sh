# start the Main remote client

cygwin=false
case "`uname`" in
CYGWIN*) cygwin=true;;
esac

JAVA=$JAVA_HOME/bin/java

CLASSPATH=classes:$GLASSFISH_HOME/lib/gf-client.jar
if $cygwin; then
CLASSPATH="classes;$GLASSFISH_HOME/lib/gf-client.jar"
fi

$JAVA -classpath "$CLASSPATH" Main
