echo off

set DEPLOY_DIR=..\build
set LIB_DIR=..\..\lib

rem copy RMI required jars to a web server (http://localhost:8080/petstore/rmi)
echo %TOMCAT_HOME% >todel.del
findstr /C:"/"  todel.del >todel2.del
IF ERRORLEVEL 1 goto pathOK

:errPath
echo remplacer les / par des \ dans TOMCAT_HOME
goto end

:pathOK
set JAVA=%JAVA_HOME%\bin\java
set RMIREGISTRY=%JAVA_HOME%\bin\rmiregistry
set CLASSPATH=
start %RMIREGISTRY%

mkdir %TOMCAT_HOME%\webapps\petstore\rmi
copy %DEPLOY_DIR%\server.jar %TOMCAT_HOME%\webapps\petstore\rmi
copy %DEPLOY_DIR%\common.jar %TOMCAT_HOME%\webapps\petstore\rmi


set XML_JAR=%LIB_DIR%\dom4j.jar;%LIB_DIR%\jaxen.jar

set CLASSPATH=%DEPLOY_DIR%\server.jar;%DEPLOY_DIR%\common.jar;%MYSQL_HOME%\lib\mysql-connector-java-5.1.21-bin.jar;%XML_JAR%

%JAVA%  -Djava.rmi.server.useLocalHostname=true  -Djava.rmi.server.logCalls=true -Djava.rmi.server.codebase="http://localhost:8080/petstore/rmi/server.jar http://localhost:8080/petstore/rmi/common.jar" -cp %CLASSPATH% com.yaps.petstore.server.RegisterServices

pause

:end 
