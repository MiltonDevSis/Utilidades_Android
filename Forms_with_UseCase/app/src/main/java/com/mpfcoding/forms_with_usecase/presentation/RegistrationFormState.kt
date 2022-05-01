package com.mpfcoding.forms_with_usecase.presentation

data class RegistrationFormState(
    val email: String = "",
    val emailError: String? = null,
    val password: String = "",
    val passwordError: String? = null,
    val repeatPassword: String = "",
    val repeatPasswordError: String? = null,
    val acceptedTerms: Boolean = false,
    val termsError: String? = null
)