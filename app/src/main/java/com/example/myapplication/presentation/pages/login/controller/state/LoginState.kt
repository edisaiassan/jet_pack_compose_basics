package com.example.myapplication.presentation.pages.login.controller.state

data class LoginState (
    val email: String = "",
    val password: String = "",
    val emailError: String? = null,
    val passwordError: String? = null,
    val enabled: Boolean = true,
)