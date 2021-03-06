@echo off

set JAVA=%JAVA_HOME%\bin\java
set RMIREGISTRY=%JAVA_HOME%\bin\rmiregistry

set DEPLOY_DIR=..\build
set LIB_DIR=..\..\lib

set XML_JAR=%LIB_DIR%\dom4j.jar;%LIB_DIR%\jaxen.jar

set CLASSPATH=%DEPLOY_DIR%\server.jar;%DEPLOY_DIR%\common.jar;%MYSQL_HOME%\lib\mysql-connector-java-5.1.21-bin.jar;%XML_JAR%

start %RMIREGISTRY%

%JAVA% -Djava.rmi.server.logCalls=true -cp %CLASSPATH%  -Djava.util.logging.config.file=mylogging.properties com.yaps.petstore.server.RegisterServices

pause