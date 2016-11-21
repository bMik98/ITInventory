'==============================================================================
' Title: printerlist.vbs
' Date: 10.11.2016
' Author: Mikhail Bedritskiy (bmik9898@gmail.com)
' Version: 1.20
' Updated: 21.11.2016
' Purpose: List all printers attached to a workstation
'==============================================================================
Option Explicit

ConnectedPrintersInfo
WScript.Quit

Public Sub ConnectedPrintersInfo()
    Dim objAdSystemInfo: Set objAdSystemInfo = CreateObject("ADSystemInfo")
    Dim wmi: Set wmi = GetObject("winmgmts:{impersonationLevel=impersonate}!\\.\root\cimv2")
    Dim printers: Set printers = wmi.ExecQuery("Select * from Win32_Printer")
    Dim dictInfo: Set dictInfo = RetriveConnectedPrintersInfo(objAdSystemInfo, printers)
    Dim strReport: strReport = ConvertDictionaryArrayToJson(dictInfo.items)
    PrintLine strReport
End Sub

'==============================================================================
' Gathering the information
'
Function RetriveConnectedPrintersInfo(ByRef objAdSystemInfo, ByRef printers)
    Dim report: Set report = CreateObject("Scripting.Dictionary")
    Dim printer, record, i
    i = 1
    For Each printer In printers
        Set record = CreatePrinterInfo(objAdSystemInfo, printer)
        report.Add i, record
        i = i + 1
        Set record = Nothing
    Next
    Set RetriveConnectedPrintersInfo = report
    Set report = Nothing
End Function

'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
Function CreatePrinterInfo(ByRef objAdSystemInfo, ByRef printer)
    Dim result: Set result = NewConnectedPrinterRecord(objAdSystemInfo)
    result.Add "name", printer.name
    result.Add "caption", printer.Caption
    result.Add "driverName", printer.DriverName
    result.Add "portName", printer.PortName
    If (printer.Default) Then
        result.Add "asDefault", "true"
    Else
        result.Add "asDefault", "false"
    End If
    result.Add "serverName", Mid(printer.ServerName, 3)
    Set CreatePrinterInfo = result
End Function

'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
Function NewConnectedPrinterRecord(objAdSystemInfo)
    Dim dict: Set dict = CreateObject("Scripting.Dictionary")
    dict.CompareMode = 1
    dict.Add "reportDate", DateFormat(Date)
    dict.Add "computerId", GetComputerFQDN(objAdSystemInfo)
    Set NewConnectedPrinterRecord = dict
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
Function ConvertDictionaryArrayToJson(arrayOfDicts)
    Dim result: result = "[" & vbCrLf & vbCrLf
    Dim i
    For i = 0 To UBound(arrayOfDicts)
        result = result & ConvertDictionaryToJson(arrayOfDicts(i))
        result = result & Iff((i = UBound(arrayOfDicts)), "", ",") & vbCrLf
    Next
    result = result & vbCrLf & "]"
    ConvertDictionaryArrayToJson = result
End Function

'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
Function ConvertDictionaryToJson(dict)
'    Dim result: result = "{" + vbCrLf
    Dim result: result = "{"
    Dim keys: keys = dict.keys
    Dim i
    For i = 0 To UBound(keys)
        result = result & ConvertPairToJson(keys(i), dict(keys(i)))
'        result = result & Iff((i = UBound(keys)), "", ",") & vbCrLf
        result = result & Iff((i = UBound(keys)), "", ", ")
    Next
    result = result + "}"
    ConvertDictionaryToJson = result
End Function

'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
Function ConvertPairToJson(ByVal key, ByVal value)
    Dim refinedValue: refinedValue = ""
    If Not IsNull(value) Then
        refinedValue = Replace(value, "\", "\\")
        refinedValue = Replace(refinedValue, """", "\""")
    End If
    ConvertPairToJson = """" & key & """:""" & refinedValue & """"
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
'    Debug.Print value
End Sub

'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
Function ConvertDateFormat(yyyymmdd)
    ConvertDateFormat = Iff((yyyymmdd = ""), _
        "", _
        Right(yyyymmdd, 2) & "." & _
        Mid(yyyymmdd, 5, 2) & "." & _
        Left(yyyymmdd, 4))
End Function

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

