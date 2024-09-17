package com.example.ucu2024_android_navigation_views_styles_leccion1.model.dummy

import com.example.ucu2024_android_navigation_views_styles_leccion1.R
import com.example.ucu2024_android_navigation_views_styles_leccion1.model.Profile

fun generateProfileDummyData(): MutableList<Profile> {
    return mutableListOf(
        Profile("Billy", "Kid", "billyk@test.com", R.drawable.card_billy_kid_zzz, R.drawable.avatar_koleda, 19, "New Eridu", "Agent", "Cunning Hares"),
        Profile("Anby", "Demara", "anbych@test.com", R.drawable.avatar_koleda, R.drawable.avatar_anby, 21, "New Eridu", "Agent", "Cunning Hares"),
        Profile("Koleda", "Belobog", "belko@test.com", R.drawable.avatar_koleda, R.drawable.avatar_koleda, 24, "New Eridu", "Belobog's President", "Belobog Heavy Industries")

    )
}