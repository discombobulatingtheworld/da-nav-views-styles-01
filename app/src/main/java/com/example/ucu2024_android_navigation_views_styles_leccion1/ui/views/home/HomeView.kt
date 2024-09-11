package com.example.ucu2024_android_navigation_views_styles_leccion1.ui.views.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ucu2024_android_navigation_views_styles_leccion1.R
import com.example.ucu2024_android_navigation_views_styles_leccion1.ui.theme.alt1.Ucu2024androidnavigationviewsstylesleccion1Theme


@Composable
fun HomeView(modifier: Modifier, navController: NavController, userMail: String?) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize(1f),
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .weight(1.3f)
                .background(
                    MaterialTheme.colorScheme.primary
                )
//                .background(
//                    Brush.verticalGradient(
//                        listOf(
//                            Color(0xFFF39F39),
//                            Color(0xFFEF762F),
//                        )
//                    )
//                )
        ) {
            ProfileContainer(modifier, navController, userMail)
        }
        Box(
            modifier = modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            DetailsContainer(modifier, navController)
        }
    }
}

@Composable
private fun ProfileContainer(modifier: Modifier, navController: NavController, userMail: String?) {
    Box(
        modifier = modifier
            .padding(20.dp)
            .fillMaxSize(1f),
    ) {
        IconButton(onClick = { /*TODO*/ }, modifier = Modifier.align(Alignment.TopEnd)) {
            Icon(painter = painterResource(id = R.drawable.baseline_more_vert_24), contentDescription = "More", tint = MaterialTheme.colorScheme.onPrimary,)
        }
        IconButton(onClick = { /*TODO*/ }, modifier = modifier.align(Alignment.TopStart)) {
            Icon(painter = painterResource(id = R.drawable.baseline_arrow_back_24), contentDescription = "Return", tint = MaterialTheme.colorScheme.onPrimary,)
        }
    }
}

@Composable
private fun DetailsContainer(modifier: Modifier, navController: NavController) {

}






@Preview(showBackground = true)
@Composable
fun ChatViewPreview() {
    Ucu2024androidnavigationviewsstylesleccion1Theme {
        val navController = rememberNavController()

        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            HomeView(Modifier.padding(innerPadding), navController, "hallo@mail.com")
        }
    }
}