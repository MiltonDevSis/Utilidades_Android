package com.mpfcoding.forms_with_usecase.usecase

class ValidateTermsUseCase {

    fun execute(acceptedTerms: Boolean): ValidationResult {
        if (!acceptedTerms) {
            return ValidationResult(
                sucessful = false,
                errorMessage = "Please accept the terms"
            )
        }
        return ValidationResult(
            sucessful = true
        )
    }
}