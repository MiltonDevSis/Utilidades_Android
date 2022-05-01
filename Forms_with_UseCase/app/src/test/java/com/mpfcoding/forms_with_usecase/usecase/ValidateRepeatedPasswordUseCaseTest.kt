package com.mpfcoding.forms_with_usecase.usecase

import org.junit.Assert
import org.junit.Before
import org.junit.Test


class ValidateRepeatedPasswordUseCaseTest{

    private lateinit var validateRepeatedPassword: ValidateRepeatedPasswordUseCase

    @Before
    fun init(){
        validateRepeatedPassword = ValidateRepeatedPasswordUseCase()
    }

    @Test
    fun testRepeatPasswordNotMatch(){
        val result = validateRepeatedPassword.execute("qwe12", "qwe1234")
        Assert.assertEquals(result, ValidationResult(
            sucessful = false,
            errorMessage = "The passwords don´t match"
        ))
    }

    @Test
    fun testRepeatedPasswordValid(){
        val result = validateRepeatedPassword.execute("qwe123", "qwe123")
        Assert.assertEquals(result, ValidationResult(
            sucessful = true
        ))
    }
}