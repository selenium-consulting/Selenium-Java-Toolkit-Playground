@echo off
setlocal EnableDelayedExpansion
SETLOCAL ENABLEEXTENSIONS

set "getKeyMacro=powershell -noprofile "^
    while (-not (38,40,13,32,27,82,69).contains($x)) {^
        $x = $Host.UI.RawUI.ReadKey('NoEcho,IncludeKeyDown').VirtualKeyCode^
    }^
    if ($x -eq 32) {^
    'toggle'^
    }^
    if ($x -eq 69) {^
    'e'^
    }^
    if ($x -eq 82) {^
    'r'^
    }^
    if ($x -eq 13) {^
    'toggle'^
    }^
    if ($x -eq 27) {^
    'esc'^
    }^
    if ($x -eq 38) {^
    'up'^
    }^
    if ($x -eq 40) {^
    'down'^
    }^
""

set "numProp=0"
set "numFile=0"

for %%f in ("*data.properties") do (
   set /A numFile+=1
   set "optionFile!numFile!=0"
   set "optionFile!numFile!name=%%~f"
)
for %%a in (
"Execute on Local"
"Execute on Grid"
"Remote Grid URL"
            
"Dont close local running Browser on Test-End"
"Path to IE Driver"
"Path to Gecko Driver"
"Path to Chrome Driver"

"Chrome"
"Internet Explorer"
"Edge"
"Firefox"
"Safari"

"Neoload Capturing On"

"Target-ENV"

"Make no DIFF Screenshots, record new reverence Images"

	   ) do (
   set /A numProp+=1
   set "optionProp!numProp!=0"
   set "optionProp!numProp!name=%%~a"
)
set "titleProp[1]=Exec Environment Propertiew"
set "titleProp[4]=Local Execution Settings"
set "titleProp[8]=Exec Browser (Value=Version)"
set "titleProp[13]=Neoload Settings"
set "titleProp[14]=Target Environment Settings"
set "titleProp[15]=Screenshot Settings"
set "radioProp[0]=1,2"
set "optionPropWithInput=3,5,6,7,8,9,10,11,12,14"
set "maxOptionsProp=%numProp%"
set "maxOptionsFile=%numFile%"
set "selectedProp=1"
set "selectedFile=1"

goto selectFile

