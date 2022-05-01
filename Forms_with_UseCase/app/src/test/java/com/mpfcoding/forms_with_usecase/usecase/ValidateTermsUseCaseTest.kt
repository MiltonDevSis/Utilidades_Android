package com.mpfcoding.forms_with_usecase.usecase

import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ValidateTermsUseCaseTest{

    private lateinit var validateTerms: ValidateTermsUseCase

    @Before
    fun init(){
        validateTerms = ValidateTermsUseCase()
    }

    @Test
    fun testRepeatPasswordNotMatch(){
        val result = validateTerms.execute(false)
        Assert.assertEquals(result, ValidationResult(
            sucessful = false,
            errorMessage = "Please accept the terms"
        ))
    }

    @Test
    fun testRepeatedPasswordValid(){
        val result = validateTerms.execute(true)
        Assert.assertEquals(result, ValidationResult(
            sucessful = true
        ))
    }
}