@REM ----------------------------------------------------------------------------
@REM  Copyright 2001-2006 The Apache Software Foundation.
@REM
@REM  Licensed under the Apache License, Version 2.0 (the "License");
@REM  you may not use this file except in compliance with the License.
@REM  You may obtain a copy of the License at
@REM
@REM       http://www.apache.org/licenses/LICENSE-2.0
@REM
@REM  Unless required by applicable law or agreed to in writing, software
@REM  distributed under the License is distributed on an "AS IS" BASIS,
@REM  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
@REM  See the License for the specific language governing permissions and
@REM  limitations under the License.
@REM ----------------------------------------------------------------------------
@REM
@REM   Copyright (c) 2001-2006 The Apache Software Foundation.  All rights
@REM   reserved.

@echo off

set ERROR_CODE=0

:init
@REM Decide how to startup depending on the version of windows

@REM -- Win98ME
if NOT "%OS%"=="Windows_NT" goto Win9xArg

@REM set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" @setlocal

@REM -- 4NT shell
if "%eval[2+2]" == "4" goto 4NTArgs

@REM -- Regular WinNT shell
set CMD_LINE_ARGS=%*
goto WinNTGetScriptDir

@REM The 4NT Shell from jp software
:4NTArgs
set CMD_LINE_ARGS=%$
goto WinNTGetScriptDir

:Win9xArg
@REM Slurp the command line arguments.  This loop allows for an unlimited number
@REM of arguments (up to the command line limit, anyway).
set CMD_LINE_ARGS=
:Win9xApp
if %1a==a goto Win9xGetScriptDir
set CMD_LINE_ARGS=%CMD_LINE_ARGS% %1
shift
goto Win9xApp

:Win9xGetScriptDir
set SAVEDIR=%CD%
%0\
cd %0\..\.. 
set BASEDIR=%CD%
cd %SAVEDIR%
set SAVE_DIR=
goto repoSetup

:WinNTGetScriptDir
set BASEDIR=%~dp0\..

:repoSetup
set REPO=


if "%JAVACMD%"=="" set JAVACMD=java

if "%REPO%"=="" set REPO=%BASEDIR%\repo

