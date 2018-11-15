#!/bin/bash
currPath='/home/ponaszki'
appPath="$currPath../target"
appName="nov-1.0-SNAPSHOT.jar"
configPath="$appPath/config/"
app="$appPath/$appName"
echo $app

#java -jar $app --spring.config.location=$configPath
echo "$configPath"