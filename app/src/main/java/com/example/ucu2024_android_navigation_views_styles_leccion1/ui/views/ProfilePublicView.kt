package com.example.ucu2024_android_navigation_views_styles_leccion1.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ucu2024_android_navigation_views_styles_leccion1.R
import com.example.ucu2024_android_navigation_views_styles_leccion1.model.Profile
import com.example.ucu2024_android_navigation_views_styles_leccion1.model.dummy.generateProfileDummyData
import com.example.ucu2024_android_navigation_views_styles_leccion1.state.ActivityState
import com.example.ucu2024_android_navigation_views_styles_leccion1.ui.components.CardSectionContainerThemed
import com.example.ucu2024_android_navigation_views_styles_leccion1.ui.components.ImageBackgroundBox
import com.example.ucu2024_android_navigation_views_styles_leccion1.ui.components.MaxSizedColumn
import com.example.ucu2024_android_navigation_views_styles_leccion1.ui.components.TopNavButton
import com.example.ucu2024_android_navigation_views_styles_leccion1.ui.components.UserPlaque
import com.example.ucu2024_android_navigation_views_styles_leccion1.ui.theme.AppTheme


@Composable
fun ProfilePublicView(userMail: String?, activityState: ActivityState = ActivityState(), onBackNavigation: () -> Unit = {}) {
    val currentProfile =
        (activityState.availableProfiles!!.value.firstOrNull { it.email == userMail }
        ?: activityState.availableProfiles.value.first()).copy(email = userMail!!)

    Scaffold { innerPadding ->
        ImageBackgroundBox(
            imageResource = R.drawable.background_tile_04b,
            modifier = Modifier.padding(innerPadding),
        ) {
            MaxSizedColumn {
                NavigationBarContainer(currentProfile, onBackNavigation)
                Box {
                    CharDisplay(currentProfile.image)
                    MaxSizedColumn(
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        Row(modifier = Modifier
                            .fillMaxHeight(0.55f)
                            .fillMaxWidth()
                            //.padding(20.dp)
                        ) {
                            CharInfoContainer(currentProfile)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun NavigationBarContainer(profile: Profile, onBackNavigation: () -> Unit = {}) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        TopNavButton(R.drawable.baseline_arrow_back_24, "Logout", onClick = onBackNavigation)
        UserPlaque(profile)
    }
}

@Composable
fun CharDisplay(imageResource: Int) {
    Image(
        painter = painterResource(id = imageResource),
        contentDescription = "Full profile picture.",
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 40.dp)
    )
}

@Composable
fun CharInfoContainer(profile: Profile) {
    Row(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surfaceContainerHigh)
            .fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp)
        ) {

            CharNamePlate("${profile.name} ${profile.lastName}", profile.agency)

            CharDatumRow("Age", "${profile.age}")


            //CharDatumPlate("Age", "${profile.age}")
            CharDatumRow("Profession", profile.profession)
            CharDatumRow("Country", profile.countryOfBirth)
            CharDatumRow("Email", profile.email)
        }
    }
}

@Composable
fun CharDatumRow(label: String, value: String) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 5.dp)
    ) {
        Text(text = label,
                style = MaterialTheme.typography.bodyMedium)
        Text(text = value,
            style = MaterialTheme.typography.bodyMedium)
    }
}

@Composable
fun CharNamePlate(profileName: String, agency: String) {
    CardSectionContainerThemed(
        modifier = Modifier
            .padding(horizontal = 5.dp, vertical = 5.dp)
            .fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 15.dp)
                .fillMaxWidth(),
        ) {
            Text(text = profileName, style = MaterialTheme.typography.titleMedium)
            Text(text = agency, style = MaterialTheme.typography.bodyMedium)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ProfilePublicViewLightPreview() {
    val activityState = ActivityState(remember { mutableStateOf(generateProfileDummyData()) })
    AppTheme(darkTheme = false) {
        ProfilePublicView("anbych@test.com", activityState)
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePublicViewDarkPreview() {
    val activityState = ActivityState(remember { mutableStateOf(generateProfileDummyData()) })
    AppTheme(darkTheme = true) {
        ProfilePublicView("belko@test.com", activityState)
    }
}