:selectPorperties
cls
echo [1;97mUse ^<esc^> to leave, ^<up^> and ^<down^> to select,^<e^> enter new Value, ^<d^> delete value , and ^<space^> or ^<enter^> to toggle[0m
FOR /L %%G IN (1,1,%maxOptionsProp%) DO (
    if DEFINED titleProp[%%G] (
       if !titleProp[%%G]! equ '' (
 	 echo. 
       ) else (
         echo. 
         echo [1;37m!titleProp[%%G]![0m
       )
    )
    set "display=[ ]"
    if !optionProp%%G! equ 1 set "display=[x]"
    (for %%n in (0 1) do ( 
      (for %%a in (!radioProp[%%n]!) do ( 
	if %%G equ %%a (
	  set "display=( )"
	  if !optionProp%%G! equ 1 set "display=(x)"
	)
      ))
    ))
    if %%G equ !selectedProp! (
      set "display=[1;37m^>!display![0m
    ) else (
      set "display= !display!
    )
    set "displayInput="
    if DEFINED optionProp%%GInput (set "displayInput=Value='!optionProp%%GInput!'")
    if !optionProp%%G! equ 1 (
       echo [97m!display! !optionProp%%Gname! !displayInput![0m
    ) else (
       echo [90m!display! !optionProp%%Gname! !displayInput![0m
    )
)
FOR /F "delims==" %%G IN ('%getKeyMacro%') DO set "key=%%G"
    if "%key%"=="up" set /a "selectedProp-=1"
    if "%key%"=="down" set /a "selectedProp+=1"
    if %selectedProp% lss 1 set /a "selectedProp=1"
    if %selectedProp% gtr %maxOptionsProp% set "selectedProp=%maxOptionsProp%"
    if "%key%"=="toggle" goto toggleProperties
    if "%key%"=="esc" goto OKProperties
    if "%key%"=="r" goto delValue
    if "%key%"=="e" goto setValue
    goto selectPorperties


:setValue
(for %%a in (!optionPropWithInput!) do ( 
   if %selectedProp% equ %%a (
     echo.
     echo.
     echo Value Change
     echo Current value = '!optionProp%selectedProp%Input!'
     set /p optionProp%selectedProp%Input="Enter Input or press Enter to keep value: "
   )
))
goto selectPorperties

:delValue
(for %%a in (!optionPropWithInput!) do ( 
   if %selectedProp% equ %%a (
     set optionProp%selectedProp%Input=
   )
))
goto selectPorperties


:toggleProperties
(for %%n in (0 1) do ( 
      (for %%a in (!radioProp[%%n]!) do ( 
	if %selectedProp% equ %%a (
	  (for %%a in (!radioProp[%%n]!) do (
            set /a "optionProp%%a=0"
	  ))
	)
      ))
    ))
set /a "optionProp%selectedProp%+=1"
set /a "optionProp%selectedProp%=!optionProp%selectedProp%!%%2"
goto selectPorperties


:OKProperties
FOR /L %%G IN (1,1,%maxOptionsProp%) DO (
if !optionProp%%G! equ 1 (
echo %%G selectedProp
)
)
pause
goto saveFile


:selectFile
cls
echo [1;97mUse ^<esc^> to leave, ^<up^> and ^<down^> to select, and ^<space^> or ^<enter^> to toggle[0m
FOR /L %%G IN (1,1,%maxOptionsFile%) DO (
    set "display= "
    if %%G equ !selectedFile! (
      set "display=[1;37m^>!display![0m
    ) else (
      set "display= !display!
    )
    if !option%%G! equ 1 (
       echo [97m!display! !optionFile%%Gname![0m
    ) else (
       echo [90m!display! !optionFile%%Gname![0m
    )
)
FOR /F "delims==" %%G IN ('%getKeyMacro%') DO set "key=%%G"
    if "%key%"=="up" set /a "selectedFile-=1"
    if "%key%"=="down" set /a "selectedFile+=1"
    if %selectedFile% lss 1 set "selectedFile=1"
    if %selectedFile% gtr %maxOptionsFile% set "selectedFile=%maxOptionsFile%"
    if "%key%"=="toggle" goto loadFile
    if "%key%"=="esc" goto end
    goto selectFile
goto selectFile

:loadFile
(FOR /f "usebackqdelims=" %%f IN (!optionFile%selectedFile%name!) DO (
  FOR /f "tokens=1,* delims==" %%a IN ("%%f") DO (
    if %%a equ env (
	if %%b equ local (
             set "optionProp1=1"
        )
	if %%b equ grid (
             set "optionProp2=1"
        )
    )
    if %%a equ #remote.grid.url (
	set "optionProp3=0"
	set "optionProp3Input=%%b"
    )
    if %%a equ remote.grid.url (
	set "optionProp3=1"
	set "optionProp3Input=%%b"
    )
    if %%a equ #webdriver.ie.driver if optionProp5 equ 0 (
	set "optionProp5=0"
	set "optionProp5Input=%%b"
    )
    if %%a equ webdriver.ie.driver (
	set "optionProp5=1"
	set "optionProp5Input=%%b"
    )
    if %%a equ #webdriver.gecko.driver if optionProp6 equ 0 (
	set "optionProp6=0"
	set "optionProp6Input=%%b"
    )
    if %%a equ webdriver.gecko.driver (
	set "optionProp6=1"
	set "optionProp6Input=%%b"
    )
    if %%a equ #webdriver.chrome.driver if optionProp7 equ 0 (
	set "optionProp7=0"
	set "optionProp7Input=%%b"
    )
    if %%a equ webdriver.chrome.driver (
	set "optionProp7=1"
	set "optionProp7Input=%%b"
    )
    if %%a equ captureModeNoDiff (
	set "optionProp15=1"
	set "optionProp15Input=%%b"
    )
    if %%a equ neoload (
	set "optionProp13=1"
	set "optionProp13Input=%%b"
    )
    if %%a equ dontCloseLocalBrowser (
	set "optionProp4=1"
	set "optionProp4Input=%%b"
    )
  )
))
goto selectPorperties

:saveFile
goto selectFile

:end 
pause

echo done
pause
(FOR /f "usebackqdelims=" %%f IN (data.properties) DO (
  echo %%f
  FOR /f "tokens=1,* delims==" %%a IN ("%%f") DO (
    if 1 equ 1 (
       rem echo %%f
    ) else (
       rem echo %%a=%%b   
    )

  )
))
 pause
exit /b
