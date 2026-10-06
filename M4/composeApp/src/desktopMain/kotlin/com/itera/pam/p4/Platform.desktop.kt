package com.itera.pam.p4

actual fun getPlatformName(): String = "Desktop JVM ${System.getProperty("java.version")}"
