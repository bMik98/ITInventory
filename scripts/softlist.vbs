'=========================================================================='
' Title: softlist.vbs'
' Date: 05.10.2016'
' Author: Mikhail Bedritskiy (bmik9898@gmail.com)'
' Version: 1.20'
' Updated: 05.10.2016'
' Purpose: List installed software on a workstation'
'==========================================================================
Option Explicit

Const HKLM = &H80000002 'HKEY_LOCAL_MACHINE 
Const ROOT = "SOFTWARE\Microsoft\Windows\CurrentVersion\Uninstall\"
Dim arrEntryNames: arrEntryNames = Array("DisplayName", "DisplayVersion", "InstallDate", "Publisher")

Dim objAdSystemInfo: Set objAdSystemInfo = CreateObject("ADSystemInfo")
Dim objReg: Set objReg = GetObject("winmgmts:{impersonationLevel=impersonate}!\\.\root\default:StdRegProv")
Dim arrKeys: arrKeys = GetRegistryKeys(objReg, HKLM, ROOT) 
Dim rec: Set rec = NewSoftwareRecordDictionary(objAdSystemInfo, arrEntryNames)

Dim i, strKey, strEntryName
PrintJsonHeader
For i = 0 To UBound(arrKeys)
    strKey = arrKeys(i)
    If(Not EntryExists(objReg, HKLM, ROOT & strKey, "ParentKeyName")) Then
        For Each strEntryName In arrEntryNames
            rec(strEntryName) = GetEntry(objReg, HKLM, ROOT & strKey, strEntryName)
        Next
        rec("RegistryKey") = strKey 
        If IsValid(rec, arrEntryNames) Then
            WScript.StdOut.WriteLine ConvertSoftwareRecordToJson(rec)
            If (i <> UBound(arrKeys)) Then
                WScript.StdOut.WriteLine ","
            End If 
        End If 
    End If
Next 
PrintJsonFooter
WScript.Quit 

Sub PrintJsonHeader()
    WScript.StdOut.WriteLine "["
    WScript.StdOut.WriteBlankLines 1
End Sub 

Sub PrintJsonFooter()
    WScript.StdOut.WriteBlankLines 1  
    WScript.StdOut.WriteLine "]"
End Sub 

Function GetRegistryKeys(objReg, Branch, RootKey)
    Dim arrSubKeys
    objReg.EnumKey HKLM, ROOT, arrSubKeys 
    GetRegistryKeys = arrSubKeys
End Function 

Function IsValid(record, arrNames)
    Dim result: result = False
    Dim name
    For Each name In arrNames
        If Not IsNull(record(name)) Then 
            result = True
            Exit For
        End If 
        Next
    IsValid = result
End Function

Function EntryExists(objReg, HKLM, strKey, strStringEntry)
    Dim strValue
    Dim ret: ret = objReg.GetStringValue(HKLM, strKey, strStringEntry, strValue)
    If(ret = 0) Then 
        EntryExists = True
    Else
        EntryExists = False
    End If
End Function

Function GetEntry(objReg, HKLM, strKey, strStringEntry)
    Dim strValue: strValue = ""
    objReg.GetStringValue HKLM, strKey, strStringEntry, strValue
    GetEntry = strValue
End Function

Function NewSoftwareRecordDictionary(objAdSystemInfo, arrEntryNames)
    Dim dict: Set dict = CreateObject("Scripting.Dictionary")
    dict.CompareMode = 1
    Dim strName : strName = Mid(objAdSystemInfo.ComputerName, 4, InStr(objAdSystemInfo.ComputerName, ",") - 4)
    dict.Add "ReportDate", DateFormat(Date)
    dict.Add "ComputerID", LCase(strName & "." & objAdSystemInfo.DomainDNSName)
    dict.Add "ComputerName", strName
    dict.Add "ComputerDnsDomain", objAdSystemInfo.DomainDNSName
    dict.Add "ComputerDN", objAdSystemInfo.ComputerName
    dict.Add "Site", objAdSystemInfo.SiteName
    dict.Add "RegistryKey", ""
    Set NewSoftwareRecordDictionary = dict
    Set dict = Nothing
End Function

Function ConvertSoftwareRecordToJson(record) 
    Dim result: result = "{" + vbCrLf
    Dim keys: keys = record.Keys
    Dim i
    For i = 0 To UBound(keys)
        result = result & """" & keys(i) & """:""" & record(keys(i)) & """"
        If (i <> UBound(keys)) Then
            result = result + ","
        End If 
        result = result + vbCrLf 
    Next
    result = result + "}" 
    ConvertSoftwareRecordToJson = result
End Function

Function DateFormat(d)
    DateFormat = Right("0" & Day(d), 2) & "." & _
    	Right("0" & Month(d),2) & "." & _
     	Year(d)
End Function
'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
