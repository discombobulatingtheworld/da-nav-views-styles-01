package com.example.ucu2024_android_navigation_views_styles_leccion1.ui.views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Badge
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ucu2024_android_navigation_views_styles_leccion1.R
import com.example.ucu2024_android_navigation_views_styles_leccion1.model.Event
import com.example.ucu2024_android_navigation_views_styles_leccion1.model.Profile
import com.example.ucu2024_android_navigation_views_styles_leccion1.model.dummy.generateEventDummyData
import com.example.ucu2024_android_navigation_views_styles_leccion1.model.dummy.generateProfileDummyData
import com.example.ucu2024_android_navigation_views_styles_leccion1.state.ActivityState
import com.example.ucu2024_android_navigation_views_styles_leccion1.ui.components.BackButtonThemed
import com.example.ucu2024_android_navigation_views_styles_leccion1.ui.components.MaxSizedColumn
import com.example.ucu2024_android_navigation_views_styles_leccion1.ui.components.OutlineSubTitleText
import com.example.ucu2024_android_navigation_views_styles_leccion1.ui.components.OutlinedIconButtonThemed
import com.example.ucu2024_android_navigation_views_styles_leccion1.ui.components.TextButtonThemed
import com.example.ucu2024_android_navigation_views_styles_leccion1.ui.components.OutlinedTitleText
import com.example.ucu2024_android_navigation_views_styles_leccion1.ui.components.StackableIconsRow
import com.example.ucu2024_android_navigation_views_styles_leccion1.ui.components.StackableIconsRowCounter
import com.example.ucu2024_android_navigation_views_styles_leccion1.ui.components.StackableIconsRowItem
import com.example.ucu2024_android_navigation_views_styles_leccion1.ui.theme.AppTheme


@Composable
fun HomeView(userMail: String, activityState: ActivityState = ActivityState(), onDetailsNavigation: (String) -> Unit = {}, onLogoutNavigation: () -> Unit = {}) {
    val eventIdx = 2
    val currentEvent =
        activityState.ongoingEvents!!.value[eventIdx]

    Scaffold { innerPadding ->
        MaxSizedColumn(
            modifier = Modifier.padding(innerPadding),
        ) {
            Row(
                modifier = Modifier.fillMaxHeight(.5f)
            ) {
                TopContainer(userMail, currentEvent, onDetailsNavigation, onLogoutNavigation)
            }
            Row(
                modifier = Modifier
                    .background(color = MaterialTheme.colorScheme.surface),
            ) {
                BottomContainer(currentEvent)
            }
        }
    }
}

@Composable
fun TopContainer(userMail: String, event: Event, onDetailsNavigation: (String) -> Unit = {}, onLogoutNavigation: () -> Unit = {}) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = event.image),
            contentDescription = "Event poster.",
            alignment = Alignment.Center,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        MaxSizedColumn {
            NavigationBarContainer(userMail, onDetailsNavigation, onLogoutNavigation)
            EventDisplayContainer(event)
        }
    }
}

@Composable
fun EventDisplayContainer(event: Event) {
    MaxSizedColumn(
        modifier = Modifier.padding(20.dp)
    ) {
        OutlinedTitleText(text = event.name)
        OutlineSubTitleText(text = event.description, modifier = Modifier
            .padding(vertical = 25.dp)
            .fillMaxWidth(0.6f))
    }
}

@Composable
fun NavigationBarContainer(userMail: String, onDetailsNavigation: (String) -> Unit = {}, onLogoutNavigation: () -> Unit = {}) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        //BackButtonThemed(R.drawable.baseline_logout_24, "Logout", onLogoutNavigation)
        FilledIconButton(
            onClick = onLogoutNavigation,
            shape = CircleShape,
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_logout_24),
                contentDescription = "Logout",
                modifier = Modifier.padding(10.dp),
            )
        }
        TextButton(onClick = { onDetailsNavigation(userMail) }) {
            Text(text = userMail)
        }
    }
}

@Composable
fun BottomContainer(event: Event) {
    MaxSizedColumn(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            EventFollowersContainer(event.followers)
            EventDetailsContainer(event.details)
        }
        Column(
            modifier = Modifier.padding(vertical = 20.dp)
        ) {
            EventActionsContainer()
        }
    }
}

@Composable
fun EventFollowersContainer(followers: List<Profile>) {
    Row {
        StackableIconsRow {
            followers.subList(0,2).forEach {
                StackableIconsRowItem(it.avatar, it.name)
            }
            StackableIconsRowCounter(followers.size)
        }
    }
}

@Composable
fun EventDetailsContainer(detailsText: String) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 20.dp)
            .verticalScroll(scrollState)
    ) {
        Text(
            text = " Event Details",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 10.dp, start = 10.dp)
        )
        Text(
            text = detailsText,
            textAlign = TextAlign.Justify,
        )
    }
}

@Composable
fun EventActionsContainer() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        val commonModifier = Modifier.height(IntrinsicSize.Min)
        OutlinedIconButtonThemed(
            iconResource = R.drawable.outline_bookmark_remove_24,
            "Remove bookmark",
            modifier = commonModifier)
        TextButtonThemed(
            text = "Follow the Event",
            modifier = commonModifier.weight(1f)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun HomeViewLightPreview() {
    val activityState = ActivityState(
        remember { mutableStateOf(generateProfileDummyData()) },
        remember { mutableStateOf(generateEventDummyData()) },
    )
    AppTheme(
        darkTheme = false,
    ) {
        HomeView(userMail = "billykid@test.com", activityState)
    }
}


@Preview(showBackground = true)
@Composable
fun HomeViewDarkPreview() {
    val activityState = ActivityState(
        remember { mutableStateOf(generateProfileDummyData()) },
        remember { mutableStateOf(generateEventDummyData()) },
    )
    AppTheme(
        darkTheme = true,
    ) {
        HomeView(userMail = "billykid@test.com", activityState)
    }
}