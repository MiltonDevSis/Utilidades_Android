package com.mpfcoding.forms_with_usecase.usecase

import androidx.core.util.PatternsCompat

class ValidateEmailUseCase {

    fun execute(email: String): ValidationResult {
        if (email.isBlank()) {
            return ValidationResult(
                sucessful = false,
                errorMessage = "The email can´t be blank"
            )
        }
        if (!PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()){
            return ValidationResult(
                sucessful = false,
                errorMessage = "That´s not a valid email"
            )
        }
        return ValidationResult(
            sucessful = true
        )
    }
}