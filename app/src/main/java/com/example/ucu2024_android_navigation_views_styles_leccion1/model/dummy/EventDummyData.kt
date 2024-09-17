package com.example.ucu2024_android_navigation_views_styles_leccion1.model.dummy

import com.example.ucu2024_android_navigation_views_styles_leccion1.R
import com.example.ucu2024_android_navigation_views_styles_leccion1.model.Event
import com.example.ucu2024_android_navigation_views_styles_leccion1.model.Profile

fun generateEventDummyData(profiles: List<Profile> = generateProfileDummyData()): List<Event> {
    return listOf(
        Event(
            "Immersive Tactical Drill",
            "The HIA Career Club VR Challenge just got a new update: A special limited-time challenge is now open to all players!",
            R.drawable.event_02,
            150,
            "• During the event, Proxies can head to the HIA Club and talk to Lyla to participate in an all-new VR Challenge.\n• During the event, a new themed stage will become available each day. After completing the first 6 themed stages, Advanced Drills will be unlocked.\n• In these event stages, achieving certain Trigger Requirements will trigger limited-duration Combat Buffs. In Advanced Drills, Proxies can freely mix and match Trigger Requirements and Combat Buffs to fight!\n• Proxies can choose to use the Trial Agents and Bangboo provided in the stage for the challenge.\n• Complete the event stages to obtain [Tuning Calibrators], [Polychromes], and other rewards.",
            profiles,
        ),
        Event(
            "Scene One, Shot One!",
            "Tell the tale of New Eridu through the camera in your hand. \"Lights! Camera! And.. Action!\"",
            R.drawable.event_03,
            80,
            "",
            profiles,
        ),
        Event(
            "\"En-Nah\" Into Your Lap",
            "Log in for seven days to obtain Boopon x10! \"En-nah uhn-ne! (We're comming!)\"",
            R.drawable.event_01,
            115,
            "",
            profiles,
        ),
    )
}