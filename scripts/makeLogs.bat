@echo off
SET ROOT=\\netapp2.regions.local\Distibutive\_Logs
:
ECHO %DATE:~-10,10% %TIME:~,8% %COMPUTERNAME% %USERNAME% %USERDOMAIN%>> %ROOT%\Log-Users\%USERNAME%-%USERDOMAIN%.log
ECHO %DATE:~-10,10% %TIME:~,8% %COMPUTERNAME% %USERNAME% %USERDOMAIN%>> %ROOT%\Log-Comps\%COMPUTERNAME%.log
:
%ROOT%\produkey.exe /sjson %ROOT%\Log-Keys\%COMPUTERNAME%.json
:
cscript //Nologo //U %ROOT%\softlist.vbs > %ROOT%\Log-Soft\%COMPUTERNAME%.json
:
cscript //Nologo //U %ROOT%\printerlist.vbs > %ROOT%\Log-Printers\%COMPUTERNAME%.json
:
%ROOT%\Log-Cfg\checkcfg.exe
SET ROOT=
:FINISH
