@echo off
echo package com.ts.main.common;>MainVerSion.java
echo public class MainVerSion {>>MainVerSion.java

set "Ymd=public static  String HMIVER = ".%date:~0,4%.%date:~5,2%.%date:~8,2%.%time:~0,2%.%time:~3,2%";                    //HMI版本"
echo %Ymd%>>MainVerSion.java
echo public static String ROM_VERSION = null;                                   //伺服软件版本>>MainVerSion.java
echo public static String MMP_VERSION = null;					//媒体软件版本>>MainVerSion.java	
echo }>>MainVerSion.java