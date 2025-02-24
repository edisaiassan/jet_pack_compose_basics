package com.example.myapplication.presentation.pages.login.controller
import androidx.lifecycle.ViewModel
import com.example.myapplication.presentation.pages.login.controller.state.LoginState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginViewModel : ViewModel() {
    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    fun onEmailChange(email: String) {
        _state.value = _state.value.copy(email = email)
    }

    fun onPasswordChange(password: String) {
        _state.value = state.value.copy(password = password)
    }

    suspend fun onEnter() {
        _state.value = _state.value.copy(enabled = false)

        val emailError = validateEmail(_state.value.email)
        val passwordError = validatePassword(_state.value.password)

        //Revalidando los formularios
        if(emailError == null && passwordError == null) {
            delay(2000)
            _state.value = _state.value.copy(
                email = "",
                password = "",
                enabled = true,
                emailError = null,
                passwordError = null,
            )
        } else {
            _state.value = _state.value.copy(
                enabled = true,
                emailError = emailError,
                passwordError = passwordError,
            )
        }

    }

    private fun validateEmail(email: String): String? {
       val emailTrim = email.trim()
        return if (emailTrim.isEmpty()) {
            "Email is required"
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            "This is the correct format: name@name.com"
        } else null
    }

    private fun validatePassword(password: String): String? {
        val passwordTrim = password.trim()
        return if (passwordTrim.isEmpty()) {
            "Password y required"
        } else if (passwordTrim.length < 6) {
            "You must insert 6 characters"
        } else null
    }
}