package com.example.ucu2024_android_navigation_views_styles_leccion1.ui.views.home

import android.graphics.Paint.Align
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Badge
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ucu2024_android_navigation_views_styles_leccion1.R
import com.example.ucu2024_android_navigation_views_styles_leccion1.ui.theme.AppTheme


@Composable
fun HomeView(userMail: String?, onDetailsNavigation: (String) -> Unit = {}, onLogoutNavigation: () -> Unit = {}) {
    Scaffold { innerPadding ->
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize(1f)
                .padding(innerPadding),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1.2f)
                    .background(MaterialTheme.colorScheme.secondaryContainer)
            ) {
                //ProfileContainer(modifier, navController, userMail)
            }
            Row(
                modifier = Modifier
                    .fillMaxSize(1f)
                    .weight(1f)
                    .background(
                        MaterialTheme.colorScheme.surface
                    )
            ) {
                //DetailsContainer(modifier, navController)
            }
        }
    }
}



@Composable
fun HomeView2(modifier: Modifier, navController: NavController, userMail: String?) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize(1f),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1.2f)
                .background(MaterialTheme.colorScheme.secondaryContainer)
        ) {
            ProfileContainer(modifier, navController, userMail)
        }
        Row(
            modifier = Modifier
                .fillMaxSize(1f)
                .weight(1f)
                .background(
                    MaterialTheme.colorScheme.surface
                )
        ) {
            DetailsContainer(modifier, navController)
        }
    }
}

@Composable
private fun ProfileContainer(modifier: Modifier, navController: NavController, userMail: String?) {
    Column(
        modifier = modifier
            .fillMaxWidth(1f)
            .fillMaxHeight(1f),
    ) {
        NavigationBarContainer(modifier, navController)
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = modifier.fillMaxHeight(1f)
        ) {
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = modifier
                    .fillMaxHeight(1f)
                    .weight(0.65f)
                    .padding(30.dp),
            ) {
                Column {
                    Text(text = "Billy Kid", style = MaterialTheme.typography.headlineMedium, color = MaterialTheme.colorScheme.onPrimaryContainer, )
                    Text(text = "$userMail", style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.onPrimaryContainer,)

                }
                Column {
                    Text(text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", style = MaterialTheme.typography.headlineSmall, color = MaterialTheme.colorScheme.onPrimaryContainer,)

                }
                Row {
                    Icon(painter = painterResource(id = R.drawable.baseline_location_on_24), contentDescription = "Location", tint = MaterialTheme.colorScheme.onPrimaryContainer)
                    Text(text = "New Eridu", style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.onPrimaryContainer)
                }
            }
            Column(
                verticalArrangement = Arrangement.Bottom,
                modifier = modifier
                    .weight(0.45f)
                    .fillMaxHeight(1f),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.card_billy_kid_zzz),
                    contentDescription = "Profile Card",
                    alignment = Alignment.TopStart,
                    modifier = modifier
                        .weight(1f)
                        .graphicsLayer(
                            transformOrigin = TransformOrigin(0f, 0f),
                            scaleX = 1.8f,
                            scaleY = 1.8f,
                            translationX = -100f,
                        )
                )
            }
        }
    }
}

@Composable
private fun NavigationBarContainer(modifier: Modifier, navController: NavController) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth(1f)
    ) {
        IconButton(onClick = { /*TODO*/ }, modifier = modifier
            .padding(20.dp)
        ) {
            Icon(painter = painterResource(id = R.drawable.baseline_arrow_back_24), contentDescription = "Return", tint = MaterialTheme.colorScheme.onPrimaryContainer,)
        }
        IconButton(onClick = { /*TODO*/ }, modifier = Modifier
            .padding(20.dp)
        ) {
            Icon(painter = painterResource(id = R.drawable.baseline_more_vert_24), contentDescription = "More", tint = MaterialTheme.colorScheme.onPrimaryContainer,)
        }
    }
}

@Composable
private fun DetailsContainer(modifier: Modifier, navController: NavController) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(15.dp)
    ) {
        Column {
            Contacts(modifier)
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(15.dp)
            ) {
                Text(text = "Proin purus sapien, accumsan eu purus vel, mollis feugiat est. Quisque erat leo, rutrum non nisi in, ultricies interdum tortor. Nulla a porttitor elit. Ut vehicula aliquam nulla non suscipit.")
            }
        }
        ButtonsBar(modifier)
    }

}

@Composable
private fun Contacts(modifier: Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy((-15).dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier.padding(end = 20.dp)
        ) {
            Contact(modifier = modifier, resource = R.drawable.avatar_koleda, description = "Koleda")
            Contact(modifier = modifier, resource = R.drawable.avatar_anby, description = "Anby")
            ContactCounter(modifier = modifier, count = 40)
        }
        Row {
            Text(text = "40 people follow this page", fontWeight = FontWeight.Bold)
        }
    }
}


@Composable
private fun Contact(modifier: Modifier, resource: Int, description: String) {
    Image(
        painter = painterResource(id = resource),
        contentDescription = description,
        modifier = modifier
            .border(BorderStroke(2.dp, MaterialTheme.colorScheme.surface), CircleShape)
            .clip(CircleShape)
            .size(45.dp)
    )

}

@Composable
private fun ContactCounter(modifier: Modifier, count: Int) {
    Badge(
        contentColor = MaterialTheme.colorScheme.inverseOnSurface,
        containerColor = MaterialTheme.colorScheme.inverseSurface,
        modifier = modifier
            .size(35.dp)
            .border(BorderStroke(2.dp, MaterialTheme.colorScheme.surface), CircleShape)
    ) {
        Text(text = "$count")
    }

}

@Composable
private fun ButtonsBar(modifier: Modifier) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        OutlinedIconButton(
            onClick = { /*TODO*/ },
            shape = RoundedCornerShape(8.dp),
            //enabled = false,
            colors = IconButtonColors(
                contentColor = MaterialTheme.colorScheme.primary,
                containerColor = MaterialTheme.colorScheme.surface,
                disabledContentColor = MaterialTheme.colorScheme.outlineVariant,
                disabledContainerColor = MaterialTheme.colorScheme.inverseOnSurface,
            ),
            modifier = modifier
                .height(50.dp)
                .width(50.dp)
        ) {
            Icon(painter = painterResource(id = R.drawable.outline_bookmark_remove_24), contentDescription = "Remove bookmark")
        }
        TextButton(
            onClick = { /*TODO*/ },
            shape = RoundedCornerShape(8.dp),
            //enabled = false,
            colors = ButtonColors(
                contentColor = MaterialTheme.colorScheme.onPrimary,
                containerColor = MaterialTheme.colorScheme.primary,
                disabledContentColor = MaterialTheme.colorScheme.inverseOnSurface,
                disabledContainerColor = MaterialTheme.colorScheme.outlineVariant,
            ),
            modifier = modifier.width(200.dp)
        ) {
            Text(
                text = "Join Now",
                style = MaterialTheme.typography.titleMedium,
                modifier = modifier.padding(5.dp)

            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HomeViewPreview() {
    HomeView(userMail = "billykid@test.com")
}