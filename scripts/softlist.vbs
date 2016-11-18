'==============================================================================
' Title: softlist.vbs'
' Date: 05.10.2016'
' Author: Mikhail Bedritskiy (bmik9898@gmail.com)'
' Version: 1.30'
' Updated: 16.11.2016'
' Purpose: List installed software on a workstation'
'==============================================================================
Option Explicit

InstalledSoftwareInfo
WScript.Quit

Sub InstalledSoftwareInfo()
    Dim dictInfo: Set dictInfo = RetriveInstalledSoftwareInfo()
    Dim strReport: strReport = ConvertDictionaryArrayToJson(dictInfo.items)
    PrintLine strReport
End Sub

'==============================================================================
' Collect the information
'
Function RetriveInstalledSoftwareInfo()
    Dim objAdSystemInfo: Set objAdSystemInfo = CreateObject("ADSystemInfo")
    Dim objReg: Set objReg = GetObject( _
        "winmgmts:{impersonationLevel=impersonate}!\\.\root\default:StdRegProv")
    Set RetriveInstalledSoftwareInfo = GetInstalledSoftwareDictionaryArray( _
        objReg, objAdSystemInfo)
End Function


'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
Function GetInstalledSoftwareDictionaryArray(objReg, objAdSystemInfo)
    Const HKLM = &H80000002 'HKEY_LOCAL_MACHINE
    Const ROOT = "SOFTWARE\Microsoft\Windows\CurrentVersion\Uninstall\"
    Dim result: Set result = CreateObject("Scripting.Dictionary")
    Dim entry, i
    Dim arrKeys: arrKeys = EnumRegistryKeys(objReg, HKLM, ROOT)
    For i = 0 To UBound(arrKeys)
        Set entry = NewSoftwareRecordDictionary(objAdSystemInfo)
        entry("title") = GetRegistryStringEntry(objReg, HKLM, ROOT & arrKeys(i), "DisplayName")
        entry("version") = GetRegistryStringEntry(objReg, HKLM, ROOT & arrKeys(i), "DisplayVersion")
        entry("installed") = GetRegistryStringEntry(objReg, HKLM, ROOT & arrKeys(i), "InstallDate")
        entry("publisher") = GetRegistryStringEntry(objReg, HKLM, ROOT & arrKeys(i), "Publisher")
        entry("registryKey") = arrKeys(i)
        If (entry("title") <> "") And Not IsUpdate(objReg, HKLM, ROOT & arrKeys(i)) Then
            result.Add i, entry
        End If
        Set entry = Nothing
    Next
    Set GetInstalledSoftwareDictionaryArray = result
End Function


'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
Function IsUpdate(objReg, HKLM, strKey)
    Dim parent: parent = GetRegistryStringEntry( _
        objReg, HKLM, strKey, "ParentKeyName")
    IsUpdate = Iff((parent = ""), False, True)
End Function

'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
Function GetRegistryStringEntry(objReg, HKLM, strKey, entryName)
    Dim strValue
    Dim strResult
    Dim ret: ret = objReg.GetStringValue(HKLM, strKey, entryName, strValue)
    If (ret <> 0) Then
        strResult = ""
    Else
        strResult = Iff(IsNull(strValue), "", strValue)
    End If
    GetRegistryStringEntry = strResult
End Function

'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
Function EnumRegistryKeys(objReg, HK, RootKey)
    Dim arrSubKeys
    objReg.EnumKey HK, RootKey, arrSubKeys
    EnumRegistryKeys = arrSubKeys
End Function

'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
Function NewSoftwareRecordDictionary(objAdSystemInfo)
    Dim dict: Set dict = CreateObject("Scripting.Dictionary")
    dict.CompareMode = 1
    dict.Add "reportDate", DateFormat(Date)
    dict.Add "computerId", GetComputerFQDN(objAdSystemInfo)
    Set NewSoftwareRecordDictionary = dict
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
'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''

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
    Dim refinedValue
    refinedValue = Replace(value, "\", "\\")
    refinedValue = Replace(refinedValue, """", "\""")
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
