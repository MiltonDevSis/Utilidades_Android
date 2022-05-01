package com.mpfcoding.forms_with_usecase.usecase

data class ValidationResult(
    val sucessful: Boolean,
    val errorMessage: String? = null
)