package com.example.ucu2024_android_navigation_views_styles_leccion1.state

import androidx.compose.runtime.MutableState
import com.example.ucu2024_android_navigation_views_styles_leccion1.model.Event
import com.example.ucu2024_android_navigation_views_styles_leccion1.model.Profile

data class ActivityState(
    val availableProfiles: MutableState<List<Profile>>? = null,
    val ongoingEvents: MutableState<List<Event>>? = null,
)