set CLASSPATH="%BASEDIR%"\etc;"%REPO%"\org\glassfish\javax.faces\2.2.16\javax.faces-2.2.16.jar;"%REPO%"\org\primefaces\primefaces\6.1\primefaces-6.1.jar;"%REPO%"\org\glassfish\jersey\containers\jersey-container-servlet\2.26\jersey-container-servlet-2.26.jar;"%REPO%"\org\glassfish\jersey\containers\jersey-container-servlet-core\2.26\jersey-container-servlet-core-2.26.jar;"%REPO%"\org\glassfish\hk2\external\javax.inject\2.5.0-b42\javax.inject-2.5.0-b42.jar;"%REPO%"\org\glassfish\jersey\core\jersey-common\2.26\jersey-common-2.26.jar;"%REPO%"\javax\annotation\javax.annotation-api\1.2\javax.annotation-api-1.2.jar;"%REPO%"\org\glassfish\hk2\osgi-resource-locator\1.0.1\osgi-resource-locator-1.0.1.jar;"%REPO%"\org\glassfish\jersey\core\jersey-server\2.26\jersey-server-2.26.jar;"%REPO%"\org\glassfish\jersey\core\jersey-client\2.26\jersey-client-2.26.jar;"%REPO%"\org\glassfish\jersey\media\jersey-media-jaxb\2.26\jersey-media-jaxb-2.26.jar;"%REPO%"\javax\validation\validation-api\1.1.0.Final\validation-api-1.1.0.Final.jar;"%REPO%"\javax\ws\rs\javax.ws.rs-api\2.1\javax.ws.rs-api-2.1.jar;"%REPO%"\com\sun\jersey\jersey-json\1.19.4\jersey-json-1.19.4.jar;"%REPO%"\org\codehaus\jettison\jettison\1.1\jettison-1.1.jar;"%REPO%"\com\sun\xml\bind\jaxb-impl\2.2.3-1\jaxb-impl-2.2.3-1.jar;"%REPO%"\javax\xml\bind\jaxb-api\2.2.2\jaxb-api-2.2.2.jar;"%REPO%"\javax\xml\stream\stax-api\1.0-2\stax-api-1.0-2.jar;"%REPO%"\javax\activation\activation\1.1\activation-1.1.jar;"%REPO%"\org\codehaus\jackson\jackson-core-asl\1.9.2\jackson-core-asl-1.9.2.jar;"%REPO%"\org\codehaus\jackson\jackson-mapper-asl\1.9.2\jackson-mapper-asl-1.9.2.jar;"%REPO%"\org\codehaus\jackson\jackson-jaxrs\1.9.2\jackson-jaxrs-1.9.2.jar;"%REPO%"\org\codehaus\jackson\jackson-xc\1.9.2\jackson-xc-1.9.2.jar;"%REPO%"\com\sun\jersey\jersey-core\1.19.4\jersey-core-1.19.4.jar;"%REPO%"\javax\ws\rs\jsr311-api\1.1.1\jsr311-api-1.1.1.jar;"%REPO%"\com\sun\jersey\jersey-client\1.19.4\jersey-client-1.19.4.jar;"%REPO%"\org\glassfish\jersey\inject\jersey-hk2\2.26\jersey-hk2-2.26.jar;"%REPO%"\org\glassfish\hk2\hk2-locator\2.5.0-b42\hk2-locator-2.5.0-b42.jar;"%REPO%"\org\glassfish\hk2\external\aopalliance-repackaged\2.5.0-b42\aopalliance-repackaged-2.5.0-b42.jar;"%REPO%"\org\glassfish\hk2\hk2-api\2.5.0-b42\hk2-api-2.5.0-b42.jar;"%REPO%"\javax\inject\javax.inject\1\javax.inject-1.jar;"%REPO%"\org\glassfish\hk2\hk2-utils\2.5.0-b42\hk2-utils-2.5.0-b42.jar;"%REPO%"\org\javassist\javassist\3.22.0-CR2\javassist-3.22.0-CR2.jar;"%REPO%"\com\google\code\gson\gson\2.8.2\gson-2.8.2.jar;"%REPO%"\com\fasterxml\jackson\core\jackson-databind\2.9.4\jackson-databind-2.9.4.jar;"%REPO%"\com\fasterxml\jackson\core\jackson-annotations\2.9.0\jackson-annotations-2.9.0.jar;"%REPO%"\com\fasterxml\jackson\core\jackson-core\2.9.4\jackson-core-2.9.4.jar;"%REPO%"\org\apache\httpcomponents\httpclient\4.5.5\httpclient-4.5.5.jar;"%REPO%"\org\apache\httpcomponents\httpcore\4.4.9\httpcore-4.4.9.jar;"%REPO%"\commons-logging\commons-logging\1.2\commons-logging-1.2.jar;"%REPO%"\commons-codec\commons-codec\1.10\commons-codec-1.10.jar;"%REPO%"\org\apache\tomcat\embed\tomcat-embed-core\8.5.23\tomcat-embed-core-8.5.23.jar;"%REPO%"\org\apache\tomcat\tomcat-annotations-api\8.5.23\tomcat-annotations-api-8.5.23.jar;"%REPO%"\org\apache\tomcat\embed\tomcat-embed-jasper\8.5.23\tomcat-embed-jasper-8.5.23.jar;"%REPO%"\org\apache\tomcat\embed\tomcat-embed-el\8.5.23\tomcat-embed-el-8.5.23.jar;"%REPO%"\org\eclipse\jdt\ecj\3.12.3\ecj-3.12.3.jar;"%REPO%"\com\github\adminfaces\admin-theme\1.0.0-RC12\admin-theme-1.0.0-RC12.jar;"%REPO%"\org\webjars\font-awesome\5.0.6\font-awesome-5.0.6.jar;"%REPO%"\org\webjars\bootstrap\4.0.0\bootstrap-4.0.0.jar;"%REPO%"\org\webjars\jquery\3.0.0\jquery-3.0.0.jar;"%REPO%"\org\webjars\npm\popper.js\1.11.1\popper.js-1.11.1.jar;"%REPO%"\br\ind\seat\Desafio-Seat\1.0.0\Desafio-Seat-1.0.0.war

set ENDORSED_DIR=
if NOT "%ENDORSED_DIR%" == "" set CLASSPATH="%BASEDIR%"\%ENDORSED_DIR%\*;%CLASSPATH%

if NOT "%CLASSPATH_PREFIX%" == "" set CLASSPATH=%CLASSPATH_PREFIX%;%CLASSPATH%

@REM Reaching here means variables are defined and arguments have been captured
:endInit

%JAVACMD% %JAVA_OPTS%  -classpath %CLASSPATH% -Dapp.name="webapp" -Dapp.repo="%REPO%" -Dapp.home="%BASEDIR%" -Dbasedir="%BASEDIR%" launch.Main %CMD_LINE_ARGS%
if %ERRORLEVEL% NEQ 0 goto error
goto end

:error
if "%OS%"=="Windows_NT" @endlocal
set ERROR_CODE=%ERRORLEVEL%

:end
@REM set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" goto endNT

@REM For old DOS remove the set variables from ENV - we assume they were not set
@REM before we started - at least we don't leave any baggage around
set CMD_LINE_ARGS=
goto postExec

:endNT
@REM If error code is set to 1 then the endlocal was done already in :error.
if %ERROR_CODE% EQU 0 @endlocal


:postExec

if "%FORCE_EXIT_ON_ERROR%" == "on" (
  if %ERROR_CODE% NEQ 0 exit %ERROR_CODE%
)

exit /B %ERROR_CODE%
