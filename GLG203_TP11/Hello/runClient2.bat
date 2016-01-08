rem start the Main2 remote client

set JAVA=%JAVA_HOME%\bin\java

set CLASSPATH=classes;%GLASSFISH_HOME%\lib\gf-client.jar

rem %JAVA% -cp %CLASSPATH% Main2


%GLASSFISH_HOME%/bin/appclient -classpath classes Main2 