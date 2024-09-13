package com.example.ucu2024_android_navigation_views_styles_leccion1.state

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.MutableState
import com.example.ucu2024_android_navigation_views_styles_leccion1.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class LoginState(onLoginNavigation: (String) -> Unit, scope: CoroutineScope, snackbarHostState: SnackbarHostState) {
    val nameInput: LoginInput = LoginInput(name = "email", placeholder = "Email input")
    val passwordInput: PasswordLoginInput = PasswordLoginInput("password", "Password input")
    val ssoProviders = mapOf(
        "Google" to R.drawable.google,
        "Facebook" to R.drawable.facebook,
    )

    val clear: () -> Unit = {
        this.nameInput.value!!.value = ""
        this.passwordInput.value!!.value = ""
    }

    val login: () -> Unit = {
        val checkName: (String?) -> Boolean = { value -> (value?.endsWith("@test.com", ignoreCase = true))?:false}
        val checkPass: (String?) -> Boolean = { value -> (value?.contentEquals("Password123"))?:false}
        if (checkName(nameInput.value?.value) && checkPass(passwordInput.value?.value)) {
            onLoginNavigation(nameInput.value?.value!!)
        }
        else {
            scope.launch {
                snackbarHostState
                    .showSnackbar(
                        "Incorrect credentials", duration = SnackbarDuration.Long,
                    )
            }
        }
    }
}

class PasswordLoginInput(
    name: String,
    placeholder: String
) : LoginInput(name, placeholder) {
    override val isPassword: Boolean = true
    var isVisible: MutableState<Boolean>? = null

    val setVisibility: (Boolean) -> Unit = { newValue ->
        this.isVisible!!.value = newValue
    }
}

open class LoginInput(
    val name: String,
    val placeholder: String,
) {
    var value: MutableState<String>? = null
    open val isPassword: Boolean = false
}