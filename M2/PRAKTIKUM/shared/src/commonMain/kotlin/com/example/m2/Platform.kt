package com.example.m2

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform