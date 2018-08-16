set curdir=%~dp0
cd /d %curdir%
echo "rebuild start"

;rd /S/Q ../module/base/(手动避免表情)build 相对路径有点问题，要查一查
rd /S/Q build

;gradle build 写需要的命令

;taskkill /F /IM java.exe