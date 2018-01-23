@echo off
call mvn package
cp target/MoreRelics.jar ../_ModTheSpire/mods
pause