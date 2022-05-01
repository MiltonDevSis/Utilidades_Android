package com.mpfcoding.forms_with_usecase.usecase

import org.junit.Assert
import org.junit.Before
import org.junit.Test


class ValidatePasswordUseCaseTest{

    private lateinit var validatePassword: ValidatePasswordUseCase

    @Before
    fun init(){
        validatePassword = ValidatePasswordUseCase()
    }

    @Test
    fun testPasswordLengh(){
        val result = validatePassword.execute("qwe12")
        Assert.assertEquals(result, ValidationResult(
            sucessful = false,
            errorMessage = "The password needs to consist of at least 6 characters"
        ))
    }

    @Test
    fun testPasswordContainsLettersAndDigits(){
        val result = validatePassword.execute("qweqwe")
        Assert.assertEquals(result, ValidationResult(
            sucessful = false,
            errorMessage = "The password needs to cotain at least letter and digit"
        ))
    }

    @Test
    fun testPasswordValid(){
        val result = validatePassword.execute("qwe123")
        Assert.assertEquals(result, ValidationResult(
            sucessful = true
        ))
    }
}