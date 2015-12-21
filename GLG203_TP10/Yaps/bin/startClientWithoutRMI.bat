@echo off
rem start a local client (which does not use RMI)

set JAVA=%JAVA_HOME%\bin\java
set DEPLOY_DIR=..\build

rem set CLASSPATH=%DEPLOY_DIR%\client.jar;%DEPLOY_DIR%\common.jar
set CLASSPATH=%DEPLOY_DIR%\client.jar;%DEPLOY_DIR%\common.jar;%DEPLOY_DIR%\server.jar;%MYSQL_HOME%\lib\mysql-connector-java-5.1.21-bin.jar

%JAVA% -DdonotuseRMI  -cp %CLASSPATH% com.yaps.petstore.client.ui.Menu
