package com.example.ucu2024_android_navigation_views_styles_leccion1.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ucu2024_android_navigation_views_styles_leccion1.R
import com.example.ucu2024_android_navigation_views_styles_leccion1.state.LoginInput
import com.example.ucu2024_android_navigation_views_styles_leccion1.state.LoginState
import com.example.ucu2024_android_navigation_views_styles_leccion1.state.PasswordLoginInput
import com.example.ucu2024_android_navigation_views_styles_leccion1.ui.components.TextButtonThemed
import com.example.ucu2024_android_navigation_views_styles_leccion1.ui.theme.AppTheme

@Composable
fun LoginView(onLoginNavigation: (String) -> Unit = {}) {
    val snackbarHostState = remember { SnackbarHostState() }
    val formState = LoginState(
        onLoginNavigation,
        rememberCoroutineScope(),
        snackbarHostState,
    )
    formState.nameInput.value = remember { mutableStateOf("") }
    formState.passwordInput.value = remember { mutableStateOf("") }
    formState.passwordInput.isVisible = remember { mutableStateOf(false) }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { innerPadding ->
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.surfaceContainerHigh)
                .fillMaxSize()

        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(10.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.app_logo),
                    contentDescription = "Logo",
                    contentScale = ContentScale.FillWidth,
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurfaceVariant),
                    modifier = Modifier
                        .size(120.dp)
                )
                LoginPanel(formState)
            }
        }
    }
}

@Composable
private fun LoginPanel(formState: LoginState) {
    Column(
        modifier = Modifier
            .padding(20.dp)
    ) {
        LoginInputs(formState.nameInput, formState.passwordInput)
        LoginActions(formState)
        LoginSSO(formState.ssoProviders)
    }
}

@Composable
private fun LoginInputs(email: LoginInput, pass: LoginInput) {
    Column(
        modifier = Modifier
            .padding(vertical = 15.dp)
    ) {
        LoginInputField(input = email)
        LoginInputField(input = pass)
        Row(
            modifier = Modifier
                .fillMaxWidth(1f),
            horizontalArrangement = Arrangement.End
        ) {
            TextButton(
                onClick = { /*TODO*/ },
                shape = RoundedCornerShape(5.dp),
            ) {
                Text(text = "Forgot password?")
            }
        }
    }
}

@Composable
private fun LoginInputField(input: LoginInput) {
    val toggle: @Composable () -> Unit = @Composable { PasswordToggle(input as PasswordLoginInput) }

    TextField(
        value = input.value!!.value,
        onValueChange = { newValue -> input.value!!.value = newValue },
        singleLine = true,
        placeholder = {
            Text(text = input.placeholder)
        },
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(1f),
        visualTransformation = if (input.isPassword && !(input as PasswordLoginInput).isVisible!!.value) PasswordVisualTransformation() else VisualTransformation.None,
        trailingIcon = if (input.isPassword) toggle else null,
        colors = TextFieldDefaults.colors().copy(
            focusedContainerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
            unfocusedContainerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
            disabledContainerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
        )
    )
}

@Composable
private fun PasswordToggle(input: PasswordLoginInput) {
    val icons: Map<Boolean, Int> = mapOf(
        false to R.drawable.baseline_visibility_24,
        true to R.drawable.baseline_visibility_off_24,
    )

    IconToggleButton(
        checked = input.isVisible!!.value,
        onCheckedChange = input.setVisibility
    ) {
        Icon(
            painter = painterResource(id = icons.getValue(input.isVisible!!.value)),
            contentDescription = "Toggle visibility",
        )
    }
}

@Composable
private fun LoginActions(formState: LoginState) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(vertical = 15.dp)
            .fillMaxWidth(1f)
    ) {
        TextButtonThemed(
            text = "Login",
            modifier = Modifier.fillMaxWidth(),
            onClick = formState.login
        )
        LoginOptions()
    }
}

@Composable
private fun LoginOptions() {
    Row(
        modifier = Modifier
            .fillMaxWidth(1f)
            .padding(5.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        TextButton(
            onClick = { /*TODO*/ },
            shape = RoundedCornerShape(5.dp),
        ) {
            Text(text = "Don't have an account?")
        }
        TextButton(
            onClick = { /*TODO*/ },
            shape = RoundedCornerShape(5.dp),
        ) {
            Text(text = "Sign-up")
        }
    }
}

@Composable
private fun LoginSSO(providers: Map<String, Int>) {
    Column(
        modifier = Modifier
            .padding(vertical = 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Or login with")
        SSOBar(providers)
    }
}

@Composable
private fun SSOBar(providers: Map<String, Int>) {
    Row(
        modifier = Modifier
            .fillMaxWidth(1f)
            .padding(30.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        providers.forEach {
            SSOLogin(providerName = it.key, imageResourceId = it.value)
        }
    }
}

@Composable
private fun SSOLogin(providerName: String, imageResourceId: Int) {
    IconButton(
        onClick = { /*TODO*/ },
        modifier = Modifier
            .size(70.dp)
    ) {
        Image(
            painter = painterResource(id = imageResourceId),
            contentDescription = providerName,
        )
    }
}


@Preview(showBackground = true)
@Composable
fun LoginViewLightPreview() {
    AppTheme(
        darkTheme = false,
    ) {
        LoginView()
    }
}


@Preview(showBackground = true)
@Composable
fun LoginViewDarkPreview() {
    AppTheme(
        darkTheme = true,
    ) {
        LoginView()
    }
}