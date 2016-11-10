'=========================================================================='
' Title: printerlist.vbs' 
' Date: 10.11.2016' 
' Author: Mikhail Bedritskiy (bmik9898@gmail.com)' 
' Version: 1.10' 
' Updated: ' 
' Purpose: List all printers attached to a workstation' 
'=========================================================================='
Option Explicit

LogPrinters
WScript.Quit

Sub LogPrinters()
    Dim objAdSystemInfo: Set objAdSystemInfo = CreateObject("ADSystemInfo")
    Dim wmi: Set wmi = GetObject("winmgmts:{impersonationLevel=impersonate}!\\.\root\cimv2")
    Dim printers: Set printers = wmi.ExecQuery("Select * from Win32_Printer")
    PrintDictionaryReport GetPrinterReport(objAdSystemInfo, printers)
End Sub

'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
' Gather the collection of required object
'
Function GetPrinterReport(ByRef objAdSystemInfo, ByRef printers)
    Dim report: Set report = CreateObject("Scripting.Dictionary")
    Dim printer, record, i
    i = 1
    For Each printer In printers
        Set record = NewRecordDictionary(objAdSystemInfo)
        UpdateRecordData record, printer
        report.Add i, record
        i = i + 1
        Set record = Nothing
    Next
    Set GetPrinterReport = report
    Set report = Nothing
End Function

Function NewRecordDictionary(ByRef objAdSystemInfo)
    Dim dict: Set dict = CreateObject("Scripting.Dictionary")
    dict.CompareMode = 1
    Dim strName : strName = Mid(objAdSystemInfo.ComputerName, 4, InStr(objAdSystemInfo.ComputerName, ",") - 4)
    dict.Add "ReportDate", DateFormat(Date)
    dict.Add "ComputerID", LCase(strName & "." & objAdSystemInfo.DomainDNSName)
    dict.Add "ComputerName", strName
    dict.Add "ComputerDnsDomain", objAdSystemInfo.DomainDNSName
    dict.Add "ComputerDN", objAdSystemInfo.ComputerName
    dict.Add "Site", objAdSystemInfo.SiteName
    Set NewRecordDictionary = dict
    Set dict = Nothing
End Function

Sub UpdateRecordData(ByRef dict, ByRef obj)
    dict.Add "Name", obj.name
    dict.Add "Caption", obj.Caption
    dict.Add "DriverName", obj.DriverName
    dict.Add "PortName", obj.PortName
    If (obj.Default) Then
        dict.Add "Default", "Yes"
    Else
        dict.Add "Default", "No"
    End If
    dict.Add "ServerName", Mid(obj.ServerName, 3)
    dict.Add "Comment", obj.Comment
    dict.Add "Location", obj.Location
End Sub
'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''

'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
' Report
'
Sub PrintDictionaryReport(ByRef dict)
    PrintLine "["
    PrintLine ""
    Dim i
    For i = 1 To dict.Count
        PrintLine ConvertDictionaryToString(dict.Item(i)) & Iff(i <> dict.Count, vbCrLf & ",", "")
    Next
    PrintLine ""
    PrintLine "]"
End Sub

Function ConvertDictionaryToString(ByRef dict)
    Dim keys: keys = dict.keys
    Dim i, result
    result = "{" & vbCrLf
    For i = 0 To UBound(keys)
        result = result & """" & keys(i) & """:"""
        If Not IsNull(dict(keys(i))) Then
            result = result & Replace(dict(keys(i)), "\", "\\")
        End If
        result = result & """" & Iff(i <> UBound(keys), ",", "") & vbCrLf
    Next
    ConvertDictionaryToString = result & "}"
End Function

Function Iff(blnFirstSelected, first, second)
    If blnFirstSelected Then
        Iff = first
    Else
        Iff = second
    End If
End Function

Sub PrintLine(value)
    WScript.StdOut.WriteLine value
End Sub

Function DateFormat(d)
    DateFormat = Right("0" & Day(d), 2) & "." & _
    	Right("0" & Month(d),2) & "." & _
     	Year(d)
End Function
'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
