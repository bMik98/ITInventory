'==============================================================================
' Title:   logoninfo.vbs
' Created: 15.11.2016
' Author:  Mikhail Bedritskiy (bmik9898@gmail.com)
' Version: 1.0
' Updated:
' Purpose: Print current time, computer fqdn and user upn name
'==============================================================================
Option Explicit

LogonInfo
WScript.Quit

Sub logonInfo()
    Dim SEPARATOR: SEPARATOR = Chr(9)
    Dim objAdSystemInfo: Set objAdSystemInfo = CreateObject("ADSystemInfo")
    Dim logonInfo: Set logonInfo = GetLogonInfo(objAdSystemInfo)
    Dim report: report = ConvertDictionaryValuesToString(logonInfo, SEPARATOR)
    PrintLine report
End Sub

'==============================================================================
' Collect the information
'
Function GetLogonInfo(ByRef objAdSystemInfo)
    Dim dict: Set dict = CreateObject("Scripting.Dictionary")
    dict.CompareMode = 1
    dict.Add "ReportDate", DateFormat(Date)
    dict.Add "ReportTime", TimeFormat(Time)
    dict.Add "ComputerID", GetComputerFQDN(objAdSystemInfo)
    dict.Add "UserId", CurrentUserUPN()
    dict.Add "Site", objAdSystemInfo.SiteName
    Set GetLogonInfo = dict
    Set dict = Nothing
End Function

'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
Function GetComputerFQDN(objAdSystemInfo)
    GetComputerFQDN = LCase(ExtractCN(objAdSystemInfo.ComputerName) & "." & _
        objAdSystemInfo.domainDnsName)
End Function

'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
Function CurrentUserUPN()
    CurrentUserUPN = LCase(Env("USERNAME") & "@" & Env("USERDNSDOMAIN"))
End Function

'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
Function ExtractCN(strDistinguishedName)
    Dim stopPosition: stopPosition = InStr(strDistinguishedName, ",") - 4
    ExtractCN = Mid(strDistinguishedName, 4, stopPosition)
End Function

'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
Function Env(var)
    Dim wshShell: Set wshShell = CreateObject("WScript.Shell")
    Env = wshShell.ExpandEnvironmentStrings("%" & var & "%")
    Set wshShell = Nothing
End Function

'==============================================================================
' Reporting
'
Function ConvertDictionaryValuesToString(ByRef dict, ByVal div)
    Dim items: items = dict.items
    Dim result: result = ""
    Dim last: last = UBound(items)
    Dim i
    For i = 0 To last
        result = result & items(i) & Iff((i = last), "", div)
    Next
    ConvertDictionaryValuesToString = result
End Function

'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
Function Iff(blnExpression, trueVal, falseVal)
    If blnExpression Then
        Iff = trueVal
    Else
        Iff = falseVal
    End If
End Function

'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
Sub PrintLine(value)
    WScript.StdOut.WriteLine value
    'Debug.Print value
End Sub

'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
Function DateFormat(d)
    DateFormat = Right("0" & Day(d), 2) & "." & _
        Right("0" & Month(d), 2) & "." & _
        Year(d)
End Function

'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
Function TimeFormat(t)
    TimeFormat = Right("0" & Hour(t), 2) & ":" & _
        Right("0" & Minute(t), 2) & ":" & _
        Right("0" & second(t), 2)
End Function

'==============================================================================

