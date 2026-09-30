package com.example.m3

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform