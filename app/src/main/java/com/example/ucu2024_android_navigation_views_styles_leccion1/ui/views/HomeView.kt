package com.example.ucu2024_android_navigation_views_styles_leccion1.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ucu2024_android_navigation_views_styles_leccion1.R
import com.example.ucu2024_android_navigation_views_styles_leccion1.model.Event
import com.example.ucu2024_android_navigation_views_styles_leccion1.model.Profile
import com.example.ucu2024_android_navigation_views_styles_leccion1.model.dummy.generateEventDummyData
import com.example.ucu2024_android_navigation_views_styles_leccion1.model.dummy.generateProfileDummyData
import com.example.ucu2024_android_navigation_views_styles_leccion1.state.ActivityState
import com.example.ucu2024_android_navigation_views_styles_leccion1.ui.components.AlertDialogThemed
import com.example.ucu2024_android_navigation_views_styles_leccion1.ui.components.MaxSizedColumn
import com.example.ucu2024_android_navigation_views_styles_leccion1.ui.components.OutlineSubTitleText
import com.example.ucu2024_android_navigation_views_styles_leccion1.ui.components.OutlinedIconButtonThemed
import com.example.ucu2024_android_navigation_views_styles_leccion1.ui.components.TextButtonThemed
import com.example.ucu2024_android_navigation_views_styles_leccion1.ui.components.OutlinedTitleText
import com.example.ucu2024_android_navigation_views_styles_leccion1.ui.components.StackableIconsRow
import com.example.ucu2024_android_navigation_views_styles_leccion1.ui.components.StackableIconsRowCounter
import com.example.ucu2024_android_navigation_views_styles_leccion1.ui.components.StackableIconsRowItem
import com.example.ucu2024_android_navigation_views_styles_leccion1.ui.components.TopNavButton
import com.example.ucu2024_android_navigation_views_styles_leccion1.ui.components.UserPlaque
import com.example.ucu2024_android_navigation_views_styles_leccion1.ui.theme.AppTheme


@Composable
fun HomeView(userMail: String, activityState: ActivityState = ActivityState(), onDetailsNavigation: (String) -> Unit = {}, onLogoutNavigation: () -> Unit = {}) {
    val eventIdx = 1
    val currentEvent =
        activityState.ongoingEvents!!.value[eventIdx]
    val currentProfile = activityState.availableProfiles!!.value.firstOrNull { it.email == userMail } ?: activityState.availableProfiles.value.first().copy(email = userMail)
    val isLogoutDialogOpen = remember { mutableStateOf(false) }
    val onRequestLogout: () -> Unit = {
        isLogoutDialogOpen.value = true
    }
    val onDismissLogoutDialog: () -> Unit = {
        isLogoutDialogOpen.value = false
    }
    val onConfirmLogout: () -> Unit = {
        isLogoutDialogOpen.value = false
        onLogoutNavigation()
    }

    if (isLogoutDialogOpen.value) {
        LogoutDialog(onConfirmLogout, onDismissLogoutDialog)
    }

    Scaffold { innerPadding ->
        MaxSizedColumn(
            modifier = Modifier.padding(innerPadding),
        ) {
            Row(
                modifier = Modifier.fillMaxHeight(.5f)
            ) {
                TopContainer(currentProfile, currentEvent, onDetailsNavigation, onRequestLogout)
            }
            Row(
                modifier = Modifier
                    .background(color = MaterialTheme.colorScheme.surfaceContainerHigh),
            ) {
                BottomContainer(currentEvent)
            }
        }
    }
}

@Composable
fun TopContainer(profile: Profile, event: Event, onDetailsNavigation: (String) -> Unit = {}, onRequestLogout: () -> Unit = {}) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = event.image),
            contentDescription = "Event poster.",
            alignment = Alignment.Center,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
            colorFilter = ColorFilter.tint(
                color = MaterialTheme.colorScheme.surfaceVariant.copy(0.3f),
                BlendMode.Multiply
            )
        )
        MaxSizedColumn {
            NavigationBarContainer(profile, onDetailsNavigation, onRequestLogout)
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
fun NavigationBarContainer(profile: Profile, onDetailsNavigation: (String) -> Unit = {}, onRequestLogout: () -> Unit = {}) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        TopNavButton(R.drawable.baseline_logout_24, "Logout", onClick = onRequestLogout)
        UserPlaque(profile, onClick =  { onDetailsNavigation(profile.email) })
    }
}

@Composable
fun BottomContainer(event: Event) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f, false)
        ) {
        Column {
            EventFollowersContainer(event.followers)
            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f, false)
            ) {
                val remainingHeight = this.maxHeight
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = remainingHeight)
                        .clipToBounds()
                ) {
                    EventDetailsContainer(event.description)
                }
            }
            EventStreetViewContainer(event.streetView)
        }
            }
        EventActionsContainer()
    }
}

@Composable
fun EventFollowersContainer(followers: List<Profile>) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(bottom = 10.dp)
    ) {
        StackableIconsRow {
            followers.subList(0,2).forEach {
                StackableIconsRowItem(it.avatar, it.name)
            }
            StackableIconsRowCounter(followers.size)
        }
        Text(text = "${followers.size} agents are following this event.", style = MaterialTheme.typography.labelLarge)
    }
}

@Composable
fun EventDetailsContainer(detailsText: String) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .padding(vertical = 10.dp)
            .verticalScroll(scrollState)
    ) {
        Text(
            text = detailsText,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Justify,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Composable
fun EventStreetViewContainer(resourceId: Int) {
    Image(
        painter = painterResource(id = resourceId),
        contentDescription = "Street View",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(vertical = 15.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .requiredHeight(100.dp)
    )

}

@Composable
fun EventActionsContainer() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
    ) {
        val commonModifier = Modifier.height(IntrinsicSize.Min)
        OutlinedIconButtonThemed(
            iconResource = R.drawable.outline_bookmark_remove_24,
            "Remove bookmark",
            enabled = false,
            modifier = commonModifier
        )
        TextButtonThemed(
            text = "Follow the Event",
            modifier = commonModifier
                .weight(1f)
                .fillMaxWidth()
        )
    }
}

@Composable
fun LogoutDialog(onConfirmLogout: () -> Unit = {}, onDismissLogoutDialog: () -> Unit = {}) {
    AlertDialogThemed(
        "Cerrar Sesión",
        "Esta a punto de cerrar sesión y volver a la pantalla de inicio. ¿Desea continuar?",
        "Si, cerrar sesión",
        onConfirmLogout,
        "Cancelar",
        onDismissLogoutDialog
    )
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


@Preview(showBackground = true)
@Composable
fun LogoutDialogLightPreview() {
    AppTheme(
        darkTheme = false,
    ) {
        LogoutDialog()
    }
}


@Preview(showBackground = true)
@Composable
fun LogoutDialogDarkPreview() {
    AppTheme(
        darkTheme = true,
    ) {
        LogoutDialog()
    }
}
