#!/bin/bash
currPath="/home/ponaszki/nov"
echo "$currPath"
appPath="$currPath"
appName="nov-1.0-SNAPSHOT.jar"
configPath="$appPath/config/"
app="$appPath/$appName"
echo $app
nohup java -jar $app --spring.config.location=$configPath &




