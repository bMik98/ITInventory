'==========================================================================
'
' VBScript Source File -- Created with SAPIEN Technologies PrimalScript 2016
'
' NAME: 
'
' AUTHOR: админ , 
' DATE  : 02.12.2016
'
' COMMENT: 
'
'==========================================================================

WScript.StdOut.WriteLine "Hello2"
Call Caller("Callable")

Public Sub Caller(ByVal funcName)
    a = GetRef(funcName)
    a
End Sub

Public Function Callable()
    WScript.StdOut.WriteLine "Hello"
    Callable = "Hello"
End Function
