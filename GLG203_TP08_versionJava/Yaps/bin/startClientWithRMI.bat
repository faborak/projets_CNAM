@echo off
rem start a remote client (which use RMI)

set JAVA=%JAVA_HOME%\bin\java
set DEPLOY_DIR=..\build

set CLASSPATH=%DEPLOY_DIR%\client.jar;%DEPLOY_DIR%\common.jar

%JAVA%  -DuseRMI -cp %CLASSPATH% com.yaps.petstore.client.ui.Menu
