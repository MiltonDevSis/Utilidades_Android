package com.mpfcoding.forms_with_usecase.usecase

class ValidateRepeatedPasswordUseCase {

    fun execute(password: String, repeatedPassword: String): ValidationResult {
        if (password != repeatedPassword) {
            return ValidationResult(
                sucessful = false,
                errorMessage = "The passwords don´t match"
            )
        }
        return ValidationResult(
            sucessful = true
        )
    }
}