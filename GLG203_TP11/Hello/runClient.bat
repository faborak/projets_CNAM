rem start the Main remote client

set JAVA=%JAVA_HOME%\bin\java

set CLASSPATH=classes;%GLASSFISH_HOME%\lib\gf-client.jar

%JAVA% -cp %CLASSPATH% Main
