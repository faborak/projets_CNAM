@echo off

set JAVA=%JAVA_HOME%\bin\java
set RMIREGISTRY=%JAVA_HOME%\bin\rmiregistry
set CLASSPATH=
start %RMIREGISTRY%


set DEPLOY_DIR=..\build
set LIB_DIR=..\..\lib

set XML_JAR=%LIB_DIR%\dom4j.jar;%LIB_DIR%\jaxen.jar

set CLASSPATH=%DEPLOY_DIR%\server.jar;%DEPLOY_DIR%\common.jar;%MYSQL_HOME%\lib\mysql-connector-java-5.1.21-bin.jar;%XML_JAR%

rem set DEPLOY_DIR_FULL_PATH=D:/home/java/iagl2/src/GLG203/TP10/Yaps/build
set DEPLOY_DIR_FULL_PATH=%CD%\%DEPLOY_DIR%

%JAVA% -Djava.rmi.server.logCalls=true -Djava.rmi.server.codebase="file:///%DEPLOY_DIR_FULL_PATH%/server.jar file:///%DEPLOY_DIR_FULL_PATH%/common.jar" -cp %CLASSPATH% com.yaps.petstore.server.RegisterServices


pause
