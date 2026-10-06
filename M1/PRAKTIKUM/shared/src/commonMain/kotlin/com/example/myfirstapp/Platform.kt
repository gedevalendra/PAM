package com.example.myfirstapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform