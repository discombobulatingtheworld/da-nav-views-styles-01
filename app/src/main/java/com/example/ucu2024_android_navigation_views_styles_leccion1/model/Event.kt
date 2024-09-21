package com.example.ucu2024_android_navigation_views_styles_leccion1.model

data class Event(
    val name: String,
    val description: String,
    val image: Int,
    val remainingHours: Int,
    val details: String,
    val followers: List<Profile>,
    val streetView: Int,
)
