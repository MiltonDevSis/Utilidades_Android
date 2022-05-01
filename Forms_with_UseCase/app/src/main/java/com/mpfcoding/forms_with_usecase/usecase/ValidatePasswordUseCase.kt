package com.mpfcoding.forms_with_usecase.usecase

class ValidatePasswordUseCase {

    fun execute(password: String): ValidationResult {
        if (password.length < 6) {
            return ValidationResult(
                sucessful = false,
                errorMessage = "The password needs to consist of at least 6 characters"
            )
        }

        val containsLettersAndDigits = password.any { it.isDigit() } &&
                password.any { it.isLetter() }

        if (!containsLettersAndDigits){
            return ValidationResult(
                sucessful = false,
                errorMessage = "The password needs to cotain at least letter and digit"
            )
        }
        return ValidationResult(
            sucessful = true
        )
    }
}